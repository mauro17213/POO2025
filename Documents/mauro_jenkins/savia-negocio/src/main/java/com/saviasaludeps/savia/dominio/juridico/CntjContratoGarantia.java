package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class CntjContratoGarantia extends Auditoria {
    
    private Integer id;
    private Integer maeGarantiaContratoId;
    private String maeGarantiaContratoCodigo;
    private String maeGarantiaContratoValor;
    private Date fechaExpedicion;
    private BigDecimal porcentajeValorContrato;
    private BigDecimal porcentajeValorAnticipo;
    private BigDecimal valorAsegurado;
    private Date vigenciaDesde;
    private Date vigenciaHasta;
    private Date fechaAprobacion;
    private Integer estado;    
    private CntjContrato cntjContratoId;
    private boolean requiereRenovacion;
    
    //Auxiliares
    private int idTemporal;
    private boolean bloquearPorcentaje;

    public CntjContratoGarantia() {
    }

    public CntjContratoGarantia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeGarantiaContratoId() {
        return maeGarantiaContratoId;
    }

    public void setMaeGarantiaContratoId(Integer maeGarantiaContratoId) {
        this.maeGarantiaContratoId = maeGarantiaContratoId;
    }

    public String getMaeGarantiaContratoCodigo() {
        return maeGarantiaContratoCodigo;
    }

    public void setMaeGarantiaContratoCodigo(String maeGarantiaContratoCodigo) {
        this.maeGarantiaContratoCodigo = maeGarantiaContratoCodigo;
    }

    public String getMaeGarantiaContratoValor() {
        return maeGarantiaContratoValor;
    }

    public void setMaeGarantiaContratoValor(String maeGarantiaContratoValor) {
        this.maeGarantiaContratoValor = maeGarantiaContratoValor;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public BigDecimal getPorcentajeValorContrato() {
        return porcentajeValorContrato;
    }

    public void setPorcentajeValorContrato(BigDecimal porcentajeValorContrato) {
        this.porcentajeValorContrato = porcentajeValorContrato;
    }

    public BigDecimal getPorcentajeValorAnticipo() {
        return porcentajeValorAnticipo;
    }

    public void setPorcentajeValorAnticipo(BigDecimal porcentajeValorAnticipo) {
        this.porcentajeValorAnticipo = porcentajeValorAnticipo;
    }

    public BigDecimal getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(BigDecimal valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }

    public Date getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(Date vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public Date getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(Date vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public CntjContrato getCntjContratoId() {
        return cntjContratoId;
    }

    public void setCntjContratoId(CntjContrato cntjContratoId) {
        this.cntjContratoId = cntjContratoId;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public boolean isRequiereRenovacion() {
        return requiereRenovacion;
    }

    public void setRequiereRenovacion(boolean requiereRenovacion) {
        this.requiereRenovacion = requiereRenovacion;
    }

    
    //Getter And Setter temporales

    public int getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(int idTemporal) {
        this.idTemporal = idTemporal;
    }

    public boolean isBloquearPorcentaje() {
        return bloquearPorcentaje;
    }

    public void setBloquearPorcentaje(boolean bloquearPorcentaje) {
        this.bloquearPorcentaje = bloquearPorcentaje;
    }

    @Override
    public String toString() {
        return "CntjContratoGarantia{" + "id=" + id + ", maeGarantiaContratoId=" + maeGarantiaContratoId + ", maeGarantiaContratoCodigo=" + maeGarantiaContratoCodigo + ", maeGarantiaContratoValor=" + maeGarantiaContratoValor + ", fechaExpedicion=" + fechaExpedicion + ", porcentajeValorContrato=" + porcentajeValorContrato + ", porcentajeValorAnticipo=" + porcentajeValorAnticipo + ", valorAsegurado=" + valorAsegurado + ", vigenciaDesde=" + vigenciaDesde + ", vigenciaHasta=" + vigenciaHasta + ", fechaAprobacion=" + fechaAprobacion + ", estado=" + estado + ", cntjContratoId=" + cntjContratoId + ", requiereAprobacion=" + requiereRenovacion + '}';
    }

    
}
