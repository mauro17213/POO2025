/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;


public class CntContratoHistoricoValidacion extends Auditoria {

    private Integer id;
    private Integer cntPrestadorId;
    private String cntPrestadorNumeroDocumento;
    private String cntPrestadorCodigoMinSalud;
    private Integer cntPrestadorSedeId;
    private String CntPrestadorSedeCodigoHabilitacion;
    private Integer cntContratoId;
    private Integer cntContratoSedeId;
    private Integer cntContratoDetalleId;
    private Integer tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private BigDecimal valor;
    private Date fechaInicio;
    private Date fechaFin;
    //2024-02-22 jyperez nuevos campos
    private Integer tipoManualTarifario;
    private String maManualTarifarioCodigo;
    private Integer maManualTarifarioAgno;
    private BigDecimal valorManual;
    private BigDecimal porcentajeVariacion;

    public CntContratoHistoricoValidacion() {
    }

    public CntContratoHistoricoValidacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCntContratoDetalleId() {
        return cntContratoDetalleId;
    }

    public void setCntContratoDetalleId(int cntContratoDetalleId) {
        this.cntContratoDetalleId = cntContratoDetalleId;
    }

    public int getCntContratoSedeId() {
        return cntContratoSedeId;
    }

    public void setCntContratoSedeId(int cntContratoSedeId) {
        this.cntContratoSedeId = cntContratoSedeId;
    }

    public int getCntContratoId() {
        return cntContratoId;
    }

    public void setCntContratoId(int cntContratoId) {
        this.cntContratoId = cntContratoId;
    }

    public int getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(int cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public int getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(int cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public String getCntPrestadorNumeroDocumento() {
        return cntPrestadorNumeroDocumento;
    }

    public void setCntPrestadorNumeroDocumento(String cntPrestadorNumeroDocumento) {
        this.cntPrestadorNumeroDocumento = cntPrestadorNumeroDocumento;
    }

    public String getCntPrestadorCodigoMinSalud() {
        return cntPrestadorCodigoMinSalud;
    }

    public void setCntPrestadorCodigoMinSalud(String cntPrestadorCodigoMinSalud) {
        this.cntPrestadorCodigoMinSalud = cntPrestadorCodigoMinSalud;
    }

    public String getCntPrestadorSedeCodigoHabilitacion() {
        return CntPrestadorSedeCodigoHabilitacion;
    }

    public void setCntPrestadorSedeCodigoHabilitacion(String CntPrestadorSedeCodigoHabilitacion) {
        this.CntPrestadorSedeCodigoHabilitacion = CntPrestadorSedeCodigoHabilitacion;
    }

    @Override
    public String toString() {
        return "CntContratoHistoricoValidacion{" + "id=" + id + ", cntPrestadorId=" + cntPrestadorId + ", cntPrestadorNumeroDocumento=" + cntPrestadorNumeroDocumento + ", cntPrestadorCodigoMinSalud=" + cntPrestadorCodigoMinSalud + ", cntPrestadorSedeId=" + cntPrestadorSedeId + ", CntPrestadorSedeCodigoHabilitacion=" + CntPrestadorSedeCodigoHabilitacion + ", cntContratoId=" + cntContratoId + ", cntContratoSedeId=" + cntContratoSedeId + ", cntContratoDetalleId=" + cntContratoDetalleId + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", valor=" + valor + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }

    /**
     * @return the tipoManualTarifario
     */
    public Integer getTipoManualTarifario() {
        return tipoManualTarifario;
    }

    /**
     * @param tipoManualTarifario the tipoManualTarifario to set
     */
    public void setTipoManualTarifario(Integer tipoManualTarifario) {
        this.tipoManualTarifario = tipoManualTarifario;
    }

    /**
     * @return the maManualTarifarioCodigo
     */
    public String getMaManualTarifarioCodigo() {
        return maManualTarifarioCodigo;
    }

    /**
     * @param maManualTarifarioCodigo the maManualTarifarioCodigo to set
     */
    public void setMaManualTarifarioCodigo(String maManualTarifarioCodigo) {
        this.maManualTarifarioCodigo = maManualTarifarioCodigo;
    }

    /**
     * @return the maManualTarifarioAgno
     */
    public Integer getMaManualTarifarioAgno() {
        return maManualTarifarioAgno;
    }

    /**
     * @param maManualTarifarioAgno the maManualTarifarioAgno to set
     */
    public void setMaManualTarifarioAgno(Integer maManualTarifarioAgno) {
        this.maManualTarifarioAgno = maManualTarifarioAgno;
    }

    /**
     * @return the valorManual
     */
    public BigDecimal getValorManual() {
        return valorManual;
    }

    /**
     * @param valorManual the valorManual to set
     */
    public void setValorManual(BigDecimal valorManual) {
        this.valorManual = valorManual;
    }

    /**
     * @return the porcentajeVariacion
     */
    public BigDecimal getPorcentajeVariacion() {
        return porcentajeVariacion;
    }

    /**
     * @param porcentajeVariacion the porcentajeVariacion to set
     */
    public void setPorcentajeVariacion(BigDecimal porcentajeVariacion) {
        this.porcentajeVariacion = porcentajeVariacion;
    }

}
