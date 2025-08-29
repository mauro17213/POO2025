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
@Table(name = "au_anexos2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexos2.findAll", query = "SELECT a FROM AuAnexos2 a"),
    @NamedQuery(name = "AuAnexos2.findById", query = "SELECT a FROM AuAnexos2 a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexos2.findByTipo", query = "SELECT a FROM AuAnexos2 a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuAnexos2.findByEstado", query = "SELECT a FROM AuAnexos2 a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexos2.findByMaeEstadoId", query = "SELECT a FROM AuAnexos2 a WHERE a.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "AuAnexos2.findByMaeEstadoCodigo", query = "SELECT a FROM AuAnexos2 a WHERE a.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "AuAnexos2.findByMaeEstadoValor", query = "SELECT a FROM AuAnexos2 a WHERE a.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "AuAnexos2.findByComentarioEstado", query = "SELECT a FROM AuAnexos2 a WHERE a.comentarioEstado = :comentarioEstado"),
    @NamedQuery(name = "AuAnexos2.findByIdLlamada", query = "SELECT a FROM AuAnexos2 a WHERE a.idLlamada = :idLlamada"),
    @NamedQuery(name = "AuAnexos2.findByMaeOrigenAtencionId", query = "SELECT a FROM AuAnexos2 a WHERE a.maeOrigenAtencionId = :maeOrigenAtencionId"),
    @NamedQuery(name = "AuAnexos2.findByMaeOrigenAtencionCodigo", query = "SELECT a FROM AuAnexos2 a WHERE a.maeOrigenAtencionCodigo = :maeOrigenAtencionCodigo"),
    @NamedQuery(name = "AuAnexos2.findByMaeOrigenAtencionValor", query = "SELECT a FROM AuAnexos2 a WHERE a.maeOrigenAtencionValor = :maeOrigenAtencionValor"),
    @NamedQuery(name = "AuAnexos2.findByMaeDestinoPacienteId", query = "SELECT a FROM AuAnexos2 a WHERE a.maeDestinoPacienteId = :maeDestinoPacienteId"),
    @NamedQuery(name = "AuAnexos2.findByMaeDestinoPacienteCodigo", query = "SELECT a FROM AuAnexos2 a WHERE a.maeDestinoPacienteCodigo = :maeDestinoPacienteCodigo"),
    @NamedQuery(name = "AuAnexos2.findByMaeDestinoPacienteValor", query = "SELECT a FROM AuAnexos2 a WHERE a.maeDestinoPacienteValor = :maeDestinoPacienteValor"),
    @NamedQuery(name = "AuAnexos2.findByCodigoAtencionIps", query = "SELECT a FROM AuAnexos2 a WHERE a.codigoAtencionIps = :codigoAtencionIps"),
    @NamedQuery(name = "AuAnexos2.findByFechaHoraAtencion", query = "SELECT a FROM AuAnexos2 a WHERE a.fechaHoraAtencion = :fechaHoraAtencion"),
    @NamedQuery(name = "AuAnexos2.findByFechaHoraReporte", query = "SELECT a FROM AuAnexos2 a WHERE a.fechaHoraReporte = :fechaHoraReporte"),
    @NamedQuery(name = "AuAnexos2.findByMotivo", query = "SELECT a FROM AuAnexos2 a WHERE a.motivo = :motivo"),
    @NamedQuery(name = "AuAnexos2.findByRemitido", query = "SELECT a FROM AuAnexos2 a WHERE a.remitido = :remitido"),
    @NamedQuery(name = "AuAnexos2.findByRemiteNit", query = "SELECT a FROM AuAnexos2 a WHERE a.remiteNit = :remiteNit"),
    @NamedQuery(name = "AuAnexos2.findByRemiteNombre", query = "SELECT a FROM AuAnexos2 a WHERE a.remiteNombre = :remiteNombre"),
    @NamedQuery(name = "AuAnexos2.findByTriage", query = "SELECT a FROM AuAnexos2 a WHERE a.triage = :triage"),
    @NamedQuery(name = "AuAnexos2.findByInformaNombre", query = "SELECT a FROM AuAnexos2 a WHERE a.informaNombre = :informaNombre"),
    @NamedQuery(name = "AuAnexos2.findByInformaCargo", query = "SELECT a FROM AuAnexos2 a WHERE a.informaCargo = :informaCargo"),
    @NamedQuery(name = "AuAnexos2.findByInformaTelefono", query = "SELECT a FROM AuAnexos2 a WHERE a.informaTelefono = :informaTelefono"),
    @NamedQuery(name = "AuAnexos2.findByRuta", query = "SELECT a FROM AuAnexos2 a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuAnexos2.findByArchivo", query = "SELECT a FROM AuAnexos2 a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuAnexos2.findByFuenteOrigen", query = "SELECT a FROM AuAnexos2 a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuAnexos2.findByVersion", query = "SELECT a FROM AuAnexos2 a WHERE a.version = :version"),
    @NamedQuery(name = "AuAnexos2.findByConsecutivo", query = "SELECT a FROM AuAnexos2 a WHERE a.consecutivo = :consecutivo"),
    @NamedQuery(name = "AuAnexos2.findByMaeViaIngresoId", query = "SELECT a FROM AuAnexos2 a WHERE a.maeViaIngresoId = :maeViaIngresoId"),
    @NamedQuery(name = "AuAnexos2.findByMaeViaIngresoCodigo", query = "SELECT a FROM AuAnexos2 a WHERE a.maeViaIngresoCodigo = :maeViaIngresoCodigo"),
    @NamedQuery(name = "AuAnexos2.findByMaeViaIngresoValor", query = "SELECT a FROM AuAnexos2 a WHERE a.maeViaIngresoValor = :maeViaIngresoValor"),
    @NamedQuery(name = "AuAnexos2.findByMaeCondicionDestinoId", query = "SELECT a FROM AuAnexos2 a WHERE a.maeCondicionDestinoId = :maeCondicionDestinoId"),
    @NamedQuery(name = "AuAnexos2.findByMaeCondicionDestinoCodigo", query = "SELECT a FROM AuAnexos2 a WHERE a.maeCondicionDestinoCodigo = :maeCondicionDestinoCodigo"),
    @NamedQuery(name = "AuAnexos2.findByMaeCondicionDestinoValor", query = "SELECT a FROM AuAnexos2 a WHERE a.maeCondicionDestinoValor = :maeCondicionDestinoValor"),
    @NamedQuery(name = "AuAnexos2.findByAfiliadoDireccionAlternativa", query = "SELECT a FROM AuAnexos2 a WHERE a.afiliadoDireccionAlternativa = :afiliadoDireccionAlternativa"),
    @NamedQuery(name = "AuAnexos2.findByUsuarioCrea", query = "SELECT a FROM AuAnexos2 a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexos2.findByTerminalCrea", query = "SELECT a FROM AuAnexos2 a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexos2.findByFechaHoraCrea", query = "SELECT a FROM AuAnexos2 a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexos2.findByUsuarioModifica", query = "SELECT a FROM AuAnexos2 a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexos2.findByTerminalModifica", query = "SELECT a FROM AuAnexos2 a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexos2.findByFechaHoraModifica", query = "SELECT a FROM AuAnexos2 a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexos2 implements Serializable {

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
    @Size(max = 1024)
    @Column(name = "comentario_estado")
    private String comentarioEstado;
    @Size(max = 32)
    @Column(name = "id_llamada")
    private String idLlamada;
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
    @Column(name = "mae_destino_paciente_id")
    private Integer maeDestinoPacienteId;
    @Size(max = 8)
    @Column(name = "mae_destino_paciente_codigo")
    private String maeDestinoPacienteCodigo;
    @Size(max = 128)
    @Column(name = "mae_destino_paciente_valor")
    private String maeDestinoPacienteValor;
    @Size(max = 16)
    @Column(name = "codigo_atencion_ips")
    private String codigoAtencionIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_atencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAtencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraReporte;
    @Size(max = 2048)
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "remitido")
    private boolean remitido;
    @Size(max = 32)
    @Column(name = "remite_nit")
    private String remiteNit;
    @Size(max = 256)
    @Column(name = "remite_nombre")
    private String remiteNombre;
    @Column(name = "triage")
    private Integer triage;
    @Size(max = 256)
    @Column(name = "informa_nombre")
    private String informaNombre;
    @Size(max = 64)
    @Column(name = "informa_cargo")
    private String informaCargo;
    @Size(max = 16)
    @Column(name = "informa_telefono")
    private String informaTelefono;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "fuente_origen")
    private Integer fuenteOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private boolean version;
    @Size(max = 64)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "mae_via_ingreso_id")
    private Integer maeViaIngresoId;
    @Size(max = 8)
    @Column(name = "mae_via_ingreso_codigo")
    private String maeViaIngresoCodigo;
    @Size(max = 128)
    @Column(name = "mae_via_ingreso_valor")
    private String maeViaIngresoValor;
    @Column(name = "mae_condicion_destino_id")
    private Integer maeCondicionDestinoId;
    @Size(max = 8)
    @Column(name = "mae_condicion_destino_codigo")
    private String maeCondicionDestinoCodigo;
    @Size(max = 128)
    @Column(name = "mae_condicion_destino_valor")
    private String maeCondicionDestinoValor;
    @Size(max = 256)
    @Column(name = "afiliado_direccion_alternativa")
    private String afiliadoDireccionAlternativa;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_profesionales_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntProfesionales cntProfesionalesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(mappedBy = "auAnexos2Id", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos2Id", fetch = FetchType.LAZY)
    private List<AuAnexo2Adjuntos> auAnexo2AdjuntosList;
    @OneToMany(mappedBy = "auAnexos2Id", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @OneToMany(mappedBy = "auAnexos2Id", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos2Id", fetch = FetchType.LAZY)
    private List<AuAnexo2Items> auAnexo2ItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos2Id", fetch = FetchType.LAZY)
    private List<AuAnexo2Diagnosticos> auAnexo2DiagnosticosList;

    public AuAnexos2() {
    }

    public AuAnexos2(Integer id) {
        this.id = id;
    }

    public AuAnexos2(Integer id, int tipo, int maeOrigenAtencionId, String maeOrigenAtencionCodigo, String maeOrigenAtencionValor, Date fechaHoraAtencion, Date fechaHoraReporte, boolean remitido, boolean version, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.maeOrigenAtencionId = maeOrigenAtencionId;
        this.maeOrigenAtencionCodigo = maeOrigenAtencionCodigo;
        this.maeOrigenAtencionValor = maeOrigenAtencionValor;
        this.fechaHoraAtencion = fechaHoraAtencion;
        this.fechaHoraReporte = fechaHoraReporte;
        this.remitido = remitido;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getComentarioEstado() {
        return comentarioEstado;
    }

    public void setComentarioEstado(String comentarioEstado) {
        this.comentarioEstado = comentarioEstado;
    }

    public String getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(String idLlamada) {
        this.idLlamada = idLlamada;
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

    public Integer getMaeDestinoPacienteId() {
        return maeDestinoPacienteId;
    }

    public void setMaeDestinoPacienteId(Integer maeDestinoPacienteId) {
        this.maeDestinoPacienteId = maeDestinoPacienteId;
    }

    public String getMaeDestinoPacienteCodigo() {
        return maeDestinoPacienteCodigo;
    }

    public void setMaeDestinoPacienteCodigo(String maeDestinoPacienteCodigo) {
        this.maeDestinoPacienteCodigo = maeDestinoPacienteCodigo;
    }

    public String getMaeDestinoPacienteValor() {
        return maeDestinoPacienteValor;
    }

    public void setMaeDestinoPacienteValor(String maeDestinoPacienteValor) {
        this.maeDestinoPacienteValor = maeDestinoPacienteValor;
    }

    public String getCodigoAtencionIps() {
        return codigoAtencionIps;
    }

    public void setCodigoAtencionIps(String codigoAtencionIps) {
        this.codigoAtencionIps = codigoAtencionIps;
    }

    public Date getFechaHoraAtencion() {
        return fechaHoraAtencion;
    }

    public void setFechaHoraAtencion(Date fechaHoraAtencion) {
        this.fechaHoraAtencion = fechaHoraAtencion;
    }

    public Date getFechaHoraReporte() {
        return fechaHoraReporte;
    }

    public void setFechaHoraReporte(Date fechaHoraReporte) {
        this.fechaHoraReporte = fechaHoraReporte;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean getRemitido() {
        return remitido;
    }

    public void setRemitido(boolean remitido) {
        this.remitido = remitido;
    }

    public String getRemiteNit() {
        return remiteNit;
    }

    public void setRemiteNit(String remiteNit) {
        this.remiteNit = remiteNit;
    }

    public String getRemiteNombre() {
        return remiteNombre;
    }

    public void setRemiteNombre(String remiteNombre) {
        this.remiteNombre = remiteNombre;
    }

    public Integer getTriage() {
        return triage;
    }

    public void setTriage(Integer triage) {
        this.triage = triage;
    }

    public String getInformaNombre() {
        return informaNombre;
    }

    public void setInformaNombre(String informaNombre) {
        this.informaNombre = informaNombre;
    }

    public String getInformaCargo() {
        return informaCargo;
    }

    public void setInformaCargo(String informaCargo) {
        this.informaCargo = informaCargo;
    }

    public String getInformaTelefono() {
        return informaTelefono;
    }

    public void setInformaTelefono(String informaTelefono) {
        this.informaTelefono = informaTelefono;
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

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
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

    public Integer getMaeViaIngresoId() {
        return maeViaIngresoId;
    }

    public void setMaeViaIngresoId(Integer maeViaIngresoId) {
        this.maeViaIngresoId = maeViaIngresoId;
    }

    public String getMaeViaIngresoCodigo() {
        return maeViaIngresoCodigo;
    }

    public void setMaeViaIngresoCodigo(String maeViaIngresoCodigo) {
        this.maeViaIngresoCodigo = maeViaIngresoCodigo;
    }

    public String getMaeViaIngresoValor() {
        return maeViaIngresoValor;
    }

    public void setMaeViaIngresoValor(String maeViaIngresoValor) {
        this.maeViaIngresoValor = maeViaIngresoValor;
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

    public String getAfiliadoDireccionAlternativa() {
        return afiliadoDireccionAlternativa;
    }

    public void setAfiliadoDireccionAlternativa(String afiliadoDireccionAlternativa) {
        this.afiliadoDireccionAlternativa = afiliadoDireccionAlternativa;
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

    @XmlTransient
    public List<AuAnexos4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexos4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    @XmlTransient
    public List<AuAnexo2Adjuntos> getAuAnexo2AdjuntosList() {
        return auAnexo2AdjuntosList;
    }

    public void setAuAnexo2AdjuntosList(List<AuAnexo2Adjuntos> auAnexo2AdjuntosList) {
        this.auAnexo2AdjuntosList = auAnexo2AdjuntosList;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<AuAnexo2Items> getAuAnexo2ItemsList() {
        return auAnexo2ItemsList;
    }

    public void setAuAnexo2ItemsList(List<AuAnexo2Items> auAnexo2ItemsList) {
        this.auAnexo2ItemsList = auAnexo2ItemsList;
    }

    @XmlTransient
    public List<AuAnexo2Diagnosticos> getAuAnexo2DiagnosticosList() {
        return auAnexo2DiagnosticosList;
    }

    public void setAuAnexo2DiagnosticosList(List<AuAnexo2Diagnosticos> auAnexo2DiagnosticosList) {
        this.auAnexo2DiagnosticosList = auAnexo2DiagnosticosList;
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
        if (!(object instanceof AuAnexos2)) {
            return false;
        }
        AuAnexos2 other = (AuAnexos2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexos2[ id=" + id + " ]";
    }
    
}
