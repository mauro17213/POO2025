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
@Table(name = "cm_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFacturas.findAll", query = "SELECT c FROM CmFacturas c"),
    @NamedQuery(name = "CmFacturas.findById", query = "SELECT c FROM CmFacturas c WHERE c.id = :id"),
    @NamedQuery(name = "CmFacturas.findByMaeTipoContratoId", query = "SELECT c FROM CmFacturas c WHERE c.maeTipoContratoId = :maeTipoContratoId"),
    @NamedQuery(name = "CmFacturas.findByMaeTipoContratoCodigo", query = "SELECT c FROM CmFacturas c WHERE c.maeTipoContratoCodigo = :maeTipoContratoCodigo"),
    @NamedQuery(name = "CmFacturas.findByMaeTipoContratoValor", query = "SELECT c FROM CmFacturas c WHERE c.maeTipoContratoValor = :maeTipoContratoValor"),
    @NamedQuery(name = "CmFacturas.findByNit", query = "SELECT c FROM CmFacturas c WHERE c.nit = :nit"),
    @NamedQuery(name = "CmFacturas.findByIps", query = "SELECT c FROM CmFacturas c WHERE c.ips = :ips"),
    @NamedQuery(name = "CmFacturas.findByNumeroRadicado", query = "SELECT c FROM CmFacturas c WHERE c.numeroRadicado = :numeroRadicado"),
    @NamedQuery(name = "CmFacturas.findByNumeroFacturado", query = "SELECT c FROM CmFacturas c WHERE c.numeroFacturado = :numeroFacturado"),
    @NamedQuery(name = "CmFacturas.findByFechaPrestacion", query = "SELECT c FROM CmFacturas c WHERE c.fechaPrestacion = :fechaPrestacion"),
    @NamedQuery(name = "CmFacturas.findByFechaRadicacion", query = "SELECT c FROM CmFacturas c WHERE c.fechaRadicacion = :fechaRadicacion"),
    @NamedQuery(name = "CmFacturas.findByMultiusuario", query = "SELECT c FROM CmFacturas c WHERE c.multiusuario = :multiusuario"),
    @NamedQuery(name = "CmFacturas.findByValorPendienteActual", query = "SELECT c FROM CmFacturas c WHERE c.valorPendienteActual = :valorPendienteActual"),
    @NamedQuery(name = "CmFacturas.findByValorInicialGlosa", query = "SELECT c FROM CmFacturas c WHERE c.valorInicialGlosa = :valorInicialGlosa"),
    @NamedQuery(name = "CmFacturas.findByMarcacion", query = "SELECT c FROM CmFacturas c WHERE c.marcacion = :marcacion"),
    @NamedQuery(name = "CmFacturas.findByFechaMarcacion", query = "SELECT c FROM CmFacturas c WHERE c.fechaMarcacion = :fechaMarcacion"),
    @NamedQuery(name = "CmFacturas.findByFechaVencimiento", query = "SELECT c FROM CmFacturas c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "CmFacturas.findByTipoAuditoria", query = "SELECT c FROM CmFacturas c WHERE c.tipoAuditoria = :tipoAuditoria"),
    @NamedQuery(name = "CmFacturas.findByHistorialProceso", query = "SELECT c FROM CmFacturas c WHERE c.historialProceso = :historialProceso"),
    @NamedQuery(name = "CmFacturas.findByValorFactura", query = "SELECT c FROM CmFacturas c WHERE c.valorFactura = :valorFactura"),
    @NamedQuery(name = "CmFacturas.findByValorPagadoFactura", query = "SELECT c FROM CmFacturas c WHERE c.valorPagadoFactura = :valorPagadoFactura"),
    @NamedQuery(name = "CmFacturas.findByValorCopago", query = "SELECT c FROM CmFacturas c WHERE c.valorCopago = :valorCopago"),
    @NamedQuery(name = "CmFacturas.findByValorBruto", query = "SELECT c FROM CmFacturas c WHERE c.valorBruto = :valorBruto"),
    @NamedQuery(name = "CmFacturas.findByValorCuotaModeradora", query = "SELECT c FROM CmFacturas c WHERE c.valorCuotaModeradora = :valorCuotaModeradora"),
    @NamedQuery(name = "CmFacturas.findByMaeRegimenId", query = "SELECT c FROM CmFacturas c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CmFacturas.findByMaeRegimenCodigo", query = "SELECT c FROM CmFacturas c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CmFacturas.findByMaeRegimenValor", query = "SELECT c FROM CmFacturas c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CmFacturas.findByEstadoFactura", query = "SELECT c FROM CmFacturas c WHERE c.estadoFactura = :estadoFactura"),
    @NamedQuery(name = "CmFacturas.findByPbs", query = "SELECT c FROM CmFacturas c WHERE c.pbs = :pbs"),
    @NamedQuery(name = "CmFacturas.findByNumeroContrato", query = "SELECT c FROM CmFacturas c WHERE c.numeroContrato = :numeroContrato"),
    @NamedQuery(name = "CmFacturas.findByOrigenFactura", query = "SELECT c FROM CmFacturas c WHERE c.origenFactura = :origenFactura"),
    @NamedQuery(name = "CmFacturas.findByUsuarioAudita", query = "SELECT c FROM CmFacturas c WHERE c.usuarioAudita = :usuarioAudita"),
    @NamedQuery(name = "CmFacturas.findByTerminalAudita", query = "SELECT c FROM CmFacturas c WHERE c.terminalAudita = :terminalAudita"),
    @NamedQuery(name = "CmFacturas.findByFechaHoraAudita", query = "SELECT c FROM CmFacturas c WHERE c.fechaHoraAudita = :fechaHoraAudita"),
    @NamedQuery(name = "CmFacturas.findByUsuarioDevuelve", query = "SELECT c FROM CmFacturas c WHERE c.usuarioDevuelve = :usuarioDevuelve"),
    @NamedQuery(name = "CmFacturas.findByTerminalDevuelve", query = "SELECT c FROM CmFacturas c WHERE c.terminalDevuelve = :terminalDevuelve"),
    @NamedQuery(name = "CmFacturas.findByFechaHoraDevuelve", query = "SELECT c FROM CmFacturas c WHERE c.fechaHoraDevuelve = :fechaHoraDevuelve"),
    @NamedQuery(name = "CmFacturas.findByUsuarioRespuesta", query = "SELECT c FROM CmFacturas c WHERE c.usuarioRespuesta = :usuarioRespuesta"),
    @NamedQuery(name = "CmFacturas.findByTerminalRespuesta", query = "SELECT c FROM CmFacturas c WHERE c.terminalRespuesta = :terminalRespuesta"),
    @NamedQuery(name = "CmFacturas.findByFechaHoraRespuesta", query = "SELECT c FROM CmFacturas c WHERE c.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "CmFacturas.findByUsuarioConcilia", query = "SELECT c FROM CmFacturas c WHERE c.usuarioConcilia = :usuarioConcilia"),
    @NamedQuery(name = "CmFacturas.findByTerminalConcilia", query = "SELECT c FROM CmFacturas c WHERE c.terminalConcilia = :terminalConcilia"),
    @NamedQuery(name = "CmFacturas.findByFechaHoraConcilia", query = "SELECT c FROM CmFacturas c WHERE c.fechaHoraConcilia = :fechaHoraConcilia"),
    @NamedQuery(name = "CmFacturas.findByRespuestaIps", query = "SELECT c FROM CmFacturas c WHERE c.respuestaIps = :respuestaIps"),
    @NamedQuery(name = "CmFacturas.findByFechaMarcacionRespuestaIps", query = "SELECT c FROM CmFacturas c WHERE c.fechaMarcacionRespuestaIps = :fechaMarcacionRespuestaIps"),
    @NamedQuery(name = "CmFacturas.findByFechaAsignacionTecnico", query = "SELECT c FROM CmFacturas c WHERE c.fechaAsignacionTecnico = :fechaAsignacionTecnico"),
    @NamedQuery(name = "CmFacturas.findByFechaAsignacionMedico", query = "SELECT c FROM CmFacturas c WHERE c.fechaAsignacionMedico = :fechaAsignacionMedico"),
    @NamedQuery(name = "CmFacturas.findByVersion", query = "SELECT c FROM CmFacturas c WHERE c.version = :version"),
    @NamedQuery(name = "CmFacturas.findByUsuarioCrea", query = "SELECT c FROM CmFacturas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFacturas.findByTerminalCrea", query = "SELECT c FROM CmFacturas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFacturas.findByFechaHoraCrea", query = "SELECT c FROM CmFacturas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmFacturas.findByUsuarioModifica", query = "SELECT c FROM CmFacturas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmFacturas.findByTerminalModifica", query = "SELECT c FROM CmFacturas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmFacturas.findByFechaHoraModifica", query = "SELECT c FROM CmFacturas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_contrato_id")
    private int maeTipoContratoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_contrato_codigo")
    private String maeTipoContratoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_contrato_valor")
    private String maeTipoContratoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ips")
    private String ips;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_radicado")
    private int numeroRadicado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_facturado")
    private String numeroFacturado;
    @Column(name = "fecha_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_radicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "multiusuario")
    private boolean multiusuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pendiente_actual")
    private BigDecimal valorPendienteActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_inicial_glosa")
    private BigDecimal valorInicialGlosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marcacion")
    private int marcacion;
    @Column(name = "fecha_marcacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMarcacion;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_auditoria")
    private int tipoAuditoria;
    @Size(max = 512)
    @Column(name = "historial_proceso")
    private String historialProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_factura")
    private BigDecimal valorFactura;
    @Column(name = "valor_pagado_factura")
    private BigDecimal valorPagadoFactura;
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Column(name = "valor_bruto")
    private BigDecimal valorBruto;
    @Column(name = "valor_cuota_moderadora")
    private BigDecimal valorCuotaModeradora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_factura")
    private int estadoFactura;
    @Column(name = "pbs")
    private Boolean pbs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen_factura")
    private int origenFactura;
    @Size(max = 128)
    @Column(name = "usuario_audita")
    private String usuarioAudita;
    @Size(max = 16)
    @Column(name = "terminal_audita")
    private String terminalAudita;
    @Column(name = "fecha_hora_audita")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAudita;
    @Size(max = 128)
    @Column(name = "usuario_devuelve")
    private String usuarioDevuelve;
    @Size(max = 16)
    @Column(name = "terminal_devuelve")
    private String terminalDevuelve;
    @Column(name = "fecha_hora_devuelve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDevuelve;
    @Size(max = 128)
    @Column(name = "usuario_respuesta")
    private String usuarioRespuesta;
    @Size(max = 16)
    @Column(name = "terminal_respuesta")
    private String terminalRespuesta;
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;
    @Size(max = 128)
    @Column(name = "usuario_concilia")
    private String usuarioConcilia;
    @Size(max = 16)
    @Column(name = "terminal_concilia")
    private String terminalConcilia;
    @Column(name = "fecha_hora_concilia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraConcilia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "respuesta_ips")
    private boolean respuestaIps;
    @Column(name = "fecha_marcacion_respuesta_ips")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMarcacionRespuestaIps;
    @Column(name = "fecha_asignacion_tecnico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacionTecnico;
    @Column(name = "fecha_asignacion_medico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacionMedico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private boolean version;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmAuditoriaAdjuntos> cmAuditoriaAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmAuditoriaDevoluciones> cmAuditoriaDevolucionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmGlosaRespuestas> cmGlosaRespuestasList;
    @OneToMany(mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmPagoFacturas> cmPagoFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmDetalles> cmDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmFacturaTransacciones> cmFacturaTransaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmFeRipsFacturas> cmFeRipsFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmAuditoriaCierres> cmAuditoriaCierresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<WsCmFacturas> wsCmFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmHistoricoFacturas> cmHistoricoFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmFacturaEstados> cmFacturaEstadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmFeNotas> cmFeNotasList;
    @OneToMany(mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmFeSoportes> cmFeSoportesList;
    @OneToMany(mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CsCopagoModeradoraHistoricos> csCopagoModeradoraHistoricosList;
    @OneToMany(mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<CmAuditoriaAutorizaciones> cmAuditoriaAutorizacionesList;
    @JoinColumn(name = "cm_fe_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFeRipsCargas cmFeRipsCargasId;
    @JoinColumn(name = "cm_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmGrupos cmGruposId;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_usuarios_lider_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosLiderId;
    @JoinColumn(name = "gn_usuarios_medico_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosMedicoId;
    @JoinColumn(name = "gn_usuarios_tecnico_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosTecnicoId;
    @JoinColumn(name = "gn_usuarios_gestiona_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosGestionaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFacturasId", fetch = FetchType.LAZY)
    private List<RcoFacturas> rcoFacturasList;

    public CmFacturas() {
    }

    public CmFacturas(Integer id) {
        this.id = id;
    }

    public CmFacturas(Integer id, int maeTipoContratoId, String nit, String ips, int numeroRadicado, String numeroFacturado, Date fechaRadicacion, boolean multiusuario, BigDecimal valorInicialGlosa, int marcacion, int tipoAuditoria, BigDecimal valorFactura, int maeRegimenId, int estadoFactura, String numeroContrato, int origenFactura, boolean respuestaIps, boolean version, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoContratoId = maeTipoContratoId;
        this.nit = nit;
        this.ips = ips;
        this.numeroRadicado = numeroRadicado;
        this.numeroFacturado = numeroFacturado;
        this.fechaRadicacion = fechaRadicacion;
        this.multiusuario = multiusuario;
        this.valorInicialGlosa = valorInicialGlosa;
        this.marcacion = marcacion;
        this.tipoAuditoria = tipoAuditoria;
        this.valorFactura = valorFactura;
        this.maeRegimenId = maeRegimenId;
        this.estadoFactura = estadoFactura;
        this.numeroContrato = numeroContrato;
        this.origenFactura = origenFactura;
        this.respuestaIps = respuestaIps;
        this.version = version;
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

    public int getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(int maeTipoContratoId) {
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public boolean getMultiusuario() {
        return multiusuario;
    }

    public void setMultiusuario(boolean multiusuario) {
        this.multiusuario = multiusuario;
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public BigDecimal getValorInicialGlosa() {
        return valorInicialGlosa;
    }

    public void setValorInicialGlosa(BigDecimal valorInicialGlosa) {
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public int getMarcacion() {
        return marcacion;
    }

    public void setMarcacion(int marcacion) {
        this.marcacion = marcacion;
    }

    public Date getFechaMarcacion() {
        return fechaMarcacion;
    }

    public void setFechaMarcacion(Date fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(int tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }

    public String getHistorialProceso() {
        return historialProceso;
    }

    public void setHistorialProceso(String historialProceso) {
        this.historialProceso = historialProceso;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public BigDecimal getValorPagadoFactura() {
        return valorPagadoFactura;
    }

    public void setValorPagadoFactura(BigDecimal valorPagadoFactura) {
        this.valorPagadoFactura = valorPagadoFactura;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
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

    public int getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(int estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Boolean getPbs() {
        return pbs;
    }

    public void setPbs(Boolean pbs) {
        this.pbs = pbs;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public int getOrigenFactura() {
        return origenFactura;
    }

    public void setOrigenFactura(int origenFactura) {
        this.origenFactura = origenFactura;
    }

    public String getUsuarioAudita() {
        return usuarioAudita;
    }

    public void setUsuarioAudita(String usuarioAudita) {
        this.usuarioAudita = usuarioAudita;
    }

    public String getTerminalAudita() {
        return terminalAudita;
    }

    public void setTerminalAudita(String terminalAudita) {
        this.terminalAudita = terminalAudita;
    }

    public Date getFechaHoraAudita() {
        return fechaHoraAudita;
    }

    public void setFechaHoraAudita(Date fechaHoraAudita) {
        this.fechaHoraAudita = fechaHoraAudita;
    }

    public String getUsuarioDevuelve() {
        return usuarioDevuelve;
    }

    public void setUsuarioDevuelve(String usuarioDevuelve) {
        this.usuarioDevuelve = usuarioDevuelve;
    }

    public String getTerminalDevuelve() {
        return terminalDevuelve;
    }

    public void setTerminalDevuelve(String terminalDevuelve) {
        this.terminalDevuelve = terminalDevuelve;
    }

    public Date getFechaHoraDevuelve() {
        return fechaHoraDevuelve;
    }

    public void setFechaHoraDevuelve(Date fechaHoraDevuelve) {
        this.fechaHoraDevuelve = fechaHoraDevuelve;
    }

    public String getUsuarioRespuesta() {
        return usuarioRespuesta;
    }

    public void setUsuarioRespuesta(String usuarioRespuesta) {
        this.usuarioRespuesta = usuarioRespuesta;
    }

    public String getTerminalRespuesta() {
        return terminalRespuesta;
    }

    public void setTerminalRespuesta(String terminalRespuesta) {
        this.terminalRespuesta = terminalRespuesta;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public String getUsuarioConcilia() {
        return usuarioConcilia;
    }

    public void setUsuarioConcilia(String usuarioConcilia) {
        this.usuarioConcilia = usuarioConcilia;
    }

    public String getTerminalConcilia() {
        return terminalConcilia;
    }

    public void setTerminalConcilia(String terminalConcilia) {
        this.terminalConcilia = terminalConcilia;
    }

    public Date getFechaHoraConcilia() {
        return fechaHoraConcilia;
    }

    public void setFechaHoraConcilia(Date fechaHoraConcilia) {
        this.fechaHoraConcilia = fechaHoraConcilia;
    }

    public boolean getRespuestaIps() {
        return respuestaIps;
    }

    public void setRespuestaIps(boolean respuestaIps) {
        this.respuestaIps = respuestaIps;
    }

    public Date getFechaMarcacionRespuestaIps() {
        return fechaMarcacionRespuestaIps;
    }

    public void setFechaMarcacionRespuestaIps(Date fechaMarcacionRespuestaIps) {
        this.fechaMarcacionRespuestaIps = fechaMarcacionRespuestaIps;
    }

    public Date getFechaAsignacionTecnico() {
        return fechaAsignacionTecnico;
    }

    public void setFechaAsignacionTecnico(Date fechaAsignacionTecnico) {
        this.fechaAsignacionTecnico = fechaAsignacionTecnico;
    }

    public Date getFechaAsignacionMedico() {
        return fechaAsignacionMedico;
    }

    public void setFechaAsignacionMedico(Date fechaAsignacionMedico) {
        this.fechaAsignacionMedico = fechaAsignacionMedico;
    }

    public boolean getVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
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
    public List<CmAuditoriaAdjuntos> getCmAuditoriaAdjuntosList() {
        return cmAuditoriaAdjuntosList;
    }

    public void setCmAuditoriaAdjuntosList(List<CmAuditoriaAdjuntos> cmAuditoriaAdjuntosList) {
        this.cmAuditoriaAdjuntosList = cmAuditoriaAdjuntosList;
    }

    @XmlTransient
    public List<CmAuditoriaDevoluciones> getCmAuditoriaDevolucionesList() {
        return cmAuditoriaDevolucionesList;
    }

    public void setCmAuditoriaDevolucionesList(List<CmAuditoriaDevoluciones> cmAuditoriaDevolucionesList) {
        this.cmAuditoriaDevolucionesList = cmAuditoriaDevolucionesList;
    }

    @XmlTransient
    public List<CmGlosaRespuestas> getCmGlosaRespuestasList() {
        return cmGlosaRespuestasList;
    }

    public void setCmGlosaRespuestasList(List<CmGlosaRespuestas> cmGlosaRespuestasList) {
        this.cmGlosaRespuestasList = cmGlosaRespuestasList;
    }

    @XmlTransient
    public List<CmPagoFacturas> getCmPagoFacturasList() {
        return cmPagoFacturasList;
    }

    public void setCmPagoFacturasList(List<CmPagoFacturas> cmPagoFacturasList) {
        this.cmPagoFacturasList = cmPagoFacturasList;
    }

    @XmlTransient
    public List<CmDetalles> getCmDetallesList() {
        return cmDetallesList;
    }

    public void setCmDetallesList(List<CmDetalles> cmDetallesList) {
        this.cmDetallesList = cmDetallesList;
    }

    @XmlTransient
    public List<CmFacturaTransacciones> getCmFacturaTransaccionesList() {
        return cmFacturaTransaccionesList;
    }

    public void setCmFacturaTransaccionesList(List<CmFacturaTransacciones> cmFacturaTransaccionesList) {
        this.cmFacturaTransaccionesList = cmFacturaTransaccionesList;
    }

    @XmlTransient
    public List<CmFeRipsFacturas> getCmFeRipsFacturasList() {
        return cmFeRipsFacturasList;
    }

    public void setCmFeRipsFacturasList(List<CmFeRipsFacturas> cmFeRipsFacturasList) {
        this.cmFeRipsFacturasList = cmFeRipsFacturasList;
    }

    @XmlTransient
    public List<CmAuditoriaCierres> getCmAuditoriaCierresList() {
        return cmAuditoriaCierresList;
    }

    public void setCmAuditoriaCierresList(List<CmAuditoriaCierres> cmAuditoriaCierresList) {
        this.cmAuditoriaCierresList = cmAuditoriaCierresList;
    }

    @XmlTransient
    public List<WsCmFacturas> getWsCmFacturasList() {
        return wsCmFacturasList;
    }

    public void setWsCmFacturasList(List<WsCmFacturas> wsCmFacturasList) {
        this.wsCmFacturasList = wsCmFacturasList;
    }

    @XmlTransient
    public List<CmHistoricoFacturas> getCmHistoricoFacturasList() {
        return cmHistoricoFacturasList;
    }

    public void setCmHistoricoFacturasList(List<CmHistoricoFacturas> cmHistoricoFacturasList) {
        this.cmHistoricoFacturasList = cmHistoricoFacturasList;
    }

    @XmlTransient
    public List<CmFacturaEstados> getCmFacturaEstadosList() {
        return cmFacturaEstadosList;
    }

    public void setCmFacturaEstadosList(List<CmFacturaEstados> cmFacturaEstadosList) {
        this.cmFacturaEstadosList = cmFacturaEstadosList;
    }

    @XmlTransient
    public List<CmFeNotas> getCmFeNotasList() {
        return cmFeNotasList;
    }

    public void setCmFeNotasList(List<CmFeNotas> cmFeNotasList) {
        this.cmFeNotasList = cmFeNotasList;
    }

    @XmlTransient
    public List<CmFeSoportes> getCmFeSoportesList() {
        return cmFeSoportesList;
    }

    public void setCmFeSoportesList(List<CmFeSoportes> cmFeSoportesList) {
        this.cmFeSoportesList = cmFeSoportesList;
    }

    @XmlTransient
    public List<CsCopagoModeradoraHistoricos> getCsCopagoModeradoraHistoricosList() {
        return csCopagoModeradoraHistoricosList;
    }

    public void setCsCopagoModeradoraHistoricosList(List<CsCopagoModeradoraHistoricos> csCopagoModeradoraHistoricosList) {
        this.csCopagoModeradoraHistoricosList = csCopagoModeradoraHistoricosList;
    }

    @XmlTransient
    public List<CmAuditoriaAutorizaciones> getCmAuditoriaAutorizacionesList() {
        return cmAuditoriaAutorizacionesList;
    }

    public void setCmAuditoriaAutorizacionesList(List<CmAuditoriaAutorizaciones> cmAuditoriaAutorizacionesList) {
        this.cmAuditoriaAutorizacionesList = cmAuditoriaAutorizacionesList;
    }

    public CmFeRipsCargas getCmFeRipsCargasId() {
        return cmFeRipsCargasId;
    }

    public void setCmFeRipsCargasId(CmFeRipsCargas cmFeRipsCargasId) {
        this.cmFeRipsCargasId = cmFeRipsCargasId;
    }

    public CmGrupos getCmGruposId() {
        return cmGruposId;
    }

    public void setCmGruposId(CmGrupos cmGruposId) {
        this.cmGruposId = cmGruposId;
    }

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnUsuarios getGnUsuariosLiderId() {
        return gnUsuariosLiderId;
    }

    public void setGnUsuariosLiderId(GnUsuarios gnUsuariosLiderId) {
        this.gnUsuariosLiderId = gnUsuariosLiderId;
    }

    public GnUsuarios getGnUsuariosMedicoId() {
        return gnUsuariosMedicoId;
    }

    public void setGnUsuariosMedicoId(GnUsuarios gnUsuariosMedicoId) {
        this.gnUsuariosMedicoId = gnUsuariosMedicoId;
    }

    public GnUsuarios getGnUsuariosTecnicoId() {
        return gnUsuariosTecnicoId;
    }

    public void setGnUsuariosTecnicoId(GnUsuarios gnUsuariosTecnicoId) {
        this.gnUsuariosTecnicoId = gnUsuariosTecnicoId;
    }

    public GnUsuarios getGnUsuariosGestionaId() {
        return gnUsuariosGestionaId;
    }

    public void setGnUsuariosGestionaId(GnUsuarios gnUsuariosGestionaId) {
        this.gnUsuariosGestionaId = gnUsuariosGestionaId;
    }

    @XmlTransient
    public List<RcoFacturas> getRcoFacturasList() {
        return rcoFacturasList;
    }

    public void setRcoFacturasList(List<RcoFacturas> rcoFacturasList) {
        this.rcoFacturasList = rcoFacturasList;
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
        if (!(object instanceof CmFacturas)) {
            return false;
        }
        CmFacturas other = (CmFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFacturas[ id=" + id + " ]";
    }
    
}
