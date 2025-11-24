package org.example.microservicioscooter.feignClient;

import org.example.microserviciotrip.entities.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name="microservicio-trip", url="http://localhost:8081/api/viajes")
public interface ViajeFeignClient {

    @GetMapping("/porMonopatin/{id}")
    public List<Viaje> getViajesXMonopatin(
            @PathVariable("id") Long monopatinId,
            @RequestParam("ultimoService") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ultimoService
    );


    @GetMapping("/porAnioViajes")
    public List<Long> getMonopatinesXViajeAnio(
            @RequestParam int anio,
            @RequestParam int minViajes
    );
}

