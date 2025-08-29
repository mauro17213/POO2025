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
@Table(name = "inf_grupo_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfGrupoUsuarios.findAll", query = "SELECT i FROM InfGrupoUsuarios i"),
    @NamedQuery(name = "InfGrupoUsuarios.findById", query = "SELECT i FROM InfGrupoUsuarios i WHERE i.id = :id"),
    @NamedQuery(name = "InfGrupoUsuarios.findByActivo", query = "SELECT i FROM InfGrupoUsuarios i WHERE i.activo = :activo"),
    @NamedQuery(name = "InfGrupoUsuarios.findByUsuarioCrea", query = "SELECT i FROM InfGrupoUsuarios i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfGrupoUsuarios.findByFechaHoraCrea", query = "SELECT i FROM InfGrupoUsuarios i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfGrupoUsuarios.findByTerminalCrea", query = "SELECT i FROM InfGrupoUsuarios i WHERE i.terminalCrea = :terminalCrea")})
public class InfGrupoUsuarios implements Serializable {

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
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @JoinColumn(name = "inf_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfGrupos infGruposId;

    public InfGrupoUsuarios() {
    }

    public InfGrupoUsuarios(Integer id) {
        this.id = id;
    }

    public InfGrupoUsuarios(Integer id, boolean activo, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.activo = activo;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
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

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public InfGrupos getInfGruposId() {
        return infGruposId;
    }

    public void setInfGruposId(InfGrupos infGruposId) {
        this.infGruposId = infGruposId;
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
        if (!(object instanceof InfGrupoUsuarios)) {
            return false;
        }
        InfGrupoUsuarios other = (InfGrupoUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfGrupoUsuarios[ id=" + id + " ]";
    }
    
}
