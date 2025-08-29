/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jperezn
 */
public class CmFacturaEnCmAuditoriaAutorizacion extends Auditoria {

    private Integer idFactura;
    private String numerFactura;
    private int numeroAutorizacion;

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getNumerFactura() {
        return numerFactura;
    }

    public void setNumerFactura(String numerFactura) {
        this.numerFactura = numerFactura;
    }

    public int getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(int numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    @Override
    public String toString() {
        return "CmFacturaEnCmAuditoriaAutorizacion{" + "idFactura=" + idFactura + ", numerFactura=" + numerFactura + ", numeroAutorizacion=" + numeroAutorizacion + '}';
    }
    
    

}
