package org.example.microserviciostop.controller;

import org.example.microserviciostop.DTO.ErrorDTO;
import org.example.microserviciostop.service.exception.ParadaNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice(basePackages = "org.example.microserviciostop.controller")
public class ParadaExceptionHandler {

    @ExceptionHandler(ParadaNotFoundException.class)
    public ErrorDTO handleViajeNotFound(ParadaNotFoundException ex) {
        return new ErrorDTO(ex.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleValidation(MethodArgumentNotValidException ex) {
        return new ErrorDTO("Error de validación", LocalDate.now());
    }

}

