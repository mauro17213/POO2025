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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "au_carga_detalles_anexos3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuCargaDetallesAnexos3.findAll", query = "SELECT a FROM AuCargaDetallesAnexos3 a"),
    @NamedQuery(name = "AuCargaDetallesAnexos3.findById", query = "SELECT a FROM AuCargaDetallesAnexos3 a WHERE a.id = :id")})
public class AuCargaDetallesAnexos3 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "au_anexo3_carga_detalles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3CargaDetalles auAnexo3CargaDetallesId;
    @JoinColumn(name = "au_anexo3_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3Cargas auAnexo3CargasId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;

    public AuCargaDetallesAnexos3() {
    }

    public AuCargaDetallesAnexos3(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuAnexo3CargaDetalles getAuAnexo3CargaDetallesId() {
        return auAnexo3CargaDetallesId;
    }

    public void setAuAnexo3CargaDetallesId(AuAnexo3CargaDetalles auAnexo3CargaDetallesId) {
        this.auAnexo3CargaDetallesId = auAnexo3CargaDetallesId;
    }

    public AuAnexo3Cargas getAuAnexo3CargasId() {
        return auAnexo3CargasId;
    }

    public void setAuAnexo3CargasId(AuAnexo3Cargas auAnexo3CargasId) {
        this.auAnexo3CargasId = auAnexo3CargasId;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
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
        if (!(object instanceof AuCargaDetallesAnexos3)) {
            return false;
        }
        AuCargaDetallesAnexos3 other = (AuCargaDetallesAnexos3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuCargaDetallesAnexos3[ id=" + id + " ]";
    }
    
}
