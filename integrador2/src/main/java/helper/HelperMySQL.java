package helper;

import entities.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class HelperMySQL {
    private EntityManager em;

    public HelperMySQL(EntityManager em){
        this.em = em;
    }

    private List<CSVRecord> getData(String archivo) throws Exception {
        Reader in = new FileReader("src/main/resources/csv_files/" + archivo);
        CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        return parser.getRecords();
    }

    public void populateDB() throws Exception {
        em.getTransaction().begin();

        // Estudiantes
        for(CSVRecord row : getData("estudiantes.csv")){
            int dni = Integer.parseInt(row.get("DNI"));
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudad = row.get("ciudad");
            Long LU = Long.parseLong(row.get("LU"));

            Estudiante e = new Estudiante(dni, nombre, apellido, edad, genero, LU, ciudad);
            em.persist(e);
        }

        // Carreras
        for(CSVRecord row : getData("carreras.csv")){
            int id = Integer.parseInt(row.get("id_carrera"));
            String nombre = row.get("carrera");
            int duracion = Integer.parseInt(row.get("duracion"));

            Carrera c = new Carrera(id, nombre, duracion);
            em.persist(c);
        }

        // EstudiaCarrera
        for(CSVRecord row : getData("estudianteCarrera.csv")){
            Long LU = Long.parseLong(row.get("id_estudiante"));
            int idCarrera = Integer.parseInt(row.get("id_carrera"));
            int inicio = Integer.parseInt(row.get("inscripcion"));
            int fin = Integer.parseInt(row.get("graduacion"));
            int antiguedad = Integer.parseInt(row.get("antiguedad"));

            Estudiante e = em.find(Estudiante.class, LU);
            Carrera c = em.find(Carrera.class, idCarrera);

            EstudiaCarrera ec = new EstudiaCarrera(e, c, inicio, fin, antiguedad);
            em.persist(ec);
        }

        em.getTransaction().commit();
    }
}
