/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author iavenegas
 */
public class AuSeguimientoServicio extends Auditoria {

    public static final String SEGUIMIENTO_OXIGENO = "1";
    public static final String SEGUIMIENTO_CPAP = "2";
    public static final String SEGUIMIENTO_ASPIRADOR = "3";
    public static final String SEGUIMIENTO_ASISTENTE_TOS = "4";
    public static final String SEGUIMIENTO_OTRO = "5";
    public static final String SEGUIMIENTO_INSUMO = "6";
    public static final String SEGUIMIENTO_BPAP = "7";
    public static final String SEGUIMIENTO_NEBULIZADOR = "8";

    private Integer id;
    private Integer maeSeguimientoServicioId;
    private String maeSeguimientoServicioCodigo;
    private String maeSeguimientoServicioValor;
    private BigDecimal litros;
    private Integer tiempoUso;
    private Integer tiempoDuracionTratamiento;
    private Boolean gasesArteriales;
    private Integer presion;
    private Integer rampa;
    private Integer maeTipoMascaraId;
    private String maeTipoMascaraCodigo;
    private String maeTipoMascaraValor;
    private Integer maeTallaMascaraId;
    private String maeTallaMascaraCodigo;
    private String maeTallaMascaraValor;
    private Integer maeTipoSondaId;
    private String maeTipoSondaCodigo;
    private String maeTipoSondaValor;
    private Integer maeTipoInsumoId;
    private String maeTipoInsumoCodigo;
    private String maeTipoInsumoValor;
    private Integer presiones;
    private String tipoMascaraTos;
    private String observaciones;
    private Boolean borrado;
    private String usuarioBorra;
    private AuSeguimiento auSeguimientoId;

    public AuSeguimientoServicio() {
    }

    public AuSeguimientoServicio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeSeguimientoServicioId() {
        return maeSeguimientoServicioId;
    }

    public void setMaeSeguimientoServicioId(Integer maeSeguimientoServicioId) {
        this.maeSeguimientoServicioId = maeSeguimientoServicioId;
    }

    public String getMaeSeguimientoServicioCodigo() {
        return maeSeguimientoServicioCodigo;
    }

    public void setMaeSeguimientoServicioCodigo(String maeSeguimientoServicioCodigo) {
        this.maeSeguimientoServicioCodigo = maeSeguimientoServicioCodigo;
    }

    public String getMaeSeguimientoServicioValor() {
        return maeSeguimientoServicioValor;
    }

    public void setMaeSeguimientoServicioValor(String maeSeguimientoServicioValor) {
        this.maeSeguimientoServicioValor = maeSeguimientoServicioValor;
    }

    public BigDecimal getLitros() {
        return litros;
    }

    public void setLitros(BigDecimal litros) {
        this.litros = litros;
    }

    public Integer getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(Integer tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public Integer getTiempoDuracionTratamiento() {
        return tiempoDuracionTratamiento;
    }

    public void setTiempoDuracionTratamiento(Integer tiempoDuracionTratamiento) {
        this.tiempoDuracionTratamiento = tiempoDuracionTratamiento;
    }

    public Boolean getGasesArteriales() {
        return gasesArteriales;
    }

    public void setGasesArteriales(Boolean gasesArteriales) {
        this.gasesArteriales = gasesArteriales;
    }

    public Integer getPresion() {
        return presion;
    }

    public void setPresion(Integer presion) {
        this.presion = presion;
    }

    public Integer getRampa() {
        return rampa;
    }

    public void setRampa(Integer rampa) {
        this.rampa = rampa;
    }

    public Integer getMaeTipoMascaraId() {
        return maeTipoMascaraId;
    }

    public void setMaeTipoMascaraId(Integer maeTipoMascaraId) {
        this.maeTipoMascaraId = maeTipoMascaraId;
    }

    public String getMaeTipoMascaraCodigo() {
        return maeTipoMascaraCodigo;
    }

    public void setMaeTipoMascaraCodigo(String maeTipoMascaraCodigo) {
        this.maeTipoMascaraCodigo = maeTipoMascaraCodigo;
    }

    public String getMaeTipoMascaraValor() {
        return maeTipoMascaraValor;
    }

    public void setMaeTipoMascaraValor(String maeTipoMascaraValor) {
        this.maeTipoMascaraValor = maeTipoMascaraValor;
    }

    public Integer getMaeTallaMascaraId() {
        return maeTallaMascaraId;
    }

    public void setMaeTallaMascaraId(Integer maeTallaMascaraId) {
        this.maeTallaMascaraId = maeTallaMascaraId;
    }

    public String getMaeTallaMascaraCodigo() {
        return maeTallaMascaraCodigo;
    }

    public void setMaeTallaMascaraCodigo(String maeTallaMascaraCodigo) {
        this.maeTallaMascaraCodigo = maeTallaMascaraCodigo;
    }

    public String getMaeTallaMascaraValor() {
        return maeTallaMascaraValor;
    }

    public void setMaeTallaMascaraValor(String maeTallaMascaraValor) {
        this.maeTallaMascaraValor = maeTallaMascaraValor;
    }

    public Integer getMaeTipoSondaId() {
        return maeTipoSondaId;
    }

    public void setMaeTipoSondaId(Integer maeTipoSondaId) {
        this.maeTipoSondaId = maeTipoSondaId;
    }

    public String getMaeTipoSondaCodigo() {
        return maeTipoSondaCodigo;
    }

    public void setMaeTipoSondaCodigo(String maeTipoSondaCodigo) {
        this.maeTipoSondaCodigo = maeTipoSondaCodigo;
    }

    public String getMaeTipoSondaValor() {
        return maeTipoSondaValor;
    }

    public void setMaeTipoSondaValor(String maeTipoSondaValor) {
        this.maeTipoSondaValor = maeTipoSondaValor;
    }

    public Integer getPresiones() {
        return presiones;
    }

    public void setPresiones(Integer presiones) {
        this.presiones = presiones;
    }

    public String getTipoMascaraTos() {
        return tipoMascaraTos;
    }

    public void setTipoMascaraTos(String tipoMascaraTos) {
        this.tipoMascaraTos = tipoMascaraTos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public AuSeguimiento getAuSeguimientoId() {
        return auSeguimientoId;
    }

    public void setAuSeguimientoId(AuSeguimiento auSeguimientoId) {
        this.auSeguimientoId = auSeguimientoId;
    }

    public Integer getMaeTipoInsumoId() {
        return maeTipoInsumoId;
    }

    public void setMaeTipoInsumoId(Integer maeTipoInsumoId) {
        this.maeTipoInsumoId = maeTipoInsumoId;
    }

    public String getMaeTipoInsumoCodigo() {
        return maeTipoInsumoCodigo;
    }

    public void setMaeTipoInsumoCodigo(String maeTipoInsumoCodigo) {
        this.maeTipoInsumoCodigo = maeTipoInsumoCodigo;
    }

    public String getMaeTipoInsumoValor() {
        return maeTipoInsumoValor;
    }

    public void setMaeTipoInsumoValor(String maeTipoInsumoValor) {
        this.maeTipoInsumoValor = maeTipoInsumoValor;
    }

    @Override
    public String toString() {
        return "AuSeguimientoServicio{" + "id=" + id + ", maeSeguimientoServicioId=" + maeSeguimientoServicioId + ", maeSeguimientoServicioCodigo=" + maeSeguimientoServicioCodigo + ", maeSeguimientoServicioValor=" + maeSeguimientoServicioValor + ", litros=" + litros + ", tiempoUso=" + tiempoUso + ", tiempoDuracionTratamiento=" + tiempoDuracionTratamiento + ", gasesArteriales=" + gasesArteriales + ", presion=" + presion + ", rampa=" + rampa + ", maeTipoMascaraId=" + maeTipoMascaraId + ", maeTipoMascaraCodigo=" + maeTipoMascaraCodigo + ", maeTipoMascaraValor=" + maeTipoMascaraValor + ", maeTallaMascaraId=" + maeTallaMascaraId + ", maeTallaMascaraCodigo=" + maeTallaMascaraCodigo + ", maeTallaMascaraValor=" + maeTallaMascaraValor + ", maeTipoSondaId=" + maeTipoSondaId + ", maeTipoSondaCodigo=" + maeTipoSondaCodigo + ", maeTipoSondaValor=" + maeTipoSondaValor + ", maeTipoInsumoId=" + maeTipoInsumoId + ", maeTipoInsumoCodigo=" + maeTipoInsumoCodigo + ", maeTipoInsumoValor=" + maeTipoInsumoValor + ", presiones=" + presiones + ", tipoMascaraTos=" + tipoMascaraTos + ", observaciones=" + observaciones + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", "
                + "auSeguimiento=" + (auSeguimientoId == null ? null : auSeguimientoId.getId()) + '}';
    }

}
