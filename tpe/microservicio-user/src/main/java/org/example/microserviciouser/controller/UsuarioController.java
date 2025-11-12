package org.example.microserviciouser.controller;

import org.example.microserviciouser.dto.UsuarioDTO;
import org.example.microserviciouser.entities.Usuario;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/buscarPorEmail")
    public ResponseEntity<UsuarioDTO> getUsuarioByEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.findByEmail(email).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO dto = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getRol(), usuario.getPassword(),usuario.getEmail());
        return ResponseEntity.ok(dto);
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


}
