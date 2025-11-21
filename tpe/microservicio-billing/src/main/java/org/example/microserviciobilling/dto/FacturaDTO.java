package org.example.microserviciobilling.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacturaDTO {
    private Long id;
    private double cobroTotal;
    private long idViaje;
    private LocalDate fechaCreacion;

    public FacturaDTO(Long id, double cobroTotal, long idViaje,LocalDate fechaCreacion) {
        this.id = id;
        this.cobroTotal = cobroTotal;
        this.idViaje = idViaje;
        this.fechaCreacion = fechaCreacion;
    }
}


