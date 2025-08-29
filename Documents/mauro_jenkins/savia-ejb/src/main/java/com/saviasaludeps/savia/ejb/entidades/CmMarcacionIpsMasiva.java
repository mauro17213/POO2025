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
@Table(name = "cm_marcacion_ips_masiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmMarcacionIpsMasiva.findAll", query = "SELECT c FROM CmMarcacionIpsMasiva c"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findById", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.id = :id"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByNombre", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByRuta", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.ruta = :ruta"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByArchivo", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByEstado", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByRegistros", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.registros = :registros"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByExitosos", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.exitosos = :exitosos"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByFallidos", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.fallidos = :fallidos"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByFechaHoraInicio", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByFechaHoraFin", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByUsuarioCrea", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByTerminalCrea", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmMarcacionIpsMasiva.findByFechaHoraCrea", query = "SELECT c FROM CmMarcacionIpsMasiva c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmMarcacionIpsMasiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "registros")
    private Integer registros;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
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
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public CmMarcacionIpsMasiva() {
    }

    public CmMarcacionIpsMasiva(Integer id) {
        this.id = id;
    }

    public CmMarcacionIpsMasiva(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    public Integer getExitosos() {
        return exitosos;
    }

    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    public Integer getFallidos() {
        return fallidos;
    }

    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
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
        if (!(object instanceof CmMarcacionIpsMasiva)) {
            return false;
        }
        CmMarcacionIpsMasiva other = (CmMarcacionIpsMasiva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmMarcacionIpsMasiva[ id=" + id + " ]";
    }
    
}
