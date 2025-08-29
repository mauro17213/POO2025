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
@Table(name = "cnt_contrato_giros_capita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoGirosCapita.findAll", query = "SELECT c FROM CntContratoGirosCapita c"),
    @NamedQuery(name = "CntContratoGirosCapita.findById", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoGirosCapita.findByPeriodoPago", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.periodoPago = :periodoPago"),
    @NamedQuery(name = "CntContratoGirosCapita.findByPeriodoGiro", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.periodoGiro = :periodoGiro"),
    @NamedQuery(name = "CntContratoGirosCapita.findByCantidadAfiliados", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.cantidadAfiliados = :cantidadAfiliados"),
    @NamedQuery(name = "CntContratoGirosCapita.findByValorUpcAfiliado", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.valorUpcAfiliado = :valorUpcAfiliado"),
    @NamedQuery(name = "CntContratoGirosCapita.findByValorGiroCapita", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.valorGiroCapita = :valorGiroCapita"),
    @NamedQuery(name = "CntContratoGirosCapita.findByUsuarioCrea", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoGirosCapita.findByTerminalCrea", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoGirosCapita.findByFechaHoraCrea", query = "SELECT c FROM CntContratoGirosCapita c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntContratoGirosCapita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_pago")
    @Temporal(TemporalType.DATE)
    private Date periodoPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_giro")
    @Temporal(TemporalType.DATE)
    private Date periodoGiro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_afiliados")
    private int cantidadAfiliados;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_upc_afiliado")
    private BigDecimal valorUpcAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_giro_capita")
    private BigDecimal valorGiroCapita;
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
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratos cntContratosId;

    public CntContratoGirosCapita() {
    }

    public CntContratoGirosCapita(Integer id) {
        this.id = id;
    }

    public CntContratoGirosCapita(Integer id, Date periodoPago, Date periodoGiro, int cantidadAfiliados, BigDecimal valorUpcAfiliado, BigDecimal valorGiroCapita, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.periodoPago = periodoPago;
        this.periodoGiro = periodoGiro;
        this.cantidadAfiliados = cantidadAfiliados;
        this.valorUpcAfiliado = valorUpcAfiliado;
        this.valorGiroCapita = valorGiroCapita;
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

    public Date getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(Date periodoPago) {
        this.periodoPago = periodoPago;
    }

    public Date getPeriodoGiro() {
        return periodoGiro;
    }

    public void setPeriodoGiro(Date periodoGiro) {
        this.periodoGiro = periodoGiro;
    }

    public int getCantidadAfiliados() {
        return cantidadAfiliados;
    }

    public void setCantidadAfiliados(int cantidadAfiliados) {
        this.cantidadAfiliados = cantidadAfiliados;
    }

    public BigDecimal getValorUpcAfiliado() {
        return valorUpcAfiliado;
    }

    public void setValorUpcAfiliado(BigDecimal valorUpcAfiliado) {
        this.valorUpcAfiliado = valorUpcAfiliado;
    }

    public BigDecimal getValorGiroCapita() {
        return valorGiroCapita;
    }

    public void setValorGiroCapita(BigDecimal valorGiroCapita) {
        this.valorGiroCapita = valorGiroCapita;
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

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
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
        if (!(object instanceof CntContratoGirosCapita)) {
            return false;
        }
        CntContratoGirosCapita other = (CntContratoGirosCapita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoGirosCapita[ id=" + id + " ]";
    }
    
}
