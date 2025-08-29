/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class MpCotizacion extends Auditoria {

    private Integer id;
    private AuCotizacion auCotizacionId;
    private MpPrescripcionInsumo mpPrescripcionInsumoId;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentoId;
    private MpPrescripcionTecnologia mpPrescripcionTecnologiaId;

    private MpPrescripcion mpPrescripcioneId;
    private int tipoTecnologia;
    private String nombreTecnologia;
    private String numeroPrescripcion;
    private int estado;
    private Integer consecutivoOrden;

    private String usuarioRechaza;
    private String terminalRechaza;
    private Date fechaHoraRechaza;

    public MpCotizacion() {
    }

    public MpCotizacion(Integer id) {
        this.id = id;
    }

    public MpCotizacion(Integer id, AuCotizacion auCotizacionId, MpPrescripcionInsumo mpPrescripcionInsumoId, MpPrescripcionMedicamento mpPrescripcionMedicamentoId, MpPrescripcionTecnologia mpPrescripcionTecnologiaId, MpPrescripcion mpPrescripcioneId, int tipoTecnologia, String nombreTecnologia, String numeroPrescripcion, int estado, Integer consecutivoOrden, String usuarioRechaza, String terminalRechaza, Date fechaHoraRechaza) {
        this.id = id;
        this.auCotizacionId = auCotizacionId;
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
        this.mpPrescripcioneId = mpPrescripcioneId;
        this.tipoTecnologia = tipoTecnologia;
        this.nombreTecnologia = nombreTecnologia;
        this.numeroPrescripcion = numeroPrescripcion;
        this.estado = estado;
        this.consecutivoOrden = consecutivoOrden;
        this.usuarioRechaza = usuarioRechaza;
        this.terminalRechaza = terminalRechaza;
        this.fechaHoraRechaza = fechaHoraRechaza;
    }

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuCotizacion getAuCotizacionId() {
        return auCotizacionId;
    }

    public void setAuCotizacionId(AuCotizacion auCotizacionId) {
        this.auCotizacionId = auCotizacionId;
    }

    public MpPrescripcionInsumo getMpPrescripcionInsumoId() {
        return mpPrescripcionInsumoId;
    }

    public void setMpPrescripcionInsumoId(MpPrescripcionInsumo mpPrescripcionInsumoId) {
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentoId() {
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    public MpPrescripcionTecnologia getMpPrescripcionTecnologiaId() {
        return mpPrescripcionTecnologiaId;
    }

    public void setMpPrescripcionTecnologiaId(MpPrescripcionTecnologia mpPrescripcionTecnologiaId) {
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
    }

    public MpPrescripcion getMpPrescripcioneId() {
        return mpPrescripcioneId;
    }

    public void setMpPrescripcioneId(MpPrescripcion mpPrescripcioneId) {
        this.mpPrescripcioneId = mpPrescripcioneId;
    }

    
    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(Integer consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public String getUsuarioRechaza() {
        return usuarioRechaza;
    }

    public void setUsuarioRechaza(String usuarioRechaza) {
        this.usuarioRechaza = usuarioRechaza;
    }

    public String getTerminalRechaza() {
        return terminalRechaza;
    }

    public void setTerminalRechaza(String terminalRechaza) {
        this.terminalRechaza = terminalRechaza;
    }

    public Date getFechaHoraRechaza() {
        return fechaHoraRechaza;
    }

    public void setFechaHoraRechaza(Date fechaHoraRechaza) {
        this.fechaHoraRechaza = fechaHoraRechaza;
    }

    @Override
    public String toString() {
        return "MpCotizacion{" + "id=" + id + ", auCotizacionId=" + auCotizacionId + ", mpPrescripcionInsumoId=" + mpPrescripcionInsumoId + ", mpPrescripcionMedicamentoId=" + mpPrescripcionMedicamentoId + ", mpPrescripcionTecnologiaId=" + mpPrescripcionTecnologiaId + ", mpPrescripcioneId=" + mpPrescripcioneId + ", tipoTecnologia=" + tipoTecnologia + ", nombreTecnologia=" + nombreTecnologia + ", numeroPrescripcion=" + numeroPrescripcion + ", estado=" + estado + ", consecutivoOrden=" + consecutivoOrden + ", usuarioRechaza=" + usuarioRechaza + ", terminalRechaza=" + terminalRechaza + ", fechaHoraRechaza=" + fechaHoraRechaza + '}';
    }

   

}
