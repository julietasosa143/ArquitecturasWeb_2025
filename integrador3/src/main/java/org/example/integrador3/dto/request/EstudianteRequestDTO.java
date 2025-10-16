package org.example.integrador3.dto.request;

public class EstudianteRequestDTO {
    private  String nombreEstudiante;
    private String apellidoEstudiante;
    private String generoEstudiante;
    private int dniEstudiante;
    private String ciudadResidencia;
    private int edad;


    public EstudianteRequestDTO(String nombreEstudiante, String apellidoEstudiante, String generoEstudiante, int edad, int dniEstudiante, String ciudadResidencia) {
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.generoEstudiante = generoEstudiante;
        this.dniEstudiante = dniEstudiante;
        this.ciudadResidencia = ciudadResidencia;
        this.edad = edad;
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

    public String getGeneroEstudiante() {
        return generoEstudiante;
    }

    public void setGeneroEstudiante(String generoEstudiante) {
        this.generoEstudiante = generoEstudiante;
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDniEstudiante() {
        return dniEstudiante;
    }

    public void setDniEstudiante(int dniEstudiante) {
        this.dniEstudiante = dniEstudiante;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }
}
