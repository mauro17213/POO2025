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
@Table(name = "cm_gestion_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmGestionUsuarios.findAll", query = "SELECT c FROM CmGestionUsuarios c"),
    @NamedQuery(name = "CmGestionUsuarios.findById", query = "SELECT c FROM CmGestionUsuarios c WHERE c.id = :id"),
    @NamedQuery(name = "CmGestionUsuarios.findByEstado", query = "SELECT c FROM CmGestionUsuarios c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmGestionUsuarios.findByValorPagadoEps", query = "SELECT c FROM CmGestionUsuarios c WHERE c.valorPagadoEps = :valorPagadoEps"),
    @NamedQuery(name = "CmGestionUsuarios.findByPorcentajePagadoEps", query = "SELECT c FROM CmGestionUsuarios c WHERE c.porcentajePagadoEps = :porcentajePagadoEps"),
    @NamedQuery(name = "CmGestionUsuarios.findByValorAceptadoIps", query = "SELECT c FROM CmGestionUsuarios c WHERE c.valorAceptadoIps = :valorAceptadoIps"),
    @NamedQuery(name = "CmGestionUsuarios.findByPorcentajeAceptadoIps", query = "SELECT c FROM CmGestionUsuarios c WHERE c.porcentajeAceptadoIps = :porcentajeAceptadoIps"),
    @NamedQuery(name = "CmGestionUsuarios.findByUsuarioGestiona", query = "SELECT c FROM CmGestionUsuarios c WHERE c.usuarioGestiona = :usuarioGestiona"),
    @NamedQuery(name = "CmGestionUsuarios.findByUsuarioCrea", query = "SELECT c FROM CmGestionUsuarios c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmGestionUsuarios.findByTerminalCrea", query = "SELECT c FROM CmGestionUsuarios c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmGestionUsuarios.findByFechaHoraCrea", query = "SELECT c FROM CmGestionUsuarios c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmGestionUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_pagado_eps")
    private BigDecimal valorPagadoEps;
    @Column(name = "porcentaje_pagado_eps")
    private BigDecimal porcentajePagadoEps;
    @Column(name = "valor_aceptado_ips")
    private BigDecimal valorAceptadoIps;
    @Column(name = "porcentaje_aceptado_ips")
    private BigDecimal porcentajeAceptadoIps;
    @Size(max = 128)
    @Column(name = "usuario_gestiona")
    private String usuarioGestiona;
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
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;

    public CmGestionUsuarios() {
    }

    public CmGestionUsuarios(Integer id) {
        this.id = id;
    }

    public CmGestionUsuarios(Integer id, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getUsuarioGestiona() {
        return usuarioGestiona;
    }

    public void setUsuarioGestiona(String usuarioGestiona) {
        this.usuarioGestiona = usuarioGestiona;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmGestionUsuarios)) {
            return false;
        }
        CmGestionUsuarios other = (CmGestionUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmGestionUsuarios[ id=" + id + " ]";
    }
    
}
