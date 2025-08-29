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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cntj_otrosi_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjOtrosiAdjuntos.findAll", query = "SELECT c FROM CntjOtrosiAdjuntos c"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findById", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByMaeTipoArchivoId", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByMaeTipoArchivoValor", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByNombre", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByRuta", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.ruta = :ruta"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByArchivo", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByExiste", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.existe = :existe"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByUsuarioCrea", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByTerminalCrea", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjOtrosiAdjuntos.findByFechaHoraCrea", query = "SELECT c FROM CntjOtrosiAdjuntos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntjOtrosiAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_archivo_id")
    private Integer maeTipoArchivoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_archivo_codigo")
    private String maeTipoArchivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_archivo_valor")
    private String maeTipoArchivoValor;
    @Size(max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Column(name = "existe")
    private Boolean existe;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cntj_otrosies_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjOtrosies cntjOtrosiesId;

    public CntjOtrosiAdjuntos() {
    }

    public CntjOtrosiAdjuntos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(Integer maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    public String getMaeTipoArchivoCodigo() {
        return maeTipoArchivoCodigo;
    }

    public void setMaeTipoArchivoCodigo(String maeTipoArchivoCodigo) {
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
    }

    public String getMaeTipoArchivoValor() {
        return maeTipoArchivoValor;
    }

    public void setMaeTipoArchivoValor(String maeTipoArchivoValor) {
        this.maeTipoArchivoValor = maeTipoArchivoValor;
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

    public Boolean getExiste() {
        return existe;
    }

    public void setExiste(Boolean existe) {
        this.existe = existe;
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

    public CntjOtrosies getCntjOtrosiesId() {
        return cntjOtrosiesId;
    }

    public void setCntjOtrosiesId(CntjOtrosies cntjOtrosiesId) {
        this.cntjOtrosiesId = cntjOtrosiesId;
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
        if (!(object instanceof CntjOtrosiAdjuntos)) {
            return false;
        }
        CntjOtrosiAdjuntos other = (CntjOtrosiAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjOtrosiAdjuntos[ id=" + id + " ]";
    }
    
}
