package org.example.microserviciotrip.feignClient;

import org.example.microserviciotrip.dto.ParadaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservicio-stop", url="localhost:8084/api/paradas")
public interface ParadaFeignClient {
    @GetMapping("/{id}")
    public ParadaDTO getById(@PathVariable("id") Long id);
}
