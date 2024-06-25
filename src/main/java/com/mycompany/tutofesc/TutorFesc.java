
package com.mycompany.TutoFesc;



import java.util.ArrayList;
import java.util.HashMap;

public class TutorFesc {
    private ArrayList<Docente> docentes;
    private ArrayList<Materia> materias;
    private HashMap<String, Boolean> tutoresOcupados;

    public TutorFesc() {
        this.docentes = new ArrayList<>();
        this.materias = new ArrayList<>();
        this.tutoresOcupados = new HashMap<>();
    }

    public void agregarDocente(String nombre, String correo, String cedula, int telefono) {
        Docente docente = new Docente(nombre, correo, cedula, telefono);
        docentes.add(docente);
        tutoresOcupados.put(nombre, false);
    }

    public void agregarMateria(String nombre, String codigo, String[] nombresDocentes) {
        Materia materia = new Materia(nombre, codigo);
        for (String nombreDocente : nombresDocentes) {
            Docente docente = buscarDocente(nombreDocente);
            if (docente != null) {
                materia.agregarDocente(docente);
            }
        }
        materias.add(materia);
    }

    private Docente buscarDocente(String nombre) {
        for (Docente docente : docentes) {
            if (docente.getNombre().equals(nombre)) {
                return docente;
            }
        }
        return null;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public HashMap<String, Boolean> getTutoresOcupados() {
        return tutoresOcupados;
    }
}
