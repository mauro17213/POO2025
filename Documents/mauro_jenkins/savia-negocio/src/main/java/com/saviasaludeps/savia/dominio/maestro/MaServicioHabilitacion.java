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
public class MaServicioHabilitacion extends Auditoria {

    private Integer id;
    private int codigo;
    private String nombre;
    private boolean activo;
    //2022-06-02 jyperez nuevos campos
    private Integer maeGrupoId;
    private String maeGrupoCodigo;
    private String maeGrupoValor;
    // campos Contratacion
    private boolean nuevoRegistro;
    private boolean editado;
    //2022-03-18 jyperez campo usado para la carga masiva
    private boolean borrar;
    
    //2022-07-05 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;

    public MaServicioHabilitacion() {
        this.nuevoRegistro = false;
        this.editado = false;
        this.borrar = false;
    }

    public MaServicioHabilitacion(Integer id) {
        this.id = id;
        this.nuevoRegistro = false;
        this.editado = false;
        this.borrar = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getNombreCorto() {
        return nombre.length() > 90 ? nombre.substring(0, 89).concat("...") : nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    @Override
    public String toString() {
        return "MaServicioHabilitacion{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", activo=" + activo + ", maeGrupoId=" + maeGrupoId + ", maeGrupoCodigo=" + maeGrupoCodigo + ", maeGrupoValor=" + maeGrupoValor + '}';
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

    /**
     * @return the maeGrupoId
     */
    public Integer getMaeGrupoId() {
        return maeGrupoId;
    }

    /**
     * @param maeGrupoId the maeGrupoId to set
     */
    public void setMaeGrupoId(Integer maeGrupoId) {
        this.maeGrupoId = maeGrupoId;
    }

    /**
     * @return the maeGrupoCodigo
     */
    public String getMaeGrupoCodigo() {
        return maeGrupoCodigo;
    }

    /**
     * @param maeGrupoCodigo the maeGrupoCodigo to set
     */
    public void setMaeGrupoCodigo(String maeGrupoCodigo) {
        this.maeGrupoCodigo = maeGrupoCodigo;
    }

    /**
     * @return the maeGrupoValor
     */
    public String getMaeGrupoValor() {
        return maeGrupoValor;
    }

    /**
     * @param maeGrupoValor the maeGrupoValor to set
     */
    public void setMaeGrupoValor(String maeGrupoValor) {
        this.maeGrupoValor = maeGrupoValor;
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

}
