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
@Table(name = "cs_topes_moderadoras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsTopesModeradoras.findAll", query = "SELECT c FROM CsTopesModeradoras c"),
    @NamedQuery(name = "CsTopesModeradoras.findById", query = "SELECT c FROM CsTopesModeradoras c WHERE c.id = :id"),
    @NamedQuery(name = "CsTopesModeradoras.findByAgno", query = "SELECT c FROM CsTopesModeradoras c WHERE c.agno = :agno"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeRegimenId", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeRegimenCodigo", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeRegimenValor", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeTipoAfiliadoId", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeTipoAfiliadoId = :maeTipoAfiliadoId"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeTipoAfiliadoCodigo", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeTipoAfiliadoCodigo = :maeTipoAfiliadoCodigo"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeTipoAfiliadoValor", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeTipoAfiliadoValor = :maeTipoAfiliadoValor"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeNivelSisbenId", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeNivelSisbenId = :maeNivelSisbenId"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeNivelSisbenCodigo", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeNivelSisbenCodigo = :maeNivelSisbenCodigo"),
    @NamedQuery(name = "CsTopesModeradoras.findByMaeNivelSisbenValor", query = "SELECT c FROM CsTopesModeradoras c WHERE c.maeNivelSisbenValor = :maeNivelSisbenValor"),
    @NamedQuery(name = "CsTopesModeradoras.findByCategoriaIbc", query = "SELECT c FROM CsTopesModeradoras c WHERE c.categoriaIbc = :categoriaIbc"),
    @NamedQuery(name = "CsTopesModeradoras.findByValor", query = "SELECT c FROM CsTopesModeradoras c WHERE c.valor = :valor"),
    @NamedQuery(name = "CsTopesModeradoras.findByUsuarioCrea", query = "SELECT c FROM CsTopesModeradoras c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CsTopesModeradoras.findByTerminalCrea", query = "SELECT c FROM CsTopesModeradoras c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CsTopesModeradoras.findByFechaHoraCrea", query = "SELECT c FROM CsTopesModeradoras c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CsTopesModeradoras.findByUsuarioModifica", query = "SELECT c FROM CsTopesModeradoras c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CsTopesModeradoras.findByTerminalModifica", query = "SELECT c FROM CsTopesModeradoras c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CsTopesModeradoras.findByFechaHoraModifica", query = "SELECT c FROM CsTopesModeradoras c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CsTopesModeradoras implements Serializable {

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
    @Size(max = 16)
    @Column(name = "categoria_ibc")
    private String categoriaIbc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
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

    public CsTopesModeradoras() {
    }

    public CsTopesModeradoras(Integer id) {
        this.id = id;
    }

    public CsTopesModeradoras(Integer id, int agno, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, int maeTipoAfiliadoId, String maeTipoAfiliadoCodigo, String maeTipoAfiliadoValor, BigDecimal valor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.agno = agno;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenCodigo = maeRegimenCodigo;
        this.maeRegimenValor = maeRegimenValor;
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
        this.valor = valor;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        if (!(object instanceof CsTopesModeradoras)) {
            return false;
        }
        CsTopesModeradoras other = (CsTopesModeradoras) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CsTopesModeradoras[ id=" + id + " ]";
    }
    
}
