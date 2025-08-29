package com.saviasaludeps.savia.webservices.rest.objeto.mipres.direccionamiento;

public class ReporteSuministro {
    
    private Integer id;
    private Integer ultEntrega;
    private Integer entregaCompleta;
    private Integer causaNoEntrega;
    private Integer noPrescripcionAsociada;
    private Integer conTecAsociada;
    private Double cantTotEntregada;
    private Integer noLote;
    private Double valorEntregado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUltEntrega() {
        return ultEntrega;
    }

    public void setUltEntrega(Integer ultEntrega) {
        this.ultEntrega = ultEntrega;
    }

    public Integer getEntregaCompleta() {
        return entregaCompleta;
    }

    public void setEntregaCompleta(Integer entregaCompleta) {
        this.entregaCompleta = entregaCompleta;
    }

    public Integer getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(Integer causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public Integer getNoPrescripcionAsociada() {
        return noPrescripcionAsociada;
    }

    public void setNoPrescripcionAsociada(Integer noPrescripcionAsociada) {
        this.noPrescripcionAsociada = noPrescripcionAsociada;
    }

    public Integer getConTecAsociada() {
        return conTecAsociada;
    }

    public void setConTecAsociada(Integer conTecAsociada) {
        this.conTecAsociada = conTecAsociada;
    }

    public Double getCantTotEntregada() {
        return cantTotEntregada;
    }

    public void setCantTotEntregada(Double cantTotEntregada) {
        this.cantTotEntregada = cantTotEntregada;
    }

    public Integer getNoLote() {
        return noLote;
    }

    public void setNoLote(Integer noLote) {
        this.noLote = noLote;
    }

    public Double getValorEntregado() {
        return valorEntregado;
    }

    public void setValorEntregado(Double valorEntregado) {
        this.valorEntregado = valorEntregado;
    }
}
