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
@Table(name = "aus_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusGrupos.findAll", query = "SELECT a FROM AusGrupos a"),
    @NamedQuery(name = "AusGrupos.findById", query = "SELECT a FROM AusGrupos a WHERE a.id = :id"),
    @NamedQuery(name = "AusGrupos.findByNombre", query = "SELECT a FROM AusGrupos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AusGrupos.findByTipo", query = "SELECT a FROM AusGrupos a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AusGrupos.findByDescripcion", query = "SELECT a FROM AusGrupos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AusGrupos.findByUsuarioCrea", query = "SELECT a FROM AusGrupos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusGrupos.findByTerminalCrea", query = "SELECT a FROM AusGrupos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusGrupos.findByFechaHoraCrea", query = "SELECT a FROM AusGrupos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusGrupos.findByUsuarioModifica", query = "SELECT a FROM AusGrupos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusGrupos.findByTerminalModifica", query = "SELECT a FROM AusGrupos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusGrupos.findByFechaHoraModifica", query = "SELECT a FROM AusGrupos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ausGruposId", fetch = FetchType.LAZY)
    private List<AusGrupoUsuarios> ausGrupoUsuariosList;

    public AusGrupos() {
    }

    public AusGrupos(Integer id) {
        this.id = id;
    }

    public AusGrupos(Integer id, String nombre, int tipo, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
    public List<AusGrupoUsuarios> getAusGrupoUsuariosList() {
        return ausGrupoUsuariosList;
    }

    public void setAusGrupoUsuariosList(List<AusGrupoUsuarios> ausGrupoUsuariosList) {
        this.ausGrupoUsuariosList = ausGrupoUsuariosList;
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
        if (!(object instanceof AusGrupos)) {
            return false;
        }
        AusGrupos other = (AusGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusGrupos[ id=" + id + " ]";
    }
    
}
