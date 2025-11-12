package org.example.microserviciouser.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="microservicio-stop", url="http://localhost:8084/api/paradas")
public interface ParadaFeignClient {
}
