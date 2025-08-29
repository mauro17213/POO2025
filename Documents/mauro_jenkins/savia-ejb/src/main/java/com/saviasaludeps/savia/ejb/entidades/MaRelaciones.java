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
@Table(name = "ma_relaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaRelaciones.findAll", query = "SELECT m FROM MaRelaciones m"),
    @NamedQuery(name = "MaRelaciones.findById", query = "SELECT m FROM MaRelaciones m WHERE m.id = :id"),
    @NamedQuery(name = "MaRelaciones.findByGnMaestroTipo", query = "SELECT m FROM MaRelaciones m WHERE m.gnMaestroTipo = :gnMaestroTipo"),
    @NamedQuery(name = "MaRelaciones.findByGnId", query = "SELECT m FROM MaRelaciones m WHERE m.gnId = :gnId"),
    @NamedQuery(name = "MaRelaciones.findByGnCodigo", query = "SELECT m FROM MaRelaciones m WHERE m.gnCodigo = :gnCodigo"),
    @NamedQuery(name = "MaRelaciones.findByGnValor", query = "SELECT m FROM MaRelaciones m WHERE m.gnValor = :gnValor"),
    @NamedQuery(name = "MaRelaciones.findByTipoTecnologia", query = "SELECT m FROM MaRelaciones m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MaRelaciones.findByMaId", query = "SELECT m FROM MaRelaciones m WHERE m.maId = :maId"),
    @NamedQuery(name = "MaRelaciones.findByMaCodigo", query = "SELECT m FROM MaRelaciones m WHERE m.maCodigo = :maCodigo"),
    @NamedQuery(name = "MaRelaciones.findByMaValor", query = "SELECT m FROM MaRelaciones m WHERE m.maValor = :maValor"),
    @NamedQuery(name = "MaRelaciones.findByActivo", query = "SELECT m FROM MaRelaciones m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaRelaciones.findByUsuarioCrea", query = "SELECT m FROM MaRelaciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaRelaciones.findByTerminalCrea", query = "SELECT m FROM MaRelaciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaRelaciones.findByFechaHoraCrea", query = "SELECT m FROM MaRelaciones m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaRelaciones.findByUsuarioModifica", query = "SELECT m FROM MaRelaciones m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaRelaciones.findByTerminalModifica", query = "SELECT m FROM MaRelaciones m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaRelaciones.findByFechaHoraModifica", query = "SELECT m FROM MaRelaciones m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaRelaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "gn_maestro_tipo")
    private String gnMaestroTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gn_id")
    private int gnId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "gn_codigo")
    private String gnCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "gn_valor")
    private String gnValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_id")
    private int maId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "ma_codigo")
    private String maCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_valor")
    private String maValor;
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
    @JoinColumn(name = "ma_relacion_tipos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaRelacionTipos maRelacionTiposId;

    public MaRelaciones() {
    }

    public MaRelaciones(Integer id) {
        this.id = id;
    }

    public MaRelaciones(Integer id, String gnMaestroTipo, int gnId, String gnCodigo, String gnValor, int tipoTecnologia, int maId, String maCodigo, String maValor, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.gnMaestroTipo = gnMaestroTipo;
        this.gnId = gnId;
        this.gnCodigo = gnCodigo;
        this.gnValor = gnValor;
        this.tipoTecnologia = tipoTecnologia;
        this.maId = maId;
        this.maCodigo = maCodigo;
        this.maValor = maValor;
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

    public String getGnMaestroTipo() {
        return gnMaestroTipo;
    }

    public void setGnMaestroTipo(String gnMaestroTipo) {
        this.gnMaestroTipo = gnMaestroTipo;
    }

    public int getGnId() {
        return gnId;
    }

    public void setGnId(int gnId) {
        this.gnId = gnId;
    }

    public String getGnCodigo() {
        return gnCodigo;
    }

    public void setGnCodigo(String gnCodigo) {
        this.gnCodigo = gnCodigo;
    }

    public String getGnValor() {
        return gnValor;
    }

    public void setGnValor(String gnValor) {
        this.gnValor = gnValor;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaId() {
        return maId;
    }

    public void setMaId(int maId) {
        this.maId = maId;
    }

    public String getMaCodigo() {
        return maCodigo;
    }

    public void setMaCodigo(String maCodigo) {
        this.maCodigo = maCodigo;
    }

    public String getMaValor() {
        return maValor;
    }

    public void setMaValor(String maValor) {
        this.maValor = maValor;
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

    public MaRelacionTipos getMaRelacionTiposId() {
        return maRelacionTiposId;
    }

    public void setMaRelacionTiposId(MaRelacionTipos maRelacionTiposId) {
        this.maRelacionTiposId = maRelacionTiposId;
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
        if (!(object instanceof MaRelaciones)) {
            return false;
        }
        MaRelaciones other = (MaRelaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaRelaciones[ id=" + id + " ]";
    }
    
}
