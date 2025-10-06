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
    private Carrera idCarrera;

    @ManyToOne
    @MapsId("dniEstudiante")
    @JoinColumn(name="dniEstudiante")
    private Estudiante dniEstudiante;
    @Column
    private int fechaInscripcion;
    @Column(nullable = true)
    private int fechaGraduacion;
    @Column
    private int antiguedad;


    public Inscripcion(Carrera idCarrera, Estudiante dniEstudiante,int inicio ,int fin ,int antiguedad){
        this.idCarrera = idCarrera;
        this.dniEstudiante = dniEstudiante;
        this.id = new InscripcionId(this.getIdCarrera(), this.getDniEstudiante());
        this.fechaInscripcion = inicio;
        this.fechaGraduacion = fin;
        this.antiguedad = antiguedad;

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
        return idCarrera.getIdCarrera();
    }
    public int getDniEstudiante() {
         return this.dniEstudiante.getDniEstudiante();
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
