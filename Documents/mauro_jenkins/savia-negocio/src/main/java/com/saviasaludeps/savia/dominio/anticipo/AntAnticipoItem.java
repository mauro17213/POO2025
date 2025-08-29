/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author NEXOS
 */
public class AntAnticipoItem extends Auditoria{
   
    private Integer id;
    private AntAnticipo antAnticiposId;
    private int tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maMedicamentoId;
    private String maMedicamentoCodigo;
    private String maMedicamentoValor;
    private BigDecimal valorTecnologiaCotizada;
    private String mpNumeroPrescripcion;
    private BigDecimal valorTecnologiaPagada;
    private int cantidad;
    private boolean borrado;
    private String borradoObservacion;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
   
    public AntAnticipoItem() {
        
    }

    public AntAnticipoItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AntAnticipo getAntAnticiposId() {
        if(antAnticiposId == null){
            antAnticiposId = new AntAnticipo();
        }
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipo antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public BigDecimal getValorTecnologiaCotizada() {
        return valorTecnologiaCotizada;
    }

    public void setValorTecnologiaCotizada(BigDecimal valorTecnologiaCotizada) {
        this.valorTecnologiaCotizada = valorTecnologiaCotizada;
    }

    public String getMpNumeroPrescripcion() {
        return mpNumeroPrescripcion;
    }

    public void setMpNumeroPrescripcion(String mpNumeroPrescripcion) {
        this.mpNumeroPrescripcion = mpNumeroPrescripcion;
    }

    public BigDecimal getValorTecnologiaPagada() {
        return valorTecnologiaPagada;
    }

    public void setValorTecnologiaPagada(BigDecimal valorTecnologiaPagada) {
        this.valorTecnologiaPagada = valorTecnologiaPagada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }
    
    //Metodo auxiliar
    public String getTipoTecnologiaStr() {
        String tipo = "";
        switch (tipoTecnologia) {
            case 1:
                tipo = "Procedimiento";
                break;
            case 2:
                tipo = "Medicamento";
                break;
            case 3:
                tipo = "Insumo";
                break;
            case 4:
                tipo = "Paquete";
                break;
        }
        return tipo;
    }

    @Override
    public String toString() {
        return "AntAnticipoItem{" + "id=" + id + ", antAnticiposId=" + antAnticiposId + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maMedicamentoId=" + maMedicamentoId + ", maMedicamentoCodigo=" + maMedicamentoCodigo + ", maMedicamentoValor=" + maMedicamentoValor + ", valorTecnologiaCotizada=" + valorTecnologiaCotizada + ", mpNumeroPrescripcion=" + mpNumeroPrescripcion + ", valorTecnologiaPagada=" + valorTecnologiaPagada + ", cantidad=" + cantidad + ", borrado=" + borrado + ", borradoObservacion=" + borradoObservacion + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + '}';
    }
}
