/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jhohan Lopez
 */
public class PeCargaVariable extends Auditoria implements Serializable {
    
    private Integer id;
    private Empresa empresa;
    private PePrograma programa;
    private CntPrestadorSede prestadorSede;
    private String nombre;
    private String ruta;
    private String archivo;
    private Boolean existe;
    private int estado;
    private Integer periodoCargue;
    private Integer registros;
    private Integer exitosos;
    private Integer fallidos;
    private Integer procesados;
    private String detalle;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private String respNombre;
    private String respRuta;
    private String respArchivo;
    private Boolean respExiste;
    private String errorCarga;
    // variables para carga de adjunto
    private transient InputStream adjuntoStream;

    public PeCargaVariable() {
    }

    public PeCargaVariable(Integer id) {
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

    public void setPrograma(PePrograma programa) {
        this.programa = programa;
    }


    public PePrograma getPrograma() {
        return programa;
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

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public Integer getPeriodoCargue() {
        return periodoCargue;
    }

    public void setPeriodoCargue(Integer periodoCargue) {
        this.periodoCargue = periodoCargue;
    }

    public CntPrestadorSede getPrestadorSede() {
        return prestadorSede;
    }

    public void setPrestadorSede(CntPrestadorSede prestadorSede) {
        this.prestadorSede = prestadorSede;
    }
    
    
    @Override
    public String toString() {
        return "PeCarga{" + "id=" + id + ", idEmpresa=" + (empresa != null ? empresa.getId() : null) 
                + ", prestadorSede={" + (prestadorSede != null ? "id="+prestadorSede.getId()+", codigoHabilitacion="+ prestadorSede.getCodigoHabilitacionSede()+"}" : null)
                + ", nombre=" + nombre + ",ruta=" + ruta + ", archivo=" + archivo + ", estado=" + estado + ", registros=" + registros + ", exitosos=" + exitosos 
                + ", fallidos="+ fallidos + ", procesados=" + procesados + ", detalle=" + detalle + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin 
                + ", programa=" + (programa != null ? programa.getId() : null) + ", existe=" + existe + ", respNombre=" + respNombre + ", respRuta=" + respRuta 
                + ", respArchivo=" + respArchivo + ", respExiste=" + respExiste + ", adjuntoStream=" + adjuntoStream + ", periodoCargue=" + periodoCargue + ", errorCarga=" + errorCarga + '}';
    }
    
    
}
