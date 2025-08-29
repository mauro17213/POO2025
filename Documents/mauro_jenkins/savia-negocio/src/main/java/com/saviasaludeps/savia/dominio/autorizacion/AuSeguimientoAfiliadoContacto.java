/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author iavenegas
 */
public class AuSeguimientoAfiliadoContacto extends Auditoria {

    public final static String TIPO_CONTACTO_TELEFONO = "1";
    public final static String TIPO_CONTACTO_CELULAR = "2";

    private Integer id;
    private String numeroContacto;
    private Integer maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private String observacion;
    private Boolean activo;
    private Boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private AuSeguimientoAfiliado auSeguimientoAfiliado;

    public AuSeguimientoAfiliadoContacto() {
    }

    public AuSeguimientoAfiliadoContacto(Integer id) {
        this.id = id;
    }

    public AuSeguimientoAfiliadoContacto(String numeroContacto, Integer maeTipoContactoId, String maeTipoContactoCodigo, String maeTipoContactoValor, String observacion, Boolean activo) {
        this.numeroContacto = numeroContacto;
        this.maeTipoContactoId = maeTipoContactoId;
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
        this.maeTipoContactoValor = maeTipoContactoValor;
        this.observacion = observacion;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public Integer getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(Integer maeTipoContactoId) {
        this.maeTipoContactoId = maeTipoContactoId;
    }

    public String getMaeTipoContactoCodigo() {
        return maeTipoContactoCodigo;
    }

    public void setMaeTipoContactoCodigo(String maeTipoContactoCodigo) {
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
    }

    public String getMaeTipoContactoValor() {
        return maeTipoContactoValor;
    }

    public void setMaeTipoContactoValor(String maeTipoContactoValor) {
        this.maeTipoContactoValor = maeTipoContactoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public AuSeguimientoAfiliado getAuSeguimientoAfiliado() {
        return auSeguimientoAfiliado;
    }

    public void setAuSeguimientoAfiliado(AuSeguimientoAfiliado auSeguimientoAfiliado) {
        this.auSeguimientoAfiliado = auSeguimientoAfiliado;
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

    @Override
    public String toString() {
        return "AuSeguimientoAfiliadoContacto{" + "id=" + id + ", numeroContacto=" + numeroContacto + ", maeTipoContactoId=" + maeTipoContactoId + ", maeTipoContactoCodigo=" + maeTipoContactoCodigo + ", maeTipoContactoValor=" + maeTipoContactoValor + ", observacion=" + observacion + ", activo=" + activo + ", borrado=" + borrado + ", auSeguimientoAfiliado=" + auSeguimientoAfiliado + '}';
    }

}
