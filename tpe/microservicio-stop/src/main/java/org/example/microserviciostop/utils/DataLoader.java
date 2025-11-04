package org.example.microserviciostop.utils;

import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.microserviciostop.entity.Parada;
import org.example.microserviciostop.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    private ParadaRepository paradaRepository;

    private List<CSVRecord> getData(String archivo) throws Exception {
        java.io.InputStream is = DataLoader.class.getClassLoader()
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

        Reader paradasReader = new InputStreamReader(
                new ClassPathResource("csv_files/paradas.csv").getInputStream()
        );
        Iterable<CSVRecord> paradasRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(paradasReader);

        for (CSVRecord row : paradasRecords) {
            long id = Long.parseLong(row.get("id"));

            if (paradaRepository.existsById(id)) {
                System.out.println("Parada " + id + " ya existe, lo salteo.");
                continue;
            }

            String direccion = (row.get("direccion"));
            String nombre= (row.get("nombre"));
            int x = Integer.parseInt(row.get("x"));
            int y = Integer.parseInt(row.get("y"));
            Parada parada = new Parada(id, direccion, nombre, x, y);
            paradaRepository.save(parada);
        }

        System.out.println("Carga de Paradas completada.");
    }
}

