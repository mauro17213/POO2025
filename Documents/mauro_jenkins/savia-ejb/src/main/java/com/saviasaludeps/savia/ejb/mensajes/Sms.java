package com.saviasaludeps.savia.ejb.mensajes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Sms extends Thread {

    private static final String URL = "http://www.portalsms.co/wsSMS/clienteWS.php";
    private static final String SMS = "admin.wi";
    private static final String PASSWORD = "67ab1640";
    private static final String PRE = "";

    static final String SEPARADOR_CELULAR = ",";

    private String destinos = "";
    private String mensaje = "";

    public Sms(String destinos, String mensaje) {
        this.destinos = destinos;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {
            if (destinos != null || !destinos.equals("")) {
                String[] destino = destinos.split(SEPARADOR_CELULAR);
                if (destino.length != 0) {
                    for (String dest : destino) {
                        if (!Sms.envioSMS(dest, "Alerta: " + mensaje)) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            
        }
    }

    private static boolean envioSMS(String destino, String mensaje) {
        boolean exitoso = false;
        try {
            URL miUrl = new URL(URL + "?celular=" + destino + "&mensaje=" + genMensaje(mensaje) + "&usuario=" + SMS + "&clave=" + PASSWORD);
            URLConnection miUrlCon = miUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(miUrlCon.getInputStream()));
            String str;
            int _res = 0;
            while ((str = br.readLine()) != null) {
                _res++;
                if (_res == 2) {
                    try {
                        _res = Integer.parseInt(str.trim());
                    } catch (NumberFormatException e) {
                    }
                    if (_res >= 400 && _res <= 412) {
                        throw new Exception("");
                    }
                }
            }
            exitoso = true;
        } catch (IOException ex) {
        } catch (Exception ex) {
        }
        return exitoso;
    }

    private static String genMensaje(String _msj) {
        if (_msj == null || _msj.equals("")) {
            return "Mensaje Indeterminado";
        } else {
            String mensaje = _msj.replace(' ', '+');
            if (mensaje.length() > 160) {
                mensaje = mensaje.substring(0, 159);
            }
            return mensaje;
        }
    }

}
