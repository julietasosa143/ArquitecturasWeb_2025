package dao;

import dto.CarreraInscriptosDTO;
import dto.ReporteCarrerasDTO;
import entities.Carrera;
import entities.Estudiante;
import helper.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CarreraDao {
    private EntityManager em;

    public CarreraDao(EntityManager em) {
        this.em = em;
    }
    public void create(Carrera c){
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();


    }
    public Carrera buscarPorId(int id){
        return em.find(Carrera.class,id);
    }
    //f) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.

    public List<CarreraInscriptosDTO> carrerasConEstudiantesOrdenados(){
        return em.createQuery(
                "SELECT new dto.CarreraInscriptosDTO(c.nombreCarrera, COUNT(i)) " +
                        "FROM Carrera c " +
                        "JOIN c.inscripciones i " +
                        "GROUP BY c.nombreCarrera " +
                        "ORDER BY COUNT(i) DESC",
                CarreraInscriptosDTO.class
        ).getResultList();
    }

    //3) Generar un reporte de las carreras, que para cada carrera incluya información de los
    //inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
    //los años de manera cronológica
    public List<ReporteCarrerasDTO> generateReport() {
        return em.createQuery(
                "SELECT new dto.ReporteCarrerasDTO(" +
                "c.nombreCarrera, " +
                        "i.fechaInscripcion, " +
                        "COUNT(i), " +
                        "COUNT(CASE WHEN i.fechaGraduacion != 0 THEN 1 END)" +
                        ") " +
                        "FROM Inscripcion i " +
                        "JOIN i.carrera c " +
                        "GROUP BY c.nombreCarrera, i.fechaInscripcion " +
                        "ORDER BY c.nombreCarrera ASC, i.fechaInscripcion ASC",
                ReporteCarrerasDTO.class
        ).getResultList();
    }

}

