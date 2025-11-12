package org.example.microserviciostop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaResponseDTO {
    long idParada;
    float x;
    float y;
}
