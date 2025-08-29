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
@Table(name = "auc_hospitalizacion_objeciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionObjeciones.findAll", query = "SELECT a FROM AucHospitalizacionObjeciones a"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findById", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByTipoTecnologia", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByMaTecnologiaId", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByMaTecnologiaCodigo", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByMaTecnologiaValor", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByCantidadSolicitada", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByObservacion", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByNotaCm", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.notaCm = :notaCm"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByBorrado", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByBorradoObservacion", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByTerminalModifica", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByUsuarioBorra", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByTerminalBorra", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AucHospitalizacionObjeciones.findByFechaHoraBorra", query = "SELECT a FROM AucHospitalizacionObjeciones a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AucHospitalizacionObjeciones implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 2048)
    @Column(name = "nota_cm")
    private String notaCm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 512)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
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

    public AucHospitalizacionObjeciones() {
    }

    public AucHospitalizacionObjeciones(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionObjeciones(Integer id, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, int cantidadSolicitada, String observacion, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.cantidadSolicitada = cantidadSolicitada;
        this.observacion = observacion;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNotaCm() {
        return notaCm;
    }

    public void setNotaCm(String notaCm) {
        this.notaCm = notaCm;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
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
        if (!(object instanceof AucHospitalizacionObjeciones)) {
            return false;
        }
        AucHospitalizacionObjeciones other = (AucHospitalizacionObjeciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionObjeciones[ id=" + id + " ]";
    }
    
}
