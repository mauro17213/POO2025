/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Admin}
 */
public class CmSincronizacion extends Auditoria {

    private Integer id;
    private byte[] jsonEnvio;
    private Date fechaHoraEnvio;
    private byte[] jsonRespuesta;
    private int codigoRetorno;
    private int codigoRespuesta;
    private String mensajeRespuesta;
    private Date fechaHoraRespuesta;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private CmRadicado cmRadicado;
    private CmConciliacion cmConciliacion;
    private CmAuditoriaCierre cmAuditoriaCierre;
    private CmFactura cmFactura;
    private Integer paquetes;
    private Integer paquetesExitosos;
    private boolean poseePaquetes;
    private int estadoTransacion;
    private int numeroPaquetesProcesados;
    private int momento;
    private CmSincronizacionPaquete sincronizacionPaquete;
  
    public final static int ESTADO_TRANSACCION_INICIADA  = 0;
    public final static int ESTADO_TRANSACCION_TERMINADA = 1;
    public final static int ESTADO_TRANSACCION_FALLIDA   = 2;
    public final static int ESTADO_TRANSACCION_TERMINADA_CON_ERRORES  = 3;
    public final static int ESTADO_TRANSACCION_TERMINADA_EXITOSA      = 4;
    
    public static final int CODIGO_RESPUESTA_ENVIO_SAP = 100;
    public static final int CODIGO_RESPUESTA_ERROR_TOKEN = 101;
    
    public static final int MOMENTO_SIN_ENVIAR_SAP = 0 ;
    public static final int MOMENTO_PASO_AUDITORIA = 1 ;
    public static final int MOMENTO_CIERRE_AUDITORIA = 2 ;
    public static final int MOMENTO_RESPUESTA_GLOSA_CONCILIACION = 3 ;
    public static final int MOMENTO_DEVOLUCION_FACTURA = 4 ;
    

    public CmSincronizacion() {
    }

    public CmSincronizacion(Integer id) {
        this.id = id;
    }

    public CmSincronizacion(Integer id, byte[] jsonEnvio, Date fechaHoraEnvio, int codigoRetorno, int codigoRespuesta, String mensajeRespuesta) {
        this.id = id;
        this.jsonEnvio = jsonEnvio;
        this.fechaHoraEnvio = fechaHoraEnvio;
        this.codigoRetorno = codigoRetorno;
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getJsonEnvio() {
        return jsonEnvio;
    }

    public void setJsonEnvio(byte[] jsonEnvio) {
        this.jsonEnvio = jsonEnvio;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public byte[] getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(byte[] jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public int getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(int codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        return cmGlosaRespuesta;
    }

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }

    public CmRadicado getCmRadicado() {
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public CmConciliacion getCmConciliacion() {
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public CmAuditoriaCierre getCmAuditoriaCierre() {
        return cmAuditoriaCierre;
    }

    public void setCmAuditoriaCierre(CmAuditoriaCierre cmAuditoriaCierre) {
        this.cmAuditoriaCierre = cmAuditoriaCierre;
    }

    public CmSincronizacionPaquete getSincronizacionPaquete() {
        if(sincronizacionPaquete == null){
            sincronizacionPaquete = new CmSincronizacionPaquete();
        }
        return sincronizacionPaquete;
    }

    public void setSincronizacionPaquete(CmSincronizacionPaquete sincronizacionPaquete) {
        this.sincronizacionPaquete = sincronizacionPaquete;
    } 
    
    public String getJsonEnvioStr() {
        String cadena = "";
        if (jsonEnvio != null) {
            cadena =  new String(jsonEnvio);
        } 
       return cadena;
    }
     
    public String getJsonRespuestaStr() {
        String cadena = "";
        if (jsonRespuesta != null) {
            cadena =  new String(jsonRespuesta);
        }
        return cadena;
    }

    public int getEstadoTransacion() {
        return estadoTransacion;
    }

    public void setEstadoTransacion(int estadoTransacion) {
        this.estadoTransacion = estadoTransacion;
    }
    
    public String getEstadoTransacionStr() {
        String str;
        switch (estadoTransacion) {
            case ESTADO_TRANSACCION_INICIADA:
                str = "Iniciada";
                break;
            case ESTADO_TRANSACCION_TERMINADA:
                str = "Terminada";
                break;
            case ESTADO_TRANSACCION_FALLIDA:
                str = "Fallida";
                break;
            case ESTADO_TRANSACCION_TERMINADA_CON_ERRORES:
                str = "Terminada Con Errores";
                break;
            case ESTADO_TRANSACCION_TERMINADA_EXITOSA:
                str = "Terminada Exitosa";
                break;
            default:
                str = " -- ";
                break;
        }
        return str;
    }
    
    public String getCodigoRespuestaStr() {
        String str;
        switch (codigoRespuesta) {
            case CODIGO_RESPUESTA_ENVIO_SAP:
                str = "Enviado SAP";
                break;
            case CODIGO_RESPUESTA_ERROR_TOKEN:
                str = "Error Generar Token";
                break;
            default:
                str = " -- ";
                break;
        }
        return str;
    }
    
   public String getMomentoStr(){
      return CmSincronizacion.getMomentoStr(getMomento());
   }
    
    public static String getMomentoStr( int momento) {
        String str;
        switch (momento) {
            case MOMENTO_SIN_ENVIAR_SAP:
                str = "Sin enviar a Sap";
                break;
            case MOMENTO_PASO_AUDITORIA:
                str = "Paso Auditoría";
                break;
            case MOMENTO_CIERRE_AUDITORIA:
                str = "Cierre Auditoría";
                break;
                 case MOMENTO_RESPUESTA_GLOSA_CONCILIACION:
                str = "Respuesta Glosa / Conciliación";
                break;
                 case MOMENTO_DEVOLUCION_FACTURA:
                str = "Devolución Factura";
                break;
            default:
                str = " -- ";
                break;
        }
        return str;
    }

    public Integer getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Integer paquetes) {
        this.paquetes = paquetes;
    }

    public Integer getPaquetesExitosos() {
        return paquetesExitosos;
    }

    public void setPaquetesExitosos(Integer paquetesExitosos) {
        this.paquetesExitosos = paquetesExitosos;
    }

    public boolean isPoseePaquetes() {
        return poseePaquetes;
    }

    public void setPoseePaquetes(boolean poseePaquetes) {
        this.poseePaquetes = poseePaquetes;
    }

    public int getNumeroPaquetesProcesados() {
        return numeroPaquetesProcesados;
    }

    public void setNumeroPaquetesProcesados(int numeroPaquetesProcesados) {
        this.numeroPaquetesProcesados = numeroPaquetesProcesados;
    }

    public int getMomento() {
        return momento;
    }

    public void setMomento(int momento) {
        this.momento = momento;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }
    
    @Override
    public String toString() {
        return "CmSincronizacion{" + "id=" + id + ", fechaHoraEnvio=" + fechaHoraEnvio + ", codigoRetorno=" + codigoRetorno + ", codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + ", fechaHoraRespuesta=" + fechaHoraRespuesta + ", paquetes=" + paquetes + ", paquetesExitosos=" + paquetesExitosos + ", poseePaquetes=" + poseePaquetes + '}';
    }
      
}
