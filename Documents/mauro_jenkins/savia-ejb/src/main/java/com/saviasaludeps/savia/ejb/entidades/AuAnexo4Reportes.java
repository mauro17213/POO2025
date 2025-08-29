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
@Table(name = "au_anexo4_reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Reportes.findAll", query = "SELECT a FROM AuAnexo4Reportes a"),
    @NamedQuery(name = "AuAnexo4Reportes.findById", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Reportes.findByArchivo", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuAnexo4Reportes.findByRuta", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuAnexo4Reportes.findByCantidadProcesada", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.cantidadProcesada = :cantidadProcesada"),
    @NamedQuery(name = "AuAnexo4Reportes.findByCantidadTotal", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.cantidadTotal = :cantidadTotal"),
    @NamedQuery(name = "AuAnexo4Reportes.findByFechaInicio", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "AuAnexo4Reportes.findByFechaFin", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.fechaFin = :fechaFin"),
    @NamedQuery(name = "AuAnexo4Reportes.findByEstado", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo4Reportes.findByDescripcion", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AuAnexo4Reportes.findByUsuarioCreaId", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.usuarioCreaId = :usuarioCreaId"),
    @NamedQuery(name = "AuAnexo4Reportes.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Reportes.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Reportes.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Reportes a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo4Reportes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "cantidad_procesada")
    private Integer cantidadProcesada;
    @Column(name = "cantidad_total")
    private Integer cantidadTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_crea_id")
    private int usuarioCreaId;
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

    public AuAnexo4Reportes() {
    }

    public AuAnexo4Reportes(Integer id) {
        this.id = id;
    }

    public AuAnexo4Reportes(Integer id, String archivo, String ruta, Date fechaInicio, Date fechaFin, int estado, int usuarioCreaId, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.archivo = archivo;
        this.ruta = ruta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.usuarioCreaId = usuarioCreaId;
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

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Integer getCantidadProcesada() {
        return cantidadProcesada;
    }

    public void setCantidadProcesada(Integer cantidadProcesada) {
        this.cantidadProcesada = cantidadProcesada;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUsuarioCreaId() {
        return usuarioCreaId;
    }

    public void setUsuarioCreaId(int usuarioCreaId) {
        this.usuarioCreaId = usuarioCreaId;
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
        if (!(object instanceof AuAnexo4Reportes)) {
            return false;
        }
        AuAnexo4Reportes other = (AuAnexo4Reportes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Reportes[ id=" + id + " ]";
    }
    
}
