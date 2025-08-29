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
@Table(name = "ws_cm_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsCmFacturas.findAll", query = "SELECT w FROM WsCmFacturas w"),
    @NamedQuery(name = "WsCmFacturas.findById", query = "SELECT w FROM WsCmFacturas w WHERE w.id = :id"),
    @NamedQuery(name = "WsCmFacturas.findByEstado", query = "SELECT w FROM WsCmFacturas w WHERE w.estado = :estado"),
    @NamedQuery(name = "WsCmFacturas.findByProveedorNit", query = "SELECT w FROM WsCmFacturas w WHERE w.proveedorNit = :proveedorNit"),
    @NamedQuery(name = "WsCmFacturas.findByNumeroDocumento", query = "SELECT w FROM WsCmFacturas w WHERE w.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "WsCmFacturas.findByNumeroRadicado", query = "SELECT w FROM WsCmFacturas w WHERE w.numeroRadicado = :numeroRadicado"),
    @NamedQuery(name = "WsCmFacturas.findByFacturaId", query = "SELECT w FROM WsCmFacturas w WHERE w.facturaId = :facturaId"),
    @NamedQuery(name = "WsCmFacturas.findByRegimen", query = "SELECT w FROM WsCmFacturas w WHERE w.regimen = :regimen"),
    @NamedQuery(name = "WsCmFacturas.findByValorDocumento", query = "SELECT w FROM WsCmFacturas w WHERE w.valorDocumento = :valorDocumento"),
    @NamedQuery(name = "WsCmFacturas.findByValorPagado", query = "SELECT w FROM WsCmFacturas w WHERE w.valorPagado = :valorPagado"),
    @NamedQuery(name = "WsCmFacturas.findByValorGlosado", query = "SELECT w FROM WsCmFacturas w WHERE w.valorGlosado = :valorGlosado"),
    @NamedQuery(name = "WsCmFacturas.findByValorCopago", query = "SELECT w FROM WsCmFacturas w WHERE w.valorCopago = :valorCopago"),
    @NamedQuery(name = "WsCmFacturas.findByFechaHoraDocumento", query = "SELECT w FROM WsCmFacturas w WHERE w.fechaHoraDocumento = :fechaHoraDocumento"),
    @NamedQuery(name = "WsCmFacturas.findByFechaHoraProceso", query = "SELECT w FROM WsCmFacturas w WHERE w.fechaHoraProceso = :fechaHoraProceso"),
    @NamedQuery(name = "WsCmFacturas.findByCuotaModeradora", query = "SELECT w FROM WsCmFacturas w WHERE w.cuotaModeradora = :cuotaModeradora"),
    @NamedQuery(name = "WsCmFacturas.findByContrato", query = "SELECT w FROM WsCmFacturas w WHERE w.contrato = :contrato"),
    @NamedQuery(name = "WsCmFacturas.findByUsuarioCrea", query = "SELECT w FROM WsCmFacturas w WHERE w.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "WsCmFacturas.findByTerminalCrea", query = "SELECT w FROM WsCmFacturas w WHERE w.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "WsCmFacturas.findByFechaHoraCrea", query = "SELECT w FROM WsCmFacturas w WHERE w.fechaHoraCrea = :fechaHoraCrea")})
public class WsCmFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Size(max = 16)
    @Column(name = "proveedor_nit")
    private String proveedorNit;
    @Size(max = 32)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Size(max = 32)
    @Column(name = "numero_radicado")
    private String numeroRadicado;
    @Column(name = "factura_id")
    private Integer facturaId;
    @Size(max = 16)
    @Column(name = "regimen")
    private String regimen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_documento")
    private BigDecimal valorDocumento;
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Column(name = "valor_glosado")
    private BigDecimal valorGlosado;
    @Column(name = "valor_copago")
    private BigDecimal valorCopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_documento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_proceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraProceso;
    @Column(name = "cuota_moderadora")
    private BigDecimal cuotaModeradora;
    @Size(max = 32)
    @Column(name = "contrato")
    private String contrato;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsCmFacturasId", fetch = FetchType.LAZY)
    private List<WsCmFacturaDetalles> wsCmFacturaDetallesList;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @JoinColumn(name = "cm_radicados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRadicados cmRadicadosId;

    public WsCmFacturas() {
    }

    public WsCmFacturas(Integer id) {
        this.id = id;
    }

    public WsCmFacturas(Integer id, short estado, Date fechaHoraDocumento, Date fechaHoraProceso, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraDocumento = fechaHoraDocumento;
        this.fechaHoraProceso = fechaHoraProceso;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(String proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public BigDecimal getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public Date getFechaHoraDocumento() {
        return fechaHoraDocumento;
    }

    public void setFechaHoraDocumento(Date fechaHoraDocumento) {
        this.fechaHoraDocumento = fechaHoraDocumento;
    }

    public Date getFechaHoraProceso() {
        return fechaHoraProceso;
    }

    public void setFechaHoraProceso(Date fechaHoraProceso) {
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public BigDecimal getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
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

    @XmlTransient
    public List<WsCmFacturaDetalles> getWsCmFacturaDetallesList() {
        return wsCmFacturaDetallesList;
    }

    public void setWsCmFacturaDetallesList(List<WsCmFacturaDetalles> wsCmFacturaDetallesList) {
        this.wsCmFacturaDetallesList = wsCmFacturaDetallesList;
    }

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public CmRadicados getCmRadicadosId() {
        return cmRadicadosId;
    }

    public void setCmRadicadosId(CmRadicados cmRadicadosId) {
        this.cmRadicadosId = cmRadicadosId;
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
        if (!(object instanceof WsCmFacturas)) {
            return false;
        }
        WsCmFacturas other = (WsCmFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsCmFacturas[ id=" + id + " ]";
    }
    
}
