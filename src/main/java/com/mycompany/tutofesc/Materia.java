
package com.mycompany.TutoFesc;


import java.util.ArrayList;

public class Materia {
    private String nombre;
    private String codigo;
    private ArrayList<Docente> docentes;

    public Materia(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.docentes = new ArrayList<>();
    }

    public void agregarDocente(Docente docente) {
        docentes.add(docente);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Docente> getDocentes() {
        return docentes;
    }
}
