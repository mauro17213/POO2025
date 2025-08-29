/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class IndicacionUNIRS implements Serializable {

    private Integer ConOrden;
    private Integer CodIndicacion;

    public Integer getConOrden() {
        return ConOrden;
    }

    public void setConOrden(Integer ConOrden) {
        this.ConOrden = ConOrden;
    }

    public Integer getCodIndicacion() {
        return CodIndicacion;
    }

    public void setCodIndicacion(Integer CodIndicacion) {
        this.CodIndicacion = CodIndicacion;
    }

}
