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
@Table(name = "cm_fe_mercurio_sincronizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findAll", query = "SELECT c FROM CmFeMercurioSincronizaciones c"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findById", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByPeriodo", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.periodo = :periodo"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByFechaHoraInicio", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByFechaHoraFin", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByDuracion", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.duracion = :duracion"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByEstado", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByDescripcion", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByRegistrosConsultados", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.registrosConsultados = :registrosConsultados"),
    @NamedQuery(name = "CmFeMercurioSincronizaciones.findByRegistrosActualizados", query = "SELECT c FROM CmFeMercurioSincronizaciones c WHERE c.registrosActualizados = :registrosActualizados")})
public class CmFeMercurioSincronizaciones implements Serializable {

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
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "duracion")
    private Integer duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Size(max = 128)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "registros_consultados")
    private Integer registrosConsultados;
    @Column(name = "registros_actualizados")
    private Integer registrosActualizados;

    public CmFeMercurioSincronizaciones() {
    }

    public CmFeMercurioSincronizaciones(Integer id) {
        this.id = id;
    }

    public CmFeMercurioSincronizaciones(Integer id, Date periodo, Date fechaHoraInicio, short estado) {
        this.id = id;
        this.periodo = periodo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
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

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getRegistrosConsultados() {
        return registrosConsultados;
    }

    public void setRegistrosConsultados(Integer registrosConsultados) {
        this.registrosConsultados = registrosConsultados;
    }

    public Integer getRegistrosActualizados() {
        return registrosActualizados;
    }

    public void setRegistrosActualizados(Integer registrosActualizados) {
        this.registrosActualizados = registrosActualizados;
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
        if (!(object instanceof CmFeMercurioSincronizaciones)) {
            return false;
        }
        CmFeMercurioSincronizaciones other = (CmFeMercurioSincronizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeMercurioSincronizaciones[ id=" + id + " ]";
    }
    
}
