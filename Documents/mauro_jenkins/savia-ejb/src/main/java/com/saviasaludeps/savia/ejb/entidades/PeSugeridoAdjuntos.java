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
@Table(name = "pe_sugerido_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeSugeridoAdjuntos.findAll", query = "SELECT p FROM PeSugeridoAdjuntos p"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findById", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.id = :id"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByMaeTipoArchivoId", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByMaeTipoArchivoValor", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByNombre", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByRuta", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.ruta = :ruta"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByArchivo", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.archivo = :archivo"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByObservacion", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByExiste", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.existe = :existe"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByUsuarioCrea", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByTerminalCrea", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByFechaHoraCrea", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByUsuarioModifica", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByTerminalModifica", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeSugeridoAdjuntos.findByFechaHoraModifica", query = "SELECT p FROM PeSugeridoAdjuntos p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeSugeridoAdjuntos implements Serializable {

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
    @Column(name = "nombre")
    private String nombre;
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
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "pe_afiliados_sugeridos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosSugeridos peAfiliadosSugeridosId;

    public PeSugeridoAdjuntos() {
    }

    public PeSugeridoAdjuntos(Integer id) {
        this.id = id;
    }

    public PeSugeridoAdjuntos(Integer id, int maeTipoArchivoId, String maeTipoArchivoCodigo, String maeTipoArchivoValor, String nombre, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoArchivoId = maeTipoArchivoId;
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
        this.maeTipoArchivoValor = maeTipoArchivoValor;
        this.nombre = nombre;
        this.ruta = ruta;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public PeAfiliadosSugeridos getPeAfiliadosSugeridosId() {
        return peAfiliadosSugeridosId;
    }

    public void setPeAfiliadosSugeridosId(PeAfiliadosSugeridos peAfiliadosSugeridosId) {
        this.peAfiliadosSugeridosId = peAfiliadosSugeridosId;
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
        if (!(object instanceof PeSugeridoAdjuntos)) {
            return false;
        }
        PeSugeridoAdjuntos other = (PeSugeridoAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeSugeridoAdjuntos[ id=" + id + " ]";
    }
    
}
