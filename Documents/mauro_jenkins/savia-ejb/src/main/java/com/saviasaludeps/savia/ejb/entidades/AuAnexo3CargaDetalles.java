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
@Table(name = "au_anexo3_carga_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3CargaDetalles.findAll", query = "SELECT a FROM AuAnexo3CargaDetalles a"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findById", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByAuAnexos3Id", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.auAnexos3Id = :auAnexos3Id"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByNumeroSolicitud", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.numeroSolicitud = :numeroSolicitud"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDocumentoId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDocumentoValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByDocumentoAfiliado", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByTelefonoAfiliado", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.telefonoAfiliado = :telefonoAfiliado"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByCelularAfiliado", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.celularAfiliado = :celularAfiliado"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaServicioSolicitadoId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maServicioSolicitadoId = :maServicioSolicitadoId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaServicioSolicitadoCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaServicioSolicitadoValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maServicioSolicitadoValor = :maServicioSolicitadoValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeCausaExternaId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeCausaExternaId = :maeCausaExternaId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeCausaExternaCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeCausaExternaCodigo = :maeCausaExternaCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeCausaExternaValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeCausaExternaValor = :maeCausaExternaValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaServicioHabilitadoId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maServicioHabilitadoId = :maServicioHabilitadoId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaServicioHabilitadoCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maServicioHabilitadoCodigo = :maServicioHabilitadoCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaServicioHabilitadoValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maServicioHabilitadoValor = :maServicioHabilitadoValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByFechaSolicitud", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByCodigoREPS", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.codigoREPS = :codigoREPS"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByNombreProfesional", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.nombreProfesional = :nombreProfesional"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDocumentoProfesionalId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDocumentoProfesionalId = :maeTipoDocumentoProfesionalId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDocumentoProfesionalCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDocumentoProfesionalCodigo = :maeTipoDocumentoProfesionalCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDocumentoProfesionalValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDocumentoProfesionalValor = :maeTipoDocumentoProfesionalValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByDocumentoProfesional", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.documentoProfesional = :documentoProfesional"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByTelefonoProfesional", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.telefonoProfesional = :telefonoProfesional"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeOrigenAtencionId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeOrigenAtencionId = :maeOrigenAtencionId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeOrigenAtencionCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeOrigenAtencionCodigo = :maeOrigenAtencionCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeOrigenAtencionValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeOrigenAtencionValor = :maeOrigenAtencionValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPrioridadAtencion", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.prioridadAtencion = :prioridadAtencion"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByTipoServicioSolicitado", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.tipoServicioSolicitado = :tipoServicioSolicitado"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeAmbitoAtencionId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeAmbitoAtencionCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeAmbitoAtencionValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByEsPrincipal", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.esPrincipal = :esPrincipal"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByTipoTecnologia", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaTecnologiaId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaTecnologiaCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaTecnologiaValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaMedicamentoId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaMedicamentoCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaMedicamentoValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByCantidadSolicitada", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByDuracionTratamiento", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.duracionTratamiento = :duracionTratamiento"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByDosis", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.dosis = :dosis"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByFrecuencia", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.frecuencia = :frecuencia"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByTipoFrecuencia", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.tipoFrecuencia = :tipoFrecuencia"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByViaAdministracion", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.viaAdministracion = :viaAdministracion"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByJustificacionClinica", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.justificacionClinica = :justificacionClinica"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaDiagnosticoId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaDiagnosticoCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaDiagnosticoValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDiagnosticoId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDiagnosticoId = :maeTipoDiagnosticoId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDiagnosticoCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoDiagnosticoValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeUbicacionesId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeUbicacionesId = :maeUbicacionesId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeUbicacionCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeUbicacionCodigo = :maeUbicacionCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeUbicacionValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeUbicacionValor = :maeUbicacionValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByDescripcion", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaEspecialidadId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maEspecialidadId = :maEspecialidadId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaEspecialidadCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maEspecialidadCodigo = :maEspecialidadCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaEspecialidadValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maEspecialidadValor = :maEspecialidadValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByFila", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.fila = :fila"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByConsecutivo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.consecutivo = :consecutivo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoServicioId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoServicioCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeTipoServicioValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPeProgramaEspecialId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.peProgramaEspecialId = :peProgramaEspecialId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPeProgramaEspecialCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.peProgramaEspecialCodigo = :peProgramaEspecialCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPeProgramaEspecialDescripcion", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.peProgramaEspecialDescripcion = :peProgramaEspecialDescripcion"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPosfechado", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.posfechado = :posfechado"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPosfechadoPrincipal", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.posfechadoPrincipal = :posfechadoPrincipal"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPosfechadoDuracion", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.posfechadoDuracion = :posfechadoDuracion"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByPosfechadoEntregas", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.posfechadoEntregas = :posfechadoEntregas"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeModalidadTecnologiaId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeModalidadTecnologiaId = :maeModalidadTecnologiaId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeModalidadTecnologiaCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeModalidadTecnologiaCodigo = :maeModalidadTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeModalidadTecnologiaValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeModalidadTecnologiaValor = :maeModalidadTecnologiaValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeFinalidadTecnologiaId", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeFinalidadTecnologiaId = :maeFinalidadTecnologiaId"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeFinalidadTecnologiaCodigo", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeFinalidadTecnologiaCodigo = :maeFinalidadTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByMaeFinalidadTecnologiaValor", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.maeFinalidadTecnologiaValor = :maeFinalidadTecnologiaValor"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByAfiliadoDireccionAlternativa", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByVersion", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.version = :version"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByTerminalCrea", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3CargaDetalles.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3CargaDetalles a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo3CargaDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "au_anexos3_id")
    private Integer auAnexos3Id;
    @Size(max = 32)
    @Column(name = "numero_solicitud")
    private String numeroSolicitud;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Size(max = 16)
    @Column(name = "documento_afiliado")
    private String documentoAfiliado;
    @Size(max = 16)
    @Column(name = "telefono_afiliado")
    private String telefonoAfiliado;
    @Size(max = 16)
    @Column(name = "celular_afiliado")
    private String celularAfiliado;
    @Column(name = "ma_servicio_solicitado_id")
    private Integer maServicioSolicitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_solicitado_codigo")
    private String maServicioSolicitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_solicitado_valor")
    private String maServicioSolicitadoValor;
    @Column(name = "mae_causa_externa_id")
    private Integer maeCausaExternaId;
    @Size(max = 8)
    @Column(name = "mae_causa_externa_codigo")
    private String maeCausaExternaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_externa_valor")
    private String maeCausaExternaValor;
    @Column(name = "ma_servicio_habilitado_id")
    private Integer maServicioHabilitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_habilitado_codigo")
    private String maServicioHabilitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_habilitado_valor")
    private String maServicioHabilitadoValor;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Size(max = 32)
    @Column(name = "codigo_REPS")
    private String codigoREPS;
    @Size(max = 64)
    @Column(name = "nombre_profesional")
    private String nombreProfesional;
    @Column(name = "mae_tipo_documento_profesional_id")
    private Integer maeTipoDocumentoProfesionalId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_profesional_codigo")
    private String maeTipoDocumentoProfesionalCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_profesional_valor")
    private String maeTipoDocumentoProfesionalValor;
    @Size(max = 16)
    @Column(name = "documento_profesional")
    private String documentoProfesional;
    @Size(max = 16)
    @Column(name = "telefono_profesional")
    private String telefonoProfesional;
    @Column(name = "mae_origen_atencion_id")
    private Integer maeOrigenAtencionId;
    @Size(max = 8)
    @Column(name = "mae_origen_atencion_codigo")
    private String maeOrigenAtencionCodigo;
    @Size(max = 128)
    @Column(name = "mae_origen_atencion_valor")
    private String maeOrigenAtencionValor;
    @Column(name = "prioridad_atencion")
    private Integer prioridadAtencion;
    @Column(name = "tipo_servicio_solicitado")
    private Integer tipoServicioSolicitado;
    @Column(name = "mae_ambito_atencion_id")
    private Integer maeAmbitoAtencionId;
    @Size(max = 8)
    @Column(name = "mae_ambito_atencion_codigo")
    private String maeAmbitoAtencionCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_atencion_valor")
    private String maeAmbitoAtencionValor;
    @Column(name = "es_principal")
    private Boolean esPrincipal;
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
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    @Column(name = "cantidad_solicitada")
    private Integer cantidadSolicitada;
    @Column(name = "duracion_tratamiento")
    private Integer duracionTratamiento;
    @Size(max = 8)
    @Column(name = "dosis")
    private String dosis;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @Size(max = 16)
    @Column(name = "tipo_frecuencia")
    private String tipoFrecuencia;
    @Column(name = "via_administracion")
    private Integer viaAdministracion;
    @Size(max = 2048)
    @Column(name = "justificacion_clinica")
    private String justificacionClinica;
    @Column(name = "ma_diagnostico_id")
    private Integer maDiagnosticoId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
    @Column(name = "mae_tipo_diagnostico_id")
    private Integer maeTipoDiagnosticoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_diagnostico_codigo")
    private String maeTipoDiagnosticoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_diagnostico_valor")
    private String maeTipoDiagnosticoValor;
    @Column(name = "mae_ubicaciones_id")
    private Integer maeUbicacionesId;
    @Size(max = 8)
    @Column(name = "mae_ubicacion_codigo")
    private String maeUbicacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_ubicacion_valor")
    private String maeUbicacionValor;
    @Size(max = 4096)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "ma_especialidad_id")
    private Integer maEspecialidadId;
    @Size(max = 32)
    @Column(name = "ma_especialidad_codigo")
    private String maEspecialidadCodigo;
    @Size(max = 512)
    @Column(name = "ma_especialidad_valor")
    private String maEspecialidadValor;
    @Column(name = "fila")
    private Integer fila;
    @Size(max = 32)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "mae_tipo_servicio_id")
    private Integer maeTipoServicioId;
    @Size(max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Column(name = "pe_programa_especial_id")
    private Integer peProgramaEspecialId;
    @Size(max = 8)
    @Column(name = "pe_programa_especial_codigo")
    private String peProgramaEspecialCodigo;
    @Size(max = 128)
    @Column(name = "pe_programa_especial_descripcion")
    private String peProgramaEspecialDescripcion;
    @Column(name = "posfechado")
    private Boolean posfechado;
    @Column(name = "posfechado_principal")
    private Boolean posfechadoPrincipal;
    @Column(name = "posfechado_duracion")
    private Integer posfechadoDuracion;
    @Column(name = "posfechado_entregas")
    private Integer posfechadoEntregas;
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
    @Column(name = "version")
    private int version;
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
    @JoinColumn(name = "au_anexo3_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3Cargas auAnexo3CargasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3CargaDetallesId", fetch = FetchType.LAZY)
    private List<AuCargaDetallesAnexos3> auCargaDetallesAnexos3List;
    @OneToMany(mappedBy = "auAnexo3CargaDetallesId", fetch = FetchType.LAZY)
    private List<AuAnexo3CargaSucesos> auAnexo3CargaSucesosList;

    public AuAnexo3CargaDetalles() {
    }

    public AuAnexo3CargaDetalles(Integer id) {
        this.id = id;
    }

    public AuAnexo3CargaDetalles(Integer id, int version, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Integer getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(Integer auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public String getTelefonoAfiliado() {
        return telefonoAfiliado;
    }

    public void setTelefonoAfiliado(String telefonoAfiliado) {
        this.telefonoAfiliado = telefonoAfiliado;
    }

    public String getCelularAfiliado() {
        return celularAfiliado;
    }

    public void setCelularAfiliado(String celularAfiliado) {
        this.celularAfiliado = celularAfiliado;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getCodigoREPS() {
        return codigoREPS;
    }

    public void setCodigoREPS(String codigoREPS) {
        this.codigoREPS = codigoREPS;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    public Integer getMaeTipoDocumentoProfesionalId() {
        return maeTipoDocumentoProfesionalId;
    }

    public void setMaeTipoDocumentoProfesionalId(Integer maeTipoDocumentoProfesionalId) {
        this.maeTipoDocumentoProfesionalId = maeTipoDocumentoProfesionalId;
    }

    public String getMaeTipoDocumentoProfesionalCodigo() {
        return maeTipoDocumentoProfesionalCodigo;
    }

    public void setMaeTipoDocumentoProfesionalCodigo(String maeTipoDocumentoProfesionalCodigo) {
        this.maeTipoDocumentoProfesionalCodigo = maeTipoDocumentoProfesionalCodigo;
    }

    public String getMaeTipoDocumentoProfesionalValor() {
        return maeTipoDocumentoProfesionalValor;
    }

    public void setMaeTipoDocumentoProfesionalValor(String maeTipoDocumentoProfesionalValor) {
        this.maeTipoDocumentoProfesionalValor = maeTipoDocumentoProfesionalValor;
    }

    public String getDocumentoProfesional() {
        return documentoProfesional;
    }

    public void setDocumentoProfesional(String documentoProfesional) {
        this.documentoProfesional = documentoProfesional;
    }

    public String getTelefonoProfesional() {
        return telefonoProfesional;
    }

    public void setTelefonoProfesional(String telefonoProfesional) {
        this.telefonoProfesional = telefonoProfesional;
    }

    public Integer getMaeOrigenAtencionId() {
        return maeOrigenAtencionId;
    }

    public void setMaeOrigenAtencionId(Integer maeOrigenAtencionId) {
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

    public Integer getPrioridadAtencion() {
        return prioridadAtencion;
    }

    public void setPrioridadAtencion(Integer prioridadAtencion) {
        this.prioridadAtencion = prioridadAtencion;
    }

    public Integer getTipoServicioSolicitado() {
        return tipoServicioSolicitado;
    }

    public void setTipoServicioSolicitado(Integer tipoServicioSolicitado) {
        this.tipoServicioSolicitado = tipoServicioSolicitado;
    }

    public Integer getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(Integer maeAmbitoAtencionId) {
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

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
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

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(String tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public Integer getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(Integer viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public Integer getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(Integer maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public Integer getMaeUbicacionesId() {
        return maeUbicacionesId;
    }

    public void setMaeUbicacionesId(Integer maeUbicacionesId) {
        this.maeUbicacionesId = maeUbicacionesId;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
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

    public Integer getPeProgramaEspecialId() {
        return peProgramaEspecialId;
    }

    public void setPeProgramaEspecialId(Integer peProgramaEspecialId) {
        this.peProgramaEspecialId = peProgramaEspecialId;
    }

    public String getPeProgramaEspecialCodigo() {
        return peProgramaEspecialCodigo;
    }

    public void setPeProgramaEspecialCodigo(String peProgramaEspecialCodigo) {
        this.peProgramaEspecialCodigo = peProgramaEspecialCodigo;
    }

    public String getPeProgramaEspecialDescripcion() {
        return peProgramaEspecialDescripcion;
    }

    public void setPeProgramaEspecialDescripcion(String peProgramaEspecialDescripcion) {
        this.peProgramaEspecialDescripcion = peProgramaEspecialDescripcion;
    }

    public Boolean getPosfechado() {
        return posfechado;
    }

    public void setPosfechado(Boolean posfechado) {
        this.posfechado = posfechado;
    }

    public Boolean getPosfechadoPrincipal() {
        return posfechadoPrincipal;
    }

    public void setPosfechadoPrincipal(Boolean posfechadoPrincipal) {
        this.posfechadoPrincipal = posfechadoPrincipal;
    }

    public Integer getPosfechadoDuracion() {
        return posfechadoDuracion;
    }

    public void setPosfechadoDuracion(Integer posfechadoDuracion) {
        this.posfechadoDuracion = posfechadoDuracion;
    }

    public Integer getPosfechadoEntregas() {
        return posfechadoEntregas;
    }

    public void setPosfechadoEntregas(Integer posfechadoEntregas) {
        this.posfechadoEntregas = posfechadoEntregas;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public AuAnexo3Cargas getAuAnexo3CargasId() {
        return auAnexo3CargasId;
    }

    public void setAuAnexo3CargasId(AuAnexo3Cargas auAnexo3CargasId) {
        this.auAnexo3CargasId = auAnexo3CargasId;
    }

    @XmlTransient
    public List<AuCargaDetallesAnexos3> getAuCargaDetallesAnexos3List() {
        return auCargaDetallesAnexos3List;
    }

    public void setAuCargaDetallesAnexos3List(List<AuCargaDetallesAnexos3> auCargaDetallesAnexos3List) {
        this.auCargaDetallesAnexos3List = auCargaDetallesAnexos3List;
    }

    @XmlTransient
    public List<AuAnexo3CargaSucesos> getAuAnexo3CargaSucesosList() {
        return auAnexo3CargaSucesosList;
    }

    public void setAuAnexo3CargaSucesosList(List<AuAnexo3CargaSucesos> auAnexo3CargaSucesosList) {
        this.auAnexo3CargaSucesosList = auAnexo3CargaSucesosList;
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
        if (!(object instanceof AuAnexo3CargaDetalles)) {
            return false;
        }
        AuAnexo3CargaDetalles other = (AuAnexo3CargaDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaDetalles[ id=" + id + " ]";
    }
    
}
