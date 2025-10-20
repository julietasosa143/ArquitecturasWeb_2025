package org.example.integrador3.util;

import jakarta.annotation.PostConstruct;
import org.example.integrador3.model.Carrera;
import org.example.integrador3.model.Estudiante;
import org.example.integrador3.model.Inscripcion;
import org.example.integrador3.model.InscripcionId;
import org.example.integrador3.repository.CarreraRepository;
import org.example.integrador3.repository.EstudianteRepository;
import org.example.integrador3.repository.InscripcionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public  class CSVReader {

    @Autowired
    private EstudianteRepository er;
    @Autowired
    private CarreraRepository cr;
    @Autowired
    private InscripcionRepository ecr;

    @PostConstruct
    public void init() {
        try {
            System.out.println("¡Ejecutando populateDB() al iniciar!");
            populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CSVRecord> getData(String archivo) throws Exception {
        java.io.InputStream is = CSVReader.class.getClassLoader()
                .getResourceAsStream("csv_files/" + archivo);
        if (is == null) {
            throw new RuntimeException("Archivo CSV no encontrado: " + archivo);
        }
        java.io.Reader in = new java.io.InputStreamReader(is);
        CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        return parser.getRecords();
    }
    @Transactional
    public void populateDB() throws Exception {



        // Estudiantes
        for(CSVRecord row : getData("estudiantes.csv")){
            int dni = Integer.parseInt(row.get("DNI"));
            Estudiante existing = er.findById(dni).orElse(null);
            if(existing != null){
                System.out.println("el estudiante  ya existe, lo salteo.");
                continue;
            }

            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudad = row.get("ciudad");
            int LU = Integer.parseInt(row.get("LU"));
            Estudiante e = new Estudiante(dni, nombre, apellido, edad, genero, ciudad,LU);
            er.save(e);
        }

        // Carreras
        for(CSVRecord row : getData("carreras.csv")){
            int id = Integer.parseInt(row.get("id_carrera"));
            Carrera existing = cr.findById( id).orElse(null);
            if(existing != null){
                System.out.println("La carrera c ya existe, la salteo.");
                continue;
            }
            String nombre = row.get("carrera");
            int duracion = Integer.parseInt(row.get("duracion"));

            Carrera c = new Carrera(id, nombre, duracion);
            cr.save(c);
        }

        // EstudiaCarrera
        for(CSVRecord row : getData("estudianteCarrera.csv")){
            int dniEstudiante = Integer.parseInt(row.get("id_estudiante"));
            int idCarrera = Integer.parseInt(row.get("id_carrera"));
            int inicio = Integer.parseInt(row.get("inscripcion"));
            int fin = Integer.parseInt(row.get("graduacion"));
            int antiguedad = Integer.parseInt(row.get("antiguedad"));


            Estudiante e = er.findById(dniEstudiante).orElse(null);
            Carrera c = cr.findById( idCarrera).orElse(null);

            if(c == null){
                continue;
            }
            if(e == null){
                continue;
            }
            Inscripcion ec;

            InscripcionId insId = new InscripcionId(idCarrera, dniEstudiante);
            Inscripcion existing = ecr.findById( insId).orElse(null);

            if (existing == null) {

                ec = new Inscripcion(c, e, inicio, fin, antiguedad);
                ecr.save(ec);
            } else {

                ec = existing;
                ec.setFechaInscripcion(inicio);
                ec.setFechaGraduacion(fin);
                ec.setAntiguedad(antiguedad);

            }
        }


    }



}