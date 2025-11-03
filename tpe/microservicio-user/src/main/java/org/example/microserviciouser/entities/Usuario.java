package org.example.microserviciouser.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    private long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String email;
    @Column
    private long telefono;
    @Column
    private String rol;
    @Column
    private LocalDate fechaAlta;

    //para ubicacion
    @Column
    private float x;
    @Column
    private float y;


    @ManyToMany
    @JoinTable
    private List<Cuenta> cuentas;

    public Usuario(long id, String nombre, String apellido, String email, long telefono,String rol, float x, float y) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.x = x;
        this.y = y;
        this.rol = null;
        this.fechaAlta = LocalDate.now();
        this.cuentas = new ArrayList<>();
    }

    public Usuario(long id, String nombre, String apellido, String email,long telefono, String rol, float x, float y, LocalDate fechaAlta){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.x = x;
        this.y = y;
        this.rol = null;
        this.fechaAlta = fechaAlta;
    }

}
