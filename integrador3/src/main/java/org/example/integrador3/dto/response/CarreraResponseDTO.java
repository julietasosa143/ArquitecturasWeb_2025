package org.example.integrador3.dto.response;

public class CarreraResponseDTO {
    private String nombre;
    private String descripcion;

    public CarreraResponseDTO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
