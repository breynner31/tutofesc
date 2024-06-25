package com.mycompany.TutoFesc;

public class Docente {
    private String nombre;
    private String correo;
    private String cedula;
    private int telefono;

    public Docente(String nombre, String correo, String cedula, int telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCedula() {
        return cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nCorreo: " + correo + "\nCédula: " + cedula + "\ntelefono: " + telefono;
    }
}
