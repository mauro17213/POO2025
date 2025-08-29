/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jyperez
 */
public class CntContratoCobertura extends Auditoria {
    
    private Integer id;
    private boolean activo;
    private CntContrato cntContrato;
    private CntContratoSede cntContratoSede;
    private CntPrestadorSede cntPrestadorSede;
    private Ubicacion ubicacion;
    //2022-07-14 jyperez nuevos campos manejo de coberturas
    private boolean nuevoRegistro;
    private boolean borrar;
    
    public CntContratoCobertura() {
    }

    public CntContratoCobertura(Integer id) {
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
     * @return the cntContrato
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContrato to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    /**
     * @return the cntPrestadorSede
     */
    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    /**
     * @param cntPrestadorSede the cntPrestadorSede to set
     */
    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    /**
     * @return the ubicacion
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "CntContratoCobertura{" + "id=" + id + ", activo=" + activo + ", cntContrato=" + cntContrato + ", cntPrestadorSede=" + cntPrestadorSede + ", ubicacion=" + ubicacion + '}';
    }

    /**
     * @return the cntContratoSede
     */
    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    /**
     * @param cntContratoSede the cntContratoSede to set
     */
    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }

    /**
     * @return the nuevoRegistro
     */
    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    /**
     * @param nuevoRegistro the nuevoRegistro to set
     */
    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }

    /**
     * @return the borrar
     */
    public boolean isBorrar() {
        return borrar;
    }

    /**
     * @param borrar the borrar to set
     */
    public void setBorrar(boolean borrar) {
        this.borrar = borrar;
    }

}
