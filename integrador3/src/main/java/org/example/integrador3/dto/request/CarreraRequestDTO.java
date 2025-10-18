package org.example.integrador3.dto.request;

import lombok.Getter;
import org.example.integrador3.model.Carrera;

@Getter
public class CarreraRequestDTO {
    private String nombreCarrera;
    private int duracionCarrera;

    public CarreraRequestDTO(String nombreCarrera, int duracionCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.duracionCarrera = duracionCarrera;
    }
    public CarreraRequestDTO() {}
    public String getNombreCarrera() {
        return nombreCarrera;
    }
    public int getDuracionCarrera() {
        return duracionCarrera;
    }
}
