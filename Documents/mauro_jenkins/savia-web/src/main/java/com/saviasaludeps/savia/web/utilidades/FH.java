/*
 * Created on Julio de 2015
 */
package com.saviasaludeps.savia.web.utilidades;

import static com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes.ESPACIO;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import static javax.persistence.criteria.Predicate.BooleanOperator.AND;

/**
 *
 * @author Raúl Palacios - SeInfor Reemplaza la clase Fecha.
 */
public class FH {
    
    //Formatos
    private static final SimpleDateFormat fomatoCompletoGuion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatoSimpleGuion = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatoMesCompleto = new SimpleDateFormat("MMMM", new Locale("es","ES"));
    private static final SimpleDateFormat formatoDiaMesGuion = new SimpleDateFormat("dd-MM");
    
    //Textos
    private static final String ESPACIO = " ";
    private static final String HORAS_CERO = "00:00:00";
    private static final String COMILLA = "'";
    private static final String HORAS_FIN = "23:59:59";
    private static final String AND = "AND";
    
    public static String darFormatoFApl(GregorianCalendar cale) {
        String str = "";
        if (cale != null && cale.isSet(GregorianCalendar.YEAR) && cale.isSet(GregorianCalendar.MONTH) && cale.isSet(GregorianCalendar.DAY_OF_MONTH)) {
            str = formatoAgno(cale.get(GregorianCalendar.YEAR)) + "-"
                    + formato(cale.get(GregorianCalendar.MONTH) + 1) + "-"
                    + formato(cale.get(GregorianCalendar.DAY_OF_MONTH));
        }
        return str;
    }

    public static String darFormatoHApl(GregorianCalendar cale) {
        String str = "";
        if (cale != null && cale.isSet(GregorianCalendar.HOUR_OF_DAY) && cale.isSet(GregorianCalendar.MINUTE)) {
            if (cale.get(GregorianCalendar.HOUR_OF_DAY) == 0) {
                str = "12:" + formato(cale.get(GregorianCalendar.MINUTE)) + " AM";
            } else if (cale.get(GregorianCalendar.HOUR_OF_DAY) == 12) {
                str = "12:" + formato(cale.get(GregorianCalendar.MINUTE)) + " PM";
            } else if (cale.get(GregorianCalendar.HOUR_OF_DAY) > 12) {
                str = formato(cale.get(GregorianCalendar.HOUR_OF_DAY) - 12) + ":" + formato(cale.get(GregorianCalendar.MINUTE)) + " PM";
            } else {
                str += formato(cale.get(GregorianCalendar.HOUR)) + ":" + formato(cale.get(GregorianCalendar.MINUTE)) + " AM";
            }
        }
        return str;
    }

    public static String darFormatoHApl24(GregorianCalendar cale) {
        String str = "";
        if (cale != null && cale.isSet(GregorianCalendar.HOUR_OF_DAY) && cale.isSet(GregorianCalendar.MINUTE)) {
            str += formato(cale.get(GregorianCalendar.HOUR_OF_DAY)) + ":" + formato(cale.get(GregorianCalendar.MINUTE));
        }
        return str;
    }

    public static String darFormatoFHApl(GregorianCalendar cale) {
        String str = "";
        if (cale != null) {
            str = darFormatoFApl(cale) + " " + darFormatoHApl(cale);
        }
        return str;
    }

    public static String darFormatoFHApl24(GregorianCalendar cale) {
        String str = "";
        if (cale != null) {
            str = darFormatoFApl(cale) + " " + darFormatoHApl24(cale);
        }
        return str;
    }

    public static GregorianCalendar darFormatoFApl(String fecha) {
        GregorianCalendar cale = new GregorianCalendar();
        cale.clear();
        if (fecha != null && !fecha.equals("")) {
            String str[] = fecha.split("-");
            if (str.length > 1) {
                cale.set(Integer.parseInt(formatoAgno(str[0])), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
            }
        }
        return cale;
    }

    public static GregorianCalendar darFormatoHApl(String hora) {
        GregorianCalendar cale = new GregorianCalendar();
        cale.clear();
        String str1[] = hora.split(" ");
        String str2[] = str1[0].split(":");
        if (str1.length > 1 && str2.length > 1) {
            if (str1[1].equals("PM")) {//PM
                if (Integer.parseInt(str2[0]) == 12) {
                    cale.set(GregorianCalendar.HOUR_OF_DAY, 12);
                } else {
                    cale.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(str2[0]) + 12);
                }
            } else {//AM
                if (Integer.parseInt(str2[0]) == 12) {
                    cale.set(GregorianCalendar.HOUR_OF_DAY, 0);
                } else {
                    cale.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(str2[0]));
                }
            }
            cale.set(GregorianCalendar.MINUTE, Integer.parseInt(str2[1]));
            if (str2.length == 2) {
                cale.set(GregorianCalendar.SECOND, 0);
            } else if (str2.length == 3) {
                cale.set(GregorianCalendar.SECOND, Integer.parseInt(str2[2]));
            }
        }
        return cale;
    }

    public static GregorianCalendar darFormatoFHApl(String fechaHora) {
        GregorianCalendar cale = new GregorianCalendar();
        cale.clear();
        if (fechaHora != null && !fechaHora.equals("")) {
            GregorianCalendar fecha = new GregorianCalendar();
            fecha.clear();
            GregorianCalendar hora = new GregorianCalendar();
            hora.clear();
            String str1[] = fechaHora.split(" ");
            if (str1.length == 3) {
                cale = new GregorianCalendar();
                fecha = darFormatoFApl(str1[0]);
                hora = darFormatoHApl(str1[1] + " " + str1[2]);
                cale.set(fecha.get(GregorianCalendar.YEAR),
                        fecha.get(GregorianCalendar.MONTH),
                        fecha.get(GregorianCalendar.DAY_OF_MONTH),
                        hora.get(GregorianCalendar.HOUR_OF_DAY),
                        hora.get(GregorianCalendar.MINUTE),
                        hora.get(GregorianCalendar.SECOND));
            }
        }
        return cale;
    }

    public static String darFormatoFBD(Date cale) {
        String str = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(cale);
        str = formatoAgno(cal.get(Calendar.YEAR)) + "-"
                + formato(cal.get(Calendar.MONTH)+1) + "-"
                + formato(cal.get(Calendar.DAY_OF_MONTH));
        return str;
    }
    
    public static String darFormatoFHBD(Date cale) {
        String str = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(cale);
        str = formatoAgno(cal.get(Calendar.YEAR)) + "-"
                + formato(cal.get(Calendar.MONTH)+1) + "-"
                + formato(cal.get(Calendar.DAY_OF_MONTH))
                + " "
                + formato(cal.get(Calendar.HOUR_OF_DAY)) + ":"
                + formato(cal.get(Calendar.MINUTE)) + ":"
                + formato(cal.get(Calendar.SECOND));
        return str;
    }

    public static String darFormatoFBD(GregorianCalendar cale) {
        String str = "";
        if (cale != null && cale.isSet(GregorianCalendar.YEAR) && cale.isSet(GregorianCalendar.MONTH) && cale.isSet(GregorianCalendar.DAY_OF_MONTH)) {
            str = formatoAgno(cale.get(GregorianCalendar.YEAR)) + "-"
                    + formato(cale.get(GregorianCalendar.MONTH) + 1) + "-"
                    + formato(cale.get(GregorianCalendar.DAY_OF_MONTH));
        }
        return str;
    }

    public static String darFormatoHBD(GregorianCalendar cale) {
        String str = "";
        if (cale != null && cale.isSet(GregorianCalendar.HOUR_OF_DAY) && cale.isSet(GregorianCalendar.MINUTE)) {
            str = formato(cale.get(GregorianCalendar.HOUR_OF_DAY)) + ":"
                    + formato(cale.get(GregorianCalendar.MINUTE));
            if (cale.isSet(GregorianCalendar.SECOND)) {
                str += ":" + formato(cale.get(GregorianCalendar.SECOND));
            }
        }
        return str;
    }

    public static String darFormatoFHBD(GregorianCalendar cale) {
        String str = "";
        if (cale != null) {
            str = darFormatoFBD(cale) + " " + darFormatoHBD(cale);
        }
        return str;
    }

    public static GregorianCalendar darFormatoFBD(String fecha) {
        GregorianCalendar cale = new GregorianCalendar();
        cale.clear();
        if (fecha != null && !fecha.equals("")) {
            String str[] = fecha.split("-");
            if (str.length == 3) {
                cale.set(Integer.parseInt(formatoAgno(str[0])), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
            }
        }
        return cale;
    }

    public static GregorianCalendar darFormatoHBD(String hora) {
        GregorianCalendar cale = new GregorianCalendar();
        cale.clear();
        String str1[] = hora.split(":");
        if (str1.length > 1) {
            cale.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(str1[0]));
            cale.set(GregorianCalendar.MINUTE, Integer.parseInt(str1[1]));
            if (str1.length == 3) {
                cale.set(GregorianCalendar.SECOND, Integer.parseInt(str1[2]));
            }
        }
        return cale;
    }

    public static GregorianCalendar darFormatoFHBD(String fechaHora) {
        GregorianCalendar cale = null;
        if (fechaHora != null && !fechaHora.equals("")) {
            GregorianCalendar fecha = new GregorianCalendar();
            fecha.clear();
            GregorianCalendar hora = new GregorianCalendar();
            hora.clear();
            String str1[] = fechaHora.split(" ");
            if (str1.length == 2) {
                cale = new GregorianCalendar();
                fecha = darFormatoFBD(str1[0]);
                hora = darFormatoHBD(str1[1]);
                cale.set(fecha.get(GregorianCalendar.YEAR),
                        fecha.get(GregorianCalendar.MONTH),
                        fecha.get(GregorianCalendar.DAY_OF_MONTH),
                        hora.get(GregorianCalendar.HOUR_OF_DAY),
                        hora.get(GregorianCalendar.MINUTE),
                        hora.get(GregorianCalendar.SECOND));
            }
        }
        return cale;
    }

    public static String darFormatoVersionApl(GregorianCalendar cale) {
        String str = "";
        if (cale != null && cale.isSet(GregorianCalendar.YEAR) && cale.isSet(GregorianCalendar.MONTH)) {
            str = (new DateFormatSymbols().getShortMonths()[cale.get(GregorianCalendar.MONTH)]) + " " + formato(cale.get(GregorianCalendar.YEAR));

        }
        return str;
    }

    private static String formato(String valor) {
        String str = "";
        if (Nro.toInt(valor) < 10) {
            str = "0";
        }
        str += Nro.toInt(valor);
        return str;
    }

    private static String formato(int valor) {
        String str = "";
        if (valor < 10) {
            str = "0";
        }
        str += valor;
        return str;
    }

    private static String formatoAgno(int valor) {
        String str = "";
        String val = String.valueOf(valor);
        if (val.length() == 1) {
            str = "200" + val;
        } else if (val.length() == 2 && valor <= 50) {
            str = "20" + val;
        } else if (val.length() == 2 && valor > 50) {
            str = "19" + val;
        } else if (val.length() == 3) {
            str = "2" + val;
        } else {
            str = val;
        }
        return str;
    }

    private static String formatoAgno(String valor) {
        String str = "";
        String val = valor;
        if (val.length() == 1) {
            str = "200" + val;
        } else if (val.length() == 2 && Nro.toInt(valor) <= 50) {
            str = "20" + val;
        } else if (val.length() == 2 && Nro.toInt(valor) > 50) {
            str = "19" + val;
        } else if (val.length() == 3) {
            str = "2" + val;
        } else {
            str = val;
        }
        return str;
    }

    public static String darFormatoAAMMDD(GregorianCalendar cale) {
        Calcular _aud = new Calcular();
        return _aud.cantidad(cale.get(GregorianCalendar.YEAR), 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.MONTH) + 1, 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.DAY_OF_MONTH), 2, '0', 'D');
    }

    public static String darFormatoAAAAMMDD(GregorianCalendar cale) {
        Calcular _aud = new Calcular();
        return _aud.cantidad(cale.get(GregorianCalendar.YEAR), 4, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.MONTH) + 1, 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.DAY_OF_MONTH), 2, '0', 'D');
    }

    public static String darFormatoHHMMSS(GregorianCalendar cale) {
        Calcular _aud = new Calcular();
        if (cale.get(GregorianCalendar.AM_PM) == 0) {
            return _aud.cantidad(cale.get(GregorianCalendar.HOUR), 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.MINUTE), 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.SECOND), 2, '0', 'D');
        } else {
            return _aud.cantidad(cale.get(GregorianCalendar.HOUR) + 12, 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.MINUTE), 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.SECOND), 2, '0', 'D');
        }
    }

    public static String darFormatoHHMM(GregorianCalendar cale) {
        Calcular _aud = new Calcular();
        return _aud.cantidad(cale.get(GregorianCalendar.HOUR_OF_DAY), 2, '0', 'D') + _aud.cantidad(cale.get(GregorianCalendar.MINUTE), 2, '0', 'D');
    }

    public static String diferenciaStr(GregorianCalendar _desde, GregorianCalendar _hasta) {
        String _str = "";
        int _dif = diferencia(_desde, _hasta);
        int _h = (int) _dif / 3600;
        int _m = (int) (_dif - _h * 3600) / 60;
        int _s = (int) (_dif - (_h * 3600) + (_m * 60));
        _str = formato(_h) + ":" + formato(_m) + ":" + formato(_s);
        return _str;
    }

    /**
     * Metodo para calcular la diferencia de dos fechas en segundos
     *
     * @param _desde
     * @param _hasta
     * @return
     */
    public static int diferencia(GregorianCalendar _desde, GregorianCalendar _hasta) {
        int _dif = 0;
        int _d = _desde.get(GregorianCalendar.YEAR) * 31536000 + _desde.get(GregorianCalendar.DAY_OF_YEAR) * 86400 + _desde.get(GregorianCalendar.HOUR_OF_DAY) * 3600 + _desde.get(GregorianCalendar.MINUTE) * 60 + _desde.get(GregorianCalendar.SECOND);
        int _h = _hasta.get(GregorianCalendar.YEAR) * 31536000 + _hasta.get(GregorianCalendar.DAY_OF_YEAR) * 86400 + _hasta.get(GregorianCalendar.HOUR_OF_DAY) * 3600 + _hasta.get(GregorianCalendar.MINUTE) * 60 + _hasta.get(GregorianCalendar.SECOND);
        _dif = _h - _d;
        return _dif;
    }

    public static String generarHash(String _add, GregorianCalendar fh) {
        return _add + "|"
                + formatoAgno(fh.get(GregorianCalendar.YEAR))
                + formato(fh.get(GregorianCalendar.MONTH))
                + formato(fh.get(GregorianCalendar.DAY_OF_MONTH))
                + formato(fh.get(GregorianCalendar.HOUR_OF_DAY))
                + formato(fh.get(GregorianCalendar.MINUTE))
                + formato(fh.get(GregorianCalendar.SECOND))
                + formato(fh.get(GregorianCalendar.MILLISECOND));
    }
    
    /**
     * Funcion que cuenta los dias entre dos fechas
     * @param fechaInicio
     * @param fechaFin
     * @return entero con los dias entre fechas
     */
    public static int contarDias(Date fechaInicio, Date fechaFin){
        long diferencia = fechaFin.getTime() - fechaInicio.getTime();
        int dias = (int) TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS) +1;
        return dias;        
    }
    
    /**
     * Funcion que agrega dias a una fecha
     * @param fecha
     * @param dias
     * @return una fecha con los dias agregados
    */
    public static Date agregarDias(Date fecha, int dias){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_MONTH, dias);
        return cal.getTime();
    }
    
     /**
     * Funcion que crea un rango de dia en una fecha dada
     * @param fecha
     * @return 
     */
    public static String crearRangoFechaCompleto(Date fecha){
        return formatoSimpleGuion.format(fecha) + ESPACIO + HORAS_CERO + COMILLA + ESPACIO + AND + ESPACIO + COMILLA + formatoSimpleGuion.format(fecha) + ESPACIO + HORAS_FIN;
    }
    
    /**
     * Funcion que devuelve el nombre del mes de un fecha
     * @param fecha
     * @return 
     */
    public static String obtenerMesFecha(Date fecha){
        return formatoMesCompleto.format(fecha);
    }
    
    /**
     * Funcion que devuelve el dia y mes de una fecha
     * @param fecha
     * @return 
     */
    public static String obtenerDiaMesFecha(Date fecha){
        return formatoDiaMesGuion.format(fecha);
    }
    
    /**
     * Funcion que devuelve el rango entre dos fechas
     * @param fechaInicial
     * @param fechaFinal
     * @return 
     */
    public static String rangoEntreDosFechasCompleto(Date fechaInicial, Date fechaFinal){
        return formatoSimpleGuion.format(fechaInicial) + ESPACIO + HORAS_CERO + COMILLA + ESPACIO + AND + ESPACIO + COMILLA + formatoSimpleGuion.format(fechaFinal) + ESPACIO + HORAS_FIN;
    }
    
    /**
     * Funcion que devuelve la fecha final de un mes
     * @param fecha
     * @return 
     */
    public static Date calcularFechaFinMes(Date fecha){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
}
