/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "mp_codigo_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpCodigoInsumos.findAll", query = "SELECT m FROM MpCodigoInsumos m"),
    @NamedQuery(name = "MpCodigoInsumos.findById", query = "SELECT m FROM MpCodigoInsumos m WHERE m.id = :id"),
    @NamedQuery(name = "MpCodigoInsumos.findByCodigoMipres", query = "SELECT m FROM MpCodigoInsumos m WHERE m.codigoMipres = :codigoMipres"),
    @NamedQuery(name = "MpCodigoInsumos.findByDescripcion", query = "SELECT m FROM MpCodigoInsumos m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MpCodigoInsumos.findByActivo", query = "SELECT m FROM MpCodigoInsumos m WHERE m.activo = :activo"),
    @NamedQuery(name = "MpCodigoInsumos.findByVersionMipres", query = "SELECT m FROM MpCodigoInsumos m WHERE m.versionMipres = :versionMipres"),
    @NamedQuery(name = "MpCodigoInsumos.findByUsuarioCrea", query = "SELECT m FROM MpCodigoInsumos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpCodigoInsumos.findByTerminalCrea", query = "SELECT m FROM MpCodigoInsumos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpCodigoInsumos.findByFechaHoraCrea", query = "SELECT m FROM MpCodigoInsumos m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpCodigoInsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo_mipres")
    private String codigoMipres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "version_mipres")
    private String versionMipres;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumosMipresId", fetch = FetchType.LAZY)
    private List<MaTecnologiasMipres> maTecnologiasMipresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumosMipresId", fetch = FetchType.LAZY)
    private List<MaInsumosMipres> maInsumosMipresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpCodigoInsumosId", fetch = FetchType.LAZY)
    private List<MaPaquetesMipres> maPaquetesMipresList;

    public MpCodigoInsumos() {
    }

    public MpCodigoInsumos(Integer id) {
        this.id = id;
    }

    public MpCodigoInsumos(Integer id, String codigoMipres, String descripcion, boolean activo, String versionMipres, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigoMipres = codigoMipres;
        this.descripcion = descripcion;
        this.activo = activo;
        this.versionMipres = versionMipres;
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

    public String getCodigoMipres() {
        return codigoMipres;
    }

    public void setCodigoMipres(String codigoMipres) {
        this.codigoMipres = codigoMipres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getVersionMipres() {
        return versionMipres;
    }

    public void setVersionMipres(String versionMipres) {
        this.versionMipres = versionMipres;
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

    @XmlTransient
    public List<MaTecnologiasMipres> getMaTecnologiasMipresList() {
        return maTecnologiasMipresList;
    }

    public void setMaTecnologiasMipresList(List<MaTecnologiasMipres> maTecnologiasMipresList) {
        this.maTecnologiasMipresList = maTecnologiasMipresList;
    }

    @XmlTransient
    public List<MaInsumosMipres> getMaInsumosMipresList() {
        return maInsumosMipresList;
    }

    public void setMaInsumosMipresList(List<MaInsumosMipres> maInsumosMipresList) {
        this.maInsumosMipresList = maInsumosMipresList;
    }

    @XmlTransient
    public List<MaPaquetesMipres> getMaPaquetesMipresList() {
        return maPaquetesMipresList;
    }

    public void setMaPaquetesMipresList(List<MaPaquetesMipres> maPaquetesMipresList) {
        this.maPaquetesMipresList = maPaquetesMipresList;
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
        if (!(object instanceof MpCodigoInsumos)) {
            return false;
        }
        MpCodigoInsumos other = (MpCodigoInsumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpCodigoInsumos[ id=" + id + " ]";
    }
    
}
