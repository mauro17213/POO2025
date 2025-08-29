/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Bsgomez
 */
public class MpCodigoInsumo extends Auditoria {

    private Integer id;
    private Integer maeInsumoId;
    private String codigoMipres;
    private String descripcion;
    private Boolean activo;
    private String versionMipres;
    //2024-08-02 jyperez campos control Lista en Insumos y Tecnologias
    private boolean nuevoRegistro;
    private boolean editado;

    public MpCodigoInsumo() {
    }

    public MpCodigoInsumo(Integer id) {
        this.id = id;
    }

    public MpCodigoInsumo(Integer id, Integer maeInsumoId, String codigoMipres, String descripcion, Boolean activo, String versionMipres) {
        this.id = id;
        this.maeInsumoId = maeInsumoId;
        this.codigoMipres = codigoMipres;
        this.descripcion = descripcion;
        this.activo = activo;
        this.versionMipres = versionMipres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeInsumoId() {
        return maeInsumoId;
    }

    public void setMaeInsumoId(Integer maeInsumoId) {
        this.maeInsumoId = maeInsumoId;
    }

    public String getCodigoMipres() {
        return codigoMipres;
    }

    public void setCodigoMipres(String codigoMipres) {
        this.codigoMipres = codigoMipres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getVersionMipres() {
        return versionMipres;
    }

    public void setVersionMipres(String versionMipres) {
        this.versionMipres = versionMipres;
    }

    @Override
    public String toString() {
        return "MpCodigoInsumo{" + "id=" + id + ", maeInsumoId=" + maeInsumoId + ", codigoMipres=" + codigoMipres + ", descripcion=" + descripcion + ", activo=" + activo + ", versionMipres=" + versionMipres + '}';
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
