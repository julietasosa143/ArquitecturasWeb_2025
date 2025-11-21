package org.example.microserviciobilling.utils;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.microserviciobilling.entities.Factura;
import org.example.microserviciobilling.entities.Tarifa;
import org.example.microserviciobilling.repository.FacturaRepository;
import org.example.microserviciobilling.repository.TarifaRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DataLoaderService {

    private final FacturaRepository facturaRepository;
    private final TarifaRepository tarifaRepository;

    public DataLoaderService(FacturaRepository facturaRepository, TarifaRepository tarifaRepository) {
        this.facturaRepository= facturaRepository;
        this.tarifaRepository = tarifaRepository;
    }
    @PostConstruct
    public void init(){
        try {
            System.out.println("Cargando facturas y tarifas");
            populateDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CSVRecord> getData(String archivo) throws Exception {
        java.io.InputStream is = DataLoaderService.class.getClassLoader()
                .getResourceAsStream("csv_files/" + archivo);
        if  (is == null) {
            throw new RuntimeException("Archivo CSV no encontrado: " + archivo);
        }
        java.io.Reader in = new java.io.InputStreamReader(is);
        CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
        return parser.getRecords();
    }
    @Transactional
    public void populateDB() throws Exception {
        // --- Facturas ---
        Reader facturasReader = new InputStreamReader(
                new ClassPathResource("csv_files/facturas.csv").getInputStream()
        );
        Iterable<CSVRecord> facturasRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(facturasReader);

        for (CSVRecord row : facturasRecords) {
            long id = Long.parseLong(row.get("id"));

            if (facturaRepository.existsById(id)) {
                System.out.println("Factura " + id + " ya existe, lo salteo.");
                continue;
            }
            long idViaje = Long.parseLong(row.get("idViaje"));
            double cobroTotal = Double.parseDouble(row.get("cobroTotal"));
            LocalDate fechaCreacion = LocalDate.parse(row.get("fechaCreacion"));
            Factura factura = new Factura(id, cobroTotal,idViaje,fechaCreacion);
            facturaRepository.save(factura);
        }
        // --- Tarifas ---
        Reader tarifasReader = new InputStreamReader(
                new ClassPathResource("csv_files/tarifas.csv").getInputStream()
        );
        Iterable<CSVRecord> tarifasRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(tarifasReader);

        for (CSVRecord row : tarifasRecords) {
            Long id = Long.parseLong(row.get("id"));

            if (tarifaRepository.existsById(id)) {
                System.out.println("Tarifa " + id + " ya existe, la salteo.");
                continue;
            }
            LocalDate fechaCreacion = LocalDate.parse(row.get("fechaCreacion"));
            LocalDate fechaExpiracion = LocalDate.parse(row.get("fechaExpiracion"));

            Double precio = Double.parseDouble(row.get("precio"));
            Double precioEspecial = Double.parseDouble(row.get("precioEspecial"));

            Tarifa tarifa = new Tarifa(id,fechaCreacion,fechaExpiracion,precio,precioEspecial);
            tarifaRepository.save(tarifa);
        }

        System.out.println("Carga de Facturas y Tarifas completada.");
    }

}
