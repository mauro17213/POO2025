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
@Table(name = "cntj_estado_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjEstadoGrupos.findAll", query = "SELECT c FROM CntjEstadoGrupos c"),
    @NamedQuery(name = "CntjEstadoGrupos.findById", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjEstadoGrupos.findByEjecucion", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.ejecucion = :ejecucion"),
    @NamedQuery(name = "CntjEstadoGrupos.findByUsuarioCrea", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjEstadoGrupos.findByTerminalCrea", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjEstadoGrupos.findByFechaHoraCrea", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjEstadoGrupos.findByUsuarioModifica", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjEstadoGrupos.findByTerminalModifica", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjEstadoGrupos.findByFechaHoraModifica", query = "SELECT c FROM CntjEstadoGrupos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjEstadoGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ejecucion")
    private boolean ejecucion;
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
    @JoinColumn(name = "cntj_estados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjEstados cntjEstadosId;
    @JoinColumn(name = "cntj_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjGrupos cntjGruposId;

    public CntjEstadoGrupos() {
    }

    public CntjEstadoGrupos(Integer id) {
        this.id = id;
    }

    public CntjEstadoGrupos(Integer id, boolean ejecucion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.ejecucion = ejecucion;
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

    public boolean getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
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

    public CntjEstados getCntjEstadosId() {
        return cntjEstadosId;
    }

    public void setCntjEstadosId(CntjEstados cntjEstadosId) {
        this.cntjEstadosId = cntjEstadosId;
    }

    public CntjGrupos getCntjGruposId() {
        return cntjGruposId;
    }

    public void setCntjGruposId(CntjGrupos cntjGruposId) {
        this.cntjGruposId = cntjGruposId;
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
        if (!(object instanceof CntjEstadoGrupos)) {
            return false;
        }
        CntjEstadoGrupos other = (CntjEstadoGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjEstadoGrupos[ id=" + id + " ]";
    }
    
}
