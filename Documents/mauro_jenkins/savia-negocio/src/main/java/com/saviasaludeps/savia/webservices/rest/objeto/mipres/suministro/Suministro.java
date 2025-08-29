/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.suministro;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;

/**
 *
 * @author yjimenez
 */
public class Suministro implements Serializable {

    private Integer ID;
    private Integer IDSuministro;
    private String NoPrescripcion;
    private String TipoTec;
    private Integer ConTec;
    private String TipoIDPaciente;
    private String NoIDPaciente;
    private Integer NoEntrega;
    private Integer UltEntrega;
    private Integer EntregaCompleta;
    private String CausaNoEntrega;
    private String NoPrescripcionAsociada;
    private Integer ConTecAsociada;
    private String CantTotEntregada;
    private String NoLote;
    private Double ValorEntregado;
    private String FecSuministro;
    private Integer EstSuministro;
    private String FecAnulacion;
    private MpConsumoFallo fallo;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDSuministro() {
        return IDSuministro;
    }

    public void setIDSuministro(Integer IDSuministro) {
        this.IDSuministro = IDSuministro;
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

    public Integer getConTec() {
        return ConTec;
    }

    public void setConTec(Integer ConTec) {
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

    public Integer getNoEntrega() {
        return NoEntrega;
    }

    public void setNoEntrega(Integer NoEntrega) {
        this.NoEntrega = NoEntrega;
    }

    public Integer getUltEntrega() {
        return UltEntrega;
    }

    public void setUltEntrega(Integer UltEntrega) {
        this.UltEntrega = UltEntrega;
    }

    public Integer getEntregaCompleta() {
        return EntregaCompleta;
    }

    public void setEntregaCompleta(Integer EntregaCompleta) {
        this.EntregaCompleta = EntregaCompleta;
    }

    public String getCausaNoEntrega() {
        return CausaNoEntrega;
    }

    public void setCausaNoEntrega(String CausaNoEntrega) {
        this.CausaNoEntrega = CausaNoEntrega;
    }

    public String getNoPrescripcionAsociada() {
        return NoPrescripcionAsociada;
    }

    public void setNoPrescripcionAsociada(String NoPrescripcionAsociada) {
        this.NoPrescripcionAsociada = NoPrescripcionAsociada;
    }

    public Integer getConTecAsociada() {
        return ConTecAsociada;
    }

    public void setConTecAsociada(Integer ConTecAsociada) {
        this.ConTecAsociada = ConTecAsociada;
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

    public Double getValorEntregado() {
        return ValorEntregado;
    }

    public void setValorEntregado(Double ValorEntregado) {
        this.ValorEntregado = ValorEntregado;
    }

    public String getFecSuministro() {
        return FecSuministro;
    }

    public void setFecSuministro(String FecSuministro) {
        this.FecSuministro = FecSuministro;
    }

    public Integer getEstSuministro() {
        return EstSuministro;
    }

    public void setEstSuministro(Integer EstSuministro) {
        this.EstSuministro = EstSuministro;
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
