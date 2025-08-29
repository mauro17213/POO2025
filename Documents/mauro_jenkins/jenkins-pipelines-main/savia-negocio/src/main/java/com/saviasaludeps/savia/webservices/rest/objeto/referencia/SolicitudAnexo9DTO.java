/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.referencia;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class SolicitudAnexo9DTO implements Serializable {

    private int nut;
    private String fechaHoraTransaccion;
    private int cantidadRegistros;
    private String codHabilitacionPrestador;
    private List<ReferenciaAnexo9DTO> referencias;

    public String getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(String fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public List<ReferenciaAnexo9DTO> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<ReferenciaAnexo9DTO> referencias) {
        this.referencias = referencias;
    }

    public String getCodHabilitacionPrestador() {
        return codHabilitacionPrestador;
    }

    public void setCodHabilitacionPrestador(String codHabilitacionPrestador) {
        this.codHabilitacionPrestador = codHabilitacionPrestador;
    }

    public int getNut() {
        return nut;
    }

    public void setNut(int nut) {
        this.nut = nut;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
