package org.example.microserviciotrip.feing;

import org.example.microserviciotrip.dto.ViajeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "viaje-service", url = "http://localhost:8084")
public class ViajeClient {

    @GetMapping("/viajes/monopatin/{idMonopatin}")
    List<ViajeDTO> getViajesPorMonopatin(@PathVariable Long idMonopatin);
}
