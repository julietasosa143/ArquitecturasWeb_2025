package controller;

import entities.Cuenta;
import entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CuentaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
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
}
