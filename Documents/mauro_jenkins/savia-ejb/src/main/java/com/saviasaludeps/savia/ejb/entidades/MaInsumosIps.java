/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "ma_insumos_ips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaInsumosIps.findAll", query = "SELECT m FROM MaInsumosIps m"),
    @NamedQuery(name = "MaInsumosIps.findById", query = "SELECT m FROM MaInsumosIps m WHERE m.id = :id"),
    @NamedQuery(name = "MaInsumosIps.findByCntPrestadorSedesId", query = "SELECT m FROM MaInsumosIps m WHERE m.cntPrestadorSedesId = :cntPrestadorSedesId"),
    @NamedQuery(name = "MaInsumosIps.findByMaeTipoId", query = "SELECT m FROM MaInsumosIps m WHERE m.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "MaInsumosIps.findByMaeTipoCodigo", query = "SELECT m FROM MaInsumosIps m WHERE m.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "MaInsumosIps.findByMaeTipoValor", query = "SELECT m FROM MaInsumosIps m WHERE m.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "MaInsumosIps.findByCodigo", query = "SELECT m FROM MaInsumosIps m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaInsumosIps.findByDescripcion", query = "SELECT m FROM MaInsumosIps m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaInsumosIps.findByAbreviatura", query = "SELECT m FROM MaInsumosIps m WHERE m.abreviatura = :abreviatura"),
    @NamedQuery(name = "MaInsumosIps.findByActivo", query = "SELECT m FROM MaInsumosIps m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaInsumosIps.findByUsuarioCrea", query = "SELECT m FROM MaInsumosIps m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaInsumosIps.findByTerminalCrea", query = "SELECT m FROM MaInsumosIps m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaInsumosIps.findByFechaHoraCrea", query = "SELECT m FROM MaInsumosIps m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaInsumosIps.findByUsuarioModifica", query = "SELECT m FROM MaInsumosIps m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaInsumosIps.findByTerminalModifica", query = "SELECT m FROM MaInsumosIps m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaInsumosIps.findByFechaHoraModifica", query = "SELECT m FROM MaInsumosIps m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaInsumosIps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_prestador_sedes_id")
    private int cntPrestadorSedesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_id")
    private int maeTipoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_codigo")
    private String maeTipoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_valor")
    private String maeTipoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 32)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @OneToMany(mappedBy = "maInsumosIpsId", fetch = FetchType.LAZY)
    private List<MaPaqueteTecnologias> maPaqueteTecnologiasList;

    public MaInsumosIps() {
    }

    public MaInsumosIps(Integer id) {
        this.id = id;
    }

    public MaInsumosIps(Integer id, int cntPrestadorSedesId, int maeTipoId, String codigo, String descripcion, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.cntPrestadorSedesId = cntPrestadorSedesId;
        this.maeTipoId = maeTipoId;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.activo = activo;
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

    public int getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(int cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    @XmlTransient
    public List<MaPaqueteTecnologias> getMaPaqueteTecnologiasList() {
        return maPaqueteTecnologiasList;
    }

    public void setMaPaqueteTecnologiasList(List<MaPaqueteTecnologias> maPaqueteTecnologiasList) {
        this.maPaqueteTecnologiasList = maPaqueteTecnologiasList;
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
        if (!(object instanceof MaInsumosIps)) {
            return false;
        }
        MaInsumosIps other = (MaInsumosIps) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaInsumosIps[ id=" + id + " ]";
    }
    
}
