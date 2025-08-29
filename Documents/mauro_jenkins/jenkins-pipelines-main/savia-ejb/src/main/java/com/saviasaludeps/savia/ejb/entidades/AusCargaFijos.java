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
@Table(name = "aus_carga_fijos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusCargaFijos.findAll", query = "SELECT a FROM AusCargaFijos a"),
    @NamedQuery(name = "AusCargaFijos.findById", query = "SELECT a FROM AusCargaFijos a WHERE a.id = :id"),
    @NamedQuery(name = "AusCargaFijos.findByNombre", query = "SELECT a FROM AusCargaFijos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AusCargaFijos.findByValor", query = "SELECT a FROM AusCargaFijos a WHERE a.valor = :valor")})
public class AusCargaFijos implements Serializable {

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
    @Size(max = 64)
    @Column(name = "valor")
    private String valor;

    public AusCargaFijos() {
    }

    public AusCargaFijos(Integer id) {
        this.id = id;
    }

    public AusCargaFijos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
        if (!(object instanceof AusCargaFijos)) {
            return false;
        }
        AusCargaFijos other = (AusCargaFijos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusCargaFijos[ id=" + id + " ]";
    }
    
}
