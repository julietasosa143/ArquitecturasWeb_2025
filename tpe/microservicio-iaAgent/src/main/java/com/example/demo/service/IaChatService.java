package com.example.demo.service;

import com.example.demo.dto.CuentaAnuladaDTO;
import com.example.demo.dto.PremiumResponse;
import com.example.demo.dto.RespuestaApi;
import com.example.demo.dto.TarifaDTO;
import com.example.demo.service.llm.GroqClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class IaChatService {

    @Autowired
    private GroqClient groqClient;

    @Autowired
    private RestTemplate restTemplate;

    // Endpoints
    private static final String MONOPATIN_SERVICE_URL = "http://localhost:8090/api/monopatines";
    private static final String VIAJES_SERVICE_URL = "http://localhost:8090/api/viajes";
    private static final String FACTURACION_SERVICE_URL = "http://localhost:8090/api/facturas";
    private static final String USUARIO_SERVICE_URL = "http://localhost:8090/api/usuarios";
    private static final String CUENTA_SERVICE_URL = "http://localhost:8090/api/cuentas";
    private static final String TARIFA_SERVICE_URL = "http://localhost:8090/api/tarifas";

    public ResponseEntity<?> procesarPrompt(String promptUsuario, boolean esPremium) {
        if (!esPremium) {
            return ResponseEntity.status(403)
                    .body(new RespuestaApi<>(false, "Acceso denegado: solo usuarios premium", null));
        }

        try {
            System.out.println("=== INICIO procesarPrompt ===");
            System.out.println("Prompt recibido: " + promptUsuario);
            //    Enviar prompt a Groq
            String respuestaIa = groqClient.preguntar(
                    """
                            Analizá el texto del usuario y devolvé SIEMPRE estas dos líneas:
                            
                                               ACCION: <el nombre de la acción detectada>
                                               PARAMETROS: <lista de parámetros clave=valor encontrados>
                            
                                               Acciones permitidas (no inventar otras):
                                               - reporteMantenimientoConPausa
                                               - reporteMantenimientoSinPausa
                                               - anularCuenta
                                               - monopatinesMasViajes
                                               - totalFacturado
                                               - usuariosMasActivos
                                               - ajustarTarifa
                                               - monopatinesCercanos
                                               - reporteUsoUsuario
                                               - reporteUsoUsuarioConAsociados
                            
                                               Reglas:
                                               - Siempre respondé ACCION y PARAMETROS.
                                               - Aunque el pedido esté mal escrito, devolvé lo más probable.
                                               - Si detectás números (id, meses, año, precio, precioEspecial), incluilos.
                                               - Todos los parámetros deben aparecer SIEMPRE, aunque sea con valores por defecto.
                                               - No uses acentos en las claves.
                                               - No agregues explicación.
                                               - Formato EXACTO:
                            
                                               ACCION: <nombre>
                                               PARAMETROS: id=..., usuarioId=..., cuentaId=..., anio=..., mesInicio=..., mesFin=..., incluyePausas=..., tipoUsuario=..., minViajes=..., precio=..., precioEspecial=...
                            
                                               Ahora procesá este pedido del usuario:
                            """
                            +promptUsuario);
            System.out.println("Respuesta de Groq: " + respuestaIa);

            // Interpretar la respuesta (ej: "reporteUsoMonopatines", "anularCuenta")
            String accion = extraerAccion(respuestaIa);
            System.out.println("Accion detectada: " + accion);
            Map<String, Object> parametros = extraerParametros(respuestaIa);
            System.out.println("Parametros extraidos: " + parametros);


            // Ejecutar la acción correspondiente llamando al microservicio correcto
            Object resultado = switch (accion) {

                case "reporteMantenimientoSinPausa" -> {
                    Long monopatinId = getLongParam(parametros, "id", 1L);
                    yield restTemplate.getForObject(
                            MONOPATIN_SERVICE_URL + "/reporteMantenimiento/sinPausa/{id}",
                            Object.class,
                            Map.of("id", monopatinId)
                    );
                }

                case "reporteMantenimientoConPausa" -> {
                    Long monopatinId = getLongParam(parametros, "id", 1L);
                    yield restTemplate.getForObject(
                            MONOPATIN_SERVICE_URL + "/reporteMantenimiento/conPausa/{id}",
                            Object.class,
                            Map.of("id", monopatinId)
                    );
                }

                case "reporteUsoMonopatines" -> {
                    // fallback legacy (por si Groq sigue devolviendo la versión vieja)
                    Long monopatinId = getLongParam(parametros, "id", 1L);
                    Boolean incluye = getBooleanParam(parametros, "incluyePausas", false);

                    String url = incluye
                            ? MONOPATIN_SERVICE_URL + "/reporteMantenimiento/conPausa/{id}"
                            : MONOPATIN_SERVICE_URL + "/reporteMantenimiento/sinPausa/{id}";

                    yield restTemplate.getForObject(url, Object.class, Map.of("id", monopatinId));
                }

                case "anularCuenta" -> {
                    ResponseEntity<String> r = restTemplate.exchange(
                            CUENTA_SERVICE_URL + "/anular/{id}",
                            HttpMethod.PUT,
                            null,
                            String.class,
                            parametros
                    );
                    yield r.getBody();
                }


                case "monopatinesMasViajes" -> restTemplate.getForObject(
                        MONOPATIN_SERVICE_URL + "/cantidadViajes/anio?anio={anio}&minViajes={minViajes}",
                        Object.class, parametros);
                case "totalFacturado" -> restTemplate.getForObject(
                        FACTURACION_SERVICE_URL + "/reporteXmeses?mesInicio={mesInicio}&mesFin={mesFin}&anio={anio}",
                        Object.class, parametros);
                case "usuariosMasActivos" -> {
                    Map<String, Object> params = new HashMap<>();
                    params.put("mes", parametros.get("mesInicio"));
                    params.put("anio", parametros.get("anio"));
                    params.put("tipoUsuario", parametros.get("tipoUsuario"));

                    yield restTemplate.getForObject(
                            USUARIO_SERVICE_URL + "/usuariosRecurrentes?mes={mes}&anio={anio}&tipoUsuario={tipoUsuario}",
                            Object.class,
                            params
                    );
                }

                case "ajustarTarifa" -> {
                   int anio = Integer.parseInt(parametros.get("anio").toString());
                    int mesInicio = Integer.parseInt(parametros.get("mesInicio").toString());
                    int mesFin = Integer.parseInt(parametros.get("mesFin").toString());

                    LocalDate fechaCreacion = LocalDate.of(anio, mesInicio, 1);
                    LocalDate fechaExpiracion = LocalDate.of(anio, mesFin, 1).withDayOfMonth(
                            YearMonth.of(anio, mesFin).lengthOfMonth()
                    );

                    double precio = Double.parseDouble(parametros.get("precio").toString());
                    double precioEspecial = Double.parseDouble(parametros.get("precioEspecial").toString());

                    TarifaDTO dto = new TarifaDTO(null, fechaCreacion, fechaExpiracion, precio, precioEspecial);

                    yield restTemplate.postForObject(
                            TARIFA_SERVICE_URL + "/ajustar",
                            dto,
                            Object.class
                    );
                }


                case "monopatinesCercanos" -> restTemplate.getForObject(
                        USUARIO_SERVICE_URL + "/monopatinesCercanos/{usuarioId}",
                        Object.class, parametros
                );

                case "reporteUsoUsuario" -> {
                    Map<String, Object> params = new HashMap<>();
                    params.put("mes", parametros.get("mesInicio"));
                    params.put("anio", parametros.get("anio"));
                    params.put("usuarioId", parametros.get("usuarioId"));
                    yield restTemplate.getForObject(
                        USUARIO_SERVICE_URL + "/reporteUso/{usuarioId}?mes={mes}&anio={anio}",
                        Object.class, params
                );
                }
                case "reporteUsoUsuarioConAsociados" -> {
                    Map<String, Object> params = new HashMap<>();
                    params.put("mes", parametros.get("mesInicio"));
                    params.put("anio", parametros.get("anio"));
                    params.put("usuarioId", parametros.get("usuarioId"));
                    yield restTemplate.getForObject(
                            USUARIO_SERVICE_URL + "/reporteUsoConAsociados/{usuarioId}?mes={mes}&anio={anio}",
                            Object.class, params
                    );
                }

                default -> "Acción desconocida: " + accion;
            };

            return ResponseEntity.ok(new RespuestaApi<>(true, "Consulta ejecutada correctamente", resultado));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RespuestaApi<>(false, "Error al procesar prompt: " + e.getMessage(), null));
        }
    }

    private String extraerAccion(String respuestaIa) {
        for (String linea : respuestaIa.split("\n")) {
            if (linea.toLowerCase().startsWith("accion:")) {
                return linea.split(":", 2)[1].trim();
            }
        }
        return "accionDesconocida";
    }

    private Map<String, Object> extraerParametros(String respuestaIa) {
        Map<String, Object> params = new HashMap<>();

        String linea = Arrays.stream(respuestaIa.split("\n"))
                .filter(l -> l.toLowerCase().startsWith("parametros:"))
                .findFirst()
                .orElse("");


        linea = linea.replace("PARAMETROS:", "").trim();

        String[] pares = linea.split(",");

        for (String par : pares) {
            if (!par.contains("=")) continue;

            String[] kv = par.split("=", 2);
            String key = kv[0].trim();
            String value = kv[1].trim();


            if (value.isEmpty()) continue;


            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("si")) {
                params.put(key, true);
                continue;
            }
            if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no")) {
                params.put(key, false);
                continue;
            }

            if (value.matches("\\d+")) {
                params.put(key, Integer.parseInt(value));
                continue;
            }


            params.put(key, value);
        }

        return params;
    }


    public boolean esUsuarioPremium(String email) {
        PremiumResponse resp = restTemplate.getForObject(
                "http://localhost:8090/api/usuarios/esPremium?email=" + email,
                PremiumResponse.class
        );
        return resp != null && Boolean.TRUE.equals(resp.getPremium());
    }


    private Long getLongParam(Map<String, Object> params, String key, Long defaultValue) {
        Object v = params.get(key);
        if (v == null) return defaultValue;
        if (v instanceof Long) return (Long) v;
        if (v instanceof Integer) return ((Integer) v).longValue();
        if (v instanceof Number) return ((Number) v).longValue();
        if (v instanceof String) {
            String s = ((String) v).trim();
            if (s.isEmpty()) return defaultValue;
            try { return Long.parseLong(s); } catch (NumberFormatException e) {
                try { return (long) Double.parseDouble(s); } catch (Exception ex) { return defaultValue; }
            }
        }
        return defaultValue;
    }

    private Boolean getBooleanParam(Map<String, Object> params, String key, Boolean defaultValue) {
        Object v = params.get(key);
        if (v == null) return defaultValue;
        if (v instanceof Boolean) return (Boolean) v;
        String s = String.valueOf(v).trim().toLowerCase();
        if (s.isEmpty()) return defaultValue;
        if (s.equals("true") || s.equals("si") || s.equals("sí") || s.equals("1")) return true;
        if (s.equals("false") || s.equals("no") || s.equals("0")) return false;
        return defaultValue;
    }
}