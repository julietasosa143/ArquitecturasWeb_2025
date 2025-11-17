package org.example.microserviciotrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ViajeDTOinicio {
    private long idParadaInicio;
    private long idParadaFin;
    private long idMonopatin;
    private LocalDateTime fechaInicio;
}
