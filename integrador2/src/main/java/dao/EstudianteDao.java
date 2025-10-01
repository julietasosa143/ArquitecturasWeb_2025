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

    public void darDeAltaEstudiante(Estudiante estudiante) {
        try{em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();}
        finally {
            em.close();
        }

    }

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
    public void findEstudianteByGender(String gender){
        try{em.getTransaction().begin();
            String spql= "Select e from Estudiante e WHERE e.generoEstudiante =:gender ";
            TypedQuery<Estudiante> typedQuery= em.createQuery(spql,Estudiante.class);
            typedQuery.setParameter("gender", gender);
            System.out.println( typedQuery.getSingleResult().toString());

        }finally{em.close();}
    }

}
