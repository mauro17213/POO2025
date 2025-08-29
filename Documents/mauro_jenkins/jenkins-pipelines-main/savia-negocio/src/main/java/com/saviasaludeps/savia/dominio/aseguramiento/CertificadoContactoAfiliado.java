/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author jyperez
 */
public class CertificadoContactoAfiliado implements Serializable{
    
    private int id;
    private Integer idAfiliado;
    private String ruta;
    private String nombreArchivo;
    // variables para carga de adjunto
    //private transient InputStream adjuntoStream;
    private String strId;
    private String strNombreSolicitante;
    private String strCargoSolicitante;
    private String strEnteControlSolicitante;
    private String strTipoDocumento;
    private String strDocumento;
    private String strNombreCompleto;
    private String strEstado;
    private String strRegimen;
    private String strDireccion;
    private String strBarrio;
    private String strTelefonos;
    private String strDepartamento;
    private String strCiudad;
    private String strPrestador;
    private String strEmail;
    private String strFechaGeneracion;

    public CertificadoContactoAfiliado() {
        
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
     * @return the strNombreSolicitante
     */
    public String getStrNombreSolicitante() {
        return strNombreSolicitante;
    }

    /**
     * @param strNombreSolicitante the strNombreSolicitante to set
     */
    public void setStrNombreSolicitante(String strNombreSolicitante) {
        this.strNombreSolicitante = strNombreSolicitante;
    }

    /**
     * @return the strCargoSolicitante
     */
    public String getStrCargoSolicitante() {
        return strCargoSolicitante;
    }

    /**
     * @param strCargoSolicitante the strCargoSolicitante to set
     */
    public void setStrCargoSolicitante(String strCargoSolicitante) {
        this.strCargoSolicitante = strCargoSolicitante;
    }

    /**
     * @return the strEnteControlSolicitante
     */
    public String getStrEnteControlSolicitante() {
        return strEnteControlSolicitante;
    }

    /**
     * @param strEnteControlSolicitante the strEnteControlSolicitante to set
     */
    public void setStrEnteControlSolicitante(String strEnteControlSolicitante) {
        this.strEnteControlSolicitante = strEnteControlSolicitante;
    }

    /**
     * @return the strTipoDocumento
     */
    public String getStrTipoDocumento() {
        return strTipoDocumento;
    }

    /**
     * @param strTipoDocumento the strTipoDocumento to set
     */
    public void setStrTipoDocumento(String strTipoDocumento) {
        this.strTipoDocumento = strTipoDocumento;
    }

    /**
     * @return the strDocumento
     */
    public String getStrDocumento() {
        return strDocumento;
    }

    /**
     * @param strDocumento the strDocumento to set
     */
    public void setStrDocumento(String strDocumento) {
        this.strDocumento = strDocumento;
    }

    /**
     * @return the strDireccion
     */
    public String getStrDireccion() {
        return strDireccion;
    }

    /**
     * @param strDireccion the strDireccion to set
     */
    public void setStrDireccion(String strDireccion) {
        this.strDireccion = strDireccion;
    }

    /**
     * @return the strBarrio
     */
    public String getStrBarrio() {
        return strBarrio;
    }

    /**
     * @param strBarrio the strBarrio to set
     */
    public void setStrBarrio(String strBarrio) {
        this.strBarrio = strBarrio;
    }

    /**
     * @return the strTelefonos
     */
    public String getStrTelefonos() {
        return strTelefonos;
    }

    /**
     * @param strTelefonos the strTelefonos to set
     */
    public void setStrTelefonos(String strTelefonos) {
        this.strTelefonos = strTelefonos;
    }

    /**
     * @return the strDepartamento
     */
    public String getStrDepartamento() {
        return strDepartamento;
    }

    /**
     * @param strDepartamento the strDepartamento to set
     */
    public void setStrDepartamento(String strDepartamento) {
        this.strDepartamento = strDepartamento;
    }

    /**
     * @return the strPrestador
     */
    public String getStrPrestador() {
        return strPrestador;
    }

    /**
     * @param strPrestador the strPrestador to set
     */
    public void setStrPrestador(String strPrestador) {
        this.strPrestador = strPrestador;
    }

    /**
     * @return the strEmail
     */
    public String getStrEmail() {
        return strEmail;
    }

    /**
     * @param strEmail the strEmail to set
     */
    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    @Override
    public String toString() {
        return "CertificadoContactoAfiliado{" + "id=" + id + ", strId=" + strId + ", strNombreSolicitante=" + strNombreSolicitante + ", strCargoSolicitante=" + strCargoSolicitante + ", strEnteControlSolicitante=" + strEnteControlSolicitante + ", strTipoDocumento=" + strTipoDocumento + ", strDocumento=" + strDocumento + ", strNombreCompleto=" + strNombreCompleto + ", strEstado=" + strEstado + ", strRegimen=" + strRegimen + ", strDireccion=" + strDireccion + ", strBarrio=" + strBarrio + ", strTelefonos=" + strTelefonos + ", strDepartamento=" + strDepartamento + ", strCiudad=" + strCiudad + ", strPrestador=" + strPrestador + ", strEmail=" + strEmail + ", strFechaGeneracion=" + strFechaGeneracion + '}';
    }
    
    
}
