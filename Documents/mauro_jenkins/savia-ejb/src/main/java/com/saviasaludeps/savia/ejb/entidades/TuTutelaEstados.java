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
@Table(name = "tu_tutela_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuTutelaEstados.findAll", query = "SELECT t FROM TuTutelaEstados t"),
    @NamedQuery(name = "TuTutelaEstados.findById", query = "SELECT t FROM TuTutelaEstados t WHERE t.id = :id"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeEstadoId", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeEstadoCodigo", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeEstadoValor", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeProcesoId", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeProcesoId = :maeProcesoId"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeProcesoCodigo", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeProcesoCodigo = :maeProcesoCodigo"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeProcesoValor", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeProcesoValor = :maeProcesoValor"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeClaseSancionId", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeClaseSancionId = :maeClaseSancionId"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeClaseSancionCodigo", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeClaseSancionCodigo = :maeClaseSancionCodigo"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeClaseSancionValor", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeClaseSancionValor = :maeClaseSancionValor"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeClaseArrestoId", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeClaseArrestoId = :maeClaseArrestoId"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeClaseArrestoCodigo", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeClaseArrestoCodigo = :maeClaseArrestoCodigo"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeClaseArrestoValor", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeClaseArrestoValor = :maeClaseArrestoValor"),
    @NamedQuery(name = "TuTutelaEstados.findByCuantia", query = "SELECT t FROM TuTutelaEstados t WHERE t.cuantia = :cuantia"),
    @NamedQuery(name = "TuTutelaEstados.findByDiasArresto", query = "SELECT t FROM TuTutelaEstados t WHERE t.diasArresto = :diasArresto"),
    @NamedQuery(name = "TuTutelaEstados.findByUvt", query = "SELECT t FROM TuTutelaEstados t WHERE t.uvt = :uvt"),
    @NamedQuery(name = "TuTutelaEstados.findByEmailGestorRemitente", query = "SELECT t FROM TuTutelaEstados t WHERE t.emailGestorRemitente = :emailGestorRemitente"),
    @NamedQuery(name = "TuTutelaEstados.findByEntregaSucesiva", query = "SELECT t FROM TuTutelaEstados t WHERE t.entregaSucesiva = :entregaSucesiva"),
    @NamedQuery(name = "TuTutelaEstados.findByExoneracion", query = "SELECT t FROM TuTutelaEstados t WHERE t.exoneracion = :exoneracion"),
    @NamedQuery(name = "TuTutelaEstados.findByImpugnacion", query = "SELECT t FROM TuTutelaEstados t WHERE t.impugnacion = :impugnacion"),
    @NamedQuery(name = "TuTutelaEstados.findByIntegralidad", query = "SELECT t FROM TuTutelaEstados t WHERE t.integralidad = :integralidad"),
    @NamedQuery(name = "TuTutelaEstados.findByMedidaProvisional", query = "SELECT t FROM TuTutelaEstados t WHERE t.medidaProvisional = :medidaProvisional"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeTipoFalloId", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeTipoFalloId = :maeTipoFalloId"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeTipoFalloCodigo", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeTipoFalloCodigo = :maeTipoFalloCodigo"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeTipoFalloValor", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeTipoFalloValor = :maeTipoFalloValor"),
    @NamedQuery(name = "TuTutelaEstados.findByFechaNotificacion", query = "SELECT t FROM TuTutelaEstados t WHERE t.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "TuTutelaEstados.findByFechaVencimiento", query = "SELECT t FROM TuTutelaEstados t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TuTutelaEstados.findByCantidadServicio", query = "SELECT t FROM TuTutelaEstados t WHERE t.cantidadServicio = :cantidadServicio"),
    @NamedQuery(name = "TuTutelaEstados.findByCantidadServicioCumplido", query = "SELECT t FROM TuTutelaEstados t WHERE t.cantidadServicioCumplido = :cantidadServicioCumplido"),
    @NamedQuery(name = "TuTutelaEstados.findByDiasCambioEstado", query = "SELECT t FROM TuTutelaEstados t WHERE t.diasCambioEstado = :diasCambioEstado"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeSmlvId", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeSmlvId = :maeSmlvId"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeSmlvCodigo", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeSmlvCodigo = :maeSmlvCodigo"),
    @NamedQuery(name = "TuTutelaEstados.findByMaeSmlvValor", query = "SELECT t FROM TuTutelaEstados t WHERE t.maeSmlvValor = :maeSmlvValor"),
    @NamedQuery(name = "TuTutelaEstados.findByCantidadItems", query = "SELECT t FROM TuTutelaEstados t WHERE t.cantidadItems = :cantidadItems"),
    @NamedQuery(name = "TuTutelaEstados.findByCantidadItemsCerrados", query = "SELECT t FROM TuTutelaEstados t WHERE t.cantidadItemsCerrados = :cantidadItemsCerrados"),
    @NamedQuery(name = "TuTutelaEstados.findByUsuarioCrea", query = "SELECT t FROM TuTutelaEstados t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuTutelaEstados.findByTerminalCrea", query = "SELECT t FROM TuTutelaEstados t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuTutelaEstados.findByFechaHoraCrea", query = "SELECT t FROM TuTutelaEstados t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuTutelaEstados.findByUsuarioModifica", query = "SELECT t FROM TuTutelaEstados t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuTutelaEstados.findByTerminalModifica", query = "SELECT t FROM TuTutelaEstados t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuTutelaEstados.findByFechaHoraModifica", query = "SELECT t FROM TuTutelaEstados t WHERE t.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "TuTutelaEstados.findByBorrado", query = "SELECT t FROM TuTutelaEstados t WHERE t.borrado = :borrado"),
    @NamedQuery(name = "TuTutelaEstados.findByUsuarioBorra", query = "SELECT t FROM TuTutelaEstados t WHERE t.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "TuTutelaEstados.findByTerminalBorra", query = "SELECT t FROM TuTutelaEstados t WHERE t.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "TuTutelaEstados.findByFechaHoraBorra", query = "SELECT t FROM TuTutelaEstados t WHERE t.fechaHoraBorra = :fechaHoraBorra")})
public class TuTutelaEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Size(min = 1, max = 64)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_proceso_id")
    private int maeProcesoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_proceso_codigo")
    private String maeProcesoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "mae_proceso_valor")
    private String maeProcesoValor;
    @Column(name = "mae_clase_sancion_id")
    private Integer maeClaseSancionId;
    @Size(max = 8)
    @Column(name = "mae_clase_sancion_codigo")
    private String maeClaseSancionCodigo;
    @Size(max = 128)
    @Column(name = "mae_clase_sancion_valor")
    private String maeClaseSancionValor;
    @Column(name = "mae_clase_arresto_id")
    private Integer maeClaseArrestoId;
    @Size(max = 8)
    @Column(name = "mae_clase_arresto_codigo")
    private String maeClaseArrestoCodigo;
    @Size(max = 128)
    @Column(name = "mae_clase_arresto_valor")
    private String maeClaseArrestoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cuantia")
    private BigDecimal cuantia;
    @Column(name = "dias_arresto")
    private Integer diasArresto;
    @Column(name = "uvt")
    private BigDecimal uvt;
    @Size(max = 128)
    @Column(name = "email_gestor_remitente")
    private String emailGestorRemitente;
    @Column(name = "entrega_sucesiva")
    private Boolean entregaSucesiva;
    @Column(name = "exoneracion")
    private Boolean exoneracion;
    @Column(name = "impugnacion")
    private Boolean impugnacion;
    @Column(name = "integralidad")
    private Boolean integralidad;
    @Column(name = "medida_provisional")
    private Boolean medidaProvisional;
    @Column(name = "mae_tipo_fallo_id")
    private Integer maeTipoFalloId;
    @Size(max = 8)
    @Column(name = "mae_tipo_fallo_codigo")
    private String maeTipoFalloCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_fallo_valor")
    private String maeTipoFalloValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Lob
    @Size(max = 16777215)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "cantidad_servicio")
    private Integer cantidadServicio;
    @Column(name = "cantidad_servicio_cumplido")
    private Integer cantidadServicioCumplido;
    @Column(name = "dias_cambio_estado")
    private Integer diasCambioEstado;
    @Column(name = "mae_smlv_id")
    private Integer maeSmlvId;
    @Size(max = 8)
    @Column(name = "mae_smlv_codigo")
    private String maeSmlvCodigo;
    @Size(max = 128)
    @Column(name = "mae_smlv_valor")
    private String maeSmlvValor;
    @Column(name = "cantidad_items")
    private Integer cantidadItems;
    @Column(name = "cantidad_items_cerrados")
    private Integer cantidadItemsCerrados;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelaEstadosId", fetch = FetchType.LAZY)
    private List<TuTutelaItems> tuTutelaItemsList;
    @OneToMany(mappedBy = "actualTuTutelaEstadosId", fetch = FetchType.LAZY)
    private List<TuTutelas> tuTutelasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelaEstadosId", fetch = FetchType.LAZY)
    private List<TuTutelaEstadoRepresentantes> tuTutelaEstadoRepresentantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelaEstadosId", fetch = FetchType.LAZY)
    private List<TuSeguimientos> tuSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelaEstadosId", fetch = FetchType.LAZY)
    private List<TuAdjuntos> tuAdjuntosList;
    @JoinColumn(name = "responsable_gn_usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios responsableGnUsuarioId;
    @JoinColumn(name = "gestor_gn_usuario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gestorGnUsuarioId;
    @JoinColumn(name = "abogado_gn_usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios abogadoGnUsuarioId;
    @JoinColumn(name = "medico_gn_usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios medicoGnUsuarioId;
    @JoinColumn(name = "tu_juzgados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuJuzgados tuJuzgadosId;
    @JoinColumn(name = "tu_tutelas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelas tuTutelasId;

    public TuTutelaEstados() {
    }

    public TuTutelaEstados(Integer id) {
        this.id = id;
    }

    public TuTutelaEstados(Integer id, int maeEstadoId, String maeEstadoCodigo, String maeEstadoValor, int maeProcesoId, String maeProcesoCodigo, String maeProcesoValor, Date fechaNotificacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, boolean borrado) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.maeEstadoCodigo = maeEstadoCodigo;
        this.maeEstadoValor = maeEstadoValor;
        this.maeProcesoId = maeProcesoId;
        this.maeProcesoCodigo = maeProcesoCodigo;
        this.maeProcesoValor = maeProcesoValor;
        this.fechaNotificacion = fechaNotificacion;
        this.usuarioCrea = usuarioCrea;
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

    public int getMaeProcesoId() {
        return maeProcesoId;
    }

    public void setMaeProcesoId(int maeProcesoId) {
        this.maeProcesoId = maeProcesoId;
    }

    public String getMaeProcesoCodigo() {
        return maeProcesoCodigo;
    }

    public void setMaeProcesoCodigo(String maeProcesoCodigo) {
        this.maeProcesoCodigo = maeProcesoCodigo;
    }

    public String getMaeProcesoValor() {
        return maeProcesoValor;
    }

    public void setMaeProcesoValor(String maeProcesoValor) {
        this.maeProcesoValor = maeProcesoValor;
    }

    public Integer getMaeClaseSancionId() {
        return maeClaseSancionId;
    }

    public void setMaeClaseSancionId(Integer maeClaseSancionId) {
        this.maeClaseSancionId = maeClaseSancionId;
    }

    public String getMaeClaseSancionCodigo() {
        return maeClaseSancionCodigo;
    }

    public void setMaeClaseSancionCodigo(String maeClaseSancionCodigo) {
        this.maeClaseSancionCodigo = maeClaseSancionCodigo;
    }

    public String getMaeClaseSancionValor() {
        return maeClaseSancionValor;
    }

    public void setMaeClaseSancionValor(String maeClaseSancionValor) {
        this.maeClaseSancionValor = maeClaseSancionValor;
    }

    public Integer getMaeClaseArrestoId() {
        return maeClaseArrestoId;
    }

    public void setMaeClaseArrestoId(Integer maeClaseArrestoId) {
        this.maeClaseArrestoId = maeClaseArrestoId;
    }

    public String getMaeClaseArrestoCodigo() {
        return maeClaseArrestoCodigo;
    }

    public void setMaeClaseArrestoCodigo(String maeClaseArrestoCodigo) {
        this.maeClaseArrestoCodigo = maeClaseArrestoCodigo;
    }

    public String getMaeClaseArrestoValor() {
        return maeClaseArrestoValor;
    }

    public void setMaeClaseArrestoValor(String maeClaseArrestoValor) {
        this.maeClaseArrestoValor = maeClaseArrestoValor;
    }

    public BigDecimal getCuantia() {
        return cuantia;
    }

    public void setCuantia(BigDecimal cuantia) {
        this.cuantia = cuantia;
    }

    public Integer getDiasArresto() {
        return diasArresto;
    }

    public void setDiasArresto(Integer diasArresto) {
        this.diasArresto = diasArresto;
    }

    public BigDecimal getUvt() {
        return uvt;
    }

    public void setUvt(BigDecimal uvt) {
        this.uvt = uvt;
    }

    public String getEmailGestorRemitente() {
        return emailGestorRemitente;
    }

    public void setEmailGestorRemitente(String emailGestorRemitente) {
        this.emailGestorRemitente = emailGestorRemitente;
    }

    public Boolean getEntregaSucesiva() {
        return entregaSucesiva;
    }

    public void setEntregaSucesiva(Boolean entregaSucesiva) {
        this.entregaSucesiva = entregaSucesiva;
    }

    public Boolean getExoneracion() {
        return exoneracion;
    }

    public void setExoneracion(Boolean exoneracion) {
        this.exoneracion = exoneracion;
    }

    public Boolean getImpugnacion() {
        return impugnacion;
    }

    public void setImpugnacion(Boolean impugnacion) {
        this.impugnacion = impugnacion;
    }

    public Boolean getIntegralidad() {
        return integralidad;
    }

    public void setIntegralidad(Boolean integralidad) {
        this.integralidad = integralidad;
    }

    public Boolean getMedidaProvisional() {
        return medidaProvisional;
    }

    public void setMedidaProvisional(Boolean medidaProvisional) {
        this.medidaProvisional = medidaProvisional;
    }

    public Integer getMaeTipoFalloId() {
        return maeTipoFalloId;
    }

    public void setMaeTipoFalloId(Integer maeTipoFalloId) {
        this.maeTipoFalloId = maeTipoFalloId;
    }

    public String getMaeTipoFalloCodigo() {
        return maeTipoFalloCodigo;
    }

    public void setMaeTipoFalloCodigo(String maeTipoFalloCodigo) {
        this.maeTipoFalloCodigo = maeTipoFalloCodigo;
    }

    public String getMaeTipoFalloValor() {
        return maeTipoFalloValor;
    }

    public void setMaeTipoFalloValor(String maeTipoFalloValor) {
        this.maeTipoFalloValor = maeTipoFalloValor;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCantidadServicio() {
        return cantidadServicio;
    }

    public void setCantidadServicio(Integer cantidadServicio) {
        this.cantidadServicio = cantidadServicio;
    }

    public Integer getCantidadServicioCumplido() {
        return cantidadServicioCumplido;
    }

    public void setCantidadServicioCumplido(Integer cantidadServicioCumplido) {
        this.cantidadServicioCumplido = cantidadServicioCumplido;
    }

    public Integer getDiasCambioEstado() {
        return diasCambioEstado;
    }

    public void setDiasCambioEstado(Integer diasCambioEstado) {
        this.diasCambioEstado = diasCambioEstado;
    }

    public Integer getMaeSmlvId() {
        return maeSmlvId;
    }

    public void setMaeSmlvId(Integer maeSmlvId) {
        this.maeSmlvId = maeSmlvId;
    }

    public String getMaeSmlvCodigo() {
        return maeSmlvCodigo;
    }

    public void setMaeSmlvCodigo(String maeSmlvCodigo) {
        this.maeSmlvCodigo = maeSmlvCodigo;
    }

    public String getMaeSmlvValor() {
        return maeSmlvValor;
    }

    public void setMaeSmlvValor(String maeSmlvValor) {
        this.maeSmlvValor = maeSmlvValor;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public Integer getCantidadItemsCerrados() {
        return cantidadItemsCerrados;
    }

    public void setCantidadItemsCerrados(Integer cantidadItemsCerrados) {
        this.cantidadItemsCerrados = cantidadItemsCerrados;
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
    public List<TuTutelaItems> getTuTutelaItemsList() {
        return tuTutelaItemsList;
    }

    public void setTuTutelaItemsList(List<TuTutelaItems> tuTutelaItemsList) {
        this.tuTutelaItemsList = tuTutelaItemsList;
    }

    @XmlTransient
    public List<TuTutelas> getTuTutelasList() {
        return tuTutelasList;
    }

    public void setTuTutelasList(List<TuTutelas> tuTutelasList) {
        this.tuTutelasList = tuTutelasList;
    }

    @XmlTransient
    public List<TuTutelaEstadoRepresentantes> getTuTutelaEstadoRepresentantesList() {
        return tuTutelaEstadoRepresentantesList;
    }

    public void setTuTutelaEstadoRepresentantesList(List<TuTutelaEstadoRepresentantes> tuTutelaEstadoRepresentantesList) {
        this.tuTutelaEstadoRepresentantesList = tuTutelaEstadoRepresentantesList;
    }

    @XmlTransient
    public List<TuSeguimientos> getTuSeguimientosList() {
        return tuSeguimientosList;
    }

    public void setTuSeguimientosList(List<TuSeguimientos> tuSeguimientosList) {
        this.tuSeguimientosList = tuSeguimientosList;
    }

    @XmlTransient
    public List<TuAdjuntos> getTuAdjuntosList() {
        return tuAdjuntosList;
    }

    public void setTuAdjuntosList(List<TuAdjuntos> tuAdjuntosList) {
        this.tuAdjuntosList = tuAdjuntosList;
    }

    public GnUsuarios getResponsableGnUsuarioId() {
        return responsableGnUsuarioId;
    }

    public void setResponsableGnUsuarioId(GnUsuarios responsableGnUsuarioId) {
        this.responsableGnUsuarioId = responsableGnUsuarioId;
    }

    public GnUsuarios getGestorGnUsuarioId() {
        return gestorGnUsuarioId;
    }

    public void setGestorGnUsuarioId(GnUsuarios gestorGnUsuarioId) {
        this.gestorGnUsuarioId = gestorGnUsuarioId;
    }

    public GnUsuarios getAbogadoGnUsuarioId() {
        return abogadoGnUsuarioId;
    }

    public void setAbogadoGnUsuarioId(GnUsuarios abogadoGnUsuarioId) {
        this.abogadoGnUsuarioId = abogadoGnUsuarioId;
    }

    public GnUsuarios getMedicoGnUsuarioId() {
        return medicoGnUsuarioId;
    }

    public void setMedicoGnUsuarioId(GnUsuarios medicoGnUsuarioId) {
        this.medicoGnUsuarioId = medicoGnUsuarioId;
    }

    public TuJuzgados getTuJuzgadosId() {
        return tuJuzgadosId;
    }

    public void setTuJuzgadosId(TuJuzgados tuJuzgadosId) {
        this.tuJuzgadosId = tuJuzgadosId;
    }

    public TuTutelas getTuTutelasId() {
        return tuTutelasId;
    }

    public void setTuTutelasId(TuTutelas tuTutelasId) {
        this.tuTutelasId = tuTutelasId;
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
        if (!(object instanceof TuTutelaEstados)) {
            return false;
        }
        TuTutelaEstados other = (TuTutelaEstados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuTutelaEstados[ id=" + id + " ]";
    }
    
}
