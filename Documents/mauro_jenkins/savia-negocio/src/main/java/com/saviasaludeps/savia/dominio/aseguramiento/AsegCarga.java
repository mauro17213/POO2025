/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author José Pérez
 */
public class AsegCarga extends Auditoria {

    private Integer id;
    private String nombre;
    private String ruta;
    private String archivo;
    private int estado;
    private int tipo;
    private Integer registros;
    private Integer exitosos;
    private Integer fallidos;
    private String detalle;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    //2025-02-12 jyperez campos nuevos
    private boolean existe;
    private String respNombre;
    private String respRuta;
    private String respArchivo;
    private boolean respExiste;
    
    private List<AsegDetalleCarga> listaAsegDetalleCargas;
    // variables para carga de adjunto
    private transient InputStream adjuntoStream;
    //constantes
    public final static int ESTADO_CARGA_EN_COLA = 0;
    public static int ESTADO_CARGA_PROCESANDO = 1;

    public AsegCarga() {
    }

    public AsegCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getRegistros() {
        return registros;
    }

    public Integer getRegistrosPendientes() {
        return exitosos + fallidos;
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

    /**
     * @return the listaAsegDetalleCargas
     */
    public List<AsegDetalleCarga> getListaAsegDetalleCargas() {
        return listaAsegDetalleCargas;
    }

    /**
     * @param listaAsegDetalleCargas the listaAsegDetalleCargas to set
     */
    public void setListaAsegDetalleCargas(List<AsegDetalleCarga> listaAsegDetalleCargas) {
        this.listaAsegDetalleCargas = listaAsegDetalleCargas;
    }

    /**
     * @return the adjuntoStream
     */
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     */
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    
        public String getFechaStr(Date fecha) {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            mensaje = sdf.format(fecha);
        }catch (Exception ex) {
            mensaje= "";
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "AsegCarga{" + "id=" + id + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", estado=" + estado + ", tipo=" + tipo + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", detalle=" + detalle + ", fechaHoraInicio=" +  getFechaStr(fechaHoraInicio) + ", fechaHoraFin=" +  getFechaStr(fechaHoraFin)  + ", existe=" + existe + ", respNombre=" + respNombre + ", respRuta=" + respRuta + ", respArchivo=" + respArchivo + ", respExiste=" + respExiste + ", listaAsegDetalleCargas=" + listaAsegDetalleCargas + '}';
    }

    /**
     * @return the existe
     */
    public boolean isExiste() {
        return existe;
    }

    /**
     * @param existe the existe to set
     */
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    /**
     * @return the respNombre
     */
    public String getRespNombre() {
        return respNombre;
    }

    /**
     * @param respNombre the respNombre to set
     */
    public void setRespNombre(String respNombre) {
        this.respNombre = respNombre;
    }

    /**
     * @return the respRuta
     */
    public String getRespRuta() {
        return respRuta;
    }

    /**
     * @param respRuta the respRuta to set
     */
    public void setRespRuta(String respRuta) {
        this.respRuta = respRuta;
    }

    /**
     * @return the respArchivo
     */
    public String getRespArchivo() {
        return respArchivo;
    }

    /**
     * @param respArchivo the respArchivo to set
     */
    public void setRespArchivo(String respArchivo) {
        this.respArchivo = respArchivo;
    }

    /**
     * @return the respExiste
     */
    public boolean isRespExiste() {
        return respExiste;
    }

    /**
     * @param respExiste the respExiste to set
     */
    public void setRespExiste(boolean respExiste) {
        this.respExiste = respExiste;
    }

}
