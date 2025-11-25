package org.example.microserviciotrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.microserviciotrip.entities.Pausa;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class ViajeDTOsinpausas {
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
