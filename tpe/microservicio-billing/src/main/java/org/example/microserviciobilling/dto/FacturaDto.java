package org.example.microserviciobilling.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FacturaDto {
    private Long id;
    private double cobroTotal;
    private LocalDate fechaCreacion;

    public FacturaDto(Long id, double cobroTotal, LocalDate fechaCreacion) {
        this.id = id;
        this.cobroTotal = cobroTotal;
        this.fechaCreacion = fechaCreacion;
    }
}


