package org.example.microservicioscooter.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document(collection = "monopatins")
public class Monopatin {
    @Id
    private long id;

    private double kmRecorridos;
    private int x;
    private int y;
    private String estado;
    private LocalDate ultimoService;


    public Monopatin(long id, double kmRecorridos, int x, int y, String estado, LocalDate ultimoService) {
        this.id = id;
        this.kmRecorridos = kmRecorridos;
        this.x = x;
        this.y = y;
        this.estado = estado;
        this.ultimoService = ultimoService;
    }
}
