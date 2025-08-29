/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MpProgramadaEntrega extends Auditoria {
    
    private Integer id;
    private String idReporteEntrega;
    private BigDecimal cantidad;
    private Integer numeroEntrega;
    private boolean entregaTotal;
    private String causaNoEntrega;
    private int estado;
    private Date fechaEntrega;
    private Date fechaAnulacion;   
    private MpPrescripcion mpPrescripcion;
    private MpPrescripcionMedicamento medicamento;
    private MpPrescripcionInsumo insumo;
    private MpPrescripcionTecnologia tecnologia;

    public MpProgramadaEntrega() {
    }

    public MpProgramadaEntrega(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdReporteEntrega() {
        return idReporteEntrega;
    }

    public void setIdReporteEntrega(String idReporteEntrega) {
        this.idReporteEntrega = idReporteEntrega;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public boolean isEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(boolean entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public String getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(String causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
    }

    public MpPrescripcionMedicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MpPrescripcionMedicamento medicamento) {
        this.medicamento = medicamento;
    }

    public MpPrescripcionInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(MpPrescripcionInsumo insumo) {
        this.insumo = insumo;
    }

    public MpPrescripcionTecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(MpPrescripcionTecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }
 
    @Override
    public String toString() {
        return "MpProgramadaEntrega{" + "id=" + id + ", idReporteEntrega=" + idReporteEntrega + ", cantidad=" + cantidad + ", entregaTotal=" + entregaTotal + ", causaNoEntrega=" + causaNoEntrega + ", estado=" + estado + ", fechaEntrega=" + fechaEntrega + ", fechaAnulacion=" + fechaAnulacion + '}';
    }
    
}
