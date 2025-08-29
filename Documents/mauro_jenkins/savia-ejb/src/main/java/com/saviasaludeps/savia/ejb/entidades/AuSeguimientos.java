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
@Table(name = "au_seguimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSeguimientos.findAll", query = "SELECT a FROM AuSeguimientos a"),
    @NamedQuery(name = "AuSeguimientos.findById", query = "SELECT a FROM AuSeguimientos a WHERE a.id = :id"),
    @NamedQuery(name = "AuSeguimientos.findByEstadoTecnologia", query = "SELECT a FROM AuSeguimientos a WHERE a.estadoTecnologia = :estadoTecnologia"),
    @NamedQuery(name = "AuSeguimientos.findByMaeEstadoSeguimientoId", query = "SELECT a FROM AuSeguimientos a WHERE a.maeEstadoSeguimientoId = :maeEstadoSeguimientoId"),
    @NamedQuery(name = "AuSeguimientos.findByMaeEstadoSeguimientoCodigo", query = "SELECT a FROM AuSeguimientos a WHERE a.maeEstadoSeguimientoCodigo = :maeEstadoSeguimientoCodigo"),
    @NamedQuery(name = "AuSeguimientos.findByMaeEstadoSeguimientoValor", query = "SELECT a FROM AuSeguimientos a WHERE a.maeEstadoSeguimientoValor = :maeEstadoSeguimientoValor"),
    @NamedQuery(name = "AuSeguimientos.findByTipoTecnologia", query = "SELECT a FROM AuSeguimientos a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuSeguimientos.findByMaTecnologiaId", query = "SELECT a FROM AuSeguimientos a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuSeguimientos.findByMaTecnologiaCodigo", query = "SELECT a FROM AuSeguimientos a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuSeguimientos.findByMaTecnologiaValor", query = "SELECT a FROM AuSeguimientos a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuSeguimientos.findByMaeTipoServicioId", query = "SELECT a FROM AuSeguimientos a WHERE a.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "AuSeguimientos.findByMaeTipoServicioCodigo", query = "SELECT a FROM AuSeguimientos a WHERE a.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "AuSeguimientos.findByMaeTipoServicioValor", query = "SELECT a FROM AuSeguimientos a WHERE a.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "AuSeguimientos.findByMaeAcompananteTipoDocumentoId", query = "SELECT a FROM AuSeguimientos a WHERE a.maeAcompananteTipoDocumentoId = :maeAcompananteTipoDocumentoId"),
    @NamedQuery(name = "AuSeguimientos.findByMaeAcompananteTipoDocumentoCodigo", query = "SELECT a FROM AuSeguimientos a WHERE a.maeAcompananteTipoDocumentoCodigo = :maeAcompananteTipoDocumentoCodigo"),
    @NamedQuery(name = "AuSeguimientos.findByMaeAcompananteTipoDocumentoValor", query = "SELECT a FROM AuSeguimientos a WHERE a.maeAcompananteTipoDocumentoValor = :maeAcompananteTipoDocumentoValor"),
    @NamedQuery(name = "AuSeguimientos.findByAcompananteDocumento", query = "SELECT a FROM AuSeguimientos a WHERE a.acompananteDocumento = :acompananteDocumento"),
    @NamedQuery(name = "AuSeguimientos.findByAcompanantePrimerNombre", query = "SELECT a FROM AuSeguimientos a WHERE a.acompanantePrimerNombre = :acompanantePrimerNombre"),
    @NamedQuery(name = "AuSeguimientos.findByAcompananteSegundoNombre", query = "SELECT a FROM AuSeguimientos a WHERE a.acompananteSegundoNombre = :acompananteSegundoNombre"),
    @NamedQuery(name = "AuSeguimientos.findByAcompanantePrimerApellido", query = "SELECT a FROM AuSeguimientos a WHERE a.acompanantePrimerApellido = :acompanantePrimerApellido"),
    @NamedQuery(name = "AuSeguimientos.findByAcompananteSegundoApellido", query = "SELECT a FROM AuSeguimientos a WHERE a.acompananteSegundoApellido = :acompananteSegundoApellido"),
    @NamedQuery(name = "AuSeguimientos.findByAcompananteTelefono", query = "SELECT a FROM AuSeguimientos a WHERE a.acompananteTelefono = :acompananteTelefono"),
    @NamedQuery(name = "AuSeguimientos.findByAcompananteDireccionResidencia", query = "SELECT a FROM AuSeguimientos a WHERE a.acompananteDireccionResidencia = :acompananteDireccionResidencia"),
    @NamedQuery(name = "AuSeguimientos.findByAcompananteBarrioResidencia", query = "SELECT a FROM AuSeguimientos a WHERE a.acompananteBarrioResidencia = :acompananteBarrioResidencia"),
    @NamedQuery(name = "AuSeguimientos.findByMaeAmbitoAtencionId", query = "SELECT a FROM AuSeguimientos a WHERE a.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "AuSeguimientos.findByMaeAmbitoAtencionCodigo", query = "SELECT a FROM AuSeguimientos a WHERE a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "AuSeguimientos.findByMaeAmbitoAtencionValor", query = "SELECT a FROM AuSeguimientos a WHERE a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor"),
    @NamedQuery(name = "AuSeguimientos.findByAplicaPrestador", query = "SELECT a FROM AuSeguimientos a WHERE a.aplicaPrestador = :aplicaPrestador"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraPrimeraGestion", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraPrimeraGestion = :fechaHoraPrimeraGestion"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraUltimaGestion", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraUltimaGestion = :fechaHoraUltimaGestion"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraAtiende", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraAtiende = :fechaHoraAtiende"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraAsignaPrestador", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraAsignaPrestador = :fechaHoraAsignaPrestador"),
    @NamedQuery(name = "AuSeguimientos.findByUsuarioCrea", query = "SELECT a FROM AuSeguimientos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSeguimientos.findByTerminalCrea", query = "SELECT a FROM AuSeguimientos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraCrea", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuSeguimientos.findByUsuarioModifica", query = "SELECT a FROM AuSeguimientos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuSeguimientos.findByTerminalModifica", query = "SELECT a FROM AuSeguimientos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraModifica", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuSeguimientos.findByUsuarioBorra", query = "SELECT a FROM AuSeguimientos a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuSeguimientos.findByTerminalBorra", query = "SELECT a FROM AuSeguimientos a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuSeguimientos.findByFechaHoraBorra", query = "SELECT a FROM AuSeguimientos a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuSeguimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_tecnologia")
    private int estadoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_seguimiento_id")
    private int maeEstadoSeguimientoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_seguimiento_codigo")
    private String maeEstadoSeguimientoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_seguimiento_valor")
    private String maeEstadoSeguimientoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_servicio_id")
    private int maeTipoServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Column(name = "mae_acompanante_tipo_documento_id")
    private Integer maeAcompananteTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_acompanante_tipo_documento_codigo")
    private String maeAcompananteTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_acompanante_tipo_documento_valor")
    private String maeAcompananteTipoDocumentoValor;
    @Size(max = 32)
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
    @Size(max = 16)
    @Column(name = "acompanante_telefono")
    private String acompananteTelefono;
    @Size(max = 1024)
    @Column(name = "acompanante_direccion_residencia")
    private String acompananteDireccionResidencia;
    @Size(max = 64)
    @Column(name = "acompanante_barrio_residencia")
    private String acompananteBarrioResidencia;
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
    @Column(name = "aplica_prestador")
    private Boolean aplicaPrestador;
    @Column(name = "fecha_hora_primera_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraPrimeraGestion;
    @Column(name = "fecha_hora_ultima_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUltimaGestion;
    @Column(name = "fecha_hora_atiende")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAtiende;
    @Column(name = "fecha_hora_asigna_prestador")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAsignaPrestador;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auSeguimientoId", fetch = FetchType.LAZY)
    private List<AuSeguimientoServicios> auSeguimientoServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auSeguimientosId", fetch = FetchType.LAZY)
    private List<AuSeguimientoPrestadorAsignados> auSeguimientoPrestadorAsignadosList;
    @OneToMany(mappedBy = "auSeguimientosId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @JoinColumn(name = "au_anexo3_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3Items auAnexo3ItemsId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @JoinColumn(name = "au_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuGrupos auGruposId;
    @JoinColumn(name = "au_seguimiento_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuSeguimientoAfiliados auSeguimientoAfiliadosId;
    @JoinColumn(name = "cnt_prestador_sede_asignado_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedeAsignadoId;
    @JoinColumn(name = "gn_acompanante_residencia_ubicacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones gnAcompananteResidenciaUbicacionId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auSeguimientosId", fetch = FetchType.LAZY)
    private List<AuSeguimientoGestiones> auSeguimientoGestionesList;

    public AuSeguimientos() {
    }

    public AuSeguimientos(Integer id) {
        this.id = id;
    }

    public AuSeguimientos(Integer id, int estadoTecnologia, int maeEstadoSeguimientoId, String maeEstadoSeguimientoCodigo, String maeEstadoSeguimientoValor, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, int maeTipoServicioId, String maeTipoServicioCodigo, String maeTipoServicioValor, int maeAmbitoAtencionId, String maeAmbitoAtencionCodigo, String maeAmbitoAtencionValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estadoTecnologia = estadoTecnologia;
        this.maeEstadoSeguimientoId = maeEstadoSeguimientoId;
        this.maeEstadoSeguimientoCodigo = maeEstadoSeguimientoCodigo;
        this.maeEstadoSeguimientoValor = maeEstadoSeguimientoValor;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.maeTipoServicioId = maeTipoServicioId;
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
        this.maeTipoServicioValor = maeTipoServicioValor;
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
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

    public int getEstadoTecnologia() {
        return estadoTecnologia;
    }

    public void setEstadoTecnologia(int estadoTecnologia) {
        this.estadoTecnologia = estadoTecnologia;
    }

    public int getMaeEstadoSeguimientoId() {
        return maeEstadoSeguimientoId;
    }

    public void setMaeEstadoSeguimientoId(int maeEstadoSeguimientoId) {
        this.maeEstadoSeguimientoId = maeEstadoSeguimientoId;
    }

    public String getMaeEstadoSeguimientoCodigo() {
        return maeEstadoSeguimientoCodigo;
    }

    public void setMaeEstadoSeguimientoCodigo(String maeEstadoSeguimientoCodigo) {
        this.maeEstadoSeguimientoCodigo = maeEstadoSeguimientoCodigo;
    }

    public String getMaeEstadoSeguimientoValor() {
        return maeEstadoSeguimientoValor;
    }

    public void setMaeEstadoSeguimientoValor(String maeEstadoSeguimientoValor) {
        this.maeEstadoSeguimientoValor = maeEstadoSeguimientoValor;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
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

    public int getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(int maeTipoServicioId) {
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

    public String getAcompananteDireccionResidencia() {
        return acompananteDireccionResidencia;
    }

    public void setAcompananteDireccionResidencia(String acompananteDireccionResidencia) {
        this.acompananteDireccionResidencia = acompananteDireccionResidencia;
    }

    public String getAcompananteBarrioResidencia() {
        return acompananteBarrioResidencia;
    }

    public void setAcompananteBarrioResidencia(String acompananteBarrioResidencia) {
        this.acompananteBarrioResidencia = acompananteBarrioResidencia;
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

    public Boolean getAplicaPrestador() {
        return aplicaPrestador;
    }

    public void setAplicaPrestador(Boolean aplicaPrestador) {
        this.aplicaPrestador = aplicaPrestador;
    }

    public Date getFechaHoraPrimeraGestion() {
        return fechaHoraPrimeraGestion;
    }

    public void setFechaHoraPrimeraGestion(Date fechaHoraPrimeraGestion) {
        this.fechaHoraPrimeraGestion = fechaHoraPrimeraGestion;
    }

    public Date getFechaHoraUltimaGestion() {
        return fechaHoraUltimaGestion;
    }

    public void setFechaHoraUltimaGestion(Date fechaHoraUltimaGestion) {
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    public Date getFechaHoraAsignaPrestador() {
        return fechaHoraAsignaPrestador;
    }

    public void setFechaHoraAsignaPrestador(Date fechaHoraAsignaPrestador) {
        this.fechaHoraAsignaPrestador = fechaHoraAsignaPrestador;
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

    @XmlTransient
    public List<AuSeguimientoServicios> getAuSeguimientoServiciosList() {
        return auSeguimientoServiciosList;
    }

    public void setAuSeguimientoServiciosList(List<AuSeguimientoServicios> auSeguimientoServiciosList) {
        this.auSeguimientoServiciosList = auSeguimientoServiciosList;
    }

    @XmlTransient
    public List<AuSeguimientoPrestadorAsignados> getAuSeguimientoPrestadorAsignadosList() {
        return auSeguimientoPrestadorAsignadosList;
    }

    public void setAuSeguimientoPrestadorAsignadosList(List<AuSeguimientoPrestadorAsignados> auSeguimientoPrestadorAsignadosList) {
        this.auSeguimientoPrestadorAsignadosList = auSeguimientoPrestadorAsignadosList;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public AuAnexo3Items getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Items auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public AuGrupos getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupos auGruposId) {
        this.auGruposId = auGruposId;
    }

    public AuSeguimientoAfiliados getAuSeguimientoAfiliadosId() {
        return auSeguimientoAfiliadosId;
    }

    public void setAuSeguimientoAfiliadosId(AuSeguimientoAfiliados auSeguimientoAfiliadosId) {
        this.auSeguimientoAfiliadosId = auSeguimientoAfiliadosId;
    }

    public CntPrestadorSedes getCntPrestadorSedeAsignadoId() {
        return cntPrestadorSedeAsignadoId;
    }

    public void setCntPrestadorSedeAsignadoId(CntPrestadorSedes cntPrestadorSedeAsignadoId) {
        this.cntPrestadorSedeAsignadoId = cntPrestadorSedeAsignadoId;
    }

    public GnUbicaciones getGnAcompananteResidenciaUbicacionId() {
        return gnAcompananteResidenciaUbicacionId;
    }

    public void setGnAcompananteResidenciaUbicacionId(GnUbicaciones gnAcompananteResidenciaUbicacionId) {
        this.gnAcompananteResidenciaUbicacionId = gnAcompananteResidenciaUbicacionId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    @XmlTransient
    public List<AuSeguimientoGestiones> getAuSeguimientoGestionesList() {
        return auSeguimientoGestionesList;
    }

    public void setAuSeguimientoGestionesList(List<AuSeguimientoGestiones> auSeguimientoGestionesList) {
        this.auSeguimientoGestionesList = auSeguimientoGestionesList;
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
        if (!(object instanceof AuSeguimientos)) {
            return false;
        }
        AuSeguimientos other = (AuSeguimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSeguimientos[ id=" + id + " ]";
    }
    
}
