package dao;

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
}

