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
    private double tMinuto;
    @Column
    private double tPausa;
    @Column
    private LocalDate fechaExpiracion;

    public Tarifa(Long id, double tMinuto, double tPausa, LocalDate fechaExpiracion) {
        this.id = id;
        this.tMinuto = tMinuto;
        this.tPausa = tPausa;
        this.fechaExpiracion = fechaExpiracion;
    }

}
