/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "gn_semaforos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnSemaforos.findAll", query = "SELECT g FROM GnSemaforos g"),
    @NamedQuery(name = "GnSemaforos.findById", query = "SELECT g FROM GnSemaforos g WHERE g.id = :id"),
    @NamedQuery(name = "GnSemaforos.findByTipo", query = "SELECT g FROM GnSemaforos g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GnSemaforos.findByNombre", query = "SELECT g FROM GnSemaforos g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnSemaforos.findByTiempo", query = "SELECT g FROM GnSemaforos g WHERE g.tiempo = :tiempo"),
    @NamedQuery(name = "GnSemaforos.findByColor", query = "SELECT g FROM GnSemaforos g WHERE g.color = :color")})
public class GnSemaforos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo")
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "color")
    private String color;

    public GnSemaforos() {
    }

    public GnSemaforos(Integer id) {
        this.id = id;
    }

    public GnSemaforos(Integer id, int tipo, String nombre, int tiempo, String color) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        if (!(object instanceof GnSemaforos)) {
            return false;
        }
        GnSemaforos other = (GnSemaforos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnSemaforos[ id=" + id + " ]";
    }
    
}
