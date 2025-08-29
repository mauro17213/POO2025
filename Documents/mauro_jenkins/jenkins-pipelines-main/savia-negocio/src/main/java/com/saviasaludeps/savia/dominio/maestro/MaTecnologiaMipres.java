/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;

/**
 *
 * @author Jose Perez
 */
public class MaTecnologiaMipres extends Auditoria {

    private Integer id;
    private String codigoMipres;
    private String descripcion;
    //objetos
    private MpCodigoInsumo mpCodigoInsumo;
    private MaTecnologia maTecnologia;
    

    public MaTecnologiaMipres() {
    }

    public MaTecnologiaMipres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the codigoMipres
     */
    public String getCodigoMipres() {
        return codigoMipres;
    }

    /**
     * @param codigoMipres the codigoMipres to set
     */
    public void setCodigoMipres(String codigoMipres) {
        this.codigoMipres = codigoMipres;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the maTecnologia
     */
    public MaTecnologia getMaTecnologia() {
        return maTecnologia;
    }

    /**
     * @param maTecnologia the maTecnologia to set
     */
    public void setMaTecnologia(MaTecnologia maTecnologia) {
        this.maTecnologia = maTecnologia;
    }

    /**
     * @return the mpCodigoInsumo
     */
    public MpCodigoInsumo getMpCodigoInsumo() {
        return mpCodigoInsumo;
    }

    /**
     * @param mpCodigoInsumo the mpCodigoInsumo to set
     */
    public void setMpCodigoInsumo(MpCodigoInsumo mpCodigoInsumo) {
        this.mpCodigoInsumo = mpCodigoInsumo;
    }

    @Override
    public String toString() {
        return "MaTecnologiaMipres{" + "id=" + id + ", codigoMipres=" + codigoMipres + ", descripcion=" + descripcion + ", mpCodigoInsumo=" + mpCodigoInsumo + ", maTecnologia=" + maTecnologia + '}';
    }
    
}
