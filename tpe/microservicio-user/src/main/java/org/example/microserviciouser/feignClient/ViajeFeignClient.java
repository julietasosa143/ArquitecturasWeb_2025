package org.example.microserviciouser.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="microservicio-trip", url = "http://localhost:8081/api/viajes")
public interface ViajeFeignClient {

    @GetMapping("/usuariosRecurrentes")
    public List<Long> getUsuariosRecurrentes(@RequestParam int mes,@RequestParam int anio);

    @GetMapping ("/tiempoDeViaje")
    public Double getTiempoDeViaje(@RequestParam long idUsuario,
                                   @RequestParam int mes,
                                   @RequestParam int anio);
}
