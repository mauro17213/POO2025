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
@Table(name = "mp_prescripcion_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionHistoricos.findAll", query = "SELECT m FROM MpPrescripcionHistoricos m"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findById", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findByTipoTecnologia", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findByIdPrescripcionTecnologia", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.idPrescripcionTecnologia = :idPrescripcionTecnologia"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findByEstado", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionHistoricos.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionHistoricos m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpPrescripcionHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prescripcion_tecnologia")
    private int idPrescripcionTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;

    public MpPrescripcionHistoricos() {
    }

    public MpPrescripcionHistoricos(Integer id) {
        this.id = id;
    }

    public MpPrescripcionHistoricos(Integer id, int tipoTecnologia, int idPrescripcionTecnologia, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.idPrescripcionTecnologia = idPrescripcionTecnologia;
        this.estado = estado;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getIdPrescripcionTecnologia() {
        return idPrescripcionTecnologia;
    }

    public void setIdPrescripcionTecnologia(int idPrescripcionTecnologia) {
        this.idPrescripcionTecnologia = idPrescripcionTecnologia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
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
        if (!(object instanceof MpPrescripcionHistoricos)) {
            return false;
        }
        MpPrescripcionHistoricos other = (MpPrescripcionHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionHistoricos[ id=" + id + " ]";
    }
    
}
