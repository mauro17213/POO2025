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
@Table(name = "cnt_contrato_historico_validaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findAll", query = "SELECT c FROM CntContratoHistoricoValidaciones c"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findById", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByCntPrestadoresNumeroDocumento", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.cntPrestadoresNumeroDocumento = :cntPrestadoresNumeroDocumento"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByCntPrestadoresCodigoMinsalud", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.cntPrestadoresCodigoMinsalud = :cntPrestadoresCodigoMinsalud"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByCntPrestadorSedesCodigoHabilitacion", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.cntPrestadorSedesCodigoHabilitacion = :cntPrestadorSedesCodigoHabilitacion"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByTipoTecnologia", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByMaTecnologiaId", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByMaTecnologiaCodigo", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByMaTecnologiaValor", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByMaManualTarifarioCodigo", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.maManualTarifarioCodigo = :maManualTarifarioCodigo"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByTipoManualTarifario", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.tipoManualTarifario = :tipoManualTarifario"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByMaManualTarifarioAgno", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.maManualTarifarioAgno = :maManualTarifarioAgno"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByValorManual", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.valorManual = :valorManual"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByPorcentajeVariacion", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.porcentajeVariacion = :porcentajeVariacion"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByValor", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.valor = :valor"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByFechaInicio", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByFechaFin", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntContratoHistoricoValidaciones.findByFechaHoraCrea", query = "SELECT c FROM CntContratoHistoricoValidaciones c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntContratoHistoricoValidaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cnt_prestadores_numero_documento")
    private String cntPrestadoresNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "cnt_prestadores_codigo_minsalud")
    private String cntPrestadoresCodigoMinsalud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "cnt_prestador_sedes_codigo_habilitacion")
    private String cntPrestadorSedesCodigoHabilitacion;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Size(max = 32)
    @Column(name = "ma_manual_tarifario_codigo")
    private String maManualTarifarioCodigo;
    @Column(name = "tipo_manual_tarifario")
    private Integer tipoManualTarifario;
    @Column(name = "ma_manual_tarifario_agno")
    private Integer maManualTarifarioAgno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_manual")
    private BigDecimal valorManual;
    @Column(name = "porcentaje_variacion")
    private BigDecimal porcentajeVariacion;
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cnt_contrato_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratoDetalles cntContratoDetallesId;
    @JoinColumn(name = "cnt_contrato_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratoSedes cntContratoSedesId;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratos cntContratosId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public CntContratoHistoricoValidaciones() {
    }

    public CntContratoHistoricoValidaciones(Integer id) {
        this.id = id;
    }

    public CntContratoHistoricoValidaciones(Integer id, String cntPrestadoresNumeroDocumento, String cntPrestadoresCodigoMinsalud, String cntPrestadorSedesCodigoHabilitacion, Date fechaInicio, Date fechaFin, Date fechaHoraCrea) {
        this.id = id;
        this.cntPrestadoresNumeroDocumento = cntPrestadoresNumeroDocumento;
        this.cntPrestadoresCodigoMinsalud = cntPrestadoresCodigoMinsalud;
        this.cntPrestadorSedesCodigoHabilitacion = cntPrestadorSedesCodigoHabilitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCntPrestadoresNumeroDocumento() {
        return cntPrestadoresNumeroDocumento;
    }

    public void setCntPrestadoresNumeroDocumento(String cntPrestadoresNumeroDocumento) {
        this.cntPrestadoresNumeroDocumento = cntPrestadoresNumeroDocumento;
    }

    public String getCntPrestadoresCodigoMinsalud() {
        return cntPrestadoresCodigoMinsalud;
    }

    public void setCntPrestadoresCodigoMinsalud(String cntPrestadoresCodigoMinsalud) {
        this.cntPrestadoresCodigoMinsalud = cntPrestadoresCodigoMinsalud;
    }

    public String getCntPrestadorSedesCodigoHabilitacion() {
        return cntPrestadorSedesCodigoHabilitacion;
    }

    public void setCntPrestadorSedesCodigoHabilitacion(String cntPrestadorSedesCodigoHabilitacion) {
        this.cntPrestadorSedesCodigoHabilitacion = cntPrestadorSedesCodigoHabilitacion;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
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

    public String getMaManualTarifarioCodigo() {
        return maManualTarifarioCodigo;
    }

    public void setMaManualTarifarioCodigo(String maManualTarifarioCodigo) {
        this.maManualTarifarioCodigo = maManualTarifarioCodigo;
    }

    public Integer getTipoManualTarifario() {
        return tipoManualTarifario;
    }

    public void setTipoManualTarifario(Integer tipoManualTarifario) {
        this.tipoManualTarifario = tipoManualTarifario;
    }

    public Integer getMaManualTarifarioAgno() {
        return maManualTarifarioAgno;
    }

    public void setMaManualTarifarioAgno(Integer maManualTarifarioAgno) {
        this.maManualTarifarioAgno = maManualTarifarioAgno;
    }

    public BigDecimal getValorManual() {
        return valorManual;
    }

    public void setValorManual(BigDecimal valorManual) {
        this.valorManual = valorManual;
    }

    public BigDecimal getPorcentajeVariacion() {
        return porcentajeVariacion;
    }

    public void setPorcentajeVariacion(BigDecimal porcentajeVariacion) {
        this.porcentajeVariacion = porcentajeVariacion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public CntContratoDetalles getCntContratoDetallesId() {
        return cntContratoDetallesId;
    }

    public void setCntContratoDetallesId(CntContratoDetalles cntContratoDetallesId) {
        this.cntContratoDetallesId = cntContratoDetallesId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CntContratoHistoricoValidaciones)) {
            return false;
        }
        CntContratoHistoricoValidaciones other = (CntContratoHistoricoValidaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoHistoricoValidaciones[ id=" + id + " ]";
    }
    
}
