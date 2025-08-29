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
@Table(name = "aus_caso_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusCasoServicios.findAll", query = "SELECT a FROM AusCasoServicios a"),
    @NamedQuery(name = "AusCasoServicios.findById", query = "SELECT a FROM AusCasoServicios a WHERE a.id = :id"),
    @NamedQuery(name = "AusCasoServicios.findByMaeTipoAdministrativoId", query = "SELECT a FROM AusCasoServicios a WHERE a.maeTipoAdministrativoId = :maeTipoAdministrativoId"),
    @NamedQuery(name = "AusCasoServicios.findByMaeTipoAdministrativoCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maeTipoAdministrativoCodigo = :maeTipoAdministrativoCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaeTipoAdministrativoValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maeTipoAdministrativoValor = :maeTipoAdministrativoValor"),
    @NamedQuery(name = "AusCasoServicios.findByMaeServicioAmbitoId", query = "SELECT a FROM AusCasoServicios a WHERE a.maeServicioAmbitoId = :maeServicioAmbitoId"),
    @NamedQuery(name = "AusCasoServicios.findByMaeServicioAmbitoCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maeServicioAmbitoCodigo = :maeServicioAmbitoCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaeServicioAmbitoValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maeServicioAmbitoValor = :maeServicioAmbitoValor"),
    @NamedQuery(name = "AusCasoServicios.findByMaeServicioMotivoId", query = "SELECT a FROM AusCasoServicios a WHERE a.maeServicioMotivoId = :maeServicioMotivoId"),
    @NamedQuery(name = "AusCasoServicios.findByMaeServicioMotivoCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maeServicioMotivoCodigo = :maeServicioMotivoCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaeServicioMotivoValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maeServicioMotivoValor = :maeServicioMotivoValor"),
    @NamedQuery(name = "AusCasoServicios.findByMaeEstadoId", query = "SELECT a FROM AusCasoServicios a WHERE a.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "AusCasoServicios.findByMaeEstadoCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaeEstadoValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "AusCasoServicios.findByFechaVencimiento", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "AusCasoServicios.findByFechaCumplimiento", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaCumplimiento = :fechaCumplimiento"),
    @NamedQuery(name = "AusCasoServicios.findByTipoTecnologia", query = "SELECT a FROM AusCasoServicios a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AusCasoServicios.findByMaTecnologiaId", query = "SELECT a FROM AusCasoServicios a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AusCasoServicios.findByMaTecnologiaCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaTecnologiaValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AusCasoServicios.findByMaServicioId", query = "SELECT a FROM AusCasoServicios a WHERE a.maServicioId = :maServicioId"),
    @NamedQuery(name = "AusCasoServicios.findByMaServicioCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maServicioCodigo = :maServicioCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaServicioValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maServicioValor = :maServicioValor"),
    @NamedQuery(name = "AusCasoServicios.findByMaServicioRelacion", query = "SELECT a FROM AusCasoServicios a WHERE a.maServicioRelacion = :maServicioRelacion"),
    @NamedQuery(name = "AusCasoServicios.findByCantidad", query = "SELECT a FROM AusCasoServicios a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "AusCasoServicios.findByPertinencia", query = "SELECT a FROM AusCasoServicios a WHERE a.pertinencia = :pertinencia"),
    @NamedQuery(name = "AusCasoServicios.findByMaDiagnosticosCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maDiagnosticosCodigo = :maDiagnosticosCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaDiagnosticosValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maDiagnosticosValor = :maDiagnosticosValor"),
    @NamedQuery(name = "AusCasoServicios.findByMaDiagnosticosRelacion", query = "SELECT a FROM AusCasoServicios a WHERE a.maDiagnosticosRelacion = :maDiagnosticosRelacion"),
    @NamedQuery(name = "AusCasoServicios.findByMaePatologiaId", query = "SELECT a FROM AusCasoServicios a WHERE a.maePatologiaId = :maePatologiaId"),
    @NamedQuery(name = "AusCasoServicios.findByMaePatologiaCodigo", query = "SELECT a FROM AusCasoServicios a WHERE a.maePatologiaCodigo = :maePatologiaCodigo"),
    @NamedQuery(name = "AusCasoServicios.findByMaePatologiaValor", query = "SELECT a FROM AusCasoServicios a WHERE a.maePatologiaValor = :maePatologiaValor"),
    @NamedQuery(name = "AusCasoServicios.findByCntPrestadorSedePrescriptoraValor", query = "SELECT a FROM AusCasoServicios a WHERE a.cntPrestadorSedePrescriptoraValor = :cntPrestadorSedePrescriptoraValor"),
    @NamedQuery(name = "AusCasoServicios.findByCntPrestadorSedeDestinoValor", query = "SELECT a FROM AusCasoServicios a WHERE a.cntPrestadorSedeDestinoValor = :cntPrestadorSedeDestinoValor"),
    @NamedQuery(name = "AusCasoServicios.findByMedicamento", query = "SELECT a FROM AusCasoServicios a WHERE a.medicamento = :medicamento"),
    @NamedQuery(name = "AusCasoServicios.findByMedicamentoCobertura", query = "SELECT a FROM AusCasoServicios a WHERE a.medicamentoCobertura = :medicamentoCobertura"),
    @NamedQuery(name = "AusCasoServicios.findByServicioAtribuidoIps", query = "SELECT a FROM AusCasoServicios a WHERE a.servicioAtribuidoIps = :servicioAtribuidoIps"),
    @NamedQuery(name = "AusCasoServicios.findByBorrado", query = "SELECT a FROM AusCasoServicios a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AusCasoServicios.findByFechaAplica", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaAplica = :fechaAplica"),
    @NamedQuery(name = "AusCasoServicios.findByFechaInicioVigencia", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaInicioVigencia = :fechaInicioVigencia"),
    @NamedQuery(name = "AusCasoServicios.findByFechaFinVigencia", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaFinVigencia = :fechaFinVigencia"),
    @NamedQuery(name = "AusCasoServicios.findByOrigenServicioDestino", query = "SELECT a FROM AusCasoServicios a WHERE a.origenServicioDestino = :origenServicioDestino"),
    @NamedQuery(name = "AusCasoServicios.findByDetalleServicioDestino", query = "SELECT a FROM AusCasoServicios a WHERE a.detalleServicioDestino = :detalleServicioDestino"),
    @NamedQuery(name = "AusCasoServicios.findByAsignacionCita", query = "SELECT a FROM AusCasoServicios a WHERE a.asignacionCita = :asignacionCita"),
    @NamedQuery(name = "AusCasoServicios.findByUsuarioCrea", query = "SELECT a FROM AusCasoServicios a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusCasoServicios.findByTerminalCrea", query = "SELECT a FROM AusCasoServicios a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusCasoServicios.findByFechaHoraCrea", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusCasoServicios.findByUsuarioModifica", query = "SELECT a FROM AusCasoServicios a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusCasoServicios.findByTerminalModifica", query = "SELECT a FROM AusCasoServicios a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusCasoServicios.findByFechaHoraModifica", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AusCasoServicios.findByUsuarioBorra", query = "SELECT a FROM AusCasoServicios a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AusCasoServicios.findByTerminalBorra", query = "SELECT a FROM AusCasoServicios a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AusCasoServicios.findByFechaHoraBorra", query = "SELECT a FROM AusCasoServicios a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AusCasoServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_administrativo_id")
    private Integer maeTipoAdministrativoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_administrativo_codigo")
    private String maeTipoAdministrativoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_administrativo_valor")
    private String maeTipoAdministrativoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_servicio_ambito_id")
    private int maeServicioAmbitoId;
    @Size(max = 8)
    @Column(name = "mae_servicio_ambito_codigo")
    private String maeServicioAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_servicio_ambito_valor")
    private String maeServicioAmbitoValor;
    @Column(name = "mae_servicio_motivo_id")
    private Integer maeServicioMotivoId;
    @Size(max = 8)
    @Column(name = "mae_servicio_motivo_codigo")
    private String maeServicioMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_servicio_motivo_valor")
    private String maeServicioMotivoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_id")
    private int maeEstadoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "fecha_cumplimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCumplimiento;
    @Column(name = "tipo_tecnologia")
    private Short tipoTecnologia;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "ma_servicio_id")
    private Integer maServicioId;
    @Size(max = 32)
    @Column(name = "ma_servicio_codigo")
    private String maServicioCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_valor")
    private String maServicioValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_servicio_relacion")
    private boolean maServicioRelacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "pertinencia")
    private Boolean pertinencia;
    @Size(max = 32)
    @Column(name = "ma_diagnosticos_codigo")
    private String maDiagnosticosCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnosticos_valor")
    private String maDiagnosticosValor;
    @Column(name = "ma_diagnosticos_relacion")
    private Boolean maDiagnosticosRelacion;
    @Column(name = "mae_patologia_id")
    private Integer maePatologiaId;
    @Size(max = 8)
    @Column(name = "mae_patologia_codigo")
    private String maePatologiaCodigo;
    @Size(max = 128)
    @Column(name = "mae_patologia_valor")
    private String maePatologiaValor;
    @Size(max = 128)
    @Column(name = "cnt_prestador_sede_prescriptora_valor")
    private String cntPrestadorSedePrescriptoraValor;
    @Size(max = 128)
    @Column(name = "cnt_prestador_sede_destino_valor")
    private String cntPrestadorSedeDestinoValor;
    @Column(name = "medicamento")
    private Boolean medicamento;
    @Column(name = "medicamento_cobertura")
    private Integer medicamentoCobertura;
    @Column(name = "servicio_atribuido_ips")
    private Integer servicioAtribuidoIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Column(name = "fecha_aplica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplica;
    @Column(name = "fecha_inicio_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioVigencia;
    @Column(name = "fecha_fin_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen_servicio_destino")
    private int origenServicioDestino;
    @Size(max = 128)
    @Column(name = "detalle_servicio_destino")
    private String detalleServicioDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asignacion_cita")
    private boolean asignacionCita;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "cnt_prestador_sede_prescriptora_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedePrescriptoraId;
    @JoinColumn(name = "cnt_prestador_sede_destino_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedeDestinoId;
    @JoinColumn(name = "aus_casos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusCasos ausCasosId;
    @JoinColumn(name = "gn_usuarios_asignado_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosAsignadoId;
    @JoinColumn(name = "ma_diagnosticos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaDiagnosticos maDiagnosticosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausServiciosId", fetch = FetchType.LAZY)
    private List<AusServicioGestiones> ausServicioGestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausCasoServiciosId", fetch = FetchType.LAZY)
    private List<AusCasoServicioCostos> ausCasoServicioCostosList;
    @OneToMany(mappedBy = "ausServiciosId", fetch = FetchType.LAZY)
    private List<AusAdjuntos> ausAdjuntosList;

    public AusCasoServicios() {
    }

    public AusCasoServicios(Integer id) {
        this.id = id;
    }

    public AusCasoServicios(Integer id, int maeServicioAmbitoId, int maeEstadoId, String maeEstadoCodigo, String maeEstadoValor, boolean maServicioRelacion, int cantidad, boolean borrado, int origenServicioDestino, boolean asignacionCita, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeServicioAmbitoId = maeServicioAmbitoId;
        this.maeEstadoId = maeEstadoId;
        this.maeEstadoCodigo = maeEstadoCodigo;
        this.maeEstadoValor = maeEstadoValor;
        this.maServicioRelacion = maServicioRelacion;
        this.cantidad = cantidad;
        this.borrado = borrado;
        this.origenServicioDestino = origenServicioDestino;
        this.asignacionCita = asignacionCita;
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

    public Integer getMaeTipoAdministrativoId() {
        return maeTipoAdministrativoId;
    }

    public void setMaeTipoAdministrativoId(Integer maeTipoAdministrativoId) {
        this.maeTipoAdministrativoId = maeTipoAdministrativoId;
    }

    public String getMaeTipoAdministrativoCodigo() {
        return maeTipoAdministrativoCodigo;
    }

    public void setMaeTipoAdministrativoCodigo(String maeTipoAdministrativoCodigo) {
        this.maeTipoAdministrativoCodigo = maeTipoAdministrativoCodigo;
    }

    public String getMaeTipoAdministrativoValor() {
        return maeTipoAdministrativoValor;
    }

    public void setMaeTipoAdministrativoValor(String maeTipoAdministrativoValor) {
        this.maeTipoAdministrativoValor = maeTipoAdministrativoValor;
    }

    public int getMaeServicioAmbitoId() {
        return maeServicioAmbitoId;
    }

    public void setMaeServicioAmbitoId(int maeServicioAmbitoId) {
        this.maeServicioAmbitoId = maeServicioAmbitoId;
    }

    public String getMaeServicioAmbitoCodigo() {
        return maeServicioAmbitoCodigo;
    }

    public void setMaeServicioAmbitoCodigo(String maeServicioAmbitoCodigo) {
        this.maeServicioAmbitoCodigo = maeServicioAmbitoCodigo;
    }

    public String getMaeServicioAmbitoValor() {
        return maeServicioAmbitoValor;
    }

    public void setMaeServicioAmbitoValor(String maeServicioAmbitoValor) {
        this.maeServicioAmbitoValor = maeServicioAmbitoValor;
    }

    public Integer getMaeServicioMotivoId() {
        return maeServicioMotivoId;
    }

    public void setMaeServicioMotivoId(Integer maeServicioMotivoId) {
        this.maeServicioMotivoId = maeServicioMotivoId;
    }

    public String getMaeServicioMotivoCodigo() {
        return maeServicioMotivoCodigo;
    }

    public void setMaeServicioMotivoCodigo(String maeServicioMotivoCodigo) {
        this.maeServicioMotivoCodigo = maeServicioMotivoCodigo;
    }

    public String getMaeServicioMotivoValor() {
        return maeServicioMotivoValor;
    }

    public void setMaeServicioMotivoValor(String maeServicioMotivoValor) {
        this.maeServicioMotivoValor = maeServicioMotivoValor;
    }

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(Date fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public Short getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Short tipoTecnologia) {
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

    public Integer getMaServicioId() {
        return maServicioId;
    }

    public void setMaServicioId(Integer maServicioId) {
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

    public boolean getMaServicioRelacion() {
        return maServicioRelacion;
    }

    public void setMaServicioRelacion(boolean maServicioRelacion) {
        this.maServicioRelacion = maServicioRelacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPertinencia() {
        return pertinencia;
    }

    public void setPertinencia(Boolean pertinencia) {
        this.pertinencia = pertinencia;
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

    public Boolean getMaDiagnosticosRelacion() {
        return maDiagnosticosRelacion;
    }

    public void setMaDiagnosticosRelacion(Boolean maDiagnosticosRelacion) {
        this.maDiagnosticosRelacion = maDiagnosticosRelacion;
    }

    public Integer getMaePatologiaId() {
        return maePatologiaId;
    }

    public void setMaePatologiaId(Integer maePatologiaId) {
        this.maePatologiaId = maePatologiaId;
    }

    public String getMaePatologiaCodigo() {
        return maePatologiaCodigo;
    }

    public void setMaePatologiaCodigo(String maePatologiaCodigo) {
        this.maePatologiaCodigo = maePatologiaCodigo;
    }

    public String getMaePatologiaValor() {
        return maePatologiaValor;
    }

    public void setMaePatologiaValor(String maePatologiaValor) {
        this.maePatologiaValor = maePatologiaValor;
    }

    public String getCntPrestadorSedePrescriptoraValor() {
        return cntPrestadorSedePrescriptoraValor;
    }

    public void setCntPrestadorSedePrescriptoraValor(String cntPrestadorSedePrescriptoraValor) {
        this.cntPrestadorSedePrescriptoraValor = cntPrestadorSedePrescriptoraValor;
    }

    public String getCntPrestadorSedeDestinoValor() {
        return cntPrestadorSedeDestinoValor;
    }

    public void setCntPrestadorSedeDestinoValor(String cntPrestadorSedeDestinoValor) {
        this.cntPrestadorSedeDestinoValor = cntPrestadorSedeDestinoValor;
    }

    public Boolean getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Boolean medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getMedicamentoCobertura() {
        return medicamentoCobertura;
    }

    public void setMedicamentoCobertura(Integer medicamentoCobertura) {
        this.medicamentoCobertura = medicamentoCobertura;
    }

    public Integer getServicioAtribuidoIps() {
        return servicioAtribuidoIps;
    }

    public void setServicioAtribuidoIps(Integer servicioAtribuidoIps) {
        this.servicioAtribuidoIps = servicioAtribuidoIps;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Date getFechaAplica() {
        return fechaAplica;
    }

    public void setFechaAplica(Date fechaAplica) {
        this.fechaAplica = fechaAplica;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public int getOrigenServicioDestino() {
        return origenServicioDestino;
    }

    public void setOrigenServicioDestino(int origenServicioDestino) {
        this.origenServicioDestino = origenServicioDestino;
    }

    public String getDetalleServicioDestino() {
        return detalleServicioDestino;
    }

    public void setDetalleServicioDestino(String detalleServicioDestino) {
        this.detalleServicioDestino = detalleServicioDestino;
    }

    public boolean getAsignacionCita() {
        return asignacionCita;
    }

    public void setAsignacionCita(boolean asignacionCita) {
        this.asignacionCita = asignacionCita;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public CntPrestadorSedes getCntPrestadorSedePrescriptoraId() {
        return cntPrestadorSedePrescriptoraId;
    }

    public void setCntPrestadorSedePrescriptoraId(CntPrestadorSedes cntPrestadorSedePrescriptoraId) {
        this.cntPrestadorSedePrescriptoraId = cntPrestadorSedePrescriptoraId;
    }

    public CntPrestadorSedes getCntPrestadorSedeDestinoId() {
        return cntPrestadorSedeDestinoId;
    }

    public void setCntPrestadorSedeDestinoId(CntPrestadorSedes cntPrestadorSedeDestinoId) {
        this.cntPrestadorSedeDestinoId = cntPrestadorSedeDestinoId;
    }

    public AusCasos getAusCasosId() {
        return ausCasosId;
    }

    public void setAusCasosId(AusCasos ausCasosId) {
        this.ausCasosId = ausCasosId;
    }

    public GnUsuarios getGnUsuariosAsignadoId() {
        return gnUsuariosAsignadoId;
    }

    public void setGnUsuariosAsignadoId(GnUsuarios gnUsuariosAsignadoId) {
        this.gnUsuariosAsignadoId = gnUsuariosAsignadoId;
    }

    public MaDiagnosticos getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(MaDiagnosticos maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    @XmlTransient
    public List<AusServicioGestiones> getAusServicioGestionesList() {
        return ausServicioGestionesList;
    }

    public void setAusServicioGestionesList(List<AusServicioGestiones> ausServicioGestionesList) {
        this.ausServicioGestionesList = ausServicioGestionesList;
    }

    @XmlTransient
    public List<AusCasoServicioCostos> getAusCasoServicioCostosList() {
        return ausCasoServicioCostosList;
    }

    public void setAusCasoServicioCostosList(List<AusCasoServicioCostos> ausCasoServicioCostosList) {
        this.ausCasoServicioCostosList = ausCasoServicioCostosList;
    }

    @XmlTransient
    public List<AusAdjuntos> getAusAdjuntosList() {
        return ausAdjuntosList;
    }

    public void setAusAdjuntosList(List<AusAdjuntos> ausAdjuntosList) {
        this.ausAdjuntosList = ausAdjuntosList;
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
        if (!(object instanceof AusCasoServicios)) {
            return false;
        }
        AusCasoServicios other = (AusCasoServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusCasoServicios[ id=" + id + " ]";
    }
    
}
