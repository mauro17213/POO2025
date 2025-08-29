/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.facturacionelectronica;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author stive
 */

public class CmFeRipsFactura extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private CmFactura cmFactura;
    private CmFeRipsCarga cmFeRipsCarga;
    private List<CmFeNota> listaNotas;

    public CmFeRipsFactura() {
    }

    public CmFeRipsFactura(Integer id) {
        this.id = id;
    }

    public CmFeRipsFactura(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public CmFeRipsCarga getCmFeRipsCarga() {
        return cmFeRipsCarga;
    }

    public void setCmFeRipsCarga(CmFeRipsCarga cmFeRipsCarga) {
        this.cmFeRipsCarga = cmFeRipsCarga;
    }

    public List<CmFeNota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<CmFeNota> listaNotas) {
        this.listaNotas = listaNotas;
    }


    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeRipsFacturas[ id=" + id + " ]";
    }
    
}
