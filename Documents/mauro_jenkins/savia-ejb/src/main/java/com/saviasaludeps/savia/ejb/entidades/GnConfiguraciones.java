/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "gn_configuraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnConfiguraciones.findAll", query = "SELECT g FROM GnConfiguraciones g"),
    @NamedQuery(name = "GnConfiguraciones.findById", query = "SELECT g FROM GnConfiguraciones g WHERE g.id = :id"),
    @NamedQuery(name = "GnConfiguraciones.findByNombre", query = "SELECT g FROM GnConfiguraciones g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnConfiguraciones.findByValor", query = "SELECT g FROM GnConfiguraciones g WHERE g.valor = :valor")})
public class GnConfiguraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 256)
    @Column(name = "valor")
    private String valor;

    public GnConfiguraciones() {
    }

    public GnConfiguraciones(Integer id) {
        this.id = id;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        if (!(object instanceof GnConfiguraciones)) {
            return false;
        }
        GnConfiguraciones other = (GnConfiguraciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnConfiguraciones[ id=" + id + " ]";
    }
    
}
