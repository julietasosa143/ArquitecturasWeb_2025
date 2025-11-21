package org.example.microserviciotrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ViajeDTOfin {
    private long idViaje;
    private LocalDateTime fechaFin;


}
