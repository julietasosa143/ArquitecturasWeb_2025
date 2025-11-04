package org.example.microserviciostop.service.exception;

public class ParadaNotFoundException extends RuntimeException {
    public ParadaNotFoundException(String message) {
        super(message);
    }
}
