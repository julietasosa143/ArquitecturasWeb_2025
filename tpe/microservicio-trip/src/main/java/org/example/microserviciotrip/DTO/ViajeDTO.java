package org.example.microserviciotrip.DTO;

import org.example.microserviciotrip.entities.Pausa;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ViajeDTO {

    private Integer id;
    private Integer idParadaInicio;
    private Integer idParadaFin;
    private List<Pausa> pausas;
    private float kilometros;
    private float tiempo;
    private float tarifa;
    private Integer idMonopatin;
    private Integer idUsuario;

    public ViajeDTO(Integer id  ,Integer paradaInicio,Integer paradaFin, float kilometros, float tiempo, float tarifa, Integer monopatin, Integer usuario){
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


    public  ViajeDTO( Integer id ,Integer pInicio, Integer pFin, float tiempo, Integer monopatin, Integer usuario){
        this.id = id;
        this.idParadaInicio = pInicio;
        this.idParadaFin = pFin;
        this.tiempo = tiempo;
        this.idMonopatin = monopatin;
        this.idUsuario = usuario;
        this.pausas= new ArrayList<Pausa>();
    }
}
