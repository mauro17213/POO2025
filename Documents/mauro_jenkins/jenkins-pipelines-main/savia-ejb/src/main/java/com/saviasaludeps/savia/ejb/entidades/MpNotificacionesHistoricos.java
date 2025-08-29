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
@Table(name = "mp_notificaciones_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpNotificacionesHistoricos.findAll", query = "SELECT m FROM MpNotificacionesHistoricos m"),
    @NamedQuery(name = "MpNotificacionesHistoricos.findById", query = "SELECT m FROM MpNotificacionesHistoricos m WHERE m.id = :id"),
    @NamedQuery(name = "MpNotificacionesHistoricos.findByProcedencia", query = "SELECT m FROM MpNotificacionesHistoricos m WHERE m.procedencia = :procedencia"),
    @NamedQuery(name = "MpNotificacionesHistoricos.findByMensaje", query = "SELECT m FROM MpNotificacionesHistoricos m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "MpNotificacionesHistoricos.findByUsuarioCrea", query = "SELECT m FROM MpNotificacionesHistoricos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpNotificacionesHistoricos.findByTerminalCrea", query = "SELECT m FROM MpNotificacionesHistoricos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpNotificacionesHistoricos.findByFechaHoraCrea", query = "SELECT m FROM MpNotificacionesHistoricos m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpNotificacionesHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procedencia")
    private int procedencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "mensaje")
    private String mensaje;
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
    @JoinColumn(name = "mp_prescripcion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionId;
    @JoinColumn(name = "mp_prescripcion_insumo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionInsumos mpPrescripcionInsumoId;
    @JoinColumn(name = "mp_prescripcion_medicamento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentoId;
    @JoinColumn(name = "mp_prescripcion_tecnologia_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionTecnologias mpPrescripcionTecnologiaId;

    public MpNotificacionesHistoricos() {
    }

    public MpNotificacionesHistoricos(Integer id) {
        this.id = id;
    }

    public MpNotificacionesHistoricos(Integer id, int procedencia, String mensaje, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.procedencia = procedencia;
        this.mensaje = mensaje;
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

    public int getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(int procedencia) {
        this.procedencia = procedencia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public MpPrescripciones getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripciones mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    public MpPrescripcionInsumos getMpPrescripcionInsumoId() {
        return mpPrescripcionInsumoId;
    }

    public void setMpPrescripcionInsumoId(MpPrescripcionInsumos mpPrescripcionInsumoId) {
        this.mpPrescripcionInsumoId = mpPrescripcionInsumoId;
    }

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentoId() {
        return mpPrescripcionMedicamentoId;
    }

    public void setMpPrescripcionMedicamentoId(MpPrescripcionMedicamentos mpPrescripcionMedicamentoId) {
        this.mpPrescripcionMedicamentoId = mpPrescripcionMedicamentoId;
    }

    public MpPrescripcionTecnologias getMpPrescripcionTecnologiaId() {
        return mpPrescripcionTecnologiaId;
    }

    public void setMpPrescripcionTecnologiaId(MpPrescripcionTecnologias mpPrescripcionTecnologiaId) {
        this.mpPrescripcionTecnologiaId = mpPrescripcionTecnologiaId;
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
        if (!(object instanceof MpNotificacionesHistoricos)) {
            return false;
        }
        MpNotificacionesHistoricos other = (MpNotificacionesHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpNotificacionesHistoricos[ id=" + id + " ]";
    }
    
}
