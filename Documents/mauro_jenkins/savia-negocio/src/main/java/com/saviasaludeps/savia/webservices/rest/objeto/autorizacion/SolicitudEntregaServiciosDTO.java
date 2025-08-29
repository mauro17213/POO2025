/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class SolicitudEntregaServiciosDTO implements Serializable {

    private String NUT;
    private Integer cantidadRegistros;
    private String fechaTransaccion;
    private List<EntregaTecnologiaDTO> entregaTecnologia;

    public String getNUT() {
        return NUT;
    }

    public void setNUT(String NUT) {
        this.NUT = NUT;
    }

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public List<EntregaTecnologiaDTO> getEntregaTecnologia() {
        return entregaTecnologia;
    }

    public void setEntregaTecnologia(List<EntregaTecnologiaDTO> entregaTecnologia) {
        this.entregaTecnologia = entregaTecnologia;
    }

}
