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
public class MaPaqueteMipres extends Auditoria {

    private Integer id;
    private String codigoMipres;
    private String descripcionMipres;
    //objetos
    private MpCodigoInsumo mpCodigoInsumo;
    private MaPaquete maPaquete;
    

    public MaPaqueteMipres() {
    }

    public MaPaqueteMipres(Integer id) {
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
     * @return the descripcionMipres
     */
    public String getDescripcionMipres() {
        return descripcionMipres;
    }

    /**
     * @param descripcionMipres the descripcionMipres to set
     */
    public void setDescripcionMipres(String descripcionMipres) {
        this.descripcionMipres = descripcionMipres;
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
        return "MaTecnologiaMipres{" + "id=" + id + ", codigoMipres=" + codigoMipres + ", descripcionMipres=" + descripcionMipres + ", mpCodigoInsumo=" + mpCodigoInsumo + ", maPaquete=" + getMaPaquete() + '}';
    }

    /**
     * @return the maPaquete
     */
    public MaPaquete getMaPaquete() {
        return maPaquete;
    }

    /**
     * @param maPaquete the maPaquete to set
     */
    public void setMaPaquete(MaPaquete maPaquete) {
        this.maPaquete = maPaquete;
    }
    
}
