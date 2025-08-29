/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.configuracionSistema;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author iavenegas
 */
public class CsTopeCopago extends Auditoria {

    private Integer id;
    private int agno;
    private int maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private int maeTipoAfiliadoId;
    private String maeTipoAfiliadoCodigo;
    private String maeTipoAfiliadoValor;
    private Integer maeNivelSisbenId;
    private String maeNivelSisbenCodigo;
    private String maeNivelSisbenValor;
    private String categoriaIbc;
    private BigDecimal porcentaje;
    private int valorTopeEvento;
    private int valorTopeAgno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public int getMaeTipoAfiliadoId() {
        return maeTipoAfiliadoId;
    }

    public void setMaeTipoAfiliadoId(int maeTipoAfiliadoId) {
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
    }

    public String getMaeTipoAfiliadoCodigo() {
        return maeTipoAfiliadoCodigo;
    }

    public void setMaeTipoAfiliadoCodigo(String maeTipoAfiliadoCodigo) {
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
    }

    public String getMaeTipoAfiliadoValor() {
        return maeTipoAfiliadoValor;
    }

    public void setMaeTipoAfiliadoValor(String maeTipoAfiliadoValor) {
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
    }

    public Integer getMaeNivelSisbenId() {
        return maeNivelSisbenId;
    }

    public void setMaeNivelSisbenId(Integer maeNivelSisbenId) {
        this.maeNivelSisbenId = maeNivelSisbenId;
    }

    public String getMaeNivelSisbenCodigo() {
        return maeNivelSisbenCodigo;
    }

    public void setMaeNivelSisbenCodigo(String maeNivelSisbenCodigo) {
        this.maeNivelSisbenCodigo = maeNivelSisbenCodigo;
    }

    public String getMaeNivelSisbenValor() {
        return maeNivelSisbenValor;
    }

    public void setMaeNivelSisbenValor(String maeNivelSisbenValor) {
        this.maeNivelSisbenValor = maeNivelSisbenValor;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getValorTopeEvento() {
        return valorTopeEvento;
    }

    public void setValorTopeEvento(int valorTopeEvento) {
        this.valorTopeEvento = valorTopeEvento;
    }

    public int getValorTopeAgno() {
        return valorTopeAgno;
    }

    public void setValorTopeAgno(int valorTopeAgno) {
        this.valorTopeAgno = valorTopeAgno;
    }

    @Override
    public String toString() {
        return "CsTopeCopago{" + "id=" + id + ", agno=" + agno + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", maeTipoAfiliadoId=" + maeTipoAfiliadoId + ", maeTipoAfiliadoCodigo=" + maeTipoAfiliadoCodigo + ", maeTipoAfiliadoValor=" + maeTipoAfiliadoValor + ", maeNivelSisbenId=" + maeNivelSisbenId + ", maeNivelSisbenCodigo=" + maeNivelSisbenCodigo + ", maeNivelSisbenValor=" + maeNivelSisbenValor + ", categoriaIbc=" + categoriaIbc + ", porcentaje=" + porcentaje + ", valorTopeEvento=" + valorTopeEvento + ", valorTopeAgno=" + valorTopeAgno + '}';
    }
}
