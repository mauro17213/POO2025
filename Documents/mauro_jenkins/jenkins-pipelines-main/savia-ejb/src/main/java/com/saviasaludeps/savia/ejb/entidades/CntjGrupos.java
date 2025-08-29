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
@Table(name = "cntj_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjGrupos.findAll", query = "SELECT c FROM CntjGrupos c"),
    @NamedQuery(name = "CntjGrupos.findById", query = "SELECT c FROM CntjGrupos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjGrupos.findByNombre", query = "SELECT c FROM CntjGrupos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjGrupos.findByDescripcion", query = "SELECT c FROM CntjGrupos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjGrupos.findByActivo", query = "SELECT c FROM CntjGrupos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntjGrupos.findByUsuarioCrea", query = "SELECT c FROM CntjGrupos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjGrupos.findByTerminalCrea", query = "SELECT c FROM CntjGrupos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjGrupos.findByFechaHoraCrea", query = "SELECT c FROM CntjGrupos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjGrupos.findByUsuarioModifica", query = "SELECT c FROM CntjGrupos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjGrupos.findByTerminalModifica", query = "SELECT c FROM CntjGrupos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjGrupos.findByFechaHoraModifica", query = "SELECT c FROM CntjGrupos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjGruposId", fetch = FetchType.LAZY)
    private List<CntjEstadoGrupos> cntjEstadoGruposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjGruposId", fetch = FetchType.LAZY)
    private List<CntjUsuarioGrupos> cntjUsuarioGruposList;

    public CntjGrupos() {
    }

    public CntjGrupos(Integer id) {
        this.id = id;
    }

    public CntjGrupos(Integer id, String nombre, String descripcion, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<CntjEstadoGrupos> getCntjEstadoGruposList() {
        return cntjEstadoGruposList;
    }

    public void setCntjEstadoGruposList(List<CntjEstadoGrupos> cntjEstadoGruposList) {
        this.cntjEstadoGruposList = cntjEstadoGruposList;
    }

    @XmlTransient
    public List<CntjUsuarioGrupos> getCntjUsuarioGruposList() {
        return cntjUsuarioGruposList;
    }

    public void setCntjUsuarioGruposList(List<CntjUsuarioGrupos> cntjUsuarioGruposList) {
        this.cntjUsuarioGruposList = cntjUsuarioGruposList;
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
        if (!(object instanceof CntjGrupos)) {
            return false;
        }
        CntjGrupos other = (CntjGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjGrupos[ id=" + id + " ]";
    }
    
}
