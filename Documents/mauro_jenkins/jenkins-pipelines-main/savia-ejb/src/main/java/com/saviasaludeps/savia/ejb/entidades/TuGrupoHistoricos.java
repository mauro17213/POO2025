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
@Table(name = "tu_grupo_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuGrupoHistoricos.findAll", query = "SELECT t FROM TuGrupoHistoricos t"),
    @NamedQuery(name = "TuGrupoHistoricos.findById", query = "SELECT t FROM TuGrupoHistoricos t WHERE t.id = :id"),
    @NamedQuery(name = "TuGrupoHistoricos.findByToString", query = "SELECT t FROM TuGrupoHistoricos t WHERE t.toString = :toString"),
    @NamedQuery(name = "TuGrupoHistoricos.findByUsuarioCrea", query = "SELECT t FROM TuGrupoHistoricos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuGrupoHistoricos.findByTerminalCrea", query = "SELECT t FROM TuGrupoHistoricos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuGrupoHistoricos.findByFechaHoraCrea", query = "SELECT t FROM TuGrupoHistoricos t WHERE t.fechaHoraCrea = :fechaHoraCrea")})
public class TuGrupoHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8192)
    @Column(name = "toString")
    private String toString;
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
    @JoinColumn(name = "tu_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuGrupos tuGruposId;

    public TuGrupoHistoricos() {
    }

    public TuGrupoHistoricos(Integer id) {
        this.id = id;
    }

    public TuGrupoHistoricos(Integer id, String toString, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.toString = toString;
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

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
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

    public TuGrupos getTuGruposId() {
        return tuGruposId;
    }

    public void setTuGruposId(TuGrupos tuGruposId) {
        this.tuGruposId = tuGruposId;
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
        if (!(object instanceof TuGrupoHistoricos)) {
            return false;
        }
        TuGrupoHistoricos other = (TuGrupoHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuGrupoHistoricos[ id=" + id + " ]";
    }
    
}
