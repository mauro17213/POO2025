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
@Table(name = "gn_modulo_versiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnModuloVersiones.findAll", query = "SELECT g FROM GnModuloVersiones g"),
    @NamedQuery(name = "GnModuloVersiones.findById", query = "SELECT g FROM GnModuloVersiones g WHERE g.id = :id"),
    @NamedQuery(name = "GnModuloVersiones.findByVersion", query = "SELECT g FROM GnModuloVersiones g WHERE g.version = :version"),
    @NamedQuery(name = "GnModuloVersiones.findByFechaVersion", query = "SELECT g FROM GnModuloVersiones g WHERE g.fechaVersion = :fechaVersion"),
    @NamedQuery(name = "GnModuloVersiones.findByDescripcion", query = "SELECT g FROM GnModuloVersiones g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnModuloVersiones.findByUsuarioCrea", query = "SELECT g FROM GnModuloVersiones g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnModuloVersiones.findByTerminalCrea", query = "SELECT g FROM GnModuloVersiones g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnModuloVersiones.findByFechaHoraCrea", query = "SELECT g FROM GnModuloVersiones g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GnModuloVersiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "version")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_version")
    @Temporal(TemporalType.DATE)
    private Date fechaVersion;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "gn_modulos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnModulos gnModulosId;

    public GnModuloVersiones() {
    }

    public GnModuloVersiones(Integer id) {
        this.id = id;
    }

    public GnModuloVersiones(Integer id, String version, Date fechaVersion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.version = version;
        this.fechaVersion = fechaVersion;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFechaVersion() {
        return fechaVersion;
    }

    public void setFechaVersion(Date fechaVersion) {
        this.fechaVersion = fechaVersion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public GnModulos getGnModulosId() {
        return gnModulosId;
    }

    public void setGnModulosId(GnModulos gnModulosId) {
        this.gnModulosId = gnModulosId;
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
        if (!(object instanceof GnModuloVersiones)) {
            return false;
        }
        GnModuloVersiones other = (GnModuloVersiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnModuloVersiones[ id=" + id + " ]";
    }
    
}
