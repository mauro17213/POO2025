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
@Table(name = "cm_informes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmInformes.findAll", query = "SELECT c FROM CmInformes c"),
    @NamedQuery(name = "CmInformes.findById", query = "SELECT c FROM CmInformes c WHERE c.id = :id"),
    @NamedQuery(name = "CmInformes.findByTipo", query = "SELECT c FROM CmInformes c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmInformes.findByEstado", query = "SELECT c FROM CmInformes c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmInformes.findByFechaDesde", query = "SELECT c FROM CmInformes c WHERE c.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "CmInformes.findByFechaHasta", query = "SELECT c FROM CmInformes c WHERE c.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "CmInformes.findByFechaHoraInicio", query = "SELECT c FROM CmInformes c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CmInformes.findByFechaHoraFin", query = "SELECT c FROM CmInformes c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CmInformes.findByRegistros", query = "SELECT c FROM CmInformes c WHERE c.registros = :registros"),
    @NamedQuery(name = "CmInformes.findByRuta", query = "SELECT c FROM CmInformes c WHERE c.ruta = :ruta"),
    @NamedQuery(name = "CmInformes.findByArchivo", query = "SELECT c FROM CmInformes c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CmInformes.findByObservacion", query = "SELECT c FROM CmInformes c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CmInformes.findByUsuarioCrea", query = "SELECT c FROM CmInformes c WHERE c.usuarioCrea = :usuarioCrea")})
public class CmInformes implements Serializable {

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
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date fechaHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIME)
    private Date fechaHoraFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registros")
    private int registros;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;

    public CmInformes() {
    }

    public CmInformes(Integer id) {
        this.id = id;
    }

    public CmInformes(Integer id, int tipo, int estado, Date fechaDesde, Date fechaHasta, Date fechaHoraInicio, Date fechaHoraFin, int registros, String usuarioCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.registros = registros;
        this.usuarioCrea = usuarioCrea;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
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
        if (!(object instanceof CmInformes)) {
            return false;
        }
        CmInformes other = (CmInformes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmInformes[ id=" + id + " ]";
    }
    
}
