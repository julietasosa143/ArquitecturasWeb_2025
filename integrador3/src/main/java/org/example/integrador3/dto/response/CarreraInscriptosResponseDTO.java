package org.example.integrador3.dto.response;
import lombok.Getter;

@Getter
public class CarreraInscriptosResponseDTO {
    private String nombreCarrera;
    private long cantidadInscriptos;

    public CarreraInscriptosResponseDTO(){}
    public CarreraInscriptosResponseDTO(String nombre, long cantidadInscriptos) {
        this.nombreCarrera = nombre;
        this.cantidadInscriptos = cantidadInscriptos;
    }

    public long getCantidadCarrera() {
        return cantidadInscriptos;
    }
}
