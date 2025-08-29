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
@Table(name = "cm_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmGrupos.findAll", query = "SELECT c FROM CmGrupos c"),
    @NamedQuery(name = "CmGrupos.findById", query = "SELECT c FROM CmGrupos c WHERE c.id = :id"),
    @NamedQuery(name = "CmGrupos.findByNombre", query = "SELECT c FROM CmGrupos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CmGrupos.findByDescripcion", query = "SELECT c FROM CmGrupos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CmGrupos.findByUsuariosIdAsignacion", query = "SELECT c FROM CmGrupos c WHERE c.usuariosIdAsignacion = :usuariosIdAsignacion"),
    @NamedQuery(name = "CmGrupos.findByActivo", query = "SELECT c FROM CmGrupos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CmGrupos.findByPbs", query = "SELECT c FROM CmGrupos c WHERE c.pbs = :pbs"),
    @NamedQuery(name = "CmGrupos.findByCamaFija", query = "SELECT c FROM CmGrupos c WHERE c.camaFija = :camaFija"),
    @NamedQuery(name = "CmGrupos.findByTipoGrupo", query = "SELECT c FROM CmGrupos c WHERE c.tipoGrupo = :tipoGrupo"),
    @NamedQuery(name = "CmGrupos.findByCategoria", query = "SELECT c FROM CmGrupos c WHERE c.categoria = :categoria"),
    @NamedQuery(name = "CmGrupos.findByUsuarioCrea", query = "SELECT c FROM CmGrupos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmGrupos.findByTerminalCrea", query = "SELECT c FROM CmGrupos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmGrupos.findByFechaHoraCrea", query = "SELECT c FROM CmGrupos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmGrupos.findByUsuarioModifica", query = "SELECT c FROM CmGrupos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmGrupos.findByTerminalModifica", query = "SELECT c FROM CmGrupos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmGrupos.findByFechaHoraModifica", query = "SELECT c FROM CmGrupos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "usuarios_id_asignacion")
    private Integer usuariosIdAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs")
    private boolean pbs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cama_fija")
    private boolean camaFija;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_grupo")
    private boolean tipoGrupo;
    @Column(name = "categoria")
    private Integer categoria;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmGruposId", fetch = FetchType.LAZY)
    private List<CmGrupoHistoricos> cmGrupoHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmGruposId", fetch = FetchType.LAZY)
    private List<CmGrupoUsuarios> cmGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmGruposId", fetch = FetchType.LAZY)
    private List<CmGrupoPrestadores> cmGrupoPrestadoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmGruposId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList;

    public CmGrupos() {
    }

    public CmGrupos(Integer id) {
        this.id = id;
    }

    public CmGrupos(Integer id, String nombre, boolean activo, boolean pbs, boolean camaFija, boolean tipoGrupo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.pbs = pbs;
        this.camaFija = camaFija;
        this.tipoGrupo = tipoGrupo;
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

    public Integer getUsuariosIdAsignacion() {
        return usuariosIdAsignacion;
    }

    public void setUsuariosIdAsignacion(Integer usuariosIdAsignacion) {
        this.usuariosIdAsignacion = usuariosIdAsignacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public boolean getCamaFija() {
        return camaFija;
    }

    public void setCamaFija(boolean camaFija) {
        this.camaFija = camaFija;
    }

    public boolean getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(boolean tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
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
    public List<CmGrupoHistoricos> getCmGrupoHistoricosList() {
        return cmGrupoHistoricosList;
    }

    public void setCmGrupoHistoricosList(List<CmGrupoHistoricos> cmGrupoHistoricosList) {
        this.cmGrupoHistoricosList = cmGrupoHistoricosList;
    }

    @XmlTransient
    public List<CmGrupoUsuarios> getCmGrupoUsuariosList() {
        return cmGrupoUsuariosList;
    }

    public void setCmGrupoUsuariosList(List<CmGrupoUsuarios> cmGrupoUsuariosList) {
        this.cmGrupoUsuariosList = cmGrupoUsuariosList;
    }

    @XmlTransient
    public List<CmGrupoPrestadores> getCmGrupoPrestadoresList() {
        return cmGrupoPrestadoresList;
    }

    public void setCmGrupoPrestadoresList(List<CmGrupoPrestadores> cmGrupoPrestadoresList) {
        this.cmGrupoPrestadoresList = cmGrupoPrestadoresList;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList() {
        return cmFacturasList;
    }

    public void setCmFacturasList(List<CmFacturas> cmFacturasList) {
        this.cmFacturasList = cmFacturasList;
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
        if (!(object instanceof CmGrupos)) {
            return false;
        }
        CmGrupos other = (CmGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmGrupos[ id=" + id + " ]";
    }
    
}
