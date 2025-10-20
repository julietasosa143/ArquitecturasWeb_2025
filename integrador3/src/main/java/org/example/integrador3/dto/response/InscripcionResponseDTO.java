package org.example.integrador3.dto.response;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class InscripcionResponseDTO {
    private String nombreEstudiante;  // Nombre del estudiante     // ID de la carrera
    private String nombreCarrera;     // Nombre de la carrera
    private int fechaInscripcion;

    public  InscripcionResponseDTO(String nombreEstudiante, String nombreCarrera,int fechaInscripcion) {
        this.nombreEstudiante = nombreEstudiante;
        this.nombreCarrera = nombreCarrera;
        this.fechaInscripcion = fechaInscripcion;
    }

}
