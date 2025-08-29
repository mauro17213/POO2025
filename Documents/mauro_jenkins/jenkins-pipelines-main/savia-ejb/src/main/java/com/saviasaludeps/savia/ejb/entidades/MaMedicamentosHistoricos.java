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
@Table(name = "ma_medicamentos_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaMedicamentosHistoricos.findAll", query = "SELECT m FROM MaMedicamentosHistoricos m"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findById", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.id = :id"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByCum", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.cum = :cum"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByDescripcionInvima", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.descripcionInvima = :descripcionInvima"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByDescripcionEstandarizada", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.descripcionEstandarizada = :descripcionEstandarizada"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeTipoId", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeTipoCodigo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeTipoValor", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeConcentracionId", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeConcentracionId = :maeConcentracionId"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeConcentracionCodigo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeConcentracionCodigo = :maeConcentracionCodigo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeConcentracionValor", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeConcentracionValor = :maeConcentracionValor"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaePrincipioActivoId", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maePrincipioActivoId = :maePrincipioActivoId"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaePrincipioActivoCodigo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maePrincipioActivoCodigo = :maePrincipioActivoCodigo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaePrincipioActivoValor", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maePrincipioActivoValor = :maePrincipioActivoValor"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeFormaFarmaceuticaId", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeFormaFarmaceuticaId = :maeFormaFarmaceuticaId"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeFormaFarmaceuticaCodigo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeFormaFarmaceuticaCodigo = :maeFormaFarmaceuticaCodigo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeFormaFarmaceuticaValor", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeFormaFarmaceuticaValor = :maeFormaFarmaceuticaValor"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeTipoPpmId", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeTipoPpmId = :maeTipoPpmId"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeTipoPpmCodigo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeTipoPpmCodigo = :maeTipoPpmCodigo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByMaeTipoPpmValor", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.maeTipoPpmValor = :maeTipoPpmValor"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByEsAltoCosto", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.esAltoCosto = :esAltoCosto"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByEsCapitado", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.esCapitado = :esCapitado"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByAplicaSubsidiado", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.aplicaSubsidiado = :aplicaSubsidiado"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByAplicaContributivo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.aplicaContributivo = :aplicaContributivo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findBySexoAplica", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.sexoAplica = :sexoAplica"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByCodigoIum", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.codigoIum = :codigoIum"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByEdadDesde", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.edadDesde = :edadDesde"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByEdadHasta", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.edadHasta = :edadHasta"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByEsRegulado", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.esRegulado = :esRegulado"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByValorMaximoRegulado", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.valorMaximoRegulado = :valorMaximoRegulado"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByValorReferenteMinimo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.valorReferenteMinimo = :valorReferenteMinimo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByValorReferete", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.valorReferete = :valorReferete"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByCodigoFinanciador", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.codigoFinanciador = :codigoFinanciador"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByIdViejo", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.idViejo = :idViejo"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByUsuarioCrea", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByTerminalCrea", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByFechaHoraCrea", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByUsuarioModifica", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByTerminalModifica", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaMedicamentosHistoricos.findByFechaHoraModifica", query = "SELECT m FROM MaMedicamentosHistoricos m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaMedicamentosHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "cum")
    private String cum;
    @Size(max = 512)
    @Column(name = "descripcion_invima")
    private String descripcionInvima;
    @Size(max = 512)
    @Column(name = "descripcion_estandarizada")
    private String descripcionEstandarizada;
    @Column(name = "mae_tipo_id")
    private Integer maeTipoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_codigo")
    private String maeTipoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_valor")
    private String maeTipoValor;
    @Column(name = "mae_concentracion_id")
    private Integer maeConcentracionId;
    @Size(max = 8)
    @Column(name = "mae_concentracion_codigo")
    private String maeConcentracionCodigo;
    @Size(max = 128)
    @Column(name = "mae_concentracion_valor")
    private String maeConcentracionValor;
    @Column(name = "mae_principio_activo_id")
    private Integer maePrincipioActivoId;
    @Size(max = 8)
    @Column(name = "mae_principio_activo_codigo")
    private String maePrincipioActivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_principio_activo_valor")
    private String maePrincipioActivoValor;
    @Column(name = "mae_forma_farmaceutica_id")
    private Integer maeFormaFarmaceuticaId;
    @Size(max = 8)
    @Column(name = "mae_forma_farmaceutica_codigo")
    private String maeFormaFarmaceuticaCodigo;
    @Size(max = 128)
    @Column(name = "mae_forma_farmaceutica_valor")
    private String maeFormaFarmaceuticaValor;
    @Column(name = "mae_tipo_ppm_id")
    private Integer maeTipoPpmId;
    @Size(max = 8)
    @Column(name = "mae_tipo_ppm_codigo")
    private String maeTipoPpmCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_ppm_valor")
    private String maeTipoPpmValor;
    @Column(name = "es_alto_costo")
    private Boolean esAltoCosto;
    @Column(name = "es_capitado")
    private Boolean esCapitado;
    @Column(name = "aplica_subsidiado")
    private Boolean aplicaSubsidiado;
    @Column(name = "aplica_contributivo")
    private Boolean aplicaContributivo;
    @Column(name = "sexo_aplica")
    private Integer sexoAplica;
    @Size(max = 8)
    @Column(name = "codigo_ium")
    private String codigoIum;
    @Column(name = "edad_desde")
    private Integer edadDesde;
    @Column(name = "edad_hasta")
    private Integer edadHasta;
    @Column(name = "es_regulado")
    private Boolean esRegulado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_maximo_regulado")
    private BigDecimal valorMaximoRegulado;
    @Column(name = "valor_referente_minimo")
    private BigDecimal valorReferenteMinimo;
    @Column(name = "valor_referete")
    private BigDecimal valorReferete;
    @Size(max = 20)
    @Column(name = "codigo_financiador")
    private String codigoFinanciador;
    @Column(name = "id_viejo")
    private Integer idViejo;
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
    @Size(max = 416)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "ma_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaMedicamentos maMedicamentosId;
    @JoinColumn(name = "ma_agrupadores_medicamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaAgrupadoresMedicamento maAgrupadoresMedicamentoId;

    public MaMedicamentosHistoricos() {
    }

    public MaMedicamentosHistoricos(Integer id) {
        this.id = id;
    }

    public MaMedicamentosHistoricos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public String getCum() {
        return cum;
    }

    public void setCum(String cum) {
        this.cum = cum;
    }

    public String getDescripcionInvima() {
        return descripcionInvima;
    }

    public void setDescripcionInvima(String descripcionInvima) {
        this.descripcionInvima = descripcionInvima;
    }

    public String getDescripcionEstandarizada() {
        return descripcionEstandarizada;
    }

    public void setDescripcionEstandarizada(String descripcionEstandarizada) {
        this.descripcionEstandarizada = descripcionEstandarizada;
    }

    public Integer getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(Integer maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public Integer getMaeConcentracionId() {
        return maeConcentracionId;
    }

    public void setMaeConcentracionId(Integer maeConcentracionId) {
        this.maeConcentracionId = maeConcentracionId;
    }

    public String getMaeConcentracionCodigo() {
        return maeConcentracionCodigo;
    }

    public void setMaeConcentracionCodigo(String maeConcentracionCodigo) {
        this.maeConcentracionCodigo = maeConcentracionCodigo;
    }

    public String getMaeConcentracionValor() {
        return maeConcentracionValor;
    }

    public void setMaeConcentracionValor(String maeConcentracionValor) {
        this.maeConcentracionValor = maeConcentracionValor;
    }

    public Integer getMaePrincipioActivoId() {
        return maePrincipioActivoId;
    }

    public void setMaePrincipioActivoId(Integer maePrincipioActivoId) {
        this.maePrincipioActivoId = maePrincipioActivoId;
    }

    public String getMaePrincipioActivoCodigo() {
        return maePrincipioActivoCodigo;
    }

    public void setMaePrincipioActivoCodigo(String maePrincipioActivoCodigo) {
        this.maePrincipioActivoCodigo = maePrincipioActivoCodigo;
    }

    public String getMaePrincipioActivoValor() {
        return maePrincipioActivoValor;
    }

    public void setMaePrincipioActivoValor(String maePrincipioActivoValor) {
        this.maePrincipioActivoValor = maePrincipioActivoValor;
    }

    public Integer getMaeFormaFarmaceuticaId() {
        return maeFormaFarmaceuticaId;
    }

    public void setMaeFormaFarmaceuticaId(Integer maeFormaFarmaceuticaId) {
        this.maeFormaFarmaceuticaId = maeFormaFarmaceuticaId;
    }

    public String getMaeFormaFarmaceuticaCodigo() {
        return maeFormaFarmaceuticaCodigo;
    }

    public void setMaeFormaFarmaceuticaCodigo(String maeFormaFarmaceuticaCodigo) {
        this.maeFormaFarmaceuticaCodigo = maeFormaFarmaceuticaCodigo;
    }

    public String getMaeFormaFarmaceuticaValor() {
        return maeFormaFarmaceuticaValor;
    }

    public void setMaeFormaFarmaceuticaValor(String maeFormaFarmaceuticaValor) {
        this.maeFormaFarmaceuticaValor = maeFormaFarmaceuticaValor;
    }

    public Integer getMaeTipoPpmId() {
        return maeTipoPpmId;
    }

    public void setMaeTipoPpmId(Integer maeTipoPpmId) {
        this.maeTipoPpmId = maeTipoPpmId;
    }

    public String getMaeTipoPpmCodigo() {
        return maeTipoPpmCodigo;
    }

    public void setMaeTipoPpmCodigo(String maeTipoPpmCodigo) {
        this.maeTipoPpmCodigo = maeTipoPpmCodigo;
    }

    public String getMaeTipoPpmValor() {
        return maeTipoPpmValor;
    }

    public void setMaeTipoPpmValor(String maeTipoPpmValor) {
        this.maeTipoPpmValor = maeTipoPpmValor;
    }

    public Boolean getEsAltoCosto() {
        return esAltoCosto;
    }

    public void setEsAltoCosto(Boolean esAltoCosto) {
        this.esAltoCosto = esAltoCosto;
    }

    public Boolean getEsCapitado() {
        return esCapitado;
    }

    public void setEsCapitado(Boolean esCapitado) {
        this.esCapitado = esCapitado;
    }

    public Boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(Boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public Boolean getAplicaContributivo() {
        return aplicaContributivo;
    }

    public void setAplicaContributivo(Boolean aplicaContributivo) {
        this.aplicaContributivo = aplicaContributivo;
    }

    public Integer getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(Integer sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public String getCodigoIum() {
        return codigoIum;
    }

    public void setCodigoIum(String codigoIum) {
        this.codigoIum = codigoIum;
    }

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public Boolean getEsRegulado() {
        return esRegulado;
    }

    public void setEsRegulado(Boolean esRegulado) {
        this.esRegulado = esRegulado;
    }

    public BigDecimal getValorMaximoRegulado() {
        return valorMaximoRegulado;
    }

    public void setValorMaximoRegulado(BigDecimal valorMaximoRegulado) {
        this.valorMaximoRegulado = valorMaximoRegulado;
    }

    public BigDecimal getValorReferenteMinimo() {
        return valorReferenteMinimo;
    }

    public void setValorReferenteMinimo(BigDecimal valorReferenteMinimo) {
        this.valorReferenteMinimo = valorReferenteMinimo;
    }

    public BigDecimal getValorReferete() {
        return valorReferete;
    }

    public void setValorReferete(BigDecimal valorReferete) {
        this.valorReferete = valorReferete;
    }

    public String getCodigoFinanciador() {
        return codigoFinanciador;
    }

    public void setCodigoFinanciador(String codigoFinanciador) {
        this.codigoFinanciador = codigoFinanciador;
    }

    public Integer getIdViejo() {
        return idViejo;
    }

    public void setIdViejo(Integer idViejo) {
        this.idViejo = idViejo;
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

    public MaMedicamentos getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(MaMedicamentos maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
    }

    public MaAgrupadoresMedicamento getMaAgrupadoresMedicamentoId() {
        return maAgrupadoresMedicamentoId;
    }

    public void setMaAgrupadoresMedicamentoId(MaAgrupadoresMedicamento maAgrupadoresMedicamentoId) {
        this.maAgrupadoresMedicamentoId = maAgrupadoresMedicamentoId;
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
        if (!(object instanceof MaMedicamentosHistoricos)) {
            return false;
        }
        MaMedicamentosHistoricos other = (MaMedicamentosHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaMedicamentosHistoricos[ id=" + id + " ]";
    }
    
}
