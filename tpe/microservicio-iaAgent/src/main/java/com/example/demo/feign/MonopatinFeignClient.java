package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="monopatin-serivice",url="http://localhost:8090/api/monopatines")
public interface MonopatinFeignClient {

    @GetMapping("/")
    List<MonopatinDTO> getMonopatins();

    @GetMapping("/{id}")
    MonopatinDTO getMonopatinById(@PathVariable Long id);

    @GetMapping("/cantidadViajes/anio")
    List<MonopatinDTO> viajesPorAnio(
            @RequestParam int anio,
            @RequestParam int minViajes
    );

}
