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
@Table(name = "rco_conciliacion_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoConciliacionAdjuntos.findAll", query = "SELECT r FROM RcoConciliacionAdjuntos r"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findById", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.id = :id"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByMaeTipoArchivoId", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByMaeTipoArchivoValor", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByTipo", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByNombreArchivo", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByRuta", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByArchivo", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.archivo = :archivo"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByExiste", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.existe = :existe"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByUsuarioCrea", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByTerminalCrea", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoConciliacionAdjuntos.findByFechaHoraCrea", query = "SELECT r FROM RcoConciliacionAdjuntos r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RcoConciliacionAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_archivo_id")
    private Integer maeTipoArchivoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_archivo_codigo")
    private String maeTipoArchivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_archivo_valor")
    private String maeTipoArchivoValor;
    @Column(name = "tipo")
    private Integer tipo;
    @Size(max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
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
    @JoinColumn(name = "rco_conciliaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RcoConciliaciones rcoConciliacionesId;

    public RcoConciliacionAdjuntos() {
    }

    public RcoConciliacionAdjuntos(Integer id) {
        this.id = id;
    }

    public RcoConciliacionAdjuntos(Integer id, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.existe = existe;
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

    public Integer getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(Integer maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    public String getMaeTipoArchivoCodigo() {
        return maeTipoArchivoCodigo;
    }

    public void setMaeTipoArchivoCodigo(String maeTipoArchivoCodigo) {
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
    }

    public String getMaeTipoArchivoValor() {
        return maeTipoArchivoValor;
    }

    public void setMaeTipoArchivoValor(String maeTipoArchivoValor) {
        this.maeTipoArchivoValor = maeTipoArchivoValor;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
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

    public RcoConciliaciones getRcoConciliacionesId() {
        return rcoConciliacionesId;
    }

    public void setRcoConciliacionesId(RcoConciliaciones rcoConciliacionesId) {
        this.rcoConciliacionesId = rcoConciliacionesId;
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
        if (!(object instanceof RcoConciliacionAdjuntos)) {
            return false;
        }
        RcoConciliacionAdjuntos other = (RcoConciliacionAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoConciliacionAdjuntos[ id=" + id + " ]";
    }
    
}
