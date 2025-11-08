package org.example.microserviciotrip.dto;

import org.example.microserviciotrip.entities.Pausa;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class ViajeDTO {

    private long id;
    private long idParadaInicio;
    private long idParadaFin;
    private List<Pausa> pausas;
    private double kilometros;
    private double tiempo;
    private double tarifa;
    private long idMonopatin;
    private long idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ViajeDTO(long id  ,long paradaInicio,long paradaFin, double kilometros, double tiempo, double tarifa, long monopatin, long usuario, LocalDate fechaInicio, LocalDate fechaFin) {
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


    public  ViajeDTO( long id ,long pInicio, long pFin, double tiempo, long monopatin, long usuario, LocalDate fechaInicio){
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
