/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ant_anticipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntAnticipos.findAll", query = "SELECT a FROM AntAnticipos a"),
    @NamedQuery(name = "AntAnticipos.findById", query = "SELECT a FROM AntAnticipos a WHERE a.id = :id"),
    @NamedQuery(name = "AntAnticipos.findByEstado", query = "SELECT a FROM AntAnticipos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AntAnticipos.findByTipo", query = "SELECT a FROM AntAnticipos a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AntAnticipos.findByPbs", query = "SELECT a FROM AntAnticipos a WHERE a.pbs = :pbs"),
    @NamedQuery(name = "AntAnticipos.findByMaeClasificacionId", query = "SELECT a FROM AntAnticipos a WHERE a.maeClasificacionId = :maeClasificacionId"),
    @NamedQuery(name = "AntAnticipos.findByMaeClasificacionCodigo", query = "SELECT a FROM AntAnticipos a WHERE a.maeClasificacionCodigo = :maeClasificacionCodigo"),
    @NamedQuery(name = "AntAnticipos.findByMaeClasificacionValor", query = "SELECT a FROM AntAnticipos a WHERE a.maeClasificacionValor = :maeClasificacionValor"),
    @NamedQuery(name = "AntAnticipos.findByMaeClasificacionTipo", query = "SELECT a FROM AntAnticipos a WHERE a.maeClasificacionTipo = :maeClasificacionTipo"),
    @NamedQuery(name = "AntAnticipos.findByJustificacion", query = "SELECT a FROM AntAnticipos a WHERE a.justificacion = :justificacion"),
    @NamedQuery(name = "AntAnticipos.findByValorCotizado", query = "SELECT a FROM AntAnticipos a WHERE a.valorCotizado = :valorCotizado"),
    @NamedQuery(name = "AntAnticipos.findByValorPagado", query = "SELECT a FROM AntAnticipos a WHERE a.valorPagado = :valorPagado"),
    @NamedQuery(name = "AntAnticipos.findByValorDisponible", query = "SELECT a FROM AntAnticipos a WHERE a.valorDisponible = :valorDisponible"),
    @NamedQuery(name = "AntAnticipos.findByRetencionSugerida", query = "SELECT a FROM AntAnticipos a WHERE a.retencionSugerida = :retencionSugerida"),
    @NamedQuery(name = "AntAnticipos.findByClasificacionObservacion", query = "SELECT a FROM AntAnticipos a WHERE a.clasificacionObservacion = :clasificacionObservacion"),
    @NamedQuery(name = "AntAnticipos.findByMaDiagnosticoId", query = "SELECT a FROM AntAnticipos a WHERE a.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "AntAnticipos.findByMaDiagnosticoCodigo", query = "SELECT a FROM AntAnticipos a WHERE a.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "AntAnticipos.findByMaDiagnosticoValor", query = "SELECT a FROM AntAnticipos a WHERE a.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "AntAnticipos.findByCodigoContabilizacionSap", query = "SELECT a FROM AntAnticipos a WHERE a.codigoContabilizacionSap = :codigoContabilizacionSap"),
    @NamedQuery(name = "AntAnticipos.findByCodigoCompensacionSap", query = "SELECT a FROM AntAnticipos a WHERE a.codigoCompensacionSap = :codigoCompensacionSap"),
    @NamedQuery(name = "AntAnticipos.findByRetencionAplicada", query = "SELECT a FROM AntAnticipos a WHERE a.retencionAplicada = :retencionAplicada"),
    @NamedQuery(name = "AntAnticipos.findByAplicaRetencion", query = "SELECT a FROM AntAnticipos a WHERE a.aplicaRetencion = :aplicaRetencion"),
    @NamedQuery(name = "AntAnticipos.findByUsuarioCrea", query = "SELECT a FROM AntAnticipos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AntAnticipos.findByTerminalCrea", query = "SELECT a FROM AntAnticipos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AntAnticipos.findByFechaHoraCrea", query = "SELECT a FROM AntAnticipos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AntAnticipos.findByUsuarioModifica", query = "SELECT a FROM AntAnticipos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AntAnticipos.findByTerminalModifica", query = "SELECT a FROM AntAnticipos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AntAnticipos.findByFechaHoraModifica", query = "SELECT a FROM AntAnticipos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AntAnticipos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "tipo")
    private Integer tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs")
    private boolean pbs;
    @Column(name = "mae_clasificacion_id")
    private Integer maeClasificacionId;
    @Size(max = 8)
    @Column(name = "mae_clasificacion_codigo")
    private String maeClasificacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_clasificacion_valor")
    private String maeClasificacionValor;
    @Size(max = 4)
    @Column(name = "mae_clasificacion_tipo")
    private String maeClasificacionTipo;
    @Size(max = 2048)
    @Column(name = "justificacion")
    private String justificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_cotizado")
    private BigDecimal valorCotizado;
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Column(name = "valor_disponible")
    private BigDecimal valorDisponible;
    @Column(name = "retencion_sugerida")
    private BigDecimal retencionSugerida;
    @Size(max = 1024)
    @Column(name = "clasificacion_observacion")
    private String clasificacionObservacion;
    @Column(name = "ma_diagnostico_id")
    private Integer maDiagnosticoId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
    @Size(max = 256)
    @Column(name = "codigo_contabilizacion_sap")
    private String codigoContabilizacionSap;
    @Size(max = 256)
    @Column(name = "codigo_compensacion_sap")
    private String codigoCompensacionSap;
    @Column(name = "retencion_aplicada")
    private BigDecimal retencionAplicada;
    @Column(name = "aplica_retencion")
    private Boolean aplicaRetencion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antAnticiposId", fetch = FetchType.LAZY)
    private List<AntAnticipoAdjuntos> antAnticipoAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antAnticiposId", fetch = FetchType.LAZY)
    private List<AntAnticipoGestiones> antAnticipoGestionesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antAnticiposId", fetch = FetchType.LAZY)
    private List<AntAnticipoItems> antAnticipoItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antAnticiposId", fetch = FetchType.LAZY)
    private List<AntAnticipoValores> antAnticipoValoresList;

    public AntAnticipos() {
    }

    public AntAnticipos(Integer id) {
        this.id = id;
    }

    public AntAnticipos(Integer id, boolean pbs, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.pbs = pbs;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public boolean getPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public Integer getMaeClasificacionId() {
        return maeClasificacionId;
    }

    public void setMaeClasificacionId(Integer maeClasificacionId) {
        this.maeClasificacionId = maeClasificacionId;
    }

    public String getMaeClasificacionCodigo() {
        return maeClasificacionCodigo;
    }

    public void setMaeClasificacionCodigo(String maeClasificacionCodigo) {
        this.maeClasificacionCodigo = maeClasificacionCodigo;
    }

    public String getMaeClasificacionValor() {
        return maeClasificacionValor;
    }

    public void setMaeClasificacionValor(String maeClasificacionValor) {
        this.maeClasificacionValor = maeClasificacionValor;
    }

    public String getMaeClasificacionTipo() {
        return maeClasificacionTipo;
    }

    public void setMaeClasificacionTipo(String maeClasificacionTipo) {
        this.maeClasificacionTipo = maeClasificacionTipo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public BigDecimal getValorCotizado() {
        return valorCotizado;
    }

    public void setValorCotizado(BigDecimal valorCotizado) {
        this.valorCotizado = valorCotizado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorDisponible() {
        return valorDisponible;
    }

    public void setValorDisponible(BigDecimal valorDisponible) {
        this.valorDisponible = valorDisponible;
    }

    public BigDecimal getRetencionSugerida() {
        return retencionSugerida;
    }

    public void setRetencionSugerida(BigDecimal retencionSugerida) {
        this.retencionSugerida = retencionSugerida;
    }

    public String getClasificacionObservacion() {
        return clasificacionObservacion;
    }

    public void setClasificacionObservacion(String clasificacionObservacion) {
        this.clasificacionObservacion = clasificacionObservacion;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public String getCodigoContabilizacionSap() {
        return codigoContabilizacionSap;
    }

    public void setCodigoContabilizacionSap(String codigoContabilizacionSap) {
        this.codigoContabilizacionSap = codigoContabilizacionSap;
    }

    public String getCodigoCompensacionSap() {
        return codigoCompensacionSap;
    }

    public void setCodigoCompensacionSap(String codigoCompensacionSap) {
        this.codigoCompensacionSap = codigoCompensacionSap;
    }

    public BigDecimal getRetencionAplicada() {
        return retencionAplicada;
    }

    public void setRetencionAplicada(BigDecimal retencionAplicada) {
        this.retencionAplicada = retencionAplicada;
    }

    public Boolean getAplicaRetencion() {
        return aplicaRetencion;
    }

    public void setAplicaRetencion(Boolean aplicaRetencion) {
        this.aplicaRetencion = aplicaRetencion;
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
    public List<AntAnticipoAdjuntos> getAntAnticipoAdjuntosList() {
        return antAnticipoAdjuntosList;
    }

    public void setAntAnticipoAdjuntosList(List<AntAnticipoAdjuntos> antAnticipoAdjuntosList) {
        this.antAnticipoAdjuntosList = antAnticipoAdjuntosList;
    }

    @XmlTransient
    public List<AntAnticipoGestiones> getAntAnticipoGestionesList() {
        return antAnticipoGestionesList;
    }

    public void setAntAnticipoGestionesList(List<AntAnticipoGestiones> antAnticipoGestionesList) {
        this.antAnticipoGestionesList = antAnticipoGestionesList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    @XmlTransient
    public List<AntAnticipoItems> getAntAnticipoItemsList() {
        return antAnticipoItemsList;
    }

    public void setAntAnticipoItemsList(List<AntAnticipoItems> antAnticipoItemsList) {
        this.antAnticipoItemsList = antAnticipoItemsList;
    }

    @XmlTransient
    public List<AntAnticipoValores> getAntAnticipoValoresList() {
        return antAnticipoValoresList;
    }

    public void setAntAnticipoValoresList(List<AntAnticipoValores> antAnticipoValoresList) {
        this.antAnticipoValoresList = antAnticipoValoresList;
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
        if (!(object instanceof AntAnticipos)) {
            return false;
        }
        AntAnticipos other = (AntAnticipos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AntAnticipos[ id=" + id + " ]";
    }
    
}
