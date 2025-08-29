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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cs_topes_copagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsTopesCopagos.findAll", query = "SELECT c FROM CsTopesCopagos c"),
    @NamedQuery(name = "CsTopesCopagos.findById", query = "SELECT c FROM CsTopesCopagos c WHERE c.id = :id"),
    @NamedQuery(name = "CsTopesCopagos.findByAgno", query = "SELECT c FROM CsTopesCopagos c WHERE c.agno = :agno"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeRegimenId", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeRegimenCodigo", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeRegimenValor", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeTipoAfiliadoId", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeTipoAfiliadoId = :maeTipoAfiliadoId"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeTipoAfiliadoCodigo", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeTipoAfiliadoCodigo = :maeTipoAfiliadoCodigo"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeTipoAfiliadoValor", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeTipoAfiliadoValor = :maeTipoAfiliadoValor"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeNivelSisbenId", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeNivelSisbenId = :maeNivelSisbenId"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeNivelSisbenCodigo", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeNivelSisbenCodigo = :maeNivelSisbenCodigo"),
    @NamedQuery(name = "CsTopesCopagos.findByMaeNivelSisbenValor", query = "SELECT c FROM CsTopesCopagos c WHERE c.maeNivelSisbenValor = :maeNivelSisbenValor"),
    @NamedQuery(name = "CsTopesCopagos.findByCategoriaIbc", query = "SELECT c FROM CsTopesCopagos c WHERE c.categoriaIbc = :categoriaIbc"),
    @NamedQuery(name = "CsTopesCopagos.findByPorcentaje", query = "SELECT c FROM CsTopesCopagos c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "CsTopesCopagos.findByValorTopeEvento", query = "SELECT c FROM CsTopesCopagos c WHERE c.valorTopeEvento = :valorTopeEvento"),
    @NamedQuery(name = "CsTopesCopagos.findByValorTopeAgno", query = "SELECT c FROM CsTopesCopagos c WHERE c.valorTopeAgno = :valorTopeAgno"),
    @NamedQuery(name = "CsTopesCopagos.findByUsuarioCrea", query = "SELECT c FROM CsTopesCopagos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CsTopesCopagos.findByTerminalCrea", query = "SELECT c FROM CsTopesCopagos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CsTopesCopagos.findByFechaHoraCrea", query = "SELECT c FROM CsTopesCopagos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CsTopesCopagos.findByUsuarioModifica", query = "SELECT c FROM CsTopesCopagos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CsTopesCopagos.findByTerminalModifica", query = "SELECT c FROM CsTopesCopagos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CsTopesCopagos.findByFechaHoraModifica", query = "SELECT c FROM CsTopesCopagos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CsTopesCopagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agno")
    private int agno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_afiliado_id")
    private int maeTipoAfiliadoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_afiliado_codigo")
    private String maeTipoAfiliadoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_afiliado_valor")
    private String maeTipoAfiliadoValor;
    @Column(name = "mae_nivel_sisben_id")
    private Integer maeNivelSisbenId;
    @Size(max = 8)
    @Column(name = "mae_nivel_sisben_codigo")
    private String maeNivelSisbenCodigo;
    @Size(max = 128)
    @Column(name = "mae_nivel_sisben_valor")
    private String maeNivelSisbenValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "categoria_ibc")
    private String categoriaIbc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_tope_evento")
    private int valorTopeEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_tope_agno")
    private int valorTopeAgno;
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

    public CsTopesCopagos() {
    }

    public CsTopesCopagos(Integer id) {
        this.id = id;
    }

    public CsTopesCopagos(Integer id, int agno, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, int maeTipoAfiliadoId, String maeTipoAfiliadoCodigo, String maeTipoAfiliadoValor, String categoriaIbc, BigDecimal porcentaje, int valorTopeEvento, int valorTopeAgno, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.agno = agno;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenCodigo = maeRegimenCodigo;
        this.maeRegimenValor = maeRegimenValor;
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
        this.categoriaIbc = categoriaIbc;
        this.porcentaje = porcentaje;
        this.valorTopeEvento = valorTopeEvento;
        this.valorTopeAgno = valorTopeAgno;
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

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public int getMaeTipoAfiliadoId() {
        return maeTipoAfiliadoId;
    }

    public void setMaeTipoAfiliadoId(int maeTipoAfiliadoId) {
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
    }

    public String getMaeTipoAfiliadoCodigo() {
        return maeTipoAfiliadoCodigo;
    }

    public void setMaeTipoAfiliadoCodigo(String maeTipoAfiliadoCodigo) {
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
    }

    public String getMaeTipoAfiliadoValor() {
        return maeTipoAfiliadoValor;
    }

    public void setMaeTipoAfiliadoValor(String maeTipoAfiliadoValor) {
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
    }

    public Integer getMaeNivelSisbenId() {
        return maeNivelSisbenId;
    }

    public void setMaeNivelSisbenId(Integer maeNivelSisbenId) {
        this.maeNivelSisbenId = maeNivelSisbenId;
    }

    public String getMaeNivelSisbenCodigo() {
        return maeNivelSisbenCodigo;
    }

    public void setMaeNivelSisbenCodigo(String maeNivelSisbenCodigo) {
        this.maeNivelSisbenCodigo = maeNivelSisbenCodigo;
    }

    public String getMaeNivelSisbenValor() {
        return maeNivelSisbenValor;
    }

    public void setMaeNivelSisbenValor(String maeNivelSisbenValor) {
        this.maeNivelSisbenValor = maeNivelSisbenValor;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getValorTopeEvento() {
        return valorTopeEvento;
    }

    public void setValorTopeEvento(int valorTopeEvento) {
        this.valorTopeEvento = valorTopeEvento;
    }

    public int getValorTopeAgno() {
        return valorTopeAgno;
    }

    public void setValorTopeAgno(int valorTopeAgno) {
        this.valorTopeAgno = valorTopeAgno;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CsTopesCopagos)) {
            return false;
        }
        CsTopesCopagos other = (CsTopesCopagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CsTopesCopagos[ id=" + id + " ]";
    }
    
}
