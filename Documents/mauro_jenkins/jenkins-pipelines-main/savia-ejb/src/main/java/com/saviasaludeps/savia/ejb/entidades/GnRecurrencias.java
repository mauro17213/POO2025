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
import javax.persistence.Lob;
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
@Table(name = "gn_recurrencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnRecurrencias.findAll", query = "SELECT g FROM GnRecurrencias g"),
    @NamedQuery(name = "GnRecurrencias.findById", query = "SELECT g FROM GnRecurrencias g WHERE g.id = :id"),
    @NamedQuery(name = "GnRecurrencias.findByNombre", query = "SELECT g FROM GnRecurrencias g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnRecurrencias.findByDescripcion", query = "SELECT g FROM GnRecurrencias g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnRecurrencias.findByActivo", query = "SELECT g FROM GnRecurrencias g WHERE g.activo = :activo"),
    @NamedQuery(name = "GnRecurrencias.findByTipoPeriodicidad", query = "SELECT g FROM GnRecurrencias g WHERE g.tipoPeriodicidad = :tipoPeriodicidad"),
    @NamedQuery(name = "GnRecurrencias.findByPeriodicidad", query = "SELECT g FROM GnRecurrencias g WHERE g.periodicidad = :periodicidad"),
    @NamedQuery(name = "GnRecurrencias.findByFechaInicio", query = "SELECT g FROM GnRecurrencias g WHERE g.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "GnRecurrencias.findByFechaFin", query = "SELECT g FROM GnRecurrencias g WHERE g.fechaFin = :fechaFin"),
    @NamedQuery(name = "GnRecurrencias.findByFechaHoraEjecucion", query = "SELECT g FROM GnRecurrencias g WHERE g.fechaHoraEjecucion = :fechaHoraEjecucion"),
    @NamedQuery(name = "GnRecurrencias.findByUsuarioCrea", query = "SELECT g FROM GnRecurrencias g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnRecurrencias.findByTerminalCrea", query = "SELECT g FROM GnRecurrencias g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnRecurrencias.findByFechaHoraCrea", query = "SELECT g FROM GnRecurrencias g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnRecurrencias.findByUsuarioModifica", query = "SELECT g FROM GnRecurrencias g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnRecurrencias.findByTerminalModifica", query = "SELECT g FROM GnRecurrencias g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnRecurrencias.findByFechaHoraModifica", query = "SELECT g FROM GnRecurrencias g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnRecurrencias implements Serializable {

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
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "script")
    private byte[] script;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_periodicidad")
    private int tipoPeriodicidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodicidad")
    private int periodicidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "fecha_hora_ejecucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEjecucion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnRecurrenciasId", fetch = FetchType.LAZY)
    private List<GnRecurrenciaHistoricos> gnRecurrenciaHistoricosList;

    public GnRecurrencias() {
    }

    public GnRecurrencias(Integer id) {
        this.id = id;
    }

    public GnRecurrencias(Integer id, String nombre, boolean activo, int tipoPeriodicidad, int periodicidad, Date fechaInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.tipoPeriodicidad = tipoPeriodicidad;
        this.periodicidad = periodicidad;
        this.fechaInicio = fechaInicio;
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

    public byte[] getScript() {
        return script;
    }

    public void setScript(byte[] script) {
        this.script = script;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTipoPeriodicidad() {
        return tipoPeriodicidad;
    }

    public void setTipoPeriodicidad(int tipoPeriodicidad) {
        this.tipoPeriodicidad = tipoPeriodicidad;
    }

    public int getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(Date fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
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
    public List<GnRecurrenciaHistoricos> getGnRecurrenciaHistoricosList() {
        return gnRecurrenciaHistoricosList;
    }

    public void setGnRecurrenciaHistoricosList(List<GnRecurrenciaHistoricos> gnRecurrenciaHistoricosList) {
        this.gnRecurrenciaHistoricosList = gnRecurrenciaHistoricosList;
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
        if (!(object instanceof GnRecurrencias)) {
            return false;
        }
        GnRecurrencias other = (GnRecurrencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnRecurrencias[ id=" + id + " ]";
    }
    
}
