/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.reporteentrega;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class ReporteEntrega implements Serializable {

    private String ID;
    private String IDReporteEntrega;
    private String NoPrescripcion;
    private String TipoTec;
    private String ConTec;
    private String TipoIDPaciente;
    private String NoIDPaciente;
    private String NoEntrega;
    private String EstadoEntrega;
    private String CausaNoEntrega;
    private String ValorEntregado;
    private String CodTecEntregado;
    private String CantTotEntregada;
    private String NoLote;
    private String FecEntrega;
    private String FecRepEntrega;
    private String EstRepEntrega;
    private String FecAnulacion;
    private MpConsumoFallo fallo;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDReporteEntrega() {
        return IDReporteEntrega;
    }

    public void setIDReporteEntrega(String IDReporteEntrega) {
        this.IDReporteEntrega = IDReporteEntrega;
    }

    public String getNoPrescripcion() {
        return NoPrescripcion;
    }

    public void setNoPrescripcion(String NoPrescripcion) {
        this.NoPrescripcion = NoPrescripcion;
    }

    public String getTipoTec() {
        return TipoTec;
    }

    public void setTipoTec(String TipoTec) {
        this.TipoTec = TipoTec;
    }

    public String getConTec() {
        return ConTec;
    }

    public void setConTec(String ConTec) {
        this.ConTec = ConTec;
    }

    public String getTipoIDPaciente() {
        return TipoIDPaciente;
    }

    public void setTipoIDPaciente(String TipoIDPaciente) {
        this.TipoIDPaciente = TipoIDPaciente;
    }

    public String getNoIDPaciente() {
        return NoIDPaciente;
    }

    public void setNoIDPaciente(String NoIDPaciente) {
        this.NoIDPaciente = NoIDPaciente;
    }

    public String getNoEntrega() {
        return NoEntrega;
    }

    public void setNoEntrega(String NoEntrega) {
        this.NoEntrega = NoEntrega;
    }

    public String getEstadoEntrega() {
        return EstadoEntrega;
    }

    public void setEstadoEntrega(String EstadoEntrega) {
        this.EstadoEntrega = EstadoEntrega;
    }

    public String getCausaNoEntrega() {
        return CausaNoEntrega;
    }

    public void setCausaNoEntrega(String CausaNoEntrega) {
        this.CausaNoEntrega = CausaNoEntrega;
    }

    public String getValorEntregado() {
        return ValorEntregado;
    }

    public void setValorEntregado(String ValorEntregado) {
        this.ValorEntregado = ValorEntregado;
    }

    public String getCodTecEntregado() {
        return CodTecEntregado;
    }

    public void setCodTecEntregado(String CodTecEntregado) {
        this.CodTecEntregado = CodTecEntregado;
    }

    public String getCantTotEntregada() {
        return CantTotEntregada;
    }

    public void setCantTotEntregada(String CantTotEntregada) {
        this.CantTotEntregada = CantTotEntregada;
    }

    public String getNoLote() {
        return NoLote;
    }

    public void setNoLote(String NoLote) {
        this.NoLote = NoLote;
    }

    public String getFecEntrega() {
        return FecEntrega;
    }

    public void setFecEntrega(String FecEntrega) {
        this.FecEntrega = FecEntrega;
    }

    public String getFecRepEntrega() {
        return FecRepEntrega;
    }

    public void setFecRepEntrega(String FecRepEntrega) {
        this.FecRepEntrega = FecRepEntrega;
    }

    public String getEstRepEntrega() {
        return EstRepEntrega;
    }

    public void setEstRepEntrega(String EstRepEntrega) {
        this.EstRepEntrega = EstRepEntrega;
    }

    public String getFecAnulacion() {
        return FecAnulacion;
    }

    public void setFecAnulacion(String FecAnulacion) {
        this.FecAnulacion = FecAnulacion;
    }

    public MpConsumoFallo getFallo() {
        return fallo;
    }

    public void setFallo(MpConsumoFallo fallo) {
        this.fallo = fallo;
    }

}
