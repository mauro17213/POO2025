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
@Table(name = "cnt_contrato_juridicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoJuridicos.findAll", query = "SELECT c FROM CntContratoJuridicos c"),
    @NamedQuery(name = "CntContratoJuridicos.findById", query = "SELECT c FROM CntContratoJuridicos c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoJuridicos.findByMaeDocumentoJuridicoId", query = "SELECT c FROM CntContratoJuridicos c WHERE c.maeDocumentoJuridicoId = :maeDocumentoJuridicoId"),
    @NamedQuery(name = "CntContratoJuridicos.findByMaeDocumentoJuridicoCodigo", query = "SELECT c FROM CntContratoJuridicos c WHERE c.maeDocumentoJuridicoCodigo = :maeDocumentoJuridicoCodigo"),
    @NamedQuery(name = "CntContratoJuridicos.findByMaeDocumentoJuridicoValor", query = "SELECT c FROM CntContratoJuridicos c WHERE c.maeDocumentoJuridicoValor = :maeDocumentoJuridicoValor"),
    @NamedQuery(name = "CntContratoJuridicos.findByConsecutivo", query = "SELECT c FROM CntContratoJuridicos c WHERE c.consecutivo = :consecutivo"),
    @NamedQuery(name = "CntContratoJuridicos.findByValor", query = "SELECT c FROM CntContratoJuridicos c WHERE c.valor = :valor"),
    @NamedQuery(name = "CntContratoJuridicos.findByFechaInicial", query = "SELECT c FROM CntContratoJuridicos c WHERE c.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "CntContratoJuridicos.findByFechaFinal", query = "SELECT c FROM CntContratoJuridicos c WHERE c.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "CntContratoJuridicos.findByEstado", query = "SELECT c FROM CntContratoJuridicos c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntContratoJuridicos.findByUsuarioCrea", query = "SELECT c FROM CntContratoJuridicos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoJuridicos.findByTerminalCrea", query = "SELECT c FROM CntContratoJuridicos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoJuridicos.findByFechaHoraCrea", query = "SELECT c FROM CntContratoJuridicos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntContratoJuridicos.findByUsuarioModifica", query = "SELECT c FROM CntContratoJuridicos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntContratoJuridicos.findByTerminalModifica", query = "SELECT c FROM CntContratoJuridicos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntContratoJuridicos.findByFechaHoraModifica", query = "SELECT c FROM CntContratoJuridicos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntContratoJuridicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_documento_juridico_id")
    private int maeDocumentoJuridicoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_documento_juridico_codigo")
    private String maeDocumentoJuridicoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_documento_juridico_valor")
    private String maeDocumentoJuridicoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo")
    private int consecutivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratos cntContratosId;

    public CntContratoJuridicos() {
    }

    public CntContratoJuridicos(Integer id) {
        this.id = id;
    }

    public CntContratoJuridicos(Integer id, int maeDocumentoJuridicoId, String maeDocumentoJuridicoCodigo, String maeDocumentoJuridicoValor, int consecutivo, Date fechaInicial, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeDocumentoJuridicoId = maeDocumentoJuridicoId;
        this.maeDocumentoJuridicoCodigo = maeDocumentoJuridicoCodigo;
        this.maeDocumentoJuridicoValor = maeDocumentoJuridicoValor;
        this.consecutivo = consecutivo;
        this.fechaInicial = fechaInicial;
        this.estado = estado;
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

    public int getMaeDocumentoJuridicoId() {
        return maeDocumentoJuridicoId;
    }

    public void setMaeDocumentoJuridicoId(int maeDocumentoJuridicoId) {
        this.maeDocumentoJuridicoId = maeDocumentoJuridicoId;
    }

    public String getMaeDocumentoJuridicoCodigo() {
        return maeDocumentoJuridicoCodigo;
    }

    public void setMaeDocumentoJuridicoCodigo(String maeDocumentoJuridicoCodigo) {
        this.maeDocumentoJuridicoCodigo = maeDocumentoJuridicoCodigo;
    }

    public String getMaeDocumentoJuridicoValor() {
        return maeDocumentoJuridicoValor;
    }

    public void setMaeDocumentoJuridicoValor(String maeDocumentoJuridicoValor) {
        this.maeDocumentoJuridicoValor = maeDocumentoJuridicoValor;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
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
        if (!(object instanceof CntContratoJuridicos)) {
            return false;
        }
        CntContratoJuridicos other = (CntContratoJuridicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoJuridicos[ id=" + id + " ]";
    }
    
}
