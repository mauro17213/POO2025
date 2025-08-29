/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class CntContratoJuridico extends Auditoria {
    private Integer id;
    private int maeDocumentoJuridicoId;
    private String maeDocumentoJuridicoCodigo;
    private String maeDocumentoJuridicoValor;
    private int consecutivo;
    private BigDecimal valor;
    private Date fechaInicial;
    private Date fechaFinal;
    private int estado;
    private CntContrato cntContrato;
    
    public CntContratoJuridico() {
    }

    public CntContratoJuridico(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the maeDocumentoJuridicoId
     */
    public int getMaeDocumentoJuridicoId() {
        return maeDocumentoJuridicoId;
    }

    /**
     * @param maeDocumentoJuridicoId the maeDocumentoJuridicoId to set
     */
    public void setMaeDocumentoJuridicoId(int maeDocumentoJuridicoId) {
        this.maeDocumentoJuridicoId = maeDocumentoJuridicoId;
    }

    /**
     * @return the maeDocumentoJuridicoCodigo
     */
    public String getMaeDocumentoJuridicoCodigo() {
        return maeDocumentoJuridicoCodigo;
    }

    /**
     * @param maeDocumentoJuridicoCodigo the maeDocumentoJuridicoCodigo to set
     */
    public void setMaeDocumentoJuridicoCodigo(String maeDocumentoJuridicoCodigo) {
        this.maeDocumentoJuridicoCodigo = maeDocumentoJuridicoCodigo;
    }

    /**
     * @return the maeDocumentoJuridicoValor
     */
    public String getMaeDocumentoJuridicoValor() {
        return maeDocumentoJuridicoValor;
    }

    /**
     * @param maeDocumentoJuridicoValor the maeDocumentoJuridicoValor to set
     */
    public void setMaeDocumentoJuridicoValor(String maeDocumentoJuridicoValor) {
        this.maeDocumentoJuridicoValor = maeDocumentoJuridicoValor;
    }

    /**
     * @return the consecutivo
     */
    public int getConsecutivo() {
        return consecutivo;
    }

    /**
     * @param consecutivo the consecutivo to set
     */
    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the cntContrato
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContrato to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }
}
