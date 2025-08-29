/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "geo_comodin_mun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeoComodinMun.findAll", query = "SELECT g FROM GeoComodinMun g"),
    @NamedQuery(name = "GeoComodinMun.findById", query = "SELECT g FROM GeoComodinMun g WHERE g.id = :id"),
    @NamedQuery(name = "GeoComodinMun.findByNombre", query = "SELECT g FROM GeoComodinMun g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GeoComodinMun.findByLat", query = "SELECT g FROM GeoComodinMun g WHERE g.lat = :lat"),
    @NamedQuery(name = "GeoComodinMun.findByLon", query = "SELECT g FROM GeoComodinMun g WHERE g.lon = :lon")})
public class GeoComodinMun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 110)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "lat")
    private String lat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "lon")
    private String lon;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones idMunicipio;

    public GeoComodinMun() {
    }

    public GeoComodinMun(Integer id) {
        this.id = id;
    }

    public GeoComodinMun(Integer id, String nombre, String lat, String lon) {
        this.id = id;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public GnUbicaciones getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(GnUbicaciones idMunicipio) {
        this.idMunicipio = idMunicipio;
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
        if (!(object instanceof GeoComodinMun)) {
            return false;
        }
        GeoComodinMun other = (GeoComodinMun) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GeoComodinMun[ id=" + id + " ]";
    }
    
}
