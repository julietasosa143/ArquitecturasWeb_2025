import dao.EstudianteDao;
import entities.Estudiante;
import helper.HelperMySQL;
import helper.JpaUtil;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();
        HelperMySQL hp = new HelperMySQL(em);
        hp.populateDB();
        EstudianteDao estudianteDao = new EstudianteDao(em);

        //crear estudiante
        Estudiante estudiante = new Estudiante();
        estudiante.setDniEstudiante(000000);
        estudiante.setNombreEstudiante("Paula");
        estudiante.setApellidoEstudiante("Rodriguez");

        // dar de alta
        estudianteDao.darDeAltaEstudiante(estudiante);
        System.out.println("se creo el estudiante");



    }
}
