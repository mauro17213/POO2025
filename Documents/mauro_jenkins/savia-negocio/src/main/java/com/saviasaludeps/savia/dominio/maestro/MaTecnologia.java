/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public class MaTecnologia extends Auditoria {

    private Integer id;
    private boolean activo;
    private Integer maeGrupoTecnologiaId;
    private String maeGrupoTecnologiaCodigo;
    private String maeGrupoTecnologiaValor;
    private String grupoDescripcion;
    private String cups;
    private String cupsDescipcion;
    private String codigoPropio;
    private String propioDescripcion;
    private Boolean aplicaSubsidiado;
    private Boolean aplicaContributivo;
    private int sexoAplica;
    private Integer maeCoberturaId;
    private String maeCoberturaCodigo;
    private String maeCoberturaValor;
    private MaIss2001Tarifario maIss2001Tarifario;
    private MaIss2000Tarifario maIss2000Tarifario;
    private MaSoatTarifario maSoatTarifario;
    private Integer nivelComplejidad;
    private Integer edadDesde;
    private Integer unidadDesde;
    private Integer edadHasta;
    private Integer unidadHasta;
    private String codigoFinanciador;
    private Integer frecuencia;
    private Integer tipoFrecuencia;
    //2021-04-23 jyperez nuevos campos
    private Integer tipoFrecuencia2;
    private Integer frecuencia2;
    private int cobertura;
    //2022-03-17 jyperez nuevo campo
    private Boolean eventoUnico;
    private String aclaracion;
    private String condicion;
    private String tipoPago;
    private List<MaTecnologiaGrupo> listaTecnologiaGrupos;
    //2022-03-22 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private boolean estadoInicialTecnologia;
    private String[] servicios;
    private String[] grupos;
    private Integer vigenciaAutorizacion;
    //2024-08-02 jyperez nuevos campos
    private List<MaTecnologiaMipres> listaTecnologiaMipres;

    public MaTecnologia() {
    }

    public MaTecnologia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return (activo) ? "SI" : "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeGrupoTecnologiaId() {
        return maeGrupoTecnologiaId;
    }

    public void setMaeGrupoTecnologiaId(Integer maeGrupoTecnologiaId) {
        this.maeGrupoTecnologiaId = maeGrupoTecnologiaId;
    }

    public String getMaeGrupoTecnologiaCodigo() {
        return maeGrupoTecnologiaCodigo;
    }

    public void setMaeGrupoTecnologiaCodigo(String maeGrupoTecnologiaCodigo) {
        this.maeGrupoTecnologiaCodigo = maeGrupoTecnologiaCodigo;
    }

    public String getMaeGrupoTecnologiaValor() {
        return maeGrupoTecnologiaValor;
    }

    public void setMaeGrupoTecnologiaValor(String maeGrupoTecnologiaValor) {
        this.maeGrupoTecnologiaValor = maeGrupoTecnologiaValor;
    }

    public String getGrupoDescripcion() {
        return grupoDescripcion;
    }

    public void setGrupoDescripcion(String grupoDescripcion) {
        this.grupoDescripcion = grupoDescripcion;
    }

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public String getCupsDescipcion() {
        return cupsDescipcion;
    }

    public void setCupsDescipcion(String cupsDescipcion) {
        this.cupsDescipcion = cupsDescipcion;
    }

    public String getCodigoPropio() {
        return codigoPropio;
    }

    public void setCodigoPropio(String codigoPropio) {
        this.codigoPropio = codigoPropio;
    }

    public String getPropioDescripcion() {
        return propioDescripcion;
    }

    public void setPropioDescripcion(String propioDescripcion) {
        this.propioDescripcion = propioDescripcion;
    }

    public Boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(Boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public Boolean getAplicaContributivo() {
        return aplicaContributivo;
    }

    public void setAplicaContributivo(Boolean aplicaContributivo) {
        this.aplicaContributivo = aplicaContributivo;
    }

    public int getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(int sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public MaIss2001Tarifario getMaIss2001Tarifario() {
        return maIss2001Tarifario;
    }

    public void setMaIss2001Tarifario(MaIss2001Tarifario maIss2001Tarifario) {
        this.maIss2001Tarifario = maIss2001Tarifario;
    }

    public MaIss2000Tarifario getMaIss2000Tarifario() {
        return maIss2000Tarifario;
    }

    public void setMaIss2000Tarifario(MaIss2000Tarifario maIss2000Tarifario) {
        this.maIss2000Tarifario = maIss2000Tarifario;
    }

    public MaSoatTarifario getMaSoatTarifario() {
        return maSoatTarifario;
    }

    public void setMaSoatTarifario(MaSoatTarifario maSoatTarifario) {
        this.maSoatTarifario = maSoatTarifario;
    }

    public Integer getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(Integer nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getUnidadDesde() {
        return unidadDesde;
    }

    public void setUnidadDesde(Integer unidadDesde) {
        this.unidadDesde = unidadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public Integer getUnidadHasta() {
        return unidadHasta;
    }

    public void setUnidadHasta(Integer unidadHasta) {
        this.unidadHasta = unidadHasta;
    }

    public String getCodigoFinanciador() {
        return codigoFinanciador;
    }

    public void setCodigoFinanciador(String codigoFinanciador) {
        this.codigoFinanciador = codigoFinanciador;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getCobertura() {
        return cobertura;
    }

    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
    }

    public Integer getVigenciaAutorizacion() {
        return vigenciaAutorizacion;
    }

    public void setVigenciaAutorizacion(Integer vigenciaAutorizacion) {
        this.vigenciaAutorizacion = vigenciaAutorizacion;
    }

    @Override
    public String toString() {
        return "MaTecnologia{" + "id=" + id + ", activo=" + activo + ", maeGrupoTecnologiaId=" + maeGrupoTecnologiaId + ", maeGrupoTecnologiaCodigo=" + maeGrupoTecnologiaCodigo + ", maeGrupoTecnologiaValor=" + maeGrupoTecnologiaValor + ", grupoDescripcion=" + grupoDescripcion + ", cups=" + cups + ", cupsDescipcion=" + cupsDescipcion + ", codigoPropio=" + codigoPropio + ", propioDescripcion=" + propioDescripcion + ", aplicaSubsidiado=" + aplicaSubsidiado + ", aplicaContributivo=" + aplicaContributivo + ", sexoAplica=" + sexoAplica + ", maIss2001Tarifario=" + maIss2001Tarifario + ", maIss2000Tarifario=" + maIss2000Tarifario + ", maSoatTarifario=" + maSoatTarifario + ", nivelComplejidad=" + nivelComplejidad + ", edadDesde=" + edadDesde + ", unidadDesde=" + unidadDesde + ", edadHasta=" + edadHasta + ", unidadHasta=" + unidadHasta + ", codigoFinanciador=" + codigoFinanciador + ", frecuencia=" + frecuencia + ", tipoFrecuencia=" + tipoFrecuencia + ", cobertura=" + cobertura + '}';
    }

    /**
     * @return the tipoFrecuencia
     */
    public Integer getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    /**
     * @param tipoFrecuencia the tipoFrecuencia to set
     */
    public void setTipoFrecuencia(Integer tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    /**
     * @return the listaTecnologiaGrupos
     */
    public List<MaTecnologiaGrupo> getListaTecnologiaGrupos() {
        return listaTecnologiaGrupos;
    }

    /**
     * @param listaTecnologiaGrupos the listaTecnologiaGrupos to set
     */
    public void setListaTecnologiaGrupos(List<MaTecnologiaGrupo> listaTecnologiaGrupos) {
        this.listaTecnologiaGrupos = listaTecnologiaGrupos;
    }

    /**
     * @return the maeCoberturaId
     */
    public Integer getMaeCoberturaId() {
        return maeCoberturaId;
    }

    /**
     * @param maeCoberturaId the maeCoberturaId to set
     */
    public void setMaeCoberturaId(Integer maeCoberturaId) {
        this.maeCoberturaId = maeCoberturaId;
    }

    /**
     * @return the maeCoberturaCodigo
     */
    public String getMaeCoberturaCodigo() {
        return maeCoberturaCodigo;
    }

    /**
     * @param maeCoberturaCodigo the maeCoberturaCodigo to set
     */
    public void setMaeCoberturaCodigo(String maeCoberturaCodigo) {
        this.maeCoberturaCodigo = maeCoberturaCodigo;
    }

    /**
     * @return the maeCoberturaValor
     */
    public String getMaeCoberturaValor() {
        return maeCoberturaValor;
    }

    /**
     * @param maeCoberturaValor the maeCoberturaValor to set
     */
    public void setMaeCoberturaValor(String maeCoberturaValor) {
        this.maeCoberturaValor = maeCoberturaValor;
    }

    /**
     * @return the tipoFrecuencia2
     */
    public Integer getTipoFrecuencia2() {
        return tipoFrecuencia2;
    }

    /**
     * @param tipoFrecuencia2 the tipoFrecuencia2 to set
     */
    public void setTipoFrecuencia2(Integer tipoFrecuencia2) {
        this.tipoFrecuencia2 = tipoFrecuencia2;
    }

    /**
     * @return the frecuencia2
     */
    public Integer getFrecuencia2() {
        return frecuencia2;
    }

    /**
     * @param frecuencia2 the frecuencia2 to set
     */
    public void setFrecuencia2(Integer frecuencia2) {
        this.frecuencia2 = frecuencia2;
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    /**
     * @return the estadoInicialTecnologia
     */
    public boolean isEstadoInicialTecnologia() {
        return estadoInicialTecnologia;
    }

    /**
     * @param estadoInicialTecnologia the estadoInicialTecnologia to set
     */
    public void setEstadoInicialTecnologia(boolean estadoInicialTecnologia) {
        this.estadoInicialTecnologia = estadoInicialTecnologia;
    }

    /**
     * @return the eventoUnico
     */
    public Boolean getEventoUnico() {
        return eventoUnico;
    }

    /**
     * @param eventoUnico the eventoUnico to set
     */
    public void setEventoUnico(Boolean eventoUnico) {
        this.eventoUnico = eventoUnico;
    }

    /**
     * @return the aclaracion
     */
    public String getAclaracion() {
        return aclaracion;
    }

    /**
     * @param aclaracion the aclaracion to set
     */
    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    /**
     * @return the condicion
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @return the servicios
     */
    public String[] getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the grupos
     */
    public String[] getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(String[] grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the listaTecnologiaMipres
     */
    public List<MaTecnologiaMipres> getListaTecnologiaMipres() {
        return listaTecnologiaMipres;
    }

    /**
     * @param listaTecnologiaMipres the listaTecnologiaMipres to set
     */
    public void setListaTecnologiaMipres(List<MaTecnologiaMipres> listaTecnologiaMipres) {
        this.listaTecnologiaMipres = listaTecnologiaMipres;
    }
}
