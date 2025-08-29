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
@Table(name = "cm_auditoria_conceptos_contables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaConceptosContables.findAll", query = "SELECT c FROM CmAuditoriaConceptosContables c"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findById", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMaeConceptosId", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.maeConceptosId = :maeConceptosId"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMaeConceptosCodigo", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.maeConceptosCodigo = :maeConceptosCodigo"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMaeConceptosValor", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.maeConceptosValor = :maeConceptosValor"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMaeCentroCostoId", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.maeCentroCostoId = :maeCentroCostoId"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMaeCentroCostoCodigo", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.maeCentroCostoCodigo = :maeCentroCostoCodigo"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMaeCentroCostoValor", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.maeCentroCostoValor = :maeCentroCostoValor"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByPorcentaje", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByCodigoMunicipio", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByMunicipioAfiliado", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.municipioAfiliado = :municipioAfiliado"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaConceptosContables.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaConceptosContables c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmAuditoriaConceptosContables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_conceptos_id")
    private int maeConceptosId;
    @Size(max = 8)
    @Column(name = "mae_conceptos_codigo")
    private String maeConceptosCodigo;
    @Size(max = 128)
    @Column(name = "mae_conceptos_valor")
    private String maeConceptosValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_centro_costo_id")
    private int maeCentroCostoId;
    @Size(max = 8)
    @Column(name = "mae_centro_costo_codigo")
    private String maeCentroCostoCodigo;
    @Size(max = 128)
    @Column(name = "mae_centro_costo_valor")
    private String maeCentroCostoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "municipio_afiliado")
    private String municipioAfiliado;
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
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;

    public CmAuditoriaConceptosContables() {
    }

    public CmAuditoriaConceptosContables(Integer id) {
        this.id = id;
    }

    public CmAuditoriaConceptosContables(Integer id, int maeConceptosId, int maeCentroCostoId, BigDecimal porcentaje, String codigoMunicipio, String municipioAfiliado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeConceptosId = maeConceptosId;
        this.maeCentroCostoId = maeCentroCostoId;
        this.porcentaje = porcentaje;
        this.codigoMunicipio = codigoMunicipio;
        this.municipioAfiliado = municipioAfiliado;
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

    public int getMaeConceptosId() {
        return maeConceptosId;
    }

    public void setMaeConceptosId(int maeConceptosId) {
        this.maeConceptosId = maeConceptosId;
    }

    public String getMaeConceptosCodigo() {
        return maeConceptosCodigo;
    }

    public void setMaeConceptosCodigo(String maeConceptosCodigo) {
        this.maeConceptosCodigo = maeConceptosCodigo;
    }

    public String getMaeConceptosValor() {
        return maeConceptosValor;
    }

    public void setMaeConceptosValor(String maeConceptosValor) {
        this.maeConceptosValor = maeConceptosValor;
    }

    public int getMaeCentroCostoId() {
        return maeCentroCostoId;
    }

    public void setMaeCentroCostoId(int maeCentroCostoId) {
        this.maeCentroCostoId = maeCentroCostoId;
    }

    public String getMaeCentroCostoCodigo() {
        return maeCentroCostoCodigo;
    }

    public void setMaeCentroCostoCodigo(String maeCentroCostoCodigo) {
        this.maeCentroCostoCodigo = maeCentroCostoCodigo;
    }

    public String getMaeCentroCostoValor() {
        return maeCentroCostoValor;
    }

    public void setMaeCentroCostoValor(String maeCentroCostoValor) {
        this.maeCentroCostoValor = maeCentroCostoValor;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getMunicipioAfiliado() {
        return municipioAfiliado;
    }

    public void setMunicipioAfiliado(String municipioAfiliado) {
        this.municipioAfiliado = municipioAfiliado;
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

    public CmDetalles getCmDetallesId() {
        return cmDetallesId;
    }

    public void setCmDetallesId(CmDetalles cmDetallesId) {
        this.cmDetallesId = cmDetallesId;
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
        if (!(object instanceof CmAuditoriaConceptosContables)) {
            return false;
        }
        CmAuditoriaConceptosContables other = (CmAuditoriaConceptosContables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaConceptosContables[ id=" + id + " ]";
    }
    
}
