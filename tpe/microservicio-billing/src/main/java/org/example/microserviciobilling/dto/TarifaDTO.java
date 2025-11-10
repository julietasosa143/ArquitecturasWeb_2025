package org.example.microserviciobilling.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TarifaDTO {

    private Long id;
    private double tMinuto;
    private double tPausa;
    private LocalDate fechaExpiracion;

    public TarifaDTO(Long id, double tMinuto, double tPausa, LocalDate fechaExpiracion) {
            this.id = id;
            this.tMinuto = tMinuto;
            this.tPausa = tPausa;
            this.fechaExpiracion = fechaExpiracion;
    }

}

