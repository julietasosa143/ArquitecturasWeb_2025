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
public class Factura {
    @Id
    private Long id;
    @Column
    private double cobroTotal;
    @Column
    private long idViaje;
    @Column
    private LocalDate fechaCreacion;

    public Factura(Long id, double cobroTotal,long idViaje, LocalDate fechaCreacion) {
        this.id = id;
        this.cobroTotal = cobroTotal;
        this.idViaje = idViaje;
        this.fechaCreacion = fechaCreacion;
    }
}
