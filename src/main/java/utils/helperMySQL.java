package utils;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

import entities.*;
import entities.Factura_Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class helperMySQL {
    private Connection conn;
    public helperMySQL() {
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


        String tableFactura ="CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL, " +
                "id_cliente INT, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), "+
                "CONSTRAINT Fk_idCliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente));";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();

        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS Factura_Producto(" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_Factura FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                "CONSTRAINT FK_Producto FOREIGN KEY (idProducto) REFERENCES producto(idProducto));";
        this.conn.prepareStatement(tableFacturaProducto).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(45) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String [] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }
    public void populateDB() throws Exception {
        try {
            System.out.println("Populating DB...");

            //Clientes
            for (CSVRecord row : getData("clientes.csv")) {
                if (row.size() >= 3) { //Verificar que hay al menos 3  campos en el csv
                    String idString = row.get(0);
                    String nombre = row.get(1);
                    String email = row.get(2);

                    if (!idString.isEmpty() && !nombre.isEmpty() && !email.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);
                            Cliente cliente = new Cliente(id, nombre, email);
                            insertCliente(cliente, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de cliente: " + e.getMessage());

                        }
                    }
                }
            }
            System.out.println("Clientes insertados");

            // Productos
            for (CSVRecord row : getData("productos.csv")) {
                if (row.size() >= 3) {
                    String idString = row.get(0);
                    String nombre = row.get(1);
                    String valorString = row.get(2);

                    if (!idString.isEmpty() && !nombre.isEmpty() && !valorString.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);
                            float valor = Float.parseFloat(valorString);
                            Producto producto = new Producto(id, nombre, valor);
                            insertProducto(producto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de producto: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Productos insertados");

            //Facturas
            for (CSVRecord row : getData("facturas.csv")) {
                if (row.size() >= 2) {
                    String idFacturaString = row.get(0);
                    String idClienteString = row.get(1);

                    if (!idFacturaString.isEmpty() && !idClienteString.isEmpty()) {
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);
                            int idCliente = Integer.parseInt(idClienteString);
                            Factura factura = new Factura(idFactura, idCliente);
                            insertFactura(factura, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Facturas insertadas");

            // Facturas-Productos
            for (CSVRecord row : getData("facturas-productos.csv")) {
                if (row.size() >= 3) {
                    String idFacturaString = row.get(0);
                    String idProductoString = row.get(1);
                    String cantidadString = row.get(2);

                    if (!idFacturaString.isEmpty() && !idProductoString.isEmpty() && !cantidadString.isEmpty()) {
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);
                            int idProducto = Integer.parseInt(idProductoString);
                            int cantidad = Integer.parseInt(cantidadString);
                            Factura_Producto fp = new Factura_Producto(idFactura, idProducto, cantidad);
                            insertFactura_Producto(fp, conn);

                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de factura: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("Facturas-Productos insertados");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private int insertCliente(Cliente cliente, Connection conn) throws Exception {
        String insert = "INSERT INTO Cliente (nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }
    private int insertProducto(Producto producto, Connection conn) throws Exception {
        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }
    private int insertFactura(Factura factura, Connection conn) throws Exception {
        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePsAndCommit(conn, ps);
        }
        return 0;

    }
    private int insertFactura_Producto(Factura_Producto fp, Connection conn) throws Exception {
        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());
            if (ps.executeUpdate() == 0){
                throw new Exception("No se pudo insertar");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }
    private void closePsAndCommit(Connection conn, PreparedStatement ps){
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
