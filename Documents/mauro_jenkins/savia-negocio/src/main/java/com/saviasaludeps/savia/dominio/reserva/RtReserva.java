/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.reserva;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.*;

/**
 *
 * @author idbohorquez
 */
public class RtReserva extends Auditoria {
    
    private Integer id;
    private String nombre;
    private Date fecha;
    private Integer estado;
    private String observacion;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Short tiempo;
    

    public RtReserva() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraInicia() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFinaliza) {
        this.fechaHoraFin = fechaHoraFinaliza;
    }
    
    public Short getTiempo(){
        return tiempo;
    }
    
    public void setTiempo(Short tiempo){
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "ReservaTecnica{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", estados=" + estado + ", observacion=" + observacion + ", fechaHoraInicia=" + fechaHoraInicio + ", fechaHoraFinaliza=" + fechaHoraFin + '}';
    }

    
    
    
}
