package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class helperMySLQ {
    private Connection conn;
    public helperMySLQ() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/arquidb";

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
        }catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
        try{
            conn = DriverManager.getConnection(uri,"root","");
            conn.setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
          if(conn != null){
              try{
                  conn.close();
              }catch (Exception e){
                  e.printStackTrace();
              }
    }
    }


    public void dropTables()throws SQLException {
        String dropCliente = "DROP TABLE IF EXISTS CLIENTE";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();

        String dropFactura = "DROP TABLE IF EXISTS FACTURA";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();

        String dropFactura_producto = "DROP TABLE IF EXISTS FACTURA_PRODUCTO";
        this.conn.prepareStatement(dropFactura_producto).execute();
        this.conn.commit();

        String dropProducto = "DROP TABLE IF EXISTS PRODUCTO";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();
    }

    public void createTables()throws SQLException{
        String tableCliente= "CREATE TABLE IF NOT EXISTS Cliente("+
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR (500) , " +
                "email VARCHAR(150) ," +
                "CONSTRAINT Client_pk PRIMARY KEY (idCliente));";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();


        String tableFactura ="CREATE TABLE IF NOT EXIST Factura(" +
                "idFactura INT NOT NULL, " +
                "id_cliente INT, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), "+
                "CONSTRAINT Fk_idCliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente));";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();

        String tableFacturaProducto = "CREATE TABLE IF NOT EXIST Factura_Producto(" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_Factura FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                "CONSTRAINT FK_Producto FOREIGN KEY (idProducto) REFERENCES producto(idProducto));";
        this.conn.prepareStatement(tableFacturaProducto).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXIST Producto(" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(45) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();
    }


}
