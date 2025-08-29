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
import javax.persistence.Lob;
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
@Table(name = "rt_sentencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtSentencias.findAll", query = "SELECT r FROM RtSentencias r"),
    @NamedQuery(name = "RtSentencias.findById", query = "SELECT r FROM RtSentencias r WHERE r.id = :id"),
    @NamedQuery(name = "RtSentencias.findByNombre", query = "SELECT r FROM RtSentencias r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RtSentencias.findByDescripcion", query = "SELECT r FROM RtSentencias r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RtSentencias.findByOrden", query = "SELECT r FROM RtSentencias r WHERE r.orden = :orden"),
    @NamedQuery(name = "RtSentencias.findByUsuarioCrea", query = "SELECT r FROM RtSentencias r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RtSentencias.findByTerminalCrea", query = "SELECT r FROM RtSentencias r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RtSentencias.findByFechaHoraCrea", query = "SELECT r FROM RtSentencias r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RtSentencias.findByUsuarioModifica", query = "SELECT r FROM RtSentencias r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RtSentencias.findByTerminalModifica", query = "SELECT r FROM RtSentencias r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RtSentencias.findByFechaHoraModifica", query = "SELECT r FROM RtSentencias r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RtSentencias implements Serializable {

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
    @Lob
    @Column(name = "sentencia")
    private byte[] sentencia;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtSentenciasId", fetch = FetchType.LAZY)
    private List<RtSentenciasEjecuciones> rtSentenciasEjecucionesList;
    @JoinColumn(name = "rt_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RtProcesos rtProcesosId;

    public RtSentencias() {
    }

    public RtSentencias(Integer id) {
        this.id = id;
    }

    public RtSentencias(Integer id, String nombre, int orden, byte[] sentencia, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
        this.sentencia = sentencia;
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

    public byte[] getSentencia() {
        return sentencia;
    }

    public void setSentencia(byte[] sentencia) {
        this.sentencia = sentencia;
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
    public List<RtSentenciasEjecuciones> getRtSentenciasEjecucionesList() {
        return rtSentenciasEjecucionesList;
    }

    public void setRtSentenciasEjecucionesList(List<RtSentenciasEjecuciones> rtSentenciasEjecucionesList) {
        this.rtSentenciasEjecucionesList = rtSentenciasEjecucionesList;
    }

    public RtProcesos getRtProcesosId() {
        return rtProcesosId;
    }

    public void setRtProcesosId(RtProcesos rtProcesosId) {
        this.rtProcesosId = rtProcesosId;
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
        if (!(object instanceof RtSentencias)) {
            return false;
        }
        RtSentencias other = (RtSentencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtSentencias[ id=" + id + " ]";
    }
    
}
