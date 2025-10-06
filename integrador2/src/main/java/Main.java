import helper.HelperMySQL;
import helper.JpaUtil;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();
        HelperMySQL hp = new HelperMySQL(em);
        hp.populateDB();

    }
}
