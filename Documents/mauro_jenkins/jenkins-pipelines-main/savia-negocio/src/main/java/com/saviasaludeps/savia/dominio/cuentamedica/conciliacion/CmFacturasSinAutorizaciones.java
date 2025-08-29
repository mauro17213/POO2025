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
public class CmFacturasSinAutorizaciones extends Auditoria implements Cloneable {

    int numeroRadicado;
    String documentoNombreCliente;
    String numeroFactuado;


    public CmFacturasSinAutorizaciones() {
        
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getDocumentoNombreCliente() {
        return documentoNombreCliente;
    }

    public void setDocumentoNombreCliente(String documentoNombreCliente) {
        this.documentoNombreCliente = documentoNombreCliente;
    }

    public String getNumeroFactuado() {
        return numeroFactuado;
    }

    public void setNumeroFactuado(String numeroFactuado) {
        this.numeroFactuado = numeroFactuado;
    }

}
