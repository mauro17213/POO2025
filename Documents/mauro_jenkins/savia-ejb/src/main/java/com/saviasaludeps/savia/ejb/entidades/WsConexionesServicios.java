/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "ws_conexiones_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsConexionesServicios.findAll", query = "SELECT w FROM WsConexionesServicios w"),
    @NamedQuery(name = "WsConexionesServicios.findById", query = "SELECT w FROM WsConexionesServicios w WHERE w.id = :id")})
public class WsConexionesServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "ws_conexiones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WsConexiones wsConexionesId;
    @JoinColumn(name = "ws_servicios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WsServicios wsServiciosId;

    public WsConexionesServicios() {
    }

    public WsConexionesServicios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof WsConexionesServicios)) {
            return false;
        }
        WsConexionesServicios other = (WsConexionesServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsConexionesServicios[ id=" + id + " ]";
    }
    
}
