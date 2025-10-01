// EstudiaCarreraId.java
package entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EstudiaCarreraId implements Serializable {
    private Long LU_estudiante;
    private int id_carrera;

    public EstudiaCarreraId() {}
    public EstudiaCarreraId(Long LU, int idCarrera){
        this.LU_estudiante = LU;
        this.id_carrera = idCarrera;
    }

    // equals y hashCode
}
