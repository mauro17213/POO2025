/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.configuracionSistema;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author sgiraldo
 */
public class CsCopagoModeradoraHistorico extends Auditoria {

    public static final boolean MODERADORA = false;
    public static final boolean COPAGO = true;

    public static final String MODERADORA_STR = "Moderadora";
    public static final String COPAGO_STR = "Copago";

    private Integer id;
    private String idAfiliado;
    private int agno;
    private int maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private boolean moderadoraCopago;
    private String categoriaIbc;
    private int maeTipoAfiliadoId;
    private String maeTipoAfiliadoCodigo;
    private String maeTipoAfiliadoValor;
    private Integer maeNivelSisbenId;
    private String maeNivelSisbenCodigo;
    private String maeNivelSisbenValor;
    private Integer valorModeradora;
    private BigDecimal porcentajeCopago;
    private Integer valorProyectado;
    private Integer valorEjecutado;
    private AsegAfiliado asegAfiliadosId;
    private AuAnexo4 auAnexos4Id;
    private CmFactura cmFacturasId;

    public CsCopagoModeradoraHistorico() {
    }

    public CsCopagoModeradoraHistorico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
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

    public boolean isModeradoraCopago() {
        return moderadoraCopago;
    }

    public void setModeradoraCopago(boolean moderadoraCopago) {
        this.moderadoraCopago = moderadoraCopago;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
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

    public Integer getValorModeradora() {
        return valorModeradora;
    }

    public void setValorModeradora(Integer valorModeradora) {
        this.valorModeradora = valorModeradora;
    }

    public BigDecimal getPorcentajeCopago() {
        return porcentajeCopago;
    }

    public void setPorcentajeCopago(BigDecimal porcentajeCopago) {
        this.porcentajeCopago = porcentajeCopago;
    }

    public Integer getValorProyectado() {
        return valorProyectado;
    }

    public void setValorProyectado(Integer valorProyectado) {
        this.valorProyectado = valorProyectado;
    }

    public Integer getValorEjecutado() {
        return valorEjecutado;
    }

    public void setValorEjecutado(Integer valorEjecutado) {
        this.valorEjecutado = valorEjecutado;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexo4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexo4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public CmFactura getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFactura cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    //metodos 
    public String moderadoraCopagoStr() {
        if (moderadoraCopago) {
            return COPAGO_STR;
        } else {
            return MODERADORA_STR;
        }
    }

    @Override
    public String toString() {
        return "CsCopagoModeradoraHistorico{" + "id=" + id + ", idAfiliado=" + idAfiliado + ", agno=" + agno + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", moderadoraCopago=" + moderadoraCopago + ", categoriaIbc=" + categoriaIbc + ", maeTipoAfiliadoId=" + maeTipoAfiliadoId + ", maeTipoAfiliadoCodigo=" + maeTipoAfiliadoCodigo + ", maeTipoAfiliadoValor=" + maeTipoAfiliadoValor + ", maeNivelSisbenId=" + maeNivelSisbenId + ", maeNivelSisbenCodigo=" + maeNivelSisbenCodigo + ", maeNivelSisbenValor=" + maeNivelSisbenValor + ", valorModeradora=" + valorModeradora + ", porcentajeCopago=" + porcentajeCopago + ", valorProyectado=" + valorProyectado + ", valorEjecutado=" + valorEjecutado + ", asegAfiliadosId=" + asegAfiliadosId + ", auAnexos4Id=" + auAnexos4Id + ", cmFacturasId=" + cmFacturasId + '}';
    }

}
