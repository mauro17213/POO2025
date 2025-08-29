/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "cm_glosa_respuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmGlosaRespuestas.findAll", query = "SELECT c FROM CmGlosaRespuestas c"),
    @NamedQuery(name = "CmGlosaRespuestas.findById", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.id = :id"),
    @NamedQuery(name = "CmGlosaRespuestas.findByTipoRespuesta", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.tipoRespuesta = :tipoRespuesta"),
    @NamedQuery(name = "CmGlosaRespuestas.findByValorCobroDetalle", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.valorCobroDetalle = :valorCobroDetalle"),
    @NamedQuery(name = "CmGlosaRespuestas.findByValorFacturado", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.valorFacturado = :valorFacturado"),
    @NamedQuery(name = "CmGlosaRespuestas.findByValorPagado", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.valorPagado = :valorPagado"),
    @NamedQuery(name = "CmGlosaRespuestas.findByVaorPagadoEps", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.vaorPagadoEps = :vaorPagadoEps"),
    @NamedQuery(name = "CmGlosaRespuestas.findByValorPendiente", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.valorPendiente = :valorPendiente"),
    @NamedQuery(name = "CmGlosaRespuestas.findByValorAceptadoIps", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.valorAceptadoIps = :valorAceptadoIps"),
    @NamedQuery(name = "CmGlosaRespuestas.findByEstadoSincronizacion", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.estadoSincronizacion = :estadoSincronizacion"),
    @NamedQuery(name = "CmGlosaRespuestas.findByRepresentanteEps", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.representanteEps = :representanteEps"),
    @NamedQuery(name = "CmGlosaRespuestas.findByRepresentanteIps", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.representanteIps = :representanteIps"),
    @NamedQuery(name = "CmGlosaRespuestas.findByUsuarioCrea", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmGlosaRespuestas.findByTerminalCrea", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmGlosaRespuestas.findByFechaHoraCrea", query = "SELECT c FROM CmGlosaRespuestas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmGlosaRespuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo_respuesta")
    private Integer tipoRespuesta;
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
    @Column(name = "vaor_pagado_eps")
    private BigDecimal vaorPagadoEps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pendiente")
    private BigDecimal valorPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_aceptado_ips")
    private BigDecimal valorAceptadoIps;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_sincronizacion")
    private int estadoSincronizacion;
    @Size(max = 128)
    @Column(name = "representante_eps")
    private String representanteEps;
    @Size(max = 128)
    @Column(name = "representante_ips")
    private String representanteIps;
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
    @JoinColumn(name = "cm_glosa_masiva_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmGlosaMasiva cmGlosaMasivaId;
    @JoinColumn(name = "cm_conciliaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmConciliaciones cmConciliacionesId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmGlosaRespuestasId", fetch = FetchType.LAZY)
    private List<CmGlosaRespuestaDetalles> cmGlosaRespuestaDetallesList;
    @OneToMany(mappedBy = "cmGlosaRespuestasId", fetch = FetchType.LAZY)
    private List<CmSincronizaciones> cmSincronizacionesList;
    @OneToMany(mappedBy = "cmGlosaRespuestasId", fetch = FetchType.LAZY)
    private List<WsCmTransacciones> wsCmTransaccionesList;
    @OneToMany(mappedBy = "cmGlosaRespuestasId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;
    @OneToMany(mappedBy = "cmGlosaRespuestasConciliacionId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList1;

    public CmGlosaRespuestas() {
    }

    public CmGlosaRespuestas(Integer id) {
        this.id = id;
    }

    public CmGlosaRespuestas(Integer id, BigDecimal valorCobroDetalle, BigDecimal valorFacturado, BigDecimal valorPagado, BigDecimal vaorPagadoEps, BigDecimal valorPendiente, BigDecimal valorAceptadoIps, String observacion, int estadoSincronizacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valorCobroDetalle = valorCobroDetalle;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.vaorPagadoEps = vaorPagadoEps;
        this.valorPendiente = valorPendiente;
        this.valorAceptadoIps = valorAceptadoIps;
        this.observacion = observacion;
        this.estadoSincronizacion = estadoSincronizacion;
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

    public Integer getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(Integer tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
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

    public BigDecimal getVaorPagadoEps() {
        return vaorPagadoEps;
    }

    public void setVaorPagadoEps(BigDecimal vaorPagadoEps) {
        this.vaorPagadoEps = vaorPagadoEps;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstadoSincronizacion() {
        return estadoSincronizacion;
    }

    public void setEstadoSincronizacion(int estadoSincronizacion) {
        this.estadoSincronizacion = estadoSincronizacion;
    }

    public String getRepresentanteEps() {
        return representanteEps;
    }

    public void setRepresentanteEps(String representanteEps) {
        this.representanteEps = representanteEps;
    }

    public String getRepresentanteIps() {
        return representanteIps;
    }

    public void setRepresentanteIps(String representanteIps) {
        this.representanteIps = representanteIps;
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

    public CmGlosaMasiva getCmGlosaMasivaId() {
        return cmGlosaMasivaId;
    }

    public void setCmGlosaMasivaId(CmGlosaMasiva cmGlosaMasivaId) {
        this.cmGlosaMasivaId = cmGlosaMasivaId;
    }

    public CmConciliaciones getCmConciliacionesId() {
        return cmConciliacionesId;
    }

    public void setCmConciliacionesId(CmConciliaciones cmConciliacionesId) {
        this.cmConciliacionesId = cmConciliacionesId;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    @XmlTransient
    public List<CmGlosaRespuestaDetalles> getCmGlosaRespuestaDetallesList() {
        return cmGlosaRespuestaDetallesList;
    }

    public void setCmGlosaRespuestaDetallesList(List<CmGlosaRespuestaDetalles> cmGlosaRespuestaDetallesList) {
        this.cmGlosaRespuestaDetallesList = cmGlosaRespuestaDetallesList;
    }

    @XmlTransient
    public List<CmSincronizaciones> getCmSincronizacionesList() {
        return cmSincronizacionesList;
    }

    public void setCmSincronizacionesList(List<CmSincronizaciones> cmSincronizacionesList) {
        this.cmSincronizacionesList = cmSincronizacionesList;
    }

    @XmlTransient
    public List<WsCmTransacciones> getWsCmTransaccionesList() {
        return wsCmTransaccionesList;
    }

    public void setWsCmTransaccionesList(List<WsCmTransacciones> wsCmTransaccionesList) {
        this.wsCmTransaccionesList = wsCmTransaccionesList;
    }

    @XmlTransient
    public List<CmRadicados> getCmRadicadosList() {
        return cmRadicadosList;
    }

    public void setCmRadicadosList(List<CmRadicados> cmRadicadosList) {
        this.cmRadicadosList = cmRadicadosList;
    }

    @XmlTransient
    public List<CmRadicados> getCmRadicadosList1() {
        return cmRadicadosList1;
    }

    public void setCmRadicadosList1(List<CmRadicados> cmRadicadosList1) {
        this.cmRadicadosList1 = cmRadicadosList1;
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
        if (!(object instanceof CmGlosaRespuestas)) {
            return false;
        }
        CmGlosaRespuestas other = (CmGlosaRespuestas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmGlosaRespuestas[ id=" + id + " ]";
    }
    
}
