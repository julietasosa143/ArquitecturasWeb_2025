package entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {
    @EmbeddedId
    private InscripcionId id;
    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name="idCarrera")
    private Carrera carrera;

    @ManyToOne
    @MapsId("dniEstudiante")
    @JoinColumn(name="dniEstudiante")
    private Estudiante estudiante;
    @Column
    private int fechaInscripcion;
    @Column(nullable = true)
    private int fechaGraduacion;
    @Column
    private int antiguedad;


    public Inscripcion(Carrera carrera, Estudiante estudiante,int inicio ,int fin ,int antiguedad){
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.id = new InscripcionId(this.getIdCarrera(), this.getDniEstudiante());
        this.fechaInscripcion = inicio;
        this.fechaGraduacion = fin;
        this.antiguedad = antiguedad;

    }
    public Inscripcion(Carrera carrera, Estudiante estudiante){
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion= LocalDate.now().getYear();
        this.fechaGraduacion = 0;
        this.antiguedad = 0;
        this.id = new InscripcionId(this.getIdCarrera(), this.getDniEstudiante());
    }

    public Inscripcion(){}

    public InscripcionId getId() {
        return id;
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

    public int getIdCarrera() {
        return carrera.getIdCarrera();
    }
    public int getDniEstudiante() {
         return this.estudiante.getDniEstudiante();
    }

    public void setInicio(int inicio) {
        this.fechaInscripcion = inicio;

    }
    public void setFin(int fin) {
        this.fechaGraduacion = fin;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
