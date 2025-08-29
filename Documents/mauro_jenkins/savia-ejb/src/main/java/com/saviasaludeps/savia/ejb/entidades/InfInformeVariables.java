/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "inf_informe_variables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfInformeVariables.findAll", query = "SELECT i FROM InfInformeVariables i"),
    @NamedQuery(name = "InfInformeVariables.findById", query = "SELECT i FROM InfInformeVariables i WHERE i.id = :id"),
    @NamedQuery(name = "InfInformeVariables.findByNombre", query = "SELECT i FROM InfInformeVariables i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InfInformeVariables.findByValor", query = "SELECT i FROM InfInformeVariables i WHERE i.valor = :valor"),
    @NamedQuery(name = "InfInformeVariables.findByOrden", query = "SELECT i FROM InfInformeVariables i WHERE i.orden = :orden"),
    @NamedQuery(name = "InfInformeVariables.findByTipo", query = "SELECT i FROM InfInformeVariables i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "InfInformeVariables.findByDinamico", query = "SELECT i FROM InfInformeVariables i WHERE i.dinamico = :dinamico"),
    @NamedQuery(name = "InfInformeVariables.findByFechaAutomatica", query = "SELECT i FROM InfInformeVariables i WHERE i.fechaAutomatica = :fechaAutomatica"),
    @NamedQuery(name = "InfInformeVariables.findByUsuarioCrea", query = "SELECT i FROM InfInformeVariables i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfInformeVariables.findByFechaHoraCrea", query = "SELECT i FROM InfInformeVariables i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfInformeVariables.findByTerminalCrea", query = "SELECT i FROM InfInformeVariables i WHERE i.terminalCrea = :terminalCrea")})
public class InfInformeVariables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "dinamico")
    private Boolean dinamico;
    @Column(name = "fecha_automatica")
    private Boolean fechaAutomatica;
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
    @JoinColumn(name = "inf_informes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfInformes infInformesId;
    @OneToMany(mappedBy = "infInformeVariablesId", fetch = FetchType.LAZY)
    private List<InfInformeValores> infInformeValoresList;

    public InfInformeVariables() {
    }

    public InfInformeVariables(Integer id) {
        this.id = id;
    }

    public InfInformeVariables(Integer id, String nombre, String valor, int orden, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.orden = orden;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Boolean getDinamico() {
        return dinamico;
    }

    public void setDinamico(Boolean dinamico) {
        this.dinamico = dinamico;
    }

    public Boolean getFechaAutomatica() {
        return fechaAutomatica;
    }

    public void setFechaAutomatica(Boolean fechaAutomatica) {
        this.fechaAutomatica = fechaAutomatica;
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

    public InfInformes getInfInformesId() {
        return infInformesId;
    }

    public void setInfInformesId(InfInformes infInformesId) {
        this.infInformesId = infInformesId;
    }

    @XmlTransient
    public List<InfInformeValores> getInfInformeValoresList() {
        return infInformeValoresList;
    }

    public void setInfInformeValoresList(List<InfInformeValores> infInformeValoresList) {
        this.infInformeValoresList = infInformeValoresList;
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
        if (!(object instanceof InfInformeVariables)) {
            return false;
        }
        InfInformeVariables other = (InfInformeVariables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfInformeVariables[ id=" + id + " ]";
    }
    
}
