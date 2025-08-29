/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jperez
 */
public class Reporte implements Serializable{
    
  
    private Integer tipoReporte;
    private byte[] contenidoEnBytes;
    private String contenidoEnString = "";
    private String nombreReporte;
    private boolean reporteEps;
    private Date fechaInicioRadicacion;
    private Date fechaFinRadicacion;
    private String nit;
    private String ips;
    private boolean mostrarfiltrosBusqueda = true;
    private String ruta;
    private int cantidadRegistros;
    private String observacion  = "";
    
    public Integer getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(Integer tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public byte[] getContenidoEnBytes() {
        return contenidoEnBytes;
    }

    public void setContenidoEnBytes(byte[] contenidoEnBytes) {
        this.contenidoEnBytes = contenidoEnBytes;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public boolean isMostrarfiltrosBusqueda() {
        return mostrarfiltrosBusqueda;
    }

    public void setMostrarfiltrosBusqueda(boolean mostrarfiltrosBusqueda) {
        this.mostrarfiltrosBusqueda = mostrarfiltrosBusqueda;
    }
     
    public void setReporteEps(boolean reporteEps) {
        this.reporteEps = reporteEps;
    }   

    public Date getFechaInicioRadicacion() {
        return fechaInicioRadicacion;
    }

    public void setFechaInicioRadicacion(Date fechaInicioRadicacion) {
        this.fechaInicioRadicacion = fechaInicioRadicacion;
    }

    public Date getFechaFinRadicacion() {
        return fechaFinRadicacion;
    }

    public void setFechaFinRadicacion(Date fechaFinRadicacion) {
        this.fechaFinRadicacion = fechaFinRadicacion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getContenidoEnString() {
        return contenidoEnString;
    }

    public void setContenidoEnString(String contenidoEnString) {
        this.contenidoEnString = contenidoEnString;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    @Override
    public String toString() {
        return "Reporte{" + "tipoReporte=" + tipoReporte + ", nombreReporte=" + nombreReporte + ", reporteEps=" + reporteEps + ", fechaInicioRadicacion=" + fechaInicioRadicacion + ", fechaFinRadicacion=" + fechaFinRadicacion + ", nit=" + nit + ", ips=" + ips + '}';
    }
    
}
