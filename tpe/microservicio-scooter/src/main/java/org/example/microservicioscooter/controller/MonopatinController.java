package org.example.microservicioscooter.controller;

import lombok.RequiredArgsConstructor;
import org.example.microservicioscooter.dto.ReporteMantenimientoDTOResponse;
import org.example.microservicioscooter.entities.Monopatin;
import org.example.microservicioscooter.service.MonopatinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monopatines")
@RequiredArgsConstructor
public class MonopatinController {

    private final MonopatinService monopatinService;


    @PutMapping("/{id}/estado")
    public ResponseEntity<Monopatin> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {

        try {
            Monopatin actualizado = monopatinService.cambiarEstado(id, estado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Monopatin> agregarMonopatin(@RequestBody Monopatin nuevo) {
        Monopatin creado = monopatinService.agregarMonopatin(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMonopatin(@PathVariable Long id) {
        try {
            monopatinService.eliminarMonopatin(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reporteMantenimiento/sinPausa/{id}")
    public ResponseEntity<ReporteMantenimientoDTOResponse> obtenerReporteSinPausa(@PathVariable Long id) {
        try {
            ReporteMantenimientoDTOResponse reporte = monopatinService.getReporteMantenimientoSinPausa(id);
            return ResponseEntity.ok().body(reporte);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reporteMantenimiento/conPausa/{id}")
    public ResponseEntity<ReporteMantenimientoDTOResponse> obtenerReporteConPausa(@PathVariable Long id) {

        try {
            ReporteMantenimientoDTOResponse reporte = monopatinService.getReporteMantenimientoConPausa(id);
            return ResponseEntity.ok().body(reporte);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(" ")
    //hacer getAll
}