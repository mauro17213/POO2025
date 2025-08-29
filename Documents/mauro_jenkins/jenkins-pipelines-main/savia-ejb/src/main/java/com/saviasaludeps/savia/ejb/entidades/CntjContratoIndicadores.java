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
@Table(name = "cntj_contrato_indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratoIndicadores.findAll", query = "SELECT c FROM CntjContratoIndicadores c"),
    @NamedQuery(name = "CntjContratoIndicadores.findById", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratoIndicadores.findByTipoIndicador", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.tipoIndicador = :tipoIndicador"),
    @NamedQuery(name = "CntjContratoIndicadores.findByDescripcion", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjContratoIndicadores.findByMeta", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.meta = :meta"),
    @NamedQuery(name = "CntjContratoIndicadores.findByAplicaDescuento", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.aplicaDescuento = :aplicaDescuento"),
    @NamedQuery(name = "CntjContratoIndicadores.findByPorcentajeDescuento", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.porcentajeDescuento = :porcentajeDescuento"),
    @NamedQuery(name = "CntjContratoIndicadores.findByValorDescuento", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.valorDescuento = :valorDescuento"),
    @NamedQuery(name = "CntjContratoIndicadores.findByUsuarioCrea", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratoIndicadores.findByTerminalCrea", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratoIndicadores.findByFechaHoraCrea", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjContratoIndicadores.findByUsuarioModifica", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjContratoIndicadores.findByTerminalModifica", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjContratoIndicadores.findByFechaHoraModifica", query = "SELECT c FROM CntjContratoIndicadores c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjContratoIndicadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_indicador")
    private int tipoIndicador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "meta")
    private String meta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_descuento")
    private boolean aplicaDescuento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_descuento")
    private BigDecimal porcentajeDescuento;
    @Column(name = "valor_descuento")
    private BigDecimal valorDescuento;
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
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;

    public CntjContratoIndicadores() {
    }

    public CntjContratoIndicadores(Integer id) {
        this.id = id;
    }

    public CntjContratoIndicadores(Integer id, int tipoIndicador, String descripcion, String meta, boolean aplicaDescuento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoIndicador = tipoIndicador;
        this.descripcion = descripcion;
        this.meta = meta;
        this.aplicaDescuento = aplicaDescuento;
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

    public int getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(int tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public boolean getAplicaDescuento() {
        return aplicaDescuento;
    }

    public void setAplicaDescuento(boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public BigDecimal getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento) {
        this.valorDescuento = valorDescuento;
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

    public CntjContratos getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContratos cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
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
        if (!(object instanceof CntjContratoIndicadores)) {
            return false;
        }
        CntjContratoIndicadores other = (CntjContratoIndicadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratoIndicadores[ id=" + id + " ]";
    }
    
}
