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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cm_auditoria_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaAdjuntos.findAll", query = "SELECT c FROM CmAuditoriaAdjuntos c"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findById", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findByArchivoTipo", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.archivoTipo = :archivoTipo"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findByArchivoNombre", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findByArchivoRuta", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaAdjuntos.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaAdjuntos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmAuditoriaAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "archivo_tipo")
    private int archivoTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;

    public CmAuditoriaAdjuntos() {
    }

    public CmAuditoriaAdjuntos(Integer id) {
        this.id = id;
    }

    public CmAuditoriaAdjuntos(Integer id, int archivoTipo, String archivoNombre, String archivoRuta, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.archivoTipo = archivoTipo;
        this.archivoNombre = archivoNombre;
        this.archivoRuta = archivoRuta;
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

    public int getArchivoTipo() {
        return archivoTipo;
    }

    public void setArchivoTipo(int archivoTipo) {
        this.archivoTipo = archivoTipo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
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
        if (!(object instanceof CmAuditoriaAdjuntos)) {
            return false;
        }
        CmAuditoriaAdjuntos other = (CmAuditoriaAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaAdjuntos[ id=" + id + " ]";
    }
    
}
