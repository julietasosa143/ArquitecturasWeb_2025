package org.example.microservicioscooter.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MonopatinDTO {

    private long id;
    private double kmRecorridos;
    private int x;
    private int y;
    private String estado;
    private LocalDate ultimoService;


    public MonopatinDTO(long id, double kmRecorridos, int x, int y, String estado, LocalDate ultimoService) {
        this.id = id;
        this.kmRecorridos = kmRecorridos;
        this.x = x;
        this.y = y;
        this.estado = estado;
        this.ultimoService = ultimoService;
    }
}
