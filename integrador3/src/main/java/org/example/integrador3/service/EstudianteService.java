package org.example.integrador3.service;

import org.example.integrador3.dto.request.EstudianteRequestDTO;
import org.example.integrador3.dto.response.EstudianteResponseDTO;
import org.example.integrador3.model.Estudiante;
import org.example.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;


    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void create(EstudianteRequestDTO requestDTO){
        this.estudianteRepository.create(requestDTO.getName(),requestDTO.getLastName(),requestDTO.getGender(),requestDTO.getDNI(),requestDTO.getCity());
    }

    public List<EstudianteResponseDTO> getAllByEdad(int edad){
        List<Estudiante> estudiantes= this.estudianteRepository.getAllByOrder(edad);

        return estudiantes.stream()
                .map(estudiante -> new EstudianteResponseDTO(estudiante.getName(),estudiante.getLastName(),estudiante.getGender(),estudiante.getDNI(),estudiante.getCity()))
                .toList();
    }
    public List<EstudianteResponseDTO> search(EstudianteRequestDTO requestDTO){
        List<Estudiante> estudiantes = this.estudianteRepository.search(estudiante.getName(),estudiante.getLastName(),estudiante.getGender(),estudiante.getDNI(),estudiante.getCity());

        return estudiantes.stream()
                .map(estudiante -> new EstudianteResponseDTO(estudiante.getName(),estudiante.getLastName(),estudiante.getGender(),estudiante.getDNI(),estudiante.getCity()))
                .toList();
    }


}
