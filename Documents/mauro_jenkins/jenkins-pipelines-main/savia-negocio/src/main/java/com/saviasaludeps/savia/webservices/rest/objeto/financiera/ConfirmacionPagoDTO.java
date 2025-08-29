/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.financiera;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class ConfirmacionPagoDTO implements Serializable {

    private String consecutivo;
    private String nitProveedor;
    private String resultado;
    private String criterioResultado;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCriterioResultado() {
        return criterioResultado;
    }

    public void setCriterioResultado(String criterioResultado) {
        this.criterioResultado = criterioResultado;
    }

}
