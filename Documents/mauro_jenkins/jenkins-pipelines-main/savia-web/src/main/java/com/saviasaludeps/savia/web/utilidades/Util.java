/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ripalacios
 */
public class Util {

    public static String formatoNombreDirectorio(String input) {
        String output = input.toLowerCase().trim();
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        output = output.replace("_", "-").replace(" ", "-");
        output = output.replace("----", "-");
        output = output.replace("---", "-");
        output = output.replace("--", "-");
        return output;
    }

    public static String reemplazarCaracteresEspeciales(String palabra) {
        String[] caracteresMalos = {" ", "ñ", "|", "à", "á", "À", "Á", "è", "é", "È", "É", "ì", "í", "Ì", "Í", "ò", "ó", "Ò", "Ó", "ù", "ú", "Ù", "Ú", "\b", "/", ":", "<", "*", "?", ">", "@"};
        String[] caracteresBuenos = {"-", "n", "", "a", "a", "A", "A", "e", "e", "E", "E", "i", "i", "I", "I", "o", "o", "O", "O", "u", "u", "U", "U", "", "", "", "", "", "", "", "a"};
        for (String letraMala : caracteresMalos) {
            if (palabra.contains(letraMala)) {
                palabra = palabra.replace(letraMala, caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
            }
        }
        return palabra;
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

    public static String fechaDateAString(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
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

    public static HashMap<Integer, Ubicacion> convertToHashUbicacion(List<Ubicacion> list) {
        HashMap<Integer, Ubicacion> map = new HashMap<>();
        for (Ubicacion i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }

    public static HashMap<Integer, Ubicacion> obtenerHasUbicacion(List<Ubicacion> listaUbicaciones) {
        HashMap<Integer, Ubicacion> hash = new HashMap();
        listaUbicaciones.forEach(ubicacion -> {
            hash.put(ubicacion.getId(), ubicacion);
        });
        return hash;
    }

    public static String completarCaracteres(String valor, char car, int cant) {
        for (int i = valor.length(); i <= cant; i++) {
            valor = car + valor;
        }
        return valor;
    }

}
