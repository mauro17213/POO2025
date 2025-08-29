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
@Table(name = "auc_diagnosticos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucDiagnosticos.findAll", query = "SELECT a FROM AucDiagnosticos a"),
    @NamedQuery(name = "AucDiagnosticos.findById", query = "SELECT a FROM AucDiagnosticos a WHERE a.id = :id"),
    @NamedQuery(name = "AucDiagnosticos.findByMaeTipoDiagnosticoId", query = "SELECT a FROM AucDiagnosticos a WHERE a.maeTipoDiagnosticoId = :maeTipoDiagnosticoId"),
    @NamedQuery(name = "AucDiagnosticos.findByMaeTipoDiagnosticoCodigo", query = "SELECT a FROM AucDiagnosticos a WHERE a.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo"),
    @NamedQuery(name = "AucDiagnosticos.findByMaeTipoDiagnosticoValor", query = "SELECT a FROM AucDiagnosticos a WHERE a.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor"),
    @NamedQuery(name = "AucDiagnosticos.findByPrincipal", query = "SELECT a FROM AucDiagnosticos a WHERE a.principal = :principal"),
    @NamedQuery(name = "AucDiagnosticos.findByMaDiagnosticoId", query = "SELECT a FROM AucDiagnosticos a WHERE a.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "AucDiagnosticos.findByMaDiagnosticoCodigo", query = "SELECT a FROM AucDiagnosticos a WHERE a.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "AucDiagnosticos.findByMaDiagnosticoValor", query = "SELECT a FROM AucDiagnosticos a WHERE a.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "AucDiagnosticos.findByUsuarioCrea", query = "SELECT a FROM AucDiagnosticos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucDiagnosticos.findByTerminalCrea", query = "SELECT a FROM AucDiagnosticos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucDiagnosticos.findByFechaHoraCrea", query = "SELECT a FROM AucDiagnosticos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AucDiagnosticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_diagnostico_id")
    private Integer maeTipoDiagnosticoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_diagnostico_codigo")
    private String maeTipoDiagnosticoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_diagnostico_valor")
    private String maeTipoDiagnosticoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "principal")
    private boolean principal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_diagnostico_id")
    private int maDiagnosticoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
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
    @JoinColumn(name = "auc_egresos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucEgresos aucEgresosId;
    @JoinColumn(name = "auc_hospitalizacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionId;
    @JoinColumn(name = "auc_ingresos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucIngresos aucIngresosId;

    public AucDiagnosticos() {
    }

    public AucDiagnosticos(Integer id) {
        this.id = id;
    }

    public AucDiagnosticos(Integer id, boolean principal, int maDiagnosticoId, String maDiagnosticoCodigo, String maDiagnosticoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.principal = principal;
        this.maDiagnosticoId = maDiagnosticoId;
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
        this.maDiagnosticoValor = maDiagnosticoValor;
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

    public Integer getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(Integer maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public int getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(int maDiagnosticoId) {
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

    public AucEgresos getAucEgresosId() {
        return aucEgresosId;
    }

    public void setAucEgresosId(AucEgresos aucEgresosId) {
        this.aucEgresosId = aucEgresosId;
    }

    public AucHospitalizaciones getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizaciones aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public AucIngresos getAucIngresosId() {
        return aucIngresosId;
    }

    public void setAucIngresosId(AucIngresos aucIngresosId) {
        this.aucIngresosId = aucIngresosId;
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
        if (!(object instanceof AucDiagnosticos)) {
            return false;
        }
        AucDiagnosticos other = (AucDiagnosticos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucDiagnosticos[ id=" + id + " ]";
    }
    
}
