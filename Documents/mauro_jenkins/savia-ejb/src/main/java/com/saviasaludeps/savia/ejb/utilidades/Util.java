/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Util {

    public static String convertirArrayToString(String[] array, String separador) {
        String result = "";
        if (array.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : array) {
                sb.append(s).append(separador);
            }
            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        return result;
    }

    public static String calcularEdad(Date fechaNacimientoIn) {
        String edad = "";
        Date fechaNacimiento;
        try {
            if (fechaNacimientoIn != null) {
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimientoIn.toString());
                Calendar fechaNac = Calendar.getInstance();
                Calendar fechaAct = Calendar.getInstance();
                fechaNac.setTime(fechaNacimiento);
                int anio = fechaAct.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
                int mes = fechaAct.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
                int dia = fechaAct.get(Calendar.DATE) - fechaNac.get(Calendar.DATE);
                if (mes < 0 || (mes == 0 && dia < 0)) {
                    anio--;
                }
                if (anio == 0) {
                    if (mes == 0) {
                        if (dia > 0) {
                            edad = String.valueOf(dia) + " Dias";
                        }
                    } else {
                        edad = String.valueOf(mes) + " Meses";
                    }
                } else {
                    edad = String.valueOf(anio) + " Años";
                }
            }
        } catch (ParseException e) {
        }
        return edad;
    }

    public static Date fechaStringADate(String fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(fecha);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    public static String fechaDateAString(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
    }
    
    public static String filtroSupresionSaltoLinea(String cadenaIn) {
        String cadenaOut = "";
        if (cadenaIn != null) {
            cadenaOut = cadenaIn.replaceAll("\\n", "");
            cadenaOut = cadenaOut.replaceAll("\\r", "");
        }
        return cadenaOut;
    }
    
    public static int convertirANumero(String str) {
        int numero;
        try {
            numero = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            numero = 0;
        }
        return numero;
    }
    
    public static HashMap<Integer, Maestro> convertToHash(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }
    
    public static HashMap<String, Maestro> convertToHashValor(List<Maestro> list) {
        HashMap<String, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getValor(), i);
        }
        return map;
    }
    
}
