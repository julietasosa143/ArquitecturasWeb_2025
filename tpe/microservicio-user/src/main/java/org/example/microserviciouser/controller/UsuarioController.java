package org.example.microserviciouser.controller;

import org.example.microserviciouser.dto.MonopatinResponseDTO;
import org.example.microserviciouser.dto.ParadaResponseDTO;
import org.example.microserviciouser.dto.ReporteDeUsoDTO;
import org.example.microserviciouser.dto.UsuarioDTO;
import org.example.microserviciouser.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.example.microserviciouser.feignClient.ParadaFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.microserviciouser.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ParadaFeignClient paradaFeignClient;


    @GetMapping ("/")
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> users = usuarioService.getAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        Usuario usuario = usuarioService.getById(id);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else  {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
        Usuario nuevo = usuarioService.save(user);
        return ResponseEntity.ok(nuevo);
    }

    @Operation(
            summary = "Usuarios que más utilizan monopatines",
            description = "Devuelve un listado filtrado por mes, año y tipo de usuario."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron usuarios recurrentes"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/usuariosRecurrentes")
    public ResponseEntity<List<UsuarioDTO>> getUsiariosRecurrente(
            @RequestParam int mes, @RequestParam int anio, @RequestParam String tipoUsuario
    ){
        System.out.println(" Entró al Controller /usuariosRecurrentes con mes=" + mes + ", anio=" + anio + ", tipo=" + tipoUsuario);
        try{
            List<UsuarioDTO> recurrentes=usuarioService.getUsuariosRecurrente(mes,anio,tipoUsuario);
            System.out.println(" Llamó al Service, tamaño lista: " + recurrentes.size());
            if(recurrentes.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return  ResponseEntity.ok(recurrentes);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN','MANTENIMIENTO')")
    @GetMapping("/monopatinesCercanos/{id}")
    public ResponseEntity<List<MonopatinResponseDTO>> getMonopatinesCercanos(@PathVariable long id){
        try {
            List<MonopatinResponseDTO> monopatinesCercanos = usuarioService.getMonopatinesCercanos(id);
            return ResponseEntity.ok(monopatinesCercanos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscarPorEmail")
    public ResponseEntity<UsuarioDTO> getUsuarioByEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.findByEmail(email).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO dto = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getRol(), usuario.getPassword(),usuario.getEmail());
        return ResponseEntity.ok(dto);
    }
    @Operation(
            summary = "Reporte de uso del usuario",
            description = "Devuelve cuánto usó el usuario los monopatines en un período determinado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyAuthority('USER','MANTENIMIENTO','ADMIN')")
    @GetMapping("/reporteUso/{id}")
    public ResponseEntity<ReporteDeUsoDTO> getReporteDeUso(@PathVariable long id,
                                                           @RequestParam int mes,
                                                           @RequestParam int anio){
        try{
            ReporteDeUsoDTO reporte = usuarioService.getReporteDeUso(id, mes, anio);
            return ResponseEntity.ok(reporte);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(
            summary = "Reporte de uso del usuario y sus asociados",
            description = "Incluye los usos del usuario principal y de otros usuarios asociados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyAuthority('USER','MANTENIMIENTO','ADMIN')")
    @GetMapping("/reporteUsoConAsociados/{id}")
    public ResponseEntity<List<ReporteDeUsoDTO>> getReporteDeUsoConAsociados(@PathVariable long id,
                                                                             @RequestParam int mes,
                                                                             @RequestParam int anio){
        try{
            List<ReporteDeUsoDTO> reporte = usuarioService.getReporteDeUsoConAsociados(id, mes, anio);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/esPremium")
    public ResponseEntity<Map<String, Boolean>> esPremium(@RequestParam String email) {
        System.out.println("° LLEGÓ PETICIÓN a /esPremium con email: " + email);
        boolean premium = usuarioService.esPremium(email);
        System.out.println("° Resultado de esPremium: " + premium);
        return ResponseEntity.ok(Map.of("premium", premium));
    }
}
