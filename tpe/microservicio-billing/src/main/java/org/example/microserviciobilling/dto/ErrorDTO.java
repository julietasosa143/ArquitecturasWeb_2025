package org.example.microserviciobilling.dto;

import java.time.LocalDate;

public class ErrorDTO {
    private String message;
    private LocalDate date;

    public ErrorDTO(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }
}

