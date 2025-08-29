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
@Table(name = "cntj_contrato_informe_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findAll", query = "SELECT c FROM CntjContratoInformeAdjuntos c"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findById", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByMaeTipoArchivoId", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByMaeTipoArchivoValor", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByNombre", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByRuta", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.ruta = :ruta"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByArchivo", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByExiste", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.existe = :existe"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByUsuarioCrea", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByTerminalCrea", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratoInformeAdjuntos.findByFechaHoraCrea", query = "SELECT c FROM CntjContratoInformeAdjuntos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntjContratoInformeAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_archivo_id")
    private int maeTipoArchivoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_archivo_codigo")
    private String maeTipoArchivoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_archivo_valor")
    private String maeTipoArchivoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
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
    @Column(name = "existe")
    private boolean existe;
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
    @JoinColumn(name = "cntj_contrato_informes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratoInformes cntjContratoInformesId;

    public CntjContratoInformeAdjuntos() {
    }

    public CntjContratoInformeAdjuntos(Integer id) {
        this.id = id;
    }

    public CntjContratoInformeAdjuntos(Integer id, int maeTipoArchivoId, String maeTipoArchivoCodigo, String maeTipoArchivoValor, String nombre, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoArchivoId = maeTipoArchivoId;
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
        this.maeTipoArchivoValor = maeTipoArchivoValor;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
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

    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
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

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
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

    public CntjContratoInformes getCntjContratoInformesId() {
        return cntjContratoInformesId;
    }

    public void setCntjContratoInformesId(CntjContratoInformes cntjContratoInformesId) {
        this.cntjContratoInformesId = cntjContratoInformesId;
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
        if (!(object instanceof CntjContratoInformeAdjuntos)) {
            return false;
        }
        CntjContratoInformeAdjuntos other = (CntjContratoInformeAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratoInformeAdjuntos[ id=" + id + " ]";
    }
    
}
