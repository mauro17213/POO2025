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
@Table(name = "cnt_contrato_sedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoSedes.findAll", query = "SELECT c FROM CntContratoSedes c"),
    @NamedQuery(name = "CntContratoSedes.findById", query = "SELECT c FROM CntContratoSedes c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoSedes.findByComplejidad", query = "SELECT c FROM CntContratoSedes c WHERE c.complejidad = :complejidad"),
    @NamedQuery(name = "CntContratoSedes.findByNumAfiliados", query = "SELECT c FROM CntContratoSedes c WHERE c.numAfiliados = :numAfiliados"),
    @NamedQuery(name = "CntContratoSedes.findByMaeModalidadContratoId", query = "SELECT c FROM CntContratoSedes c WHERE c.maeModalidadContratoId = :maeModalidadContratoId"),
    @NamedQuery(name = "CntContratoSedes.findByMaeModalidadContratoCodigo", query = "SELECT c FROM CntContratoSedes c WHERE c.maeModalidadContratoCodigo = :maeModalidadContratoCodigo"),
    @NamedQuery(name = "CntContratoSedes.findByMaeModalidadContratoValor", query = "SELECT c FROM CntContratoSedes c WHERE c.maeModalidadContratoValor = :maeModalidadContratoValor"),
    @NamedQuery(name = "CntContratoSedes.findByNivelAtencion", query = "SELECT c FROM CntContratoSedes c WHERE c.nivelAtencion = :nivelAtencion"),
    @NamedQuery(name = "CntContratoSedes.findByFechaInicio", query = "SELECT c FROM CntContratoSedes c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntContratoSedes.findByFechaFin", query = "SELECT c FROM CntContratoSedes c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntContratoSedes.findByValorUpcAfiliado", query = "SELECT c FROM CntContratoSedes c WHERE c.valorUpcAfiliado = :valorUpcAfiliado"),
    @NamedQuery(name = "CntContratoSedes.findByValorContrato", query = "SELECT c FROM CntContratoSedes c WHERE c.valorContrato = :valorContrato"),
    @NamedQuery(name = "CntContratoSedes.findByObservacion", query = "SELECT c FROM CntContratoSedes c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaSubsidiado", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaSubsidiado = :aplicaSubsidiado"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaContribuitivo", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaContribuitivo = :aplicaContribuitivo"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaPac", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaPac = :aplicaPac"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaGlosaExtemporanea", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaGlosaExtemporanea = :aplicaGlosaExtemporanea"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaAuditoria", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaAuditoria = :aplicaAuditoria"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaPortabilidad", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaPortabilidad = :aplicaPortabilidad"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaAgendamiento", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaAgendamiento = :aplicaAgendamiento"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaAutorizacion", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaAutorizacion = :aplicaAutorizacion"),
    @NamedQuery(name = "CntContratoSedes.findByAplicaRecaudoCopagosIps", query = "SELECT c FROM CntContratoSedes c WHERE c.aplicaRecaudoCopagosIps = :aplicaRecaudoCopagosIps"),
    @NamedQuery(name = "CntContratoSedes.findByCucon", query = "SELECT c FROM CntContratoSedes c WHERE c.cucon = :cucon"),
    @NamedQuery(name = "CntContratoSedes.findByUsuarioCrea", query = "SELECT c FROM CntContratoSedes c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoSedes.findByTerminalCrea", query = "SELECT c FROM CntContratoSedes c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoSedes.findByFechaHoraCrea", query = "SELECT c FROM CntContratoSedes c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntContratoSedes.findByUsuarioModifica", query = "SELECT c FROM CntContratoSedes c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntContratoSedes.findByTerminalModifica", query = "SELECT c FROM CntContratoSedes c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntContratoSedes.findByFechaHoraModifica", query = "SELECT c FROM CntContratoSedes c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntContratoSedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "complejidad")
    private Integer complejidad;
    @Column(name = "num_afiliados")
    private Integer numAfiliados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_modalidad_contrato_id")
    private int maeModalidadContratoId;
    @Size(max = 8)
    @Column(name = "mae_modalidad_contrato_codigo")
    private String maeModalidadContratoCodigo;
    @Size(max = 128)
    @Column(name = "mae_modalidad_contrato_valor")
    private String maeModalidadContratoValor;
    @Column(name = "nivel_atencion")
    private Integer nivelAtencion;
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
    @Column(name = "valor_upc_afiliado")
    private BigDecimal valorUpcAfiliado;
    @Column(name = "valor_contrato")
    private BigDecimal valorContrato;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "aplica_subsidiado")
    private Boolean aplicaSubsidiado;
    @Column(name = "aplica_contribuitivo")
    private Boolean aplicaContribuitivo;
    @Column(name = "aplica_pac")
    private Boolean aplicaPac;
    @Column(name = "aplica_glosa_extemporanea")
    private Boolean aplicaGlosaExtemporanea;
    @Column(name = "aplica_auditoria")
    private Boolean aplicaAuditoria;
    @Column(name = "aplica_portabilidad")
    private Boolean aplicaPortabilidad;
    @Column(name = "aplica_agendamiento")
    private Boolean aplicaAgendamiento;
    @Column(name = "aplica_autorizacion")
    private Boolean aplicaAutorizacion;
    @Column(name = "aplica_recaudo_copagos_ips")
    private Boolean aplicaRecaudoCopagosIps;
    @Size(max = 128)
    @Column(name = "cucon")
    private String cucon;
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
    @OneToMany(mappedBy = "cntContratoSedesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricos> cntContratoHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratoSedesId", fetch = FetchType.LAZY)
    private List<CntContratoDetalles> cntContratoDetallesList;
    @OneToMany(mappedBy = "cntContratoSedesId", fetch = FetchType.LAZY)
    private List<AucHospitalizacionServicios> aucHospitalizacionServiciosList;
    @OneToMany(mappedBy = "cntContratoSedesId", fetch = FetchType.LAZY)
    private List<CntContratoCoberturas> cntContratoCoberturasList;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @OneToMany(mappedBy = "cntContratoSedesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntContratoSedesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;

    public CntContratoSedes() {
    }

    public CntContratoSedes(Integer id) {
        this.id = id;
    }

    public CntContratoSedes(Integer id, int maeModalidadContratoId, Date fechaInicio, Date fechaFin, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeModalidadContratoId = maeModalidadContratoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public Integer getNumAfiliados() {
        return numAfiliados;
    }

    public void setNumAfiliados(Integer numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    public int getMaeModalidadContratoId() {
        return maeModalidadContratoId;
    }

    public void setMaeModalidadContratoId(int maeModalidadContratoId) {
        this.maeModalidadContratoId = maeModalidadContratoId;
    }

    public String getMaeModalidadContratoCodigo() {
        return maeModalidadContratoCodigo;
    }

    public void setMaeModalidadContratoCodigo(String maeModalidadContratoCodigo) {
        this.maeModalidadContratoCodigo = maeModalidadContratoCodigo;
    }

    public String getMaeModalidadContratoValor() {
        return maeModalidadContratoValor;
    }

    public void setMaeModalidadContratoValor(String maeModalidadContratoValor) {
        this.maeModalidadContratoValor = maeModalidadContratoValor;
    }

    public Integer getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(Integer nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
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

    public BigDecimal getValorUpcAfiliado() {
        return valorUpcAfiliado;
    }

    public void setValorUpcAfiliado(BigDecimal valorUpcAfiliado) {
        this.valorUpcAfiliado = valorUpcAfiliado;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(Boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public Boolean getAplicaContribuitivo() {
        return aplicaContribuitivo;
    }

    public void setAplicaContribuitivo(Boolean aplicaContribuitivo) {
        this.aplicaContribuitivo = aplicaContribuitivo;
    }

    public Boolean getAplicaPac() {
        return aplicaPac;
    }

    public void setAplicaPac(Boolean aplicaPac) {
        this.aplicaPac = aplicaPac;
    }

    public Boolean getAplicaGlosaExtemporanea() {
        return aplicaGlosaExtemporanea;
    }

    public void setAplicaGlosaExtemporanea(Boolean aplicaGlosaExtemporanea) {
        this.aplicaGlosaExtemporanea = aplicaGlosaExtemporanea;
    }

    public Boolean getAplicaAuditoria() {
        return aplicaAuditoria;
    }

    public void setAplicaAuditoria(Boolean aplicaAuditoria) {
        this.aplicaAuditoria = aplicaAuditoria;
    }

    public Boolean getAplicaPortabilidad() {
        return aplicaPortabilidad;
    }

    public void setAplicaPortabilidad(Boolean aplicaPortabilidad) {
        this.aplicaPortabilidad = aplicaPortabilidad;
    }

    public Boolean getAplicaAgendamiento() {
        return aplicaAgendamiento;
    }

    public void setAplicaAgendamiento(Boolean aplicaAgendamiento) {
        this.aplicaAgendamiento = aplicaAgendamiento;
    }

    public Boolean getAplicaAutorizacion() {
        return aplicaAutorizacion;
    }

    public void setAplicaAutorizacion(Boolean aplicaAutorizacion) {
        this.aplicaAutorizacion = aplicaAutorizacion;
    }

    public Boolean getAplicaRecaudoCopagosIps() {
        return aplicaRecaudoCopagosIps;
    }

    public void setAplicaRecaudoCopagosIps(Boolean aplicaRecaudoCopagosIps) {
        this.aplicaRecaudoCopagosIps = aplicaRecaudoCopagosIps;
    }

    public String getCucon() {
        return cucon;
    }

    public void setCucon(String cucon) {
        this.cucon = cucon;
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
    public List<CntContratoHistoricos> getCntContratoHistoricosList() {
        return cntContratoHistoricosList;
    }

    public void setCntContratoHistoricosList(List<CntContratoHistoricos> cntContratoHistoricosList) {
        this.cntContratoHistoricosList = cntContratoHistoricosList;
    }

    @XmlTransient
    public List<CntContratoDetalles> getCntContratoDetallesList() {
        return cntContratoDetallesList;
    }

    public void setCntContratoDetallesList(List<CntContratoDetalles> cntContratoDetallesList) {
        this.cntContratoDetallesList = cntContratoDetallesList;
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

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    @XmlTransient
    public List<CntContratoHistoricoPrestaciones> getCntContratoHistoricoPrestacionesList() {
        return cntContratoHistoricoPrestacionesList;
    }

    public void setCntContratoHistoricoPrestacionesList(List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList) {
        this.cntContratoHistoricoPrestacionesList = cntContratoHistoricoPrestacionesList;
    }

    @XmlTransient
    public List<CntContratoHistoricoValidaciones> getCntContratoHistoricoValidacionesList() {
        return cntContratoHistoricoValidacionesList;
    }

    public void setCntContratoHistoricoValidacionesList(List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList) {
        this.cntContratoHistoricoValidacionesList = cntContratoHistoricoValidacionesList;
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
        if (!(object instanceof CntContratoSedes)) {
            return false;
        }
        CntContratoSedes other = (CntContratoSedes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoSedes[ id=" + id + " ]";
    }
    
}
