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
@Table(name = "gj_proceso_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjProcesoAdjuntos.findAll", query = "SELECT g FROM GjProcesoAdjuntos g"),
    @NamedQuery(name = "GjProcesoAdjuntos.findById", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.id = :id"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByNombreArchivo", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByRuta", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.ruta = :ruta"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByArchivo", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.archivo = :archivo"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByMaeTipoId", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByMaeTipoCodigo", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByMaeTipoValor", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByUsuarioCrea", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByTerminalCrea", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjProcesoAdjuntos.findByFechaHoraCrea", query = "SELECT g FROM GjProcesoAdjuntos g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GjProcesoAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_id")
    private int maeTipoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_codigo")
    private String maeTipoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_valor")
    private String maeTipoValor;
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
    @JoinColumn(name = "gj_proceso_historicos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GjProcesoHistoricos gjProcesoHistoricosId;
    @JoinColumn(name = "gj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjProcesos gjProcesosId;

    public GjProcesoAdjuntos() {
    }

    public GjProcesoAdjuntos(Integer id) {
        this.id = id;
    }

    public GjProcesoAdjuntos(Integer id, String nombreArchivo, String ruta, String archivo, int maeTipoId, String maeTipoCodigo, String maeTipoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.archivo = archivo;
        this.maeTipoId = maeTipoId;
        this.maeTipoCodigo = maeTipoCodigo;
        this.maeTipoValor = maeTipoValor;
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
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

    public GjProcesoHistoricos getGjProcesoHistoricosId() {
        return gjProcesoHistoricosId;
    }

    public void setGjProcesoHistoricosId(GjProcesoHistoricos gjProcesoHistoricosId) {
        this.gjProcesoHistoricosId = gjProcesoHistoricosId;
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
        if (!(object instanceof GjProcesoAdjuntos)) {
            return false;
        }
        GjProcesoAdjuntos other = (GjProcesoAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjProcesoAdjuntos[ id=" + id + " ]";
    }
    
}
