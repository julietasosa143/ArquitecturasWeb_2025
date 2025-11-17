package org.example.microserviciobilling.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Data
public class TarifaDTO {

    private Long id;
    private LocalDate fechaCreacion;
    private LocalDate fechaExpiracion;
    private double precio;
    private double precioEspecial;

    public TarifaDTO(Long id, LocalDate fechaCreacion, LocalDate fechaExpiracion, double precio, double precioEspecial) {
            this.id = id;
            this.fechaCreacion = fechaCreacion;
            this.fechaExpiracion = fechaExpiracion;
            this.precio = precio;
            this.precioEspecial = precioEspecial;
    }

}

