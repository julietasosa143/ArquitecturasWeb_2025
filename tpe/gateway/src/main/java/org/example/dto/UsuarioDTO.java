package org.example.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String rol;
    private String email;
    private String password;
    public UsuarioDTO(long id, String nombre, String apellido, String rol,String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email=email;
        this.rol = rol;
        this.password = password;

    }
}
