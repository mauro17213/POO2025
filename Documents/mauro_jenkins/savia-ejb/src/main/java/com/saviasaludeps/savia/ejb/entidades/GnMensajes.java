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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "gn_mensajes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnMensajes.findAll", query = "SELECT g FROM GnMensajes g"),
    @NamedQuery(name = "GnMensajes.findById", query = "SELECT g FROM GnMensajes g WHERE g.id = :id"),
    @NamedQuery(name = "GnMensajes.findByNombre", query = "SELECT g FROM GnMensajes g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnMensajes.findByDescripcon", query = "SELECT g FROM GnMensajes g WHERE g.descripcon = :descripcon"),
    @NamedQuery(name = "GnMensajes.findByFechaDesde", query = "SELECT g FROM GnMensajes g WHERE g.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "GnMensajes.findByFechaHasta", query = "SELECT g FROM GnMensajes g WHERE g.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "GnMensajes.findByPrioridad", query = "SELECT g FROM GnMensajes g WHERE g.prioridad = :prioridad"),
    @NamedQuery(name = "GnMensajes.findByExposicion", query = "SELECT g FROM GnMensajes g WHERE g.exposicion = :exposicion"),
    @NamedQuery(name = "GnMensajes.findByUsuarioCrea", query = "SELECT g FROM GnMensajes g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnMensajes.findByTerminalCrea", query = "SELECT g FROM GnMensajes g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnMensajes.findByFechaHoraCrea", query = "SELECT g FROM GnMensajes g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnMensajes.findByUsuarioModifica", query = "SELECT g FROM GnMensajes g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnMensajes.findByTerminalModifica", query = "SELECT g FROM GnMensajes g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnMensajes.findByFechaHoraModifica", query = "SELECT g FROM GnMensajes g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnMensajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcon")
    private String descripcon;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "contenido")
    private byte[] contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prioridad")
    private int prioridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exposicion")
    private int exposicion;
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

    public GnMensajes() {
    }

    public GnMensajes(Integer id) {
        this.id = id;
    }

    public GnMensajes(Integer id, String nombre, String descripcon, byte[] contenido, Date fechaDesde, int prioridad, int exposicion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.descripcon = descripcon;
        this.contenido = contenido;
        this.fechaDesde = fechaDesde;
        this.prioridad = prioridad;
        this.exposicion = exposicion;
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

    public String getDescripcon() {
        return descripcon;
    }

    public void setDescripcon(String descripcon) {
        this.descripcon = descripcon;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getExposicion() {
        return exposicion;
    }

    public void setExposicion(int exposicion) {
        this.exposicion = exposicion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GnMensajes)) {
            return false;
        }
        GnMensajes other = (GnMensajes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnMensajes[ id=" + id + " ]";
    }
    
}
