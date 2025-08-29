/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.utilidades;

import com.saviasaludeps.savia.dominio.financiera.FinGiro;
import com.saviasaludeps.savia.dominio.financiera.FinPostulacion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author jepn
 */
public class Utilidades {
    
    public static final int COLUMNA_CONSECUTIVO = 0;
    public static final int COLUMNA_ID_GIRO = 1;
    public static final int COLUMNA_TIPO_POSTULACION = 2;
    public static final int COLUMNA_NIT_PRESTADOR = 3;
    public static final int COLUMNA_MUNICIPIO = 4;
    public static final int COLUMNA_DEPARTAMENTO = 5;
    public static final int COLUMNA_POSTULACION_ADRES = 6;
    public static final int COLUMNA_VALOR_CAPITA= 7;
    public static final int COLUMNA_VALOR_REAJUSTE = 8;
    public static final int COLUMNA_VALOR_PGP = 9;
    public static final int COLUMNA_VALOR_COMPROMISOS = 10;
    public static final int COLUMNA_VALOR_EVENTO = 11;
    public static final int COLUMNA_VALOR_TOTAL_PROGRAMADO = 12;
    
    public static final int TIPO_OPERACION_MEMORIA = 1;
    public static final int TIPO_OPERACION_DB = 2;
    
    
    public static boolean validarRespuestaError(String validacionParcial) {
        return !validacionParcial.trim().equals("");
    }
    
    public static boolean validarRespuestaOK(String validacionParcial) {
        return validacionParcial.trim().equals("");
    }

    public static String validarCampoNumerico(String campo) {
        String mensaje;
        if (campo.matches("\\d*")) {
            mensaje = "";
        } else {
            mensaje = " no cumple con el formato numérico.";
        }
        return mensaje;
    }
  
    public static String validarExistenciaCampo(String campo) {
        String mensaje;
        if (campo == null || campo.trim().equals("")) {
            mensaje = " valor nulo.";
        } else {
            mensaje = "";
        }
        return mensaje;
    }
    
    public static String validarCampoVacio(String campo) {
        String mensaje;
        if (campo == null || campo.trim().equals("")) {
            mensaje = "";
        } else {
            mensaje = "existe campo";
        }
        return mensaje;
    }
    
    public static String agregarNuevoRegistroParaArchivo(String registroTexto, String estadoOperacion, String registroError) {
        StringBuilder contenido = new StringBuilder();
        contenido.append(registroTexto)
                .append(" ")
                .append(estadoOperacion)
                .append(" ")
                .append(registroError)
                .append("\n");
        return contenido.toString();
    }

    public static boolean generarArchivoRespuesta(StringBuilder contenidoArchivo, String ruta, String nombreArchivo) {
        File archivo;
        BufferedWriter bw = null;
        boolean hayGeneracion ;
        try {
            archivo = new File(ruta, nombreArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contenidoArchivo.toString());
            bw.close();
            hayGeneracion = true;
        } catch (Exception e) {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex1) {
                }
            }
            hayGeneracion = false;
        }
        return hayGeneracion;
    }

    public static String validarEsBigDecimal(String cadena) { 
         String mensaje="";
        try { 
            new BigDecimal(cadena); 
        } catch (NumberFormatException e) {
             mensaje = " valor no decimal.";
        } 
        return mensaje;
    }
    
    public static String validarEsTipoPostulacionValida(String cadena) {
        String mensaje = "";
        try {
            int tipo = Integer.parseInt(cadena);
            if (tipo < 1 || tipo > 4) {
                mensaje = " el tipo debe estas en el rango de (1-4).";
            }
        } catch (NumberFormatException e) {
            mensaje = " error."+e.toString();
        }
        return mensaje;
    }
    
    public static String asignarNombreArchivo(String nombreArchivo, String prefijoNombre) {
        SimpleDateFormat formato = new SimpleDateFormat("YYYYMMddHHmmss");
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf("."), nombreArchivo.length());
        return String.format("%s%s%s", prefijoNombre, formato.format(new Date()),extension) ;
    }
    
    public static int contarColumnas(String cadena, String caracter) {
        return cadena.split(caracter).length;
    }
    
     public static String validarTipoPostulacion(String campo) {
        String respuesta = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        respuesta = Utilidades.validarCampoNumerico(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        respuesta = Utilidades.validarEsTipoPostulacionValida(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        return "";
    }
     
    public static String validarDecimalNoObligatorio(String campo) {
        String respuesta = Utilidades.validarCampoVacio(campo);
        if (Utilidades.validarRespuestaOK(respuesta)) {
            return "";
        }
        
        respuesta = Utilidades.validarEsBigDecimal(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        return "";
    }
    
      public static String validarDecimalObligatorio(String campo) {
        String respuesta = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        
        respuesta = Utilidades.validarEsBigDecimal(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        return "";
    }
      
     public static String validarNumeroObligatorio(String campo) {
        String respuesta = Utilidades.validarExistenciaCampo(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        
        respuesta = Utilidades.validarCampoNumerico(campo);
        if (Utilidades.validarRespuestaError(respuesta)) {
            return respuesta;
        }
        return "";
    }  
     
     
    public static  String obtenerCampo( String[] campos,  int indice) {
        String campo =  Optional.ofNullable(campos[indice]).orElse("");
        return campo.trim();
    }
   
    public static BigDecimal formatearBigDecimalVacio(String campo) {
        String valor = campo.equals("") ? "0" : campo;
        return new BigDecimal(valor);
    }
    
    public static String validarGiroPrestadorExistente(HashMap<String, Integer> cachePrestadorGiro, FinPostulacion postulacion, int tipoOperacion) {

        if (cachePrestadorGiro != null && postulacion != null) {
            String nit = Optional.ofNullable(postulacion.getPrestadorNit()).
                    orElse("");
            FinGiro giro = Optional.ofNullable(postulacion.getFinGiro()).
                    orElse(new FinGiro());
            if (!nit.isEmpty() && giro.getId() != null) {
                String clavePrestadorGiro = nit + "-" + giro.getId();
                if (cachePrestadorGiro.get(clavePrestadorGiro) != null) {
                    String mensaje = tipoOperacion == TIPO_OPERACION_MEMORIA ? "Registro duplicado con el id giro para este prestador." :
                            "Ya existe una postulación con el id giro para este prestador."; 
                    return mensaje;
                } else {
                    cachePrestadorGiro.put(clavePrestadorGiro, tipoOperacion);
                }
            }
        }

        return "";
    }
 
}
