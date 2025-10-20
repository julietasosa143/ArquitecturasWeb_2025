package org.example.integrador3.dto.request;

public class EstudianteRequestDTO {
    private  String nombreEstudiante;
    private String apellidoEstudiante;
    private String generoEstudiante;
    private Integer dniEstudiante;
    private String ciudadResidencia;
    private Integer edad;
    private Integer nroLu;


    public EstudianteRequestDTO(String nombreEstudiante, String apellidoEstudiante, String generoEstudiante, Integer edad, Integer dniEstudiante, String ciudadResidencia, Integer nroLu) {
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.generoEstudiante = generoEstudiante;
        this.dniEstudiante = dniEstudiante;
        this.ciudadResidencia = ciudadResidencia;
        this.edad = edad;
        this.nroLu = nroLu;
    }


    public Integer getNroLu() {
        return nroLu;
    }

    public void setNroLu(Integer nroLu) {
        this.nroLu = nroLu;
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
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getDniEstudiante() {
        return dniEstudiante;
    }

    public void setDniEstudiante(Integer dniEstudiante) {
        this.dniEstudiante = dniEstudiante;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }
}
