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
@Table(name = "cm_pago_factura_retenciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmPagoFacturaRetenciones.findAll", query = "SELECT c FROM CmPagoFacturaRetenciones c"),
    @NamedQuery(name = "CmPagoFacturaRetenciones.findById", query = "SELECT c FROM CmPagoFacturaRetenciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmPagoFacturaRetenciones.findByCodigo", query = "SELECT c FROM CmPagoFacturaRetenciones c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CmPagoFacturaRetenciones.findByDescripcion", query = "SELECT c FROM CmPagoFacturaRetenciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CmPagoFacturaRetenciones.findByValor", query = "SELECT c FROM CmPagoFacturaRetenciones c WHERE c.valor = :valor"),
    @NamedQuery(name = "CmPagoFacturaRetenciones.findByFechaHoraCrea", query = "SELECT c FROM CmPagoFacturaRetenciones c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmPagoFacturaRetenciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_pago_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmPagoFacturas cmPagoFacturasId;

    public CmPagoFacturaRetenciones() {
    }

    public CmPagoFacturaRetenciones(Integer id) {
        this.id = id;
    }

    public CmPagoFacturaRetenciones(Integer id, String codigo, String descripcion, BigDecimal valor, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public CmPagoFacturas getCmPagoFacturasId() {
        return cmPagoFacturasId;
    }

    public void setCmPagoFacturasId(CmPagoFacturas cmPagoFacturasId) {
        this.cmPagoFacturasId = cmPagoFacturasId;
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
        if (!(object instanceof CmPagoFacturaRetenciones)) {
            return false;
        }
        CmPagoFacturaRetenciones other = (CmPagoFacturaRetenciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmPagoFacturaRetenciones[ id=" + id + " ]";
    }
    
}
