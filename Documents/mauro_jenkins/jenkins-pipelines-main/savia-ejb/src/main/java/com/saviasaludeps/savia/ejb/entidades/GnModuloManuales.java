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
@Table(name = "gn_modulo_manuales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnModuloManuales.findAll", query = "SELECT g FROM GnModuloManuales g"),
    @NamedQuery(name = "GnModuloManuales.findById", query = "SELECT g FROM GnModuloManuales g WHERE g.id = :id"),
    @NamedQuery(name = "GnModuloManuales.findByVersion", query = "SELECT g FROM GnModuloManuales g WHERE g.version = :version"),
    @NamedQuery(name = "GnModuloManuales.findByFechaVersion", query = "SELECT g FROM GnModuloManuales g WHERE g.fechaVersion = :fechaVersion"),
    @NamedQuery(name = "GnModuloManuales.findByActual", query = "SELECT g FROM GnModuloManuales g WHERE g.actual = :actual"),
    @NamedQuery(name = "GnModuloManuales.findByDescripcion", query = "SELECT g FROM GnModuloManuales g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnModuloManuales.findByNombre", query = "SELECT g FROM GnModuloManuales g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnModuloManuales.findByRuta", query = "SELECT g FROM GnModuloManuales g WHERE g.ruta = :ruta"),
    @NamedQuery(name = "GnModuloManuales.findByArchivo", query = "SELECT g FROM GnModuloManuales g WHERE g.archivo = :archivo"),
    @NamedQuery(name = "GnModuloManuales.findByTipo", query = "SELECT g FROM GnModuloManuales g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GnModuloManuales.findByUsuarioCrea", query = "SELECT g FROM GnModuloManuales g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnModuloManuales.findByTerminalCrea", query = "SELECT g FROM GnModuloManuales g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnModuloManuales.findByFechaHoraCrea", query = "SELECT g FROM GnModuloManuales g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GnModuloManuales implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual")
    private boolean actual;
    @Size(max = 124)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "tipo")
    private Integer tipo;
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

    public GnModuloManuales() {
    }

    public GnModuloManuales(Integer id) {
        this.id = id;
    }

    public GnModuloManuales(Integer id, String version, Date fechaVersion, boolean actual, String nombre, String ruta, String archivo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.version = version;
        this.fechaVersion = fechaVersion;
        this.actual = actual;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
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

    public boolean getActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof GnModuloManuales)) {
            return false;
        }
        GnModuloManuales other = (GnModuloManuales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnModuloManuales[ id=" + id + " ]";
    }
    
}
