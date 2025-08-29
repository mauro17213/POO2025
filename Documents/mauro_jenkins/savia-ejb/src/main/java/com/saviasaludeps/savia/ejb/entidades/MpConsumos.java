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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "mp_consumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpConsumos.findAll", query = "SELECT m FROM MpConsumos m"),
    @NamedQuery(name = "MpConsumos.findById", query = "SELECT m FROM MpConsumos m WHERE m.id = :id"),
    @NamedQuery(name = "MpConsumos.findByPeriodo", query = "SELECT m FROM MpConsumos m WHERE m.periodo = :periodo"),
    @NamedQuery(name = "MpConsumos.findByServicio", query = "SELECT m FROM MpConsumos m WHERE m.servicio = :servicio"),
    @NamedQuery(name = "MpConsumos.findByRegistros", query = "SELECT m FROM MpConsumos m WHERE m.registros = :registros"),
    @NamedQuery(name = "MpConsumos.findByRegistrosExitosos", query = "SELECT m FROM MpConsumos m WHERE m.registrosExitosos = :registrosExitosos"),
    @NamedQuery(name = "MpConsumos.findByEstado", query = "SELECT m FROM MpConsumos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpConsumos.findByObservacion", query = "SELECT m FROM MpConsumos m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MpConsumos.findByServicioDireccion", query = "SELECT m FROM MpConsumos m WHERE m.servicioDireccion = :servicioDireccion"),
    @NamedQuery(name = "MpConsumos.findByServicioDescripcion", query = "SELECT m FROM MpConsumos m WHERE m.servicioDescripcion = :servicioDescripcion"),
    @NamedQuery(name = "MpConsumos.findByFechaHoraInicio", query = "SELECT m FROM MpConsumos m WHERE m.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "MpConsumos.findByFechaHoraFin", query = "SELECT m FROM MpConsumos m WHERE m.fechaHoraFin = :fechaHoraFin")})
public class MpConsumos implements Serializable {

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
    @Size(min = 1, max = 2)
    @Column(name = "servicio")
    private String servicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros")
    private int registros;
    @Column(name = "registros_exitosos")
    private Integer registrosExitosos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 8)
    @Column(name = "servicio_direccion")
    private String servicioDireccion;
    @Size(max = 32)
    @Column(name = "servicio_descripcion")
    private String servicioDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpConsumosId", fetch = FetchType.LAZY)
    private List<MpConsumoFallos> mpConsumoFallosList;

    public MpConsumos() {
    }

    public MpConsumos(Integer id) {
        this.id = id;
    }

    public MpConsumos(Integer id, Date periodo, String servicio, int registros, int estado, String observacion, Date fechaHoraInicio) {
        this.id = id;
        this.periodo = periodo;
        this.servicio = servicio;
        this.registros = registros;
        this.estado = estado;
        this.observacion = observacion;
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

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public Integer getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(Integer registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getServicioDireccion() {
        return servicioDireccion;
    }

    public void setServicioDireccion(String servicioDireccion) {
        this.servicioDireccion = servicioDireccion;
    }

    public String getServicioDescripcion() {
        return servicioDescripcion;
    }

    public void setServicioDescripcion(String servicioDescripcion) {
        this.servicioDescripcion = servicioDescripcion;
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

    @XmlTransient
    public List<MpConsumoFallos> getMpConsumoFallosList() {
        return mpConsumoFallosList;
    }

    public void setMpConsumoFallosList(List<MpConsumoFallos> mpConsumoFallosList) {
        this.mpConsumoFallosList = mpConsumoFallosList;
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
        if (!(object instanceof MpConsumos)) {
            return false;
        }
        MpConsumos other = (MpConsumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpConsumos[ id=" + id + " ]";
    }
    
}
