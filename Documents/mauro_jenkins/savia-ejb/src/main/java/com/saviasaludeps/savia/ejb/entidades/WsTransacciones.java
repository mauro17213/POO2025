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
import javax.persistence.Lob;
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
@Table(name = "ws_transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsTransacciones.findAll", query = "SELECT w FROM WsTransacciones w"),
    @NamedQuery(name = "WsTransacciones.findById", query = "SELECT w FROM WsTransacciones w WHERE w.id = :id"),
    @NamedQuery(name = "WsTransacciones.findByEstado", query = "SELECT w FROM WsTransacciones w WHERE w.estado = :estado"),
    @NamedQuery(name = "WsTransacciones.findByIpSolicitud", query = "SELECT w FROM WsTransacciones w WHERE w.ipSolicitud = :ipSolicitud"),
    @NamedQuery(name = "WsTransacciones.findByUsuario", query = "SELECT w FROM WsTransacciones w WHERE w.usuario = :usuario"),
    @NamedQuery(name = "WsTransacciones.findByToken", query = "SELECT w FROM WsTransacciones w WHERE w.token = :token"),
    @NamedQuery(name = "WsTransacciones.findByFechaHoraSolicitud", query = "SELECT w FROM WsTransacciones w WHERE w.fechaHoraSolicitud = :fechaHoraSolicitud"),
    @NamedQuery(name = "WsTransacciones.findByFechaHoraRespuesta", query = "SELECT w FROM WsTransacciones w WHERE w.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "WsTransacciones.findByContingencia", query = "SELECT w FROM WsTransacciones w WHERE w.contingencia = :contingencia")})
public class WsTransacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ip_solicitud")
    private String ipSolicitud;
    @Size(max = 512)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 512)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSolicitud;
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;
    @Column(name = "contingencia")
    private Boolean contingencia;
    @Lob
    @Column(name = "json_solicitud")
    private byte[] jsonSolicitud;
    @Lob
    @Column(name = "json_respuesta")
    private byte[] jsonRespuesta;
    @JoinColumn(name = "ws_conexiones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WsConexiones wsConexionesId;
    @JoinColumn(name = "ws_servicios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WsServicios wsServiciosId;

    public WsTransacciones() {
    }

    public WsTransacciones(Integer id) {
        this.id = id;
    }

    public WsTransacciones(Integer id, int estado, String ipSolicitud, Date fechaHoraSolicitud) {
        this.id = id;
        this.estado = estado;
        this.ipSolicitud = ipSolicitud;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIpSolicitud() {
        return ipSolicitud;
    }

    public void setIpSolicitud(String ipSolicitud) {
        this.ipSolicitud = ipSolicitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public Boolean getContingencia() {
        return contingencia;
    }

    public void setContingencia(Boolean contingencia) {
        this.contingencia = contingencia;
    }

    public byte[] getJsonSolicitud() {
        return jsonSolicitud;
    }

    public void setJsonSolicitud(byte[] jsonSolicitud) {
        this.jsonSolicitud = jsonSolicitud;
    }

    public byte[] getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(byte[] jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public WsConexiones getWsConexionesId() {
        return wsConexionesId;
    }

    public void setWsConexionesId(WsConexiones wsConexionesId) {
        this.wsConexionesId = wsConexionesId;
    }

    public WsServicios getWsServiciosId() {
        return wsServiciosId;
    }

    public void setWsServiciosId(WsServicios wsServiciosId) {
        this.wsServiciosId = wsServiciosId;
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
        if (!(object instanceof WsTransacciones)) {
            return false;
        }
        WsTransacciones other = (WsTransacciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsTransacciones[ id=" + id + " ]";
    }
    
}
