package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Estudiante {
    @Id
    private int dniEstudiante;
    @Column
    private int luEstudiante;
    @Column
    private String nombreEstudiante;
    @Column
    private String apellidoEstudiante;
    @Column
    private int edad;
    @Column
    private String generoEstudiante;
    @Column
    private String ciudadResidencia;
    @OneToMany (mappedBy="dniEstudiante")
    private List <Inscripcion> inscripciones= new ArrayList<Inscripcion>();


    public Estudiante() {
        super();
    }

    public Estudiante (int id, int luUniversitaria, String nombre, String apellido, int edad, String genero, String ciudadResidencia) {
        super();
        this.dniEstudiante = id;
        this.luEstudiante = luUniversitaria;
        this.nombreEstudiante = nombre;
        this.apellidoEstudiante = apellido;
        this.edad = edad;
        this.generoEstudiante = genero;
        this.ciudadResidencia = ciudadResidencia;

    }

    public int getDniEstudiante() {
        return dniEstudiante;
    }

    public int getLuEstudiante() {
        return luEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public int getEdad() {
        return edad;
    }

    public void setDniEstudiante(int dniEstudiante) {
        this.dniEstudiante = dniEstudiante;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGeneroEstudiante() {
        return generoEstudiante;
    }

    public void setGeneroEstudiante(String generoEstudiante) {
        this.generoEstudiante = generoEstudiante;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    @Override
    public String toString() {
        return "Estudiante " + nombreEstudiante +", "+ apellidoEstudiante+ " DNI: "+ dniEstudiante+" libreta nro: "+luEstudiante + " genero: "+ generoEstudiante+" ciudad de residencia: "+ciudadResidencia ;
    }
}
