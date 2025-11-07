package org.example.microserviciobilling.controller;

import jakarta.validation.Valid;
import org.example.microserviciobilling.dto.FacturaDto;
import org.example.microserviciobilling.service.FacturaService;
import org.example.microserviciobilling.service.exception.FacturaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {


    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<FacturaDto>> getAll(){
        List<FacturaDto> facturas = facturaService.findAll();
        if (facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<FacturaDto> getById (@PathVariable ("id") Long id) {
    FacturaDto factura = facturaService.findById(id);
    if (factura == null){
        throw new FacturaNotFoundException("No se encontro la factura con id:" + id);
    }
    return ResponseEntity.ok(factura);
    }

    @PostMapping("")
    public ResponseEntity<FacturaDto> createFactura(@Valid @RequestBody FacturaDto dto) {
        FacturaDto facturaNueva = facturaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaNueva);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        FacturaDto factura = facturaService.findById(id);
        if (factura == null){
            throw new FacturaNotFoundException("No se encontro la factura con id:" + id);
        }
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
