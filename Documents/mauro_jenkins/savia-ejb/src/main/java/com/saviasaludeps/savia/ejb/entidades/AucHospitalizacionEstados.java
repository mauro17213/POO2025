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
@Table(name = "auc_hospitalizacion_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionEstados.findAll", query = "SELECT a FROM AucHospitalizacionEstados a"),
    @NamedQuery(name = "AucHospitalizacionEstados.findById", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByEstado", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.estado = :estado"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByEstadoAuditoria", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.estadoAuditoria = :estadoAuditoria"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByFuenteOrigen", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByObservacion", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionEstados.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionEstados a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AucHospitalizacionEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_auditoria")
    private short estadoAuditoria;
    @Column(name = "fuente_origen")
    private Short fuenteOrigen;
    @Size(max = 128)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;

    public AucHospitalizacionEstados() {
    }

    public AucHospitalizacionEstados(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionEstados(Integer id, short estado, short estadoAuditoria, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.estadoAuditoria = estadoAuditoria;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(short estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }

    public Short getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Short fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
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
        if (!(object instanceof AucHospitalizacionEstados)) {
            return false;
        }
        AucHospitalizacionEstados other = (AucHospitalizacionEstados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionEstados[ id=" + id + " ]";
    }
    
}
