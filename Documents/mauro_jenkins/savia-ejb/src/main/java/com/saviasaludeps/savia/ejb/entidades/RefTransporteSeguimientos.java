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
@Table(name = "ref_transporte_seguimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefTransporteSeguimientos.findAll", query = "SELECT r FROM RefTransporteSeguimientos r"),
    @NamedQuery(name = "RefTransporteSeguimientos.findById", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.id = :id"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByMaeTipoReporteId", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.maeTipoReporteId = :maeTipoReporteId"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByMaeTipoReporteCodigo", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.maeTipoReporteCodigo = :maeTipoReporteCodigo"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByMaeTipoReporteValor", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.maeTipoReporteValor = :maeTipoReporteValor"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByObservacion", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByUsuarioCrea", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByTerminalCrea", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefTransporteSeguimientos.findByFechaHoraCrea", query = "SELECT r FROM RefTransporteSeguimientos r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RefTransporteSeguimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_reporte_id")
    private int maeTipoReporteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_reporte_codigo")
    private String maeTipoReporteCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_reporte_valor")
    private String maeTipoReporteValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
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
    @JoinColumn(name = "ref_transportes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefTransportes refTransportesId;

    public RefTransporteSeguimientos() {
    }

    public RefTransporteSeguimientos(Integer id) {
        this.id = id;
    }

    public RefTransporteSeguimientos(Integer id, int maeTipoReporteId, String maeTipoReporteCodigo, String maeTipoReporteValor, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoReporteId = maeTipoReporteId;
        this.maeTipoReporteCodigo = maeTipoReporteCodigo;
        this.maeTipoReporteValor = maeTipoReporteValor;
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

    public int getMaeTipoReporteId() {
        return maeTipoReporteId;
    }

    public void setMaeTipoReporteId(int maeTipoReporteId) {
        this.maeTipoReporteId = maeTipoReporteId;
    }

    public String getMaeTipoReporteCodigo() {
        return maeTipoReporteCodigo;
    }

    public void setMaeTipoReporteCodigo(String maeTipoReporteCodigo) {
        this.maeTipoReporteCodigo = maeTipoReporteCodigo;
    }

    public String getMaeTipoReporteValor() {
        return maeTipoReporteValor;
    }

    public void setMaeTipoReporteValor(String maeTipoReporteValor) {
        this.maeTipoReporteValor = maeTipoReporteValor;
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

    public RefTransportes getRefTransportesId() {
        return refTransportesId;
    }

    public void setRefTransportesId(RefTransportes refTransportesId) {
        this.refTransportesId = refTransportesId;
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
        if (!(object instanceof RefTransporteSeguimientos)) {
            return false;
        }
        RefTransporteSeguimientos other = (RefTransporteSeguimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefTransporteSeguimientos[ id=" + id + " ]";
    }
    
}
