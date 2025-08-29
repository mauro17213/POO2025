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
@Table(name = "rt_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtProcesos.findAll", query = "SELECT r FROM RtProcesos r"),
    @NamedQuery(name = "RtProcesos.findById", query = "SELECT r FROM RtProcesos r WHERE r.id = :id"),
    @NamedQuery(name = "RtProcesos.findByNombre", query = "SELECT r FROM RtProcesos r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RtProcesos.findByDescripcion", query = "SELECT r FROM RtProcesos r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RtProcesos.findByOrden", query = "SELECT r FROM RtProcesos r WHERE r.orden = :orden"),
    @NamedQuery(name = "RtProcesos.findByUsuarioCrea", query = "SELECT r FROM RtProcesos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RtProcesos.findByTerminalCrea", query = "SELECT r FROM RtProcesos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RtProcesos.findByFechaHoraCrea", query = "SELECT r FROM RtProcesos r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RtProcesos.findByUsuarioModifica", query = "SELECT r FROM RtProcesos r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RtProcesos.findByTerminalModifica", query = "SELECT r FROM RtProcesos r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RtProcesos.findByFechaHoraModifica", query = "SELECT r FROM RtProcesos r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RtProcesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtProcesosId", fetch = FetchType.LAZY)
    private List<RtProcesoEjecuciones> rtProcesoEjecucionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtProcesosId", fetch = FetchType.LAZY)
    private List<RtSentencias> rtSentenciasList;

    public RtProcesos() {
    }

    public RtProcesos(Integer id) {
        this.id = id;
    }

    public RtProcesos(Integer id, String nombre, int orden, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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
    public List<RtSentencias> getRtSentenciasList() {
        return rtSentenciasList;
    }

    public void setRtSentenciasList(List<RtSentencias> rtSentenciasList) {
        this.rtSentenciasList = rtSentenciasList;
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
        if (!(object instanceof RtProcesos)) {
            return false;
        }
        RtProcesos other = (RtProcesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtProcesos[ id=" + id + " ]";
    }
    
}
