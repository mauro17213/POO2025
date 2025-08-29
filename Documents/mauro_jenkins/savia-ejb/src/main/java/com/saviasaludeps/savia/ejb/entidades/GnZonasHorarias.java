/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "gn_zonas_horarias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnZonasHorarias.findAll", query = "SELECT g FROM GnZonasHorarias g"),
    @NamedQuery(name = "GnZonasHorarias.findById", query = "SELECT g FROM GnZonasHorarias g WHERE g.id = :id"),
    @NamedQuery(name = "GnZonasHorarias.findByCodigo", query = "SELECT g FROM GnZonasHorarias g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GnZonasHorarias.findByNombre", query = "SELECT g FROM GnZonasHorarias g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnZonasHorarias.findByUtc", query = "SELECT g FROM GnZonasHorarias g WHERE g.utc = :utc")})
public class GnZonasHorarias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "UTC")
    private BigDecimal utc;
    @OneToMany(mappedBy = "gnZonasHorariasId", fetch = FetchType.LAZY)
    private List<GnUbicaciones> gnUbicacionesList;
    @OneToMany(mappedBy = "gnZonasHorariasId", fetch = FetchType.LAZY)
    private List<GnUsuarios> gnUsuariosList;

    public GnZonasHorarias() {
    }

    public GnZonasHorarias(Integer id) {
        this.id = id;
    }

    public GnZonasHorarias(Integer id, String codigo, String nombre, BigDecimal utc) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.utc = utc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getUtc() {
        return utc;
    }

    public void setUtc(BigDecimal utc) {
        this.utc = utc;
    }

    @XmlTransient
    public List<GnUbicaciones> getGnUbicacionesList() {
        return gnUbicacionesList;
    }

    public void setGnUbicacionesList(List<GnUbicaciones> gnUbicacionesList) {
        this.gnUbicacionesList = gnUbicacionesList;
    }

    @XmlTransient
    public List<GnUsuarios> getGnUsuariosList() {
        return gnUsuariosList;
    }

    public void setGnUsuariosList(List<GnUsuarios> gnUsuariosList) {
        this.gnUsuariosList = gnUsuariosList;
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
        if (!(object instanceof GnZonasHorarias)) {
            return false;
        }
        GnZonasHorarias other = (GnZonasHorarias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnZonasHorarias[ id=" + id + " ]";
    }
    
}
