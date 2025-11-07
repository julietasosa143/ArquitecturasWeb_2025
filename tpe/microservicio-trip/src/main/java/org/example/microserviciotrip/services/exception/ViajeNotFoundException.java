package org.example.microserviciotrip.services.exception;

public class ViajeNotFoundException extends RuntimeException{

    public ViajeNotFoundException(String mensaje) {
        super(mensaje);
    }
}
