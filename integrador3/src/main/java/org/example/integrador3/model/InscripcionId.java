package org.example.integrador3.model;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
public class InscripcionId implements Serializable {
    private int idCarrera;
    private int libretaUniversitaria;

    public InscripcionId(){super();}
    public InscripcionId(int idCarrera, int libretaUniversitaria){
        this.idCarrera = idCarrera;
        this.libretaUniversitaria = libretaUniversitaria;
    }
    public int getIdCarrera() {
        return idCarrera;
    }
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
    public int getLibretaUniversitaria() {
        return libretaUniversitaria;
    }
    public void setLibretaUniversitaria(int libretaUniversitaria) {
        this.libretaUniversitaria= libretaUniversitaria;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscripcionId)) return false;
        InscripcionId that = (InscripcionId) o;
        return idCarrera == that.idCarrera &&
                libretaUniversitaria == that.libretaUniversitaria;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idCarrera, libretaUniversitaria);
    }
}
