/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author yjimenez
 */
public class SincronizacionNotificacionFactura extends Auditoria {

    private Integer radicadoId;
    private Integer glosaRespuestaId;
    private Integer conciliacionId;
    private Integer auditoriaCierreId;
    private Integer devolucionId;
    private Integer cmFacturaId;
    
    public static final String TIPO_TRANSACCION_M1_RADICACION_FACTURA = "1";
    public static final String TIPO_TRANSACCION_M2_FACTURA_AUDITADA = "2";
    public static final String TIPO_TRANSACCION_M3_RESPUESTA_GLOSA  = "3";
    public static final String TIPO_TRANSACCION_M4_CONCILIACION     = "4";
    
    private String tipoTransaccion;

    public Integer getRadicadoId() {
        return radicadoId;
    }

    public void setRadicadoId(Integer radicadoId) {
        this.radicadoId = radicadoId;
    }

    public Integer getGlosaRespuestaId() {
        return glosaRespuestaId;
    }

    public void setGlosaRespuestaId(Integer glosaRespuestaId) {
        this.glosaRespuestaId = glosaRespuestaId;
    }

    public Integer getConciliacionId() {
        return conciliacionId;
    }

    public void setConciliacionId(Integer conciliacionId) {
        this.conciliacionId = conciliacionId;
    }

    public Integer getAuditoriaCierreId() {
        return auditoriaCierreId;
    }

    public void setAuditoriaCierreId(Integer auditoriaCierreId) {
        this.auditoriaCierreId = auditoriaCierreId;
    }

    public Integer getDevolucionId() {
        return devolucionId;
    }

    public void setDevolucionId(Integer devolucionId) {
        this.devolucionId = devolucionId;
    }

    public Integer getCmFacturaId() {
        return cmFacturaId;
    }

    public void setCmFacturaId(Integer cmFacturaId) {
        this.cmFacturaId = cmFacturaId;
    }
    
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
    
    public static String getTipoTransaccionStr(String tipoTransaccion) {
         String str;
        switch (tipoTransaccion) {
            case TIPO_TRANSACCION_M1_RADICACION_FACTURA:
                str = "Radicacion Factura M1";
                break;
            case TIPO_TRANSACCION_M2_FACTURA_AUDITADA:
                str = "Factura Auditada M2";
                break;
            case TIPO_TRANSACCION_M3_RESPUESTA_GLOSA:
                str = "Respuesta Glosa M3";
                break;
            case TIPO_TRANSACCION_M4_CONCILIACION:
                str = "Conciliacion M4";
                break;  
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public String getTipoTransaccionStr() {
        return SincronizacionNotificacionFactura.getTipoTransaccionStr(getTipoTransaccion());
    }

}
