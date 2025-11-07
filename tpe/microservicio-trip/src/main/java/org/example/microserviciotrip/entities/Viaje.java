package org.example.microserviciotrip.entities;

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
public class Viaje {
    @Id
    private Integer id;
    @Column
    private Integer idParadaInicio;
    @Column
    private Integer idParadaFin;
    @OneToMany( mappedBy = "viaje",cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Pausa> pausas;
    @Column
    private double kilometros;
    @Column
    private double tiempo;
    @Column
    private double tarifa;
    @Column
    private Integer idMonopatin;
    @Column
    private Integer idUsuario;
    @Column
    private LocalDate fechaInicio;
    @Column
    private LocalDate fechaFin;

    public Viaje(Integer id  ,Integer paradaInicio,Integer paradaFin, double kilometros, double tiempo, double tarifa, Integer monopatin, Integer usuario,  LocalDate fechaInicio, LocalDate fechaFin) {
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


    public  Viaje(Integer id , Integer pInicio, Integer pFin, double tiempo, Integer monopatin, Integer usuario, LocalDate fechaInicio){
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
