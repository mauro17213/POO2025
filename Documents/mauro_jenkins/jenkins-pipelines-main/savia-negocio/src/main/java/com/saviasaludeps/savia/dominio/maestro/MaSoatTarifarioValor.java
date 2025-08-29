/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author Jose Perez
 */
public class MaSoatTarifarioValor extends Auditoria {
    private Integer id;
    private int agno;
    private BigDecimal valor;
    private MaSoatTarifario maSoatTarifario;

    public MaSoatTarifarioValor() {
    }

    public MaSoatTarifarioValor(Integer id) {
        this.id = id;
    }

    public MaSoatTarifarioValor(Integer id, int agno, BigDecimal valor) {
        this.id = id;
        this.agno = agno;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAgno() {
        return agno;
    }
    
    public String getAgnoStr() {
        return String.valueOf(agno);
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarioValores[ id=" + id + " ]";
    }

    /**
     * @return the maSoatTarifario
     */
    public MaSoatTarifario getMaSoatTarifario() {
        return maSoatTarifario;
    }

    /**
     * @param maSoatTarifario the maSoatTarifario to set
     */
    public void setMaSoatTarifario(MaSoatTarifario maSoatTarifario) {
        this.maSoatTarifario = maSoatTarifario;
    }
}
