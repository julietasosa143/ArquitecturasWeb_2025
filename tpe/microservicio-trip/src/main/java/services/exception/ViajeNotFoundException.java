package services.exception;

public class ViajeNotFoundException extends RuntimeException{

    public ViajeNotFoundException(String mensaje) {
        super(mensaje);
    }
}
