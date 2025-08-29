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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "mp_programaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpProgramaciones.findAll", query = "SELECT m FROM MpProgramaciones m"),
    @NamedQuery(name = "MpProgramaciones.findById", query = "SELECT m FROM MpProgramaciones m WHERE m.id = :id"),
    @NamedQuery(name = "MpProgramaciones.findByDirecionamientoId", query = "SELECT m FROM MpProgramaciones m WHERE m.direcionamientoId = :direcionamientoId"),
    @NamedQuery(name = "MpProgramaciones.findByFechaDireccionamiento", query = "SELECT m FROM MpProgramaciones m WHERE m.fechaDireccionamiento = :fechaDireccionamiento"),
    @NamedQuery(name = "MpProgramaciones.findByFechaMaxEntrega", query = "SELECT m FROM MpProgramaciones m WHERE m.fechaMaxEntrega = :fechaMaxEntrega"),
    @NamedQuery(name = "MpProgramaciones.findByCantidadTotalPrescrita", query = "SELECT m FROM MpProgramaciones m WHERE m.cantidadTotalPrescrita = :cantidadTotalPrescrita"),
    @NamedQuery(name = "MpProgramaciones.findByCantidadPendienteEntregar", query = "SELECT m FROM MpProgramaciones m WHERE m.cantidadPendienteEntregar = :cantidadPendienteEntregar"),
    @NamedQuery(name = "MpProgramaciones.findByTotalEntregar", query = "SELECT m FROM MpProgramaciones m WHERE m.totalEntregar = :totalEntregar"),
    @NamedQuery(name = "MpProgramaciones.findByNumeroProgramacion", query = "SELECT m FROM MpProgramaciones m WHERE m.numeroProgramacion = :numeroProgramacion"),
    @NamedQuery(name = "MpProgramaciones.findBySubEntrega", query = "SELECT m FROM MpProgramaciones m WHERE m.subEntrega = :subEntrega"),
    @NamedQuery(name = "MpProgramaciones.findByCantidadPendiente", query = "SELECT m FROM MpProgramaciones m WHERE m.cantidadPendiente = :cantidadPendiente"),
    @NamedQuery(name = "MpProgramaciones.findByEstadoProgramacion", query = "SELECT m FROM MpProgramaciones m WHERE m.estadoProgramacion = :estadoProgramacion"),
    @NamedQuery(name = "MpProgramaciones.findByFechaAnulacionDireccionamiento", query = "SELECT m FROM MpProgramaciones m WHERE m.fechaAnulacionDireccionamiento = :fechaAnulacionDireccionamiento"),
    @NamedQuery(name = "MpProgramaciones.findByCausalNoEntrega", query = "SELECT m FROM MpProgramaciones m WHERE m.causalNoEntrega = :causalNoEntrega"),
    @NamedQuery(name = "MpProgramaciones.findByUsuarioCrea", query = "SELECT m FROM MpProgramaciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpProgramaciones.findByTerminalCrea", query = "SELECT m FROM MpProgramaciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpProgramaciones.findByFechaHoraCrea", query = "SELECT m FROM MpProgramaciones m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpProgramaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "direcionamiento_id")
    private Integer direcionamientoId;
    @Column(name = "fecha_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamiento;
    @Column(name = "fecha_max_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMaxEntrega;
    @Column(name = "cantidad_total_prescrita")
    private Integer cantidadTotalPrescrita;
    @Column(name = "cantidad_pendiente_entregar")
    private Integer cantidadPendienteEntregar;
    @Column(name = "total_entregar")
    private Integer totalEntregar;
    @Column(name = "numero_programacion")
    private Integer numeroProgramacion;
    @Column(name = "sub_entrega")
    private Integer subEntrega;
    @Column(name = "cantidad_pendiente")
    private Integer cantidadPendiente;
    @Column(name = "estado_programacion")
    private Integer estadoProgramacion;
    @Column(name = "fecha_anulacion_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacionDireccionamiento;
    @Size(max = 2048)
    @Column(name = "causal_no_entrega")
    private String causalNoEntrega;
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

    public MpProgramaciones() {
    }

    public MpProgramaciones(Integer id) {
        this.id = id;
    }

    public MpProgramaciones(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public Integer getDirecionamientoId() {
        return direcionamientoId;
    }

    public void setDirecionamientoId(Integer direcionamientoId) {
        this.direcionamientoId = direcionamientoId;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaxEntrega() {
        return fechaMaxEntrega;
    }

    public void setFechaMaxEntrega(Date fechaMaxEntrega) {
        this.fechaMaxEntrega = fechaMaxEntrega;
    }

    public Integer getCantidadTotalPrescrita() {
        return cantidadTotalPrescrita;
    }

    public void setCantidadTotalPrescrita(Integer cantidadTotalPrescrita) {
        this.cantidadTotalPrescrita = cantidadTotalPrescrita;
    }

    public Integer getCantidadPendienteEntregar() {
        return cantidadPendienteEntregar;
    }

    public void setCantidadPendienteEntregar(Integer cantidadPendienteEntregar) {
        this.cantidadPendienteEntregar = cantidadPendienteEntregar;
    }

    public Integer getTotalEntregar() {
        return totalEntregar;
    }

    public void setTotalEntregar(Integer totalEntregar) {
        this.totalEntregar = totalEntregar;
    }

    public Integer getNumeroProgramacion() {
        return numeroProgramacion;
    }

    public void setNumeroProgramacion(Integer numeroProgramacion) {
        this.numeroProgramacion = numeroProgramacion;
    }

    public Integer getSubEntrega() {
        return subEntrega;
    }

    public void setSubEntrega(Integer subEntrega) {
        this.subEntrega = subEntrega;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public Integer getEstadoProgramacion() {
        return estadoProgramacion;
    }

    public void setEstadoProgramacion(Integer estadoProgramacion) {
        this.estadoProgramacion = estadoProgramacion;
    }

    public Date getFechaAnulacionDireccionamiento() {
        return fechaAnulacionDireccionamiento;
    }

    public void setFechaAnulacionDireccionamiento(Date fechaAnulacionDireccionamiento) {
        this.fechaAnulacionDireccionamiento = fechaAnulacionDireccionamiento;
    }

    public String getCausalNoEntrega() {
        return causalNoEntrega;
    }

    public void setCausalNoEntrega(String causalNoEntrega) {
        this.causalNoEntrega = causalNoEntrega;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MpProgramaciones)) {
            return false;
        }
        MpProgramaciones other = (MpProgramaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpProgramaciones[ id=" + id + " ]";
    }
    
}
