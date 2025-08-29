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
@Table(name = "cm_historico_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmHistoricoFacturas.findAll", query = "SELECT c FROM CmHistoricoFacturas c"),
    @NamedQuery(name = "CmHistoricoFacturas.findById", query = "SELECT c FROM CmHistoricoFacturas c WHERE c.id = :id"),
    @NamedQuery(name = "CmHistoricoFacturas.findByTipos", query = "SELECT c FROM CmHistoricoFacturas c WHERE c.tipos = :tipos"),
    @NamedQuery(name = "CmHistoricoFacturas.findByUsuarioCrea", query = "SELECT c FROM CmHistoricoFacturas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmHistoricoFacturas.findByTerminalCrea", query = "SELECT c FROM CmHistoricoFacturas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmHistoricoFacturas.findByFechaHoraCrea", query = "SELECT c FROM CmHistoricoFacturas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmHistoricoFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "cm_historico_facturas")
    private String cmHistoricoFacturas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipos")
    private int tipos;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
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
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;

    public CmHistoricoFacturas() {
    }

    public CmHistoricoFacturas(Integer id) {
        this.id = id;
    }

    public CmHistoricoFacturas(Integer id, int tipos, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipos = tipos;
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

    public String getCmHistoricoFacturas() {
        return cmHistoricoFacturas;
    }

    public void setCmHistoricoFacturas(String cmHistoricoFacturas) {
        this.cmHistoricoFacturas = cmHistoricoFacturas;
    }

    public int getTipos() {
        return tipos;
    }

    public void setTipos(int tipos) {
        this.tipos = tipos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
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
        if (!(object instanceof CmHistoricoFacturas)) {
            return false;
        }
        CmHistoricoFacturas other = (CmHistoricoFacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmHistoricoFacturas[ id=" + id + " ]";
    }
    
}
