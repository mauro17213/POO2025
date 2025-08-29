/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.Tutela.servicio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaTokenIntersavia;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SolicitudToken;
import com.saviasaludeps.savia.web.rest.Tutela.DTO.RespuestaSolitudTutela;
import com.saviasaludeps.savia.web.rest.Tutela.DTO.RespuestaTutela;
import com.saviasaludeps.savia.web.rest.Tutela.DTO.SolicitudTutela;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.primefaces.shaded.json.JSONException;

/**
 *
 * @author Stiven Giraldo
 */
public class TutelaServicio {
    
    private final static String CEDULA_CIUDADANIA = "Cedula Ciudadania";
    private final static String CEDULA_EXTRANJERIA = "Cedula Extranjeria";
    private final static String PASAPORTE = "Pasaporte";
    private final static String CERTIFICADO_NACIDO_VIVO = "Certificado de Nacido Vivo";
    private final static String REGISTRO_CIVIL_NACIMIENTO = "Registro Civil de Nacimiento";
    private final static String TARJETA_IDENTIDAD = "Tarjeta Identidad";
    private final static String CARNE_DIPLOMATICO = "Carne Diplomatico";
    private final static String SALVO_CONDUCTO = "Salvoconducto de Permanencia";
    
    private final static String ID_CEDULA_CIUDADANIA = "CC";
    private final static String ID_CEDULA_EXTRANJERIA = "CE";
    private final static String ID_PASAPORTE = "PA";
    private final static String ID_CERTIFICADO_NACIDO_VIVO = "CV";
    private final static String ID_REGISTRO_CIVIL_NACIMIENTO = "RC";
    private final static String ID_TARJETA_IDENTIDAD = "TI";
    private final static String ID_CARNE_DIPLOMATICO = "CD";
    private final static String ID_SALVO_CONDUCTO = "SC";
    
    String httpUrl;
    RespuestaTokenIntersavia token;
    String jsonEntrada;
    
    public List<RespuestaTutela> consumoServicioTutela(String tipoDocumento, String numeroDocumento){
        List<RespuestaTutela> tutelas = new ArrayList();
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        try {
            token = generarTokenExterno();
            String urlTutela = PropApl.getInstance().get(PropApl.AU_TUTELA_CONSULTA_URL);
            SolicitudTutela tutela = new SolicitudTutela(tipoDocumento,numeroDocumento);
            jsonEntrada = tutela.obtenerJSON();
            Response response = consumoPostGenerico(urlTutela, token, jsonEntrada );
            if( response != null && response.getStatus() == 200){
                RespuestaSolitudTutela respuestaSolicitud = gson.fromJson(response.getEntity().toString(), RespuestaSolitudTutela.class);
                if(respuestaSolicitud.getTutelas() != null && !respuestaSolicitud.getTutelas().isEmpty()){
                    tutelas = respuestaSolicitud.getTutelas();
                }
            }
        } catch (TimeoutException  e){
            Logger.getLogger(TutelaServicio.class.getName()).log(Level.SEVERE, null, e);
            tutelas = new ArrayList();
        } catch (Exception ex) {
            Logger.getLogger(TutelaServicio.class.getName()).log(Level.SEVERE, null, ex);
            tutelas = new ArrayList();
        }
        return tutelas;
    }
    
    private RespuestaTokenIntersavia generarTokenExterno() throws Exception {
        RespuestaTokenIntersavia tokenRespuesta;
        SolicitudToken solicitudToken = new SolicitudToken();
        String usuario = PropApl.getInstance().get(PropApl.AU_TUTELA_CONSULTA_USUARIO_TOKEN);
        String contrasena = PropApl.getInstance().get(PropApl.AU_TUTELA_CONSULTA_PASSWORD_TOKEN);
        String urlToken = PropApl.getInstance().get(PropApl.RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA);
        solicitudToken.setUsername(usuario);
        solicitudToken.setPassword(contrasena);
        tokenRespuesta = consumoToken(
                urlToken,
                solicitudToken
        );
        return tokenRespuesta;
    }

    private static RespuestaTokenIntersavia consumoToken(String httpURL, SolicitudToken solicitud) throws Exception {
        RespuestaTokenIntersavia respuesta = null;

        try {
            //Objeto GSON
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd"); //Formato fecha 
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            //Inicio consumos
            URL url = new URL(httpURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            //conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            //String jsonEntrada = gson.toJson(solicitud);
            String jsonEntrada = "username=" + solicitud.getUsername() + "&password=" + solicitud.getPassword() + "&grant_type=";
            InputStreamReader read;
            int len = jsonEntrada.length();
            conn.setRequestProperty("Content-Length", Integer.toString(len));
            conn.connect();
            OutputStreamWriter outStr = new OutputStreamWriter(conn.getOutputStream());
            outStr.write(jsonEntrada, 0, len);
            outStr.flush();
            try {
                read = new InputStreamReader(conn.getInputStream(), "UTF-8");
            } catch (IOException exception) {
                read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
            }
            //Lectura por linea
            InputStream ins = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            try (BufferedReader in = new BufferedReader(isr)) {
                String inputLine;
                String result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                }
                final RespuestaTokenIntersavia respuestasToken = gson.fromJson(result, RespuestaTokenIntersavia.class
                );
                if (respuestasToken == null) {
                    System.err.println("ERROR: Sin respuesta");
                } else {
                    respuesta = respuestasToken;
                    respuesta.setToken(respuesta.getAccess_token());
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger("Error consumoToken : "+TutelaServicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger("Error consumoToken : "+TutelaServicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonSyntaxException | JSONException ex) {
            Logger.getLogger("Error consumoToken : "+TutelaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    private static Response consumoPostGenerico(String httpUrl, RespuestaTokenIntersavia token, String jsonEntrada) {
        String result = "";
        Response response = null;
        try {
            //Objeto GSON
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd"); //Formato fecha 
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            //Inicio consumos
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token.getToken()); // Autenticacion 
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            InputStreamReader read;
            int len = jsonEntrada.length();
            conn.connect();
            OutputStreamWriter outStr = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            outStr.write(jsonEntrada, 0, len);
            outStr.flush();
            try {
                read = new InputStreamReader(conn.getInputStream(), "UTF-8");
            } catch (IOException exception) {
                read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
            }
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                //Lectura por linea
                InputStream ins = conn.getErrorStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(isr)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }

                    response = Response.status(conn.getResponseCode()).entity(result).build();
                }
            } else {
                //Lectura por linea
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(isr)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    response = Response.status(conn.getResponseCode()).entity(result).build();
                }
            }
        } catch (MalformedURLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        } catch (JsonSyntaxException | JSONException ex) {
            System.err.println("ERROR: " + ex.toString());
        }
        return response;
    }
    
    public String obtenerIdTipoDocumento(String tipoDocumento){
        String valor = "";
        switch(tipoDocumento){
            case CEDULA_CIUDADANIA:
                valor = ID_CEDULA_CIUDADANIA;
                break;
            case CEDULA_EXTRANJERIA:
                valor = ID_CEDULA_EXTRANJERIA;
                break;
            case PASAPORTE:
                valor = ID_PASAPORTE;
                break;
            case CERTIFICADO_NACIDO_VIVO:
                valor = ID_CERTIFICADO_NACIDO_VIVO;
                break;
            case REGISTRO_CIVIL_NACIMIENTO:
                valor = ID_REGISTRO_CIVIL_NACIMIENTO;
                break;
            case TARJETA_IDENTIDAD:
                valor = ID_TARJETA_IDENTIDAD;
                break;
            case CARNE_DIPLOMATICO:
                valor = ID_CARNE_DIPLOMATICO;
                break;
            case SALVO_CONDUCTO:
                valor = ID_SALVO_CONDUCTO;
                break;
            
        }
        return valor;
    }
    
}
