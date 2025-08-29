package com.saviasaludeps.savia.web.rest.NotificacionFactura.servicio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaTokenIntersavia;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SolicitudToken;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.shaded.json.JSONException;

public class WsInterOperabilidad {

    private int ws;
    private RespuestaTokenIntersavia token;
    private String httpUrl;

    public WsInterOperabilidad(int wsConfiguracion) {
        try {
            this.ws = wsConfiguracion;
            SolicitudToken solicitudToken = new SolicitudToken();
            String usuario =  PropApl.getInstance().get(PropApl.WS_USUARIO_TOKEN_CONCILIACION_FACTURA);
            String contrasena = PropApl.getInstance().get(PropApl.WS_CONTRASENA_TOKEN_CONCILIACION_FACTURA);
            String urlToken = PropApl.getInstance().get(PropApl.RUTA_SERVICIO_TOKEN_CONCILIACION_FACTURA);
            this.httpUrl = PropApl.getInstance().get(this.ws);
            solicitudToken.setUsername(usuario);
            solicitudToken.setPassword(contrasena);
            this.token = consumoToken(
                    urlToken,
                    solicitudToken);
        } catch (Exception ex) {
        }
    }

    public Response consumoPostGenerico(String jsonEntrada) {
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
            conn.setRequestProperty("Authorization", "Bearer " + getToken().getToken()); // Autenticacion 
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            InputStreamReader read;
            int len = jsonEntrada.length();
            conn.connect();
            OutputStreamWriter outStr = new OutputStreamWriter(
                    conn.getOutputStream(), StandardCharsets.UTF_8
            );
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
                    response = Response.status(conn.getResponseCode())
                            .entity(result).build();
                }
            } else {
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(isr)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    response = Response.status(conn.getResponseCode())
                            .entity(result).build();
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

    public final RespuestaTokenIntersavia consumoToken(String httpURL, SolicitudToken solicitud) throws Exception {
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
        } catch (IOException ex) {
        } catch (JsonSyntaxException | JSONException ex) {
        }
        return respuesta;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }

    public RespuestaTokenIntersavia getToken() {
        return token;
    }

    public void setToken(RespuestaTokenIntersavia token) {
        this.token = token;
    }

}
