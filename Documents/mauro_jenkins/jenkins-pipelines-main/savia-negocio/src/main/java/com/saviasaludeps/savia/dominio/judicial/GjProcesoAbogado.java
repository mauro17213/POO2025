/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author bsgomez
 */
public class GjProcesoAbogado extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean activo;

    private GjAbogado gjAbogadosId;
    private GjProceso gjProcesosId;

    public GjProcesoAbogado() {
    }

    public GjProcesoAbogado(Integer id) {
        this.id = id;
    }

    public GjProcesoAbogado(Integer id, Date fechaInicio, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.activo = activo;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
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

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public GjAbogado getGjAbogadosId() {
        if (gjAbogadosId == null) {
            gjAbogadosId = new GjAbogado();
        }
        return gjAbogadosId;
    }

    public void setGjAbogadosId(GjAbogado gjAbogadosId) {
        this.gjAbogadosId = gjAbogadosId;
    }

    public GjProceso getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProceso gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    @Override
    public String toString() {
        return "GjProcesoAbogado{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", activo=" + activo + ", usuarioCrea=" + usuarioCrea + ", terminalCrea=" + terminalCrea + ", fechaHoraCrea=" + fechaHoraCrea + ", usuarioModifica=" + usuarioModifica + ", terminalModifica=" + terminalModifica + ", fechaHoraModifica=" + fechaHoraModifica + ", gjAbogadosId=" + gjAbogadosId + ", gjProcesosId=" + gjProcesosId + '}';
    }

}
