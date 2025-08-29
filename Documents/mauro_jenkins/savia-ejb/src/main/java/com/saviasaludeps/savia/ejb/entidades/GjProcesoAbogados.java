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
@Table(name = "gj_proceso_abogados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesoAbogados.findAll", query = "SELECT g FROM GjProcesoAbogados g"),
    @NamedQuery(name = "GjProcesoAbogados.findById", query = "SELECT g FROM GjProcesoAbogados g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesoAbogados.findByFechaInicio", query = "SELECT g FROM GjProcesoAbogados g WHERE g.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "GjProcesoAbogados.findByFechaFin", query = "SELECT g FROM GjProcesoAbogados g WHERE g.fechaFin = :fechaFin"),
    @NamedQuery(name = "GjProcesoAbogados.findByActivo", query = "SELECT g FROM GjProcesoAbogados g WHERE g.activo = :activo"),
    @NamedQuery(name = "GjProcesoAbogados.findByUsuarioCrea", query = "SELECT g FROM GjProcesoAbogados g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjProcesoAbogados.findByTerminalCrea", query = "SELECT g FROM GjProcesoAbogados g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesoAbogados.findByFechaHoraCrea", query = "SELECT g FROM GjProcesoAbogados g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjProcesoAbogados.findByUsuarioModifica", query = "SELECT g FROM GjProcesoAbogados g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjProcesoAbogados.findByTerminalModifica", query = "SELECT g FROM GjProcesoAbogados g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjProcesoAbogados.findByFechaHoraModifica", query = "SELECT g FROM GjProcesoAbogados g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjProcesoAbogados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @JoinColumn(name = "gj_abogados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjAbogados gjAbogadosId;
    @JoinColumn(name = "gj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesos gjProcesosId;

    public GjProcesoAbogados() {
    }

    public GjProcesoAbogados(Integer id) {
        this.id = id;
    }

    public GjProcesoAbogados(Integer id, Date fechaInicio, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.activo = activo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public GjAbogados getGjAbogadosId() {
        return gjAbogadosId;
    }

    public void setGjAbogadosId(GjAbogados gjAbogadosId) {
        this.gjAbogadosId = gjAbogadosId;
    }

    public GjProcesos getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProcesos gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
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
        if (!(object instanceof GjProcesoAbogados)) {
            return false;
        }
        GjProcesoAbogados other = (GjProcesoAbogados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesoAbogados[ id=" + id + " ]";
    }
    
}
