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
@Table(name = "cnt_contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratos.findAll", query = "SELECT c FROM CntContratos c"),
    @NamedQuery(name = "CntContratos.findById", query = "SELECT c FROM CntContratos c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratos.findByNegociacion", query = "SELECT c FROM CntContratos c WHERE c.negociacion = :negociacion"),
    @NamedQuery(name = "CntContratos.findByContrato", query = "SELECT c FROM CntContratos c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CntContratos.findByDescripcion", query = "SELECT c FROM CntContratos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntContratos.findByActivo", query = "SELECT c FROM CntContratos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntContratos.findByMaeEstadoContratoId", query = "SELECT c FROM CntContratos c WHERE c.maeEstadoContratoId = :maeEstadoContratoId"),
    @NamedQuery(name = "CntContratos.findByMaeEstadoContratoCodigo", query = "SELECT c FROM CntContratos c WHERE c.maeEstadoContratoCodigo = :maeEstadoContratoCodigo"),
    @NamedQuery(name = "CntContratos.findByMaeEstadoContratoValor", query = "SELECT c FROM CntContratos c WHERE c.maeEstadoContratoValor = :maeEstadoContratoValor"),
    @NamedQuery(name = "CntContratos.findByFechaInicio", query = "SELECT c FROM CntContratos c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntContratos.findByFechaFin", query = "SELECT c FROM CntContratos c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntContratos.findByValor", query = "SELECT c FROM CntContratos c WHERE c.valor = :valor"),
    @NamedQuery(name = "CntContratos.findByValorMes", query = "SELECT c FROM CntContratos c WHERE c.valorMes = :valorMes"),
    @NamedQuery(name = "CntContratos.findByValorPresupuestoTotal", query = "SELECT c FROM CntContratos c WHERE c.valorPresupuestoTotal = :valorPresupuestoTotal"),
    @NamedQuery(name = "CntContratos.findByDiasLimitePago", query = "SELECT c FROM CntContratos c WHERE c.diasLimitePago = :diasLimitePago"),
    @NamedQuery(name = "CntContratos.findByNumAfiliados", query = "SELECT c FROM CntContratos c WHERE c.numAfiliados = :numAfiliados"),
    @NamedQuery(name = "CntContratos.findByMaeRegimenId", query = "SELECT c FROM CntContratos c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CntContratos.findByMaeRegimenCodigo", query = "SELECT c FROM CntContratos c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CntContratos.findByMaeRegimenValor", query = "SELECT c FROM CntContratos c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CntContratos.findByAutorizaGestion", query = "SELECT c FROM CntContratos c WHERE c.autorizaGestion = :autorizaGestion"),
    @NamedQuery(name = "CntContratos.findByEjecucionContratoAutorizado", query = "SELECT c FROM CntContratos c WHERE c.ejecucionContratoAutorizado = :ejecucionContratoAutorizado"),
    @NamedQuery(name = "CntContratos.findByEjecucionContratoPrestado", query = "SELECT c FROM CntContratos c WHERE c.ejecucionContratoPrestado = :ejecucionContratoPrestado"),
    @NamedQuery(name = "CntContratos.findByEjecucionTotalContrato", query = "SELECT c FROM CntContratos c WHERE c.ejecucionTotalContrato = :ejecucionTotalContrato"),
    @NamedQuery(name = "CntContratos.findByUsuarioCrea", query = "SELECT c FROM CntContratos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratos.findByTerminalCrea", query = "SELECT c FROM CntContratos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratos.findByFechaHoraCrea", query = "SELECT c FROM CntContratos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntContratos.findByUsuarioModifica", query = "SELECT c FROM CntContratos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntContratos.findByTerminalModifica", query = "SELECT c FROM CntContratos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntContratos.findByFechaHoraModifica", query = "SELECT c FROM CntContratos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntContratos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "negociacion")
    private String negociacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "contrato")
    private String contrato;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_contrato_id")
    private int maeEstadoContratoId;
    @Size(max = 8)
    @Column(name = "mae_estado_contrato_codigo")
    private String maeEstadoContratoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_contrato_valor")
    private String maeEstadoContratoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "valor_mes")
    private BigDecimal valorMes;
    @Column(name = "valor_presupuesto_total")
    private BigDecimal valorPresupuestoTotal;
    @Column(name = "dias_limite_pago")
    private Integer diasLimitePago;
    @Column(name = "num_afiliados")
    private Integer numAfiliados;
    @Column(name = "mae_regimen_id")
    private Integer maeRegimenId;
    @Size(max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Size(max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autoriza_gestion")
    private boolean autorizaGestion;
    @Column(name = "ejecucion_contrato_autorizado")
    private BigDecimal ejecucionContratoAutorizado;
    @Column(name = "ejecucion_contrato_prestado")
    private BigDecimal ejecucionContratoPrestado;
    @Column(name = "ejecucion_total_contrato")
    private BigDecimal ejecucionTotalContrato;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoNotasTecnicas> cntContratoNotasTecnicasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoDescuentos> cntContratoDescuentosList;
    @OneToMany(mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricos> cntContratoHistoricosList;
    @OneToMany(mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoDetalles> cntContratoDetallesList;
    @OneToMany(mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CmFeRipsCargas> cmFeRipsCargasList;
    @OneToMany(mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionServicios> aucHospitalizacionServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoCoberturas> cntContratoCoberturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoSedes> cntContratoSedesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoAdjuntos> cntContratoAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoJuridicos> cntContratoJuridicosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoGirosCapita> cntContratoGirosCapitaList;
    @OneToMany(mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CmRipsCargas> cmRipsCargasList;
    @OneToMany(mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CmAuditoriaCapitaDescuentos> cmAuditoriaCapitaDescuentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratosId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public CntContratos() {
    }

    public CntContratos(Integer id) {
        this.id = id;
    }

    public CntContratos(Integer id, String contrato, boolean activo, int maeEstadoContratoId, Date fechaInicio, Date fechaFin, BigDecimal valor, boolean autorizaGestion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.contrato = contrato;
        this.activo = activo;
        this.maeEstadoContratoId = maeEstadoContratoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valor = valor;
        this.autorizaGestion = autorizaGestion;
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

    public String getNegociacion() {
        return negociacion;
    }

    public void setNegociacion(String negociacion) {
        this.negociacion = negociacion;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMaeEstadoContratoId() {
        return maeEstadoContratoId;
    }

    public void setMaeEstadoContratoId(int maeEstadoContratoId) {
        this.maeEstadoContratoId = maeEstadoContratoId;
    }

    public String getMaeEstadoContratoCodigo() {
        return maeEstadoContratoCodigo;
    }

    public void setMaeEstadoContratoCodigo(String maeEstadoContratoCodigo) {
        this.maeEstadoContratoCodigo = maeEstadoContratoCodigo;
    }

    public String getMaeEstadoContratoValor() {
        return maeEstadoContratoValor;
    }

    public void setMaeEstadoContratoValor(String maeEstadoContratoValor) {
        this.maeEstadoContratoValor = maeEstadoContratoValor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorMes() {
        return valorMes;
    }

    public void setValorMes(BigDecimal valorMes) {
        this.valorMes = valorMes;
    }

    public BigDecimal getValorPresupuestoTotal() {
        return valorPresupuestoTotal;
    }

    public void setValorPresupuestoTotal(BigDecimal valorPresupuestoTotal) {
        this.valorPresupuestoTotal = valorPresupuestoTotal;
    }

    public Integer getDiasLimitePago() {
        return diasLimitePago;
    }

    public void setDiasLimitePago(Integer diasLimitePago) {
        this.diasLimitePago = diasLimitePago;
    }

    public Integer getNumAfiliados() {
        return numAfiliados;
    }

    public void setNumAfiliados(Integer numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public boolean getAutorizaGestion() {
        return autorizaGestion;
    }

    public void setAutorizaGestion(boolean autorizaGestion) {
        this.autorizaGestion = autorizaGestion;
    }

    public BigDecimal getEjecucionContratoAutorizado() {
        return ejecucionContratoAutorizado;
    }

    public void setEjecucionContratoAutorizado(BigDecimal ejecucionContratoAutorizado) {
        this.ejecucionContratoAutorizado = ejecucionContratoAutorizado;
    }

    public BigDecimal getEjecucionContratoPrestado() {
        return ejecucionContratoPrestado;
    }

    public void setEjecucionContratoPrestado(BigDecimal ejecucionContratoPrestado) {
        this.ejecucionContratoPrestado = ejecucionContratoPrestado;
    }

    public BigDecimal getEjecucionTotalContrato() {
        return ejecucionTotalContrato;
    }

    public void setEjecucionTotalContrato(BigDecimal ejecucionTotalContrato) {
        this.ejecucionTotalContrato = ejecucionTotalContrato;
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
    public List<CntContratoNotasTecnicas> getCntContratoNotasTecnicasList() {
        return cntContratoNotasTecnicasList;
    }

    public void setCntContratoNotasTecnicasList(List<CntContratoNotasTecnicas> cntContratoNotasTecnicasList) {
        this.cntContratoNotasTecnicasList = cntContratoNotasTecnicasList;
    }

    @XmlTransient
    public List<CntContratoDescuentos> getCntContratoDescuentosList() {
        return cntContratoDescuentosList;
    }

    public void setCntContratoDescuentosList(List<CntContratoDescuentos> cntContratoDescuentosList) {
        this.cntContratoDescuentosList = cntContratoDescuentosList;
    }

    @XmlTransient
    public List<CntContratoHistoricos> getCntContratoHistoricosList() {
        return cntContratoHistoricosList;
    }

    public void setCntContratoHistoricosList(List<CntContratoHistoricos> cntContratoHistoricosList) {
        this.cntContratoHistoricosList = cntContratoHistoricosList;
    }

    @XmlTransient
    public List<AuAnexos4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexos4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    @XmlTransient
    public List<CntContratoDetalles> getCntContratoDetallesList() {
        return cntContratoDetallesList;
    }

    public void setCntContratoDetallesList(List<CntContratoDetalles> cntContratoDetallesList) {
        this.cntContratoDetallesList = cntContratoDetallesList;
    }

    @XmlTransient
    public List<CmFeRipsCargas> getCmFeRipsCargasList() {
        return cmFeRipsCargasList;
    }

    public void setCmFeRipsCargasList(List<CmFeRipsCargas> cmFeRipsCargasList) {
        this.cmFeRipsCargasList = cmFeRipsCargasList;
    }

    @XmlTransient
    public List<AucHospitalizacionServicios> getAucHospitalizacionServiciosList() {
        return aucHospitalizacionServiciosList;
    }

    public void setAucHospitalizacionServiciosList(List<AucHospitalizacionServicios> aucHospitalizacionServiciosList) {
        this.aucHospitalizacionServiciosList = aucHospitalizacionServiciosList;
    }

    @XmlTransient
    public List<CntContratoCoberturas> getCntContratoCoberturasList() {
        return cntContratoCoberturasList;
    }

    public void setCntContratoCoberturasList(List<CntContratoCoberturas> cntContratoCoberturasList) {
        this.cntContratoCoberturasList = cntContratoCoberturasList;
    }

    @XmlTransient
    public List<CntContratoSedes> getCntContratoSedesList() {
        return cntContratoSedesList;
    }

    public void setCntContratoSedesList(List<CntContratoSedes> cntContratoSedesList) {
        this.cntContratoSedesList = cntContratoSedesList;
    }

    @XmlTransient
    public List<CntContratoAdjuntos> getCntContratoAdjuntosList() {
        return cntContratoAdjuntosList;
    }

    public void setCntContratoAdjuntosList(List<CntContratoAdjuntos> cntContratoAdjuntosList) {
        this.cntContratoAdjuntosList = cntContratoAdjuntosList;
    }

    @XmlTransient
    public List<CntContratoJuridicos> getCntContratoJuridicosList() {
        return cntContratoJuridicosList;
    }

    public void setCntContratoJuridicosList(List<CntContratoJuridicos> cntContratoJuridicosList) {
        this.cntContratoJuridicosList = cntContratoJuridicosList;
    }

    @XmlTransient
    public List<CntContratoGirosCapita> getCntContratoGirosCapitaList() {
        return cntContratoGirosCapitaList;
    }

    public void setCntContratoGirosCapitaList(List<CntContratoGirosCapita> cntContratoGirosCapitaList) {
        this.cntContratoGirosCapitaList = cntContratoGirosCapitaList;
    }

    @XmlTransient
    public List<CmRipsCargas> getCmRipsCargasList() {
        return cmRipsCargasList;
    }

    public void setCmRipsCargasList(List<CmRipsCargas> cmRipsCargasList) {
        this.cmRipsCargasList = cmRipsCargasList;
    }

    @XmlTransient
    public List<CntContratoHistoricoPrestaciones> getCntContratoHistoricoPrestacionesList() {
        return cntContratoHistoricoPrestacionesList;
    }

    public void setCntContratoHistoricoPrestacionesList(List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList) {
        this.cntContratoHistoricoPrestacionesList = cntContratoHistoricoPrestacionesList;
    }

    @XmlTransient
    public List<CmAuditoriaCapitaDescuentos> getCmAuditoriaCapitaDescuentosList() {
        return cmAuditoriaCapitaDescuentosList;
    }

    public void setCmAuditoriaCapitaDescuentosList(List<CmAuditoriaCapitaDescuentos> cmAuditoriaCapitaDescuentosList) {
        this.cmAuditoriaCapitaDescuentosList = cmAuditoriaCapitaDescuentosList;
    }

    @XmlTransient
    public List<CntContratoHistoricoValidaciones> getCntContratoHistoricoValidacionesList() {
        return cntContratoHistoricoValidacionesList;
    }

    public void setCntContratoHistoricoValidacionesList(List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList) {
        this.cntContratoHistoricoValidacionesList = cntContratoHistoricoValidacionesList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntContratos)) {
            return false;
        }
        CntContratos other = (CntContratos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratos[ id=" + id + " ]";
    }
    
}
