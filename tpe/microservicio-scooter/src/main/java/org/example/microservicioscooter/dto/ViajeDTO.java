package org.example.microservicioscooter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ViajeDTO {
    private long id;
    private long idParadaInicio;
    private long idParadaFin;
    private double tiempo;
    private double kilometros;
    private double precio;
    private long idMonopatin;
    private long idUsuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

}
