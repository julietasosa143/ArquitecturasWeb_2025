package DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PausaDTO {
    private Integer id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private float total;

    public PausaDTO() {}

    public PausaDTO(Integer id, LocalDateTime fechaInicio, LocalDateTime fechaFin, float total) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }
}
