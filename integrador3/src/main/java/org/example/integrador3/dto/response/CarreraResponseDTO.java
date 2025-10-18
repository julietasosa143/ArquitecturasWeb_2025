package org.example.integrador3.dto.response;

public class CarreraResponseDTO {
    private String nombre;
    private Integer duracionCarrera;

    public CarreraResponseDTO(String nombre, Integer duracionCarrera) {
        this.nombre = nombre;
        this.duracionCarrera = this.duracionCarrera;
    }

    public String getNombre() {
        return nombre;
    }
    public Integer getDuracionCarrera() {
        return duracionCarrera;
    }
}
