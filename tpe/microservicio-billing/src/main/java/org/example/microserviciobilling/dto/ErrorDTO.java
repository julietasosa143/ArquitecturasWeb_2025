package org.example.microserviciobilling.dto;

import lombok.Data;

import java.time.LocalDate;
@Data

public class ErrorDTO {
    private String message;
    private LocalDate date;

    public ErrorDTO(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }
}

