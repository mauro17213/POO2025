/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.contratacion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class IpsSedesDTO implements Serializable {

    private String Municipio;
    private String codigoPrestador;
    private String direccion;
    private String nombre;
    private String codigo;
    private String codigoHabilitacion;
    private String zonaPrecedencia;
    private String estado;
    private String nivelAtencion;
    private String clasePrestador;
    private String fax;
    private String telefonoCitas;
    private String correoElectronico;
    private String telefonoAdministrativo;

    public IpsSedesDTO() {
    }

    public IpsSedesDTO(String Municipio, String codigoPrestador, String direccion, String nombre, String codigo, String codigoHabilitacion, String zonaPrecedencia, String estado, String nivelAtencion, String clasePrestador) {
        this.Municipio = Municipio;
        this.codigoPrestador = codigoPrestador;
        this.direccion = direccion;
        this.nombre = nombre;
        this.codigo = codigo;
        this.codigoHabilitacion = codigoHabilitacion;
        this.zonaPrecedencia = zonaPrecedencia;
        this.estado = estado;
        this.nivelAtencion = nivelAtencion;
        this.clasePrestador = clasePrestador;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getCodigoPrestador() {
        return codigoPrestador;
    }

    public void setCodigoPrestador(String codigoPrestador) {
        this.codigoPrestador = codigoPrestador;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public String getZonaPrecedencia() {
        return zonaPrecedencia;
    }

    public void setZonaPrecedencia(String zonaPrecedencia) {
        this.zonaPrecedencia = zonaPrecedencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(String nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    public String getClasePrestador() {
        return clasePrestador;
    }

    public void setClasePrestador(String clasePrestador) {
        this.clasePrestador = clasePrestador;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelefonoCitas() {
        return telefonoCitas;
    }

    public void setTelefonoCitas(String telefonoCitas) {
        this.telefonoCitas = telefonoCitas;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoAdministrativo() {
        return telefonoAdministrativo;
    }

    public void setTelefonoAdministrativo(String telefonoAdministrativo) {
        this.telefonoAdministrativo = telefonoAdministrativo;
    }

    @Override
    public String toString() {
        return "IpsSedes{" + "Municipio=" + Municipio + ", codigoPrestador=" + codigoPrestador + ", direccion=" + direccion + ", nombre=" + nombre + ", codigo=" + codigo + ", codigoHabilitacion=" + codigoHabilitacion + ", zonaPrecedencia=" + zonaPrecedencia + ", estado=" + estado + ", nivelAtencion=" + nivelAtencion + ", clasePrestador=" + clasePrestador + ", fax=" + fax + ", telefonoCitas=" + telefonoCitas + ", correoElectronico=" + correoElectronico + ", telefonoAdministrativo=" + telefonoAdministrativo + '}';
    }

}
