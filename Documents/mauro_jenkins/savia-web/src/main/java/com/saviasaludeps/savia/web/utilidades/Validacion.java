/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import java.util.Date;

/**
 *
 * @author raul-palacios
 */
public class Validacion {
    
    public static boolean isNumerico(String cadena){
    	try {
            Integer.parseInt(cadena);
            return true;
    	} catch (NumberFormatException nfe){
            return false;
    	}
    }
    
    public static boolean validarFechaNacimientoPorHabeasData(Date fechaNacEncontrada, Date fechaNacPuesta){
        boolean esFechaValida ;      
        if (fechaNacEncontrada == null || fechaNacPuesta == null) {
            esFechaValida = false;
        } else {
            esFechaValida = fechaNacEncontrada.equals(fechaNacPuesta);
        }
        return esFechaValida;
    }
    
}
