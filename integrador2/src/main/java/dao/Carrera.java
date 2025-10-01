package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int idCarrera;
    private String nombreCarrera;
    private int duracion;


    public Carrera() {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.duracion = duracion;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;

    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones = new ArrayList<>();

}
