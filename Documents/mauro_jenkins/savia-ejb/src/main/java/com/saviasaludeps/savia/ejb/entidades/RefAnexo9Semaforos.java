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
@Table(name = "ref_anexo9_semaforos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexo9Semaforos.findAll", query = "SELECT r FROM RefAnexo9Semaforos r"),
    @NamedQuery(name = "RefAnexo9Semaforos.findById", query = "SELECT r FROM RefAnexo9Semaforos r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexo9Semaforos.findByNombre", query = "SELECT r FROM RefAnexo9Semaforos r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RefAnexo9Semaforos.findByTiempo", query = "SELECT r FROM RefAnexo9Semaforos r WHERE r.tiempo = :tiempo"),
    @NamedQuery(name = "RefAnexo9Semaforos.findByColor", query = "SELECT r FROM RefAnexo9Semaforos r WHERE r.color = :color")})
public class RefAnexo9Semaforos implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo")
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "color")
    private String color;

    public RefAnexo9Semaforos() {
    }

    public RefAnexo9Semaforos(Integer id) {
        this.id = id;
    }

    public RefAnexo9Semaforos(Integer id, String nombre, int tiempo, String color) {
        this.id = id;
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
        if (!(object instanceof RefAnexo9Semaforos)) {
            return false;
        }
        RefAnexo9Semaforos other = (RefAnexo9Semaforos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexo9Semaforos[ id=" + id + " ]";
    }
    
}
