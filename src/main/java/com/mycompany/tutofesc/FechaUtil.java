/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.TutoFesc;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaUtil {

    public static String obtenerFechaConEmoji() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "📅 " + fechaActual.format(formatter);
    }
}
