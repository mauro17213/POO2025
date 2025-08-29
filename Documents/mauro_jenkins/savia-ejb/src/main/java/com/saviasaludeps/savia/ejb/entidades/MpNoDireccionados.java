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
@Table(name = "mp_no_direccionados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpNoDireccionados.findAll", query = "SELECT m FROM MpNoDireccionados m"),
    @NamedQuery(name = "MpNoDireccionados.findById", query = "SELECT m FROM MpNoDireccionados m WHERE m.id = :id"),
    @NamedQuery(name = "MpNoDireccionados.findByTipoTecnologia", query = "SELECT m FROM MpNoDireccionados m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpNoDireccionados.findByJustificacionNoDireccionamiento", query = "SELECT m FROM MpNoDireccionados m WHERE m.justificacionNoDireccionamiento = :justificacionNoDireccionamiento"),
    @NamedQuery(name = "MpNoDireccionados.findByNumeroPrescripcionAsociada", query = "SELECT m FROM MpNoDireccionados m WHERE m.numeroPrescripcionAsociada = :numeroPrescripcionAsociada"),
    @NamedQuery(name = "MpNoDireccionados.findByConTecAsociada", query = "SELECT m FROM MpNoDireccionados m WHERE m.conTecAsociada = :conTecAsociada"),
    @NamedQuery(name = "MpNoDireccionados.findByFecNoDireccionamiento", query = "SELECT m FROM MpNoDireccionados m WHERE m.fecNoDireccionamiento = :fecNoDireccionamiento"),
    @NamedQuery(name = "MpNoDireccionados.findByEstadoNoDireccionamiento", query = "SELECT m FROM MpNoDireccionados m WHERE m.estadoNoDireccionamiento = :estadoNoDireccionamiento"),
    @NamedQuery(name = "MpNoDireccionados.findByFechaAnulacion", query = "SELECT m FROM MpNoDireccionados m WHERE m.fechaAnulacion = :fechaAnulacion"),
    @NamedQuery(name = "MpNoDireccionados.findByConsecutivoTecnologia", query = "SELECT m FROM MpNoDireccionados m WHERE m.consecutivoTecnologia = :consecutivoTecnologia"),
    @NamedQuery(name = "MpNoDireccionados.findByIdNoDireccionamiento", query = "SELECT m FROM MpNoDireccionados m WHERE m.idNoDireccionamiento = :idNoDireccionamiento"),
    @NamedQuery(name = "MpNoDireccionados.findByCodigoNoDireccionamiento", query = "SELECT m FROM MpNoDireccionados m WHERE m.codigoNoDireccionamiento = :codigoNoDireccionamiento"),
    @NamedQuery(name = "MpNoDireccionados.findByRespuestaNoDireccionamiento", query = "SELECT m FROM MpNoDireccionados m WHERE m.respuestaNoDireccionamiento = :respuestaNoDireccionamiento"),
    @NamedQuery(name = "MpNoDireccionados.findByIdTransaccion", query = "SELECT m FROM MpNoDireccionados m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpNoDireccionados.findByRespuestaAnulacion", query = "SELECT m FROM MpNoDireccionados m WHERE m.respuestaAnulacion = :respuestaAnulacion"),
    @NamedQuery(name = "MpNoDireccionados.findByUsuarioCrea", query = "SELECT m FROM MpNoDireccionados m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpNoDireccionados.findByTerminalCrea", query = "SELECT m FROM MpNoDireccionados m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpNoDireccionados.findByFechaHoraCrea", query = "SELECT m FROM MpNoDireccionados m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpNoDireccionados.findByUsuarioAnula", query = "SELECT m FROM MpNoDireccionados m WHERE m.usuarioAnula = :usuarioAnula"),
    @NamedQuery(name = "MpNoDireccionados.findByTerminalAnula", query = "SELECT m FROM MpNoDireccionados m WHERE m.terminalAnula = :terminalAnula"),
    @NamedQuery(name = "MpNoDireccionados.findByFechaHoraAnula", query = "SELECT m FROM MpNoDireccionados m WHERE m.fechaHoraAnula = :fechaHoraAnula")})
public class MpNoDireccionados implements Serializable {

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
    @Size(max = 512)
    @Column(name = "justificacion_no_direccionamiento")
    private String justificacionNoDireccionamiento;
    @Size(max = 45)
    @Column(name = "numero_prescripcion_asociada")
    private String numeroPrescripcionAsociada;
    @Column(name = "con_tec_asociada")
    private Integer conTecAsociada;
    @Column(name = "fec_no_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecNoDireccionamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_no_direccionamiento")
    private int estadoNoDireccionamiento;
    @Column(name = "fecha_anulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;
    @Column(name = "consecutivo_tecnologia")
    private Integer consecutivoTecnologia;
    @Column(name = "id_no_direccionamiento")
    private Integer idNoDireccionamiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_no_direccionamiento")
    private int codigoNoDireccionamiento;
    @Size(max = 512)
    @Column(name = "respuesta_no_direccionamiento")
    private String respuestaNoDireccionamiento;
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Size(max = 512)
    @Column(name = "respuesta_anulacion")
    private String respuestaAnulacion;
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
    @Column(name = "usuario_anula")
    private String usuarioAnula;
    @Size(max = 16)
    @Column(name = "terminal_anula")
    private String terminalAnula;
    @Column(name = "fecha_hora_anula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAnula;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;

    public MpNoDireccionados() {
    }

    public MpNoDireccionados(Integer id) {
        this.id = id;
    }

    public MpNoDireccionados(Integer id, int tipoTecnologia, int estadoNoDireccionamiento, int codigoNoDireccionamiento, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.estadoNoDireccionamiento = estadoNoDireccionamiento;
        this.codigoNoDireccionamiento = codigoNoDireccionamiento;
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

    public String getJustificacionNoDireccionamiento() {
        return justificacionNoDireccionamiento;
    }

    public void setJustificacionNoDireccionamiento(String justificacionNoDireccionamiento) {
        this.justificacionNoDireccionamiento = justificacionNoDireccionamiento;
    }

    public String getNumeroPrescripcionAsociada() {
        return numeroPrescripcionAsociada;
    }

    public void setNumeroPrescripcionAsociada(String numeroPrescripcionAsociada) {
        this.numeroPrescripcionAsociada = numeroPrescripcionAsociada;
    }

    public Integer getConTecAsociada() {
        return conTecAsociada;
    }

    public void setConTecAsociada(Integer conTecAsociada) {
        this.conTecAsociada = conTecAsociada;
    }

    public Date getFecNoDireccionamiento() {
        return fecNoDireccionamiento;
    }

    public void setFecNoDireccionamiento(Date fecNoDireccionamiento) {
        this.fecNoDireccionamiento = fecNoDireccionamiento;
    }

    public int getEstadoNoDireccionamiento() {
        return estadoNoDireccionamiento;
    }

    public void setEstadoNoDireccionamiento(int estadoNoDireccionamiento) {
        this.estadoNoDireccionamiento = estadoNoDireccionamiento;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Integer getConsecutivoTecnologia() {
        return consecutivoTecnologia;
    }

    public void setConsecutivoTecnologia(Integer consecutivoTecnologia) {
        this.consecutivoTecnologia = consecutivoTecnologia;
    }

    public Integer getIdNoDireccionamiento() {
        return idNoDireccionamiento;
    }

    public void setIdNoDireccionamiento(Integer idNoDireccionamiento) {
        this.idNoDireccionamiento = idNoDireccionamiento;
    }

    public int getCodigoNoDireccionamiento() {
        return codigoNoDireccionamiento;
    }

    public void setCodigoNoDireccionamiento(int codigoNoDireccionamiento) {
        this.codigoNoDireccionamiento = codigoNoDireccionamiento;
    }

    public String getRespuestaNoDireccionamiento() {
        return respuestaNoDireccionamiento;
    }

    public void setRespuestaNoDireccionamiento(String respuestaNoDireccionamiento) {
        this.respuestaNoDireccionamiento = respuestaNoDireccionamiento;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getRespuestaAnulacion() {
        return respuestaAnulacion;
    }

    public void setRespuestaAnulacion(String respuestaAnulacion) {
        this.respuestaAnulacion = respuestaAnulacion;
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

    public String getUsuarioAnula() {
        return usuarioAnula;
    }

    public void setUsuarioAnula(String usuarioAnula) {
        this.usuarioAnula = usuarioAnula;
    }

    public String getTerminalAnula() {
        return terminalAnula;
    }

    public void setTerminalAnula(String terminalAnula) {
        this.terminalAnula = terminalAnula;
    }

    public Date getFechaHoraAnula() {
        return fechaHoraAnula;
    }

    public void setFechaHoraAnula(Date fechaHoraAnula) {
        this.fechaHoraAnula = fechaHoraAnula;
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
        if (!(object instanceof MpNoDireccionados)) {
            return false;
        }
        MpNoDireccionados other = (MpNoDireccionados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpNoDireccionados[ id=" + id + " ]";
    }
    
}
