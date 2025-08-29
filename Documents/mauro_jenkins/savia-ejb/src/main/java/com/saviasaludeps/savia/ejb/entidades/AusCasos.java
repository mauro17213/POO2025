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
@Table(name = "aus_casos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusCasos.findAll", query = "SELECT a FROM AusCasos a"),
    @NamedQuery(name = "AusCasos.findById", query = "SELECT a FROM AusCasos a WHERE a.id = :id"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudEstadoId", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudEstadoId = :maeSolicitudEstadoId"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudEstadoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudEstadoCodigo = :maeSolicitudEstadoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudEstadoValor", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudEstadoValor = :maeSolicitudEstadoValor"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudTipoId", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudTipoId = :maeSolicitudTipoId"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudTipoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudTipoCodigo = :maeSolicitudTipoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudTipoValor", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudTipoValor = :maeSolicitudTipoValor"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudOrigenId", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudOrigenId = :maeSolicitudOrigenId"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudOrigenCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudOrigenCodigo = :maeSolicitudOrigenCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudOrigenValor", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudOrigenValor = :maeSolicitudOrigenValor"),
    @NamedQuery(name = "AusCasos.findByMaeCanalSupersaludId", query = "SELECT a FROM AusCasos a WHERE a.maeCanalSupersaludId = :maeCanalSupersaludId"),
    @NamedQuery(name = "AusCasos.findByMaeCanalSupersaludCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeCanalSupersaludCodigo = :maeCanalSupersaludCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeCanalSupersaludValor", query = "SELECT a FROM AusCasos a WHERE a.maeCanalSupersaludValor = :maeCanalSupersaludValor"),
    @NamedQuery(name = "AusCasos.findByRadicado", query = "SELECT a FROM AusCasos a WHERE a.radicado = :radicado"),
    @NamedQuery(name = "AusCasos.findByPertinencia", query = "SELECT a FROM AusCasos a WHERE a.pertinencia = :pertinencia"),
    @NamedQuery(name = "AusCasos.findByMultireparto", query = "SELECT a FROM AusCasos a WHERE a.multireparto = :multireparto"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudPrioridadId", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudPrioridadId = :maeSolicitudPrioridadId"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudPrioridadCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudPrioridadCodigo = :maeSolicitudPrioridadCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudPrioridadValor", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudPrioridadValor = :maeSolicitudPrioridadValor"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudEnteControlId", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudEnteControlId = :maeSolicitudEnteControlId"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudEnteControlCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudEnteControlCodigo = :maeSolicitudEnteControlCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudEnteControValor", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudEnteControValor = :maeSolicitudEnteControValor"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudRiesgoVidalId", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudRiesgoVidalId = :maeSolicitudRiesgoVidalId"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudRiesgoVidalCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudRiesgoVidalCodigo = :maeSolicitudRiesgoVidalCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSolicitudRiesgoVidalValor", query = "SELECT a FROM AusCasos a WHERE a.maeSolicitudRiesgoVidalValor = :maeSolicitudRiesgoVidalValor"),
    @NamedQuery(name = "AusCasos.findByFechaNotificacion", query = "SELECT a FROM AusCasos a WHERE a.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "AusCasos.findByFechaCiere", query = "SELECT a FROM AusCasos a WHERE a.fechaCiere = :fechaCiere"),
    @NamedQuery(name = "AusCasos.findByAfiliadoEdad", query = "SELECT a FROM AusCasos a WHERE a.afiliadoEdad = :afiliadoEdad"),
    @NamedQuery(name = "AusCasos.findByAccionanteEdad", query = "SELECT a FROM AusCasos a WHERE a.accionanteEdad = :accionanteEdad"),
    @NamedQuery(name = "AusCasos.findByParentesco", query = "SELECT a FROM AusCasos a WHERE a.parentesco = :parentesco"),
    @NamedQuery(name = "AusCasos.findByFechaVencimiento", query = "SELECT a FROM AusCasos a WHERE a.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "AusCasos.findByFechaHoraResponsable", query = "SELECT a FROM AusCasos a WHERE a.fechaHoraResponsable = :fechaHoraResponsable"),
    @NamedQuery(name = "AusCasos.findByMultiusuario", query = "SELECT a FROM AusCasos a WHERE a.multiusuario = :multiusuario"),
    @NamedQuery(name = "AusCasos.findByInstruccion", query = "SELECT a FROM AusCasos a WHERE a.instruccion = :instruccion"),
    @NamedQuery(name = "AusCasos.findByReabierto", query = "SELECT a FROM AusCasos a WHERE a.reabierto = :reabierto"),
    @NamedQuery(name = "AusCasos.findByFalloTutela", query = "SELECT a FROM AusCasos a WHERE a.falloTutela = :falloTutela"),
    @NamedQuery(name = "AusCasos.findByRedireccionado", query = "SELECT a FROM AusCasos a WHERE a.redireccionado = :redireccionado"),
    @NamedQuery(name = "AusCasos.findByModalidadEntrega", query = "SELECT a FROM AusCasos a WHERE a.modalidadEntrega = :modalidadEntrega"),
    @NamedQuery(name = "AusCasos.findByMaeZonaId", query = "SELECT a FROM AusCasos a WHERE a.maeZonaId = :maeZonaId"),
    @NamedQuery(name = "AusCasos.findByMaeZonaCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeZonaCodigo = :maeZonaCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeZonaValor", query = "SELECT a FROM AusCasos a WHERE a.maeZonaValor = :maeZonaValor"),
    @NamedQuery(name = "AusCasos.findByDireccionResidencia", query = "SELECT a FROM AusCasos a WHERE a.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "AusCasos.findByBarrio", query = "SELECT a FROM AusCasos a WHERE a.barrio = :barrio"),
    @NamedQuery(name = "AusCasos.findByObservacionBorra", query = "SELECT a FROM AusCasos a WHERE a.observacionBorra = :observacionBorra"),
    @NamedQuery(name = "AusCasos.findByBorrado", query = "SELECT a FROM AusCasos a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AusCasos.findByCantidadServicios", query = "SELECT a FROM AusCasos a WHERE a.cantidadServicios = :cantidadServicios"),
    @NamedQuery(name = "AusCasos.findByCantidadServiciosCerrados", query = "SELECT a FROM AusCasos a WHERE a.cantidadServiciosCerrados = :cantidadServiciosCerrados"),
    @NamedQuery(name = "AusCasos.findByMaeMotivoReabreId", query = "SELECT a FROM AusCasos a WHERE a.maeMotivoReabreId = :maeMotivoReabreId"),
    @NamedQuery(name = "AusCasos.findByMaeMotivoReabreCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeMotivoReabreCodigo = :maeMotivoReabreCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeMotivoReabreValor", query = "SELECT a FROM AusCasos a WHERE a.maeMotivoReabreValor = :maeMotivoReabreValor"),
    @NamedQuery(name = "AusCasos.findByObservacionReabre", query = "SELECT a FROM AusCasos a WHERE a.observacionReabre = :observacionReabre"),
    @NamedQuery(name = "AusCasos.findByMaeTecnologiaAltoCostoId", query = "SELECT a FROM AusCasos a WHERE a.maeTecnologiaAltoCostoId = :maeTecnologiaAltoCostoId"),
    @NamedQuery(name = "AusCasos.findByMaeTecnologiaAltoCostoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeTecnologiaAltoCostoCodigo = :maeTecnologiaAltoCostoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeTecnologiaAltoCostoValor", query = "SELECT a FROM AusCasos a WHERE a.maeTecnologiaAltoCostoValor = :maeTecnologiaAltoCostoValor"),
    @NamedQuery(name = "AusCasos.findByMaeMotivoEspecificoId", query = "SELECT a FROM AusCasos a WHERE a.maeMotivoEspecificoId = :maeMotivoEspecificoId"),
    @NamedQuery(name = "AusCasos.findByMaeMotivoEspecificoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeMotivoEspecificoCodigo = :maeMotivoEspecificoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeMotivoEspecificoValor", query = "SELECT a FROM AusCasos a WHERE a.maeMotivoEspecificoValor = :maeMotivoEspecificoValor"),
    @NamedQuery(name = "AusCasos.findByMaeTipoMotivoEspecificoId", query = "SELECT a FROM AusCasos a WHERE a.maeTipoMotivoEspecificoId = :maeTipoMotivoEspecificoId"),
    @NamedQuery(name = "AusCasos.findByMaeTipoMotivoEspecificoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeTipoMotivoEspecificoCodigo = :maeTipoMotivoEspecificoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeTipoMotivoEspecificoValor", query = "SELECT a FROM AusCasos a WHERE a.maeTipoMotivoEspecificoValor = :maeTipoMotivoEspecificoValor"),
    @NamedQuery(name = "AusCasos.findByMaeSubtipoMotivoEspecificoId", query = "SELECT a FROM AusCasos a WHERE a.maeSubtipoMotivoEspecificoId = :maeSubtipoMotivoEspecificoId"),
    @NamedQuery(name = "AusCasos.findByMaeSubtipoMotivoEspecificoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeSubtipoMotivoEspecificoCodigo = :maeSubtipoMotivoEspecificoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeSubtipoMotivoEspecificoValor", query = "SELECT a FROM AusCasos a WHERE a.maeSubtipoMotivoEspecificoValor = :maeSubtipoMotivoEspecificoValor"),
    @NamedQuery(name = "AusCasos.findByCerrado", query = "SELECT a FROM AusCasos a WHERE a.cerrado = :cerrado"),
    @NamedQuery(name = "AusCasos.findByMaeCasoCerradoId", query = "SELECT a FROM AusCasos a WHERE a.maeCasoCerradoId = :maeCasoCerradoId"),
    @NamedQuery(name = "AusCasos.findByMaeCasoCerradoCodigo", query = "SELECT a FROM AusCasos a WHERE a.maeCasoCerradoCodigo = :maeCasoCerradoCodigo"),
    @NamedQuery(name = "AusCasos.findByMaeCasoCerradoValor", query = "SELECT a FROM AusCasos a WHERE a.maeCasoCerradoValor = :maeCasoCerradoValor"),
    @NamedQuery(name = "AusCasos.findByProteccionDatos", query = "SELECT a FROM AusCasos a WHERE a.proteccionDatos = :proteccionDatos"),
    @NamedQuery(name = "AusCasos.findByUsuarioPluripatologico", query = "SELECT a FROM AusCasos a WHERE a.usuarioPluripatologico = :usuarioPluripatologico"),
    @NamedQuery(name = "AusCasos.findByOrigenCierre", query = "SELECT a FROM AusCasos a WHERE a.origenCierre = :origenCierre"),
    @NamedQuery(name = "AusCasos.findByFechaCreacionCaso", query = "SELECT a FROM AusCasos a WHERE a.fechaCreacionCaso = :fechaCreacionCaso"),
    @NamedQuery(name = "AusCasos.findByUsuarioCrea", query = "SELECT a FROM AusCasos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusCasos.findByTerminalCrea", query = "SELECT a FROM AusCasos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusCasos.findByFechaHoraCrea", query = "SELECT a FROM AusCasos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusCasos.findByUsuarioModifica", query = "SELECT a FROM AusCasos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusCasos.findByTerminalModifica", query = "SELECT a FROM AusCasos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusCasos.findByFechaHoraModifica", query = "SELECT a FROM AusCasos a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AusCasos.findByUsuarioBorra", query = "SELECT a FROM AusCasos a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AusCasos.findByTerminalBorra", query = "SELECT a FROM AusCasos a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AusCasos.findByFechaHoraBorra", query = "SELECT a FROM AusCasos a WHERE a.fechaHoraBorra = :fechaHoraBorra"),
    @NamedQuery(name = "AusCasos.findByUsuarioReabre", query = "SELECT a FROM AusCasos a WHERE a.usuarioReabre = :usuarioReabre"),
    @NamedQuery(name = "AusCasos.findByTerminalReabre", query = "SELECT a FROM AusCasos a WHERE a.terminalReabre = :terminalReabre"),
    @NamedQuery(name = "AusCasos.findByFechaHoraReabre", query = "SELECT a FROM AusCasos a WHERE a.fechaHoraReabre = :fechaHoraReabre"),
    @NamedQuery(name = "AusCasos.findByUsuarioCierra", query = "SELECT a FROM AusCasos a WHERE a.usuarioCierra = :usuarioCierra"),
    @NamedQuery(name = "AusCasos.findByTerminalCierra", query = "SELECT a FROM AusCasos a WHERE a.terminalCierra = :terminalCierra"),
    @NamedQuery(name = "AusCasos.findByFechaHoraCierra", query = "SELECT a FROM AusCasos a WHERE a.fechaHoraCierra = :fechaHoraCierra")})
public class AusCasos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_solicitud_estado_id")
    private Integer maeSolicitudEstadoId;
    @Size(max = 8)
    @Column(name = "mae_solicitud_estado_codigo")
    private String maeSolicitudEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_solicitud_estado_valor")
    private String maeSolicitudEstadoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_solicitud_tipo_id")
    private int maeSolicitudTipoId;
    @Size(max = 8)
    @Column(name = "mae_solicitud_tipo_codigo")
    private String maeSolicitudTipoCodigo;
    @Size(max = 128)
    @Column(name = "mae_solicitud_tipo_valor")
    private String maeSolicitudTipoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_solicitud_origen_id")
    private int maeSolicitudOrigenId;
    @Size(max = 8)
    @Column(name = "mae_solicitud_origen_codigo")
    private String maeSolicitudOrigenCodigo;
    @Size(max = 128)
    @Column(name = "mae_solicitud_origen_valor")
    private String maeSolicitudOrigenValor;
    @Column(name = "mae_canal_supersalud_id")
    private Integer maeCanalSupersaludId;
    @Size(max = 8)
    @Column(name = "mae_canal_supersalud_codigo")
    private String maeCanalSupersaludCodigo;
    @Size(max = 128)
    @Column(name = "mae_canal_supersalud_valor")
    private String maeCanalSupersaludValor;
    @Size(max = 32)
    @Column(name = "radicado")
    private String radicado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pertinencia")
    private boolean pertinencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "multireparto")
    private boolean multireparto;
    @Column(name = "mae_solicitud_prioridad_id")
    private Integer maeSolicitudPrioridadId;
    @Size(max = 8)
    @Column(name = "mae_solicitud_prioridad_codigo")
    private String maeSolicitudPrioridadCodigo;
    @Size(max = 128)
    @Column(name = "mae_solicitud_prioridad_valor")
    private String maeSolicitudPrioridadValor;
    @Column(name = "mae_solicitud_ente_control_id")
    private Integer maeSolicitudEnteControlId;
    @Size(max = 8)
    @Column(name = "mae_solicitud_ente_control_codigo")
    private String maeSolicitudEnteControlCodigo;
    @Size(max = 128)
    @Column(name = "mae_solicitud_ente_contro_valor")
    private String maeSolicitudEnteControValor;
    @Column(name = "mae_solicitud_riesgo_vidal_id")
    private Integer maeSolicitudRiesgoVidalId;
    @Size(max = 8)
    @Column(name = "mae_solicitud_riesgo_vidal_codigo")
    private String maeSolicitudRiesgoVidalCodigo;
    @Size(max = 128)
    @Column(name = "mae_solicitud_riesgo_vidal_valor")
    private String maeSolicitudRiesgoVidalValor;
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Column(name = "fecha_ciere")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCiere;
    @Column(name = "afiliado_edad")
    private Integer afiliadoEdad;
    @Column(name = "accionante_edad")
    private Integer accionanteEdad;
    @Size(max = 32)
    @Column(name = "parentesco")
    private String parentesco;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "fecha_hora_responsable")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraResponsable;
    @Size(max = 32)
    @Column(name = "multiusuario")
    private String multiusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "instruccion")
    private boolean instruccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reabierto")
    private boolean reabierto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fallo_tutela")
    private boolean falloTutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "redireccionado")
    private boolean redireccionado;
    @Column(name = "modalidad_entrega")
    private Integer modalidadEntrega;
    @Column(name = "mae_zona_id")
    private Integer maeZonaId;
    @Size(max = 8)
    @Column(name = "mae_zona_codigo")
    private String maeZonaCodigo;
    @Size(max = 128)
    @Column(name = "mae_zona_valor")
    private String maeZonaValor;
    @Size(max = 512)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Size(max = 64)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 256)
    @Column(name = "observacion_borra")
    private String observacionBorra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Column(name = "cantidad_servicios")
    private Integer cantidadServicios;
    @Column(name = "cantidad_servicios_cerrados")
    private Integer cantidadServiciosCerrados;
    @Column(name = "mae_motivo_reabre_id")
    private Integer maeMotivoReabreId;
    @Size(max = 8)
    @Column(name = "mae_motivo_reabre_codigo")
    private String maeMotivoReabreCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_reabre_valor")
    private String maeMotivoReabreValor;
    @Size(max = 256)
    @Column(name = "observacion_reabre")
    private String observacionReabre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tecnologia_alto_costo_id")
    private int maeTecnologiaAltoCostoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tecnologia_alto_costo_codigo")
    private String maeTecnologiaAltoCostoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "mae_tecnologia_alto_costo_valor")
    private String maeTecnologiaAltoCostoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_especifico_id")
    private int maeMotivoEspecificoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_motivo_especifico_codigo")
    private String maeMotivoEspecificoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "mae_motivo_especifico_valor")
    private String maeMotivoEspecificoValor;
    @Column(name = "mae_tipo_motivo_especifico_id")
    private Integer maeTipoMotivoEspecificoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_motivo_especifico_codigo")
    private String maeTipoMotivoEspecificoCodigo;
    @Size(max = 256)
    @Column(name = "mae_tipo_motivo_especifico_valor")
    private String maeTipoMotivoEspecificoValor;
    @Column(name = "mae_subtipo_motivo_especifico_id")
    private Integer maeSubtipoMotivoEspecificoId;
    @Size(max = 16)
    @Column(name = "mae_subtipo_motivo_especifico_codigo")
    private String maeSubtipoMotivoEspecificoCodigo;
    @Size(max = 256)
    @Column(name = "mae_subtipo_motivo_especifico_valor")
    private String maeSubtipoMotivoEspecificoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cerrado")
    private boolean cerrado;
    @Column(name = "mae_caso_cerrado_id")
    private Integer maeCasoCerradoId;
    @Size(max = 8)
    @Column(name = "mae_caso_cerrado_codigo")
    private String maeCasoCerradoCodigo;
    @Size(max = 256)
    @Column(name = "mae_caso_cerrado_valor")
    private String maeCasoCerradoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proteccion_datos")
    private boolean proteccionDatos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_pluripatologico")
    private boolean usuarioPluripatologico;
    @Column(name = "origen_cierre")
    private Integer origenCierre;
    @Column(name = "fecha_creacion_caso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacionCaso;
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
    @Size(max = 128)
    @Column(name = "usuario_reabre")
    private String usuarioReabre;
    @Size(max = 16)
    @Column(name = "terminal_reabre")
    private String terminalReabre;
    @Column(name = "fecha_hora_reabre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraReabre;
    @Size(max = 128)
    @Column(name = "usuario_cierra")
    private String usuarioCierra;
    @Size(max = 16)
    @Column(name = "terminal_cierra")
    private String terminalCierra;
    @Column(name = "fecha_hora_cierra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCierra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausCasosId", fetch = FetchType.LAZY)
    private List<AusCasoServicios> ausCasoServiciosList;
    @JoinColumn(name = "aus_carga_masivas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusCargaMasivas ausCargaMasivasId;
    @JoinColumn(name = "aus_personas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusPersonas ausPersonasId;
    @JoinColumn(name = "aus_accionante_personas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusPersonas ausAccionantePersonasId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnSedes gnSedesId;
    @JoinColumn(name = "gn_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesId;
    @JoinColumn(name = "gn_usuario_cierre_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuarioCierreId;
    @JoinColumn(name = "gn_usuarios_responsable_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosResponsableId;
    @OneToMany(mappedBy = "ausCasosId", fetch = FetchType.LAZY)
    private List<AusSolicitudes> ausSolicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausCasosId", fetch = FetchType.LAZY)
    private List<AusSeguimientos> ausSeguimientosList;
    @OneToMany(mappedBy = "ausCasosId", fetch = FetchType.LAZY)
    private List<AusAdjuntos> ausAdjuntosList;

    public AusCasos() {
    }

    public AusCasos(Integer id) {
        this.id = id;
    }

    public AusCasos(Integer id, int maeSolicitudTipoId, int maeSolicitudOrigenId, boolean pertinencia, boolean multireparto, boolean instruccion, boolean reabierto, boolean falloTutela, boolean redireccionado, boolean borrado, int maeTecnologiaAltoCostoId, String maeTecnologiaAltoCostoCodigo, String maeTecnologiaAltoCostoValor, int maeMotivoEspecificoId, String maeMotivoEspecificoCodigo, String maeMotivoEspecificoValor, boolean cerrado, boolean proteccionDatos, boolean usuarioPluripatologico, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeSolicitudTipoId = maeSolicitudTipoId;
        this.maeSolicitudOrigenId = maeSolicitudOrigenId;
        this.pertinencia = pertinencia;
        this.multireparto = multireparto;
        this.instruccion = instruccion;
        this.reabierto = reabierto;
        this.falloTutela = falloTutela;
        this.redireccionado = redireccionado;
        this.borrado = borrado;
        this.maeTecnologiaAltoCostoId = maeTecnologiaAltoCostoId;
        this.maeTecnologiaAltoCostoCodigo = maeTecnologiaAltoCostoCodigo;
        this.maeTecnologiaAltoCostoValor = maeTecnologiaAltoCostoValor;
        this.maeMotivoEspecificoId = maeMotivoEspecificoId;
        this.maeMotivoEspecificoCodigo = maeMotivoEspecificoCodigo;
        this.maeMotivoEspecificoValor = maeMotivoEspecificoValor;
        this.cerrado = cerrado;
        this.proteccionDatos = proteccionDatos;
        this.usuarioPluripatologico = usuarioPluripatologico;
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

    public Integer getMaeSolicitudEstadoId() {
        return maeSolicitudEstadoId;
    }

    public void setMaeSolicitudEstadoId(Integer maeSolicitudEstadoId) {
        this.maeSolicitudEstadoId = maeSolicitudEstadoId;
    }

    public String getMaeSolicitudEstadoCodigo() {
        return maeSolicitudEstadoCodigo;
    }

    public void setMaeSolicitudEstadoCodigo(String maeSolicitudEstadoCodigo) {
        this.maeSolicitudEstadoCodigo = maeSolicitudEstadoCodigo;
    }

    public String getMaeSolicitudEstadoValor() {
        return maeSolicitudEstadoValor;
    }

    public void setMaeSolicitudEstadoValor(String maeSolicitudEstadoValor) {
        this.maeSolicitudEstadoValor = maeSolicitudEstadoValor;
    }

    public int getMaeSolicitudTipoId() {
        return maeSolicitudTipoId;
    }

    public void setMaeSolicitudTipoId(int maeSolicitudTipoId) {
        this.maeSolicitudTipoId = maeSolicitudTipoId;
    }

    public String getMaeSolicitudTipoCodigo() {
        return maeSolicitudTipoCodigo;
    }

    public void setMaeSolicitudTipoCodigo(String maeSolicitudTipoCodigo) {
        this.maeSolicitudTipoCodigo = maeSolicitudTipoCodigo;
    }

    public String getMaeSolicitudTipoValor() {
        return maeSolicitudTipoValor;
    }

    public void setMaeSolicitudTipoValor(String maeSolicitudTipoValor) {
        this.maeSolicitudTipoValor = maeSolicitudTipoValor;
    }

    public int getMaeSolicitudOrigenId() {
        return maeSolicitudOrigenId;
    }

    public void setMaeSolicitudOrigenId(int maeSolicitudOrigenId) {
        this.maeSolicitudOrigenId = maeSolicitudOrigenId;
    }

    public String getMaeSolicitudOrigenCodigo() {
        return maeSolicitudOrigenCodigo;
    }

    public void setMaeSolicitudOrigenCodigo(String maeSolicitudOrigenCodigo) {
        this.maeSolicitudOrigenCodigo = maeSolicitudOrigenCodigo;
    }

    public String getMaeSolicitudOrigenValor() {
        return maeSolicitudOrigenValor;
    }

    public void setMaeSolicitudOrigenValor(String maeSolicitudOrigenValor) {
        this.maeSolicitudOrigenValor = maeSolicitudOrigenValor;
    }

    public Integer getMaeCanalSupersaludId() {
        return maeCanalSupersaludId;
    }

    public void setMaeCanalSupersaludId(Integer maeCanalSupersaludId) {
        this.maeCanalSupersaludId = maeCanalSupersaludId;
    }

    public String getMaeCanalSupersaludCodigo() {
        return maeCanalSupersaludCodigo;
    }

    public void setMaeCanalSupersaludCodigo(String maeCanalSupersaludCodigo) {
        this.maeCanalSupersaludCodigo = maeCanalSupersaludCodigo;
    }

    public String getMaeCanalSupersaludValor() {
        return maeCanalSupersaludValor;
    }

    public void setMaeCanalSupersaludValor(String maeCanalSupersaludValor) {
        this.maeCanalSupersaludValor = maeCanalSupersaludValor;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public boolean getPertinencia() {
        return pertinencia;
    }

    public void setPertinencia(boolean pertinencia) {
        this.pertinencia = pertinencia;
    }

    public boolean getMultireparto() {
        return multireparto;
    }

    public void setMultireparto(boolean multireparto) {
        this.multireparto = multireparto;
    }

    public Integer getMaeSolicitudPrioridadId() {
        return maeSolicitudPrioridadId;
    }

    public void setMaeSolicitudPrioridadId(Integer maeSolicitudPrioridadId) {
        this.maeSolicitudPrioridadId = maeSolicitudPrioridadId;
    }

    public String getMaeSolicitudPrioridadCodigo() {
        return maeSolicitudPrioridadCodigo;
    }

    public void setMaeSolicitudPrioridadCodigo(String maeSolicitudPrioridadCodigo) {
        this.maeSolicitudPrioridadCodigo = maeSolicitudPrioridadCodigo;
    }

    public String getMaeSolicitudPrioridadValor() {
        return maeSolicitudPrioridadValor;
    }

    public void setMaeSolicitudPrioridadValor(String maeSolicitudPrioridadValor) {
        this.maeSolicitudPrioridadValor = maeSolicitudPrioridadValor;
    }

    public Integer getMaeSolicitudEnteControlId() {
        return maeSolicitudEnteControlId;
    }

    public void setMaeSolicitudEnteControlId(Integer maeSolicitudEnteControlId) {
        this.maeSolicitudEnteControlId = maeSolicitudEnteControlId;
    }

    public String getMaeSolicitudEnteControlCodigo() {
        return maeSolicitudEnteControlCodigo;
    }

    public void setMaeSolicitudEnteControlCodigo(String maeSolicitudEnteControlCodigo) {
        this.maeSolicitudEnteControlCodigo = maeSolicitudEnteControlCodigo;
    }

    public String getMaeSolicitudEnteControValor() {
        return maeSolicitudEnteControValor;
    }

    public void setMaeSolicitudEnteControValor(String maeSolicitudEnteControValor) {
        this.maeSolicitudEnteControValor = maeSolicitudEnteControValor;
    }

    public Integer getMaeSolicitudRiesgoVidalId() {
        return maeSolicitudRiesgoVidalId;
    }

    public void setMaeSolicitudRiesgoVidalId(Integer maeSolicitudRiesgoVidalId) {
        this.maeSolicitudRiesgoVidalId = maeSolicitudRiesgoVidalId;
    }

    public String getMaeSolicitudRiesgoVidalCodigo() {
        return maeSolicitudRiesgoVidalCodigo;
    }

    public void setMaeSolicitudRiesgoVidalCodigo(String maeSolicitudRiesgoVidalCodigo) {
        this.maeSolicitudRiesgoVidalCodigo = maeSolicitudRiesgoVidalCodigo;
    }

    public String getMaeSolicitudRiesgoVidalValor() {
        return maeSolicitudRiesgoVidalValor;
    }

    public void setMaeSolicitudRiesgoVidalValor(String maeSolicitudRiesgoVidalValor) {
        this.maeSolicitudRiesgoVidalValor = maeSolicitudRiesgoVidalValor;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaCiere() {
        return fechaCiere;
    }

    public void setFechaCiere(Date fechaCiere) {
        this.fechaCiere = fechaCiere;
    }

    public Integer getAfiliadoEdad() {
        return afiliadoEdad;
    }

    public void setAfiliadoEdad(Integer afiliadoEdad) {
        this.afiliadoEdad = afiliadoEdad;
    }

    public Integer getAccionanteEdad() {
        return accionanteEdad;
    }

    public void setAccionanteEdad(Integer accionanteEdad) {
        this.accionanteEdad = accionanteEdad;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaHoraResponsable() {
        return fechaHoraResponsable;
    }

    public void setFechaHoraResponsable(Date fechaHoraResponsable) {
        this.fechaHoraResponsable = fechaHoraResponsable;
    }

    public String getMultiusuario() {
        return multiusuario;
    }

    public void setMultiusuario(String multiusuario) {
        this.multiusuario = multiusuario;
    }

    public boolean getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(boolean instruccion) {
        this.instruccion = instruccion;
    }

    public boolean getReabierto() {
        return reabierto;
    }

    public void setReabierto(boolean reabierto) {
        this.reabierto = reabierto;
    }

    public boolean getFalloTutela() {
        return falloTutela;
    }

    public void setFalloTutela(boolean falloTutela) {
        this.falloTutela = falloTutela;
    }

    public boolean getRedireccionado() {
        return redireccionado;
    }

    public void setRedireccionado(boolean redireccionado) {
        this.redireccionado = redireccionado;
    }

    public Integer getModalidadEntrega() {
        return modalidadEntrega;
    }

    public void setModalidadEntrega(Integer modalidadEntrega) {
        this.modalidadEntrega = modalidadEntrega;
    }

    public Integer getMaeZonaId() {
        return maeZonaId;
    }

    public void setMaeZonaId(Integer maeZonaId) {
        this.maeZonaId = maeZonaId;
    }

    public String getMaeZonaCodigo() {
        return maeZonaCodigo;
    }

    public void setMaeZonaCodigo(String maeZonaCodigo) {
        this.maeZonaCodigo = maeZonaCodigo;
    }

    public String getMaeZonaValor() {
        return maeZonaValor;
    }

    public void setMaeZonaValor(String maeZonaValor) {
        this.maeZonaValor = maeZonaValor;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getObservacionBorra() {
        return observacionBorra;
    }

    public void setObservacionBorra(String observacionBorra) {
        this.observacionBorra = observacionBorra;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getCantidadServicios() {
        return cantidadServicios;
    }

    public void setCantidadServicios(Integer cantidadServicios) {
        this.cantidadServicios = cantidadServicios;
    }

    public Integer getCantidadServiciosCerrados() {
        return cantidadServiciosCerrados;
    }

    public void setCantidadServiciosCerrados(Integer cantidadServiciosCerrados) {
        this.cantidadServiciosCerrados = cantidadServiciosCerrados;
    }

    public Integer getMaeMotivoReabreId() {
        return maeMotivoReabreId;
    }

    public void setMaeMotivoReabreId(Integer maeMotivoReabreId) {
        this.maeMotivoReabreId = maeMotivoReabreId;
    }

    public String getMaeMotivoReabreCodigo() {
        return maeMotivoReabreCodigo;
    }

    public void setMaeMotivoReabreCodigo(String maeMotivoReabreCodigo) {
        this.maeMotivoReabreCodigo = maeMotivoReabreCodigo;
    }

    public String getMaeMotivoReabreValor() {
        return maeMotivoReabreValor;
    }

    public void setMaeMotivoReabreValor(String maeMotivoReabreValor) {
        this.maeMotivoReabreValor = maeMotivoReabreValor;
    }

    public String getObservacionReabre() {
        return observacionReabre;
    }

    public void setObservacionReabre(String observacionReabre) {
        this.observacionReabre = observacionReabre;
    }

    public int getMaeTecnologiaAltoCostoId() {
        return maeTecnologiaAltoCostoId;
    }

    public void setMaeTecnologiaAltoCostoId(int maeTecnologiaAltoCostoId) {
        this.maeTecnologiaAltoCostoId = maeTecnologiaAltoCostoId;
    }

    public String getMaeTecnologiaAltoCostoCodigo() {
        return maeTecnologiaAltoCostoCodigo;
    }

    public void setMaeTecnologiaAltoCostoCodigo(String maeTecnologiaAltoCostoCodigo) {
        this.maeTecnologiaAltoCostoCodigo = maeTecnologiaAltoCostoCodigo;
    }

    public String getMaeTecnologiaAltoCostoValor() {
        return maeTecnologiaAltoCostoValor;
    }

    public void setMaeTecnologiaAltoCostoValor(String maeTecnologiaAltoCostoValor) {
        this.maeTecnologiaAltoCostoValor = maeTecnologiaAltoCostoValor;
    }

    public int getMaeMotivoEspecificoId() {
        return maeMotivoEspecificoId;
    }

    public void setMaeMotivoEspecificoId(int maeMotivoEspecificoId) {
        this.maeMotivoEspecificoId = maeMotivoEspecificoId;
    }

    public String getMaeMotivoEspecificoCodigo() {
        return maeMotivoEspecificoCodigo;
    }

    public void setMaeMotivoEspecificoCodigo(String maeMotivoEspecificoCodigo) {
        this.maeMotivoEspecificoCodigo = maeMotivoEspecificoCodigo;
    }

    public String getMaeMotivoEspecificoValor() {
        return maeMotivoEspecificoValor;
    }

    public void setMaeMotivoEspecificoValor(String maeMotivoEspecificoValor) {
        this.maeMotivoEspecificoValor = maeMotivoEspecificoValor;
    }

    public Integer getMaeTipoMotivoEspecificoId() {
        return maeTipoMotivoEspecificoId;
    }

    public void setMaeTipoMotivoEspecificoId(Integer maeTipoMotivoEspecificoId) {
        this.maeTipoMotivoEspecificoId = maeTipoMotivoEspecificoId;
    }

    public String getMaeTipoMotivoEspecificoCodigo() {
        return maeTipoMotivoEspecificoCodigo;
    }

    public void setMaeTipoMotivoEspecificoCodigo(String maeTipoMotivoEspecificoCodigo) {
        this.maeTipoMotivoEspecificoCodigo = maeTipoMotivoEspecificoCodigo;
    }

    public String getMaeTipoMotivoEspecificoValor() {
        return maeTipoMotivoEspecificoValor;
    }

    public void setMaeTipoMotivoEspecificoValor(String maeTipoMotivoEspecificoValor) {
        this.maeTipoMotivoEspecificoValor = maeTipoMotivoEspecificoValor;
    }

    public Integer getMaeSubtipoMotivoEspecificoId() {
        return maeSubtipoMotivoEspecificoId;
    }

    public void setMaeSubtipoMotivoEspecificoId(Integer maeSubtipoMotivoEspecificoId) {
        this.maeSubtipoMotivoEspecificoId = maeSubtipoMotivoEspecificoId;
    }

    public String getMaeSubtipoMotivoEspecificoCodigo() {
        return maeSubtipoMotivoEspecificoCodigo;
    }

    public void setMaeSubtipoMotivoEspecificoCodigo(String maeSubtipoMotivoEspecificoCodigo) {
        this.maeSubtipoMotivoEspecificoCodigo = maeSubtipoMotivoEspecificoCodigo;
    }

    public String getMaeSubtipoMotivoEspecificoValor() {
        return maeSubtipoMotivoEspecificoValor;
    }

    public void setMaeSubtipoMotivoEspecificoValor(String maeSubtipoMotivoEspecificoValor) {
        this.maeSubtipoMotivoEspecificoValor = maeSubtipoMotivoEspecificoValor;
    }

    public boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Integer getMaeCasoCerradoId() {
        return maeCasoCerradoId;
    }

    public void setMaeCasoCerradoId(Integer maeCasoCerradoId) {
        this.maeCasoCerradoId = maeCasoCerradoId;
    }

    public String getMaeCasoCerradoCodigo() {
        return maeCasoCerradoCodigo;
    }

    public void setMaeCasoCerradoCodigo(String maeCasoCerradoCodigo) {
        this.maeCasoCerradoCodigo = maeCasoCerradoCodigo;
    }

    public String getMaeCasoCerradoValor() {
        return maeCasoCerradoValor;
    }

    public void setMaeCasoCerradoValor(String maeCasoCerradoValor) {
        this.maeCasoCerradoValor = maeCasoCerradoValor;
    }

    public boolean getProteccionDatos() {
        return proteccionDatos;
    }

    public void setProteccionDatos(boolean proteccionDatos) {
        this.proteccionDatos = proteccionDatos;
    }

    public boolean getUsuarioPluripatologico() {
        return usuarioPluripatologico;
    }

    public void setUsuarioPluripatologico(boolean usuarioPluripatologico) {
        this.usuarioPluripatologico = usuarioPluripatologico;
    }

    public Integer getOrigenCierre() {
        return origenCierre;
    }

    public void setOrigenCierre(Integer origenCierre) {
        this.origenCierre = origenCierre;
    }

    public Date getFechaCreacionCaso() {
        return fechaCreacionCaso;
    }

    public void setFechaCreacionCaso(Date fechaCreacionCaso) {
        this.fechaCreacionCaso = fechaCreacionCaso;
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

    public String getUsuarioReabre() {
        return usuarioReabre;
    }

    public void setUsuarioReabre(String usuarioReabre) {
        this.usuarioReabre = usuarioReabre;
    }

    public String getTerminalReabre() {
        return terminalReabre;
    }

    public void setTerminalReabre(String terminalReabre) {
        this.terminalReabre = terminalReabre;
    }

    public Date getFechaHoraReabre() {
        return fechaHoraReabre;
    }

    public void setFechaHoraReabre(Date fechaHoraReabre) {
        this.fechaHoraReabre = fechaHoraReabre;
    }

    public String getUsuarioCierra() {
        return usuarioCierra;
    }

    public void setUsuarioCierra(String usuarioCierra) {
        this.usuarioCierra = usuarioCierra;
    }

    public String getTerminalCierra() {
        return terminalCierra;
    }

    public void setTerminalCierra(String terminalCierra) {
        this.terminalCierra = terminalCierra;
    }

    public Date getFechaHoraCierra() {
        return fechaHoraCierra;
    }

    public void setFechaHoraCierra(Date fechaHoraCierra) {
        this.fechaHoraCierra = fechaHoraCierra;
    }

    @XmlTransient
    public List<AusCasoServicios> getAusCasoServiciosList() {
        return ausCasoServiciosList;
    }

    public void setAusCasoServiciosList(List<AusCasoServicios> ausCasoServiciosList) {
        this.ausCasoServiciosList = ausCasoServiciosList;
    }

    public AusCargaMasivas getAusCargaMasivasId() {
        return ausCargaMasivasId;
    }

    public void setAusCargaMasivasId(AusCargaMasivas ausCargaMasivasId) {
        this.ausCargaMasivasId = ausCargaMasivasId;
    }

    public AusPersonas getAusPersonasId() {
        return ausPersonasId;
    }

    public void setAusPersonasId(AusPersonas ausPersonasId) {
        this.ausPersonasId = ausPersonasId;
    }

    public AusPersonas getAusAccionantePersonasId() {
        return ausAccionantePersonasId;
    }

    public void setAusAccionantePersonasId(AusPersonas ausAccionantePersonasId) {
        this.ausAccionantePersonasId = ausAccionantePersonasId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
    }

    public GnUbicaciones getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(GnUbicaciones gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    public GnUsuarios getGnUsuarioCierreId() {
        return gnUsuarioCierreId;
    }

    public void setGnUsuarioCierreId(GnUsuarios gnUsuarioCierreId) {
        this.gnUsuarioCierreId = gnUsuarioCierreId;
    }

    public GnUsuarios getGnUsuariosResponsableId() {
        return gnUsuariosResponsableId;
    }

    public void setGnUsuariosResponsableId(GnUsuarios gnUsuariosResponsableId) {
        this.gnUsuariosResponsableId = gnUsuariosResponsableId;
    }

    @XmlTransient
    public List<AusSolicitudes> getAusSolicitudesList() {
        return ausSolicitudesList;
    }

    public void setAusSolicitudesList(List<AusSolicitudes> ausSolicitudesList) {
        this.ausSolicitudesList = ausSolicitudesList;
    }

    @XmlTransient
    public List<AusSeguimientos> getAusSeguimientosList() {
        return ausSeguimientosList;
    }

    public void setAusSeguimientosList(List<AusSeguimientos> ausSeguimientosList) {
        this.ausSeguimientosList = ausSeguimientosList;
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
        if (!(object instanceof AusCasos)) {
            return false;
        }
        AusCasos other = (AusCasos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusCasos[ id=" + id + " ]";
    }
    
}
