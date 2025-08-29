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
public class EntregaTecnologiaRespuestaDTO implements Serializable {

    private Integer autorizacion;
    private List<DetalleEntregaRespuestaDTO> detalleEntrega;

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Integer autorizacion) {
        this.autorizacion = autorizacion;
    }

    public List<DetalleEntregaRespuestaDTO> getDetalleEntrega() {
        return detalleEntrega;
    }

    public void setDetalleEntrega(List<DetalleEntregaRespuestaDTO> detalleEntrega) {
        this.detalleEntrega = detalleEntrega;
    }

}
