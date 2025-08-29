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
public class GnUsuarioSesion extends Auditoria {
    
    private Integer id;
    private String idSesion;
    private String ipServidor;
    private Date fechaHoraInicio;
    private Date fechaHoraUltimaGestion;
    private Date fechaHoraFin;
    private boolean activa;
    private Usuario gnUsuarioId;

    public GnUsuarioSesion() {
    }

    public GnUsuarioSesion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraUltimaGestion() {
        return fechaHoraUltimaGestion;
    }

    public void setFechaHoraUltimaGestion(Date fechaHoraUltimaGestion) {
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    @Override
    public String toString() {
        return "GnUsuarioSesion{" + "id=" + id + ", idSesion=" + idSesion + ", ipServidor=" + ipServidor + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", activa=" + activa + '}';
    }
    
}
