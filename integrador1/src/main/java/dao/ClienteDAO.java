package dao;

import dto.ClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }


    public List<ClienteDTO> findClientesByOrderBilling() throws SQLException {
        String sql = "SELECT c.idCliente, c.nombre, c.email, " +
                     "IFNULL(SUM(fp.cantidad * p.valor),0) AS totalBilling " +
                     "FROM Cliente c " +
                     "LEFT JOIN Factura f ON c.idCliente = f.idCliente " +
                     "LEFT JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                     "LEFT JOIN Producto p ON fp.idProducto = p.idProducto " +
                     "GROUP BY c.idCliente, c.nombre, c.email " +
                     "ORDER BY totalBilling DESC";   // o DESC";

        List<ClienteDTO> listaClienteDTO = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaClienteDTO.add( new ClienteDTO(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getDouble("totalBilling")
                ));
            }

        }

    return listaClienteDTO;
    }
}
