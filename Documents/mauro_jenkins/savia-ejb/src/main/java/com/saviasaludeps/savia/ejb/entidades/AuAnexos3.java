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
@Table(name = "au_anexos3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexos3.findAll", query = "SELECT a FROM AuAnexos3 a"),
    @NamedQuery(name = "AuAnexos3.findById", query = "SELECT a FROM AuAnexos3 a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexos3.findByNumero", query = "SELECT a FROM AuAnexos3 a WHERE a.numero = :numero"),
    @NamedQuery(name = "AuAnexos3.findByFechaSolicitud", query = "SELECT a FROM AuAnexos3 a WHERE a.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "AuAnexos3.findByNombreAcompanante", query = "SELECT a FROM AuAnexos3 a WHERE a.nombreAcompanante = :nombreAcompanante"),
    @NamedQuery(name = "AuAnexos3.findByTelefonoAcompanante", query = "SELECT a FROM AuAnexos3 a WHERE a.telefonoAcompanante = :telefonoAcompanante"),
    @NamedQuery(name = "AuAnexos3.findByCelularAcompanente", query = "SELECT a FROM AuAnexos3 a WHERE a.celularAcompanente = :celularAcompanente"),
    @NamedQuery(name = "AuAnexos3.findByEstado", query = "SELECT a FROM AuAnexos3 a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexos3.findByEstadoProcesoActual", query = "SELECT a FROM AuAnexos3 a WHERE a.estadoProcesoActual = :estadoProcesoActual"),
    @NamedQuery(name = "AuAnexos3.findByMaeEstadoMotivoId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeEstadoMotivoId = :maeEstadoMotivoId"),
    @NamedQuery(name = "AuAnexos3.findByMaeEstadoMotivoCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeEstadoMotivoCodigo = :maeEstadoMotivoCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeEstadoMotivoValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeEstadoMotivoValor = :maeEstadoMotivoValor"),
    @NamedQuery(name = "AuAnexos3.findByEstadoJustificacion", query = "SELECT a FROM AuAnexos3 a WHERE a.estadoJustificacion = :estadoJustificacion"),
    @NamedQuery(name = "AuAnexos3.findByMaeCausaExternaId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeCausaExternaId = :maeCausaExternaId"),
    @NamedQuery(name = "AuAnexos3.findByMaeCausaExternaCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeCausaExternaCodigo = :maeCausaExternaCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeCausaExternaValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeCausaExternaValor = :maeCausaExternaValor"),
    @NamedQuery(name = "AuAnexos3.findByMaeAmbitoAtencionId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "AuAnexos3.findByMaeAmbitoAtencionCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeAmbitoAtencionValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor"),
    @NamedQuery(name = "AuAnexos3.findByMaServicioSolicitadoId", query = "SELECT a FROM AuAnexos3 a WHERE a.maServicioSolicitadoId = :maServicioSolicitadoId"),
    @NamedQuery(name = "AuAnexos3.findByMaServicioSolicitadoCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaServicioSolicitadoValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maServicioSolicitadoValor = :maServicioSolicitadoValor"),
    @NamedQuery(name = "AuAnexos3.findByMaServicioHabilitadoId", query = "SELECT a FROM AuAnexos3 a WHERE a.maServicioHabilitadoId = :maServicioHabilitadoId"),
    @NamedQuery(name = "AuAnexos3.findByMaServicioHabilitadoCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maServicioHabilitadoCodigo = :maServicioHabilitadoCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaServicioHabilitadoValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maServicioHabilitadoValor = :maServicioHabilitadoValor"),
    @NamedQuery(name = "AuAnexos3.findByPeProgramaEspecialId", query = "SELECT a FROM AuAnexos3 a WHERE a.peProgramaEspecialId = :peProgramaEspecialId"),
    @NamedQuery(name = "AuAnexos3.findByMaeOrigenAtencionId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeOrigenAtencionId = :maeOrigenAtencionId"),
    @NamedQuery(name = "AuAnexos3.findByMaeOrigenAtencionCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeOrigenAtencionCodigo = :maeOrigenAtencionCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeOrigenAtencionValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeOrigenAtencionValor = :maeOrigenAtencionValor"),
    @NamedQuery(name = "AuAnexos3.findByPrioridad", query = "SELECT a FROM AuAnexos3 a WHERE a.prioridad = :prioridad"),
    @NamedQuery(name = "AuAnexos3.findByMaeTipoServicioId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "AuAnexos3.findByMaeTipoServicioCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeTipoServicioValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "AuAnexos3.findByMaeUbicacionId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeUbicacionId = :maeUbicacionId"),
    @NamedQuery(name = "AuAnexos3.findByMaeUbicacionCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeUbicacionCodigo = :maeUbicacionCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeUbicacionValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeUbicacionValor = :maeUbicacionValor"),
    @NamedQuery(name = "AuAnexos3.findByCama", query = "SELECT a FROM AuAnexos3 a WHERE a.cama = :cama"),
    @NamedQuery(name = "AuAnexos3.findByJustificacionClinica", query = "SELECT a FROM AuAnexos3 a WHERE a.justificacionClinica = :justificacionClinica"),
    @NamedQuery(name = "AuAnexos3.findByNombreProfesional", query = "SELECT a FROM AuAnexos3 a WHERE a.nombreProfesional = :nombreProfesional"),
    @NamedQuery(name = "AuAnexos3.findByCargoProfesional", query = "SELECT a FROM AuAnexos3 a WHERE a.cargoProfesional = :cargoProfesional"),
    @NamedQuery(name = "AuAnexos3.findByTelefonoProfesional", query = "SELECT a FROM AuAnexos3 a WHERE a.telefonoProfesional = :telefonoProfesional"),
    @NamedQuery(name = "AuAnexos3.findByCelularProfesional", query = "SELECT a FROM AuAnexos3 a WHERE a.celularProfesional = :celularProfesional"),
    @NamedQuery(name = "AuAnexos3.findByFuenteOrigen", query = "SELECT a FROM AuAnexos3 a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuAnexos3.findByPeProgramaEspecialValor", query = "SELECT a FROM AuAnexos3 a WHERE a.peProgramaEspecialValor = :peProgramaEspecialValor"),
    @NamedQuery(name = "AuAnexos3.findByPeProgramaEspecialCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.peProgramaEspecialCodigo = :peProgramaEspecialCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeGuiaManejoIntegralId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeGuiaManejoIntegralId = :maeGuiaManejoIntegralId"),
    @NamedQuery(name = "AuAnexos3.findByMaeGuiaManejoIntegralCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeGuiaManejoIntegralCodigo = :maeGuiaManejoIntegralCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeGuiaManejoIntegralValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeGuiaManejoIntegralValor = :maeGuiaManejoIntegralValor"),
    @NamedQuery(name = "AuAnexos3.findByCuotaModeradora", query = "SELECT a FROM AuAnexos3 a WHERE a.cuotaModeradora = :cuotaModeradora"),
    @NamedQuery(name = "AuAnexos3.findByCuotaRecuperacion", query = "SELECT a FROM AuAnexos3 a WHERE a.cuotaRecuperacion = :cuotaRecuperacion"),
    @NamedQuery(name = "AuAnexos3.findByCopago", query = "SELECT a FROM AuAnexos3 a WHERE a.copago = :copago"),
    @NamedQuery(name = "AuAnexos3.findByRuta", query = "SELECT a FROM AuAnexos3 a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuAnexos3.findByArchivo", query = "SELECT a FROM AuAnexos3 a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuAnexos3.findByVersion", query = "SELECT a FROM AuAnexos3 a WHERE a.version = :version"),
    @NamedQuery(name = "AuAnexos3.findByConsecutivo", query = "SELECT a FROM AuAnexos3 a WHERE a.consecutivo = :consecutivo"),
    @NamedQuery(name = "AuAnexos3.findByMaeModalidadTecnologiaId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeModalidadTecnologiaId = :maeModalidadTecnologiaId"),
    @NamedQuery(name = "AuAnexos3.findByMaeModalidadTecnologiaCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeModalidadTecnologiaCodigo = :maeModalidadTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeModalidadTecnologiaValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeModalidadTecnologiaValor = :maeModalidadTecnologiaValor"),
    @NamedQuery(name = "AuAnexos3.findByMaeFinalidadTecnologiaId", query = "SELECT a FROM AuAnexos3 a WHERE a.maeFinalidadTecnologiaId = :maeFinalidadTecnologiaId"),
    @NamedQuery(name = "AuAnexos3.findByMaeFinalidadTecnologiaCodigo", query = "SELECT a FROM AuAnexos3 a WHERE a.maeFinalidadTecnologiaCodigo = :maeFinalidadTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexos3.findByMaeFinalidadTecnologiaValor", query = "SELECT a FROM AuAnexos3 a WHERE a.maeFinalidadTecnologiaValor = :maeFinalidadTecnologiaValor"),
    @NamedQuery(name = "AuAnexos3.findByAfiliadoDireccionAlternativa", query = "SELECT a FROM AuAnexos3 a WHERE a.afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa"),
    @NamedQuery(name = "AuAnexos3.findByFuenteAnula", query = "SELECT a FROM AuAnexos3 a WHERE a.fuenteAnula = :fuenteAnula"),
    @NamedQuery(name = "AuAnexos3.findByUsuarioCrea", query = "SELECT a FROM AuAnexos3 a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexos3.findByTerminalCrea", query = "SELECT a FROM AuAnexos3 a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexos3.findByFechaHoraCrea", query = "SELECT a FROM AuAnexos3 a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexos3.findByUsuarioModifica", query = "SELECT a FROM AuAnexos3 a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexos3.findByTerminalModifica", query = "SELECT a FROM AuAnexos3 a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexos3.findByFechaHoraModifica", query = "SELECT a FROM AuAnexos3 a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexos3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Size(max = 128)
    @Column(name = "nombre_acompanante")
    private String nombreAcompanante;
    @Size(max = 16)
    @Column(name = "telefono_acompanante")
    private String telefonoAcompanante;
    @Size(max = 16)
    @Column(name = "celular_acompanente")
    private String celularAcompanente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_proceso_actual")
    private int estadoProcesoActual;
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
    @Column(name = "mae_causa_externa_id")
    private Integer maeCausaExternaId;
    @Size(max = 8)
    @Column(name = "mae_causa_externa_codigo")
    private String maeCausaExternaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_externa_valor")
    private String maeCausaExternaValor;
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
    @Column(name = "ma_servicio_solicitado_id")
    private int maServicioSolicitadoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ma_servicio_solicitado_codigo")
    private String maServicioSolicitadoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ma_servicio_solicitado_valor")
    private String maServicioSolicitadoValor;
    @Column(name = "ma_servicio_habilitado_id")
    private Integer maServicioHabilitadoId;
    @Size(max = 16)
    @Column(name = "ma_servicio_habilitado_codigo")
    private String maServicioHabilitadoCodigo;
    @Size(max = 256)
    @Column(name = "ma_servicio_habilitado_valor")
    private String maServicioHabilitadoValor;
    @Column(name = "pe_programa_especial_id")
    private Integer peProgramaEspecialId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_origen_atencion_id")
    private int maeOrigenAtencionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_origen_atencion_codigo")
    private String maeOrigenAtencionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_origen_atencion_valor")
    private String maeOrigenAtencionValor;
    @Column(name = "prioridad")
    private Boolean prioridad;
    @Column(name = "mae_tipo_servicio_id")
    private Integer maeTipoServicioId;
    @Size(max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Column(name = "mae_ubicacion_id")
    private Integer maeUbicacionId;
    @Size(max = 8)
    @Column(name = "mae_ubicacion_codigo")
    private String maeUbicacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_ubicacion_valor")
    private String maeUbicacionValor;
    @Size(max = 16)
    @Column(name = "cama")
    private String cama;
    @Size(max = 2048)
    @Column(name = "justificacion_clinica")
    private String justificacionClinica;
    @Size(max = 64)
    @Column(name = "nombre_profesional")
    private String nombreProfesional;
    @Size(max = 64)
    @Column(name = "cargo_profesional")
    private String cargoProfesional;
    @Size(max = 16)
    @Column(name = "telefono_profesional")
    private String telefonoProfesional;
    @Size(max = 16)
    @Column(name = "celular_profesional")
    private String celularProfesional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private int fuenteOrigen;
    @Size(max = 128)
    @Column(name = "pe_programa_especial_valor")
    private String peProgramaEspecialValor;
    @Size(max = 8)
    @Column(name = "pe_programa_especial_codigo")
    private String peProgramaEspecialCodigo;
    @Column(name = "mae_guia_manejo_integral_id")
    private Integer maeGuiaManejoIntegralId;
    @Size(max = 8)
    @Column(name = "mae_guia_manejo_integral_codigo")
    private String maeGuiaManejoIntegralCodigo;
    @Size(max = 128)
    @Column(name = "mae_guia_manejo_integral_valor")
    private String maeGuiaManejoIntegralValor;
    @Column(name = "cuota_moderadora")
    private Boolean cuotaModeradora;
    @Column(name = "cuota_recuperacion")
    private Boolean cuotaRecuperacion;
    @Column(name = "copago")
    private Boolean copago;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private boolean version;
    @Size(max = 64)
    @Column(name = "consecutivo")
    private String consecutivo;
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
    @Column(name = "fuente_anula")
    private Integer fuenteAnula;
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
    @OneToMany(mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @JoinColumn(name = "au_anexo3_carga_anuladas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo3CargaAnuladas auAnexo3CargaAnuladasId;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexo3_cargas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo3Cargas auAnexo3CargasId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_profesionales_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntProfesionales cntProfesionalesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @OneToMany(mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<PeAfiliadosSugeridos> peAfiliadosSugeridosList;
    @OneToMany(mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3CargaAnuladaSucesos> auAnexo3CargaAnuladaSucesosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuRechazos> auRechazosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3Adjuntos> auAnexo3AdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3Historicos> auAnexo3HistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3Afiliados> auAnexo3AfiliadosList;
    @OneToMany(mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuCotizaciones> auCotizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3Items> auAnexo3ItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<PeDireccionados> peDireccionadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuCargaDetallesAnexos3> auCargaDetallesAnexos3List;
    @OneToMany(mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3Diagnosticos> auAnexo3DiagnosticosList;
    @OneToMany(mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos3Id", fetch = FetchType.LAZY)
    private List<AuAnexo3Tutelas> auAnexo3TutelasList;

    public AuAnexos3() {
    }

    public AuAnexos3(Integer id) {
        this.id = id;
    }

    public AuAnexos3(Integer id, Date fechaSolicitud, int estado, int estadoProcesoActual, int maeAmbitoAtencionId, String maeAmbitoAtencionCodigo, String maeAmbitoAtencionValor, int maServicioSolicitadoId, String maServicioSolicitadoCodigo, String maServicioSolicitadoValor, int maeOrigenAtencionId, String maeOrigenAtencionCodigo, String maeOrigenAtencionValor, int fuenteOrigen, boolean version, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.estadoProcesoActual = estadoProcesoActual;
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
        this.maServicioSolicitadoId = maServicioSolicitadoId;
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
        this.maeOrigenAtencionId = maeOrigenAtencionId;
        this.maeOrigenAtencionCodigo = maeOrigenAtencionCodigo;
        this.maeOrigenAtencionValor = maeOrigenAtencionValor;
        this.fuenteOrigen = fuenteOrigen;
        this.version = version;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getNombreAcompanante() {
        return nombreAcompanante;
    }

    public void setNombreAcompanante(String nombreAcompanante) {
        this.nombreAcompanante = nombreAcompanante;
    }

    public String getTelefonoAcompanante() {
        return telefonoAcompanante;
    }

    public void setTelefonoAcompanante(String telefonoAcompanante) {
        this.telefonoAcompanante = telefonoAcompanante;
    }

    public String getCelularAcompanente() {
        return celularAcompanente;
    }

    public void setCelularAcompanente(String celularAcompanente) {
        this.celularAcompanente = celularAcompanente;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoProcesoActual() {
        return estadoProcesoActual;
    }

    public void setEstadoProcesoActual(int estadoProcesoActual) {
        this.estadoProcesoActual = estadoProcesoActual;
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

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
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

    public int getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(int maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public Integer getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(Integer maServicioHabilitadoId) {
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

    public Integer getPeProgramaEspecialId() {
        return peProgramaEspecialId;
    }

    public void setPeProgramaEspecialId(Integer peProgramaEspecialId) {
        this.peProgramaEspecialId = peProgramaEspecialId;
    }

    public int getMaeOrigenAtencionId() {
        return maeOrigenAtencionId;
    }

    public void setMaeOrigenAtencionId(int maeOrigenAtencionId) {
        this.maeOrigenAtencionId = maeOrigenAtencionId;
    }

    public String getMaeOrigenAtencionCodigo() {
        return maeOrigenAtencionCodigo;
    }

    public void setMaeOrigenAtencionCodigo(String maeOrigenAtencionCodigo) {
        this.maeOrigenAtencionCodigo = maeOrigenAtencionCodigo;
    }

    public String getMaeOrigenAtencionValor() {
        return maeOrigenAtencionValor;
    }

    public void setMaeOrigenAtencionValor(String maeOrigenAtencionValor) {
        this.maeOrigenAtencionValor = maeOrigenAtencionValor;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
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

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    public String getCargoProfesional() {
        return cargoProfesional;
    }

    public void setCargoProfesional(String cargoProfesional) {
        this.cargoProfesional = cargoProfesional;
    }

    public String getTelefonoProfesional() {
        return telefonoProfesional;
    }

    public void setTelefonoProfesional(String telefonoProfesional) {
        this.telefonoProfesional = telefonoProfesional;
    }

    public String getCelularProfesional() {
        return celularProfesional;
    }

    public void setCelularProfesional(String celularProfesional) {
        this.celularProfesional = celularProfesional;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String getPeProgramaEspecialValor() {
        return peProgramaEspecialValor;
    }

    public void setPeProgramaEspecialValor(String peProgramaEspecialValor) {
        this.peProgramaEspecialValor = peProgramaEspecialValor;
    }

    public String getPeProgramaEspecialCodigo() {
        return peProgramaEspecialCodigo;
    }

    public void setPeProgramaEspecialCodigo(String peProgramaEspecialCodigo) {
        this.peProgramaEspecialCodigo = peProgramaEspecialCodigo;
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

    public Boolean getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(Boolean cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public Boolean getCuotaRecuperacion() {
        return cuotaRecuperacion;
    }

    public void setCuotaRecuperacion(Boolean cuotaRecuperacion) {
        this.cuotaRecuperacion = cuotaRecuperacion;
    }

    public Boolean getCopago() {
        return copago;
    }

    public void setCopago(Boolean copago) {
        this.copago = copago;
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

    public Integer getFuenteAnula() {
        return fuenteAnula;
    }

    public void setFuenteAnula(Integer fuenteAnula) {
        this.fuenteAnula = fuenteAnula;
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
    public List<AuAnexos4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexos4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    public AuAnexo3CargaAnuladas getAuAnexo3CargaAnuladasId() {
        return auAnexo3CargaAnuladasId;
    }

    public void setAuAnexo3CargaAnuladasId(AuAnexo3CargaAnuladas auAnexo3CargaAnuladasId) {
        this.auAnexo3CargaAnuladasId = auAnexo3CargaAnuladasId;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexo3Cargas getAuAnexo3CargasId() {
        return auAnexo3CargasId;
    }

    public void setAuAnexo3CargasId(AuAnexo3Cargas auAnexo3CargasId) {
        this.auAnexo3CargasId = auAnexo3CargasId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntProfesionales getCntProfesionalesId() {
        return cntProfesionalesId;
    }

    public void setCntProfesionalesId(CntProfesionales cntProfesionalesId) {
        this.cntProfesionalesId = cntProfesionalesId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    @XmlTransient
    public List<PeAfiliadosSugeridos> getPeAfiliadosSugeridosList() {
        return peAfiliadosSugeridosList;
    }

    public void setPeAfiliadosSugeridosList(List<PeAfiliadosSugeridos> peAfiliadosSugeridosList) {
        this.peAfiliadosSugeridosList = peAfiliadosSugeridosList;
    }

    @XmlTransient
    public List<AuAnexo3CargaAnuladaSucesos> getAuAnexo3CargaAnuladaSucesosList() {
        return auAnexo3CargaAnuladaSucesosList;
    }

    public void setAuAnexo3CargaAnuladaSucesosList(List<AuAnexo3CargaAnuladaSucesos> auAnexo3CargaAnuladaSucesosList) {
        this.auAnexo3CargaAnuladaSucesosList = auAnexo3CargaAnuladaSucesosList;
    }

    @XmlTransient
    public List<AuRechazos> getAuRechazosList() {
        return auRechazosList;
    }

    public void setAuRechazosList(List<AuRechazos> auRechazosList) {
        this.auRechazosList = auRechazosList;
    }

    @XmlTransient
    public List<AuAnexo3Adjuntos> getAuAnexo3AdjuntosList() {
        return auAnexo3AdjuntosList;
    }

    public void setAuAnexo3AdjuntosList(List<AuAnexo3Adjuntos> auAnexo3AdjuntosList) {
        this.auAnexo3AdjuntosList = auAnexo3AdjuntosList;
    }

    @XmlTransient
    public List<AuAnexo3Historicos> getAuAnexo3HistoricosList() {
        return auAnexo3HistoricosList;
    }

    public void setAuAnexo3HistoricosList(List<AuAnexo3Historicos> auAnexo3HistoricosList) {
        this.auAnexo3HistoricosList = auAnexo3HistoricosList;
    }

    @XmlTransient
    public List<AuAnexo3Afiliados> getAuAnexo3AfiliadosList() {
        return auAnexo3AfiliadosList;
    }

    public void setAuAnexo3AfiliadosList(List<AuAnexo3Afiliados> auAnexo3AfiliadosList) {
        this.auAnexo3AfiliadosList = auAnexo3AfiliadosList;
    }

    @XmlTransient
    public List<AuCotizaciones> getAuCotizacionesList() {
        return auCotizacionesList;
    }

    public void setAuCotizacionesList(List<AuCotizaciones> auCotizacionesList) {
        this.auCotizacionesList = auCotizacionesList;
    }

    @XmlTransient
    public List<AuAnexo3Items> getAuAnexo3ItemsList() {
        return auAnexo3ItemsList;
    }

    public void setAuAnexo3ItemsList(List<AuAnexo3Items> auAnexo3ItemsList) {
        this.auAnexo3ItemsList = auAnexo3ItemsList;
    }

    @XmlTransient
    public List<PeDireccionados> getPeDireccionadosList() {
        return peDireccionadosList;
    }

    public void setPeDireccionadosList(List<PeDireccionados> peDireccionadosList) {
        this.peDireccionadosList = peDireccionadosList;
    }

    @XmlTransient
    public List<AuCargaDetallesAnexos3> getAuCargaDetallesAnexos3List() {
        return auCargaDetallesAnexos3List;
    }

    public void setAuCargaDetallesAnexos3List(List<AuCargaDetallesAnexos3> auCargaDetallesAnexos3List) {
        this.auCargaDetallesAnexos3List = auCargaDetallesAnexos3List;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    @XmlTransient
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    @XmlTransient
    public List<AuAnexo3Diagnosticos> getAuAnexo3DiagnosticosList() {
        return auAnexo3DiagnosticosList;
    }

    public void setAuAnexo3DiagnosticosList(List<AuAnexo3Diagnosticos> auAnexo3DiagnosticosList) {
        this.auAnexo3DiagnosticosList = auAnexo3DiagnosticosList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<AuAnexo3Tutelas> getAuAnexo3TutelasList() {
        return auAnexo3TutelasList;
    }

    public void setAuAnexo3TutelasList(List<AuAnexo3Tutelas> auAnexo3TutelasList) {
        this.auAnexo3TutelasList = auAnexo3TutelasList;
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
        if (!(object instanceof AuAnexos3)) {
            return false;
        }
        AuAnexos3 other = (AuAnexos3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexos3[ id=" + id + " ]";
    }
    
}
