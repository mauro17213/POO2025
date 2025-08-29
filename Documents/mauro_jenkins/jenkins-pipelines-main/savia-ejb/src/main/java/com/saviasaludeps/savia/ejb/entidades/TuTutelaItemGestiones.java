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
import javax.persistence.Lob;
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
@Table(name = "tu_tutela_item_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuTutelaItemGestiones.findAll", query = "SELECT t FROM TuTutelaItemGestiones t"),
    @NamedQuery(name = "TuTutelaItemGestiones.findById", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.id = :id"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByMaeEstadoItemId", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.maeEstadoItemId = :maeEstadoItemId"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByMaeEstadoItemCodigo", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.maeEstadoItemCodigo = :maeEstadoItemCodigo"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByMaeEstadoItemValor", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.maeEstadoItemValor = :maeEstadoItemValor"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByUsuarioCrea", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByTerminalCrea", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByFechaHoraCrea", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByUsuarioModifica", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByTerminalModifica", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuTutelaItemGestiones.findByFechaHoraModifica", query = "SELECT t FROM TuTutelaItemGestiones t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuTutelaItemGestiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "observacion_ips")
    private String observacionIps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_item_id")
    private int maeEstadoItemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_item_codigo")
    private String maeEstadoItemCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_item_valor")
    private String maeEstadoItemValor;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "tu_tutela_item_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelaItems tuTutelaItemId;

    public TuTutelaItemGestiones() {
    }

    public TuTutelaItemGestiones(Integer id) {
        this.id = id;
    }

    public TuTutelaItemGestiones(Integer id, String observacion, String observacionIps, int maeEstadoItemId, String maeEstadoItemCodigo, String maeEstadoItemValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.observacion = observacion;
        this.observacionIps = observacionIps;
        this.maeEstadoItemId = maeEstadoItemId;
        this.maeEstadoItemCodigo = maeEstadoItemCodigo;
        this.maeEstadoItemValor = maeEstadoItemValor;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservacionIps() {
        return observacionIps;
    }

    public void setObservacionIps(String observacionIps) {
        this.observacionIps = observacionIps;
    }

    public int getMaeEstadoItemId() {
        return maeEstadoItemId;
    }

    public void setMaeEstadoItemId(int maeEstadoItemId) {
        this.maeEstadoItemId = maeEstadoItemId;
    }

    public String getMaeEstadoItemCodigo() {
        return maeEstadoItemCodigo;
    }

    public void setMaeEstadoItemCodigo(String maeEstadoItemCodigo) {
        this.maeEstadoItemCodigo = maeEstadoItemCodigo;
    }

    public String getMaeEstadoItemValor() {
        return maeEstadoItemValor;
    }

    public void setMaeEstadoItemValor(String maeEstadoItemValor) {
        this.maeEstadoItemValor = maeEstadoItemValor;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public TuTutelaItems getTuTutelaItemId() {
        return tuTutelaItemId;
    }

    public void setTuTutelaItemId(TuTutelaItems tuTutelaItemId) {
        this.tuTutelaItemId = tuTutelaItemId;
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
        if (!(object instanceof TuTutelaItemGestiones)) {
            return false;
        }
        TuTutelaItemGestiones other = (TuTutelaItemGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuTutelaItemGestiones[ id=" + id + " ]";
    }
    
}
