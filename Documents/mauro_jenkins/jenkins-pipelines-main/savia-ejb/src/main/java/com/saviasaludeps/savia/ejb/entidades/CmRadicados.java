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
@Table(name = "cm_radicados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRadicados.findAll", query = "SELECT c FROM CmRadicados c"),
    @NamedQuery(name = "CmRadicados.findById", query = "SELECT c FROM CmRadicados c WHERE c.id = :id"),
    @NamedQuery(name = "CmRadicados.findByTipoTransaccion", query = "SELECT c FROM CmRadicados c WHERE c.tipoTransaccion = :tipoTransaccion"),
    @NamedQuery(name = "CmRadicados.findByTipoRelacion", query = "SELECT c FROM CmRadicados c WHERE c.tipoRelacion = :tipoRelacion"),
    @NamedQuery(name = "CmRadicados.findByEstado", query = "SELECT c FROM CmRadicados c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmRadicados.findByIntentosPermitidos", query = "SELECT c FROM CmRadicados c WHERE c.intentosPermitidos = :intentosPermitidos"),
    @NamedQuery(name = "CmRadicados.findByIntentosEjecutados", query = "SELECT c FROM CmRadicados c WHERE c.intentosEjecutados = :intentosEjecutados"),
    @NamedQuery(name = "CmRadicados.findByCmFacturaId", query = "SELECT c FROM CmRadicados c WHERE c.cmFacturaId = :cmFacturaId"),
    @NamedQuery(name = "CmRadicados.findByRadicado", query = "SELECT c FROM CmRadicados c WHERE c.radicado = :radicado"),
    @NamedQuery(name = "CmRadicados.findByEstadoRadicado", query = "SELECT c FROM CmRadicados c WHERE c.estadoRadicado = :estadoRadicado"),
    @NamedQuery(name = "CmRadicados.findByCmGlosaMasivaId", query = "SELECT c FROM CmRadicados c WHERE c.cmGlosaMasivaId = :cmGlosaMasivaId"),
    @NamedQuery(name = "CmRadicados.findByUsuarioCrea", query = "SELECT c FROM CmRadicados c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRadicados.findByTerminalCrea", query = "SELECT c FROM CmRadicados c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRadicados.findByFechaHoraCrea", query = "SELECT c FROM CmRadicados c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmRadicados.findByFechaHoraFactura", query = "SELECT c FROM CmRadicados c WHERE c.fechaHoraFactura = :fechaHoraFactura"),
    @NamedQuery(name = "CmRadicados.findByFechaHoraTransaccion", query = "SELECT c FROM CmRadicados c WHERE c.fechaHoraTransaccion = :fechaHoraTransaccion"),
    @NamedQuery(name = "CmRadicados.findByFechaHoraEnvioSap", query = "SELECT c FROM CmRadicados c WHERE c.fechaHoraEnvioSap = :fechaHoraEnvioSap")})
public class CmRadicados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_transaccion")
    private short tipoTransaccion;
    @Column(name = "tipo_relacion")
    private Short tipoRelacion;
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intentos_permitidos")
    private short intentosPermitidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intentos_ejecutados")
    private short intentosEjecutados;
    @Column(name = "cm_factura_id")
    private Integer cmFacturaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "radicado")
    private int radicado;
    @Column(name = "estado_radicado")
    private Boolean estadoRadicado;
    @Column(name = "cm_glosa_masiva_id")
    private Integer cmGlosaMasivaId;
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
    @Column(name = "fecha_hora_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFactura;
    @Column(name = "fecha_hora_transaccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraTransaccion;
    @Column(name = "fecha_hora_envio_sap")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvioSap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRadicadosId", fetch = FetchType.LAZY)
    private List<WsCmFacturas> wsCmFacturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRadicadosId", fetch = FetchType.LAZY)
    private List<CmSincronizaciones> cmSincronizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRadicadosId", fetch = FetchType.LAZY)
    private List<WsCmTransacciones> wsCmTransaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRadicadosId", fetch = FetchType.LAZY)
    private List<CmSincronizacionEncabezados> cmSincronizacionEncabezadosList;
    @JoinColumn(name = "cm_auditoria_cierres_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmAuditoriaCierres cmAuditoriaCierresId;
    @JoinColumn(name = "cm_auditoria_devoluciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmAuditoriaDevoluciones cmAuditoriaDevolucionesId;
    @JoinColumn(name = "cm_auditoria_masiva_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmAuditoriaMasiva cmAuditoriaMasivaId;
    @JoinColumn(name = "cm_conciliaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmConciliaciones cmConciliacionesId;
    @JoinColumn(name = "cm_devolucion_masiva_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmDevolucionMasiva cmDevolucionMasivaId;
    @JoinColumn(name = "cm_fe_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFeRipsCargas cmFeRipsCargasId;
    @JoinColumn(name = "cm_glosa_respuestas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmGlosaRespuestas cmGlosaRespuestasId;
    @JoinColumn(name = "cm_glosa_respuestas_conciliacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmGlosaRespuestas cmGlosaRespuestasConciliacionId;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRadicados() {
    }

    public CmRadicados(Integer id) {
        this.id = id;
    }

    public CmRadicados(Integer id, short tipoTransaccion, short intentosPermitidos, short intentosEjecutados, int radicado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.intentosPermitidos = intentosPermitidos;
        this.intentosEjecutados = intentosEjecutados;
        this.radicado = radicado;
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

    public short getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(short tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Short getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(Short tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public short getIntentosPermitidos() {
        return intentosPermitidos;
    }

    public void setIntentosPermitidos(short intentosPermitidos) {
        this.intentosPermitidos = intentosPermitidos;
    }

    public short getIntentosEjecutados() {
        return intentosEjecutados;
    }

    public void setIntentosEjecutados(short intentosEjecutados) {
        this.intentosEjecutados = intentosEjecutados;
    }

    public Integer getCmFacturaId() {
        return cmFacturaId;
    }

    public void setCmFacturaId(Integer cmFacturaId) {
        this.cmFacturaId = cmFacturaId;
    }

    public int getRadicado() {
        return radicado;
    }

    public void setRadicado(int radicado) {
        this.radicado = radicado;
    }

    public Boolean getEstadoRadicado() {
        return estadoRadicado;
    }

    public void setEstadoRadicado(Boolean estadoRadicado) {
        this.estadoRadicado = estadoRadicado;
    }

    public Integer getCmGlosaMasivaId() {
        return cmGlosaMasivaId;
    }

    public void setCmGlosaMasivaId(Integer cmGlosaMasivaId) {
        this.cmGlosaMasivaId = cmGlosaMasivaId;
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

    public Date getFechaHoraFactura() {
        return fechaHoraFactura;
    }

    public void setFechaHoraFactura(Date fechaHoraFactura) {
        this.fechaHoraFactura = fechaHoraFactura;
    }

    public Date getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(Date fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public Date getFechaHoraEnvioSap() {
        return fechaHoraEnvioSap;
    }

    public void setFechaHoraEnvioSap(Date fechaHoraEnvioSap) {
        this.fechaHoraEnvioSap = fechaHoraEnvioSap;
    }

    @XmlTransient
    public List<WsCmFacturas> getWsCmFacturasList() {
        return wsCmFacturasList;
    }

    public void setWsCmFacturasList(List<WsCmFacturas> wsCmFacturasList) {
        this.wsCmFacturasList = wsCmFacturasList;
    }

    @XmlTransient
    public List<CmSincronizaciones> getCmSincronizacionesList() {
        return cmSincronizacionesList;
    }

    public void setCmSincronizacionesList(List<CmSincronizaciones> cmSincronizacionesList) {
        this.cmSincronizacionesList = cmSincronizacionesList;
    }

    @XmlTransient
    public List<WsCmTransacciones> getWsCmTransaccionesList() {
        return wsCmTransaccionesList;
    }

    public void setWsCmTransaccionesList(List<WsCmTransacciones> wsCmTransaccionesList) {
        this.wsCmTransaccionesList = wsCmTransaccionesList;
    }

    @XmlTransient
    public List<CmSincronizacionEncabezados> getCmSincronizacionEncabezadosList() {
        return cmSincronizacionEncabezadosList;
    }

    public void setCmSincronizacionEncabezadosList(List<CmSincronizacionEncabezados> cmSincronizacionEncabezadosList) {
        this.cmSincronizacionEncabezadosList = cmSincronizacionEncabezadosList;
    }

    public CmAuditoriaCierres getCmAuditoriaCierresId() {
        return cmAuditoriaCierresId;
    }

    public void setCmAuditoriaCierresId(CmAuditoriaCierres cmAuditoriaCierresId) {
        this.cmAuditoriaCierresId = cmAuditoriaCierresId;
    }

    public CmAuditoriaDevoluciones getCmAuditoriaDevolucionesId() {
        return cmAuditoriaDevolucionesId;
    }

    public void setCmAuditoriaDevolucionesId(CmAuditoriaDevoluciones cmAuditoriaDevolucionesId) {
        this.cmAuditoriaDevolucionesId = cmAuditoriaDevolucionesId;
    }

    public CmAuditoriaMasiva getCmAuditoriaMasivaId() {
        return cmAuditoriaMasivaId;
    }

    public void setCmAuditoriaMasivaId(CmAuditoriaMasiva cmAuditoriaMasivaId) {
        this.cmAuditoriaMasivaId = cmAuditoriaMasivaId;
    }

    public CmConciliaciones getCmConciliacionesId() {
        return cmConciliacionesId;
    }

    public void setCmConciliacionesId(CmConciliaciones cmConciliacionesId) {
        this.cmConciliacionesId = cmConciliacionesId;
    }

    public CmDevolucionMasiva getCmDevolucionMasivaId() {
        return cmDevolucionMasivaId;
    }

    public void setCmDevolucionMasivaId(CmDevolucionMasiva cmDevolucionMasivaId) {
        this.cmDevolucionMasivaId = cmDevolucionMasivaId;
    }

    public CmFeRipsCargas getCmFeRipsCargasId() {
        return cmFeRipsCargasId;
    }

    public void setCmFeRipsCargasId(CmFeRipsCargas cmFeRipsCargasId) {
        this.cmFeRipsCargasId = cmFeRipsCargasId;
    }

    public CmGlosaRespuestas getCmGlosaRespuestasId() {
        return cmGlosaRespuestasId;
    }

    public void setCmGlosaRespuestasId(CmGlosaRespuestas cmGlosaRespuestasId) {
        this.cmGlosaRespuestasId = cmGlosaRespuestasId;
    }

    public CmGlosaRespuestas getCmGlosaRespuestasConciliacionId() {
        return cmGlosaRespuestasConciliacionId;
    }

    public void setCmGlosaRespuestasConciliacionId(CmGlosaRespuestas cmGlosaRespuestasConciliacionId) {
        this.cmGlosaRespuestasConciliacionId = cmGlosaRespuestasConciliacionId;
    }

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
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
        if (!(object instanceof CmRadicados)) {
            return false;
        }
        CmRadicados other = (CmRadicados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRadicados[ id=" + id + " ]";
    }
    
}
