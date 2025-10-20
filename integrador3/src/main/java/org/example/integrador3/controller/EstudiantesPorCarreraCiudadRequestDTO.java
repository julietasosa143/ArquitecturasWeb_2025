package org.example.integrador3.controller;

public class EstudiantesPorCarreraCiudadRequestDTO {
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String nombreCarrera;
    private String ciudadResidencia;

    public EstudiantesPorCarreraCiudadRequestDTO(String nombre, String apellido, String ciudadResidencia, String nombreCarrera) {
        this.nombreEstudiante = nombre;
        this.apellidoEstudiante = apellido;
        this.ciudadResidencia = ciudadResidencia;
        this.nombreCarrera = nombreCarrera;
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

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }
}
