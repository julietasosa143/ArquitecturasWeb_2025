package dao;

import dto.ProductoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public ProductoDTO findBestSeller() throws SQLException {
        String query = "SELECT p.idProducto, p.nombre, " +
                "IFNULL(SUM(p.valor * fp.cantidad), 0) AS totalRecaudado "+
                "FROM Producto p "+
                "LEFT JOIN Factura_Producto fp " +
                "ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY totalRecaudado DESC " +
                "LIMIT 1;";
        ProductoDTO bestSeller = null;
        try(PreparedStatement ps = conn.prepareStatement(query);){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                bestSeller = new ProductoDTO(rs.getString("nombre"), rs.getInt("idProducto"), rs.getInt("totalRecaudado"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return bestSeller;
    }

}
