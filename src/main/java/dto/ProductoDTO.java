package dto;

public class ProductoDTO {
    private String nombre;
    private int idProducto;
    private int totalRecaudado;

    public ProductoDTO(String nombre, int idProducto, int totalRecaudado) {
        this.nombre = nombre;
        this.idProducto = idProducto;
        this.totalRecaudado = totalRecaudado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(int totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }
    public String toString(){
        return "Producto" + nombre+
                "ID " +idProducto+
                "Total recaudado" +totalRecaudado;
    }
}
