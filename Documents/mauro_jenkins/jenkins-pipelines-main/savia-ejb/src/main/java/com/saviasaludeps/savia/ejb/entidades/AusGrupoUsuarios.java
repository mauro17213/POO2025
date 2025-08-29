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
@Table(name = "aus_grupo_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusGrupoUsuarios.findAll", query = "SELECT a FROM AusGrupoUsuarios a"),
    @NamedQuery(name = "AusGrupoUsuarios.findById", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.id = :id"),
    @NamedQuery(name = "AusGrupoUsuarios.findByActivo", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.activo = :activo"),
    @NamedQuery(name = "AusGrupoUsuarios.findByUsuarioCrea", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusGrupoUsuarios.findByTerminalCrea", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusGrupoUsuarios.findByFechaHoraCrea", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusGrupoUsuarios.findByUsuarioModifica", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusGrupoUsuarios.findByTerminalModifica", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusGrupoUsuarios.findByFechaHoraModifica", query = "SELECT a FROM AusGrupoUsuarios a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusGrupoUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @JoinColumn(name = "aus_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusGrupos ausGruposId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public AusGrupoUsuarios() {
    }

    public AusGrupoUsuarios(Integer id) {
        this.id = id;
    }

    public AusGrupoUsuarios(Integer id, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public AusGrupos getAusGruposId() {
        return ausGruposId;
    }

    public void setAusGruposId(AusGrupos ausGruposId) {
        this.ausGruposId = ausGruposId;
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
        if (!(object instanceof AusGrupoUsuarios)) {
            return false;
        }
        AusGrupoUsuarios other = (AusGrupoUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusGrupoUsuarios[ id=" + id + " ]";
    }
    
}
