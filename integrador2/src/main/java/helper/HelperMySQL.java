package helper;

import entities.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import java.util.List;

public class HelperMySQL {
    private EntityManager em;

    public HelperMySQL(EntityManager em){
        this.em = em;
    }

    private List<CSVRecord> getData(String archivo) throws Exception {
        java.io.InputStream is = HelperMySQL.class.getClassLoader()
                .getResourceAsStream("csv_files/" + archivo);
        if (is == null) {
            throw new RuntimeException("Archivo CSV no encontrado: " + archivo);
        }
        java.io.Reader in = new java.io.InputStreamReader(is);
        CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        return parser.getRecords();
    }
    public void populateDB() throws Exception {
        em.getTransaction().begin();


        // Estudiantes
        for(CSVRecord row : getData("estudiantes.csv")){
            int dni = Integer.parseInt(row.get("DNI"));
            Estudiante existing = em.find(Estudiante.class, dni);
            if(existing != null){
                System.out.println("el estudiante  ya existe, la salteo.");
                continue;
            }

            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudad = row.get("ciudad");
            int LU = Integer.parseInt(row.get("LU"));
            Estudiante e = new Estudiante(dni,LU, nombre, apellido, edad, genero, ciudad);
            em.persist(e);
        }

        // Carreras
        for(CSVRecord row : getData("carreras.csv")){
            int id = Integer.parseInt(row.get("id_carrera"));
            Carrera existing = em.find(Carrera.class, id);
            if(existing != null){
                System.out.println("La carrera c ya existe, la salteo.");
                continue;
            }
            String nombre = row.get("carrera");
            int duracion = Integer.parseInt(row.get("duracion"));

            Carrera c = new Carrera(id, nombre, duracion);
            em.persist(c);
        }

        // EstudiaCarrera
        for(CSVRecord row : getData("estudianteCarrera.csv")){
            int dniEstudiante = Integer.parseInt(row.get("id_estudiante"));
            int idCarrera = Integer.parseInt(row.get("id_carrera"));
            int inicio = Integer.parseInt(row.get("inscripcion"));
            int fin = Integer.parseInt(row.get("graduacion"));
            int antiguedad = Integer.parseInt(row.get("antiguedad"));


            Estudiante e = em.find(Estudiante.class, dniEstudiante);
            Carrera c = em.find(Carrera.class, idCarrera);

            if(c == null){
                System.out.println("No existe el carrera con el id: " + idCarrera);
                continue;
            }
            if(e == null){
                System.out.println("No existe el estudiante con el id: " + dniEstudiante);
                continue;
            }
            Inscripcion ec;

            InscripcionId insId = new InscripcionId(idCarrera, dniEstudiante);
            Inscripcion existing = em.find(Inscripcion.class, insId);

            if (existing == null) {

                ec = new Inscripcion(c, e, inicio, fin, antiguedad);
                em.persist(ec);
            } else {

                ec = existing;
                ec.setInicio(inicio);
                ec.setFin(fin);
                ec.setAntiguedad(antiguedad);

            }
        }

        em.getTransaction().commit();
    }


}
