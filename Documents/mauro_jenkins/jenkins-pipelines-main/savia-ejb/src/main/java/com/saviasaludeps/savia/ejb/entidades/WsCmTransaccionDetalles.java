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
@Table(name = "ws_cm_transaccion_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsCmTransaccionDetalles.findAll", query = "SELECT w FROM WsCmTransaccionDetalles w"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findById", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.id = :id"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findByEstado", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.estado = :estado"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findByFechaHoraEnvio", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.fechaHoraEnvio = :fechaHoraEnvio"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findByFechaHoraRespuesta", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findByCodigoRetorno", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.codigoRetorno = :codigoRetorno"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findByCodigoRespuesta", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.codigoRespuesta = :codigoRespuesta"),
    @NamedQuery(name = "WsCmTransaccionDetalles.findByMensajeRespuesta", query = "SELECT w FROM WsCmTransaccionDetalles w WHERE w.mensajeRespuesta = :mensajeRespuesta")})
public class WsCmTransaccionDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "json_envio")
    private byte[] jsonEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;
    @Lob
    @Column(name = "json_respuesta")
    private byte[] jsonRespuesta;
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;
    @Column(name = "codigo_retorno")
    private Short codigoRetorno;
    @Column(name = "codigo_respuesta")
    private Short codigoRespuesta;
    @Size(max = 512)
    @Column(name = "mensaje_respuesta")
    private String mensajeRespuesta;
    @JoinColumn(name = "ws_cm_transacciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WsCmTransacciones wsCmTransaccionesId;

    public WsCmTransaccionDetalles() {
    }

    public WsCmTransaccionDetalles(Integer id) {
        this.id = id;
    }

    public WsCmTransaccionDetalles(Integer id, short estado, byte[] jsonEnvio, Date fechaHoraEnvio) {
        this.id = id;
        this.estado = estado;
        this.jsonEnvio = jsonEnvio;
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public byte[] getJsonEnvio() {
        return jsonEnvio;
    }

    public void setJsonEnvio(byte[] jsonEnvio) {
        this.jsonEnvio = jsonEnvio;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public byte[] getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(byte[] jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public Short getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(Short codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public Short getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Short codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public WsCmTransacciones getWsCmTransaccionesId() {
        return wsCmTransaccionesId;
    }

    public void setWsCmTransaccionesId(WsCmTransacciones wsCmTransaccionesId) {
        this.wsCmTransaccionesId = wsCmTransaccionesId;
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
        if (!(object instanceof WsCmTransaccionDetalles)) {
            return false;
        }
        WsCmTransaccionDetalles other = (WsCmTransaccionDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsCmTransaccionDetalles[ id=" + id + " ]";
    }
    
}
