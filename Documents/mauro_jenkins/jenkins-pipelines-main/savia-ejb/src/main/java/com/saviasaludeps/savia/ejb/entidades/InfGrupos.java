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
@Table(name = "inf_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfGrupos.findAll", query = "SELECT i FROM InfGrupos i"),
    @NamedQuery(name = "InfGrupos.findById", query = "SELECT i FROM InfGrupos i WHERE i.id = :id"),
    @NamedQuery(name = "InfGrupos.findByNombre", query = "SELECT i FROM InfGrupos i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InfGrupos.findByDescripcion", query = "SELECT i FROM InfGrupos i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "InfGrupos.findByUsuarioCrea", query = "SELECT i FROM InfGrupos i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfGrupos.findByFechaHoraCrea", query = "SELECT i FROM InfGrupos i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfGrupos.findByTerminalCrea", query = "SELECT i FROM InfGrupos i WHERE i.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "InfGrupos.findByUsuarioModifica", query = "SELECT i FROM InfGrupos i WHERE i.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "InfGrupos.findByFechaHoraModifica", query = "SELECT i FROM InfGrupos i WHERE i.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "InfGrupos.findByTerminalModifica", query = "SELECT i FROM InfGrupos i WHERE i.terminalModifica = :terminalModifica")})
public class InfGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infGruposId", fetch = FetchType.LAZY)
    private List<InfGrupoUsuarios> infGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infGruposId", fetch = FetchType.LAZY)
    private List<InfInformes> infInformesList;

    public InfGrupos() {
    }

    public InfGrupos(Integer id) {
        this.id = id;
    }

    public InfGrupos(Integer id, String descripcion, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.descripcion = descripcion;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    @XmlTransient
    public List<InfGrupoUsuarios> getInfGrupoUsuariosList() {
        return infGrupoUsuariosList;
    }

    public void setInfGrupoUsuariosList(List<InfGrupoUsuarios> infGrupoUsuariosList) {
        this.infGrupoUsuariosList = infGrupoUsuariosList;
    }

    @XmlTransient
    public List<InfInformes> getInfInformesList() {
        return infInformesList;
    }

    public void setInfInformesList(List<InfInformes> infInformesList) {
        this.infInformesList = infInformesList;
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
        if (!(object instanceof InfGrupos)) {
            return false;
        }
        InfGrupos other = (InfGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfGrupos[ id=" + id + " ]";
    }
    
}
