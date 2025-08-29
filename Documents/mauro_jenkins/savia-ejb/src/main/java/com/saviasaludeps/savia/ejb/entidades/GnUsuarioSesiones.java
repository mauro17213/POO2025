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
@Table(name = "gn_usuario_sesiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnUsuarioSesiones.findAll", query = "SELECT g FROM GnUsuarioSesiones g"),
    @NamedQuery(name = "GnUsuarioSesiones.findById", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.id = :id"),
    @NamedQuery(name = "GnUsuarioSesiones.findByIdSesion", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.idSesion = :idSesion"),
    @NamedQuery(name = "GnUsuarioSesiones.findByIpServidor", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.ipServidor = :ipServidor"),
    @NamedQuery(name = "GnUsuarioSesiones.findByFechaHoraInicio", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "GnUsuarioSesiones.findByFechaHoraFin", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "GnUsuarioSesiones.findByFechaHoraUltimaGestion", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.fechaHoraUltimaGestion = :fechaHoraUltimaGestion"),
    @NamedQuery(name = "GnUsuarioSesiones.findByActiva", query = "SELECT g FROM GnUsuarioSesiones g WHERE g.activa = :activa")})
public class GnUsuarioSesiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "id_sesion")
    private String idSesion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ip_servidor")
    private String ipServidor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_ultima_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraUltimaGestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activa")
    private boolean activa;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public GnUsuarioSesiones() {
    }

    public GnUsuarioSesiones(Integer id) {
        this.id = id;
    }

    public GnUsuarioSesiones(Integer id, String idSesion, String ipServidor, Date fechaHoraInicio, Date fechaHoraUltimaGestion, boolean activa) {
        this.id = id;
        this.idSesion = idSesion;
        this.ipServidor = ipServidor;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
        this.activa = activa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Date getFechaHoraUltimaGestion() {
        return fechaHoraUltimaGestion;
    }

    public void setFechaHoraUltimaGestion(Date fechaHoraUltimaGestion) {
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
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
        if (!(object instanceof GnUsuarioSesiones)) {
            return false;
        }
        GnUsuarioSesiones other = (GnUsuarioSesiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnUsuarioSesiones[ id=" + id + " ]";
    }
    
}
