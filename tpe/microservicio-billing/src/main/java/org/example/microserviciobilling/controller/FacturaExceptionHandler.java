package org.example.microserviciobilling.controller;

import org.example.microserviciobilling.dto.ErrorDTO;
import org.example.microserviciobilling.service.exception.FacturaNotFoundException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice(basePackages = "org.example.microserviciobilling.controller")
public class FacturaExceptionHandler {

    @ExceptionHandler(FacturaNotFoundException.class)
    public ErrorDTO handleFacturaNotFound(FacturaNotFoundException ex) {
        return new ErrorDTO(ex.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleValidation(MethodArgumentNotValidException ex) {
        return new ErrorDTO("Error de validación", LocalDate.now());
    }

}

