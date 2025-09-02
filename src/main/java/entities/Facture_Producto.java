package entities;

public class Factura_Producto {
    private Integer idFactura;
    private Integer IdProducto;
    private Integer cantidad;

    public Factura_Producto(Integer idFactura, Integer IdProducto, Integer cantidad) {
        this.idFactura = idFactura;
        this.IdProducto = IdProducto;
        this.cantidad = cantidad;
    }

    public Factura_Producto() {}

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(Integer IdProducto) {
        this.IdProducto = IdProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String toString() {
        return "Factura_Producto{" +
                ", idFactura" + idFactura +
                ", IdProducto" + IdProducto +
                ", cantidad" + cantidad +
                '}';
    }
}
