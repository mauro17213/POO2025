/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.aseguramiento.utilidades;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
//import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class Utilidades {

    public static float getMesesPeriodo(Date fechaInicio, Date fechaFin) {
        float cantMeses = 0;
        try {
            //2024-09-24 jyperez revisión error fechaInicio que en update si no se modifica viene con un objeto Sql.Date y no un Util.Date
            Calendar fechaIni = Calendar.getInstance();
            Calendar fechaFn = Calendar.getInstance();
            //asignamos las fechas a los calendar
            fechaIni.setTime(fechaInicio);
            fechaFn.setTime(fechaFin);
            // Cálculo de las diferencias.
            int years = fechaFn.get(Calendar.YEAR) - fechaIni.get(Calendar.YEAR);
            int months = fechaFn.get(Calendar.MONTH) - fechaIni.get(Calendar.MONTH);
            int days = fechaFn.get(Calendar.DAY_OF_MONTH) - fechaIni.get(Calendar.DAY_OF_MONTH);

            // Calcular la diferencia entre las fechas
            Integer meses = months;
            Integer mesesAnio = (years * 12);
            // como los valores de los dias pueden dar negativo hay que validar - si es menor a cero se resta el mes... no se ha completado
            // los días no los contariamos ya que hacemos el proceso es con los meses...
            if ( days < 0) {
                meses = meses -1;
            }
            cantMeses = meses + mesesAnio;
            //cantMeses+= Float.parseFloat(mesDia);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantMeses;
    }

    // Método para calcular la cantidad de años bisiestos en un rango
    private static int calcularAniosBisiestos(int anioInicio, int anioFin) {
        int contador = 0;
        for (int anio = anioInicio; anio <= anioFin; anio++) {
            if (esBisiesto(anio)) {
                contador++;
            }
        }
        return contador;
    }

    // Método para verificar si un año es bisiesto
    private static boolean esBisiesto(int anio) {
        // Divisible entre 4, y (NO divisible entre 100 o divisible entre 400)
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }

    public static boolean esBisiesto_(int anio) {
        // Divisible entre 4, y (NO divisible entre 100 o divisible entre 400)
        return anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0);
    }

}
