package dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Inscripcion {
    @Id
    private InscripcionId id;
    @Column
    private LocalDate fechaInscripcion;
    @Column(nullable = true)
    private LocalDate fechaGraduacion;

    public Inscripcion(InscripcionId id, LocalDate fechaInscripcion) {
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;

    }

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
