/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author acuartas
 */
public class GatTaquillaFuncionario extends Auditoria {

    private Integer id;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private GatSedeTaquilla gatSedeTaquillaId;
    private Usuario gnUsuarioId;

    public GatTaquillaFuncionario() {
    }

    public GatTaquillaFuncionario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public GatSedeTaquilla getGatSedeTaquillaId() {
        return gatSedeTaquillaId;
    }

    public void setGatSedeTaquillaId(GatSedeTaquilla gatSedeTaquillaId) {
        this.gatSedeTaquillaId = gatSedeTaquillaId;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    @Override
    public String toString() {
        return "GatTaquillaFuncionario{" + "id=" + id + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", gatSedeTaquillaId=" + gatSedeTaquillaId + ", gnUsuarioId=" + gnUsuarioId + '}';
    }
    
}
