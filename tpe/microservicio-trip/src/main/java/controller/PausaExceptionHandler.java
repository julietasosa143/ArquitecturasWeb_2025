package controller;

import DTO.ErrorDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import services.exception.PausaNotFoundException;

import java.time.LocalDate;

@RestControllerAdvice(basePackages = "src.main.java.controller")
public class PausaExceptionHandler {

    @ExceptionHandler(PausaNotFoundException.class)
    public ErrorDTO handlePausaNotFound(PausaNotFoundException ex) {
        return new ErrorDTO(ex.getMessage(), LocalDate.now());
    }
}
