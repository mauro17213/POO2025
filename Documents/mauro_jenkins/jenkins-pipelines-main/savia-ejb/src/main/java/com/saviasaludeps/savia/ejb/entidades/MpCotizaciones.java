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
@Table(name = "mp_cotizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpCotizaciones.findAll", query = "SELECT m FROM MpCotizaciones m"),
    @NamedQuery(name = "MpCotizaciones.findById", query = "SELECT m FROM MpCotizaciones m WHERE m.id = :id"),
    @NamedQuery(name = "MpCotizaciones.findByTipoTecnologia", query = "SELECT m FROM MpCotizaciones m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpCotizaciones.findByNombreTecnologia", query = "SELECT m FROM MpCotizaciones m WHERE m.nombreTecnologia = :nombreTecnologia"),
    @NamedQuery(name = "MpCotizaciones.findByNumeroPrescripcion", query = "SELECT m FROM MpCotizaciones m WHERE m.numeroPrescripcion = :numeroPrescripcion"),
    @NamedQuery(name = "MpCotizaciones.findByEstado", query = "SELECT m FROM MpCotizaciones m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpCotizaciones.findByConsecutivoOrden", query = "SELECT m FROM MpCotizaciones m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpCotizaciones.findByUsuarioCrea", query = "SELECT m FROM MpCotizaciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpCotizaciones.findByTerminalCrea", query = "SELECT m FROM MpCotizaciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpCotizaciones.findByFechaHoraCrea", query = "SELECT m FROM MpCotizaciones m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpCotizaciones.findByUsuarioModifica", query = "SELECT m FROM MpCotizaciones m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpCotizaciones.findByTerminalModifica", query = "SELECT m FROM MpCotizaciones m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpCotizaciones.findByFechaHoraModifica", query = "SELECT m FROM MpCotizaciones m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MpCotizaciones.findByUsuarioRechaza", query = "SELECT m FROM MpCotizaciones m WHERE m.usuarioRechaza = :usuarioRechaza"),
    @NamedQuery(name = "MpCotizaciones.findByTerminalRechaza", query = "SELECT m FROM MpCotizaciones m WHERE m.terminalRechaza = :terminalRechaza"),
    @NamedQuery(name = "MpCotizaciones.findByFechaHoraRechaza", query = "SELECT m FROM MpCotizaciones m WHERE m.fechaHoraRechaza = :fechaHoraRechaza")})
public class MpCotizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Size(max = 1024)
    @Column(name = "nombre_tecnologia")
    private String nombreTecnologia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_prescripcion")
    private String numeroPrescripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "consecutivo_orden")
    private Integer consecutivoOrden;
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
    @Size(max = 128)
    @Column(name = "usuario_rechaza")
    private String usuarioRechaza;
    @Size(max = 16)
    @Column(name = "terminal_rechaza")
    private String terminalRechaza;
    @Column(name = "fecha_hora_rechaza")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRechaza;
    @JoinColumn(name = "au_cotizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuCotizaciones auCotizacionesId;
    @JoinColumn(name = "mp_prescripcion_insumos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionInsumos mpPrescripcionInsumosId;
    @JoinColumn(name = "mp_prescripcion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentosId;
    @JoinColumn(name = "mp_prescripcion_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionTecnologias mpPrescripcionTecnologiasId;
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;

    public MpCotizaciones() {
    }

    public MpCotizaciones(Integer id) {
        this.id = id;
    }

    public MpCotizaciones(Integer id, int tipoTecnologia, String numeroPrescripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.numeroPrescripcion = numeroPrescripcion;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(Integer consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
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

    public String getUsuarioRechaza() {
        return usuarioRechaza;
    }

    public void setUsuarioRechaza(String usuarioRechaza) {
        this.usuarioRechaza = usuarioRechaza;
    }

    public String getTerminalRechaza() {
        return terminalRechaza;
    }

    public void setTerminalRechaza(String terminalRechaza) {
        this.terminalRechaza = terminalRechaza;
    }

    public Date getFechaHoraRechaza() {
        return fechaHoraRechaza;
    }

    public void setFechaHoraRechaza(Date fechaHoraRechaza) {
        this.fechaHoraRechaza = fechaHoraRechaza;
    }

    public AuCotizaciones getAuCotizacionesId() {
        return auCotizacionesId;
    }

    public void setAuCotizacionesId(AuCotizaciones auCotizacionesId) {
        this.auCotizacionesId = auCotizacionesId;
    }

    public MpPrescripcionInsumos getMpPrescripcionInsumosId() {
        return mpPrescripcionInsumosId;
    }

    public void setMpPrescripcionInsumosId(MpPrescripcionInsumos mpPrescripcionInsumosId) {
        this.mpPrescripcionInsumosId = mpPrescripcionInsumosId;
    }

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamentos mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
    }

    public MpPrescripcionTecnologias getMpPrescripcionTecnologiasId() {
        return mpPrescripcionTecnologiasId;
    }

    public void setMpPrescripcionTecnologiasId(MpPrescripcionTecnologias mpPrescripcionTecnologiasId) {
        this.mpPrescripcionTecnologiasId = mpPrescripcionTecnologiasId;
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
        if (!(object instanceof MpCotizaciones)) {
            return false;
        }
        MpCotizaciones other = (MpCotizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpCotizaciones[ id=" + id + " ]";
    }
    
}
