package org.example.microserviciouser.controller;

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

}
