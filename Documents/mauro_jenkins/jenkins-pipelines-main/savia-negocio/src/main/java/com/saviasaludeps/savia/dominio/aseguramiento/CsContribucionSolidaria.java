/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author jyperez
 */
public class CsContribucionSolidaria extends Auditoria {

    private Integer id;
    private int maeContribucionSolidariaId;
    private String maeContribucionSolidariaCodigo;
    private String maeContribucionSolidariaValor;
    private Integer agno;
    private BigDecimal valor;
    private BigDecimal porcentaje;
    

    public CsContribucionSolidaria() {
    }

    public CsContribucionSolidaria(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the agno
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     * @param agno the agno to set
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
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

    @Override
    public String toString() {
        return "CsContribucionSolidaria{" + "id=" + id + ", agno=" + agno + ", valor=" + valor + ", porcentaje=" + porcentaje + '}';
    }

    /**
     * @return the maeContribucionSolidariaId
     */
    public int getMaeContribucionSolidariaId() {
        return maeContribucionSolidariaId;
    }

    /**
     * @param maeContribucionSolidariaId the maeContribucionSolidariaId to set
     */
    public void setMaeContribucionSolidariaId(int maeContribucionSolidariaId) {
        this.maeContribucionSolidariaId = maeContribucionSolidariaId;
    }

    /**
     * @return the maeContribucionSolidariaCodigo
     */
    public String getMaeContribucionSolidariaCodigo() {
        return maeContribucionSolidariaCodigo;
    }

    /**
     * @param maeContribucionSolidariaCodigo the maeContribucionSolidariaCodigo to set
     */
    public void setMaeContribucionSolidariaCodigo(String maeContribucionSolidariaCodigo) {
        this.maeContribucionSolidariaCodigo = maeContribucionSolidariaCodigo;
    }

    /**
     * @return the maeContribucionSolidariaValor
     */
    public String getMaeContribucionSolidariaValor() {
        return maeContribucionSolidariaValor;
    }

    /**
     * @param maeContribucionSolidariaValor the maeContribucionSolidariaValor to set
     */
    public void setMaeContribucionSolidariaValor(String maeContribucionSolidariaValor) {
        this.maeContribucionSolidariaValor = maeContribucionSolidariaValor;
    }

}
