import dao.EstudianteDao;
import entities.Estudiante;
import helper.HelperMySQL;
import helper.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManager em = JpaUtil.getEntityManager();
        HelperMySQL hp = new HelperMySQL(em);
        hp.populateDB();
        EstudianteDao estudianteDao = new EstudianteDao(em);

        //crear estudiante
        Estudiante estudiante = new Estudiante();
        estudiante.setDniEstudiante(400000);
        estudiante.setNombreEstudiante("Paula");
        estudiante.setApellidoEstudiante("Rodriguez");

        // dar de alta
        estudianteDao.darDeAltaEstudiante(estudiante);
        System.out.println("se creo el estudiante");

        //recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        List<Estudiante>estudiantes = estudianteDao.getAllEstudiantesByLU();
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
        //recuperar un estudiante, en base a su número de libreta universitaria.
        Estudiante estudiante1 = estudianteDao.findEstudianteByLU(72976);
        System.out.println(estudiante1);

        // recuperar todos los estudiantes, en base a su género.
        List<Estudiante> estudiantesG = estudianteDao.findEstudianteByGender("Male");
        for (Estudiante e : estudiantesG) {
            System.out.println(e);
        }
    }
}
