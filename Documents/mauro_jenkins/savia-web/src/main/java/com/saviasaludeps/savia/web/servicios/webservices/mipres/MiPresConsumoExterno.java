/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.servicios.webservices.mipres;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author oquiroz
 */
public class MiPresConsumoExterno {

    public final static int ESTADO_CONSUMO_CONSULTANDO = 0;
    public final static int ESTADO_CONSUMO_GUARDANDO = 1;
    public final static int ESTADO_CONSUMO_EXITOSO = 2;
    public final static int ESTADO_CONSUMO_CONSULTANDO_ERROR = 3;
    public final static int ESTADO_CONSUMO_GUARDANDO_ERROR = 4;

    /**
     * Método para obtener token vigente
     *
     * @return @throws Exception
     */
    public Token obtenerTokenInterSavia() throws Exception {
        String usuario = PropApl.getInstance().get(PropApl.MP_WS_TOKEN_USUARIO);
        String contrasena = PropApl.getInstance().get(PropApl.MP_WS_TOKEN_CONTRASENA);
        String httpURL = PropApl.getInstance().get(PropApl.MP_WS_TOKEN_URL);
        return obtenerTokenInterSavia(httpURL, usuario, contrasena);
    }

    /**
     * Método para obtener token vigente
     *
     * @param httpURL
     * @param usuario
     * @param contrasena
     * @return
     * @throws Exception
     */
    public Token obtenerTokenInterSavia(String httpURL, String usuario, String contrasena) throws Exception {
        Token token = null;
        try {
            //url token
            URL url = new URL(httpURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setDoOutput(true);
            //Conexion y carga de variables
            String input = "username=" + usuario + "&password=" + contrasena + "&grant_type=";
            int len = input.length();
            conn.setRequestProperty("Content-Length", Integer.toString(len));
            conn.connect();
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write(input, 0, len);
            osw.flush();
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new Exception("Error de comunicacion con intersavia |");
            } else {
                //Lectura por linea
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(ins, "utf8"), 8192)) {
                    String inputLine;
                    token = null;
                    if ((inputLine = in.readLine()) != null) {
                        Gson gson = new Gson();
                        token = gson.fromJson(inputLine, Token.class);
                    }
                    if (token == null || token.getAccessToken().isEmpty()) {
                        throw new Exception("Los datos enviados no son validos para generar token |");
                    }
                }
            }
        } catch (JsonSyntaxException | IOException ex) {
            throw new Exception("Ip, Usuario o Cantraseña Incorrecta |");
        }
        return token;
    }

    public List<DireccionamientoBaseDTO> servicioGetDireccionamientoPorNumeroPrescripcion(
            String httpUrl,
            Token token,
            String prescripcion) throws Exception {
        List<DireccionamientoBaseDTO> respuesta = null;
        String result = "";
        Response response = null;
        try {
            httpUrl += "/" + prescripcion;
            //Objeto GSON
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd"); //Formato fecha 
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            //Inicio consumos Direccionamiento
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + token.getAccessToken()); // Autenticacion 
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                //Lectura por linea
                InputStream ins = conn.getErrorStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(ins, "utf8"), 8192)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    throw new Exception(conn.getResponseCode() + "||" + result);
                    //response = Response.status(conn.getResponseCode()).entity(result).build();
//                    getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
                }
            } else {
                //Lectura por linea
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(ins, "utf8"), 8192)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    Type listType = new TypeToken<List<DireccionamientoBaseDTO>>() {
                    }.getType();
                    final List<DireccionamientoBaseDTO> respuestaDireccionamiento = gson.fromJson(result, listType);

                    if (respuestaDireccionamiento == null) {
                        System.err.println("ERROR: Sin respuesta");
                    } else {
                        respuesta = respuestaDireccionamiento;
                    }
                }
            }
        } catch (MalformedURLException ex) {
            throw new Exception(ex.getMessage());
//            System.err.println("ERROR: " + ex.getMessage());
//            getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
//            System.err.println("ERROR: " + ex.getMessage());
//            getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
        } catch (JsonSyntaxException ex) {
            throw new Exception(ex.getMessage());
//            System.err.println("ERROR: " + ex.toString());
//            getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
        }
        return respuesta;
    }

    public List<NoDireccionamientoBaseDTO> servicioGetNoDireccionamientoPorNumeroPrescripcion(
            String httpUrl,
            Token token,
            String prescripcion) throws Exception {
        List<NoDireccionamientoBaseDTO> respuesta = null;
        String result = "";
        Response response = null;
        try {
            httpUrl += "/" + prescripcion;
            //Objeto GSON
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd"); //Formato fecha 
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            //Inicio consumos Direccionamiento
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Authorization", "Bearer " + token.getAccessToken()); // Autenticacion 
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                //Lectura por linea
                InputStream ins = conn.getErrorStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(ins, "utf8"), 8192)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    throw new Exception(conn.getResponseCode() + "||" + result);
                    //response = Response.status(conn.getResponseCode()).entity(result).build();
//                    getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
                }
            } else {
                //Lectura por linea
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(ins, "utf8"), 8192)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result += inputLine;
                    }
                    Type listType = new TypeToken<List<NoDireccionamientoBaseDTO>>() {
                    }.getType();
                    final List<NoDireccionamientoBaseDTO> respuestaDireccionamiento = gson.fromJson(result, listType);

                    if (respuestaDireccionamiento == null) {
                        System.err.println("ERROR: Sin respuesta");
                    } else {
                        respuesta = respuestaDireccionamiento;
                    }
                }
            }
        } catch (MalformedURLException ex) {
            throw new Exception(ex.getMessage());
//            System.err.println("ERROR: " + ex.getMessage());
//            getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
//            System.err.println("ERROR: " + ex.getMessage());
//            getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
        } catch (JsonSyntaxException ex) {
            throw new Exception(ex.getMessage());
//            System.err.println("ERROR: " + ex.toString());
//            getPrescripcionWsRemoto().actualizarConsumo(ESTADO_CONSUMO_CONSULTANDO_ERROR, result, 0, idConsumo, 0);
        }
        return respuesta;
    }

    public Date obtenerFVencimientoToken(Token token) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, Integer.parseInt(token.getExpiresIn()) - 1);
        Date fecha = calendar.getTime();
        return fecha;
    }

    public List<JuntaMedicaBaseDTO> servicioGetJuntaPorNumeroPrescripcion(
            String httpUrl, Token token, String prescripcion
    ) throws Exception {
        try {
            httpUrl += "/" + prescripcion;
            String result = consumoGET(httpUrl, token);
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd");
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            Type listType = new TypeToken<List<JuntaMedicaBaseDTO>>() {
            }.getType();
            List<JuntaMedicaBaseDTO> respuesta = gson.fromJson(result, listType);
            return (respuesta != null) ? respuesta : Collections.emptyList();
        } catch (MalformedURLException ex) {
            throw new Exception(ex.getMessage());
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        } catch (JsonSyntaxException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public RespuestaAnulaacionPrescripcionDTO consultaPrescripcionesAnulacion(
            String httpUrl, Token token, String noPrescripcion
    ) throws Exception {
        try {
            httpUrl += "/" + noPrescripcion;
            String result = consumoGET(httpUrl, token); 

            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd");
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();

            PreRespuestaAnulacionPrescripcionDTO[] respuestaArray = gson.fromJson(
                    result, PreRespuestaAnulacionPrescripcionDTO[].class
            );

            // Validación para evitar NullPointerException
            if (respuestaArray != null && respuestaArray.length > 0) {
                return respuestaArray[0].getAnulaciones();
            } else {
                return new RespuestaAnulaacionPrescripcionDTO(); // Devuelve objeto vacío si no hay datos
            }

        } catch (MalformedURLException ex) {
            throw new Exception("URL mal formada: " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error de comunicación: " + ex.getMessage());
        } catch (JsonSyntaxException ex) {
            throw new Exception("Error al interpretar JSON: " + ex.getMessage());
        }
    }

    public List<FacturacionBaseDTO> servicioGetFacturaPorNumeroPrescripcion(
            String httpUrl, Token token, String prescripcion
    ) throws Exception {
        try {
            httpUrl += "/" + prescripcion;
            String result = consumoGET(httpUrl, token);
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd");
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            Type listType = new TypeToken<List<FacturacionBaseDTO>>() {
            }.getType();
            List<FacturacionBaseDTO> respuesta = gson.fromJson(result, listType);
            return (respuesta != null) ? respuesta : Collections.emptyList();
        } catch (MalformedURLException ex) {
            throw new Exception(ex.getMessage());
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        } catch (JsonSyntaxException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<ReporteEntregaBaseDTO> servicioGetEntregaPorNumeroPrescripcion(
            String httpUrl, Token token, String prescripcion
    ) throws Exception {
        try {
            httpUrl += "/" + prescripcion;
            String result = consumoGET(httpUrl, token);
            GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
            gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd");
            gsonBuilderRespuesta.serializeNulls();
            Gson gson = gsonBuilderRespuesta.create();
            Type listType = new TypeToken<List<ReporteEntregaBaseDTO>>() {
            }.getType();
            List<ReporteEntregaBaseDTO> respuesta = gson.fromJson(result, listType);
            return (respuesta != null) ? respuesta : Collections.emptyList();
        } catch (MalformedURLException ex) {
            throw new Exception(ex.getMessage());
        } catch (IOException ex) {
            throw new Exception(ex.getMessage());
        } catch (JsonSyntaxException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    private String consumoGET(String httpUrl, Token token) throws Exception {//nueva version
        StringBuilder result = new StringBuilder();
        try {
            // Inicio consumos Prescripcion
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            String charset = "utf-8";
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + token.getAccessToken()); // Autenticación
            conn.setRequestMethod("GET");

            int codigo = conn.getResponseCode();
            if (codigo == 200 || codigo == 201) {
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf8"), 8192)) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result.append(inputLine);
                    }
                }
            }
        } catch (MalformedURLException ex) {
            throw new Exception("URL mal formada: " + ex.getMessage());
        } catch (IOException | JsonSyntaxException ex) {
            throw new Exception("Error de conexión o sintaxis JSON: " + ex.getMessage());
        }
        return result.toString();
    }

}
