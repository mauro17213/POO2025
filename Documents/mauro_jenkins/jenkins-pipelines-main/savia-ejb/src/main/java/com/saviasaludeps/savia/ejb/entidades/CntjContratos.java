/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cntj_contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratos.findAll", query = "SELECT c FROM CntjContratos c"),
    @NamedQuery(name = "CntjContratos.findById", query = "SELECT c FROM CntjContratos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratos.findByMaeTipoContratoId", query = "SELECT c FROM CntjContratos c WHERE c.maeTipoContratoId = :maeTipoContratoId"),
    @NamedQuery(name = "CntjContratos.findByMaeTipoContratoCodigo", query = "SELECT c FROM CntjContratos c WHERE c.maeTipoContratoCodigo = :maeTipoContratoCodigo"),
    @NamedQuery(name = "CntjContratos.findByMaeTipoContratoValor", query = "SELECT c FROM CntjContratos c WHERE c.maeTipoContratoValor = :maeTipoContratoValor"),
    @NamedQuery(name = "CntjContratos.findByFechaInicio", query = "SELECT c FROM CntjContratos c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntjContratos.findByFechaFin", query = "SELECT c FROM CntjContratos c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntjContratos.findByValorInicial", query = "SELECT c FROM CntjContratos c WHERE c.valorInicial = :valorInicial"),
    @NamedQuery(name = "CntjContratos.findByValorPagadoTotal", query = "SELECT c FROM CntjContratos c WHERE c.valorPagadoTotal = :valorPagadoTotal"),
    @NamedQuery(name = "CntjContratos.findByValorContratoMasAdiciones", query = "SELECT c FROM CntjContratos c WHERE c.valorContratoMasAdiciones = :valorContratoMasAdiciones"),
    @NamedQuery(name = "CntjContratos.findByValorTotalOtrosies", query = "SELECT c FROM CntjContratos c WHERE c.valorTotalOtrosies = :valorTotalOtrosies"),
    @NamedQuery(name = "CntjContratos.findByContrato", query = "SELECT c FROM CntjContratos c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CntjContratos.findByMaeEstadoContratoId", query = "SELECT c FROM CntjContratos c WHERE c.maeEstadoContratoId = :maeEstadoContratoId"),
    @NamedQuery(name = "CntjContratos.findByMaeEstadoContratoCodigo", query = "SELECT c FROM CntjContratos c WHERE c.maeEstadoContratoCodigo = :maeEstadoContratoCodigo"),
    @NamedQuery(name = "CntjContratos.findByMaeEstadoContratoValor", query = "SELECT c FROM CntjContratos c WHERE c.maeEstadoContratoValor = :maeEstadoContratoValor"),
    @NamedQuery(name = "CntjContratos.findByProceso", query = "SELECT c FROM CntjContratos c WHERE c.proceso = :proceso"),
    @NamedQuery(name = "CntjContratos.findByMaeClaseContratoId", query = "SELECT c FROM CntjContratos c WHERE c.maeClaseContratoId = :maeClaseContratoId"),
    @NamedQuery(name = "CntjContratos.findByMaeClaseContratoCodigo", query = "SELECT c FROM CntjContratos c WHERE c.maeClaseContratoCodigo = :maeClaseContratoCodigo"),
    @NamedQuery(name = "CntjContratos.findByMaeClaseContratoValor", query = "SELECT c FROM CntjContratos c WHERE c.maeClaseContratoValor = :maeClaseContratoValor"),
    @NamedQuery(name = "CntjContratos.findByEstadoLegalizacion", query = "SELECT c FROM CntjContratos c WHERE c.estadoLegalizacion = :estadoLegalizacion"),
    @NamedQuery(name = "CntjContratos.findByMaeModalidadContratoId", query = "SELECT c FROM CntjContratos c WHERE c.maeModalidadContratoId = :maeModalidadContratoId"),
    @NamedQuery(name = "CntjContratos.findByMaeModalidadContratoCodigo", query = "SELECT c FROM CntjContratos c WHERE c.maeModalidadContratoCodigo = :maeModalidadContratoCodigo"),
    @NamedQuery(name = "CntjContratos.findByMaeModalidadContratoValor", query = "SELECT c FROM CntjContratos c WHERE c.maeModalidadContratoValor = :maeModalidadContratoValor"),
    @NamedQuery(name = "CntjContratos.findByComplejidad", query = "SELECT c FROM CntjContratos c WHERE c.complejidad = :complejidad"),
    @NamedQuery(name = "CntjContratos.findByMaeRegimenId", query = "SELECT c FROM CntjContratos c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CntjContratos.findByMaeRegimenCodigo", query = "SELECT c FROM CntjContratos c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CntjContratos.findByMaeRegimenValor", query = "SELECT c FROM CntjContratos c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CntjContratos.findByPlazoInicialMeses", query = "SELECT c FROM CntjContratos c WHERE c.plazoInicialMeses = :plazoInicialMeses"),
    @NamedQuery(name = "CntjContratos.findByPlazoInicialDias", query = "SELECT c FROM CntjContratos c WHERE c.plazoInicialDias = :plazoInicialDias"),
    @NamedQuery(name = "CntjContratos.findByPlazoProrrogas", query = "SELECT c FROM CntjContratos c WHERE c.plazoProrrogas = :plazoProrrogas"),
    @NamedQuery(name = "CntjContratos.findByPlazoTotalDias", query = "SELECT c FROM CntjContratos c WHERE c.plazoTotalDias = :plazoTotalDias"),
    @NamedQuery(name = "CntjContratos.findByValorMes", query = "SELECT c FROM CntjContratos c WHERE c.valorMes = :valorMes"),
    @NamedQuery(name = "CntjContratos.findByValorDia", query = "SELECT c FROM CntjContratos c WHERE c.valorDia = :valorDia"),
    @NamedQuery(name = "CntjContratos.findByValorUpc", query = "SELECT c FROM CntjContratos c WHERE c.valorUpc = :valorUpc"),
    @NamedQuery(name = "CntjContratos.findByValorAdiciones", query = "SELECT c FROM CntjContratos c WHERE c.valorAdiciones = :valorAdiciones"),
    @NamedQuery(name = "CntjContratos.findByValorTotal", query = "SELECT c FROM CntjContratos c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "CntjContratos.findByFormaPago", query = "SELECT c FROM CntjContratos c WHERE c.formaPago = :formaPago"),
    @NamedQuery(name = "CntjContratos.findByTipoAnticipo", query = "SELECT c FROM CntjContratos c WHERE c.tipoAnticipo = :tipoAnticipo"),
    @NamedQuery(name = "CntjContratos.findByValorAnticipo", query = "SELECT c FROM CntjContratos c WHERE c.valorAnticipo = :valorAnticipo"),
    @NamedQuery(name = "CntjContratos.findByFechaSuscripcion", query = "SELECT c FROM CntjContratos c WHERE c.fechaSuscripcion = :fechaSuscripcion"),
    @NamedQuery(name = "CntjContratos.findByTipoGasto", query = "SELECT c FROM CntjContratos c WHERE c.tipoGasto = :tipoGasto"),
    @NamedQuery(name = "CntjContratos.findByFechaSuspension", query = "SELECT c FROM CntjContratos c WHERE c.fechaSuspension = :fechaSuspension"),
    @NamedQuery(name = "CntjContratos.findByMotivoSuspension", query = "SELECT c FROM CntjContratos c WHERE c.motivoSuspension = :motivoSuspension"),
    @NamedQuery(name = "CntjContratos.findByMotivoTerminacionAnticipada", query = "SELECT c FROM CntjContratos c WHERE c.motivoTerminacionAnticipada = :motivoTerminacionAnticipada"),
    @NamedQuery(name = "CntjContratos.findByEnlacePublicaSecop", query = "SELECT c FROM CntjContratos c WHERE c.enlacePublicaSecop = :enlacePublicaSecop"),
    @NamedQuery(name = "CntjContratos.findByFechaPublicaSecop", query = "SELECT c FROM CntjContratos c WHERE c.fechaPublicaSecop = :fechaPublicaSecop"),
    @NamedQuery(name = "CntjContratos.findByResponsablePublicaSecop", query = "SELECT c FROM CntjContratos c WHERE c.responsablePublicaSecop = :responsablePublicaSecop"),
    @NamedQuery(name = "CntjContratos.findByFechaLiquidacion", query = "SELECT c FROM CntjContratos c WHERE c.fechaLiquidacion = :fechaLiquidacion"),
    @NamedQuery(name = "CntjContratos.findByValorFacturado", query = "SELECT c FROM CntjContratos c WHERE c.valorFacturado = :valorFacturado"),
    @NamedQuery(name = "CntjContratos.findByPeriodicidadSeguimiento", query = "SELECT c FROM CntjContratos c WHERE c.periodicidadSeguimiento = :periodicidadSeguimiento"),
    @NamedQuery(name = "CntjContratos.findByUsuarioCrea", query = "SELECT c FROM CntjContratos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratos.findByTerminalCrea", query = "SELECT c FROM CntjContratos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratos.findByFechaHoraCrea", query = "SELECT c FROM CntjContratos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjContratos.findByUsuarioModifica", query = "SELECT c FROM CntjContratos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjContratos.findByTerminalModifica", query = "SELECT c FROM CntjContratos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjContratos.findByFechaHoraModifica", query = "SELECT c FROM CntjContratos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjContratos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_contrato_id")
    private Integer maeTipoContratoId;
    @Size(max = 45)
    @Column(name = "mae_tipo_contrato_codigo")
    private String maeTipoContratoCodigo;
    @Size(max = 45)
    @Column(name = "mae_tipo_contrato_valor")
    private String maeTipoContratoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_inicial")
    private BigDecimal valorInicial;
    @Column(name = "valor_pagado_total")
    private BigDecimal valorPagadoTotal;
    @Column(name = "valor_contrato_mas_adiciones")
    private BigDecimal valorContratoMasAdiciones;
    @Column(name = "valor_total_otrosies")
    private BigDecimal valorTotalOtrosies;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "contrato")
    private String contrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_contrato_id")
    private int maeEstadoContratoId;
    @Size(max = 8)
    @Column(name = "mae_estado_contrato_codigo")
    private String maeEstadoContratoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_contrato_valor")
    private String maeEstadoContratoValor;
    @Size(max = 64)
    @Column(name = "proceso")
    private String proceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_clase_contrato_id")
    private int maeClaseContratoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_clase_contrato_codigo")
    private String maeClaseContratoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_clase_contrato_valor")
    private String maeClaseContratoValor;
    @Column(name = "estado_legalizacion")
    private Integer estadoLegalizacion;
    @Column(name = "mae_modalidad_contrato_id")
    private Integer maeModalidadContratoId;
    @Size(max = 8)
    @Column(name = "mae_modalidad_contrato_codigo")
    private String maeModalidadContratoCodigo;
    @Size(max = 128)
    @Column(name = "mae_modalidad_contrato_valor")
    private String maeModalidadContratoValor;
    @Column(name = "complejidad")
    private Integer complejidad;
    @Column(name = "mae_regimen_id")
    private Integer maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazo_inicial_meses")
    private int plazoInicialMeses;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "plazo_inicial_dias")
    private String plazoInicialDias;
    @Column(name = "plazo_prorrogas")
    private Integer plazoProrrogas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazo_total_dias")
    private int plazoTotalDias;
    @Column(name = "valor_mes")
    private BigDecimal valorMes;
    @Column(name = "valor_dia")
    private BigDecimal valorDia;
    @Column(name = "valor_upc")
    private BigDecimal valorUpc;
    @Column(name = "valor_adiciones")
    private BigDecimal valorAdiciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "forma_pago")
    private Integer formaPago;
    @Column(name = "tipo_anticipo")
    private Integer tipoAnticipo;
    @Column(name = "valor_anticipo")
    private BigDecimal valorAnticipo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "fecha_suscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaSuscripcion;
    @Column(name = "tipo_gasto")
    private Integer tipoGasto;
    @Column(name = "fecha_suspension")
    @Temporal(TemporalType.DATE)
    private Date fechaSuspension;
    @Size(max = 1024)
    @Column(name = "motivo_suspension")
    private String motivoSuspension;
    @Size(max = 512)
    @Column(name = "motivo_terminacion_anticipada")
    private String motivoTerminacionAnticipada;
    @Size(max = 1024)
    @Column(name = "enlace_publica_secop")
    private String enlacePublicaSecop;
    @Column(name = "fecha_publica_secop")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicaSecop;
    @Size(max = 128)
    @Column(name = "responsable_publica_secop")
    private String responsablePublicaSecop;
    @Column(name = "fecha_liquidacion")
    @Temporal(TemporalType.DATE)
    private Date fechaLiquidacion;
    @Column(name = "valor_facturado")
    private BigDecimal valorFacturado;
    @Column(name = "periodicidad_seguimiento")
    private Integer periodicidadSeguimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoIndicadores> cntjContratoIndicadoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoObligaciones> cntjContratoObligacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoGarantias> cntjContratoGarantiasList;
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;
    @JoinColumn(name = "cntj_terceros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjTerceros cntjTercerosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoInformes> cntjContratoInformesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoHistoricos> cntjContratoHistoricosList;
    @OneToMany(mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjDocumentos> cntjDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoSupervisores> cntjContratoSupervisoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjContratoSeguimientos> cntjContratoSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratosId", fetch = FetchType.LAZY)
    private List<CntjOtrosies> cntjOtrosiesList;

    public CntjContratos() {
    }

    public CntjContratos(Integer id) {
        this.id = id;
    }

    public CntjContratos(Integer id, Date fechaInicio, Date fechaFin, BigDecimal valorInicial, String contrato, int maeEstadoContratoId, int maeClaseContratoId, String maeClaseContratoCodigo, String maeClaseContratoValor, int plazoInicialMeses, String plazoInicialDias, int plazoTotalDias, BigDecimal valorTotal, String objeto, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valorInicial = valorInicial;
        this.contrato = contrato;
        this.maeEstadoContratoId = maeEstadoContratoId;
        this.maeClaseContratoId = maeClaseContratoId;
        this.maeClaseContratoCodigo = maeClaseContratoCodigo;
        this.maeClaseContratoValor = maeClaseContratoValor;
        this.plazoInicialMeses = plazoInicialMeses;
        this.plazoInicialDias = plazoInicialDias;
        this.plazoTotalDias = plazoTotalDias;
        this.valorTotal = valorTotal;
        this.objeto = objeto;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(Integer maeTipoContratoId) {
        this.maeTipoContratoId = maeTipoContratoId;
    }

    public String getMaeTipoContratoCodigo() {
        return maeTipoContratoCodigo;
    }

    public void setMaeTipoContratoCodigo(String maeTipoContratoCodigo) {
        this.maeTipoContratoCodigo = maeTipoContratoCodigo;
    }

    public String getMaeTipoContratoValor() {
        return maeTipoContratoValor;
    }

    public void setMaeTipoContratoValor(String maeTipoContratoValor) {
        this.maeTipoContratoValor = maeTipoContratoValor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorPagadoTotal() {
        return valorPagadoTotal;
    }

    public void setValorPagadoTotal(BigDecimal valorPagadoTotal) {
        this.valorPagadoTotal = valorPagadoTotal;
    }

    public BigDecimal getValorContratoMasAdiciones() {
        return valorContratoMasAdiciones;
    }

    public void setValorContratoMasAdiciones(BigDecimal valorContratoMasAdiciones) {
        this.valorContratoMasAdiciones = valorContratoMasAdiciones;
    }

    public BigDecimal getValorTotalOtrosies() {
        return valorTotalOtrosies;
    }

    public void setValorTotalOtrosies(BigDecimal valorTotalOtrosies) {
        this.valorTotalOtrosies = valorTotalOtrosies;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getMaeEstadoContratoId() {
        return maeEstadoContratoId;
    }

    public void setMaeEstadoContratoId(int maeEstadoContratoId) {
        this.maeEstadoContratoId = maeEstadoContratoId;
    }

    public String getMaeEstadoContratoCodigo() {
        return maeEstadoContratoCodigo;
    }

    public void setMaeEstadoContratoCodigo(String maeEstadoContratoCodigo) {
        this.maeEstadoContratoCodigo = maeEstadoContratoCodigo;
    }

    public String getMaeEstadoContratoValor() {
        return maeEstadoContratoValor;
    }

    public void setMaeEstadoContratoValor(String maeEstadoContratoValor) {
        this.maeEstadoContratoValor = maeEstadoContratoValor;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public int getMaeClaseContratoId() {
        return maeClaseContratoId;
    }

    public void setMaeClaseContratoId(int maeClaseContratoId) {
        this.maeClaseContratoId = maeClaseContratoId;
    }

    public String getMaeClaseContratoCodigo() {
        return maeClaseContratoCodigo;
    }

    public void setMaeClaseContratoCodigo(String maeClaseContratoCodigo) {
        this.maeClaseContratoCodigo = maeClaseContratoCodigo;
    }

    public String getMaeClaseContratoValor() {
        return maeClaseContratoValor;
    }

    public void setMaeClaseContratoValor(String maeClaseContratoValor) {
        this.maeClaseContratoValor = maeClaseContratoValor;
    }

    public Integer getEstadoLegalizacion() {
        return estadoLegalizacion;
    }

    public void setEstadoLegalizacion(Integer estadoLegalizacion) {
        this.estadoLegalizacion = estadoLegalizacion;
    }

    public Integer getMaeModalidadContratoId() {
        return maeModalidadContratoId;
    }

    public void setMaeModalidadContratoId(Integer maeModalidadContratoId) {
        this.maeModalidadContratoId = maeModalidadContratoId;
    }

    public String getMaeModalidadContratoCodigo() {
        return maeModalidadContratoCodigo;
    }

    public void setMaeModalidadContratoCodigo(String maeModalidadContratoCodigo) {
        this.maeModalidadContratoCodigo = maeModalidadContratoCodigo;
    }

    public String getMaeModalidadContratoValor() {
        return maeModalidadContratoValor;
    }

    public void setMaeModalidadContratoValor(String maeModalidadContratoValor) {
        this.maeModalidadContratoValor = maeModalidadContratoValor;
    }

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public int getPlazoInicialMeses() {
        return plazoInicialMeses;
    }

    public void setPlazoInicialMeses(int plazoInicialMeses) {
        this.plazoInicialMeses = plazoInicialMeses;
    }

    public String getPlazoInicialDias() {
        return plazoInicialDias;
    }

    public void setPlazoInicialDias(String plazoInicialDias) {
        this.plazoInicialDias = plazoInicialDias;
    }

    public Integer getPlazoProrrogas() {
        return plazoProrrogas;
    }

    public void setPlazoProrrogas(Integer plazoProrrogas) {
        this.plazoProrrogas = plazoProrrogas;
    }

    public int getPlazoTotalDias() {
        return plazoTotalDias;
    }

    public void setPlazoTotalDias(int plazoTotalDias) {
        this.plazoTotalDias = plazoTotalDias;
    }

    public BigDecimal getValorMes() {
        return valorMes;
    }

    public void setValorMes(BigDecimal valorMes) {
        this.valorMes = valorMes;
    }

    public BigDecimal getValorDia() {
        return valorDia;
    }

    public void setValorDia(BigDecimal valorDia) {
        this.valorDia = valorDia;
    }

    public BigDecimal getValorUpc() {
        return valorUpc;
    }

    public void setValorUpc(BigDecimal valorUpc) {
        this.valorUpc = valorUpc;
    }

    public BigDecimal getValorAdiciones() {
        return valorAdiciones;
    }

    public void setValorAdiciones(BigDecimal valorAdiciones) {
        this.valorAdiciones = valorAdiciones;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getTipoAnticipo() {
        return tipoAnticipo;
    }

    public void setTipoAnticipo(Integer tipoAnticipo) {
        this.tipoAnticipo = tipoAnticipo;
    }

    public BigDecimal getValorAnticipo() {
        return valorAnticipo;
    }

    public void setValorAnticipo(BigDecimal valorAnticipo) {
        this.valorAnticipo = valorAnticipo;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public Integer getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(Integer tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Date getFechaSuspension() {
        return fechaSuspension;
    }

    public void setFechaSuspension(Date fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }

    public String getMotivoTerminacionAnticipada() {
        return motivoTerminacionAnticipada;
    }

    public void setMotivoTerminacionAnticipada(String motivoTerminacionAnticipada) {
        this.motivoTerminacionAnticipada = motivoTerminacionAnticipada;
    }

    public String getEnlacePublicaSecop() {
        return enlacePublicaSecop;
    }

    public void setEnlacePublicaSecop(String enlacePublicaSecop) {
        this.enlacePublicaSecop = enlacePublicaSecop;
    }

    public Date getFechaPublicaSecop() {
        return fechaPublicaSecop;
    }

    public void setFechaPublicaSecop(Date fechaPublicaSecop) {
        this.fechaPublicaSecop = fechaPublicaSecop;
    }

    public String getResponsablePublicaSecop() {
        return responsablePublicaSecop;
    }

    public void setResponsablePublicaSecop(String responsablePublicaSecop) {
        this.responsablePublicaSecop = responsablePublicaSecop;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public Integer getPeriodicidadSeguimiento() {
        return periodicidadSeguimiento;
    }

    public void setPeriodicidadSeguimiento(Integer periodicidadSeguimiento) {
        this.periodicidadSeguimiento = periodicidadSeguimiento;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<CntjContratoIndicadores> getCntjContratoIndicadoresList() {
        return cntjContratoIndicadoresList;
    }

    public void setCntjContratoIndicadoresList(List<CntjContratoIndicadores> cntjContratoIndicadoresList) {
        this.cntjContratoIndicadoresList = cntjContratoIndicadoresList;
    }

    @XmlTransient
    public List<CntjContratoObligaciones> getCntjContratoObligacionesList() {
        return cntjContratoObligacionesList;
    }

    public void setCntjContratoObligacionesList(List<CntjContratoObligaciones> cntjContratoObligacionesList) {
        this.cntjContratoObligacionesList = cntjContratoObligacionesList;
    }

    @XmlTransient
    public List<CntjContratoGarantias> getCntjContratoGarantiasList() {
        return cntjContratoGarantiasList;
    }

    public void setCntjContratoGarantiasList(List<CntjContratoGarantias> cntjContratoGarantiasList) {
        this.cntjContratoGarantiasList = cntjContratoGarantiasList;
    }

    public CntjExpedientes getCntjExpedientesId() {
        return cntjExpedientesId;
    }

    public void setCntjExpedientesId(CntjExpedientes cntjExpedientesId) {
        this.cntjExpedientesId = cntjExpedientesId;
    }

    public CntjTerceros getCntjTercerosId() {
        return cntjTercerosId;
    }

    public void setCntjTercerosId(CntjTerceros cntjTercerosId) {
        this.cntjTercerosId = cntjTercerosId;
    }

    @XmlTransient
    public List<CntjContratoInformes> getCntjContratoInformesList() {
        return cntjContratoInformesList;
    }

    public void setCntjContratoInformesList(List<CntjContratoInformes> cntjContratoInformesList) {
        this.cntjContratoInformesList = cntjContratoInformesList;
    }

    @XmlTransient
    public List<CntjContratoHistoricos> getCntjContratoHistoricosList() {
        return cntjContratoHistoricosList;
    }

    public void setCntjContratoHistoricosList(List<CntjContratoHistoricos> cntjContratoHistoricosList) {
        this.cntjContratoHistoricosList = cntjContratoHistoricosList;
    }

    @XmlTransient
    public List<CntjDocumentos> getCntjDocumentosList() {
        return cntjDocumentosList;
    }

    public void setCntjDocumentosList(List<CntjDocumentos> cntjDocumentosList) {
        this.cntjDocumentosList = cntjDocumentosList;
    }

    @XmlTransient
    public List<CntjContratoSupervisores> getCntjContratoSupervisoresList() {
        return cntjContratoSupervisoresList;
    }

    public void setCntjContratoSupervisoresList(List<CntjContratoSupervisores> cntjContratoSupervisoresList) {
        this.cntjContratoSupervisoresList = cntjContratoSupervisoresList;
    }

    @XmlTransient
    public List<CntjContratoSeguimientos> getCntjContratoSeguimientosList() {
        return cntjContratoSeguimientosList;
    }

    public void setCntjContratoSeguimientosList(List<CntjContratoSeguimientos> cntjContratoSeguimientosList) {
        this.cntjContratoSeguimientosList = cntjContratoSeguimientosList;
    }

    @XmlTransient
    public List<CntjOtrosies> getCntjOtrosiesList() {
        return cntjOtrosiesList;
    }

    public void setCntjOtrosiesList(List<CntjOtrosies> cntjOtrosiesList) {
        this.cntjOtrosiesList = cntjOtrosiesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntjContratos)) {
            return false;
        }
        CntjContratos other = (CntjContratos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratos[ id=" + id + " ]";
    }
    
}
