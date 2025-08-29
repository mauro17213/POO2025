/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public class AuSeguimientoGestion extends Auditoria {

    public static final int TIPO_CAMBIO_ESTADO = 0;
    public static final int TIPO_GESTION = 1;

    public static final String ESTADO_PENDIENTE = "1";
    public static final String ESTADO_EN_GESTION = "2";
    public static final String ESTADO_ASIGNADO_PRESTADOR = "3";
    public static final String ESTADO_ACEPTADO_PRESTADOR = "4";
    public static final String ESTADO_RECHAZADO_PRESTADOR = "5";
    public static final String ESTADO_AUTORIZADO = "6";
    public static final String ESTADO_ENTREGADO = "7";
    public static final String ESTADO_GESTIONADO = "8";
    public static final String ESTADO_CANCELADO = "9";
    public static final String ESTADO_GESTION_NOTA = "10";
    public static final String ESTADO_REPROGRAMAR_SERVICIO = "11";
    
    public static final String MOTIVO_OTRO = "11";

    private Integer id;
    private String descripcion;
    private int tipo;
    private int maeEstadoSeguimientoGestionId;
    private String maeEstadoSeguimientoGestionCodigo;
    private String maeEstadoSeguimientoGestionValor;
    private int maeMotivoSeguimientoId;
    private String maeMotivoSeguimientoCodigo;
    private String maeMotivoSeguimientoValor;
    private Date fechaEntregaPropuesta;
    private Date fechaHoraEntregaReal;
    private Boolean borrado;
    private AuSeguimiento auSeguimiento;
    
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    
    private List<AuSolicitudAdjunto> auSolicitudAdjuntosList;

    public final static short TAMANIO_OBSERVACION = 30;

    public AuSeguimientoGestion() {
    }

    public AuSeguimientoGestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getMaeEstadoSeguimientoGestionId() {
        return maeEstadoSeguimientoGestionId;
    }

    public void setMaeEstadoSeguimientoGestionId(Integer maeEstadoSeguimientoGestionId) {
        this.maeEstadoSeguimientoGestionId = maeEstadoSeguimientoGestionId;
    }

    public String getMaeEstadoSeguimientoGestionCodigo() {
        return maeEstadoSeguimientoGestionCodigo;
    }

    public void setMaeEstadoSeguimientoGestionCodigo(String maeEstadoSeguimientoGestionCodigo) {
        this.maeEstadoSeguimientoGestionCodigo = maeEstadoSeguimientoGestionCodigo;
    }

    public String getMaeEstadoSeguimientoGestionValor() {
        return maeEstadoSeguimientoGestionValor;
    }

    public void setMaeEstadoSeguimientoGestionValor(String maeEstadoSeguimientoGestionValor) {
        this.maeEstadoSeguimientoGestionValor = maeEstadoSeguimientoGestionValor;
    }

    public Integer getMaeMotivoSeguimientoId() {
        return maeMotivoSeguimientoId;
    }

    public void setMaeMotivoSeguimientoId(Integer maeMotivoSeguimientoId) {
        this.maeMotivoSeguimientoId = maeMotivoSeguimientoId;
    }

    public String getMaeMotivoSeguimientoCodigo() {
        return maeMotivoSeguimientoCodigo;
    }

    public void setMaeMotivoSeguimientoCodigo(String maeMotivoSeguimientoCodigo) {
        this.maeMotivoSeguimientoCodigo = maeMotivoSeguimientoCodigo;
    }

    public String getMaeMotivoSeguimientoValor() {
        return maeMotivoSeguimientoValor;
    }

    public void setMaeMotivoSeguimientoValor(String maeMotivoSeguimientoValor) {
        this.maeMotivoSeguimientoValor = maeMotivoSeguimientoValor;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public AuSeguimiento getAuSeguimiento() {
        return auSeguimiento;
    }

    public void setAuSeguimiento(AuSeguimiento auSeguimiento) {
        this.auSeguimiento = auSeguimiento;
    }

    public Date getFechaEntregaPropuesta() {
        return fechaEntregaPropuesta;
    }

    public void setFechaEntregaPropuesta(Date fechaEntregaPropuesta) {
        this.fechaEntregaPropuesta = fechaEntregaPropuesta;
    }

    public Date getFechaHoraEntregaReal() {
        return fechaHoraEntregaReal;
    }

    public void setFechaHoraEntregaReal(Date fechaHoraEntregaReal) {
        this.fechaHoraEntregaReal = fechaHoraEntregaReal;
    }

    public String getDescripcionCorto() {
        String observacionCorto = "";
        if (getDescripcion() != null) {
            observacionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    public String getTipoStr() {
        switch (tipo) {
            case TIPO_CAMBIO_ESTADO:
                return "Cambio Estado";
            case TIPO_GESTION:
                return "Gestión";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "AuSeguimientoGestiones{" + "id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + ", maeEstadoSeguimientoGestionId=" + maeEstadoSeguimientoGestionId + ", maeEstadoSeguimientoGestionCodigo=" + maeEstadoSeguimientoGestionCodigo + ", maeEstadoSeguimientoGestionValor=" + maeEstadoSeguimientoGestionValor + ", maeMotivoSeguimientoId=" + maeMotivoSeguimientoId + ", maeMotivoSeguimientoCodigo=" + maeMotivoSeguimientoCodigo + ", maeMotivoSeguimientoValor=" + maeMotivoSeguimientoValor + ", borrado=" + borrado + ", auSeguimiento=" + auSeguimiento + '}';
    }

    /**
     * @return the usuarioBorra
     */
    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    /**
     * @param usuarioBorra the usuarioBorra to set
     */
    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    /**
     * @return the terminalBorra
     */
    public String getTerminalBorra() {
        return terminalBorra;
    }

    /**
     * @param terminalBorra the terminalBorra to set
     */
    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    /**
     * @return the fechaHoraBorra
     */
    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    /**
     * @param fechaHoraBorra the fechaHoraBorra to set
     */
    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    /**
     * @return the auSolicitudAdjuntosList
     */
    public List<AuSolicitudAdjunto> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    /**
     * @param auSolicitudAdjuntosList the auSolicitudAdjuntosList to set
     */
    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjunto> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

}
