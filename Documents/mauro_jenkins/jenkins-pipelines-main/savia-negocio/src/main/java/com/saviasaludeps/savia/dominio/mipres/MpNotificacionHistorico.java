/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class MpNotificacionHistorico extends Auditoria {

    private Integer id;
    private int procedencia;
    private String mensaje;

    private MpPrescripcion mpPrescripcionId;
    private MpPrescripcionInsumo mpPrescripcionInsumoId;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentoId;
    private MpPrescripcionTecnologia mpPrescripcionTecnologiaId;

    public MpNotificacionHistorico() {
    }

    public MpNotificacionHistorico(Integer id) {
        this.id = id;
    }

    public MpNotificacionHistorico(Integer id, int procedencia, String mensaje, MpPrescripcion mpPrescripcionId, MpPrescripcionInsumo mpPrescripcionInsumoId, MpPrescripcionMedicamento mpPrescripcionMedicamentoId, MpPrescripcionTecnologia mpPrescripcionTecnologiaId) {
        this.id = id;
        this.procedencia = procedencia;
        this.mensaje = mensaje;
        this.mpPrescripcionId = mpPrescripcionId;
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(int procedencia) {
        this.procedencia = procedencia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public MpPrescripcion getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripcion mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public MpPrescripcionInsumo getMpPrescripcionInsumoId() {
        return mpPrescripcionInsumoId;
    }

    public void setMpPrescripcionInsumoId(MpPrescripcionInsumo mpPrescripcionInsumoId) {
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentoId() {
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamento mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    public MpPrescripcionTecnologia getMpPrescripcionTecnologiaId() {
        return mpPrescripcionTecnologiaId;
    }

    public void setMpPrescripcionTecnologiaId(MpPrescripcionTecnologia mpPrescripcionTecnologiaId) {
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
    }

    @Override
    public String toString() {
        return "MpNotificacionHistorico{" + "id=" + id + ", procedencia=" + procedencia + ", mensaje=" + mensaje + ", mpPrescripcionId=" + mpPrescripcionId + ", mpPrescripcionInsumoId=" + mpPrescripcionInsumoId + ", mpPrescripcionMedicamentoId=" + mpPrescripcionMedicamentoId + ", mpPrescripcionTecnologiaId=" + mpPrescripcionTecnologiaId + '}';
    }

}
