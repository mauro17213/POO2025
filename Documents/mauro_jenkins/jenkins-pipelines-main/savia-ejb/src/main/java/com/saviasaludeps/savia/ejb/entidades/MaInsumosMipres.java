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
@Table(name = "ma_insumos_mipres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaInsumosMipres.findAll", query = "SELECT m FROM MaInsumosMipres m"),
    @NamedQuery(name = "MaInsumosMipres.findById", query = "SELECT m FROM MaInsumosMipres m WHERE m.id = :id"),
    @NamedQuery(name = "MaInsumosMipres.findByCodigoMipres", query = "SELECT m FROM MaInsumosMipres m WHERE m.codigoMipres = :codigoMipres"),
    @NamedQuery(name = "MaInsumosMipres.findByDescripcionMipres", query = "SELECT m FROM MaInsumosMipres m WHERE m.descripcionMipres = :descripcionMipres"),
    @NamedQuery(name = "MaInsumosMipres.findByUsuarioCrea", query = "SELECT m FROM MaInsumosMipres m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaInsumosMipres.findByFechaHoraCrea", query = "SELECT m FROM MaInsumosMipres m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaInsumosMipres.findByTerminalCrea", query = "SELECT m FROM MaInsumosMipres m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaInsumosMipres.findByUsuarioModifica", query = "SELECT m FROM MaInsumosMipres m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaInsumosMipres.findByFechaHoraModifica", query = "SELECT m FROM MaInsumosMipres m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MaInsumosMipres.findByTerminalModifica", query = "SELECT m FROM MaInsumosMipres m WHERE m.terminalModifica = :terminalModifica")})
public class MaInsumosMipres implements Serializable {

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
    @Column(name = "descripcion_mipres")
    private String descripcionMipres;
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
    @JoinColumn(name = "ma_insumos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaInsumos maInsumosId;

    public MaInsumosMipres() {
    }

    public MaInsumosMipres(Integer id) {
        this.id = id;
    }

    public MaInsumosMipres(Integer id, String codigoMipres, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
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

    public String getDescripcionMipres() {
        return descripcionMipres;
    }

    public void setDescripcionMipres(String descripcionMipres) {
        this.descripcionMipres = descripcionMipres;
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

    public MaInsumos getMaInsumosId() {
        return maInsumosId;
    }

    public void setMaInsumosId(MaInsumos maInsumosId) {
        this.maInsumosId = maInsumosId;
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
        if (!(object instanceof MaInsumosMipres)) {
            return false;
        }
        MaInsumosMipres other = (MaInsumosMipres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaInsumosMipres[ id=" + id + " ]";
    }
    
}
