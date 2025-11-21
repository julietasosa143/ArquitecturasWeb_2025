package org.example.microservicioscooter.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import org.example.microservicioscooter.dto.MonopatinDTO;
import org.example.microservicioscooter.dto.MonopatinResponseDTO;
import org.example.microservicioscooter.dto.ReporteMantenimientoDTOResponse;
import org.example.microservicioscooter.entities.Monopatin;
import org.example.microservicioscooter.service.MonopatinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatines")
@RequiredArgsConstructor
public class MonopatinController {

    private final MonopatinService monopatinService;


    @PutMapping("/{id}/estado")
    public ResponseEntity<Monopatin> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        try {
            Monopatin actualizado = monopatinService.cambiarEstado(id, estado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Monopatin> agregarMonopatin(@RequestBody Monopatin nuevo) {
        Monopatin creado = monopatinService.agregarMonopatin(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMonopatin(@PathVariable Long id) {
        try {
            monopatinService.eliminarMonopatin(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(
            summary = "Generar reporte de mantenimiento sin pausa",
            description = "Devuelve un reporte de uso del monopatín sin contar los tiempos de pausa."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reporteMantenimiento/sinPausa/{id}")
    public ResponseEntity<ReporteMantenimientoDTOResponse> obtenerReporteSinPausa(@PathVariable Long id) {
        try {
            ReporteMantenimientoDTOResponse reporte = monopatinService.getReporteMantenimientoSinPausa(id);
            return ResponseEntity.ok().body(reporte);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(
            summary = "Generar reporte de mantenimiento con pausa",
            description = "Devuelve un reporte de uso del monopatín considerando los tiempos de pausa."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reporteMantenimiento/conPausa/{id}")
    public ResponseEntity<ReporteMantenimientoDTOResponse> obtenerReporteConPausa(@PathVariable Long id) {

        try {
            ReporteMantenimientoDTOResponse reporte = monopatinService.getReporteMantenimientoConPausa(id);
            return ResponseEntity.ok().body(reporte);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(
            summary = "Consultar monopatines con más de X viajes por año",
            description = "Devuelve un listado de monopatines que superan una cantidad mínima de viajes en un año dado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado generado correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping("/cantidadViajes/anio")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesConMasDeXViajesXAnio(
            @RequestParam int anio,
            @RequestParam int minViajes){
        try{
            List<MonopatinDTO> monopatines= monopatinService.getMonopatinesViajesAnio(anio,minViajes);
            return ResponseEntity.ok().body(monopatines);
        }catch ( Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }


    }
    @GetMapping("/{id}")
    public ResponseEntity<MonopatinDTO> getById(@RequestParam long id ){
        try{
            MonopatinDTO monopatin = monopatinService.findById(id);
            return ResponseEntity.ok().body(monopatin);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping ("/")
    public ResponseEntity<List<MonopatinDTO>> getMonopatines(){
        try{
            List<MonopatinDTO> monopatines= monopatinService.getAll();
            return ResponseEntity.ok().body(monopatines);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
@Operation(
        summary = "Obtener monopatines cercanos",
        description = "Devuelve un listado de monopatines cercanos segun la ubicacion (coordenadas X e Y) enviada por parametro."
)
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listados obtenido correctamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos o error en la solicitud"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
})
    @GetMapping("/monopatinesCercanos")
    public ResponseEntity<List<MonopatinResponseDTO>> getMonopatinesCercanos(@RequestParam float x, @RequestParam float y){
        try{
            List<MonopatinResponseDTO> monopatinesCercanos = monopatinService.getMonopatinesCercanos(x, y);
            return ResponseEntity.ok().body(monopatinesCercanos);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}