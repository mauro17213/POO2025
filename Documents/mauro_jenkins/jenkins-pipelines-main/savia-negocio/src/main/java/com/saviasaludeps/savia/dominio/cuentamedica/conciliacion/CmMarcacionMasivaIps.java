/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author jorge perez
 */
public class CmMarcacionMasivaIps extends Auditoria {
    
    public static final int ESTADO_MARCACION_EN_PROCESO = 0;
    public static final int ESTADO_MARCACION_PROCESADO  = 1;
    public static final int ESTADO_MARCACION_CANCELADO  = 2;
    
    private int id;
    private boolean hayErroresEnMarcacion;
    private StringBuilder mensajeError;
    private String nombreArchivo;
    private String ruta;
    private String nombre;
    private String archivo;
    private int    estado;
    private Integer registros;
    private Integer exitosos;
    private Integer fallidos;   
    private transient InputStream contenidoArchivo;
    private Date fechaHoraFin;
    private Date fechaHoraInicio;
    private Empresa empresa;

    public CmMarcacionMasivaIps() {
    }

    public CmMarcacionMasivaIps(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getHayErroresEnMarcacion() {
        return hayErroresEnMarcacion;
    }

    public void setHayErroresEnMarcacion(boolean hayErroresEnMarcacion) {
        this.hayErroresEnMarcacion = hayErroresEnMarcacion;
    }

    public StringBuilder getMensajeError() {
        if (mensajeError == null) {
            mensajeError = new StringBuilder();
        }
        return mensajeError;
    }


    public void setMensajeError(StringBuilder mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public InputStream getContenidoArchivo() {
        return contenidoArchivo;
    }

    public void setContenidoArchivo(InputStream contenidoArchivo) {
        this.contenidoArchivo = contenidoArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public String getEstadoStr() {
        return CmMarcacionMasivaIps.getEstadoStr(getEstado());
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_MARCACION_PROCESADO:
                str = "Procesado";
                break;
            case ESTADO_MARCACION_EN_PROCESO:
                str = "En Proceso";
                break;
            case ESTADO_MARCACION_CANCELADO:
                str = "Cancelado";
                break;  
            default:
                str = "";
                break;
        }
        return str;
    }  

    @Override
    public String toString() {
        return "CmMarcacionMasivaIps{" + "id=" + id + ", nombreArchivo=" + nombreArchivo +", empresa id = "+ getEmpresa().getId() +", ruta=" + ruta + ", nombre=" + nombre + ", archivo=" + archivo + ", estado=" + estado + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", fechaHoraFin=" + fechaHoraFin + ", fechaHoraInicio=" + fechaHoraInicio + "mensaje error = "+mensajeError+'}';
    }  
    
}
