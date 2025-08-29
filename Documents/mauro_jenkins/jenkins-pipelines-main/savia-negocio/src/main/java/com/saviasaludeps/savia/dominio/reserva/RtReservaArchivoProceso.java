/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.reserva;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.*;

/**
 *
 * @author aguevara
 */
public class RtReservaArchivoProceso extends Auditoria {
    
    private Integer id;
    private String nombre;
    private Short estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Short tiempo;
    private String descripcion;

    
    public RtReservaArchivoProceso(){
        
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

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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

    public Short getTiempo() {
        return tiempo;
    }

    public void setTiempo(Short tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ReservaArvicoProceso{" + "id=" + id + ", nombre=" + nombre + ", estados=" + estado + ", fechaHoraInicia=" + fechaHoraInicio + ", fechaHoraFinaliza=" + fechaHoraFin + ", descripcion=" + descripcion +'}';
    }
    
}
