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
@Table(name = "au_anexo3_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3Historicos.findAll", query = "SELECT a FROM AuAnexo3Historicos a"),
    @NamedQuery(name = "AuAnexo3Historicos.findById", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3Historicos.findByTipo", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuAnexo3Historicos.findByObservacion", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuAnexo3Historicos.findByEstado", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo3Historicos.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3Historicos.findByTerminalCrea", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3Historicos.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3Historicos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo3Historicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private Integer estado;
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
    @JoinColumn(name = "au_anexo3_items_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo3Items auAnexo3ItemsId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;

    public AuAnexo3Historicos() {
    }

    public AuAnexo3Historicos(Integer id) {
        this.id = id;
    }

    public AuAnexo3Historicos(Integer id, int tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public AuAnexo3Items getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Items auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
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
        if (!(object instanceof AuAnexo3Historicos)) {
            return false;
        }
        AuAnexo3Historicos other = (AuAnexo3Historicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3Historicos[ id=" + id + " ]";
    }
    
}
