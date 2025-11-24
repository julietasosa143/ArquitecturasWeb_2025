package org.example.microserviciomockservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/mock/maps")
public class MockController {

    @GetMapping("/buscar")
    public Map<String, Object> connectToMaps(@RequestParam float x, @RequestParam float y) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Conectando con Google Maps... Búsqueda simulada posicion:" + x +" "+ y);
        response.put("status", "OK");
        return response;
    }
}
