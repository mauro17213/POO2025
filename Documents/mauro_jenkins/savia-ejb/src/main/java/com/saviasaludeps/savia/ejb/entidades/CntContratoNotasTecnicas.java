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
@Table(name = "cnt_contrato_notas_tecnicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntContratoNotasTecnicas.findAll", query = "SELECT c FROM CntContratoNotasTecnicas c"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findById", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.id = :id"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByTipoTecnologia", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByMaTecnologiaId", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByMaTecnologiaCodigo", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByMaTecnologiaValor", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByCostoPromedio", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.costoPromedio = :costoPromedio"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByTipoFrecuencia", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.tipoFrecuencia = :tipoFrecuencia"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByFrecuenciaUso", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.frecuenciaUso = :frecuenciaUso"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByCantidadAfiliados", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.cantidadAfiliados = :cantidadAfiliados"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByMaeAmbitoId", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByMaeAmbitoCodigo", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByMaeAmbitoValor", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByObservacion", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByFechaInicio", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByFechaFin", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByUsuarioCrea", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByTerminalCrea", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByFechaHoraCrea", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByUsuarioModifica", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByTerminalModifica", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntContratoNotasTecnicas.findByFechaHoraModifica", query = "SELECT c FROM CntContratoNotasTecnicas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntContratoNotasTecnicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_promedio")
    private BigDecimal costoPromedio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_frecuencia")
    private int tipoFrecuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "frecuencia_uso")
    private BigDecimal frecuenciaUso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_afiliados")
    private int cantidadAfiliados;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_ambito_id")
    private int maeAmbitoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
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

    public CntContratoNotasTecnicas() {
    }

    public CntContratoNotasTecnicas(Integer id) {
        this.id = id;
    }

    public CntContratoNotasTecnicas(Integer id, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, BigDecimal costoPromedio, int tipoFrecuencia, BigDecimal frecuenciaUso, int cantidadAfiliados, int maeAmbitoId, String maeAmbitoCodigo, String maeAmbitoValor, Date fechaInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.costoPromedio = costoPromedio;
        this.tipoFrecuencia = tipoFrecuencia;
        this.frecuenciaUso = frecuenciaUso;
        this.cantidadAfiliados = cantidadAfiliados;
        this.maeAmbitoId = maeAmbitoId;
        this.maeAmbitoCodigo = maeAmbitoCodigo;
        this.maeAmbitoValor = maeAmbitoValor;
        this.fechaInicio = fechaInicio;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
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

    public BigDecimal getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(BigDecimal costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public int getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(int tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public BigDecimal getFrecuenciaUso() {
        return frecuenciaUso;
    }

    public void setFrecuenciaUso(BigDecimal frecuenciaUso) {
        this.frecuenciaUso = frecuenciaUso;
    }

    public int getCantidadAfiliados() {
        return cantidadAfiliados;
    }

    public void setCantidadAfiliados(int cantidadAfiliados) {
        this.cantidadAfiliados = cantidadAfiliados;
    }

    public int getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(int maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof CntContratoNotasTecnicas)) {
            return false;
        }
        CntContratoNotasTecnicas other = (CntContratoNotasTecnicas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntContratoNotasTecnicas[ id=" + id + " ]";
    }
    
}
