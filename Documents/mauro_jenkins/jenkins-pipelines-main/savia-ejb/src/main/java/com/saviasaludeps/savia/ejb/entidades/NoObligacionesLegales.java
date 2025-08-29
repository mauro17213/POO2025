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
@Table(name = "no_obligaciones_legales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NoObligacionesLegales.findAll", query = "SELECT n FROM NoObligacionesLegales n"),
    @NamedQuery(name = "NoObligacionesLegales.findById", query = "SELECT n FROM NoObligacionesLegales n WHERE n.id = :id"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeObligacionLegalId", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeObligacionLegalId = :maeObligacionLegalId"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeObligacionLegalCodigo", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeObligacionLegalCodigo = :maeObligacionLegalCodigo"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeObligacionLegalValor", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeObligacionLegalValor = :maeObligacionLegalValor"),
    @NamedQuery(name = "NoObligacionesLegales.findByCodigo", query = "SELECT n FROM NoObligacionesLegales n WHERE n.codigo = :codigo"),
    @NamedQuery(name = "NoObligacionesLegales.findByDescripcion", query = "SELECT n FROM NoObligacionesLegales n WHERE n.descripcion = :descripcion"),
    @NamedQuery(name = "NoObligacionesLegales.findByAgnoPublicacion", query = "SELECT n FROM NoObligacionesLegales n WHERE n.agnoPublicacion = :agnoPublicacion"),
    @NamedQuery(name = "NoObligacionesLegales.findByActivo", query = "SELECT n FROM NoObligacionesLegales n WHERE n.activo = :activo"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeEntesControlId", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeEntesControlId = :maeEntesControlId"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeEntesControlCodigo", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeEntesControlCodigo = :maeEntesControlCodigo"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeEntesControlValor", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeEntesControlValor = :maeEntesControlValor"),
    @NamedQuery(name = "NoObligacionesLegales.findByAplicaDetalle", query = "SELECT n FROM NoObligacionesLegales n WHERE n.aplicaDetalle = :aplicaDetalle"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeMedioEnvioId", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeMedioEnvioId = :maeMedioEnvioId"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeMedioEnvioCodigo", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeMedioEnvioCodigo = :maeMedioEnvioCodigo"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaeMedioEnvioValor", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maeMedioEnvioValor = :maeMedioEnvioValor"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaePeriodicidadId", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maePeriodicidadId = :maePeriodicidadId"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaePeriodicidadCodigo", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maePeriodicidadCodigo = :maePeriodicidadCodigo"),
    @NamedQuery(name = "NoObligacionesLegales.findByMaePeriodicidadValor", query = "SELECT n FROM NoObligacionesLegales n WHERE n.maePeriodicidadValor = :maePeriodicidadValor"),
    @NamedQuery(name = "NoObligacionesLegales.findByScriptEjecucion", query = "SELECT n FROM NoObligacionesLegales n WHERE n.scriptEjecucion = :scriptEjecucion"),
    @NamedQuery(name = "NoObligacionesLegales.findByUsuarioCrea", query = "SELECT n FROM NoObligacionesLegales n WHERE n.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "NoObligacionesLegales.findByTerminalCrea", query = "SELECT n FROM NoObligacionesLegales n WHERE n.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "NoObligacionesLegales.findByFechaHoraCrea", query = "SELECT n FROM NoObligacionesLegales n WHERE n.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "NoObligacionesLegales.findByUsuarioModifica", query = "SELECT n FROM NoObligacionesLegales n WHERE n.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "NoObligacionesLegales.findByTerminalModifica", query = "SELECT n FROM NoObligacionesLegales n WHERE n.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "NoObligacionesLegales.findByFechaHoraModific", query = "SELECT n FROM NoObligacionesLegales n WHERE n.fechaHoraModific = :fechaHoraModific")})
public class NoObligacionesLegales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_obligacion_legal_id")
    private int maeObligacionLegalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_obligacion_legal_codigo")
    private String maeObligacionLegalCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_obligacion_legal_valor")
    private String maeObligacionLegalValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agno_publicacion")
    private int agnoPublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_entes_control_id")
    private int maeEntesControlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_entes_control_codigo")
    private String maeEntesControlCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_entes_control_valor")
    private String maeEntesControlValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_detalle")
    private boolean aplicaDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_medio_envio_id")
    private int maeMedioEnvioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_medio_envio_codigo")
    private String maeMedioEnvioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_medio_envio_valor")
    private String maeMedioEnvioValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_periodicidad_id")
    private int maePeriodicidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_periodicidad_codigo")
    private String maePeriodicidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_periodicidad_valor")
    private String maePeriodicidadValor;
    @Size(max = 128)
    @Column(name = "script_ejecucion")
    private String scriptEjecucion;
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
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modific")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModific;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noObligacionesLegalesId", fetch = FetchType.LAZY)
    private List<NoObligacionLegalDetalles> noObligacionLegalDetallesList;

    public NoObligacionesLegales() {
    }

    public NoObligacionesLegales(Integer id) {
        this.id = id;
    }

    public NoObligacionesLegales(Integer id, int maeObligacionLegalId, String maeObligacionLegalCodigo, String maeObligacionLegalValor, String codigo, String descripcion, int agnoPublicacion, int activo, int maeEntesControlId, String maeEntesControlCodigo, String maeEntesControlValor, boolean aplicaDetalle, int maeMedioEnvioId, String maeMedioEnvioCodigo, String maeMedioEnvioValor, int maePeriodicidadId, String maePeriodicidadCodigo, String maePeriodicidadValor, String usuarioCrea, String terminalCrea) {
        this.id = id;
        this.maeObligacionLegalId = maeObligacionLegalId;
        this.maeObligacionLegalCodigo = maeObligacionLegalCodigo;
        this.maeObligacionLegalValor = maeObligacionLegalValor;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.agnoPublicacion = agnoPublicacion;
        this.activo = activo;
        this.maeEntesControlId = maeEntesControlId;
        this.maeEntesControlCodigo = maeEntesControlCodigo;
        this.maeEntesControlValor = maeEntesControlValor;
        this.aplicaDetalle = aplicaDetalle;
        this.maeMedioEnvioId = maeMedioEnvioId;
        this.maeMedioEnvioCodigo = maeMedioEnvioCodigo;
        this.maeMedioEnvioValor = maeMedioEnvioValor;
        this.maePeriodicidadId = maePeriodicidadId;
        this.maePeriodicidadCodigo = maePeriodicidadCodigo;
        this.maePeriodicidadValor = maePeriodicidadValor;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeObligacionLegalId() {
        return maeObligacionLegalId;
    }

    public void setMaeObligacionLegalId(int maeObligacionLegalId) {
        this.maeObligacionLegalId = maeObligacionLegalId;
    }

    public String getMaeObligacionLegalCodigo() {
        return maeObligacionLegalCodigo;
    }

    public void setMaeObligacionLegalCodigo(String maeObligacionLegalCodigo) {
        this.maeObligacionLegalCodigo = maeObligacionLegalCodigo;
    }

    public String getMaeObligacionLegalValor() {
        return maeObligacionLegalValor;
    }

    public void setMaeObligacionLegalValor(String maeObligacionLegalValor) {
        this.maeObligacionLegalValor = maeObligacionLegalValor;
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

    public int getAgnoPublicacion() {
        return agnoPublicacion;
    }

    public void setAgnoPublicacion(int agnoPublicacion) {
        this.agnoPublicacion = agnoPublicacion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getMaeEntesControlId() {
        return maeEntesControlId;
    }

    public void setMaeEntesControlId(int maeEntesControlId) {
        this.maeEntesControlId = maeEntesControlId;
    }

    public String getMaeEntesControlCodigo() {
        return maeEntesControlCodigo;
    }

    public void setMaeEntesControlCodigo(String maeEntesControlCodigo) {
        this.maeEntesControlCodigo = maeEntesControlCodigo;
    }

    public String getMaeEntesControlValor() {
        return maeEntesControlValor;
    }

    public void setMaeEntesControlValor(String maeEntesControlValor) {
        this.maeEntesControlValor = maeEntesControlValor;
    }

    public boolean getAplicaDetalle() {
        return aplicaDetalle;
    }

    public void setAplicaDetalle(boolean aplicaDetalle) {
        this.aplicaDetalle = aplicaDetalle;
    }

    public int getMaeMedioEnvioId() {
        return maeMedioEnvioId;
    }

    public void setMaeMedioEnvioId(int maeMedioEnvioId) {
        this.maeMedioEnvioId = maeMedioEnvioId;
    }

    public String getMaeMedioEnvioCodigo() {
        return maeMedioEnvioCodigo;
    }

    public void setMaeMedioEnvioCodigo(String maeMedioEnvioCodigo) {
        this.maeMedioEnvioCodigo = maeMedioEnvioCodigo;
    }

    public String getMaeMedioEnvioValor() {
        return maeMedioEnvioValor;
    }

    public void setMaeMedioEnvioValor(String maeMedioEnvioValor) {
        this.maeMedioEnvioValor = maeMedioEnvioValor;
    }

    public int getMaePeriodicidadId() {
        return maePeriodicidadId;
    }

    public void setMaePeriodicidadId(int maePeriodicidadId) {
        this.maePeriodicidadId = maePeriodicidadId;
    }

    public String getMaePeriodicidadCodigo() {
        return maePeriodicidadCodigo;
    }

    public void setMaePeriodicidadCodigo(String maePeriodicidadCodigo) {
        this.maePeriodicidadCodigo = maePeriodicidadCodigo;
    }

    public String getMaePeriodicidadValor() {
        return maePeriodicidadValor;
    }

    public void setMaePeriodicidadValor(String maePeriodicidadValor) {
        this.maePeriodicidadValor = maePeriodicidadValor;
    }

    public String getScriptEjecucion() {
        return scriptEjecucion;
    }

    public void setScriptEjecucion(String scriptEjecucion) {
        this.scriptEjecucion = scriptEjecucion;
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

    public Date getFechaHoraModific() {
        return fechaHoraModific;
    }

    public void setFechaHoraModific(Date fechaHoraModific) {
        this.fechaHoraModific = fechaHoraModific;
    }

    @XmlTransient
    public List<NoObligacionLegalDetalles> getNoObligacionLegalDetallesList() {
        return noObligacionLegalDetallesList;
    }

    public void setNoObligacionLegalDetallesList(List<NoObligacionLegalDetalles> noObligacionLegalDetallesList) {
        this.noObligacionLegalDetallesList = noObligacionLegalDetallesList;
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
        if (!(object instanceof NoObligacionesLegales)) {
            return false;
        }
        NoObligacionesLegales other = (NoObligacionesLegales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.NoObligacionesLegales[ id=" + id + " ]";
    }
    
}
