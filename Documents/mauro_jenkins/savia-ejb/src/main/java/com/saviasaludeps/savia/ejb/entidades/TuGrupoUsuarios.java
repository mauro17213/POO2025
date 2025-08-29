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
@Table(name = "tu_grupo_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuGrupoUsuarios.findAll", query = "SELECT t FROM TuGrupoUsuarios t"),
    @NamedQuery(name = "TuGrupoUsuarios.findById", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.id = :id"),
    @NamedQuery(name = "TuGrupoUsuarios.findByTipo", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TuGrupoUsuarios.findByActivo", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.activo = :activo"),
    @NamedQuery(name = "TuGrupoUsuarios.findByUsuarioCrea", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuGrupoUsuarios.findByTerminalCrea", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuGrupoUsuarios.findByFechaHoraCrea", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuGrupoUsuarios.findByUsuarioModifica", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuGrupoUsuarios.findByTerminalModifica", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuGrupoUsuarios.findByFechaHoraModifica", query = "SELECT t FROM TuGrupoUsuarios t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuGrupoUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
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
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @JoinColumn(name = "tu_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuGrupos tuGruposId;

    public TuGrupoUsuarios() {
    }

    public TuGrupoUsuarios(Integer id) {
        this.id = id;
    }

    public TuGrupoUsuarios(Integer id, int tipo, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public TuGrupos getTuGruposId() {
        return tuGruposId;
    }

    public void setTuGruposId(TuGrupos tuGruposId) {
        this.tuGruposId = tuGruposId;
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
        if (!(object instanceof TuGrupoUsuarios)) {
            return false;
        }
        TuGrupoUsuarios other = (TuGrupoUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuGrupoUsuarios[ id=" + id + " ]";
    }
    
}
