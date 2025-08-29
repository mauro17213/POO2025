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
@Table(name = "au_anexo2_rescate_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo2RescateGestiones.findAll", query = "SELECT a FROM AuAnexo2RescateGestiones a"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findById", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByTipo", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByObservacion", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByFechaHoraGestion", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.fechaHoraGestion = :fechaHoraGestion"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByMaeMotivoRescateId", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.maeMotivoRescateId = :maeMotivoRescateId"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByMaeMotivoRescateCodigo", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.maeMotivoRescateCodigo = :maeMotivoRescateCodigo"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByMaeMotivoRescateValor", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.maeMotivoRescateValor = :maeMotivoRescateValor"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByMaeMotivoRescateTipo", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.maeMotivoRescateTipo = :maeMotivoRescateTipo"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByFechaHoraDireccionamiento", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.fechaHoraDireccionamiento = :fechaHoraDireccionamiento"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByUsuarioCrea", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByTerminalCrea", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo2RescateGestiones.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo2RescateGestiones a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuAnexo2RescateGestiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_gestion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraGestion;
    @Column(name = "mae_motivo_rescate_id")
    private Integer maeMotivoRescateId;
    @Size(max = 8)
    @Column(name = "mae_motivo_rescate_codigo")
    private String maeMotivoRescateCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_rescate_valor")
    private String maeMotivoRescateValor;
    @Size(max = 4)
    @Column(name = "mae_motivo_rescate_tipo")
    private String maeMotivoRescateTipo;
    @Column(name = "fecha_hora_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDireccionamiento;
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
    @JoinColumn(name = "au_anexo2_rescates_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo2Rescates auAnexo2RescatesId;

    public AuAnexo2RescateGestiones() {
    }

    public AuAnexo2RescateGestiones(Integer id) {
        this.id = id;
    }

    public AuAnexo2RescateGestiones(Integer id, short tipo, String observacion, Date fechaHoraGestion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.observacion = observacion;
        this.fechaHoraGestion = fechaHoraGestion;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaHoraGestion() {
        return fechaHoraGestion;
    }

    public void setFechaHoraGestion(Date fechaHoraGestion) {
        this.fechaHoraGestion = fechaHoraGestion;
    }

    public Integer getMaeMotivoRescateId() {
        return maeMotivoRescateId;
    }

    public void setMaeMotivoRescateId(Integer maeMotivoRescateId) {
        this.maeMotivoRescateId = maeMotivoRescateId;
    }

    public String getMaeMotivoRescateCodigo() {
        return maeMotivoRescateCodigo;
    }

    public void setMaeMotivoRescateCodigo(String maeMotivoRescateCodigo) {
        this.maeMotivoRescateCodigo = maeMotivoRescateCodigo;
    }

    public String getMaeMotivoRescateValor() {
        return maeMotivoRescateValor;
    }

    public void setMaeMotivoRescateValor(String maeMotivoRescateValor) {
        this.maeMotivoRescateValor = maeMotivoRescateValor;
    }

    public String getMaeMotivoRescateTipo() {
        return maeMotivoRescateTipo;
    }

    public void setMaeMotivoRescateTipo(String maeMotivoRescateTipo) {
        this.maeMotivoRescateTipo = maeMotivoRescateTipo;
    }

    public Date getFechaHoraDireccionamiento() {
        return fechaHoraDireccionamiento;
    }

    public void setFechaHoraDireccionamiento(Date fechaHoraDireccionamiento) {
        this.fechaHoraDireccionamiento = fechaHoraDireccionamiento;
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

    public AuAnexo2Rescates getAuAnexo2RescatesId() {
        return auAnexo2RescatesId;
    }

    public void setAuAnexo2RescatesId(AuAnexo2Rescates auAnexo2RescatesId) {
        this.auAnexo2RescatesId = auAnexo2RescatesId;
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
        if (!(object instanceof AuAnexo2RescateGestiones)) {
            return false;
        }
        AuAnexo2RescateGestiones other = (AuAnexo2RescateGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo2RescateGestiones[ id=" + id + " ]";
    }
    
}
