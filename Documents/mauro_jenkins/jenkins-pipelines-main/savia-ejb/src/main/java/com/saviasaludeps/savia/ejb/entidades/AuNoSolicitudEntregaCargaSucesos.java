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
@Table(name = "au_no_solicitud_entrega_carga_sucesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudEntregaCargaSucesos.findAll", query = "SELECT a FROM AuNoSolicitudEntregaCargaSucesos a"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargaSucesos.findById", query = "SELECT a FROM AuNoSolicitudEntregaCargaSucesos a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargaSucesos.findByFila", query = "SELECT a FROM AuNoSolicitudEntregaCargaSucesos a WHERE a.fila = :fila"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargaSucesos.findByEstado", query = "SELECT a FROM AuNoSolicitudEntregaCargaSucesos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuNoSolicitudEntregaCargaSucesos.findByFechaHoraProceso", query = "SELECT a FROM AuNoSolicitudEntregaCargaSucesos a WHERE a.fechaHoraProceso = :fechaHoraProceso")})
public class AuNoSolicitudEntregaCargaSucesos implements Serializable {

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
    @Column(name = "fila")
    private int fila;
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
    @JoinColumn(name = "au_no_solicitud_entrega_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuNoSolicitudEntregaCargas auNoSolicitudEntregaCargasId;
    @JoinColumn(name = "au_no_solicitud_entregas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudEntregas auNoSolicitudEntregasId;

    public AuNoSolicitudEntregaCargaSucesos() {
    }

    public AuNoSolicitudEntregaCargaSucesos(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudEntregaCargaSucesos(Integer id, byte[] data, int fila, int estado, Date fechaHoraProceso) {
        this.id = id;
        this.data = data;
        this.fila = fila;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
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

    public AuNoSolicitudEntregaCargas getAuNoSolicitudEntregaCargasId() {
        return auNoSolicitudEntregaCargasId;
    }

    public void setAuNoSolicitudEntregaCargasId(AuNoSolicitudEntregaCargas auNoSolicitudEntregaCargasId) {
        this.auNoSolicitudEntregaCargasId = auNoSolicitudEntregaCargasId;
    }

    public AuNoSolicitudEntregas getAuNoSolicitudEntregasId() {
        return auNoSolicitudEntregasId;
    }

    public void setAuNoSolicitudEntregasId(AuNoSolicitudEntregas auNoSolicitudEntregasId) {
        this.auNoSolicitudEntregasId = auNoSolicitudEntregasId;
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
        if (!(object instanceof AuNoSolicitudEntregaCargaSucesos)) {
            return false;
        }
        AuNoSolicitudEntregaCargaSucesos other = (AuNoSolicitudEntregaCargaSucesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregaCargaSucesos[ id=" + id + " ]";
    }
    
}
