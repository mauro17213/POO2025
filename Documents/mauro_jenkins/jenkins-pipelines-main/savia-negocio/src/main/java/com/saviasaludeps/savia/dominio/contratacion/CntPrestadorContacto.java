/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jyperez
 */
public class CntPrestadorContacto extends Auditoria {
    
    private Integer id;
    private int maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private int maeAreaContactoId;
    private String maeAreaContactoCodigo;
    private String maeAreaContactoValor;
    private String contacto;
    private boolean autorizaEnvio;
    private boolean activo;
    private String observacion;
    //2022-05-26 jyperez campos adicionales
    private boolean nuevoRegistro;
    private boolean editado;
    
    private CntPrestador cntPrestador;
    private CntPrestadorSede cntPrestadorSede;
    
    public CntPrestadorContacto() {
        nuevoRegistro = false;
        editado = false;
    }

    public CntPrestadorContacto(Integer id) {
        this.id = id;
        nuevoRegistro = false;
        editado = false;
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
     * @return the maeTipoContactoId
     */
    public int getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    /**
     * @param maeTipoContactoId the maeTipoContactoId to set
     */
    public void setMaeTipoContactoId(int maeTipoContactoId) {
        this.maeTipoContactoId = maeTipoContactoId;
    }

    /**
     * @return the maeTipoContactoCodigo
     */
    public String getMaeTipoContactoCodigo() {
        return maeTipoContactoCodigo;
    }

    /**
     * @param maeTipoContactoCodigo the maeTipoContactoCodigo to set
     */
    public void setMaeTipoContactoCodigo(String maeTipoContactoCodigo) {
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
    }

    /**
     * @return the maeTipoContactoValor
     */
    public String getMaeTipoContactoValor() {
        return maeTipoContactoValor;
    }

    /**
     * @param maeTipoContactoValor the maeTipoContactoValor to set
     */
    public void setMaeTipoContactoValor(String maeTipoContactoValor) {
        this.maeTipoContactoValor = maeTipoContactoValor;
    }

    /**
     * @return the contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * @return the autorizaEnvio
     */
    public boolean isAutorizaEnvio() {
        return autorizaEnvio;
    }

    /**
     * @param autorizaEnvio the autorizaEnvio to set
     */
    public void setAutorizaEnvio(boolean autorizaEnvio) {
        this.autorizaEnvio = autorizaEnvio;
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
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the cntPrestador
     */
    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    /**
     * @param cntPrestador the cntPrestador to set
     */
    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
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

    @Override
    public String toString() {
        return "CntPrestadorContacto{" + "id=" + id + ", maeTipoContactoId=" + maeTipoContactoId + ", maeTipoContactoCodigo=" + maeTipoContactoCodigo + ", maeTipoContactoValor=" + maeTipoContactoValor + ", contacto=" + contacto + ", autorizaEnvio=" + autorizaEnvio + ", activo=" + activo + ", observacion=" + observacion + ", cntPrestadorSede=" + cntPrestadorSede + '}';
    }

    /**
     * @return the maeAreaContactoId
     */
    public int getMaeAreaContactoId() {
        return maeAreaContactoId;
    }

    /**
     * @param maeAreaContactoId the maeAreaContactoId to set
     */
    public void setMaeAreaContactoId(int maeAreaContactoId) {
        this.maeAreaContactoId = maeAreaContactoId;
    }

    /**
     * @return the maeAreaContactoCodigo
     */
    public String getMaeAreaContactoCodigo() {
        return maeAreaContactoCodigo;
    }

    /**
     * @param maeAreaContactoCodigo the maeAreaContactoCodigo to set
     */
    public void setMaeAreaContactoCodigo(String maeAreaContactoCodigo) {
        this.maeAreaContactoCodigo = maeAreaContactoCodigo;
    }

    /**
     * @return the maeAreaContactoValor
     */
    public String getMaeAreaContactoValor() {
        return maeAreaContactoValor;
    }

    /**
     * @param maeAreaContactoValor the maeAreaContactoValor to set
     */
    public void setMaeAreaContactoValor(String maeAreaContactoValor) {
        this.maeAreaContactoValor = maeAreaContactoValor;
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
     * @return the editado
     */
    public boolean isEditado() {
        return editado;
    }

    /**
     * @param editado the editado to set
     */
    public void setEditado(boolean editado) {
        this.editado = editado;
    }

}
