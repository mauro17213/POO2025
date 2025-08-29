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
@Table(name = "auc_hospitalizacion_inoportunidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findAll", query = "SELECT a FROM AucHospitalizacionInoportunidades a"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findById", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByMaeTipoInoportunidadId", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.maeTipoInoportunidadId = :maeTipoInoportunidadId"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByMaeTipoInoportunidadCodigo", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.maeTipoInoportunidadCodigo = :maeTipoInoportunidadCodigo"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByMaeTipoInoportunidadValor", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.maeTipoInoportunidadValor = :maeTipoInoportunidadValor"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByFechaInicioInoportunidad", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.fechaInicioInoportunidad = :fechaInicioInoportunidad"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByFechaFinInoportunidad", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.fechaFinInoportunidad = :fechaFinInoportunidad"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByMaeMotivoFinInoportunidadId", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.maeMotivoFinInoportunidadId = :maeMotivoFinInoportunidadId"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByMaeMotivoFinInoportunidadCodigo", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.maeMotivoFinInoportunidadCodigo = :maeMotivoFinInoportunidadCodigo"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByMaeMotivoFinInoportunidadValor", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.maeMotivoFinInoportunidadValor = :maeMotivoFinInoportunidadValor"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByDiasInoportunidad", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.diasInoportunidad = :diasInoportunidad"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByObservacion", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByBorrado", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByObservacionBorrado", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.observacionBorrado = :observacionBorrado"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByTerminalModifica", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByUsuarioBorra", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByTerminalBorra", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AucHospitalizacionInoportunidades.findByFechaHoraBorra", query = "SELECT a FROM AucHospitalizacionInoportunidades a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AucHospitalizacionInoportunidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_inoportunidad_id")
    private int maeTipoInoportunidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_inoportunidad_codigo")
    private String maeTipoInoportunidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_inoportunidad_valor")
    private String maeTipoInoportunidadValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_inoportunidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioInoportunidad;
    @Column(name = "fecha_fin_inoportunidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinInoportunidad;
    @Column(name = "mae_motivo_fin_inoportunidad_id")
    private Integer maeMotivoFinInoportunidadId;
    @Size(max = 8)
    @Column(name = "mae_motivo_fin_inoportunidad_codigo")
    private String maeMotivoFinInoportunidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_fin_inoportunidad_valor")
    private String maeMotivoFinInoportunidadValor;
    @Column(name = "dias_inoportunidad")
    private Integer diasInoportunidad;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 512)
    @Column(name = "observacion_borrado")
    private String observacionBorrado;
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
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;

    public AucHospitalizacionInoportunidades() {
    }

    public AucHospitalizacionInoportunidades(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionInoportunidades(Integer id, int maeTipoInoportunidadId, String maeTipoInoportunidadCodigo, String maeTipoInoportunidadValor, Date fechaInicioInoportunidad, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoInoportunidadId = maeTipoInoportunidadId;
        this.maeTipoInoportunidadCodigo = maeTipoInoportunidadCodigo;
        this.maeTipoInoportunidadValor = maeTipoInoportunidadValor;
        this.fechaInicioInoportunidad = fechaInicioInoportunidad;
        this.borrado = borrado;
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

    public int getMaeTipoInoportunidadId() {
        return maeTipoInoportunidadId;
    }

    public void setMaeTipoInoportunidadId(int maeTipoInoportunidadId) {
        this.maeTipoInoportunidadId = maeTipoInoportunidadId;
    }

    public String getMaeTipoInoportunidadCodigo() {
        return maeTipoInoportunidadCodigo;
    }

    public void setMaeTipoInoportunidadCodigo(String maeTipoInoportunidadCodigo) {
        this.maeTipoInoportunidadCodigo = maeTipoInoportunidadCodigo;
    }

    public String getMaeTipoInoportunidadValor() {
        return maeTipoInoportunidadValor;
    }

    public void setMaeTipoInoportunidadValor(String maeTipoInoportunidadValor) {
        this.maeTipoInoportunidadValor = maeTipoInoportunidadValor;
    }

    public Date getFechaInicioInoportunidad() {
        return fechaInicioInoportunidad;
    }

    public void setFechaInicioInoportunidad(Date fechaInicioInoportunidad) {
        this.fechaInicioInoportunidad = fechaInicioInoportunidad;
    }

    public Date getFechaFinInoportunidad() {
        return fechaFinInoportunidad;
    }

    public void setFechaFinInoportunidad(Date fechaFinInoportunidad) {
        this.fechaFinInoportunidad = fechaFinInoportunidad;
    }

    public Integer getMaeMotivoFinInoportunidadId() {
        return maeMotivoFinInoportunidadId;
    }

    public void setMaeMotivoFinInoportunidadId(Integer maeMotivoFinInoportunidadId) {
        this.maeMotivoFinInoportunidadId = maeMotivoFinInoportunidadId;
    }

    public String getMaeMotivoFinInoportunidadCodigo() {
        return maeMotivoFinInoportunidadCodigo;
    }

    public void setMaeMotivoFinInoportunidadCodigo(String maeMotivoFinInoportunidadCodigo) {
        this.maeMotivoFinInoportunidadCodigo = maeMotivoFinInoportunidadCodigo;
    }

    public String getMaeMotivoFinInoportunidadValor() {
        return maeMotivoFinInoportunidadValor;
    }

    public void setMaeMotivoFinInoportunidadValor(String maeMotivoFinInoportunidadValor) {
        this.maeMotivoFinInoportunidadValor = maeMotivoFinInoportunidadValor;
    }

    public Integer getDiasInoportunidad() {
        return diasInoportunidad;
    }

    public void setDiasInoportunidad(Integer diasInoportunidad) {
        this.diasInoportunidad = diasInoportunidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getObservacionBorrado() {
        return observacionBorrado;
    }

    public void setObservacionBorrado(String observacionBorrado) {
        this.observacionBorrado = observacionBorrado;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
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
        if (!(object instanceof AucHospitalizacionInoportunidades)) {
            return false;
        }
        AucHospitalizacionInoportunidades other = (AucHospitalizacionInoportunidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionInoportunidades[ id=" + id + " ]";
    }
    
}
