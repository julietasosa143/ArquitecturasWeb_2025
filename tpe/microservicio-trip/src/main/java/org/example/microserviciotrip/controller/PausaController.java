package org.example.microserviciotrip.controller;

import org.example.microserviciotrip.DTO.PausaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.microserviciotrip.services.PausaService;
import org.example.microserviciotrip.services.exception.PausaNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/pausas")
public class PausaController {

    private PausaService pausaService;
    public PausaController(PausaService pausaService) {
        this.pausaService = pausaService;
    }

    @GetMapping
    public ResponseEntity<List<PausaDTO>> findAll() {
        List<PausaDTO> pausas = pausaService.findAll();
        return ResponseEntity.ok(pausas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PausaDTO> findById(@PathVariable Integer id) {
        PausaDTO pausaDTO= pausaService.findById(id);
        if(pausaDTO==null){
            throw new PausaNotFoundException("Pausa no encontrada");
        }
        return ResponseEntity.ok(pausaDTO);
    }

    @PostMapping
    public ResponseEntity<PausaDTO> save(@RequestBody PausaDTO pDTO) {
        PausaDTO pausaDTO=pausaService.save(pDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pausaDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PausaDTO> delete(@PathVariable Integer id) {
        PausaDTO pausaDTO= pausaService.findById(id);
        if(pausaDTO==null){
            throw new PausaNotFoundException("No se puede agregar el viaje con id: " + id);
        }
        pausaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
