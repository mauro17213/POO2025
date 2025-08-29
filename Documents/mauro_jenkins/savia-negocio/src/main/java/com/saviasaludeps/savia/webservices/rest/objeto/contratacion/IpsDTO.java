/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.contratacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class IpsDTO implements Serializable {

    private String codigoHabilitacion;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificacion;
    private String razonSocial;
    private String naturalezaJuridica;
    private String prefijo;
    private String clase;
    private String categoria;
    private String nivelAtencion;
    private String tipoDocRepresentanteLegal;
    private String docRepresentanteLegal;
    private String nomRepresentanteLegal;
    private List<IpsSedesDTO> sedes;

    public IpsDTO(String codigoHabilitacion, String tipoDocumento, String numeroDocumento, String digitoVerificacion, String razonSocial, String naturalezaJuridica, String prefijo, String clase, String categoria, String nivelAtencion) {
        this.codigoHabilitacion = codigoHabilitacion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.digitoVerificacion = digitoVerificacion;
        this.razonSocial = razonSocial;
        this.naturalezaJuridica = naturalezaJuridica;
        this.prefijo = prefijo;
        this.clase = clase;
        this.categoria = categoria;
        this.nivelAtencion = nivelAtencion;
    }

    public IpsDTO() {
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNaturalezaJuridica() {
        return naturalezaJuridica;
    }

    public void setNaturalezaJuridica(String naturalezaJuridica) {
        this.naturalezaJuridica = naturalezaJuridica;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(String nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    public List<IpsSedesDTO> getSedes() {
        return sedes;
    }

    public void setSedes(List<IpsSedesDTO> sedes) {
        this.sedes = sedes;
    }

    public String getTipoDocRepresentanteLegal() {
        return tipoDocRepresentanteLegal;
    }

    public void setTipoDocRepresentanteLegal(String tipoDocRepresentanteLegal) {
        this.tipoDocRepresentanteLegal = tipoDocRepresentanteLegal;
    }

    public String getDocRepresentanteLegal() {
        return docRepresentanteLegal;
    }

    public void setDocRepresentanteLegal(String docRepresentanteLegal) {
        this.docRepresentanteLegal = docRepresentanteLegal;
    }

    public String getNomRepresentanteLegal() {
        return nomRepresentanteLegal;
    }

    public void setNomRepresentanteLegal(String nomRepresentanteLegal) {
        this.nomRepresentanteLegal = nomRepresentanteLegal;
    }

    @Override
    public String toString() {
        return "Ips{" + "codigoHabilitacion=" + codigoHabilitacion + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", digitoVerificacion=" + digitoVerificacion + ", razonSocial=" + razonSocial + ", naturalezaJuridica=" + naturalezaJuridica + ", prefijo=" + prefijo + ", clase=" + clase + ", categoria=" + categoria + ", nivelAtencion=" + nivelAtencion + ", tipoDocRepresentanteLegal=" + tipoDocRepresentanteLegal + ", docRepresentanteLegal=" + docRepresentanteLegal + ", nomRepresentanteLegal=" + nomRepresentanteLegal + ", sedes=" + sedes + '}';
    }

}
