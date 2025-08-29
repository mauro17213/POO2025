/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

public class CmPagoFacturaRetencion extends Auditoria {

    public CmPagoFacturaRetencion() {
    }

    public CmPagoFacturaRetencion(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String codigo;
    private String descripcion;
    private BigDecimal valor;
    private CmPagoFactura cmPagoFacturasId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CmPagoFactura getCmPagoFacturasId() {
        return cmPagoFacturasId;
    }

    public void setCmPagoFacturasId(CmPagoFactura cmPagoFacturasId) {
        this.cmPagoFacturasId = cmPagoFacturasId;
    }
    
    
}
