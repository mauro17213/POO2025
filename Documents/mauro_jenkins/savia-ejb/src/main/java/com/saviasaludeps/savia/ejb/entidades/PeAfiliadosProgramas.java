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
@Table(name = "pe_afiliados_programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeAfiliadosProgramas.findAll", query = "SELECT p FROM PeAfiliadosProgramas p"),
    @NamedQuery(name = "PeAfiliadosProgramas.findById", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.id = :id"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByTipoPaciente", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.tipoPaciente = :tipoPaciente"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByActivo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.activo = :activo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaActivoId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaActivoId = :maeCausaActivoId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaActivoCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaActivoCodigo = :maeCausaActivoCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaActivoValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaActivoValor = :maeCausaActivoValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaInactivoId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaInactivoId = :maeCausaInactivoId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaInactivoCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaInactivoCodigo = :maeCausaInactivoCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaInactivoValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaInactivoValor = :maeCausaInactivoValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByFechaInicioPrograma", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.fechaInicioPrograma = :fechaInicioPrograma"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByFechaFinPrograma", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.fechaFinPrograma = :fechaFinPrograma"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnosticoPrincipalId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnosticoPrincipalId = :maDiagnosticoPrincipalId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnosticoPrincipalCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnosticoPrincipalCodigo = :maDiagnosticoPrincipalCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnosticoPrincipalValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnosticoPrincipalValor = :maDiagnosticoPrincipalValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnostico2Id", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnostico2Id = :maDiagnostico2Id"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnostico2Codigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnostico2Codigo = :maDiagnostico2Codigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnostico2Valor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnostico2Valor = :maDiagnostico2Valor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnostico3Id", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnostico3Id = :maDiagnostico3Id"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnostico3Codigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnostico3Codigo = :maDiagnostico3Codigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaDiagnostico3Valor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maDiagnostico3Valor = :maDiagnostico3Valor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByFechaDiagnostico", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.fechaDiagnostico = :fechaDiagnostico"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeRegionCorporalId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeRegionCorporalId = :maeRegionCorporalId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeRegionCorporalCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeRegionCorporalCodigo = :maeRegionCorporalCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeRegionCorporalValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeRegionCorporalValor = :maeRegionCorporalValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeMedioDxId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeMedioDxId = :maeMedioDxId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeMedioDxCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeMedioDxCodigo = :maeMedioDxCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeMedioDxValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeMedioDxValor = :maeMedioDxValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByFuenteOrigen", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByIdSolicitudOrigen", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.idSolicitudOrigen = :idSolicitudOrigen"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByAdherente", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.adherente = :adherente"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByDisentimiento", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.disentimiento = :disentimiento"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByEstadoSivigila", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.estadoSivigila = :estadoSivigila"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByNotificadoSivigila", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.notificadoSivigila = :notificadoSivigila"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaDescarteId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaDescarteId = :maeCausaDescarteId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaDescarteCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaDescarteCodigo = :maeCausaDescarteCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeCausaDescarteValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeCausaDescarteValor = :maeCausaDescarteValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByEstadoDiagnostico", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.estadoDiagnostico = :estadoDiagnostico"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByPlanificacionFamiliar", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.planificacionFamiliar = :planificacionFamiliar"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeNueveSentenciasId", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeNueveSentenciasId = :maeNueveSentenciasId"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeNueveSentenciasCodigo", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeNueveSentenciasCodigo = :maeNueveSentenciasCodigo"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByMaeNueveSentenciasValor", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.maeNueveSentenciasValor = :maeNueveSentenciasValor"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByUsuarioCrea", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByTerminalCrea", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByFechaHoraCrea", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByUsuarioModifica", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByTerminalModifica", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeAfiliadosProgramas.findByFechaHoraModifica", query = "SELECT p FROM PeAfiliadosProgramas p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeAfiliadosProgramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_paciente")
    private short tipoPaciente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_causa_activo_id")
    private int maeCausaActivoId;
    @Size(max = 8)
    @Column(name = "mae_causa_activo_codigo")
    private String maeCausaActivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_activo_valor")
    private String maeCausaActivoValor;
    @Column(name = "mae_causa_inactivo_id")
    private Integer maeCausaInactivoId;
    @Size(max = 8)
    @Column(name = "mae_causa_inactivo_codigo")
    private String maeCausaInactivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_inactivo_valor")
    private String maeCausaInactivoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_programa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioPrograma;
    @Column(name = "fecha_fin_programa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPrograma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_diagnostico_principal_id")
    private int maDiagnosticoPrincipalId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_principal_codigo")
    private String maDiagnosticoPrincipalCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_principal_valor")
    private String maDiagnosticoPrincipalValor;
    @Column(name = "ma_diagnostico2_id")
    private Integer maDiagnostico2Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico2_codigo")
    private String maDiagnostico2Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico2_valor")
    private String maDiagnostico2Valor;
    @Column(name = "ma_diagnostico3_id")
    private Integer maDiagnostico3Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico3_codigo")
    private String maDiagnostico3Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico3_valor")
    private String maDiagnostico3Valor;
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDiagnostico;
    @Column(name = "mae_region_corporal_id")
    private Integer maeRegionCorporalId;
    @Size(max = 8)
    @Column(name = "mae_region_corporal_codigo")
    private String maeRegionCorporalCodigo;
    @Size(max = 128)
    @Column(name = "mae_region_corporal_valor")
    private String maeRegionCorporalValor;
    @Column(name = "mae_medio_dx_id")
    private Integer maeMedioDxId;
    @Size(max = 8)
    @Column(name = "mae_medio_dx_codigo")
    private String maeMedioDxCodigo;
    @Size(max = 128)
    @Column(name = "mae_medio_dx_valor")
    private String maeMedioDxValor;
    @Column(name = "fuente_origen")
    private Integer fuenteOrigen;
    @Column(name = "id_solicitud_origen")
    private Integer idSolicitudOrigen;
    @Column(name = "adherente")
    private Boolean adherente;
    @Column(name = "disentimiento")
    private Boolean disentimiento;
    @Column(name = "estado_sivigila")
    private Integer estadoSivigila;
    @Column(name = "notificado_sivigila")
    private Integer notificadoSivigila;
    @Column(name = "mae_causa_descarte_id")
    private Integer maeCausaDescarteId;
    @Size(max = 8)
    @Column(name = "mae_causa_descarte_codigo")
    private String maeCausaDescarteCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_descarte_valor")
    private String maeCausaDescarteValor;
    @Column(name = "estado_diagnostico")
    private Integer estadoDiagnostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "planificacion_familiar")
    private boolean planificacionFamiliar;
    @Column(name = "mae_nueve_sentencias_id")
    private Integer maeNueveSentenciasId;
    @Size(max = 8)
    @Column(name = "mae_nueve_sentencias_codigo")
    private String maeNueveSentenciasCodigo;
    @Size(max = 128)
    @Column(name = "mae_nueve_sentencias_valor")
    private String maeNueveSentenciasValor;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosProgramasId", fetch = FetchType.LAZY)
    private List<PeCargaHistoricos> peCargaHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosProgramasId", fetch = FetchType.LAZY)
    private List<PeAfiliadoDiagnosticos> peAfiliadoDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosProgramasId", fetch = FetchType.LAZY)
    private List<PeGestiones> peGestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosProgramasId", fetch = FetchType.LAZY)
    private List<PeProgramasTraslados> peProgramasTrasladosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosProgramasId", fetch = FetchType.LAZY)
    private List<PeVariablesValores> peVariablesValoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosId", fetch = FetchType.LAZY)
    private List<PeAdjuntos> peAdjuntosList;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @JoinColumn(name = "pe_afiliados_sugeridos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeAfiliadosSugeridos peAfiliadosSugeridosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosProgramasId", fetch = FetchType.LAZY)
    private List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;

    public PeAfiliadosProgramas() {
    }

    public PeAfiliadosProgramas(Integer id) {
        this.id = id;
    }

    public PeAfiliadosProgramas(Integer id, short tipoPaciente, boolean activo, int maeCausaActivoId, Date fechaInicioPrograma, int maDiagnosticoPrincipalId, boolean planificacionFamiliar, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoPaciente = tipoPaciente;
        this.activo = activo;
        this.maeCausaActivoId = maeCausaActivoId;
        this.fechaInicioPrograma = fechaInicioPrograma;
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
        this.planificacionFamiliar = planificacionFamiliar;
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

    public short getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(short tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMaeCausaActivoId() {
        return maeCausaActivoId;
    }

    public void setMaeCausaActivoId(int maeCausaActivoId) {
        this.maeCausaActivoId = maeCausaActivoId;
    }

    public String getMaeCausaActivoCodigo() {
        return maeCausaActivoCodigo;
    }

    public void setMaeCausaActivoCodigo(String maeCausaActivoCodigo) {
        this.maeCausaActivoCodigo = maeCausaActivoCodigo;
    }

    public String getMaeCausaActivoValor() {
        return maeCausaActivoValor;
    }

    public void setMaeCausaActivoValor(String maeCausaActivoValor) {
        this.maeCausaActivoValor = maeCausaActivoValor;
    }

    public Integer getMaeCausaInactivoId() {
        return maeCausaInactivoId;
    }

    public void setMaeCausaInactivoId(Integer maeCausaInactivoId) {
        this.maeCausaInactivoId = maeCausaInactivoId;
    }

    public String getMaeCausaInactivoCodigo() {
        return maeCausaInactivoCodigo;
    }

    public void setMaeCausaInactivoCodigo(String maeCausaInactivoCodigo) {
        this.maeCausaInactivoCodigo = maeCausaInactivoCodigo;
    }

    public String getMaeCausaInactivoValor() {
        return maeCausaInactivoValor;
    }

    public void setMaeCausaInactivoValor(String maeCausaInactivoValor) {
        this.maeCausaInactivoValor = maeCausaInactivoValor;
    }

    public Date getFechaInicioPrograma() {
        return fechaInicioPrograma;
    }

    public void setFechaInicioPrograma(Date fechaInicioPrograma) {
        this.fechaInicioPrograma = fechaInicioPrograma;
    }

    public Date getFechaFinPrograma() {
        return fechaFinPrograma;
    }

    public void setFechaFinPrograma(Date fechaFinPrograma) {
        this.fechaFinPrograma = fechaFinPrograma;
    }

    public int getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(int maDiagnosticoPrincipalId) {
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

    public Integer getMaDiagnostico2Id() {
        return maDiagnostico2Id;
    }

    public void setMaDiagnostico2Id(Integer maDiagnostico2Id) {
        this.maDiagnostico2Id = maDiagnostico2Id;
    }

    public String getMaDiagnostico2Codigo() {
        return maDiagnostico2Codigo;
    }

    public void setMaDiagnostico2Codigo(String maDiagnostico2Codigo) {
        this.maDiagnostico2Codigo = maDiagnostico2Codigo;
    }

    public String getMaDiagnostico2Valor() {
        return maDiagnostico2Valor;
    }

    public void setMaDiagnostico2Valor(String maDiagnostico2Valor) {
        this.maDiagnostico2Valor = maDiagnostico2Valor;
    }

    public Integer getMaDiagnostico3Id() {
        return maDiagnostico3Id;
    }

    public void setMaDiagnostico3Id(Integer maDiagnostico3Id) {
        this.maDiagnostico3Id = maDiagnostico3Id;
    }

    public String getMaDiagnostico3Codigo() {
        return maDiagnostico3Codigo;
    }

    public void setMaDiagnostico3Codigo(String maDiagnostico3Codigo) {
        this.maDiagnostico3Codigo = maDiagnostico3Codigo;
    }

    public String getMaDiagnostico3Valor() {
        return maDiagnostico3Valor;
    }

    public void setMaDiagnostico3Valor(String maDiagnostico3Valor) {
        this.maDiagnostico3Valor = maDiagnostico3Valor;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public Integer getMaeRegionCorporalId() {
        return maeRegionCorporalId;
    }

    public void setMaeRegionCorporalId(Integer maeRegionCorporalId) {
        this.maeRegionCorporalId = maeRegionCorporalId;
    }

    public String getMaeRegionCorporalCodigo() {
        return maeRegionCorporalCodigo;
    }

    public void setMaeRegionCorporalCodigo(String maeRegionCorporalCodigo) {
        this.maeRegionCorporalCodigo = maeRegionCorporalCodigo;
    }

    public String getMaeRegionCorporalValor() {
        return maeRegionCorporalValor;
    }

    public void setMaeRegionCorporalValor(String maeRegionCorporalValor) {
        this.maeRegionCorporalValor = maeRegionCorporalValor;
    }

    public Integer getMaeMedioDxId() {
        return maeMedioDxId;
    }

    public void setMaeMedioDxId(Integer maeMedioDxId) {
        this.maeMedioDxId = maeMedioDxId;
    }

    public String getMaeMedioDxCodigo() {
        return maeMedioDxCodigo;
    }

    public void setMaeMedioDxCodigo(String maeMedioDxCodigo) {
        this.maeMedioDxCodigo = maeMedioDxCodigo;
    }

    public String getMaeMedioDxValor() {
        return maeMedioDxValor;
    }

    public void setMaeMedioDxValor(String maeMedioDxValor) {
        this.maeMedioDxValor = maeMedioDxValor;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public Integer getIdSolicitudOrigen() {
        return idSolicitudOrigen;
    }

    public void setIdSolicitudOrigen(Integer idSolicitudOrigen) {
        this.idSolicitudOrigen = idSolicitudOrigen;
    }

    public Boolean getAdherente() {
        return adherente;
    }

    public void setAdherente(Boolean adherente) {
        this.adherente = adherente;
    }

    public Boolean getDisentimiento() {
        return disentimiento;
    }

    public void setDisentimiento(Boolean disentimiento) {
        this.disentimiento = disentimiento;
    }

    public Integer getEstadoSivigila() {
        return estadoSivigila;
    }

    public void setEstadoSivigila(Integer estadoSivigila) {
        this.estadoSivigila = estadoSivigila;
    }

    public Integer getNotificadoSivigila() {
        return notificadoSivigila;
    }

    public void setNotificadoSivigila(Integer notificadoSivigila) {
        this.notificadoSivigila = notificadoSivigila;
    }

    public Integer getMaeCausaDescarteId() {
        return maeCausaDescarteId;
    }

    public void setMaeCausaDescarteId(Integer maeCausaDescarteId) {
        this.maeCausaDescarteId = maeCausaDescarteId;
    }

    public String getMaeCausaDescarteCodigo() {
        return maeCausaDescarteCodigo;
    }

    public void setMaeCausaDescarteCodigo(String maeCausaDescarteCodigo) {
        this.maeCausaDescarteCodigo = maeCausaDescarteCodigo;
    }

    public String getMaeCausaDescarteValor() {
        return maeCausaDescarteValor;
    }

    public void setMaeCausaDescarteValor(String maeCausaDescarteValor) {
        this.maeCausaDescarteValor = maeCausaDescarteValor;
    }

    public Integer getEstadoDiagnostico() {
        return estadoDiagnostico;
    }

    public void setEstadoDiagnostico(Integer estadoDiagnostico) {
        this.estadoDiagnostico = estadoDiagnostico;
    }

    public boolean getPlanificacionFamiliar() {
        return planificacionFamiliar;
    }

    public void setPlanificacionFamiliar(boolean planificacionFamiliar) {
        this.planificacionFamiliar = planificacionFamiliar;
    }

    public Integer getMaeNueveSentenciasId() {
        return maeNueveSentenciasId;
    }

    public void setMaeNueveSentenciasId(Integer maeNueveSentenciasId) {
        this.maeNueveSentenciasId = maeNueveSentenciasId;
    }

    public String getMaeNueveSentenciasCodigo() {
        return maeNueveSentenciasCodigo;
    }

    public void setMaeNueveSentenciasCodigo(String maeNueveSentenciasCodigo) {
        this.maeNueveSentenciasCodigo = maeNueveSentenciasCodigo;
    }

    public String getMaeNueveSentenciasValor() {
        return maeNueveSentenciasValor;
    }

    public void setMaeNueveSentenciasValor(String maeNueveSentenciasValor) {
        this.maeNueveSentenciasValor = maeNueveSentenciasValor;
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
    public List<PeCargaHistoricos> getPeCargaHistoricosList() {
        return peCargaHistoricosList;
    }

    public void setPeCargaHistoricosList(List<PeCargaHistoricos> peCargaHistoricosList) {
        this.peCargaHistoricosList = peCargaHistoricosList;
    }

    @XmlTransient
    public List<PeAfiliadoDiagnosticos> getPeAfiliadoDiagnosticosList() {
        return peAfiliadoDiagnosticosList;
    }

    public void setPeAfiliadoDiagnosticosList(List<PeAfiliadoDiagnosticos> peAfiliadoDiagnosticosList) {
        this.peAfiliadoDiagnosticosList = peAfiliadoDiagnosticosList;
    }

    @XmlTransient
    public List<PeGestiones> getPeGestionesList() {
        return peGestionesList;
    }

    public void setPeGestionesList(List<PeGestiones> peGestionesList) {
        this.peGestionesList = peGestionesList;
    }

    @XmlTransient
    public List<PeProgramasTraslados> getPeProgramasTrasladosList() {
        return peProgramasTrasladosList;
    }

    public void setPeProgramasTrasladosList(List<PeProgramasTraslados> peProgramasTrasladosList) {
        this.peProgramasTrasladosList = peProgramasTrasladosList;
    }

    @XmlTransient
    public List<PeVariablesValores> getPeVariablesValoresList() {
        return peVariablesValoresList;
    }

    public void setPeVariablesValoresList(List<PeVariablesValores> peVariablesValoresList) {
        this.peVariablesValoresList = peVariablesValoresList;
    }

    @XmlTransient
    public List<PeAdjuntos> getPeAdjuntosList() {
        return peAdjuntosList;
    }

    public void setPeAdjuntosList(List<PeAdjuntos> peAdjuntosList) {
        this.peAdjuntosList = peAdjuntosList;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public PeAfiliadosSugeridos getPeAfiliadosSugeridosId() {
        return peAfiliadosSugeridosId;
    }

    public void setPeAfiliadosSugeridosId(PeAfiliadosSugeridos peAfiliadosSugeridosId) {
        this.peAfiliadosSugeridosId = peAfiliadosSugeridosId;
    }

    @XmlTransient
    public List<PeVariablesValoresHistoricos> getPeVariablesValoresHistoricosList() {
        return peVariablesValoresHistoricosList;
    }

    public void setPeVariablesValoresHistoricosList(List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList) {
        this.peVariablesValoresHistoricosList = peVariablesValoresHistoricosList;
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
        if (!(object instanceof PeAfiliadosProgramas)) {
            return false;
        }
        PeAfiliadosProgramas other = (PeAfiliadosProgramas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeAfiliadosProgramas[ id=" + id + " ]";
    }
    
}
