/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeIpsPrograma extends Auditoria implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean activo;
    private CntPrestadorSede cntPrestadorSedesId;
    private PePrograma peProgramasId;

    public PeIpsPrograma() {
    }

    public PeIpsPrograma(Integer id) {
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

    public boolean isActivo() {
        return activo;
    }
    
    public String getActivoString() {
        return activo ? "Si" : "No";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public CntPrestadorSede getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSede cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public PePrograma getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PePrograma peProgramasId) {
        this.peProgramasId = peProgramasId;
    }
    
    @Override
    public String toString() {
        return "PeAdjunto{" + "id=" + id + ", FechaInicio=" + fechaInicio + ", FechaFin=" + fechaFin + ", Activo=" + activo 
                + ", cntPrestadorSedeId=" + cntPrestadorSedesId + ", PePrograma=" + peProgramasId + '}';
    }
}
