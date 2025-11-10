package org.example.microserviciobilling.controller;

import jakarta.validation.Valid;
import org.example.microserviciobilling.dto.FacturaDTO;
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
    public ResponseEntity<List<FacturaDTO>> getAll(){
        List<FacturaDTO> facturas = facturaService.findAll();
        if (facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<FacturaDTO> getById (@PathVariable ("id") Long id) {
    FacturaDTO factura = facturaService.findById(id);
    if (factura == null){
        throw new FacturaNotFoundException("No se encontro la factura con id:" + id);
    }
    return ResponseEntity.ok(factura);
    }

    @PostMapping("")
    public ResponseEntity<FacturaDTO> createFactura(@Valid @RequestBody FacturaDTO dto) {
        FacturaDTO facturaNueva = facturaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaNueva);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        FacturaDTO factura = facturaService.findById(id);
        if (factura == null){
            throw new FacturaNotFoundException("No se encontro la factura con id:" + id);
        }
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reporteXmeses")
    public ResponseEntity<Double> reporteXmeses(@RequestParam int mesInicio,
                                                          @RequestParam int mesFin,
                                                @RequestParam int anio){
        try{
            Double totalFacturado = facturaService.getReporte(mesInicio, mesFin, anio);
            if (totalFacturado == null) {
                // Devuelve 404 si no hay datos, o 204 (No Content)
                return ResponseEntity.notFound().build();
            }else {
                return ResponseEntity.ok(totalFacturado);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
