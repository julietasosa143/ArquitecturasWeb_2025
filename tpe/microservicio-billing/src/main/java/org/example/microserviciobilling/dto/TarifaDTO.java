package org.example.microserviciobilling.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TarifaDTO {

    private Long id;
    private LocalDate fechaExpiracion;
    private double precio;
    private double precioEspecial;

    public TarifaDTO(Long id,  LocalDate fechaExpiracion, double precio, double precioEspecial) {
            this.id = id;
            this.fechaExpiracion = fechaExpiracion;
            this.precio = precio;
            this.precioEspecial = precioEspecial;
    }

}

