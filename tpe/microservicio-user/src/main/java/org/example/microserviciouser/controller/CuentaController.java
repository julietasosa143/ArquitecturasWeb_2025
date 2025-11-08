package org.example.microserviciouser.controller;

import org.example.microserviciouser.entities.Cuenta;
import lombok.RequiredArgsConstructor;
import org.example.microserviciouser.service.exception.CuentaNotFoundException;
import org.example.microserviciouser.service.exception.CuentaYaAnuladaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.microserviciouser.service.CuentaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/")
    public ResponseEntity<List<Cuenta>> getAll(){
        List<Cuenta> users = cuentaService.getAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getById(@PathVariable Long id){
        Cuenta cuenta = cuentaService.getById(id);
        if(cuenta==null){
            return ResponseEntity.notFound().build();
        }else  {
            return ResponseEntity.ok(cuenta);
        }
    }

    @PostMapping("")
    public ResponseEntity<Cuenta> save(@RequestBody Cuenta user) {
        Cuenta nuevo = cuentaService.save(user);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<String> anular(@PathVariable Long id) {
        try {
            cuentaService.anularCuenta(id);
            return ResponseEntity.ok("Cuenta inhabilitada correctamente.");
        } catch (CuentaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (CuentaYaAnuladaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
