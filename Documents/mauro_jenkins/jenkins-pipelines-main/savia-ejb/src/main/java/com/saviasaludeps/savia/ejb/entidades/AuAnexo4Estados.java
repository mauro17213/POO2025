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
@Table(name = "au_anexo4_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Estados.findAll", query = "SELECT a FROM AuAnexo4Estados a"),
    @NamedQuery(name = "AuAnexo4Estados.findById", query = "SELECT a FROM AuAnexo4Estados a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Estados.findByMaeEstadoId", query = "SELECT a FROM AuAnexo4Estados a WHERE a.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "AuAnexo4Estados.findByMaeEstadoCodigo", query = "SELECT a FROM AuAnexo4Estados a WHERE a.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "AuAnexo4Estados.findByMaeEstadoValor", query = "SELECT a FROM AuAnexo4Estados a WHERE a.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "AuAnexo4Estados.findByMaeMotivoEstadoId", query = "SELECT a FROM AuAnexo4Estados a WHERE a.maeMotivoEstadoId = :maeMotivoEstadoId"),
    @NamedQuery(name = "AuAnexo4Estados.findByMaeMotivoEstadoCodigo", query = "SELECT a FROM AuAnexo4Estados a WHERE a.maeMotivoEstadoCodigo = :maeMotivoEstadoCodigo"),
    @NamedQuery(name = "AuAnexo4Estados.findByMaeMotivoEstadoValor", query = "SELECT a FROM AuAnexo4Estados a WHERE a.maeMotivoEstadoValor = :maeMotivoEstadoValor"),
    @NamedQuery(name = "AuAnexo4Estados.findByObservacion", query = "SELECT a FROM AuAnexo4Estados a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuAnexo4Estados.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Estados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Estados.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Estados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Estados.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Estados a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo4Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_id")
    private int maeEstadoId;
    @Size(max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Column(name = "mae_motivo_estado_id")
    private Integer maeMotivoEstadoId;
    @Size(max = 8)
    @Column(name = "mae_motivo_estado_codigo")
    private String maeMotivoEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_estado_valor")
    private String maeMotivoEstadoValor;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
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

    public AuAnexo4Estados() {
    }

    public AuAnexo4Estados(Integer id) {
        this.id = id;
    }

    public AuAnexo4Estados(Integer id, int maeEstadoId, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
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

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public Integer getMaeMotivoEstadoId() {
        return maeMotivoEstadoId;
    }

    public void setMaeMotivoEstadoId(Integer maeMotivoEstadoId) {
        this.maeMotivoEstadoId = maeMotivoEstadoId;
    }

    public String getMaeMotivoEstadoCodigo() {
        return maeMotivoEstadoCodigo;
    }

    public void setMaeMotivoEstadoCodigo(String maeMotivoEstadoCodigo) {
        this.maeMotivoEstadoCodigo = maeMotivoEstadoCodigo;
    }

    public String getMaeMotivoEstadoValor() {
        return maeMotivoEstadoValor;
    }

    public void setMaeMotivoEstadoValor(String maeMotivoEstadoValor) {
        this.maeMotivoEstadoValor = maeMotivoEstadoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof AuAnexo4Estados)) {
            return false;
        }
        AuAnexo4Estados other = (AuAnexo4Estados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Estados[ id=" + id + " ]";
    }
    
}
