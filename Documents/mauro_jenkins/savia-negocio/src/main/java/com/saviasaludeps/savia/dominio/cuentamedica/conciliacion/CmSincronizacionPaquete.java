/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author admin
 */

public class CmSincronizacionPaquete  extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int estadoTransacion;
    private byte[] jsonEnvio;
    private Date fechaHoraEnvio;
    private byte[] jsonRespuesta;
    private Date fechaHoraRespuesta;
    private Integer codigoRetorno;
    private Integer codigoRespuesta;
    private String mensajeRespuesta;
    private CmSincronizacion cmSincronizacion;
    private int tipoTransaccion;
    
    public final static int ESTADO_TRANSACCION_INICIADA  = 0;
    public final static int ESTADO_TRANSACCION_TERMINADA_INESPERADA = 1;
    public final static int ESTADO_TRANSACCION_FALLIDA   = 2;
    public final static int ESTADO_TRANSACCION_TERMINADA_CON_ERRORES  = 3;
    public final static int ESTADO_TRANSACCION_TERMINADA_EXITOSA      = 4;
    
    public static final int CODIGO_RESPUESTA_ENVIO_SAP   = 100;
    public static final int CODIGO_RESPUESTA_ERROR_TOKEN = 101;
    public static final int CODIGO_RESPUESTA_ENVIO_M1_EXITOSO = 2;
    public static final int CODIGO_RESPUESTA_ENVIO_M2_EXITOSO = 2;
    public static final int CODIGO_RESPUESTA_ENVIO_M3_EXITOSO = 13;
    public static final int CODIGO_RESPUESTA_ENVIO_M4_EXITOSO = 2;
    public static final int CODIGO_RESPUESTA_ENVIO_EXITOSO = 2;
    public static final int CODIGO_RESPUESTA_ENVIO_CON_NOVEDADES = 102;

    public CmSincronizacionPaquete() {
    }

    public CmSincronizacionPaquete(Integer id) {
        this.id = id;
    }

    public CmSincronizacionPaquete(Integer id, int estadoTransacion, byte[] jsonEnvio, Date fechaHoraEnvio) {
        this.id = id;
        this.estadoTransacion = estadoTransacion;
        this.jsonEnvio = jsonEnvio;
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstadoTransacion() {
        return estadoTransacion;
    }

    public void setEstadoTransacion(int estadoTransacion) {
        this.estadoTransacion = estadoTransacion;
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

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public Integer getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(Integer codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public CmSincronizacion getCmSincronizacion() {
        return cmSincronizacion;
    }

    public void setCmSincronizacion(CmSincronizacion cmSincronizacion) {
        this.cmSincronizacion = cmSincronizacion;
    }

     public String getJsonEnvioStr() {
        if (jsonEnvio != null) {
            return new String(jsonEnvio);
        } else {
            return "";
        }
    }
     
    public String getJsonRespuestaStr() {
        if (jsonRespuesta != null) {
            return new String(jsonRespuesta);
        } else {
            return "";
        }
    }
    
    public String getEstadoTransacionStr() {
        String str;
        switch (estadoTransacion) {
            case ESTADO_TRANSACCION_INICIADA:
                str = "Iniciada";
                break;
            case ESTADO_TRANSACCION_TERMINADA_INESPERADA:
                str = "Terminada Inesperada";
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
        return CmSincronizacionPaquete.getCodigoRespuestaStr(getCodigoRespuesta());
    }
     
    public static String getCodigoRespuestaStr(int codigoRespuesta) {
        String str;
        switch (codigoRespuesta) {
            case CODIGO_RESPUESTA_ERROR_TOKEN:
                str = "Paquete con errore a generar token autenticacion.";
                break;
            case CODIGO_RESPUESTA_ENVIO_SAP:
                str = "Paquete enviado a sap.";
                break;
            case CODIGO_RESPUESTA_ENVIO_EXITOSO:
            case CODIGO_RESPUESTA_ENVIO_M3_EXITOSO:
                str = "Paquete procesado por sap exitosamente.";
                break;
            case CODIGO_RESPUESTA_ENVIO_CON_NOVEDADES:
                str = "Paquete con novedades.";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    

    @Override
    public String toString() {
        return "CmSincronizacionPaquete{" + "id=" + id + ", estadoTransacion=" + estadoTransacion + ", fechaHoraEnvio=" + fechaHoraEnvio + ", fechaHoraRespuesta=" + fechaHoraRespuesta + ", codigoRetorno=" + codigoRetorno + ", codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + '}';
    }
    
}
