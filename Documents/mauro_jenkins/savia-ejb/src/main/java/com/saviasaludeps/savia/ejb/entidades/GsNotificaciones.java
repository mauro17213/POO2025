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
@Table(name = "gs_notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsNotificaciones.findAll", query = "SELECT g FROM GsNotificaciones g"),
    @NamedQuery(name = "GsNotificaciones.findById", query = "SELECT g FROM GsNotificaciones g WHERE g.id = :id"),
    @NamedQuery(name = "GsNotificaciones.findByTipo", query = "SELECT g FROM GsNotificaciones g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GsNotificaciones.findByCorreo", query = "SELECT g FROM GsNotificaciones g WHERE g.correo = :correo"),
    @NamedQuery(name = "GsNotificaciones.findByCelular", query = "SELECT g FROM GsNotificaciones g WHERE g.celular = :celular"),
    @NamedQuery(name = "GsNotificaciones.findByEncabezado", query = "SELECT g FROM GsNotificaciones g WHERE g.encabezado = :encabezado"),
    @NamedQuery(name = "GsNotificaciones.findByDetalle", query = "SELECT g FROM GsNotificaciones g WHERE g.detalle = :detalle"),
    @NamedQuery(name = "GsNotificaciones.findByEstado", query = "SELECT g FROM GsNotificaciones g WHERE g.estado = :estado"),
    @NamedQuery(name = "GsNotificaciones.findByFechaHoraCrea", query = "SELECT g FROM GsNotificaciones g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GsNotificaciones.findByFechaHoraModifica", query = "SELECT g FROM GsNotificaciones g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GsNotificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 256)
    @Column(name = "correo")
    private String correo;
    @Size(max = 16)
    @Column(name = "celular")
    private String celular;
    @Size(max = 128)
    @Column(name = "encabezado")
    private String encabezado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "gs_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GsSolicitudes gsSolicitudesId;

    public GsNotificaciones() {
    }

    public GsNotificaciones(Integer id) {
        this.id = id;
    }

    public GsNotificaciones(Integer id, int tipo, String detalle, int estado, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.detalle = detalle;
        this.estado = estado;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public GsSolicitudes getGsSolicitudesId() {
        return gsSolicitudesId;
    }

    public void setGsSolicitudesId(GsSolicitudes gsSolicitudesId) {
        this.gsSolicitudesId = gsSolicitudesId;
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
        if (!(object instanceof GsNotificaciones)) {
            return false;
        }
        GsNotificaciones other = (GsNotificaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsNotificaciones[ id=" + id + " ]";
    }
    
}
