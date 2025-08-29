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
@Table(name = "aseg_validacion_derechos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegValidacionDerechos.findAll", query = "SELECT a FROM AsegValidacionDerechos a"),
    @NamedQuery(name = "AsegValidacionDerechos.findById", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.id = :id"),
    @NamedQuery(name = "AsegValidacionDerechos.findByEstado", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsegValidacionDerechos.findByFechaHoraInicio", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "AsegValidacionDerechos.findByFechaHoraFin", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "AsegValidacionDerechos.findByRegistrosCargados", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.registrosCargados = :registrosCargados"),
    @NamedQuery(name = "AsegValidacionDerechos.findByRegistrosEncontrados", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.registrosEncontrados = :registrosEncontrados"),
    @NamedQuery(name = "AsegValidacionDerechos.findByRuta", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AsegValidacionDerechos.findByArchivo", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AsegValidacionDerechos.findByUsuarioCrea", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegValidacionDerechos.findByTerminalCrea", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegValidacionDerechos.findByFechaHoraCrea", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegValidacionDerechos.findByUsuarioModifica", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegValidacionDerechos.findByTerminalModifica", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegValidacionDerechos.findByFechaHoraModifica", query = "SELECT a FROM AsegValidacionDerechos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegValidacionDerechos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "registros_cargados")
    private Integer registrosCargados;
    @Column(name = "registros_encontrados")
    private Integer registrosEncontrados;
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

    public AsegValidacionDerechos() {
    }

    public AsegValidacionDerechos(Integer id) {
        this.id = id;
    }

    public AsegValidacionDerechos(Integer id, int estado, Date fechaHoraInicio, String ruta, String archivo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.ruta = ruta;
        this.archivo = archivo;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Integer getRegistrosCargados() {
        return registrosCargados;
    }

    public void setRegistrosCargados(Integer registrosCargados) {
        this.registrosCargados = registrosCargados;
    }

    public Integer getRegistrosEncontrados() {
        return registrosEncontrados;
    }

    public void setRegistrosEncontrados(Integer registrosEncontrados) {
        this.registrosEncontrados = registrosEncontrados;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsegValidacionDerechos)) {
            return false;
        }
        AsegValidacionDerechos other = (AsegValidacionDerechos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegValidacionDerechos[ id=" + id + " ]";
    }
    
}
