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
@Table(name = "mp_prescripcion_tecnologias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionTecnologias.findAll", query = "SELECT m FROM MpPrescripcionTecnologias m"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findById", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByIdTransaccion", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByIdDireccionamiento", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.idDireccionamiento = :idDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByEstado", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByEstadoJuntaProfesionales", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.estadoJuntaProfesionales = :estadoJuntaProfesionales"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByConsecutivoOrden", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTipoTecnologia", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTipoPrestacion", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.tipoPrestacion = :tipoPrestacion"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud2", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud2 = :causaSolicitud2"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud3", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud3 = :causaSolicitud3"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud4", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud4 = :causaSolicitud4"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud5", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud5 = :causaSolicitud5"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud6", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud6 = :causaSolicitud6"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud7", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud7 = :causaSolicitud7"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud11", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud11 = :causaSolicitud11"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCausaSolicitud12", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.causaSolicitud12 = :causaSolicitud12"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByRazonCausaSolicitud51", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.razonCausaSolicitud51 = :razonCausaSolicitud51"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByDescripcionRazon51", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.descripcionRazon51 = :descripcionRazon51"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCodigoRazonCausa52", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.codigoRazonCausa52 = :codigoRazonCausa52"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByDescripcionRazon52", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.descripcionRazon52 = :descripcionRazon52"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByMaTecnologiaId", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByMaTecnologiaCodigo", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByMaTecnologiaValor", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadFormulada", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadFormulada = :cantidadFormulada"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFrecuenciaDeUso", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.frecuenciaDeUso = :frecuenciaDeUso"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCodigoUnidadTiempoFrecuenciaUso", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.codigoUnidadTiempoFrecuenciaUso = :codigoUnidadTiempoFrecuenciaUso"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadDuracionTratamiento", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadDuracionTratamiento = :cantidadDuracionTratamiento"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadTotal", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadTotal = :cantidadTotal"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCodigoPeriodoDuracionTratamiento", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.codigoPeriodoDuracionTratamiento = :codigoPeriodoDuracionTratamiento"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByJustificacionNoPbs", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.justificacionNoPbs = :justificacionNoPbs"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByIndicacionesPaciente", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.indicacionesPaciente = :indicacionesPaciente"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadTotalEntrega", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadTotalEntrega = :cantidadTotalEntrega"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByEntregados", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.entregados = :entregados"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByPendientes", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.pendientes = :pendientes"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFechaDireccionamiento", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.fechaDireccionamiento = :fechaDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFechaMaximaEntrega", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.fechaMaximaEntrega = :fechaMaximaEntrega"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByValorUnitario", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByDireccionado", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.direccionado = :direccionado"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCicloFacturacion", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cicloFacturacion = :cicloFacturacion"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCodFacturaIps", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.codFacturaIps = :codFacturaIps"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByJustificacionTecJunta", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.justificacionTecJunta = :justificacionTecJunta"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByModJunta", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.modJunta = :modJunta"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByNumActaJunta", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.numActaJunta = :numActaJunta"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByReaccionesAdversasPaciente", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.reaccionesAdversasPaciente = :reaccionesAdversasPaciente"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadDireccionada", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadDireccionada = :cantidadDireccionada"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadMinimaDispensada", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadMinimaDispensada = :cantidadMinimaDispensada"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCantidadPrescrita", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.cantidadPrescrita = :cantidadPrescrita"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCodigoMipresEntregar", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.codigoMipresEntregar = :codigoMipresEntregar"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCodFormulada", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.codFormulada = :codFormulada"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByEstadoAuditoria", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.estadoAuditoria = :estadoAuditoria"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByConsecutivoJuntaTecnologia", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.consecutivoJuntaTecnologia = :consecutivoJuntaTecnologia"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFechaActaJunta", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.fechaActaJunta = :fechaActaJunta"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByEstadoPrescripcion", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.estadoPrescripcion = :estadoPrescripcion"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByCombinacionCups", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.combinacionCups = :combinacionCups"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTieneCups", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.tieneCups = :tieneCups"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByDescProcedPbsCargoUpc", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.descProcedPbsCargoUpc = :descProcedPbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByDescartoEvidenciaEfiEfecClinica", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.descartoEvidenciaEfiEfecClinica = :descartoEvidenciaEfiEfecClinica"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByDescartoNoExistePbs", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.descartoNoExistePbs = :descartoNoExistePbs"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByEvidenciaEfiEfecClinica", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.evidenciaEfiEfecClinica = :evidenciaEfiEfecClinica"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByExistePbsUpc", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.existePbsUpc = :existePbsUpc"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByObsReaccPbsCargoUpc", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.obsReaccPbsCargoUpc = :obsReaccPbsCargoUpc"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByPbsUtilizado", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.pbsUtilizado = :pbsUtilizado"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByExistentePbsUpc", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.existentePbsUpc = :existentePbsUpc"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByListaNoUsoSanitarioUnirs", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.listaNoUsoSanitarioUnirs = :listaNoUsoSanitarioUnirs"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFaseExperimental", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.faseExperimental = :faseExperimental"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByRegAprobAutClin", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.regAprobAutClin = :regAprobAutClin"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFinanciadoPbsUpc", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.financiadoPbsUpc = :financiadoPbsUpc"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByUtilizoProcedExistentePbsUpc", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.utilizoProcedExistentePbsUpc = :utilizoProcedExistentePbsUpc"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByResultSatisPrev", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.resultSatisPrev = :resultSatisPrev"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByRegistroInvima", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.registroInvima = :registroInvima"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByAtendido", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.atendido = :atendido"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTipoTutela", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.tipoTutela = :tipoTutela"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByBanderaAtencion", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.banderaAtencion = :banderaAtencion"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByUsuarioModifica", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTerminalModifica", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFechaHoraModifica", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByUsuarioAtiende", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.usuarioAtiende = :usuarioAtiende"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByTerminalAtiende", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.terminalAtiende = :terminalAtiende"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByFechaHoraAtiende", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.fechaHoraAtiende = :fechaHoraAtiende"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByProcePbsDescartado", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.procePbsDescartado = :procePbsDescartado"),
    @NamedQuery(name = "MpPrescripcionTecnologias.findByProcRealizaraCol", query = "SELECT m FROM MpPrescripcionTecnologias m WHERE m.procRealizaraCol = :procRealizaraCol")})
public class MpPrescripcionTecnologias implements Serializable {

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
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_prestacion")
    private int tipoPrestacion;
    @Column(name = "causa_solicitud_2")
    private Integer causaSolicitud2;
    @Column(name = "causa_solicitud_3")
    private Integer causaSolicitud3;
    @Column(name = "causa_solicitud_4")
    private Integer causaSolicitud4;
    @Column(name = "causa_solicitud_5")
    private Integer causaSolicitud5;
    @Column(name = "causa_solicitud_6")
    private Integer causaSolicitud6;
    @Column(name = "causa_solicitud_7")
    private Integer causaSolicitud7;
    @Column(name = "causa_solicitud_11")
    private Integer causaSolicitud11;
    @Column(name = "causa_solicitud_12")
    private Integer causaSolicitud12;
    @Column(name = "razon_causa_solicitud_51")
    private Integer razonCausaSolicitud51;
    @Size(max = 256)
    @Column(name = "descripcion_razon_51")
    private String descripcionRazon51;
    @Column(name = "codigo_razon_causa_52")
    private Integer codigoRazonCausa52;
    @Size(max = 256)
    @Column(name = "descripcion_razon_52")
    private String descripcionRazon52;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "cantidad_formulada")
    private Integer cantidadFormulada;
    @Column(name = "frecuencia_de_uso")
    private Integer frecuenciaDeUso;
    @Column(name = "codigo_unidad_tiempo_frecuencia_uso")
    private Integer codigoUnidadTiempoFrecuenciaUso;
    @Column(name = "cantidad_duracion_tratamiento")
    private Integer cantidadDuracionTratamiento;
    @Column(name = "cantidad_total")
    private Integer cantidadTotal;
    @Column(name = "codigo_periodo_duracion_tratamiento")
    private Integer codigoPeriodoDuracionTratamiento;
    @Size(max = 4096)
    @Column(name = "justificacion_no_pbs")
    private String justificacionNoPbs;
    @Size(max = 2048)
    @Column(name = "indicaciones_paciente")
    private String indicacionesPaciente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad_total_entrega")
    private BigDecimal cantidadTotalEntrega;
    @Column(name = "entregados")
    private BigDecimal entregados;
    @Column(name = "pendientes")
    private BigDecimal pendientes;
    @Column(name = "fecha_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamiento;
    @Column(name = "fecha_maxima_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaMaximaEntrega;
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
    @Size(max = 128)
    @Column(name = "mod_junta")
    private String modJunta;
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
    @Column(name = "cod_formulada")
    private String codFormulada;
    @Column(name = "estado_auditoria")
    private Integer estadoAuditoria;
    @Column(name = "consecutivo_junta_tecnologia")
    private Integer consecutivoJuntaTecnologia;
    @Column(name = "fecha_acta_junta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActaJunta;
    @Column(name = "estado_prescripcion")
    private Integer estadoPrescripcion;
    @Column(name = "combinacion_cups")
    private Boolean combinacionCups;
    @Column(name = "tiene_cups")
    private Boolean tieneCups;
    @Column(name = "desc_proced_pbs_cargo_upc")
    private Boolean descProcedPbsCargoUpc;
    @Column(name = "descarto_evidencia_efi_efec_clinica")
    private Boolean descartoEvidenciaEfiEfecClinica;
    @Column(name = "descarto_no_existe_pbs")
    private Boolean descartoNoExistePbs;
    @Column(name = "evidencia_efi_efec_clinica")
    private Boolean evidenciaEfiEfecClinica;
    @Column(name = "existe_pbs_upc")
    private Boolean existePbsUpc;
    @Column(name = "obs_reacc_pbs_cargo_upc")
    private Boolean obsReaccPbsCargoUpc;
    @Column(name = "pbs_utilizado")
    private Boolean pbsUtilizado;
    @Column(name = "existente_pbs_upc")
    private Boolean existentePbsUpc;
    @Column(name = "lista_no_uso_sanitario_unirs")
    private Boolean listaNoUsoSanitarioUnirs;
    @Column(name = "fase_experimental")
    private Boolean faseExperimental;
    @Column(name = "reg_aprob_aut_clin")
    private Boolean regAprobAutClin;
    @Column(name = "financiado_pbs_upc")
    private Boolean financiadoPbsUpc;
    @Column(name = "utilizo_proced_existente_pbs_upc")
    private Boolean utilizoProcedExistentePbsUpc;
    @Column(name = "result_satis_prev")
    private Boolean resultSatisPrev;
    @Column(name = "registro_invima")
    private Boolean registroInvima;
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
    @Column(name = "proce_pbs_descartado")
    private Boolean procePbsDescartado;
    @Column(name = "proc_realizara_col")
    private Boolean procRealizaraCol;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiasId", fetch = FetchType.LAZY)
    private List<MpProgramadaEntregas> mpProgramadaEntregasList;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiasId", fetch = FetchType.LAZY)
    private List<MpNoDireccionados> mpNoDireccionadosList;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiaId", fetch = FetchType.LAZY)
    private List<MpPrescripcionItemAuditoria> mpPrescripcionItemAuditoriaList;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiaId", fetch = FetchType.LAZY)
    private List<MpNotificacionesHistoricos> mpNotificacionesHistoricosList;
    @JoinColumn(name = "mp_prescripcion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionId;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiasId", fetch = FetchType.LAZY)
    private List<MpPrescripcionProgramadas> mpPrescripcionProgramadasList;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiasId", fetch = FetchType.LAZY)
    private List<MpCotizaciones> mpCotizacionesList;
    @OneToMany(mappedBy = "mpPrescripcionTecnologiasId", fetch = FetchType.LAZY)
    private List<MpDireccionamientos> mpDireccionamientosList;
    @OneToMany(mappedBy = "mpPrescripicionTecnologiasId", fetch = FetchType.LAZY)
    private List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList;

    public MpPrescripcionTecnologias() {
    }

    public MpPrescripcionTecnologias(Integer id) {
        this.id = id;
    }

    public MpPrescripcionTecnologias(Integer id, int estado, int estadoJuntaProfesionales, int consecutivoOrden, int tipoTecnologia, int tipoPrestacion, boolean atendido, boolean banderaAtencion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
        this.consecutivoOrden = consecutivoOrden;
        this.tipoTecnologia = tipoTecnologia;
        this.tipoPrestacion = tipoPrestacion;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(int tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
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

    public Integer getCausaSolicitud6() {
        return causaSolicitud6;
    }

    public void setCausaSolicitud6(Integer causaSolicitud6) {
        this.causaSolicitud6 = causaSolicitud6;
    }

    public Integer getCausaSolicitud7() {
        return causaSolicitud7;
    }

    public void setCausaSolicitud7(Integer causaSolicitud7) {
        this.causaSolicitud7 = causaSolicitud7;
    }

    public Integer getCausaSolicitud11() {
        return causaSolicitud11;
    }

    public void setCausaSolicitud11(Integer causaSolicitud11) {
        this.causaSolicitud11 = causaSolicitud11;
    }

    public Integer getCausaSolicitud12() {
        return causaSolicitud12;
    }

    public void setCausaSolicitud12(Integer causaSolicitud12) {
        this.causaSolicitud12 = causaSolicitud12;
    }

    public Integer getRazonCausaSolicitud51() {
        return razonCausaSolicitud51;
    }

    public void setRazonCausaSolicitud51(Integer razonCausaSolicitud51) {
        this.razonCausaSolicitud51 = razonCausaSolicitud51;
    }

    public String getDescripcionRazon51() {
        return descripcionRazon51;
    }

    public void setDescripcionRazon51(String descripcionRazon51) {
        this.descripcionRazon51 = descripcionRazon51;
    }

    public Integer getCodigoRazonCausa52() {
        return codigoRazonCausa52;
    }

    public void setCodigoRazonCausa52(Integer codigoRazonCausa52) {
        this.codigoRazonCausa52 = codigoRazonCausa52;
    }

    public String getDescripcionRazon52() {
        return descripcionRazon52;
    }

    public void setDescripcionRazon52(String descripcionRazon52) {
        this.descripcionRazon52 = descripcionRazon52;
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

    public Integer getCantidadFormulada() {
        return cantidadFormulada;
    }

    public void setCantidadFormulada(Integer cantidadFormulada) {
        this.cantidadFormulada = cantidadFormulada;
    }

    public Integer getFrecuenciaDeUso() {
        return frecuenciaDeUso;
    }

    public void setFrecuenciaDeUso(Integer frecuenciaDeUso) {
        this.frecuenciaDeUso = frecuenciaDeUso;
    }

    public Integer getCodigoUnidadTiempoFrecuenciaUso() {
        return codigoUnidadTiempoFrecuenciaUso;
    }

    public void setCodigoUnidadTiempoFrecuenciaUso(Integer codigoUnidadTiempoFrecuenciaUso) {
        this.codigoUnidadTiempoFrecuenciaUso = codigoUnidadTiempoFrecuenciaUso;
    }

    public Integer getCantidadDuracionTratamiento() {
        return cantidadDuracionTratamiento;
    }

    public void setCantidadDuracionTratamiento(Integer cantidadDuracionTratamiento) {
        this.cantidadDuracionTratamiento = cantidadDuracionTratamiento;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getCodigoPeriodoDuracionTratamiento() {
        return codigoPeriodoDuracionTratamiento;
    }

    public void setCodigoPeriodoDuracionTratamiento(Integer codigoPeriodoDuracionTratamiento) {
        this.codigoPeriodoDuracionTratamiento = codigoPeriodoDuracionTratamiento;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public String getIndicacionesPaciente() {
        return indicacionesPaciente;
    }

    public void setIndicacionesPaciente(String indicacionesPaciente) {
        this.indicacionesPaciente = indicacionesPaciente;
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

    public BigDecimal getPendientes() {
        return pendientes;
    }

    public void setPendientes(BigDecimal pendientes) {
        this.pendientes = pendientes;
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

    public String getModJunta() {
        return modJunta;
    }

    public void setModJunta(String modJunta) {
        this.modJunta = modJunta;
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

    public String getCodFormulada() {
        return codFormulada;
    }

    public void setCodFormulada(String codFormulada) {
        this.codFormulada = codFormulada;
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

    public Boolean getCombinacionCups() {
        return combinacionCups;
    }

    public void setCombinacionCups(Boolean combinacionCups) {
        this.combinacionCups = combinacionCups;
    }

    public Boolean getTieneCups() {
        return tieneCups;
    }

    public void setTieneCups(Boolean tieneCups) {
        this.tieneCups = tieneCups;
    }

    public Boolean getDescProcedPbsCargoUpc() {
        return descProcedPbsCargoUpc;
    }

    public void setDescProcedPbsCargoUpc(Boolean descProcedPbsCargoUpc) {
        this.descProcedPbsCargoUpc = descProcedPbsCargoUpc;
    }

    public Boolean getDescartoEvidenciaEfiEfecClinica() {
        return descartoEvidenciaEfiEfecClinica;
    }

    public void setDescartoEvidenciaEfiEfecClinica(Boolean descartoEvidenciaEfiEfecClinica) {
        this.descartoEvidenciaEfiEfecClinica = descartoEvidenciaEfiEfecClinica;
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

    public Boolean getExistePbsUpc() {
        return existePbsUpc;
    }

    public void setExistePbsUpc(Boolean existePbsUpc) {
        this.existePbsUpc = existePbsUpc;
    }

    public Boolean getObsReaccPbsCargoUpc() {
        return obsReaccPbsCargoUpc;
    }

    public void setObsReaccPbsCargoUpc(Boolean obsReaccPbsCargoUpc) {
        this.obsReaccPbsCargoUpc = obsReaccPbsCargoUpc;
    }

    public Boolean getPbsUtilizado() {
        return pbsUtilizado;
    }

    public void setPbsUtilizado(Boolean pbsUtilizado) {
        this.pbsUtilizado = pbsUtilizado;
    }

    public Boolean getExistentePbsUpc() {
        return existentePbsUpc;
    }

    public void setExistentePbsUpc(Boolean existentePbsUpc) {
        this.existentePbsUpc = existentePbsUpc;
    }

    public Boolean getListaNoUsoSanitarioUnirs() {
        return listaNoUsoSanitarioUnirs;
    }

    public void setListaNoUsoSanitarioUnirs(Boolean listaNoUsoSanitarioUnirs) {
        this.listaNoUsoSanitarioUnirs = listaNoUsoSanitarioUnirs;
    }

    public Boolean getFaseExperimental() {
        return faseExperimental;
    }

    public void setFaseExperimental(Boolean faseExperimental) {
        this.faseExperimental = faseExperimental;
    }

    public Boolean getRegAprobAutClin() {
        return regAprobAutClin;
    }

    public void setRegAprobAutClin(Boolean regAprobAutClin) {
        this.regAprobAutClin = regAprobAutClin;
    }

    public Boolean getFinanciadoPbsUpc() {
        return financiadoPbsUpc;
    }

    public void setFinanciadoPbsUpc(Boolean financiadoPbsUpc) {
        this.financiadoPbsUpc = financiadoPbsUpc;
    }

    public Boolean getUtilizoProcedExistentePbsUpc() {
        return utilizoProcedExistentePbsUpc;
    }

    public void setUtilizoProcedExistentePbsUpc(Boolean utilizoProcedExistentePbsUpc) {
        this.utilizoProcedExistentePbsUpc = utilizoProcedExistentePbsUpc;
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

    public Boolean getProcePbsDescartado() {
        return procePbsDescartado;
    }

    public void setProcePbsDescartado(Boolean procePbsDescartado) {
        this.procePbsDescartado = procePbsDescartado;
    }

    public Boolean getProcRealizaraCol() {
        return procRealizaraCol;
    }

    public void setProcRealizaraCol(Boolean procRealizaraCol) {
        this.procRealizaraCol = procRealizaraCol;
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
        if (!(object instanceof MpPrescripcionTecnologias)) {
            return false;
        }
        MpPrescripcionTecnologias other = (MpPrescripcionTecnologias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionTecnologias[ id=" + id + " ]";
    }
    
}
