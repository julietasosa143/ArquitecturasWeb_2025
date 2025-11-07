package org.example.microserviciotrip.controller;

import org.example.microserviciotrip.dto.ViajeDTO;
import jakarta.validation.Valid;
import org.example.microserviciotrip.entities.Viaje;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.microserviciotrip.services.ViajeService;
import org.example.microserviciotrip.services.exception.ViajeNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    private ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping
    public ResponseEntity<List<ViajeDTO>> getAll() {
        List<ViajeDTO> viajes = viajeService.findAll();
        return ok(viajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> getById(@PathVariable Integer id) {
        ViajeDTO viaje = viajeService.findById(id);
        if (viaje == null) {
            throw new ViajeNotFoundException("No se encontró el viaje con id: " + id);
        }
        return ok(viaje);
    }


    @PostMapping
    public ResponseEntity<ViajeDTO> createViaje(@Valid @RequestBody ViajeDTO dto) {
        ViajeDTO nuevoViaje = viajeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoViaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        ViajeDTO viaje = viajeService.findById(id);
        if (viaje == null) {
            throw new ViajeNotFoundException("No se puede eliminar: viaje con id " + id + " no existe");
        }
        viajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/porMonopatin/{id}")
    public ResponseEntity<List<Viaje>> getAllPorMonopatin(
            @PathVariable long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ultimoService)
    {
        List<Viaje> viajes = viajeService.getViajesXMonopatin(id, ultimoService);

        if(!viajes.isEmpty()) {
            return ok(viajes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
