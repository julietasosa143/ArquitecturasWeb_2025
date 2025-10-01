// Carrera.java
package entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Carrera {
    @Id
    private int id;

    private String nombre;
    private int duracion;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private Set<EstudiaCarrera> estudiantes;

    public Carrera() {}
    public Carrera(int id, String nombre, int duracion){
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    // getters y setters
}
