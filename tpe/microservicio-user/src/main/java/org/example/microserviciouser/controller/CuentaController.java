package org.example.microserviciouser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.microserviciouser.entities.Cuenta;
import lombok.RequiredArgsConstructor;
import org.example.microserviciouser.service.exception.CuentaNotFoundException;
import org.example.microserviciouser.service.exception.CuentaYaAnuladaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(
            summary = "Anular cuenta",
            description = "Inhabilita totalmente una cuenta de usuario para el uso de la aplicación."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cuenta anulada correctamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/anular/{id}")
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
