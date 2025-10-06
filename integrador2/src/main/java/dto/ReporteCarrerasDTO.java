package dto;

public class ReporteCarrerasDTO {
    private String nombreCarrera;
    private Integer anioInscripcion;
    private Long cantidadInscriptos;
    private Long cantidadEgresados;

    public ReporteCarrerasDTO(String nombreCarrera,Integer anioInscripcion, Long cantidadInscriptos,Long cantidadEgresados ) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscripcion = anioInscripcion;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public Integer getAnioInscripcion() {
        return anioInscripcion;
    }

    public Long getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public Long getCantidadEgresados() {
        return cantidadEgresados;
    }
    @Override
    public String toString() {
        return "Carrera: " + nombreCarrera
                + ", Año: " + anioInscripcion
                + ", Inscriptos: " + cantidadInscriptos
                + ", Egresados: " + cantidadEgresados;
    }
}
