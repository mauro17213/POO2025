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
@Table(name = "cm_sincronizacion_paquetes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmSincronizacionPaquetes.findAll", query = "SELECT c FROM CmSincronizacionPaquetes c"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findById", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.id = :id"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findByEstadoTransacion", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.estadoTransacion = :estadoTransacion"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findByFechaHoraEnvio", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.fechaHoraEnvio = :fechaHoraEnvio"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findByFechaHoraRespuesta", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findByCodigoRetorno", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.codigoRetorno = :codigoRetorno"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findByCodigoRespuesta", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.codigoRespuesta = :codigoRespuesta"),
    @NamedQuery(name = "CmSincronizacionPaquetes.findByMensajeRespuesta", query = "SELECT c FROM CmSincronizacionPaquetes c WHERE c.mensajeRespuesta = :mensajeRespuesta")})
public class CmSincronizacionPaquetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_transacion")
    private int estadoTransacion;
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
    private Integer codigoRetorno;
    @Column(name = "codigo_respuesta")
    private Integer codigoRespuesta;
    @Size(max = 512)
    @Column(name = "mensaje_respuesta")
    private String mensajeRespuesta;
    @JoinColumn(name = "cm_sincronizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmSincronizaciones cmSincronizacionesId;

    public CmSincronizacionPaquetes() {
    }

    public CmSincronizacionPaquetes(Integer id) {
        this.id = id;
    }

    public CmSincronizacionPaquetes(Integer id, int estadoTransacion, byte[] jsonEnvio, Date fechaHoraEnvio) {
        this.id = id;
        this.estadoTransacion = estadoTransacion;
        this.jsonEnvio = jsonEnvio;
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstadoTransacion() {
        return estadoTransacion;
    }

    public void setEstadoTransacion(int estadoTransacion) {
        this.estadoTransacion = estadoTransacion;
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

    public Integer getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(Integer codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public CmSincronizaciones getCmSincronizacionesId() {
        return cmSincronizacionesId;
    }

    public void setCmSincronizacionesId(CmSincronizaciones cmSincronizacionesId) {
        this.cmSincronizacionesId = cmSincronizacionesId;
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
        if (!(object instanceof CmSincronizacionPaquetes)) {
            return false;
        }
        CmSincronizacionPaquetes other = (CmSincronizacionPaquetes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmSincronizacionPaquetes[ id=" + id + " ]";
    }
    
}
