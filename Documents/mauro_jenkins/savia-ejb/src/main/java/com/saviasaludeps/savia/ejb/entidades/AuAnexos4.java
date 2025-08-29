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
@Table(name = "au_anexos4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexos4.findAll", query = "SELECT a FROM AuAnexos4 a"),
    @NamedQuery(name = "AuAnexos4.findById", query = "SELECT a FROM AuAnexos4 a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexos4.findByNumeroAutorizacion", query = "SELECT a FROM AuAnexos4 a WHERE a.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "AuAnexos4.findByFechaInicio", query = "SELECT a FROM AuAnexos4 a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "AuAnexos4.findByFechaFin", query = "SELECT a FROM AuAnexos4 a WHERE a.fechaFin = :fechaFin"),
    @NamedQuery(name = "AuAnexos4.findByDiasVigencia", query = "SELECT a FROM AuAnexos4 a WHERE a.diasVigencia = :diasVigencia"),
    @NamedQuery(name = "AuAnexos4.findByPosfechada", query = "SELECT a FROM AuAnexos4 a WHERE a.posfechada = :posfechada"),
    @NamedQuery(name = "AuAnexos4.findByFechaAutorizacion", query = "SELECT a FROM AuAnexos4 a WHERE a.fechaAutorizacion = :fechaAutorizacion"),
    @NamedQuery(name = "AuAnexos4.findByFechaAutorizacionImpresion", query = "SELECT a FROM AuAnexos4 a WHERE a.fechaAutorizacionImpresion = :fechaAutorizacionImpresion"),
    @NamedQuery(name = "AuAnexos4.findByEstado", query = "SELECT a FROM AuAnexos4 a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexos4.findByMaeEstadoMotivoId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeEstadoMotivoId = :maeEstadoMotivoId"),
    @NamedQuery(name = "AuAnexos4.findByMaeEstadoMotivoCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeEstadoMotivoCodigo = :maeEstadoMotivoCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeEstadoMotivoValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeEstadoMotivoValor = :maeEstadoMotivoValor"),
    @NamedQuery(name = "AuAnexos4.findByEstadoJustificacion", query = "SELECT a FROM AuAnexos4 a WHERE a.estadoJustificacion = :estadoJustificacion"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoTipoDocumento", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoTipoDocumento = :afiliadoTipoDocumento"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoNumeroDocumento", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoNumeroDocumento = :afiliadoNumeroDocumento"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoPrimerApellido", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoPrimerApellido = :afiliadoPrimerApellido"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoSegundoApellido", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoSegundoApellido = :afiliadoSegundoApellido"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoPrimerNombre", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoPrimerNombre = :afiliadoPrimerNombre"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoSegundoNombre", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoSegundoNombre = :afiliadoSegundoNombre"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoFechaNacimiento", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoFechaNacimiento = :afiliadoFechaNacimiento"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoUbicacion", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoUbicacion = :afiliadoUbicacion"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoDepartamento", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoDepartamento = :afiliadoDepartamento"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoMunicipio", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoMunicipio = :afiliadoMunicipio"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoDireccion", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoDireccion = :afiliadoDireccion"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoTelefono", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoTelefono = :afiliadoTelefono"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoCelular", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoCelular = :afiliadoCelular"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoCorreo", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoCorreo = :afiliadoCorreo"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorTipoDocumento", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorTipoDocumento = :prestadorTipoDocumento"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorNumeroDocumento", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorNumeroDocumento = :prestadorNumeroDocumento"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorNombre", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorNombre = :prestadorNombre"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorCodigoHabilitacion", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorCodigoHabilitacion = :prestadorCodigoHabilitacion"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorTelefonoCita", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorTelefonoCita = :prestadorTelefonoCita"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorDireccion", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorDireccion = :prestadorDireccion"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorDepartamento", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorDepartamento = :prestadorDepartamento"),
    @NamedQuery(name = "AuAnexos4.findByPrestadorMunicipio", query = "SELECT a FROM AuAnexos4 a WHERE a.prestadorMunicipio = :prestadorMunicipio"),
    @NamedQuery(name = "AuAnexos4.findByMaeRegimenId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "AuAnexos4.findByMaeRegimenCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeRegimenValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "AuAnexos4.findByMaeAmbitoAtencionId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "AuAnexos4.findByMaeAmbitoAtencionCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeAmbitoAtencionValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor"),
    @NamedQuery(name = "AuAnexos4.findByMaServicioHabilitadoId", query = "SELECT a FROM AuAnexos4 a WHERE a.maServicioHabilitadoId = :maServicioHabilitadoId"),
    @NamedQuery(name = "AuAnexos4.findByMaServicioHabilitadoCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maServicioHabilitadoCodigo = :maServicioHabilitadoCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaServicioHabilitadoValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maServicioHabilitadoValor = :maServicioHabilitadoValor"),
    @NamedQuery(name = "AuAnexos4.findByMaEspecialidadId", query = "SELECT a FROM AuAnexos4 a WHERE a.maEspecialidadId = :maEspecialidadId"),
    @NamedQuery(name = "AuAnexos4.findByMaEspecialidadCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maEspecialidadCodigo = :maEspecialidadCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaEspecialidadValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maEspecialidadValor = :maEspecialidadValor"),
    @NamedQuery(name = "AuAnexos4.findByMaeGuiaManejoIntegralId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeGuiaManejoIntegralId = :maeGuiaManejoIntegralId"),
    @NamedQuery(name = "AuAnexos4.findByMaeGuiaManejoIntegralCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeGuiaManejoIntegralCodigo = :maeGuiaManejoIntegralCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeGuiaManejoIntegralValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeGuiaManejoIntegralValor = :maeGuiaManejoIntegralValor"),
    @NamedQuery(name = "AuAnexos4.findByCantidadEntregas", query = "SELECT a FROM AuAnexos4 a WHERE a.cantidadEntregas = :cantidadEntregas"),
    @NamedQuery(name = "AuAnexos4.findByNumeroEntrega", query = "SELECT a FROM AuAnexos4 a WHERE a.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "AuAnexos4.findByAnexo3Cama", query = "SELECT a FROM AuAnexos4 a WHERE a.anexo3Cama = :anexo3Cama"),
    @NamedQuery(name = "AuAnexos4.findByTipoServicioHabilitado", query = "SELECT a FROM AuAnexos4 a WHERE a.tipoServicioHabilitado = :tipoServicioHabilitado"),
    @NamedQuery(name = "AuAnexos4.findByDiagnosticoPrincipal", query = "SELECT a FROM AuAnexos4 a WHERE a.diagnosticoPrincipal = :diagnosticoPrincipal"),
    @NamedQuery(name = "AuAnexos4.findByNombreAutoriza", query = "SELECT a FROM AuAnexos4 a WHERE a.nombreAutoriza = :nombreAutoriza"),
    @NamedQuery(name = "AuAnexos4.findByCargoActividadAutoriza", query = "SELECT a FROM AuAnexos4 a WHERE a.cargoActividadAutoriza = :cargoActividadAutoriza"),
    @NamedQuery(name = "AuAnexos4.findByEpsTelefono", query = "SELECT a FROM AuAnexos4 a WHERE a.epsTelefono = :epsTelefono"),
    @NamedQuery(name = "AuAnexos4.findByEntidadPago", query = "SELECT a FROM AuAnexos4 a WHERE a.entidadPago = :entidadPago"),
    @NamedQuery(name = "AuAnexos4.findByCodigoEntidadPago", query = "SELECT a FROM AuAnexos4 a WHERE a.codigoEntidadPago = :codigoEntidadPago"),
    @NamedQuery(name = "AuAnexos4.findByAplicaFactura", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaFactura = :aplicaFactura"),
    @NamedQuery(name = "AuAnexos4.findByAplicaNobps", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaNobps = :aplicaNobps"),
    @NamedQuery(name = "AuAnexos4.findByAplicaPac", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaPac = :aplicaPac"),
    @NamedQuery(name = "AuAnexos4.findByAplicaCuotaModeradora", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaCuotaModeradora = :aplicaCuotaModeradora"),
    @NamedQuery(name = "AuAnexos4.findByAplicaCopago", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaCopago = :aplicaCopago"),
    @NamedQuery(name = "AuAnexos4.findByAplicaCuotaRecuperacion", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaCuotaRecuperacion = :aplicaCuotaRecuperacion"),
    @NamedQuery(name = "AuAnexos4.findByAplicaOtro", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaOtro = :aplicaOtro"),
    @NamedQuery(name = "AuAnexos4.findByAplicaAltocosto", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaAltocosto = :aplicaAltocosto"),
    @NamedQuery(name = "AuAnexos4.findByAplicaTutela", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaTutela = :aplicaTutela"),
    @NamedQuery(name = "AuAnexos4.findByAplicaTopeMaximo", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaTopeMaximo = :aplicaTopeMaximo"),
    @NamedQuery(name = "AuAnexos4.findByAplicaNoRed", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaNoRed = :aplicaNoRed"),
    @NamedQuery(name = "AuAnexos4.findByAplicaAutorizacionAutomatica", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaAutorizacionAutomatica = :aplicaAutorizacionAutomatica"),
    @NamedQuery(name = "AuAnexos4.findByAplicaTiqueteBonoVale", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaTiqueteBonoVale = :aplicaTiqueteBonoVale"),
    @NamedQuery(name = "AuAnexos4.findByAplicaCapitaRecobro", query = "SELECT a FROM AuAnexos4 a WHERE a.aplicaCapitaRecobro = :aplicaCapitaRecobro"),
    @NamedQuery(name = "AuAnexos4.findByValorCuotaModeradora", query = "SELECT a FROM AuAnexos4 a WHERE a.valorCuotaModeradora = :valorCuotaModeradora"),
    @NamedQuery(name = "AuAnexos4.findByValorCopago", query = "SELECT a FROM AuAnexos4 a WHERE a.valorCopago = :valorCopago"),
    @NamedQuery(name = "AuAnexos4.findByValorPac", query = "SELECT a FROM AuAnexos4 a WHERE a.valorPac = :valorPac"),
    @NamedQuery(name = "AuAnexos4.findByValorCuotaRecuperacion", query = "SELECT a FROM AuAnexos4 a WHERE a.valorCuotaRecuperacion = :valorCuotaRecuperacion"),
    @NamedQuery(name = "AuAnexos4.findByValorCuotaOtro", query = "SELECT a FROM AuAnexos4 a WHERE a.valorCuotaOtro = :valorCuotaOtro"),
    @NamedQuery(name = "AuAnexos4.findByValorTopeMaximo", query = "SELECT a FROM AuAnexos4 a WHERE a.valorTopeMaximo = :valorTopeMaximo"),
    @NamedQuery(name = "AuAnexos4.findByValorCotizacion", query = "SELECT a FROM AuAnexos4 a WHERE a.valorCotizacion = :valorCotizacion"),
    @NamedQuery(name = "AuAnexos4.findBySemanasAfiliacion", query = "SELECT a FROM AuAnexos4 a WHERE a.semanasAfiliacion = :semanasAfiliacion"),
    @NamedQuery(name = "AuAnexos4.findByNumeroPrescripcion", query = "SELECT a FROM AuAnexos4 a WHERE a.numeroPrescripcion = :numeroPrescripcion"),
    @NamedQuery(name = "AuAnexos4.findByNumeroTutela", query = "SELECT a FROM AuAnexos4 a WHERE a.numeroTutela = :numeroTutela"),
    @NamedQuery(name = "AuAnexos4.findByExcentoCopago", query = "SELECT a FROM AuAnexos4 a WHERE a.excentoCopago = :excentoCopago"),
    @NamedQuery(name = "AuAnexos4.findByMedioAutorizacion", query = "SELECT a FROM AuAnexos4 a WHERE a.medioAutorizacion = :medioAutorizacion"),
    @NamedQuery(name = "AuAnexos4.findByPorcentajeRecuperacion", query = "SELECT a FROM AuAnexos4 a WHERE a.porcentajeRecuperacion = :porcentajeRecuperacion"),
    @NamedQuery(name = "AuAnexos4.findByImpresionesAutorizadas", query = "SELECT a FROM AuAnexos4 a WHERE a.impresionesAutorizadas = :impresionesAutorizadas"),
    @NamedQuery(name = "AuAnexos4.findByImpresionesRealizadas", query = "SELECT a FROM AuAnexos4 a WHERE a.impresionesRealizadas = :impresionesRealizadas"),
    @NamedQuery(name = "AuAnexos4.findByObservacion", query = "SELECT a FROM AuAnexos4 a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuAnexos4.findByArchivoNombre", query = "SELECT a FROM AuAnexos4 a WHERE a.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "AuAnexos4.findByRuta", query = "SELECT a FROM AuAnexos4 a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuAnexos4.findByArchivo", query = "SELECT a FROM AuAnexos4 a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuAnexos4.findByContratoAnticipado", query = "SELECT a FROM AuAnexos4 a WHERE a.contratoAnticipado = :contratoAnticipado"),
    @NamedQuery(name = "AuAnexos4.findByContratoAnticipadoObservacion", query = "SELECT a FROM AuAnexos4 a WHERE a.contratoAnticipadoObservacion = :contratoAnticipadoObservacion"),
    @NamedQuery(name = "AuAnexos4.findByPagoAnticipado", query = "SELECT a FROM AuAnexos4 a WHERE a.pagoAnticipado = :pagoAnticipado"),
    @NamedQuery(name = "AuAnexos4.findByVersion", query = "SELECT a FROM AuAnexos4 a WHERE a.version = :version"),
    @NamedQuery(name = "AuAnexos4.findByConsecutivo", query = "SELECT a FROM AuAnexos4 a WHERE a.consecutivo = :consecutivo"),
    @NamedQuery(name = "AuAnexos4.findByMaeUbicacionId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeUbicacionId = :maeUbicacionId"),
    @NamedQuery(name = "AuAnexos4.findByMaeUbicacionCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeUbicacionCodigo = :maeUbicacionCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeUbicacionValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeUbicacionValor = :maeUbicacionValor"),
    @NamedQuery(name = "AuAnexos4.findByMaeModalidadTecnologiaId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeModalidadTecnologiaId = :maeModalidadTecnologiaId"),
    @NamedQuery(name = "AuAnexos4.findByMaeModalidadTecnologiaCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeModalidadTecnologiaCodigo = :maeModalidadTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeModalidadTecnologiaValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeModalidadTecnologiaValor = :maeModalidadTecnologiaValor"),
    @NamedQuery(name = "AuAnexos4.findByMaeFinalidadTecnologiaId", query = "SELECT a FROM AuAnexos4 a WHERE a.maeFinalidadTecnologiaId = :maeFinalidadTecnologiaId"),
    @NamedQuery(name = "AuAnexos4.findByMaeFinalidadTecnologiaCodigo", query = "SELECT a FROM AuAnexos4 a WHERE a.maeFinalidadTecnologiaCodigo = :maeFinalidadTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexos4.findByMaeFinalidadTecnologiaValor", query = "SELECT a FROM AuAnexos4 a WHERE a.maeFinalidadTecnologiaValor = :maeFinalidadTecnologiaValor"),
    @NamedQuery(name = "AuAnexos4.findByAfiliadoDireccionAlternativa", query = "SELECT a FROM AuAnexos4 a WHERE a.afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa"),
    @NamedQuery(name = "AuAnexos4.findByTopeAplicado", query = "SELECT a FROM AuAnexos4 a WHERE a.topeAplicado = :topeAplicado"),
    @NamedQuery(name = "AuAnexos4.findByFuenteAnula", query = "SELECT a FROM AuAnexos4 a WHERE a.fuenteAnula = :fuenteAnula"),
    @NamedQuery(name = "AuAnexos4.findByMotivoExentoCobro", query = "SELECT a FROM AuAnexos4 a WHERE a.motivoExentoCobro = :motivoExentoCobro"),
    @NamedQuery(name = "AuAnexos4.findByUsuarioCrea", query = "SELECT a FROM AuAnexos4 a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexos4.findByTerminalCrea", query = "SELECT a FROM AuAnexos4 a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexos4.findByFechaHoraCrea", query = "SELECT a FROM AuAnexos4 a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexos4.findByUsuarioModifica", query = "SELECT a FROM AuAnexos4 a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexos4.findByTerminalModifica", query = "SELECT a FROM AuAnexos4 a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexos4.findByFechaHoraModifica", query = "SELECT a FROM AuAnexos4 a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexos4 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_autorizacion")
    private String numeroAutorizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias_vigencia")
    private int diasVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "posfechada")
    private int posfechada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacion;
    @Column(name = "fecha_autorizacion_impresion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacionImpresion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "mae_estado_motivo_id")
    private Integer maeEstadoMotivoId;
    @Size(max = 8)
    @Column(name = "mae_estado_motivo_codigo")
    private String maeEstadoMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_motivo_valor")
    private String maeEstadoMotivoValor;
    @Size(max = 1024)
    @Column(name = "estado_justificacion")
    private String estadoJustificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "afiliado_tipo_documento")
    private String afiliadoTipoDocumento;
    @Size(max = 16)
    @Column(name = "afiliado_numero_documento")
    private String afiliadoNumeroDocumento;
    @Size(max = 32)
    @Column(name = "afiliado_primer_apellido")
    private String afiliadoPrimerApellido;
    @Size(max = 32)
    @Column(name = "afiliado_segundo_apellido")
    private String afiliadoSegundoApellido;
    @Size(max = 32)
    @Column(name = "afiliado_primer_nombre")
    private String afiliadoPrimerNombre;
    @Size(max = 32)
    @Column(name = "afiliado_segundo_nombre")
    private String afiliadoSegundoNombre;
    @Column(name = "afiliado_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date afiliadoFechaNacimiento;
    @Column(name = "afiliado_ubicacion")
    private Integer afiliadoUbicacion;
    @Size(max = 32)
    @Column(name = "afiliado_departamento")
    private String afiliadoDepartamento;
    @Size(max = 32)
    @Column(name = "afiliado_municipio")
    private String afiliadoMunicipio;
    @Size(max = 256)
    @Column(name = "afiliado_direccion")
    private String afiliadoDireccion;
    @Size(max = 32)
    @Column(name = "afiliado_telefono")
    private String afiliadoTelefono;
    @Size(max = 32)
    @Column(name = "afiliado_celular")
    private String afiliadoCelular;
    @Size(max = 128)
    @Column(name = "afiliado_correo")
    private String afiliadoCorreo;
    @Size(max = 8)
    @Column(name = "prestador_tipo_documento")
    private String prestadorTipoDocumento;
    @Size(max = 16)
    @Column(name = "prestador_numero_documento")
    private String prestadorNumeroDocumento;
    @Size(max = 256)
    @Column(name = "prestador_nombre")
    private String prestadorNombre;
    @Size(max = 16)
    @Column(name = "prestador_codigo_habilitacion")
    private String prestadorCodigoHabilitacion;
    @Size(max = 64)
    @Column(name = "prestador_telefono_cita")
    private String prestadorTelefonoCita;
    @Size(max = 256)
    @Column(name = "prestador_direccion")
    private String prestadorDireccion;
    @Size(max = 32)
    @Column(name = "prestador_departamento")
    private String prestadorDepartamento;
    @Size(max = 32)
    @Column(name = "prestador_municipio")
    private String prestadorMunicipio;
    @Column(name = "mae_regimen_id")
    private Integer maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_ambito_atencion_id")
    private int maeAmbitoAtencionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_ambito_atencion_codigo")
    private String maeAmbitoAtencionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_ambito_atencion_valor")
    private String maeAmbitoAtencionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_servicio_habilitado_id")
    private int maServicioHabilitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_habilitado_codigo")
    private String maServicioHabilitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_habilitado_valor")
    private String maServicioHabilitadoValor;
    @Column(name = "ma_especialidad_id")
    private Integer maEspecialidadId;
    @Size(max = 32)
    @Column(name = "ma_especialidad_codigo")
    private String maEspecialidadCodigo;
    @Size(max = 512)
    @Column(name = "ma_especialidad_valor")
    private String maEspecialidadValor;
    @Column(name = "mae_guia_manejo_integral_id")
    private Integer maeGuiaManejoIntegralId;
    @Size(max = 8)
    @Column(name = "mae_guia_manejo_integral_codigo")
    private String maeGuiaManejoIntegralCodigo;
    @Size(max = 128)
    @Column(name = "mae_guia_manejo_integral_valor")
    private String maeGuiaManejoIntegralValor;
    @Column(name = "cantidad_entregas")
    private Integer cantidadEntregas;
    @Column(name = "numero_entrega")
    private Integer numeroEntrega;
    @Size(max = 16)
    @Column(name = "anexo3_cama")
    private String anexo3Cama;
    @Size(max = 128)
    @Column(name = "tipo_servicio_habilitado")
    private String tipoServicioHabilitado;
    @Size(max = 256)
    @Column(name = "diagnostico_principal")
    private String diagnosticoPrincipal;
    @Size(max = 128)
    @Column(name = "nombre_autoriza")
    private String nombreAutoriza;
    @Size(max = 128)
    @Column(name = "cargo_actividad_autoriza")
    private String cargoActividadAutoriza;
    @Size(max = 16)
    @Column(name = "eps_telefono")
    private String epsTelefono;
    @Size(max = 128)
    @Column(name = "entidad_pago")
    private String entidadPago;
    @Size(max = 8)
    @Column(name = "codigo_entidad_pago")
    private String codigoEntidadPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_factura")
    private boolean aplicaFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_nobps")
    private boolean aplicaNobps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_pac")
    private boolean aplicaPac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_cuota_moderadora")
    private boolean aplicaCuotaModeradora;
    @Column(name = "aplica_copago")
    private Boolean aplicaCopago;
    @Column(name = "aplica_cuota_recuperacion")
    private Boolean aplicaCuotaRecuperacion;
    @Column(name = "aplica_otro")
    private Boolean aplicaOtro;
    @Column(name = "aplica_altocosto")
    private Boolean aplicaAltocosto;
    @Column(name = "aplica_tutela")
    private Boolean aplicaTutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_tope_maximo")
    private boolean aplicaTopeMaximo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_no_red")
    private boolean aplicaNoRed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_autorizacion_automatica")
    private boolean aplicaAutorizacionAutomatica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_tiquete_bono_vale")
    private boolean aplicaTiqueteBonoVale;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_capita_recobro")
    private boolean aplicaCapitaRecobro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_cuota_moderadora")
    private BigDecimal valorCuotaModeradora;
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Column(name = "valor_pac")
    private BigDecimal valorPac;
    @Column(name = "valor_cuota_recuperacion")
    private BigDecimal valorCuotaRecuperacion;
    @Column(name = "valor_cuota_otro")
    private BigDecimal valorCuotaOtro;
    @Column(name = "valor_tope_maximo")
    private BigDecimal valorTopeMaximo;
    @Column(name = "valor_cotizacion")
    private BigDecimal valorCotizacion;
    @Column(name = "semanas_afiliacion")
    private Integer semanasAfiliacion;
    @Size(max = 32)
    @Column(name = "numero_prescripcion")
    private String numeroPrescripcion;
    @Size(max = 32)
    @Column(name = "numero_tutela")
    private String numeroTutela;
    @Column(name = "excento_copago")
    private Boolean excentoCopago;
    @Column(name = "medio_autorizacion")
    private Integer medioAutorizacion;
    @Column(name = "porcentaje_recuperacion")
    private BigDecimal porcentajeRecuperacion;
    @Column(name = "impresiones_autorizadas")
    private Integer impresionesAutorizadas;
    @Column(name = "impresiones_realizadas")
    private Integer impresionesRealizadas;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 256)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "contrato_anticipado")
    private Boolean contratoAnticipado;
    @Size(max = 512)
    @Column(name = "contrato_anticipado_observacion")
    private String contratoAnticipadoObservacion;
    @Column(name = "pago_anticipado")
    private Boolean pagoAnticipado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private boolean version;
    @Size(max = 64)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "mae_ubicacion_id")
    private Integer maeUbicacionId;
    @Size(max = 8)
    @Column(name = "mae_ubicacion_codigo")
    private String maeUbicacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_ubicacion_valor")
    private String maeUbicacionValor;
    @Column(name = "mae_modalidad_tecnologia_id")
    private Integer maeModalidadTecnologiaId;
    @Size(max = 8)
    @Column(name = "mae_modalidad_tecnologia_codigo")
    private String maeModalidadTecnologiaCodigo;
    @Size(max = 128)
    @Column(name = "mae_modalidad_tecnologia_valor")
    private String maeModalidadTecnologiaValor;
    @Column(name = "mae_finalidad_tecnologia_id")
    private Integer maeFinalidadTecnologiaId;
    @Size(max = 8)
    @Column(name = "mae_finalidad_tecnologia_codigo")
    private String maeFinalidadTecnologiaCodigo;
    @Size(max = 128)
    @Column(name = "mae_finalidad_tecnologia_valor")
    private String maeFinalidadTecnologiaValor;
    @Size(max = 256)
    @Column(name = "afiliado_direccion_alternativa")
    private String afiliadoDireccionAlternativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tope_aplicado")
    private boolean topeAplicado;
    @Column(name = "fuente_anula")
    private Integer fuenteAnula;
    @Size(max = 512)
    @Column(name = "motivo_exento_cobro")
    private String motivoExentoCobro;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuAnexo4Impresiones> auAnexo4ImpresionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuAnexo4Items> auAnexo4ItemsList;
    @JoinColumn(name = "au_anexo4_carga_anuladas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo4CargaAnuladas auAnexo4CargaAnuladasId;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos2_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos2 auAnexos2Id;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuAnexo4Entregas> auAnexo4EntregasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuAnexo4Historicos> auAnexo4HistoricosList;
    @OneToMany(mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuAnexo4CargaAnuladaSucesos> auAnexo4CargaAnuladaSucesosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuAnexo4Estados> auAnexo4EstadosList;
    @OneToMany(mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
    @OneToMany(mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<CsCopagoModeradoraHistoricos> csCopagoModeradoraHistoricosList;
    @OneToMany(mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4Id", fetch = FetchType.LAZY)
    private List<CmAuditoriaAutorizaciones> cmAuditoriaAutorizacionesList;

    public AuAnexos4() {
    }

    public AuAnexos4(Integer id) {
        this.id = id;
    }

    public AuAnexos4(Integer id, String numeroAutorizacion, Date fechaInicio, Date fechaFin, int diasVigencia, int posfechada, Date fechaAutorizacion, int estado, String afiliadoTipoDocumento, int maeAmbitoAtencionId, String maeAmbitoAtencionCodigo, String maeAmbitoAtencionValor, int maServicioHabilitadoId, boolean aplicaFactura, boolean aplicaNobps, boolean aplicaPac, boolean aplicaCuotaModeradora, boolean aplicaTopeMaximo, boolean aplicaNoRed, boolean aplicaAutorizacionAutomatica, boolean aplicaTiqueteBonoVale, boolean aplicaCapitaRecobro, boolean version, boolean topeAplicado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numeroAutorizacion = numeroAutorizacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasVigencia = diasVigencia;
        this.posfechada = posfechada;
        this.fechaAutorizacion = fechaAutorizacion;
        this.estado = estado;
        this.afiliadoTipoDocumento = afiliadoTipoDocumento;
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
        this.maServicioHabilitadoId = maServicioHabilitadoId;
        this.aplicaFactura = aplicaFactura;
        this.aplicaNobps = aplicaNobps;
        this.aplicaPac = aplicaPac;
        this.aplicaCuotaModeradora = aplicaCuotaModeradora;
        this.aplicaTopeMaximo = aplicaTopeMaximo;
        this.aplicaNoRed = aplicaNoRed;
        this.aplicaAutorizacionAutomatica = aplicaAutorizacionAutomatica;
        this.aplicaTiqueteBonoVale = aplicaTiqueteBonoVale;
        this.aplicaCapitaRecobro = aplicaCapitaRecobro;
        this.version = version;
        this.topeAplicado = topeAplicado;
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

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }

    public int getPosfechada() {
        return posfechada;
    }

    public void setPosfechada(int posfechada) {
        this.posfechada = posfechada;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getFechaAutorizacionImpresion() {
        return fechaAutorizacionImpresion;
    }

    public void setFechaAutorizacionImpresion(Date fechaAutorizacionImpresion) {
        this.fechaAutorizacionImpresion = fechaAutorizacionImpresion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getMaeEstadoMotivoId() {
        return maeEstadoMotivoId;
    }

    public void setMaeEstadoMotivoId(Integer maeEstadoMotivoId) {
        this.maeEstadoMotivoId = maeEstadoMotivoId;
    }

    public String getMaeEstadoMotivoCodigo() {
        return maeEstadoMotivoCodigo;
    }

    public void setMaeEstadoMotivoCodigo(String maeEstadoMotivoCodigo) {
        this.maeEstadoMotivoCodigo = maeEstadoMotivoCodigo;
    }

    public String getMaeEstadoMotivoValor() {
        return maeEstadoMotivoValor;
    }

    public void setMaeEstadoMotivoValor(String maeEstadoMotivoValor) {
        this.maeEstadoMotivoValor = maeEstadoMotivoValor;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public String getAfiliadoTipoDocumento() {
        return afiliadoTipoDocumento;
    }

    public void setAfiliadoTipoDocumento(String afiliadoTipoDocumento) {
        this.afiliadoTipoDocumento = afiliadoTipoDocumento;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public Date getAfiliadoFechaNacimiento() {
        return afiliadoFechaNacimiento;
    }

    public void setAfiliadoFechaNacimiento(Date afiliadoFechaNacimiento) {
        this.afiliadoFechaNacimiento = afiliadoFechaNacimiento;
    }

    public Integer getAfiliadoUbicacion() {
        return afiliadoUbicacion;
    }

    public void setAfiliadoUbicacion(Integer afiliadoUbicacion) {
        this.afiliadoUbicacion = afiliadoUbicacion;
    }

    public String getAfiliadoDepartamento() {
        return afiliadoDepartamento;
    }

    public void setAfiliadoDepartamento(String afiliadoDepartamento) {
        this.afiliadoDepartamento = afiliadoDepartamento;
    }

    public String getAfiliadoMunicipio() {
        return afiliadoMunicipio;
    }

    public void setAfiliadoMunicipio(String afiliadoMunicipio) {
        this.afiliadoMunicipio = afiliadoMunicipio;
    }

    public String getAfiliadoDireccion() {
        return afiliadoDireccion;
    }

    public void setAfiliadoDireccion(String afiliadoDireccion) {
        this.afiliadoDireccion = afiliadoDireccion;
    }

    public String getAfiliadoTelefono() {
        return afiliadoTelefono;
    }

    public void setAfiliadoTelefono(String afiliadoTelefono) {
        this.afiliadoTelefono = afiliadoTelefono;
    }

    public String getAfiliadoCelular() {
        return afiliadoCelular;
    }

    public void setAfiliadoCelular(String afiliadoCelular) {
        this.afiliadoCelular = afiliadoCelular;
    }

    public String getAfiliadoCorreo() {
        return afiliadoCorreo;
    }

    public void setAfiliadoCorreo(String afiliadoCorreo) {
        this.afiliadoCorreo = afiliadoCorreo;
    }

    public String getPrestadorTipoDocumento() {
        return prestadorTipoDocumento;
    }

    public void setPrestadorTipoDocumento(String prestadorTipoDocumento) {
        this.prestadorTipoDocumento = prestadorTipoDocumento;
    }

    public String getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(String prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorNombre() {
        return prestadorNombre;
    }

    public void setPrestadorNombre(String prestadorNombre) {
        this.prestadorNombre = prestadorNombre;
    }

    public String getPrestadorCodigoHabilitacion() {
        return prestadorCodigoHabilitacion;
    }

    public void setPrestadorCodigoHabilitacion(String prestadorCodigoHabilitacion) {
        this.prestadorCodigoHabilitacion = prestadorCodigoHabilitacion;
    }

    public String getPrestadorTelefonoCita() {
        return prestadorTelefonoCita;
    }

    public void setPrestadorTelefonoCita(String prestadorTelefonoCita) {
        this.prestadorTelefonoCita = prestadorTelefonoCita;
    }

    public String getPrestadorDireccion() {
        return prestadorDireccion;
    }

    public void setPrestadorDireccion(String prestadorDireccion) {
        this.prestadorDireccion = prestadorDireccion;
    }

    public String getPrestadorDepartamento() {
        return prestadorDepartamento;
    }

    public void setPrestadorDepartamento(String prestadorDepartamento) {
        this.prestadorDepartamento = prestadorDepartamento;
    }

    public String getPrestadorMunicipio() {
        return prestadorMunicipio;
    }

    public void setPrestadorMunicipio(String prestadorMunicipio) {
        this.prestadorMunicipio = prestadorMunicipio;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public int getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(int maeAmbitoAtencionId) {
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

    public int getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(int maServicioHabilitadoId) {
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

    public Integer getMaeGuiaManejoIntegralId() {
        return maeGuiaManejoIntegralId;
    }

    public void setMaeGuiaManejoIntegralId(Integer maeGuiaManejoIntegralId) {
        this.maeGuiaManejoIntegralId = maeGuiaManejoIntegralId;
    }

    public String getMaeGuiaManejoIntegralCodigo() {
        return maeGuiaManejoIntegralCodigo;
    }

    public void setMaeGuiaManejoIntegralCodigo(String maeGuiaManejoIntegralCodigo) {
        this.maeGuiaManejoIntegralCodigo = maeGuiaManejoIntegralCodigo;
    }

    public String getMaeGuiaManejoIntegralValor() {
        return maeGuiaManejoIntegralValor;
    }

    public void setMaeGuiaManejoIntegralValor(String maeGuiaManejoIntegralValor) {
        this.maeGuiaManejoIntegralValor = maeGuiaManejoIntegralValor;
    }

    public Integer getCantidadEntregas() {
        return cantidadEntregas;
    }

    public void setCantidadEntregas(Integer cantidadEntregas) {
        this.cantidadEntregas = cantidadEntregas;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public String getAnexo3Cama() {
        return anexo3Cama;
    }

    public void setAnexo3Cama(String anexo3Cama) {
        this.anexo3Cama = anexo3Cama;
    }

    public String getTipoServicioHabilitado() {
        return tipoServicioHabilitado;
    }

    public void setTipoServicioHabilitado(String tipoServicioHabilitado) {
        this.tipoServicioHabilitado = tipoServicioHabilitado;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getNombreAutoriza() {
        return nombreAutoriza;
    }

    public void setNombreAutoriza(String nombreAutoriza) {
        this.nombreAutoriza = nombreAutoriza;
    }

    public String getCargoActividadAutoriza() {
        return cargoActividadAutoriza;
    }

    public void setCargoActividadAutoriza(String cargoActividadAutoriza) {
        this.cargoActividadAutoriza = cargoActividadAutoriza;
    }

    public String getEpsTelefono() {
        return epsTelefono;
    }

    public void setEpsTelefono(String epsTelefono) {
        this.epsTelefono = epsTelefono;
    }

    public String getEntidadPago() {
        return entidadPago;
    }

    public void setEntidadPago(String entidadPago) {
        this.entidadPago = entidadPago;
    }

    public String getCodigoEntidadPago() {
        return codigoEntidadPago;
    }

    public void setCodigoEntidadPago(String codigoEntidadPago) {
        this.codigoEntidadPago = codigoEntidadPago;
    }

    public boolean getAplicaFactura() {
        return aplicaFactura;
    }

    public void setAplicaFactura(boolean aplicaFactura) {
        this.aplicaFactura = aplicaFactura;
    }

    public boolean getAplicaNobps() {
        return aplicaNobps;
    }

    public void setAplicaNobps(boolean aplicaNobps) {
        this.aplicaNobps = aplicaNobps;
    }

    public boolean getAplicaPac() {
        return aplicaPac;
    }

    public void setAplicaPac(boolean aplicaPac) {
        this.aplicaPac = aplicaPac;
    }

    public boolean getAplicaCuotaModeradora() {
        return aplicaCuotaModeradora;
    }

    public void setAplicaCuotaModeradora(boolean aplicaCuotaModeradora) {
        this.aplicaCuotaModeradora = aplicaCuotaModeradora;
    }

    public Boolean getAplicaCopago() {
        return aplicaCopago;
    }

    public void setAplicaCopago(Boolean aplicaCopago) {
        this.aplicaCopago = aplicaCopago;
    }

    public Boolean getAplicaCuotaRecuperacion() {
        return aplicaCuotaRecuperacion;
    }

    public void setAplicaCuotaRecuperacion(Boolean aplicaCuotaRecuperacion) {
        this.aplicaCuotaRecuperacion = aplicaCuotaRecuperacion;
    }

    public Boolean getAplicaOtro() {
        return aplicaOtro;
    }

    public void setAplicaOtro(Boolean aplicaOtro) {
        this.aplicaOtro = aplicaOtro;
    }

    public Boolean getAplicaAltocosto() {
        return aplicaAltocosto;
    }

    public void setAplicaAltocosto(Boolean aplicaAltocosto) {
        this.aplicaAltocosto = aplicaAltocosto;
    }

    public Boolean getAplicaTutela() {
        return aplicaTutela;
    }

    public void setAplicaTutela(Boolean aplicaTutela) {
        this.aplicaTutela = aplicaTutela;
    }

    public boolean getAplicaTopeMaximo() {
        return aplicaTopeMaximo;
    }

    public void setAplicaTopeMaximo(boolean aplicaTopeMaximo) {
        this.aplicaTopeMaximo = aplicaTopeMaximo;
    }

    public boolean getAplicaNoRed() {
        return aplicaNoRed;
    }

    public void setAplicaNoRed(boolean aplicaNoRed) {
        this.aplicaNoRed = aplicaNoRed;
    }

    public boolean getAplicaAutorizacionAutomatica() {
        return aplicaAutorizacionAutomatica;
    }

    public void setAplicaAutorizacionAutomatica(boolean aplicaAutorizacionAutomatica) {
        this.aplicaAutorizacionAutomatica = aplicaAutorizacionAutomatica;
    }

    public boolean getAplicaTiqueteBonoVale() {
        return aplicaTiqueteBonoVale;
    }

    public void setAplicaTiqueteBonoVale(boolean aplicaTiqueteBonoVale) {
        this.aplicaTiqueteBonoVale = aplicaTiqueteBonoVale;
    }

    public boolean getAplicaCapitaRecobro() {
        return aplicaCapitaRecobro;
    }

    public void setAplicaCapitaRecobro(boolean aplicaCapitaRecobro) {
        this.aplicaCapitaRecobro = aplicaCapitaRecobro;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorPac() {
        return valorPac;
    }

    public void setValorPac(BigDecimal valorPac) {
        this.valorPac = valorPac;
    }

    public BigDecimal getValorCuotaRecuperacion() {
        return valorCuotaRecuperacion;
    }

    public void setValorCuotaRecuperacion(BigDecimal valorCuotaRecuperacion) {
        this.valorCuotaRecuperacion = valorCuotaRecuperacion;
    }

    public BigDecimal getValorCuotaOtro() {
        return valorCuotaOtro;
    }

    public void setValorCuotaOtro(BigDecimal valorCuotaOtro) {
        this.valorCuotaOtro = valorCuotaOtro;
    }

    public BigDecimal getValorTopeMaximo() {
        return valorTopeMaximo;
    }

    public void setValorTopeMaximo(BigDecimal valorTopeMaximo) {
        this.valorTopeMaximo = valorTopeMaximo;
    }

    public BigDecimal getValorCotizacion() {
        return valorCotizacion;
    }

    public void setValorCotizacion(BigDecimal valorCotizacion) {
        this.valorCotizacion = valorCotizacion;
    }

    public Integer getSemanasAfiliacion() {
        return semanasAfiliacion;
    }

    public void setSemanasAfiliacion(Integer semanasAfiliacion) {
        this.semanasAfiliacion = semanasAfiliacion;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getNumeroTutela() {
        return numeroTutela;
    }

    public void setNumeroTutela(String numeroTutela) {
        this.numeroTutela = numeroTutela;
    }

    public Boolean getExcentoCopago() {
        return excentoCopago;
    }

    public void setExcentoCopago(Boolean excentoCopago) {
        this.excentoCopago = excentoCopago;
    }

    public Integer getMedioAutorizacion() {
        return medioAutorizacion;
    }

    public void setMedioAutorizacion(Integer medioAutorizacion) {
        this.medioAutorizacion = medioAutorizacion;
    }

    public BigDecimal getPorcentajeRecuperacion() {
        return porcentajeRecuperacion;
    }

    public void setPorcentajeRecuperacion(BigDecimal porcentajeRecuperacion) {
        this.porcentajeRecuperacion = porcentajeRecuperacion;
    }

    public Integer getImpresionesAutorizadas() {
        return impresionesAutorizadas;
    }

    public void setImpresionesAutorizadas(Integer impresionesAutorizadas) {
        this.impresionesAutorizadas = impresionesAutorizadas;
    }

    public Integer getImpresionesRealizadas() {
        return impresionesRealizadas;
    }

    public void setImpresionesRealizadas(Integer impresionesRealizadas) {
        this.impresionesRealizadas = impresionesRealizadas;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Boolean getContratoAnticipado() {
        return contratoAnticipado;
    }

    public void setContratoAnticipado(Boolean contratoAnticipado) {
        this.contratoAnticipado = contratoAnticipado;
    }

    public String getContratoAnticipadoObservacion() {
        return contratoAnticipadoObservacion;
    }

    public void setContratoAnticipadoObservacion(String contratoAnticipadoObservacion) {
        this.contratoAnticipadoObservacion = contratoAnticipadoObservacion;
    }

    public Boolean getPagoAnticipado() {
        return pagoAnticipado;
    }

    public void setPagoAnticipado(Boolean pagoAnticipado) {
        this.pagoAnticipado = pagoAnticipado;
    }

    public boolean getVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getMaeUbicacionId() {
        return maeUbicacionId;
    }

    public void setMaeUbicacionId(Integer maeUbicacionId) {
        this.maeUbicacionId = maeUbicacionId;
    }

    public String getMaeUbicacionCodigo() {
        return maeUbicacionCodigo;
    }

    public void setMaeUbicacionCodigo(String maeUbicacionCodigo) {
        this.maeUbicacionCodigo = maeUbicacionCodigo;
    }

    public String getMaeUbicacionValor() {
        return maeUbicacionValor;
    }

    public void setMaeUbicacionValor(String maeUbicacionValor) {
        this.maeUbicacionValor = maeUbicacionValor;
    }

    public Integer getMaeModalidadTecnologiaId() {
        return maeModalidadTecnologiaId;
    }

    public void setMaeModalidadTecnologiaId(Integer maeModalidadTecnologiaId) {
        this.maeModalidadTecnologiaId = maeModalidadTecnologiaId;
    }

    public String getMaeModalidadTecnologiaCodigo() {
        return maeModalidadTecnologiaCodigo;
    }

    public void setMaeModalidadTecnologiaCodigo(String maeModalidadTecnologiaCodigo) {
        this.maeModalidadTecnologiaCodigo = maeModalidadTecnologiaCodigo;
    }

    public String getMaeModalidadTecnologiaValor() {
        return maeModalidadTecnologiaValor;
    }

    public void setMaeModalidadTecnologiaValor(String maeModalidadTecnologiaValor) {
        this.maeModalidadTecnologiaValor = maeModalidadTecnologiaValor;
    }

    public Integer getMaeFinalidadTecnologiaId() {
        return maeFinalidadTecnologiaId;
    }

    public void setMaeFinalidadTecnologiaId(Integer maeFinalidadTecnologiaId) {
        this.maeFinalidadTecnologiaId = maeFinalidadTecnologiaId;
    }

    public String getMaeFinalidadTecnologiaCodigo() {
        return maeFinalidadTecnologiaCodigo;
    }

    public void setMaeFinalidadTecnologiaCodigo(String maeFinalidadTecnologiaCodigo) {
        this.maeFinalidadTecnologiaCodigo = maeFinalidadTecnologiaCodigo;
    }

    public String getMaeFinalidadTecnologiaValor() {
        return maeFinalidadTecnologiaValor;
    }

    public void setMaeFinalidadTecnologiaValor(String maeFinalidadTecnologiaValor) {
        this.maeFinalidadTecnologiaValor = maeFinalidadTecnologiaValor;
    }

    public String getAfiliadoDireccionAlternativa() {
        return afiliadoDireccionAlternativa;
    }

    public void setAfiliadoDireccionAlternativa(String afiliadoDireccionAlternativa) {
        this.afiliadoDireccionAlternativa = afiliadoDireccionAlternativa;
    }

    public boolean getTopeAplicado() {
        return topeAplicado;
    }

    public void setTopeAplicado(boolean topeAplicado) {
        this.topeAplicado = topeAplicado;
    }

    public Integer getFuenteAnula() {
        return fuenteAnula;
    }

    public void setFuenteAnula(Integer fuenteAnula) {
        this.fuenteAnula = fuenteAnula;
    }

    public String getMotivoExentoCobro() {
        return motivoExentoCobro;
    }

    public void setMotivoExentoCobro(String motivoExentoCobro) {
        this.motivoExentoCobro = motivoExentoCobro;
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
    public List<AuAnexo4Impresiones> getAuAnexo4ImpresionesList() {
        return auAnexo4ImpresionesList;
    }

    public void setAuAnexo4ImpresionesList(List<AuAnexo4Impresiones> auAnexo4ImpresionesList) {
        this.auAnexo4ImpresionesList = auAnexo4ImpresionesList;
    }

    @XmlTransient
    public List<AuAnexo4Items> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Items> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    public AuAnexo4CargaAnuladas getAuAnexo4CargaAnuladasId() {
        return auAnexo4CargaAnuladasId;
    }

    public void setAuAnexo4CargaAnuladasId(AuAnexo4CargaAnuladas auAnexo4CargaAnuladasId) {
        this.auAnexo4CargaAnuladasId = auAnexo4CargaAnuladasId;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexos2 getAuAnexos2Id() {
        return auAnexos2Id;
    }

    public void setAuAnexos2Id(AuAnexos2 auAnexos2Id) {
        this.auAnexos2Id = auAnexos2Id;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
    }

    @XmlTransient
    public List<AuAnexo4Entregas> getAuAnexo4EntregasList() {
        return auAnexo4EntregasList;
    }

    public void setAuAnexo4EntregasList(List<AuAnexo4Entregas> auAnexo4EntregasList) {
        this.auAnexo4EntregasList = auAnexo4EntregasList;
    }

    @XmlTransient
    public List<AuAnexo4Historicos> getAuAnexo4HistoricosList() {
        return auAnexo4HistoricosList;
    }

    public void setAuAnexo4HistoricosList(List<AuAnexo4Historicos> auAnexo4HistoricosList) {
        this.auAnexo4HistoricosList = auAnexo4HistoricosList;
    }

    @XmlTransient
    public List<AuAnexo4CargaAnuladaSucesos> getAuAnexo4CargaAnuladaSucesosList() {
        return auAnexo4CargaAnuladaSucesosList;
    }

    public void setAuAnexo4CargaAnuladaSucesosList(List<AuAnexo4CargaAnuladaSucesos> auAnexo4CargaAnuladaSucesosList) {
        this.auAnexo4CargaAnuladaSucesosList = auAnexo4CargaAnuladaSucesosList;
    }

    @XmlTransient
    public List<AuAnexo4Estados> getAuAnexo4EstadosList() {
        return auAnexo4EstadosList;
    }

    public void setAuAnexo4EstadosList(List<AuAnexo4Estados> auAnexo4EstadosList) {
        this.auAnexo4EstadosList = auAnexo4EstadosList;
    }

    @XmlTransient
    public List<CntContratoHistoricoPrestaciones> getCntContratoHistoricoPrestacionesList() {
        return cntContratoHistoricoPrestacionesList;
    }

    public void setCntContratoHistoricoPrestacionesList(List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList) {
        this.cntContratoHistoricoPrestacionesList = cntContratoHistoricoPrestacionesList;
    }

    @XmlTransient
    public List<CsCopagoModeradoraHistoricos> getCsCopagoModeradoraHistoricosList() {
        return csCopagoModeradoraHistoricosList;
    }

    public void setCsCopagoModeradoraHistoricosList(List<CsCopagoModeradoraHistoricos> csCopagoModeradoraHistoricosList) {
        this.csCopagoModeradoraHistoricosList = csCopagoModeradoraHistoricosList;
    }

    @XmlTransient
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    @XmlTransient
    public List<CmAuditoriaAutorizaciones> getCmAuditoriaAutorizacionesList() {
        return cmAuditoriaAutorizacionesList;
    }

    public void setCmAuditoriaAutorizacionesList(List<CmAuditoriaAutorizaciones> cmAuditoriaAutorizacionesList) {
        this.cmAuditoriaAutorizacionesList = cmAuditoriaAutorizacionesList;
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
        if (!(object instanceof AuAnexos4)) {
            return false;
        }
        AuAnexos4 other = (AuAnexos4) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexos4[ id=" + id + " ]";
    }
    
}
