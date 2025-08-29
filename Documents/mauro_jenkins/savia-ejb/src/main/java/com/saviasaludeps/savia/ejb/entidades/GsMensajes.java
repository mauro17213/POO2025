/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "gs_mensajes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsMensajes.findAll", query = "SELECT g FROM GsMensajes g"),
    @NamedQuery(name = "GsMensajes.findById", query = "SELECT g FROM GsMensajes g WHERE g.id = :id"),
    @NamedQuery(name = "GsMensajes.findByNombre", query = "SELECT g FROM GsMensajes g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GsMensajes.findByTipo", query = "SELECT g FROM GsMensajes g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GsMensajes.findByEstado", query = "SELECT g FROM GsMensajes g WHERE g.estado = :estado"),
    @NamedQuery(name = "GsMensajes.findByEncabezado", query = "SELECT g FROM GsMensajes g WHERE g.encabezado = :encabezado"),
    @NamedQuery(name = "GsMensajes.findByMensajeLargo", query = "SELECT g FROM GsMensajes g WHERE g.mensajeLargo = :mensajeLargo"),
    @NamedQuery(name = "GsMensajes.findByMensajeCorto", query = "SELECT g FROM GsMensajes g WHERE g.mensajeCorto = :mensajeCorto")})
public class GsMensajes implements Serializable {

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
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "encabezado")
    private String encabezado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "mensaje_largo")
    private String mensajeLargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 164)
    @Column(name = "mensaje_corto")
    private String mensajeCorto;
    @OneToMany(mappedBy = "gsMensajesId", fetch = FetchType.LAZY)
    private List<GsSolicitudes> gsSolicitudesList;

    public GsMensajes() {
    }

    public GsMensajes(Integer id) {
        this.id = id;
    }

    public GsMensajes(Integer id, String nombre, int tipo, int estado, String encabezado, String mensajeLargo, String mensajeCorto) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.encabezado = encabezado;
        this.mensajeLargo = mensajeLargo;
        this.mensajeCorto = mensajeCorto;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getMensajeLargo() {
        return mensajeLargo;
    }

    public void setMensajeLargo(String mensajeLargo) {
        this.mensajeLargo = mensajeLargo;
    }

    public String getMensajeCorto() {
        return mensajeCorto;
    }

    public void setMensajeCorto(String mensajeCorto) {
        this.mensajeCorto = mensajeCorto;
    }

    @XmlTransient
    public List<GsSolicitudes> getGsSolicitudesList() {
        return gsSolicitudesList;
    }

    public void setGsSolicitudesList(List<GsSolicitudes> gsSolicitudesList) {
        this.gsSolicitudesList = gsSolicitudesList;
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
        if (!(object instanceof GsMensajes)) {
            return false;
        }
        GsMensajes other = (GsMensajes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsMensajes[ id=" + id + " ]";
    }
    
}
