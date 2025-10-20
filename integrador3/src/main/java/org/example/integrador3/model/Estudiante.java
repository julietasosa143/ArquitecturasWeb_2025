package org.example.integrador3.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Estudiante {
    @Id
    @Column(name="dni_estudiante", nullable=false, unique=true)
    private int dniEstudiante;
    @Column
    private int libretaUniversitaria;
    @Column
    private String nombreEstudiante;
    @Column
    private String apellidoEstudiante;
    @Column
    private int edadEstudiante;
    @Column
    private String generoEstudiante;
    @Column
    private String ciudadResidencia;
    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones= new ArrayList<Inscripcion>();

    public Estudiante() {
        super();
    }
    public Estudiante(int dniEstudiante, String nombre, String apellido, int edad, String genero, String ciudadResidencia) {
        this.dniEstudiante = dniEstudiante;
        this.nombreEstudiante = nombre;
        this.apellidoEstudiante = apellido;
        this.edadEstudiante = edad;
        this.generoEstudiante = genero;
        this.ciudadResidencia = ciudadResidencia;
    }


    public Estudiante(int dniEstudiante,String nombre,String apellido, int edad, String generoEstudiante, String ciudadResidencia,int libretaUniversitaria) {
        this.dniEstudiante = dniEstudiante;
        this.apellidoEstudiante=apellido;
        this.edadEstudiante = edad;
        this.nombreEstudiante=nombre;
        this.generoEstudiante=generoEstudiante;
        this.ciudadResidencia=ciudadResidencia;
        this.libretaUniversitaria=libretaUniversitaria;


    }

    public int getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public int getDniEstudiante() {
        return dniEstudiante;
    }

    public void setDniEstudiante(int dniEstudiante) {
        this.dniEstudiante = dniEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public int getEdadEstudiante() {
        return edadEstudiante;
    }

    public void setEdadEstudiante(int edadEstudiante) {
        this.edadEstudiante = edadEstudiante;
    }

    public String getGeneroEstudiante() {
        return generoEstudiante;
    }

    public void setGeneroEstudiante(String generoEstudiante) {
        this.generoEstudiante = generoEstudiante;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    @Override
    public String toString() {
        return "Estudiante: "+ nombreEstudiante+" "+apellidoEstudiante+
                " edad: "+edadEstudiante+" dni nro: "+ dniEstudiante+
                " genero: "+generoEstudiante+" libreta universitaria nro: "+ libretaUniversitaria+
                " que reside en la ciudad de "+ciudadResidencia;
    }
}
