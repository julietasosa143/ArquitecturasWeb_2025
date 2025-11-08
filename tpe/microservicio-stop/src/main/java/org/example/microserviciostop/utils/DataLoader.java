package org.example.microserviciostop.utils;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.microserviciostop.entity.Parada;
import org.example.microserviciostop.repository.ParadaRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class DataLoader {

    private final ParadaRepository paradaRepository;

    public DataLoader(final ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("Cargando paradas...");
            populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            String direccion = row.get("direccion");
            String nombre = row.get("nombre");
            int x = Integer.parseInt(row.get("x"));
            int y = Integer.parseInt(row.get("y"));

            Parada parada = new Parada();
            parada.setDireccion(direccion);
            parada.setNombre(nombre);
            parada.setX(x);
            parada.setY(y);

            paradaRepository.save(parada);
        }

        System.out.println("Carga de Paradas completada.");
    }
}


