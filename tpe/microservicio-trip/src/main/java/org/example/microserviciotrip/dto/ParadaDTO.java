package org.example.microserviciotrip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaDTO {
    long idParada;
    float x;
    float y;
}
