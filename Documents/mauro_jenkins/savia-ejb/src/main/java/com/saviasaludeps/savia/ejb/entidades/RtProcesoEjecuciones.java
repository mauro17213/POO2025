/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "rt_proceso_ejecuciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtProcesoEjecuciones.findAll", query = "SELECT r FROM RtProcesoEjecuciones r"),
    @NamedQuery(name = "RtProcesoEjecuciones.findById", query = "SELECT r FROM RtProcesoEjecuciones r WHERE r.id = :id"),
    @NamedQuery(name = "RtProcesoEjecuciones.findByEstado", query = "SELECT r FROM RtProcesoEjecuciones r WHERE r.estado = :estado"),
    @NamedQuery(name = "RtProcesoEjecuciones.findByFechaHoraInicio", query = "SELECT r FROM RtProcesoEjecuciones r WHERE r.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "RtProcesoEjecuciones.findByFechaHoraFin", query = "SELECT r FROM RtProcesoEjecuciones r WHERE r.fechaHoraFin = :fechaHoraFin")})
public class RtProcesoEjecuciones implements Serializable {

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
    @JoinColumn(name = "rt_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtProcesos rtProcesosId;
    @JoinColumn(name = "rt_reservas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtReservas rtReservasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtProcesoEjecucionesId", fetch = FetchType.LAZY)
    private List<RtSentenciasEjecuciones> rtSentenciasEjecucionesList;

    public RtProcesoEjecuciones() {
    }

    public RtProcesoEjecuciones(Integer id) {
        this.id = id;
    }

    public RtProcesoEjecuciones(Integer id, boolean estado, Date fechaHoraInicio) {
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

    public RtProcesos getRtProcesosId() {
        return rtProcesosId;
    }

    public void setRtProcesosId(RtProcesos rtProcesosId) {
        this.rtProcesosId = rtProcesosId;
    }

    public RtReservas getRtReservasId() {
        return rtReservasId;
    }

    public void setRtReservasId(RtReservas rtReservasId) {
        this.rtReservasId = rtReservasId;
    }

    @XmlTransient
    public List<RtSentenciasEjecuciones> getRtSentenciasEjecucionesList() {
        return rtSentenciasEjecucionesList;
    }

    public void setRtSentenciasEjecucionesList(List<RtSentenciasEjecuciones> rtSentenciasEjecucionesList) {
        this.rtSentenciasEjecucionesList = rtSentenciasEjecucionesList;
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
        if (!(object instanceof RtProcesoEjecuciones)) {
            return false;
        }
        RtProcesoEjecuciones other = (RtProcesoEjecuciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtProcesoEjecuciones[ id=" + id + " ]";
    }
    
}
