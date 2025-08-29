/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class Anexo4Reporte extends Auditoria {
    
    private int numeroAutorizacion;
    private Integer anexo2;
    private Integer anexo3;
    private int tipoDocumento;
    private String numeroDocumento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String codigoTecnologia;
    private String nombreTecnologia;
    private int cantidadAutorizada;
    private String nombrePrestador;
    private Integer estado;
    private Date fechaAutorizacion;
    private String usuarioAutorizado;
    private String telefonos;
    private String regimen;
    private String ambito;
    private String nombreSede;

    public Anexo4Reporte(int numeroAutorizacion, Integer anexo2, Integer anexo3, int tipoDocumento, String numeroDocumento, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String codigoTecnologia, String nombreTecnologia, int cantidadAutorizada, String nombrePrestador, Integer estado, Date fechaAutorizacion, String usuarioAutorizado, String telefonos, String regimen, String ambito, String nombreSede) {
        this.numeroAutorizacion = numeroAutorizacion;
        this.anexo2 = anexo2;
        this.anexo3 = anexo3;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.codigoTecnologia = codigoTecnologia;
        this.nombreTecnologia = nombreTecnologia;
        this.cantidadAutorizada = cantidadAutorizada;
        this.nombrePrestador = nombrePrestador;
        this.estado = estado;
        this.fechaAutorizacion = fechaAutorizacion;
        this.usuarioAutorizado = usuarioAutorizado;
        this.telefonos = telefonos;
        this.regimen = regimen;
        this.ambito = ambito;
        this.nombreSede = nombreSede;
    }
    
    public int getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(int numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Integer getAnexo2() {
        return anexo2;
    }

    public void setAnexo2(Integer anexo2) {
        this.anexo2 = anexo2;
    }

    public Integer getAnexo3() {
        return anexo3;
    }

    public void setAnexo3(Integer anexo3) {
        this.anexo3 = anexo3;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public int getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(int cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getUsuarioAutorizado() {
        return usuarioAutorizado;
    }

    public void setUsuarioAutorizado(String usuarioAutorizado) {
        this.usuarioAutorizado = usuarioAutorizado;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }
    
    public String obtenerNombreCompleto(){
        String nombre = "";
        nombre += getPrimerNombre()+" ";
        nombre += getSegundoNombre() != null ? getSegundoNombre()+" " : "";
        nombre += getPrimerApellido()+" ";
        nombre += getSegundoApellido() != null ? getSegundoApellido()+" " : "";
        return nombre;
    }

    public String getNombrePrestador() {
        return nombrePrestador;
    }

    public void setNombrePrestador(String nombrePrestador) {
        this.nombrePrestador = nombrePrestador;
    }
    
}
