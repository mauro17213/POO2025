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
@Table(name = "mp_direccionamientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpDireccionamientos.findAll", query = "SELECT m FROM MpDireccionamientos m"),
    @NamedQuery(name = "MpDireccionamientos.findById", query = "SELECT m FROM MpDireccionamientos m WHERE m.id = :id"),
    @NamedQuery(name = "MpDireccionamientos.findByTipoTecnologia", query = "SELECT m FROM MpDireccionamientos m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeTipoDocumentoPrestadorId", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeTipoDocumentoPrestadorId = :maeTipoDocumentoPrestadorId"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeTipoDocumentoPrestadorCodigo", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeTipoDocumentoPrestadorCodigo = :maeTipoDocumentoPrestadorCodigo"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeTipoDocumentoPrestadorValor", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeTipoDocumentoPrestadorValor = :maeTipoDocumentoPrestadorValor"),
    @NamedQuery(name = "MpDireccionamientos.findByPrestadorNumeroDocumento", query = "SELECT m FROM MpDireccionamientos m WHERE m.prestadorNumeroDocumento = :prestadorNumeroDocumento"),
    @NamedQuery(name = "MpDireccionamientos.findByPrestadorRazonSocial", query = "SELECT m FROM MpDireccionamientos m WHERE m.prestadorRazonSocial = :prestadorRazonSocial"),
    @NamedQuery(name = "MpDireccionamientos.findBySedeCodigoHabilitacion", query = "SELECT m FROM MpDireccionamientos m WHERE m.sedeCodigoHabilitacion = :sedeCodigoHabilitacion"),
    @NamedQuery(name = "MpDireccionamientos.findBySedeDireccionPrestador", query = "SELECT m FROM MpDireccionamientos m WHERE m.sedeDireccionPrestador = :sedeDireccionPrestador"),
    @NamedQuery(name = "MpDireccionamientos.findBySedeTelefonoPrestador", query = "SELECT m FROM MpDireccionamientos m WHERE m.sedeTelefonoPrestador = :sedeTelefonoPrestador"),
    @NamedQuery(name = "MpDireccionamientos.findByEstado", query = "SELECT m FROM MpDireccionamientos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpDireccionamientos.findByIdTransaccion", query = "SELECT m FROM MpDireccionamientos m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpDireccionamientos.findByFechaDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.fechaDireccionamiento = :fechaDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByFechaMaxEntrega", query = "SELECT m FROM MpDireccionamientos m WHERE m.fechaMaxEntrega = :fechaMaxEntrega"),
    @NamedQuery(name = "MpDireccionamientos.findByConsecutivoEntrega", query = "SELECT m FROM MpDireccionamientos m WHERE m.consecutivoEntrega = :consecutivoEntrega"),
    @NamedQuery(name = "MpDireccionamientos.findByEntregaTotal", query = "SELECT m FROM MpDireccionamientos m WHERE m.entregaTotal = :entregaTotal"),
    @NamedQuery(name = "MpDireccionamientos.findByEntregadoNumero", query = "SELECT m FROM MpDireccionamientos m WHERE m.entregadoNumero = :entregadoNumero"),
    @NamedQuery(name = "MpDireccionamientos.findByEntregadoTotal", query = "SELECT m FROM MpDireccionamientos m WHERE m.entregadoTotal = :entregadoTotal"),
    @NamedQuery(name = "MpDireccionamientos.findByEntregadoPendiente", query = "SELECT m FROM MpDireccionamientos m WHERE m.entregadoPendiente = :entregadoPendiente"),
    @NamedQuery(name = "MpDireccionamientos.findByJustificacionDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.justificacionDireccionamiento = :justificacionDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByEnvioCorreoAuto", query = "SELECT m FROM MpDireccionamientos m WHERE m.envioCorreoAuto = :envioCorreoAuto"),
    @NamedQuery(name = "MpDireccionamientos.findByCausaNoEntregaCod", query = "SELECT m FROM MpDireccionamientos m WHERE m.causaNoEntregaCod = :causaNoEntregaCod"),
    @NamedQuery(name = "MpDireccionamientos.findByTipoTecnologiaDireccionado", query = "SELECT m FROM MpDireccionamientos m WHERE m.tipoTecnologiaDireccionado = :tipoTecnologiaDireccionado"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoMpEntrega", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoMpEntrega = :codigoMpEntrega"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoMpPropio", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoMpPropio = :codigoMpPropio"),
    @NamedQuery(name = "MpDireccionamientos.findBySubEntrega", query = "SELECT m FROM MpDireccionamientos m WHERE m.subEntrega = :subEntrega"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoPrestadorSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoPrestadorSede = :codigoPrestadorSede"),
    @NamedQuery(name = "MpDireccionamientos.findByUbicacionSedeId", query = "SELECT m FROM MpDireccionamientos m WHERE m.ubicacionSedeId = :ubicacionSedeId"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeRegionSedeId", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeRegionSedeId = :maeRegionSedeId"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeRegionSedeCodigo", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeRegionSedeCodigo = :maeRegionSedeCodigo"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeRegionSedeValor", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeRegionSedeValor = :maeRegionSedeValor"),
    @NamedQuery(name = "MpDireccionamientos.findByDireccionSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.direccionSede = :direccionSede"),
    @NamedQuery(name = "MpDireccionamientos.findByNombreSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.nombreSede = :nombreSede"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoSede = :codigoSede"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoHabilitacionSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoHabilitacionSede = :codigoHabilitacionSede"),
    @NamedQuery(name = "MpDireccionamientos.findByFaxSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.faxSede = :faxSede"),
    @NamedQuery(name = "MpDireccionamientos.findByTelefonoCitasSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.telefonoCitasSede = :telefonoCitasSede"),
    @NamedQuery(name = "MpDireccionamientos.findByCorreoElectronicoSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.correoElectronicoSede = :correoElectronicoSede"),
    @NamedQuery(name = "MpDireccionamientos.findByTelefonoAdministrativoSede", query = "SELECT m FROM MpDireccionamientos m WHERE m.telefonoAdministrativoSede = :telefonoAdministrativoSede"),
    @NamedQuery(name = "MpDireccionamientos.findByDireccionamientoEsEstadoAnulado", query = "SELECT m FROM MpDireccionamientos m WHERE m.direccionamientoEsEstadoAnulado = :direccionamientoEsEstadoAnulado"),
    @NamedQuery(name = "MpDireccionamientos.findByFechaAnulacionDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.fechaAnulacionDireccionamiento = :fechaAnulacionDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeTipoDocumentoId", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeTipoDocumentoCodigo", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "MpDireccionamientos.findByMaeTipoDocumentoValor", query = "SELECT m FROM MpDireccionamientos m WHERE m.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "MpDireccionamientos.findByNumeroDocumentoPaciente", query = "SELECT m FROM MpDireccionamientos m WHERE m.numeroDocumentoPaciente = :numeroDocumentoPaciente"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoTipoDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoTipoDireccionamiento = :codigoTipoDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoEntregaParcial", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoEntregaParcial = :codigoEntregaParcial"),
    @NamedQuery(name = "MpDireccionamientos.findByCodigoEntregaDiferida", query = "SELECT m FROM MpDireccionamientos m WHERE m.codigoEntregaDiferida = :codigoEntregaDiferida"),
    @NamedQuery(name = "MpDireccionamientos.findByEsEntregaParcial", query = "SELECT m FROM MpDireccionamientos m WHERE m.esEntregaParcial = :esEntregaParcial"),
    @NamedQuery(name = "MpDireccionamientos.findByEsEntregaDiferida", query = "SELECT m FROM MpDireccionamientos m WHERE m.esEntregaDiferida = :esEntregaDiferida"),
    @NamedQuery(name = "MpDireccionamientos.findByNumeroPrescripcionAsoc", query = "SELECT m FROM MpDireccionamientos m WHERE m.numeroPrescripcionAsoc = :numeroPrescripcionAsoc"),
    @NamedQuery(name = "MpDireccionamientos.findByConsecutivoTecAsociada", query = "SELECT m FROM MpDireccionamientos m WHERE m.consecutivoTecAsociada = :consecutivoTecAsociada"),
    @NamedQuery(name = "MpDireccionamientos.findByRespuestaDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.respuestaDireccionamiento = :respuestaDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByRespuestaProgramacion", query = "SELECT m FROM MpDireccionamientos m WHERE m.respuestaProgramacion = :respuestaProgramacion"),
    @NamedQuery(name = "MpDireccionamientos.findByEstadoProgramacion", query = "SELECT m FROM MpDireccionamientos m WHERE m.estadoProgramacion = :estadoProgramacion"),
    @NamedQuery(name = "MpDireccionamientos.findByPreeliminado", query = "SELECT m FROM MpDireccionamientos m WHERE m.preeliminado = :preeliminado"),
    @NamedQuery(name = "MpDireccionamientos.findByEliminado", query = "SELECT m FROM MpDireccionamientos m WHERE m.eliminado = :eliminado"),
    @NamedQuery(name = "MpDireccionamientos.findByFechaEnvioAuto", query = "SELECT m FROM MpDireccionamientos m WHERE m.fechaEnvioAuto = :fechaEnvioAuto"),
    @NamedQuery(name = "MpDireccionamientos.findByIdDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.idDireccionamiento = :idDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByConsecutivoOrden", query = "SELECT m FROM MpDireccionamientos m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpDireccionamientos.findByUltimoDireccionamiento", query = "SELECT m FROM MpDireccionamientos m WHERE m.ultimoDireccionamiento = :ultimoDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientos.findByOrigen", query = "SELECT m FROM MpDireccionamientos m WHERE m.origen = :origen"),
    @NamedQuery(name = "MpDireccionamientos.findByValorTecContratada", query = "SELECT m FROM MpDireccionamientos m WHERE m.valorTecContratada = :valorTecContratada"),
    @NamedQuery(name = "MpDireccionamientos.findByCausalDiferidaParcial", query = "SELECT m FROM MpDireccionamientos m WHERE m.causalDiferidaParcial = :causalDiferidaParcial"),
    @NamedQuery(name = "MpDireccionamientos.findByUsuarioCrea", query = "SELECT m FROM MpDireccionamientos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpDireccionamientos.findByTerminalCrea", query = "SELECT m FROM MpDireccionamientos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpDireccionamientos.findByFechaHoraCrea", query = "SELECT m FROM MpDireccionamientos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpDireccionamientos.findByUsuarioAnula", query = "SELECT m FROM MpDireccionamientos m WHERE m.usuarioAnula = :usuarioAnula"),
    @NamedQuery(name = "MpDireccionamientos.findByTerminalAnula", query = "SELECT m FROM MpDireccionamientos m WHERE m.terminalAnula = :terminalAnula"),
    @NamedQuery(name = "MpDireccionamientos.findByFechaHoraAnula", query = "SELECT m FROM MpDireccionamientos m WHERE m.fechaHoraAnula = :fechaHoraAnula")})
public class MpDireccionamientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
    @Column(name = "mae_tipo_documento_prestador_id")
    private Integer maeTipoDocumentoPrestadorId;
    @Size(max = 16)
    @Column(name = "mae_tipo_documento_prestador_codigo")
    private String maeTipoDocumentoPrestadorCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_prestador_valor")
    private String maeTipoDocumentoPrestadorValor;
    @Column(name = "prestador_numero_documento")
    private Integer prestadorNumeroDocumento;
    @Size(max = 512)
    @Column(name = "prestador_razon_social")
    private String prestadorRazonSocial;
    @Size(max = 32)
    @Column(name = "sede_codigo_habilitacion")
    private String sedeCodigoHabilitacion;
    @Size(max = 128)
    @Column(name = "sede_direccion_prestador")
    private String sedeDireccionPrestador;
    @Size(max = 45)
    @Column(name = "sede_telefono_prestador")
    private String sedeTelefonoPrestador;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Column(name = "fecha_direccionamiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDireccionamiento;
    @Column(name = "fecha_max_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaMaxEntrega;
    @Column(name = "consecutivo_entrega")
    private Integer consecutivoEntrega;
    @Column(name = "entrega_total")
    private Integer entregaTotal;
    @Column(name = "entregado_numero")
    private Integer entregadoNumero;
    @Column(name = "entregado_total")
    private Integer entregadoTotal;
    @Column(name = "entregado_pendiente")
    private Integer entregadoPendiente;
    @Size(max = 2048)
    @Column(name = "justificacion_direccionamiento")
    private String justificacionDireccionamiento;
    @Column(name = "envio_correo_auto")
    private Boolean envioCorreoAuto;
    @Column(name = "causa_no_entrega_cod")
    private Integer causaNoEntregaCod;
    @Column(name = "tipo_tecnologia_direccionado")
    private Integer tipoTecnologiaDireccionado;
    @Size(max = 1024)
    @Column(name = "codigo_mp_entrega")
    private String codigoMpEntrega;
    @Size(max = 2048)
    @Column(name = "codigo_mp_propio")
    private String codigoMpPropio;
    @Column(name = "sub_entrega")
    private Integer subEntrega;
    @Size(max = 16)
    @Column(name = "codigo_prestador_sede")
    private String codigoPrestadorSede;
    @Size(max = 8)
    @Column(name = "ubicacion_sede_id")
    private String ubicacionSedeId;
    @Column(name = "mae_region_sede_id")
    private Integer maeRegionSedeId;
    @Size(max = 8)
    @Column(name = "mae_region_sede_codigo")
    private String maeRegionSedeCodigo;
    @Size(max = 128)
    @Column(name = "mae_region_sede_valor")
    private String maeRegionSedeValor;
    @Size(max = 256)
    @Column(name = "direccion_sede")
    private String direccionSede;
    @Size(max = 512)
    @Column(name = "nombre_sede")
    private String nombreSede;
    @Size(max = 16)
    @Column(name = "codigo_sede")
    private String codigoSede;
    @Size(max = 16)
    @Column(name = "codigo_habilitacion_sede")
    private String codigoHabilitacionSede;
    @Size(max = 32)
    @Column(name = "fax_sede")
    private String faxSede;
    @Size(max = 64)
    @Column(name = "telefono_citas_sede")
    private String telefonoCitasSede;
    @Size(max = 64)
    @Column(name = "correo_electronico_sede")
    private String correoElectronicoSede;
    @Size(max = 64)
    @Column(name = "telefono_administrativo_sede")
    private String telefonoAdministrativoSede;
    @Column(name = "direccionamiento_es_estado_anulado")
    private Boolean direccionamientoEsEstadoAnulado;
    @Column(name = "fecha_anulacion_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacionDireccionamiento;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Size(max = 16)
    @Column(name = "numero_documento_paciente")
    private String numeroDocumentoPaciente;
    @Column(name = "codigo_tipo_direccionamiento")
    private Integer codigoTipoDireccionamiento;
    @Column(name = "codigo_entrega_parcial")
    private Integer codigoEntregaParcial;
    @Column(name = "codigo_entrega_diferida")
    private Integer codigoEntregaDiferida;
    @Column(name = "es_entrega_parcial")
    private Boolean esEntregaParcial;
    @Column(name = "es_entrega_diferida")
    private Boolean esEntregaDiferida;
    @Size(max = 45)
    @Column(name = "numero_prescripcion_asoc")
    private String numeroPrescripcionAsoc;
    @Size(max = 45)
    @Column(name = "consecutivo_tec_asociada")
    private String consecutivoTecAsociada;
    @Size(max = 1024)
    @Column(name = "respuesta_direccionamiento")
    private String respuestaDireccionamiento;
    @Size(max = 512)
    @Column(name = "respuesta_programacion")
    private String respuestaProgramacion;
    @Column(name = "estado_programacion")
    private Integer estadoProgramacion;
    @Column(name = "preeliminado")
    private Boolean preeliminado;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "fecha_envio_auto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvioAuto;
    @Column(name = "id_direccionamiento")
    private Integer idDireccionamiento;
    @Column(name = "consecutivo_orden")
    private Integer consecutivoOrden;
    @Column(name = "ultimo_direccionamiento")
    private Boolean ultimoDireccionamiento;
    @Column(name = "origen")
    private Integer origen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_tec_contratada")
    private BigDecimal valorTecContratada;
    @Size(max = 256)
    @Column(name = "causal_diferida_parcial")
    private String causalDiferidaParcial;
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
    @Column(name = "usuario_anula")
    private String usuarioAnula;
    @Size(max = 16)
    @Column(name = "terminal_anula")
    private String terminalAnula;
    @Column(name = "fecha_hora_anula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAnula;
    @OneToMany(mappedBy = "mpDireccionamientosId", fetch = FetchType.LAZY)
    private List<MpDireccionamientosProgramadas> mpDireccionamientosProgramadasList;
    @JoinColumn(name = "mp_prescripcion_insumos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionInsumos mpPrescripcionInsumosId;
    @JoinColumn(name = "mp_prescripcion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentosId;
    @JoinColumn(name = "mp_prescripcion_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionTecnologias mpPrescripcionTecnologiasId;
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;
    @OneToMany(mappedBy = "mpDireccionamientoId", fetch = FetchType.LAZY)
    private List<MpDireccionamientoEntregados> mpDireccionamientoEntregadosList;

    public MpDireccionamientos() {
    }

    public MpDireccionamientos(Integer id) {
        this.id = id;
    }

    public MpDireccionamientos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaeTipoDocumentoPrestadorId() {
        return maeTipoDocumentoPrestadorId;
    }

    public void setMaeTipoDocumentoPrestadorId(Integer maeTipoDocumentoPrestadorId) {
        this.maeTipoDocumentoPrestadorId = maeTipoDocumentoPrestadorId;
    }

    public String getMaeTipoDocumentoPrestadorCodigo() {
        return maeTipoDocumentoPrestadorCodigo;
    }

    public void setMaeTipoDocumentoPrestadorCodigo(String maeTipoDocumentoPrestadorCodigo) {
        this.maeTipoDocumentoPrestadorCodigo = maeTipoDocumentoPrestadorCodigo;
    }

    public String getMaeTipoDocumentoPrestadorValor() {
        return maeTipoDocumentoPrestadorValor;
    }

    public void setMaeTipoDocumentoPrestadorValor(String maeTipoDocumentoPrestadorValor) {
        this.maeTipoDocumentoPrestadorValor = maeTipoDocumentoPrestadorValor;
    }

    public Integer getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(Integer prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getSedeCodigoHabilitacion() {
        return sedeCodigoHabilitacion;
    }

    public void setSedeCodigoHabilitacion(String sedeCodigoHabilitacion) {
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
    }

    public String getSedeDireccionPrestador() {
        return sedeDireccionPrestador;
    }

    public void setSedeDireccionPrestador(String sedeDireccionPrestador) {
        this.sedeDireccionPrestador = sedeDireccionPrestador;
    }

    public String getSedeTelefonoPrestador() {
        return sedeTelefonoPrestador;
    }

    public void setSedeTelefonoPrestador(String sedeTelefonoPrestador) {
        this.sedeTelefonoPrestador = sedeTelefonoPrestador;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaxEntrega() {
        return fechaMaxEntrega;
    }

    public void setFechaMaxEntrega(Date fechaMaxEntrega) {
        this.fechaMaxEntrega = fechaMaxEntrega;
    }

    public Integer getConsecutivoEntrega() {
        return consecutivoEntrega;
    }

    public void setConsecutivoEntrega(Integer consecutivoEntrega) {
        this.consecutivoEntrega = consecutivoEntrega;
    }

    public Integer getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(Integer entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public Integer getEntregadoNumero() {
        return entregadoNumero;
    }

    public void setEntregadoNumero(Integer entregadoNumero) {
        this.entregadoNumero = entregadoNumero;
    }

    public Integer getEntregadoTotal() {
        return entregadoTotal;
    }

    public void setEntregadoTotal(Integer entregadoTotal) {
        this.entregadoTotal = entregadoTotal;
    }

    public Integer getEntregadoPendiente() {
        return entregadoPendiente;
    }

    public void setEntregadoPendiente(Integer entregadoPendiente) {
        this.entregadoPendiente = entregadoPendiente;
    }

    public String getJustificacionDireccionamiento() {
        return justificacionDireccionamiento;
    }

    public void setJustificacionDireccionamiento(String justificacionDireccionamiento) {
        this.justificacionDireccionamiento = justificacionDireccionamiento;
    }

    public Boolean getEnvioCorreoAuto() {
        return envioCorreoAuto;
    }

    public void setEnvioCorreoAuto(Boolean envioCorreoAuto) {
        this.envioCorreoAuto = envioCorreoAuto;
    }

    public Integer getCausaNoEntregaCod() {
        return causaNoEntregaCod;
    }

    public void setCausaNoEntregaCod(Integer causaNoEntregaCod) {
        this.causaNoEntregaCod = causaNoEntregaCod;
    }

    public Integer getTipoTecnologiaDireccionado() {
        return tipoTecnologiaDireccionado;
    }

    public void setTipoTecnologiaDireccionado(Integer tipoTecnologiaDireccionado) {
        this.tipoTecnologiaDireccionado = tipoTecnologiaDireccionado;
    }

    public String getCodigoMpEntrega() {
        return codigoMpEntrega;
    }

    public void setCodigoMpEntrega(String codigoMpEntrega) {
        this.codigoMpEntrega = codigoMpEntrega;
    }

    public String getCodigoMpPropio() {
        return codigoMpPropio;
    }

    public void setCodigoMpPropio(String codigoMpPropio) {
        this.codigoMpPropio = codigoMpPropio;
    }

    public Integer getSubEntrega() {
        return subEntrega;
    }

    public void setSubEntrega(Integer subEntrega) {
        this.subEntrega = subEntrega;
    }

    public String getCodigoPrestadorSede() {
        return codigoPrestadorSede;
    }

    public void setCodigoPrestadorSede(String codigoPrestadorSede) {
        this.codigoPrestadorSede = codigoPrestadorSede;
    }

    public String getUbicacionSedeId() {
        return ubicacionSedeId;
    }

    public void setUbicacionSedeId(String ubicacionSedeId) {
        this.ubicacionSedeId = ubicacionSedeId;
    }

    public Integer getMaeRegionSedeId() {
        return maeRegionSedeId;
    }

    public void setMaeRegionSedeId(Integer maeRegionSedeId) {
        this.maeRegionSedeId = maeRegionSedeId;
    }

    public String getMaeRegionSedeCodigo() {
        return maeRegionSedeCodigo;
    }

    public void setMaeRegionSedeCodigo(String maeRegionSedeCodigo) {
        this.maeRegionSedeCodigo = maeRegionSedeCodigo;
    }

    public String getMaeRegionSedeValor() {
        return maeRegionSedeValor;
    }

    public void setMaeRegionSedeValor(String maeRegionSedeValor) {
        this.maeRegionSedeValor = maeRegionSedeValor;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getCodigoHabilitacionSede() {
        return codigoHabilitacionSede;
    }

    public void setCodigoHabilitacionSede(String codigoHabilitacionSede) {
        this.codigoHabilitacionSede = codigoHabilitacionSede;
    }

    public String getFaxSede() {
        return faxSede;
    }

    public void setFaxSede(String faxSede) {
        this.faxSede = faxSede;
    }

    public String getTelefonoCitasSede() {
        return telefonoCitasSede;
    }

    public void setTelefonoCitasSede(String telefonoCitasSede) {
        this.telefonoCitasSede = telefonoCitasSede;
    }

    public String getCorreoElectronicoSede() {
        return correoElectronicoSede;
    }

    public void setCorreoElectronicoSede(String correoElectronicoSede) {
        this.correoElectronicoSede = correoElectronicoSede;
    }

    public String getTelefonoAdministrativoSede() {
        return telefonoAdministrativoSede;
    }

    public void setTelefonoAdministrativoSede(String telefonoAdministrativoSede) {
        this.telefonoAdministrativoSede = telefonoAdministrativoSede;
    }

    public Boolean getDireccionamientoEsEstadoAnulado() {
        return direccionamientoEsEstadoAnulado;
    }

    public void setDireccionamientoEsEstadoAnulado(Boolean direccionamientoEsEstadoAnulado) {
        this.direccionamientoEsEstadoAnulado = direccionamientoEsEstadoAnulado;
    }

    public Date getFechaAnulacionDireccionamiento() {
        return fechaAnulacionDireccionamiento;
    }

    public void setFechaAnulacionDireccionamiento(Date fechaAnulacionDireccionamiento) {
        this.fechaAnulacionDireccionamiento = fechaAnulacionDireccionamiento;
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

    public String getNumeroDocumentoPaciente() {
        return numeroDocumentoPaciente;
    }

    public void setNumeroDocumentoPaciente(String numeroDocumentoPaciente) {
        this.numeroDocumentoPaciente = numeroDocumentoPaciente;
    }

    public Integer getCodigoTipoDireccionamiento() {
        return codigoTipoDireccionamiento;
    }

    public void setCodigoTipoDireccionamiento(Integer codigoTipoDireccionamiento) {
        this.codigoTipoDireccionamiento = codigoTipoDireccionamiento;
    }

    public Integer getCodigoEntregaParcial() {
        return codigoEntregaParcial;
    }

    public void setCodigoEntregaParcial(Integer codigoEntregaParcial) {
        this.codigoEntregaParcial = codigoEntregaParcial;
    }

    public Integer getCodigoEntregaDiferida() {
        return codigoEntregaDiferida;
    }

    public void setCodigoEntregaDiferida(Integer codigoEntregaDiferida) {
        this.codigoEntregaDiferida = codigoEntregaDiferida;
    }

    public Boolean getEsEntregaParcial() {
        return esEntregaParcial;
    }

    public void setEsEntregaParcial(Boolean esEntregaParcial) {
        this.esEntregaParcial = esEntregaParcial;
    }

    public Boolean getEsEntregaDiferida() {
        return esEntregaDiferida;
    }

    public void setEsEntregaDiferida(Boolean esEntregaDiferida) {
        this.esEntregaDiferida = esEntregaDiferida;
    }

    public String getNumeroPrescripcionAsoc() {
        return numeroPrescripcionAsoc;
    }

    public void setNumeroPrescripcionAsoc(String numeroPrescripcionAsoc) {
        this.numeroPrescripcionAsoc = numeroPrescripcionAsoc;
    }

    public String getConsecutivoTecAsociada() {
        return consecutivoTecAsociada;
    }

    public void setConsecutivoTecAsociada(String consecutivoTecAsociada) {
        this.consecutivoTecAsociada = consecutivoTecAsociada;
    }

    public String getRespuestaDireccionamiento() {
        return respuestaDireccionamiento;
    }

    public void setRespuestaDireccionamiento(String respuestaDireccionamiento) {
        this.respuestaDireccionamiento = respuestaDireccionamiento;
    }

    public String getRespuestaProgramacion() {
        return respuestaProgramacion;
    }

    public void setRespuestaProgramacion(String respuestaProgramacion) {
        this.respuestaProgramacion = respuestaProgramacion;
    }

    public Integer getEstadoProgramacion() {
        return estadoProgramacion;
    }

    public void setEstadoProgramacion(Integer estadoProgramacion) {
        this.estadoProgramacion = estadoProgramacion;
    }

    public Boolean getPreeliminado() {
        return preeliminado;
    }

    public void setPreeliminado(Boolean preeliminado) {
        this.preeliminado = preeliminado;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechaEnvioAuto() {
        return fechaEnvioAuto;
    }

    public void setFechaEnvioAuto(Date fechaEnvioAuto) {
        this.fechaEnvioAuto = fechaEnvioAuto;
    }

    public Integer getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(Integer idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public Integer getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(Integer consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public Boolean getUltimoDireccionamiento() {
        return ultimoDireccionamiento;
    }

    public void setUltimoDireccionamiento(Boolean ultimoDireccionamiento) {
        this.ultimoDireccionamiento = ultimoDireccionamiento;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public BigDecimal getValorTecContratada() {
        return valorTecContratada;
    }

    public void setValorTecContratada(BigDecimal valorTecContratada) {
        this.valorTecContratada = valorTecContratada;
    }

    public String getCausalDiferidaParcial() {
        return causalDiferidaParcial;
    }

    public void setCausalDiferidaParcial(String causalDiferidaParcial) {
        this.causalDiferidaParcial = causalDiferidaParcial;
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

    public String getUsuarioAnula() {
        return usuarioAnula;
    }

    public void setUsuarioAnula(String usuarioAnula) {
        this.usuarioAnula = usuarioAnula;
    }

    public String getTerminalAnula() {
        return terminalAnula;
    }

    public void setTerminalAnula(String terminalAnula) {
        this.terminalAnula = terminalAnula;
    }

    public Date getFechaHoraAnula() {
        return fechaHoraAnula;
    }

    public void setFechaHoraAnula(Date fechaHoraAnula) {
        this.fechaHoraAnula = fechaHoraAnula;
    }

    @XmlTransient
    public List<MpDireccionamientosProgramadas> getMpDireccionamientosProgramadasList() {
        return mpDireccionamientosProgramadasList;
    }

    public void setMpDireccionamientosProgramadasList(List<MpDireccionamientosProgramadas> mpDireccionamientosProgramadasList) {
        this.mpDireccionamientosProgramadasList = mpDireccionamientosProgramadasList;
    }

    public MpPrescripcionInsumos getMpPrescripcionInsumosId() {
        return mpPrescripcionInsumosId;
    }

    public void setMpPrescripcionInsumosId(MpPrescripcionInsumos mpPrescripcionInsumosId) {
        this.mpPrescripcionInsumosId = mpPrescripcionInsumosId;
    }

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamentos mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
    }

    public MpPrescripcionTecnologias getMpPrescripcionTecnologiasId() {
        return mpPrescripcionTecnologiasId;
    }

    public void setMpPrescripcionTecnologiasId(MpPrescripcionTecnologias mpPrescripcionTecnologiasId) {
        this.mpPrescripcionTecnologiasId = mpPrescripcionTecnologiasId;
    }

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
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
        if (!(object instanceof MpDireccionamientos)) {
            return false;
        }
        MpDireccionamientos other = (MpDireccionamientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpDireccionamientos[ id=" + id + " ]";
    }
    
}
