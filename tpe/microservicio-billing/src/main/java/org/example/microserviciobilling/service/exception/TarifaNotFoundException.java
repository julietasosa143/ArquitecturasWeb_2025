package org.example.microserviciobilling.service.exception;

public class TarifaNotFoundException extends RuntimeException{
    public TarifaNotFoundException(String message){
        super(message);
    }
}
