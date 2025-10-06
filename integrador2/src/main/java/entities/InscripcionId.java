package entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscripcionId)) return false;
        InscripcionId that = (InscripcionId) o;
        return idCarrera == that.idCarrera &&
                dniEstudiante == that.dniEstudiante;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idCarrera, dniEstudiante);
    }
}

