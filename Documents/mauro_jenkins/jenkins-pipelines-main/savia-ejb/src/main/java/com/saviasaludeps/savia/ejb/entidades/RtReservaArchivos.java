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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "rt_reserva_archivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtReservaArchivos.findAll", query = "SELECT r FROM RtReservaArchivos r"),
    @NamedQuery(name = "RtReservaArchivos.findById", query = "SELECT r FROM RtReservaArchivos r WHERE r.id = :id"),
    @NamedQuery(name = "RtReservaArchivos.findByTipo", query = "SELECT r FROM RtReservaArchivos r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RtReservaArchivos.findByEstado", query = "SELECT r FROM RtReservaArchivos r WHERE r.estado = :estado"),
    @NamedQuery(name = "RtReservaArchivos.findByObservacion", query = "SELECT r FROM RtReservaArchivos r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RtReservaArchivos.findByDescargado", query = "SELECT r FROM RtReservaArchivos r WHERE r.descargado = :descargado"),
    @NamedQuery(name = "RtReservaArchivos.findByTieneArchivo", query = "SELECT r FROM RtReservaArchivos r WHERE r.tieneArchivo = :tieneArchivo"),
    @NamedQuery(name = "RtReservaArchivos.findByArchivoNombre", query = "SELECT r FROM RtReservaArchivos r WHERE r.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "RtReservaArchivos.findByRuta", query = "SELECT r FROM RtReservaArchivos r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "RtReservaArchivos.findByArchivo", query = "SELECT r FROM RtReservaArchivos r WHERE r.archivo = :archivo"),
    @NamedQuery(name = "RtReservaArchivos.findByFechaHoraInicio", query = "SELECT r FROM RtReservaArchivos r WHERE r.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "RtReservaArchivos.findByFechaHoraFin", query = "SELECT r FROM RtReservaArchivos r WHERE r.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "RtReservaArchivos.findByTiempo", query = "SELECT r FROM RtReservaArchivos r WHERE r.tiempo = :tiempo"),
    @NamedQuery(name = "RtReservaArchivos.findByUsuarioCrea", query = "SELECT r FROM RtReservaArchivos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RtReservaArchivos.findByTerminalCrea", query = "SELECT r FROM RtReservaArchivos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RtReservaArchivos.findByFechaHoraCrea", query = "SELECT r FROM RtReservaArchivos r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RtReservaArchivos.findByUsuarioModifica", query = "SELECT r FROM RtReservaArchivos r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RtReservaArchivos.findByTerminalModifica", query = "SELECT r FROM RtReservaArchivos r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RtReservaArchivos.findByFechaHoraModifica", query = "SELECT r FROM RtReservaArchivos r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RtReservaArchivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descargado")
    private boolean descargado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiene_archivo")
    private boolean tieneArchivo;
    @Size(max = 256)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtReservaArchivosId", fetch = FetchType.LAZY)
    private List<RtReservaArchivoProcesos> rtReservaArchivoProcesosList;
    @JoinColumn(name = "rt_reservas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtReservas rtReservasId;

    public RtReservaArchivos() {
    }

    public RtReservaArchivos(Integer id) {
        this.id = id;
    }

    public RtReservaArchivos(Integer id, short tipo, int estado, boolean descargado, boolean tieneArchivo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.descargado = descargado;
        this.tieneArchivo = tieneArchivo;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
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

    public boolean getDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    public boolean getTieneArchivo() {
        return tieneArchivo;
    }

    public void setTieneArchivo(boolean tieneArchivo) {
        this.tieneArchivo = tieneArchivo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
    public List<RtReservaArchivoProcesos> getRtReservaArchivoProcesosList() {
        return rtReservaArchivoProcesosList;
    }

    public void setRtReservaArchivoProcesosList(List<RtReservaArchivoProcesos> rtReservaArchivoProcesosList) {
        this.rtReservaArchivoProcesosList = rtReservaArchivoProcesosList;
    }

    public RtReservas getRtReservasId() {
        return rtReservasId;
    }

    public void setRtReservasId(RtReservas rtReservasId) {
        this.rtReservasId = rtReservasId;
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
        if (!(object instanceof RtReservaArchivos)) {
            return false;
        }
        RtReservaArchivos other = (RtReservaArchivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtReservaArchivos[ id=" + id + " ]";
    }
    
}
