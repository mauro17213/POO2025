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
@Table(name = "aus_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusAdjuntos.findAll", query = "SELECT a FROM AusAdjuntos a"),
    @NamedQuery(name = "AusAdjuntos.findById", query = "SELECT a FROM AusAdjuntos a WHERE a.id = :id"),
    @NamedQuery(name = "AusAdjuntos.findByNombre", query = "SELECT a FROM AusAdjuntos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AusAdjuntos.findByRuta", query = "SELECT a FROM AusAdjuntos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AusAdjuntos.findByArchivo", query = "SELECT a FROM AusAdjuntos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AusAdjuntos.findByUsuarioCrea", query = "SELECT a FROM AusAdjuntos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusAdjuntos.findByTerminalCrea", query = "SELECT a FROM AusAdjuntos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusAdjuntos.findByFechaHoraCrea", query = "SELECT a FROM AusAdjuntos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusAdjuntos.findByUsuarioModifica", query = "SELECT a FROM AusAdjuntos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusAdjuntos.findByTerminalModifica", query = "SELECT a FROM AusAdjuntos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusAdjuntos.findByFechaHoraModifica", query = "SELECT a FROM AusAdjuntos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
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
    @JoinColumn(name = "aus_casos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusCasos ausCasosId;
    @JoinColumn(name = "aus_seguimientos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusSeguimientos ausSeguimientosId;
    @JoinColumn(name = "aus_servicios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusCasoServicios ausServiciosId;
    @JoinColumn(name = "aus_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusSolicitudes ausSolicitudesId;

    public AusAdjuntos() {
    }

    public AusAdjuntos(Integer id) {
        this.id = id;
    }

    public AusAdjuntos(Integer id, String nombre, String ruta, String archivo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
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

    public AusCasos getAusCasosId() {
        return ausCasosId;
    }

    public void setAusCasosId(AusCasos ausCasosId) {
        this.ausCasosId = ausCasosId;
    }

    public AusSeguimientos getAusSeguimientosId() {
        return ausSeguimientosId;
    }

    public void setAusSeguimientosId(AusSeguimientos ausSeguimientosId) {
        this.ausSeguimientosId = ausSeguimientosId;
    }

    public AusCasoServicios getAusServiciosId() {
        return ausServiciosId;
    }

    public void setAusServiciosId(AusCasoServicios ausServiciosId) {
        this.ausServiciosId = ausServiciosId;
    }

    public AusSolicitudes getAusSolicitudesId() {
        return ausSolicitudesId;
    }

    public void setAusSolicitudesId(AusSolicitudes ausSolicitudesId) {
        this.ausSolicitudesId = ausSolicitudesId;
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
        if (!(object instanceof AusAdjuntos)) {
            return false;
        }
        AusAdjuntos other = (AusAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusAdjuntos[ id=" + id + " ]";
    }
    
}
