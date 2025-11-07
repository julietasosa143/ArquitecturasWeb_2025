package org.example.microserviciotrip.dto;

import org.example.microserviciotrip.entities.Pausa;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class ViajeDTO {

    private Integer id;
    private Integer idParadaInicio;
    private Integer idParadaFin;
    private List<Pausa> pausas;
    private double kilometros;
    private double tiempo;
    private double tarifa;
    private Integer idMonopatin;
    private Integer idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ViajeDTO(Integer id  ,Integer paradaInicio,Integer paradaFin, double kilometros, double tiempo, double tarifa, Integer monopatin, Integer usuario, LocalDate fechaInicio, LocalDate fechaFin) {
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


    public  ViajeDTO( Integer id ,Integer pInicio, Integer pFin, double tiempo, Integer monopatin, Integer usuario, LocalDate fechaInicio){
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
