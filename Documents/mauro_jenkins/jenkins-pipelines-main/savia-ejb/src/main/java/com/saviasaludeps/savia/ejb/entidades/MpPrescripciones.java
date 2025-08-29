/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "mp_prescripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripciones.findAll", query = "SELECT m FROM MpPrescripciones m"),
    @NamedQuery(name = "MpPrescripciones.findById", query = "SELECT m FROM MpPrescripciones m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripciones.findByRecobrante", query = "SELECT m FROM MpPrescripciones m WHERE m.recobrante = :recobrante"),
    @NamedQuery(name = "MpPrescripciones.findByMaTipoDocumentoPrestadorId", query = "SELECT m FROM MpPrescripciones m WHERE m.maTipoDocumentoPrestadorId = :maTipoDocumentoPrestadorId"),
    @NamedQuery(name = "MpPrescripciones.findByMaTipoDocumentoPrestadorCodigo", query = "SELECT m FROM MpPrescripciones m WHERE m.maTipoDocumentoPrestadorCodigo = :maTipoDocumentoPrestadorCodigo"),
    @NamedQuery(name = "MpPrescripciones.findByMaTipoDocumentoPrestadorValor", query = "SELECT m FROM MpPrescripciones m WHERE m.maTipoDocumentoPrestadorValor = :maTipoDocumentoPrestadorValor"),
    @NamedQuery(name = "MpPrescripciones.findByPrestadorNumeroDocumento", query = "SELECT m FROM MpPrescripciones m WHERE m.prestadorNumeroDocumento = :prestadorNumeroDocumento"),
    @NamedQuery(name = "MpPrescripciones.findByPrestadorRazonSocial", query = "SELECT m FROM MpPrescripciones m WHERE m.prestadorRazonSocial = :prestadorRazonSocial"),
    @NamedQuery(name = "MpPrescripciones.findBySedeCodigoHabilitacion", query = "SELECT m FROM MpPrescripciones m WHERE m.sedeCodigoHabilitacion = :sedeCodigoHabilitacion"),
    @NamedQuery(name = "MpPrescripciones.findByNumeroPrescripcion", query = "SELECT m FROM MpPrescripciones m WHERE m.numeroPrescripcion = :numeroPrescripcion"),
    @NamedQuery(name = "MpPrescripciones.findByConsecutivoMipres", query = "SELECT m FROM MpPrescripciones m WHERE m.consecutivoMipres = :consecutivoMipres"),
    @NamedQuery(name = "MpPrescripciones.findByFechaPrescripcion", query = "SELECT m FROM MpPrescripciones m WHERE m.fechaPrescripcion = :fechaPrescripcion"),
    @NamedQuery(name = "MpPrescripciones.findByHoraPrescripcion", query = "SELECT m FROM MpPrescripciones m WHERE m.horaPrescripcion = :horaPrescripcion"),
    @NamedQuery(name = "MpPrescripciones.findByTipoPrescripcion", query = "SELECT m FROM MpPrescripciones m WHERE m.tipoPrescripcion = :tipoPrescripcion"),
    @NamedQuery(name = "MpPrescripciones.findByCodAmbAte", query = "SELECT m FROM MpPrescripciones m WHERE m.codAmbAte = :codAmbAte"),
    @NamedQuery(name = "MpPrescripciones.findByReferenciaAmbitoAtencion", query = "SELECT m FROM MpPrescripciones m WHERE m.referenciaAmbitoAtencion = :referenciaAmbitoAtencion"),
    @NamedQuery(name = "MpPrescripciones.findByPacienteCovid19", query = "SELECT m FROM MpPrescripciones m WHERE m.pacienteCovid19 = :pacienteCovid19"),
    @NamedQuery(name = "MpPrescripciones.findByEnfermedadHuerfana", query = "SELECT m FROM MpPrescripciones m WHERE m.enfermedadHuerfana = :enfermedadHuerfana"),
    @NamedQuery(name = "MpPrescripciones.findByCodigoEnfermedadHuerfana", query = "SELECT m FROM MpPrescripciones m WHERE m.codigoEnfermedadHuerfana = :codigoEnfermedadHuerfana"),
    @NamedQuery(name = "MpPrescripciones.findByDiagnosticoEnfermedadHuerfana", query = "SELECT m FROM MpPrescripciones m WHERE m.diagnosticoEnfermedadHuerfana = :diagnosticoEnfermedadHuerfana"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoPrincipalId", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoPrincipalId = :maDiagnosticoPrincipalId"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoPrincipalCodigo", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoPrincipalCodigo = :maDiagnosticoPrincipalCodigo"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoPrincipalValor", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoPrincipalValor = :maDiagnosticoPrincipalValor"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoRelacionado1Id", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoRelacionado1Id = :maDiagnosticoRelacionado1Id"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoRelacionado1Codigo", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoRelacionado1Codigo = :maDiagnosticoRelacionado1Codigo"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoRelacionado1Valor", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoRelacionado1Valor = :maDiagnosticoRelacionado1Valor"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoRelacionado2Id", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoRelacionado2Id = :maDiagnosticoRelacionado2Id"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoRelacionado2Codigo", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoRelacionado2Codigo = :maDiagnosticoRelacionado2Codigo"),
    @NamedQuery(name = "MpPrescripciones.findByMaDiagnosticoRelacionado2Valor", query = "SELECT m FROM MpPrescripciones m WHERE m.maDiagnosticoRelacionado2Valor = :maDiagnosticoRelacionado2Valor"),
    @NamedQuery(name = "MpPrescripciones.findBySopNutricional", query = "SELECT m FROM MpPrescripciones m WHERE m.sopNutricional = :sopNutricional"),
    @NamedQuery(name = "MpPrescripciones.findByCodigoEps", query = "SELECT m FROM MpPrescripciones m WHERE m.codigoEps = :codigoEps"),
    @NamedQuery(name = "MpPrescripciones.findByAsegAfiliadoMadreTipoDocumento", query = "SELECT m FROM MpPrescripciones m WHERE m.asegAfiliadoMadreTipoDocumento = :asegAfiliadoMadreTipoDocumento"),
    @NamedQuery(name = "MpPrescripciones.findByAsegAfiliadoMadreDocumento", query = "SELECT m FROM MpPrescripciones m WHERE m.asegAfiliadoMadreDocumento = :asegAfiliadoMadreDocumento"),
    @NamedQuery(name = "MpPrescripciones.findByTipoTransaccion", query = "SELECT m FROM MpPrescripciones m WHERE m.tipoTransaccion = :tipoTransaccion"),
    @NamedQuery(name = "MpPrescripciones.findByNumeroDocumentoDonanteVivo", query = "SELECT m FROM MpPrescripciones m WHERE m.numeroDocumentoDonanteVivo = :numeroDocumentoDonanteVivo"),
    @NamedQuery(name = "MpPrescripciones.findByTipoDocumentoDonanteVivo", query = "SELECT m FROM MpPrescripciones m WHERE m.tipoDocumentoDonanteVivo = :tipoDocumentoDonanteVivo"),
    @NamedQuery(name = "MpPrescripciones.findByEstado", query = "SELECT m FROM MpPrescripciones m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripciones.findByAfectaPresMax", query = "SELECT m FROM MpPrescripciones m WHERE m.afectaPresMax = :afectaPresMax"),
    @NamedQuery(name = "MpPrescripciones.findByCompradorHomologo", query = "SELECT m FROM MpPrescripciones m WHERE m.compradorHomologo = :compradorHomologo"),
    @NamedQuery(name = "MpPrescripciones.findByTranscripcion", query = "SELECT m FROM MpPrescripciones m WHERE m.transcripcion = :transcripcion"),
    @NamedQuery(name = "MpPrescripciones.findByDireccionIpsPrescriptora", query = "SELECT m FROM MpPrescripciones m WHERE m.direccionIpsPrescriptora = :direccionIpsPrescriptora"),
    @NamedQuery(name = "MpPrescripciones.findByTelefonoIpsPrescriptora", query = "SELECT m FROM MpPrescripciones m WHERE m.telefonoIpsPrescriptora = :telefonoIpsPrescriptora"),
    @NamedQuery(name = "MpPrescripciones.findByCodHabilitacionIpsPrescriptora", query = "SELECT m FROM MpPrescripciones m WHERE m.codHabilitacionIpsPrescriptora = :codHabilitacionIpsPrescriptora"),
    @NamedQuery(name = "MpPrescripciones.findByMunicipioIpsPrescriptora", query = "SELECT m FROM MpPrescripciones m WHERE m.municipioIpsPrescriptora = :municipioIpsPrescriptora"),
    @NamedQuery(name = "MpPrescripciones.findByDerechosVerificados", query = "SELECT m FROM MpPrescripciones m WHERE m.derechosVerificados = :derechosVerificados"),
    @NamedQuery(name = "MpPrescripciones.findByPortabilidad", query = "SELECT m FROM MpPrescripciones m WHERE m.portabilidad = :portabilidad"),
    @NamedQuery(name = "MpPrescripciones.findByMunicipioPortabilidad", query = "SELECT m FROM MpPrescripciones m WHERE m.municipioPortabilidad = :municipioPortabilidad"),
    @NamedQuery(name = "MpPrescripciones.findByTransferidaPor", query = "SELECT m FROM MpPrescripciones m WHERE m.transferidaPor = :transferidaPor"),
    @NamedQuery(name = "MpPrescripciones.findByActualizadaPor", query = "SELECT m FROM MpPrescripciones m WHERE m.actualizadaPor = :actualizadaPor"),
    @NamedQuery(name = "MpPrescripciones.findByRequiereAnulacion", query = "SELECT m FROM MpPrescripciones m WHERE m.requiereAnulacion = :requiereAnulacion"),
    @NamedQuery(name = "MpPrescripciones.findByNotaAuditoria", query = "SELECT m FROM MpPrescripciones m WHERE m.notaAuditoria = :notaAuditoria"),
    @NamedQuery(name = "MpPrescripciones.findByUsuarioCrea", query = "SELECT m FROM MpPrescripciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripciones.findByTerminalCrea", query = "SELECT m FROM MpPrescripciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripciones.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripciones m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpPrescripciones.findByUsuarioModifica", query = "SELECT m FROM MpPrescripciones m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpPrescripciones.findByTerminalModifica", query = "SELECT m FROM MpPrescripciones m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpPrescripciones.findByFechaHoraModifica", query = "SELECT m FROM MpPrescripciones m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpPrescripciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recobrante")
    private boolean recobrante;
    @Column(name = "ma_tipo_documento_prestador_id")
    private Integer maTipoDocumentoPrestadorId;
    @Size(max = 8)
    @Column(name = "ma_tipo_documento_prestador_codigo")
    private String maTipoDocumentoPrestadorCodigo;
    @Size(max = 128)
    @Column(name = "ma_tipo_documento_prestador_valor")
    private String maTipoDocumentoPrestadorValor;
    @Size(max = 32)
    @Column(name = "prestador_numero_documento")
    private String prestadorNumeroDocumento;
    @Size(max = 256)
    @Column(name = "prestador_razon_social")
    private String prestadorRazonSocial;
    @Size(max = 16)
    @Column(name = "sede_codigo_habilitacion")
    private String sedeCodigoHabilitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_prescripcion")
    private String numeroPrescripcion;
    @Size(max = 2)
    @Column(name = "consecutivo_mipres")
    private String consecutivoMipres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_prescripcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrescripcion;
    @Column(name = "hora_prescripcion")
    @Temporal(TemporalType.TIME)
    private Date horaPrescripcion;
    @Column(name = "tipo_prescripcion")
    private Boolean tipoPrescripcion;
    @Size(max = 4)
    @Column(name = "cod_amb_ate")
    private String codAmbAte;
    @Column(name = "referencia_ambito_atencion")
    private Boolean referenciaAmbitoAtencion;
    @Column(name = "paciente_covid_19")
    private Boolean pacienteCovid19;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enfermedad_huerfana")
    private boolean enfermedadHuerfana;
    @Size(max = 16)
    @Column(name = "codigo_enfermedad_huerfana")
    private String codigoEnfermedadHuerfana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "diagnostico_enfermedad_huerfana")
    private boolean diagnosticoEnfermedadHuerfana;
    @Column(name = "ma_diagnostico_principal_id")
    private Integer maDiagnosticoPrincipalId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_principal_codigo")
    private String maDiagnosticoPrincipalCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_principal_valor")
    private String maDiagnosticoPrincipalValor;
    @Column(name = "ma_diagnostico_relacionado1_id")
    private Integer maDiagnosticoRelacionado1Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_relacionado1_codigo")
    private String maDiagnosticoRelacionado1Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_relacionado1_valor")
    private String maDiagnosticoRelacionado1Valor;
    @Column(name = "ma_diagnostico_relacionado2_id")
    private Integer maDiagnosticoRelacionado2Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_relacionado2_codigo")
    private String maDiagnosticoRelacionado2Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_relacionado2_valor")
    private String maDiagnosticoRelacionado2Valor;
    @Size(max = 32)
    @Column(name = "sop_nutricional")
    private String sopNutricional;
    @Size(max = 16)
    @Column(name = "codigo_eps")
    private String codigoEps;
    @Size(max = 4)
    @Column(name = "aseg_afiliado_madre_tipo_documento")
    private String asegAfiliadoMadreTipoDocumento;
    @Size(max = 16)
    @Column(name = "aseg_afiliado_madre_documento")
    private String asegAfiliadoMadreDocumento;
    @Column(name = "tipo_transaccion")
    private Integer tipoTransaccion;
    @Size(max = 16)
    @Column(name = "numero_documento_donante_vivo")
    private String numeroDocumentoDonanteVivo;
    @Size(max = 4)
    @Column(name = "tipo_documento_donante_vivo")
    private String tipoDocumentoDonanteVivo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "afecta_pres_max")
    private Boolean afectaPresMax;
    @Size(max = 48)
    @Column(name = "comprador_homologo")
    private String compradorHomologo;
    @Size(max = 2048)
    @Column(name = "transcripcion")
    private String transcripcion;
    @Size(max = 256)
    @Column(name = "direccion_ips_prescriptora")
    private String direccionIpsPrescriptora;
    @Size(max = 48)
    @Column(name = "telefono_ips_prescriptora")
    private String telefonoIpsPrescriptora;
    @Size(max = 48)
    @Column(name = "cod_habilitacion_ips_prescriptora")
    private String codHabilitacionIpsPrescriptora;
    @Column(name = "municipio_ips_prescriptora")
    private Integer municipioIpsPrescriptora;
    @Column(name = "derechos_verificados")
    private Boolean derechosVerificados;
    @Column(name = "portabilidad")
    private Boolean portabilidad;
    @Column(name = "municipio_portabilidad")
    private Integer municipioPortabilidad;
    @Size(max = 192)
    @Column(name = "transferida_por")
    private String transferidaPor;
    @Size(max = 192)
    @Column(name = "actualizada_por")
    private String actualizadaPor;
    @Column(name = "requiere_anulacion")
    private Boolean requiereAnulacion;
    @Size(max = 2048)
    @Column(name = "nota_auditoria")
    private String notaAuditoria;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_profesionales_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntProfesionales cntProfesionalesId;
    @JoinColumn(name = "mp_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpAfiliados mpAfiliadosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpProgramadaEntregas> mpProgramadaEntregasList;
    @OneToMany(mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<PeAfiliadosSugeridos> peAfiliadosSugeridosList;
    @OneToMany(mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpNoDireccionados> mpNoDireccionadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionId", fetch = FetchType.LAZY)
    private List<MpPrescripcionItemAuditoria> mpPrescripcionItemAuditoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionId", fetch = FetchType.LAZY)
    private List<MpNotificacionesHistoricos> mpNotificacionesHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpPrescripcionHistoricos> mpPrescripcionHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpPrescripcionRecobrantes> mpPrescripcionRecobrantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionId", fetch = FetchType.LAZY)
    private List<MpPrescripcionTecnologias> mpPrescripcionTecnologiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionId", fetch = FetchType.LAZY)
    private List<MpPrescripcionInsumos> mpPrescripcionInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpPrescripcionAnulada> mpPrescripcionAnuladaList;
    @OneToMany(mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpPrescripcionProgramadas> mpPrescripcionProgramadasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpCotizaciones> mpCotizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpPrescripcionMedicamentos> mpPrescripcionMedicamentosList;
    @OneToMany(mappedBy = "mpPrescripcionId", fetch = FetchType.LAZY)
    private List<AuCotizaciones> auCotizacionesList;
    @OneToMany(mappedBy = "mpPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpDireccionamientos> mpDireccionamientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpPrescripcionId", fetch = FetchType.LAZY)
    private List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList;

    public MpPrescripciones() {
    }

    public MpPrescripciones(Integer id) {
        this.id = id;
    }

    public MpPrescripciones(Integer id, boolean recobrante, String numeroPrescripcion, Date fechaPrescripcion, boolean enfermedadHuerfana, boolean diagnosticoEnfermedadHuerfana, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.recobrante = recobrante;
        this.numeroPrescripcion = numeroPrescripcion;
        this.fechaPrescripcion = fechaPrescripcion;
        this.enfermedadHuerfana = enfermedadHuerfana;
        this.diagnosticoEnfermedadHuerfana = diagnosticoEnfermedadHuerfana;
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

    public boolean getRecobrante() {
        return recobrante;
    }

    public void setRecobrante(boolean recobrante) {
        this.recobrante = recobrante;
    }

    public Integer getMaTipoDocumentoPrestadorId() {
        return maTipoDocumentoPrestadorId;
    }

    public void setMaTipoDocumentoPrestadorId(Integer maTipoDocumentoPrestadorId) {
        this.maTipoDocumentoPrestadorId = maTipoDocumentoPrestadorId;
    }

    public String getMaTipoDocumentoPrestadorCodigo() {
        return maTipoDocumentoPrestadorCodigo;
    }

    public void setMaTipoDocumentoPrestadorCodigo(String maTipoDocumentoPrestadorCodigo) {
        this.maTipoDocumentoPrestadorCodigo = maTipoDocumentoPrestadorCodigo;
    }

    public String getMaTipoDocumentoPrestadorValor() {
        return maTipoDocumentoPrestadorValor;
    }

    public void setMaTipoDocumentoPrestadorValor(String maTipoDocumentoPrestadorValor) {
        this.maTipoDocumentoPrestadorValor = maTipoDocumentoPrestadorValor;
    }

    public String getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(String prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getSedeCodigoHabilitacion() {
        return sedeCodigoHabilitacion;
    }

    public void setSedeCodigoHabilitacion(String sedeCodigoHabilitacion) {
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getConsecutivoMipres() {
        return consecutivoMipres;
    }

    public void setConsecutivoMipres(String consecutivoMipres) {
        this.consecutivoMipres = consecutivoMipres;
    }

    public Date getFechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(Date fechaPrescripcion) {
        this.fechaPrescripcion = fechaPrescripcion;
    }

    public Date getHoraPrescripcion() {
        return horaPrescripcion;
    }

    public void setHoraPrescripcion(Date horaPrescripcion) {
        this.horaPrescripcion = horaPrescripcion;
    }

    public Boolean getTipoPrescripcion() {
        return tipoPrescripcion;
    }

    public void setTipoPrescripcion(Boolean tipoPrescripcion) {
        this.tipoPrescripcion = tipoPrescripcion;
    }

    public String getCodAmbAte() {
        return codAmbAte;
    }

    public void setCodAmbAte(String codAmbAte) {
        this.codAmbAte = codAmbAte;
    }

    public Boolean getReferenciaAmbitoAtencion() {
        return referenciaAmbitoAtencion;
    }

    public void setReferenciaAmbitoAtencion(Boolean referenciaAmbitoAtencion) {
        this.referenciaAmbitoAtencion = referenciaAmbitoAtencion;
    }

    public Boolean getPacienteCovid19() {
        return pacienteCovid19;
    }

    public void setPacienteCovid19(Boolean pacienteCovid19) {
        this.pacienteCovid19 = pacienteCovid19;
    }

    public boolean getEnfermedadHuerfana() {
        return enfermedadHuerfana;
    }

    public void setEnfermedadHuerfana(boolean enfermedadHuerfana) {
        this.enfermedadHuerfana = enfermedadHuerfana;
    }

    public String getCodigoEnfermedadHuerfana() {
        return codigoEnfermedadHuerfana;
    }

    public void setCodigoEnfermedadHuerfana(String codigoEnfermedadHuerfana) {
        this.codigoEnfermedadHuerfana = codigoEnfermedadHuerfana;
    }

    public boolean getDiagnosticoEnfermedadHuerfana() {
        return diagnosticoEnfermedadHuerfana;
    }

    public void setDiagnosticoEnfermedadHuerfana(boolean diagnosticoEnfermedadHuerfana) {
        this.diagnosticoEnfermedadHuerfana = diagnosticoEnfermedadHuerfana;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public Integer getMaDiagnosticoRelacionado1Id() {
        return maDiagnosticoRelacionado1Id;
    }

    public void setMaDiagnosticoRelacionado1Id(Integer maDiagnosticoRelacionado1Id) {
        this.maDiagnosticoRelacionado1Id = maDiagnosticoRelacionado1Id;
    }

    public String getMaDiagnosticoRelacionado1Codigo() {
        return maDiagnosticoRelacionado1Codigo;
    }

    public void setMaDiagnosticoRelacionado1Codigo(String maDiagnosticoRelacionado1Codigo) {
        this.maDiagnosticoRelacionado1Codigo = maDiagnosticoRelacionado1Codigo;
    }

    public String getMaDiagnosticoRelacionado1Valor() {
        return maDiagnosticoRelacionado1Valor;
    }

    public void setMaDiagnosticoRelacionado1Valor(String maDiagnosticoRelacionado1Valor) {
        this.maDiagnosticoRelacionado1Valor = maDiagnosticoRelacionado1Valor;
    }

    public Integer getMaDiagnosticoRelacionado2Id() {
        return maDiagnosticoRelacionado2Id;
    }

    public void setMaDiagnosticoRelacionado2Id(Integer maDiagnosticoRelacionado2Id) {
        this.maDiagnosticoRelacionado2Id = maDiagnosticoRelacionado2Id;
    }

    public String getMaDiagnosticoRelacionado2Codigo() {
        return maDiagnosticoRelacionado2Codigo;
    }

    public void setMaDiagnosticoRelacionado2Codigo(String maDiagnosticoRelacionado2Codigo) {
        this.maDiagnosticoRelacionado2Codigo = maDiagnosticoRelacionado2Codigo;
    }

    public String getMaDiagnosticoRelacionado2Valor() {
        return maDiagnosticoRelacionado2Valor;
    }

    public void setMaDiagnosticoRelacionado2Valor(String maDiagnosticoRelacionado2Valor) {
        this.maDiagnosticoRelacionado2Valor = maDiagnosticoRelacionado2Valor;
    }

    public String getSopNutricional() {
        return sopNutricional;
    }

    public void setSopNutricional(String sopNutricional) {
        this.sopNutricional = sopNutricional;
    }

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getAsegAfiliadoMadreTipoDocumento() {
        return asegAfiliadoMadreTipoDocumento;
    }

    public void setAsegAfiliadoMadreTipoDocumento(String asegAfiliadoMadreTipoDocumento) {
        this.asegAfiliadoMadreTipoDocumento = asegAfiliadoMadreTipoDocumento;
    }

    public String getAsegAfiliadoMadreDocumento() {
        return asegAfiliadoMadreDocumento;
    }

    public void setAsegAfiliadoMadreDocumento(String asegAfiliadoMadreDocumento) {
        this.asegAfiliadoMadreDocumento = asegAfiliadoMadreDocumento;
    }

    public Integer getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(Integer tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getNumeroDocumentoDonanteVivo() {
        return numeroDocumentoDonanteVivo;
    }

    public void setNumeroDocumentoDonanteVivo(String numeroDocumentoDonanteVivo) {
        this.numeroDocumentoDonanteVivo = numeroDocumentoDonanteVivo;
    }

    public String getTipoDocumentoDonanteVivo() {
        return tipoDocumentoDonanteVivo;
    }

    public void setTipoDocumentoDonanteVivo(String tipoDocumentoDonanteVivo) {
        this.tipoDocumentoDonanteVivo = tipoDocumentoDonanteVivo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Boolean getAfectaPresMax() {
        return afectaPresMax;
    }

    public void setAfectaPresMax(Boolean afectaPresMax) {
        this.afectaPresMax = afectaPresMax;
    }

    public String getCompradorHomologo() {
        return compradorHomologo;
    }

    public void setCompradorHomologo(String compradorHomologo) {
        this.compradorHomologo = compradorHomologo;
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public String getDireccionIpsPrescriptora() {
        return direccionIpsPrescriptora;
    }

    public void setDireccionIpsPrescriptora(String direccionIpsPrescriptora) {
        this.direccionIpsPrescriptora = direccionIpsPrescriptora;
    }

    public String getTelefonoIpsPrescriptora() {
        return telefonoIpsPrescriptora;
    }

    public void setTelefonoIpsPrescriptora(String telefonoIpsPrescriptora) {
        this.telefonoIpsPrescriptora = telefonoIpsPrescriptora;
    }

    public String getCodHabilitacionIpsPrescriptora() {
        return codHabilitacionIpsPrescriptora;
    }

    public void setCodHabilitacionIpsPrescriptora(String codHabilitacionIpsPrescriptora) {
        this.codHabilitacionIpsPrescriptora = codHabilitacionIpsPrescriptora;
    }

    public Integer getMunicipioIpsPrescriptora() {
        return municipioIpsPrescriptora;
    }

    public void setMunicipioIpsPrescriptora(Integer municipioIpsPrescriptora) {
        this.municipioIpsPrescriptora = municipioIpsPrescriptora;
    }

    public Boolean getDerechosVerificados() {
        return derechosVerificados;
    }

    public void setDerechosVerificados(Boolean derechosVerificados) {
        this.derechosVerificados = derechosVerificados;
    }

    public Boolean getPortabilidad() {
        return portabilidad;
    }

    public void setPortabilidad(Boolean portabilidad) {
        this.portabilidad = portabilidad;
    }

    public Integer getMunicipioPortabilidad() {
        return municipioPortabilidad;
    }

    public void setMunicipioPortabilidad(Integer municipioPortabilidad) {
        this.municipioPortabilidad = municipioPortabilidad;
    }

    public String getTransferidaPor() {
        return transferidaPor;
    }

    public void setTransferidaPor(String transferidaPor) {
        this.transferidaPor = transferidaPor;
    }

    public String getActualizadaPor() {
        return actualizadaPor;
    }

    public void setActualizadaPor(String actualizadaPor) {
        this.actualizadaPor = actualizadaPor;
    }

    public Boolean getRequiereAnulacion() {
        return requiereAnulacion;
    }

    public void setRequiereAnulacion(Boolean requiereAnulacion) {
        this.requiereAnulacion = requiereAnulacion;
    }

    public String getNotaAuditoria() {
        return notaAuditoria;
    }

    public void setNotaAuditoria(String notaAuditoria) {
        this.notaAuditoria = notaAuditoria;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntProfesionales getCntProfesionalesId() {
        return cntProfesionalesId;
    }

    public void setCntProfesionalesId(CntProfesionales cntProfesionalesId) {
        this.cntProfesionalesId = cntProfesionalesId;
    }

    public MpAfiliados getMpAfiliadosId() {
        return mpAfiliadosId;
    }

    public void setMpAfiliadosId(MpAfiliados mpAfiliadosId) {
        this.mpAfiliadosId = mpAfiliadosId;
    }

    @XmlTransient
    public List<MpProgramadaEntregas> getMpProgramadaEntregasList() {
        return mpProgramadaEntregasList;
    }

    public void setMpProgramadaEntregasList(List<MpProgramadaEntregas> mpProgramadaEntregasList) {
        this.mpProgramadaEntregasList = mpProgramadaEntregasList;
    }

    @XmlTransient
    public List<PeAfiliadosSugeridos> getPeAfiliadosSugeridosList() {
        return peAfiliadosSugeridosList;
    }

    public void setPeAfiliadosSugeridosList(List<PeAfiliadosSugeridos> peAfiliadosSugeridosList) {
        this.peAfiliadosSugeridosList = peAfiliadosSugeridosList;
    }

    @XmlTransient
    public List<MpNoDireccionados> getMpNoDireccionadosList() {
        return mpNoDireccionadosList;
    }

    public void setMpNoDireccionadosList(List<MpNoDireccionados> mpNoDireccionadosList) {
        this.mpNoDireccionadosList = mpNoDireccionadosList;
    }

    @XmlTransient
    public List<MpPrescripcionItemAuditoria> getMpPrescripcionItemAuditoriaList() {
        return mpPrescripcionItemAuditoriaList;
    }

    public void setMpPrescripcionItemAuditoriaList(List<MpPrescripcionItemAuditoria> mpPrescripcionItemAuditoriaList) {
        this.mpPrescripcionItemAuditoriaList = mpPrescripcionItemAuditoriaList;
    }

    @XmlTransient
    public List<MpNotificacionesHistoricos> getMpNotificacionesHistoricosList() {
        return mpNotificacionesHistoricosList;
    }

    public void setMpNotificacionesHistoricosList(List<MpNotificacionesHistoricos> mpNotificacionesHistoricosList) {
        this.mpNotificacionesHistoricosList = mpNotificacionesHistoricosList;
    }

    @XmlTransient
    public List<MpPrescripcionHistoricos> getMpPrescripcionHistoricosList() {
        return mpPrescripcionHistoricosList;
    }

    public void setMpPrescripcionHistoricosList(List<MpPrescripcionHistoricos> mpPrescripcionHistoricosList) {
        this.mpPrescripcionHistoricosList = mpPrescripcionHistoricosList;
    }

    @XmlTransient
    public List<MpPrescripcionRecobrantes> getMpPrescripcionRecobrantesList() {
        return mpPrescripcionRecobrantesList;
    }

    public void setMpPrescripcionRecobrantesList(List<MpPrescripcionRecobrantes> mpPrescripcionRecobrantesList) {
        this.mpPrescripcionRecobrantesList = mpPrescripcionRecobrantesList;
    }

    @XmlTransient
    public List<MpPrescripcionTecnologias> getMpPrescripcionTecnologiasList() {
        return mpPrescripcionTecnologiasList;
    }

    public void setMpPrescripcionTecnologiasList(List<MpPrescripcionTecnologias> mpPrescripcionTecnologiasList) {
        this.mpPrescripcionTecnologiasList = mpPrescripcionTecnologiasList;
    }

    @XmlTransient
    public List<MpPrescripcionInsumos> getMpPrescripcionInsumosList() {
        return mpPrescripcionInsumosList;
    }

    public void setMpPrescripcionInsumosList(List<MpPrescripcionInsumos> mpPrescripcionInsumosList) {
        this.mpPrescripcionInsumosList = mpPrescripcionInsumosList;
    }

    @XmlTransient
    public List<MpPrescripcionAnulada> getMpPrescripcionAnuladaList() {
        return mpPrescripcionAnuladaList;
    }

    public void setMpPrescripcionAnuladaList(List<MpPrescripcionAnulada> mpPrescripcionAnuladaList) {
        this.mpPrescripcionAnuladaList = mpPrescripcionAnuladaList;
    }

    @XmlTransient
    public List<MpPrescripcionProgramadas> getMpPrescripcionProgramadasList() {
        return mpPrescripcionProgramadasList;
    }

    public void setMpPrescripcionProgramadasList(List<MpPrescripcionProgramadas> mpPrescripcionProgramadasList) {
        this.mpPrescripcionProgramadasList = mpPrescripcionProgramadasList;
    }

    @XmlTransient
    public List<MpCotizaciones> getMpCotizacionesList() {
        return mpCotizacionesList;
    }

    public void setMpCotizacionesList(List<MpCotizaciones> mpCotizacionesList) {
        this.mpCotizacionesList = mpCotizacionesList;
    }

    @XmlTransient
    public List<MpPrescripcionMedicamentos> getMpPrescripcionMedicamentosList() {
        return mpPrescripcionMedicamentosList;
    }

    public void setMpPrescripcionMedicamentosList(List<MpPrescripcionMedicamentos> mpPrescripcionMedicamentosList) {
        this.mpPrescripcionMedicamentosList = mpPrescripcionMedicamentosList;
    }

    @XmlTransient
    public List<AuCotizaciones> getAuCotizacionesList() {
        return auCotizacionesList;
    }

    public void setAuCotizacionesList(List<AuCotizaciones> auCotizacionesList) {
        this.auCotizacionesList = auCotizacionesList;
    }

    @XmlTransient
    public List<MpDireccionamientos> getMpDireccionamientosList() {
        return mpDireccionamientosList;
    }

    public void setMpDireccionamientosList(List<MpDireccionamientos> mpDireccionamientosList) {
        this.mpDireccionamientosList = mpDireccionamientosList;
    }

    @XmlTransient
    public List<MpDireccionamientoEntregados> getMpDireccionamientoEntregadosList() {
        return mpDireccionamientoEntregadosList;
    }

    public void setMpDireccionamientoEntregadosList(List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList) {
        this.mpDireccionamientoEntregadosList = mpDireccionamientoEntregadosList;
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
        if (!(object instanceof MpPrescripciones)) {
            return false;
        }
        MpPrescripciones other = (MpPrescripciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripciones[ id=" + id + " ]";
    }
    
}
