/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CmPagoTransaccion extends Auditoria {

    public CmPagoTransaccion() {
    }

    public CmPagoTransaccion(Integer id) {
        this.id = id;
    }

    private Integer id;
    private int nut;
    private int facturas;
    private int paquete;
    private boolean finalizado;
    private Date fechaHoraCrea;
    private CmPago cmPagosId;
    private List<CmPagoFactura> cmPagoFacturasList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNut() {
        return nut;
    }

    public void setNut(int nut) {
        this.nut = nut;
    }

    public int getFacturas() {
        return facturas;
    }

    public void setFacturas(int facturas) {
        this.facturas = facturas;
    }

    public int getPaquete() {
        return paquete;
    }

    public void setPaquete(int paquete) {
        this.paquete = paquete;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public CmPago getCmPagosId() {
        return cmPagosId;
    }

    public void setCmPagosId(CmPago cmPagosId) {
        this.cmPagosId = cmPagosId;
    }

    public List<CmPagoFactura> getCmPagoFacturasList() {
        return cmPagoFacturasList;
    }

    public void setCmPagoFacturasList(List<CmPagoFactura> cmPagoFacturasList) {
        this.cmPagoFacturasList = cmPagoFacturasList;
    }
}
