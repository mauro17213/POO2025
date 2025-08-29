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
@Table(name = "ref_anexo9_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexo9Estados.findAll", query = "SELECT r FROM RefAnexo9Estados r"),
    @NamedQuery(name = "RefAnexo9Estados.findById", query = "SELECT r FROM RefAnexo9Estados r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexo9Estados.findByEstado", query = "SELECT r FROM RefAnexo9Estados r WHERE r.estado = :estado"),
    @NamedQuery(name = "RefAnexo9Estados.findByMaeMotivoId", query = "SELECT r FROM RefAnexo9Estados r WHERE r.maeMotivoId = :maeMotivoId"),
    @NamedQuery(name = "RefAnexo9Estados.findByMaeMotivoCodigo", query = "SELECT r FROM RefAnexo9Estados r WHERE r.maeMotivoCodigo = :maeMotivoCodigo"),
    @NamedQuery(name = "RefAnexo9Estados.findByMaeMotivoValor", query = "SELECT r FROM RefAnexo9Estados r WHERE r.maeMotivoValor = :maeMotivoValor"),
    @NamedQuery(name = "RefAnexo9Estados.findByMaeEstadoId", query = "SELECT r FROM RefAnexo9Estados r WHERE r.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "RefAnexo9Estados.findByMaeEstadoCodigo", query = "SELECT r FROM RefAnexo9Estados r WHERE r.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "RefAnexo9Estados.findByMaeEstadoValor", query = "SELECT r FROM RefAnexo9Estados r WHERE r.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "RefAnexo9Estados.findByObservacion", query = "SELECT r FROM RefAnexo9Estados r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "RefAnexo9Estados.findByUsuarioCrea", query = "SELECT r FROM RefAnexo9Estados r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefAnexo9Estados.findByTerminalCrea", query = "SELECT r FROM RefAnexo9Estados r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefAnexo9Estados.findByFechaHoraCrea", query = "SELECT r FROM RefAnexo9Estados r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RefAnexo9Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "mae_motivo_id")
    private Integer maeMotivoId;
    @Size(max = 8)
    @Column(name = "mae_motivo_codigo")
    private String maeMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_valor")
    private String maeMotivoValor;
    @Column(name = "mae_estado_id")
    private Integer maeEstadoId;
    @Size(max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Size(max = 1024)
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
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;

    public RefAnexo9Estados() {
    }

    public RefAnexo9Estados(Integer id) {
        this.id = id;
    }

    public RefAnexo9Estados(Integer id, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(Integer maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
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

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
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
        if (!(object instanceof RefAnexo9Estados)) {
            return false;
        }
        RefAnexo9Estados other = (RefAnexo9Estados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexo9Estados[ id=" + id + " ]";
    }
    
}
