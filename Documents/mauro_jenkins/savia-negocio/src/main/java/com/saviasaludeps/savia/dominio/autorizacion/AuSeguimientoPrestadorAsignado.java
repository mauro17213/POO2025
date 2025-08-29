/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author iavenegas
 */
public class AuSeguimientoPrestadorAsignado extends Auditoria {

    public final static int ESTADO_ASIGNADO = 1;
    public final static int ESTADO_ACEPTADO = 2;
    public final static int ESTADO_RECHAZADO = 3;
    public final static int ESTADO_APROBADO = 4;
    
    private Integer id;
    private int estado;
    private Boolean borrado;

    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private AuSeguimiento auSeguimiento;
    private CntPrestadorSede cntPrestadorSede;

    public AuSeguimientoPrestadorAsignado() {
    }

    public AuSeguimientoPrestadorAsignado(Integer id) {
        this.id = id;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public AuSeguimiento getAuSeguimiento() {
        return auSeguimiento;
    }

    public void setAuSeguimiento(AuSeguimiento auSeguimiento) {
        this.auSeguimiento = auSeguimiento;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    @Override
    public String toString() {
        return "AuSeguimientoPrestadorAsignado{" + "id=" + id + ", estado=" + estado + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + ", auSeguimiento=" + auSeguimiento + ", cntPrestadorSede=" + cntPrestadorSede + '}';
    }

}
