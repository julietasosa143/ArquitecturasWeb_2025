package org.example.integrador3.service;

import org.example.integrador3.dto.request.InscripcionRequestDTO;
import org.example.integrador3.dto.response.InscripcionResponseDTO;
import org.example.integrador3.model.Carrera;
import org.example.integrador3.model.Estudiante;
import org.example.integrador3.model.Inscripcion;
import org.example.integrador3.repository.CarreraRepository;
import org.example.integrador3.repository.EstudianteRepository;
import org.example.integrador3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InscripcionService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    public InscripcionService(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, InscripcionRepository inscripcionRepository ) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
        this.inscripcionRepository = inscripcionRepository;

    }



    public InscripcionResponseDTO matricular(InscripcionRequestDTO inscripcionDTO) {
        Estudiante estudiante= estudianteRepository.findById(inscripcionDTO.getDniEstudiante()).orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Carrera carrera = carreraRepository.findById(inscripcionDTO.getIdCarrera()).orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Inscripcion inscripcion = new Inscripcion(carrera,estudiante);
        Inscripcion inscripcionAgregado= this.inscripcionRepository.save(inscripcion);
        return new InscripcionResponseDTO(inscripcionAgregado.getEstudiante().getNombreEstudiante(),inscripcionAgregado.getCarrera().getNombreCarrera(),inscripcionAgregado.getFechaInscripcion());
    }


}
