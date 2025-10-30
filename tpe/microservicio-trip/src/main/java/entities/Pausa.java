package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Pausa {
    @Id
    private Integer id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private float total;

    public  Pausa(Integer id, LocalDateTime fechaInicio, LocalDateTime fechaFin, float total) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.total = total;
    }

    public Pausa(Integer id) {
        this.id = id;
        this.fechaInicio = LocalDateTime.now();
    }

    public void finalizarPausa(){
        this.fechaFin = LocalDateTime.now();
        this.total= Duration.between(this.fechaInicio, this.fechaFin).toMinutes();
    }

}
