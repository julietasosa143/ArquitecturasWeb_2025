package dto;

public class ClienteDTO {
    private int idCliente;
    private String nombre;
    private String email;
    private double billing;

    public ClienteDTO(int idCliente,String nombre, String email, double billing) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.billing = billing;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getBilling() {
        return billing;
    }
    public void setBilling(double billing) {
        this.billing = billing;
    }

    public String toString(){
        return "CLienteDTO{" +
                "nombre='" + nombre + '\'' +
                ",email='" + email + '\'' +
                ",facturacion=" + billing +
                '}';


    }
}
