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
@Table(name = "au_anexo4_impresiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Impresiones.findAll", query = "SELECT a FROM AuAnexo4Impresiones a"),
    @NamedQuery(name = "AuAnexo4Impresiones.findById", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Impresiones.findByTipoImpresion", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.tipoImpresion = :tipoImpresion"),
    @NamedQuery(name = "AuAnexo4Impresiones.findByOrigenImpresion", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.origenImpresion = :origenImpresion"),
    @NamedQuery(name = "AuAnexo4Impresiones.findByImpresion", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.impresion = :impresion"),
    @NamedQuery(name = "AuAnexo4Impresiones.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Impresiones.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Impresiones.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Impresiones a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo4Impresiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_impresion")
    private int tipoImpresion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen_impresion")
    private int origenImpresion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "impresion")
    private int impresion;
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
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;

    public AuAnexo4Impresiones() {
    }

    public AuAnexo4Impresiones(Integer id) {
        this.id = id;
    }

    public AuAnexo4Impresiones(Integer id, int tipoImpresion, int origenImpresion, int impresion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoImpresion = tipoImpresion;
        this.origenImpresion = origenImpresion;
        this.impresion = impresion;
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

    public int getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(int tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public int getOrigenImpresion() {
        return origenImpresion;
    }

    public void setOrigenImpresion(int origenImpresion) {
        this.origenImpresion = origenImpresion;
    }

    public int getImpresion() {
        return impresion;
    }

    public void setImpresion(int impresion) {
        this.impresion = impresion;
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

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
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
        if (!(object instanceof AuAnexo4Impresiones)) {
            return false;
        }
        AuAnexo4Impresiones other = (AuAnexo4Impresiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Impresiones[ id=" + id + " ]";
    }
    
}
