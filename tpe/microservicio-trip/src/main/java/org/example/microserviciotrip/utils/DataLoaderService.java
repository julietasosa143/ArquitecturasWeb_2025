package org.example.microserviciotrip.utils;

import jakarta.annotation.PostConstruct;
import org.example.microserviciotrip.entities.Pausa;
import org.example.microserviciotrip.entities.Viaje;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.example.microserviciotrip.repository.PausaRepository;
import org.example.microserviciotrip.repository.ViajeRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class DataLoaderService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Autowired
    private PausaRepository pausaRepository;

    @PostConstruct
    public void init() {
        try {
            System.out.println("Cargando usuarios");
            populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CSVRecord> getData(String archivo) throws Exception {
        java.io.InputStream is = DataLoaderService.class.getClassLoader()
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

        // --- Viajes ---
        Reader viajesReader = new InputStreamReader(
                new ClassPathResource("csv_files/viajes.csv").getInputStream()
        );
        Iterable<CSVRecord> viajesRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(viajesReader);

        for (CSVRecord row : viajesRecords) {
            long id = Long.parseLong(row.get("id"));

            if (viajeRepository.existsById(id)) {
                System.out.println("Viaje " + id + " ya existe, lo salteo.");
                continue;
            }

            int paradaInicio = Integer.parseInt(row.get("idParadaInicio"));
            int paradaFin = Integer.parseInt(row.get("idParadaFin"));
            float tiempo = Float.parseFloat(row.get("tiempo"));
            double kilometros = Double.parseDouble(row.get("kilometros"));
            float precio = Float.parseFloat(row.get("precio"));
            int idMonopatin = Integer.parseInt(row.get("idMonopatin"));
            int idUsuario = Integer.parseInt(row.get("idUsuario"));
            LocalDateTime fechaInicio = LocalDateTime.parse(row.get("fechaInicio"));
            LocalDateTime fechaFin = LocalDateTime.parse(row.get("fechaFin"));

            Viaje viaje = new Viaje(id, paradaInicio, paradaFin, tiempo,kilometros, precio, idMonopatin, idUsuario, fechaInicio, fechaFin);
            viajeRepository.save(viaje);
        }


        Reader pausasReader = new InputStreamReader(
                new ClassPathResource("csv_files/pausas.csv").getInputStream()
        );
        Iterable<CSVRecord> pausasRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(pausasReader);

        for (CSVRecord row : pausasRecords) {
            int id = Integer.parseInt(row.get("id"));

            if (pausaRepository.existsById(id)) {
                System.out.println("Pausa " + id + " ya existe, la salteo.");
                continue;
            }

            LocalDateTime fechaInicio = LocalDateTime.parse(row.get("fechaInicio"));
            LocalDateTime fechaFin = LocalDateTime.parse(row.get("fechaFin"));
            float total = Float.parseFloat(row.get("total"));
            long viajeId = Long.parseLong(row.get("viaje_id"));

            Viaje viaje = viajeRepository.findById(viajeId).orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
            if (viaje == null) {
                System.out.println("Viaje " + viajeId + " no existe, salto la pausa " + id);
                continue;
            }

            Pausa pausa = new Pausa(id, fechaInicio, fechaFin, total);
            pausa.setViaje(viaje);

            pausaRepository.save(pausa);
        }

        System.out.println("Carga de Viajes y Pausas completada.");
    }
}
