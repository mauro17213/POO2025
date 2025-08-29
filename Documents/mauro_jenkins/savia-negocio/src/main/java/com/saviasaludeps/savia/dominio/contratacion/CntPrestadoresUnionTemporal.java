/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jose Perez
 */
public class CntPrestadoresUnionTemporal extends Auditoria {
    
    private Integer id;
    private Boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    
    private CntPrestador cntPrestadorUnionTemporal;
    private CntPrestador cntPrestador;
    

    public CntPrestadoresUnionTemporal() {
    }

    public CntPrestadoresUnionTemporal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the borrado
     */
    public Boolean getBorrado() {
        return borrado;
    }

    /**
     * @param borrado the borrado to set
     */
    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    /**
     * @return the usuarioBorra
     */
    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    /**
     * @param usuarioBorra the usuarioBorra to set
     */
    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    /**
     * @return the terminalBorra
     */
    public String getTerminalBorra() {
        return terminalBorra;
    }

    /**
     * @param terminalBorra the terminalBorra to set
     */
    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    /**
     * @return the fechaHoraBorra
     */
    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    /**
     * @param fechaHoraBorra the fechaHoraBorra to set
     */
    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    /**
     * @return the cntPrestadorUnionTemporal
     */
    public CntPrestador getCntPrestadorUnionTemporal() {
        return cntPrestadorUnionTemporal;
    }

    /**
     * @param cntPrestadorUnionTemporal the cntPrestadorUnionTemporal to set
     */
    public void setCntPrestadorUnionTemporal(CntPrestador cntPrestadorUnionTemporal) {
        this.cntPrestadorUnionTemporal = cntPrestadorUnionTemporal;
    }

    /**
     * @return the cntPrestador
     */
    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    /**
     * @param cntPrestador the cntPrestador to set
     */
    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    @Override
    public String toString() {
        return "CntPrestadoresUnionTemporal{" + "id=" + id + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + ", cntPrestadorUnionTemporal=" + cntPrestadorUnionTemporal + ", cntPrestador=" + cntPrestador + '}';
    }
}
