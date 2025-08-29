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
@Table(name = "cm_sincronizacion_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmSincronizacionDetalles.findAll", query = "SELECT c FROM CmSincronizacionDetalles c"),
    @NamedQuery(name = "CmSincronizacionDetalles.findById", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.id = :id"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByIdDetalles", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.idDetalles = :idDetalles"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByTipologia", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.tipologia = :tipologia"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByConsecutivo", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.consecutivo = :consecutivo"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByCodigoMunicipio", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByConceptoContable", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.conceptoContable = :conceptoContable"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByValorOperacion", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.valorOperacion = :valorOperacion"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByUsuarioCrea", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByTerminalCrea", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmSincronizacionDetalles.findByFechaHoraCrea", query = "SELECT c FROM CmSincronizacionDetalles c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmSincronizacionDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_detalles")
    private Integer idDetalles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipologia")
    private int tipologia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "concepto_contable")
    private String conceptoContable;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_operacion")
    private BigDecimal valorOperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_sincronizacion_encabezados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmSincronizacionEncabezados cmSincronizacionEncabezadosId;

    public CmSincronizacionDetalles() {
    }

    public CmSincronizacionDetalles(Integer id) {
        this.id = id;
    }

    public CmSincronizacionDetalles(Integer id, int tipologia, String consecutivo, String codigoMunicipio, String conceptoContable, BigDecimal valorOperacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipologia = tipologia;
        this.consecutivo = consecutivo;
        this.codigoMunicipio = codigoMunicipio;
        this.conceptoContable = conceptoContable;
        this.valorOperacion = valorOperacion;
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

    public Integer getIdDetalles() {
        return idDetalles;
    }

    public void setIdDetalles(Integer idDetalles) {
        this.idDetalles = idDetalles;
    }

    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int tipologia) {
        this.tipologia = tipologia;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getConceptoContable() {
        return conceptoContable;
    }

    public void setConceptoContable(String conceptoContable) {
        this.conceptoContable = conceptoContable;
    }

    public BigDecimal getValorOperacion() {
        return valorOperacion;
    }

    public void setValorOperacion(BigDecimal valorOperacion) {
        this.valorOperacion = valorOperacion;
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

    public CmSincronizacionEncabezados getCmSincronizacionEncabezadosId() {
        return cmSincronizacionEncabezadosId;
    }

    public void setCmSincronizacionEncabezadosId(CmSincronizacionEncabezados cmSincronizacionEncabezadosId) {
        this.cmSincronizacionEncabezadosId = cmSincronizacionEncabezadosId;
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
        if (!(object instanceof CmSincronizacionDetalles)) {
            return false;
        }
        CmSincronizacionDetalles other = (CmSincronizacionDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmSincronizacionDetalles[ id=" + id + " ]";
    }
    
}
