package org.example.microserviciouser.service.exception;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String message) {
        super(message);
    }
}
