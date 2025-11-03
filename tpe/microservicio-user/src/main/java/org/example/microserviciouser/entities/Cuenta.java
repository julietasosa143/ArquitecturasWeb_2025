package org.example.microserviciouser.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String tipoCuenta;

    @ManyToMany(mappedBy= "cuentas")
    private List<Cuenta> usuarios;

    public Cuenta(long id,  String tipoCuenta, double balance) {
        this.id = id;
        this.tipoCuenta = tipoCuenta;
        this.balance = balance;
        this.usuarios = new ArrayList<>();
    }

}
