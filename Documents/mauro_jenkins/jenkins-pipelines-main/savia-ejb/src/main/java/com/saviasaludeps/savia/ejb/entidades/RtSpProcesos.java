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
@Table(name = "rt_sp_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RtSpProcesos.findAll", query = "SELECT r FROM RtSpProcesos r"),
    @NamedQuery(name = "RtSpProcesos.findById", query = "SELECT r FROM RtSpProcesos r WHERE r.id = :id"),
    @NamedQuery(name = "RtSpProcesos.findByTipoArchivo", query = "SELECT r FROM RtSpProcesos r WHERE r.tipoArchivo = :tipoArchivo"),
    @NamedQuery(name = "RtSpProcesos.findByOrden", query = "SELECT r FROM RtSpProcesos r WHERE r.orden = :orden"),
    @NamedQuery(name = "RtSpProcesos.findByTipo", query = "SELECT r FROM RtSpProcesos r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RtSpProcesos.findByStoredProcedure", query = "SELECT r FROM RtSpProcesos r WHERE r.storedProcedure = :storedProcedure"),
    @NamedQuery(name = "RtSpProcesos.findByDescripcion", query = "SELECT r FROM RtSpProcesos r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RtSpProcesos.findByTiempo", query = "SELECT r FROM RtSpProcesos r WHERE r.tiempo = :tiempo"),
    @NamedQuery(name = "RtSpProcesos.findByUsuarioCrea", query = "SELECT r FROM RtSpProcesos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RtSpProcesos.findByTerminalCrea", query = "SELECT r FROM RtSpProcesos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RtSpProcesos.findByFechaHoraCrea", query = "SELECT r FROM RtSpProcesos r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RtSpProcesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo_archivo")
    private Short tipoArchivo;
    @Size(max = 64)
    @Column(name = "orden")
    private String orden;
    @Column(name = "tipo")
    private Short tipo;
    @Size(max = 32)
    @Column(name = "stored_procedure")
    private String storedProcedure;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spProcesosId", fetch = FetchType.LAZY)
    private List<RtReservaArchivoProcesos> rtReservaArchivoProcesosList;

    public RtSpProcesos() {
    }

    public RtSpProcesos(Integer id) {
        this.id = id;
    }

    public RtSpProcesos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public Short getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(Short tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public String getStoredProcedure() {
        return storedProcedure;
    }

    public void setStoredProcedure(String storedProcedure) {
        this.storedProcedure = storedProcedure;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<RtReservaArchivoProcesos> getRtReservaArchivoProcesosList() {
        return rtReservaArchivoProcesosList;
    }

    public void setRtReservaArchivoProcesosList(List<RtReservaArchivoProcesos> rtReservaArchivoProcesosList) {
        this.rtReservaArchivoProcesosList = rtReservaArchivoProcesosList;
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
        if (!(object instanceof RtSpProcesos)) {
            return false;
        }
        RtSpProcesos other = (RtSpProcesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RtSpProcesos[ id=" + id + " ]";
    }
    
}
