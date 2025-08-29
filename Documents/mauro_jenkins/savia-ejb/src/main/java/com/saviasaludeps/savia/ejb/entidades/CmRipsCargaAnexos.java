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
@Table(name = "cm_rips_carga_anexos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaAnexos.findAll", query = "SELECT c FROM CmRipsCargaAnexos c"),
    @NamedQuery(name = "CmRipsCargaAnexos.findById", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByTipoArchivo", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.tipoArchivo = :tipoArchivo"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByExiste", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.existe = :existe"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaAnexos.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaAnexos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaAnexos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipo_archivo")
    private String tipoArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
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
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsCargaAnexos() {
    }

    public CmRipsCargaAnexos(Integer id) {
        this.id = id;
    }

    public CmRipsCargaAnexos(Integer id, String tipoArchivo, String archivoNombreOriginal, String archivoRuta, String archivoNombre, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoArchivo = tipoArchivo;
        this.archivoNombreOriginal = archivoNombreOriginal;
        this.archivoRuta = archivoRuta;
        this.archivoNombre = archivoNombre;
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

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
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

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
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
        if (!(object instanceof CmRipsCargaAnexos)) {
            return false;
        }
        CmRipsCargaAnexos other = (CmRipsCargaAnexos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAnexos[ id=" + id + " ]";
    }
    
}
