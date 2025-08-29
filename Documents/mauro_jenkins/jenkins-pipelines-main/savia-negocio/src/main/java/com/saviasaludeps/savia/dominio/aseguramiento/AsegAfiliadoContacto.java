/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jyperez
 */
public class AsegAfiliadoContacto extends Auditoria {

    private Integer id;
    private String numeroContacto;
    private int maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private String observacion;
    private boolean activo;
    private AsegAfiliado asegAfiliado;
    //campos adicionales
    private boolean nuevoRegistro;
    private boolean editado;

    public AsegAfiliadoContacto() {
    }

    public AsegAfiliadoContacto(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the numeroContacto
     */
    public String getNumeroContacto() {
        return numeroContacto;
    }

    /**
     * @param numeroContacto the numeroContacto to set
     */
    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
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
     * @return the asegAfiliado
     */
    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    /**
     * @param asegAfiliado the asegAfiliado to set
     */
    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    @Override
    public String toString() {
        return "AsegAfiliadoContacto{" + "id=" + id + ", numeroContacto=" + numeroContacto + ", maeTipoContactoId=" + maeTipoContactoId + ", maeTipoContactoCodigo=" + maeTipoContactoCodigo + ", maeTipoContactoValor=" + maeTipoContactoValor + ", observacion=" + observacion + ", activo=" + activo + '}';
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
