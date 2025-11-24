package com.example.demo.controller;

import com.example.demo.dto.RespuestaApi;
import com.example.demo.service.IaChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
public class IaController {


    @Autowired
    private IaChatService iaService;

    @PostMapping("/prompt")
    public ResponseEntity<?> procesarPrompt(@RequestBody String prompt) {
        System.out.println("ENTRO AL PROMPT: " + prompt);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Aquí auth.getName() normalmente te devuelve el username (email)
        String email = auth.getName();

        // Chequear si es premium usando el microservicio user
        if (!iaService.esUsuarioPremium(email)) {
            return ResponseEntity.status(403)
                    .body(new RespuestaApi<>(false, "Acceso denegado: solo usuarios premium", null));
        }

        return iaService.procesarPrompt(prompt, true);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("IA funcionando");
    }


}
