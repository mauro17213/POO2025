/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cm_auditoria_autorizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findAll", query = "SELECT c FROM CmAuditoriaAutorizaciones c"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findById", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByNumeroAutorizacion", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.numeroAutorizacion = :numeroAutorizacion"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByCodigoServicio", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.codigoServicio = :codigoServicio"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByNombreServicio", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.nombreServicio = :nombreServicio"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByCantidad", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByValorAutorizacion", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.valorAutorizacion = :valorAutorizacion"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByFechaAutorizacion", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.fechaAutorizacion = :fechaAutorizacion"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByActiva", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.activa = :activa"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByUsuarioModifica", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByTerminalModifica", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByFechaHoraModifica", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "CmAuditoriaAutorizaciones.findByNombrePrestador", query = "SELECT c FROM CmAuditoriaAutorizaciones c WHERE c.nombrePrestador = :nombrePrestador")})
public class CmAuditoriaAutorizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_autorizacion")
    private String numeroAutorizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo_servicio")
    private String codigoServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre_servicio")
    private String nombreServicio;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_autorizacion")
    private BigDecimal valorAutorizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutorizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activa")
    private boolean activa;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 264)
    @Column(name = "nombre_prestador")
    private String nombrePrestador;
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;

    public CmAuditoriaAutorizaciones() {
    }

    public CmAuditoriaAutorizaciones(Integer id) {
        this.id = id;
    }

    public CmAuditoriaAutorizaciones(Integer id, String numeroAutorizacion, String codigoServicio, String nombreServicio, Date fechaAutorizacion, boolean activa, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numeroAutorizacion = numeroAutorizacion;
        this.codigoServicio = codigoServicio;
        this.nombreServicio = nombreServicio;
        this.fechaAutorizacion = fechaAutorizacion;
        this.activa = activa;
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

    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorAutorizacion() {
        return valorAutorizacion;
    }

    public void setValorAutorizacion(BigDecimal valorAutorizacion) {
        this.valorAutorizacion = valorAutorizacion;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getNombrePrestador() {
        return nombrePrestador;
    }

    public void setNombrePrestador(String nombrePrestador) {
        this.nombrePrestador = nombrePrestador;
    }

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
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
        if (!(object instanceof CmAuditoriaAutorizaciones)) {
            return false;
        }
        CmAuditoriaAutorizaciones other = (CmAuditoriaAutorizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaAutorizaciones[ id=" + id + " ]";
    }
    
}
