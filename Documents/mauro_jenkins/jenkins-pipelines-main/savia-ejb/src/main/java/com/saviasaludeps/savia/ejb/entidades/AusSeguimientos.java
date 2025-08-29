/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "aus_seguimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusSeguimientos.findAll", query = "SELECT a FROM AusSeguimientos a"),
    @NamedQuery(name = "AusSeguimientos.findById", query = "SELECT a FROM AusSeguimientos a WHERE a.id = :id"),
    @NamedQuery(name = "AusSeguimientos.findByMaeEstadoId", query = "SELECT a FROM AusSeguimientos a WHERE a.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "AusSeguimientos.findByMaeEstadoCodigo", query = "SELECT a FROM AusSeguimientos a WHERE a.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "AusSeguimientos.findByMaeEstadoValor", query = "SELECT a FROM AusSeguimientos a WHERE a.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "AusSeguimientos.findByUsuarioCrea", query = "SELECT a FROM AusSeguimientos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusSeguimientos.findByTerminalCrea", query = "SELECT a FROM AusSeguimientos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusSeguimientos.findByFechaHoraCrea", query = "SELECT a FROM AusSeguimientos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusSeguimientos.findByUsuarioModifica", query = "SELECT a FROM AusSeguimientos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusSeguimientos.findByTerminalModifica", query = "SELECT a FROM AusSeguimientos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusSeguimientos.findByFechaHoraModifica", query = "SELECT a FROM AusSeguimientos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusSeguimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_id")
    private int maeEstadoId;
    @Size(max = 32)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Size(max = 512)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "aus_casos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusCasos ausCasosId;
    @OneToMany(mappedBy = "ausSeguimientosId", fetch = FetchType.LAZY)
    private List<AusAdjuntos> ausAdjuntosList;

    public AusSeguimientos() {
    }

    public AusSeguimientos(Integer id) {
        this.id = id;
    }

    public AusSeguimientos(Integer id, int maeEstadoId, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.observacion = observacion;
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

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public AusCasos getAusCasosId() {
        return ausCasosId;
    }

    public void setAusCasosId(AusCasos ausCasosId) {
        this.ausCasosId = ausCasosId;
    }

    @XmlTransient
    public List<AusAdjuntos> getAusAdjuntosList() {
        return ausAdjuntosList;
    }

    public void setAusAdjuntosList(List<AusAdjuntos> ausAdjuntosList) {
        this.ausAdjuntosList = ausAdjuntosList;
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
        if (!(object instanceof AusSeguimientos)) {
            return false;
        }
        AusSeguimientos other = (AusSeguimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusSeguimientos[ id=" + id + " ]";
    }
    
}
