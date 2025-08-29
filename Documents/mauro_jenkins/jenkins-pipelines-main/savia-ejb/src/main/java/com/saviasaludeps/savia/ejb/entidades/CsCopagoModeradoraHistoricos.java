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
@Table(name = "cs_copago_moderadora_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findAll", query = "SELECT c FROM CsCopagoModeradoraHistoricos c"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findById", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.id = :id"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByIdAfiliado", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.idAfiliado = :idAfiliado"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByAgno", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.agno = :agno"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeRegimenId", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeRegimenCodigo", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeRegimenValor", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByModeradoraCopago", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.moderadoraCopago = :moderadoraCopago"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByCategoriaIbc", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.categoriaIbc = :categoriaIbc"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeTipoAfiliadoId", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeTipoAfiliadoId = :maeTipoAfiliadoId"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeTipoAfiliadoCodigo", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeTipoAfiliadoCodigo = :maeTipoAfiliadoCodigo"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeTipoAfiliadoValor", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeTipoAfiliadoValor = :maeTipoAfiliadoValor"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeNivelSisbenId", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeNivelSisbenId = :maeNivelSisbenId"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeNivelSisbenCodigo", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeNivelSisbenCodigo = :maeNivelSisbenCodigo"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByMaeNivelSisbenValor", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.maeNivelSisbenValor = :maeNivelSisbenValor"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByValorModeradora", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.valorModeradora = :valorModeradora"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByPorcentajeCopago", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.porcentajeCopago = :porcentajeCopago"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByValorProyectado", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.valorProyectado = :valorProyectado"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByValorEjecutado", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.valorEjecutado = :valorEjecutado"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByUsuarioCrea", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByTerminalCrea", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CsCopagoModeradoraHistoricos.findByFechaHoraCrea", query = "SELECT c FROM CsCopagoModeradoraHistoricos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CsCopagoModeradoraHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id_afiliado")
    private String idAfiliado;
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
    @Column(name = "moderadora_copago")
    private boolean moderadoraCopago;
    @Size(max = 16)
    @Column(name = "categoria_ibc")
    private String categoriaIbc;
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
    @Column(name = "valor_moderadora")
    private Integer valorModeradora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_copago")
    private BigDecimal porcentajeCopago;
    @Column(name = "valor_proyectado")
    private Integer valorProyectado;
    @Column(name = "valor_ejecutado")
    private Integer valorEjecutado;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @JoinColumn(name = "cm_facturas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CmFacturas cmFacturasId;

    public CsCopagoModeradoraHistoricos() {
    }

    public CsCopagoModeradoraHistoricos(Integer id) {
        this.id = id;
    }

    public CsCopagoModeradoraHistoricos(Integer id, String idAfiliado, int agno, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, boolean moderadoraCopago, int maeTipoAfiliadoId, String maeTipoAfiliadoCodigo, String maeTipoAfiliadoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.agno = agno;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenCodigo = maeRegimenCodigo;
        this.maeRegimenValor = maeRegimenValor;
        this.moderadoraCopago = moderadoraCopago;
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
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

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
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

    public boolean getModeradoraCopago() {
        return moderadoraCopago;
    }

    public void setModeradoraCopago(boolean moderadoraCopago) {
        this.moderadoraCopago = moderadoraCopago;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
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

    public Integer getValorModeradora() {
        return valorModeradora;
    }

    public void setValorModeradora(Integer valorModeradora) {
        this.valorModeradora = valorModeradora;
    }

    public BigDecimal getPorcentajeCopago() {
        return porcentajeCopago;
    }

    public void setPorcentajeCopago(BigDecimal porcentajeCopago) {
        this.porcentajeCopago = porcentajeCopago;
    }

    public Integer getValorProyectado() {
        return valorProyectado;
    }

    public void setValorProyectado(Integer valorProyectado) {
        this.valorProyectado = valorProyectado;
    }

    public Integer getValorEjecutado() {
        return valorEjecutado;
    }

    public void setValorEjecutado(Integer valorEjecutado) {
        this.valorEjecutado = valorEjecutado;
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

    public CmFacturas getCmFacturasId() {
        return cmFacturasId;
    }

    public void setCmFacturasId(CmFacturas cmFacturasId) {
        this.cmFacturasId = cmFacturasId;
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
        if (!(object instanceof CsCopagoModeradoraHistoricos)) {
            return false;
        }
        CsCopagoModeradoraHistoricos other = (CsCopagoModeradoraHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CsCopagoModeradoraHistoricos[ id=" + id + " ]";
    }
    
}
