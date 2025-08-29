/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaCarga extends Auditoria {

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
    //objetos
    private List<MaDetalleCarga> listaDetalleCarga;
    // variables para carga de adjunto
    private transient InputStream adjuntoStream;
    //constantes
    private static int ESTADO_CARGA_EN_COLA = 0;
    private static int ESTADO_CARGA_PROCESANDO = 1;

    public MaCarga() {
        
    }
    
    public MaCarga(Integer id, int estado, Date fechaHoraInicio, int registrosTotal) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registros = registrosTotal;
    }

    public MaCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the registros
     */
    public Integer getRegistros() {
        return registros;
    }

    /**
     * @param registros the registros to set
     */
    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    /**
     * @return the exitosos
     */
    public Integer getExitosos() {
        return exitosos;
    }

    /**
     * @param exitosos the exitosos to set
     */
    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    /**
     * @return the fallidos
     */
    public Integer getFallidos() {
        return fallidos;
    }

    /**
     * @param fallidos the fallidos to set
     */
    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the fechaHoraInicio
     */
    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * @param fechaHoraInicio the fechaHoraInicio to set
     */
    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * @return the fechaHoraFin
     */
    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    /**
     * @param fechaHoraFin the fechaHoraFin to set
     */
    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    /**
     * @return the listaDetalleCarga
     */
    public List<MaDetalleCarga> getListaDetalleCarga() {
        return listaDetalleCarga;
    }

    /**
     * @param listaDetalleCarga the listaDetalleCarga to set
     */
    public void setListaDetalleCarga(List<MaDetalleCarga> listaDetalleCarga) {
        this.listaDetalleCarga = listaDetalleCarga;
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

    /**
     * @return the ESTADO_CARGA_EN_COLA
     */
    public static int getESTADO_CARGA_EN_COLA() {
        return ESTADO_CARGA_EN_COLA;
    }

    /**
     * @param aESTADO_CARGA_EN_COLA the ESTADO_CARGA_EN_COLA to set
     */
    public static void setESTADO_CARGA_EN_COLA(int aESTADO_CARGA_EN_COLA) {
        ESTADO_CARGA_EN_COLA = aESTADO_CARGA_EN_COLA;
    }

    /**
     * @return the ESTADO_CARGA_PROCESANDO
     */
    public static int getESTADO_CARGA_PROCESANDO() {
        return ESTADO_CARGA_PROCESANDO;
    }

    /**
     * @param aESTADO_CARGA_PROCESANDO the ESTADO_CARGA_PROCESANDO to set
     */
    public static void setESTADO_CARGA_PROCESANDO(int aESTADO_CARGA_PROCESANDO) {
        ESTADO_CARGA_PROCESANDO = aESTADO_CARGA_PROCESANDO;
    }

    public Integer getRegistrosPendientes() {
        return exitosos + fallidos;
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

    @Override
    public String toString() {
        return "MaCarga{" + "id=" + id + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", estado=" + estado + ", tipo=" + tipo + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", detalle=" + detalle + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", existe=" + existe + ", respNombre=" + respNombre + ", respRuta=" + respRuta + ", respArchivo=" + respArchivo + ", respExiste=" + respExiste + ", listaDetalleCarga=" + listaDetalleCarga + '}';
    }
    
}
