package org.example.microserviciostop.DTO;

import lombok.Data;

@Data
public class ParadaDTO {

    private Long id;
    private String direccion;
    private String nombre;
    //para localizacion
    private  float x;
    private float y;

    public ParadaDTO(Long id, String direccion, String nombre, float x, float y) {
            this.id = id;
            this.direccion = direccion;
            this.nombre = nombre;
            this.x = x;
            this.y = y;
    }
}

