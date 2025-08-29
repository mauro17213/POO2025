package com.saviasaludeps.savia.webservices.rest.objeto.autorizacion;

/**
 *
 * @author LFRIVERA
 */
public class EntregaDTO {

    // Campos para entrega
    private String numeroEntrega;
    private String cantidadEntregada;
    private String reclamaAfiliado;
    private String nombreReclama;
    private String celularReclama;
    private String causaNoEntrega;
    private String fechaEntrega;

    public String getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(String numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public String getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(String cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public String getReclamaAfiliado() {
        return reclamaAfiliado;
    }

    public void setReclamaAfiliado(String reclamaAfiliado) {
        this.reclamaAfiliado = reclamaAfiliado;
    }

    public String getNombreReclama() {
        return nombreReclama;
    }

    public void setNombreReclama(String nombreReclama) {
        this.nombreReclama = nombreReclama;
    }

    public String getCelularReclama() {
        return celularReclama;
    }

    public void setCelularReclama(String celularReclama) {
        this.celularReclama = celularReclama;
    }

    public String getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(String causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
