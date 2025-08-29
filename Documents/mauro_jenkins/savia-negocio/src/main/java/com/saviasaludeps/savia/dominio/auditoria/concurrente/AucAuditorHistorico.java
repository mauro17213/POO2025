/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class AucAuditorHistorico extends Auditoria {
    
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private AucAuditor aucAuditorId;

    public AucAuditorHistorico() {
    }

    public AucAuditorHistorico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public AucAuditor getAucAuditorId() {
        return aucAuditorId;
    }

    public void setAucAuditorId(AucAuditor aucAuditorId) {
        this.aucAuditorId = aucAuditorId;
    }

    @Override
    public String toString() {
        return "AucAuditorHistorico{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", aucAuditorId=" + aucAuditorId + '}';
    }
    
}
