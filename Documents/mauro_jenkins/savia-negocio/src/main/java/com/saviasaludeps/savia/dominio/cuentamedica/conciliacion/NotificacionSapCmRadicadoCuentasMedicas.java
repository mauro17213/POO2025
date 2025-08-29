/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jperezn
 */
public class NotificacionSapCmRadicadoCuentasMedicas extends Auditoria {
    
    public static final int TIPO_NOTIFICACION_CIERRE_AUDITORIA                 = 1;
    public static final int TIPO_NOTIFICACION_DEVOLUCION_AUDITORIA             = 2;
    public static final int TIPO_NOTIFICACION_FACTURA_M1                       = 3;
    public static final int TIPO_NOTIFICACION_CIERRE_FACTURA_INDIVIDUAL        = 4;
    public static final int TIPO_NOTIFICACION_DEVOLUCION_FACTURA_INDIVIDUAL    = 5;
    public static final int TIPO_NOTIFICACION_CONCILIACION_O_RESPUESTA_GLOSA   = 6;
    public static final int TIPO_NOTIFICACION_FACTURA_M1_MASIVO                = 7;
    
    public static final String TIPO_TRANSACCION_M1_RADICACION_FACTURA = "1";
    public static final String TIPO_TRANSACCION_M2_FACTURA_AUDITADA = "2";
    public static final String TIPO_TRANSACCION_M3_RESPUESTA_GLOSA  = "3";
    public static final String TIPO_TRANSACCION_M4_CONCILIACION     = "4";

    private Integer radicadoId;
    private Integer glosaRespuestaId;
    private Integer conciliacionId;
    private Integer auditoriaCierreId;
    private Integer devolucionId;
    private Integer cmFacturaId;
    private Integer auditoriaCierreMasivaId;
    private Integer devolucionMasivaId;
    private Integer tipoNotificacion;
    private Integer cmRipsCargaId;
    
    private String tipoTransaccion;

    public NotificacionSapCmRadicadoCuentasMedicas() {
    }

    
    public NotificacionSapCmRadicadoCuentasMedicas(Integer radicadoId) {
        this.radicadoId = radicadoId;
    }

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

    public Integer getAuditoriaCierreMasivaId() {
        return auditoriaCierreMasivaId;
    }

    public void setAuditoriaCierreMasivaId(Integer auditoriaCierreMasivaId) {
        this.auditoriaCierreMasivaId = auditoriaCierreMasivaId;
    }

    public Integer getDevolucionMasivaId() {
        return devolucionMasivaId;
    }

    public void setDevolucionMasivaId(Integer devolucionMasivaId) {
        this.devolucionMasivaId = devolucionMasivaId;
    }

    public Integer getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(Integer tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
    
    
    public String getTipoNotificacionStr() {
        return NotificacionSapCmRadicadoCuentasMedicas.getTipoNotificacionStr(getTipoNotificacion());
    }

    public Integer getCmRipsCargaId() {
        return cmRipsCargaId;
    }

    public void setCmRipsCargaId(Integer cmRipsCargaId) {
        this.cmRipsCargaId = cmRipsCargaId;
    }
     
    public static String getTipoNotificacionStr(int estadoFactura) {
        String str;
        switch (estadoFactura) {
            case TIPO_NOTIFICACION_CIERRE_AUDITORIA:
                str = "Notificacion Cierre Auditoria Masiva ";
                break;
            case TIPO_NOTIFICACION_DEVOLUCION_AUDITORIA:
                str = "Notificacion Devolucion Auditoria Masiva ";
                break;
            case TIPO_NOTIFICACION_FACTURA_M1:
            case TIPO_NOTIFICACION_FACTURA_M1_MASIVO:    
                str = "Notificacion Factura M1";
                break;
             case TIPO_NOTIFICACION_CIERRE_FACTURA_INDIVIDUAL:
                str = "Notificacion Cierre Factura Individual ";
                break;
             case TIPO_NOTIFICACION_DEVOLUCION_FACTURA_INDIVIDUAL:
                str = "Notificacion Devolucion factura Individual ";
                break;
             case TIPO_NOTIFICACION_CONCILIACION_O_RESPUESTA_GLOSA:
                str = "Notificacion conciliacion o respuesta glosa ";
                break;
            default:
                str = "";
                break;
        }
        return str;
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
        return NotificacionSapCmRadicadoCuentasMedicas.getTipoTransaccionStr(getTipoTransaccion());
    }

}
