/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeCarga extends Auditoria implements Serializable {
    
    private Integer id;
    private Empresa empresa = null;
    private String nombre;
    private Integer tipo;
    private String ruta;
    private String archivo;
    private int estado;
    private Integer registros;
    private Integer exitosos;
    private Integer fallidos;
    private Integer procesados;
    private String detalle;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer idPrograma;
    private String programa;
    private Boolean existe;
    private String respNombre;
    private String respRuta;
    private String respArchivo;
    private Boolean respExiste;
    
    private String codigoHabilitacion;
    // variables para carga de adjunto
    private transient InputStream adjuntoStream;

    public PeCarga() {
    }

    public PeCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    public Integer getExitosos() {
        return exitosos;
    }

    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    public Integer getFallidos() {
        return fallidos;
    }

    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    
    public Integer getRegistrosPendientes() {
        return exitosos + fallidos;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }      

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public Integer getProcesados() {
        return procesados;
    }

    public void setProcesados(Integer procesados) {
        this.procesados = procesados;
    }

    public Boolean getExiste() {
        return existe;
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
    }

    public String getRespNombre() {
        return respNombre;
    }

    public void setRespNombre(String respNombre) {
        this.respNombre = respNombre;
    }

    public String getRespRuta() {
        return respRuta;
    }

    public void setRespRuta(String respRuta) {
        this.respRuta = respRuta;
    }

    public String getRespArchivo() {
        return respArchivo;
    }

    public void setRespArchivo(String respArchivo) {
        this.respArchivo = respArchivo;
    }

    public Boolean getRespExiste() {
        return respExiste;
    }

    public void setRespExiste(Boolean respExiste) {
        this.respExiste = respExiste;
    }

    @Override
    public String toString() {
        return "PeCarga{" + "id=" + id + ", empresa=" + empresa + ", nombre=" + nombre + ", tipo=" + tipo + ", ruta=" + ruta + ", archivo=" + archivo + ", estado=" + estado + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", procesados=" + procesados + ", detalle=" + detalle + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", idPrograma=" + idPrograma + ", programa=" + programa + ", existe=" + existe + ", respNombre=" + respNombre + ", respRuta=" + respRuta + ", respArchivo=" + respArchivo + ", respExiste=" + respExiste + ", codigoHabilitacion=" + codigoHabilitacion + ", adjuntoStream=" + adjuntoStream + '}';
    }
    
    
}
