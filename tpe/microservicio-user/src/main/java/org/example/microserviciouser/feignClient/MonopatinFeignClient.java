package org.example.microserviciouser.feignClient;

import org.example.microserviciouser.dto.MonopatinResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient (name="microservicio-scooter", url="localhost:8083/api/monopatines")
public interface MonopatinFeignClient {

    @GetMapping("/monopatinesCercanos")
    List<MonopatinResponseDTO> getMonopatinesCercanos(@RequestParam float x, @RequestParam float y);
}
