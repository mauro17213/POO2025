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
@Table(name = "ma_especialidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaEspecialidades.findAll", query = "SELECT m FROM MaEspecialidades m"),
    @NamedQuery(name = "MaEspecialidades.findById", query = "SELECT m FROM MaEspecialidades m WHERE m.id = :id"),
    @NamedQuery(name = "MaEspecialidades.findByCodigo", query = "SELECT m FROM MaEspecialidades m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaEspecialidades.findByNombre", query = "SELECT m FROM MaEspecialidades m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaEspecialidades.findByDescripcion", query = "SELECT m FROM MaEspecialidades m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaEspecialidades.findByActivo", query = "SELECT m FROM MaEspecialidades m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaEspecialidades.findByUsuarioCrea", query = "SELECT m FROM MaEspecialidades m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaEspecialidades.findByTerminalCrea", query = "SELECT m FROM MaEspecialidades m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaEspecialidades.findByFechaHoraCrea", query = "SELECT m FROM MaEspecialidades m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaEspecialidades.findByUsuarioModifica", query = "SELECT m FROM MaEspecialidades m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaEspecialidades.findByTerminalModifica", query = "SELECT m FROM MaEspecialidades m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaEspecialidades.findByFechaHporaModifica", query = "SELECT m FROM MaEspecialidades m WHERE m.fechaHporaModifica = :fechaHporaModifica")})
public class MaEspecialidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @Column(name = "fecha_hpora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHporaModifica;

    public MaEspecialidades() {
    }

    public MaEspecialidades(Integer id) {
        this.id = id;
    }

    public MaEspecialidades(Integer id, String codigo, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.activo = activo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public Date getFechaHporaModifica() {
        return fechaHporaModifica;
    }

    public void setFechaHporaModifica(Date fechaHporaModifica) {
        this.fechaHporaModifica = fechaHporaModifica;
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
        if (!(object instanceof MaEspecialidades)) {
            return false;
        }
        MaEspecialidades other = (MaEspecialidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaEspecialidades[ id=" + id + " ]";
    }
    
}
