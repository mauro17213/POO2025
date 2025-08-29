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
@Table(name = "au_cotizacion_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuCotizacionAdjuntos.findAll", query = "SELECT a FROM AuCotizacionAdjuntos a"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findById", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.id = :id"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByNombreArchivo", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByRuta", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByArchivo", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByMaeTipoArchivoId", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByMaeTipoArchivoValor", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByUsuarioCrea", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByTerminalCrea", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuCotizacionAdjuntos.findByFechaHoraCrea", query = "SELECT a FROM AuCotizacionAdjuntos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuCotizacionAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_archivo_id")
    private int maeTipoArchivoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_archivo_codigo")
    private String maeTipoArchivoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_archivo_valor")
    private String maeTipoArchivoValor;
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
    @JoinColumn(name = "au_cotizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuCotizaciones auCotizacionesId;

    public AuCotizacionAdjuntos() {
    }

    public AuCotizacionAdjuntos(Integer id) {
        this.id = id;
    }

    public AuCotizacionAdjuntos(Integer id, String nombreArchivo, String ruta, String archivo, int maeTipoArchivoId, String maeTipoArchivoCodigo, String maeTipoArchivoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.archivo = archivo;
        this.maeTipoArchivoId = maeTipoArchivoId;
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
        this.maeTipoArchivoValor = maeTipoArchivoValor;
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

    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
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

    public AuCotizaciones getAuCotizacionesId() {
        return auCotizacionesId;
    }

    public void setAuCotizacionesId(AuCotizaciones auCotizacionesId) {
        this.auCotizacionesId = auCotizacionesId;
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
        if (!(object instanceof AuCotizacionAdjuntos)) {
            return false;
        }
        AuCotizacionAdjuntos other = (AuCotizacionAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuCotizacionAdjuntos[ id=" + id + " ]";
    }
    
}
