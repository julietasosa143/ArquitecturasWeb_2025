package factory;

public class AbstractFactory {
        public static final int MYSQL_JDBC = 1;
        public static final int DERBY_JDBC = 2;

        public static AbstractFactory getDAOFactory(int whichFactory) {
            switch (whichFactory) {
                case MYSQL_JDBC : {
                    return MySQLDAOFactory.getInstance();
                }
                case DERBY_JDBC: return null;
                default: return null;
            }
        }

}

