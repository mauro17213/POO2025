/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaInsumo extends Auditoria {
    
    private Integer id;
    private int maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private boolean automatico;
    private String codigoHabilitacion;
    private String codigo;
    private String descripcion;
    private String abreviatura;
    private boolean activo;
    //2020-12-23 jyperez nuevo campo
    private int cobertura;
    
    //20219-09-29 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private boolean estadoInicialTecnologia;
    
    //2024-08-06 jyperez nuevos campos
    private List<MaInsumoMipres> listaInsumoMipres;
    
    //parametrización campos
    public final static int COBERTURA_POS = 1;
    public final static int COBERTURA_NO_POS = 2;
    public final static int COBERTURA_CONDICIONAL = 3;
    
    
    public MaInsumo() {
    }

    public MaInsumo(Integer id) {
        this.id = id;
    }

    public MaInsumo(Integer id, int maeTipoId, String codigo, String descripcion, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoId = maeTipoId;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.activo = activo;
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
     * @return the maeTipoId
     */
    public int getMaeTipoId() {
        return maeTipoId;
    }

    /**
     * @param maeTipoId the maeTipoId to set
     */
    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    /**
     * @return the maeTipoCodigo
     */
    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    /**
     * @param maeTipoCodigo the maeTipoCodigo to set
     */
    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    /**
     * @return the maeTipoValor
     */
    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    /**
     * @param maeTipoValor the maeTipoValor to set
     */
    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    /**
     * @return the automatico
     */
    public boolean isAutomatico() {
        return automatico;
    }

    /**
     * @param automatico the automatico to set
     */
    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    /**
     * @return the codigoHabilitacion
     */
    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    /**
     * @param codigoHabilitacion the codigoHabilitacion to set
     */
    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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
     * @return the cobertura
     */
    public int getCobertura() {
        return cobertura;
    }

    /**
     * @param cobertura the cobertura to set
     */
    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
    }

    @Override
    public String toString() {
        return "MaInsumo{" + "id=" + id + ", maeTipoId=" + maeTipoId + ", maeTipoCodigo=" + maeTipoCodigo + ", maeTipoValor=" + maeTipoValor + ", automatico=" + automatico + ", codigoHabilitacion=" + codigoHabilitacion + ", codigo=" + codigo + ", descripcion=" + descripcion + ", abreviatura=" + abreviatura + ", activo=" + activo + ", cobertura=" + cobertura + '}';
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
     * @return the estadoInicialTecnologia
     */
    public boolean isEstadoInicialTecnologia() {
        return estadoInicialTecnologia;
    }

    /**
     * @param estadoInicialTecnologia the estadoInicialTecnologia to set
     */
    public void setEstadoInicialTecnologia(boolean estadoInicialTecnologia) {
        this.estadoInicialTecnologia = estadoInicialTecnologia;
    }

    /**
     * @return the listaInsumoMipres
     */
    public List<MaInsumoMipres> getListaInsumoMipres() {
        return listaInsumoMipres;
    }

    /**
     * @param listaInsumoMipres the listaInsumoMipres to set
     */
    public void setListaInsumoMipres(List<MaInsumoMipres> listaInsumoMipres) {
        this.listaInsumoMipres = listaInsumoMipres;
    }
    
}
