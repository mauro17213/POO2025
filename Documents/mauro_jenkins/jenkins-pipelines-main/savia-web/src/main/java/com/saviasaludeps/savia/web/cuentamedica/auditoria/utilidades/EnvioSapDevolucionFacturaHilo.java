/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDevolucionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaDevolucionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaSolicitudDevolucionFactura;
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
public class EnvioSapDevolucionFacturaHilo extends Thread {
    
    String httpUrl;
    RespuestaTokenIntersavia token;
    String jsonEntrada;
    CmSincronizacion sincronizacion;
    public static StringBuilder buildErrores;
    public final static int LONGITUD_MENSAJE_RESPUESTA = 512;
    public CmSincronizacionPaquete sincronizacionPaqute;
    public final static String CODIGO_RESPUESTA_EXITO = "2";
    public final static String CODIGO_YA_SE_REALIZO_M4 = "3";
    public final static String CODIGO_FACTURA_CREADA_SAP = "20";
    
    public static StringBuilder getBuildErrores() {
        if(buildErrores == null){
          buildErrores = new StringBuilder();
        }
        return buildErrores;
    }

    public static void setBuildErrores(StringBuilder buildErrores) {
        EnvioSapDevolucionFacturaHilo.buildErrores = buildErrores;
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
    
    private CmAuditoriaDevolucionRemoto getCmAuditoriaDevolucionRemoto() throws Exception {
        return (CmAuditoriaDevolucionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDevolucionServicio", CmAuditoriaDevolucionRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
    
    public EnvioSapDevolucionFacturaHilo(RespuestaTokenIntersavia token, String jsonEntrada,  CmSincronizacion sincronizacion ) {
       this.httpUrl = PropApl.getInstance().get(PropApl.CM_WS_RUTA_SERVICIO_DEVOLUCION_FACTURA);
       this.token = token;
       this.jsonEntrada = jsonEntrada;
       this.sincronizacion = sincronizacion;
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
        int facturasProcesdasExitosas = 0;
        String mensajeRespuesta = "";
        String codigoRespuesta = "";
        int codigo ;
        NotificacionUtilidades notificacionUtilidad = new NotificacionUtilidades();
        
        try {                      
            Response response = consumoPostGenerico(httpUrl, token, jsonEntrada);
            if (response != null && response.getStatus() == 200) {
                RespuestaSolicitudDevolucionFactura respuesta = 
                   gson.fromJson(response.getEntity().toString(), RespuestaSolicitudDevolucionFactura.class);
                if (respuesta != null && respuesta.getFacturas() != null && !respuesta.getFacturas().isEmpty()) {
                   int facturasProcesadas = respuesta.getFacturas().size();
                    for (RespuestaDevolucionFactura factura : respuesta.getFacturas()) {  
                        codigoRespuesta = factura.getCodigoRespuesta();
                        mensajeRespuesta = factura.getDescripcionRespuesta();                      
                        if (factura.getCodigoRespuesta().equals(CODIGO_RESPUESTA_EXITO) || 
                            factura.getCodigoRespuesta().equals(CODIGO_YA_SE_REALIZO_M4) ||
                            factura.getCodigoRespuesta().equals(CODIGO_FACTURA_CREADA_SAP)    ) {
                            CmSincronizacionEncabezado enca = getCmSincronizacionEncabezadoRemoto().consultarEnacabezadoNitNumeroDocumento(factura.getNITProveedor(), factura.getNumeroDocumento());
                            if (enca != null) {
                                notificacionUtilidad.actualizarUsuarioPorMomento(enca,CmFactura.ESTADO_FACTURA_DEVUELTA);
                                enca.setEstado(CmSincronizacionEncabezado.ESTADO_FINALIZADO);
                                getCmSincronizacionEncabezadoRemoto().actualizar(enca);
                                CmRadicado radicado = getCmRadicadoRemoto().consultar(enca.getCmRadicadosId().getId());
                                listaRadicados.add(radicado);
                            }
                            facturasProcesdasExitosas++;
                        }
                        notificacionUtilidad.actualizarHistoricoTransaccionFacturaDevolucion(factura);
                    }
                    if( facturasProcesadas == facturasProcesdasExitosas ){
                       estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_EXITOSA;
                    }else{
                       estadoTransaccionPaquete = CmSincronizacionPaquete.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES;
                    } 
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
                sincronizacion.setJsonRespuesta(response.getEntity().toString().getBytes());
                sincronizacion.setCodigoRetorno(response.getStatus());
                sincronizacion.setCodigoRespuesta(codigo);
                sincronizacion.setMensajeRespuesta(mensajeRespuesta);
                sincronizacion.setEstadoTransacion(estadoTransaccionPaquete);
                sincronizacion.setPaquetesExitosos(facturasProcesdasExitosas);
                sincronizacion.setFechaHoraRespuesta(new Date());
                getCmSincronizacionRemoto().actualizar(sincronizacion);

                for (CmRadicado radicado : listaRadicados) {
                    List<CmSincronizacionEncabezado> encabezado = getCmSincronizacionEncabezadoRemoto().consultarDetalles(radicado.getId());
                    if (encabezado.isEmpty()) {
                        radicado.setEstado_radicado(Boolean.TRUE);
                        getCmRadicadoRemoto().actualizar(radicado);
                        //cambiar estado de factura
                        if( radicado.getCmAuditoriaDevolucion() != null && 
                            radicado.getCmAuditoriaDevolucion().getId() != null){
                            CmAuditoriaDevolucion devolucion = getCmAuditoriaDevolucionRemoto().consultar(radicado.getCmAuditoriaDevolucion().getId());
                            if(devolucion.getCmFactura() != null && devolucion.getCmFactura().getId() != null){                            
                                cambiarEstadoFacturaDevuelta(devolucion.getCmFactura().getId());
                                guardarHistorialEstadoFactura(devolucion, CmFactura.ESTADO_FACTURA_DEVUELTA);
                            }
                        }
                    }
                }
            } else {
                String mensajeError = getBuildErrores().length() > 0 ? getBuildErrores().toString() : "";
                registrarSincronizacionExcepcion(sincronizacion, CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES, mensajeError);
            }         
  
        } catch (Exception ex) {
          Logger.getLogger("Error al devolver factura a SAP : "+EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
          registrarSincronizacionExcepcion(sincronizacion, CmSincronizacion.ESTADO_TRANSACCION_TERMINADA_CON_ERRORES, ex.toString());
        }   
    }
    
    private void cambiarEstadoFacturaDevuelta(int idFactura) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idFactura);
            paramConsulta.setParametroConsulta2(CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA);
            paramConsulta.setParametroConsulta3(CmFactura.ESTADO_FACTURA_DEVUELTA);
            getCmFacturaRemoto().actualizarEstadoAuditoria(paramConsulta);
        } catch (Exception ex) {
            Logger.getLogger("Error al cambiarEstado Factura : " + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger("Error al registrarSincronizacionExcepcion Devolver Factura : " + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
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
                  Logger.getLogger("Error SAP : "+EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, exception);
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

                        response = Response.status(conn.getResponseCode()).entity(result+errorGeneral).build();
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
            Logger.getLogger(prefijoError + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JsonSyntaxException | JSONException ex) {
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.getMessage());
            Logger.getLogger(prefijoError + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            getBuildErrores().append(" ->");
            getBuildErrores().append(prefijoError);
            getBuildErrores().append(ex.toString());
            Logger.getLogger(prefijoError + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
     
    private void guardarHistorialEstadoFactura( CmAuditoriaDevolucion devolucion, int estadoFactura) {
        try {
            CmFacturaEstado facturaEstado = new CmFacturaEstado();
            facturaEstado.setCmFactura(new CmFactura(devolucion.getCmFactura().getId()));
            facturaEstado.setEstadoFactura(estadoFactura);
            facturaEstado.setUsuarioCrea(devolucion.getUsuarioCrea());
            facturaEstado.setTerminalCrea(devolucion.getTerminalCrea());
            facturaEstado.setFechaHoraCrea(new Date());
            getCmFacturaEstadoRemoto().insertar(facturaEstado);
        } catch (Exception e) {
            Logger.getLogger("Error al guardarHistorialEstadoFactura envioSapDevolucion : " + EnvioSapDevolucionFacturaHilo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

   
}
