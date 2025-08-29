/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "ws_cm_transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsCmTransacciones.findAll", query = "SELECT w FROM WsCmTransacciones w"),
    @NamedQuery(name = "WsCmTransacciones.findById", query = "SELECT w FROM WsCmTransacciones w WHERE w.id = :id"),
    @NamedQuery(name = "WsCmTransacciones.findByCmFacturasId", query = "SELECT w FROM WsCmTransacciones w WHERE w.cmFacturasId = :cmFacturasId"),
    @NamedQuery(name = "WsCmTransacciones.findByEstado", query = "SELECT w FROM WsCmTransacciones w WHERE w.estado = :estado"),
    @NamedQuery(name = "WsCmTransacciones.findByMomento", query = "SELECT w FROM WsCmTransacciones w WHERE w.momento = :momento"),
    @NamedQuery(name = "WsCmTransacciones.findByFechaHoraEnvio", query = "SELECT w FROM WsCmTransacciones w WHERE w.fechaHoraEnvio = :fechaHoraEnvio"),
    @NamedQuery(name = "WsCmTransacciones.findByCodigoRetorno", query = "SELECT w FROM WsCmTransacciones w WHERE w.codigoRetorno = :codigoRetorno"),
    @NamedQuery(name = "WsCmTransacciones.findByCodigoRespuesta", query = "SELECT w FROM WsCmTransacciones w WHERE w.codigoRespuesta = :codigoRespuesta"),
    @NamedQuery(name = "WsCmTransacciones.findByMensajeRespuesta", query = "SELECT w FROM WsCmTransacciones w WHERE w.mensajeRespuesta = :mensajeRespuesta"),
    @NamedQuery(name = "WsCmTransacciones.findByFechaHoraRespuesta", query = "SELECT w FROM WsCmTransacciones w WHERE w.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "WsCmTransacciones.findByPaquetes", query = "SELECT w FROM WsCmTransacciones w WHERE w.paquetes = :paquetes"),
    @NamedQuery(name = "WsCmTransacciones.findByPaquetesExitosos", query = "SELECT w FROM WsCmTransacciones w WHERE w.paquetesExitosos = :paquetesExitosos")})
public class WsCmTransacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cm_facturas_id")
    private Integer cmFacturasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "momento")
    private short momento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;
    @Column(name = "codigo_retorno")
    private Short codigoRetorno;
    @Column(name = "codigo_respuesta")
    private Short codigoRespuesta;
    @Size(max = 512)
    @Column(name = "mensaje_respuesta")
    private String mensajeRespuesta;
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paquetes")
    private short paquetes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paquetes_exitosos")
    private short paquetesExitosos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsCmTransaccionesId", fetch = FetchType.LAZY)
    private List<WsCmTransaccionDetalles> wsCmTransaccionDetallesList;
    @JoinColumn(name = "cm_glosa_respuestas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmGlosaRespuestas cmGlosaRespuestasId;
    @JoinColumn(name = "cm_auditoria_cierres_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmAuditoriaCierres cmAuditoriaCierresId;
    @JoinColumn(name = "cm_conciliaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmConciliaciones cmConciliacionesId;
    @JoinColumn(name = "cm_radicados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRadicados cmRadicadosId;

    public WsCmTransacciones() {
    }

    public WsCmTransacciones(Integer id) {
        this.id = id;
    }

    public WsCmTransacciones(Integer id, short estado, short momento, Date fechaHoraEnvio, short paquetes, short paquetesExitosos) {
        this.id = id;
        this.estado = estado;
        this.momento = momento;
        this.fechaHoraEnvio = fechaHoraEnvio;
        this.paquetes = paquetes;
        this.paquetesExitosos = paquetesExitosos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(Integer cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getMomento() {
        return momento;
    }

    public void setMomento(short momento) {
        this.momento = momento;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
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

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public short getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(short paquetes) {
        this.paquetes = paquetes;
    }

    public short getPaquetesExitosos() {
        return paquetesExitosos;
    }

    public void setPaquetesExitosos(short paquetesExitosos) {
        this.paquetesExitosos = paquetesExitosos;
    }

    @XmlTransient
    public List<WsCmTransaccionDetalles> getWsCmTransaccionDetallesList() {
        return wsCmTransaccionDetallesList;
    }

    public void setWsCmTransaccionDetallesList(List<WsCmTransaccionDetalles> wsCmTransaccionDetallesList) {
        this.wsCmTransaccionDetallesList = wsCmTransaccionDetallesList;
    }

    public CmGlosaRespuestas getCmGlosaRespuestasId() {
        return cmGlosaRespuestasId;
    }

    public void setCmGlosaRespuestasId(CmGlosaRespuestas cmGlosaRespuestasId) {
        this.cmGlosaRespuestasId = cmGlosaRespuestasId;
    }

    public CmAuditoriaCierres getCmAuditoriaCierresId() {
        return cmAuditoriaCierresId;
    }

    public void setCmAuditoriaCierresId(CmAuditoriaCierres cmAuditoriaCierresId) {
        this.cmAuditoriaCierresId = cmAuditoriaCierresId;
    }

    public CmConciliaciones getCmConciliacionesId() {
        return cmConciliacionesId;
    }

    public void setCmConciliacionesId(CmConciliaciones cmConciliacionesId) {
        this.cmConciliacionesId = cmConciliacionesId;
    }

    public CmRadicados getCmRadicadosId() {
        return cmRadicadosId;
    }

    public void setCmRadicadosId(CmRadicados cmRadicadosId) {
        this.cmRadicadosId = cmRadicadosId;
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
        if (!(object instanceof WsCmTransacciones)) {
            return false;
        }
        WsCmTransacciones other = (WsCmTransacciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsCmTransacciones[ id=" + id + " ]";
    }
    
}
