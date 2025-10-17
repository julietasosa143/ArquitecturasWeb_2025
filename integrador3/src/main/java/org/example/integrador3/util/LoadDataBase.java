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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class LoadDataBase {
    @Autowired
    public EstudianteRepository estudianteRepository;


    @Autowired
    public CarreraRepository carreraRepository;


    @Autowired
    public InscripcionRepository inscripcionRepository;

    private List<CSVRecord> getData(String archivo) throws Exception {
        System.out.println("Buscando archivo en ruta: csv_files/" + archivo);
        InputStream is = getClass().getClassLoader().getResourceAsStream("csv_files/"+archivo);
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

        for (CSVRecord row : getData("estudiantes.csv")) {
            int dni= Integer.parseInt(row.get("DNI"));
            if(estudianteRepository.existsById(dni)){
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
            estudianteRepository.saveAndFlush(e);
        }


        for (CSVRecord row : getData("carreras.csv")) {
            int id = Integer.parseInt(row.get("id_carrera"));
            if (carreraRepository.existsById(id)) {
                System.out.println("La carrera ya existe, se saltea.");
                continue;
            }
            Carrera c = new Carrera(id,
                    row.get("carrera"),
                    Integer.parseInt(row.get("duracion")));
            carreraRepository.saveAndFlush(c);
        }


        for (CSVRecord row : getData("estudianteCarrera.csv")) {
            int dniEstudiante = Integer.parseInt(row.get("id_estudiante"));
            int idCarrera = Integer.parseInt(row.get("id_carrera"));

            Estudiante e = estudianteRepository.findById(dniEstudiante).orElse(null);
            Carrera c = carreraRepository.findById(idCarrera).orElse(null);
            if (e == null || c == null) continue;

            Inscripcion inscripcion = new Inscripcion(
                    c,
                    e,
                    Integer.parseInt(row.get("inscripcion")),
                    Integer.parseInt(row.get("graduacion")),
                    Integer.parseInt(row.get("antiguedad"))
            );
            inscripcionRepository.save(inscripcion);
        }

    }

}
