package org.example.microserviciobilling.service.exception;

public class FacturaNotFoundException extends RuntimeException{
    public FacturaNotFoundException (String message){ super(message);}
}
