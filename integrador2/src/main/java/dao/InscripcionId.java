package dao;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
@Embeddable
public class InscripcionId implements Serializable {
    private int idCarrera;
    private int dniEstudiante;

    public InscripcionId() {}
    public InscripcionId(int idCarrera, int idEstudiante) {
        this.idCarrera = idCarrera;
        this.dniEstudiante = idEstudiante;
    }

    public int getIdCarrera() {
        return idCarrera;
    }
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
    public int getDniEstudiante() {
        return dniEstudiante;
    }
    public void setDniEstudiante(int idEstudiante) {
        this.dniEstudiante = idEstudiante;
    }
}

