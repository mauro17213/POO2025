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
@Table(name = "au_anexo2_semaforos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo2Semaforos.findAll", query = "SELECT a FROM AuAnexo2Semaforos a"),
    @NamedQuery(name = "AuAnexo2Semaforos.findById", query = "SELECT a FROM AuAnexo2Semaforos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo2Semaforos.findByNombre", query = "SELECT a FROM AuAnexo2Semaforos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AuAnexo2Semaforos.findByTiempo", query = "SELECT a FROM AuAnexo2Semaforos a WHERE a.tiempo = :tiempo"),
    @NamedQuery(name = "AuAnexo2Semaforos.findByColor", query = "SELECT a FROM AuAnexo2Semaforos a WHERE a.color = :color"),
    @NamedQuery(name = "AuAnexo2Semaforos.findByTipo", query = "SELECT a FROM AuAnexo2Semaforos a WHERE a.tipo = :tipo")})
public class AuAnexo2Semaforos implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;

    public AuAnexo2Semaforos() {
    }

    public AuAnexo2Semaforos(Integer id) {
        this.id = id;
    }

    public AuAnexo2Semaforos(Integer id, String nombre, int tiempo, String color, int tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.color = color;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof AuAnexo2Semaforos)) {
            return false;
        }
        AuAnexo2Semaforos other = (AuAnexo2Semaforos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo2Semaforos[ id=" + id + " ]";
    }
    
}
