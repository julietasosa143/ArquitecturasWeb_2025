package controller;

import DTO.ErrorDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import services.exception.ViajeNotFoundException;

import java.time.LocalDate;

@RestControllerAdvice(basePackages = "src.main.java.controller")
public class ViajeExceptionHandler {

    @ExceptionHandler(ViajeNotFoundException.class)
    public ErrorDTO handleViajeNotFound(ViajeNotFoundException ex) {
        return new ErrorDTO(ex.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleValidation(MethodArgumentNotValidException ex) {
        return new ErrorDTO("Error de validación", LocalDate.now());
    }

}
