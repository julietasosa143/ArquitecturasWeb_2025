package org.example.microserviciouser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDeUsoDTO {
    long idUsuario;
    String nombre;
    int mes;
    int anio;
    double tiempoDeViajePersonal;
}
