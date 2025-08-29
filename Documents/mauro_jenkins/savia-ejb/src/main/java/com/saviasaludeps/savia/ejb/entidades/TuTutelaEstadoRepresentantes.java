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
@Table(name = "tu_tutela_estado_representantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findAll", query = "SELECT t FROM TuTutelaEstadoRepresentantes t"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findById", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.id = :id"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findByUsuarioCrea", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findByTerminalCrea", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findByFechaHoraCrea", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findByUsuarioModifica", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findByTuTutelaEstadoRepresentantescol", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.tuTutelaEstadoRepresentantescol = :tuTutelaEstadoRepresentantescol"),
    @NamedQuery(name = "TuTutelaEstadoRepresentantes.findByFechaHoraModifica", query = "SELECT t FROM TuTutelaEstadoRepresentantes t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuTutelaEstadoRepresentantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Column(name = "tu_tutela_estado_representantescol")
    private String tuTutelaEstadoRepresentantescol;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "tu_representantes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuRepresentantes tuRepresentantesId;
    @JoinColumn(name = "tu_tutela_estados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelaEstados tuTutelaEstadosId;

    public TuTutelaEstadoRepresentantes() {
    }

    public TuTutelaEstadoRepresentantes(Integer id) {
        this.id = id;
    }

    public TuTutelaEstadoRepresentantes(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public String getTuTutelaEstadoRepresentantescol() {
        return tuTutelaEstadoRepresentantescol;
    }

    public void setTuTutelaEstadoRepresentantescol(String tuTutelaEstadoRepresentantescol) {
        this.tuTutelaEstadoRepresentantescol = tuTutelaEstadoRepresentantescol;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public TuRepresentantes getTuRepresentantesId() {
        return tuRepresentantesId;
    }

    public void setTuRepresentantesId(TuRepresentantes tuRepresentantesId) {
        this.tuRepresentantesId = tuRepresentantesId;
    }

    public TuTutelaEstados getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstados tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
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
        if (!(object instanceof TuTutelaEstadoRepresentantes)) {
            return false;
        }
        TuTutelaEstadoRepresentantes other = (TuTutelaEstadoRepresentantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuTutelaEstadoRepresentantes[ id=" + id + " ]";
    }
    
}
