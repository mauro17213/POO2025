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
import javax.persistence.Lob;
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
@Table(name = "archivo_plano_temporal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchivoPlanoTemporal.findAll", query = "SELECT a FROM ArchivoPlanoTemporal a"),
    @NamedQuery(name = "ArchivoPlanoTemporal.findById", query = "SELECT a FROM ArchivoPlanoTemporal a WHERE a.id = :id"),
    @NamedQuery(name = "ArchivoPlanoTemporal.findByEstado", query = "SELECT a FROM ArchivoPlanoTemporal a WHERE a.estado = :estado"),
    @NamedQuery(name = "ArchivoPlanoTemporal.findByFechaInicio", query = "SELECT a FROM ArchivoPlanoTemporal a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "ArchivoPlanoTemporal.findByFechaFin", query = "SELECT a FROM ArchivoPlanoTemporal a WHERE a.fechaFin = :fechaFin")})
public class ArchivoPlanoTemporal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "query")
    private String query;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "respuesta")
    private String respuesta;

    public ArchivoPlanoTemporal() {
    }

    public ArchivoPlanoTemporal(Integer id) {
        this.id = id;
    }

    public ArchivoPlanoTemporal(Integer id, String query, String estado) {
        this.id = id;
        this.query = query;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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
        if (!(object instanceof ArchivoPlanoTemporal)) {
            return false;
        }
        ArchivoPlanoTemporal other = (ArchivoPlanoTemporal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.ArchivoPlanoTemporal[ id=" + id + " ]";
    }
    
}
