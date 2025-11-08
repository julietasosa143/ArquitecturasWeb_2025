package org.example.microserviciobilling.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TarifaDto {

    private Long id;
    private double tMinuto;
    private double tPausa;
    private LocalDate fechaExpiracion;

    public TarifaDto(Long id, double tMinuto, double tPausa, LocalDate fechaExpiracion) {
            this.id = id;
            this.tMinuto = tMinuto;
            this.tPausa = tPausa;
            this.fechaExpiracion = fechaExpiracion;
    }

}

