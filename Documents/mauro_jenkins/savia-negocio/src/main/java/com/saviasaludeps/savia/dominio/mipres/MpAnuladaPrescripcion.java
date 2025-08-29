/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class MpAnuladaPrescripcion extends Auditoria {

    private Integer id;
    private String numeroPrescripcion;
    private Integer estado;
    private Integer tipo;
    private String justificacion;
    private String observacion;
    private String usuarioSolicita;
    private Date fechaHoraSolicitud;
    private String usuarioAnula;
    private Date fechaHoraAnulacion;

    private MpPrescripcion mpPrescripcionId;

    public MpAnuladaPrescripcion() {
    }

    public MpAnuladaPrescripcion(Integer id) {
        this.id = id;
    }

    public MpAnuladaPrescripcion(Integer id, Integer estado, Integer tipo, String numeroPrescripcion, String justificacion, String observacion, String usuarioSolicita, Date fechaHoraSolicitud, String usuarioAnula, Date fechaHoraAnulacion, MpPrescripcion mpPrescripcionId) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.numeroPrescripcion = numeroPrescripcion;
        this.justificacion = justificacion;
        this.observacion = observacion;
        this.usuarioSolicita = usuarioSolicita;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.usuarioAnula = usuarioAnula;
        this.fechaHoraAnulacion = fechaHoraAnulacion;
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuarioSolicita() {
        return usuarioSolicita;
    }

    public void setUsuarioSolicita(String usuarioSolicita) {
        this.usuarioSolicita = usuarioSolicita;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public String getUsuarioAnula() {
        return usuarioAnula;
    }

    public void setUsuarioAnula(String usuarioAnula) {
        this.usuarioAnula = usuarioAnula;
    }

    public Date getFechaHoraAnulacion() {
        return fechaHoraAnulacion;
    }

    public void setFechaHoraAnulacion(Date fechaHoraAnulacion) {
        this.fechaHoraAnulacion = fechaHoraAnulacion;
    }

    public MpPrescripcion getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripcion mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    @Override
    public String toString() {
        return "MpAnuladaPrescripcion{" + "id=" + id + ", estado=" + estado + ", tipo=" + tipo + ", numeroPrescripcion=" + numeroPrescripcion + ", justificacion=" + justificacion + ", observacion=" + observacion + ", usuarioSolicita=" + usuarioSolicita + ", fechaHoraSolicitud=" + fechaHoraSolicitud + ", usuarioAnula=" + usuarioAnula + ", fechaHoraAnulacion=" + fechaHoraAnulacion + ", mpPrescripcionId=" + mpPrescripcionId + '}';
    }

}
