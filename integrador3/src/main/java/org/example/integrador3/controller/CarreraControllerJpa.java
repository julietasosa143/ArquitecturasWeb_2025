package org.example.integrador3.controller;

import org.example.integrador3.dto.request.CarreraRequestDTO;
import org.example.integrador3.dto.response.CarreraInscriptosResponseDTO;
import org.example.integrador3.dto.response.CarreraResponseDTO;
import org.example.integrador3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraControllerJpa {

    @Qualifier("carreraService")
    @Autowired
    private final CarreraService carreraService;

    public CarreraControllerJpa(@Qualifier("carreraService") CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @PostMapping
    public ResponseEntity<?> createCarrera(@RequestBody CarreraRequestDTO carrera) {
        try {
            CarreraResponseDTO created = this.carreraService.create(carrera);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"No se pudo crear la carrera.\"}");
        }
    }
    //Obtener carreras con estudiantes inscriptos, ordenadas por cantidad de inscriptos
    @GetMapping("/inscriptos")
    public ResponseEntity<?> getCarrerasOrdenadasPorInscriptos() {
        try {
            List<CarreraInscriptosResponseDTO> result = carreraService.getCarrerasConInscriptosOrdenadas();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"No se pudieron obtener las carreras con inscriptos.\"}");
        }
    }
}
