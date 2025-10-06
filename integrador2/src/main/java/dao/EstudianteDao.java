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
//A) dar de alta un estudiante
    public void darDeAltaEstudiante(Estudiante estudiante) {
        try{em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();}
        finally {
            em.close();
        }

    }
// C) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple. Elegimos libreta universitaria
    public void getAllEstudiantesByLU() {
        try{em.getTransaction().begin();
            String spql = "SELECT e FROM Estudiante e ORDER BY e.luEstudiante";
            Query query=em.createQuery(spql);
            List<Estudiante> estudiantes = query.getResultList();
            for(Estudiante estudiante:estudiantes){
                System.out.println(estudiante.toString());
            }

        }finally{em.close();}


    }
    // D)recuperar un estudiante, en base a su número de libreta universitaria.
    public void findEstudianteByLU(int luEstudiante){
        try{em.getTransaction().begin();
            String spql= "Select e from Estudiante e WHERE e.luEstudiante =:luEstudiante ";
            TypedQuery<Estudiante> typedQuery= em.createQuery(spql,Estudiante.class);
            typedQuery.setParameter("luEstudiante", luEstudiante);
            List<Estudiante> estudiantes =typedQuery.getResultList();
            for(Estudiante estudiante:estudiantes){
                System.out.println(estudiante.toString());
            }

        }finally{em.close();}
    }
    //E) recuperar todos los estudiantes, en base a su género.
    public void findEstudianteByGender(String gender){
        try{em.getTransaction().begin();
            String spql= "Select e from Estudiante e WHERE e.generoEstudiante =:gender ";
            TypedQuery<Estudiante> typedQuery= em.createQuery(spql,Estudiante.class);
            typedQuery.setParameter("gender", gender);
            System.out.println( typedQuery.getSingleResult().toString());

        }finally{em.close();}
    }

}
