package com.saviasaludeps.savia.ejb.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date removerTiempo(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date setFinalDia(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    public static Integer getTotalSemanasActuales(Date fechaInicial) {
        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = new Date().getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        Double totalDias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        Double totalSemanas = totalDias / 7;
        return totalSemanas.intValue();
    }

    public static boolean esFechaValida(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(fecha);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
