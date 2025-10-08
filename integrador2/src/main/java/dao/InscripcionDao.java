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
        this.em = em;
    }

    //B) Inscribir a un estudiante en una Carrera
    public void enroll (Inscripcion inscripcion){
        em.getTransaction().begin();
        em.merge(inscripcion);
        em.getTransaction().commit();

    }
}
