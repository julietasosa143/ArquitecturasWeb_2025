package com.example.demo.controller;

import com.example.demo.service.IaChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
@RequiredArgsConstructor
public class IaController {
    private final IaChatService iaService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(
            @RequestBody ChatRequest request,
            @RequestHeader("Authorization")String token
    ){
        return ResponseEntity.ok(iaService.processChat(request,token));
    }
}
