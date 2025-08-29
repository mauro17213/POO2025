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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "mp_direccionamientos_programadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpDireccionamientosProgramadas.findAll", query = "SELECT m FROM MpDireccionamientosProgramadas m"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findById", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.id = :id"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByIdReporteEntrega", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.idReporteEntrega = :idReporteEntrega"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByCantidadEntrega", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.cantidadEntrega = :cantidadEntrega"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByNumeroEntrega", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByEntregaTotal", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.entregaTotal = :entregaTotal"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByCausaNoEntrega", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.causaNoEntrega = :causaNoEntrega"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByEstado", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByFechaEntrega", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByFechaAnulacion", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByJustificacionDireccionamiento", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.justificacionDireccionamiento = :justificacionDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByFechaDireccionamientoAutomatico", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.fechaDireccionamientoAutomatico = :fechaDireccionamientoAutomatico"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByFechaMaximaDireccionamiento", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.fechaMaximaDireccionamiento = :fechaMaximaDireccionamiento"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByUsuarioCrea", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByTerminalCrea", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpDireccionamientosProgramadas.findByFechaHoraCrea", query = "SELECT m FROM MpDireccionamientosProgramadas m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpDireccionamientosProgramadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_reporte_entrega")
    private Integer idReporteEntrega;
    @Column(name = "cantidad_entrega")
    private Integer cantidadEntrega;
    @Column(name = "numero_entrega")
    private Integer numeroEntrega;
    @Column(name = "entrega_total")
    private Integer entregaTotal;
    @Column(name = "causa_no_entrega")
    private Integer causaNoEntrega;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "fecha_anulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAnulacion;
    @Size(max = 2048)
    @Column(name = "justificacion_direccionamiento")
    private String justificacionDireccionamiento;
    @Column(name = "fecha_direccionamiento_automatico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamientoAutomatico;
    @Column(name = "fecha_maxima_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMaximaDireccionamiento;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "mp_direccionamientos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpDireccionamientos mpDireccionamientosId;

    public MpDireccionamientosProgramadas() {
    }

    public MpDireccionamientosProgramadas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdReporteEntrega() {
        return idReporteEntrega;
    }

    public void setIdReporteEntrega(Integer idReporteEntrega) {
        this.idReporteEntrega = idReporteEntrega;
    }

    public Integer getCantidadEntrega() {
        return cantidadEntrega;
    }

    public void setCantidadEntrega(Integer cantidadEntrega) {
        this.cantidadEntrega = cantidadEntrega;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public Integer getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(Integer entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public Integer getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(Integer causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getJustificacionDireccionamiento() {
        return justificacionDireccionamiento;
    }

    public void setJustificacionDireccionamiento(String justificacionDireccionamiento) {
        this.justificacionDireccionamiento = justificacionDireccionamiento;
    }

    public Date getFechaDireccionamientoAutomatico() {
        return fechaDireccionamientoAutomatico;
    }

    public void setFechaDireccionamientoAutomatico(Date fechaDireccionamientoAutomatico) {
        this.fechaDireccionamientoAutomatico = fechaDireccionamientoAutomatico;
    }

    public Date getFechaMaximaDireccionamiento() {
        return fechaMaximaDireccionamiento;
    }

    public void setFechaMaximaDireccionamiento(Date fechaMaximaDireccionamiento) {
        this.fechaMaximaDireccionamiento = fechaMaximaDireccionamiento;
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

    public MpDireccionamientos getMpDireccionamientosId() {
        return mpDireccionamientosId;
    }

    public void setMpDireccionamientosId(MpDireccionamientos mpDireccionamientosId) {
        this.mpDireccionamientosId = mpDireccionamientosId;
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
        if (!(object instanceof MpDireccionamientosProgramadas)) {
            return false;
        }
        MpDireccionamientosProgramadas other = (MpDireccionamientosProgramadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpDireccionamientosProgramadas[ id=" + id + " ]";
    }
    
}
