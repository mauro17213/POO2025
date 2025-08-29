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
@Table(name = "gj_proceso_pretenciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesoPretenciones.findAll", query = "SELECT g FROM GjProcesoPretenciones g"),
    @NamedQuery(name = "GjProcesoPretenciones.findById", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesoPretenciones.findByMaePretencionId", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.maePretencionId = :maePretencionId"),
    @NamedQuery(name = "GjProcesoPretenciones.findByMaePretencionCodigo", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.maePretencionCodigo = :maePretencionCodigo"),
    @NamedQuery(name = "GjProcesoPretenciones.findByMaePretencionValor", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.maePretencionValor = :maePretencionValor"),
    @NamedQuery(name = "GjProcesoPretenciones.findByUsuarioCrea", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjProcesoPretenciones.findByTerminalCrea", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesoPretenciones.findByFechaHoraCrea", query = "SELECT g FROM GjProcesoPretenciones g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GjProcesoPretenciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_pretencion_id")
    private int maePretencionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_pretencion_codigo")
    private String maePretencionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_pretencion_valor")
    private String maePretencionValor;
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
    @JoinColumn(name = "gj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesos gjProcesosId;

    public GjProcesoPretenciones() {
    }

    public GjProcesoPretenciones(Integer id) {
        this.id = id;
    }

    public GjProcesoPretenciones(Integer id, int maePretencionId, String maePretencionCodigo, String maePretencionValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maePretencionId = maePretencionId;
        this.maePretencionCodigo = maePretencionCodigo;
        this.maePretencionValor = maePretencionValor;
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

    public int getMaePretencionId() {
        return maePretencionId;
    }

    public void setMaePretencionId(int maePretencionId) {
        this.maePretencionId = maePretencionId;
    }

    public String getMaePretencionCodigo() {
        return maePretencionCodigo;
    }

    public void setMaePretencionCodigo(String maePretencionCodigo) {
        this.maePretencionCodigo = maePretencionCodigo;
    }

    public String getMaePretencionValor() {
        return maePretencionValor;
    }

    public void setMaePretencionValor(String maePretencionValor) {
        this.maePretencionValor = maePretencionValor;
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

    public GjProcesos getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProcesos gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
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
        if (!(object instanceof GjProcesoPretenciones)) {
            return false;
        }
        GjProcesoPretenciones other = (GjProcesoPretenciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesoPretenciones[ id=" + id + " ]";
    }
    
}
