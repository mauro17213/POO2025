/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaRelacion extends Auditoria {
    
    private Integer id;
    private MaRelacionTipo maRelacionTiposId;
    private String gnMaestroTipo;
    private int gnId;
    private String gnCodigo;
    private String gnValor;
    private int tipoTecnologia;
    private int maId;
    private String maCodigo;
    private String maValor;
    private boolean activo;
   
    public MaRelacion() {
        
    }

    public MaRelacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MaRelacionTipo getMaRelacionTiposId() {
        return maRelacionTiposId;
    }

    public void setMaRelacionTiposId(MaRelacionTipo maRelacionTiposId) {
        this.maRelacionTiposId = maRelacionTiposId;
    }

    public String getGnMaestroTipo() {
        return gnMaestroTipo;
    }

    public void setGnMaestroTipo(String gnMaestroTipo) {
        this.gnMaestroTipo = gnMaestroTipo;
    }

    public int getGnId() {
        return gnId;
    }

    public void setGnId(int gnId) {
        this.gnId = gnId;
    }

    public String getGnCodigo() {
        return gnCodigo;
    }

    public void setGnCodigo(String gnCodigo) {
        this.gnCodigo = gnCodigo;
    }

    public String getGnValor() {
        return gnValor;
    }

    public void setGnValor(String gnValor) {
        this.gnValor = gnValor;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaId() {
        return maId;
    }

    public void setMaId(int maId) {
        this.maId = maId;
    }

    public String getMaCodigo() {
        return maCodigo;
    }

    public void setMaCodigo(String maCodigo) {
        this.maCodigo = maCodigo;
    }

    public String getMaValor() {
        return maValor;
    }

    public void setMaValor(String maValor) {
        this.maValor = maValor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "MaRelacion{" + "id=" + id + ", gnMaestroTipo=" + gnMaestroTipo + ", gnId=" + gnId + ", gnCodigo=" + gnCodigo + ", gnValor=" + gnValor + ", tipoTecnologia=" + tipoTecnologia + ", maId=" + maId + ", maCodigo=" + maCodigo + ", maValor=" + maValor + ", activo=" + activo + '}';
    }
}
