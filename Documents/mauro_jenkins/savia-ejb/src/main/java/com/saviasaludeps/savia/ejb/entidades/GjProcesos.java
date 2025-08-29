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
@Table(name = "gj_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesos.findAll", query = "SELECT g FROM GjProcesos g"),
    @NamedQuery(name = "GjProcesos.findById", query = "SELECT g FROM GjProcesos g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesos.findByRadicado", query = "SELECT g FROM GjProcesos g WHERE g.radicado = :radicado"),
    @NamedQuery(name = "GjProcesos.findByFechaRadicado", query = "SELECT g FROM GjProcesos g WHERE g.fechaRadicado = :fechaRadicado"),
    @NamedQuery(name = "GjProcesos.findByFechaAdmision", query = "SELECT g FROM GjProcesos g WHERE g.fechaAdmision = :fechaAdmision"),
    @NamedQuery(name = "GjProcesos.findByFechaNotificacion", query = "SELECT g FROM GjProcesos g WHERE g.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "GjProcesos.findByMaeJurisdiccionId", query = "SELECT g FROM GjProcesos g WHERE g.maeJurisdiccionId = :maeJurisdiccionId"),
    @NamedQuery(name = "GjProcesos.findByMaeJurisdiccionCodigo", query = "SELECT g FROM GjProcesos g WHERE g.maeJurisdiccionCodigo = :maeJurisdiccionCodigo"),
    @NamedQuery(name = "GjProcesos.findByMaeJurisdiccionValor", query = "SELECT g FROM GjProcesos g WHERE g.maeJurisdiccionValor = :maeJurisdiccionValor"),
    @NamedQuery(name = "GjProcesos.findByMaeClaseId", query = "SELECT g FROM GjProcesos g WHERE g.maeClaseId = :maeClaseId"),
    @NamedQuery(name = "GjProcesos.findByMaeClaseCodigo", query = "SELECT g FROM GjProcesos g WHERE g.maeClaseCodigo = :maeClaseCodigo"),
    @NamedQuery(name = "GjProcesos.findByMaeClaseValor", query = "SELECT g FROM GjProcesos g WHERE g.maeClaseValor = :maeClaseValor"),
    @NamedQuery(name = "GjProcesos.findByMaeClaseDescripcionId", query = "SELECT g FROM GjProcesos g WHERE g.maeClaseDescripcionId = :maeClaseDescripcionId"),
    @NamedQuery(name = "GjProcesos.findByMaeClaseDescripcionCodigo", query = "SELECT g FROM GjProcesos g WHERE g.maeClaseDescripcionCodigo = :maeClaseDescripcionCodigo"),
    @NamedQuery(name = "GjProcesos.findByMaeClaseDescripcionValor", query = "SELECT g FROM GjProcesos g WHERE g.maeClaseDescripcionValor = :maeClaseDescripcionValor"),
    @NamedQuery(name = "GjProcesos.findByPretencionDescripcion", query = "SELECT g FROM GjProcesos g WHERE g.pretencionDescripcion = :pretencionDescripcion"),
    @NamedQuery(name = "GjProcesos.findByCuantia", query = "SELECT g FROM GjProcesos g WHERE g.cuantia = :cuantia"),
    @NamedQuery(name = "GjProcesos.findByCuantiaObjetiva", query = "SELECT g FROM GjProcesos g WHERE g.cuantiaObjetiva = :cuantiaObjetiva"),
    @NamedQuery(name = "GjProcesos.findByEstado", query = "SELECT g FROM GjProcesos g WHERE g.estado = :estado"),
    @NamedQuery(name = "GjProcesos.findByEstadoProceso", query = "SELECT g FROM GjProcesos g WHERE g.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "GjProcesos.findByMaeInstanciaId", query = "SELECT g FROM GjProcesos g WHERE g.maeInstanciaId = :maeInstanciaId"),
    @NamedQuery(name = "GjProcesos.findByMaeInstanciaCodigo", query = "SELECT g FROM GjProcesos g WHERE g.maeInstanciaCodigo = :maeInstanciaCodigo"),
    @NamedQuery(name = "GjProcesos.findByMaeInstanciaValor", query = "SELECT g FROM GjProcesos g WHERE g.maeInstanciaValor = :maeInstanciaValor"),
    @NamedQuery(name = "GjProcesos.findByLlamamientoGarantia", query = "SELECT g FROM GjProcesos g WHERE g.llamamientoGarantia = :llamamientoGarantia"),
    @NamedQuery(name = "GjProcesos.findByMaeMedicaCautelarId", query = "SELECT g FROM GjProcesos g WHERE g.maeMedicaCautelarId = :maeMedicaCautelarId"),
    @NamedQuery(name = "GjProcesos.findByMaeMedicaCautelarCodigo", query = "SELECT g FROM GjProcesos g WHERE g.maeMedicaCautelarCodigo = :maeMedicaCautelarCodigo"),
    @NamedQuery(name = "GjProcesos.findByMaeMedicaCautelarValor", query = "SELECT g FROM GjProcesos g WHERE g.maeMedicaCautelarValor = :maeMedicaCautelarValor"),
    @NamedQuery(name = "GjProcesos.findByMontoMedida", query = "SELECT g FROM GjProcesos g WHERE g.montoMedida = :montoMedida"),
    @NamedQuery(name = "GjProcesos.findByProbabilidad", query = "SELECT g FROM GjProcesos g WHERE g.probabilidad = :probabilidad"),
    @NamedQuery(name = "GjProcesos.findByRiesgoClasificacion", query = "SELECT g FROM GjProcesos g WHERE g.riesgoClasificacion = :riesgoClasificacion"),
    @NamedQuery(name = "GjProcesos.findByClaseProvision", query = "SELECT g FROM GjProcesos g WHERE g.claseProvision = :claseProvision"),
    @NamedQuery(name = "GjProcesos.findByFechaTerminacion", query = "SELECT g FROM GjProcesos g WHERE g.fechaTerminacion = :fechaTerminacion"),
    @NamedQuery(name = "GjProcesos.findBySentidoSentencia", query = "SELECT g FROM GjProcesos g WHERE g.sentidoSentencia = :sentidoSentencia"),
    @NamedQuery(name = "GjProcesos.findByValorRiesgoCondena", query = "SELECT g FROM GjProcesos g WHERE g.valorRiesgoCondena = :valorRiesgoCondena"),
    @NamedQuery(name = "GjProcesos.findByValorSentenciaEjecutoria", query = "SELECT g FROM GjProcesos g WHERE g.valorSentenciaEjecutoria = :valorSentenciaEjecutoria"),
    @NamedQuery(name = "GjProcesos.findByEstadoCumplimientoCondena", query = "SELECT g FROM GjProcesos g WHERE g.estadoCumplimientoCondena = :estadoCumplimientoCondena"),
    @NamedQuery(name = "GjProcesos.findByMaeActuacionTerminacionId", query = "SELECT g FROM GjProcesos g WHERE g.maeActuacionTerminacionId = :maeActuacionTerminacionId"),
    @NamedQuery(name = "GjProcesos.findByMaeActuacionTerminacionCodigo", query = "SELECT g FROM GjProcesos g WHERE g.maeActuacionTerminacionCodigo = :maeActuacionTerminacionCodigo"),
    @NamedQuery(name = "GjProcesos.findByMaeActuacionTerminacionValor", query = "SELECT g FROM GjProcesos g WHERE g.maeActuacionTerminacionValor = :maeActuacionTerminacionValor"),
    @NamedQuery(name = "GjProcesos.findByValorAcuerdoTransaccion", query = "SELECT g FROM GjProcesos g WHERE g.valorAcuerdoTransaccion = :valorAcuerdoTransaccion"),
    @NamedQuery(name = "GjProcesos.findByFechaUltimaActuacion", query = "SELECT g FROM GjProcesos g WHERE g.fechaUltimaActuacion = :fechaUltimaActuacion"),
    @NamedQuery(name = "GjProcesos.findByFechaContestacion", query = "SELECT g FROM GjProcesos g WHERE g.fechaContestacion = :fechaContestacion"),
    @NamedQuery(name = "GjProcesos.findByUsuariosCrea", query = "SELECT g FROM GjProcesos g WHERE g.usuariosCrea = :usuariosCrea"),
    @NamedQuery(name = "GjProcesos.findByTerminalCrea", query = "SELECT g FROM GjProcesos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesos.findByFechaHoraCrea", query = "SELECT g FROM GjProcesos g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjProcesos.findByUsuarioModifica", query = "SELECT g FROM GjProcesos g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjProcesos.findByTerminalModifica", query = "SELECT g FROM GjProcesos g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjProcesos.findByFechaHoraModifica", query = "SELECT g FROM GjProcesos g WHERE g.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "GjProcesos.findByBorrado", query = "SELECT g FROM GjProcesos g WHERE g.borrado = :borrado"),
    @NamedQuery(name = "GjProcesos.findByUsuarioBorra", query = "SELECT g FROM GjProcesos g WHERE g.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "GjProcesos.findByTerminalBorra", query = "SELECT g FROM GjProcesos g WHERE g.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "GjProcesos.findByFechaHoraBorra", query = "SELECT g FROM GjProcesos g WHERE g.fechaHoraBorra = :fechaHoraBorra")})
public class GjProcesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "radicado")
    private String radicado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_radicado")
    @Temporal(TemporalType.DATE)
    private Date fechaRadicado;
    @Column(name = "fecha_admision")
    @Temporal(TemporalType.DATE)
    private Date fechaAdmision;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_jurisdiccion_id")
    private int maeJurisdiccionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_jurisdiccion_codigo")
    private String maeJurisdiccionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_jurisdiccion_valor")
    private String maeJurisdiccionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_clase_id")
    private int maeClaseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_clase_codigo")
    private String maeClaseCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_clase_valor")
    private String maeClaseValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_clase_descripcion_id")
    private int maeClaseDescripcionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_clase_descripcion_codigo")
    private String maeClaseDescripcionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_clase_descripcion_valor")
    private String maeClaseDescripcionValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "pretencion_descripcion")
    private String pretencionDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuantia")
    private BigDecimal cuantia;
    @Column(name = "cuantia_objetiva")
    private BigDecimal cuantiaObjetiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_proceso")
    private short estadoProceso;
    @Column(name = "mae_instancia_id")
    private Integer maeInstanciaId;
    @Size(max = 8)
    @Column(name = "mae_instancia_codigo")
    private String maeInstanciaCodigo;
    @Size(max = 128)
    @Column(name = "mae_instancia_valor")
    private String maeInstanciaValor;
    @Column(name = "llamamiento_garantia")
    private Boolean llamamientoGarantia;
    @Column(name = "mae_medica_cautelar_id")
    private Integer maeMedicaCautelarId;
    @Size(max = 8)
    @Column(name = "mae_medica_cautelar_codigo")
    private String maeMedicaCautelarCodigo;
    @Size(max = 128)
    @Column(name = "mae_medica_cautelar_valor")
    private String maeMedicaCautelarValor;
    @Column(name = "monto_medida")
    private BigDecimal montoMedida;
    @Column(name = "probabilidad")
    private Short probabilidad;
    @Column(name = "riesgo_clasificacion")
    private Short riesgoClasificacion;
    @Column(name = "clase_provision")
    private Short claseProvision;
    @Column(name = "fecha_terminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminacion;
    @Column(name = "sentido_sentencia")
    private Short sentidoSentencia;
    @Column(name = "valor_riesgo_condena")
    private BigDecimal valorRiesgoCondena;
    @Column(name = "valor_sentencia_ejecutoria")
    private BigDecimal valorSentenciaEjecutoria;
    @Column(name = "estado_cumplimiento_condena")
    private Short estadoCumplimientoCondena;
    @Column(name = "mae_actuacion_terminacion_id")
    private Integer maeActuacionTerminacionId;
    @Size(max = 8)
    @Column(name = "mae_actuacion_terminacion_codigo")
    private String maeActuacionTerminacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_actuacion_terminacion_valor")
    private String maeActuacionTerminacionValor;
    @Column(name = "valor_acuerdo_transaccion")
    private BigDecimal valorAcuerdoTransaccion;
    @Column(name = "fecha_ultima_actuacion")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaActuacion;
    @Lob
    @Column(name = "ultima_actuacion")
    private byte[] ultimaActuacion;
    @Column(name = "fecha_contestacion")
    @Temporal(TemporalType.DATE)
    private Date fechaContestacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuarios_crea")
    private String usuariosCrea;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesosId", fetch = FetchType.LAZY)
    private List<GjProcesoPretenciones> gjProcesoPretencionesList;
    @JoinColumn(name = "gn_ubicaciones_proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesProcesoId;
    @JoinColumn(name = "tu_juzgados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuJuzgados tuJuzgadosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesosId", fetch = FetchType.LAZY)
    private List<GjProcesoTerceros> gjProcesoTercerosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesosId", fetch = FetchType.LAZY)
    private List<GjProcesoAdjuntos> gjProcesoAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesosId", fetch = FetchType.LAZY)
    private List<GjProcesoAbogados> gjProcesoAbogadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesosId", fetch = FetchType.LAZY)
    private List<GjProcesoGarantias> gjProcesoGarantiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gjProcesosId", fetch = FetchType.LAZY)
    private List<GjProcesoHistoricos> gjProcesoHistoricosList;

    public GjProcesos() {
    }

    public GjProcesos(Integer id) {
        this.id = id;
    }

    public GjProcesos(Integer id, String radicado, Date fechaRadicado, int maeJurisdiccionId, String maeJurisdiccionCodigo, String maeJurisdiccionValor, int maeClaseId, String maeClaseCodigo, String maeClaseValor, int maeClaseDescripcionId, String maeClaseDescripcionCodigo, String maeClaseDescripcionValor, String pretencionDescripcion, BigDecimal cuantia, short estado, short estadoProceso, String usuariosCrea, String terminalCrea, Date fechaHoraCrea, boolean borrado) {
        this.id = id;
        this.radicado = radicado;
        this.fechaRadicado = fechaRadicado;
        this.maeJurisdiccionId = maeJurisdiccionId;
        this.maeJurisdiccionCodigo = maeJurisdiccionCodigo;
        this.maeJurisdiccionValor = maeJurisdiccionValor;
        this.maeClaseId = maeClaseId;
        this.maeClaseCodigo = maeClaseCodigo;
        this.maeClaseValor = maeClaseValor;
        this.maeClaseDescripcionId = maeClaseDescripcionId;
        this.maeClaseDescripcionCodigo = maeClaseDescripcionCodigo;
        this.maeClaseDescripcionValor = maeClaseDescripcionValor;
        this.pretencionDescripcion = pretencionDescripcion;
        this.cuantia = cuantia;
        this.estado = estado;
        this.estadoProceso = estadoProceso;
        this.usuariosCrea = usuariosCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.borrado = borrado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    public Date getFechaAdmision() {
        return fechaAdmision;
    }

    public void setFechaAdmision(Date fechaAdmision) {
        this.fechaAdmision = fechaAdmision;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public int getMaeJurisdiccionId() {
        return maeJurisdiccionId;
    }

    public void setMaeJurisdiccionId(int maeJurisdiccionId) {
        this.maeJurisdiccionId = maeJurisdiccionId;
    }

    public String getMaeJurisdiccionCodigo() {
        return maeJurisdiccionCodigo;
    }

    public void setMaeJurisdiccionCodigo(String maeJurisdiccionCodigo) {
        this.maeJurisdiccionCodigo = maeJurisdiccionCodigo;
    }

    public String getMaeJurisdiccionValor() {
        return maeJurisdiccionValor;
    }

    public void setMaeJurisdiccionValor(String maeJurisdiccionValor) {
        this.maeJurisdiccionValor = maeJurisdiccionValor;
    }

    public int getMaeClaseId() {
        return maeClaseId;
    }

    public void setMaeClaseId(int maeClaseId) {
        this.maeClaseId = maeClaseId;
    }

    public String getMaeClaseCodigo() {
        return maeClaseCodigo;
    }

    public void setMaeClaseCodigo(String maeClaseCodigo) {
        this.maeClaseCodigo = maeClaseCodigo;
    }

    public String getMaeClaseValor() {
        return maeClaseValor;
    }

    public void setMaeClaseValor(String maeClaseValor) {
        this.maeClaseValor = maeClaseValor;
    }

    public int getMaeClaseDescripcionId() {
        return maeClaseDescripcionId;
    }

    public void setMaeClaseDescripcionId(int maeClaseDescripcionId) {
        this.maeClaseDescripcionId = maeClaseDescripcionId;
    }

    public String getMaeClaseDescripcionCodigo() {
        return maeClaseDescripcionCodigo;
    }

    public void setMaeClaseDescripcionCodigo(String maeClaseDescripcionCodigo) {
        this.maeClaseDescripcionCodigo = maeClaseDescripcionCodigo;
    }

    public String getMaeClaseDescripcionValor() {
        return maeClaseDescripcionValor;
    }

    public void setMaeClaseDescripcionValor(String maeClaseDescripcionValor) {
        this.maeClaseDescripcionValor = maeClaseDescripcionValor;
    }

    public String getPretencionDescripcion() {
        return pretencionDescripcion;
    }

    public void setPretencionDescripcion(String pretencionDescripcion) {
        this.pretencionDescripcion = pretencionDescripcion;
    }

    public BigDecimal getCuantia() {
        return cuantia;
    }

    public void setCuantia(BigDecimal cuantia) {
        this.cuantia = cuantia;
    }

    public BigDecimal getCuantiaObjetiva() {
        return cuantiaObjetiva;
    }

    public void setCuantiaObjetiva(BigDecimal cuantiaObjetiva) {
        this.cuantiaObjetiva = cuantiaObjetiva;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(short estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Integer getMaeInstanciaId() {
        return maeInstanciaId;
    }

    public void setMaeInstanciaId(Integer maeInstanciaId) {
        this.maeInstanciaId = maeInstanciaId;
    }

    public String getMaeInstanciaCodigo() {
        return maeInstanciaCodigo;
    }

    public void setMaeInstanciaCodigo(String maeInstanciaCodigo) {
        this.maeInstanciaCodigo = maeInstanciaCodigo;
    }

    public String getMaeInstanciaValor() {
        return maeInstanciaValor;
    }

    public void setMaeInstanciaValor(String maeInstanciaValor) {
        this.maeInstanciaValor = maeInstanciaValor;
    }

    public Boolean getLlamamientoGarantia() {
        return llamamientoGarantia;
    }

    public void setLlamamientoGarantia(Boolean llamamientoGarantia) {
        this.llamamientoGarantia = llamamientoGarantia;
    }

    public Integer getMaeMedicaCautelarId() {
        return maeMedicaCautelarId;
    }

    public void setMaeMedicaCautelarId(Integer maeMedicaCautelarId) {
        this.maeMedicaCautelarId = maeMedicaCautelarId;
    }

    public String getMaeMedicaCautelarCodigo() {
        return maeMedicaCautelarCodigo;
    }

    public void setMaeMedicaCautelarCodigo(String maeMedicaCautelarCodigo) {
        this.maeMedicaCautelarCodigo = maeMedicaCautelarCodigo;
    }

    public String getMaeMedicaCautelarValor() {
        return maeMedicaCautelarValor;
    }

    public void setMaeMedicaCautelarValor(String maeMedicaCautelarValor) {
        this.maeMedicaCautelarValor = maeMedicaCautelarValor;
    }

    public BigDecimal getMontoMedida() {
        return montoMedida;
    }

    public void setMontoMedida(BigDecimal montoMedida) {
        this.montoMedida = montoMedida;
    }

    public Short getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Short probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Short getRiesgoClasificacion() {
        return riesgoClasificacion;
    }

    public void setRiesgoClasificacion(Short riesgoClasificacion) {
        this.riesgoClasificacion = riesgoClasificacion;
    }

    public Short getClaseProvision() {
        return claseProvision;
    }

    public void setClaseProvision(Short claseProvision) {
        this.claseProvision = claseProvision;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Short getSentidoSentencia() {
        return sentidoSentencia;
    }

    public void setSentidoSentencia(Short sentidoSentencia) {
        this.sentidoSentencia = sentidoSentencia;
    }

    public BigDecimal getValorRiesgoCondena() {
        return valorRiesgoCondena;
    }

    public void setValorRiesgoCondena(BigDecimal valorRiesgoCondena) {
        this.valorRiesgoCondena = valorRiesgoCondena;
    }

    public BigDecimal getValorSentenciaEjecutoria() {
        return valorSentenciaEjecutoria;
    }

    public void setValorSentenciaEjecutoria(BigDecimal valorSentenciaEjecutoria) {
        this.valorSentenciaEjecutoria = valorSentenciaEjecutoria;
    }

    public Short getEstadoCumplimientoCondena() {
        return estadoCumplimientoCondena;
    }

    public void setEstadoCumplimientoCondena(Short estadoCumplimientoCondena) {
        this.estadoCumplimientoCondena = estadoCumplimientoCondena;
    }

    public Integer getMaeActuacionTerminacionId() {
        return maeActuacionTerminacionId;
    }

    public void setMaeActuacionTerminacionId(Integer maeActuacionTerminacionId) {
        this.maeActuacionTerminacionId = maeActuacionTerminacionId;
    }

    public String getMaeActuacionTerminacionCodigo() {
        return maeActuacionTerminacionCodigo;
    }

    public void setMaeActuacionTerminacionCodigo(String maeActuacionTerminacionCodigo) {
        this.maeActuacionTerminacionCodigo = maeActuacionTerminacionCodigo;
    }

    public String getMaeActuacionTerminacionValor() {
        return maeActuacionTerminacionValor;
    }

    public void setMaeActuacionTerminacionValor(String maeActuacionTerminacionValor) {
        this.maeActuacionTerminacionValor = maeActuacionTerminacionValor;
    }

    public BigDecimal getValorAcuerdoTransaccion() {
        return valorAcuerdoTransaccion;
    }

    public void setValorAcuerdoTransaccion(BigDecimal valorAcuerdoTransaccion) {
        this.valorAcuerdoTransaccion = valorAcuerdoTransaccion;
    }

    public Date getFechaUltimaActuacion() {
        return fechaUltimaActuacion;
    }

    public void setFechaUltimaActuacion(Date fechaUltimaActuacion) {
        this.fechaUltimaActuacion = fechaUltimaActuacion;
    }

    public byte[] getUltimaActuacion() {
        return ultimaActuacion;
    }

    public void setUltimaActuacion(byte[] ultimaActuacion) {
        this.ultimaActuacion = ultimaActuacion;
    }

    public Date getFechaContestacion() {
        return fechaContestacion;
    }

    public void setFechaContestacion(Date fechaContestacion) {
        this.fechaContestacion = fechaContestacion;
    }

    public String getUsuariosCrea() {
        return usuariosCrea;
    }

    public void setUsuariosCrea(String usuariosCrea) {
        this.usuariosCrea = usuariosCrea;
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

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    @XmlTransient
    public List<GjProcesoPretenciones> getGjProcesoPretencionesList() {
        return gjProcesoPretencionesList;
    }

    public void setGjProcesoPretencionesList(List<GjProcesoPretenciones> gjProcesoPretencionesList) {
        this.gjProcesoPretencionesList = gjProcesoPretencionesList;
    }

    public GnUbicaciones getGnUbicacionesProcesoId() {
        return gnUbicacionesProcesoId;
    }

    public void setGnUbicacionesProcesoId(GnUbicaciones gnUbicacionesProcesoId) {
        this.gnUbicacionesProcesoId = gnUbicacionesProcesoId;
    }

    public TuJuzgados getTuJuzgadosId() {
        return tuJuzgadosId;
    }

    public void setTuJuzgadosId(TuJuzgados tuJuzgadosId) {
        this.tuJuzgadosId = tuJuzgadosId;
    }

    @XmlTransient
    public List<GjProcesoTerceros> getGjProcesoTercerosList() {
        return gjProcesoTercerosList;
    }

    public void setGjProcesoTercerosList(List<GjProcesoTerceros> gjProcesoTercerosList) {
        this.gjProcesoTercerosList = gjProcesoTercerosList;
    }

    @XmlTransient
    public List<GjProcesoAdjuntos> getGjProcesoAdjuntosList() {
        return gjProcesoAdjuntosList;
    }

    public void setGjProcesoAdjuntosList(List<GjProcesoAdjuntos> gjProcesoAdjuntosList) {
        this.gjProcesoAdjuntosList = gjProcesoAdjuntosList;
    }

    @XmlTransient
    public List<GjProcesoAbogados> getGjProcesoAbogadosList() {
        return gjProcesoAbogadosList;
    }

    public void setGjProcesoAbogadosList(List<GjProcesoAbogados> gjProcesoAbogadosList) {
        this.gjProcesoAbogadosList = gjProcesoAbogadosList;
    }

    @XmlTransient
    public List<GjProcesoGarantias> getGjProcesoGarantiasList() {
        return gjProcesoGarantiasList;
    }

    public void setGjProcesoGarantiasList(List<GjProcesoGarantias> gjProcesoGarantiasList) {
        this.gjProcesoGarantiasList = gjProcesoGarantiasList;
    }

    @XmlTransient
    public List<GjProcesoHistoricos> getGjProcesoHistoricosList() {
        return gjProcesoHistoricosList;
    }

    public void setGjProcesoHistoricosList(List<GjProcesoHistoricos> gjProcesoHistoricosList) {
        this.gjProcesoHistoricosList = gjProcesoHistoricosList;
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
        if (!(object instanceof GjProcesos)) {
            return false;
        }
        GjProcesos other = (GjProcesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesos[ id=" + id + " ]";
    }
    
}
