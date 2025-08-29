/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cm_pago_transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmPagoTransacciones.findAll", query = "SELECT c FROM CmPagoTransacciones c"),
    @NamedQuery(name = "CmPagoTransacciones.findById", query = "SELECT c FROM CmPagoTransacciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmPagoTransacciones.findByNut", query = "SELECT c FROM CmPagoTransacciones c WHERE c.nut = :nut"),
    @NamedQuery(name = "CmPagoTransacciones.findByFacturas", query = "SELECT c FROM CmPagoTransacciones c WHERE c.facturas = :facturas"),
    @NamedQuery(name = "CmPagoTransacciones.findByPaquete", query = "SELECT c FROM CmPagoTransacciones c WHERE c.paquete = :paquete"),
    @NamedQuery(name = "CmPagoTransacciones.findByFinalizado", query = "SELECT c FROM CmPagoTransacciones c WHERE c.finalizado = :finalizado"),
    @NamedQuery(name = "CmPagoTransacciones.findByFechaHoraCrea", query = "SELECT c FROM CmPagoTransacciones c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmPagoTransacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nut")
    private int nut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "facturas")
    private int facturas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paquete")
    private int paquete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finalizado")
    private boolean finalizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_pagos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmPagos cmPagosId;
    @OneToMany(mappedBy = "cmPagoTransaccionesId", fetch = FetchType.LAZY)
    private List<CmPagoFacturas> cmPagoFacturasList;

    public CmPagoTransacciones() {
    }

    public CmPagoTransacciones(Integer id) {
        this.id = id;
    }

    public CmPagoTransacciones(Integer id, int nut, int facturas, int paquete, boolean finalizado, Date fechaHoraCrea) {
        this.id = id;
        this.nut = nut;
        this.facturas = facturas;
        this.paquete = paquete;
        this.finalizado = finalizado;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNut() {
        return nut;
    }

    public void setNut(int nut) {
        this.nut = nut;
    }

    public int getFacturas() {
        return facturas;
    }

    public void setFacturas(int facturas) {
        this.facturas = facturas;
    }

    public int getPaquete() {
        return paquete;
    }

    public void setPaquete(int paquete) {
        this.paquete = paquete;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public CmPagos getCmPagosId() {
        return cmPagosId;
    }

    public void setCmPagosId(CmPagos cmPagosId) {
        this.cmPagosId = cmPagosId;
    }

    @XmlTransient
    public List<CmPagoFacturas> getCmPagoFacturasList() {
        return cmPagoFacturasList;
    }

    public void setCmPagoFacturasList(List<CmPagoFacturas> cmPagoFacturasList) {
        this.cmPagoFacturasList = cmPagoFacturasList;
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
        if (!(object instanceof CmPagoTransacciones)) {
            return false;
        }
        CmPagoTransacciones other = (CmPagoTransacciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmPagoTransacciones[ id=" + id + " ]";
    }
    
}
