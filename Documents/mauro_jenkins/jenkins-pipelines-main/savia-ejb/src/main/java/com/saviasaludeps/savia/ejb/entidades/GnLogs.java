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
@Table(name = "gn_logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnLogs.findAll", query = "SELECT g FROM GnLogs g"),
    @NamedQuery(name = "GnLogs.findById", query = "SELECT g FROM GnLogs g WHERE g.id = :id"),
    @NamedQuery(name = "GnLogs.findByModulo", query = "SELECT g FROM GnLogs g WHERE g.modulo = :modulo"),
    @NamedQuery(name = "GnLogs.findByAccion", query = "SELECT g FROM GnLogs g WHERE g.accion = :accion"),
    @NamedQuery(name = "GnLogs.findByOpcion", query = "SELECT g FROM GnLogs g WHERE g.opcion = :opcion"),
    @NamedQuery(name = "GnLogs.findByEmpresaEjecucion", query = "SELECT g FROM GnLogs g WHERE g.empresaEjecucion = :empresaEjecucion"),
    @NamedQuery(name = "GnLogs.findByUsuario", query = "SELECT g FROM GnLogs g WHERE g.usuario = :usuario"),
    @NamedQuery(name = "GnLogs.findByEmpresaUsuario", query = "SELECT g FROM GnLogs g WHERE g.empresaUsuario = :empresaUsuario"),
    @NamedQuery(name = "GnLogs.findByDescripcion", query = "SELECT g FROM GnLogs g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnLogs.findByFechaHora", query = "SELECT g FROM GnLogs g WHERE g.fechaHora = :fechaHora"),
    @NamedQuery(name = "GnLogs.findByIpPublica", query = "SELECT g FROM GnLogs g WHERE g.ipPublica = :ipPublica"),
    @NamedQuery(name = "GnLogs.findByIpPrivada", query = "SELECT g FROM GnLogs g WHERE g.ipPrivada = :ipPrivada")})
public class GnLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "modulo")
    private String modulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "accion")
    private String accion;
    @Size(max = 32)
    @Column(name = "opcion")
    private String opcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "empresa_ejecucion")
    private String empresaEjecucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "empresa_usuario")
    private String empresaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32768)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Size(max = 64)
    @Column(name = "ip_publica")
    private String ipPublica;
    @Size(max = 64)
    @Column(name = "ip_privada")
    private String ipPrivada;

    public GnLogs() {
    }

    public GnLogs(Integer id) {
        this.id = id;
    }

    public GnLogs(Integer id, String modulo, String accion, String empresaEjecucion, String usuario, String empresaUsuario, String descripcion, Date fechaHora) {
        this.id = id;
        this.modulo = modulo;
        this.accion = accion;
        this.empresaEjecucion = empresaEjecucion;
        this.usuario = usuario;
        this.empresaUsuario = empresaUsuario;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getEmpresaEjecucion() {
        return empresaEjecucion;
    }

    public void setEmpresaEjecucion(String empresaEjecucion) {
        this.empresaEjecucion = empresaEjecucion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmpresaUsuario() {
        return empresaUsuario;
    }

    public void setEmpresaUsuario(String empresaUsuario) {
        this.empresaUsuario = empresaUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIpPublica() {
        return ipPublica;
    }

    public void setIpPublica(String ipPublica) {
        this.ipPublica = ipPublica;
    }

    public String getIpPrivada() {
        return ipPrivada;
    }

    public void setIpPrivada(String ipPrivada) {
        this.ipPrivada = ipPrivada;
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
        if (!(object instanceof GnLogs)) {
            return false;
        }
        GnLogs other = (GnLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnLogs[ id=" + id + " ]";
    }
    
}
