/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import java.io.Serializable;

/**
 *
 * @author jyperez
 */
public class CertificadoAfiliacion implements Serializable{
    
    private int id;
    private Integer idAfiliado;
    private String ruta;
    private String nombreArchivo;
    // variables para carga de adjunto
    //private transient InputStream adjuntoStream;
    private String strNombreCompleto;
    private String strDocumentoCompleto;
    private String strNivelSisben;
    private String strFechaAfiliacion;
    private String strCiudad;
    private String strRegimen;
    private String strEstado;
    private String strModelo;
    private String strFechaRetiro;
    private String strSemanas;
    private String strFechaGeneracion;
    private String strGrupoSisben;
    private String strId;
    //2025-05-13 jyperez nuevo campo
    private String strCategoriaIBC;

    public CertificadoAfiliacion() {
        
    }

    /**
     * @return the idAfiliado
     */
    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    /**
     * @param idAfiliado the idAfiliado to set
     */
    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    /**
     * @return the strNombreCompleto
     */
    public String getStrNombreCompleto() {
        return strNombreCompleto;
    }

    /**
     * @param strNombreCompleto the strNombreCompleto to set
     */
    public void setStrNombreCompleto(String strNombreCompleto) {
        this.strNombreCompleto = strNombreCompleto;
    }

    /**
     * @return the strDocumentoCompleto
     */
    public String getStrDocumentoCompleto() {
        return strDocumentoCompleto;
    }

    /**
     * @param strDocumentoCompleto the strDocumentoCompleto to set
     */
    public void setStrDocumentoCompleto(String strDocumentoCompleto) {
        this.strDocumentoCompleto = strDocumentoCompleto;
    }

    /**
     * @return the strNivelSisben
     */
    public String getStrNivelSisben() {
        return strNivelSisben;
    }

    /**
     * @param strNivelSisben the strNivelSisben to set
     */
    public void setStrNivelSisben(String strNivelSisben) {
        this.strNivelSisben = strNivelSisben;
    }

    /**
     * @return the strFechaAfiliacion
     */
    public String getStrFechaAfiliacion() {
        return strFechaAfiliacion;
    }

    /**
     * @param strFechaAfiliacion the strFechaAfiliacion to set
     */
    public void setStrFechaAfiliacion(String strFechaAfiliacion) {
        this.strFechaAfiliacion = strFechaAfiliacion;
    }

    /**
     * @return the strCiudad
     */
    public String getStrCiudad() {
        return strCiudad;
    }

    /**
     * @param strCiudad the strCiudad to set
     */
    public void setStrCiudad(String strCiudad) {
        this.strCiudad = strCiudad;
    }

    /**
     * @return the strRegimen
     */
    public String getStrRegimen() {
        return strRegimen;
    }

    /**
     * @param strRegimen the strRegimen to set
     */
    public void setStrRegimen(String strRegimen) {
        this.strRegimen = strRegimen;
    }

    /**
     * @return the strEstado
     */
    public String getStrEstado() {
        return strEstado;
    }

    /**
     * @param strEstado the strEstado to set
     */
    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

    /**
     * @return the strModelo
     */
    public String getStrModelo() {
        return strModelo;
    }

    /**
     * @param strModelo the strModelo to set
     */
    public void setStrModelo(String strModelo) {
        this.strModelo = strModelo;
    }

    /**
     * @return the strFechaRetiro
     */
    public String getStrFechaRetiro() {
        return strFechaRetiro;
    }

    /**
     * @param strFechaRetiro the strFechaRetiro to set
     */
    public void setStrFechaRetiro(String strFechaRetiro) {
        this.strFechaRetiro = strFechaRetiro;
    }

    /**
     * @return the strSemanas
     */
    public String getStrSemanas() {
        return strSemanas;
    }

    /**
     * @param strSemanas the strSemanas to set
     */
    public void setStrSemanas(String strSemanas) {
        this.strSemanas = strSemanas;
    }

    /**
     * @return the strFechaGeneracion
     */
    public String getStrFechaGeneracion() {
        return strFechaGeneracion;
    }

    /**
     * @param strFechaGeneracion the strFechaGeneracion to set
     */
    public void setStrFechaGeneracion(String strFechaGeneracion) {
        this.strFechaGeneracion = strFechaGeneracion;
    }

    @Override
    public String toString() {
        return "CertificadoAfiliacion{" + "strNombreCompleto=" + strNombreCompleto + ", strDocumentoCompleto=" + strDocumentoCompleto + ", strNivelSisben=" + strNivelSisben + ", strFechaAfiliacion=" + strFechaAfiliacion + ", strCiudad=" + strCiudad + ", strRegimen=" + strRegimen + ", strEstado=" + strEstado + ", strModelo=" + strModelo + ", strFechaRetiro=" + strFechaRetiro + ", strSemanas=" + strSemanas + ", strFechaGeneracion=" + strFechaGeneracion + '}';
    }

    /**
     * @return the strGrupoSisben
     */
    public String getStrGrupoSisben() {
        return strGrupoSisben;
    }

    /**
     * @param strGrupoSisben the strGrupoSisben to set
     */
    public void setStrGrupoSisben(String strGrupoSisben) {
        this.strGrupoSisben = strGrupoSisben;
    }

    /**
     * @return the strId
     */
    public String getStrId() {
        return strId;
    }

    /**
     * @param strId the strId to set
     */
    public void setStrId(String strId) {
        this.strId = strId;
    }

    /**
     * @return the strCategoriaIBC
     */
    public String getStrCategoriaIBC() {
        return strCategoriaIBC;
    }

    /**
     * @param strCategoriaIBC the strCategoriaIBC to set
     */
    public void setStrCategoriaIBC(String strCategoriaIBC) {
        this.strCategoriaIBC = strCategoriaIBC;
    }

    /**
     * @return the adjuntoStream
     * /
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     * /
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }*/
    
}
