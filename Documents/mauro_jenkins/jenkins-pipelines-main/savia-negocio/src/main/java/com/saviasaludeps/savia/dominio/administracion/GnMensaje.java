/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class GnMensaje extends Auditoria {
    
    //ESTADOS
    public static final int ESTADO_URGENTE = 0;
    public static final int ESTADO_ALTA = 1;
    public static final int ESTADO_MEDIA = 2;
    public static final int ESTADO_BAJA = 3;
    public static final int ESTADO_INTERNA = 0;
    public static final int ESTADO_EXTERNA = 1;
    public static final int ESTADO_AMBAS = 2;
    
    //Textos
    private static final String URGENTE = "Urgente";
    private static final String ALTA = "Alta";
    private static final String MEDIA = "Media";
    private static final String BAJA = "Baja";
    private static final String INTERNA = "Interna";
    private static final String EXTERNA = "Externa";
    private static final String AMBAS = "Ambas";
    
    private Integer id;
    private String nombre;
    private String descripcon;
    private byte[] contenido;
    private Date fechaDesde;
    private Date fechaHasta;
    private int prioridad;
    private int exposicion;
    
    //Axuliares
    private String texto;

    public GnMensaje() {
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

    public String getDescripcon() {
        return descripcon;
    }

    public void setDescripcon(String descripcon) {
        this.descripcon = descripcon;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getExposicion() {
        return exposicion;
    }

    public void setExposicion(int exposicion) {
        this.exposicion = exposicion;
    }
    
    //Metodos auxiliares
    public String getExposicionStr() {
        switch(getExposicion()) {
            case ESTADO_INTERNA:
                return INTERNA;
            case ESTADO_EXTERNA:
                return EXTERNA;
            case ESTADO_AMBAS:
                return AMBAS;
        }
        return "";
    }
    
    public String getPrioridadStr() {
        switch(getPrioridad()) {            
            case ESTADO_URGENTE:
                return URGENTE;
            case ESTADO_ALTA:
                return ALTA;
            case ESTADO_MEDIA:
                return MEDIA;
            case ESTADO_BAJA:
                return BAJA;
        }
        return "";
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }  

    @Override
    public String toString() {
        return "GnMensaje{" + "id=" + id + ", nombre=" + nombre + ", descripcon=" + descripcon + ", contenido=" + contenido + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", prioridad=" + prioridad + ", exposicion=" + exposicion + '}';
    }
    
}
