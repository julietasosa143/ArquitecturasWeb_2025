package org.example.microserviciotrip.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"viaje"})
public class Pausa {
    @Id
    private Integer id;
    @Column
    private LocalDateTime fechaInicio;
    @Column
    private LocalDateTime fechaFin;
    @Column
    private float total;
    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

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
