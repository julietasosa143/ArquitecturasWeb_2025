package org.example.microserviciouser.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient (name="microservicio-scooter", url="localhost://8083/api/monopatines")
public interface MonopatinFeignClient {
}
