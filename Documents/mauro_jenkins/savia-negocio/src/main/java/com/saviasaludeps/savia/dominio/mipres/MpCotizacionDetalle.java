/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Bsgomez
 */
public class MpCotizacionDetalle extends Auditoria {

    private Integer id;
    private Integer idItem;
    private Integer idCotizacion;
    private Integer idPrescripcion;
    private Integer tipoTecnologia;
    private String nombreTipoTecnologia;
    private String numeroPrescripcion;
    private String nombreTecnologia;
    private String nombreTipoEstado;
    private Integer estado;

    public MpCotizacionDetalle() {
    }

    public MpCotizacionDetalle(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Integer idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Integer getIdPrescripcion() {
        return idPrescripcion;
    }

    public void setIdPrescripcion(Integer idPrescripcion) {
        this.idPrescripcion = idPrescripcion;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getNombreTipoTecnologia() {
        return nombreTipoTecnologia;
    }

    public void setNombreTipoTecnologia(String nombreTipoTecnologia) {
        this.nombreTipoTecnologia = nombreTipoTecnologia;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getNombreTipoEstado() {
        return nombreTipoEstado;
    }

    public void setNombreTipoEstado(String nombreTipoEstado) {
        this.nombreTipoEstado = nombreTipoEstado;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "MpCotizacionDetalle{" + "id=" + id + ", idItem=" + idItem + ", idCotizacion=" + idCotizacion + ", idPrescripcion=" + idPrescripcion + ", tipoTecnologia=" + tipoTecnologia + ", nombreTipoTecnologia=" + nombreTipoTecnologia + ", numeroPrescripcion=" + numeroPrescripcion + ", nombreTecnologia=" + nombreTecnologia + ", nombreTipoEstado=" + nombreTipoEstado + ", estado=" + estado + '}';
    }

}
