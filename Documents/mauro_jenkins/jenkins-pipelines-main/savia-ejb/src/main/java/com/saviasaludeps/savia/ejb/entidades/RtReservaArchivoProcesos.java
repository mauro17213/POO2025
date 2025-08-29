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
@Table(name = "rt_reserva_archivo_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtReservaArchivoProcesos.findAll", query = "SELECT r FROM RtReservaArchivoProcesos r"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findById", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.id = :id"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByNombre", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByEstado", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.estado = :estado"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByFechaHoraInicio", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByFechaHoraFin", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByTiempo", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.tiempo = :tiempo"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByDescripcion", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByUsuarioCrea", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByTerminalCrea", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RtReservaArchivoProcesos.findByFechaHoraCrea", query = "SELECT r FROM RtReservaArchivoProcesos r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RtReservaArchivoProcesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Short estado;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tiempo")
    private Short tiempo;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "rt_reserva_archivos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtReservaArchivos rtReservaArchivosId;
    @JoinColumn(name = "sp_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtSpProcesos spProcesosId;

    public RtReservaArchivoProcesos() {
    }

    public RtReservaArchivoProcesos(Integer id) {
        this.id = id;
    }

    public RtReservaArchivoProcesos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
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

    public Short getTiempo() {
        return tiempo;
    }

    public void setTiempo(Short tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public RtReservaArchivos getRtReservaArchivosId() {
        return rtReservaArchivosId;
    }

    public void setRtReservaArchivosId(RtReservaArchivos rtReservaArchivosId) {
        this.rtReservaArchivosId = rtReservaArchivosId;
    }

    public RtSpProcesos getSpProcesosId() {
        return spProcesosId;
    }

    public void setSpProcesosId(RtSpProcesos spProcesosId) {
        this.spProcesosId = spProcesosId;
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
        if (!(object instanceof RtReservaArchivoProcesos)) {
            return false;
        }
        RtReservaArchivoProcesos other = (RtReservaArchivoProcesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtReservaArchivoProcesos[ id=" + id + " ]";
    }
    
}
