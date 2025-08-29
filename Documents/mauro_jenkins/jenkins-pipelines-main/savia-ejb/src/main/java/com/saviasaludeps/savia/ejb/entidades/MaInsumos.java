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
@Table(name = "ma_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaInsumos.findAll", query = "SELECT m FROM MaInsumos m"),
    @NamedQuery(name = "MaInsumos.findById", query = "SELECT m FROM MaInsumos m WHERE m.id = :id"),
    @NamedQuery(name = "MaInsumos.findByMaeTipoId", query = "SELECT m FROM MaInsumos m WHERE m.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "MaInsumos.findByMaeTipoCodigo", query = "SELECT m FROM MaInsumos m WHERE m.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "MaInsumos.findByMaeTipoValor", query = "SELECT m FROM MaInsumos m WHERE m.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "MaInsumos.findByAutomatico", query = "SELECT m FROM MaInsumos m WHERE m.automatico = :automatico"),
    @NamedQuery(name = "MaInsumos.findByCodigoHabilitacion", query = "SELECT m FROM MaInsumos m WHERE m.codigoHabilitacion = :codigoHabilitacion"),
    @NamedQuery(name = "MaInsumos.findByCodigo", query = "SELECT m FROM MaInsumos m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaInsumos.findByDescripcion", query = "SELECT m FROM MaInsumos m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaInsumos.findByAbreviatura", query = "SELECT m FROM MaInsumos m WHERE m.abreviatura = :abreviatura"),
    @NamedQuery(name = "MaInsumos.findByActivo", query = "SELECT m FROM MaInsumos m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaInsumos.findByCobertura", query = "SELECT m FROM MaInsumos m WHERE m.cobertura = :cobertura"),
    @NamedQuery(name = "MaInsumos.findByUsuarioCrea", query = "SELECT m FROM MaInsumos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaInsumos.findByTerminalCrea", query = "SELECT m FROM MaInsumos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaInsumos.findByFechaHoraCrea", query = "SELECT m FROM MaInsumos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaInsumos.findByUsuarioModifica", query = "SELECT m FROM MaInsumos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaInsumos.findByTerminalModifica", query = "SELECT m FROM MaInsumos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaInsumos.findByFechaHoraModifica", query = "SELECT m FROM MaInsumos m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaInsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Column(name = "automatico")
    private boolean automatico;
    @Size(max = 16)
    @Column(name = "codigo_habilitacion")
    private String codigoHabilitacion;
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
    @Column(name = "cobertura")
    private int cobertura;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maInsumosId", fetch = FetchType.LAZY)
    private List<MaInsumosMipres> maInsumosMipresList;
    @OneToMany(mappedBy = "maInsumosId", fetch = FetchType.LAZY)
    private List<MaPaquetes> maPaquetesList;

    public MaInsumos() {
    }

    public MaInsumos(Integer id) {
        this.id = id;
    }

    public MaInsumos(Integer id, int maeTipoId, boolean automatico, String codigo, String descripcion, boolean activo, int cobertura, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoId = maeTipoId;
        this.automatico = automatico;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.activo = activo;
        this.cobertura = cobertura;
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

    public boolean getAutomatico() {
        return automatico;
    }

    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
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

    public int getCobertura() {
        return cobertura;
    }

    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
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
    public List<MaInsumosMipres> getMaInsumosMipresList() {
        return maInsumosMipresList;
    }

    public void setMaInsumosMipresList(List<MaInsumosMipres> maInsumosMipresList) {
        this.maInsumosMipresList = maInsumosMipresList;
    }

    @XmlTransient
    public List<MaPaquetes> getMaPaquetesList() {
        return maPaquetesList;
    }

    public void setMaPaquetesList(List<MaPaquetes> maPaquetesList) {
        this.maPaquetesList = maPaquetesList;
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
        if (!(object instanceof MaInsumos)) {
            return false;
        }
        MaInsumos other = (MaInsumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaInsumos[ id=" + id + " ]";
    }
    
}
