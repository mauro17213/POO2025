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
@Table(name = "cntj_expediente_auditorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjExpedienteAuditorias.findAll", query = "SELECT c FROM CntjExpedienteAuditorias c"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findById", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.id = :id"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByObservacion", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByUsuarioCrea", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByTerminalCrea", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByFechaHoraCrea", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByUsuarioModifica", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByTerminalModifica", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjExpedienteAuditorias.findByFechaHoraModifica", query = "SELECT c FROM CntjExpedienteAuditorias c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjExpedienteAuditorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "cntj_estado_ejecuciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjEstadoEjecuciones cntjEstadoEjecucionesId;
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;

    public CntjExpedienteAuditorias() {
    }

    public CntjExpedienteAuditorias(Integer id) {
        this.id = id;
    }

    public CntjExpedienteAuditorias(Integer id, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.observacion = observacion;
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

    public CntjEstadoEjecuciones getCntjEstadoEjecucionesId() {
        return cntjEstadoEjecucionesId;
    }

    public void setCntjEstadoEjecucionesId(CntjEstadoEjecuciones cntjEstadoEjecucionesId) {
        this.cntjEstadoEjecucionesId = cntjEstadoEjecucionesId;
    }

    public CntjExpedientes getCntjExpedientesId() {
        return cntjExpedientesId;
    }

    public void setCntjExpedientesId(CntjExpedientes cntjExpedientesId) {
        this.cntjExpedientesId = cntjExpedientesId;
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
        if (!(object instanceof CntjExpedienteAuditorias)) {
            return false;
        }
        CntjExpedienteAuditorias other = (CntjExpedienteAuditorias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjExpedienteAuditorias[ id=" + id + " ]";
    }
    
}
