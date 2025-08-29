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
@Table(name = "ws_tokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsTokens.findAll", query = "SELECT w FROM WsTokens w"),
    @NamedQuery(name = "WsTokens.findById", query = "SELECT w FROM WsTokens w WHERE w.id = :id"),
    @NamedQuery(name = "WsTokens.findByToken", query = "SELECT w FROM WsTokens w WHERE w.token = :token"),
    @NamedQuery(name = "WsTokens.findByFechaHoraInicio", query = "SELECT w FROM WsTokens w WHERE w.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "WsTokens.findByTiempo", query = "SELECT w FROM WsTokens w WHERE w.tiempo = :tiempo"),
    @NamedQuery(name = "WsTokens.findByFechaHoraFin", query = "SELECT w FROM WsTokens w WHERE w.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "WsTokens.findByIpSolicita", query = "SELECT w FROM WsTokens w WHERE w.ipSolicita = :ipSolicita"),
    @NamedQuery(name = "WsTokens.findByTransacciones", query = "SELECT w FROM WsTokens w WHERE w.transacciones = :transacciones")})
public class WsTokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo")
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ip_solicita")
    private String ipSolicita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transacciones")
    private int transacciones;
    @JoinColumn(name = "ws_conexiones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WsConexiones wsConexionesId;

    public WsTokens() {
    }

    public WsTokens(Integer id) {
        this.id = id;
    }

    public WsTokens(Integer id, String token, Date fechaHoraInicio, int tiempo, Date fechaHoraFin, String ipSolicita, int transacciones) {
        this.id = id;
        this.token = token;
        this.fechaHoraInicio = fechaHoraInicio;
        this.tiempo = tiempo;
        this.fechaHoraFin = fechaHoraFin;
        this.ipSolicita = ipSolicita;
        this.transacciones = transacciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getIpSolicita() {
        return ipSolicita;
    }

    public void setIpSolicita(String ipSolicita) {
        this.ipSolicita = ipSolicita;
    }

    public int getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(int transacciones) {
        this.transacciones = transacciones;
    }

    public WsConexiones getWsConexionesId() {
        return wsConexionesId;
    }

    public void setWsConexionesId(WsConexiones wsConexionesId) {
        this.wsConexionesId = wsConexionesId;
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
        if (!(object instanceof WsTokens)) {
            return false;
        }
        WsTokens other = (WsTokens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsTokens[ id=" + id + " ]";
    }
    
}
