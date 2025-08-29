/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author AlexanderDiaz
 */
public class MaTecnologiaGrupo extends Auditoria {
    
    private Integer id;
    private int maeGrupoTecnologiaId;
    private String maeGrupoTecnologiaCodigo;
    private String maeGrupoTecnologiaValor;
    private MaTecnologia maTecnologia;
    //campos Maestro Tecnologia
    private boolean nuevoRegistro;
    private boolean editado;
    private boolean borrar;

    public MaTecnologiaGrupo() {
        this.nuevoRegistro = false;
    }

    public MaTecnologiaGrupo(Integer id) {
        this.id = id;
        this.nuevoRegistro = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeGrupoTecnologiaId() {
        return maeGrupoTecnologiaId;
    }

    public void setMaeGrupoTecnologiaId(int maeGrupoTecnologiaId) {
        this.maeGrupoTecnologiaId = maeGrupoTecnologiaId;
    }

    public String getMaeGrupoTecnologiaCodigo() {
        return maeGrupoTecnologiaCodigo;
    }

    public void setMaeGrupoTecnologiaCodigo(String maeGrupoTecnologiaCodigo) {
        this.maeGrupoTecnologiaCodigo = maeGrupoTecnologiaCodigo;
    }

    public String getMaeGrupoTecnologiaValor() {
        return maeGrupoTecnologiaValor;
    }

    public void setMaeGrupoTecnologiaValor(String maeGrupoTecnologiaValor) {
        this.maeGrupoTecnologiaValor = maeGrupoTecnologiaValor;
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

    @Override
    public String toString() {
        return "MaTecnologiaGrupo{" + "id=" + id + ", maeGrupoTecnologiaId=" + maeGrupoTecnologiaId + ", maeGrupoTecnologiaCodigo=" + maeGrupoTecnologiaCodigo + ", maeGrupoTecnologiaValor=" + maeGrupoTecnologiaValor + ", maTecnologia=" + maTecnologia + '}';
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
