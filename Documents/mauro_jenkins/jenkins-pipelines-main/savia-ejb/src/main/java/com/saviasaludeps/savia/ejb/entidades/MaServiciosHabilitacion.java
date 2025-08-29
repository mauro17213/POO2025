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
@Table(name = "ma_servicios_habilitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaServiciosHabilitacion.findAll", query = "SELECT m FROM MaServiciosHabilitacion m"),
    @NamedQuery(name = "MaServiciosHabilitacion.findById", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.id = :id"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByCodigo", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByNombre", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByActivo", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByMaeGrupoId", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.maeGrupoId = :maeGrupoId"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByMaeGrupoCodigo", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.maeGrupoCodigo = :maeGrupoCodigo"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByMaeGrupoValor", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.maeGrupoValor = :maeGrupoValor"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByUsuarioCrea", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByTerminalCrea", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByFechaHoraCrea", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByUsuarioModifica", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByTerminalModifica", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaServiciosHabilitacion.findByFechaHoraModifica", query = "SELECT m FROM MaServiciosHabilitacion m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaServiciosHabilitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "mae_grupo_id")
    private Integer maeGrupoId;
    @Size(max = 8)
    @Column(name = "mae_grupo_codigo")
    private String maeGrupoCodigo;
    @Size(max = 128)
    @Column(name = "mae_grupo_valor")
    private String maeGrupoValor;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maServiciosHabilitacionId", fetch = FetchType.LAZY)
    private List<CntPrestadorSedeServiciosHabilitacion> cntPrestadorSedeServiciosHabilitacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maServiciosHabilitacionId", fetch = FetchType.LAZY)
    private List<MaTecnologiaServiciosHabilitacion> maTecnologiaServiciosHabilitacionList;

    public MaServiciosHabilitacion() {
    }

    public MaServiciosHabilitacion(Integer id) {
        this.id = id;
    }

    public MaServiciosHabilitacion(Integer id, int codigo, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeGrupoId() {
        return maeGrupoId;
    }

    public void setMaeGrupoId(Integer maeGrupoId) {
        this.maeGrupoId = maeGrupoId;
    }

    public String getMaeGrupoCodigo() {
        return maeGrupoCodigo;
    }

    public void setMaeGrupoCodigo(String maeGrupoCodigo) {
        this.maeGrupoCodigo = maeGrupoCodigo;
    }

    public String getMaeGrupoValor() {
        return maeGrupoValor;
    }

    public void setMaeGrupoValor(String maeGrupoValor) {
        this.maeGrupoValor = maeGrupoValor;
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
    public List<CntPrestadorSedeServiciosHabilitacion> getCntPrestadorSedeServiciosHabilitacionList() {
        return cntPrestadorSedeServiciosHabilitacionList;
    }

    public void setCntPrestadorSedeServiciosHabilitacionList(List<CntPrestadorSedeServiciosHabilitacion> cntPrestadorSedeServiciosHabilitacionList) {
        this.cntPrestadorSedeServiciosHabilitacionList = cntPrestadorSedeServiciosHabilitacionList;
    }

    @XmlTransient
    public List<MaTecnologiaServiciosHabilitacion> getMaTecnologiaServiciosHabilitacionList() {
        return maTecnologiaServiciosHabilitacionList;
    }

    public void setMaTecnologiaServiciosHabilitacionList(List<MaTecnologiaServiciosHabilitacion> maTecnologiaServiciosHabilitacionList) {
        this.maTecnologiaServiciosHabilitacionList = maTecnologiaServiciosHabilitacionList;
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
        if (!(object instanceof MaServiciosHabilitacion)) {
            return false;
        }
        MaServiciosHabilitacion other = (MaServiciosHabilitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaServiciosHabilitacion[ id=" + id + " ]";
    }
    
}
