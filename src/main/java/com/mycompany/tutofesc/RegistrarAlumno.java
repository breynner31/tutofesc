package com.mycompany.TutoFesc;

import java.awt.Dimension;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;

public class RegistrarAlumno {

    public static void main(String[] args) {
        TutorFesc tutorFesc = new TutorFesc();

        // Agregar docentes
        tutorFesc.agregarDocente("Sebastian Galindo", "sebastian@fesc.edu.co", "1094220447", 313555972);
        tutorFesc.agregarDocente("JESUS FIGUEROA", "jesus@fesc.edu.co", "1091358527", 312568486);
        tutorFesc.agregarDocente("Angel Ureña", "angel@fesc.edu.co", "1093674628", 311255658);

        // Agregar materias
        tutorFesc.agregarMateria("ARQUICTECTURA - DISEÑO - SOFTWARE", "ADS101", new String[]{"Sebastian Galindo", "JESUS FIGUEROA"});
        tutorFesc.agregarMateria("PROGRAMACION POO", "POO102", new String[]{"Sebastian Galindo", "Angel Ureña"});
        tutorFesc.agregarMateria("DESARROLLO BASICO EN APLICACIONES WEB", "DAW103", new String[]{"JESUS FIGUEROA", "Angel Ureña"});

        // Registro de alumnos
        RegistrarAlumno app = new RegistrarAlumno();
        app.iniciar(tutorFesc);
    }

    public void iniciar(TutorFesc tutorFesc) {
        ArrayList<String[]> registros = new ArrayList<>();
        HashMap<String, Boolean> tutoresOcupados = tutorFesc.getTutoresOcupados();

        do {
            // Ingresar a la página o app
            JOptionPane.showMessageDialog(null, "Bienvenido a TUTO FESC");

            // Ingresar nombre del alumno
            String nombreAlumno = null;
            boolean nombreValido = false;

            while (!nombreValido) {
                nombreAlumno = JOptionPane.showInputDialog("Ingrese su nombre:");
                if (nombreAlumno == null || nombreAlumno.trim().isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else {
                    nombreValido = true;
                }
            }

            // Registrarse como estudiantes de la FESC (correo institucional)
            String correo = null;
            boolean correoValido = false;

            while (!correoValido) {
                correo = JOptionPane.showInputDialog("Ingrese su correo institucional:");
                if (correo == null) { // Si el usuario presiona "Cancelar" o cierra el diálogo
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    } else {
                        continue;
                    }
                }
                if (correo.endsWith("@fesc.edu.co")) {
                    correoValido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un correo institucional válido con terminación @fesc.edu.co.");
                }
            }

            // Selección de materia la cual necesita refuerzo
            ArrayList<Materia> materiasList = tutorFesc.getMaterias();
            String[] materias = materiasList.stream().map(Materia::getNombre).toArray(String[]::new);
            String materia = (String) JOptionPane.showInputDialog(null, "Seleccione la materia que necesita refuerzo:",
                    "Seleccionar Materia", JOptionPane.QUESTION_MESSAGE, null, materias, materias[0]);

            if (materia == null) { // Si el usuario presiona "Cancelar" o cierra el diálogo
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }

            // Obtener tutores disponibles para la materia seleccionada
            Materia materiaSeleccionada = materiasList.stream().filter(m -> m.getNombre().equals(materia)).findFirst().orElse(null);
            String[] tutores = materiaSeleccionada.getDocentes().stream().map(Docente::getNombre).toArray(String[]::new);

            // Selección tutor
            String tutor = null;
            boolean tutorValido = false;
            while (!tutorValido) {
                tutor = (String) JOptionPane.showInputDialog(null, "Seleccione un tutor para " + materia + ":",
                        "Seleccionar Tutor", JOptionPane.QUESTION_MESSAGE, null, tutores, tutores[0]);

                if (tutor == null) { // Si el usuario presiona "Cancelar" o cierra el diálogo
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    } else {
                        continue;
                    }
                }

                if (tutoresOcupados.get(tutor)) {
                    JOptionPane.showMessageDialog(null, "El tutor " + tutor + " ya está ocupado. Por favor, seleccione otro tutor.");
                    continue;
                }

                tutorValido = true;
                tutoresOcupados.put(tutor, true); // Marcar el tutor como ocupado
            }

            // Mostrar los datos personales del tutor
            Docente docenteSeleccionado = null;
            if (materiaSeleccionada != null && materiaSeleccionada.getDocentes() != null) {
                final String tutorSeleccionado = tutor;
                docenteSeleccionado = materiaSeleccionada.getDocentes().stream()
                        .filter(d -> d != null && d.getNombre() != null && d.getNombre().equals(tutorSeleccionado))
                        .findFirst().orElse(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede encontrar el docente para la materia seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                continue; // Volvemos al inicio del bucle para permitir al usuario seleccionar otra materia
            }

            if (docenteSeleccionado != null) {
                // Crear un cuadro de diálogo más grande para mostrar los datos personales del tutor
                JTextArea textArea = new JTextArea(docenteSeleccionado.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 200));
                JOptionPane.showMessageDialog(null, scrollPane, "Datos del Tutor", JOptionPane.INFORMATION_MESSAGE);
                       } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos del tutor.", "Error", JOptionPane.ERROR_MESSAGE);
                continue; // Volvemos al inicio del bucle para permitir al usuario seleccionar otro tutor
            }

            // Perfil del tutor (virtual-presencial)
            String[] modalidad = {"Virtual", "Presencial"};
            String perfilTutor = (String) JOptionPane.showInputDialog(null, "Seleccione la modalidad del tutor:",
                    "Perfil del Tutor", JOptionPane.QUESTION_MESSAGE, null, modalidad, modalidad[0]);

            if (perfilTutor == null) { // Si el usuario presiona "Cancelar" o cierra el diálogo
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }

            // Seleccionar fecha y hora de la tutoría
            String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha de la tutoría (YYYY-MM-DD):");
            String hora = JOptionPane.showInputDialog(null, "Ingrese la hora de la tutoría (HH:MM):");

            // Mostrar resumen de la solicitud y registrar al alumno
            String[] registro = {
                    "Nombre: " + nombreAlumno,
                    "Correo: " + correo,
                    "Materia: " + materia,
                    "Tutor: " + tutor,
                    "Modalidad: " + perfilTutor,
                    "Fecha: " + fecha,
                    "Hora: " + hora
            };
            registros.add(registro);

            // Mostrar mensaje de finalización del registro del alumno
            JOptionPane.showMessageDialog(null, "Se ha finalizado el registro del alumno " + nombreAlumno + ".", "Registro Finalizado", JOptionPane.INFORMATION_MESSAGE);

            // Preguntar al usuario si quiere registrar otro alumno
            int option = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro alumno?", "Registrar Otro Alumno", JOptionPane.YES_NO_OPTION);
            if (option != JOptionPane.YES_OPTION) {
                break;
            }
        } while (true);

        // Mostrar la información de todos los alumnos registrados
        StringBuilder registroAlumnos = new StringBuilder();
        for (String[] registro : registros) {
            registroAlumnos.append(String.join("\n", registro)).append("\n\n");
        }

        // Crear un cuadro de diálogo más grande para mostrar el registro de alumnos
        JTextArea textArea = new JTextArea(registroAlumnos.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "Registro de Alumnos", JOptionPane.INFORMATION_MESSAGE);
    }
}
