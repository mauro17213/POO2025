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
@Table(name = "rco_factura_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoFacturaDetalles.findAll", query = "SELECT r FROM RcoFacturaDetalles r"),
    @NamedQuery(name = "RcoFacturaDetalles.findById", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.id = :id"),
    @NamedQuery(name = "RcoFacturaDetalles.findByModalidad", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.modalidad = :modalidad"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaeEstadoId", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaeEstadoCodigo", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaeEstadoValor", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "RcoFacturaDetalles.findByObservacionDetalle", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.observacionDetalle = :observacionDetalle"),
    @NamedQuery(name = "RcoFacturaDetalles.findByNumeroContrato", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.numeroContrato = :numeroContrato"),
    @NamedQuery(name = "RcoFacturaDetalles.findByAplicaRecobro", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.aplicaRecobro = :aplicaRecobro"),
    @NamedQuery(name = "RcoFacturaDetalles.findByCausalRecobro", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.causalRecobro = :causalRecobro"),
    @NamedQuery(name = "RcoFacturaDetalles.findByCmDetalleEstado", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.cmDetalleEstado = :cmDetalleEstado"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaeTipoDocumentoId", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaeTipoDocumentoCodigo", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaeTipoDocumentoValor", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "RcoFacturaDetalles.findByDocumento", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.documento = :documento"),
    @NamedQuery(name = "RcoFacturaDetalles.findByNombreCompleto", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "RcoFacturaDetalles.findByConsecutivoItem", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.consecutivoItem = :consecutivoItem"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaServicioHabilitadoId", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maServicioHabilitadoId = :maServicioHabilitadoId"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaServicioCodigo", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maServicioCodigo = :maServicioCodigo"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaServicioValor", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maServicioValor = :maServicioValor"),
    @NamedQuery(name = "RcoFacturaDetalles.findByCantidad", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.cantidad = :cantidad"),
    @NamedQuery(name = "RcoFacturaDetalles.findByValorFacturado", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.valorFacturado = :valorFacturado"),
    @NamedQuery(name = "RcoFacturaDetalles.findByObservacion", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RcoFacturaDetalles.findByTipoServicio", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.tipoServicio = :tipoServicio"),
    @NamedQuery(name = "RcoFacturaDetalles.findByFechaHoraPrestacion", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.fechaHoraPrestacion = :fechaHoraPrestacion"),
    @NamedQuery(name = "RcoFacturaDetalles.findByConceptoContable", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.conceptoContable = :conceptoContable"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaDiagnostico", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maDiagnostico = :maDiagnostico"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaDiagnosticoId", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaDiagnosticoCodigo", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "RcoFacturaDetalles.findByMaDiagnosticoValor", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "RcoFacturaDetalles.findByValorTotalRecobro", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.valorTotalRecobro = :valorTotalRecobro"),
    @NamedQuery(name = "RcoFacturaDetalles.findByValorRestanteRecobro", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.valorRestanteRecobro = :valorRestanteRecobro"),
    @NamedQuery(name = "RcoFacturaDetalles.findByEstadoConciliacion", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.estadoConciliacion = :estadoConciliacion"),
    @NamedQuery(name = "RcoFacturaDetalles.findByUsuarioCrea", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoFacturaDetalles.findByTerminalCrea", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoFacturaDetalles.findByFechaHoraCrea", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RcoFacturaDetalles.findByUsuarioModifica", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RcoFacturaDetalles.findByTerminalModifica", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RcoFacturaDetalles.findByFechaHoraModifica", query = "SELECT r FROM RcoFacturaDetalles r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RcoFacturaDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "modalidad")
    private Integer modalidad;
    @Column(name = "mae_estado_id")
    private Integer maeEstadoId;
    @Size(max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Size(max = 2048)
    @Column(name = "observacion_detalle")
    private String observacionDetalle;
    @Size(max = 32)
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_recobro")
    private boolean aplicaRecobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "causal_recobro")
    private boolean causalRecobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cm_detalle_estado")
    private int cmDetalleEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo_item")
    private int consecutivoItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_servicio_habilitado_id")
    private int maServicioHabilitadoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_servicio_codigo")
    private String maServicioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_servicio_valor")
    private String maServicioValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_facturado")
    private int valorFacturado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_servicio")
    private int tipoServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraPrestacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "concepto_contable")
    private String conceptoContable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_diagnostico")
    private String maDiagnostico;
    @Column(name = "ma_diagnostico_id")
    private Integer maDiagnosticoId;
    @Size(max = 8)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Size(max = 128)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total_recobro")
    private BigDecimal valorTotalRecobro;
    @Column(name = "valor_restante_recobro")
    private BigDecimal valorRestanteRecobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_conciliacion")
    private boolean estadoConciliacion;
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
    @OneToMany(mappedBy = "rcoFacturaDetallesId", fetch = FetchType.LAZY)
    private List<RcoConciliacionGestiones> rcoConciliacionGestionesList;
    @JoinColumn(name = "cnt_prestadores_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadoresSedesId;
    @JoinColumn(name = "gn_usuario_responsable_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuarioResponsableId;
    @JoinColumn(name = "cm_detalle_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmDetalles cmDetalleId;
    @JoinColumn(name = "pe_programa_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeProgramas peProgramaId;
    @JoinColumn(name = "rco_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RcoFacturas rcoFacturasId;
    @JoinColumn(name = "rco_grupos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RcoGrupos rcoGruposId;
    @JoinColumn(name = "rco_conciliacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RcoConciliaciones rcoConciliacionId;

    public RcoFacturaDetalles() {
    }

    public RcoFacturaDetalles(Integer id) {
        this.id = id;
    }

    public RcoFacturaDetalles(Integer id, boolean aplicaRecobro, boolean causalRecobro, int cmDetalleEstado, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, String nombreCompleto, int consecutivoItem, int maServicioHabilitadoId, String maServicioCodigo, String maServicioValor, int cantidad, int valorFacturado, String observacion, int tipoServicio, Date fechaHoraPrestacion, String conceptoContable, String maDiagnostico, boolean estadoConciliacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.aplicaRecobro = aplicaRecobro;
        this.causalRecobro = causalRecobro;
        this.cmDetalleEstado = cmDetalleEstado;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.consecutivoItem = consecutivoItem;
        this.maServicioHabilitadoId = maServicioHabilitadoId;
        this.maServicioCodigo = maServicioCodigo;
        this.maServicioValor = maServicioValor;
        this.cantidad = cantidad;
        this.valorFacturado = valorFacturado;
        this.observacion = observacion;
        this.tipoServicio = tipoServicio;
        this.fechaHoraPrestacion = fechaHoraPrestacion;
        this.conceptoContable = conceptoContable;
        this.maDiagnostico = maDiagnostico;
        this.estadoConciliacion = estadoConciliacion;
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

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public String getObservacionDetalle() {
        return observacionDetalle;
    }

    public void setObservacionDetalle(String observacionDetalle) {
        this.observacionDetalle = observacionDetalle;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public boolean getCausalRecobro() {
        return causalRecobro;
    }

    public void setCausalRecobro(boolean causalRecobro) {
        this.causalRecobro = causalRecobro;
    }

    public int getCmDetalleEstado() {
        return cmDetalleEstado;
    }

    public void setCmDetalleEstado(int cmDetalleEstado) {
        this.cmDetalleEstado = cmDetalleEstado;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getConsecutivoItem() {
        return consecutivoItem;
    }

    public void setConsecutivoItem(int consecutivoItem) {
        this.consecutivoItem = consecutivoItem;
    }

    public int getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(int maServicioHabilitadoId) {
        this.maServicioHabilitadoId = maServicioHabilitadoId;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(int valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Date getFechaHoraPrestacion() {
        return fechaHoraPrestacion;
    }

    public void setFechaHoraPrestacion(Date fechaHoraPrestacion) {
        this.fechaHoraPrestacion = fechaHoraPrestacion;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public String getMaDiagnostico() {
        return maDiagnostico;
    }

    public void setMaDiagnostico(String maDiagnostico) {
        this.maDiagnostico = maDiagnostico;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public BigDecimal getValorTotalRecobro() {
        return valorTotalRecobro;
    }

    public void setValorTotalRecobro(BigDecimal valorTotalRecobro) {
        this.valorTotalRecobro = valorTotalRecobro;
    }

    public BigDecimal getValorRestanteRecobro() {
        return valorRestanteRecobro;
    }

    public void setValorRestanteRecobro(BigDecimal valorRestanteRecobro) {
        this.valorRestanteRecobro = valorRestanteRecobro;
    }

    public boolean getEstadoConciliacion() {
        return estadoConciliacion;
    }

    public void setEstadoConciliacion(boolean estadoConciliacion) {
        this.estadoConciliacion = estadoConciliacion;
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
    public List<RcoConciliacionGestiones> getRcoConciliacionGestionesList() {
        return rcoConciliacionGestionesList;
    }

    public void setRcoConciliacionGestionesList(List<RcoConciliacionGestiones> rcoConciliacionGestionesList) {
        this.rcoConciliacionGestionesList = rcoConciliacionGestionesList;
    }

    public CntPrestadorSedes getCntPrestadoresSedesId() {
        return cntPrestadoresSedesId;
    }

    public void setCntPrestadoresSedesId(CntPrestadorSedes cntPrestadoresSedesId) {
        this.cntPrestadoresSedesId = cntPrestadoresSedesId;
    }

    public GnUsuarios getGnUsuarioResponsableId() {
        return gnUsuarioResponsableId;
    }

    public void setGnUsuarioResponsableId(GnUsuarios gnUsuarioResponsableId) {
        this.gnUsuarioResponsableId = gnUsuarioResponsableId;
    }

    public CmDetalles getCmDetalleId() {
        return cmDetalleId;
    }

    public void setCmDetalleId(CmDetalles cmDetalleId) {
        this.cmDetalleId = cmDetalleId;
    }

    public PeProgramas getPeProgramaId() {
        return peProgramaId;
    }

    public void setPeProgramaId(PeProgramas peProgramaId) {
        this.peProgramaId = peProgramaId;
    }

    public RcoFacturas getRcoFacturasId() {
        return rcoFacturasId;
    }

    public void setRcoFacturasId(RcoFacturas rcoFacturasId) {
        this.rcoFacturasId = rcoFacturasId;
    }

    public RcoGrupos getRcoGruposId() {
        return rcoGruposId;
    }

    public void setRcoGruposId(RcoGrupos rcoGruposId) {
        this.rcoGruposId = rcoGruposId;
    }

    public RcoConciliaciones getRcoConciliacionId() {
        return rcoConciliacionId;
    }

    public void setRcoConciliacionId(RcoConciliaciones rcoConciliacionId) {
        this.rcoConciliacionId = rcoConciliacionId;
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
        if (!(object instanceof RcoFacturaDetalles)) {
            return false;
        }
        RcoFacturaDetalles other = (RcoFacturaDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoFacturaDetalles[ id=" + id + " ]";
    }
    
}
