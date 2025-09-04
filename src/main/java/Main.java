import dao.ClienteDAO;
import dto.ClienteDTO;
import factory.AbstractFactory;
import utils.helperMySQL;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            helperMySQL dbMySQL = new helperMySQL();
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
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
