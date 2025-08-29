/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CmSincronizacionEncabezadoResumen extends Auditoria {

 
    private BigDecimal valorGlosado;    
    private BigDecimal valorDocumento;
    private BigDecimal valorPagado;
    private String tipoEstadoStr;
    
    public CmSincronizacionEncabezadoResumen() {
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public BigDecimal getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public String getTipoEstadoStr() {
        return tipoEstadoStr;
    }

    public void setTipoEstadoStr(String tipoEstadoStr) {
        this.tipoEstadoStr = tipoEstadoStr;
    }
    
}
