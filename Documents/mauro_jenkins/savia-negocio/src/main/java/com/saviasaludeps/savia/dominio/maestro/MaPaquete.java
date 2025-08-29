/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public class MaPaquete extends Auditoria {
    
    private Integer id;
    private String codigo;
    private String nombre;
    private boolean activo;
    private String incluye;
    private String excluye;
    private String observacion;
    private Integer maeAmbitoId;
    private String maeAmbitoCodigo;
    private String maeAmbitoValor;
    private String requisitosTecnicos;
    private Boolean esAltoCosto;
    //2020-12-22 nuevos campos
    private Integer tipoTecnologia;
    private MaInsumo maInsumo;
    private MaMedicamento maMedicamento;
    private MaTecnologia maTecnologia;
    //2022-04-19 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private boolean estadoInicialActivo;
    //2024-08-06 jyperez nuevos campos
    private List<MaPaqueteMipres> listaPaqueteMipres;
    
    public MaPaquete() {
    }

    public MaPaquete(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getExcluye() {
        return excluye;
    }

    public void setExcluye(String excluye) {
        this.excluye = excluye;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public String getRequisitosTecnicos() {
        return requisitosTecnicos;
    }

    public void setRequisitosTecnicos(String requisitosTecnicos) {
        this.requisitosTecnicos = requisitosTecnicos;
    }

    public Boolean isEsAltoCosto() {
        return getEsAltoCosto();
    }

    public void setEsAltoCosto(Boolean esAtoCosto) {
        this.esAltoCosto = esAtoCosto;
    }

    /**
     * @return the tipoTecnologia
     */
    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    /**
     * @param tipoTecnologia the tipoTecnologia to set
     */
    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
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
     * @return the maMedicamento
     */
    public MaMedicamento getMaMedicamento() {
        return maMedicamento;
    }

    /**
     * @param maMedicamento the maMedicamento to set
     */
    public void setMaMedicamento(MaMedicamento maMedicamento) {
        this.maMedicamento = maMedicamento;
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
        return "MaPaquete{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", activo=" + activo + ", incluye=" + incluye + ", excluye=" + excluye + ", observacion=" + observacion + ", maeAmbitoId=" + maeAmbitoId + ", maeAmbitoCodigo=" + maeAmbitoCodigo + ", maeAmbitoValor=" + maeAmbitoValor + ", requisitosTecnicos=" + requisitosTecnicos + ", esAltoCosto=" + esAltoCosto + ", tipoTecnologia=" + tipoTecnologia + ", maInsumo=" + maInsumo + ", maMedicamento=" + maMedicamento + ", maTecnologia=" + maTecnologia + '}';
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
     * @return the esAtoCosto
     */
    public Boolean getEsAltoCosto() {
        return esAltoCosto;
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

    /**
     * @return the estadoInicialActivo
     */
    public boolean isEstadoInicialActivo() {
        return estadoInicialActivo;
    }

    /**
     * @param estadoInicialActivo the estadoInicialActivo to set
     */
    public void setEstadoInicialActivo(boolean estadoInicialActivo) {
        this.estadoInicialActivo = estadoInicialActivo;
    }

    /**
     * @return the listaPaqueteMipres
     */
    public List<MaPaqueteMipres> getListaPaqueteMipres() {
        return listaPaqueteMipres;
    }

    /**
     * @param listaPaqueteMipres the listaPaqueteMipres to set
     */
    public void setListaPaqueteMipres(List<MaPaqueteMipres> listaPaqueteMipres) {
        this.listaPaqueteMipres = listaPaqueteMipres;
    }
}
