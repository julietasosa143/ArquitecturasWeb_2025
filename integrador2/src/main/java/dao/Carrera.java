package dao;
@Entity
public class Carrera {
    @Id
    private int idCarrera;
    private String nombreCarrera;

    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Carrera() {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;

    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }


}
