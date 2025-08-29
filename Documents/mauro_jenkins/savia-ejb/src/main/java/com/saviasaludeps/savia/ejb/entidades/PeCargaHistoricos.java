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
@Table(name = "pe_carga_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeCargaHistoricos.findAll", query = "SELECT p FROM PeCargaHistoricos p"),
    @NamedQuery(name = "PeCargaHistoricos.findById", query = "SELECT p FROM PeCargaHistoricos p WHERE p.id = :id"),
    @NamedQuery(name = "PeCargaHistoricos.findByTipo", query = "SELECT p FROM PeCargaHistoricos p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnosticoPrincipalId", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnosticoPrincipalId = :maDiagnosticoPrincipalId"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnosticoPrincipalCodigo", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnosticoPrincipalCodigo = :maDiagnosticoPrincipalCodigo"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnosticoPrincipalValor", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnosticoPrincipalValor = :maDiagnosticoPrincipalValor"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnostico2Id", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnostico2Id = :maDiagnostico2Id"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnostico2Codigo", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnostico2Codigo = :maDiagnostico2Codigo"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnostico2Valor", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnostico2Valor = :maDiagnostico2Valor"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnostico3Id", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnostico3Id = :maDiagnostico3Id"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnostico3Codigo", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnostico3Codigo = :maDiagnostico3Codigo"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaDiagnostico3Valor", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maDiagnostico3Valor = :maDiagnostico3Valor"),
    @NamedQuery(name = "PeCargaHistoricos.findByFechaDiagnostico", query = "SELECT p FROM PeCargaHistoricos p WHERE p.fechaDiagnostico = :fechaDiagnostico"),
    @NamedQuery(name = "PeCargaHistoricos.findByFechaInicioPrograma", query = "SELECT p FROM PeCargaHistoricos p WHERE p.fechaInicioPrograma = :fechaInicioPrograma"),
    @NamedQuery(name = "PeCargaHistoricos.findByFechaFinPrograma", query = "SELECT p FROM PeCargaHistoricos p WHERE p.fechaFinPrograma = :fechaFinPrograma"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaeRegionCorporalId", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maeRegionCorporalId = :maeRegionCorporalId"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaeRegionCorporalCodigo", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maeRegionCorporalCodigo = :maeRegionCorporalCodigo"),
    @NamedQuery(name = "PeCargaHistoricos.findByMaeRegionCorporalValor", query = "SELECT p FROM PeCargaHistoricos p WHERE p.maeRegionCorporalValor = :maeRegionCorporalValor"),
    @NamedQuery(name = "PeCargaHistoricos.findByUsuarioCrea", query = "SELECT p FROM PeCargaHistoricos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeCargaHistoricos.findByTerminalCrea", query = "SELECT p FROM PeCargaHistoricos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeCargaHistoricos.findByFechaHoraCrea", query = "SELECT p FROM PeCargaHistoricos p WHERE p.fechaHoraCrea = :fechaHoraCrea")})
public class PeCargaHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private boolean tipo;
    @Column(name = "ma_diagnostico_principal_id")
    private Integer maDiagnosticoPrincipalId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_principal_codigo")
    private String maDiagnosticoPrincipalCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_principal_valor")
    private String maDiagnosticoPrincipalValor;
    @Column(name = "ma_diagnostico_2_id")
    private Integer maDiagnostico2Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_2_codigo")
    private String maDiagnostico2Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_2_valor")
    private String maDiagnostico2Valor;
    @Column(name = "ma_diagnostico_3_id")
    private Integer maDiagnostico3Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_3_codigo")
    private String maDiagnostico3Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_3_valor")
    private String maDiagnostico3Valor;
    @Column(name = "fecha_diagnostico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDiagnostico;
    @Column(name = "fecha_inicio_programa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioPrograma;
    @Column(name = "fecha_fin_programa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPrograma;
    @Column(name = "mae_region_corporal_id")
    private Integer maeRegionCorporalId;
    @Size(max = 8)
    @Column(name = "mae_region_corporal_codigo")
    private String maeRegionCorporalCodigo;
    @Size(max = 128)
    @Column(name = "mae_region_corporal_valor")
    private String maeRegionCorporalValor;
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
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "pe_afiliados_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosProgramas peAfiliadosProgramasId;
    @JoinColumn(name = "pe_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeCargas peCargasId;

    public PeCargaHistoricos() {
    }

    public PeCargaHistoricos(Integer id) {
        this.id = id;
    }

    public PeCargaHistoricos(Integer id, boolean tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
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

    public boolean getTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public Integer getMaDiagnostico2Id() {
        return maDiagnostico2Id;
    }

    public void setMaDiagnostico2Id(Integer maDiagnostico2Id) {
        this.maDiagnostico2Id = maDiagnostico2Id;
    }

    public String getMaDiagnostico2Codigo() {
        return maDiagnostico2Codigo;
    }

    public void setMaDiagnostico2Codigo(String maDiagnostico2Codigo) {
        this.maDiagnostico2Codigo = maDiagnostico2Codigo;
    }

    public String getMaDiagnostico2Valor() {
        return maDiagnostico2Valor;
    }

    public void setMaDiagnostico2Valor(String maDiagnostico2Valor) {
        this.maDiagnostico2Valor = maDiagnostico2Valor;
    }

    public Integer getMaDiagnostico3Id() {
        return maDiagnostico3Id;
    }

    public void setMaDiagnostico3Id(Integer maDiagnostico3Id) {
        this.maDiagnostico3Id = maDiagnostico3Id;
    }

    public String getMaDiagnostico3Codigo() {
        return maDiagnostico3Codigo;
    }

    public void setMaDiagnostico3Codigo(String maDiagnostico3Codigo) {
        this.maDiagnostico3Codigo = maDiagnostico3Codigo;
    }

    public String getMaDiagnostico3Valor() {
        return maDiagnostico3Valor;
    }

    public void setMaDiagnostico3Valor(String maDiagnostico3Valor) {
        this.maDiagnostico3Valor = maDiagnostico3Valor;
    }

    public Date getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public Date getFechaInicioPrograma() {
        return fechaInicioPrograma;
    }

    public void setFechaInicioPrograma(Date fechaInicioPrograma) {
        this.fechaInicioPrograma = fechaInicioPrograma;
    }

    public Date getFechaFinPrograma() {
        return fechaFinPrograma;
    }

    public void setFechaFinPrograma(Date fechaFinPrograma) {
        this.fechaFinPrograma = fechaFinPrograma;
    }

    public Integer getMaeRegionCorporalId() {
        return maeRegionCorporalId;
    }

    public void setMaeRegionCorporalId(Integer maeRegionCorporalId) {
        this.maeRegionCorporalId = maeRegionCorporalId;
    }

    public String getMaeRegionCorporalCodigo() {
        return maeRegionCorporalCodigo;
    }

    public void setMaeRegionCorporalCodigo(String maeRegionCorporalCodigo) {
        this.maeRegionCorporalCodigo = maeRegionCorporalCodigo;
    }

    public String getMaeRegionCorporalValor() {
        return maeRegionCorporalValor;
    }

    public void setMaeRegionCorporalValor(String maeRegionCorporalValor) {
        this.maeRegionCorporalValor = maeRegionCorporalValor;
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

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public PeAfiliadosProgramas getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosProgramas peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    public PeCargas getPeCargasId() {
        return peCargasId;
    }

    public void setPeCargasId(PeCargas peCargasId) {
        this.peCargasId = peCargasId;
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
        if (!(object instanceof PeCargaHistoricos)) {
            return false;
        }
        PeCargaHistoricos other = (PeCargaHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeCargaHistoricos[ id=" + id + " ]";
    }
    
}
