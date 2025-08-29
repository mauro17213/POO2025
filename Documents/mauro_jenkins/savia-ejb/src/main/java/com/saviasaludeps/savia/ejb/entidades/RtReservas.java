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
@Table(name = "rt_reservas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtReservas.findAll", query = "SELECT r FROM RtReservas r"),
    @NamedQuery(name = "RtReservas.findById", query = "SELECT r FROM RtReservas r WHERE r.id = :id"),
    @NamedQuery(name = "RtReservas.findByNombre", query = "SELECT r FROM RtReservas r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RtReservas.findByFecha", query = "SELECT r FROM RtReservas r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RtReservas.findByEstados", query = "SELECT r FROM RtReservas r WHERE r.estados = :estados"),
    @NamedQuery(name = "RtReservas.findByObservacion", query = "SELECT r FROM RtReservas r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RtReservas.findByFechaHoraInicio", query = "SELECT r FROM RtReservas r WHERE r.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "RtReservas.findByFechaHoraFin", query = "SELECT r FROM RtReservas r WHERE r.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "RtReservas.findByTiempo", query = "SELECT r FROM RtReservas r WHERE r.tiempo = :tiempo"),
    @NamedQuery(name = "RtReservas.findByAprobacion", query = "SELECT r FROM RtReservas r WHERE r.aprobacion = :aprobacion"),
    @NamedQuery(name = "RtReservas.findByUsuarioCrea", query = "SELECT r FROM RtReservas r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RtReservas.findByTerminalCrea", query = "SELECT r FROM RtReservas r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RtReservas.findByFechaHoraCrea", query = "SELECT r FROM RtReservas r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RtReservas.findByUsuarioModifica", query = "SELECT r FROM RtReservas r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RtReservas.findByTerminalModifica", query = "SELECT r FROM RtReservas r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RtReservas.findByFechaHoraModifica", query = "SELECT r FROM RtReservas r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RtReservas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estados")
    private int estados;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tiempo")
    private Short tiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aprobacion")
    private boolean aprobacion;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtReservasId", fetch = FetchType.LAZY)
    private List<RtProcesoEjecuciones> rtProcesoEjecucionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtReservasId", fetch = FetchType.LAZY)
    private List<RtReservaArchivos> rtReservaArchivosList;

    public RtReservas() {
    }

    public RtReservas(Integer id) {
        this.id = id;
    }

    public RtReservas(Integer id, String nombre, Date fecha, int estados, boolean aprobacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estados = estados;
        this.aprobacion = aprobacion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstados() {
        return estados;
    }

    public void setEstados(int estados) {
        this.estados = estados;
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

    public Short getTiempo() {
        return tiempo;
    }

    public void setTiempo(Short tiempo) {
        this.tiempo = tiempo;
    }

    public boolean getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(boolean aprobacion) {
        this.aprobacion = aprobacion;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<RtProcesoEjecuciones> getRtProcesoEjecucionesList() {
        return rtProcesoEjecucionesList;
    }

    public void setRtProcesoEjecucionesList(List<RtProcesoEjecuciones> rtProcesoEjecucionesList) {
        this.rtProcesoEjecucionesList = rtProcesoEjecucionesList;
    }

    @XmlTransient
    public List<RtReservaArchivos> getRtReservaArchivosList() {
        return rtReservaArchivosList;
    }

    public void setRtReservaArchivosList(List<RtReservaArchivos> rtReservaArchivosList) {
        this.rtReservaArchivosList = rtReservaArchivosList;
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
        if (!(object instanceof RtReservas)) {
            return false;
        }
        RtReservas other = (RtReservas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtReservas[ id=" + id + " ]";
    }
    
}
