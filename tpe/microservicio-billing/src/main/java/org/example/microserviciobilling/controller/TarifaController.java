package org.example.microserviciobilling.controller;

import jakarta.validation.Valid;
import org.example.microserviciobilling.dto.TarifaDTO;
import org.example.microserviciobilling.service.TarifaService;
import org.example.microserviciobilling.service.exception.TarifaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<List<TarifaDTO>> getAll(){
        List<TarifaDTO> tarifas = tarifaService.findAll();
        if (tarifas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarifas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaDTO> getById(@PathVariable Long id) {
        TarifaDTO tarifa = tarifaService.findById(id);
        if (tarifa == null){
            throw new TarifaNotFoundException("No se encontro la tarifa con id: " +id);
        }
        return ResponseEntity.ok(tarifa);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/ajustar")
    public ResponseEntity<TarifaDTO> ajustarTarifa(@Valid @RequestBody TarifaDTO dto) {
        try {
            TarifaDTO tarifa = tarifaService.ajustar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifa);
        }catch (TarifaNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }//cambiar fecha de expiracion por inicio y expiracion

    @PostMapping("")
    public ResponseEntity<TarifaDTO> createTarifa(@Valid @RequestBody TarifaDTO dto){
        TarifaDTO tarifaNueva = tarifaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifaNueva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable Long id) {
        TarifaDTO tarifa = tarifaService.findById(id);
        if(tarifa == null) {
            throw new TarifaNotFoundException("No se encontro la tarifa con id: " +id);
        }
        tarifaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}