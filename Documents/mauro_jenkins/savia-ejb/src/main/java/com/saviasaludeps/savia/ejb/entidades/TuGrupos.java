/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "tu_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuGrupos.findAll", query = "SELECT t FROM TuGrupos t"),
    @NamedQuery(name = "TuGrupos.findById", query = "SELECT t FROM TuGrupos t WHERE t.id = :id"),
    @NamedQuery(name = "TuGrupos.findByTipoAuditorInicial", query = "SELECT t FROM TuGrupos t WHERE t.tipoAuditorInicial = :tipoAuditorInicial"),
    @NamedQuery(name = "TuGrupos.findByNombre", query = "SELECT t FROM TuGrupos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TuGrupos.findByDescripcion", query = "SELECT t FROM TuGrupos t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TuGrupos.findByOrden", query = "SELECT t FROM TuGrupos t WHERE t.orden = :orden"),
    @NamedQuery(name = "TuGrupos.findByUltimoUsuarioId", query = "SELECT t FROM TuGrupos t WHERE t.ultimoUsuarioId = :ultimoUsuarioId"),
    @NamedQuery(name = "TuGrupos.findByActivo", query = "SELECT t FROM TuGrupos t WHERE t.activo = :activo"),
    @NamedQuery(name = "TuGrupos.findByUsuarioCrea", query = "SELECT t FROM TuGrupos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuGrupos.findByTerminalCrea", query = "SELECT t FROM TuGrupos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuGrupos.findByFechaHoraCrea", query = "SELECT t FROM TuGrupos t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuGrupos.findByUsuarioModifica", query = "SELECT t FROM TuGrupos t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuGrupos.findByTerminalModifica", query = "SELECT t FROM TuGrupos t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuGrupos.findByFechaHoraModifica", query = "SELECT t FROM TuGrupos t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_auditor_inicial")
    private int tipoAuditorInicial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultimo_usuario_id")
    private int ultimoUsuarioId;
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
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuGruposId", fetch = FetchType.LAZY)
    private List<TuGrupoUsuarios> tuGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuGruposId", fetch = FetchType.LAZY)
    private List<TuGrupoHistoricos> tuGrupoHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuGruposId", fetch = FetchType.LAZY)
    private List<TuGrupoEstados> tuGrupoEstadosList;

    public TuGrupos() {
    }

    public TuGrupos(Integer id) {
        this.id = id;
    }

    public TuGrupos(Integer id, int tipoAuditorInicial, String nombre, String descripcion, int orden, int ultimoUsuarioId, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoAuditorInicial = tipoAuditorInicial;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.orden = orden;
        this.ultimoUsuarioId = ultimoUsuarioId;
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

    public int getTipoAuditorInicial() {
        return tipoAuditorInicial;
    }

    public void setTipoAuditorInicial(int tipoAuditorInicial) {
        this.tipoAuditorInicial = tipoAuditorInicial;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getUltimoUsuarioId() {
        return ultimoUsuarioId;
    }

    public void setUltimoUsuarioId(int ultimoUsuarioId) {
        this.ultimoUsuarioId = ultimoUsuarioId;
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

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<TuGrupoUsuarios> getTuGrupoUsuariosList() {
        return tuGrupoUsuariosList;
    }

    public void setTuGrupoUsuariosList(List<TuGrupoUsuarios> tuGrupoUsuariosList) {
        this.tuGrupoUsuariosList = tuGrupoUsuariosList;
    }

    @XmlTransient
    public List<TuGrupoHistoricos> getTuGrupoHistoricosList() {
        return tuGrupoHistoricosList;
    }

    public void setTuGrupoHistoricosList(List<TuGrupoHistoricos> tuGrupoHistoricosList) {
        this.tuGrupoHistoricosList = tuGrupoHistoricosList;
    }

    @XmlTransient
    public List<TuGrupoEstados> getTuGrupoEstadosList() {
        return tuGrupoEstadosList;
    }

    public void setTuGrupoEstadosList(List<TuGrupoEstados> tuGrupoEstadosList) {
        this.tuGrupoEstadosList = tuGrupoEstadosList;
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
        if (!(object instanceof TuGrupos)) {
            return false;
        }
        TuGrupos other = (TuGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuGrupos[ id=" + id + " ]";
    }
    
}
