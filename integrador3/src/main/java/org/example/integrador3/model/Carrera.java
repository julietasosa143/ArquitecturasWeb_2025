package org.example.integrador3.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    @Column(name = "id_carrera")
    private Integer idCarrera;
    @Column
    private String nombreCarrera;
    @Column
    private int duracionCarrera;
    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();

    public Carrera(){super();}
    public Carrera(String nombreCarrera, int duracionCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.duracionCarrera = duracionCarrera;
    }

    public Carrera(Integer id, String nombreCarrera, int duracionCarrera) {
        this.idCarrera = id;
        this.nombreCarrera = nombreCarrera;
        this.duracionCarrera = duracionCarrera;
    }
    public Integer getId() {
        return idCarrera;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getDuracionCarrera() {
        return duracionCarrera;
    }

    public void setDuracionCarrera(int duracionCarrera) {
        this.duracionCarrera = duracionCarrera;
    }
}
