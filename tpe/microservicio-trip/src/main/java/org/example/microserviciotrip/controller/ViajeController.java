package org.example.microserviciotrip.controller;

import org.example.microserviciotrip.dto.ViajeDTO;
import jakarta.validation.Valid;
import org.example.microserviciotrip.dto.ViajeDTOfin;
import org.example.microserviciotrip.dto.ViajeDTOinicio;
import org.example.microserviciotrip.entities.Viaje;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.microserviciotrip.services.ViajeService;
import org.example.microserviciotrip.services.exception.ViajeNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    private ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ViajeDTO>> getAll() {
        List<ViajeDTO> viajes = viajeService.findAll();
        return ok(viajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> getById(@PathVariable long id) {
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
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        ViajeDTO viaje = viajeService.findById(id);
        if (viaje == null) {
            throw new ViajeNotFoundException("No se puede eliminar: viaje con id " + id + " no existe");
        }
        viajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/porMonopatin/{id}")
    public ResponseEntity<List<ViajeDTO>> getAllPorMonopatin(
            @PathVariable long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ultimoService)
    {
        List<ViajeDTO> viajes = viajeService.getViajesXMonopatin(id, ultimoService);

        if(!viajes.isEmpty()) {
            return ok(viajes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/porAnioViajes")
    public ResponseEntity<List<Long>> getMonopatinesXViajeAnio(
            @RequestParam int anio,
            @RequestParam int minViajes
    ){
        List<Long> ids = viajeService.getMonopatinesXViajeXAnio(anio,minViajes);

        if(!ids.isEmpty()) {
            return ok(ids);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/usuariosRecurrentes")
    public  ResponseEntity<List<Long>>getUsuariosRecurrentes(
            @RequestParam int mes, @RequestParam int anio
    ){
        try{
            List<Long> idRecurrentes = viajeService.getUsuariosRecurrentes(mes,anio);
            if(idRecurrentes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }else{
                return  ResponseEntity.ok(idRecurrentes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tiempoViaje")
    public ResponseEntity<Double> getTiempoViaje(@RequestParam long idUsuario,
                                 @RequestParam int mes,
                                 @RequestParam int anio) {
        try{
            double tiempoTotal = viajeService.getTiempoViaje(idUsuario, mes, anio);
            return ResponseEntity.status(HttpStatus.OK).body(tiempoTotal);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/inicializarViaje")
    public ResponseEntity<ViajeDTO> inicializarViaje(@RequestBody ViajeDTOinicio viajeDTO) {
        try{

            return ResponseEntity.ok(viajeService.inicializarViaje(viajeDTO));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/finalizarViaje")
    public ResponseEntity<ViajeDTO> finalizarViaje(@RequestBody ViajeDTOfin viajeDTO) {
        try{
            return ResponseEntity.ok(viajeService.finalizarViaje(viajeDTO));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
