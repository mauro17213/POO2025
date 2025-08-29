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
@Table(name = "aseg_informes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegInformes.findAll", query = "SELECT a FROM AsegInformes a"),
    @NamedQuery(name = "AsegInformes.findById", query = "SELECT a FROM AsegInformes a WHERE a.id = :id"),
    @NamedQuery(name = "AsegInformes.findByTipo", query = "SELECT a FROM AsegInformes a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AsegInformes.findByEstado", query = "SELECT a FROM AsegInformes a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsegInformes.findByFechaDesde", query = "SELECT a FROM AsegInformes a WHERE a.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "AsegInformes.findByFechaHasta", query = "SELECT a FROM AsegInformes a WHERE a.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "AsegInformes.findByFechaHoraInicio", query = "SELECT a FROM AsegInformes a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AsegInformes.findByFechaHoraFin", query = "SELECT a FROM AsegInformes a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AsegInformes.findByRegistros", query = "SELECT a FROM AsegInformes a WHERE a.registros = :registros"),
    @NamedQuery(name = "AsegInformes.findByRuta", query = "SELECT a FROM AsegInformes a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AsegInformes.findByArchivo", query = "SELECT a FROM AsegInformes a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AsegInformes.findByObservacion", query = "SELECT a FROM AsegInformes a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AsegInformes.findByUsuarioCrea", query = "SELECT a FROM AsegInformes a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegInformes.findByTerminalCrea", query = "SELECT a FROM AsegInformes a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegInformes.findByFechaHoraCrea", query = "SELECT a FROM AsegInformes a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegInformes implements Serializable {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
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

    public AsegInformes() {
    }

    public AsegInformes(Integer id) {
        this.id = id;
    }

    public AsegInformes(Integer id, int tipo, int estado, Date fechaDesde, Date fechaHasta, Date fechaHoraInicio, int registros, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registros = registros;
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
        if (!(object instanceof AsegInformes)) {
            return false;
        }
        AsegInformes other = (AsegInformes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegInformes[ id=" + id + " ]";
    }
    
}
