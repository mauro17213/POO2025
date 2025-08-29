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
@Table(name = "gn_recurrencia_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnRecurrenciaHistoricos.findAll", query = "SELECT g FROM GnRecurrenciaHistoricos g"),
    @NamedQuery(name = "GnRecurrenciaHistoricos.findById", query = "SELECT g FROM GnRecurrenciaHistoricos g WHERE g.id = :id"),
    @NamedQuery(name = "GnRecurrenciaHistoricos.findByExitoso", query = "SELECT g FROM GnRecurrenciaHistoricos g WHERE g.exitoso = :exitoso"),
    @NamedQuery(name = "GnRecurrenciaHistoricos.findByRespuesta", query = "SELECT g FROM GnRecurrenciaHistoricos g WHERE g.respuesta = :respuesta"),
    @NamedQuery(name = "GnRecurrenciaHistoricos.findByFechaHoraInicio", query = "SELECT g FROM GnRecurrenciaHistoricos g WHERE g.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "GnRecurrenciaHistoricos.findByFechaHoraFin", query = "SELECT g FROM GnRecurrenciaHistoricos g WHERE g.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "GnRecurrenciaHistoricos.findByTiempo", query = "SELECT g FROM GnRecurrenciaHistoricos g WHERE g.tiempo = :tiempo")})
public class GnRecurrenciaHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exitoso")
    private boolean exitoso;
    @Size(max = 256)
    @Column(name = "respuesta")
    private String respuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tiempo")
    private Integer tiempo;
    @JoinColumn(name = "gn_recurrencias_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnRecurrencias gnRecurrenciasId;

    public GnRecurrenciaHistoricos() {
    }

    public GnRecurrenciaHistoricos(Integer id) {
        this.id = id;
    }

    public GnRecurrenciaHistoricos(Integer id, boolean exitoso, Date fechaHoraInicio) {
        this.id = id;
        this.exitoso = exitoso;
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public GnRecurrencias getGnRecurrenciasId() {
        return gnRecurrenciasId;
    }

    public void setGnRecurrenciasId(GnRecurrencias gnRecurrenciasId) {
        this.gnRecurrenciasId = gnRecurrenciasId;
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
        if (!(object instanceof GnRecurrenciaHistoricos)) {
            return false;
        }
        GnRecurrenciaHistoricos other = (GnRecurrenciaHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnRecurrenciaHistoricos[ id=" + id + " ]";
    }
    
}
