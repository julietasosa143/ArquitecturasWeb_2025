package org.example.microservicioscooter.dto;

import lombok.Data;

@Data
public class ReporteMantenimientoDTOResponse {
    private double kmRecorridos;
    private double tiempoTotal;

    public ReporteMantenimientoDTOResponse(double kmRecorridos, double tiempoTotal) {
        this.kmRecorridos = kmRecorridos;
        this.tiempoTotal = tiempoTotal;
    }
}
