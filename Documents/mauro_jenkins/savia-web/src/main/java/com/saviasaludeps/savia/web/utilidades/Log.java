/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

/**
 *
 * @author Nacho
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static Log instance = null;
    private static String IP = null;
    private static final String MODULO = "WEB";

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public Log() {
        try {
            byte[] direccion = InetAddress.getLocalHost().getAddress();
            IP = "";
            for (int i = 0; i < direccion.length; i++) {
                if (i > 0) {
                    IP += ".";
                }
                IP += direccion[i] & 255;
            }
        } catch (UnknownHostException ex) {
            IP = "IP DESCONOCIDA";
        }
//        try {
//            modulo = System.getProperty("sun.java.command");
//        } catch (Exception ex) {
//            modulo = "MOD.NO.IDEN";
//        }
    }

    public void error(String nombre, String mensaje, Exception e) {
        Logger logger = Logger.getLogger(nombre);
        FileHandler fh = null;
        try {
            fh = new FileHandler(PropApl.getInstance().get(PropApl.LOG_RUTA_ERRORES) + "(" + MODULO + ") " + FH.darFormatoFApl(new GregorianCalendar()) + "_error.txt");
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // the following statement is used to log any messages
            logger.log(Level.WARNING, mensaje, e);
        } catch (SecurityException | IOException ex) {
        } finally {
            if (fh != null) {
                try {
                    fh.close();
                } catch (SecurityException exe) {
                }
            }
        }
    }

    public void suceso(String nombre, String suceso) {
        Logger logger = Logger.getLogger(nombre);
        FileHandler fh = null;
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler(PropApl.getInstance().get(PropApl.LOG_RUTA_SUCESOS) + "(" + MODULO + ") " + FH.darFormatoFApl(new GregorianCalendar()) + "_suceso.txt");
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            // the following statement is used to log any messages
            logger.log(Level.INFO, suceso);
        } catch (SecurityException | IOException ex) {
        } finally {
            if (fh != null) {
                try {
                    fh.close();
                } catch (SecurityException exe) {
                }
            }
        }
    }
}
