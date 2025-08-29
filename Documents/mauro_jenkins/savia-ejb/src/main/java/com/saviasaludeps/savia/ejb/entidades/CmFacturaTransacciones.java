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
@Table(name = "cm_factura_transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFacturaTransacciones.findAll", query = "SELECT c FROM CmFacturaTransacciones c"),
    @NamedQuery(name = "CmFacturaTransacciones.findById", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmFacturaTransacciones.findByTipo", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmFacturaTransacciones.findByEstado", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmFacturaTransacciones.findByFechaHoraInicio", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CmFacturaTransacciones.findByFechaHoraFin", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CmFacturaTransacciones.findByRespuestaCodigo", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.respuestaCodigo = :respuestaCodigo"),
    @NamedQuery(name = "CmFacturaTransacciones.findByRespuestaDescripcion", query = "SELECT c FROM CmFacturaTransacciones c WHERE c.respuestaDescripcion = :respuestaDescripcion")})
public class CmFacturaTransacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "respuesta_codigo")
    private Integer respuestaCodigo;
    @Size(max = 512)
    @Column(name = "respuesta_descripcion")
    private String respuestaDescripcion;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;

    public CmFacturaTransacciones() {
    }

    public CmFacturaTransacciones(Integer id) {
        this.id = id;
    }

    public CmFacturaTransacciones(Integer id, int tipo, int estado, Date fechaHoraInicio) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getRespuestaCodigo() {
        return respuestaCodigo;
    }

    public void setRespuestaCodigo(Integer respuestaCodigo) {
        this.respuestaCodigo = respuestaCodigo;
    }

    public String getRespuestaDescripcion() {
        return respuestaDescripcion;
    }

    public void setRespuestaDescripcion(String respuestaDescripcion) {
        this.respuestaDescripcion = respuestaDescripcion;
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
        if (!(object instanceof CmFacturaTransacciones)) {
            return false;
        }
        CmFacturaTransacciones other = (CmFacturaTransacciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFacturaTransacciones[ id=" + id + " ]";
    }
    
}
