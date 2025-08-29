/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.reserva;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class RtReservaArchivo extends Auditoria {
    
   
    private Integer id;
    private Short tipo;    
    private Integer estado;
    private String observacion;
    private boolean descargado;
    private boolean tieneArchivo;
    private String archivoNombre;
    private String ruta;
    private String archivo;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Short tiempo;
 
    private RtReserva rtReservaId;

    public RtReservaArchivo() {
    }

    public RtReservaArchivo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    public boolean isTieneArchivo() {
        return tieneArchivo;
    }

    public void setTieneArchivo(boolean tieneArchivo) {
        this.tieneArchivo = tieneArchivo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
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

    public RtReserva getRtReservaId() {
        return rtReservaId;
    }

    public void setRtReservaId(RtReserva rtReservaId) {
        this.rtReservaId = rtReservaId;
    }
    
    public String getEstadoStr(){
        String valor = "";
        if(estado == null){
             return valor;
        }
        switch (getEstado()) {
            case 0:
                valor= "Generando";
                break;
            case 1:
                valor= "Confirmado";
                break;
            case 2:
                valor= "Rechazado";
                break;
        }
        return valor;
    }
    
    public Date getFechaHoraInicio(){
        return fechaHoraInicio;
    }
    
    public void setFechaHoraInicio(Date fechaHoraInicia){
        this.fechaHoraInicio = fechaHoraInicia;
    }
    
    public Date getFechaHoraFin(){
        return fechaHoraFin;
    }
    
    public void setFechaHoraFin(Date fechaHoraFin){
        this.fechaHoraFin = fechaHoraFin;
    }
    
    public Short getTiempo(){
        return tiempo;
    }
    
    public void setTiempo(Short tiempo){
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "RtReservaArchivo{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", observacion=" + observacion + ", descargado=" + descargado + ", tieneArchivo=" + tieneArchivo + ", archivoNombre=" + archivoNombre + ", ruta=" + ruta + ", archivo=" + archivo + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", tiempo=" + tiempo + ", rtReservaId=" + rtReservaId + '}';
    }

    
}
