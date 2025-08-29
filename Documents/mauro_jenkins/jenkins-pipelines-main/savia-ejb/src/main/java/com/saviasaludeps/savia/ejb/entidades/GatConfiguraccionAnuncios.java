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
@Table(name = "gat_configuraccion_anuncios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatConfiguraccionAnuncios.findAll", query = "SELECT g FROM GatConfiguraccionAnuncios g"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findById", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.id = :id"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByNombre", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByDescripcion", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByFechaDesde", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByFechaHasta", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByUrl", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.url = :url"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByUsuarioCrea", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByTerminalCrea", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatConfiguraccionAnuncios.findByFechaHoraCrea", query = "SELECT g FROM GatConfiguraccionAnuncios g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatConfiguraccionAnuncios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    @Size(max = 2048)
    @Column(name = "url")
    private String url;
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

    public GatConfiguraccionAnuncios() {
    }

    public GatConfiguraccionAnuncios(Integer id) {
        this.id = id;
    }

    public GatConfiguraccionAnuncios(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GatConfiguraccionAnuncios)) {
            return false;
        }
        GatConfiguraccionAnuncios other = (GatConfiguraccionAnuncios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatConfiguraccionAnuncios[ id=" + id + " ]";
    }
    
}
