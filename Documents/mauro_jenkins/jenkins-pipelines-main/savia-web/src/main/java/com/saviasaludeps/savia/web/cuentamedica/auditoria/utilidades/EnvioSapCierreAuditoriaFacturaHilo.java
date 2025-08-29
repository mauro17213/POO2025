/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaCierreRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFacturaPaquete;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaTokenIntersavia;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
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
public class EnvioSapCierreAuditoriaFacturaHilo extends Thread {
    
    String httpUrl;
    RespuestaTokenIntersavia token;
    String jsonEntrada;
    CmSincronizacion cmsincronizacion ;
    public static StringBuilder buildErrores;
    public final static int LONGITUD_MENSAJE_RESPUESTA = 512;
    public final static String CODIGO_CREACION_NOTAS_EXITOSA_M2 = "13";
    public final static String CODIGO_CREACION_FACTURA_EXITOSA_M2 = "2";
    public final static String CODIGO_YA_SE_REALIZO_M2 = "7";
    public final static String CODIGO_FACTURA_CREADA_SAP = "20";
    
    public final static int TIPO_PETICION_TRADICIONAL  = 1;
    public final static int TIPO_PETICION_PAQUETE      = 2;
    public final static int TIPO_PETICION_INTRASAVIA   = 3;
    
    private int tipoPeticionEnvio;

    public static StringBuilder getBuildErrores() {
        if(buildErrores == null){
          buildErrores = new StringBuilder();
        }
        return buildErrores;
    }

    public static void setBuildErrores(StringBuilder buildErrores) {
        EnvioSapCierreAuditoriaFacturaHilo.buildErrores = buildErrores;
    }

    public int getTipoPeticionEnvio() {
        return tipoPeticionEnvio;
    }

    public void setTipoPeticionEnvio(int tipoPeticionEnvio) {
        this.tipoPeticionEnvio = tipoPeticionEnvio;
    }
    
    public EnvioSapCierreAuditoriaFacturaHilo( RespuestaTokenIntersavia token, String jsonEntrada,  CmSincronizacion cmsincronizacion , int tipoEnvioPeticion) {
       this.httpUrl =  PropApl.getInstance().get(PropApl.CM_WS_NOTIFICACION_FACTURA_URL);
       this.token = token;
       this.jsonEntrada = jsonEntrada;
       this.cmsincronizacion = cmsincronizacion;
       this.tipoPeticionEnvio = tipoEnvioPeticion;
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
    
    private CmAuditoriaCierreRemoto getCmAuditoriaCierreRemoto() throws Exception {
        return (CmAuditoriaCierreRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaCierreServicio", CmAuditoriaCierreRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
        
    @Override
    public void run() {
        
        List<CmRadicado> listaRadicados = new ArrayList<>();
        setBuildErrores( new StringBuilder() );
        int estadoTransaccionPaquete = 0 ;
        String mensajeRespuesta = "";
        String codigoRespuesta = "";
        int codigo ;
        try {  
                       
            Response response = consumoPostGenerico(httpUrl, token, jsonEntrada);
            int facturasProcesdasExitosas = 0;
            if (response != null && response.getStatus() == 200) {
                 
               boolean ejecucionTotalExitosa = procesarPeticionRespuestaM2(response.getEntity().toString(), getTipoPeticionEnvio(), listaRadicados);                
                
                if (ejecucionTotalExitosa) {
                    facturasProcesdasExitosas = 1;
                    codigoRespuesta = CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_EXITOSO+"";
                    mensajeRespuesta = CmSincronizacionPaquete.getCodigoRespuestaStr(CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_EXITOSO);
                    estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA;
                } else {
                    codigoRespuesta = CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_CON_NOVEDADES+"";
                    mensajeRespuesta = CmSincronizacionPaquete.getCodigoRespuestaStr(CmSincronizacionPaquete.CODIGO_RESPUESTA_ENVIO_CON_NOVEDADES);
                    estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES;
                }
            }
             if(response != null){
                
                if( response.getStatus() != 200 ) {
                  estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_INESPERADA;
                }
            
                try{
                    codigo = Integer.parseInt(codigoRespuesta);
                }catch(NumberFormatException e){
                    codigo = 0;
                }
                cmsincronizacion.setJsonRespuesta(response.getEntity().toString().getBytes());
                cmsincronizacion.setCodigoRetorno(response.getStatus());
                cmsincronizacion.setEstadoTransacion(estadoTransaccionPaquete);
                cmsincronizacion.setPaquetesExitosos(facturasProcesdasExitosas);
                cmsincronizacion.setFechaHoraRespuesta(new Date());
                cmsincronizacion.setCodigoRespuesta(codigo);
                cmsincronizacion.setMensajeRespuesta(mensajeRespuesta);
                getCmSincronizacionRemoto().actualizar(cmsincronizacion);

                for (CmRadicado radicado : listaRadicados) {
                    List<CmSincronizacionEncabezado> encabezado = getCmSincronizacionEncabezadoRemoto().consultarDetalles(radicado.getId());
                    if (encabezado.isEmpty()) {
                        radicado.setEstado_radicado(Boolean.TRUE);
                        getCmRadicadoRemoto().actualizar(radicado);
                    }
                }
            } else {
                String mensajeError = getBuildErrores().length() > 0 ? getBuildErrores().toString() : "";
                registrarSincronizacionExcepcion(cmsincronizacion, CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES, mensajeError);
            }   

        } catch (Exception ex) {
          registrarSincronizacionExcepcion(cmsincronizacion, CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES, ex.toString());
          Logger.getLogger("Error al notificar factura momento 2 SAP : "+EnvioSapCierreAuditoriaFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private void registrarSincronizacionExcepcion(CmSincronizacion sincronizacion, int estadoTransaccion, String mensajeError) {
        try {
            mensajeError = mensajeError.length() > LONGITUD_MENSAJE_RESPUESTA ?
                           mensajeError.substring(0, LONGITUD_MENSAJE_RESPUESTA) : mensajeError;
            sincronizacion.setEstadoTransacion(estadoTransaccion);
            sincronizacion.setCodigoRespuesta(0);
            sincronizacion.setMensajeRespuesta(mensajeError);
            sincronizacion.setJsonRespuesta(mensajeError.getBytes());
            sincronizacion.setFechaHoraRespuesta(new Date());
            getCmSincronizacionRemoto().actualizar(sincronizacion);
        } catch (Exception ex) {
            Logger.getLogger("Error al registrarSincronizacionExcepcion  momento 2 Factura : " + EnvioSapCierreAuditoriaFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean cambiarEstadoFactura(int idFactura, int estadoFactura) {
        boolean actualizadoExitoso;
        try {
            int estadoAuditoria = CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA;
            if( CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA == estadoFactura ){
               estadoAuditoria = CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA;
            } 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idFactura);
            paramConsulta.setParametroConsulta2(estadoAuditoria);
            paramConsulta.setParametroConsulta3(estadoFactura);
            getCmFacturaRemoto().actualizarEstadoAuditoria(paramConsulta);
            actualizadoExitoso = true;
        } catch (Exception ex) {
            actualizadoExitoso = false;
        }
        
        return actualizadoExitoso;
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
                  Logger.getLogger("Error SAP : "+EnvioSapCierreAuditoriaFacturaHilo.class.getName()).log(Level.SEVERE, null, exception);
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
            Logger.getLogger(prefijoError + EnvioSapCierreAuditoriaFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JsonSyntaxException | JSONException ex) {
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.getMessage());
            Logger.getLogger(prefijoError + EnvioSapCierreAuditoriaFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.toString());
            Logger.getLogger(prefijoError + EnvioSapCierreAuditoriaFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
     
    private void guardarHistorialEstadoFactura( CmAuditoriaCierre cierre, int estadoFactura) {
        try {
            CmFacturaEstado facturaEstado = new CmFacturaEstado();
            facturaEstado.setCmFactura(new CmFactura(cierre.getCmFactura().getId()));
            facturaEstado.setEstadoFactura(estadoFactura);
            facturaEstado.setUsuarioCrea(cierre.getUsuarioCrea());
            facturaEstado.setTerminalCrea(cierre.getTerminalCrea());
            facturaEstado.setFechaHoraCrea(new Date());
            getCmFacturaEstadoRemoto().insertar(facturaEstado);
        } catch (Exception e) {
            Logger.getLogger("Error al guardarHistorialEstadoFactura envioSapCierre : " + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private int obtenerNuevoEstadoFactura(int idFactura) {
        int estadoFactura = CmFactura.ESTADO_FACTURA_GLOSADA;
        try {
            CmFactura factura = getCmFacturaRemoto().consultar(idFactura);
            if (factura != null
                    && (factura.getValorPendienteActual() == null
                    || factura.getValorPendienteActual() != null
                    && factura.getValorPendienteActual().compareTo(BigDecimal.ZERO) == 0)) {
                estadoFactura = CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA;
            }
        } catch (Exception e) {
            Logger.getLogger("Error al obtenerNuevoEstadoFactura envioSapCierre : " + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, e);
        }
        return estadoFactura;
    }
    
    private boolean procesarPeticionRespuestaM2(String respuesta, int tipoEnvio, List<CmRadicado> listaRadicados)  {

        tipoEnvio = tipoEnvio == 0 ? TIPO_PETICION_TRADICIONAL : tipoEnvio;
        boolean procesamietoCompleto = false;
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        int facturasProcesadas = 0;
        int facturasProcesdasExitosas = 0;
        NotificacionUtilidades notificacionUtilidades = new NotificacionUtilidades();
        
        try {
            switch (tipoEnvio) {
                case TIPO_PETICION_TRADICIONAL:
                    JsonArray entries = (JsonArray) new JsonParser().parse(respuesta);
                    if (entries != null && entries.size() > 0) {
                        facturasProcesadas = entries.size();
                        for (JsonElement entry : entries) {
                            RespuestaNotificacionFactura factura = gson.fromJson((JsonObject) entry, RespuestaNotificacionFactura.class);;

                            if (factura.getCodigoResultado().equals(CODIGO_CREACION_NOTAS_EXITOSA_M2)
                                    || factura.getCodigoResultado().equals(CODIGO_CREACION_FACTURA_EXITOSA_M2)
                                    || factura.getCodigoResultado().equals(CODIGO_YA_SE_REALIZO_M2)
                                    || factura.getCodigoResultado().equals(CODIGO_FACTURA_CREADA_SAP) ) {

                                CmSincronizacionEncabezado enca = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoPorNitConcecutivo(factura.getNITProveedor(), Integer.valueOf(factura.getConsecutivo()));
                                if (enca != null) {
                                    
                                    CmRadicado radicado = getCmRadicadoRemoto().consultar(enca.getCmRadicadosId().getId());
                                    listaRadicados.add(radicado);
                                    //cambiar estado de factura
                                    if (radicado.getCmAuditoriaCierre() != null
                                            && radicado.getCmAuditoriaCierre().getId() != null) {
                                        CmAuditoriaCierre cierre = getCmAuditoriaCierreRemoto().consultar(radicado.getCmAuditoriaCierre().getId());
                                        if (cierre.getCmFactura() != null && cierre.getCmFactura().getId() != null) {
                                            int nuevoEstadoFactura = obtenerNuevoEstadoFactura(cierre.getCmFactura().getId());
                                            if (cambiarEstadoFactura(cierre.getCmFactura().getId(), nuevoEstadoFactura)) {
                                                enca.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                                                getCmSincronizacionEncabezadoRemoto().actualizar(enca);
                                                guardarHistorialEstadoFactura(cierre, nuevoEstadoFactura);
                                                notificacionUtilidades.actualizarUsuarioPorMomento(enca,CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA);
                                            }

                                        }
                                    }
                                }
                                facturasProcesdasExitosas++;
                            }
                            notificacionUtilidades.actualizarHistoricoTransaccionFacturaAuditoria(factura);
                        }
                    }
                    procesamietoCompleto = (facturasProcesadas == facturasProcesdasExitosas);
                    break;
                case TIPO_PETICION_PAQUETE:
                    //NuevoParceoSap 
                    RespuestaNotificacionFacturaPaquete paquete = gson.fromJson(respuesta, RespuestaNotificacionFacturaPaquete.class);
                    if (paquete != null) {
                        facturasProcesadas = paquete.getFacturas() == null ? 0 : paquete.getFacturas().size();
                        for (RespuestaNotificacionFactura factura : paquete.getFacturas()) {
                            if (factura.getCodigoResultado().equals(CODIGO_CREACION_NOTAS_EXITOSA_M2)
                                    || factura.getCodigoResultado().equals(CODIGO_CREACION_FACTURA_EXITOSA_M2)
                                    || factura.getCodigoResultado().equals(CODIGO_YA_SE_REALIZO_M2)
                                    || factura.getCodigoResultado().equals(CODIGO_FACTURA_CREADA_SAP) ) {

                                CmSincronizacionEncabezado enca = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoPorNitConcecutivo(factura.getNITProveedor(), Integer.valueOf(factura.getConsecutivo()));
                                if (enca != null) {

                                    CmRadicado radicado = getCmRadicadoRemoto().consultar(enca.getCmRadicadosId().getId());
                                    listaRadicados.add(radicado);
                                    //cambiar estado de factura
                                    if (radicado.getCmAuditoriaCierre() != null
                                            && radicado.getCmAuditoriaCierre().getId() != null) {
                                        CmAuditoriaCierre cierre = getCmAuditoriaCierreRemoto().consultar(radicado.getCmAuditoriaCierre().getId());
                                        if (cierre.getCmFactura() != null && cierre.getCmFactura().getId() != null) {
                                            int nuevoEstadoFactura = obtenerNuevoEstadoFactura(cierre.getCmFactura().getId());
                                            if (cambiarEstadoFactura(cierre.getCmFactura().getId(), nuevoEstadoFactura)) {
                                                enca.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                                                getCmSincronizacionEncabezadoRemoto().actualizar(enca);
                                                guardarHistorialEstadoFactura(cierre, nuevoEstadoFactura);
                                                notificacionUtilidades.actualizarUsuarioPorMomento(enca,CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA);
                                            }

                                        }
                                    }
                                }
                                facturasProcesdasExitosas++;
                            }
                            notificacionUtilidades.actualizarHistoricoTransaccionFacturaAuditoria(factura);
                        }
                    }

                    procesamietoCompleto = (facturasProcesadas == facturasProcesdasExitosas);
                    break;
            }
        } catch ( Exception ex) {
            procesamietoCompleto = false;
        }
        return procesamietoCompleto;
    }
    

     
}
