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
@Table(name = "gn_sms_envios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnSmsEnvios.findAll", query = "SELECT g FROM GnSmsEnvios g"),
    @NamedQuery(name = "GnSmsEnvios.findById", query = "SELECT g FROM GnSmsEnvios g WHERE g.id = :id"),
    @NamedQuery(name = "GnSmsEnvios.findByOrigen", query = "SELECT g FROM GnSmsEnvios g WHERE g.origen = :origen"),
    @NamedQuery(name = "GnSmsEnvios.findByCelulares", query = "SELECT g FROM GnSmsEnvios g WHERE g.celulares = :celulares"),
    @NamedQuery(name = "GnSmsEnvios.findByTexto", query = "SELECT g FROM GnSmsEnvios g WHERE g.texto = :texto"),
    @NamedQuery(name = "GnSmsEnvios.findByEstado", query = "SELECT g FROM GnSmsEnvios g WHERE g.estado = :estado"),
    @NamedQuery(name = "GnSmsEnvios.findByFechaHoraCrea", query = "SELECT g FROM GnSmsEnvios g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnSmsEnvios.findByFechaHoraEnvio", query = "SELECT g FROM GnSmsEnvios g WHERE g.fechaHoraEnvio = :fechaHoraEnvio")})
public class GnSmsEnvios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen")
    private int origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "celulares")
    private String celulares;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "texto")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Column(name = "fecha_hora_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;

    public GnSmsEnvios() {
    }

    public GnSmsEnvios(Integer id) {
        this.id = id;
    }

    public GnSmsEnvios(Integer id, int origen, String celulares, String texto, int estado, Date fechaHoraCrea) {
        this.id = id;
        this.origen = origen;
        this.celulares = celulares;
        this.texto = texto;
        this.estado = estado;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public String getCelulares() {
        return celulares;
    }

    public void setCelulares(String celulares) {
        this.celulares = celulares;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
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
        if (!(object instanceof GnSmsEnvios)) {
            return false;
        }
        GnSmsEnvios other = (GnSmsEnvios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnSmsEnvios[ id=" + id + " ]";
    }
    
}
