
package com.mycompany.TutoFesc;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HoraUtil {

    public static String obtenerHoraConEmoji() {
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return "⏰ " + horaActual.format(formatter);
    }
}
