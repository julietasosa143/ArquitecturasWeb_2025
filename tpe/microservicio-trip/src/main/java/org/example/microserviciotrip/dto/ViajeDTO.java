package org.example.microserviciotrip.dto;

import org.example.microserviciotrip.entities.Pausa;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class ViajeDTO {

    private long id;
    private long idParadaInicio;
    private long idParadaFin;
    private List<Pausa> pausas;
    private double tiempo;
    private double precio;
    private long idMonopatin;
    private long idUsuario;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public ViajeDTO(long id  ,long paradaInicio,long paradaFin, double tiempo, double precio, long monopatin, long usuario, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
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


    public  ViajeDTO( long id ,long pInicio, long pFin, double tiempo, long monopatin, long usuario, LocalDateTime fechaInicio){
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
