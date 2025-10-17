package org.example.integrador3.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.integrador3.model.*;
import org.example.integrador3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class LoadDataBase {
    @Autowired
    public EstudianteRepository estudiantRepository;

    //todavia no creamos el repository
    //@Autowired
    //public CarreraRepository carreraRepository;

    //este tambien nos falta
    //@Autowired
    //public InscripcionRepository inscripcionRepository;

    private List<CSVRecord> getData(String archivo) throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("csv_file/"+archivo);
        if (is == null) {
            throw new RuntimeException("Archivo CSV no encontrado: " + archivo);
        }
        InputStreamReader in = new InputStreamReader(is);
        CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        return csvParser.getRecords();


    }
    @PostConstruct
    @Transactional
    public void populateDB() throws Exception {

        for (CSVRecord row : getData("csv_file/estudiantes.csv")) {
            int dni= Integer.parseInt(row.get("DNI"));
            if(estudiantRepository.existsById(dni)){
                System.out.println("el estudiante ya existe, se saltea");
                continue;
            }
            Estudiante e = new Estudiante(
                    dni,
                    row.get("nombre"),
                    row.get("apellido"),
                    Integer.parseInt(row.get("edad")),
                    row.get("genero"),
                    row.get("ciudad")
            );
            estudiantRepository.save(e);
        }

//        // Cargar carreras
//        for (CSVRecord row : getData("carreras.csv")) {
//            int id = Integer.parseInt(row.get("id_carrera"));
//            if (carreraRepository.existsById(id)) {
//                System.out.println("La carrera ya existe, se saltea.");
//                continue;
//            }
//            Carrera c = new Carrera(id, row.get("carrera"), Integer.parseInt(row.get("duracion")));
//            carreraRepository.save(c);
//        }

//        // Cargar inscripciones
//        for (CSVRecord row : getData("estudianteCarrera.csv")) {
//            int dniEstudiante = Integer.parseInt(row.get("id_estudiante"));
//            int idCarrera = Integer.parseInt(row.get("id_carrera"));
//
//            Estudiante e = estudianteRepository.findByDniEstudiante(dniEstudiante);
//            Carrera c = carreraRepository.findById(idCarrera).orElse(null);
//            if (e == null || c == null) continue;
//
//            Inscripcion inscripcion = new Inscripcion(
//                    c,
//                    e,
//                    Integer.parseInt(row.get("inscripcion")),
//                    Integer.parseInt(row.get("graduacion")),
//                    Integer.parseInt(row.get("antiguedad"))
//            );
//            inscripcionRepository.save(inscripcion);
//        }

    }

}
