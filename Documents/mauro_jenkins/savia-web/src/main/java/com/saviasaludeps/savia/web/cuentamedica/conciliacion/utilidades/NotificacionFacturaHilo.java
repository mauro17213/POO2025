/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionPaqueteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaTokenIntersavia;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.primefaces.shaded.json.JSONException;

/**
 *
 * @author admin
 */
public class NotificacionFacturaHilo extends Thread {
    
    String httpUrl;
    RespuestaTokenIntersavia token;
    String jsonEntrada;
    List<CmSincronizacion> listaSincronizacion = new ArrayList<>();
    public static StringBuilder buildErrores;
    public final static int LONGITUD_MENSAJE_RESPUESTA = 512;
    public CmSincronizacionPaquete sincronizacionPaqute;
    public final static String CODIGO_NOTAS_CREADAS_EXITOSAS = "13";

    public static StringBuilder getBuildErrores() {
        if(buildErrores == null){
          buildErrores = new StringBuilder();
        }
        return buildErrores;
    }

    public static void setBuildErrores(StringBuilder buildErrores) {
        NotificacionFacturaHilo.buildErrores = buildErrores;
    }
    public NotificacionFacturaHilo(CmSincronizacionPaquete sincroPaquete, RespuestaTokenIntersavia token, String jsonEntrada,  List<CmSincronizacion> listaSincronizacion ) {
       this.sincronizacionPaqute = sincroPaquete;
       this.httpUrl = PropApl.getInstance().get(PropApl.CM_WS_NOTIFICACION_FACTURA_URL);
       this.token = token;
       this.jsonEntrada = jsonEntrada;
       this.listaSincronizacion = listaSincronizacion;
    }
    
    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }

    private CmRadicadoRemoto getCmRadicadoRemoto() throws Exception {
        return (CmRadicadoRemoto) RemotoEJB.getEJBRemoto("CmRadicadoServicio", CmRadicadoRemoto.class.getName());
    }
    
    private CmSincronizacionRemoto getCmSincronizacionRemoto() throws Exception {
        return (CmSincronizacionRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionServicio", CmSincronizacionRemoto.class.getName());
    }
    
    private CmSincronizacionPaqueteRemoto getCmSincronizacionPaqueteRemoto() throws Exception {
        return (CmSincronizacionPaqueteRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionPaqueteServicio", CmSincronizacionPaqueteRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }
    
    private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
       
    @Override
    public void run() {
        
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        List<CmRadicado> listaRadicados = new ArrayList<>();
        setBuildErrores( new StringBuilder() );
        int estadoTransaccionPaquete = 0 ;
        int facturasProcesadas = 0;
        int facturasProcesdasExitosas = 0;
        String mensajeRespuesta = "";
        String codigoRespuesta = "";
        int codigo ;
        try {  
                       
            Response response = consumoPostGenerico(httpUrl, token, jsonEntrada);
            if (response != null && response.getStatus() == 200) {
                
               JsonArray entries = (JsonArray) new JsonParser().parse(response.getEntity().toString());
               if (entries != null && entries.size() > 0) {
                facturasProcesadas = entries.size();
                    for (JsonElement entry : entries) {
                       RespuestaNotificacionFactura factura = gson.fromJson((JsonObject) entry, RespuestaNotificacionFactura.class);
                       codigoRespuesta = factura.getCodigoResultado();
                       mensajeRespuesta = factura.getResultado();
                       if (factura.getCodigoResultado().equals(CODIGO_NOTAS_CREADAS_EXITOSAS)) {
                           int idFactura = Integer.parseInt(factura.getConsecutivo());
                           CmSincronizacionEncabezado enca = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoPorNitConcecutivo(factura.getNITProveedor(), Integer.valueOf(factura.getConsecutivo()));

                           if (enca != null) {
                               enca.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                               getCmSincronizacionEncabezadoRemoto().actualizar(enca);
                               CmRadicado radicado = getCmRadicadoRemoto().consultar(enca.getCmRadicadosId().getId());
                               listaRadicados.add(radicado);
                               int nuevoEstadoFactura = obtenerNuevoEstadoFactura(radicado);
                               cambiarEstadoFactura(nuevoEstadoFactura, idFactura);
                               guardarHistorialEstadoFactura(nuevoEstadoFactura, idFactura, enca);
                           }
                           facturasProcesdasExitosas++;
                       }
                   }
               }
               if( facturasProcesadas == facturasProcesdasExitosas ){
                   estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA;
                }else{
                   estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES;
                }
            }
               
               
            if (response != null) {
                
                if( response.getStatus() != 200 ) {
                  estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_INESPERADA;
                }
  
                if( sincronizacionPaqute != null ){
                    try {
                        codigo = Integer.parseInt(codigoRespuesta);
                    } catch (NumberFormatException e) {
                        codigo = 0;
                    }
                    sincronizacionPaqute.setCodigoRespuesta(codigo);
                    sincronizacionPaqute.setCodigoRetorno(response.getStatus());
                    sincronizacionPaqute.setEstadoTransacion(estadoTransaccionPaquete);
                    sincronizacionPaqute.setJsonRespuesta(response.getEntity().toString().getBytes());
                    sincronizacionPaqute.setFechaHoraRespuesta(new Date());
                    sincronizacionPaqute.setMensajeRespuesta(mensajeRespuesta);
                    getCmSincronizacionPaqueteRemoto().actualizar(sincronizacionPaqute);
                }
                
                for ( CmSincronizacion cmSincronizacion : listaSincronizacion ) {
                    listaSincronizacion.get(0).setNumeroPaquetesProcesados(listaSincronizacion.get(0).getNumeroPaquetesProcesados() + 1);
                    int paquetesProcesados = listaSincronizacion.get(0).getNumeroPaquetesProcesados();
                    
                    cmSincronizacion = getCmSincronizacionRemoto().consultar(cmSincronizacion.getId());
                    int cantidadPaquetesEjecutadoExitosos = cmSincronizacion.getPaquetesExitosos() ;
            
                    if( CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA == estadoTransaccionPaquete){
                      cantidadPaquetesEjecutadoExitosos = cmSincronizacion.getPaquetesExitosos() + 1;
                      cmSincronizacion.setPaquetesExitosos(cantidadPaquetesEjecutadoExitosos);
                    }
                    if ( paquetesProcesados == cmSincronizacion.getPaquetes() ) {
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA);
                        cmSincronizacion.setCodigoRespuesta(0);
                        cmSincronizacion.setFechaHoraRespuesta(new Date());
                    }
                    if ( cantidadPaquetesEjecutadoExitosos == cmSincronizacion.getPaquetes() ) {
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_EXITOSA);
                    }
                    
                    String nuevaRespuesta = " Paquete procesado de id " + sincronizacionPaqute.getId() 
                                           +  " con estado : "+ sincronizacionPaqute.getEstadoTransacionStr();
                    if (paquetesProcesados > 1) {
                        String respuestaAnterior = new String(cmSincronizacion.getJsonRespuesta());
                        nuevaRespuesta += " - " + respuestaAnterior;
                    }  
                    cmSincronizacion.setJsonRespuesta(nuevaRespuesta.getBytes());                
                    cmSincronizacion.setCodigoRetorno(response.getStatus());
                    getCmSincronizacionRemoto().actualizar(cmSincronizacion);
                }
            }

            for (CmRadicado radicado : listaRadicados) {
                List<CmSincronizacionEncabezado> encabezado = getCmSincronizacionEncabezadoRemoto().consultarDetalles(radicado.getId());
                if (encabezado.isEmpty()) {
                    radicado.setEstado_radicado(Boolean.TRUE);
                    getCmRadicadoRemoto().actualizar(radicado);
                }
            }
            
            if ( response == null ) {

                String mensajeError = getBuildErrores().length() > 0 ? getBuildErrores().toString() : "";
                              mensajeError = mensajeError.length() > LONGITUD_MENSAJE_RESPUESTA ? 
                              mensajeError.substring(0, LONGITUD_MENSAJE_RESPUESTA) : mensajeError;
                
                for ( CmSincronizacion cmSincronizacion : listaSincronizacion ) {
                    listaSincronizacion.get(0).setNumeroPaquetesProcesados(listaSincronizacion.get(0).getNumeroPaquetesProcesados() + 1);
                    int paquetesProcesados = listaSincronizacion.get(0).getNumeroPaquetesProcesados();
                    cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_FALLIDA);
                    if ( paquetesProcesados == cmSincronizacion.getPaquetes() ) {
                        cmSincronizacion.setEstadoTransacion(CmSincronizacion.ESTADO_TRANSACCION_TERMINADA);
                        cmSincronizacion.setCodigoRespuesta(0);
                    }
                    cmSincronizacion.setMensajeRespuesta(mensajeError);
                    cmSincronizacion.setFechaHoraRespuesta(new Date());
                    getCmSincronizacionRemoto().actualizar(cmSincronizacion);
                }

                if ( sincronizacionPaqute != null ) {
                    registrarSincronizacionExcepcion(sincronizacionPaqute, CmSincronizacionPaquete.ESTADO_TRANSACCION_FALLIDA, mensajeError);
                }
            }
            
        } catch (Exception ex) {
          Logger.getLogger("Error al notificar factura momento 3 a SAP : "+NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
          registrarSincronizacionExcepcion(sincronizacionPaqute, CmSincronizacionPaquete.ESTADO_TRANSACCION_FALLIDA, ex.toString());
        }   
    }
    
    
    private void cambiarEstadoFactura( int estadoFactura , int  idFactura) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            if (idFactura > 0) {
                paramConsulta.setParametroConsulta2(null);
                paramConsulta.setParametroConsulta1(idFactura);
                paramConsulta.setParametroConsulta3(estadoFactura);
                getCmFacturaRemoto().actualizarEstadoAuditoria(paramConsulta);
            }
        } catch (Exception ex) {
            Logger.getLogger("Error al cambiarEstado Factura  Momento3: " + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void guardarHistorialEstadoFactura(int estadoFactura  , int idFactura, CmSincronizacionEncabezado encabezado) {
        try {            
            CmFacturaEstado facturaEstado = new CmFacturaEstado();
            facturaEstado.setCmFactura(new CmFactura(idFactura));
            facturaEstado.setEstadoFactura(estadoFactura);
            facturaEstado.setUsuarioCrea(encabezado.getUsuarioCrea());
            facturaEstado.setTerminalCrea(encabezado.getTerminalCrea());
            facturaEstado.setFechaHoraCrea(new Date());
            getCmFacturaEstadoRemoto().insertar(facturaEstado);
        } catch (Exception e) {
            Logger.getLogger("Error al guardarHistorialEstadoFactura envioSapMomento3 : " + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private int obtenerNuevoEstadoFactura(CmRadicado radicado) {
        int estadoFactura =  CmFactura.ESTADO_FACTURA_GLOSADA;
        try {
            boolean esConciliacionMasiva = radicado.getCmConciliacion() != null
                    && radicado.getCmConciliacion().getId() != null;
            
            if (esConciliacionMasiva) {
                estadoFactura = CmFactura.ESTADO_FACTURA_CONCILIADA;
            }            
            if(!esConciliacionMasiva && radicado.getCmGlosaRespuesta() != null && 
               radicado.getCmGlosaRespuesta().getId() != null){
               CmGlosaRespuesta respuesta =  getCmGlosaRespuestaRemoto().consultar(radicado.getCmGlosaRespuesta().getId());
               estadoFactura = respuesta.getTipoRespuesta() == CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION ? 
                                                               CmFactura.ESTADO_FACTURA_CONCILIADA : estadoFactura;
            }        
        } catch (Exception e) {
            Logger.getLogger("Error al obtenerNuevoEstadoFactura envioSapMomento3 : " + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, e);
        }
        return estadoFactura;
    }
    
    private void registrarSincronizacionExcepcion(CmSincronizacionPaquete sincronizacion, int estadoTransaccion, String mensajeError) {
        try {
            mensajeError = mensajeError.length() > LONGITUD_MENSAJE_RESPUESTA ?
                           mensajeError.substring(0, LONGITUD_MENSAJE_RESPUESTA) : mensajeError;
            sincronizacion.setEstadoTransacion(estadoTransaccion);
            sincronizacion.setCodigoRespuesta(0);
            sincronizacion.setMensajeRespuesta(mensajeError);
            sincronizacion.setJsonRespuesta(mensajeError.getBytes());
            sincronizacion.setFechaHoraRespuesta(new Date());
            getCmSincronizacionPaqueteRemoto().actualizar(sincronizacion);
        } catch (Exception ex) {
            Logger.getLogger("Error al registrarSincronizacionExcepcion momento 3 : " + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private static Response consumoPostGenerico(String httpUrl, RespuestaTokenIntersavia token, String jsonEntrada) {
        String result = "";
        Response response = null;
        String prefijoError = "Error al consumir servicio SAP : ";
        String errorGeneral = "";
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
                errorGeneral = exception != null ? exception.toString() : "";
                if(conn.getErrorStream() == null){
                  getBuildErrores().append(" ->");
                  getBuildErrores().append(exception.toString());
                  Logger.getLogger("Error SAP : "+NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, exception);
                }else{
                  read = new InputStreamReader(conn.getErrorStream(), "UTF-8");
                }
            }
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                //Lectura por linea
                InputStream ins = conn.getErrorStream();
                if(ins != null){
                    InputStreamReader isr = new InputStreamReader(ins);
                    try (BufferedReader in = new BufferedReader(isr)) {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            result += inputLine;
                        }

                        response = Response.status(conn.getResponseCode()).entity(result + errorGeneral).build();
                    }
                }else{
                  result += getBuildErrores().toString();
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
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.getMessage());
            Logger.getLogger(prefijoError + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JsonSyntaxException | JSONException ex) {
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.getMessage());
            Logger.getLogger(prefijoError + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.toString());
            Logger.getLogger(prefijoError + NotificacionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

   
}
