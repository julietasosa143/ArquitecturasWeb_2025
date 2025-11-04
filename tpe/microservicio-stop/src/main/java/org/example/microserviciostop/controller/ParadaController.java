package org.example.microserviciostop.controller;

import jakarta.validation.Valid;
import org.example.microserviciostop.DTO.ParadaDTO;
import org.example.microserviciostop.service.exception.ParadaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.microserviciostop.service.ParadaService;

import java.util.List;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    private final ParadaService paradaService;

    public ParadaController(ParadaService paradaService) {
        this.paradaService = paradaService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ParadaDTO>> getAll(){
        List<ParadaDTO> paradas = paradaService.findAll();
        if (paradas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paradas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParadaDTO> getById(@PathVariable("id") Long id) {
        ParadaDTO parada = paradaService.findById(id);
        if (parada == null) {
            throw new ParadaNotFoundException("No se encontró la parada con id: " + id);
        }
        return ResponseEntity.ok(parada);
    }

    @PostMapping("")
    public ResponseEntity<ParadaDTO> save(@Valid @RequestBody ParadaDTO dto) {
        ParadaDTO paradaNueva = paradaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paradaNueva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        paradaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

