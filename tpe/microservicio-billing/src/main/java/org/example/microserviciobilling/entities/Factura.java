package org.example.microserviciobilling.entities;

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
    private Integer id;
    private double cobroTotal;
    private LocalDate fechaCreacion;

    public Factura(Integer id, double cobroTotal, LocalDate fechaCreacion) {
        this.id = id;
        this.cobroTotal = cobroTotal;
        this.fechaCreacion = fechaCreacion;
    }
}
