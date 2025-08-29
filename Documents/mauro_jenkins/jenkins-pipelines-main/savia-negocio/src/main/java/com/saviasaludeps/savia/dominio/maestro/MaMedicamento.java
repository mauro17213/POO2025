/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaMedicamento extends Auditoria {
    
    private Integer id;
    private boolean activo;
    private String cum;
    private String descripcionInvima;
    private String descripcionEstandarizada;
    private Integer maeCoberturaId;
    private String maeCoberturaCodigo;
    private String maeCoberturaValor;
    private Integer maeConcentracionId;
    private String maeConcentracionCodigo;
    private String maeConcentracionValor;
    private Integer maePrincipioActivoId;
    private String maePrincipioActivoCodigo;
    private String maePrincipioActivoValor;
    private Integer maeFormaFarmaceuticaId;
    private String maeFormaFarmaceuticaCodigo;
    private String maeFormaFarmaceuticaValor;
    private Integer maeTipoPpmId;
    private String maeTipoPpmCodigo;
    private String maeTipoPpmValor;
    private Boolean esAltoCosto;
    private Boolean esCapitado;
    private Boolean aplicaSubsidiado;
    private Boolean aplicaContributivo;
    private Integer sexoAplica;
    private String codigoIum;
    private Integer edadDesde;
    private Integer edadHasta;
    private Boolean esRegulado;
    private BigDecimal valorMaximoRegulado;
    private BigDecimal valorReferenteMinimo;
    private BigDecimal valorReferete;
    private String codigoFinanciador;
    private Integer idViejo;
    //2020-12-23 jyperez nuevo campo
    private int cobertura;
    //2021-06-16 Sprint 1 nuevos campos
    private String descripcionLargaEstandarizada;
    private String expediente;
    private String nombreComercial;
    private String laboratorio;
    private String registroSanitario;
    private Date fechaExpedicion;
    private Date fechaVencimiento;
    //private Integer estadoRegistroSanitario;
    private Integer cantidadCum;
    private Date fechaActivo;
    private Date fechaInactivo;
    private Integer maeAtc1Id;
    private String maeAtc1Codigo;
    private String maeAtc1Valor;
    private Integer maeAtc2Id;
    private String maeAtc2Codigo;
    private String maeAtc2Valor;
    private Integer maeAtc3Id;
    private String maeAtc3Codigo;
    private String maeAtc3Valor;
    //private String viaAdministracion;
    private Boolean mce;
    private Boolean monopolioEstado;
    private Boolean estrechoMargenTerapeutico;
    private String aclaracion;
    private String normaRegulacion;
    private BigDecimal valorFraccion;
    private BigDecimal valorPresentacionComercial;
    private Integer cantidad;
    private Integer diasTratamiento;
    private Integer maeGrupoAnatomicoPpalId;
    private String maeGrupoAnatomicoPpalCodigo;
    private String maeGrupoAnatomicoPpalValor;
    private Integer maeGrupoTerapeuticoPpalId;
    private String maeGrupoTerapeuticoPpalCodigo;
    private String maeGrupoTerapeuticoPpalValor;
    //private String grupoTerapeuticoPrincipal;
    private String dci;
    private String descripcionDci;
    private String provenienteInvima;
    private String agrupadorCondicionPbs;
    private BigDecimal valorComercial;
    private Boolean listaUnirs;
    private String codigoSuficienciaUpc;
    private String cantidadConcentracionSufUpc;
    private String unidadConcentracionSufUpc;
    private String unidadMedidaSuficiencia;
    private Boolean noPbsMenorValor;
    private Boolean muestraMedica;
    //private String maMedicamentoAdicionalCamposcol;
    //2021-06-28 jyperez nuevos campos - cambio estado registro sanitario
    private Integer maeEstadoRegistroSanitarioId;
    private String maeEstadoRegistroSanitarioCodigo;
    private String maeEstadoRegistroSanitarioValor;
    //2025-05-23 jyperez nuevo campo
    private String formaFarmaceutica;
    
    private MaAgrupadorMedicamento maAgrupadorMedicamento;
    
    //20219-09-29 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private boolean estadoInicialTecnologia;

    public MaMedicamento() {
    }

    
    
    public MaMedicamento(Integer id) {
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
        return (activo)?"SI":"NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCum() {
        return cum;
    }

    public void setCum(String cum) {
        this.cum = cum;
    }

    public String getDescripcionInvima() {
        return descripcionInvima;
    }

    public void setDescripcionInvima(String descripcionInvima) {
        this.descripcionInvima = descripcionInvima;
    }

    public String getDescripcionEstandarizada() {
        return descripcionEstandarizada;
    }

    public void setDescripcionEstandarizada(String descripcionEstandarizada) {
        this.descripcionEstandarizada = descripcionEstandarizada;
    }

    public Integer getMaeConcentracionId() {
        return maeConcentracionId;
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

    public void setMaeConcentracionId(Integer maeConcentracionId) {
        this.maeConcentracionId = maeConcentracionId;
    }

    public String getMaeConcentracionCodigo() {
        return maeConcentracionCodigo;
    }

    public void setMaeConcentracionCodigo(String maeConcentracionCodigo) {
        this.maeConcentracionCodigo = maeConcentracionCodigo;
    }

    public String getMaeConcentracionValor() {
        return maeConcentracionValor;
    }

    public void setMaeConcentracionValor(String maeConcentracionValor) {
        this.maeConcentracionValor = maeConcentracionValor;
    }

    public Integer getMaePrincipioActivoId() {
        return maePrincipioActivoId;
    }

    public void setMaePrincipioActivoId(Integer maePrincipioActivoId) {
        this.maePrincipioActivoId = maePrincipioActivoId;
    }

    public String getMaePrincipioActivoCodigo() {
        return maePrincipioActivoCodigo;
    }

    public void setMaePrincipioActivoCodigo(String maePrincipioActivoCodigo) {
        this.maePrincipioActivoCodigo = maePrincipioActivoCodigo;
    }

    public String getMaePrincipioActivoValor() {
        return maePrincipioActivoValor;
    }

    public void setMaePrincipioActivoValor(String maePrincipioActivoValor) {
        this.maePrincipioActivoValor = maePrincipioActivoValor;
    }

    public Integer getMaeFormaFarmaceuticaId() {
        return maeFormaFarmaceuticaId;
    }

    public void setMaeFormaFarmaceuticaId(Integer maeFormaFarmaceuticaId) {
        this.maeFormaFarmaceuticaId = maeFormaFarmaceuticaId;
    }

    public String getMaeFormaFarmaceuticaCodigo() {
        return maeFormaFarmaceuticaCodigo;
    }

    public void setMaeFormaFarmaceuticaCodigo(String maeFormaFarmaceuticaCodigo) {
        this.maeFormaFarmaceuticaCodigo = maeFormaFarmaceuticaCodigo;
    }

    public String getMaeFormaFarmaceuticaValor() {
        return maeFormaFarmaceuticaValor;
    }

    public void setMaeFormaFarmaceuticaValor(String maeFormaFarmaceuticaValor) {
        this.maeFormaFarmaceuticaValor = maeFormaFarmaceuticaValor;
    }

    public Integer getMaeTipoPpmId() {
        return maeTipoPpmId;
    }

    public void setMaeTipoPpmId(Integer maeTipoPpmId) {
        this.maeTipoPpmId = maeTipoPpmId;
    }

    public String getMaeTipoPpmCodigo() {
        return maeTipoPpmCodigo;
    }

    public void setMaeTipoPpmCodigo(String maeTipoPpmCodigo) {
        this.maeTipoPpmCodigo = maeTipoPpmCodigo;
    }

    public String getMaeTipoPpmValor() {
        return maeTipoPpmValor;
    }

    public void setMaeTipoPpmValor(String maeTipoPpmValor) {
        this.maeTipoPpmValor = maeTipoPpmValor;
    }

    public Boolean getEsAltoCosto() {
        return esAltoCosto;
    }

    public void setEsAltoCosto(Boolean esAltoCosto) {
        this.esAltoCosto = esAltoCosto;
    }

    public Boolean getEsCapitado() {
        return esCapitado;
    }

    public void setEsCapitado(Boolean esCapitado) {
        this.esCapitado = esCapitado;
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

    public Integer getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(Integer sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public String getCodigoIum() {
        return codigoIum;
    }

    public void setCodigoIum(String codigoIum) {
        this.codigoIum = codigoIum;
    }

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public Boolean getEsRegulado() {
        return esRegulado;
    }

    public void setEsRegulado(Boolean esRegulado) {
        this.esRegulado = esRegulado;
    }

    public BigDecimal getValorMaximoRegulado() {
        return valorMaximoRegulado;
    }

    public void setValorMaximoRegulado(BigDecimal valorMaximoRegulado) {
        this.valorMaximoRegulado = valorMaximoRegulado;
    }

    public BigDecimal getValorReferenteMinimo() {
        return valorReferenteMinimo;
    }

    public void setValorReferenteMinimo(BigDecimal valorReferenteMinimo) {
        this.valorReferenteMinimo = valorReferenteMinimo;
    }

    public BigDecimal getValorReferete() {
        return valorReferete;
    }

    public void setValorReferete(BigDecimal valorReferete) {
        this.valorReferete = valorReferete;
    }

    public String getCodigoFinanciador() {
        return codigoFinanciador;
    }

    public void setCodigoFinanciador(String codigoFinanciador) {
        this.codigoFinanciador = codigoFinanciador;
    }

    public Integer getIdViejo() {
        return idViejo;
    }

    public void setIdViejo(Integer idViejo) {
        this.idViejo = idViejo;
    }

    public MaAgrupadorMedicamento getMaAgrupadorMedicamento() {
        return maAgrupadorMedicamento;
    }

    public void setMaAgrupadorMedicamento(MaAgrupadorMedicamento maAgrupadorMedicamento) {
        this.maAgrupadorMedicamento = maAgrupadorMedicamento;
    }

    /**
     * @return the cobertura
     */
    public int getCobertura() {
        return cobertura;
    }

    /**
     * @param cobertura the cobertura to set
     */
    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * @return the descripcionLargaEstandarizada
     */
    public String getDescripcionLargaEstandarizada() {
        return descripcionLargaEstandarizada;
    }

    /**
     * @param descripcionLargaEstandarizada the descripcionLargaEstandarizada to set
     */
    public void setDescripcionLargaEstandarizada(String descripcionLargaEstandarizada) {
        this.descripcionLargaEstandarizada = descripcionLargaEstandarizada;
    }

    /**
     * @return the expediente
     */
    public String getExpediente() {
        return expediente;
    }

    /**
     * @param expediente the expediente to set
     */
    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    /**
     * @return the nombreComercial
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * @param nombreComercial the nombreComercial to set
     */
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * @return the laboratorio
     */
    public String getLaboratorio() {
        return laboratorio;
    }

    /**
     * @param laboratorio the laboratorio to set
     */
    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * @return the registroSanitario
     */
    public String getRegistroSanitario() {
        return registroSanitario;
    }

    /**
     * @param registroSanitario the registroSanitario to set
     */
    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    /**
     * @return the fechaExpedicion
     */
    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * @param fechaExpedicion the fechaExpedicion to set
     */
    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the cantidadCum
     */
    public Integer getCantidadCum() {
        return cantidadCum;
    }

    /**
     * @param cantidadCum the cantidadCum to set
     */
    public void setCantidadCum(Integer cantidadCum) {
        this.cantidadCum = cantidadCum;
    }

    /**
     * @return the fechaActivo
     */
    public Date getFechaActivo() {
        return fechaActivo;
    }

    /**
     * @param fechaActivo the fechaActivo to set
     */
    public void setFechaActivo(Date fechaActivo) {
        this.fechaActivo = fechaActivo;
    }

    /**
     * @return the fechaInactivo
     */
    public Date getFechaInactivo() {
        return fechaInactivo;
    }

    /**
     * @param fechaInactivo the fechaInactivo to set
     */
    public void setFechaInactivo(Date fechaInactivo) {
        this.fechaInactivo = fechaInactivo;
    }

    /**
     * @return the maeAtc1Id
     */
    public Integer getMaeAtc1Id() {
        return maeAtc1Id;
    }

    /**
     * @param maeAtc1Id the maeAtc1Id to set
     */
    public void setMaeAtc1Id(Integer maeAtc1Id) {
        this.maeAtc1Id = maeAtc1Id;
    }

    /**
     * @return the maeAtc1Codigo
     */
    public String getMaeAtc1Codigo() {
        return maeAtc1Codigo;
    }

    /**
     * @param maeAtc1Codigo the maeAtc1Codigo to set
     */
    public void setMaeAtc1Codigo(String maeAtc1Codigo) {
        this.maeAtc1Codigo = maeAtc1Codigo;
    }

    /**
     * @return the maeAtc1Valor
     */
    public String getMaeAtc1Valor() {
        return maeAtc1Valor;
    }

    /**
     * @param maeAtc1Valor the maeAtc1Valor to set
     */
    public void setMaeAtc1Valor(String maeAtc1Valor) {
        this.maeAtc1Valor = maeAtc1Valor;
    }

    /**
     * @return the maeAtc2Id
     */
    public Integer getMaeAtc2Id() {
        return maeAtc2Id;
    }

    /**
     * @param maeAtc2Id the maeAtc2Id to set
     */
    public void setMaeAtc2Id(Integer maeAtc2Id) {
        this.maeAtc2Id = maeAtc2Id;
    }

    /**
     * @return the maeAtc2Codigo
     */
    public String getMaeAtc2Codigo() {
        return maeAtc2Codigo;
    }

    /**
     * @param maeAtc2Codigo the maeAtc2Codigo to set
     */
    public void setMaeAtc2Codigo(String maeAtc2Codigo) {
        this.maeAtc2Codigo = maeAtc2Codigo;
    }

    /**
     * @return the maeAtc2Valor
     */
    public String getMaeAtc2Valor() {
        return maeAtc2Valor;
    }

    /**
     * @param maeAtc2Valor the maeAtc2Valor to set
     */
    public void setMaeAtc2Valor(String maeAtc2Valor) {
        this.maeAtc2Valor = maeAtc2Valor;
    }

    /**
     * @return the maeAtc3Id
     */
    public Integer getMaeAtc3Id() {
        return maeAtc3Id;
    }

    /**
     * @param maeAtc3Id the maeAtc3Id to set
     */
    public void setMaeAtc3Id(Integer maeAtc3Id) {
        this.maeAtc3Id = maeAtc3Id;
    }

    /**
     * @return the maeAtc3Codigo
     */
    public String getMaeAtc3Codigo() {
        return maeAtc3Codigo;
    }

    /**
     * @param maeAtc3Codigo the maeAtc3Codigo to set
     */
    public void setMaeAtc3Codigo(String maeAtc3Codigo) {
        this.maeAtc3Codigo = maeAtc3Codigo;
    }

    /**
     * @return the maeAtc3Valor
     */
    public String getMaeAtc3Valor() {
        return maeAtc3Valor;
    }

    /**
     * @param maeAtc3Valor the maeAtc3Valor to set
     */
    public void setMaeAtc3Valor(String maeAtc3Valor) {
        this.maeAtc3Valor = maeAtc3Valor;
    }

    /**
     * @return the mce
     */
    public Boolean getMce() {
        return mce;
    }

    /**
     * @param mce the mce to set
     */
    public void setMce(Boolean mce) {
        this.mce = mce;
    }

    /**
     * @return the monopolioEstado
     */
    public Boolean getMonopolioEstado() {
        return monopolioEstado;
    }

    /**
     * @param monopolioEstado the monopolioEstado to set
     */
    public void setMonopolioEstado(Boolean monopolioEstado) {
        this.monopolioEstado = monopolioEstado;
    }

    /**
     * @return the estrechoMargenTerapeutico
     */
    public Boolean getEstrechoMargenTerapeutico() {
        return estrechoMargenTerapeutico;
    }

    /**
     * @param estrechoMargenTerapeutico the estrechoMargenTerapeutico to set
     */
    public void setEstrechoMargenTerapeutico(Boolean estrechoMargenTerapeutico) {
        this.estrechoMargenTerapeutico = estrechoMargenTerapeutico;
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
     * @return the normaRegulacion
     */
    public String getNormaRegulacion() {
        return normaRegulacion;
    }

    /**
     * @param normaRegulacion the normaRegulacion to set
     */
    public void setNormaRegulacion(String normaRegulacion) {
        this.normaRegulacion = normaRegulacion;
    }

    /**
     * @return the valorFraccion
     */
    public BigDecimal getValorFraccion() {
        return valorFraccion;
    }

    /**
     * @param valorFraccion the valorFraccion to set
     */
    public void setValorFraccion(BigDecimal valorFraccion) {
        this.valorFraccion = valorFraccion;
    }

    /**
     * @return the valorPresentacionComercial
     */
    public BigDecimal getValorPresentacionComercial() {
        return valorPresentacionComercial;
    }

    /**
     * @param valorPresentacionComercial the valorPresentacionComercial to set
     */
    public void setValorPresentacionComercial(BigDecimal valorPresentacionComercial) {
        this.valorPresentacionComercial = valorPresentacionComercial;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the diasTratamiento
     */
    public Integer getDiasTratamiento() {
        return diasTratamiento;
    }

    /**
     * @param diasTratamiento the diasTratamiento to set
     */
    public void setDiasTratamiento(Integer diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    /**
     * @return the maeGrupoAnatomicoPpalId
     */
    public Integer getMaeGrupoAnatomicoPpalId() {
        return maeGrupoAnatomicoPpalId;
    }

    /**
     * @param maeGrupoAnatomicoPpalId the maeGrupoAnatomicoPpalId to set
     */
    public void setMaeGrupoAnatomicoPpalId(Integer maeGrupoAnatomicoPpalId) {
        this.maeGrupoAnatomicoPpalId = maeGrupoAnatomicoPpalId;
    }

    /**
     * @return the maeGrupoAnatomicoPpalCodigo
     */
    public String getMaeGrupoAnatomicoPpalCodigo() {
        return maeGrupoAnatomicoPpalCodigo;
    }

    /**
     * @param maeGrupoAnatomicoPpalCodigo the maeGrupoAnatomicoPpalCodigo to set
     */
    public void setMaeGrupoAnatomicoPpalCodigo(String maeGrupoAnatomicoPpalCodigo) {
        this.maeGrupoAnatomicoPpalCodigo = maeGrupoAnatomicoPpalCodigo;
    }

    /**
     * @return the maeGrupoAnatomicoPpalValor
     */
    public String getMaeGrupoAnatomicoPpalValor() {
        return maeGrupoAnatomicoPpalValor;
    }

    /**
     * @param maeGrupoAnatomicoPpalValor the maeGrupoAnatomicoPpalValor to set
     */
    public void setMaeGrupoAnatomicoPpalValor(String maeGrupoAnatomicoPpalValor) {
        this.maeGrupoAnatomicoPpalValor = maeGrupoAnatomicoPpalValor;
    }

    /**
     * @return the maeGrupoTerapeuticoPpalId
     */
    public Integer getMaeGrupoTerapeuticoPpalId() {
        return maeGrupoTerapeuticoPpalId;
    }

    /**
     * @param maeGrupoTerapeuticoPpalId the maeGrupoTerapeuticoPpalId to set
     */
    public void setMaeGrupoTerapeuticoPpalId(Integer maeGrupoTerapeuticoPpalId) {
        this.maeGrupoTerapeuticoPpalId = maeGrupoTerapeuticoPpalId;
    }

    /**
     * @return the maeGrupoTerapeuticoPpalCodigo
     */
    public String getMaeGrupoTerapeuticoPpalCodigo() {
        return maeGrupoTerapeuticoPpalCodigo;
    }

    /**
     * @param maeGrupoTerapeuticoPpalCodigo the maeGrupoTerapeuticoPpalCodigo to set
     */
    public void setMaeGrupoTerapeuticoPpalCodigo(String maeGrupoTerapeuticoPpalCodigo) {
        this.maeGrupoTerapeuticoPpalCodigo = maeGrupoTerapeuticoPpalCodigo;
    }

    /**
     * @return the maeGrupoTerapeuticoPpalValor
     */
    public String getMaeGrupoTerapeuticoPpalValor() {
        return maeGrupoTerapeuticoPpalValor;
    }

    /**
     * @param maeGrupoTerapeuticoPpalValor the maeGrupoTerapeuticoPpalValor to set
     */
    public void setMaeGrupoTerapeuticoPpalValor(String maeGrupoTerapeuticoPpalValor) {
        this.maeGrupoTerapeuticoPpalValor = maeGrupoTerapeuticoPpalValor;
    }

    /**
     * @return the dci
     */
    public String getDci() {
        return dci;
    }

    /**
     * @param dci the dci to set
     */
    public void setDci(String dci) {
        this.dci = dci;
    }

    /**
     * @return the descripcionDci
     */
    public String getDescripcionDci() {
        return descripcionDci;
    }

    /**
     * @param descripcionDci the descripcionDci to set
     */
    public void setDescripcionDci(String descripcionDci) {
        this.descripcionDci = descripcionDci;
    }

    /**
     * @return the provenienteInvima
     */
    public String getProvenienteInvima() {
        return provenienteInvima;
    }

    /**
     * @param provenienteInvima the provenienteInvima to set
     */
    public void setProvenienteInvima(String provenienteInvima) {
        this.provenienteInvima = provenienteInvima;
    }

    /**
     * @return the agrupadorCondicionPbs
     */
    public String getAgrupadorCondicionPbs() {
        return agrupadorCondicionPbs;
    }

    /**
     * @param agrupadorCondicionPbs the agrupadorCondicionPbs to set
     */
    public void setAgrupadorCondicionPbs(String agrupadorCondicionPbs) {
        this.agrupadorCondicionPbs = agrupadorCondicionPbs;
    }

    /**
     * @return the valorComercial
     */
    public BigDecimal getValorComercial() {
        return valorComercial;
    }

    /**
     * @param valorComercial the valorComercial to set
     */
    public void setValorComercial(BigDecimal valorComercial) {
        this.valorComercial = valorComercial;
    }

    /**
     * @return the listaUnirs
     */
    public Boolean getListaUnirs() {
        return listaUnirs;
    }

    /**
     * @param listaUnirs the listaUnirs to set
     */
    public void setListaUnirs(Boolean listaUnirs) {
        this.listaUnirs = listaUnirs;
    }

    /**
     * @return the codigoSuficienciaUpc
     */
    public String getCodigoSuficienciaUpc() {
        return codigoSuficienciaUpc;
    }

    /**
     * @param codigoSuficienciaUpc the codigoSuficienciaUpc to set
     */
    public void setCodigoSuficienciaUpc(String codigoSuficienciaUpc) {
        this.codigoSuficienciaUpc = codigoSuficienciaUpc;
    }

    /**
     * @return the cantidadConcentracionSufUpc
     */
    public String getCantidadConcentracionSufUpc() {
        return cantidadConcentracionSufUpc;
    }

    /**
     * @param cantidadConcentracionSufUpc the cantidadConcentracionSufUpc to set
     */
    public void setCantidadConcentracionSufUpc(String cantidadConcentracionSufUpc) {
        this.cantidadConcentracionSufUpc = cantidadConcentracionSufUpc;
    }

    /**
     * @return the unidadConcentracionSufUpc
     */
    public String getUnidadConcentracionSufUpc() {
        return unidadConcentracionSufUpc;
    }

    /**
     * @param unidadConcentracionSufUpc the unidadConcentracionSufUpc to set
     */
    public void setUnidadConcentracionSufUpc(String unidadConcentracionSufUpc) {
        this.unidadConcentracionSufUpc = unidadConcentracionSufUpc;
    }

    /**
     * @return the unidadMedidaSuficiencia
     */
    public String getUnidadMedidaSuficiencia() {
        return unidadMedidaSuficiencia;
    }

    /**
     * @param unidadMedidaSuficiencia the unidadMedidaSuficiencia to set
     */
    public void setUnidadMedidaSuficiencia(String unidadMedidaSuficiencia) {
        this.unidadMedidaSuficiencia = unidadMedidaSuficiencia;
    }

    /**
     * @return the muestraMedica
     */
    public Boolean getMuestraMedica() {
        return muestraMedica;
    }

    /**
     * @param muestraMedica the muestraMedica to set
     */
    public void setMuestraMedica(Boolean muestraMedica) {
        this.muestraMedica = muestraMedica;
    }

    /**
     * @return the maeEstadoRegistroSanitarioId
     */
    public Integer getMaeEstadoRegistroSanitarioId() {
        return maeEstadoRegistroSanitarioId;
    }

    /**
     * @param maeEstadoRegistroSanitarioId the maeEstadoRegistroSanitarioId to set
     */
    public void setMaeEstadoRegistroSanitarioId(Integer maeEstadoRegistroSanitarioId) {
        this.maeEstadoRegistroSanitarioId = maeEstadoRegistroSanitarioId;
    }

    /**
     * @return the maeEstadoRegistroSanitarioCodigo
     */
    public String getMaeEstadoRegistroSanitarioCodigo() {
        return maeEstadoRegistroSanitarioCodigo;
    }

    /**
     * @param maeEstadoRegistroSanitarioCodigo the maeEstadoRegistroSanitarioCodigo to set
     */
    public void setMaeEstadoRegistroSanitarioCodigo(String maeEstadoRegistroSanitarioCodigo) {
        this.maeEstadoRegistroSanitarioCodigo = maeEstadoRegistroSanitarioCodigo;
    }

    /**
     * @return the maeEstadoRegistroSanitarioValor
     */
    public String getMaeEstadoRegistroSanitarioValor() {
        return maeEstadoRegistroSanitarioValor;
    }

    /**
     * @param maeEstadoRegistroSanitarioValor the maeEstadoRegistroSanitarioValor to set
     */
    public void setMaeEstadoRegistroSanitarioValor(String maeEstadoRegistroSanitarioValor) {
        this.maeEstadoRegistroSanitarioValor = maeEstadoRegistroSanitarioValor;
    }

    @Override
    public String toString() {
        return "MaMedicamento{" + "id=" + id + ", activo=" + activo + ", cum=" + cum + ", descripcionInvima=" + descripcionInvima + ", descripcionEstandarizada=" + descripcionEstandarizada + ", maeCoberturaId=" + maeCoberturaId + ", maeCoberturaCodigo=" + maeCoberturaCodigo + ", maeCoberturaValor=" + maeCoberturaValor + ", maeConcentracionId=" + maeConcentracionId + ", maeConcentracionCodigo=" + maeConcentracionCodigo + ", maeConcentracionValor=" + maeConcentracionValor + ", maePrincipioActivoId=" + maePrincipioActivoId + ", maePrincipioActivoCodigo=" + maePrincipioActivoCodigo + ", maePrincipioActivoValor=" + maePrincipioActivoValor + ", maeFormaFarmaceuticaId=" + maeFormaFarmaceuticaId + ", maeFormaFarmaceuticaCodigo=" + maeFormaFarmaceuticaCodigo + ", maeFormaFarmaceuticaValor=" + maeFormaFarmaceuticaValor + ", maeTipoPpmId=" + maeTipoPpmId + ", maeTipoPpmCodigo=" + maeTipoPpmCodigo + ", maeTipoPpmValor=" + maeTipoPpmValor + ", esAltoCosto=" + esAltoCosto + ", esCapitado=" + esCapitado + ", aplicaSubsidiado=" + aplicaSubsidiado + ", aplicaContributivo=" + aplicaContributivo + ", sexoAplica=" + sexoAplica + ", codigoIum=" + codigoIum + ", edadDesde=" + edadDesde + ", edadHasta=" + edadHasta + ", esRegulado=" + esRegulado + ", valorMaximoRegulado=" + valorMaximoRegulado + ", valorReferenteMinimo=" + valorReferenteMinimo + ", valorReferete=" + valorReferete + ", codigoFinanciador=" + codigoFinanciador + ", idViejo=" + idViejo + ", cobertura=" + cobertura + ", descripcionLargaEstandarizada=" + descripcionLargaEstandarizada + ", expediente=" + expediente + ", nombreComercial=" + nombreComercial + ", laboratorio=" + laboratorio + ", registroSanitario=" + registroSanitario + ", fechaExpedicion=" + fechaExpedicion + ", fechaVencimiento=" + fechaVencimiento + ", maeEstadoRegistroSanitarioId=" + getMaeEstadoRegistroSanitarioId() + ", maeEstadoRegistroSanitarioCodigo=" + getMaeEstadoRegistroSanitarioCodigo() + ", maeEstadoRegistroSanitarioValor=" + getMaeEstadoRegistroSanitarioValor() + ", cantidadCum=" + cantidadCum + ", fechaActivo=" + fechaActivo + ", fechaInactivo=" + fechaInactivo + ", maeAtc1Id=" + maeAtc1Id + ", maeAtc1Codigo=" + maeAtc1Codigo + ", maeAtc1Valor=" + maeAtc1Valor + ", maeAtc2Id=" + maeAtc2Id + ", maeAtc2Codigo=" + maeAtc2Codigo + ", maeAtc2Valor=" + maeAtc2Valor + ", maeAtc3Id=" + maeAtc3Id + ", maeAtc3Codigo=" + maeAtc3Codigo + ", maeAtc3Valor=" + maeAtc3Valor + ", mce=" + mce + ", monopolioEstado=" + monopolioEstado + ", estrechoMargenTerapeutico=" + estrechoMargenTerapeutico + ", aclaracion=" + aclaracion + ", normaRegulacion=" + normaRegulacion + ", valorFraccion=" + valorFraccion + ", valorPresentacionComercial=" + valorPresentacionComercial + ", cantidad=" + cantidad + ", diasTratamiento=" + diasTratamiento + ", maeGrupoAnatomicoPpalId=" + maeGrupoAnatomicoPpalId + ", maeGrupoAnatomicoPpalCodigo=" + maeGrupoAnatomicoPpalCodigo + ", maeGrupoAnatomicoPpalValor=" + maeGrupoAnatomicoPpalValor + ", maeGrupoTerapeuticoPpalId=" + maeGrupoTerapeuticoPpalId + ", maeGrupoTerapeuticoPpalCodigo=" + maeGrupoTerapeuticoPpalCodigo + ", maeGrupoTerapeuticoPpalValor=" + maeGrupoTerapeuticoPpalValor + ", dci=" + dci + ", descripcionDci=" + descripcionDci + ", provenienteInvima=" + provenienteInvima + ", agrupadorCondicionPbs=" + agrupadorCondicionPbs + ", valorComercial=" + valorComercial + ", listaUnirs=" + listaUnirs + ", codigoSuficienciaUpc=" + codigoSuficienciaUpc + ", cantidadConcentracionSufUpc=" + cantidadConcentracionSufUpc + ", unidadConcentracionSufUpc=" + unidadConcentracionSufUpc + ", unidadMedidaSuficiencia=" + unidadMedidaSuficiencia + ", muestraMedica=" + muestraMedica +", formaFarmaceutica=" + formaFarmaceutica + ", maAgrupadorMedicamento=" + maAgrupadorMedicamento + '}';
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
     * @return the noPbsMenorValor
     */
    public Boolean getNoPbsMenorValor() {
        return noPbsMenorValor;
    }

    /**
     * @param noPbsMenorValor the noPbsMenorValor to set
     */
    public void setNoPbsMenorValor(Boolean noPbsMenorValor) {
        this.noPbsMenorValor = noPbsMenorValor;
    }

    /**
     * @return the formaFarmaceutica
     */
    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    /**
     * @param formaFarmaceutica the formaFarmaceutica to set
     */
    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

}
