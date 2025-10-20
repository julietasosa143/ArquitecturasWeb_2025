package org.example.integrador3.service;

import org.example.integrador3.dto.request.EstudianteRequestDTO;
import org.example.integrador3.dto.response.EstudianteResponseDTO;
import org.example.integrador3.dto.response.EstudiantesPorCarreraCiudadResponseDTO;
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

    public EstudianteResponseDTO create(EstudianteRequestDTO requestDTO){
        Estudiante estudiante = new Estudiante(requestDTO.getDniEstudiante(),requestDTO.getNombreEstudiante(),requestDTO.getApellidoEstudiante(), requestDTO.getEdad(), requestDTO.getGeneroEstudiante(),requestDTO.getCiudadResidencia(), requestDTO.getNroLu());
        Estudiante estudianteAgregado= this.estudianteRepository.save(estudiante);
        //si no se crea el save tira una excepcion
       return   new EstudianteResponseDTO(estudianteAgregado.getNombreEstudiante(),estudianteAgregado.getApellidoEstudiante(),estudianteAgregado.getGeneroEstudiante(),estudianteAgregado.getEdadEstudiante(),estudianteAgregado.getDniEstudiante(),estudianteAgregado.getCiudadResidencia(), estudianteAgregado.getLibretaUniversitaria());

    }

    public List<EstudianteResponseDTO> getAllByEdad(){
        List<Estudiante> estudiantes= this.estudianteRepository.getAllByAge();

        return estudiantes.stream()
                .map(estudiante -> new EstudianteResponseDTO(estudiante.getNombreEstudiante(),estudiante.getApellidoEstudiante(),estudiante.getGeneroEstudiante(),estudiante.getEdadEstudiante(),estudiante.getDniEstudiante(),estudiante.getCiudadResidencia(), estudiante.getLibretaUniversitaria()))
                .toList();
    }
    public List<EstudianteResponseDTO> search(EstudianteRequestDTO requestDTO){
                                                                        //public EstudianteRequestDTO(String nombreEstudiante, String apellidoEstudiante, String generoEstudiante, int edad,int dniEstudiante, String ciudadResidencia)
        List<Estudiante> estudiantes = this.estudianteRepository.search(requestDTO.getNombreEstudiante(),requestDTO.getApellidoEstudiante(),requestDTO.getGeneroEstudiante(),requestDTO.getEdad(),requestDTO.getDniEstudiante(),requestDTO.getCiudadResidencia(), requestDTO.getNroLu());

        return estudiantes.stream()
                .map(estudiante -> new EstudianteResponseDTO(estudiante.getNombreEstudiante(),estudiante.getApellidoEstudiante(),estudiante.getGeneroEstudiante(),estudiante.getEdadEstudiante(),estudiante.getDniEstudiante(),estudiante.getCiudadResidencia(), estudiante.getLibretaUniversitaria()))
                .toList();
    }
    public EstudianteResponseDTO getEstudianteByDNI(Integer dniEstudiante){
        Estudiante requerido= this.estudianteRepository.getById(dniEstudiante);
        return new EstudianteResponseDTO(requerido.getNombreEstudiante(), requerido.getApellidoEstudiante(), requerido.getGeneroEstudiante(), requerido.getEdadEstudiante(), requerido.getDniEstudiante(), requerido.getCiudadResidencia(), requerido.getLibretaUniversitaria());
    }

    public List<EstudiantesPorCarreraCiudadResponseDTO> getEstudiantesXCarrerayCiudad(String carrera, String ciudad){
        List<EstudiantesPorCarreraCiudadResponseDTO> estudiantes = this.estudianteRepository.getPorCarrerayCiudad(carrera, ciudad);
        return estudiantes.stream()
                .map(estudiante -> new EstudiantesPorCarreraCiudadResponseDTO(estudiante.getNombreEstudiante(),estudiante.getApellidoEstudiante(),estudiante.getCiudadResidencia(), estudiante.getNombreCarrera()))
                .toList();
    }



}
