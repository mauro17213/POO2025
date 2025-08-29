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
@Table(name = "rco_conciliacion_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoConciliacionGestiones.findAll", query = "SELECT r FROM RcoConciliacionGestiones r"),
    @NamedQuery(name = "RcoConciliacionGestiones.findById", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.id = :id"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByAcuerdoRecobro", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.acuerdoRecobro = :acuerdoRecobro"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByObservacion", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByValorTotal", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.valorTotal = :valorTotal"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByValorAceptadoIps", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.valorAceptadoIps = :valorAceptadoIps"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByValorRestante", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.valorRestante = :valorRestante"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByCobroConciliacion", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.cobroConciliacion = :cobroConciliacion"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByUsuarioCrea", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByTerminalCrea", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByFechaHoraCrea", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByUsuarioModifica", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByTerminalModifica", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RcoConciliacionGestiones.findByFechaHoraModifica", query = "SELECT r FROM RcoConciliacionGestiones r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RcoConciliacionGestiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "acuerdo_recobro")
    private Integer acuerdoRecobro;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "valor_total")
    private Long valorTotal;
    @Column(name = "valor_aceptado_ips")
    private Long valorAceptadoIps;
    @Column(name = "valor_restante")
    private Long valorRestante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cobro_conciliacion")
    private boolean cobroConciliacion;
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
    @JoinColumn(name = "rco_conciliaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RcoConciliaciones rcoConciliacionesId;
    @JoinColumn(name = "rco_factura_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RcoFacturaDetalles rcoFacturaDetallesId;

    public RcoConciliacionGestiones() {
    }

    public RcoConciliacionGestiones(Integer id) {
        this.id = id;
    }

    public RcoConciliacionGestiones(Integer id, boolean cobroConciliacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.cobroConciliacion = cobroConciliacion;
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

    public Integer getAcuerdoRecobro() {
        return acuerdoRecobro;
    }

    public void setAcuerdoRecobro(Integer acuerdoRecobro) {
        this.acuerdoRecobro = acuerdoRecobro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(Long valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public Long getValorRestante() {
        return valorRestante;
    }

    public void setValorRestante(Long valorRestante) {
        this.valorRestante = valorRestante;
    }

    public boolean getCobroConciliacion() {
        return cobroConciliacion;
    }

    public void setCobroConciliacion(boolean cobroConciliacion) {
        this.cobroConciliacion = cobroConciliacion;
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

    public RcoConciliaciones getRcoConciliacionesId() {
        return rcoConciliacionesId;
    }

    public void setRcoConciliacionesId(RcoConciliaciones rcoConciliacionesId) {
        this.rcoConciliacionesId = rcoConciliacionesId;
    }

    public RcoFacturaDetalles getRcoFacturaDetallesId() {
        return rcoFacturaDetallesId;
    }

    public void setRcoFacturaDetallesId(RcoFacturaDetalles rcoFacturaDetallesId) {
        this.rcoFacturaDetallesId = rcoFacturaDetallesId;
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
        if (!(object instanceof RcoConciliacionGestiones)) {
            return false;
        }
        RcoConciliacionGestiones other = (RcoConciliacionGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoConciliacionGestiones[ id=" + id + " ]";
    }
    
}
