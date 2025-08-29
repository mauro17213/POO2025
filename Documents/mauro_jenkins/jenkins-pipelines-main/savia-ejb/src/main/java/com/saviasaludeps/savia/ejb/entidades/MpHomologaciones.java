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
@Table(name = "mp_homologaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpHomologaciones.findAll", query = "SELECT m FROM MpHomologaciones m"),
    @NamedQuery(name = "MpHomologaciones.findById", query = "SELECT m FROM MpHomologaciones m WHERE m.id = :id"),
    @NamedQuery(name = "MpHomologaciones.findByTipo", query = "SELECT m FROM MpHomologaciones m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MpHomologaciones.findByCodigo", query = "SELECT m FROM MpHomologaciones m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MpHomologaciones.findByNombre", query = "SELECT m FROM MpHomologaciones m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MpHomologaciones.findByDescripcion", query = "SELECT m FROM MpHomologaciones m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MpHomologaciones.findByUsuarioCrea", query = "SELECT m FROM MpHomologaciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpHomologaciones.findByTerminalCrea", query = "SELECT m FROM MpHomologaciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpHomologaciones.findByFechaHoraCrea", query = "SELECT m FROM MpHomologaciones m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpHomologaciones implements Serializable {

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
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;

    public MpHomologaciones() {
    }

    public MpHomologaciones(Integer id) {
        this.id = id;
    }

    public MpHomologaciones(Integer id, int tipo, String codigo, String nombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
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
        if (!(object instanceof MpHomologaciones)) {
            return false;
        }
        MpHomologaciones other = (MpHomologaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpHomologaciones[ id=" + id + " ]";
    }
    
}
