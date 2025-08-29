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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "aseg_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAdjuntos.findAll", query = "SELECT a FROM AsegAdjuntos a"),
    @NamedQuery(name = "AsegAdjuntos.findById", query = "SELECT a FROM AsegAdjuntos a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAdjuntos.findByTipoArchivo", query = "SELECT a FROM AsegAdjuntos a WHERE a.tipoArchivo = :tipoArchivo"),
    @NamedQuery(name = "AsegAdjuntos.findByNombreArchivo", query = "SELECT a FROM AsegAdjuntos a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "AsegAdjuntos.findByRuta", query = "SELECT a FROM AsegAdjuntos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AsegAdjuntos.findByArchivo", query = "SELECT a FROM AsegAdjuntos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AsegAdjuntos.findByObservacion", query = "SELECT a FROM AsegAdjuntos a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AsegAdjuntos.findByUsuarioCrea", query = "SELECT a FROM AsegAdjuntos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAdjuntos.findByFechaHoraCrea", query = "SELECT a FROM AsegAdjuntos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegAdjuntos.findByTerminalCrea", query = "SELECT a FROM AsegAdjuntos a WHERE a.terminalCrea = :terminalCrea")})
public class AsegAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_archivo")
    private int tipoArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 256)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @JoinColumn(name = "radicado_novedades_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegRadicadoNovedades radicadoNovedadesId;

    public AsegAdjuntos() {
    }

    public AsegAdjuntos(Integer id) {
        this.id = id;
    }

    public AsegAdjuntos(Integer id, int tipoArchivo, String nombreArchivo, String ruta, String archivo, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.tipoArchivo = tipoArchivo;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.archivo = archivo;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public AsegRadicadoNovedades getRadicadoNovedadesId() {
        return radicadoNovedadesId;
    }

    public void setRadicadoNovedadesId(AsegRadicadoNovedades radicadoNovedadesId) {
        this.radicadoNovedadesId = radicadoNovedadesId;
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
        if (!(object instanceof AsegAdjuntos)) {
            return false;
        }
        AsegAdjuntos other = (AsegAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAdjuntos[ id=" + id + " ]";
    }
    
}
