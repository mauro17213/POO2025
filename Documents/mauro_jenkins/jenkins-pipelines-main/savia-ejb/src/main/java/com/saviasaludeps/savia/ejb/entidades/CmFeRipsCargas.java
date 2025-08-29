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
@Table(name = "cm_fe_rips_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeRipsCargas.findAll", query = "SELECT c FROM CmFeRipsCargas c"),
    @NamedQuery(name = "CmFeRipsCargas.findById", query = "SELECT c FROM CmFeRipsCargas c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeRipsCargas.findByTipo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmFeRipsCargas.findByContrato", query = "SELECT c FROM CmFeRipsCargas c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CmFeRipsCargas.findByCntTipoContratoId", query = "SELECT c FROM CmFeRipsCargas c WHERE c.cntTipoContratoId = :cntTipoContratoId"),
    @NamedQuery(name = "CmFeRipsCargas.findByEstado", query = "SELECT c FROM CmFeRipsCargas c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeRegimenId", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeRegimenCodigo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeRegimenValor", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeContratoModalidadId", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeContratoModalidadId = :maeContratoModalidadId"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeContratoModalidadCodigo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeContratoModalidadCodigo = :maeContratoModalidadCodigo"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeContratoModalidadValor", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeContratoModalidadValor = :maeContratoModalidadValor"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraInicio", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraFin", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CmFeRipsCargas.findByTiempo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.tiempo = :tiempo"),
    @NamedQuery(name = "CmFeRipsCargas.findByCobertura", query = "SELECT c FROM CmFeRipsCargas c WHERE c.cobertura = :cobertura"),
    @NamedQuery(name = "CmFeRipsCargas.findByFacturaNumero", query = "SELECT c FROM CmFeRipsCargas c WHERE c.facturaNumero = :facturaNumero"),
    @NamedQuery(name = "CmFeRipsCargas.findByFacturaValor", query = "SELECT c FROM CmFeRipsCargas c WHERE c.facturaValor = :facturaValor"),
    @NamedQuery(name = "CmFeRipsCargas.findBySoportesNumero", query = "SELECT c FROM CmFeRipsCargas c WHERE c.soportesNumero = :soportesNumero"),
    @NamedQuery(name = "CmFeRipsCargas.findByRechazo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.rechazo = :rechazo"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraRechazo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraRechazo = :fechaHoraRechazo"),
    @NamedQuery(name = "CmFeRipsCargas.findByObservacionRechazo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.observacionRechazo = :observacionRechazo"),
    @NamedQuery(name = "CmFeRipsCargas.findByDevolucion", query = "SELECT c FROM CmFeRipsCargas c WHERE c.devolucion = :devolucion"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraDevolucion", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraDevolucion = :fechaHoraDevolucion"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeDevolucionId", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeDevolucionId = :maeDevolucionId"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeDevolucionCodigo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeDevolucionCodigo = :maeDevolucionCodigo"),
    @NamedQuery(name = "CmFeRipsCargas.findByMaeDevolucionValor", query = "SELECT c FROM CmFeRipsCargas c WHERE c.maeDevolucionValor = :maeDevolucionValor"),
    @NamedQuery(name = "CmFeRipsCargas.findByObservacionDevolucion", query = "SELECT c FROM CmFeRipsCargas c WHERE c.observacionDevolucion = :observacionDevolucion"),
    @NamedQuery(name = "CmFeRipsCargas.findByObservacion", query = "SELECT c FROM CmFeRipsCargas c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CmFeRipsCargas.findByDocumentoPrestador", query = "SELECT c FROM CmFeRipsCargas c WHERE c.documentoPrestador = :documentoPrestador"),
    @NamedQuery(name = "CmFeRipsCargas.findByCuv", query = "SELECT c FROM CmFeRipsCargas c WHERE c.cuv = :cuv"),
    @NamedQuery(name = "CmFeRipsCargas.findByCufe", query = "SELECT c FROM CmFeRipsCargas c WHERE c.cufe = :cufe"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe1601EpsErronea", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de1601EpsErronea = :de1601EpsErronea"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe4401ProfesionalRed", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de4401ProfesionalRed = :de4401ProfesionalRed"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe4402ProfesionalIndependiente", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de4402ProfesionalIndependiente = :de4402ProfesionalIndependiente"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe5001Pagada", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de5001Pagada = :de5001Pagada"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe5002Radicada", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de5002Radicada = :de5002Radicada"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe5601SoporteFe", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de5601SoporteFe = :de5601SoporteFe"),
    @NamedQuery(name = "CmFeRipsCargas.findByDe5601Soportes", query = "SELECT c FROM CmFeRipsCargas c WHERE c.de5601Soportes = :de5601Soportes"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraEmision", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraEmision = :fechaHoraEmision"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraMinisterio", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraMinisterio = :fechaHoraMinisterio"),
    @NamedQuery(name = "CmFeRipsCargas.findByOrigenCarga", query = "SELECT c FROM CmFeRipsCargas c WHERE c.origenCarga = :origenCarga"),
    @NamedQuery(name = "CmFeRipsCargas.findByUrlXml", query = "SELECT c FROM CmFeRipsCargas c WHERE c.urlXml = :urlXml"),
    @NamedQuery(name = "CmFeRipsCargas.findByUrlJson", query = "SELECT c FROM CmFeRipsCargas c WHERE c.urlJson = :urlJson"),
    @NamedQuery(name = "CmFeRipsCargas.findByValorCopago", query = "SELECT c FROM CmFeRipsCargas c WHERE c.valorCopago = :valorCopago"),
    @NamedQuery(name = "CmFeRipsCargas.findByValorCuotaModeradora", query = "SELECT c FROM CmFeRipsCargas c WHERE c.valorCuotaModeradora = :valorCuotaModeradora"),
    @NamedQuery(name = "CmFeRipsCargas.findByOrigen", query = "SELECT c FROM CmFeRipsCargas c WHERE c.origen = :origen"),
    @NamedQuery(name = "CmFeRipsCargas.findByCapitaPeriodo", query = "SELECT c FROM CmFeRipsCargas c WHERE c.capitaPeriodo = :capitaPeriodo"),
    @NamedQuery(name = "CmFeRipsCargas.findByNumeroNota", query = "SELECT c FROM CmFeRipsCargas c WHERE c.numeroNota = :numeroNota"),
    @NamedQuery(name = "CmFeRipsCargas.findBySoporteMercurio", query = "SELECT c FROM CmFeRipsCargas c WHERE c.soporteMercurio = :soporteMercurio"),
    @NamedQuery(name = "CmFeRipsCargas.findByMultiusuario", query = "SELECT c FROM CmFeRipsCargas c WHERE c.multiusuario = :multiusuario"),
    @NamedQuery(name = "CmFeRipsCargas.findByUsuarioCrea", query = "SELECT c FROM CmFeRipsCargas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFeRipsCargas.findByTerminalCrea", query = "SELECT c FROM CmFeRipsCargas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraCrea", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmFeRipsCargas.findByUsuarioModifica", query = "SELECT c FROM CmFeRipsCargas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmFeRipsCargas.findByTerminalModifica", query = "SELECT c FROM CmFeRipsCargas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmFeRipsCargas.findByFechaHoraModifica", query = "SELECT c FROM CmFeRipsCargas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmFeRipsCargas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 32)
    @Column(name = "contrato")
    private String contrato;
    @Column(name = "cnt_tipo_contrato_id")
    private Integer cntTipoContratoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "mae_regimen_id")
    private Integer maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Column(name = "mae_contrato_modalidad_id")
    private Integer maeContratoModalidadId;
    @Size(max = 8)
    @Column(name = "mae_contrato_modalidad_codigo")
    private String maeContratoModalidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_contrato_modalidad_valor")
    private String maeContratoModalidadValor;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tiempo")
    private Integer tiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cobertura")
    private boolean cobertura;
    @Size(max = 16)
    @Column(name = "factura_numero")
    private String facturaNumero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "factura_valor")
    private BigDecimal facturaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soportes_numero")
    private int soportesNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rechazo")
    private boolean rechazo;
    @Column(name = "fecha_hora_rechazo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRechazo;
    @Size(max = 512)
    @Column(name = "observacion_rechazo")
    private String observacionRechazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "devolucion")
    private boolean devolucion;
    @Column(name = "fecha_hora_devolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDevolucion;
    @Column(name = "mae_devolucion_id")
    private Integer maeDevolucionId;
    @Size(max = 8)
    @Column(name = "mae_devolucion_codigo")
    private String maeDevolucionCodigo;
    @Size(max = 128)
    @Column(name = "mae_devolucion_valor")
    private String maeDevolucionValor;
    @Size(max = 512)
    @Column(name = "observacion_devolucion")
    private String observacionDevolucion;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "documento_prestador")
    private String documentoPrestador;
    @Size(max = 128)
    @Column(name = "cuv")
    private String cuv;
    @Size(max = 128)
    @Column(name = "cufe")
    private String cufe;
    @Column(name = "de1601_eps_erronea")
    private Boolean de1601EpsErronea;
    @Column(name = "de4401_profesional_red")
    private Boolean de4401ProfesionalRed;
    @Column(name = "de4402_profesional_independiente")
    private Boolean de4402ProfesionalIndependiente;
    @Column(name = "de5001_pagada")
    private Boolean de5001Pagada;
    @Column(name = "de5002_radicada")
    private Boolean de5002Radicada;
    @Column(name = "de5601_soporte_fe")
    private Boolean de5601SoporteFe;
    @Column(name = "de5601_soportes")
    private Boolean de5601Soportes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_emision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEmision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_ministerio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraMinisterio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen_carga")
    private int origenCarga;
    @Size(max = 512)
    @Column(name = "url_xml")
    private String urlXml;
    @Size(max = 512)
    @Column(name = "url_json")
    private String urlJson;
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Column(name = "valor_cuota_moderadora")
    private BigDecimal valorCuotaModeradora;
    @Column(name = "origen")
    private Integer origen;
    @Column(name = "capita_periodo")
    private Integer capitaPeriodo;
    @Size(max = 16)
    @Column(name = "numero_nota")
    private String numeroNota;
    @Column(name = "soporte_mercurio")
    private Short soporteMercurio;
    @Column(name = "multiusuario")
    private Boolean multiusuario;
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
    @OneToMany(mappedBy = "cargaPeriodoId", fetch = FetchType.LAZY)
    private List<CmFeRipsCargas> cmFeRipsCargasList;
    @JoinColumn(name = "carga_periodo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFeRipsCargas cargaPeriodoId;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "radicador_asignado", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios radicadorAsignado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFeTransacciones> cmFeTransaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFeRipsFacturas> cmFeRipsFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFeRipsCargaAdjuntos> cmFeRipsCargaAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFeRipsCargaContenidos> cmFeRipsCargaContenidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFeSoportes> cmFeSoportesList;
    @OneToMany(mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList;
    @OneToMany(mappedBy = "cmFeRipsCargasId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;

    public CmFeRipsCargas() {
    }

    public CmFeRipsCargas(Integer id) {
        this.id = id;
    }

    public CmFeRipsCargas(Integer id, int tipo, int estado, boolean cobertura, int soportesNumero, boolean rechazo, boolean devolucion, String documentoPrestador, Date fechaHoraEmision, Date fechaHoraMinisterio, int origenCarga, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.cobertura = cobertura;
        this.soportesNumero = soportesNumero;
        this.rechazo = rechazo;
        this.devolucion = devolucion;
        this.documentoPrestador = documentoPrestador;
        this.fechaHoraEmision = fechaHoraEmision;
        this.fechaHoraMinisterio = fechaHoraMinisterio;
        this.origenCarga = origenCarga;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getCntTipoContratoId() {
        return cntTipoContratoId;
    }

    public void setCntTipoContratoId(Integer cntTipoContratoId) {
        this.cntTipoContratoId = cntTipoContratoId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Integer getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(Integer maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public boolean getCobertura() {
        return cobertura;
    }

    public void setCobertura(boolean cobertura) {
        this.cobertura = cobertura;
    }

    public String getFacturaNumero() {
        return facturaNumero;
    }

    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }

    public BigDecimal getFacturaValor() {
        return facturaValor;
    }

    public void setFacturaValor(BigDecimal facturaValor) {
        this.facturaValor = facturaValor;
    }

    public int getSoportesNumero() {
        return soportesNumero;
    }

    public void setSoportesNumero(int soportesNumero) {
        this.soportesNumero = soportesNumero;
    }

    public boolean getRechazo() {
        return rechazo;
    }

    public void setRechazo(boolean rechazo) {
        this.rechazo = rechazo;
    }

    public Date getFechaHoraRechazo() {
        return fechaHoraRechazo;
    }

    public void setFechaHoraRechazo(Date fechaHoraRechazo) {
        this.fechaHoraRechazo = fechaHoraRechazo;
    }

    public String getObservacionRechazo() {
        return observacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        this.observacionRechazo = observacionRechazo;
    }

    public boolean getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public Date getFechaHoraDevolucion() {
        return fechaHoraDevolucion;
    }

    public void setFechaHoraDevolucion(Date fechaHoraDevolucion) {
        this.fechaHoraDevolucion = fechaHoraDevolucion;
    }

    public Integer getMaeDevolucionId() {
        return maeDevolucionId;
    }

    public void setMaeDevolucionId(Integer maeDevolucionId) {
        this.maeDevolucionId = maeDevolucionId;
    }

    public String getMaeDevolucionCodigo() {
        return maeDevolucionCodigo;
    }

    public void setMaeDevolucionCodigo(String maeDevolucionCodigo) {
        this.maeDevolucionCodigo = maeDevolucionCodigo;
    }

    public String getMaeDevolucionValor() {
        return maeDevolucionValor;
    }

    public void setMaeDevolucionValor(String maeDevolucionValor) {
        this.maeDevolucionValor = maeDevolucionValor;
    }

    public String getObservacionDevolucion() {
        return observacionDevolucion;
    }

    public void setObservacionDevolucion(String observacionDevolucion) {
        this.observacionDevolucion = observacionDevolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDocumentoPrestador() {
        return documentoPrestador;
    }

    public void setDocumentoPrestador(String documentoPrestador) {
        this.documentoPrestador = documentoPrestador;
    }

    public String getCuv() {
        return cuv;
    }

    public void setCuv(String cuv) {
        this.cuv = cuv;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public Boolean getDe1601EpsErronea() {
        return de1601EpsErronea;
    }

    public void setDe1601EpsErronea(Boolean de1601EpsErronea) {
        this.de1601EpsErronea = de1601EpsErronea;
    }

    public Boolean getDe4401ProfesionalRed() {
        return de4401ProfesionalRed;
    }

    public void setDe4401ProfesionalRed(Boolean de4401ProfesionalRed) {
        this.de4401ProfesionalRed = de4401ProfesionalRed;
    }

    public Boolean getDe4402ProfesionalIndependiente() {
        return de4402ProfesionalIndependiente;
    }

    public void setDe4402ProfesionalIndependiente(Boolean de4402ProfesionalIndependiente) {
        this.de4402ProfesionalIndependiente = de4402ProfesionalIndependiente;
    }

    public Boolean getDe5001Pagada() {
        return de5001Pagada;
    }

    public void setDe5001Pagada(Boolean de5001Pagada) {
        this.de5001Pagada = de5001Pagada;
    }

    public Boolean getDe5002Radicada() {
        return de5002Radicada;
    }

    public void setDe5002Radicada(Boolean de5002Radicada) {
        this.de5002Radicada = de5002Radicada;
    }

    public Boolean getDe5601SoporteFe() {
        return de5601SoporteFe;
    }

    public void setDe5601SoporteFe(Boolean de5601SoporteFe) {
        this.de5601SoporteFe = de5601SoporteFe;
    }

    public Boolean getDe5601Soportes() {
        return de5601Soportes;
    }

    public void setDe5601Soportes(Boolean de5601Soportes) {
        this.de5601Soportes = de5601Soportes;
    }

    public Date getFechaHoraEmision() {
        return fechaHoraEmision;
    }

    public void setFechaHoraEmision(Date fechaHoraEmision) {
        this.fechaHoraEmision = fechaHoraEmision;
    }

    public Date getFechaHoraMinisterio() {
        return fechaHoraMinisterio;
    }

    public void setFechaHoraMinisterio(Date fechaHoraMinisterio) {
        this.fechaHoraMinisterio = fechaHoraMinisterio;
    }

    public int getOrigenCarga() {
        return origenCarga;
    }

    public void setOrigenCarga(int origenCarga) {
        this.origenCarga = origenCarga;
    }

    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }

    public String getUrlJson() {
        return urlJson;
    }

    public void setUrlJson(String urlJson) {
        this.urlJson = urlJson;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Integer getCapitaPeriodo() {
        return capitaPeriodo;
    }

    public void setCapitaPeriodo(Integer capitaPeriodo) {
        this.capitaPeriodo = capitaPeriodo;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public Short getSoporteMercurio() {
        return soporteMercurio;
    }

    public void setSoporteMercurio(Short soporteMercurio) {
        this.soporteMercurio = soporteMercurio;
    }

    public Boolean getMultiusuario() {
        return multiusuario;
    }

    public void setMultiusuario(Boolean multiusuario) {
        this.multiusuario = multiusuario;
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
    public List<CmFeRipsCargas> getCmFeRipsCargasList() {
        return cmFeRipsCargasList;
    }

    public void setCmFeRipsCargasList(List<CmFeRipsCargas> cmFeRipsCargasList) {
        this.cmFeRipsCargasList = cmFeRipsCargasList;
    }

    public CmFeRipsCargas getCargaPeriodoId() {
        return cargaPeriodoId;
    }

    public void setCargaPeriodoId(CmFeRipsCargas cargaPeriodoId) {
        this.cargaPeriodoId = cargaPeriodoId;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnUsuarios getRadicadorAsignado() {
        return radicadorAsignado;
    }

    public void setRadicadorAsignado(GnUsuarios radicadorAsignado) {
        this.radicadorAsignado = radicadorAsignado;
    }

    @XmlTransient
    public List<CmFeTransacciones> getCmFeTransaccionesList() {
        return cmFeTransaccionesList;
    }

    public void setCmFeTransaccionesList(List<CmFeTransacciones> cmFeTransaccionesList) {
        this.cmFeTransaccionesList = cmFeTransaccionesList;
    }

    @XmlTransient
    public List<CmFeRipsFacturas> getCmFeRipsFacturasList() {
        return cmFeRipsFacturasList;
    }

    public void setCmFeRipsFacturasList(List<CmFeRipsFacturas> cmFeRipsFacturasList) {
        this.cmFeRipsFacturasList = cmFeRipsFacturasList;
    }

    @XmlTransient
    public List<CmFeRipsCargaAdjuntos> getCmFeRipsCargaAdjuntosList() {
        return cmFeRipsCargaAdjuntosList;
    }

    public void setCmFeRipsCargaAdjuntosList(List<CmFeRipsCargaAdjuntos> cmFeRipsCargaAdjuntosList) {
        this.cmFeRipsCargaAdjuntosList = cmFeRipsCargaAdjuntosList;
    }

    @XmlTransient
    public List<CmFeRipsCargaContenidos> getCmFeRipsCargaContenidosList() {
        return cmFeRipsCargaContenidosList;
    }

    public void setCmFeRipsCargaContenidosList(List<CmFeRipsCargaContenidos> cmFeRipsCargaContenidosList) {
        this.cmFeRipsCargaContenidosList = cmFeRipsCargaContenidosList;
    }

    @XmlTransient
    public List<CmFeSoportes> getCmFeSoportesList() {
        return cmFeSoportesList;
    }

    public void setCmFeSoportesList(List<CmFeSoportes> cmFeSoportesList) {
        this.cmFeSoportesList = cmFeSoportesList;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList() {
        return cmFacturasList;
    }

    public void setCmFacturasList(List<CmFacturas> cmFacturasList) {
        this.cmFacturasList = cmFacturasList;
    }

    @XmlTransient
    public List<CmRadicados> getCmRadicadosList() {
        return cmRadicadosList;
    }

    public void setCmRadicadosList(List<CmRadicados> cmRadicadosList) {
        this.cmRadicadosList = cmRadicadosList;
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
        if (!(object instanceof CmFeRipsCargas)) {
            return false;
        }
        CmFeRipsCargas other = (CmFeRipsCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargas[ id=" + id + " ]";
    }
    
}
