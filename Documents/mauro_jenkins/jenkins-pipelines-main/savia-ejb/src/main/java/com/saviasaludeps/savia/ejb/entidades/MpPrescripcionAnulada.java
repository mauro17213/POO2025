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
@Table(name = "mp_prescripcion_anulada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionAnulada.findAll", query = "SELECT m FROM MpPrescripcionAnulada m"),
    @NamedQuery(name = "MpPrescripcionAnulada.findById", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByEstado", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByTipo", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByNumeroPrescripcion", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.numeroPrescripcion = :numeroPrescripcion"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByJustificacion", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.justificacion = :justificacion"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByObservacion", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByUsuarioSolicita", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.usuarioSolicita = :usuarioSolicita"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByFechaHoraSolicitud", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.fechaHoraSolicitud = :fechaHoraSolicitud"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByUsuarioAnula", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.usuarioAnula = :usuarioAnula"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByFechaHoraAnulacion", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.fechaHoraAnulacion = :fechaHoraAnulacion"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionAnulada.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionAnulada m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpPrescripcionAnulada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "tipo")
    private Integer tipo;
    @Size(max = 32)
    @Column(name = "numero_prescripcion")
    private String numeroPrescripcion;
    @Size(max = 2048)
    @Column(name = "justificacion")
    private String justificacion;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 128)
    @Column(name = "usuario_solicita")
    private String usuarioSolicita;
    @Column(name = "fecha_hora_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSolicitud;
    @Size(max = 128)
    @Column(name = "usuario_anula")
    private String usuarioAnula;
    @Column(name = "fecha_hora_anulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAnulacion;
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
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;

    public MpPrescripcionAnulada() {
    }

    public MpPrescripcionAnulada(Integer id) {
        this.id = id;
    }

    public MpPrescripcionAnulada(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuarioSolicita() {
        return usuarioSolicita;
    }

    public void setUsuarioSolicita(String usuarioSolicita) {
        this.usuarioSolicita = usuarioSolicita;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public String getUsuarioAnula() {
        return usuarioAnula;
    }

    public void setUsuarioAnula(String usuarioAnula) {
        this.usuarioAnula = usuarioAnula;
    }

    public Date getFechaHoraAnulacion() {
        return fechaHoraAnulacion;
    }

    public void setFechaHoraAnulacion(Date fechaHoraAnulacion) {
        this.fechaHoraAnulacion = fechaHoraAnulacion;
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

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
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
        if (!(object instanceof MpPrescripcionAnulada)) {
            return false;
        }
        MpPrescripcionAnulada other = (MpPrescripcionAnulada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionAnulada[ id=" + id + " ]";
    }
    
}
