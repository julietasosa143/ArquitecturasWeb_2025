import dao.CarreraDao;
import dao.EstudianteDao;
import dao.InscripcionDao;
import dto.CarreraInscriptosDTO;
import dto.ReporteCarrerasDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;
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
        Estudiante paulaR = new Estudiante();
        paulaR.setDniEstudiante(400000);
        paulaR.setNombreEstudiante("Paula");
        paulaR.setApellidoEstudiante("Rodriguez");

        Carrera lta = new Carrera(16,"Licenciatura en Tecnologia Ambiental", 5);
        CarreraDao carreraDao = new CarreraDao(em);
        carreraDao.create(lta);

        // A- dar de alta un estudiante
        estudianteDao.darDeAltaEstudiante(paulaR);
        System.out.println("se creo el estudiante");

        // B- matricular a un estudiante en una carrera
        System.out.println("se inscribe paula en la carrera lta");
        Inscripcion inscripcion1 = new Inscripcion(lta, paulaR);
        InscripcionDao inscripcionDao = new InscripcionDao(em);
        inscripcionDao.enroll(inscripcion1);


        // C- recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        List<Estudiante>estudiantes = estudianteDao.getAllEstudiantesByLU();
        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
        // D- recuperar un estudiante, en base a su número de libreta universitaria.
        Estudiante estudiante1 = estudianteDao.findEstudianteByLU(72976);
        System.out.println(estudiante1);

        // E- recuperar todos los estudiantes, en base a su género.
        List<Estudiante> estudiantesG = estudianteDao.findEstudianteByGender("Male");
        for (Estudiante e : estudiantesG) {
            System.out.println(e);
        }
        // F- recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
        List<CarreraInscriptosDTO> carreras1 = carreraDao.carrerasConEstudiantesOrdenados();
        for (CarreraInscriptosDTO c : carreras1) {
            System.out.println(c);
        }
        // G- recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        List<Estudiante> estudiantesCiudad= estudianteDao.estudiantesPorCarreraYciudad("TUDAI","Rauch");
        if(estudiantesCiudad.isEmpty()){
            System.out.println("No existe el estudiantes de esa carrera, en esa ciudad");
        }else {
            for (Estudiante e : estudiantesCiudad) {
                System.out.println(e);
            }
        }
        //3- Generar un reporte de las carreras, que para cada carrera incluya
        // información de los inscriptos y egresados por año.
        // Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
        List<ReporteCarrerasDTO>carreras = carreraDao.generateReport();
        for (ReporteCarrerasDTO c : carreras) {
            System.out.println(c);
        }
    }
}
