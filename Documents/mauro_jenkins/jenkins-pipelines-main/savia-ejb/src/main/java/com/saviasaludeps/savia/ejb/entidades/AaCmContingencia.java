/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "aa_cm_contingencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AaCmContingencia.findAll", query = "SELECT a FROM AaCmContingencia a"),
    @NamedQuery(name = "AaCmContingencia.findById", query = "SELECT a FROM AaCmContingencia a WHERE a.id = :id"),
    @NamedQuery(name = "AaCmContingencia.findByUsuario", query = "SELECT a FROM AaCmContingencia a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "AaCmContingencia.findByFechaHora", query = "SELECT a FROM AaCmContingencia a WHERE a.fechaHora = :fechaHora")})
public class AaCmContingencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    public AaCmContingencia() {
    }

    public AaCmContingencia(Integer id) {
        this.id = id;
    }

    public AaCmContingencia(Integer id, String usuario, Date fechaHora) {
        this.id = id;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
        if (!(object instanceof AaCmContingencia)) {
            return false;
        }
        AaCmContingencia other = (AaCmContingencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AaCmContingencia[ id=" + id + " ]";
    }
    
}
