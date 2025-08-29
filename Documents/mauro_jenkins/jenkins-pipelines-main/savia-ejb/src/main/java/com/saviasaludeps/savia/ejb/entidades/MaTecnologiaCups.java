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
@Table(name = "ma_tecnologia_cups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaTecnologiaCups.findAll", query = "SELECT m FROM MaTecnologiaCups m"),
    @NamedQuery(name = "MaTecnologiaCups.findById", query = "SELECT m FROM MaTecnologiaCups m WHERE m.id = :id"),
    @NamedQuery(name = "MaTecnologiaCups.findByCodigoPropio", query = "SELECT m FROM MaTecnologiaCups m WHERE m.codigoPropio = :codigoPropio"),
    @NamedQuery(name = "MaTecnologiaCups.findByCups", query = "SELECT m FROM MaTecnologiaCups m WHERE m.cups = :cups"),
    @NamedQuery(name = "MaTecnologiaCups.findByMaeCoberturaId", query = "SELECT m FROM MaTecnologiaCups m WHERE m.maeCoberturaId = :maeCoberturaId"),
    @NamedQuery(name = "MaTecnologiaCups.findByMaeCoberturaCodigo", query = "SELECT m FROM MaTecnologiaCups m WHERE m.maeCoberturaCodigo = :maeCoberturaCodigo"),
    @NamedQuery(name = "MaTecnologiaCups.findByMaeCoberturaValor", query = "SELECT m FROM MaTecnologiaCups m WHERE m.maeCoberturaValor = :maeCoberturaValor"),
    @NamedQuery(name = "MaTecnologiaCups.findByFechaCorte", query = "SELECT m FROM MaTecnologiaCups m WHERE m.fechaCorte = :fechaCorte")})
public class MaTecnologiaCups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_propio")
    private String codigoPropio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cups")
    private String cups;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_cobertura_id")
    private int maeCoberturaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_cobertura_codigo")
    private String maeCoberturaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_cobertura_valor")
    private String maeCoberturaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;
    @JoinColumn(name = "ma_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaTecnologias maTecnologiasId;

    public MaTecnologiaCups() {
    }

    public MaTecnologiaCups(Integer id) {
        this.id = id;
    }

    public MaTecnologiaCups(Integer id, String codigoPropio, String cups, int maeCoberturaId, String maeCoberturaCodigo, String maeCoberturaValor, Date fechaCorte) {
        this.id = id;
        this.codigoPropio = codigoPropio;
        this.cups = cups;
        this.maeCoberturaId = maeCoberturaId;
        this.maeCoberturaCodigo = maeCoberturaCodigo;
        this.maeCoberturaValor = maeCoberturaValor;
        this.fechaCorte = fechaCorte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoPropio() {
        return codigoPropio;
    }

    public void setCodigoPropio(String codigoPropio) {
        this.codigoPropio = codigoPropio;
    }

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public int getMaeCoberturaId() {
        return maeCoberturaId;
    }

    public void setMaeCoberturaId(int maeCoberturaId) {
        this.maeCoberturaId = maeCoberturaId;
    }

    public String getMaeCoberturaCodigo() {
        return maeCoberturaCodigo;
    }

    public void setMaeCoberturaCodigo(String maeCoberturaCodigo) {
        this.maeCoberturaCodigo = maeCoberturaCodigo;
    }

    public String getMaeCoberturaValor() {
        return maeCoberturaValor;
    }

    public void setMaeCoberturaValor(String maeCoberturaValor) {
        this.maeCoberturaValor = maeCoberturaValor;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public MaTecnologias getMaTecnologiasId() {
        return maTecnologiasId;
    }

    public void setMaTecnologiasId(MaTecnologias maTecnologiasId) {
        this.maTecnologiasId = maTecnologiasId;
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
        if (!(object instanceof MaTecnologiaCups)) {
            return false;
        }
        MaTecnologiaCups other = (MaTecnologiaCups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaTecnologiaCups[ id=" + id + " ]";
    }
    
}
