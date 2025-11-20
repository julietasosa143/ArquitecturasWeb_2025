package org.example.microserviciotrip.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PausaDTO {
    private Integer id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private double total;

    public PausaDTO() {}

    public PausaDTO(Integer id, LocalDateTime fechaInicio, LocalDateTime fechaFin, double total) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }
}
