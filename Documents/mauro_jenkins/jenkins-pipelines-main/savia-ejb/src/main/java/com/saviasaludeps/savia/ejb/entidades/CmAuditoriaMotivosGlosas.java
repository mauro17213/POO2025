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
@Table(name = "cm_auditoria_motivos_glosas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findAll", query = "SELECT c FROM CmAuditoriaMotivosGlosas c"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findById", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoId", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoId = :maeMotivoId"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoCodigo", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoCodigo = :maeMotivoCodigo"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoValor", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoValor = :maeMotivoValor"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoEspecificoId", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoEspecificoId = :maeMotivoEspecificoId"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoEspecificoCodigo", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoEspecificoCodigo = :maeMotivoEspecificoCodigo"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoEspecificoValor", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoEspecificoValor = :maeMotivoEspecificoValor"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoGlosaAplicacionId", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoGlosaAplicacionId = :maeMotivoGlosaAplicacionId"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoGlosaAplicacionCodigo", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoGlosaAplicacionCodigo = :maeMotivoGlosaAplicacionCodigo"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoGlosaAplicacionValor", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoGlosaAplicacionValor = :maeMotivoGlosaAplicacionValor"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByMaeMotivoGlosaAplicacionDescripcion", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.maeMotivoGlosaAplicacionDescripcion = :maeMotivoGlosaAplicacionDescripcion"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByPorcentaje", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByValorMotivo", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.valorMotivo = :valorMotivo"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByTipologia", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.tipologia = :tipologia"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaMotivosGlosas.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaMotivosGlosas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmAuditoriaMotivosGlosas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_id")
    private int maeMotivoId;
    @Size(max = 8)
    @Column(name = "mae_motivo_codigo")
    private String maeMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_valor")
    private String maeMotivoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_especifico_id")
    private int maeMotivoEspecificoId;
    @Size(max = 8)
    @Column(name = "mae_motivo_especifico_codigo")
    private String maeMotivoEspecificoCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_especifico_valor")
    private String maeMotivoEspecificoValor;
    @Column(name = "mae_motivo_glosa_aplicacion_id")
    private Integer maeMotivoGlosaAplicacionId;
    @Size(max = 8)
    @Column(name = "mae_motivo_glosa_aplicacion_codigo")
    private String maeMotivoGlosaAplicacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_glosa_aplicacion_valor")
    private String maeMotivoGlosaAplicacionValor;
    @Size(max = 512)
    @Column(name = "mae_motivo_glosa_aplicacion_descripcion")
    private String maeMotivoGlosaAplicacionDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Column(name = "valor_motivo")
    private BigDecimal valorMotivo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipologia")
    private int tipologia;
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

    public CmAuditoriaMotivosGlosas() {
    }

    public CmAuditoriaMotivosGlosas(Integer id) {
        this.id = id;
    }

    public CmAuditoriaMotivosGlosas(Integer id, int maeMotivoId, int maeMotivoEspecificoId, int tipologia, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeMotivoId = maeMotivoId;
        this.maeMotivoEspecificoId = maeMotivoEspecificoId;
        this.tipologia = tipologia;
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

    public int getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(int maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public int getMaeMotivoEspecificoId() {
        return maeMotivoEspecificoId;
    }

    public void setMaeMotivoEspecificoId(int maeMotivoEspecificoId) {
        this.maeMotivoEspecificoId = maeMotivoEspecificoId;
    }

    public String getMaeMotivoEspecificoCodigo() {
        return maeMotivoEspecificoCodigo;
    }

    public void setMaeMotivoEspecificoCodigo(String maeMotivoEspecificoCodigo) {
        this.maeMotivoEspecificoCodigo = maeMotivoEspecificoCodigo;
    }

    public String getMaeMotivoEspecificoValor() {
        return maeMotivoEspecificoValor;
    }

    public void setMaeMotivoEspecificoValor(String maeMotivoEspecificoValor) {
        this.maeMotivoEspecificoValor = maeMotivoEspecificoValor;
    }

    public Integer getMaeMotivoGlosaAplicacionId() {
        return maeMotivoGlosaAplicacionId;
    }

    public void setMaeMotivoGlosaAplicacionId(Integer maeMotivoGlosaAplicacionId) {
        this.maeMotivoGlosaAplicacionId = maeMotivoGlosaAplicacionId;
    }

    public String getMaeMotivoGlosaAplicacionCodigo() {
        return maeMotivoGlosaAplicacionCodigo;
    }

    public void setMaeMotivoGlosaAplicacionCodigo(String maeMotivoGlosaAplicacionCodigo) {
        this.maeMotivoGlosaAplicacionCodigo = maeMotivoGlosaAplicacionCodigo;
    }

    public String getMaeMotivoGlosaAplicacionValor() {
        return maeMotivoGlosaAplicacionValor;
    }

    public void setMaeMotivoGlosaAplicacionValor(String maeMotivoGlosaAplicacionValor) {
        this.maeMotivoGlosaAplicacionValor = maeMotivoGlosaAplicacionValor;
    }

    public String getMaeMotivoGlosaAplicacionDescripcion() {
        return maeMotivoGlosaAplicacionDescripcion;
    }

    public void setMaeMotivoGlosaAplicacionDescripcion(String maeMotivoGlosaAplicacionDescripcion) {
        this.maeMotivoGlosaAplicacionDescripcion = maeMotivoGlosaAplicacionDescripcion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getValorMotivo() {
        return valorMotivo;
    }

    public void setValorMotivo(BigDecimal valorMotivo) {
        this.valorMotivo = valorMotivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int tipologia) {
        this.tipologia = tipologia;
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
        if (!(object instanceof CmAuditoriaMotivosGlosas)) {
            return false;
        }
        CmAuditoriaMotivosGlosas other = (CmAuditoriaMotivosGlosas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaMotivosGlosas[ id=" + id + " ]";
    }
    
}
