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
@Table(name = "fe_consumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeConsumos.findAll", query = "SELECT f FROM FeConsumos f"),
    @NamedQuery(name = "FeConsumos.findById", query = "SELECT f FROM FeConsumos f WHERE f.id = :id"),
    @NamedQuery(name = "FeConsumos.findByPeriodo", query = "SELECT f FROM FeConsumos f WHERE f.periodo = :periodo"),
    @NamedQuery(name = "FeConsumos.findByRegistrosReportados", query = "SELECT f FROM FeConsumos f WHERE f.registrosReportados = :registrosReportados"),
    @NamedQuery(name = "FeConsumos.findByRegistrosExitosos", query = "SELECT f FROM FeConsumos f WHERE f.registrosExitosos = :registrosExitosos"),
    @NamedQuery(name = "FeConsumos.findByEstado", query = "SELECT f FROM FeConsumos f WHERE f.estado = :estado"),
    @NamedQuery(name = "FeConsumos.findByObservacion", query = "SELECT f FROM FeConsumos f WHERE f.observacion = :observacion"),
    @NamedQuery(name = "FeConsumos.findByFechaHoraInicio", query = "SELECT f FROM FeConsumos f WHERE f.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "FeConsumos.findByFechaHoraFin", query = "SELECT f FROM FeConsumos f WHERE f.fechaHoraFin = :fechaHoraFin")})
public class FeConsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo")
    @Temporal(TemporalType.DATE)
    private Date periodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros_reportados")
    private int registrosReportados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros_exitosos")
    private int registrosExitosos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;

    public FeConsumos() {
    }

    public FeConsumos(Integer id) {
        this.id = id;
    }

    public FeConsumos(Integer id, Date periodo, int registrosReportados, int registrosExitosos, short estado, Date fechaHoraInicio) {
        this.id = id;
        this.periodo = periodo;
        this.registrosReportados = registrosReportados;
        this.registrosExitosos = registrosExitosos;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

    public int getRegistrosReportados() {
        return registrosReportados;
    }

    public void setRegistrosReportados(int registrosReportados) {
        this.registrosReportados = registrosReportados;
    }

    public int getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(int registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeConsumos)) {
            return false;
        }
        FeConsumos other = (FeConsumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.FeConsumos[ id=" + id + " ]";
    }
    
}
