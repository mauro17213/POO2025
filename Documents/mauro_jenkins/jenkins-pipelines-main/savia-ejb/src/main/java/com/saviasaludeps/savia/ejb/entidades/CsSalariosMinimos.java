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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cs_salarios_minimos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsSalariosMinimos.findAll", query = "SELECT c FROM CsSalariosMinimos c"),
    @NamedQuery(name = "CsSalariosMinimos.findById", query = "SELECT c FROM CsSalariosMinimos c WHERE c.id = :id"),
    @NamedQuery(name = "CsSalariosMinimos.findByAgno", query = "SELECT c FROM CsSalariosMinimos c WHERE c.agno = :agno"),
    @NamedQuery(name = "CsSalariosMinimos.findByIncremento", query = "SELECT c FROM CsSalariosMinimos c WHERE c.incremento = :incremento"),
    @NamedQuery(name = "CsSalariosMinimos.findBySalarioMinimoMensual", query = "SELECT c FROM CsSalariosMinimos c WHERE c.salarioMinimoMensual = :salarioMinimoMensual"),
    @NamedQuery(name = "CsSalariosMinimos.findBySalarioMinimoDiario", query = "SELECT c FROM CsSalariosMinimos c WHERE c.salarioMinimoDiario = :salarioMinimoDiario"),
    @NamedQuery(name = "CsSalariosMinimos.findByUsuarioCrea", query = "SELECT c FROM CsSalariosMinimos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CsSalariosMinimos.findByTerminalCrea", query = "SELECT c FROM CsSalariosMinimos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CsSalariosMinimos.findByFechaHoraCrea", query = "SELECT c FROM CsSalariosMinimos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CsSalariosMinimos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agno")
    private int agno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "incremento")
    private BigDecimal incremento;
    @Column(name = "salario_minimo_mensual")
    private Integer salarioMinimoMensual;
    @Column(name = "salario_minimo_diario")
    private Integer salarioMinimoDiario;
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

    public CsSalariosMinimos() {
    }

    public CsSalariosMinimos(Integer id) {
        this.id = id;
    }

    public CsSalariosMinimos(Integer id, int agno, BigDecimal incremento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.agno = agno;
        this.incremento = incremento;
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

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public BigDecimal getIncremento() {
        return incremento;
    }

    public void setIncremento(BigDecimal incremento) {
        this.incremento = incremento;
    }

    public Integer getSalarioMinimoMensual() {
        return salarioMinimoMensual;
    }

    public void setSalarioMinimoMensual(Integer salarioMinimoMensual) {
        this.salarioMinimoMensual = salarioMinimoMensual;
    }

    public Integer getSalarioMinimoDiario() {
        return salarioMinimoDiario;
    }

    public void setSalarioMinimoDiario(Integer salarioMinimoDiario) {
        this.salarioMinimoDiario = salarioMinimoDiario;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CsSalariosMinimos)) {
            return false;
        }
        CsSalariosMinimos other = (CsSalariosMinimos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CsSalariosMinimos[ id=" + id + " ]";
    }
    
}
