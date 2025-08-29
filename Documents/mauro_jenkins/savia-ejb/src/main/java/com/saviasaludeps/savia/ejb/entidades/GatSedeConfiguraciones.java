/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "gat_sede_configuraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatSedeConfiguraciones.findAll", query = "SELECT g FROM GatSedeConfiguraciones g"),
    @NamedQuery(name = "GatSedeConfiguraciones.findById", query = "SELECT g FROM GatSedeConfiguraciones g WHERE g.id = :id"),
    @NamedQuery(name = "GatSedeConfiguraciones.findByTurnero", query = "SELECT g FROM GatSedeConfiguraciones g WHERE g.turnero = :turnero"),
    @NamedQuery(name = "GatSedeConfiguraciones.findByCaracol", query = "SELECT g FROM GatSedeConfiguraciones g WHERE g.caracol = :caracol")})
public class GatSedeConfiguraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "turnero")
    private int turnero;
    @Column(name = "caracol")
    private Boolean caracol;
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnSedes gnSedesId;

    public GatSedeConfiguraciones() {
    }

    public GatSedeConfiguraciones(Integer id) {
        this.id = id;
    }

    public GatSedeConfiguraciones(Integer id, int turnero) {
        this.id = id;
        this.turnero = turnero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTurnero() {
        return turnero;
    }

    public void setTurnero(int turnero) {
        this.turnero = turnero;
    }

    public Boolean getCaracol() {
        return caracol;
    }

    public void setCaracol(Boolean caracol) {
        this.caracol = caracol;
    }

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
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
        if (!(object instanceof GatSedeConfiguraciones)) {
            return false;
        }
        GatSedeConfiguraciones other = (GatSedeConfiguraciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatSedeConfiguraciones[ id=" + id + " ]";
    }
    
}
