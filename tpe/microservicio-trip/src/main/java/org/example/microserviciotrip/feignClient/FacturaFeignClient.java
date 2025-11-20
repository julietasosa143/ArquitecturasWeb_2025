package org.example.microserviciotrip.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name ="microservicio-billing", url ="localhost:8086/api/facturas")
public interface FacturaFeignClient {

    @GetMapping("/getPrecioViaje")
    Double getPrecioViaje(@RequestParam long idViaje,
                          @RequestParam double tiempoTotal,
                          @RequestParam double tiempoPausas,
                          @RequestParam LocalDateTime fechaFin);
}
