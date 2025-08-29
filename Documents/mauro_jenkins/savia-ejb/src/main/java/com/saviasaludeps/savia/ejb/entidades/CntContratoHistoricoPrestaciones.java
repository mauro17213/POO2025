/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cnt_contrato_historico_prestaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findAll", query = "SELECT c FROM CntContratoHistoricoPrestaciones c"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByIdcntContratoPrestacion", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.idcntContratoPrestacion = :idcntContratoPrestacion"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByTipo", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByAutorizacion", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByValor", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.valor = :valor"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByTipoTecnologia", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByMaTecnologiaId", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByMaTecnologiaCodigo", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByMaTecnologiaValor", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByFechaHoraPrestacion", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.fechaHoraPrestacion = :fechaHoraPrestacion"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByAnulado", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.anulado = :anulado"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByUsuarioCrea", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByTerminalCrea", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByFechaHoraCrea", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByUsuarioModifica", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByTerminalModifica", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntContratoHistoricoPrestaciones.findByFechaHoraModifica", query = "SELECT c FROM CntContratoHistoricoPrestaciones c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntContratoHistoricoPrestaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcnt_contrato_prestacion")
    private Integer idcntContratoPrestacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
    @Size(max = 8)
    @Column(name = "autorizacion")
    private String autorizacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "tipo_tecnologia")
    private Boolean tipoTecnologia;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_prestacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraPrestacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulado")
    private boolean anulado;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;
    @JoinColumn(name = "cnt_contrato_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratoSedes cntContratoSedesId;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public CntContratoHistoricoPrestaciones() {
    }

    public CntContratoHistoricoPrestaciones(Integer idcntContratoPrestacion) {
        this.idcntContratoPrestacion = idcntContratoPrestacion;
    }

    public CntContratoHistoricoPrestaciones(Integer idcntContratoPrestacion, boolean tipo, BigDecimal valor, Date fechaHoraPrestacion, boolean anulado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.idcntContratoPrestacion = idcntContratoPrestacion;
        this.tipo = tipo;
        this.valor = valor;
        this.fechaHoraPrestacion = fechaHoraPrestacion;
        this.anulado = anulado;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getIdcntContratoPrestacion() {
        return idcntContratoPrestacion;
    }

    public void setIdcntContratoPrestacion(Integer idcntContratoPrestacion) {
        this.idcntContratoPrestacion = idcntContratoPrestacion;
    }

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Boolean tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
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

    public Date getFechaHoraPrestacion() {
        return fechaHoraPrestacion;
    }

    public void setFechaHoraPrestacion(Date fechaHoraPrestacion) {
        this.fechaHoraPrestacion = fechaHoraPrestacion;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public CmDetalles getCmDetallesId() {
        return cmDetallesId;
    }

    public void setCmDetallesId(CmDetalles cmDetallesId) {
        this.cmDetallesId = cmDetallesId;
    }

    public CntContratoSedes getCntContratoSedesId() {
        return cntContratoSedesId;
    }

    public void setCntContratoSedesId(CntContratoSedes cntContratoSedesId) {
        this.cntContratoSedesId = cntContratoSedesId;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcntContratoPrestacion != null ? idcntContratoPrestacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntContratoHistoricoPrestaciones)) {
            return false;
        }
        CntContratoHistoricoPrestaciones other = (CntContratoHistoricoPrestaciones) object;
        if ((this.idcntContratoPrestacion == null && other.idcntContratoPrestacion != null) || (this.idcntContratoPrestacion != null && !this.idcntContratoPrestacion.equals(other.idcntContratoPrestacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoHistoricoPrestaciones[ idcntContratoPrestacion=" + idcntContratoPrestacion + " ]";
    }
    
}
