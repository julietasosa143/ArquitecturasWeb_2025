// Estudiante.java
package entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Estudiante {
    @Id
    private Long LU;

    private int dni;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Set<EstudiaCarrera> carreras;

    public Estudiante() {}
    public Estudiante(int dni, String nombre, String apellido, int edad, String genero, Long LU, String ciudad){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.LU = LU;
        this.ciudad = ciudad;
    }

    // getters y setters
}
