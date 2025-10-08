package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int idCarrera;
    @Column
    private String nombreCarrera;
    @Column
    private int duracion;
    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones;


    public Carrera() {
        super();
    }

    public Carrera(int idCarrera, String nombreCarrera, int duracion) {
        super();
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

    public int getIdCarrera() {
        return idCarrera;
    }

    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }



}
