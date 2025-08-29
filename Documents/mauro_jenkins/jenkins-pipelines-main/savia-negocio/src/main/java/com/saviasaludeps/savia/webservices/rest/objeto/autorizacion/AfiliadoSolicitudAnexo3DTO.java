/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class AfiliadoSolicitudAnexo3DTO implements Serializable {

    private String tipoDocumento;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String fechaNacimiento;
    private String nivelSisben;
    private String nivelIBC;
    private String telResAfil;
    private String codDepartAfil;
    private String codCiudadAfil;
    private String deparAfil;
    private String ciudadAfil;
    private String tipoBeneficiario;
    private String regimen;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getNivelIBC() {
        return nivelIBC;
    }

    public void setNivelIBC(String nivelIBC) {
        this.nivelIBC = nivelIBC;
    }

    public String getTelResAfil() {
        return telResAfil;
    }

    public void setTelResAfil(String telResAfil) {
        this.telResAfil = telResAfil;
    }

    public String getCodDepartAfil() {
        return codDepartAfil;
    }

    public void setCodDepartAfil(String codDepartAfil) {
        this.codDepartAfil = codDepartAfil;
    }

    public String getCodCiudadAfil() {
        return codCiudadAfil;
    }

    public void setCodCiudadAfil(String codCiudadAfil) {
        this.codCiudadAfil = codCiudadAfil;
    }

    public String getDeparAfil() {
        return deparAfil;
    }

    public void setDeparAfil(String deparAfil) {
        this.deparAfil = deparAfil;
    }

    public String getCiudadAfil() {
        return ciudadAfil;
    }

    public void setCiudadAfil(String ciudadAfil) {
        this.ciudadAfil = ciudadAfil;
    }

    public String getTipoBeneficiario() {
        return tipoBeneficiario;
    }

    public void setTipoBeneficiario(String tipoBeneficiario) {
        this.tipoBeneficiario = tipoBeneficiario;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

}
