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
@Table(name = "gn_perfiles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnPerfiles.findAll", query = "SELECT g FROM GnPerfiles g"),
    @NamedQuery(name = "GnPerfiles.findById", query = "SELECT g FROM GnPerfiles g WHERE g.id = :id"),
    @NamedQuery(name = "GnPerfiles.findByNombre", query = "SELECT g FROM GnPerfiles g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnPerfiles.findByDescripcion", query = "SELECT g FROM GnPerfiles g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnPerfiles.findByActivo", query = "SELECT g FROM GnPerfiles g WHERE g.activo = :activo"),
    @NamedQuery(name = "GnPerfiles.findByUsuarioCrea", query = "SELECT g FROM GnPerfiles g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnPerfiles.findByTerminalCrea", query = "SELECT g FROM GnPerfiles g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnPerfiles.findByFechaHoraCrea", query = "SELECT g FROM GnPerfiles g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnPerfiles.findByUsuarioModifica", query = "SELECT g FROM GnPerfiles g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnPerfiles.findByTerminalModifica", query = "SELECT g FROM GnPerfiles g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnPerfiles.findByFechaHoraModifica", query = "SELECT g FROM GnPerfiles g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnPerfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnPerfiles", fetch = FetchType.LAZY)
    private List<GnPermisos> gnPermisosList;
    @OneToMany(mappedBy = "gnPerfilesId", fetch = FetchType.LAZY)
    private List<GnMaestroTipos> gnMaestroTiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnPerfilesId", fetch = FetchType.LAZY)
    private List<GnRolesPerfiles> gnRolesPerfilesList;

    public GnPerfiles() {
    }

    public GnPerfiles(Integer id) {
        this.id = id;
    }

    public GnPerfiles(Integer id, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<GnPermisos> getGnPermisosList() {
        return gnPermisosList;
    }

    public void setGnPermisosList(List<GnPermisos> gnPermisosList) {
        this.gnPermisosList = gnPermisosList;
    }

    @XmlTransient
    public List<GnMaestroTipos> getGnMaestroTiposList() {
        return gnMaestroTiposList;
    }

    public void setGnMaestroTiposList(List<GnMaestroTipos> gnMaestroTiposList) {
        this.gnMaestroTiposList = gnMaestroTiposList;
    }

    @XmlTransient
    public List<GnRolesPerfiles> getGnRolesPerfilesList() {
        return gnRolesPerfilesList;
    }

    public void setGnRolesPerfilesList(List<GnRolesPerfiles> gnRolesPerfilesList) {
        this.gnRolesPerfilesList = gnRolesPerfilesList;
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
        if (!(object instanceof GnPerfiles)) {
            return false;
        }
        GnPerfiles other = (GnPerfiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnPerfiles[ id=" + id + " ]";
    }
    
}
