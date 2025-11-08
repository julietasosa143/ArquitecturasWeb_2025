package org.example.microservicioscooter.utils;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.microservicioscooter.entities.Monopatin;
import org.example.microservicioscooter.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class DataLoader {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @PostConstruct
    public void init() {
        try {
            System.out.println("Cargando monopatines en MongoDB...");
            populateDB();
            System.out.println("Carga de monopatines completa. Total: " + monopatinRepository.count());
        } catch (Exception e) {
            System.err.println("Error al cargar monopatines: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void populateDB() throws Exception {

        List<Monopatin> monopatines = new ArrayList<>();

        Reader reader = new InputStreamReader(
                new ClassPathResource("csv_files/monopatines.csv")
                        .getInputStream()
        );
        System.out.println("CSV encontrado: " + reader.ready());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(reader);

        for (CSVRecord row : records) {
            long id = Long.parseLong(row.get("id"));

            if (monopatinRepository.existsById(id)) {
                continue;
            }

            long kmRecorridos = Long.parseLong(row.get("kmRecorridos"));
            int x = Integer.parseInt(row.get("x"));
            int y = Integer.parseInt(row.get("y"));
            String estado = row.get("estado");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaStr = row.get("ultimoService");
            LocalDate ultimoService = null;
            try {
                ultimoService = LocalDate.parse(fechaStr.trim(), formatter);
            } catch (Exception e) {
                System.err.println("Fecha inválida para id " + id + ": " + fechaStr);
            }


            Monopatin monopatin = new Monopatin(id, kmRecorridos, x, y, estado, ultimoService);
            System.out.println("Agregado monopatín con ID: " + id + " y fecha: " + ultimoService);
            monopatines.add(monopatin);
        }


        if (!monopatines.isEmpty()) {
            monopatinRepository.saveAll(monopatines);
            System.out.println("Registros guardados en DB: " + monopatines.size());
        }else{
            System.out.println("No se agregaron registros por errores o IDs repetidos.");
        }
        System.out.println("IDs cargados con numeros: ");
        monopatines.forEach(m -> System.out.println(m.getId()));
    }
}


