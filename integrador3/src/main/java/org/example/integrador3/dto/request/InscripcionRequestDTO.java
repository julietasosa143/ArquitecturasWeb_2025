package org.example.integrador3.dto.request;

import lombok.Getter;
import org.example.integrador3.model.Carrera;
import org.example.integrador3.model.Estudiante;

@Getter
public class InscripcionRequestDTO {
    private Integer idCarrera;
    private int dniEstudiante;

    public InscripcionRequestDTO(Integer idcarrera, int dniEstudiante) {
        this.idCarrera = idcarrera;
        this.dniEstudiante = dniEstudiante;
    }
}
