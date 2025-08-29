/*
 * Validar.java
 *
 * Created on 28 de julio de 2005, 02:50 PM
 */

package com.saviasaludeps.savia.web.utilidades;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Ripalacios
 * Agrupa funciones gen�ricas de validaci�n.
 */
public class Nro {
    
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 8-feb-2006<br>
    * Da formato de edici�n a valores num�ricos
    * @param d Valor double al que se lengthdara el formato.
    * @return String con el valor con formato para mostrarse en las consultas.
    */ 
    public static String getFormato(double d){
            NumberFormat nf1 = NumberFormat.getInstance();
            return nf1.format(d);
    }
    
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * determina si el valor recibido es un tipo de dato double v�lido.
    * @param valor Valor a determinar si es un double v�lido
    * @return true/false indicando si el valor recibido es o no un double v�lido.
    */ 
    public static boolean isDouble(String valor){
        double val=0; 
        boolean resul = true;
        if (valor==null || valor.trim().equals(""))
            resul = true;
        else{ 
            try{
            val = Double.parseDouble(valor.trim());
            }catch(NumberFormatException e){
                resul = false;
            }
        }
        return resul;
    }
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * determina si el valor recibido es un tipo de dato float v�lido.
    * @param valor Valor a determinar si es un float v�lido
    * @return true/false indicando si el valor recibido es o no un float v�lido.
    */ 
    public static boolean isFloat(String valor){
        float val=0; 
        boolean resul = true;
        if (valor==null || valor.trim().equals(""))
            resul = true;
        else{ 
            try{
            val = Float.parseFloat(valor.trim());
            }catch(NumberFormatException e){
                resul = false;
            }
        }
        return resul;
    }
    
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * determina si el valor recibido es un tipo e dato short v�lido.
    * @param valor Valor a determinar si es un short v�lido
    * @return true/false indicando si el valor recibido es o no un long v�lido.
    */ 
    public static boolean isShort(String valor){
        short val=0; 
        boolean resul = true;
        if (valor==null || valor.trim().equals(""))
            resul = true;
        else{ 
            try{
            val = Short.parseShort(valor.trim());
            }catch(NumberFormatException e){
                resul = false;
            }
        }
        return resul;
    }
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * determina si el valor recibido es un byte v�lido.
    * @param valor Valor a determinar si es un byte v�lido.
    * @return true/false indicando si el valor recibido es o no un long v�lido.
    */ 
    public static boolean isByte(String valor){
        byte val=0; 
        boolean resul = true;
        if (valor==null || valor.trim().equals(""))
            resul = true;
        else{ 
            try{
            val = Byte.parseByte(valor.trim());
            }catch(NumberFormatException e){
                resul = false;
            }
        }
        return resul;
    }
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 28-jul-2005<br>
    * determina si el valor recibido es un long v�lido.
    * @return true/false indicando si el valor recibido es o no un int v�lido.
    * @param valor Valor a evaluar
    */ 
    public static boolean isInt(String valor){
        long val=0; 
        boolean resul = true;
        if (valor==null || valor.trim().equals(""))
            resul = true;
        else{ 
            try{
            val = Long.parseLong(valor.trim());
            }catch(NumberFormatException e){
                resul = false;
            }
        }
        return resul;
    }
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 28-jul-2005<br>
    * determina si el valor recibido es un long v�lido.
    * @return true/false indicando si el valor recibido es o no un long v�lido.
    * @param valor Valor a evaluar
    */ 
    public static boolean isLong(String valor){
        long val=0; 
        boolean resul = true;
        if (valor==null || valor.trim().equals(""))
            resul = true;
        else{ 
            try{
            val = Long.parseLong(valor.trim());
            }catch(NumberFormatException e){
                resul = false;
            }
        }
        return resul;
    }
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 28-jul-2005<br>
    * Convierte un String a long.  Si el String es null, vacio o no num�rico, lo convierte a cero.
    * @return int con el resultado.
    * @param valor Valor string a cnvertir
    */ 
    public static long toLong(String valor){
        long val=0;
        if (valor==null || valor.trim().equals(""))
            val = 0;
        else{ 
            try{
            val = Long.parseLong(valor.trim());
            }catch(NumberFormatException e){
                val=0;
            }
        }
        return val;
    }
   /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 28-jul-2005<br>
    * Convierte un String a entero.  Si el String es null, vacio o no num�rico, lo convierte a cero.
    * @param valor Valor String a convertir.
    * @return int con el resulatado entero.
    */ 
   public static int toInt(String valor){
        int val=0;
        if (valor==null || valor.trim().equals(""))
            val = 0;
        else{ 
            try{
            val = Integer.parseInt(valor.trim());
            }catch(NumberFormatException e){
                val=0;
            }
        }
        return val;
    }
   
   /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * Convierte un String a byte.  Si el String es null, vacio o no num�rico, lo convierte a cero.
    * @param valor Valor String a convertir.
    * @return int con el resulatado entero.
    */ 
   public static byte toByte(String valor){
        byte val=0;
        if (valor==null || valor.trim().equals(""))
            val = 0;
        else{ 
            try{
            val = Byte.parseByte(valor.trim());
            }catch(NumberFormatException e){
                val=0;
            }
        }
        return val;
    }
    /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * Convierte un String a short.  Si el String es null, vacio o no num�rico, lo convierte a cero.
    * @param valor Valor String a convertir.
    * @return short con el resulatado entero.
    */ 
   public static short toShort(String valor){
        short val=0;
        if (valor==null || valor.trim().equals(""))
            val = 0;
        else{ 
            try{
            val = Short.parseShort(valor.trim());
            }catch(NumberFormatException e){
                val=0;
            }
        }
        return val;
    }
   
   /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * Convierte un String a float.  Si el String es null, vacio o no num�rico, lo convierte a 0.0
    * @param valor Valor a convertir.
    * @return float con el resultado convertido.
    */ 
   public static float toFloat(String valor){
        float val=0;
        if (valor==null || valor.trim().equals(""))
            val = 0;
        else{ 
            try{
            val = Float.parseFloat(valor.trim());
            }catch(NumberFormatException e){
                val=0;
            }
        }
        return val;
    }
   
   /**
    * Creaci�n: Ripalacios :: SystemTech Integral :: 9-ago-2005<br>
    * Convierte un String a double.  Si el String es null, vacio o no num�rico, lo convierte a 0.0
    * @param valor Valor a convertir.
    * @return double con el resultado convertido.
    */ 
   public static double toDouble(String valor){
        double val=0;
        if (valor==null || valor.trim().equals(""))
            val = 0;
        else{ 
            try{
            val = Double.parseDouble(valor.trim());
            }catch(NumberFormatException e){
                val=0;
            }
        }
        return val;
    }
}
