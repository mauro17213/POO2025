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
@Table(name = "cm_pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmPagos.findAll", query = "SELECT c FROM CmPagos c"),
    @NamedQuery(name = "CmPagos.findById", query = "SELECT c FROM CmPagos c WHERE c.id = :id"),
    @NamedQuery(name = "CmPagos.findByIdetificador", query = "SELECT c FROM CmPagos c WHERE c.idetificador = :idetificador"),
    @NamedQuery(name = "CmPagos.findByTipo", query = "SELECT c FROM CmPagos c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmPagos.findByForma", query = "SELECT c FROM CmPagos c WHERE c.forma = :forma"),
    @NamedQuery(name = "CmPagos.findByMaeTipoDocumentoId", query = "SELECT c FROM CmPagos c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmPagos.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmPagos c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmPagos.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmPagos c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmPagos.findByDocumento", query = "SELECT c FROM CmPagos c WHERE c.documento = :documento"),
    @NamedQuery(name = "CmPagos.findByFechaHora", query = "SELECT c FROM CmPagos c WHERE c.fechaHora = :fechaHora"),
    @NamedQuery(name = "CmPagos.findByFacturas", query = "SELECT c FROM CmPagos c WHERE c.facturas = :facturas"),
    @NamedQuery(name = "CmPagos.findByValorBruto", query = "SELECT c FROM CmPagos c WHERE c.valorBruto = :valorBruto"),
    @NamedQuery(name = "CmPagos.findByValorNeto", query = "SELECT c FROM CmPagos c WHERE c.valorNeto = :valorNeto"),
    @NamedQuery(name = "CmPagos.findByValorDeducciones", query = "SELECT c FROM CmPagos c WHERE c.valorDeducciones = :valorDeducciones"),
    @NamedQuery(name = "CmPagos.findByValorCompensacionAnticipos", query = "SELECT c FROM CmPagos c WHERE c.valorCompensacionAnticipos = :valorCompensacionAnticipos"),
    @NamedQuery(name = "CmPagos.findByUsuarioCrea", query = "SELECT c FROM CmPagos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmPagos.findByTerminalCrea", query = "SELECT c FROM CmPagos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmPagos.findByFechaHoraCrea", query = "SELECT c FROM CmPagos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmPagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "idetificador")
    private String idetificador;
    @Column(name = "tipo")
    private Short tipo;
    @Column(name = "forma")
    private Short forma;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Column(name = "facturas")
    private Integer facturas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_bruto")
    private BigDecimal valorBruto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_neto")
    private BigDecimal valorNeto;
    @Column(name = "valor_deducciones")
    private BigDecimal valorDeducciones;
    @Column(name = "valor_compensacion_anticipos")
    private BigDecimal valorCompensacionAnticipos;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmPagosId", fetch = FetchType.LAZY)
    private List<CmPagoTransacciones> cmPagoTransaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmPagosId", fetch = FetchType.LAZY)
    private List<CmPagoFacturas> cmPagoFacturasList;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public CmPagos() {
    }

    public CmPagos(Integer id) {
        this.id = id;
    }

    public CmPagos(Integer id, String idetificador, String maeTipoDocumentoCodigo, String documento, Date fechaHora, BigDecimal valorNeto, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idetificador = idetificador;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documento = documento;
        this.fechaHora = fechaHora;
        this.valorNeto = valorNeto;
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

    public String getIdetificador() {
        return idetificador;
    }

    public void setIdetificador(String idetificador) {
        this.idetificador = idetificador;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public Short getForma() {
        return forma;
    }

    public void setForma(Short forma) {
        this.forma = forma;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getFacturas() {
        return facturas;
    }

    public void setFacturas(Integer facturas) {
        this.facturas = facturas;
    }

    public BigDecimal getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(BigDecimal valorBruto) {
        this.valorBruto = valorBruto;
    }

    public BigDecimal getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(BigDecimal valorNeto) {
        this.valorNeto = valorNeto;
    }

    public BigDecimal getValorDeducciones() {
        return valorDeducciones;
    }

    public void setValorDeducciones(BigDecimal valorDeducciones) {
        this.valorDeducciones = valorDeducciones;
    }

    public BigDecimal getValorCompensacionAnticipos() {
        return valorCompensacionAnticipos;
    }

    public void setValorCompensacionAnticipos(BigDecimal valorCompensacionAnticipos) {
        this.valorCompensacionAnticipos = valorCompensacionAnticipos;
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
    public List<CmPagoTransacciones> getCmPagoTransaccionesList() {
        return cmPagoTransaccionesList;
    }

    public void setCmPagoTransaccionesList(List<CmPagoTransacciones> cmPagoTransaccionesList) {
        this.cmPagoTransaccionesList = cmPagoTransaccionesList;
    }

    @XmlTransient
    public List<CmPagoFacturas> getCmPagoFacturasList() {
        return cmPagoFacturasList;
    }

    public void setCmPagoFacturasList(List<CmPagoFacturas> cmPagoFacturasList) {
        this.cmPagoFacturasList = cmPagoFacturasList;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof CmPagos)) {
            return false;
        }
        CmPagos other = (CmPagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmPagos[ id=" + id + " ]";
    }
    
}
