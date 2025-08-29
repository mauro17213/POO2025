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
@Table(name = "mp_medicamento_indicaciones_unirs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findAll", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m"),
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findById", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m WHERE m.id = :id"),
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findByConsecutivoOrden", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findByCodigoIndicacion", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m WHERE m.codigoIndicacion = :codigoIndicacion"),
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findByUsuarioCrea", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findByTerminalCrea", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpMedicamentoIndicacionesUnirs.findByFechaHoraCrea", query = "SELECT m FROM MpMedicamentoIndicacionesUnirs m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpMedicamentoIndicacionesUnirs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo_orden")
    private int consecutivoOrden;
    @Size(max = 8)
    @Column(name = "codigo_indicacion")
    private String codigoIndicacion;
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
    @JoinColumn(name = "mp_codigo_unirs_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpCodigoUnirs mpCodigoUnirsId;
    @JoinColumn(name = "mp_prescripcion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentosId;

    public MpMedicamentoIndicacionesUnirs() {
    }

    public MpMedicamentoIndicacionesUnirs(Integer id) {
        this.id = id;
    }

    public MpMedicamentoIndicacionesUnirs(Integer id, int consecutivoOrden, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.consecutivoOrden = consecutivoOrden;
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

    public int getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(int consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public String getCodigoIndicacion() {
        return codigoIndicacion;
    }

    public void setCodigoIndicacion(String codigoIndicacion) {
        this.codigoIndicacion = codigoIndicacion;
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

    public MpCodigoUnirs getMpCodigoUnirsId() {
        return mpCodigoUnirsId;
    }

    public void setMpCodigoUnirsId(MpCodigoUnirs mpCodigoUnirsId) {
        this.mpCodigoUnirsId = mpCodigoUnirsId;
    }

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamentos mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
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
        if (!(object instanceof MpMedicamentoIndicacionesUnirs)) {
            return false;
        }
        MpMedicamentoIndicacionesUnirs other = (MpMedicamentoIndicacionesUnirs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpMedicamentoIndicacionesUnirs[ id=" + id + " ]";
    }
    
}
