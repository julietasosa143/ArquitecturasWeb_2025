package org.example.microserviciouser.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="microservicio-mock-services", url = "http://localhost:8005/mock/maps")
public interface MockFeignClient {
    @GetMapping("/connect")
    public Map<String, Object> connectToMaps(@RequestParam float x, @RequestParam float y);
}
