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
@Table(name = "mp_prescripcion_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionInsumos.findAll", query = "SELECT m FROM MpPrescripcionInsumos m"),
    @NamedQuery(name = "MpPrescripcionInsumos.findById", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByIdTransaccion", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByIdDireccionamiento", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.idDireccionamiento = :idDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByEstado", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByEstadoJuntaProfesionales", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.estadoJuntaProfesionales = :estadoJuntaProfesionales"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByConsecutivoOrden", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTipoPrestacion", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.tipoPrestacion = :tipoPrestacion"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTipoTecnologia", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCausaSolicitud1", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.causaSolicitud1 = :causaSolicitud1"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCausaSolicitud2", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.causaSolicitud2 = :causaSolicitud2"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCausaSolicitud3", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.causaSolicitud3 = :causaSolicitud3"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCausaSolicitud4", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.causaSolicitud4 = :causaSolicitud4"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCausaSolicitud5", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.causaSolicitud5 = :causaSolicitud5"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoDispositivo", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoDispositivo = :codigoDispositivo"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCantidad", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFrecuenciaUso", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.frecuenciaUso = :frecuenciaUso"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByNombreTecnologiaAvalada", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.nombreTecnologiaAvalada = :nombreTecnologiaAvalada"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByDescripcionCausaS4", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.descripcionCausaS4 = :descripcionCausaS4"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoForma", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoForma = :codigoForma"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCantidadFormulada", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cantidadFormulada = :cantidadFormulada"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByDescripcionCausa4", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.descripcionCausa4 = :descripcionCausa4"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoServicioComplementario", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoServicioComplementario = :codigoServicioComplementario"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByDescripcionServicioComplementario", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.descripcionServicioComplementario = :descripcionServicioComplementario"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByDuracionTratamiento", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.duracionTratamiento = :duracionTratamiento"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTipoTransporte", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.tipoTransporte = :tipoTransporte"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByReqAcom", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.reqAcom = :reqAcom"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTipoDocumentoAcompananteAlbergue", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.tipoDocumentoAcompananteAlbergue = :tipoDocumentoAcompananteAlbergue"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByNumeroDocumentoAcompanante", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.numeroDocumentoAcompanante = :numeroDocumentoAcompanante"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTipoDocumentoAcompanante", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.tipoDocumentoAcompanante = :tipoDocumentoAcompanante"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByParentezcoAcompanante", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.parentezcoAcompanante = :parentezcoAcompanante"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByNombreAlb", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.nombreAlb = :nombreAlb"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoMunicipioOrigenAlb", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoMunicipioOrigenAlb = :codigoMunicipioOrigenAlb"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoMunicipioDestinoAlb", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoMunicipioDestinoAlb = :codigoMunicipioDestinoAlb"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByJustificacionNoPbs", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.justificacionNoPbs = :justificacionNoPbs"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByIndicacionesRecomendaciones", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.indicacionesRecomendaciones = :indicacionesRecomendaciones"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCantidadTotalEntrega", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cantidadTotalEntrega = :cantidadTotalEntrega"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByEntregados", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.entregados = :entregados"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByPendiente", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.pendiente = :pendiente"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFechaDireccionamiento", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.fechaDireccionamiento = :fechaDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFechaMaximaEntrega", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.fechaMaximaEntrega = :fechaMaximaEntrega"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByMaeServiciosComplementariosId", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.maeServiciosComplementariosId = :maeServiciosComplementariosId"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByMaeServiciosComplementariosCodigo", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.maeServiciosComplementariosCodigo = :maeServiciosComplementariosCodigo"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByMaeServiciosComplementariosValor", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.maeServiciosComplementariosValor = :maeServiciosComplementariosValor"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByMaeDispositivosId", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.maeDispositivosId = :maeDispositivosId"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByMaeDispositivosCodigo", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.maeDispositivosCodigo = :maeDispositivosCodigo"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByMaedispositivosValor", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.maedispositivosValor = :maedispositivosValor"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByValorUnitario", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByDireccionado", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.direccionado = :direccionado"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCicloFacturacion", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cicloFacturacion = :cicloFacturacion"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodFacturaIps", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codFacturaIps = :codFacturaIps"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByJustificacionTecJunta", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.justificacionTecJunta = :justificacionTecJunta"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByModalidadJunta", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.modalidadJunta = :modalidadJunta"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByNumActaJunta", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.numActaJunta = :numActaJunta"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByReaccionesAdversasPaciente", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.reaccionesAdversasPaciente = :reaccionesAdversasPaciente"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCantidadDireccionada", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cantidadDireccionada = :cantidadDireccionada"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCantidadMinimaDispensada", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cantidadMinimaDispensada = :cantidadMinimaDispensada"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCantidadPrescrita", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.cantidadPrescrita = :cantidadPrescrita"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoMipresEntregar", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoMipresEntregar = :codigoMipresEntregar"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodigoFormulada", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codigoFormulada = :codigoFormulada"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByEstadoAuditoria", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.estadoAuditoria = :estadoAuditoria"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByConsecutivoJuntaTecnologia", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.consecutivoJuntaTecnologia = :consecutivoJuntaTecnologia"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFechaActaJunta", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.fechaActaJunta = :fechaActaJunta"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByEstadoPrescripcion", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.estadoPrescripcion = :estadoPrescripcion"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByUsoServCosmeticoSuntuario", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.usoServCosmeticoSuntuario = :usoServCosmeticoSuntuario"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByServicioPrestaraColombia", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.servicioPrestaraColombia = :servicioPrestaraColombia"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByServRegistradoAutCompetente", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.servRegistradoAutCompetente = :servRegistradoAutCompetente"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByServCondicionClinDiagPaciente", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.servCondicionClinDiagPaciente = :servCondicionClinDiagPaciente"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByDescartoNoExistePbs", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.descartoNoExistePbs = :descartoNoExistePbs"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByEvidenciaEfiEfecClinica", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.evidenciaEfiEfecClinica = :evidenciaEfiEfecClinica"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByResultSatisPrev", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.resultSatisPrev = :resultSatisPrev"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByRegistroInvima", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.registroInvima = :registroInvima"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodFrecuenciaUso", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codFrecuenciaUso = :codFrecuenciaUso"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByCodPerDurTrat", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.codPerDurTrat = :codPerDurTrat"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByAtendido", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.atendido = :atendido"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTipoTutela", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.tipoTutela = :tipoTutela"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByBanderaAtencion", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.banderaAtencion = :banderaAtencion"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByUsuarioModifica", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTerminalModifica", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFechaHoraModifica", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByUsuarioAtiende", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.usuarioAtiende = :usuarioAtiende"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByTerminalAtiende", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.terminalAtiende = :terminalAtiende"),
    @NamedQuery(name = "MpPrescripcionInsumos.findByFechaHoraAtiende", query = "SELECT m FROM MpPrescripcionInsumos m WHERE m.fechaHoraAtiende = :fechaHoraAtiende")})
public class MpPrescripcionInsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Size(max = 32)
    @Column(name = "id_direccionamiento")
    private String idDireccionamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_junta_profesionales")
    private int estadoJuntaProfesionales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo_orden")
    private int consecutivoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_prestacion")
    private int tipoPrestacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Column(name = "causa_solicitud_1")
    private Integer causaSolicitud1;
    @Column(name = "causa_solicitud_2")
    private Integer causaSolicitud2;
    @Column(name = "causa_solicitud_3")
    private Integer causaSolicitud3;
    @Column(name = "causa_solicitud_4")
    private Integer causaSolicitud4;
    @Column(name = "causa_solicitud_5")
    private Integer causaSolicitud5;
    @Size(max = 45)
    @Column(name = "codigo_dispositivo")
    private String codigoDispositivo;
    @Size(max = 8)
    @Column(name = "cantidad")
    private String cantidad;
    @Size(max = 45)
    @Column(name = "frecuencia_uso")
    private String frecuenciaUso;
    @Size(max = 512)
    @Column(name = "nombre_tecnologia_avalada")
    private String nombreTecnologiaAvalada;
    @Size(max = 45)
    @Column(name = "descripcion_causa_s4")
    private String descripcionCausaS4;
    @Size(max = 16)
    @Column(name = "codigo_forma")
    private String codigoForma;
    @Size(max = 32)
    @Column(name = "cantidad_formulada")
    private String cantidadFormulada;
    @Size(max = 256)
    @Column(name = "descripcion_causa_4")
    private String descripcionCausa4;
    @Column(name = "codigo_servicio_complementario")
    private Integer codigoServicioComplementario;
    @Size(max = 512)
    @Column(name = "descripcion_servicio_complementario")
    private String descripcionServicioComplementario;
    @Column(name = "duracion_tratamiento")
    private Integer duracionTratamiento;
    @Size(max = 32)
    @Column(name = "tipo_transporte")
    private String tipoTransporte;
    @Size(max = 64)
    @Column(name = "req_acom")
    private String reqAcom;
    @Size(max = 64)
    @Column(name = "tipo_documento_acompanante_albergue")
    private String tipoDocumentoAcompananteAlbergue;
    @Size(max = 16)
    @Column(name = "numero_documento_acompanante")
    private String numeroDocumentoAcompanante;
    @Size(max = 8)
    @Column(name = "tipo_documento_acompanante")
    private String tipoDocumentoAcompanante;
    @Size(max = 128)
    @Column(name = "parentezco_acompanante")
    private String parentezcoAcompanante;
    @Size(max = 128)
    @Column(name = "nombre_alb")
    private String nombreAlb;
    @Size(max = 32)
    @Column(name = "codigo_municipio_origen_alb")
    private String codigoMunicipioOrigenAlb;
    @Size(max = 32)
    @Column(name = "codigo_municipio_destino_alb")
    private String codigoMunicipioDestinoAlb;
    @Size(max = 4096)
    @Column(name = "justificacion_no_pbs")
    private String justificacionNoPbs;
    @Size(max = 2048)
    @Column(name = "indicaciones_recomendaciones")
    private String indicacionesRecomendaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad_total_entrega")
    private BigDecimal cantidadTotalEntrega;
    @Column(name = "entregados")
    private BigDecimal entregados;
    @Column(name = "pendiente")
    private BigDecimal pendiente;
    @Column(name = "fecha_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamiento;
    @Column(name = "fecha_maxima_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaMaximaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_servicios_complementarios_id")
    private int maeServiciosComplementariosId;
    @Size(max = 8)
    @Column(name = "mae_servicios_complementarios_codigo")
    private String maeServiciosComplementariosCodigo;
    @Size(max = 128)
    @Column(name = "mae_servicios_complementarios_valor")
    private String maeServiciosComplementariosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_dispositivos_id")
    private int maeDispositivosId;
    @Size(max = 8)
    @Column(name = "mae_dispositivos_codigo")
    private String maeDispositivosCodigo;
    @Size(max = 128)
    @Column(name = "mae_dispositivos_Valor")
    private String maedispositivosValor;
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    @Size(max = 128)
    @Column(name = "direccionado")
    private String direccionado;
    @Column(name = "ciclo_facturacion")
    private Integer cicloFacturacion;
    @Column(name = "cod_factura_ips")
    private Integer codFacturaIps;
    @Size(max = 2048)
    @Column(name = "justificacion_tec_junta")
    private String justificacionTecJunta;
    @Size(max = 48)
    @Column(name = "modalidad_junta")
    private String modalidadJunta;
    @Size(max = 48)
    @Column(name = "num_acta_junta")
    private String numActaJunta;
    @Column(name = "reacciones_adversas_paciente")
    private Boolean reaccionesAdversasPaciente;
    @Column(name = "cantidad_direccionada")
    private Integer cantidadDireccionada;
    @Column(name = "cantidad_minima_dispensada")
    private Integer cantidadMinimaDispensada;
    @Column(name = "cantidad_prescrita")
    private Integer cantidadPrescrita;
    @Size(max = 48)
    @Column(name = "codigo_mipres_entregar")
    private String codigoMipresEntregar;
    @Size(max = 48)
    @Column(name = "codigo_formulada")
    private String codigoFormulada;
    @Column(name = "estado_auditoria")
    private Integer estadoAuditoria;
    @Column(name = "consecutivo_junta_tecnologia")
    private Integer consecutivoJuntaTecnologia;
    @Column(name = "fecha_acta_junta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActaJunta;
    @Column(name = "estado_prescripcion")
    private Integer estadoPrescripcion;
    @Column(name = "uso_serv_cosmetico_suntuario")
    private Boolean usoServCosmeticoSuntuario;
    @Column(name = "servicio_prestara_colombia")
    private Boolean servicioPrestaraColombia;
    @Column(name = "serv_registrado_aut_competente")
    private Boolean servRegistradoAutCompetente;
    @Column(name = "serv_condicion_clin_diag_paciente")
    private Boolean servCondicionClinDiagPaciente;
    @Column(name = "descarto_no_existe_pbs")
    private Boolean descartoNoExistePbs;
    @Column(name = "evidencia_efi_efec_clinica")
    private Boolean evidenciaEfiEfecClinica;
    @Column(name = "result_satis_prev")
    private Boolean resultSatisPrev;
    @Column(name = "registro_invima")
    private Boolean registroInvima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_frecuencia_uso")
    private int codFrecuenciaUso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_per_dur_trat")
    private int codPerDurTrat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "atendido")
    private boolean atendido;
    @Column(name = "tipo_tutela")
    private Integer tipoTutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bandera_atencion")
    private boolean banderaAtencion;
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
    @Column(name = "usuario_atiende")
    private String usuarioAtiende;
    @Size(max = 16)
    @Column(name = "terminal_atiende")
    private String terminalAtiende;
    @Column(name = "fecha_hora_atiende")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAtiende;
    @OneToMany(mappedBy = "mpPrescripcionInsumosId", fetch = FetchType.LAZY)
    private List<MpProgramadaEntregas> mpProgramadaEntregasList;
    @OneToMany(mappedBy = "mpPrescripcionInsumosId", fetch = FetchType.LAZY)
    private List<MpNoDireccionados> mpNoDireccionadosList;
    @OneToMany(mappedBy = "mpPrescripcionInsumoId", fetch = FetchType.LAZY)
    private List<MpPrescripcionItemAuditoria> mpPrescripcionItemAuditoriaList;
    @OneToMany(mappedBy = "mpPrescripcionInsumoId", fetch = FetchType.LAZY)
    private List<MpNotificacionesHistoricos> mpNotificacionesHistoricosList;
    @JoinColumn(name = "mp_prescripcion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionId;
    @OneToMany(mappedBy = "mpPrescripcionInsumosId", fetch = FetchType.LAZY)
    private List<MpPrescripcionProgramadas> mpPrescripcionProgramadasList;
    @OneToMany(mappedBy = "mpPrescripcionInsumosId", fetch = FetchType.LAZY)
    private List<MpCotizaciones> mpCotizacionesList;
    @OneToMany(mappedBy = "mpPrescripcionInsumosId", fetch = FetchType.LAZY)
    private List<MpDireccionamientos> mpDireccionamientosList;
    @OneToMany(mappedBy = "mpPrescripicionInsumosId", fetch = FetchType.LAZY)
    private List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList;

    public MpPrescripcionInsumos() {
    }

    public MpPrescripcionInsumos(Integer id) {
        this.id = id;
    }

    public MpPrescripcionInsumos(Integer id, int estado, int estadoJuntaProfesionales, int consecutivoOrden, int tipoPrestacion, int tipoTecnologia, int maeServiciosComplementariosId, int maeDispositivosId, int codFrecuenciaUso, int codPerDurTrat, boolean atendido, boolean banderaAtencion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
        this.consecutivoOrden = consecutivoOrden;
        this.tipoPrestacion = tipoPrestacion;
        this.tipoTecnologia = tipoTecnologia;
        this.maeServiciosComplementariosId = maeServiciosComplementariosId;
        this.maeDispositivosId = maeDispositivosId;
        this.codFrecuenciaUso = codFrecuenciaUso;
        this.codPerDurTrat = codPerDurTrat;
        this.atendido = atendido;
        this.banderaAtencion = banderaAtencion;
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

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(String idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoJuntaProfesionales() {
        return estadoJuntaProfesionales;
    }

    public void setEstadoJuntaProfesionales(int estadoJuntaProfesionales) {
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
    }

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public int getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(int tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getCausaSolicitud1() {
        return causaSolicitud1;
    }

    public void setCausaSolicitud1(Integer causaSolicitud1) {
        this.causaSolicitud1 = causaSolicitud1;
    }

    public Integer getCausaSolicitud2() {
        return causaSolicitud2;
    }

    public void setCausaSolicitud2(Integer causaSolicitud2) {
        this.causaSolicitud2 = causaSolicitud2;
    }

    public Integer getCausaSolicitud3() {
        return causaSolicitud3;
    }

    public void setCausaSolicitud3(Integer causaSolicitud3) {
        this.causaSolicitud3 = causaSolicitud3;
    }

    public Integer getCausaSolicitud4() {
        return causaSolicitud4;
    }

    public void setCausaSolicitud4(Integer causaSolicitud4) {
        this.causaSolicitud4 = causaSolicitud4;
    }

    public Integer getCausaSolicitud5() {
        return causaSolicitud5;
    }

    public void setCausaSolicitud5(Integer causaSolicitud5) {
        this.causaSolicitud5 = causaSolicitud5;
    }

    public String getCodigoDispositivo() {
        return codigoDispositivo;
    }

    public void setCodigoDispositivo(String codigoDispositivo) {
        this.codigoDispositivo = codigoDispositivo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFrecuenciaUso() {
        return frecuenciaUso;
    }

    public void setFrecuenciaUso(String frecuenciaUso) {
        this.frecuenciaUso = frecuenciaUso;
    }

    public String getNombreTecnologiaAvalada() {
        return nombreTecnologiaAvalada;
    }

    public void setNombreTecnologiaAvalada(String nombreTecnologiaAvalada) {
        this.nombreTecnologiaAvalada = nombreTecnologiaAvalada;
    }

    public String getDescripcionCausaS4() {
        return descripcionCausaS4;
    }

    public void setDescripcionCausaS4(String descripcionCausaS4) {
        this.descripcionCausaS4 = descripcionCausaS4;
    }

    public String getCodigoForma() {
        return codigoForma;
    }

    public void setCodigoForma(String codigoForma) {
        this.codigoForma = codigoForma;
    }

    public String getCantidadFormulada() {
        return cantidadFormulada;
    }

    public void setCantidadFormulada(String cantidadFormulada) {
        this.cantidadFormulada = cantidadFormulada;
    }

    public String getDescripcionCausa4() {
        return descripcionCausa4;
    }

    public void setDescripcionCausa4(String descripcionCausa4) {
        this.descripcionCausa4 = descripcionCausa4;
    }

    public Integer getCodigoServicioComplementario() {
        return codigoServicioComplementario;
    }

    public void setCodigoServicioComplementario(Integer codigoServicioComplementario) {
        this.codigoServicioComplementario = codigoServicioComplementario;
    }

    public String getDescripcionServicioComplementario() {
        return descripcionServicioComplementario;
    }

    public void setDescripcionServicioComplementario(String descripcionServicioComplementario) {
        this.descripcionServicioComplementario = descripcionServicioComplementario;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getReqAcom() {
        return reqAcom;
    }

    public void setReqAcom(String reqAcom) {
        this.reqAcom = reqAcom;
    }

    public String getTipoDocumentoAcompananteAlbergue() {
        return tipoDocumentoAcompananteAlbergue;
    }

    public void setTipoDocumentoAcompananteAlbergue(String tipoDocumentoAcompananteAlbergue) {
        this.tipoDocumentoAcompananteAlbergue = tipoDocumentoAcompananteAlbergue;
    }

    public String getNumeroDocumentoAcompanante() {
        return numeroDocumentoAcompanante;
    }

    public void setNumeroDocumentoAcompanante(String numeroDocumentoAcompanante) {
        this.numeroDocumentoAcompanante = numeroDocumentoAcompanante;
    }

    public String getTipoDocumentoAcompanante() {
        return tipoDocumentoAcompanante;
    }

    public void setTipoDocumentoAcompanante(String tipoDocumentoAcompanante) {
        this.tipoDocumentoAcompanante = tipoDocumentoAcompanante;
    }

    public String getParentezcoAcompanante() {
        return parentezcoAcompanante;
    }

    public void setParentezcoAcompanante(String parentezcoAcompanante) {
        this.parentezcoAcompanante = parentezcoAcompanante;
    }

    public String getNombreAlb() {
        return nombreAlb;
    }

    public void setNombreAlb(String nombreAlb) {
        this.nombreAlb = nombreAlb;
    }

    public String getCodigoMunicipioOrigenAlb() {
        return codigoMunicipioOrigenAlb;
    }

    public void setCodigoMunicipioOrigenAlb(String codigoMunicipioOrigenAlb) {
        this.codigoMunicipioOrigenAlb = codigoMunicipioOrigenAlb;
    }

    public String getCodigoMunicipioDestinoAlb() {
        return codigoMunicipioDestinoAlb;
    }

    public void setCodigoMunicipioDestinoAlb(String codigoMunicipioDestinoAlb) {
        this.codigoMunicipioDestinoAlb = codigoMunicipioDestinoAlb;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public String getIndicacionesRecomendaciones() {
        return indicacionesRecomendaciones;
    }

    public void setIndicacionesRecomendaciones(String indicacionesRecomendaciones) {
        this.indicacionesRecomendaciones = indicacionesRecomendaciones;
    }

    public BigDecimal getCantidadTotalEntrega() {
        return cantidadTotalEntrega;
    }

    public void setCantidadTotalEntrega(BigDecimal cantidadTotalEntrega) {
        this.cantidadTotalEntrega = cantidadTotalEntrega;
    }

    public BigDecimal getEntregados() {
        return entregados;
    }

    public void setEntregados(BigDecimal entregados) {
        this.entregados = entregados;
    }

    public BigDecimal getPendiente() {
        return pendiente;
    }

    public void setPendiente(BigDecimal pendiente) {
        this.pendiente = pendiente;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaximaEntrega() {
        return fechaMaximaEntrega;
    }

    public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
        this.fechaMaximaEntrega = fechaMaximaEntrega;
    }

    public int getMaeServiciosComplementariosId() {
        return maeServiciosComplementariosId;
    }

    public void setMaeServiciosComplementariosId(int maeServiciosComplementariosId) {
        this.maeServiciosComplementariosId = maeServiciosComplementariosId;
    }

    public String getMaeServiciosComplementariosCodigo() {
        return maeServiciosComplementariosCodigo;
    }

    public void setMaeServiciosComplementariosCodigo(String maeServiciosComplementariosCodigo) {
        this.maeServiciosComplementariosCodigo = maeServiciosComplementariosCodigo;
    }

    public String getMaeServiciosComplementariosValor() {
        return maeServiciosComplementariosValor;
    }

    public void setMaeServiciosComplementariosValor(String maeServiciosComplementariosValor) {
        this.maeServiciosComplementariosValor = maeServiciosComplementariosValor;
    }

    public int getMaeDispositivosId() {
        return maeDispositivosId;
    }

    public void setMaeDispositivosId(int maeDispositivosId) {
        this.maeDispositivosId = maeDispositivosId;
    }

    public String getMaeDispositivosCodigo() {
        return maeDispositivosCodigo;
    }

    public void setMaeDispositivosCodigo(String maeDispositivosCodigo) {
        this.maeDispositivosCodigo = maeDispositivosCodigo;
    }

    public String getMaedispositivosValor() {
        return maedispositivosValor;
    }

    public void setMaedispositivosValor(String maedispositivosValor) {
        this.maedispositivosValor = maedispositivosValor;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDireccionado() {
        return direccionado;
    }

    public void setDireccionado(String direccionado) {
        this.direccionado = direccionado;
    }

    public Integer getCicloFacturacion() {
        return cicloFacturacion;
    }

    public void setCicloFacturacion(Integer cicloFacturacion) {
        this.cicloFacturacion = cicloFacturacion;
    }

    public Integer getCodFacturaIps() {
        return codFacturaIps;
    }

    public void setCodFacturaIps(Integer codFacturaIps) {
        this.codFacturaIps = codFacturaIps;
    }

    public String getJustificacionTecJunta() {
        return justificacionTecJunta;
    }

    public void setJustificacionTecJunta(String justificacionTecJunta) {
        this.justificacionTecJunta = justificacionTecJunta;
    }

    public String getModalidadJunta() {
        return modalidadJunta;
    }

    public void setModalidadJunta(String modalidadJunta) {
        this.modalidadJunta = modalidadJunta;
    }

    public String getNumActaJunta() {
        return numActaJunta;
    }

    public void setNumActaJunta(String numActaJunta) {
        this.numActaJunta = numActaJunta;
    }

    public Boolean getReaccionesAdversasPaciente() {
        return reaccionesAdversasPaciente;
    }

    public void setReaccionesAdversasPaciente(Boolean reaccionesAdversasPaciente) {
        this.reaccionesAdversasPaciente = reaccionesAdversasPaciente;
    }

    public Integer getCantidadDireccionada() {
        return cantidadDireccionada;
    }

    public void setCantidadDireccionada(Integer cantidadDireccionada) {
        this.cantidadDireccionada = cantidadDireccionada;
    }

    public Integer getCantidadMinimaDispensada() {
        return cantidadMinimaDispensada;
    }

    public void setCantidadMinimaDispensada(Integer cantidadMinimaDispensada) {
        this.cantidadMinimaDispensada = cantidadMinimaDispensada;
    }

    public Integer getCantidadPrescrita() {
        return cantidadPrescrita;
    }

    public void setCantidadPrescrita(Integer cantidadPrescrita) {
        this.cantidadPrescrita = cantidadPrescrita;
    }

    public String getCodigoMipresEntregar() {
        return codigoMipresEntregar;
    }

    public void setCodigoMipresEntregar(String codigoMipresEntregar) {
        this.codigoMipresEntregar = codigoMipresEntregar;
    }

    public String getCodigoFormulada() {
        return codigoFormulada;
    }

    public void setCodigoFormulada(String codigoFormulada) {
        this.codigoFormulada = codigoFormulada;
    }

    public Integer getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Integer estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Integer getConsecutivoJuntaTecnologia() {
        return consecutivoJuntaTecnologia;
    }

    public void setConsecutivoJuntaTecnologia(Integer consecutivoJuntaTecnologia) {
        this.consecutivoJuntaTecnologia = consecutivoJuntaTecnologia;
    }

    public Date getFechaActaJunta() {
        return fechaActaJunta;
    }

    public void setFechaActaJunta(Date fechaActaJunta) {
        this.fechaActaJunta = fechaActaJunta;
    }

    public Integer getEstadoPrescripcion() {
        return estadoPrescripcion;
    }

    public void setEstadoPrescripcion(Integer estadoPrescripcion) {
        this.estadoPrescripcion = estadoPrescripcion;
    }

    public Boolean getUsoServCosmeticoSuntuario() {
        return usoServCosmeticoSuntuario;
    }

    public void setUsoServCosmeticoSuntuario(Boolean usoServCosmeticoSuntuario) {
        this.usoServCosmeticoSuntuario = usoServCosmeticoSuntuario;
    }

    public Boolean getServicioPrestaraColombia() {
        return servicioPrestaraColombia;
    }

    public void setServicioPrestaraColombia(Boolean servicioPrestaraColombia) {
        this.servicioPrestaraColombia = servicioPrestaraColombia;
    }

    public Boolean getServRegistradoAutCompetente() {
        return servRegistradoAutCompetente;
    }

    public void setServRegistradoAutCompetente(Boolean servRegistradoAutCompetente) {
        this.servRegistradoAutCompetente = servRegistradoAutCompetente;
    }

    public Boolean getServCondicionClinDiagPaciente() {
        return servCondicionClinDiagPaciente;
    }

    public void setServCondicionClinDiagPaciente(Boolean servCondicionClinDiagPaciente) {
        this.servCondicionClinDiagPaciente = servCondicionClinDiagPaciente;
    }

    public Boolean getDescartoNoExistePbs() {
        return descartoNoExistePbs;
    }

    public void setDescartoNoExistePbs(Boolean descartoNoExistePbs) {
        this.descartoNoExistePbs = descartoNoExistePbs;
    }

    public Boolean getEvidenciaEfiEfecClinica() {
        return evidenciaEfiEfecClinica;
    }

    public void setEvidenciaEfiEfecClinica(Boolean evidenciaEfiEfecClinica) {
        this.evidenciaEfiEfecClinica = evidenciaEfiEfecClinica;
    }

    public Boolean getResultSatisPrev() {
        return resultSatisPrev;
    }

    public void setResultSatisPrev(Boolean resultSatisPrev) {
        this.resultSatisPrev = resultSatisPrev;
    }

    public Boolean getRegistroInvima() {
        return registroInvima;
    }

    public void setRegistroInvima(Boolean registroInvima) {
        this.registroInvima = registroInvima;
    }

    public int getCodFrecuenciaUso() {
        return codFrecuenciaUso;
    }

    public void setCodFrecuenciaUso(int codFrecuenciaUso) {
        this.codFrecuenciaUso = codFrecuenciaUso;
    }

    public int getCodPerDurTrat() {
        return codPerDurTrat;
    }

    public void setCodPerDurTrat(int codPerDurTrat) {
        this.codPerDurTrat = codPerDurTrat;
    }

    public boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public Integer getTipoTutela() {
        return tipoTutela;
    }

    public void setTipoTutela(Integer tipoTutela) {
        this.tipoTutela = tipoTutela;
    }

    public boolean getBanderaAtencion() {
        return banderaAtencion;
    }

    public void setBanderaAtencion(boolean banderaAtencion) {
        this.banderaAtencion = banderaAtencion;
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

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public String getTerminalAtiende() {
        return terminalAtiende;
    }

    public void setTerminalAtiende(String terminalAtiende) {
        this.terminalAtiende = terminalAtiende;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    @XmlTransient
    public List<MpProgramadaEntregas> getMpProgramadaEntregasList() {
        return mpProgramadaEntregasList;
    }

    public void setMpProgramadaEntregasList(List<MpProgramadaEntregas> mpProgramadaEntregasList) {
        this.mpProgramadaEntregasList = mpProgramadaEntregasList;
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

    public MpPrescripciones getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripciones mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
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
        if (!(object instanceof MpPrescripcionInsumos)) {
            return false;
        }
        MpPrescripcionInsumos other = (MpPrescripcionInsumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionInsumos[ id=" + id + " ]";
    }
    
}
