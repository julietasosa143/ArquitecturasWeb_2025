package org.example.microserviciouser.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class Cuenta {
    @Id
    private long id;
    @Column
    private double balance;
    @Column
    private String estado;
    @Column
    LocalDate fechaAlta;

    @ManyToMany(mappedBy= "cuentas")
    private List<Usuario> usuarios;

    public Cuenta(long id, double balance,String estado, LocalDate fechaAlta) {
        this.id = id;
        this.balance = balance;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.usuarios = new ArrayList<>();
    }

}
