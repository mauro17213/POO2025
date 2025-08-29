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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "rt_sentencias_ejecuciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtSentenciasEjecuciones.findAll", query = "SELECT r FROM RtSentenciasEjecuciones r"),
    @NamedQuery(name = "RtSentenciasEjecuciones.findById", query = "SELECT r FROM RtSentenciasEjecuciones r WHERE r.id = :id"),
    @NamedQuery(name = "RtSentenciasEjecuciones.findByEstado", query = "SELECT r FROM RtSentenciasEjecuciones r WHERE r.estado = :estado"),
    @NamedQuery(name = "RtSentenciasEjecuciones.findByFechaHoraInicio", query = "SELECT r FROM RtSentenciasEjecuciones r WHERE r.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "RtSentenciasEjecuciones.findByFechaHoraFin", query = "SELECT r FROM RtSentenciasEjecuciones r WHERE r.fechaHoraFin = :fechaHoraFin")})
public class RtSentenciasEjecuciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @JoinColumn(name = "rt_proceso_ejecuciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtProcesoEjecuciones rtProcesoEjecucionesId;
    @JoinColumn(name = "rt_sentencias_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtSentencias rtSentenciasId;

    public RtSentenciasEjecuciones() {
    }

    public RtSentenciasEjecuciones(Integer id) {
        this.id = id;
    }

    public RtSentenciasEjecuciones(Integer id, boolean estado, Date fechaHoraInicio) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public RtProcesoEjecuciones getRtProcesoEjecucionesId() {
        return rtProcesoEjecucionesId;
    }

    public void setRtProcesoEjecucionesId(RtProcesoEjecuciones rtProcesoEjecucionesId) {
        this.rtProcesoEjecucionesId = rtProcesoEjecucionesId;
    }

    public RtSentencias getRtSentenciasId() {
        return rtSentenciasId;
    }

    public void setRtSentenciasId(RtSentencias rtSentenciasId) {
        this.rtSentenciasId = rtSentenciasId;
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
        if (!(object instanceof RtSentenciasEjecuciones)) {
            return false;
        }
        RtSentenciasEjecuciones other = (RtSentenciasEjecuciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtSentenciasEjecuciones[ id=" + id + " ]";
    }
    
}
