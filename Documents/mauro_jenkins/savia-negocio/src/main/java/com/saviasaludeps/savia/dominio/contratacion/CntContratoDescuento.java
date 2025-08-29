/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author jyperez
 */
public class CntContratoDescuento extends Auditoria {
    private Integer id;
    private int maeTipoDescuentoId;
    private String maeTipoDescuentoCodigo;
    private String maeTipoDescuentoValor;
    private BigDecimal porcentaje;
    private CntContrato cntContrato;
    
    public CntContratoDescuento() {
    }

    public CntContratoDescuento(Integer id) {
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
     * @return the maeTipoDescuentoId
     */
    public int getMaeTipoDescuentoId() {
        return maeTipoDescuentoId;
    }

    /**
     * @param maeTipoDescuentoId the maeTipoDescuentoId to set
     */
    public void setMaeTipoDescuentoId(int maeTipoDescuentoId) {
        this.maeTipoDescuentoId = maeTipoDescuentoId;
    }

    /**
     * @return the maeTipoDescuentoCodigo
     */
    public String getMaeTipoDescuentoCodigo() {
        return maeTipoDescuentoCodigo;
    }

    /**
     * @param maeTipoDescuentoCodigo the maeTipoDescuentoCodigo to set
     */
    public void setMaeTipoDescuentoCodigo(String maeTipoDescuentoCodigo) {
        this.maeTipoDescuentoCodigo = maeTipoDescuentoCodigo;
    }

    /**
     * @return the maeTipoDescuentoValor
     */
    public String getMaeTipoDescuentoValor() {
        return maeTipoDescuentoValor;
    }

    /**
     * @param maeTipoDescuentoValor the maeTipoDescuentoValor to set
     */
    public void setMaeTipoDescuentoValor(String maeTipoDescuentoValor) {
        this.maeTipoDescuentoValor = maeTipoDescuentoValor;
    }

    /**
     * @return the porcentaje
     */
    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
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
