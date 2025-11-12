package org.example.microserviciouser.feignClient;

import org.example.microserviciouser.dto.ParadaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="microservicio-stop", url="http://localhost:8084/api/paradas")
public interface ParadaFeignClient {
    @GetMapping("/monopatinesCercanos")
    public List<ParadaResponseDTO> getParadasCercanas(@RequestParam float x, @RequestParam float y );
}
