package dao;

import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;
import helper.JpaUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class InscripcionDao {
    private EntityManager em;
    public InscripcionDao(EntityManager em){
        this.em = JpaUtil.getEntityManager();
    }

    //B) Inscribir a un estudiante en una Carrera
    public void enroll (Inscripcion inscripcion){
        try {
            em.getTransaction().begin();
            em.persist(inscripcion);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
}
