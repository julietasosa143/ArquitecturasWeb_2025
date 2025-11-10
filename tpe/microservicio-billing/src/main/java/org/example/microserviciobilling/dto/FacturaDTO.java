package org.example.microserviciobilling.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacturaDTO {
    private Long id;
    private double cobroTotal;
    private LocalDate fechaCreacion;

    public FacturaDTO(Long id, double cobroTotal, LocalDate fechaCreacion) {
        this.id = id;
        this.cobroTotal = cobroTotal;
        this.fechaCreacion = fechaCreacion;
    }
}


