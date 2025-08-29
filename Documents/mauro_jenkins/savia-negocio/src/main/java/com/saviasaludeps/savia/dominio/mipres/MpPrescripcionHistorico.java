/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MpPrescripcionHistorico extends Auditoria {

    private Integer id;
    private int tipoTecnologia;
    private int idPrescripcionTecnologia;
    private int estado;
    private MpPrescripcion mpPrescripcion;

    public MpPrescripcionHistorico() {
    }

    public MpPrescripcionHistorico(Integer id) {
        this.id = id;
    }

    public MpPrescripcionHistorico(Integer id, int tipoTecnologia, int idPrescripcionTecnologia, int estado, MpPrescripcion mpPrescripcion) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.idPrescripcionTecnologia = idPrescripcionTecnologia;
        this.estado = estado;
        this.mpPrescripcion = mpPrescripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getIdPrescripcionTecnologia() {
        return idPrescripcionTecnologia;
    }

    public void setIdPrescripcionTecnologia(int idPrescripcionTecnologia) {
        this.idPrescripcionTecnologia = idPrescripcionTecnologia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
    }

    @Override
    public String toString() {
        return "MpPrescripcionHistorico{" + "id=" + id + ", tipoTecnologia=" + tipoTecnologia + ", idPrescripcionTecnologia=" + idPrescripcionTecnologia + ", estado=" + estado + ", mpPrescripcion=" + mpPrescripcion + '}';
    }

}
