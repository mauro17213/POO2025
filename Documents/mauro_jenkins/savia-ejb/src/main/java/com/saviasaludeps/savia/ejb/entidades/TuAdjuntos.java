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
@Table(name = "tu_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuAdjuntos.findAll", query = "SELECT t FROM TuAdjuntos t"),
    @NamedQuery(name = "TuAdjuntos.findById", query = "SELECT t FROM TuAdjuntos t WHERE t.id = :id"),
    @NamedQuery(name = "TuAdjuntos.findByMaeTipoAnexoId", query = "SELECT t FROM TuAdjuntos t WHERE t.maeTipoAnexoId = :maeTipoAnexoId"),
    @NamedQuery(name = "TuAdjuntos.findByMaeTipoAnexoCodigo", query = "SELECT t FROM TuAdjuntos t WHERE t.maeTipoAnexoCodigo = :maeTipoAnexoCodigo"),
    @NamedQuery(name = "TuAdjuntos.findByMaeTipoAnexoValor", query = "SELECT t FROM TuAdjuntos t WHERE t.maeTipoAnexoValor = :maeTipoAnexoValor"),
    @NamedQuery(name = "TuAdjuntos.findByNombreArchivo", query = "SELECT t FROM TuAdjuntos t WHERE t.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "TuAdjuntos.findByArchivo", query = "SELECT t FROM TuAdjuntos t WHERE t.archivo = :archivo"),
    @NamedQuery(name = "TuAdjuntos.findByRuta", query = "SELECT t FROM TuAdjuntos t WHERE t.ruta = :ruta"),
    @NamedQuery(name = "TuAdjuntos.findByObservacion", query = "SELECT t FROM TuAdjuntos t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TuAdjuntos.findByUsuarioCrea", query = "SELECT t FROM TuAdjuntos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuAdjuntos.findByTerminalCrea", query = "SELECT t FROM TuAdjuntos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuAdjuntos.findByFechaHoraCrea", query = "SELECT t FROM TuAdjuntos t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuAdjuntos.findByUsuarioModifica", query = "SELECT t FROM TuAdjuntos t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuAdjuntos.findByTerminalModifica", query = "SELECT t FROM TuAdjuntos t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuAdjuntos.findByFechaHoraModifica", query = "SELECT t FROM TuAdjuntos t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_anexo_id")
    private Integer maeTipoAnexoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_anexo_codigo")
    private String maeTipoAnexoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_anexo_valor")
    private String maeTipoAnexoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ruta")
    private String ruta;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "tu_seguimientos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TuSeguimientos tuSeguimientosId;
    @JoinColumn(name = "tu_tutela_estados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelaEstados tuTutelaEstadosId;
    @JoinColumn(name = "tu_tutela_items_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TuTutelaItems tuTutelaItemsId;

    public TuAdjuntos() {
    }

    public TuAdjuntos(Integer id) {
        this.id = id;
    }

    public TuAdjuntos(Integer id, String nombreArchivo, String archivo, String ruta, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombreArchivo = nombreArchivo;
        this.archivo = archivo;
        this.ruta = ruta;
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

    public Integer getMaeTipoAnexoId() {
        return maeTipoAnexoId;
    }

    public void setMaeTipoAnexoId(Integer maeTipoAnexoId) {
        this.maeTipoAnexoId = maeTipoAnexoId;
    }

    public String getMaeTipoAnexoCodigo() {
        return maeTipoAnexoCodigo;
    }

    public void setMaeTipoAnexoCodigo(String maeTipoAnexoCodigo) {
        this.maeTipoAnexoCodigo = maeTipoAnexoCodigo;
    }

    public String getMaeTipoAnexoValor() {
        return maeTipoAnexoValor;
    }

    public void setMaeTipoAnexoValor(String maeTipoAnexoValor) {
        this.maeTipoAnexoValor = maeTipoAnexoValor;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    public TuSeguimientos getTuSeguimientosId() {
        return tuSeguimientosId;
    }

    public void setTuSeguimientosId(TuSeguimientos tuSeguimientosId) {
        this.tuSeguimientosId = tuSeguimientosId;
    }

    public TuTutelaEstados getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstados tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    public TuTutelaItems getTuTutelaItemsId() {
        return tuTutelaItemsId;
    }

    public void setTuTutelaItemsId(TuTutelaItems tuTutelaItemsId) {
        this.tuTutelaItemsId = tuTutelaItemsId;
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
        if (!(object instanceof TuAdjuntos)) {
            return false;
        }
        TuAdjuntos other = (TuAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuAdjuntos[ id=" + id + " ]";
    }
    
}
