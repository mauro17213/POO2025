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
public class AuAnexo4Destino extends Auditoria {
    
    private Integer id;
    private int ubicacionId;
    private String ubicacionValor;
    private int orden;
    private boolean activo;
    private AuAnexo4Zona auAnexo3ZonaId;

    public AuAnexo4Destino() {
    }

    public AuAnexo4Destino(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getUbicacionValor() {
        return ubicacionValor;
    }

    public void setUbicacionValor(String ubicacionValor) {
        this.ubicacionValor = ubicacionValor;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public AuAnexo4Zona getAuAnexo3ZonaId() {
        return auAnexo3ZonaId;
    }

    public void setAuAnexo3ZonaId(AuAnexo4Zona auAnexo3ZonaId) {
        this.auAnexo3ZonaId = auAnexo3ZonaId;
    }

    @Override
    public String toString() {
        return "AuAnexo4Destino{" + "id=" + id + ", ubicacionId=" + ubicacionId + ", ubicacionValor=" + ubicacionValor + ", orden=" + orden + ", activo=" + activo + '}';
    }        
}
