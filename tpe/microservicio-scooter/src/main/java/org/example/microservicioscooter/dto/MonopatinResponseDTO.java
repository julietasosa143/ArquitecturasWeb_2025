package org.example.microservicioscooter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonopatinResponseDTO {
    private long idMonopatin;
    private float x;
    private float y;

}
