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
@Table(name = "tu_representantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuRepresentantes.findAll", query = "SELECT t FROM TuRepresentantes t"),
    @NamedQuery(name = "TuRepresentantes.findById", query = "SELECT t FROM TuRepresentantes t WHERE t.id = :id"),
    @NamedQuery(name = "TuRepresentantes.findByNombre", query = "SELECT t FROM TuRepresentantes t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TuRepresentantes.findByActivo", query = "SELECT t FROM TuRepresentantes t WHERE t.activo = :activo"),
    @NamedQuery(name = "TuRepresentantes.findByUsuarioCrea", query = "SELECT t FROM TuRepresentantes t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuRepresentantes.findByTerminalCrea", query = "SELECT t FROM TuRepresentantes t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuRepresentantes.findByFechaHoraCrea", query = "SELECT t FROM TuRepresentantes t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuRepresentantes.findByUsuarioModifica", query = "SELECT t FROM TuRepresentantes t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuRepresentantes.findByTerminalModifica", query = "SELECT t FROM TuRepresentantes t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuRepresentantes.findByFechaHoraModifica", query = "SELECT t FROM TuRepresentantes t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuRepresentantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuRepresentantesId", fetch = FetchType.LAZY)
    private List<TuTutelaEstadoRepresentantes> tuTutelaEstadoRepresentantesList;

    public TuRepresentantes() {
    }

    public TuRepresentantes(Integer id) {
        this.id = id;
    }

    public TuRepresentantes(Integer id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<TuTutelaEstadoRepresentantes> getTuTutelaEstadoRepresentantesList() {
        return tuTutelaEstadoRepresentantesList;
    }

    public void setTuTutelaEstadoRepresentantesList(List<TuTutelaEstadoRepresentantes> tuTutelaEstadoRepresentantesList) {
        this.tuTutelaEstadoRepresentantesList = tuTutelaEstadoRepresentantesList;
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
        if (!(object instanceof TuRepresentantes)) {
            return false;
        }
        TuRepresentantes other = (TuRepresentantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuRepresentantes[ id=" + id + " ]";
    }
    
}
