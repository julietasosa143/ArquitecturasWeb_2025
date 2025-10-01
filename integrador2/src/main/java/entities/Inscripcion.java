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
    private LocalDate fechaInscripcion;
    @Column(nullable = true)
    private LocalDate fechaGraduacion;

    public Inscripcion(Carrera  idCarrera,Estudiante dniEstudiante ,InscripcionId id, LocalDate fechaInscripcion) {
        this.idCarrera = idCarrera;
        this.dniEstudiante = dniEstudiante;
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;

    }

    public Inscripcion(){}

    public InscripcionId getId() {
        return id;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public LocalDate getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(LocalDate fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }
}
