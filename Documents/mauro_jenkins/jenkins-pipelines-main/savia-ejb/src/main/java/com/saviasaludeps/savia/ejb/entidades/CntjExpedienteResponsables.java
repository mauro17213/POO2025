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
@Table(name = "cntj_expediente_responsables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjExpedienteResponsables.findAll", query = "SELECT c FROM CntjExpedienteResponsables c"),
    @NamedQuery(name = "CntjExpedienteResponsables.findById", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.id = :id"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByFechaInicial", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByFechaFinal", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByRol", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.rol = :rol"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByUsuarioCrea", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByTerminalCrea", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByFechaHoraCrea", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByUsuarioModifica", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByTerminalModifica", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjExpedienteResponsables.findByFechaHoraModifica", query = "SELECT c FROM CntjExpedienteResponsables c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjExpedienteResponsables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "rol")
    private Integer rol;
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
    @JoinColumn(name = "cntj_expedientes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjExpedientes cntjExpedientesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public CntjExpedienteResponsables() {
    }

    public CntjExpedienteResponsables(Integer id) {
        this.id = id;
    }

    public CntjExpedienteResponsables(Integer id, Date fechaInicial, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicial = fechaInicial;
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

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntjExpedienteResponsables)) {
            return false;
        }
        CntjExpedienteResponsables other = (CntjExpedienteResponsables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjExpedienteResponsables[ id=" + id + " ]";
    }
    
}
