package org.example.microserviciobilling.controller;

import jakarta.validation.Valid;
import org.example.microserviciobilling.dto.TarifaDto;
import org.example.microserviciobilling.service.TarifaService;
import org.example.microserviciobilling.service.exception.TarifaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaController {
    private TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }
    @GetMapping("/")
    public ResponseEntity<List<TarifaDto>> getAll(){
        List<TarifaDto> tarifas = tarifaService.findAll();
        if (tarifas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarifas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaDto> getById(@PathVariable Long id) {
        TarifaDto tarifa = tarifaService.findById(id);
        if (tarifa == null){
            throw new TarifaNotFoundException("No se encontro la tarifa con id: " +id);
        }
        return ResponseEntity.ok(tarifa);
    }

    @PostMapping("")
    public ResponseEntity<TarifaDto> createTarifa(@Valid @RequestBody TarifaDto dto){
        TarifaDto tarifaNueva = tarifaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifaNueva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable Long id) {
        TarifaDto tarifa = tarifaService.findById(id);
        if(tarifa == null) {
            throw new TarifaNotFoundException("No se encontro la tarifa con id: " +id);
        }
        tarifaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}