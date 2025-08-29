/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

/**
 *
 * @author rpalacios
 */
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
//import com.saviasaludeps.savia.dominio.webservices.Afiliado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.MediaType;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author Admin}
 */
public class UtilRest {

    public static final String BASIC_AUTH = "cHJ1ZWJhOnBydWViYQ==";
    //url de pruebas
    //public static final String URL_AFILIADO_GET = "http://10.250.0.119/intrasavia-webservices/rest/afiliado/consultar-afiliado-largo/";
    public static final String URL_AFILIADO_GET = "http://localhost:8080/intrasavia-webservices/rest/afiliado/consultar-afiliado-largo/";

    public static GsAfiliado consultarUsuarioServicioGet(String TipoDocumento, String Documento)
            throws JsonSyntaxException, IOException, Exception {
        JSONObject jsonObj;
        GsAfiliado afiliado = new GsAfiliado();
        String httpURL = URL_AFILIADO_GET.trim();
        int CODIGO_EXITO_RESPUESTA_SERVICIO = 1;
        try {
            URL url = new URL(httpURL + TipoDocumento + "/" + Documento);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + BASIC_AUTH);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestMethod("GET");
            InputStreamReader read;
            conn.connect();
            try {
                read = new InputStreamReader(conn.getInputStream(), "UTF-8");
            } catch (IOException exception) {
                try {
                    read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw e;
                }
            }
            //Lectura por linea
            InputStream ins = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            try (BufferedReader in = new BufferedReader(isr)) {
                String inputLine;
                if ((inputLine = in.readLine()) != null) {
                    inputLine = inputLine.replace("},{", "}\n{");
                    jsonObj = new JSONObject(inputLine);
                    int codigoRespuestaPeticion = jsonObj.getInt("codigoRespuesta");
                    JSONObject afiliadoEncontrado;
                    if (codigoRespuestaPeticion == CODIGO_EXITO_RESPUESTA_SERVICIO) {
                        afiliadoEncontrado = jsonObj.getJSONObject("afiliado");
                        afiliado.setPrimerNombre(afiliadoEncontrado.getString("primerNombre"));
                        afiliado.setSegundoNombre(afiliadoEncontrado.getString("segundoNombre"));
                        afiliado.setPrimerApellido(afiliadoEncontrado.getString("primerApellido"));
                        afiliado.setSegundoApellido(afiliadoEncontrado.getString("segundoApellido"));
//                        afiliado.setTipoDocumento(afiliadoEncontrado.getString("tipoDocumento"));
//                        String pattern = "yyyy-MM-dd";
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//                        Date date = simpleDateFormat.parse(afiliadoEncontrado.getString("fechaNacimiento"));
//                        afiliado.setFechaNacimiento(date);
//                        afiliado.setDepartamentoEs(afiliadoEncontrado.getString("departamentoEs"));
//                        afiliado.setCiudadDescripcion(afiliadoEncontrado.getString("ciudadDescripcion"));
//                        afiliado.setEstadoAfiliadoDescripcion(afiliadoEncontrado.getString("estadoAfiliadoDescripcion"));
//                        //afiliado.setSexoDescripcion( afiliadoEncontrado.getString("sexoDescripcion") == null ? "" : afiliadoEncontrado.getString("sexoDescripcion") );
//                        afiliado.setCodigoSexo(afiliadoEncontrado.getString("sexoCodigo"));
//                        //afiliado.setZonaDescripcion( afiliadoEncontrado.getString("zonaDescripcion") == null ? "" : afiliadoEncontrado.getString("zonaDescripcion") );
//                        afiliado.setCodigoZona(afiliadoEncontrado.getString("zonaCodigo"));
//                        afiliado.setContratoAfiliado(afiliadoEncontrado.getString("contratoAfiliado"));
//                        afiliado.setEstadoAfiliadoCodigo(afiliadoEncontrado.getInt("estadoAfiliadoCodigo"));
//                        afiliado.setEstadoAfiliadoDescripcion(afiliadoEncontrado.getString("estadoAfiliadoDescripcion"));
                    }
                }
            }
        } catch (JsonSyntaxException | IOException ex) {
            throw ex;
        }
        return afiliado;
    }

    public static String consumoTokenIntersavia(String httpURL, String usuario, String contrasena) throws Exception {
        String token = "";
        try {
            JSONObject jsonObj;
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
            //Lectura por linea
            InputStream ins = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            try (BufferedReader in = new BufferedReader(isr)) {
                String inputLine;
                token = null;
                if ((inputLine = in.readLine()) != null) {
                    Gson gson = new Gson();
                    inputLine = inputLine.replace("},{", "}\n{");
                    jsonObj = new JSONObject(inputLine);
                    token = jsonObj.getString("access_token");
                }
            }
        } catch (JsonSyntaxException | IOException ex) {
            throw new Exception("Error al conectarse con el servicio generación token :".concat(httpURL));
        }
        return token;
    }

    public static GsAfiliado consumeAfiliadoIntersavia(String httpURL, String token, String tipoIdentificacion, String identificacion, String fechaNacimiento) throws Exception {
        JSONObject jsonObj;
        GsAfiliado afiliado = new GsAfiliado();
        try {
            if (token != null && !token.isEmpty()) {
                httpURL = httpURL.trim();
                URL url = new URL(httpURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + token); // Autenticacion
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestMethod("POST");
                String jsonEntrada = "{\"tipoDocumento\":\"" + tipoIdentificacion + "\","
                        + "\"numeroDocumento\":\"" + identificacion + "\","
                        + "\"fechaNacimiento\":\"" + fechaNacimiento + "\","
                        + "\"primerNombre\":\"\","
                        + "\"segundoNombre\":\"\","
                        + "\"primerApellido\":\"\","
                        + "\"segundoApellido\":\"\"}";
                InputStreamReader read;
                int len = jsonEntrada.length();
                conn.connect();
                OutputStreamWriter outStr = new OutputStreamWriter(conn.getOutputStream());
                outStr.write(jsonEntrada, 0, len);
                outStr.flush();
                try {
                    read = new InputStreamReader(conn.getInputStream(), "UTF-8");
                } catch (IOException exception) {
                    try {
                        read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new Exception("Error al buscar el servicio web: ".concat(httpURL));
                    }
                }
                //Lectura por linea
                InputStream ins = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(ins);
                try (BufferedReader in = new BufferedReader(isr)) {
                    String inputLine;
                    if ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("},{")) {
                            int i = inputLine.indexOf("},{");
                            inputLine = inputLine.substring(0, i).concat("}]}");
                        }
                        inputLine = inputLine.replace("},{", "}\n{");
                        jsonObj = new JSONObject(inputLine);
                        if (jsonObj.has("afiliados")) {
                            boolean existeAfiliado = !jsonObj.get("afiliados").equals(JSONObject.NULL);
                            if (existeAfiliado) {
                                JSONObject afiliadoEncontrado;
                                JSONArray arrayAfiliado = (JSONArray) jsonObj.get("afiliados");
                                afiliadoEncontrado = arrayAfiliado.getJSONObject(0);
                                afiliado.setPrimerNombre(afiliadoEncontrado.getString("primerNombreAfiliado"));
                                afiliado.setSegundoNombre(afiliadoEncontrado.getString("segundoNombreAfiliado"));
                                afiliado.setPrimerApellido(afiliadoEncontrado.getString("primerApellidoAfiliado"));
                                afiliado.setSegundoApellido(afiliadoEncontrado.getString("segundoApellidoAfiliado"));
//                                afiliado.setTipoDocumento(afiliadoEncontrado.getString("tipoDocumentoAfiliado"));
//                                afiliado.setDocumento(afiliadoEncontrado.getString("documentoAfiliado"));
//                                afiliado.setSexoDescripcion(afiliadoEncontrado.getString("sexoAfiliado"));
                                //Fecha de nacimiento
                                String pattern = "yyyy-MM-dd";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                                Date date = simpleDateFormat.parse(afiliadoEncontrado.getString("fechaNacimientoAfiliado"));
                                afiliado.setFechaNacimiento(date);
//                                afiliado.setEstadoAfiliadoDescripcion(afiliadoEncontrado.getString("estadoAfiliacion"));
//                                //Lugar de residencia
//                                afiliado.setPrefijoMunicipio(afiliadoEncontrado.getString("codCiudadResidencia"));
//                                afiliado.setPrefijoDepartamento(afiliadoEncontrado.getString("codDepartamentoResidencia"));
//                                afiliado.setDireccion(afiliadoEncontrado.getString("direccion"));
//                                //Lugar de Afiliación
//                                afiliado.setPrefijoMunicipioAfiliacion(afiliadoEncontrado.getString("codMunicipioAfiliacion"));
//                                afiliado.setPrefijoDepartamentoAfiliacion(afiliadoEncontrado.getString("codDepartamentoAfiliacion"));
                            }

                        }
                    }
                }
            } else {
                throw new Exception("El token a caducado por favor intente nuevamente");
            }
        } catch (JsonSyntaxException | IOException ex) {
            throw new Exception("Error al buscar el servicio web: ".concat(httpURL));
        }
        return afiliado;
    }
}
