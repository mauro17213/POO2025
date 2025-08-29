/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class AuSolicitud extends Auditoria {
    
    private int id;
    private int numeroSolicitud;
    private Maestro tipoCargue;
    private Maestro tipoDocumento;
    private int numeroDocumento;
    private String apellidos;
    private String nombres;
    private int numeroAutorizaciones;
    private int cantidadTecnologias;
    private Date fechaSolicitud;
    private Maestro ambito;
    private Maestro ipsSolicita;
    private Maestro servicioSolicitado;
    private Usuario usuarioSolicita;
    private Usuario usuarioResponsable;
    private String anulado;
    private AuAnexo3 anexo3;
    private AuAnexo4 anexo4;
    
    public AuSolicitud(){
        this.anexo3 = new AuAnexo3();
        this.anexo4 = new AuAnexo4();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(int numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Maestro getTipoCargue() {
        return tipoCargue;
    }

    public void setTipoCargue(Maestro tipoCargue) {
        this.tipoCargue = tipoCargue;
    }

    public Maestro getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Maestro tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNumeroAutorizaciones() {
        return numeroAutorizaciones;
    }

    public void setNumeroAutorizaciones(int numeroAutorizaciones) {
        this.numeroAutorizaciones = numeroAutorizaciones;
    }

    public int getCantidadTecnologias() {
        return cantidadTecnologias;
    }

    public void setCantidadTecnologias(int cantidadTecnologias) {
        this.cantidadTecnologias = cantidadTecnologias;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Maestro getAmbito() {
        return ambito;
    }

    public void setAmbito(Maestro ambito) {
        this.ambito = ambito;
    }

    public Maestro getIpsSolicita() {
        return ipsSolicita;
    }

    public void setIpsSolicita(Maestro ipsSolicita) {
        this.ipsSolicita = ipsSolicita;
    }

    public Maestro getServicioSolicitado() {
        return servicioSolicitado;
    }

    public void setServicioSolicitado(Maestro servicioSolicitado) {
        this.servicioSolicitado = servicioSolicitado;
    }

    public Usuario getUsuarioSolicita() {
        return usuarioSolicita;
    }

    public void setUsuarioSolicita(Usuario usuarioSolicita) {
        this.usuarioSolicita = usuarioSolicita;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }   

    public AuAnexo3 getAnexo3() {
        return anexo3;
    }

    public void setAnexo3(AuAnexo3 anexo3) {
        this.anexo3 = anexo3;
    }

    public AuAnexo4 getAnexo4() {
        return anexo4;
    }

    public void setAnexo4(AuAnexo4 anexo4) {
        this.anexo4 = anexo4;
    }   

    @Override
    public String toString() {
        return "AuSolicitud{" + "id=" + id + ", numeroSolicitud=" + numeroSolicitud + ", tipoCargue=" + tipoCargue + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", apellidos=" + apellidos + ", nombres=" + nombres + ", numeroAutorizaciones=" + numeroAutorizaciones + ", cantidadTecnologias=" + cantidadTecnologias + ", fechaSolicitud=" + fechaSolicitud + ", ambito=" + ambito + ", ipsSolicita=" + ipsSolicita + ", servicioSolicitado=" + servicioSolicitado + ", usuarioSolicita=" + usuarioSolicita + ", usuarioResponsable=" + usuarioResponsable + ", anulado=" + anulado + '}';
    }
}
