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
@Table(name = "fin_giros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FinGiros.findAll", query = "SELECT f FROM FinGiros f"),
    @NamedQuery(name = "FinGiros.findById", query = "SELECT f FROM FinGiros f WHERE f.id = :id"),
    @NamedQuery(name = "FinGiros.findByTipo", query = "SELECT f FROM FinGiros f WHERE f.tipo = :tipo"),
    @NamedQuery(name = "FinGiros.findByNombre", query = "SELECT f FROM FinGiros f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FinGiros.findByBorrado", query = "SELECT f FROM FinGiros f WHERE f.borrado = :borrado"),
    @NamedQuery(name = "FinGiros.findByUsuarioCrea", query = "SELECT f FROM FinGiros f WHERE f.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "FinGiros.findByTerminalCrea", query = "SELECT f FROM FinGiros f WHERE f.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "FinGiros.findByFechaHoraCrea", query = "SELECT f FROM FinGiros f WHERE f.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "FinGiros.findByUsuarioModifica", query = "SELECT f FROM FinGiros f WHERE f.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "FinGiros.findByTerminalModifica", query = "SELECT f FROM FinGiros f WHERE f.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "FinGiros.findByFechaHoraModifica", query = "SELECT f FROM FinGiros f WHERE f.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "FinGiros.findByUsuarioBorra", query = "SELECT f FROM FinGiros f WHERE f.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "FinGiros.findByTerminalBorra", query = "SELECT f FROM FinGiros f WHERE f.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "FinGiros.findByFechaHoraBorra", query = "SELECT f FROM FinGiros f WHERE f.fechaHoraBorra = :fechaHoraBorra")})
public class FinGiros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "finGirosId", fetch = FetchType.LAZY)
    private List<FinPostulaciones> finPostulacionesList;

    public FinGiros() {
    }

    public FinGiros(Integer id) {
        this.id = id;
    }

    public FinGiros(Integer id, int tipo, String nombre, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    @XmlTransient
    public List<FinPostulaciones> getFinPostulacionesList() {
        return finPostulacionesList;
    }

    public void setFinPostulacionesList(List<FinPostulaciones> finPostulacionesList) {
        this.finPostulacionesList = finPostulacionesList;
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
        if (!(object instanceof FinGiros)) {
            return false;
        }
        FinGiros other = (FinGiros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.FinGiros[ id=" + id + " ]";
    }
    
}
