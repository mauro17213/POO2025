
package com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WsCmTransaccion extends Auditoria {
    
    
    public static final short ESTADO_EN_CREACION = 1;
    public static final short ESTADO_CREADA  = 2;
    public static final short ESTADO_ENVIANDO = 3;
    public static final short ESTADO_FINALIZADA = 4;
    
    public static final int MOMENTO_SIN_ENVIAR_SAP        = 0;
    public static final int MOMENTO_PASO_AUDITORIA        = 1;
    public static final int MOMENTO_PASO_CIERRE_AUDITORIA = 2;
    public static final int MOMENTO_PASO_RESPUESTA_GLOSA  = 3;
    public static final int MOMENTO_PASO_CONCILIACION     = 4;
    public static final int MOMENTO_PASO_DEVOLUCION       = 5;
    public static final int TAMANIO_OBSERVACION = 75;
    
    private Integer id;
    private Integer cmFacturasId;
    private short estado;
    private short momento;
    private Date fechaHoraEnvio;
    private short codigoRetorno;
    private short codigoRespuesta;
    private String mensajeRespuesta;
    private Date fechaHoraRespuesta;
    private short paquetes;
    private short paquetesExitosos;
    private List<WsCmTransaccionDetalle> wsCmTransaccionDetalles;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private CmAuditoriaCierre cmAuditoriaCierre;
    private CmConciliacion cmConciliacion;
    private CmRadicado cmRadicado;
    private StringBuilder registroEstadoTransaccionDetalle = new StringBuilder();

    public WsCmTransaccion() {
        wsCmTransaccionDetalles = new ArrayList<>();
        cmGlosaRespuesta = new CmGlosaRespuesta();
        cmAuditoriaCierre = new CmAuditoriaCierre();
        cmConciliacion = new CmConciliacion();
        cmRadicado = new CmRadicado();
        registroEstadoTransaccionDetalle = new StringBuilder();
    }

    public WsCmTransaccion(Integer id) {
        this.id = id;
        wsCmTransaccionDetalles = new ArrayList<>();
        cmGlosaRespuesta = new CmGlosaRespuesta();
        cmAuditoriaCierre = new CmAuditoriaCierre();
        cmConciliacion = new CmConciliacion();
        cmRadicado = new CmRadicado();
        registroEstadoTransaccionDetalle = new StringBuilder();
    }

    public WsCmTransaccion(Integer id, short estado, short momento, Date fechaHoraEnvio, short paquetes, short paquetesExitosos) {
        this.id = id;
        this.estado = estado;
        this.momento = momento;
        this.fechaHoraEnvio = fechaHoraEnvio;
        this.paquetes = paquetes;
        this.paquetesExitosos = paquetesExitosos;
        wsCmTransaccionDetalles = new ArrayList<>();
        cmGlosaRespuesta = new CmGlosaRespuesta();
        cmAuditoriaCierre = new CmAuditoriaCierre();
        cmConciliacion = new CmConciliacion();
        cmRadicado = new CmRadicado();
        registroEstadoTransaccionDetalle = new StringBuilder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(Integer cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getMomento() {
        return momento;
    }

    public void setMomento(short momento) {
        this.momento = momento;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public short getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(short codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public short getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(short codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        if(mensajeRespuesta==null){
            mensajeRespuesta = "";
        }
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }
    
    public String getMensajeRespuestaCorto() {
        if (mensajeRespuesta != null && mensajeRespuesta.length() >= TAMANIO_OBSERVACION) {
            return mensajeRespuesta.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return mensajeRespuesta;
        } 
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public short getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(short paquetes) {
        this.paquetes = paquetes;
    }

    public short getPaquetesExitosos() {
        return paquetesExitosos;
    }

    public void setPaquetesExitosos(short paquetesExitosos) {
        this.paquetesExitosos = paquetesExitosos;
    }

    public List<WsCmTransaccionDetalle> getWsCmTransaccionDetalles() {
        return wsCmTransaccionDetalles;
    }

    public void setWsCmTransaccionDetalles(List<WsCmTransaccionDetalle> wsCmTransaccionDetalles) {
        this.wsCmTransaccionDetalles = wsCmTransaccionDetalles;
    }

    public CmAuditoriaCierre getCmAuditoriaCierre() {
        return cmAuditoriaCierre;
    }

    public void setCmAuditoriaCierre(CmAuditoriaCierre cmAuditoriaCierre) {
        this.cmAuditoriaCierre = cmAuditoriaCierre;
    }

    public CmConciliacion getCmConciliacion() {
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public CmRadicado getCmRadicado() {
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        return cmGlosaRespuesta;
    }

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }
    
    public boolean existeWsCmTransaccion() {
        return this.getId() != null && this.getId() > 0;
    }

    public StringBuilder getRegistroEstadoTransaccionDetalle() {
        return registroEstadoTransaccionDetalle;
    }

    public void setRegistroEstadoTransaccionDetalle(StringBuilder registroEstadoTransaccionDetalle) {
        this.registroEstadoTransaccionDetalle = registroEstadoTransaccionDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    
    public String getEstadoStr() {
        return WsCmTransaccion.getEstadoStr(getEstado());
    }
    
    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_EN_CREACION:
                str = "En Creación";
                break;
            case ESTADO_CREADA:
                str = "Creada";
                break;
            case ESTADO_ENVIANDO:
                str = "Enviando";
                break;
             case ESTADO_FINALIZADA:
                str = "Finalizada";
                break;    
            default:
                str = "";
                break;
        }
        return str;
    }
    
    
    public String getMomentoStr() {
        return WsCmTransaccion.getMomentoStr(getMomento());
    }
    
    public static String getMomentoStr(int momento) {
        String str;
        switch (momento) {
            case MOMENTO_SIN_ENVIAR_SAP:
                str = "Momento 0 sin enviar a SAP";
                break;
            case MOMENTO_PASO_AUDITORIA:
                str = "Momento 1 Paso auditoría ";
                break;
            case MOMENTO_PASO_CIERRE_AUDITORIA:
                str = "Momento 2 Cierre auditoría";
                break;
            case MOMENTO_PASO_RESPUESTA_GLOSA:
                str = "Momento 3 Respuesta glosa";
                break;
            case MOMENTO_PASO_CONCILIACION:
                str = "Momento 4 conciliación";
                break;
            case MOMENTO_PASO_DEVOLUCION:
                str = "Momento Devolución";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof WsCmTransaccion)) {
            return false;
        }
        WsCmTransaccion other = (WsCmTransaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return "WsCmTransaccion{" + "id=" + id + ", cmFacturasId=" + cmFacturasId + ", estado=" + estado + ", momento=" + momento + ", fechaHoraEnvio=" + fechaHoraEnvio + ", codigoRetorno=" + codigoRetorno + ", codigoRespuesta=" + codigoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + ", fechaHoraRespuesta=" + fechaHoraRespuesta + ", paquetes=" + paquetes + ", paquetesExitosos=" + paquetesExitosos + '}';
    }

    
}
