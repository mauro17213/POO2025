/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class AsegTraslado extends Auditoria {

    private Integer id;
    private String codigoEps;
    private String tipoDocumentoBdua;
    private String numeroDocumentoBdua;
    private String primerApellidoBdua;
    private String segundoApellidoBdua;
    private String primerNombreBdua;
    private String segundoNombreBdua;
    private Date fechaNacimientoBdua;
    private String generoBdua;
    private Date fechaReporte;
    private int ubicacion;
    private AsegAfiliado asegAfiliado;
    private Integer maeEpsOrigenId;
    //2020-08-19 jyperez adición objeto Maestro para tratamiento de lista
    private Maestro maestroEps;
    //2021-07-14 jyperez variable adicional manejo de actualización carga masiva
    private Boolean registroExistente;

    public AsegTraslado() {
    }

    public AsegTraslado(Integer id) {
        this.id = id;
    }

    public AsegTraslado(Integer id, String tipoDocumentoBdua, String numeroDocumentoBdua, String primerApellidoBdua, String primerNombreBdua, Date fechaNacimientoBdua, String generoBdua, int ubicacion) {
        this.id = id;
        this.tipoDocumentoBdua = tipoDocumentoBdua;
        this.numeroDocumentoBdua = numeroDocumentoBdua;
        this.primerApellidoBdua = primerApellidoBdua;
        this.primerNombreBdua = primerNombreBdua;
        this.fechaNacimientoBdua = fechaNacimientoBdua;
        this.generoBdua = generoBdua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getTipoDocumentoBdua() {
        return tipoDocumentoBdua;
    }

    public void setTipoDocumentoBdua(String tipoDocumentoBdua) {
        this.tipoDocumentoBdua = tipoDocumentoBdua;
    }

    public String getNumeroDocumentoBdua() {
        return numeroDocumentoBdua;
    }

    public void setNumeroDocumentoBdua(String numeroDocumentoBdua) {
        this.numeroDocumentoBdua = numeroDocumentoBdua;
    }

    public String getPrimerApellidoBdua() {
        return primerApellidoBdua;
    }

    public void setPrimerApellidoBdua(String primerApellidoBdua) {
        this.primerApellidoBdua = primerApellidoBdua;
    }

    public String getSegundoApellidoBdua() {
        return segundoApellidoBdua;
    }

    public void setSegundoApellidoBdua(String segundoApellidoBdua) {
        this.segundoApellidoBdua = segundoApellidoBdua;
    }

    public String getPrimerNombreBdua() {
        return primerNombreBdua;
    }

    public void setPrimerNombreBdua(String primerNombreBdua) {
        this.primerNombreBdua = primerNombreBdua;
    }

    public String getSegundoNombreBdua() {
        return segundoNombreBdua;
    }

    public void setSegundoNombreBdua(String segundoNombreBdua) {
        this.segundoNombreBdua = segundoNombreBdua;
    }

    public Date getFechaNacimientoBdua() {
        return fechaNacimientoBdua;
    }

    public void setFechaNacimientoBdua(Date fechaNacimientoBdua) {
        this.fechaNacimientoBdua = fechaNacimientoBdua;
    }

    public String getGeneroBdua() {
        return generoBdua;
    }

    public void setGeneroBdua(String generoBdua) {
        this.generoBdua = generoBdua;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    @Override
    public String toString() {
        return "AsegTraslado{" + "id=" + id + ", codigoEps=" + codigoEps + ", tipoDocumentoBdua=" + tipoDocumentoBdua + ", numeroDocumentoBdua=" + numeroDocumentoBdua + ", primerApellidoBdua=" + primerApellidoBdua + ", segundoApellidoBdua=" + segundoApellidoBdua + ", primerNombreBdua=" + primerNombreBdua + ", segundoNombreBdua=" + segundoNombreBdua + ", fechaNacimientoBdua=" + fechaNacimientoBdua + ", generoBdua=" + generoBdua + ", fechaReporte=" + fechaReporte + ", ubicacion=" + ubicacion + ", asegAfiliado=" + asegAfiliado + '}';
    }

    /**
     * @return the maeEpsOrigenId
     */
    public Integer getMaeEpsOrigenId() {
        return maeEpsOrigenId;
    }

    /**
     * @param maeEpsOrigenId the maeEpsOrigenId to set
     */
    public void setMaeEpsOrigenId(Integer maeEpsOrigenId) {
        this.maeEpsOrigenId = maeEpsOrigenId;
    }

    /**
     * @return the maestroEps
     */
    public Maestro getMaestroEps() {
        return maestroEps;
    }

    /**
     * @param maestroEps the maestroEps to set
     */
    public void setMaestroEps(Maestro maestroEps) {
        this.maestroEps = maestroEps;
    }

    /**
     * @return the registroExistente
     */
    public Boolean getRegistroExistente() {
        return registroExistente;
    }

    /**
     * @param registroExistente the registroExistente to set
     */
    public void setRegistroExistente(Boolean registroExistente) {
        this.registroExistente = registroExistente;
    }
    
}
