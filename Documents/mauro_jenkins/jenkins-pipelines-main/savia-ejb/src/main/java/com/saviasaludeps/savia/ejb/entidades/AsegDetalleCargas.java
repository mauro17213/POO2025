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
@Table(name = "aseg_detalle_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegDetalleCargas.findAll", query = "SELECT a FROM AsegDetalleCargas a"),
    @NamedQuery(name = "AsegDetalleCargas.findById", query = "SELECT a FROM AsegDetalleCargas a WHERE a.id = :id"),
    @NamedQuery(name = "AsegDetalleCargas.findByEstado", query = "SELECT a FROM AsegDetalleCargas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsegDetalleCargas.findByFechaHoraProceso", query = "SELECT a FROM AsegDetalleCargas a WHERE a.fechaHoraProceso = :fechaHoraProceso")})
public class AsegDetalleCargas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "data")
    private byte[] data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "detalle_fallo")
    private String detalleFallo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_proceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraProceso;
    @JoinColumn(name = "aseg_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegCargas asegCargasId;

    public AsegDetalleCargas() {
    }

    public AsegDetalleCargas(Integer id) {
        this.id = id;
    }

    public AsegDetalleCargas(Integer id, byte[] data, int estado, Date fechaHoraProceso) {
        this.id = id;
        this.data = data;
        this.estado = estado;
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDetalleFallo() {
        return detalleFallo;
    }

    public void setDetalleFallo(String detalleFallo) {
        this.detalleFallo = detalleFallo;
    }

    public Date getFechaHoraProceso() {
        return fechaHoraProceso;
    }

    public void setFechaHoraProceso(Date fechaHoraProceso) {
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public AsegCargas getAsegCargasId() {
        return asegCargasId;
    }

    public void setAsegCargasId(AsegCargas asegCargasId) {
        this.asegCargasId = asegCargasId;
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
        if (!(object instanceof AsegDetalleCargas)) {
            return false;
        }
        AsegDetalleCargas other = (AsegDetalleCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegDetalleCargas[ id=" + id + " ]";
    }
    
}
