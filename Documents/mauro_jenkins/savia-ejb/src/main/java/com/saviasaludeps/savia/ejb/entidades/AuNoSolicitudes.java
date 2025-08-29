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
@Table(name = "au_no_solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudes.findAll", query = "SELECT a FROM AuNoSolicitudes a"),
    @NamedQuery(name = "AuNoSolicitudes.findById", query = "SELECT a FROM AuNoSolicitudes a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudes.findByCntPrestadorSedesId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.cntPrestadorSedesId = :cntPrestadorSedesId"),
    @NamedQuery(name = "AuNoSolicitudes.findByFuenteOrigen", query = "SELECT a FROM AuNoSolicitudes a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuNoSolicitudes.findByEstado", query = "SELECT a FROM AuNoSolicitudes a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuNoSolicitudes.findByEstadoJustificacion", query = "SELECT a FROM AuNoSolicitudes a WHERE a.estadoJustificacion = :estadoJustificacion"),
    @NamedQuery(name = "AuNoSolicitudes.findByCntPrestadorEntregaId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.cntPrestadorEntregaId = :cntPrestadorEntregaId"),
    @NamedQuery(name = "AuNoSolicitudes.findByCntPrestadorSedeEntregaId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.cntPrestadorSedeEntregaId = :cntPrestadorSedeEntregaId"),
    @NamedQuery(name = "AuNoSolicitudes.findByAuNoSolicitudCargasId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.auNoSolicitudCargasId = :auNoSolicitudCargasId"),
    @NamedQuery(name = "AuNoSolicitudes.findByFechaOrdenMedica", query = "SELECT a FROM AuNoSolicitudes a WHERE a.fechaOrdenMedica = :fechaOrdenMedica"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeAmbitoAtencionId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeAmbitoAtencionCodigo", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeAmbitoAtencionValor", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeAmbitoAtencionValor = :maeAmbitoAtencionValor"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeAmbitoAtencionTipo", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeAmbitoAtencionTipo = :maeAmbitoAtencionTipo"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaServicioHabilitacionId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maServicioHabilitacionId = :maServicioHabilitacionId"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaServicioHabilitacionCodigo", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maServicioHabilitacionCodigo = :maServicioHabilitacionCodigo"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaServicioHabilitacionValor", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maServicioHabilitacionValor = :maServicioHabilitacionValor"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaEspecialidadId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maEspecialidadId = :maEspecialidadId"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaEspecialidadCodigo", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maEspecialidadCodigo = :maEspecialidadCodigo"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaEspecialidadValor", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maEspecialidadValor = :maEspecialidadValor"),
    @NamedQuery(name = "AuNoSolicitudes.findByJustificacionClinica", query = "SELECT a FROM AuNoSolicitudes a WHERE a.justificacionClinica = :justificacionClinica"),
    @NamedQuery(name = "AuNoSolicitudes.findByConsecutivoOrden", query = "SELECT a FROM AuNoSolicitudes a WHERE a.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "AuNoSolicitudes.findByTutela", query = "SELECT a FROM AuNoSolicitudes a WHERE a.tutela = :tutela"),
    @NamedQuery(name = "AuNoSolicitudes.findByTipoFormula", query = "SELECT a FROM AuNoSolicitudes a WHERE a.tipoFormula = :tipoFormula"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeMotivoSinAutorizacionId", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeMotivoSinAutorizacionId = :maeMotivoSinAutorizacionId"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeMotivoSinAutorizacionTipo", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeMotivoSinAutorizacionTipo = :maeMotivoSinAutorizacionTipo"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeMotivoSinAutorizacionValor", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeMotivoSinAutorizacionValor = :maeMotivoSinAutorizacionValor"),
    @NamedQuery(name = "AuNoSolicitudes.findByMaeMotivoSinAutorizacionCodigo", query = "SELECT a FROM AuNoSolicitudes a WHERE a.maeMotivoSinAutorizacionCodigo = :maeMotivoSinAutorizacionCodigo"),
    @NamedQuery(name = "AuNoSolicitudes.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudes a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudes.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudes a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudes.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudes a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuNoSolicitudes.findByUsuarioModifica", query = "SELECT a FROM AuNoSolicitudes a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuNoSolicitudes.findByTerminalModifica", query = "SELECT a FROM AuNoSolicitudes a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuNoSolicitudes.findByFechaHoraModifica", query = "SELECT a FROM AuNoSolicitudes a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuNoSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_prestador_sedes_id")
    private int cntPrestadorSedesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private int fuenteOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 1024)
    @Column(name = "estado_justificacion")
    private String estadoJustificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_prestador_entrega_id")
    private int cntPrestadorEntregaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_prestador_sede_entrega_id")
    private int cntPrestadorSedeEntregaId;
    @Column(name = "au_no_solicitud_cargas_id")
    private Integer auNoSolicitudCargasId;
    @Column(name = "fecha_orden_medica")
    @Temporal(TemporalType.DATE)
    private Date fechaOrdenMedica;
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
    @Size(min = 1, max = 4)
    @Column(name = "mae_ambito_atencion_tipo")
    private String maeAmbitoAtencionTipo;
    @Column(name = "ma_servicio_habilitacion_id")
    private Integer maServicioHabilitacionId;
    @Size(max = 32)
    @Column(name = "ma_servicio_habilitacion_codigo")
    private String maServicioHabilitacionCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_habilitacion_valor")
    private String maServicioHabilitacionValor;
    @Column(name = "ma_especialidad_id")
    private Integer maEspecialidadId;
    @Size(max = 32)
    @Column(name = "ma_especialidad_codigo")
    private String maEspecialidadCodigo;
    @Size(max = 512)
    @Column(name = "ma_especialidad_valor")
    private String maEspecialidadValor;
    @Size(max = 2048)
    @Column(name = "justificacion_clinica")
    private String justificacionClinica;
    @Size(max = 64)
    @Column(name = "consecutivo_orden")
    private String consecutivoOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tutela")
    private boolean tutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_formula")
    private int tipoFormula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_sin_autorizacion_id")
    private int maeMotivoSinAutorizacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_motivo_sin_autorizacion_tipo")
    private String maeMotivoSinAutorizacionTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_motivo_sin_autorizacion_valor")
    private String maeMotivoSinAutorizacionValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_motivo_sin_autorizacion_codigo")
    private String maeMotivoSinAutorizacionCodigo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auNoSolicitudesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregas> auNoSolicitudEntregasList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prestador_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadorId;
    @JoinColumn(name = "cnt_profesionales_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntProfesionales cntProfesionalesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auNoSolicitudesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudDiagnosticos> auNoSolicitudDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auNoSolicitudesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudItems> auNoSolicitudItemsList;
    @OneToMany(mappedBy = "auNoSolicitudId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudCargaDetalles> auNoSolicitudCargaDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auNoSolicitudesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudHistoricos> auNoSolicitudHistoricosList;
    @OneToMany(mappedBy = "auNoSolicitudesId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;

    public AuNoSolicitudes() {
    }

    public AuNoSolicitudes(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudes(Integer id, int cntPrestadorSedesId, int fuenteOrigen, int estado, int cntPrestadorEntregaId, int cntPrestadorSedeEntregaId, int maeAmbitoAtencionId, String maeAmbitoAtencionCodigo, String maeAmbitoAtencionValor, String maeAmbitoAtencionTipo, boolean tutela, int tipoFormula, int maeMotivoSinAutorizacionId, String maeMotivoSinAutorizacionTipo, String maeMotivoSinAutorizacionValor, String maeMotivoSinAutorizacionCodigo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.cntPrestadorSedesId = cntPrestadorSedesId;
        this.fuenteOrigen = fuenteOrigen;
        this.estado = estado;
        this.cntPrestadorEntregaId = cntPrestadorEntregaId;
        this.cntPrestadorSedeEntregaId = cntPrestadorSedeEntregaId;
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
        this.maeAmbitoAtencionTipo = maeAmbitoAtencionTipo;
        this.tutela = tutela;
        this.tipoFormula = tipoFormula;
        this.maeMotivoSinAutorizacionId = maeMotivoSinAutorizacionId;
        this.maeMotivoSinAutorizacionTipo = maeMotivoSinAutorizacionTipo;
        this.maeMotivoSinAutorizacionValor = maeMotivoSinAutorizacionValor;
        this.maeMotivoSinAutorizacionCodigo = maeMotivoSinAutorizacionCodigo;
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

    public int getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(int cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public int getCntPrestadorEntregaId() {
        return cntPrestadorEntregaId;
    }

    public void setCntPrestadorEntregaId(int cntPrestadorEntregaId) {
        this.cntPrestadorEntregaId = cntPrestadorEntregaId;
    }

    public int getCntPrestadorSedeEntregaId() {
        return cntPrestadorSedeEntregaId;
    }

    public void setCntPrestadorSedeEntregaId(int cntPrestadorSedeEntregaId) {
        this.cntPrestadorSedeEntregaId = cntPrestadorSedeEntregaId;
    }

    public Integer getAuNoSolicitudCargasId() {
        return auNoSolicitudCargasId;
    }

    public void setAuNoSolicitudCargasId(Integer auNoSolicitudCargasId) {
        this.auNoSolicitudCargasId = auNoSolicitudCargasId;
    }

    public Date getFechaOrdenMedica() {
        return fechaOrdenMedica;
    }

    public void setFechaOrdenMedica(Date fechaOrdenMedica) {
        this.fechaOrdenMedica = fechaOrdenMedica;
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

    public String getMaeAmbitoAtencionTipo() {
        return maeAmbitoAtencionTipo;
    }

    public void setMaeAmbitoAtencionTipo(String maeAmbitoAtencionTipo) {
        this.maeAmbitoAtencionTipo = maeAmbitoAtencionTipo;
    }

    public Integer getMaServicioHabilitacionId() {
        return maServicioHabilitacionId;
    }

    public void setMaServicioHabilitacionId(Integer maServicioHabilitacionId) {
        this.maServicioHabilitacionId = maServicioHabilitacionId;
    }

    public String getMaServicioHabilitacionCodigo() {
        return maServicioHabilitacionCodigo;
    }

    public void setMaServicioHabilitacionCodigo(String maServicioHabilitacionCodigo) {
        this.maServicioHabilitacionCodigo = maServicioHabilitacionCodigo;
    }

    public String getMaServicioHabilitacionValor() {
        return maServicioHabilitacionValor;
    }

    public void setMaServicioHabilitacionValor(String maServicioHabilitacionValor) {
        this.maServicioHabilitacionValor = maServicioHabilitacionValor;
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

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public String getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(String consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public boolean getTutela() {
        return tutela;
    }

    public void setTutela(boolean tutela) {
        this.tutela = tutela;
    }

    public int getTipoFormula() {
        return tipoFormula;
    }

    public void setTipoFormula(int tipoFormula) {
        this.tipoFormula = tipoFormula;
    }

    public int getMaeMotivoSinAutorizacionId() {
        return maeMotivoSinAutorizacionId;
    }

    public void setMaeMotivoSinAutorizacionId(int maeMotivoSinAutorizacionId) {
        this.maeMotivoSinAutorizacionId = maeMotivoSinAutorizacionId;
    }

    public String getMaeMotivoSinAutorizacionTipo() {
        return maeMotivoSinAutorizacionTipo;
    }

    public void setMaeMotivoSinAutorizacionTipo(String maeMotivoSinAutorizacionTipo) {
        this.maeMotivoSinAutorizacionTipo = maeMotivoSinAutorizacionTipo;
    }

    public String getMaeMotivoSinAutorizacionValor() {
        return maeMotivoSinAutorizacionValor;
    }

    public void setMaeMotivoSinAutorizacionValor(String maeMotivoSinAutorizacionValor) {
        this.maeMotivoSinAutorizacionValor = maeMotivoSinAutorizacionValor;
    }

    public String getMaeMotivoSinAutorizacionCodigo() {
        return maeMotivoSinAutorizacionCodigo;
    }

    public void setMaeMotivoSinAutorizacionCodigo(String maeMotivoSinAutorizacionCodigo) {
        this.maeMotivoSinAutorizacionCodigo = maeMotivoSinAutorizacionCodigo;
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
    public List<AuNoSolicitudEntregas> getAuNoSolicitudEntregasList() {
        return auNoSolicitudEntregasList;
    }

    public void setAuNoSolicitudEntregasList(List<AuNoSolicitudEntregas> auNoSolicitudEntregasList) {
        this.auNoSolicitudEntregasList = auNoSolicitudEntregasList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadores getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(CntPrestadores cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
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
    public List<AuNoSolicitudDiagnosticos> getAuNoSolicitudDiagnosticosList() {
        return auNoSolicitudDiagnosticosList;
    }

    public void setAuNoSolicitudDiagnosticosList(List<AuNoSolicitudDiagnosticos> auNoSolicitudDiagnosticosList) {
        this.auNoSolicitudDiagnosticosList = auNoSolicitudDiagnosticosList;
    }

    @XmlTransient
    public List<AuNoSolicitudItems> getAuNoSolicitudItemsList() {
        return auNoSolicitudItemsList;
    }

    public void setAuNoSolicitudItemsList(List<AuNoSolicitudItems> auNoSolicitudItemsList) {
        this.auNoSolicitudItemsList = auNoSolicitudItemsList;
    }

    @XmlTransient
    public List<AuNoSolicitudCargaDetalles> getAuNoSolicitudCargaDetallesList() {
        return auNoSolicitudCargaDetallesList;
    }

    public void setAuNoSolicitudCargaDetallesList(List<AuNoSolicitudCargaDetalles> auNoSolicitudCargaDetallesList) {
        this.auNoSolicitudCargaDetallesList = auNoSolicitudCargaDetallesList;
    }

    @XmlTransient
    public List<AuNoSolicitudHistoricos> getAuNoSolicitudHistoricosList() {
        return auNoSolicitudHistoricosList;
    }

    public void setAuNoSolicitudHistoricosList(List<AuNoSolicitudHistoricos> auNoSolicitudHistoricosList) {
        this.auNoSolicitudHistoricosList = auNoSolicitudHistoricosList;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
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
        if (!(object instanceof AuNoSolicitudes)) {
            return false;
        }
        AuNoSolicitudes other = (AuNoSolicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudes[ id=" + id + " ]";
    }
    
}
