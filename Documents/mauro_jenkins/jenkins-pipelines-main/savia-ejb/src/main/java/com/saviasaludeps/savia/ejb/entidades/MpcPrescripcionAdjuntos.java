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
@Table(name = "mpc_prescripcion_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findAll", query = "SELECT m FROM MpcPrescripcionAdjuntos m"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findById", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.id = :id"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByTipo", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByNombreArchivo", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByRuta", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.ruta = :ruta"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByArchivo", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.archivo = :archivo"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByExiste", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.existe = :existe"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByUsuarioCrea", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByTerminalCrea", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByFechaHoraCrea", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByUsuarioModifica", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByTerminalModifica", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpcPrescripcionAdjuntos.findByFechaHoraModifica", query = "SELECT m FROM MpcPrescripcionAdjuntos m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpcPrescripcionAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
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
    @JoinColumn(name = "mpc_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpcPrescripciones mpcPrescripcionesId;

    public MpcPrescripcionAdjuntos() {
    }

    public MpcPrescripcionAdjuntos(Integer id) {
        this.id = id;
    }

    public MpcPrescripcionAdjuntos(Integer id, short tipo, String nombreArchivo, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.nombreArchivo = nombreArchivo;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
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

    public MpcPrescripciones getMpcPrescripcionesId() {
        return mpcPrescripcionesId;
    }

    public void setMpcPrescripcionesId(MpcPrescripciones mpcPrescripcionesId) {
        this.mpcPrescripcionesId = mpcPrescripcionesId;
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
        if (!(object instanceof MpcPrescripcionAdjuntos)) {
            return false;
        }
        MpcPrescripcionAdjuntos other = (MpcPrescripcionAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpcPrescripcionAdjuntos[ id=" + id + " ]";
    }
    
}
