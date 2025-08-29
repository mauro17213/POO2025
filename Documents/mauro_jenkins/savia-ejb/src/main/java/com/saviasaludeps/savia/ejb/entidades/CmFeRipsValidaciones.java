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
@Table(name = "cm_fe_rips_validaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeRipsValidaciones.findAll", query = "SELECT c FROM CmFeRipsValidaciones c"),
    @NamedQuery(name = "CmFeRipsValidaciones.findById", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByNombreValidacion", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.nombreValidacion = :nombreValidacion"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByDescripcion", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByEstado", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByIdValidacion", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.idValidacion = :idValidacion"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByUsuarioModifica", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByTerminalModifica", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmFeRipsValidaciones.findByFechaHoraModifica", query = "SELECT c FROM CmFeRipsValidaciones c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmFeRipsValidaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre_validacion")
    private String nombreValidacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_validacion")
    private int idValidacion;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmFeRipsValidacionesId", fetch = FetchType.LAZY)
    private List<CmFeRipsValidacionesHistoricos> cmFeRipsValidacionesHistoricosList;

    public CmFeRipsValidaciones() {
    }

    public CmFeRipsValidaciones(Integer id) {
        this.id = id;
    }

    public CmFeRipsValidaciones(Integer id, String nombreValidacion, String descripcion, boolean estado, int idValidacion) {
        this.id = id;
        this.nombreValidacion = nombreValidacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idValidacion = idValidacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreValidacion() {
        return nombreValidacion;
    }

    public void setNombreValidacion(String nombreValidacion) {
        this.nombreValidacion = nombreValidacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdValidacion() {
        return idValidacion;
    }

    public void setIdValidacion(int idValidacion) {
        this.idValidacion = idValidacion;
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
    public List<CmFeRipsValidacionesHistoricos> getCmFeRipsValidacionesHistoricosList() {
        return cmFeRipsValidacionesHistoricosList;
    }

    public void setCmFeRipsValidacionesHistoricosList(List<CmFeRipsValidacionesHistoricos> cmFeRipsValidacionesHistoricosList) {
        this.cmFeRipsValidacionesHistoricosList = cmFeRipsValidacionesHistoricosList;
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
        if (!(object instanceof CmFeRipsValidaciones)) {
            return false;
        }
        CmFeRipsValidaciones other = (CmFeRipsValidaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeRipsValidaciones[ id=" + id + " ]";
    }
    
}
