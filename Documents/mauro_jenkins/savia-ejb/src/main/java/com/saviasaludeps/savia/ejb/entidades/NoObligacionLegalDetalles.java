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
@Table(name = "no_obligacion_legal_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NoObligacionLegalDetalles.findAll", query = "SELECT n FROM NoObligacionLegalDetalles n"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findById", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.id = :id"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByIndicador", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.indicador = :indicador"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByTipoTecnologia", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByMaTecnologiaId", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByMaTecnologiaCodigo", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByMaTecnologiaValor", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByCodigoPropio", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.codigoPropio = :codigoPropio"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByActivo", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.activo = :activo"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByUsuarioCrea", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByTerminalCrea", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByFechaHoraCrea", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByUsuarioModifica", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByTerminalModifica", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "NoObligacionLegalDetalles.findByFechaHoraModifica", query = "SELECT n FROM NoObligacionLegalDetalles n WHERE n.fechaHoraModifica = :fechaHoraModifica")})
public class NoObligacionLegalDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "indicador")
    private String indicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Size(max = 32)
    @Column(name = "codigo_propio")
    private String codigoPropio;
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
    @JoinColumn(name = "no_obligaciones_legales_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NoObligacionesLegales noObligacionesLegalesId;

    public NoObligacionLegalDetalles() {
    }

    public NoObligacionLegalDetalles(Integer id) {
        this.id = id;
    }

    public NoObligacionLegalDetalles(Integer id, String indicador, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.indicador = indicador;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
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

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public String getCodigoPropio() {
        return codigoPropio;
    }

    public void setCodigoPropio(String codigoPropio) {
        this.codigoPropio = codigoPropio;
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

    public NoObligacionesLegales getNoObligacionesLegalesId() {
        return noObligacionesLegalesId;
    }

    public void setNoObligacionesLegalesId(NoObligacionesLegales noObligacionesLegalesId) {
        this.noObligacionesLegalesId = noObligacionesLegalesId;
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
        if (!(object instanceof NoObligacionLegalDetalles)) {
            return false;
        }
        NoObligacionLegalDetalles other = (NoObligacionLegalDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.NoObligacionLegalDetalles[ id=" + id + " ]";
    }
    
}
