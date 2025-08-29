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
@Table(name = "cm_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmDetalles.findAll", query = "SELECT c FROM CmDetalles c"),
    @NamedQuery(name = "CmDetalles.findById", query = "SELECT c FROM CmDetalles c WHERE c.id = :id"),
    @NamedQuery(name = "CmDetalles.findByEstado", query = "SELECT c FROM CmDetalles c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmDetalles.findByMaeTipoDocumentoId", query = "SELECT c FROM CmDetalles c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmDetalles.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmDetalles c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmDetalles.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmDetalles c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmDetalles.findByDocumento", query = "SELECT c FROM CmDetalles c WHERE c.documento = :documento"),
    @NamedQuery(name = "CmDetalles.findByNombreCompletoAfiliado", query = "SELECT c FROM CmDetalles c WHERE c.nombreCompletoAfiliado = :nombreCompletoAfiliado"),
    @NamedQuery(name = "CmDetalles.findByConsecutivoItem", query = "SELECT c FROM CmDetalles c WHERE c.consecutivoItem = :consecutivoItem"),
    @NamedQuery(name = "CmDetalles.findByMaServicioId", query = "SELECT c FROM CmDetalles c WHERE c.maServicioId = :maServicioId"),
    @NamedQuery(name = "CmDetalles.findByMaServicioCodigo", query = "SELECT c FROM CmDetalles c WHERE c.maServicioCodigo = :maServicioCodigo"),
    @NamedQuery(name = "CmDetalles.findByMaServicioValor", query = "SELECT c FROM CmDetalles c WHERE c.maServicioValor = :maServicioValor"),
    @NamedQuery(name = "CmDetalles.findByCantidad", query = "SELECT c FROM CmDetalles c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "CmDetalles.findByRadicadoGlosa", query = "SELECT c FROM CmDetalles c WHERE c.radicadoGlosa = :radicadoGlosa"),
    @NamedQuery(name = "CmDetalles.findByValorCopago", query = "SELECT c FROM CmDetalles c WHERE c.valorCopago = :valorCopago"),
    @NamedQuery(name = "CmDetalles.findByValorFacturado", query = "SELECT c FROM CmDetalles c WHERE c.valorFacturado = :valorFacturado"),
    @NamedQuery(name = "CmDetalles.findByValorUnitario", query = "SELECT c FROM CmDetalles c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "CmDetalles.findByValorPagado", query = "SELECT c FROM CmDetalles c WHERE c.valorPagado = :valorPagado"),
    @NamedQuery(name = "CmDetalles.findByValorPendiente", query = "SELECT c FROM CmDetalles c WHERE c.valorPendiente = :valorPendiente"),
    @NamedQuery(name = "CmDetalles.findByValorPendienteActual", query = "SELECT c FROM CmDetalles c WHERE c.valorPendienteActual = :valorPendienteActual"),
    @NamedQuery(name = "CmDetalles.findByValorAceptadoIps", query = "SELECT c FROM CmDetalles c WHERE c.valorAceptadoIps = :valorAceptadoIps"),
    @NamedQuery(name = "CmDetalles.findByValorPagadoEps", query = "SELECT c FROM CmDetalles c WHERE c.valorPagadoEps = :valorPagadoEps"),
    @NamedQuery(name = "CmDetalles.findByPorcentajePagadoEps", query = "SELECT c FROM CmDetalles c WHERE c.porcentajePagadoEps = :porcentajePagadoEps"),
    @NamedQuery(name = "CmDetalles.findByPorcentajeAceptadoIps", query = "SELECT c FROM CmDetalles c WHERE c.porcentajeAceptadoIps = :porcentajeAceptadoIps"),
    @NamedQuery(name = "CmDetalles.findByAplicaAc", query = "SELECT c FROM CmDetalles c WHERE c.aplicaAc = :aplicaAc"),
    @NamedQuery(name = "CmDetalles.findByAplicaDc", query = "SELECT c FROM CmDetalles c WHERE c.aplicaDc = :aplicaDc"),
    @NamedQuery(name = "CmDetalles.findByAplicaPbs", query = "SELECT c FROM CmDetalles c WHERE c.aplicaPbs = :aplicaPbs"),
    @NamedQuery(name = "CmDetalles.findByTipoServicio", query = "SELECT c FROM CmDetalles c WHERE c.tipoServicio = :tipoServicio"),
    @NamedQuery(name = "CmDetalles.findByFechaPrestacion", query = "SELECT c FROM CmDetalles c WHERE c.fechaPrestacion = :fechaPrestacion"),
    @NamedQuery(name = "CmDetalles.findByValorCopagoItem", query = "SELECT c FROM CmDetalles c WHERE c.valorCopagoItem = :valorCopagoItem"),
    @NamedQuery(name = "CmDetalles.findByAplicaGlosa", query = "SELECT c FROM CmDetalles c WHERE c.aplicaGlosa = :aplicaGlosa"),
    @NamedQuery(name = "CmDetalles.findByAplicaConcepto", query = "SELECT c FROM CmDetalles c WHERE c.aplicaConcepto = :aplicaConcepto"),
    @NamedQuery(name = "CmDetalles.findByAplicaDx", query = "SELECT c FROM CmDetalles c WHERE c.aplicaDx = :aplicaDx"),
    @NamedQuery(name = "CmDetalles.findByAplicaAutorizacion", query = "SELECT c FROM CmDetalles c WHERE c.aplicaAutorizacion = :aplicaAutorizacion"),
    @NamedQuery(name = "CmDetalles.findByValorGlosa", query = "SELECT c FROM CmDetalles c WHERE c.valorGlosa = :valorGlosa"),
    @NamedQuery(name = "CmDetalles.findByMotivoGlosa", query = "SELECT c FROM CmDetalles c WHERE c.motivoGlosa = :motivoGlosa"),
    @NamedQuery(name = "CmDetalles.findByConceptoContable", query = "SELECT c FROM CmDetalles c WHERE c.conceptoContable = :conceptoContable"),
    @NamedQuery(name = "CmDetalles.findByPorcentajeConcepto", query = "SELECT c FROM CmDetalles c WHERE c.porcentajeConcepto = :porcentajeConcepto"),
    @NamedQuery(name = "CmDetalles.findByNumeroAutorizacion", query = "SELECT c FROM CmDetalles c WHERE c.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "CmDetalles.findByCodigoDx", query = "SELECT c FROM CmDetalles c WHERE c.codigoDx = :codigoDx"),
    @NamedQuery(name = "CmDetalles.findByNombreDx", query = "SELECT c FROM CmDetalles c WHERE c.nombreDx = :nombreDx"),
    @NamedQuery(name = "CmDetalles.findByMaeAmbitoId", query = "SELECT c FROM CmDetalles c WHERE c.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "CmDetalles.findByMaeAmbitoCodigo", query = "SELECT c FROM CmDetalles c WHERE c.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "CmDetalles.findByMaeAmbitoValor", query = "SELECT c FROM CmDetalles c WHERE c.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "CmDetalles.findByCopagoNoEfectivo", query = "SELECT c FROM CmDetalles c WHERE c.copagoNoEfectivo = :copagoNoEfectivo"),
    @NamedQuery(name = "CmDetalles.findByAplicaRecobro", query = "SELECT c FROM CmDetalles c WHERE c.aplicaRecobro = :aplicaRecobro"),
    @NamedQuery(name = "CmDetalles.findByMipresNumeroPrescripcion", query = "SELECT c FROM CmDetalles c WHERE c.mipresNumeroPrescripcion = :mipresNumeroPrescripcion"),
    @NamedQuery(name = "CmDetalles.findByMipresIdEntrega", query = "SELECT c FROM CmDetalles c WHERE c.mipresIdEntrega = :mipresIdEntrega"),
    @NamedQuery(name = "CmDetalles.findByUsuarioCrea", query = "SELECT c FROM CmDetalles c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmDetalles.findByTerminalCrea", query = "SELECT c FROM CmDetalles c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmDetalles.findByFechaHoraCrea", query = "SELECT c FROM CmDetalles c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmDetalles.findByUsuarioModifica", query = "SELECT c FROM CmDetalles c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmDetalles.findByTerminalModifica", query = "SELECT c FROM CmDetalles c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmDetalles.findByFechaHoraModifica", query = "SELECT c FROM CmDetalles c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre_completo_afiliado")
    private String nombreCompletoAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo_item")
    private int consecutivoItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_servicio_id")
    private int maServicioId;
    @Size(max = 32)
    @Column(name = "ma_servicio_codigo")
    private String maServicioCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_valor")
    private String maServicioValor;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "radicado_glosa")
    private Integer radicadoGlosa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_facturado")
    private BigDecimal valorFacturado;
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Column(name = "valor_pendiente")
    private BigDecimal valorPendiente;
    @Column(name = "valor_pendiente_actual")
    private BigDecimal valorPendienteActual;
    @Column(name = "valor_aceptado_ips")
    private BigDecimal valorAceptadoIps;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "valor_pagado_eps")
    private BigDecimal valorPagadoEps;
    @Column(name = "porcentaje_pagado_eps")
    private BigDecimal porcentajePagadoEps;
    @Column(name = "porcentaje_aceptado_ips")
    private BigDecimal porcentajeAceptadoIps;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion_respuesta_detalles")
    private String observacionRespuestaDetalles;
    @Column(name = "aplica_ac")
    private Boolean aplicaAc;
    @Column(name = "aplica_dc")
    private Boolean aplicaDc;
    @Column(name = "aplica_pbs")
    private Boolean aplicaPbs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_servicio")
    private int tipoServicio;
    @Column(name = "fecha_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrestacion;
    @Column(name = "valor_copago_item")
    private BigDecimal valorCopagoItem;
    @Column(name = "aplica_glosa")
    private Boolean aplicaGlosa;
    @Column(name = "aplica_concepto")
    private Boolean aplicaConcepto;
    @Column(name = "aplica_dx")
    private Boolean aplicaDx;
    @Column(name = "aplica_autorizacion")
    private Boolean aplicaAutorizacion;
    @Column(name = "valor_glosa")
    private BigDecimal valorGlosa;
    @Size(max = 1024)
    @Column(name = "motivo_glosa")
    private String motivoGlosa;
    @Size(max = 1024)
    @Column(name = "concepto_contable")
    private String conceptoContable;
    @Column(name = "porcentaje_concepto")
    private Short porcentajeConcepto;
    @Size(max = 32)
    @Column(name = "numero_autorizacion")
    private String numeroAutorizacion;
    @Size(max = 8)
    @Column(name = "codigo_dx")
    private String codigoDx;
    @Size(max = 1024)
    @Column(name = "nombre_dx")
    private String nombreDx;
    @Column(name = "mae_ambito_id")
    private Integer maeAmbitoId;
    @Size(max = 8)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Column(name = "copago_no_efectivo")
    private Boolean copagoNoEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_recobro")
    private boolean aplicaRecobro;
    @Size(max = 32)
    @Column(name = "mipres_numero_prescripcion")
    private String mipresNumeroPrescripcion;
    @Column(name = "mipres_id_entrega")
    private Integer mipresIdEntrega;
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
    @OneToMany(mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmAuditoriaAdjuntos> cmAuditoriaAdjuntosList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmAuditoriaDiagnosticos> cmAuditoriaDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmGestionUsuarios> cmGestionUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmGlosaRespuestaDetalles> cmGlosaRespuestaDetallesList;
    @OneToMany(mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmAuditoriaConceptosContables> cmAuditoriaConceptosContablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmAuditoriaCapitaDescuentos> cmAuditoriaCapitaDescuentosList;
    @OneToMany(mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmAuditoriaAutorizaciones> cmAuditoriaAutorizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetallesId", fetch = FetchType.LAZY)
    private List<CmAuditoriaMotivosGlosas> cmAuditoriaMotivosGlosasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmDetalleId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;

    public CmDetalles() {
    }

    public CmDetalles(Integer id) {
        this.id = id;
    }

    public CmDetalles(Integer id, int estado, int maeTipoDocumentoId, String documento, String nombreCompletoAfiliado, int consecutivoItem, int maServicioId, BigDecimal valorCopago, BigDecimal valorFacturado, int tipoServicio, boolean aplicaRecobro, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.documento = documento;
        this.nombreCompletoAfiliado = nombreCompletoAfiliado;
        this.consecutivoItem = consecutivoItem;
        this.maServicioId = maServicioId;
        this.valorCopago = valorCopago;
        this.valorFacturado = valorFacturado;
        this.tipoServicio = tipoServicio;
        this.aplicaRecobro = aplicaRecobro;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombreCompletoAfiliado() {
        return nombreCompletoAfiliado;
    }

    public void setNombreCompletoAfiliado(String nombreCompletoAfiliado) {
        this.nombreCompletoAfiliado = nombreCompletoAfiliado;
    }

    public int getConsecutivoItem() {
        return consecutivoItem;
    }

    public void setConsecutivoItem(int consecutivoItem) {
        this.consecutivoItem = consecutivoItem;
    }

    public int getMaServicioId() {
        return maServicioId;
    }

    public void setMaServicioId(int maServicioId) {
        this.maServicioId = maServicioId;
    }

    public String getMaServicioCodigo() {
        return maServicioCodigo;
    }

    public void setMaServicioCodigo(String maServicioCodigo) {
        this.maServicioCodigo = maServicioCodigo;
    }

    public String getMaServicioValor() {
        return maServicioValor;
    }

    public void setMaServicioValor(String maServicioValor) {
        this.maServicioValor = maServicioValor;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getRadicadoGlosa() {
        return radicadoGlosa;
    }

    public void setRadicadoGlosa(Integer radicadoGlosa) {
        this.radicadoGlosa = radicadoGlosa;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(BigDecimal valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
    }

    public String getObservacionRespuestaDetalles() {
        return observacionRespuestaDetalles;
    }

    public void setObservacionRespuestaDetalles(String observacionRespuestaDetalles) {
        this.observacionRespuestaDetalles = observacionRespuestaDetalles;
    }

    public Boolean getAplicaAc() {
        return aplicaAc;
    }

    public void setAplicaAc(Boolean aplicaAc) {
        this.aplicaAc = aplicaAc;
    }

    public Boolean getAplicaDc() {
        return aplicaDc;
    }

    public void setAplicaDc(Boolean aplicaDc) {
        this.aplicaDc = aplicaDc;
    }

    public Boolean getAplicaPbs() {
        return aplicaPbs;
    }

    public void setAplicaPbs(Boolean aplicaPbs) {
        this.aplicaPbs = aplicaPbs;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public BigDecimal getValorCopagoItem() {
        return valorCopagoItem;
    }

    public void setValorCopagoItem(BigDecimal valorCopagoItem) {
        this.valorCopagoItem = valorCopagoItem;
    }

    public Boolean getAplicaGlosa() {
        return aplicaGlosa;
    }

    public void setAplicaGlosa(Boolean aplicaGlosa) {
        this.aplicaGlosa = aplicaGlosa;
    }

    public Boolean getAplicaConcepto() {
        return aplicaConcepto;
    }

    public void setAplicaConcepto(Boolean aplicaConcepto) {
        this.aplicaConcepto = aplicaConcepto;
    }

    public Boolean getAplicaDx() {
        return aplicaDx;
    }

    public void setAplicaDx(Boolean aplicaDx) {
        this.aplicaDx = aplicaDx;
    }

    public Boolean getAplicaAutorizacion() {
        return aplicaAutorizacion;
    }

    public void setAplicaAutorizacion(Boolean aplicaAutorizacion) {
        this.aplicaAutorizacion = aplicaAutorizacion;
    }

    public BigDecimal getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(BigDecimal valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public String getMotivoGlosa() {
        return motivoGlosa;
    }

    public void setMotivoGlosa(String motivoGlosa) {
        this.motivoGlosa = motivoGlosa;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public Short getPorcentajeConcepto() {
        return porcentajeConcepto;
    }

    public void setPorcentajeConcepto(Short porcentajeConcepto) {
        this.porcentajeConcepto = porcentajeConcepto;
    }

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getCodigoDx() {
        return codigoDx;
    }

    public void setCodigoDx(String codigoDx) {
        this.codigoDx = codigoDx;
    }

    public String getNombreDx() {
        return nombreDx;
    }

    public void setNombreDx(String nombreDx) {
        this.nombreDx = nombreDx;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public Boolean getCopagoNoEfectivo() {
        return copagoNoEfectivo;
    }

    public void setCopagoNoEfectivo(Boolean copagoNoEfectivo) {
        this.copagoNoEfectivo = copagoNoEfectivo;
    }

    public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public String getMipresNumeroPrescripcion() {
        return mipresNumeroPrescripcion;
    }

    public void setMipresNumeroPrescripcion(String mipresNumeroPrescripcion) {
        this.mipresNumeroPrescripcion = mipresNumeroPrescripcion;
    }

    public Integer getMipresIdEntrega() {
        return mipresIdEntrega;
    }

    public void setMipresIdEntrega(Integer mipresIdEntrega) {
        this.mipresIdEntrega = mipresIdEntrega;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    @XmlTransient
    public List<CmAuditoriaDiagnosticos> getCmAuditoriaDiagnosticosList() {
        return cmAuditoriaDiagnosticosList;
    }

    public void setCmAuditoriaDiagnosticosList(List<CmAuditoriaDiagnosticos> cmAuditoriaDiagnosticosList) {
        this.cmAuditoriaDiagnosticosList = cmAuditoriaDiagnosticosList;
    }

    @XmlTransient
    public List<CmGestionUsuarios> getCmGestionUsuariosList() {
        return cmGestionUsuariosList;
    }

    public void setCmGestionUsuariosList(List<CmGestionUsuarios> cmGestionUsuariosList) {
        this.cmGestionUsuariosList = cmGestionUsuariosList;
    }

    @XmlTransient
    public List<CmGlosaRespuestaDetalles> getCmGlosaRespuestaDetallesList() {
        return cmGlosaRespuestaDetallesList;
    }

    public void setCmGlosaRespuestaDetallesList(List<CmGlosaRespuestaDetalles> cmGlosaRespuestaDetallesList) {
        this.cmGlosaRespuestaDetallesList = cmGlosaRespuestaDetallesList;
    }

    @XmlTransient
    public List<CntContratoHistoricoPrestaciones> getCntContratoHistoricoPrestacionesList() {
        return cntContratoHistoricoPrestacionesList;
    }

    public void setCntContratoHistoricoPrestacionesList(List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList) {
        this.cntContratoHistoricoPrestacionesList = cntContratoHistoricoPrestacionesList;
    }

    @XmlTransient
    public List<CmAuditoriaConceptosContables> getCmAuditoriaConceptosContablesList() {
        return cmAuditoriaConceptosContablesList;
    }

    public void setCmAuditoriaConceptosContablesList(List<CmAuditoriaConceptosContables> cmAuditoriaConceptosContablesList) {
        this.cmAuditoriaConceptosContablesList = cmAuditoriaConceptosContablesList;
    }

    @XmlTransient
    public List<CmAuditoriaCapitaDescuentos> getCmAuditoriaCapitaDescuentosList() {
        return cmAuditoriaCapitaDescuentosList;
    }

    public void setCmAuditoriaCapitaDescuentosList(List<CmAuditoriaCapitaDescuentos> cmAuditoriaCapitaDescuentosList) {
        this.cmAuditoriaCapitaDescuentosList = cmAuditoriaCapitaDescuentosList;
    }

    @XmlTransient
    public List<CmAuditoriaAutorizaciones> getCmAuditoriaAutorizacionesList() {
        return cmAuditoriaAutorizacionesList;
    }

    public void setCmAuditoriaAutorizacionesList(List<CmAuditoriaAutorizaciones> cmAuditoriaAutorizacionesList) {
        this.cmAuditoriaAutorizacionesList = cmAuditoriaAutorizacionesList;
    }

    @XmlTransient
    public List<CmAuditoriaMotivosGlosas> getCmAuditoriaMotivosGlosasList() {
        return cmAuditoriaMotivosGlosasList;
    }

    public void setCmAuditoriaMotivosGlosasList(List<CmAuditoriaMotivosGlosas> cmAuditoriaMotivosGlosasList) {
        this.cmAuditoriaMotivosGlosasList = cmAuditoriaMotivosGlosasList;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
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
        if (!(object instanceof CmDetalles)) {
            return false;
        }
        CmDetalles other = (CmDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmDetalles[ id=" + id + " ]";
    }
    
}
