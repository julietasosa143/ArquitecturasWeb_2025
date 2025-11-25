package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaAnuladaDTO {
    private long id;
    private double balance;
    private String mensaje;
}
