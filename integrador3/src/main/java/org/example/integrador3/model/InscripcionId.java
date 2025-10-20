package org.example.integrador3.model;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
public class InscripcionId implements Serializable {
    private int idCarrera;
    private int dniEstudiante;

    public InscripcionId(){super();}
    public InscripcionId(int idCarrera, int dniEstudiante){
        this.idCarrera = idCarrera;
        this.dniEstudiante = dniEstudiante;
    }
    public int getIdCarrera() {
        return idCarrera;
    }
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
    public int getDniEstudiante() {
        return dniEstudiante;
    }
    public void setDniEstudiante(int dniEstudiante) {
        this.dniEstudiante= dniEstudiante;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscripcionId)) return false;
        InscripcionId that = (InscripcionId) o;
        return idCarrera == that.idCarrera &&
                dniEstudiante == that.dniEstudiante;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idCarrera, dniEstudiante);
    }
}
