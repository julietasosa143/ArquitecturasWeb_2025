package org.example.microserviciotrip.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private float kilometros;
    @Column
    private float tiempo;
    @Column
    private float tarifa;
    @Column
    private Integer idMonopatin;
    @Column
    private Integer idUsuario;

    public Viaje(Integer id  ,Integer paradaInicio,Integer paradaFin, float kilometros, float tiempo, float tarifa, Integer monopatin, Integer usuario){
        this.id = id;
        this.idParadaInicio = paradaInicio;
        this.idParadaFin = paradaFin;
        this.pausas = new ArrayList<Pausa>();
        this.kilometros = kilometros;
        this.tiempo = tiempo;
        this.tarifa = tarifa;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
    }


    public  Viaje( Integer id ,Integer pInicio, Integer pFin, float tiempo, Integer monopatin, Integer usuario){
        this.id = id;
        this.idParadaInicio = pInicio;
        this.idParadaFin = pFin;
        this.tiempo = tiempo;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
        this.pausas= new ArrayList<Pausa>();
    }


}
