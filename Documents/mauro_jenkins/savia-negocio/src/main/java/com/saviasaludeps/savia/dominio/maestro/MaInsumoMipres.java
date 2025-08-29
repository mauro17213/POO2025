/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.mipres.MpCodigoInsumo;
import java.util.Date;

/**
 *
 * @author Jose Perez
 */
public class MaInsumoMipres extends Auditoria {
    
    private Integer id;
    private String codigoMipres;
    private String descripcionMipres;
    //objetos
    private MpCodigoInsumo mpCodigoInsumo;
    private MaInsumo maInsumo;
    
    
    public MaInsumoMipres() {
    }

    public MaInsumoMipres(Integer id) {
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
     * @return the maInsumo
     */
    public MaInsumo getMaInsumo() {
        return maInsumo;
    }

    /**
     * @param maInsumo the maInsumo to set
     */
    public void setMaInsumo(MaInsumo maInsumo) {
        this.maInsumo = maInsumo;
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
        return "MaInsumoMipres{" + "id=" + id + ", codigoMipres=" + codigoMipres + ", descripcionMipres=" + descripcionMipres + ", mpCodigoInsumo=" + mpCodigoInsumo + ", maInsumo=" + maInsumo + '}';
    }

}
