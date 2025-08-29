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
@Table(name = "gs_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsAdjuntos.findAll", query = "SELECT g FROM GsAdjuntos g"),
    @NamedQuery(name = "GsAdjuntos.findById", query = "SELECT g FROM GsAdjuntos g WHERE g.id = :id"),
    @NamedQuery(name = "GsAdjuntos.findByTipo", query = "SELECT g FROM GsAdjuntos g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GsAdjuntos.findByNombre", query = "SELECT g FROM GsAdjuntos g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GsAdjuntos.findByRuta", query = "SELECT g FROM GsAdjuntos g WHERE g.ruta = :ruta"),
    @NamedQuery(name = "GsAdjuntos.findByArchivo", query = "SELECT g FROM GsAdjuntos g WHERE g.archivo = :archivo"),
    @NamedQuery(name = "GsAdjuntos.findByExiste", query = "SELECT g FROM GsAdjuntos g WHERE g.existe = :existe")})
public class GsAdjuntos implements Serializable {

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
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
    @JoinColumn(name = "gs_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GsSolicitudes gsSolicitudesId;

    public GsAdjuntos() {
    }

    public GsAdjuntos(Integer id) {
        this.id = id;
    }

    public GsAdjuntos(Integer id, int tipo, String nombre, String ruta, String archivo, boolean existe) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public GsSolicitudes getGsSolicitudesId() {
        return gsSolicitudesId;
    }

    public void setGsSolicitudesId(GsSolicitudes gsSolicitudesId) {
        this.gsSolicitudesId = gsSolicitudesId;
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
        if (!(object instanceof GsAdjuntos)) {
            return false;
        }
        GsAdjuntos other = (GsAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsAdjuntos[ id=" + id + " ]";
    }
    
}
