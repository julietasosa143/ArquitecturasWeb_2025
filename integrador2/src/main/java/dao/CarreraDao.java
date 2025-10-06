package dao;

import dto.ReporteCarrerasDTO;
import entities.Carrera;
import entities.Estudiante;
import helper.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraDao {
    private EntityManager em;

    public CarreraDao(EntityManager em) {
        this.em = JpaUtil.getEntityManager();
    }
    public void create(Carrera c){
        try{em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }finally{
            em.close();
        }

    }
    public Carrera buscarPorId(int id){
        return em.find(Carrera.class,id);
    }
    //f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.

    public List<Carrera> carrerasConEstudiantesOrdenados(){
        return em.createQuery(
                "SELECT c FROM Carrera c JOIN c.inscripciones i " +
                        "GROUP BY c " +
                        "ORDER BY COUNT(i) DESC",
                Carrera.class
        ).getResultList();
    }
    // g) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public List<Estudiante> estudiantesPorCarreraYciudad(int idCarrera, String ciudad){
        return em.createQuery(
                "SELECT i.dniEstudiante " +
                        "FROM Inscripcion i " +
                        "WHERE i.idCarrera.idCarrera = :id AND i.dniEstudiante.ciudadResidencia = :ciudad", Estudiante.class
        ).setParameter("id", idCarrera)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }
    //3) Generar un reporte de las carreras, que para cada carrera incluya información de los
    //inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
    //los años de manera cronológica
    public List<ReporteCarrerasDTO> generateReport() {
        return em.createQuery(
                "SELECT new dto.ReporteCarrerasDTO(" +
                        "c.nombreCarrera, " +
                        "YEAR(i.fechaInscripcion), " +
                        "COUNT(i), " +
                        "SUM(CASE WHEN i.fechaGraduacion IS NOT NULL THEN 1 ELSE 0 END)" +
                        ") " +
                        "FROM Inscripcion i " +
                        "JOIN i.idCarrera c " +
                        "GROUP BY c.nombreCarrera, YEAR(i.fechaInscripcion) " +
                        "ORDER BY c.nombreCarrera ASC, YEAR(i.fechaInscripcion) ASC",
                ReporteCarrerasDTO.class
        ).getResultList();
    }
}

