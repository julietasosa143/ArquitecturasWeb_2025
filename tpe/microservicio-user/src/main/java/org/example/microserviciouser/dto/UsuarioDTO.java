package org.example.microserviciouser.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UsuarioDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String rol;



    //para ubicacion

    public UsuarioDTO(long id, String nombre, String apellido, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;

        this.rol = rol;

    }

}
