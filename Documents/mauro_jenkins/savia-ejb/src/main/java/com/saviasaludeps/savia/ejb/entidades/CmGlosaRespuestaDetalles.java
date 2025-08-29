/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "cm_glosa_respuesta_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findAll", query = "SELECT c FROM CmGlosaRespuestaDetalles c"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findById", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.id = :id"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByDocumento", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.documento = :documento"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByValorCobroDetalle", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.valorCobroDetalle = :valorCobroDetalle"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByValorFacturado", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.valorFacturado = :valorFacturado"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByValorPagado", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.valorPagado = :valorPagado"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByValorPagadoEps", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.valorPagadoEps = :valorPagadoEps"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByPorcentajePagadoEps", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.porcentajePagadoEps = :porcentajePagadoEps"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByValorPendiente", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.valorPendiente = :valorPendiente"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByValorAceptadoIps", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.valorAceptadoIps = :valorAceptadoIps"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByPorcentajeAceptadoIps", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.porcentajeAceptadoIps = :porcentajeAceptadoIps"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByUsuarioCrea", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByTerminalCrea", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmGlosaRespuestaDetalles.findByFechaHoraCrea", query = "SELECT c FROM CmGlosaRespuestaDetalles c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmGlosaRespuestaDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento")
    private String documento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_cobro_detalle")
    private BigDecimal valorCobroDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_facturado")
    private BigDecimal valorFacturado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pagado_eps")
    private BigDecimal valorPagadoEps;
    @Column(name = "porcentaje_pagado_eps")
    private BigDecimal porcentajePagadoEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pendiente")
    private BigDecimal valorPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_aceptado_ips")
    private BigDecimal valorAceptadoIps;
    @Column(name = "porcentaje_aceptado_ips")
    private BigDecimal porcentajeAceptadoIps;
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
    @Size(min = 1, max = 45)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;
    @JoinColumn(name = "cm_glosa_respuestas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmGlosaRespuestas cmGlosaRespuestasId;

    public CmGlosaRespuestaDetalles() {
    }

    public CmGlosaRespuestaDetalles(Integer id) {
        this.id = id;
    }

    public CmGlosaRespuestaDetalles(Integer id, String documento, BigDecimal valorCobroDetalle, BigDecimal valorFacturado, BigDecimal valorPagado, BigDecimal valorPagadoEps, BigDecimal valorPendiente, BigDecimal valorAceptadoIps, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.documento = documento;
        this.valorCobroDetalle = valorCobroDetalle;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.valorPagadoEps = valorPagadoEps;
        this.valorPendiente = valorPendiente;
        this.valorAceptadoIps = valorAceptadoIps;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public BigDecimal getValorCobroDetalle() {
        return valorCobroDetalle;
    }

    public void setValorCobroDetalle(BigDecimal valorCobroDetalle) {
        this.valorCobroDetalle = valorCobroDetalle;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(BigDecimal valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
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

    public CmDetalles getCmDetallesId() {
        return cmDetallesId;
    }

    public void setCmDetallesId(CmDetalles cmDetallesId) {
        this.cmDetallesId = cmDetallesId;
    }

    public CmGlosaRespuestas getCmGlosaRespuestasId() {
        return cmGlosaRespuestasId;
    }

    public void setCmGlosaRespuestasId(CmGlosaRespuestas cmGlosaRespuestasId) {
        this.cmGlosaRespuestasId = cmGlosaRespuestasId;
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
        if (!(object instanceof CmGlosaRespuestaDetalles)) {
            return false;
        }
        CmGlosaRespuestaDetalles other = (CmGlosaRespuestaDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestaDetalles[ id=" + id + " ]";
    }
    
}
