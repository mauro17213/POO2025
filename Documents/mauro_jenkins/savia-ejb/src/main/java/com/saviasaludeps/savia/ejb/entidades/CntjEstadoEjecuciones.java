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
@Table(name = "cntj_estado_ejecuciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjEstadoEjecuciones.findAll", query = "SELECT c FROM CntjEstadoEjecuciones c"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findById", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.id = :id"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByObservacion", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByFechaEjecucion", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.fechaEjecucion = :fechaEjecucion"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByUsuarioCrea", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByTerminalCrea", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByFechaHoraCrea", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByUsuarioModifica", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByTerminalModifica", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjEstadoEjecuciones.findByFechaHoraModifica", query = "SELECT c FROM CntjEstadoEjecuciones c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjEstadoEjecuciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha_ejecucion")
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;
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
    @JoinColumn(name = "cntj_documentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjDocumentos cntjDocumentosId;
    @JoinColumn(name = "cntj_estados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjEstados cntjEstadosId;
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @JoinColumn(name = "cntj_lineas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjLineas cntjLineasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadoEjecucionesId", fetch = FetchType.LAZY)
    private List<CntjExpedienteAuditorias> cntjExpedienteAuditoriasList;

    public CntjEstadoEjecuciones() {
    }

    public CntjEstadoEjecuciones(Integer id) {
        this.id = id;
    }

    public CntjEstadoEjecuciones(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
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

    public CntjDocumentos getCntjDocumentosId() {
        return cntjDocumentosId;
    }

    public void setCntjDocumentosId(CntjDocumentos cntjDocumentosId) {
        this.cntjDocumentosId = cntjDocumentosId;
    }

    public CntjEstados getCntjEstadosId() {
        return cntjEstadosId;
    }

    public void setCntjEstadosId(CntjEstados cntjEstadosId) {
        this.cntjEstadosId = cntjEstadosId;
    }

    public CntjExpedientes getCntjExpedientesId() {
        return cntjExpedientesId;
    }

    public void setCntjExpedientesId(CntjExpedientes cntjExpedientesId) {
        this.cntjExpedientesId = cntjExpedientesId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public CntjLineas getCntjLineasId() {
        return cntjLineasId;
    }

    public void setCntjLineasId(CntjLineas cntjLineasId) {
        this.cntjLineasId = cntjLineasId;
    }

    @XmlTransient
    public List<CntjExpedienteAuditorias> getCntjExpedienteAuditoriasList() {
        return cntjExpedienteAuditoriasList;
    }

    public void setCntjExpedienteAuditoriasList(List<CntjExpedienteAuditorias> cntjExpedienteAuditoriasList) {
        this.cntjExpedienteAuditoriasList = cntjExpedienteAuditoriasList;
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
        if (!(object instanceof CntjEstadoEjecuciones)) {
            return false;
        }
        CntjEstadoEjecuciones other = (CntjEstadoEjecuciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjEstadoEjecuciones[ id=" + id + " ]";
    }
    
}
