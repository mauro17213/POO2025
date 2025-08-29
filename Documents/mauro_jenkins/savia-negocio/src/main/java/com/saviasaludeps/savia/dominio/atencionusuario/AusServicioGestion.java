/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;


public class AusServicioGestion extends Auditoria {

    private Integer id;
    private int estado;
    private String observacion;
    private AusCasoServicio servicios;

    public AusServicioGestion() {
    }

    public AusServicioGestion(Integer id) {
        this.id = id;
    }

    public AusServicioGestion(Integer id, int estado, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.observacion = observacion;
        this.setUsuarioCrea(usuarioCrea);
        this.setTerminalCrea(terminalCrea);
        this.setFechaHoraCrea(fechaHoraCrea);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
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

    public AusCasoServicio getAusServicios() {
        return servicios;
    }

    public void setAusServicios(AusCasoServicio serviciosId) {
        this.servicios = serviciosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AusServicioGestion)) {
            return false;
        }
        AusServicioGestion other = (AusServicioGestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AusServicioGestion{" + "id=" + id + ", estado=" + estado + ", observacion=" + observacion + ", servicios=" + servicios + '}';
    }

    
}
