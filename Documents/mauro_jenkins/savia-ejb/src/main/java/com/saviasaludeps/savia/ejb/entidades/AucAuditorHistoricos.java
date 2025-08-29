/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "auc_auditor_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucAuditorHistoricos.findAll", query = "SELECT a FROM AucAuditorHistoricos a"),
    @NamedQuery(name = "AucAuditorHistoricos.findById", query = "SELECT a FROM AucAuditorHistoricos a WHERE a.id = :id"),
    @NamedQuery(name = "AucAuditorHistoricos.findByFechaInicio", query = "SELECT a FROM AucAuditorHistoricos a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "AucAuditorHistoricos.findByFechaFin", query = "SELECT a FROM AucAuditorHistoricos a WHERE a.fechaFin = :fechaFin"),
    @NamedQuery(name = "AucAuditorHistoricos.findByUsuarioCrea", query = "SELECT a FROM AucAuditorHistoricos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucAuditorHistoricos.findByTerminalCrea", query = "SELECT a FROM AucAuditorHistoricos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucAuditorHistoricos.findByFechaHoraCrea", query = "SELECT a FROM AucAuditorHistoricos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AucAuditorHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
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
    @JoinColumn(name = "auc_auditores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucAuditores aucAuditoresId;

    public AucAuditorHistoricos() {
    }

    public AucAuditorHistoricos(Integer id) {
        this.id = id;
    }

    public AucAuditorHistoricos(Integer id, Date fechaInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public AucAuditores getAucAuditoresId() {
        return aucAuditoresId;
    }

    public void setAucAuditoresId(AucAuditores aucAuditoresId) {
        this.aucAuditoresId = aucAuditoresId;
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
        if (!(object instanceof AucAuditorHistoricos)) {
            return false;
        }
        AucAuditorHistoricos other = (AucAuditorHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucAuditorHistoricos[ id=" + id + " ]";
    }
    
}
