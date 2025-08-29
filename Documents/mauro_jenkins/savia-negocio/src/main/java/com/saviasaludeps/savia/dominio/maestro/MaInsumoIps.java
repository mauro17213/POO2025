/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaInsumoIps extends Auditoria {
    
    private Integer id;
    private int cntPrestadorSedesId;
    private int maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private String codigo;
    private String descripcion;
    private String abreviatura;
    private boolean activo;
    
        public MaInsumoIps() {
    }

    public MaInsumoIps(Integer id) {
        this.id = id;
    }

    public MaInsumoIps(Integer id, int maeTipoId, String codigo, String descripcion, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoId = maeTipoId;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.activo = activo;
        usuarioCrea = usuarioCrea;
        terminalCrea = terminalCrea;
        fechaHoraCrea = fechaHoraCrea;
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
     * @return the cntPrestadorSedesId
     */
    public int getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    /**
     * @param cntPrestadorSedesId the cntPrestadorSedesId to set
     */
    public void setCntPrestadorSedesId(int cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

}
