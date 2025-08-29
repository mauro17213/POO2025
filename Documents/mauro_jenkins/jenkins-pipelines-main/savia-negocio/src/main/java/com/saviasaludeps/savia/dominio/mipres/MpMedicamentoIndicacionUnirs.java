/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class MpMedicamentoIndicacionUnirs extends Auditoria {

    private Integer id;
    private int consecutivoOrden;
    private String codigoIndicacion;
    private MpCodigoUnirss mpCodigoUnirsId;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentoId;

    public MpMedicamentoIndicacionUnirs() {
    }

    public MpMedicamentoIndicacionUnirs(Integer id) {
        this.id = id;
    }

    public MpMedicamentoIndicacionUnirs(Integer id, int consecutivoOrden, String codigoIndicacion, MpCodigoUnirss mpCodigoUnirsId, MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.id = id;
        this.consecutivoOrden = consecutivoOrden;
        this.codigoIndicacion = codigoIndicacion;
        this.mpCodigoUnirsId = mpCodigoUnirsId;
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public String getCodigoIndicacion() {
        return codigoIndicacion;
    }

    public void setCodigoIndicacion(String codigoIndicacion) {
        this.codigoIndicacion = codigoIndicacion;
    }

    public MpCodigoUnirss getMpCodigoUnirsId() {
        return mpCodigoUnirsId;
    }

    public void setMpCodigoUnirsId(MpCodigoUnirss mpCodigoUnirsId) {
        this.mpCodigoUnirsId = mpCodigoUnirsId;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentoId() {
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    @Override
    public String toString() {
        return "MpMedicamentoIndicacionUnirs{" + "id=" + id + ", consecutivoOrden=" + consecutivoOrden + ", codigoIndicacion=" + codigoIndicacion + ", mpCodigoUnirsId=" + mpCodigoUnirsId + ", mpPrescripcionMedicamentoId=" + mpPrescripcionMedicamentoId + '}';
    }

}
