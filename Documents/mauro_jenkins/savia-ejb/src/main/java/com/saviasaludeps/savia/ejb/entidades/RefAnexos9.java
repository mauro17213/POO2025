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
@Table(name = "ref_anexos9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexos9.findAll", query = "SELECT r FROM RefAnexos9 r"),
    @NamedQuery(name = "RefAnexos9.findById", query = "SELECT r FROM RefAnexos9 r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexos9.findByTipo", query = "SELECT r FROM RefAnexos9 r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RefAnexos9.findByNumeroSolicitud", query = "SELECT r FROM RefAnexos9 r WHERE r.numeroSolicitud = :numeroSolicitud"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraSolicitud", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraSolicitud = :fechaHoraSolicitud"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraRegiostro", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraRegiostro = :fechaHoraRegiostro"),
    @NamedQuery(name = "RefAnexos9.findByAplicaNoIpsContrato", query = "SELECT r FROM RefAnexos9 r WHERE r.aplicaNoIpsContrato = :aplicaNoIpsContrato"),
    @NamedQuery(name = "RefAnexos9.findByAplicaNoAfiliado", query = "SELECT r FROM RefAnexos9 r WHERE r.aplicaNoAfiliado = :aplicaNoAfiliado"),
    @NamedQuery(name = "RefAnexos9.findByMaEspecialidadesId", query = "SELECT r FROM RefAnexos9 r WHERE r.maEspecialidadesId = :maEspecialidadesId"),
    @NamedQuery(name = "RefAnexos9.findByMaEspecialidadCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maEspecialidadCodigo = :maEspecialidadCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaEspecialidadValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maEspecialidadValor = :maEspecialidadValor"),
    @NamedQuery(name = "RefAnexos9.findByMaServicioSolicitaId", query = "SELECT r FROM RefAnexos9 r WHERE r.maServicioSolicitaId = :maServicioSolicitaId"),
    @NamedQuery(name = "RefAnexos9.findByMaServicioSolicitaCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maServicioSolicitaCodigo = :maServicioSolicitaCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaServicioSolicitaValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maServicioSolicitaValor = :maServicioSolicitaValor"),
    @NamedQuery(name = "RefAnexos9.findByMotivo", query = "SELECT r FROM RefAnexos9 r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "RefAnexos9.findByMaServicioRemiteId", query = "SELECT r FROM RefAnexos9 r WHERE r.maServicioRemiteId = :maServicioRemiteId"),
    @NamedQuery(name = "RefAnexos9.findByMaServicioRemiteCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maServicioRemiteCodigo = :maServicioRemiteCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaServicioRemiteValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maServicioRemiteValor = :maServicioRemiteValor"),
    @NamedQuery(name = "RefAnexos9.findByUbicacion", query = "SELECT r FROM RefAnexos9 r WHERE r.ubicacion = :ubicacion"),
    @NamedQuery(name = "RefAnexos9.findByCama", query = "SELECT r FROM RefAnexos9 r WHERE r.cama = :cama"),
    @NamedQuery(name = "RefAnexos9.findByNumeroTiket", query = "SELECT r FROM RefAnexos9 r WHERE r.numeroTiket = :numeroTiket"),
    @NamedQuery(name = "RefAnexos9.findByAplicaLdf", query = "SELECT r FROM RefAnexos9 r WHERE r.aplicaLdf = :aplicaLdf"),
    @NamedQuery(name = "RefAnexos9.findByAplicaMaterna", query = "SELECT r FROM RefAnexos9 r WHERE r.aplicaMaterna = :aplicaMaterna"),
    @NamedQuery(name = "RefAnexos9.findByAplicaNeonato", query = "SELECT r FROM RefAnexos9 r WHERE r.aplicaNeonato = :aplicaNeonato"),
    @NamedQuery(name = "RefAnexos9.findByMaeCanalComunicacionId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCanalComunicacionId = :maeCanalComunicacionId"),
    @NamedQuery(name = "RefAnexos9.findByMaeCanalComunicacionCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCanalComunicacionCodigo = :maeCanalComunicacionCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeCanalComunicacionValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCanalComunicacionValor = :maeCanalComunicacionValor"),
    @NamedQuery(name = "RefAnexos9.findByEstado", query = "SELECT r FROM RefAnexos9 r WHERE r.estado = :estado"),
    @NamedQuery(name = "RefAnexos9.findByMaeEstadoId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "RefAnexos9.findByMaeEstadoCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeEstadoValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "RefAnexos9.findByMaeAcompananteTipoDocumentoId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeAcompananteTipoDocumentoId = :maeAcompananteTipoDocumentoId"),
    @NamedQuery(name = "RefAnexos9.findByMaeAcompananteTipoDocumentoCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeAcompananteTipoDocumentoCodigo = :maeAcompananteTipoDocumentoCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeAcompananteTipoDocumentoValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeAcompananteTipoDocumentoValor = :maeAcompananteTipoDocumentoValor"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteDocumento", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteDocumento = :acompananteDocumento"),
    @NamedQuery(name = "RefAnexos9.findByAcompanantePrimerNombre", query = "SELECT r FROM RefAnexos9 r WHERE r.acompanantePrimerNombre = :acompanantePrimerNombre"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteSegundoNombre", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteSegundoNombre = :acompananteSegundoNombre"),
    @NamedQuery(name = "RefAnexos9.findByAcompanantePrimerApellido", query = "SELECT r FROM RefAnexos9 r WHERE r.acompanantePrimerApellido = :acompanantePrimerApellido"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteSegundoApellido", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteSegundoApellido = :acompananteSegundoApellido"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteTelefono", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteTelefono = :acompananteTelefono"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteDireccion", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteDireccion = :acompananteDireccion"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteMunicipio", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteMunicipio = :acompananteMunicipio"),
    @NamedQuery(name = "RefAnexos9.findByAcompananteDepartamento", query = "SELECT r FROM RefAnexos9 r WHERE r.acompananteDepartamento = :acompananteDepartamento"),
    @NamedQuery(name = "RefAnexos9.findByInformanteNombre", query = "SELECT r FROM RefAnexos9 r WHERE r.informanteNombre = :informanteNombre"),
    @NamedQuery(name = "RefAnexos9.findByInformanteTelefono", query = "SELECT r FROM RefAnexos9 r WHERE r.informanteTelefono = :informanteTelefono"),
    @NamedQuery(name = "RefAnexos9.findByInformanteCargo", query = "SELECT r FROM RefAnexos9 r WHERE r.informanteCargo = :informanteCargo"),
    @NamedQuery(name = "RefAnexos9.findByProfesionalSolicitaNombre", query = "SELECT r FROM RefAnexos9 r WHERE r.profesionalSolicitaNombre = :profesionalSolicitaNombre"),
    @NamedQuery(name = "RefAnexos9.findByProfesionalSolicitaTelefono", query = "SELECT r FROM RefAnexos9 r WHERE r.profesionalSolicitaTelefono = :profesionalSolicitaTelefono"),
    @NamedQuery(name = "RefAnexos9.findByFuenteOrigen", query = "SELECT r FROM RefAnexos9 r WHERE r.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "RefAnexos9.findByDiagnosticoEmergente", query = "SELECT r FROM RefAnexos9 r WHERE r.diagnosticoEmergente = :diagnosticoEmergente"),
    @NamedQuery(name = "RefAnexos9.findByConsecutivo", query = "SELECT r FROM RefAnexos9 r WHERE r.consecutivo = :consecutivo"),
    @NamedQuery(name = "RefAnexos9.findByMaeCausaExternaId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCausaExternaId = :maeCausaExternaId"),
    @NamedQuery(name = "RefAnexos9.findByMaeCausaExternaCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCausaExternaCodigo = :maeCausaExternaCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeCausaExternaValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCausaExternaValor = :maeCausaExternaValor"),
    @NamedQuery(name = "RefAnexos9.findByMaeCondicionDestinoId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCondicionDestinoId = :maeCondicionDestinoId"),
    @NamedQuery(name = "RefAnexos9.findByMaeCondicionDestinoCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCondicionDestinoCodigo = :maeCondicionDestinoCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeCondicionDestinoValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeCondicionDestinoValor = :maeCondicionDestinoValor"),
    @NamedQuery(name = "RefAnexos9.findByPrioridad", query = "SELECT r FROM RefAnexos9 r WHERE r.prioridad = :prioridad"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAtencionId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAtencionId = :maeTipoAtencionId"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAtencionCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAtencionCodigo = :maeTipoAtencionCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAtencionValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAtencionValor = :maeTipoAtencionValor"),
    @NamedQuery(name = "RefAnexos9.findByMaeUbicacionId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeUbicacionId = :maeUbicacionId"),
    @NamedQuery(name = "RefAnexos9.findByMaeUbicacionCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeUbicacionCodigo = :maeUbicacionCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeUbicacionValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeUbicacionValor = :maeUbicacionValor"),
    @NamedQuery(name = "RefAnexos9.findByMaeModalidadTecnologiaId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeModalidadTecnologiaId = :maeModalidadTecnologiaId"),
    @NamedQuery(name = "RefAnexos9.findByMaeModalidadTecnologiaCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeModalidadTecnologiaCodigo = :maeModalidadTecnologiaCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeModalidadTecnologiaValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeModalidadTecnologiaValor = :maeModalidadTecnologiaValor"),
    @NamedQuery(name = "RefAnexos9.findByTipoTecnologia", query = "SELECT r FROM RefAnexos9 r WHERE r.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "RefAnexos9.findByMaTecnologiaId", query = "SELECT r FROM RefAnexos9 r WHERE r.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "RefAnexos9.findByMaTecnologiaCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaTecnologiaValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "RefAnexos9.findByCantidadTecnologiaSolicitada", query = "SELECT r FROM RefAnexos9 r WHERE r.cantidadTecnologiaSolicitada = :cantidadTecnologiaSolicitada"),
    @NamedQuery(name = "RefAnexos9.findByAutorizacion", query = "SELECT r FROM RefAnexos9 r WHERE r.autorizacion = :autorizacion"),
    @NamedQuery(name = "RefAnexos9.findByAfiliadoDireccionAlternativa", query = "SELECT r FROM RefAnexos9 r WHERE r.afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa"),
    @NamedQuery(name = "RefAnexos9.findByNombreContactoEmergencia", query = "SELECT r FROM RefAnexos9 r WHERE r.nombreContactoEmergencia = :nombreContactoEmergencia"),
    @NamedQuery(name = "RefAnexos9.findByTelefonoContactoEmergencia", query = "SELECT r FROM RefAnexos9 r WHERE r.telefonoContactoEmergencia = :telefonoContactoEmergencia"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraInicioGestion", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraInicioGestion = :fechaHoraInicioGestion"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraFinGestion", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraFinGestion = :fechaHoraFinGestion"),
    @NamedQuery(name = "RefAnexos9.findByDiasGestion", query = "SELECT r FROM RefAnexos9 r WHERE r.diasGestion = :diasGestion"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraUltimaGestion", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraUltimaGestion = :fechaHoraUltimaGestion"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraAdjuntoEvolucion", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraAdjuntoEvolucion = :fechaHoraAdjuntoEvolucion"),
    @NamedQuery(name = "RefAnexos9.findByRequiereContraste", query = "SELECT r FROM RefAnexos9 r WHERE r.requiereContraste = :requiereContraste"),
    @NamedQuery(name = "RefAnexos9.findByRequiereSedacion", query = "SELECT r FROM RefAnexos9 r WHERE r.requiereSedacion = :requiereSedacion"),
    @NamedQuery(name = "RefAnexos9.findByExamenBag", query = "SELECT r FROM RefAnexos9 r WHERE r.examenBag = :examenBag"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAislamientoId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAislamientoId = :maeTipoAislamientoId"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAislamientoCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAislamientoCodigo = :maeTipoAislamientoCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAislamientoValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAislamientoValor = :maeTipoAislamientoValor"),
    @NamedQuery(name = "RefAnexos9.findByMaeTipoAislamientoTipo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeTipoAislamientoTipo = :maeTipoAislamientoTipo"),
    @NamedQuery(name = "RefAnexos9.findByMaeMaternoPerinatalId", query = "SELECT r FROM RefAnexos9 r WHERE r.maeMaternoPerinatalId = :maeMaternoPerinatalId"),
    @NamedQuery(name = "RefAnexos9.findByMaeMaternoPerinatalCodigo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeMaternoPerinatalCodigo = :maeMaternoPerinatalCodigo"),
    @NamedQuery(name = "RefAnexos9.findByMaeMaternoPerinatalValor", query = "SELECT r FROM RefAnexos9 r WHERE r.maeMaternoPerinatalValor = :maeMaternoPerinatalValor"),
    @NamedQuery(name = "RefAnexos9.findByMaeMaternoPerinatalTipo", query = "SELECT r FROM RefAnexos9 r WHERE r.maeMaternoPerinatalTipo = :maeMaternoPerinatalTipo"),
    @NamedQuery(name = "RefAnexos9.findByUsuarioCrea", query = "SELECT r FROM RefAnexos9 r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefAnexos9.findByTerminalCrea", query = "SELECT r FROM RefAnexos9 r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraCrea", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RefAnexos9.findByUsuarioModifica", query = "SELECT r FROM RefAnexos9 r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RefAnexos9.findByTerminalModifica", query = "SELECT r FROM RefAnexos9 r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RefAnexos9.findByFechaHoraModifica", query = "SELECT r FROM RefAnexos9 r WHERE r.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "RefAnexos9.findByVersion", query = "SELECT r FROM RefAnexos9 r WHERE r.version = :version")})
public class RefAnexos9 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 32)
    @Column(name = "numero_solicitud")
    private String numeroSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_regiostro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegiostro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_no_ips_contrato")
    private boolean aplicaNoIpsContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_no_afiliado")
    private boolean aplicaNoAfiliado;
    @Column(name = "ma_especialidades_id")
    private Integer maEspecialidadesId;
    @Size(max = 16)
    @Column(name = "ma_especialidad_codigo")
    private String maEspecialidadCodigo;
    @Size(max = 254)
    @Column(name = "ma_especialidad_valor")
    private String maEspecialidadValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_servicio_solicita_id")
    private int maServicioSolicitaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ma_servicio_solicita_codigo")
    private String maServicioSolicitaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ma_servicio_solicita_valor")
    private String maServicioSolicitaValor;
    @Size(max = 2048)
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "ma_servicio_remite_id")
    private Integer maServicioRemiteId;
    @Size(max = 16)
    @Column(name = "ma_servicio_remite_codigo")
    private String maServicioRemiteCodigo;
    @Size(max = 256)
    @Column(name = "ma_servicio_remite_valor")
    private String maServicioRemiteValor;
    @Size(max = 32)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Size(max = 32)
    @Column(name = "cama")
    private String cama;
    @Size(max = 64)
    @Column(name = "numero_tiket")
    private String numeroTiket;
    @Column(name = "aplica_ldf")
    private Boolean aplicaLdf;
    @Column(name = "aplica_materna")
    private Boolean aplicaMaterna;
    @Column(name = "aplica_neonato")
    private Boolean aplicaNeonato;
    @Column(name = "mae_canal_comunicacion_id")
    private Integer maeCanalComunicacionId;
    @Size(max = 8)
    @Column(name = "mae_canal_comunicacion_codigo")
    private String maeCanalComunicacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_canal_comunicacion_valor")
    private String maeCanalComunicacionValor;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "mae_estado_id")
    private Integer maeEstadoId;
    @Size(max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Column(name = "mae_acompanante_tipo_documento_id")
    private Integer maeAcompananteTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_acompanante_tipo_documento_codigo")
    private String maeAcompananteTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_acompanante_tipo_documento_valor")
    private String maeAcompananteTipoDocumentoValor;
    @Size(max = 16)
    @Column(name = "acompanante_documento")
    private String acompananteDocumento;
    @Size(max = 64)
    @Column(name = "acompanante_primer_nombre")
    private String acompanantePrimerNombre;
    @Size(max = 64)
    @Column(name = "acompanante_segundo_nombre")
    private String acompananteSegundoNombre;
    @Size(max = 64)
    @Column(name = "acompanante_primer_apellido")
    private String acompanantePrimerApellido;
    @Size(max = 64)
    @Column(name = "acompanante_segundo_apellido")
    private String acompananteSegundoApellido;
    @Size(max = 12)
    @Column(name = "acompanante_telefono")
    private String acompananteTelefono;
    @Size(max = 256)
    @Column(name = "acompanante_direccion")
    private String acompananteDireccion;
    @Size(max = 128)
    @Column(name = "acompanante_municipio")
    private String acompananteMunicipio;
    @Size(max = 128)
    @Column(name = "acompanante_departamento")
    private String acompananteDepartamento;
    @Size(max = 512)
    @Column(name = "informante_nombre")
    private String informanteNombre;
    @Size(max = 16)
    @Column(name = "informante_telefono")
    private String informanteTelefono;
    @Size(max = 64)
    @Column(name = "informante_cargo")
    private String informanteCargo;
    @Size(max = 256)
    @Column(name = "profesional_solicita_nombre")
    private String profesionalSolicitaNombre;
    @Size(max = 16)
    @Column(name = "profesional_solicita_telefono")
    private String profesionalSolicitaTelefono;
    @Column(name = "fuente_origen")
    private Integer fuenteOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "diagnostico_emergente")
    private boolean diagnosticoEmergente;
    @Size(max = 64)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "mae_causa_externa_id")
    private Integer maeCausaExternaId;
    @Size(max = 8)
    @Column(name = "mae_causa_externa_codigo")
    private String maeCausaExternaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_externa_valor")
    private String maeCausaExternaValor;
    @Column(name = "mae_condicion_destino_id")
    private Integer maeCondicionDestinoId;
    @Size(max = 8)
    @Column(name = "mae_condicion_destino_codigo")
    private String maeCondicionDestinoCodigo;
    @Size(max = 128)
    @Column(name = "mae_condicion_destino_valor")
    private String maeCondicionDestinoValor;
    @Column(name = "prioridad")
    private Boolean prioridad;
    @Column(name = "mae_tipo_atencion_id")
    private Integer maeTipoAtencionId;
    @Size(max = 8)
    @Column(name = "mae_tipo_atencion_codigo")
    private String maeTipoAtencionCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_atencion_valor")
    private String maeTipoAtencionValor;
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
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "cantidad_tecnologia_solicitada")
    private Integer cantidadTecnologiaSolicitada;
    @Column(name = "autorizacion")
    private Integer autorizacion;
    @Size(max = 256)
    @Column(name = "afiliado_direccion_alternativa")
    private String afiliadoDireccionAlternativa;
    @Size(max = 256)
    @Column(name = "nombre_contacto_emergencia")
    private String nombreContactoEmergencia;
    @Size(max = 32)
    @Column(name = "telefono_contacto_emergencia")
    private String telefonoContactoEmergencia;
    @Column(name = "fecha_hora_inicio_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicioGestion;
    @Column(name = "fecha_hora_fin_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFinGestion;
    @Column(name = "dias_gestion")
    private Integer diasGestion;
    @Column(name = "fecha_hora_ultima_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUltimaGestion;
    @Column(name = "fecha_hora_adjunto_evolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAdjuntoEvolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requiere_contraste")
    private boolean requiereContraste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requiere_sedacion")
    private boolean requiereSedacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "examen_bag")
    private boolean examenBag;
    @Column(name = "mae_tipo_aislamiento_id")
    private Integer maeTipoAislamientoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_aislamiento_codigo")
    private String maeTipoAislamientoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_aislamiento_valor")
    private String maeTipoAislamientoValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_aislamiento_tipo")
    private String maeTipoAislamientoTipo;
    @Column(name = "mae_materno_perinatal_id")
    private Integer maeMaternoPerinatalId;
    @Size(max = 8)
    @Column(name = "mae_materno_perinatal_codigo")
    private String maeMaternoPerinatalCodigo;
    @Size(max = 128)
    @Column(name = "mae_materno_perinatal_valor")
    private String maeMaternoPerinatalValor;
    @Size(max = 4)
    @Column(name = "mae_materno_perinatal_tipo")
    private String maeMaternoPerinatalTipo;
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
    @Column(name = "version")
    private boolean version;
    @OneToMany(mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @OneToMany(mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<PeAfiliadosSugeridos> peAfiliadosSugeridosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<RefAnexo9Adjuntos> refAnexo9AdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<RefAnexo9Gestiones> refAnexo9GestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<RefAnexo9Diagnosticos> refAnexo9DiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<RefAnexo9DatosClinicos> refAnexo9DatosClinicosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<RefAnexo9Estados> refAnexo9EstadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refAnexos9Id", fetch = FetchType.LAZY)
    private List<RefTransportes> refTransportesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestador_sedes_ubicacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesUbicacionId;
    @JoinColumn(name = "cnt_profesionales_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntProfesionales cntProfesionalesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_empresas_ubicacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones gnEmpresasUbicacionId;

    public RefAnexos9() {
    }

    public RefAnexos9(Integer id) {
        this.id = id;
    }

    public RefAnexos9(Integer id, int tipo, Date fechaHoraSolicitud, Date fechaHoraRegiostro, boolean aplicaNoIpsContrato, boolean aplicaNoAfiliado, int maServicioSolicitaId, String maServicioSolicitaCodigo, String maServicioSolicitaValor, boolean diagnosticoEmergente, boolean requiereContraste, boolean requiereSedacion, boolean examenBag, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, boolean version) {
        this.id = id;
        this.tipo = tipo;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.fechaHoraRegiostro = fechaHoraRegiostro;
        this.aplicaNoIpsContrato = aplicaNoIpsContrato;
        this.aplicaNoAfiliado = aplicaNoAfiliado;
        this.maServicioSolicitaId = maServicioSolicitaId;
        this.maServicioSolicitaCodigo = maServicioSolicitaCodigo;
        this.maServicioSolicitaValor = maServicioSolicitaValor;
        this.diagnosticoEmergente = diagnosticoEmergente;
        this.requiereContraste = requiereContraste;
        this.requiereSedacion = requiereSedacion;
        this.examenBag = examenBag;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public Date getFechaHoraRegiostro() {
        return fechaHoraRegiostro;
    }

    public void setFechaHoraRegiostro(Date fechaHoraRegiostro) {
        this.fechaHoraRegiostro = fechaHoraRegiostro;
    }

    public boolean getAplicaNoIpsContrato() {
        return aplicaNoIpsContrato;
    }

    public void setAplicaNoIpsContrato(boolean aplicaNoIpsContrato) {
        this.aplicaNoIpsContrato = aplicaNoIpsContrato;
    }

    public boolean getAplicaNoAfiliado() {
        return aplicaNoAfiliado;
    }

    public void setAplicaNoAfiliado(boolean aplicaNoAfiliado) {
        this.aplicaNoAfiliado = aplicaNoAfiliado;
    }

    public Integer getMaEspecialidadesId() {
        return maEspecialidadesId;
    }

    public void setMaEspecialidadesId(Integer maEspecialidadesId) {
        this.maEspecialidadesId = maEspecialidadesId;
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

    public int getMaServicioSolicitaId() {
        return maServicioSolicitaId;
    }

    public void setMaServicioSolicitaId(int maServicioSolicitaId) {
        this.maServicioSolicitaId = maServicioSolicitaId;
    }

    public String getMaServicioSolicitaCodigo() {
        return maServicioSolicitaCodigo;
    }

    public void setMaServicioSolicitaCodigo(String maServicioSolicitaCodigo) {
        this.maServicioSolicitaCodigo = maServicioSolicitaCodigo;
    }

    public String getMaServicioSolicitaValor() {
        return maServicioSolicitaValor;
    }

    public void setMaServicioSolicitaValor(String maServicioSolicitaValor) {
        this.maServicioSolicitaValor = maServicioSolicitaValor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getMaServicioRemiteId() {
        return maServicioRemiteId;
    }

    public void setMaServicioRemiteId(Integer maServicioRemiteId) {
        this.maServicioRemiteId = maServicioRemiteId;
    }

    public String getMaServicioRemiteCodigo() {
        return maServicioRemiteCodigo;
    }

    public void setMaServicioRemiteCodigo(String maServicioRemiteCodigo) {
        this.maServicioRemiteCodigo = maServicioRemiteCodigo;
    }

    public String getMaServicioRemiteValor() {
        return maServicioRemiteValor;
    }

    public void setMaServicioRemiteValor(String maServicioRemiteValor) {
        this.maServicioRemiteValor = maServicioRemiteValor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getNumeroTiket() {
        return numeroTiket;
    }

    public void setNumeroTiket(String numeroTiket) {
        this.numeroTiket = numeroTiket;
    }

    public Boolean getAplicaLdf() {
        return aplicaLdf;
    }

    public void setAplicaLdf(Boolean aplicaLdf) {
        this.aplicaLdf = aplicaLdf;
    }

    public Boolean getAplicaMaterna() {
        return aplicaMaterna;
    }

    public void setAplicaMaterna(Boolean aplicaMaterna) {
        this.aplicaMaterna = aplicaMaterna;
    }

    public Boolean getAplicaNeonato() {
        return aplicaNeonato;
    }

    public void setAplicaNeonato(Boolean aplicaNeonato) {
        this.aplicaNeonato = aplicaNeonato;
    }

    public Integer getMaeCanalComunicacionId() {
        return maeCanalComunicacionId;
    }

    public void setMaeCanalComunicacionId(Integer maeCanalComunicacionId) {
        this.maeCanalComunicacionId = maeCanalComunicacionId;
    }

    public String getMaeCanalComunicacionCodigo() {
        return maeCanalComunicacionCodigo;
    }

    public void setMaeCanalComunicacionCodigo(String maeCanalComunicacionCodigo) {
        this.maeCanalComunicacionCodigo = maeCanalComunicacionCodigo;
    }

    public String getMaeCanalComunicacionValor() {
        return maeCanalComunicacionValor;
    }

    public void setMaeCanalComunicacionValor(String maeCanalComunicacionValor) {
        this.maeCanalComunicacionValor = maeCanalComunicacionValor;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
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

    public Integer getMaeAcompananteTipoDocumentoId() {
        return maeAcompananteTipoDocumentoId;
    }

    public void setMaeAcompananteTipoDocumentoId(Integer maeAcompananteTipoDocumentoId) {
        this.maeAcompananteTipoDocumentoId = maeAcompananteTipoDocumentoId;
    }

    public String getMaeAcompananteTipoDocumentoCodigo() {
        return maeAcompananteTipoDocumentoCodigo;
    }

    public void setMaeAcompananteTipoDocumentoCodigo(String maeAcompananteTipoDocumentoCodigo) {
        this.maeAcompananteTipoDocumentoCodigo = maeAcompananteTipoDocumentoCodigo;
    }

    public String getMaeAcompananteTipoDocumentoValor() {
        return maeAcompananteTipoDocumentoValor;
    }

    public void setMaeAcompananteTipoDocumentoValor(String maeAcompananteTipoDocumentoValor) {
        this.maeAcompananteTipoDocumentoValor = maeAcompananteTipoDocumentoValor;
    }

    public String getAcompananteDocumento() {
        return acompananteDocumento;
    }

    public void setAcompananteDocumento(String acompananteDocumento) {
        this.acompananteDocumento = acompananteDocumento;
    }

    public String getAcompanantePrimerNombre() {
        return acompanantePrimerNombre;
    }

    public void setAcompanantePrimerNombre(String acompanantePrimerNombre) {
        this.acompanantePrimerNombre = acompanantePrimerNombre;
    }

    public String getAcompananteSegundoNombre() {
        return acompananteSegundoNombre;
    }

    public void setAcompananteSegundoNombre(String acompananteSegundoNombre) {
        this.acompananteSegundoNombre = acompananteSegundoNombre;
    }

    public String getAcompanantePrimerApellido() {
        return acompanantePrimerApellido;
    }

    public void setAcompanantePrimerApellido(String acompanantePrimerApellido) {
        this.acompanantePrimerApellido = acompanantePrimerApellido;
    }

    public String getAcompananteSegundoApellido() {
        return acompananteSegundoApellido;
    }

    public void setAcompananteSegundoApellido(String acompananteSegundoApellido) {
        this.acompananteSegundoApellido = acompananteSegundoApellido;
    }

    public String getAcompananteTelefono() {
        return acompananteTelefono;
    }

    public void setAcompananteTelefono(String acompananteTelefono) {
        this.acompananteTelefono = acompananteTelefono;
    }

    public String getAcompananteDireccion() {
        return acompananteDireccion;
    }

    public void setAcompananteDireccion(String acompananteDireccion) {
        this.acompananteDireccion = acompananteDireccion;
    }

    public String getAcompananteMunicipio() {
        return acompananteMunicipio;
    }

    public void setAcompananteMunicipio(String acompananteMunicipio) {
        this.acompananteMunicipio = acompananteMunicipio;
    }

    public String getAcompananteDepartamento() {
        return acompananteDepartamento;
    }

    public void setAcompananteDepartamento(String acompananteDepartamento) {
        this.acompananteDepartamento = acompananteDepartamento;
    }

    public String getInformanteNombre() {
        return informanteNombre;
    }

    public void setInformanteNombre(String informanteNombre) {
        this.informanteNombre = informanteNombre;
    }

    public String getInformanteTelefono() {
        return informanteTelefono;
    }

    public void setInformanteTelefono(String informanteTelefono) {
        this.informanteTelefono = informanteTelefono;
    }

    public String getInformanteCargo() {
        return informanteCargo;
    }

    public void setInformanteCargo(String informanteCargo) {
        this.informanteCargo = informanteCargo;
    }

    public String getProfesionalSolicitaNombre() {
        return profesionalSolicitaNombre;
    }

    public void setProfesionalSolicitaNombre(String profesionalSolicitaNombre) {
        this.profesionalSolicitaNombre = profesionalSolicitaNombre;
    }

    public String getProfesionalSolicitaTelefono() {
        return profesionalSolicitaTelefono;
    }

    public void setProfesionalSolicitaTelefono(String profesionalSolicitaTelefono) {
        this.profesionalSolicitaTelefono = profesionalSolicitaTelefono;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public boolean getDiagnosticoEmergente() {
        return diagnosticoEmergente;
    }

    public void setDiagnosticoEmergente(boolean diagnosticoEmergente) {
        this.diagnosticoEmergente = diagnosticoEmergente;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
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

    public Integer getMaeCondicionDestinoId() {
        return maeCondicionDestinoId;
    }

    public void setMaeCondicionDestinoId(Integer maeCondicionDestinoId) {
        this.maeCondicionDestinoId = maeCondicionDestinoId;
    }

    public String getMaeCondicionDestinoCodigo() {
        return maeCondicionDestinoCodigo;
    }

    public void setMaeCondicionDestinoCodigo(String maeCondicionDestinoCodigo) {
        this.maeCondicionDestinoCodigo = maeCondicionDestinoCodigo;
    }

    public String getMaeCondicionDestinoValor() {
        return maeCondicionDestinoValor;
    }

    public void setMaeCondicionDestinoValor(String maeCondicionDestinoValor) {
        this.maeCondicionDestinoValor = maeCondicionDestinoValor;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getMaeTipoAtencionId() {
        return maeTipoAtencionId;
    }

    public void setMaeTipoAtencionId(Integer maeTipoAtencionId) {
        this.maeTipoAtencionId = maeTipoAtencionId;
    }

    public String getMaeTipoAtencionCodigo() {
        return maeTipoAtencionCodigo;
    }

    public void setMaeTipoAtencionCodigo(String maeTipoAtencionCodigo) {
        this.maeTipoAtencionCodigo = maeTipoAtencionCodigo;
    }

    public String getMaeTipoAtencionValor() {
        return maeTipoAtencionValor;
    }

    public void setMaeTipoAtencionValor(String maeTipoAtencionValor) {
        this.maeTipoAtencionValor = maeTipoAtencionValor;
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

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
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

    public Integer getCantidadTecnologiaSolicitada() {
        return cantidadTecnologiaSolicitada;
    }

    public void setCantidadTecnologiaSolicitada(Integer cantidadTecnologiaSolicitada) {
        this.cantidadTecnologiaSolicitada = cantidadTecnologiaSolicitada;
    }

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Integer autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getAfiliadoDireccionAlternativa() {
        return afiliadoDireccionAlternativa;
    }

    public void setAfiliadoDireccionAlternativa(String afiliadoDireccionAlternativa) {
        this.afiliadoDireccionAlternativa = afiliadoDireccionAlternativa;
    }

    public String getNombreContactoEmergencia() {
        return nombreContactoEmergencia;
    }

    public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
        this.nombreContactoEmergencia = nombreContactoEmergencia;
    }

    public String getTelefonoContactoEmergencia() {
        return telefonoContactoEmergencia;
    }

    public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
    }

    public Date getFechaHoraInicioGestion() {
        return fechaHoraInicioGestion;
    }

    public void setFechaHoraInicioGestion(Date fechaHoraInicioGestion) {
        this.fechaHoraInicioGestion = fechaHoraInicioGestion;
    }

    public Date getFechaHoraFinGestion() {
        return fechaHoraFinGestion;
    }

    public void setFechaHoraFinGestion(Date fechaHoraFinGestion) {
        this.fechaHoraFinGestion = fechaHoraFinGestion;
    }

    public Integer getDiasGestion() {
        return diasGestion;
    }

    public void setDiasGestion(Integer diasGestion) {
        this.diasGestion = diasGestion;
    }

    public Date getFechaHoraUltimaGestion() {
        return fechaHoraUltimaGestion;
    }

    public void setFechaHoraUltimaGestion(Date fechaHoraUltimaGestion) {
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
    }

    public Date getFechaHoraAdjuntoEvolucion() {
        return fechaHoraAdjuntoEvolucion;
    }

    public void setFechaHoraAdjuntoEvolucion(Date fechaHoraAdjuntoEvolucion) {
        this.fechaHoraAdjuntoEvolucion = fechaHoraAdjuntoEvolucion;
    }

    public boolean getRequiereContraste() {
        return requiereContraste;
    }

    public void setRequiereContraste(boolean requiereContraste) {
        this.requiereContraste = requiereContraste;
    }

    public boolean getRequiereSedacion() {
        return requiereSedacion;
    }

    public void setRequiereSedacion(boolean requiereSedacion) {
        this.requiereSedacion = requiereSedacion;
    }

    public boolean getExamenBag() {
        return examenBag;
    }

    public void setExamenBag(boolean examenBag) {
        this.examenBag = examenBag;
    }

    public Integer getMaeTipoAislamientoId() {
        return maeTipoAislamientoId;
    }

    public void setMaeTipoAislamientoId(Integer maeTipoAislamientoId) {
        this.maeTipoAislamientoId = maeTipoAislamientoId;
    }

    public String getMaeTipoAislamientoCodigo() {
        return maeTipoAislamientoCodigo;
    }

    public void setMaeTipoAislamientoCodigo(String maeTipoAislamientoCodigo) {
        this.maeTipoAislamientoCodigo = maeTipoAislamientoCodigo;
    }

    public String getMaeTipoAislamientoValor() {
        return maeTipoAislamientoValor;
    }

    public void setMaeTipoAislamientoValor(String maeTipoAislamientoValor) {
        this.maeTipoAislamientoValor = maeTipoAislamientoValor;
    }

    public String getMaeTipoAislamientoTipo() {
        return maeTipoAislamientoTipo;
    }

    public void setMaeTipoAislamientoTipo(String maeTipoAislamientoTipo) {
        this.maeTipoAislamientoTipo = maeTipoAislamientoTipo;
    }

    public Integer getMaeMaternoPerinatalId() {
        return maeMaternoPerinatalId;
    }

    public void setMaeMaternoPerinatalId(Integer maeMaternoPerinatalId) {
        this.maeMaternoPerinatalId = maeMaternoPerinatalId;
    }

    public String getMaeMaternoPerinatalCodigo() {
        return maeMaternoPerinatalCodigo;
    }

    public void setMaeMaternoPerinatalCodigo(String maeMaternoPerinatalCodigo) {
        this.maeMaternoPerinatalCodigo = maeMaternoPerinatalCodigo;
    }

    public String getMaeMaternoPerinatalValor() {
        return maeMaternoPerinatalValor;
    }

    public void setMaeMaternoPerinatalValor(String maeMaternoPerinatalValor) {
        this.maeMaternoPerinatalValor = maeMaternoPerinatalValor;
    }

    public String getMaeMaternoPerinatalTipo() {
        return maeMaternoPerinatalTipo;
    }

    public void setMaeMaternoPerinatalTipo(String maeMaternoPerinatalTipo) {
        this.maeMaternoPerinatalTipo = maeMaternoPerinatalTipo;
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

    public boolean getVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    @XmlTransient
    public List<AuAnexos4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexos4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    @XmlTransient
    public List<PeAfiliadosSugeridos> getPeAfiliadosSugeridosList() {
        return peAfiliadosSugeridosList;
    }

    public void setPeAfiliadosSugeridosList(List<PeAfiliadosSugeridos> peAfiliadosSugeridosList) {
        this.peAfiliadosSugeridosList = peAfiliadosSugeridosList;
    }

    @XmlTransient
    public List<RefAnexo9Adjuntos> getRefAnexo9AdjuntosList() {
        return refAnexo9AdjuntosList;
    }

    public void setRefAnexo9AdjuntosList(List<RefAnexo9Adjuntos> refAnexo9AdjuntosList) {
        this.refAnexo9AdjuntosList = refAnexo9AdjuntosList;
    }

    @XmlTransient
    public List<RefAnexo9Gestiones> getRefAnexo9GestionesList() {
        return refAnexo9GestionesList;
    }

    public void setRefAnexo9GestionesList(List<RefAnexo9Gestiones> refAnexo9GestionesList) {
        this.refAnexo9GestionesList = refAnexo9GestionesList;
    }

    @XmlTransient
    public List<RefAnexo9Diagnosticos> getRefAnexo9DiagnosticosList() {
        return refAnexo9DiagnosticosList;
    }

    public void setRefAnexo9DiagnosticosList(List<RefAnexo9Diagnosticos> refAnexo9DiagnosticosList) {
        this.refAnexo9DiagnosticosList = refAnexo9DiagnosticosList;
    }

    @XmlTransient
    public List<RefAnexo9DatosClinicos> getRefAnexo9DatosClinicosList() {
        return refAnexo9DatosClinicosList;
    }

    public void setRefAnexo9DatosClinicosList(List<RefAnexo9DatosClinicos> refAnexo9DatosClinicosList) {
        this.refAnexo9DatosClinicosList = refAnexo9DatosClinicosList;
    }

    @XmlTransient
    public List<RefAnexo9Estados> getRefAnexo9EstadosList() {
        return refAnexo9EstadosList;
    }

    public void setRefAnexo9EstadosList(List<RefAnexo9Estados> refAnexo9EstadosList) {
        this.refAnexo9EstadosList = refAnexo9EstadosList;
    }

    @XmlTransient
    public List<RefTransportes> getRefTransportesList() {
        return refTransportesList;
    }

    public void setRefTransportesList(List<RefTransportes> refTransportesList) {
        this.refTransportesList = refTransportesList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestadorSedes getCntPrestadorSedesUbicacionId() {
        return cntPrestadorSedesUbicacionId;
    }

    public void setCntPrestadorSedesUbicacionId(CntPrestadorSedes cntPrestadorSedesUbicacionId) {
        this.cntPrestadorSedesUbicacionId = cntPrestadorSedesUbicacionId;
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

    public GnUbicaciones getGnEmpresasUbicacionId() {
        return gnEmpresasUbicacionId;
    }

    public void setGnEmpresasUbicacionId(GnUbicaciones gnEmpresasUbicacionId) {
        this.gnEmpresasUbicacionId = gnEmpresasUbicacionId;
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
        if (!(object instanceof RefAnexos9)) {
            return false;
        }
        RefAnexos9 other = (RefAnexos9) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexos9[ id=" + id + " ]";
    }
    
}
