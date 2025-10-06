package dto;

public class ReporteCarrerasDTO {
    private String nombreCarrera;
    private int anioInscripcion;
    private int cantidadInscriptos;
    private int cantidadEgresados;

    public ReporteCarrerasDTO(String nombreCarrera,int anioInscripcion, int cantidadInscriptos,int cantidadEgresados ) {
        this.nombreCarrera = nombreCarrera;
        this.anioInscripcion = anioInscripcion;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getAnioInscripcion() {
        return anioInscripcion;
    }

    public int getCantidadInscriptos() {
        return cantidadInscriptos;
    }

    public int getCantidadEgresados() {
        return cantidadEgresados;
    }
}
