/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "pe_variables_valores_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeVariablesValoresHistoricos.findAll", query = "SELECT p FROM PeVariablesValoresHistoricos p"),
    @NamedQuery(name = "PeVariablesValoresHistoricos.findById", query = "SELECT p FROM PeVariablesValoresHistoricos p WHERE p.id = :id"),
    @NamedQuery(name = "PeVariablesValoresHistoricos.findByValor", query = "SELECT p FROM PeVariablesValoresHistoricos p WHERE p.valor = :valor"),
    @NamedQuery(name = "PeVariablesValoresHistoricos.findByUsuarioCrea", query = "SELECT p FROM PeVariablesValoresHistoricos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeVariablesValoresHistoricos.findByTerminalCrea", query = "SELECT p FROM PeVariablesValoresHistoricos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeVariablesValoresHistoricos.findByFechaHoraCrea", query = "SELECT p FROM PeVariablesValoresHistoricos p WHERE p.fechaHoraCrea = :fechaHoraCrea")})
public class PeVariablesValoresHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "valor")
    private String valor;
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
    @JoinColumn(name = "pe_cargas_variables_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeCargasVariables peCargasVariablesId;
    @JoinColumn(name = "pe_afiliados_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosProgramas peAfiliadosProgramasId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @JoinColumn(name = "pe_variables_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeVariables peVariablesId;
    @JoinColumn(name = "pe_variables_valores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeVariablesValores peVariablesValoresId;

    public PeVariablesValoresHistoricos() {
    }

    public PeVariablesValoresHistoricos(Integer id) {
        this.id = id;
    }

    public PeVariablesValoresHistoricos(Integer id, String valor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valor = valor;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public PeCargasVariables getPeCargasVariablesId() {
        return peCargasVariablesId;
    }

    public void setPeCargasVariablesId(PeCargasVariables peCargasVariablesId) {
        this.peCargasVariablesId = peCargasVariablesId;
    }

    public PeAfiliadosProgramas getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosProgramas peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public PeVariables getPeVariablesId() {
        return peVariablesId;
    }

    public void setPeVariablesId(PeVariables peVariablesId) {
        this.peVariablesId = peVariablesId;
    }

    public PeVariablesValores getPeVariablesValoresId() {
        return peVariablesValoresId;
    }

    public void setPeVariablesValoresId(PeVariablesValores peVariablesValoresId) {
        this.peVariablesValoresId = peVariablesValoresId;
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
        if (!(object instanceof PeVariablesValoresHistoricos)) {
            return false;
        }
        PeVariablesValoresHistoricos other = (PeVariablesValoresHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeVariablesValoresHistoricos[ id=" + id + " ]";
    }
    
}
