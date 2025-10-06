package dto;

public class CarreraInscriptosDTO {
    private String nombreCarrera;
    private Long cantidadInscriptos;

    public CarreraInscriptosDTO(String nombreCarrera, Long cantidadInscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.cantidadInscriptos = cantidadInscriptos;
    }
    public String getNombreCarrera() {
        return nombreCarrera;
    }
    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
    public Long getCantidadInscriptos() {
        return cantidadInscriptos;
    }
    public void setCantidadInscriptos(Long cantidadInscriptos) {
        this.cantidadInscriptos = cantidadInscriptos;
    }

}
