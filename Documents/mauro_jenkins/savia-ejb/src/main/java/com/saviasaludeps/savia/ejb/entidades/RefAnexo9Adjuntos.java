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
@Table(name = "ref_anexo9_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexo9Adjuntos.findAll", query = "SELECT r FROM RefAnexo9Adjuntos r"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findById", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByMaeTipoArchivoId", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByMaeTipoArchivoCodigo", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByMaeTipoArchivoValor", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByNombreArchivo", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByRuta", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.ruta = :ruta"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByBorrado", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.borrado = :borrado"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByBorradoObservacion", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByArchivo", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.archivo = :archivo"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByExiste", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.existe = :existe"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByUsuarioCrea", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByTerminalCrea", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByFechaHoraCrea", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByUsuarioBorra", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByTerminalBorra", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "RefAnexo9Adjuntos.findByFechaHoraBorra", query = "SELECT r FROM RefAnexo9Adjuntos r WHERE r.fechaHoraBorra = :fechaHoraBorra")})
public class RefAnexo9Adjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Column(name = "borrado")
    private int borrado;
    @Size(max = 1024)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private int existe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;

    public RefAnexo9Adjuntos() {
    }

    public RefAnexo9Adjuntos(Integer id) {
        this.id = id;
    }

    public RefAnexo9Adjuntos(Integer id, int maeTipoArchivoId, String maeTipoArchivoCodigo, String maeTipoArchivoValor, String nombreArchivo, String ruta, int borrado, String archivo, int existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoArchivoId = maeTipoArchivoId;
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
        this.maeTipoArchivoValor = maeTipoArchivoValor;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.borrado = borrado;
        this.archivo = archivo;
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

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getExiste() {
        return existe;
    }

    public void setExiste(int existe) {
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
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
        if (!(object instanceof RefAnexo9Adjuntos)) {
            return false;
        }
        RefAnexo9Adjuntos other = (RefAnexo9Adjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexo9Adjuntos[ id=" + id + " ]";
    }
    
}
