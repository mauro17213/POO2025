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
@Table(name = "au_no_solicitud_carga_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findAll", query = "SELECT a FROM AuNoSolicitudCargaDetalles a"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findById", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByNumeroNoSolicitud", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.numeroNoSolicitud = :numeroNoSolicitud"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoTipo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoTipo = :maeTipoDocumentoTipo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByDocumentoAfiliado", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeAmbitoAtencionId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeAmbitoAtencionCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeAmbitoAtencionValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeAmbitoAtencionTipo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeAmbitoAtencionTipo = :maeAmbitoAtencionTipo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByFechaOrdenMedica", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.fechaOrdenMedica = :fechaOrdenMedica"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaServicioSolicitadoId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maServicioSolicitadoId = :maServicioSolicitadoId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaServicioSolicitadoCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaServicioSolicitadoValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maServicioSolicitadoValor = :maServicioSolicitadoValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByCodigoRepsIpsSolicita", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.codigoRepsIpsSolicita = :codigoRepsIpsSolicita"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoProfesionalId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoProfesionalId = :maeTipoDocumentoProfesionalId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoProfesionalCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoProfesionalCodigo = :maeTipoDocumentoProfesionalCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoProfesionalValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoProfesionalValor = :maeTipoDocumentoProfesionalValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeTipoDocumentoProfesionalTipo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeTipoDocumentoProfesionalTipo = :maeTipoDocumentoProfesionalTipo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByDocumentoProfesional", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.documentoProfesional = :documentoProfesional"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaEspecialidadId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maEspecialidadId = :maEspecialidadId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaEspecialidadCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maEspecialidadCodigo = :maEspecialidadCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaEspecialidadValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maEspecialidadValor = :maEspecialidadValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByCodigoRepsIpsEntrega", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.codigoRepsIpsEntrega = :codigoRepsIpsEntrega"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaDiagnosticosId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maDiagnosticosId = :maDiagnosticosId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaDiagnosticosCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaDiagnosticosValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByPrincipal", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.principal = :principal"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaServicioHabilitadoId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maServicioHabilitadoId = :maServicioHabilitadoId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaServicioHabilitadoCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maServicioHabilitadoCodigo = :maServicioHabilitadoCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaServicioHabilitadoValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maServicioHabilitadoValor = :maServicioHabilitadoValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByTipoTecnologia", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaTecnologiaId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaTecnologiaCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaTecnologiaValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaMedicamentoId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaMedicamentoCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaMedicamentoValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByCantidadSolicitada", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByDosis", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.dosis = :dosis"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByFrecuencia", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.frecuencia = :frecuencia"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByViaAdministracion", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.viaAdministracion = :viaAdministracion"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByJustificacionClinica", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.justificacionClinica = :justificacionClinica"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByConsecutivo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.consecutivo = :consecutivo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByFila", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.fila = :fila"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByDuracionTratamiento", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.duracionTratamiento = :duracionTratamiento"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByTipoFormula", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.tipoFormula = :tipoFormula"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByTutela", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.tutela = :tutela"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByNumEntregas", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.numEntregas = :numEntregas"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByValorUnitario", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByValorTotal", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.valorTotal = :valorTotal"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByAltoCosto", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.altoCosto = :altoCosto"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByPbs", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.pbs = :pbs"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByConsecutivoOrden", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeMotivoSinAutorizacionId", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeMotivoSinAutorizacionId = :maeMotivoSinAutorizacionId"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeMotivoSinAutorizacionCodigo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeMotivoSinAutorizacionCodigo = :maeMotivoSinAutorizacionCodigo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeMotivoSinAutorizacionValor", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeMotivoSinAutorizacionValor = :maeMotivoSinAutorizacionValor"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByMaeMotivoSinAutorizacionTipo", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.maeMotivoSinAutorizacionTipo = :maeMotivoSinAutorizacionTipo"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudCargaDetalles.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudCargaDetalles a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuNoSolicitudCargaDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 64)
    @Column(name = "numero_no_solicitud")
    private String numeroNoSolicitud;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_documento_tipo")
    private String maeTipoDocumentoTipo;
    @Size(max = 16)
    @Column(name = "documento_afiliado")
    private String documentoAfiliado;
    @Column(name = "mae_ambito_atencion_id")
    private Integer maeAmbitoAtencionId;
    @Size(max = 8)
    @Column(name = "mae_ambito_atencion_codigo")
    private String maeAmbitoAtencionCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_atencion_valor")
    private String maeAmbitoAtencionValor;
    @Size(max = 4)
    @Column(name = "mae_ambito_atencion_tipo")
    private String maeAmbitoAtencionTipo;
    @Column(name = "fecha_orden_medica")
    @Temporal(TemporalType.DATE)
    private Date fechaOrdenMedica;
    @Column(name = "ma_servicio_solicitado_id")
    private Integer maServicioSolicitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_solicitado_codigo")
    private String maServicioSolicitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_solicitado_valor")
    private String maServicioSolicitadoValor;
    @Size(max = 32)
    @Column(name = "codigo_reps_ips_solicita")
    private String codigoRepsIpsSolicita;
    @Column(name = "mae_tipo_documento_profesional_id")
    private Integer maeTipoDocumentoProfesionalId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_profesional_codigo")
    private String maeTipoDocumentoProfesionalCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_profesional_valor")
    private String maeTipoDocumentoProfesionalValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_documento_profesional_tipo")
    private String maeTipoDocumentoProfesionalTipo;
    @Size(max = 16)
    @Column(name = "documento_profesional")
    private String documentoProfesional;
    @Column(name = "ma_especialidad_id")
    private Integer maEspecialidadId;
    @Size(max = 32)
    @Column(name = "ma_especialidad_codigo")
    private String maEspecialidadCodigo;
    @Size(max = 512)
    @Column(name = "ma_especialidad_valor")
    private String maEspecialidadValor;
    @Size(max = 32)
    @Column(name = "codigo_reps_ips_entrega")
    private String codigoRepsIpsEntrega;
    @Column(name = "ma_diagnosticos_id")
    private Integer maDiagnosticosId;
    @Size(max = 32)
    @Column(name = "ma_diagnosticos_codigo")
    private String maDiagnosticosCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnosticos_valor")
    private String maDiagnosticosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "principal")
    private boolean principal;
    @Column(name = "ma_servicio_habilitado_id")
    private Integer maServicioHabilitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_habilitado_codigo")
    private String maServicioHabilitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_habilitado_valor")
    private String maServicioHabilitadoValor;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    @Column(name = "cantidad_solicitada")
    private Integer cantidadSolicitada;
    @Size(max = 16)
    @Column(name = "dosis")
    private String dosis;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @Column(name = "via_administracion")
    private Integer viaAdministracion;
    @Size(max = 2048)
    @Column(name = "justificacion_clinica")
    private String justificacionClinica;
    @Size(max = 32)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "fila")
    private Integer fila;
    @Column(name = "duracion_tratamiento")
    private Integer duracionTratamiento;
    @Column(name = "tipo_formula")
    private Integer tipoFormula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tutela")
    private boolean tutela;
    @Column(name = "num_entregas")
    private Integer numEntregas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alto_costo")
    private boolean altoCosto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs")
    private boolean pbs;
    @Size(max = 64)
    @Column(name = "consecutivo_orden")
    private String consecutivoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_sin_autorizacion_id")
    private int maeMotivoSinAutorizacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_motivo_sin_autorizacion_codigo")
    private String maeMotivoSinAutorizacionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_motivo_sin_autorizacion_valor")
    private String maeMotivoSinAutorizacionValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_motivo_sin_autorizacion_tipo")
    private String maeMotivoSinAutorizacionTipo;
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
    @OneToMany(mappedBy = "auNoSolicitudCargaDetallesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudCargaSucesos> auNoSolicitudCargaSucesosList;
    @JoinColumn(name = "au_no_solicitud_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudes auNoSolicitudId;
    @JoinColumn(name = "au_no_solicitud_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuNoSolicitudCargas auNoSolicitudCargasId;

    public AuNoSolicitudCargaDetalles() {
    }

    public AuNoSolicitudCargaDetalles(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudCargaDetalles(Integer id, boolean principal, boolean tutela, boolean altoCosto, boolean pbs, int maeMotivoSinAutorizacionId, String maeMotivoSinAutorizacionCodigo, String maeMotivoSinAutorizacionValor, String maeMotivoSinAutorizacionTipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.principal = principal;
        this.tutela = tutela;
        this.altoCosto = altoCosto;
        this.pbs = pbs;
        this.maeMotivoSinAutorizacionId = maeMotivoSinAutorizacionId;
        this.maeMotivoSinAutorizacionCodigo = maeMotivoSinAutorizacionCodigo;
        this.maeMotivoSinAutorizacionValor = maeMotivoSinAutorizacionValor;
        this.maeMotivoSinAutorizacionTipo = maeMotivoSinAutorizacionTipo;
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

    public String getNumeroNoSolicitud() {
        return numeroNoSolicitud;
    }

    public void setNumeroNoSolicitud(String numeroNoSolicitud) {
        this.numeroNoSolicitud = numeroNoSolicitud;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
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

    public String getMaeTipoDocumentoTipo() {
        return maeTipoDocumentoTipo;
    }

    public void setMaeTipoDocumentoTipo(String maeTipoDocumentoTipo) {
        this.maeTipoDocumentoTipo = maeTipoDocumentoTipo;
    }

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public Integer getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(Integer maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public String getMaeAmbitoAtencionValor() {
        return maeAmbitoAtencionValor;
    }

    public void setMaeAmbitoAtencionValor(String maeAmbitoAtencionValor) {
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
    }

    public String getMaeAmbitoAtencionTipo() {
        return maeAmbitoAtencionTipo;
    }

    public void setMaeAmbitoAtencionTipo(String maeAmbitoAtencionTipo) {
        this.maeAmbitoAtencionTipo = maeAmbitoAtencionTipo;
    }

    public Date getFechaOrdenMedica() {
        return fechaOrdenMedica;
    }

    public void setFechaOrdenMedica(Date fechaOrdenMedica) {
        this.fechaOrdenMedica = fechaOrdenMedica;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public String getCodigoRepsIpsSolicita() {
        return codigoRepsIpsSolicita;
    }

    public void setCodigoRepsIpsSolicita(String codigoRepsIpsSolicita) {
        this.codigoRepsIpsSolicita = codigoRepsIpsSolicita;
    }

    public Integer getMaeTipoDocumentoProfesionalId() {
        return maeTipoDocumentoProfesionalId;
    }

    public void setMaeTipoDocumentoProfesionalId(Integer maeTipoDocumentoProfesionalId) {
        this.maeTipoDocumentoProfesionalId = maeTipoDocumentoProfesionalId;
    }

    public String getMaeTipoDocumentoProfesionalCodigo() {
        return maeTipoDocumentoProfesionalCodigo;
    }

    public void setMaeTipoDocumentoProfesionalCodigo(String maeTipoDocumentoProfesionalCodigo) {
        this.maeTipoDocumentoProfesionalCodigo = maeTipoDocumentoProfesionalCodigo;
    }

    public String getMaeTipoDocumentoProfesionalValor() {
        return maeTipoDocumentoProfesionalValor;
    }

    public void setMaeTipoDocumentoProfesionalValor(String maeTipoDocumentoProfesionalValor) {
        this.maeTipoDocumentoProfesionalValor = maeTipoDocumentoProfesionalValor;
    }

    public String getMaeTipoDocumentoProfesionalTipo() {
        return maeTipoDocumentoProfesionalTipo;
    }

    public void setMaeTipoDocumentoProfesionalTipo(String maeTipoDocumentoProfesionalTipo) {
        this.maeTipoDocumentoProfesionalTipo = maeTipoDocumentoProfesionalTipo;
    }

    public String getDocumentoProfesional() {
        return documentoProfesional;
    }

    public void setDocumentoProfesional(String documentoProfesional) {
        this.documentoProfesional = documentoProfesional;
    }

    public Integer getMaEspecialidadId() {
        return maEspecialidadId;
    }

    public void setMaEspecialidadId(Integer maEspecialidadId) {
        this.maEspecialidadId = maEspecialidadId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
    }

    public String getCodigoRepsIpsEntrega() {
        return codigoRepsIpsEntrega;
    }

    public void setCodigoRepsIpsEntrega(String codigoRepsIpsEntrega) {
        this.codigoRepsIpsEntrega = codigoRepsIpsEntrega;
    }

    public Integer getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(Integer maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public String getMaDiagnosticosCodigo() {
        return maDiagnosticosCodigo;
    }

    public void setMaDiagnosticosCodigo(String maDiagnosticosCodigo) {
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
    }

    public String getMaDiagnosticosValor() {
        return maDiagnosticosValor;
    }

    public void setMaDiagnosticosValor(String maDiagnosticosValor) {
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Integer getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(Integer maServicioHabilitadoId) {
        this.maServicioHabilitadoId = maServicioHabilitadoId;
    }

    public String getMaServicioHabilitadoCodigo() {
        return maServicioHabilitadoCodigo;
    }

    public void setMaServicioHabilitadoCodigo(String maServicioHabilitadoCodigo) {
        this.maServicioHabilitadoCodigo = maServicioHabilitadoCodigo;
    }

    public String getMaServicioHabilitadoValor() {
        return maServicioHabilitadoValor;
    }

    public void setMaServicioHabilitadoValor(String maServicioHabilitadoValor) {
        this.maServicioHabilitadoValor = maServicioHabilitadoValor;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(Integer viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public Integer getTipoFormula() {
        return tipoFormula;
    }

    public void setTipoFormula(Integer tipoFormula) {
        this.tipoFormula = tipoFormula;
    }

    public boolean getTutela() {
        return tutela;
    }

    public void setTutela(boolean tutela) {
        this.tutela = tutela;
    }

    public Integer getNumEntregas() {
        return numEntregas;
    }

    public void setNumEntregas(Integer numEntregas) {
        this.numEntregas = numEntregas;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean getAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
    }

    public boolean getPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public String getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(String consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public int getMaeMotivoSinAutorizacionId() {
        return maeMotivoSinAutorizacionId;
    }

    public void setMaeMotivoSinAutorizacionId(int maeMotivoSinAutorizacionId) {
        this.maeMotivoSinAutorizacionId = maeMotivoSinAutorizacionId;
    }

    public String getMaeMotivoSinAutorizacionCodigo() {
        return maeMotivoSinAutorizacionCodigo;
    }

    public void setMaeMotivoSinAutorizacionCodigo(String maeMotivoSinAutorizacionCodigo) {
        this.maeMotivoSinAutorizacionCodigo = maeMotivoSinAutorizacionCodigo;
    }

    public String getMaeMotivoSinAutorizacionValor() {
        return maeMotivoSinAutorizacionValor;
    }

    public void setMaeMotivoSinAutorizacionValor(String maeMotivoSinAutorizacionValor) {
        this.maeMotivoSinAutorizacionValor = maeMotivoSinAutorizacionValor;
    }

    public String getMaeMotivoSinAutorizacionTipo() {
        return maeMotivoSinAutorizacionTipo;
    }

    public void setMaeMotivoSinAutorizacionTipo(String maeMotivoSinAutorizacionTipo) {
        this.maeMotivoSinAutorizacionTipo = maeMotivoSinAutorizacionTipo;
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

    @XmlTransient
    public List<AuNoSolicitudCargaSucesos> getAuNoSolicitudCargaSucesosList() {
        return auNoSolicitudCargaSucesosList;
    }

    public void setAuNoSolicitudCargaSucesosList(List<AuNoSolicitudCargaSucesos> auNoSolicitudCargaSucesosList) {
        this.auNoSolicitudCargaSucesosList = auNoSolicitudCargaSucesosList;
    }

    public AuNoSolicitudes getAuNoSolicitudId() {
        return auNoSolicitudId;
    }

    public void setAuNoSolicitudId(AuNoSolicitudes auNoSolicitudId) {
        this.auNoSolicitudId = auNoSolicitudId;
    }

    public AuNoSolicitudCargas getAuNoSolicitudCargasId() {
        return auNoSolicitudCargasId;
    }

    public void setAuNoSolicitudCargasId(AuNoSolicitudCargas auNoSolicitudCargasId) {
        this.auNoSolicitudCargasId = auNoSolicitudCargasId;
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
        if (!(object instanceof AuNoSolicitudCargaDetalles)) {
            return false;
        }
        AuNoSolicitudCargaDetalles other = (AuNoSolicitudCargaDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudCargaDetalles[ id=" + id + " ]";
    }
    
}
