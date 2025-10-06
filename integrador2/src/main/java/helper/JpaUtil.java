package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

        private static final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("integrador2");

        public static EntityManager getEntityManager() {
            return emf.createEntityManager();
        }

        public static void close() {
            emf.close();
        }
}
