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
import javax.persistence.Lob;
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
@Table(name = "cm_sincronizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmSincronizaciones.findAll", query = "SELECT c FROM CmSincronizaciones c"),
    @NamedQuery(name = "CmSincronizaciones.findById", query = "SELECT c FROM CmSincronizaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmSincronizaciones.findByCmFacturasId", query = "SELECT c FROM CmSincronizaciones c WHERE c.cmFacturasId = :cmFacturasId"),
    @NamedQuery(name = "CmSincronizaciones.findByMomento", query = "SELECT c FROM CmSincronizaciones c WHERE c.momento = :momento"),
    @NamedQuery(name = "CmSincronizaciones.findByFechaHoraEnvio", query = "SELECT c FROM CmSincronizaciones c WHERE c.fechaHoraEnvio = :fechaHoraEnvio"),
    @NamedQuery(name = "CmSincronizaciones.findByCodigoRetorno", query = "SELECT c FROM CmSincronizaciones c WHERE c.codigoRetorno = :codigoRetorno"),
    @NamedQuery(name = "CmSincronizaciones.findByCodigoRespuesta", query = "SELECT c FROM CmSincronizaciones c WHERE c.codigoRespuesta = :codigoRespuesta"),
    @NamedQuery(name = "CmSincronizaciones.findByMensajeRespuesta", query = "SELECT c FROM CmSincronizaciones c WHERE c.mensajeRespuesta = :mensajeRespuesta"),
    @NamedQuery(name = "CmSincronizaciones.findByFechaHoraRespuesta", query = "SELECT c FROM CmSincronizaciones c WHERE c.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "CmSincronizaciones.findByEstadoTransacion", query = "SELECT c FROM CmSincronizaciones c WHERE c.estadoTransacion = :estadoTransacion"),
    @NamedQuery(name = "CmSincronizaciones.findByPaquetes", query = "SELECT c FROM CmSincronizaciones c WHERE c.paquetes = :paquetes"),
    @NamedQuery(name = "CmSincronizaciones.findByPaquetesExitosos", query = "SELECT c FROM CmSincronizaciones c WHERE c.paquetesExitosos = :paquetesExitosos")})
public class CmSincronizaciones implements Serializable {

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
    @Column(name = "momento")
    private int momento;
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
    @Column(name = "codigo_retorno")
    private Integer codigoRetorno;
    @Column(name = "codigo_respuesta")
    private Integer codigoRespuesta;
    @Size(max = 512)
    @Column(name = "mensaje_respuesta")
    private String mensajeRespuesta;
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_transacion")
    private int estadoTransacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paquetes")
    private int paquetes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paquetes_exitosos")
    private int paquetesExitosos;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmSincronizacionesId", fetch = FetchType.LAZY)
    private List<CmSincronizacionPaquetes> cmSincronizacionPaquetesList;

    public CmSincronizaciones() {
    }

    public CmSincronizaciones(Integer id) {
        this.id = id;
    }

    public CmSincronizaciones(Integer id, int momento, byte[] jsonEnvio, Date fechaHoraEnvio, int estadoTransacion, int paquetes, int paquetesExitosos) {
        this.id = id;
        this.momento = momento;
        this.jsonEnvio = jsonEnvio;
        this.fechaHoraEnvio = fechaHoraEnvio;
        this.estadoTransacion = estadoTransacion;
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

    public int getMomento() {
        return momento;
    }

    public void setMomento(int momento) {
        this.momento = momento;
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

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public int getEstadoTransacion() {
        return estadoTransacion;
    }

    public void setEstadoTransacion(int estadoTransacion) {
        this.estadoTransacion = estadoTransacion;
    }

    public int getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(int paquetes) {
        this.paquetes = paquetes;
    }

    public int getPaquetesExitosos() {
        return paquetesExitosos;
    }

    public void setPaquetesExitosos(int paquetesExitosos) {
        this.paquetesExitosos = paquetesExitosos;
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

    @XmlTransient
    public List<CmSincronizacionPaquetes> getCmSincronizacionPaquetesList() {
        return cmSincronizacionPaquetesList;
    }

    public void setCmSincronizacionPaquetesList(List<CmSincronizacionPaquetes> cmSincronizacionPaquetesList) {
        this.cmSincronizacionPaquetesList = cmSincronizacionPaquetesList;
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
        if (!(object instanceof CmSincronizaciones)) {
            return false;
        }
        CmSincronizaciones other = (CmSincronizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmSincronizaciones[ id=" + id + " ]";
    }
    
}
