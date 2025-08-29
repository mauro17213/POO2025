/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class MpPrescripcionAuditoria extends Auditoria {

    private Integer id;
    private String notaAuditoria;
    private Integer estado;
    private MpPrescripcionMedicamento mpMedicamentoId;
    private MpPrescripcion mpPrescripcionId;
    private MpPrescripcionInsumo mpInsumoId;
    private MpPrescripcionTecnologia mpTecnologiaId;

    private Integer idAfiliado;
    private String documentoPrestador;

    public MpPrescripcionAuditoria() {
    }

    public MpPrescripcionAuditoria(Integer id, String notaAuditoria, Integer estado, MpPrescripcionMedicamento mpMedicamentoId, MpPrescripcion mpPrescripcionId, MpPrescripcionInsumo mpInsumoId, MpPrescripcionTecnologia mpTecnologiaId) {
        this.id = id;
        this.notaAuditoria = notaAuditoria;
        this.estado = estado;
        this.mpMedicamentoId = mpMedicamentoId;
        this.mpPrescripcionId = mpPrescripcionId;
        this.mpInsumoId = mpInsumoId;
        this.mpTecnologiaId = mpTecnologiaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotaAuditoria() {
        return notaAuditoria;
    }

    public void setNotaAuditoria(String notaAuditoria) {
        this.notaAuditoria = notaAuditoria;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public MpPrescripcionMedicamento getMpMedicamentoId() {
        return mpMedicamentoId;
    }

    public void setMpMedicamentoId(MpPrescripcionMedicamento mpMedicamentoId) {
        this.mpMedicamentoId = mpMedicamentoId;
    }

    public MpPrescripcion getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripcion mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public MpPrescripcionInsumo getMpInsumoId() {
        return mpInsumoId;
    }

    public void setMpInsumoId(MpPrescripcionInsumo mpInsumoId) {
        this.mpInsumoId = mpInsumoId;
    }

    public MpPrescripcionTecnologia getMpTecnologiaId() {
        return mpTecnologiaId;
    }

    public void setMpTecnologiaId(MpPrescripcionTecnologia mpTecnologiaId) {
        this.mpTecnologiaId = mpTecnologiaId;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getDocumentoPrestador() {
        return documentoPrestador;
    }

    public void setDocumentoPrestador(String documentoPrestador) {
        this.documentoPrestador = documentoPrestador;
    }

    @Override
    public String toString() {
        return "MpPrescripcionAuditoria{" + "id=" + id + ", notaAuditoria=" + notaAuditoria + ", estado=" + estado + ", mpMedicamentoId=" + mpMedicamentoId + ", mpPrescripcionId=" + mpPrescripcionId + ", mpInsumoId=" + mpInsumoId + ", mpTecnologiaId=" + mpTecnologiaId + '}';
    }

}
