/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jyperez
 */
public class MaTecnologiaServicioHabilitacion extends Auditoria {
    private Integer id;
    private boolean activo;
    private MaServicioHabilitacion maServicioHabilitacion;
    private MaTecnologia maTecnologia;
    
    public MaTecnologiaServicioHabilitacion() {
    }

    public MaTecnologiaServicioHabilitacion(Integer id) {
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
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the maServicioHabilitacion
     */
    public MaServicioHabilitacion getMaServicioHabilitacion() {
        return maServicioHabilitacion;
    }

    /**
     * @param maServicioHabilitacion the maServicioHabilitacion to set
     */
    public void setMaServicioHabilitacion(MaServicioHabilitacion maServicioHabilitacion) {
        this.maServicioHabilitacion = maServicioHabilitacion;
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
}
