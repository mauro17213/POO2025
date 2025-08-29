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
@Table(name = "cm_auditoria_cierres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaCierres.findAll", query = "SELECT c FROM CmAuditoriaCierres c"),
    @NamedQuery(name = "CmAuditoriaCierres.findById", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaCierres.findByValorFacturado", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.valorFacturado = :valorFacturado"),
    @NamedQuery(name = "CmAuditoriaCierres.findByValorGlosado", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.valorGlosado = :valorGlosado"),
    @NamedQuery(name = "CmAuditoriaCierres.findByValorPagado", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.valorPagado = :valorPagado"),
    @NamedQuery(name = "CmAuditoriaCierres.findByCantidadDetalles", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.cantidadDetalles = :cantidadDetalles"),
    @NamedQuery(name = "CmAuditoriaCierres.findByCantidadDetallesRegistradas", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.cantidadDetallesRegistradas = :cantidadDetallesRegistradas"),
    @NamedQuery(name = "CmAuditoriaCierres.findByFechaHoraRegistroInicio", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.fechaHoraRegistroInicio = :fechaHoraRegistroInicio"),
    @NamedQuery(name = "CmAuditoriaCierres.findByFechaHoraRegistroFinalizacion", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.fechaHoraRegistroFinalizacion = :fechaHoraRegistroFinalizacion"),
    @NamedQuery(name = "CmAuditoriaCierres.findByUsuariosCrea", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.usuariosCrea = :usuariosCrea"),
    @NamedQuery(name = "CmAuditoriaCierres.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaCierres.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaCierres c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmAuditoriaCierres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_facturado")
    private BigDecimal valorFacturado;
    @Column(name = "valor_glosado")
    private BigDecimal valorGlosado;
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_detalles")
    private int cantidadDetalles;
    @Column(name = "cantidad_detalles_registradas")
    private Integer cantidadDetallesRegistradas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_registro_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistroInicio;
    @Column(name = "fecha_hora_registro_finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRegistroFinalizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuarios_crea")
    private String usuariosCrea;
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
    @JoinColumn(name = "cm_auditoria_masiva_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmAuditoriaMasiva cmAuditoriaMasivaId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @OneToMany(mappedBy = "cmAuditoriaCierresId", fetch = FetchType.LAZY)
    private List<CmSincronizaciones> cmSincronizacionesList;
    @OneToMany(mappedBy = "cmAuditoriaCierresId", fetch = FetchType.LAZY)
    private List<WsCmTransacciones> wsCmTransaccionesList;
    @OneToMany(mappedBy = "cmAuditoriaCierresId", fetch = FetchType.LAZY)
    private List<CmRadicados> cmRadicadosList;

    public CmAuditoriaCierres() {
    }

    public CmAuditoriaCierres(Integer id) {
        this.id = id;
    }

    public CmAuditoriaCierres(Integer id, BigDecimal valorFacturado, int cantidadDetalles, Date fechaHoraRegistroInicio, String usuariosCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valorFacturado = valorFacturado;
        this.cantidadDetalles = cantidadDetalles;
        this.fechaHoraRegistroInicio = fechaHoraRegistroInicio;
        this.usuariosCrea = usuariosCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public int getCantidadDetalles() {
        return cantidadDetalles;
    }

    public void setCantidadDetalles(int cantidadDetalles) {
        this.cantidadDetalles = cantidadDetalles;
    }

    public Integer getCantidadDetallesRegistradas() {
        return cantidadDetallesRegistradas;
    }

    public void setCantidadDetallesRegistradas(Integer cantidadDetallesRegistradas) {
        this.cantidadDetallesRegistradas = cantidadDetallesRegistradas;
    }

    public Date getFechaHoraRegistroInicio() {
        return fechaHoraRegistroInicio;
    }

    public void setFechaHoraRegistroInicio(Date fechaHoraRegistroInicio) {
        this.fechaHoraRegistroInicio = fechaHoraRegistroInicio;
    }

    public Date getFechaHoraRegistroFinalizacion() {
        return fechaHoraRegistroFinalizacion;
    }

    public void setFechaHoraRegistroFinalizacion(Date fechaHoraRegistroFinalizacion) {
        this.fechaHoraRegistroFinalizacion = fechaHoraRegistroFinalizacion;
    }

    public String getUsuariosCrea() {
        return usuariosCrea;
    }

    public void setUsuariosCrea(String usuariosCrea) {
        this.usuariosCrea = usuariosCrea;
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

    public CmAuditoriaMasiva getCmAuditoriaMasivaId() {
        return cmAuditoriaMasivaId;
    }

    public void setCmAuditoriaMasivaId(CmAuditoriaMasiva cmAuditoriaMasivaId) {
        this.cmAuditoriaMasivaId = cmAuditoriaMasivaId;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmAuditoriaCierres)) {
            return false;
        }
        CmAuditoriaCierres other = (CmAuditoriaCierres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCierres[ id=" + id + " ]";
    }
    
}
