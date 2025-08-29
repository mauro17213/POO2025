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
@Table(name = "auc_hospitalizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizaciones.findAll", query = "SELECT a FROM AucHospitalizaciones a"),
    @NamedQuery(name = "AucHospitalizaciones.findById", query = "SELECT a FROM AucHospitalizaciones a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizaciones.findByCodigoEvento", query = "SELECT a FROM AucHospitalizaciones a WHERE a.codigoEvento = :codigoEvento"),
    @NamedQuery(name = "AucHospitalizaciones.findByEstado", query = "SELECT a FROM AucHospitalizaciones a WHERE a.estado = :estado"),
    @NamedQuery(name = "AucHospitalizaciones.findByEstadoAuditoria", query = "SELECT a FROM AucHospitalizaciones a WHERE a.estadoAuditoria = :estadoAuditoria"),
    @NamedQuery(name = "AucHospitalizaciones.findByCierreAuditoria", query = "SELECT a FROM AucHospitalizaciones a WHERE a.cierreAuditoria = :cierreAuditoria"),
    @NamedQuery(name = "AucHospitalizaciones.findByFechaInicioHospitalizacion", query = "SELECT a FROM AucHospitalizaciones a WHERE a.fechaInicioHospitalizacion = :fechaInicioHospitalizacion"),
    @NamedQuery(name = "AucHospitalizaciones.findByFechaFinHospitalizacion", query = "SELECT a FROM AucHospitalizaciones a WHERE a.fechaFinHospitalizacion = :fechaFinHospitalizacion"),
    @NamedQuery(name = "AucHospitalizaciones.findByDiasHospitalizacion", query = "SELECT a FROM AucHospitalizaciones a WHERE a.diasHospitalizacion = :diasHospitalizacion"),
    @NamedQuery(name = "AucHospitalizaciones.findByFechaUltimaNota", query = "SELECT a FROM AucHospitalizaciones a WHERE a.fechaUltimaNota = :fechaUltimaNota"),
    @NamedQuery(name = "AucHospitalizaciones.findByAplicaRescate", query = "SELECT a FROM AucHospitalizaciones a WHERE a.aplicaRescate = :aplicaRescate"),
    @NamedQuery(name = "AucHospitalizaciones.findByMaEspecialidadesId", query = "SELECT a FROM AucHospitalizaciones a WHERE a.maEspecialidadesId = :maEspecialidadesId"),
    @NamedQuery(name = "AucHospitalizaciones.findByMaEspecialidadesCodigo", query = "SELECT a FROM AucHospitalizaciones a WHERE a.maEspecialidadesCodigo = :maEspecialidadesCodigo"),
    @NamedQuery(name = "AucHospitalizaciones.findByMaEspecialidadesValor", query = "SELECT a FROM AucHospitalizaciones a WHERE a.maEspecialidadesValor = :maEspecialidadesValor"),
    @NamedQuery(name = "AucHospitalizaciones.findByValorDiarioAcumulado", query = "SELECT a FROM AucHospitalizaciones a WHERE a.valorDiarioAcumulado = :valorDiarioAcumulado"),
    @NamedQuery(name = "AucHospitalizaciones.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizaciones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizaciones.findByTerminalCrea", query = "SELECT a FROM AucHospitalizaciones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizaciones.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizaciones a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizaciones.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizaciones a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizaciones.findByTerminalModifica", query = "SELECT a FROM AucHospitalizaciones a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizaciones.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizaciones a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AucHospitalizaciones.findByUsuarioCierreAuditoria", query = "SELECT a FROM AucHospitalizaciones a WHERE a.usuarioCierreAuditoria = :usuarioCierreAuditoria"),
    @NamedQuery(name = "AucHospitalizaciones.findByTerminalCierreAuditoria", query = "SELECT a FROM AucHospitalizaciones a WHERE a.terminalCierreAuditoria = :terminalCierreAuditoria"),
    @NamedQuery(name = "AucHospitalizaciones.findByFechaHoraCierreAuditoria", query = "SELECT a FROM AucHospitalizaciones a WHERE a.fechaHoraCierreAuditoria = :fechaHoraCierreAuditoria")})
public class AucHospitalizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_evento")
    private int codigoEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "estado_auditoria")
    private Integer estadoAuditoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cierre_auditoria")
    private boolean cierreAuditoria;
    @Column(name = "fecha_inicio_hospitalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioHospitalizacion;
    @Column(name = "fecha_fin_hospitalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinHospitalizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias_hospitalizacion")
    private int diasHospitalizacion;
    @Column(name = "fecha_ultima_nota")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaNota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate")
    private int aplicaRescate;
    @Column(name = "ma_especialidades_id")
    private Integer maEspecialidadesId;
    @Size(max = 32)
    @Column(name = "ma_especialidades_codigo")
    private String maEspecialidadesCodigo;
    @Size(max = 512)
    @Column(name = "ma_especialidades_valor")
    private String maEspecialidadesValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_diario_acumulado")
    private BigDecimal valorDiarioAcumulado;
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
    @Column(name = "usuario_cierre_auditoria")
    private String usuarioCierreAuditoria;
    @Size(max = 16)
    @Column(name = "terminal_cierre_auditoria")
    private String terminalCierreAuditoria;
    @Column(name = "fecha_hora_cierre_auditoria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCierreAuditoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionInoportunidades> aucHospitalizacionInoportunidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucJustificacionEstanciasProlongadas> aucJustificacionEstanciasProlongadasList;
    @OneToMany(mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<PeAfiliadosSugeridos> peAfiliadosSugeridosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionServicios> aucHospitalizacionServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionEstancias> aucHospitalizacionEstanciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionAdversos> aucHospitalizacionAdversosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionSeguimientos> aucHospitalizacionSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionPatologias> aucHospitalizacionPatologiasList;
    @OneToMany(mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<PeAfiliadosProgramas> peAfiliadosProgramasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionObjeciones> aucHospitalizacionObjecionesList;
    @OneToMany(mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionEstados> aucHospitalizacionEstadosList;
    @OneToMany(mappedBy = "aucHospitalizacionId", fetch = FetchType.LAZY)
    private List<AucDiagnosticos> aucDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucHospitalizacionesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionHistoricos> aucHospitalizacionHistoricosList;
    @JoinColumn(name = "auc_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucAfiliados aucAfiliadosId;
    @JoinColumn(name = "auc_egresos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucEgresos aucEgresosId;
    @JoinColumn(name = "auc_ingresos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucIngresos aucIngresosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_usuarios_auditor_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosAuditorId;

    public AucHospitalizaciones() {
    }

    public AucHospitalizaciones(Integer id) {
        this.id = id;
    }

    public AucHospitalizaciones(Integer id, int codigoEvento, int estado, boolean cierreAuditoria, int diasHospitalizacion, int aplicaRescate, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigoEvento = codigoEvento;
        this.estado = estado;
        this.cierreAuditoria = cierreAuditoria;
        this.diasHospitalizacion = diasHospitalizacion;
        this.aplicaRescate = aplicaRescate;
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

    public int getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Integer estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public boolean getCierreAuditoria() {
        return cierreAuditoria;
    }

    public void setCierreAuditoria(boolean cierreAuditoria) {
        this.cierreAuditoria = cierreAuditoria;
    }

    public Date getFechaInicioHospitalizacion() {
        return fechaInicioHospitalizacion;
    }

    public void setFechaInicioHospitalizacion(Date fechaInicioHospitalizacion) {
        this.fechaInicioHospitalizacion = fechaInicioHospitalizacion;
    }

    public Date getFechaFinHospitalizacion() {
        return fechaFinHospitalizacion;
    }

    public void setFechaFinHospitalizacion(Date fechaFinHospitalizacion) {
        this.fechaFinHospitalizacion = fechaFinHospitalizacion;
    }

    public int getDiasHospitalizacion() {
        return diasHospitalizacion;
    }

    public void setDiasHospitalizacion(int diasHospitalizacion) {
        this.diasHospitalizacion = diasHospitalizacion;
    }

    public Date getFechaUltimaNota() {
        return fechaUltimaNota;
    }

    public void setFechaUltimaNota(Date fechaUltimaNota) {
        this.fechaUltimaNota = fechaUltimaNota;
    }

    public int getAplicaRescate() {
        return aplicaRescate;
    }

    public void setAplicaRescate(int aplicaRescate) {
        this.aplicaRescate = aplicaRescate;
    }

    public Integer getMaEspecialidadesId() {
        return maEspecialidadesId;
    }

    public void setMaEspecialidadesId(Integer maEspecialidadesId) {
        this.maEspecialidadesId = maEspecialidadesId;
    }

    public String getMaEspecialidadesCodigo() {
        return maEspecialidadesCodigo;
    }

    public void setMaEspecialidadesCodigo(String maEspecialidadesCodigo) {
        this.maEspecialidadesCodigo = maEspecialidadesCodigo;
    }

    public String getMaEspecialidadesValor() {
        return maEspecialidadesValor;
    }

    public void setMaEspecialidadesValor(String maEspecialidadesValor) {
        this.maEspecialidadesValor = maEspecialidadesValor;
    }

    public BigDecimal getValorDiarioAcumulado() {
        return valorDiarioAcumulado;
    }

    public void setValorDiarioAcumulado(BigDecimal valorDiarioAcumulado) {
        this.valorDiarioAcumulado = valorDiarioAcumulado;
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

    public String getUsuarioCierreAuditoria() {
        return usuarioCierreAuditoria;
    }

    public void setUsuarioCierreAuditoria(String usuarioCierreAuditoria) {
        this.usuarioCierreAuditoria = usuarioCierreAuditoria;
    }

    public String getTerminalCierreAuditoria() {
        return terminalCierreAuditoria;
    }

    public void setTerminalCierreAuditoria(String terminalCierreAuditoria) {
        this.terminalCierreAuditoria = terminalCierreAuditoria;
    }

    public Date getFechaHoraCierreAuditoria() {
        return fechaHoraCierreAuditoria;
    }

    public void setFechaHoraCierreAuditoria(Date fechaHoraCierreAuditoria) {
        this.fechaHoraCierreAuditoria = fechaHoraCierreAuditoria;
    }

    @XmlTransient
    public List<AucHospitalizacionInoportunidades> getAucHospitalizacionInoportunidadesList() {
        return aucHospitalizacionInoportunidadesList;
    }

    public void setAucHospitalizacionInoportunidadesList(List<AucHospitalizacionInoportunidades> aucHospitalizacionInoportunidadesList) {
        this.aucHospitalizacionInoportunidadesList = aucHospitalizacionInoportunidadesList;
    }

    @XmlTransient
    public List<AucJustificacionEstanciasProlongadas> getAucJustificacionEstanciasProlongadasList() {
        return aucJustificacionEstanciasProlongadasList;
    }

    public void setAucJustificacionEstanciasProlongadasList(List<AucJustificacionEstanciasProlongadas> aucJustificacionEstanciasProlongadasList) {
        this.aucJustificacionEstanciasProlongadasList = aucJustificacionEstanciasProlongadasList;
    }

    @XmlTransient
    public List<PeAfiliadosSugeridos> getPeAfiliadosSugeridosList() {
        return peAfiliadosSugeridosList;
    }

    public void setPeAfiliadosSugeridosList(List<PeAfiliadosSugeridos> peAfiliadosSugeridosList) {
        this.peAfiliadosSugeridosList = peAfiliadosSugeridosList;
    }

    @XmlTransient
    public List<AucHospitalizacionServicios> getAucHospitalizacionServiciosList() {
        return aucHospitalizacionServiciosList;
    }

    public void setAucHospitalizacionServiciosList(List<AucHospitalizacionServicios> aucHospitalizacionServiciosList) {
        this.aucHospitalizacionServiciosList = aucHospitalizacionServiciosList;
    }

    @XmlTransient
    public List<AucHospitalizacionEstancias> getAucHospitalizacionEstanciasList() {
        return aucHospitalizacionEstanciasList;
    }

    public void setAucHospitalizacionEstanciasList(List<AucHospitalizacionEstancias> aucHospitalizacionEstanciasList) {
        this.aucHospitalizacionEstanciasList = aucHospitalizacionEstanciasList;
    }

    @XmlTransient
    public List<AucHospitalizacionAdversos> getAucHospitalizacionAdversosList() {
        return aucHospitalizacionAdversosList;
    }

    public void setAucHospitalizacionAdversosList(List<AucHospitalizacionAdversos> aucHospitalizacionAdversosList) {
        this.aucHospitalizacionAdversosList = aucHospitalizacionAdversosList;
    }

    @XmlTransient
    public List<AucHospitalizacionSeguimientos> getAucHospitalizacionSeguimientosList() {
        return aucHospitalizacionSeguimientosList;
    }

    public void setAucHospitalizacionSeguimientosList(List<AucHospitalizacionSeguimientos> aucHospitalizacionSeguimientosList) {
        this.aucHospitalizacionSeguimientosList = aucHospitalizacionSeguimientosList;
    }

    @XmlTransient
    public List<AucHospitalizacionPatologias> getAucHospitalizacionPatologiasList() {
        return aucHospitalizacionPatologiasList;
    }

    public void setAucHospitalizacionPatologiasList(List<AucHospitalizacionPatologias> aucHospitalizacionPatologiasList) {
        this.aucHospitalizacionPatologiasList = aucHospitalizacionPatologiasList;
    }

    @XmlTransient
    public List<PeAfiliadosProgramas> getPeAfiliadosProgramasList() {
        return peAfiliadosProgramasList;
    }

    public void setPeAfiliadosProgramasList(List<PeAfiliadosProgramas> peAfiliadosProgramasList) {
        this.peAfiliadosProgramasList = peAfiliadosProgramasList;
    }

    @XmlTransient
    public List<AucHospitalizacionObjeciones> getAucHospitalizacionObjecionesList() {
        return aucHospitalizacionObjecionesList;
    }

    public void setAucHospitalizacionObjecionesList(List<AucHospitalizacionObjeciones> aucHospitalizacionObjecionesList) {
        this.aucHospitalizacionObjecionesList = aucHospitalizacionObjecionesList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<AucHospitalizacionEstados> getAucHospitalizacionEstadosList() {
        return aucHospitalizacionEstadosList;
    }

    public void setAucHospitalizacionEstadosList(List<AucHospitalizacionEstados> aucHospitalizacionEstadosList) {
        this.aucHospitalizacionEstadosList = aucHospitalizacionEstadosList;
    }

    @XmlTransient
    public List<AucDiagnosticos> getAucDiagnosticosList() {
        return aucDiagnosticosList;
    }

    public void setAucDiagnosticosList(List<AucDiagnosticos> aucDiagnosticosList) {
        this.aucDiagnosticosList = aucDiagnosticosList;
    }

    @XmlTransient
    public List<AucHospitalizacionHistoricos> getAucHospitalizacionHistoricosList() {
        return aucHospitalizacionHistoricosList;
    }

    public void setAucHospitalizacionHistoricosList(List<AucHospitalizacionHistoricos> aucHospitalizacionHistoricosList) {
        this.aucHospitalizacionHistoricosList = aucHospitalizacionHistoricosList;
    }

    public AucAfiliados getAucAfiliadosId() {
        return aucAfiliadosId;
    }

    public void setAucAfiliadosId(AucAfiliados aucAfiliadosId) {
        this.aucAfiliadosId = aucAfiliadosId;
    }

    public AucEgresos getAucEgresosId() {
        return aucEgresosId;
    }

    public void setAucEgresosId(AucEgresos aucEgresosId) {
        this.aucEgresosId = aucEgresosId;
    }

    public AucIngresos getAucIngresosId() {
        return aucIngresosId;
    }

    public void setAucIngresosId(AucIngresos aucIngresosId) {
        this.aucIngresosId = aucIngresosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnUsuarios getGnUsuariosAuditorId() {
        return gnUsuariosAuditorId;
    }

    public void setGnUsuariosAuditorId(GnUsuarios gnUsuariosAuditorId) {
        this.gnUsuariosAuditorId = gnUsuariosAuditorId;
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
        if (!(object instanceof AucHospitalizaciones)) {
            return false;
        }
        AucHospitalizaciones other = (AucHospitalizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizaciones[ id=" + id + " ]";
    }
    
}
