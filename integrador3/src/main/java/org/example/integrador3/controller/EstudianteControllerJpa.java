package org.example.integrador3.controller;

import org.apache.coyote.Response;
import org.example.integrador3.dto.request.EstudianteRequestDTO;
import org.example.integrador3.dto.response.EstudianteResponseDTO;
import org.example.integrador3.model.Estudiante;
import org.example.integrador3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estudiantes")
public class EstudianteControllerJpa {
    //borrar despues pero tira error por que falta el @service de estudianteService
    @Qualifier("estudianteService")
    @Autowired
    private final EstudianteService estudianteService;

    public EstudianteControllerJpa(@Qualifier("estudianteService") EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    //dar de alta un estudiante
    @PostMapping
    public ResponseEntity<?> createEstudiante(@RequestBody EstudianteRequestDTO estudiante) {
        try{
             EstudianteResponseDTO estudianteResponseDTO =estudianteService.create(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body( estudianteResponseDTO);
        }catch (Exception ex){
            //podemos retornar un texto que diga el error pero tendriamos que retornar de tipo <?>
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"{\\\"error\\\":\\\"Error. No se pudo crear. \\\"}\"");
        }
    }
    //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    @GetMapping("/")
    public ResponseEntity<List<EstudianteResponseDTO>> getAllEstudiantesOrderByEdad( ) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body( estudianteService.getAllByEdad());
        }catch (Exception e){
            //podemos retornar un texto que diga el error pero tendriamos que retornar de tipo <?>
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //recuperar un estudiante, en base a su número de libreta universitaria, recuperar todos los estudiantes, en base a su género, recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @GetMapping("/search")
    public  ResponseEntity<List<EstudianteResponseDTO>> getAllEstudiantesBY(EstudianteRequestDTO estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.search(estudiante));
        }catch (Exception e){
            //podemos retornar un texto que diga el error pero tendriamos que retornar de tipo <?>
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }





}
