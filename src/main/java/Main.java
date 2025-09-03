import factory.AbstractFactory;
import utils.helperMySQL;

import java.sql.SQLException;

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



        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
