/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Stiven Giraldo
 */
public class AuGrupoRegion extends Auditoria {
    
    private AuGrupo auGrupo;
    private Integer id;
    private int maeRegionId;
    private String maeRegionCodigo;
    private String maeRegionValor;

    public AuGrupoRegion() {
    }

    public AuGrupoRegion(Integer id) {
        this.id = id;
    }

    public AuGrupo getAuGrupo() {
        return auGrupo;
    }

    public void setAuGrupo(AuGrupo auGrupo) {
        this.auGrupo = auGrupo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeRegionId() {
        return maeRegionId;
    }

    public void setMaeRegionId(int maeRegionId) {
        this.maeRegionId = maeRegionId;
    }

    public String getMaeRegionCodigo() {
        return maeRegionCodigo;
    }

    public void setMaeRegionCodigo(String maeRegionCodigo) {
        this.maeRegionCodigo = maeRegionCodigo;
    }

    public String getMaeRegionValor() {
        return maeRegionValor;
    }

    public void setMaeRegionValor(String maeRegionValor) {
        this.maeRegionValor = maeRegionValor;
    }

    @Override
    public String toString() {
        return "AuGrupoRegion{" + "auGrupo=" + auGrupo + ", id=" + id + ", maeRegionId=" + maeRegionId + ", maeRegionCodigo=" + maeRegionCodigo + ", maeRegionValor=" + maeRegionValor + '}';
    }
    
}
