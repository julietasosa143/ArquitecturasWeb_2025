package org.example.integrador3.controller;

import org.example.integrador3.dto.request.InscripcionRequestDTO;
import org.example.integrador3.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.integrador3.controller.InscripcionControllerJpa;

@RestController
@RequestMapping("inscripciones")
public class InscripcionControllerJpa {

    @Qualifier("inscripcionService")
    @Autowired
    private final InscripcionService inscripcionService;

    public InscripcionControllerJpa(@Qualifier("inscripcionService") InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }
    //b) matricular un estudiante en una carrera
    @PostMapping("/")
    public ResponseEntity<?> matricular(@RequestBody InscripcionRequestDTO inscripcionesRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionService.matricular(inscripcionesRequestDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"No se pudo crear la matriculacion.\"}");
        }
    }
}
