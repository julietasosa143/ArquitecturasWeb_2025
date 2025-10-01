// EstudiaCarrera.java
package entities;

import jakarta.persistence.*;

@Entity
public class EstudiaCarrera {
    @EmbeddedId
    private EstudiaCarreraId id;

    @ManyToOne
    @MapsId("LU_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("id_carrera")
    private Carrera carrera;

    private int anioInscripcion;
    private int anioGraduacion;
    private int antiguedad;

    public EstudiaCarrera() {}
    public EstudiaCarrera(Estudiante e, Carrera c, int anioInscripcion, int anioGraduacion, int antiguedad){
        this.estudiante = e;
        this.carrera = c;
        this.anioInscripcion = anioInscripcion;
        this.anioGraduacion = anioGraduacion;
        this.antiguedad = antiguedad;
        this.id = new EstudiaCarreraId(e.getLU(), c.getId());
    }

    // getters y setters
}
