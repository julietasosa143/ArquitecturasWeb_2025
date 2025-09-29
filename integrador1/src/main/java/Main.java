import dao.ClienteDAO;
import dao.ProductoDAO;
import dto.ClienteDTO;
import dto.ProductoDTO;
import factory.AbstractFactory;
import utils.HelperMySQL;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            HelperMySQL dbMySQL = new HelperMySQL();
            dbMySQL.dropTables();
            dbMySQL.createTables();
            dbMySQL.populateDB();
            dbMySQL.closeConnection();

            AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
            System.out.println();
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println();
            ClienteDAO cliente = chosenFactory.getClienteDAO();

            System.out.println("Lista de clientes ordenada por facturacion");
            List<ClienteDTO> listadoClientes = cliente.findClientesByOrderBilling();
            for(ClienteDTO clienteDTO : listadoClientes) {
                System.out.println(clienteDTO.toString());
            }
            ProductoDAO producto = chosenFactory.getProductoDAO();
            System.out.println("Producto que mas recaudo");

            ProductoDTO productoMasRecaudo = producto.findBestSeller();
            System.out.println(productoMasRecaudo.toString());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
