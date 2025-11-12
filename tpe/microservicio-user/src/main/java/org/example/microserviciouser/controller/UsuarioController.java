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
import org.springframework.web.bind.annotation.*;
import org.example.microserviciouser.service.UsuarioService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
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


}
