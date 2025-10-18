package org.example.integrador3.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Inscripcion {
    @EmbeddedId
    private InscripcionId id;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name="id_Carrera")
    private Carrera carrera;

    @ManyToOne
    @MapsId("dniEstudiante")
    @JoinColumn(name="dni_Estudiante")
    private Estudiante estudiante;

    @Column
    private int fechaInscripcion;
    @Column
    private int fechaGraduacion;
    @Column
    private int antiguedad;

    public Inscripcion(){super();}

    public Inscripcion(Carrera carrera, Estudiante estudiante){
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion = LocalDate.now().getYear();
        this.fechaGraduacion = 0;
        this.antiguedad = 0;
        this.id = new InscripcionId(this.getIdCarrera(), this.getDniEstudiante());
    }

    public Inscripcion( Carrera carrera, Estudiante estudiante, int fechaInscripcion, int fechaGraduacion, int antiguedad){
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaGraduacion = fechaGraduacion;
        this.antiguedad = antiguedad;
        this.id = new InscripcionId(carrera.getIdCarrera(),estudiante.getDniEstudiante());


    }

    public int getIdCarrera() {
        return this.carrera.getId();
    }
    public int getDniEstudiante(){
        return this.estudiante.getDniEstudiante();
    }
    public InscripcionId getId() {
        return id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public int getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(int fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(int fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
