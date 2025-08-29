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
@Table(name = "ma_relacion_tipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaRelacionTipos.findAll", query = "SELECT m FROM MaRelacionTipos m"),
    @NamedQuery(name = "MaRelacionTipos.findById", query = "SELECT m FROM MaRelacionTipos m WHERE m.id = :id"),
    @NamedQuery(name = "MaRelacionTipos.findByNombre", query = "SELECT m FROM MaRelacionTipos m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaRelacionTipos.findByDescripcion", query = "SELECT m FROM MaRelacionTipos m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaRelacionTipos.findByUsuarioCrea", query = "SELECT m FROM MaRelacionTipos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaRelacionTipos.findByTerminalCrea", query = "SELECT m FROM MaRelacionTipos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaRelacionTipos.findByFechaHoraCrea", query = "SELECT m FROM MaRelacionTipos m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaRelacionTipos implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maRelacionTiposId", fetch = FetchType.LAZY)
    private List<MaRelaciones> maRelacionesList;

    public MaRelacionTipos() {
    }

    public MaRelacionTipos(Integer id) {
        this.id = id;
    }

    public MaRelacionTipos(Integer id, String nombre, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
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

    @XmlTransient
    public List<MaRelaciones> getMaRelacionesList() {
        return maRelacionesList;
    }

    public void setMaRelacionesList(List<MaRelaciones> maRelacionesList) {
        this.maRelacionesList = maRelacionesList;
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
        if (!(object instanceof MaRelacionTipos)) {
            return false;
        }
        MaRelacionTipos other = (MaRelacionTipos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaRelacionTipos[ id=" + id + " ]";
    }
    
}
