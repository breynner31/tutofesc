
package com.mycompany.TutoFesc;


public class Alumno {
    private String nombre;
    private String correo;

    public Alumno(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Alumno: " + nombre + ", Correo: " + correo;
    }
}
