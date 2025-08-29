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
@Table(name = "inf_informe_valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfInformeValores.findAll", query = "SELECT i FROM InfInformeValores i"),
    @NamedQuery(name = "InfInformeValores.findById", query = "SELECT i FROM InfInformeValores i WHERE i.id = :id"),
    @NamedQuery(name = "InfInformeValores.findByVariable", query = "SELECT i FROM InfInformeValores i WHERE i.variable = :variable"),
    @NamedQuery(name = "InfInformeValores.findByUsuarioCrea", query = "SELECT i FROM InfInformeValores i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfInformeValores.findByFechaHoraCrea", query = "SELECT i FROM InfInformeValores i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfInformeValores.findByTerminalCrea", query = "SELECT i FROM InfInformeValores i WHERE i.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "InfInformeValores.findByUsuarioModifica", query = "SELECT i FROM InfInformeValores i WHERE i.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "InfInformeValores.findByFechaHoraModifica", query = "SELECT i FROM InfInformeValores i WHERE i.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "InfInformeValores.findByTerminalModifica", query = "SELECT i FROM InfInformeValores i WHERE i.terminalModifica = :terminalModifica")})
public class InfInformeValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "variable")
    private String variable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @JoinColumn(name = "inf_generados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfInformeGenerados infGeneradosId;
    @JoinColumn(name = "inf_informe_variables_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private InfInformeVariables infInformeVariablesId;

    public InfInformeValores() {
    }

    public InfInformeValores(Integer id) {
        this.id = id;
    }

    public InfInformeValores(Integer id, String variable, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.variable = variable;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public InfInformeGenerados getInfGeneradosId() {
        return infGeneradosId;
    }

    public void setInfGeneradosId(InfInformeGenerados infGeneradosId) {
        this.infGeneradosId = infGeneradosId;
    }

    public InfInformeVariables getInfInformeVariablesId() {
        return infInformeVariablesId;
    }

    public void setInfInformeVariablesId(InfInformeVariables infInformeVariablesId) {
        this.infInformeVariablesId = infInformeVariablesId;
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
        if (!(object instanceof InfInformeValores)) {
            return false;
        }
        InfInformeValores other = (InfInformeValores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfInformeValores[ id=" + id + " ]";
    }
    
}
