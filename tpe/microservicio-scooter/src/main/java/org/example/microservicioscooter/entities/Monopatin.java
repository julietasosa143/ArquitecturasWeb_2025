package org.example.microservicioscooter.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@Document
public class Monopatin {
    @Id
    private long id;

    private long kmRecorridos;
    private int x;
    private int y;
    private String estado;

    public Monopatin(long id, long kmRecorridos, int x, int y, String estado) {
        this.id = id;
        this.kmRecorridos = kmRecorridos;
        this.x = x;
        this.y = y;
        this.estado = estado;
    }
}
