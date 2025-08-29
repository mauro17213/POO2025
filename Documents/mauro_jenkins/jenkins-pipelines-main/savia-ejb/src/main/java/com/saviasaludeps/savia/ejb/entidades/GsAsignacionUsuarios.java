/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "gs_asignacion_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsAsignacionUsuarios.findAll", query = "SELECT g FROM GsAsignacionUsuarios g"),
    @NamedQuery(name = "GsAsignacionUsuarios.findById", query = "SELECT g FROM GsAsignacionUsuarios g WHERE g.id = :id"),
    @NamedQuery(name = "GsAsignacionUsuarios.findByTipoSolicitud", query = "SELECT g FROM GsAsignacionUsuarios g WHERE g.tipoSolicitud = :tipoSolicitud"),
    @NamedQuery(name = "GsAsignacionUsuarios.findByZonasId", query = "SELECT g FROM GsAsignacionUsuarios g WHERE g.zonasId = :zonasId"),
    @NamedQuery(name = "GsAsignacionUsuarios.findByUbicacionesId", query = "SELECT g FROM GsAsignacionUsuarios g WHERE g.ubicacionesId = :ubicacionesId"),
    @NamedQuery(name = "GsAsignacionUsuarios.findByUsuarioId", query = "SELECT g FROM GsAsignacionUsuarios g WHERE g.usuarioId = :usuarioId")})
public class GsAsignacionUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_solicitud")
    private int tipoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zonas_id")
    private int zonasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ubicaciones_id")
    private int ubicacionesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_id")
    private int usuarioId;

    public GsAsignacionUsuarios() {
    }

    public GsAsignacionUsuarios(Integer id) {
        this.id = id;
    }

    public GsAsignacionUsuarios(Integer id, int tipoSolicitud, int zonasId, int ubicacionesId, int usuarioId) {
        this.id = id;
        this.tipoSolicitud = tipoSolicitud;
        this.zonasId = zonasId;
        this.ubicacionesId = ubicacionesId;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(int tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public int getZonasId() {
        return zonasId;
    }

    public void setZonasId(int zonasId) {
        this.zonasId = zonasId;
    }

    public int getUbicacionesId() {
        return ubicacionesId;
    }

    public void setUbicacionesId(int ubicacionesId) {
        this.ubicacionesId = ubicacionesId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof GsAsignacionUsuarios)) {
            return false;
        }
        GsAsignacionUsuarios other = (GsAsignacionUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsAsignacionUsuarios[ id=" + id + " ]";
    }
    
}
