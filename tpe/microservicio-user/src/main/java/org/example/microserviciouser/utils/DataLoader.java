package org.example.microserviciouser.utils;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.microserviciouser.entities.Cuenta;
import org.example.microserviciouser.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.example.microserviciouser.repository.CuentaRepository;
import org.example.microserviciouser.repository.UsuarioRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

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
        java.io.InputStream is = DataLoader.class.getClassLoader()
                .getResourceAsStream("csv_files/" + archivo);
        if (is == null) {
            throw new RuntimeException("Archivo no encontrado" + archivo);
        }
        java.io.Reader reader = new java.io.InputStreamReader(is);
        CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
        return parser.getRecords();
    }

    @Transactional
    public void populateDB() throws Exception {
        //Usuarios
        Reader userReader = new InputStreamReader
                (new ClassPathResource("csv_files/usuarios.csv")
                        .getInputStream()
                );
        Iterable<CSVRecord> userRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader().parse(userReader);
        for (CSVRecord row : userRecords) {
            long id = Long.parseLong(row.get("id"));
            if (usuarioRepository.existsById(id)) {
                System.out.println("El usuario con el id " + id + " ya existe");
                continue;
            }
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            String email = row.get("email");
            long telefono = Long.parseLong(row.get("telefono"));
            String rol = row.get("rol");
            int x = Integer.parseInt(row.get("x"));
            int y = Integer.parseInt(row.get("y"));

            Usuario usuario = new Usuario(id, nombre, apellido, email, telefono, rol, x, y);
            usuarioRepository.save(usuario);
        }

        //Cuentas
        Reader cuentaReader = new InputStreamReader(
                new ClassPathResource("csv_files/cuentas.csv")
                        .getInputStream()
        );
        Iterable<CSVRecord> cuentaRecords = CSVFormat.DEFAULT
                .withFirstRecordAsHeader().parse(cuentaReader);
        for (CSVRecord row : cuentaRecords) {
            long id = Long.parseLong(row.get("id"));
            if (cuentaRepository.existsById(id)) {
                System.out.println("La cuenta con el id " + id + " ya existe");
                continue;
            }
            double balance = Double.parseDouble(row.get("balance"));
            String estado = row.get("estado");
            String tipoCuenta = row.get("tipoCuenta");
            LocalDate fechaAlta = LocalDate.parse(row.get("fechaAlta"));
            Cuenta cuenta = new Cuenta(id, balance, estado, tipoCuenta,fechaAlta);
            cuentaRepository.save(cuenta);
        }
    }
}
