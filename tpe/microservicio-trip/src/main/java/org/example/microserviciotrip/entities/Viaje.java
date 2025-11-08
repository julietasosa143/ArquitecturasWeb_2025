package org.example.microserviciotrip.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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
    private double kilometros;
    @Column
    private double tiempo;
    @Column
    private double tarifa;
    @Column
    private long idMonopatin;
    @Column
    private long idUsuario;
    @Column
    private LocalDate fechaInicio;
    @Column
    private LocalDate fechaFin;

    public Viaje(long id  ,long paradaInicio,long paradaFin, double kilometros, double tiempo, double tarifa, long monopatin, long usuario,  LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.idParadaInicio = paradaInicio;
        this.idParadaFin = paradaFin;
        this.pausas = new ArrayList<Pausa>();
        this.kilometros = kilometros;
        this.tiempo = tiempo;
        this.tarifa = tarifa;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public  Viaje(long id , long pInicio, long pFin, double tiempo, long monopatin, long usuario, LocalDate fechaInicio){
        this.id = id;
        this.idParadaInicio = pInicio;
        this.idParadaFin = pFin;
        this.tiempo = tiempo;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
        this.pausas= new ArrayList<Pausa>();
        this.fechaInicio = fechaInicio;
    }


}
