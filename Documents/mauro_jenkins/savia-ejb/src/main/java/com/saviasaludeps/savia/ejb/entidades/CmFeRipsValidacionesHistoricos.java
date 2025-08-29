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
@Table(name = "cm_fe_rips_validaciones_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeRipsValidacionesHistoricos.findAll", query = "SELECT c FROM CmFeRipsValidacionesHistoricos c"),
    @NamedQuery(name = "CmFeRipsValidacionesHistoricos.findById", query = "SELECT c FROM CmFeRipsValidacionesHistoricos c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeRipsValidacionesHistoricos.findByEstado", query = "SELECT c FROM CmFeRipsValidacionesHistoricos c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmFeRipsValidacionesHistoricos.findByUsuarioCrea", query = "SELECT c FROM CmFeRipsValidacionesHistoricos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFeRipsValidacionesHistoricos.findByTerminalCrea", query = "SELECT c FROM CmFeRipsValidacionesHistoricos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFeRipsValidacionesHistoricos.findByFechaHoraCrea", query = "SELECT c FROM CmFeRipsValidacionesHistoricos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmFeRipsValidacionesHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
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
    @JoinColumn(name = "cm_fe_rips_validaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFeRipsValidaciones cmFeRipsValidacionesId;

    public CmFeRipsValidacionesHistoricos() {
    }

    public CmFeRipsValidacionesHistoricos(Integer id) {
        this.id = id;
    }

    public CmFeRipsValidacionesHistoricos(Integer id, boolean estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public CmFeRipsValidaciones getCmFeRipsValidacionesId() {
        return cmFeRipsValidacionesId;
    }

    public void setCmFeRipsValidacionesId(CmFeRipsValidaciones cmFeRipsValidacionesId) {
        this.cmFeRipsValidacionesId = cmFeRipsValidacionesId;
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
        if (!(object instanceof CmFeRipsValidacionesHistoricos)) {
            return false;
        }
        CmFeRipsValidacionesHistoricos other = (CmFeRipsValidacionesHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeRipsValidacionesHistoricos[ id=" + id + " ]";
    }
    
}
