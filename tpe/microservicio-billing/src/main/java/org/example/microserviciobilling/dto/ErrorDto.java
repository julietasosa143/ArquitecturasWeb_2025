package org.example.microserviciobilling.dto;

import java.time.LocalDate;

public class ErrorDto {
    private String message;
    private LocalDate date;

    public ErrorDto(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }
}

