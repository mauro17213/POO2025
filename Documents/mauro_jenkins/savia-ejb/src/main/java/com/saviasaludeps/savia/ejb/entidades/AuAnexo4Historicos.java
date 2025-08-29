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
@Table(name = "au_anexo4_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Historicos.findAll", query = "SELECT a FROM AuAnexo4Historicos a"),
    @NamedQuery(name = "AuAnexo4Historicos.findById", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Historicos.findByMaeCausaId", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.maeCausaId = :maeCausaId"),
    @NamedQuery(name = "AuAnexo4Historicos.findByMaeCausaCodigo", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.maeCausaCodigo = :maeCausaCodigo"),
    @NamedQuery(name = "AuAnexo4Historicos.findByMaeCausaValor", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.maeCausaValor = :maeCausaValor"),
    @NamedQuery(name = "AuAnexo4Historicos.findByObservacionAutorizacion", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.observacionAutorizacion = :observacionAutorizacion"),
    @NamedQuery(name = "AuAnexo4Historicos.findByEstado", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo4Historicos.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Historicos.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Historicos.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Historicos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo4Historicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_causa_id")
    private int maeCausaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_causa_codigo")
    private String maeCausaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_causa_valor")
    private String maeCausaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "observacion_autorizacion")
    private String observacionAutorizacion;
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
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;

    public AuAnexo4Historicos() {
    }

    public AuAnexo4Historicos(Integer id) {
        this.id = id;
    }

    public AuAnexo4Historicos(Integer id, int maeCausaId, String maeCausaCodigo, String maeCausaValor, String observacionAutorizacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeCausaId = maeCausaId;
        this.maeCausaCodigo = maeCausaCodigo;
        this.maeCausaValor = maeCausaValor;
        this.observacionAutorizacion = observacionAutorizacion;
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

    public int getMaeCausaId() {
        return maeCausaId;
    }

    public void setMaeCausaId(int maeCausaId) {
        this.maeCausaId = maeCausaId;
    }

    public String getMaeCausaCodigo() {
        return maeCausaCodigo;
    }

    public void setMaeCausaCodigo(String maeCausaCodigo) {
        this.maeCausaCodigo = maeCausaCodigo;
    }

    public String getMaeCausaValor() {
        return maeCausaValor;
    }

    public void setMaeCausaValor(String maeCausaValor) {
        this.maeCausaValor = maeCausaValor;
    }

    public String getObservacionAutorizacion() {
        return observacionAutorizacion;
    }

    public void setObservacionAutorizacion(String observacionAutorizacion) {
        this.observacionAutorizacion = observacionAutorizacion;
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
        if (!(object instanceof AuAnexo4Historicos)) {
            return false;
        }
        AuAnexo4Historicos other = (AuAnexo4Historicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Historicos[ id=" + id + " ]";
    }
    
}
