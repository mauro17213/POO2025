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
public class SolicitudConfirmacionPagoDTO implements Serializable {

    private String consecutivo;
    private String NITProveedor;
    private String tipoDocumento;
    private String numeroDocumento;
    private String numeroAutorizacion;
    private String fechaPago;
    private String valorPago;
    private String valorCompesacionAnticipo;

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getNITProveedor() {
        return NITProveedor;
    }

    public void setNITProveedor(String NITProveedor) {
        this.NITProveedor = NITProveedor;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

    public String getValorCompesacionAnticipo() {
        return valorCompesacionAnticipo;
    }

    public void setValorCompesacionAnticipo(String valorCompesacionAnticipo) {
        this.valorCompesacionAnticipo = valorCompesacionAnticipo;
    }

}
