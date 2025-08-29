/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo3Tutela extends Auditoria{
    
    private Integer id;
    private int numeroTutela;
    private String estadoProceso;
    private boolean exoneracionCopago;
    private Date fechaFallo;
    private Date fechaNotificacion;
    private Date fechaVencimiento;
    private Boolean integralidad;
    private Boolean medidaProvisional;
    private String numeroFallo;
    private String radicadoJuzgado;
    private String fase;
    private AuAnexo3 auAnexo3Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(int numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public boolean isExoneracionCopago() {
        return exoneracionCopago;
    }

    public void setExoneracionCopago(boolean exoneracionCopago) {
        this.exoneracionCopago = exoneracionCopago;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getIntegralidad() {
        return integralidad;
    }

    public void setIntegralidad(Boolean integralidad) {
        this.integralidad = integralidad;
    }

    public Boolean getMedidaProvisional() {
        return medidaProvisional;
    }

    public void setMedidaProvisional(Boolean medidaProvisional) {
        this.medidaProvisional = medidaProvisional;
    }

    public String getNumeroFallo() {
        return numeroFallo;
    }

    public void setNumeroFallo(String numeroFallo) {
        this.numeroFallo = numeroFallo;
    }

    public String getRadicadoJuzgado() {
        return radicadoJuzgado;
    }

    public void setRadicadoJuzgado(String radicadoJuzgado) {
        this.radicadoJuzgado = radicadoJuzgado;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public AuAnexo3 getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(AuAnexo3 auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    @Override
    public String toString() {
        return "AuAnexo3Tutela{" + "id=" + id + ", numeroTutela=" + numeroTutela + ", estadoProceso=" + estadoProceso + ", exoneracionCopago=" + exoneracionCopago + ", fechaFallo=" + fechaFallo + ", fechaNotificacion=" + fechaNotificacion + ", fechaVencimiento=" + fechaVencimiento + ", integralidad=" + integralidad + ", medidaProvisional=" + medidaProvisional + ", numeroFallo=" + numeroFallo + ", radicadoJuzgado=" + radicadoJuzgado + ", fase=" + fase + '}';
    }
}
