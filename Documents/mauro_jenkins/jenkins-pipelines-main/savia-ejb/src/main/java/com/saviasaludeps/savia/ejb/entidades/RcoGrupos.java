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
@Table(name = "rco_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoGrupos.findAll", query = "SELECT r FROM RcoGrupos r"),
    @NamedQuery(name = "RcoGrupos.findById", query = "SELECT r FROM RcoGrupos r WHERE r.id = :id"),
    @NamedQuery(name = "RcoGrupos.findByNombre", query = "SELECT r FROM RcoGrupos r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RcoGrupos.findByTipo", query = "SELECT r FROM RcoGrupos r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RcoGrupos.findByDescripcion", query = "SELECT r FROM RcoGrupos r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RcoGrupos.findByUsuarioCrea", query = "SELECT r FROM RcoGrupos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoGrupos.findByTerminalCrea", query = "SELECT r FROM RcoGrupos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoGrupos.findByFechaHoraCrea", query = "SELECT r FROM RcoGrupos r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RcoGrupos.findByUsuarioModifica", query = "SELECT r FROM RcoGrupos r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RcoGrupos.findByTerminalModifica", query = "SELECT r FROM RcoGrupos r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RcoGrupos.findByFechaHoraModifica", query = "SELECT r FROM RcoGrupos r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RcoGrupos implements Serializable {

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
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 512)
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
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @OneToMany(mappedBy = "rcoGruposId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rcoGruposId", fetch = FetchType.LAZY)
    private List<RcoGrupoUsuarios> rcoGrupoUsuariosList;

    public RcoGrupos() {
    }

    public RcoGrupos(Integer id) {
        this.id = id;
    }

    public RcoGrupos(Integer id, int tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
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

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
    }

    @XmlTransient
    public List<RcoGrupoUsuarios> getRcoGrupoUsuariosList() {
        return rcoGrupoUsuariosList;
    }

    public void setRcoGrupoUsuariosList(List<RcoGrupoUsuarios> rcoGrupoUsuariosList) {
        this.rcoGrupoUsuariosList = rcoGrupoUsuariosList;
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
        if (!(object instanceof RcoGrupos)) {
            return false;
        }
        RcoGrupos other = (RcoGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoGrupos[ id=" + id + " ]";
    }
    
}
