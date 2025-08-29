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
@Table(name = "aus_servicio_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusServicioGestiones.findAll", query = "SELECT a FROM AusServicioGestiones a"),
    @NamedQuery(name = "AusServicioGestiones.findById", query = "SELECT a FROM AusServicioGestiones a WHERE a.id = :id"),
    @NamedQuery(name = "AusServicioGestiones.findByMaeEstadoId", query = "SELECT a FROM AusServicioGestiones a WHERE a.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "AusServicioGestiones.findByMaeEstadoCodigo", query = "SELECT a FROM AusServicioGestiones a WHERE a.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "AusServicioGestiones.findByMaeEstadoValor", query = "SELECT a FROM AusServicioGestiones a WHERE a.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "AusServicioGestiones.findByUsuarioCrea", query = "SELECT a FROM AusServicioGestiones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusServicioGestiones.findByTerminalCrea", query = "SELECT a FROM AusServicioGestiones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusServicioGestiones.findByFechaHoraCrea", query = "SELECT a FROM AusServicioGestiones a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusServicioGestiones.findByUsuarioModifica", query = "SELECT a FROM AusServicioGestiones a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusServicioGestiones.findByTerminalModifica", query = "SELECT a FROM AusServicioGestiones a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusServicioGestiones.findByFechaHoraModifica", query = "SELECT a FROM AusServicioGestiones a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusServicioGestiones implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "aus_servicios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusCasoServicios ausServiciosId;

    public AusServicioGestiones() {
    }

    public AusServicioGestiones(Integer id) {
        this.id = id;
    }

    public AusServicioGestiones(Integer id, int maeEstadoId, String maeEstadoCodigo, String maeEstadoValor, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.maeEstadoCodigo = maeEstadoCodigo;
        this.maeEstadoValor = maeEstadoValor;
        this.observacion = observacion;
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

    public AusCasoServicios getAusServiciosId() {
        return ausServiciosId;
    }

    public void setAusServiciosId(AusCasoServicios ausServiciosId) {
        this.ausServiciosId = ausServiciosId;
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
        if (!(object instanceof AusServicioGestiones)) {
            return false;
        }
        AusServicioGestiones other = (AusServicioGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusServicioGestiones[ id=" + id + " ]";
    }
    
}
