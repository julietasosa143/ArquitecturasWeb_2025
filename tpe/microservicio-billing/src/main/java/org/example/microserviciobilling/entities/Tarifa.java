package org.example.microserviciobilling.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class Tarifa {
    @Id
    private Long id;
    @Column
    private LocalDate fechaCreacion;
    @Column
    private LocalDate fechaExpiracion;
    @Column
    private double precio;
    @Column
    private double precioEspecial;

    public Tarifa(Long id, LocalDate fechaCreacion, LocalDate fechaExpiracion, double precio, double precioEspecial) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.precio = precio;
        this.precioEspecial = precioEspecial;
    }

}
