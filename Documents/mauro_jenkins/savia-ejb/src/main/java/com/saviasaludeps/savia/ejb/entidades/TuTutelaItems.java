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
@Table(name = "tu_tutela_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuTutelaItems.findAll", query = "SELECT t FROM TuTutelaItems t"),
    @NamedQuery(name = "TuTutelaItems.findById", query = "SELECT t FROM TuTutelaItems t WHERE t.id = :id"),
    @NamedQuery(name = "TuTutelaItems.findByDestinoCntPrestadorSedeNombre", query = "SELECT t FROM TuTutelaItems t WHERE t.destinoCntPrestadorSedeNombre = :destinoCntPrestadorSedeNombre"),
    @NamedQuery(name = "TuTutelaItems.findByPrescripcionCntPrestadorSedeNombre", query = "SELECT t FROM TuTutelaItems t WHERE t.prescripcionCntPrestadorSedeNombre = :prescripcionCntPrestadorSedeNombre"),
    @NamedQuery(name = "TuTutelaItems.findByCantidad", query = "SELECT t FROM TuTutelaItems t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TuTutelaItems.findByMaeCausaTutelaId", query = "SELECT t FROM TuTutelaItems t WHERE t.maeCausaTutelaId = :maeCausaTutelaId"),
    @NamedQuery(name = "TuTutelaItems.findByMaeCausaTutelaCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maeCausaTutelaCodigo = :maeCausaTutelaCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaeCausaTutelaValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maeCausaTutelaValor = :maeCausaTutelaValor"),
    @NamedQuery(name = "TuTutelaItems.findByFechaEnvio", query = "SELECT t FROM TuTutelaItems t WHERE t.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "TuTutelaItems.findByFechaCita", query = "SELECT t FROM TuTutelaItems t WHERE t.fechaCita = :fechaCita"),
    @NamedQuery(name = "TuTutelaItems.findByFechaRespuesta", query = "SELECT t FROM TuTutelaItems t WHERE t.fechaRespuesta = :fechaRespuesta"),
    @NamedQuery(name = "TuTutelaItems.findByFechaCumplimiento", query = "SELECT t FROM TuTutelaItems t WHERE t.fechaCumplimiento = :fechaCumplimiento"),
    @NamedQuery(name = "TuTutelaItems.findByCorreoDestinatario", query = "SELECT t FROM TuTutelaItems t WHERE t.correoDestinatario = :correoDestinatario"),
    @NamedQuery(name = "TuTutelaItems.findByTipoServicioId", query = "SELECT t FROM TuTutelaItems t WHERE t.tipoServicioId = :tipoServicioId"),
    @NamedQuery(name = "TuTutelaItems.findByNotificadaAIps", query = "SELECT t FROM TuTutelaItems t WHERE t.notificadaAIps = :notificadaAIps"),
    @NamedQuery(name = "TuTutelaItems.findByRespuestaIps", query = "SELECT t FROM TuTutelaItems t WHERE t.respuestaIps = :respuestaIps"),
    @NamedQuery(name = "TuTutelaItems.findByParametroIpsId", query = "SELECT t FROM TuTutelaItems t WHERE t.parametroIpsId = :parametroIpsId"),
    @NamedQuery(name = "TuTutelaItems.findByTipoServicio", query = "SELECT t FROM TuTutelaItems t WHERE t.tipoServicio = :tipoServicio"),
    @NamedQuery(name = "TuTutelaItems.findByMaTecnologiaId", query = "SELECT t FROM TuTutelaItems t WHERE t.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "TuTutelaItems.findByMaTecnologiaCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaTecnologiaValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "TuTutelaItems.findByMaePresentacionId", query = "SELECT t FROM TuTutelaItems t WHERE t.maePresentacionId = :maePresentacionId"),
    @NamedQuery(name = "TuTutelaItems.findByMaePresentacionCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maePresentacionCodigo = :maePresentacionCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaePresentacionValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maePresentacionValor = :maePresentacionValor"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoPrestacionId", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoPrestacionId = :maeTipoPrestacionId"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoPrestacionCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoPrestacionCodigo = :maeTipoPrestacionCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoPrestacionValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoPrestacionValor = :maeTipoPrestacionValor"),
    @NamedQuery(name = "TuTutelaItems.findBySolicitarFechaCita", query = "SELECT t FROM TuTutelaItems t WHERE t.solicitarFechaCita = :solicitarFechaCita"),
    @NamedQuery(name = "TuTutelaItems.findByAuAutorizacionesId", query = "SELECT t FROM TuTutelaItems t WHERE t.auAutorizacionesId = :auAutorizacionesId"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoServicioId", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoServicioCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoServicioValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "TuTutelaItems.findByAplicaDestino", query = "SELECT t FROM TuTutelaItems t WHERE t.aplicaDestino = :aplicaDestino"),
    @NamedQuery(name = "TuTutelaItems.findByAplicaAsignacion", query = "SELECT t FROM TuTutelaItems t WHERE t.aplicaAsignacion = :aplicaAsignacion"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoAsignacionId", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoAsignacionId = :maeTipoAsignacionId"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoAsignacionCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoAsignacionCodigo = :maeTipoAsignacionCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaeTipoAsignacionValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maeTipoAsignacionValor = :maeTipoAsignacionValor"),
    @NamedQuery(name = "TuTutelaItems.findByMaeEstadoItemId", query = "SELECT t FROM TuTutelaItems t WHERE t.maeEstadoItemId = :maeEstadoItemId"),
    @NamedQuery(name = "TuTutelaItems.findByMaeEstadoItemCodigo", query = "SELECT t FROM TuTutelaItems t WHERE t.maeEstadoItemCodigo = :maeEstadoItemCodigo"),
    @NamedQuery(name = "TuTutelaItems.findByMaeEstadoItemValor", query = "SELECT t FROM TuTutelaItems t WHERE t.maeEstadoItemValor = :maeEstadoItemValor"),
    @NamedQuery(name = "TuTutelaItems.findByUsuarioCrea", query = "SELECT t FROM TuTutelaItems t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuTutelaItems.findByTerminalCrea", query = "SELECT t FROM TuTutelaItems t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuTutelaItems.findByFechaHoraCrea", query = "SELECT t FROM TuTutelaItems t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuTutelaItems.findByUsuarioModifica", query = "SELECT t FROM TuTutelaItems t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuTutelaItems.findByTerminalModifica", query = "SELECT t FROM TuTutelaItems t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuTutelaItems.findByFechaHoraModifica", query = "SELECT t FROM TuTutelaItems t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuTutelaItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "destino_cnt_prestador_sede_nombre")
    private String destinoCntPrestadorSedeNombre;
    @Size(max = 256)
    @Column(name = "prescripcion_cnt_prestador_sede_nombre")
    private String prescripcionCntPrestadorSedeNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "mae_causa_tutela_id")
    private Integer maeCausaTutelaId;
    @Size(max = 8)
    @Column(name = "mae_causa_tutela_codigo")
    private String maeCausaTutelaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_tutela_valor")
    private String maeCausaTutelaValor;
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Column(name = "fecha_cita")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;
    @Column(name = "fecha_respuesta")
    @Temporal(TemporalType.DATE)
    private Date fechaRespuesta;
    @Column(name = "fecha_cumplimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaCumplimiento;
    @Size(max = 128)
    @Column(name = "correo_destinatario")
    private String correoDestinatario;
    @Column(name = "tipo_servicio_id")
    private Integer tipoServicioId;
    @Column(name = "notificada_a_ips")
    private Boolean notificadaAIps;
    @Column(name = "respuesta_ips")
    private Boolean respuestaIps;
    @Column(name = "parametro_ips_id")
    private Integer parametroIpsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_servicio")
    private int tipoServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "mae_presentacion_id")
    private Integer maePresentacionId;
    @Size(max = 8)
    @Column(name = "mae_presentacion_codigo")
    private String maePresentacionCodigo;
    @Size(max = 64)
    @Column(name = "mae_presentacion_valor")
    private String maePresentacionValor;
    @Column(name = "mae_tipo_prestacion_id")
    private Integer maeTipoPrestacionId;
    @Size(max = 8)
    @Column(name = "mae_tipo_prestacion_codigo")
    private String maeTipoPrestacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_prestacion_valor")
    private String maeTipoPrestacionValor;
    @Lob
    @Size(max = 16777215)
    @Column(name = "observacion")
    private String observacion;
    @Lob
    @Size(max = 16777215)
    @Column(name = "observacion_ips")
    private String observacionIps;
    @Column(name = "solicitar_fecha_cita")
    private Boolean solicitarFechaCita;
    @Column(name = "au_autorizaciones_id")
    private Integer auAutorizacionesId;
    @Column(name = "mae_tipo_servicio_id")
    private Integer maeTipoServicioId;
    @Size(max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_destino")
    private boolean aplicaDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_asignacion")
    private boolean aplicaAsignacion;
    @Column(name = "mae_tipo_asignacion_id")
    private Integer maeTipoAsignacionId;
    @Size(max = 8)
    @Column(name = "mae_tipo_asignacion_codigo")
    private String maeTipoAsignacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_asignacion_valor")
    private String maeTipoAsignacionValor;
    @Column(name = "mae_estado_item_id")
    private Integer maeEstadoItemId;
    @Size(max = 8)
    @Column(name = "mae_estado_item_codigo")
    private String maeEstadoItemCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_item_valor")
    private String maeEstadoItemValor;
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
    @JoinColumn(name = "destino_cnt_prestador_sede_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes destinoCntPrestadorSedeId;
    @JoinColumn(name = "prescripcion_cnt_prestador_sede_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes prescripcionCntPrestadorSedeId;
    @JoinColumn(name = "gn_usuario_asignado_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuarioAsignadoId;
    @JoinColumn(name = "tu_personas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuPersonas tuPersonasId;
    @JoinColumn(name = "tu_tutela_estados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelaEstados tuTutelaEstadosId;
    @JoinColumn(name = "tu_tutelas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelas tuTutelasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelaItemId", fetch = FetchType.LAZY)
    private List<TuTutelaItemGestiones> tuTutelaItemGestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelaItemsId", fetch = FetchType.LAZY)
    private List<TuTutelaDiagnosticos> tuTutelaDiagnosticosList;
    @OneToMany(mappedBy = "tuTutelaItemsId", fetch = FetchType.LAZY)
    private List<TuAdjuntos> tuAdjuntosList;

    public TuTutelaItems() {
    }

    public TuTutelaItems(Integer id) {
        this.id = id;
    }

    public TuTutelaItems(Integer id, int cantidad, int tipoServicio, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, boolean aplicaDestino, boolean aplicaAsignacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.cantidad = cantidad;
        this.tipoServicio = tipoServicio;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.aplicaDestino = aplicaDestino;
        this.aplicaAsignacion = aplicaAsignacion;
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

    public String getDestinoCntPrestadorSedeNombre() {
        return destinoCntPrestadorSedeNombre;
    }

    public void setDestinoCntPrestadorSedeNombre(String destinoCntPrestadorSedeNombre) {
        this.destinoCntPrestadorSedeNombre = destinoCntPrestadorSedeNombre;
    }

    public String getPrescripcionCntPrestadorSedeNombre() {
        return prescripcionCntPrestadorSedeNombre;
    }

    public void setPrescripcionCntPrestadorSedeNombre(String prescripcionCntPrestadorSedeNombre) {
        this.prescripcionCntPrestadorSedeNombre = prescripcionCntPrestadorSedeNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getMaeCausaTutelaId() {
        return maeCausaTutelaId;
    }

    public void setMaeCausaTutelaId(Integer maeCausaTutelaId) {
        this.maeCausaTutelaId = maeCausaTutelaId;
    }

    public String getMaeCausaTutelaCodigo() {
        return maeCausaTutelaCodigo;
    }

    public void setMaeCausaTutelaCodigo(String maeCausaTutelaCodigo) {
        this.maeCausaTutelaCodigo = maeCausaTutelaCodigo;
    }

    public String getMaeCausaTutelaValor() {
        return maeCausaTutelaValor;
    }

    public void setMaeCausaTutelaValor(String maeCausaTutelaValor) {
        this.maeCausaTutelaValor = maeCausaTutelaValor;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Date getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(Date fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }

    public Integer getTipoServicioId() {
        return tipoServicioId;
    }

    public void setTipoServicioId(Integer tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }

    public Boolean getNotificadaAIps() {
        return notificadaAIps;
    }

    public void setNotificadaAIps(Boolean notificadaAIps) {
        this.notificadaAIps = notificadaAIps;
    }

    public Boolean getRespuestaIps() {
        return respuestaIps;
    }

    public void setRespuestaIps(Boolean respuestaIps) {
        this.respuestaIps = respuestaIps;
    }

    public Integer getParametroIpsId() {
        return parametroIpsId;
    }

    public void setParametroIpsId(Integer parametroIpsId) {
        this.parametroIpsId = parametroIpsId;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
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

    public Integer getMaePresentacionId() {
        return maePresentacionId;
    }

    public void setMaePresentacionId(Integer maePresentacionId) {
        this.maePresentacionId = maePresentacionId;
    }

    public String getMaePresentacionCodigo() {
        return maePresentacionCodigo;
    }

    public void setMaePresentacionCodigo(String maePresentacionCodigo) {
        this.maePresentacionCodigo = maePresentacionCodigo;
    }

    public String getMaePresentacionValor() {
        return maePresentacionValor;
    }

    public void setMaePresentacionValor(String maePresentacionValor) {
        this.maePresentacionValor = maePresentacionValor;
    }

    public Integer getMaeTipoPrestacionId() {
        return maeTipoPrestacionId;
    }

    public void setMaeTipoPrestacionId(Integer maeTipoPrestacionId) {
        this.maeTipoPrestacionId = maeTipoPrestacionId;
    }

    public String getMaeTipoPrestacionCodigo() {
        return maeTipoPrestacionCodigo;
    }

    public void setMaeTipoPrestacionCodigo(String maeTipoPrestacionCodigo) {
        this.maeTipoPrestacionCodigo = maeTipoPrestacionCodigo;
    }

    public String getMaeTipoPrestacionValor() {
        return maeTipoPrestacionValor;
    }

    public void setMaeTipoPrestacionValor(String maeTipoPrestacionValor) {
        this.maeTipoPrestacionValor = maeTipoPrestacionValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionIps() {
        return observacionIps;
    }

    public void setObservacionIps(String observacionIps) {
        this.observacionIps = observacionIps;
    }

    public Boolean getSolicitarFechaCita() {
        return solicitarFechaCita;
    }

    public void setSolicitarFechaCita(Boolean solicitarFechaCita) {
        this.solicitarFechaCita = solicitarFechaCita;
    }

    public Integer getAuAutorizacionesId() {
        return auAutorizacionesId;
    }

    public void setAuAutorizacionesId(Integer auAutorizacionesId) {
        this.auAutorizacionesId = auAutorizacionesId;
    }

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    public boolean getAplicaDestino() {
        return aplicaDestino;
    }

    public void setAplicaDestino(boolean aplicaDestino) {
        this.aplicaDestino = aplicaDestino;
    }

    public boolean getAplicaAsignacion() {
        return aplicaAsignacion;
    }

    public void setAplicaAsignacion(boolean aplicaAsignacion) {
        this.aplicaAsignacion = aplicaAsignacion;
    }

    public Integer getMaeTipoAsignacionId() {
        return maeTipoAsignacionId;
    }

    public void setMaeTipoAsignacionId(Integer maeTipoAsignacionId) {
        this.maeTipoAsignacionId = maeTipoAsignacionId;
    }

    public String getMaeTipoAsignacionCodigo() {
        return maeTipoAsignacionCodigo;
    }

    public void setMaeTipoAsignacionCodigo(String maeTipoAsignacionCodigo) {
        this.maeTipoAsignacionCodigo = maeTipoAsignacionCodigo;
    }

    public String getMaeTipoAsignacionValor() {
        return maeTipoAsignacionValor;
    }

    public void setMaeTipoAsignacionValor(String maeTipoAsignacionValor) {
        this.maeTipoAsignacionValor = maeTipoAsignacionValor;
    }

    public Integer getMaeEstadoItemId() {
        return maeEstadoItemId;
    }

    public void setMaeEstadoItemId(Integer maeEstadoItemId) {
        this.maeEstadoItemId = maeEstadoItemId;
    }

    public String getMaeEstadoItemCodigo() {
        return maeEstadoItemCodigo;
    }

    public void setMaeEstadoItemCodigo(String maeEstadoItemCodigo) {
        this.maeEstadoItemCodigo = maeEstadoItemCodigo;
    }

    public String getMaeEstadoItemValor() {
        return maeEstadoItemValor;
    }

    public void setMaeEstadoItemValor(String maeEstadoItemValor) {
        this.maeEstadoItemValor = maeEstadoItemValor;
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

    public CntPrestadorSedes getDestinoCntPrestadorSedeId() {
        return destinoCntPrestadorSedeId;
    }

    public void setDestinoCntPrestadorSedeId(CntPrestadorSedes destinoCntPrestadorSedeId) {
        this.destinoCntPrestadorSedeId = destinoCntPrestadorSedeId;
    }

    public CntPrestadorSedes getPrescripcionCntPrestadorSedeId() {
        return prescripcionCntPrestadorSedeId;
    }

    public void setPrescripcionCntPrestadorSedeId(CntPrestadorSedes prescripcionCntPrestadorSedeId) {
        this.prescripcionCntPrestadorSedeId = prescripcionCntPrestadorSedeId;
    }

    public GnUsuarios getGnUsuarioAsignadoId() {
        return gnUsuarioAsignadoId;
    }

    public void setGnUsuarioAsignadoId(GnUsuarios gnUsuarioAsignadoId) {
        this.gnUsuarioAsignadoId = gnUsuarioAsignadoId;
    }

    public TuPersonas getTuPersonasId() {
        return tuPersonasId;
    }

    public void setTuPersonasId(TuPersonas tuPersonasId) {
        this.tuPersonasId = tuPersonasId;
    }

    public TuTutelaEstados getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstados tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    public TuTutelas getTuTutelasId() {
        return tuTutelasId;
    }

    public void setTuTutelasId(TuTutelas tuTutelasId) {
        this.tuTutelasId = tuTutelasId;
    }

    @XmlTransient
    public List<TuTutelaItemGestiones> getTuTutelaItemGestionesList() {
        return tuTutelaItemGestionesList;
    }

    public void setTuTutelaItemGestionesList(List<TuTutelaItemGestiones> tuTutelaItemGestionesList) {
        this.tuTutelaItemGestionesList = tuTutelaItemGestionesList;
    }

    @XmlTransient
    public List<TuTutelaDiagnosticos> getTuTutelaDiagnosticosList() {
        return tuTutelaDiagnosticosList;
    }

    public void setTuTutelaDiagnosticosList(List<TuTutelaDiagnosticos> tuTutelaDiagnosticosList) {
        this.tuTutelaDiagnosticosList = tuTutelaDiagnosticosList;
    }

    @XmlTransient
    public List<TuAdjuntos> getTuAdjuntosList() {
        return tuAdjuntosList;
    }

    public void setTuAdjuntosList(List<TuAdjuntos> tuAdjuntosList) {
        this.tuAdjuntosList = tuAdjuntosList;
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
        if (!(object instanceof TuTutelaItems)) {
            return false;
        }
        TuTutelaItems other = (TuTutelaItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuTutelaItems[ id=" + id + " ]";
    }
    
}
