package org.example.microserviciouser.utils;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.microserviciouser.repository.CuentaRepository;
import org.example.microserviciouser.repository.UsuarioRepository;

import java.util.List;

@Service
public class DataLoader {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @PostConstruct
    public void init(){
        try{
            System.out.println("Cargando usuarios");
            populateDB();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private List<CSVRecord> getData(){
        java.io.InputStream is = DataLoader.class.getClassLoader().getResourceAsStream("csv_files/"+ archivo);
        if(is == null){
            throw new RuntimeException("Archivo no encontrado");
        }
        java.io.Reader reader = new java.io.InputStreamReader(is);
        CSVParser parser = new CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
        return parser.getRecords();
    }

    @Transactional
}
