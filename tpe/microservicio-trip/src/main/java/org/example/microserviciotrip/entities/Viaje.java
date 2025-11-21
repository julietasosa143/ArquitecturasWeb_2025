package org.example.microserviciotrip.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class Viaje {
    @Id
    private long id;
    @Column
    private long idParadaInicio;
    @Column
    private long idParadaFin;
    @OneToMany( mappedBy = "viaje",cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Pausa> pausas;
    @Column
    private double tiempo;
    @Column
    private double precio;
    @Column
    private long idMonopatin;
    @Column
    private long idUsuario;
    @Column
    private LocalDateTime fechaInicio;
    @Column
    private LocalDateTime fechaFin;

    public Viaje(long id  ,long paradaInicio,long paradaFin, double tiempo, double precio, long monopatin, long usuario,  LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.idParadaInicio = paradaInicio;
        this.idParadaFin = paradaFin;
        this.pausas = new ArrayList<Pausa>();
        this.tiempo = tiempo;
        this.precio = precio;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public  Viaje(long id , long pInicio, long pFin, double tiempo, long monopatin, long usuario, LocalDateTime fechaInicio){
        this.id = id;
        this.idParadaInicio = pInicio;
        this.idParadaFin = pFin;
        this.tiempo = tiempo;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
        this.pausas= new ArrayList<Pausa>();
        this.fechaInicio = fechaInicio;
    }

    public double getTiempoPausas(){
        double tiempo=0;
        for(Pausa p: pausas){
            tiempo+=p.getTotal();
        }
        return tiempo;
    }


}
