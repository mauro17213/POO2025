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
@Table(name = "cntj_contrato_supervisores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratoSupervisores.findAll", query = "SELECT c FROM CntjContratoSupervisores c"),
    @NamedQuery(name = "CntjContratoSupervisores.findById", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratoSupervisores.findByFechaInicio", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntjContratoSupervisores.findByFechaFin", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntjContratoSupervisores.findByEtapaDesignacion", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.etapaDesignacion = :etapaDesignacion"),
    @NamedQuery(name = "CntjContratoSupervisores.findByEstadoSupervision", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.estadoSupervision = :estadoSupervision"),
    @NamedQuery(name = "CntjContratoSupervisores.findByInformesPendientes", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.informesPendientes = :informesPendientes"),
    @NamedQuery(name = "CntjContratoSupervisores.findByUsuarioCrea", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratoSupervisores.findByTerminalCrea", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratoSupervisores.findByFechaHoraCrea", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjContratoSupervisores.findByUsuarioModifica", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjContratoSupervisores.findByTerminalModifica", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjContratoSupervisores.findByFechaHoraModifica", query = "SELECT c FROM CntjContratoSupervisores c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjContratoSupervisores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "etapa_designacion")
    private Integer etapaDesignacion;
    @Column(name = "estado_supervision")
    private Integer estadoSupervision;
    @Column(name = "informes_pendientes")
    private Integer informesPendientes;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratoSupervisoresId", fetch = FetchType.LAZY)
    private List<CntjContratoInformes> cntjContratoInformesList;
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;
    @JoinColumn(name = "cntj_terceros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjTerceros cntjTercerosId;

    public CntjContratoSupervisores() {
    }

    public CntjContratoSupervisores(Integer id) {
        this.id = id;
    }

    public CntjContratoSupervisores(Integer id, Date fechaInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicio = fechaInicio;
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

    public Integer getEtapaDesignacion() {
        return etapaDesignacion;
    }

    public void setEtapaDesignacion(Integer etapaDesignacion) {
        this.etapaDesignacion = etapaDesignacion;
    }

    public Integer getEstadoSupervision() {
        return estadoSupervision;
    }

    public void setEstadoSupervision(Integer estadoSupervision) {
        this.estadoSupervision = estadoSupervision;
    }

    public Integer getInformesPendientes() {
        return informesPendientes;
    }

    public void setInformesPendientes(Integer informesPendientes) {
        this.informesPendientes = informesPendientes;
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
    public List<CntjContratoInformes> getCntjContratoInformesList() {
        return cntjContratoInformesList;
    }

    public void setCntjContratoInformesList(List<CntjContratoInformes> cntjContratoInformesList) {
        this.cntjContratoInformesList = cntjContratoInformesList;
    }

    public CntjContratos getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContratos cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
    }

    public CntjTerceros getCntjTercerosId() {
        return cntjTercerosId;
    }

    public void setCntjTercerosId(CntjTerceros cntjTercerosId) {
        this.cntjTercerosId = cntjTercerosId;
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
        if (!(object instanceof CntjContratoSupervisores)) {
            return false;
        }
        CntjContratoSupervisores other = (CntjContratoSupervisores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratoSupervisores[ id=" + id + " ]";
    }
    
}
