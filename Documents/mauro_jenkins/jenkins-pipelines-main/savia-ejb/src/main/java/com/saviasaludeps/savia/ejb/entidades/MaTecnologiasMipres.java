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
@Table(name = "ma_tecnologias_mipres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaTecnologiasMipres.findAll", query = "SELECT m FROM MaTecnologiasMipres m"),
    @NamedQuery(name = "MaTecnologiasMipres.findById", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.id = :id"),
    @NamedQuery(name = "MaTecnologiasMipres.findByCodigoMipres", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.codigoMipres = :codigoMipres"),
    @NamedQuery(name = "MaTecnologiasMipres.findByDescripcion", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaTecnologiasMipres.findByUsuarioCrea", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaTecnologiasMipres.findByFechaHoraCrea", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaTecnologiasMipres.findByTerminalCrea", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaTecnologiasMipres.findByUsuarioModifica", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaTecnologiasMipres.findByFechaHoraModifica", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MaTecnologiasMipres.findByTerminalModifica", query = "SELECT m FROM MaTecnologiasMipres m WHERE m.terminalModifica = :terminalModifica")})
public class MaTecnologiasMipres implements Serializable {

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
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @JoinColumn(name = "insumos_mipres_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpCodigoInsumos insumosMipresId;
    @JoinColumn(name = "ma_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaTecnologias maTecnologiasId;

    public MaTecnologiasMipres() {
    }

    public MaTecnologiasMipres(Integer id) {
        this.id = id;
    }

    public MaTecnologiasMipres(Integer id, String codigoMipres, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.codigoMipres = codigoMipres;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
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

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public MpCodigoInsumos getInsumosMipresId() {
        return insumosMipresId;
    }

    public void setInsumosMipresId(MpCodigoInsumos insumosMipresId) {
        this.insumosMipresId = insumosMipresId;
    }

    public MaTecnologias getMaTecnologiasId() {
        return maTecnologiasId;
    }

    public void setMaTecnologiasId(MaTecnologias maTecnologiasId) {
        this.maTecnologiasId = maTecnologiasId;
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
        if (!(object instanceof MaTecnologiasMipres)) {
            return false;
        }
        MaTecnologiasMipres other = (MaTecnologiasMipres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaTecnologiasMipres[ id=" + id + " ]";
    }
    
}
