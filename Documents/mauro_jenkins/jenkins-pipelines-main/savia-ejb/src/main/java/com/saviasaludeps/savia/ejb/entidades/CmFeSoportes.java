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
@Table(name = "cm_fe_soportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeSoportes.findAll", query = "SELECT c FROM CmFeSoportes c"),
    @NamedQuery(name = "CmFeSoportes.findById", query = "SELECT c FROM CmFeSoportes c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeSoportes.findByMaeTipoSoporteId", query = "SELECT c FROM CmFeSoportes c WHERE c.maeTipoSoporteId = :maeTipoSoporteId"),
    @NamedQuery(name = "CmFeSoportes.findByMaeTipoSoporteCodigo", query = "SELECT c FROM CmFeSoportes c WHERE c.maeTipoSoporteCodigo = :maeTipoSoporteCodigo"),
    @NamedQuery(name = "CmFeSoportes.findByMaeTipoSoporteValor", query = "SELECT c FROM CmFeSoportes c WHERE c.maeTipoSoporteValor = :maeTipoSoporteValor"),
    @NamedQuery(name = "CmFeSoportes.findByArchivoNombre", query = "SELECT c FROM CmFeSoportes c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmFeSoportes.findByArchivoRuta", query = "SELECT c FROM CmFeSoportes c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmFeSoportes.findByArchivo", query = "SELECT c FROM CmFeSoportes c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CmFeSoportes.findByArchivoExiste", query = "SELECT c FROM CmFeSoportes c WHERE c.archivoExiste = :archivoExiste"),
    @NamedQuery(name = "CmFeSoportes.findByUsuarioCrea", query = "SELECT c FROM CmFeSoportes c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFeSoportes.findByTerminalCrea", query = "SELECT c FROM CmFeSoportes c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFeSoportes.findByFechaHoraCrea", query = "SELECT c FROM CmFeSoportes c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmFeSoportes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_soporte_id")
    private int maeTipoSoporteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_soporte_codigo")
    private String maeTipoSoporteCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_soporte_valor")
    private String maeTipoSoporteValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "archivo_existe")
    private boolean archivoExiste;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;
    @JoinColumn(name = "cm_fe_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFeRipsCargas cmFeRipsCargasId;

    public CmFeSoportes() {
    }

    public CmFeSoportes(Integer id) {
        this.id = id;
    }

    public CmFeSoportes(Integer id, int maeTipoSoporteId, String maeTipoSoporteCodigo, String maeTipoSoporteValor, String archivoNombre, String archivoRuta, String archivo, boolean archivoExiste, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoSoporteId = maeTipoSoporteId;
        this.maeTipoSoporteCodigo = maeTipoSoporteCodigo;
        this.maeTipoSoporteValor = maeTipoSoporteValor;
        this.archivoNombre = archivoNombre;
        this.archivoRuta = archivoRuta;
        this.archivo = archivo;
        this.archivoExiste = archivoExiste;
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

    public int getMaeTipoSoporteId() {
        return maeTipoSoporteId;
    }

    public void setMaeTipoSoporteId(int maeTipoSoporteId) {
        this.maeTipoSoporteId = maeTipoSoporteId;
    }

    public String getMaeTipoSoporteCodigo() {
        return maeTipoSoporteCodigo;
    }

    public void setMaeTipoSoporteCodigo(String maeTipoSoporteCodigo) {
        this.maeTipoSoporteCodigo = maeTipoSoporteCodigo;
    }

    public String getMaeTipoSoporteValor() {
        return maeTipoSoporteValor;
    }

    public void setMaeTipoSoporteValor(String maeTipoSoporteValor) {
        this.maeTipoSoporteValor = maeTipoSoporteValor;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean getArchivoExiste() {
        return archivoExiste;
    }

    public void setArchivoExiste(boolean archivoExiste) {
        this.archivoExiste = archivoExiste;
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

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
    }

    public CmFeRipsCargas getCmFeRipsCargasId() {
        return cmFeRipsCargasId;
    }

    public void setCmFeRipsCargasId(CmFeRipsCargas cmFeRipsCargasId) {
        this.cmFeRipsCargasId = cmFeRipsCargasId;
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
        if (!(object instanceof CmFeSoportes)) {
            return false;
        }
        CmFeSoportes other = (CmFeSoportes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeSoportes[ id=" + id + " ]";
    }
    
}
