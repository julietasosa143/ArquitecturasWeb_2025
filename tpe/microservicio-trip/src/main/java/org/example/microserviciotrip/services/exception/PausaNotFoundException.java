package org.example.microserviciotrip.services.exception;

public class PausaNotFoundException extends RuntimeException {
    public PausaNotFoundException(String message) {
        super(message);
    }
}
