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
@Table(name = "ant_anticipo_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntAnticipoAdjuntos.findAll", query = "SELECT a FROM AntAnticipoAdjuntos a"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findById", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.id = :id"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByOrigen", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.origen = :origen"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByMaeTipoArchivoId", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByMaeTipoArchivoValor", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByMaeTipoArchivoTipo", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.maeTipoArchivoTipo = :maeTipoArchivoTipo"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByNombre", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByRuta", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByArchivo", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByExiste", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.existe = :existe"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByBorrado", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByBorradoObservacion", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByUsuarioCrea", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByTerminalCrea", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByFechaHoraCrea", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByUsuarioBorra", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByTerminalBorra", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AntAnticipoAdjuntos.findByFechaHoraBorra", query = "SELECT a FROM AntAnticipoAdjuntos a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AntAnticipoAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen")
    private int origen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_archivo_id")
    private int maeTipoArchivoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_archivo_codigo")
    private String maeTipoArchivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_archivo_valor")
    private String maeTipoArchivoValor;
    @Size(max = 4)
    @Column(name = "mae_tipo_archivo_tipo")
    private String maeTipoArchivoTipo;
    @Size(max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 1024)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "ant_anticipos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AntAnticipos antAnticiposId;

    public AntAnticipoAdjuntos() {
    }

    public AntAnticipoAdjuntos(Integer id) {
        this.id = id;
    }

    public AntAnticipoAdjuntos(Integer id, int origen, int maeTipoArchivoId, String ruta, String archivo, boolean existe, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.origen = origen;
        this.maeTipoArchivoId = maeTipoArchivoId;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.borrado = borrado;
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

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
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

    public String getMaeTipoArchivoTipo() {
        return maeTipoArchivoTipo;
    }

    public void setMaeTipoArchivoTipo(String maeTipoArchivoTipo) {
        this.maeTipoArchivoTipo = maeTipoArchivoTipo;
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

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
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

    public AntAnticipos getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipos antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
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
        if (!(object instanceof AntAnticipoAdjuntos)) {
            return false;
        }
        AntAnticipoAdjuntos other = (AntAnticipoAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AntAnticipoAdjuntos[ id=" + id + " ]";
    }
    
}
