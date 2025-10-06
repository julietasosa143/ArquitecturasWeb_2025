package dao;

import entities.Estudiante;
import helper.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EstudianteDao {

    private EntityManager em;

    public EstudianteDao() {
        this.em = JpaUtil.getEntityManager();
    }
    public  EstudianteDao(EntityManager em) {
        this.em = em;
    }

    public void darDeAltaEstudiante(Estudiante estudiante) {
        em.getTransaction().begin();
        em.merge(estudiante);
        em.getTransaction().commit();


    }

    public List<Estudiante> getAllEstudiantesByLU() {
        String spql = "SELECT e FROM Estudiante e ORDER BY e.luEstudiante";
        Query query = em.createQuery(spql);
        List<Estudiante> estudiantes = query.getResultList();
        for(Estudiante estudiante : estudiantes){
            System.out.println(estudiante.toString());
        }
        return estudiantes;


    }

    public Estudiante findEstudianteByLU(int luEstudiante){

            String spql= "Select e from Estudiante e WHERE e.luEstudiante =:luEstudiante ";
            TypedQuery<Estudiante> typedQuery= em.createQuery(spql,Estudiante.class);
            typedQuery.setParameter("luEstudiante", luEstudiante);
            Estudiante estudiante =typedQuery.getSingleResult();

            return estudiante;

    }
    public List<Estudiante> findEstudianteByGender(String gender){

            String spql= "Select e from Estudiante e WHERE e.generoEstudiante =:gender ";
            TypedQuery<Estudiante> typedQuery= em.createQuery(spql,Estudiante.class);
            typedQuery.setParameter("gender", gender);
            List<Estudiante> estudiantes = typedQuery.getResultList();

            return estudiantes;

    }

}
