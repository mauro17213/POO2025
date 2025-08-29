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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ma_paquetes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaPaquetes.findAll", query = "SELECT m FROM MaPaquetes m"),
    @NamedQuery(name = "MaPaquetes.findById", query = "SELECT m FROM MaPaquetes m WHERE m.id = :id"),
    @NamedQuery(name = "MaPaquetes.findByCodigo", query = "SELECT m FROM MaPaquetes m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaPaquetes.findByNombre", query = "SELECT m FROM MaPaquetes m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MaPaquetes.findByActivo", query = "SELECT m FROM MaPaquetes m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaPaquetes.findByIncluye", query = "SELECT m FROM MaPaquetes m WHERE m.incluye = :incluye"),
    @NamedQuery(name = "MaPaquetes.findByExcluye", query = "SELECT m FROM MaPaquetes m WHERE m.excluye = :excluye"),
    @NamedQuery(name = "MaPaquetes.findByObservacion", query = "SELECT m FROM MaPaquetes m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MaPaquetes.findByMaeAmbitoId", query = "SELECT m FROM MaPaquetes m WHERE m.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "MaPaquetes.findByMaeAmbitoCodigo", query = "SELECT m FROM MaPaquetes m WHERE m.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "MaPaquetes.findByMaeAmbitoValor", query = "SELECT m FROM MaPaquetes m WHERE m.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "MaPaquetes.findByRequisitosTecnicos", query = "SELECT m FROM MaPaquetes m WHERE m.requisitosTecnicos = :requisitosTecnicos"),
    @NamedQuery(name = "MaPaquetes.findByEsAtoCosto", query = "SELECT m FROM MaPaquetes m WHERE m.esAtoCosto = :esAtoCosto"),
    @NamedQuery(name = "MaPaquetes.findByTipoTecnologia", query = "SELECT m FROM MaPaquetes m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MaPaquetes.findByUsuarioCrea", query = "SELECT m FROM MaPaquetes m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaPaquetes.findByTerminalCrea", query = "SELECT m FROM MaPaquetes m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaPaquetes.findByFechaHoraCrea", query = "SELECT m FROM MaPaquetes m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaPaquetes.findByUsuarioModifica", query = "SELECT m FROM MaPaquetes m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaPaquetes.findByTerminalModifica", query = "SELECT m FROM MaPaquetes m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaPaquetes.findByFechaHoraModifica", query = "SELECT m FROM MaPaquetes m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaPaquetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 1024)
    @Column(name = "incluye")
    private String incluye;
    @Size(max = 1024)
    @Column(name = "excluye")
    private String excluye;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "mae_ambito_id")
    private Integer maeAmbitoId;
    @Size(max = 8)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Size(max = 1024)
    @Column(name = "requisitos_tecnicos")
    private String requisitosTecnicos;
    @Column(name = "es_ato_costo")
    private Boolean esAtoCosto;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maPaquetesId", fetch = FetchType.LAZY)
    private List<MaPaquetesMipres> maPaquetesMipresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maPaquetesId", fetch = FetchType.LAZY)
    private List<MaPaqueteTecnologias> maPaqueteTecnologiasList;
    @JoinColumn(name = "ma_insumos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaInsumos maInsumosId;
    @JoinColumn(name = "ma_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaMedicamentos maMedicamentosId;
    @JoinColumn(name = "ma_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaTecnologias maTecnologiasId;

    public MaPaquetes() {
    }

    public MaPaquetes(Integer id) {
        this.id = id;
    }

    public MaPaquetes(Integer id, String codigo, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getExcluye() {
        return excluye;
    }

    public void setExcluye(String excluye) {
        this.excluye = excluye;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public String getRequisitosTecnicos() {
        return requisitosTecnicos;
    }

    public void setRequisitosTecnicos(String requisitosTecnicos) {
        this.requisitosTecnicos = requisitosTecnicos;
    }

    public Boolean getEsAtoCosto() {
        return esAtoCosto;
    }

    public void setEsAtoCosto(Boolean esAtoCosto) {
        this.esAtoCosto = esAtoCosto;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
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
    public List<MaPaquetesMipres> getMaPaquetesMipresList() {
        return maPaquetesMipresList;
    }

    public void setMaPaquetesMipresList(List<MaPaquetesMipres> maPaquetesMipresList) {
        this.maPaquetesMipresList = maPaquetesMipresList;
    }

    @XmlTransient
    public List<MaPaqueteTecnologias> getMaPaqueteTecnologiasList() {
        return maPaqueteTecnologiasList;
    }

    public void setMaPaqueteTecnologiasList(List<MaPaqueteTecnologias> maPaqueteTecnologiasList) {
        this.maPaqueteTecnologiasList = maPaqueteTecnologiasList;
    }

    public MaInsumos getMaInsumosId() {
        return maInsumosId;
    }

    public void setMaInsumosId(MaInsumos maInsumosId) {
        this.maInsumosId = maInsumosId;
    }

    public MaMedicamentos getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(MaMedicamentos maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
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
        if (!(object instanceof MaPaquetes)) {
            return false;
        }
        MaPaquetes other = (MaPaquetes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaPaquetes[ id=" + id + " ]";
    }
    
}
