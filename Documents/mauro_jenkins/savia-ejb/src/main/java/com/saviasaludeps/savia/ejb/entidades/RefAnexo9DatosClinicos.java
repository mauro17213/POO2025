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
@Table(name = "ref_anexo9_datos_clinicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefAnexo9DatosClinicos.findAll", query = "SELECT r FROM RefAnexo9DatosClinicos r"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findById", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.id = :id"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByFechaHoraDatos", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.fechaHoraDatos = :fechaHoraDatos"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTriage", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.triage = :triage"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByAntecedentes", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.antecedentes = :antecedentes"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByResumenClinico", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.resumenClinico = :resumenClinico"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTemperatura", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.temperatura = :temperatura"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByFrecuenciaCardiaca", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.frecuenciaCardiaca = :frecuenciaCardiaca"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTensionArtedialDiastole", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.tensionArtedialDiastole = :tensionArtedialDiastole"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTensionArterialSistole", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.tensionArterialSistole = :tensionArterialSistole"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByFrecuenciaRespiratoria", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.frecuenciaRespiratoria = :frecuenciaRespiratoria"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySaturacionOxigeno", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.saturacionOxigeno = :saturacionOxigeno"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByPeso", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.peso = :peso"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTalla", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.talla = :talla"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByPerimetroAbdominal", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.perimetroAbdominal = :perimetroAbdominal"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByImc", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.imc = :imc"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByHallazgosExamenFisico", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.hallazgosExamenFisico = :hallazgosExamenFisico"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByExamenesApoyoDiagnostico", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.examenesApoyoDiagnostico = :examenesApoyoDiagnostico"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTratamientoAplicado", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.tratamientoAplicado = :tratamientoAplicado"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByMotivoRemision", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.motivoRemision = :motivoRemision"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByEscalaGlasgow", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.escalaGlasgow = :escalaGlasgow"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaNeurologico", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaNeurologico = :sistemaNeurologico"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaSentidos", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaSentidos = :sistemaSentidos"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaCardiobascular", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaCardiobascular = :sistemaCardiobascular"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaRespiratorio", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaRespiratorio = :sistemaRespiratorio"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaDigestivo", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaDigestivo = :sistemaDigestivo"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaGenitoUrinario", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaGenitoUrinario = :sistemaGenitoUrinario"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findBySistemaOsteomuscular", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.sistemaOsteomuscular = :sistemaOsteomuscular"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByObservacionGeneral", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.observacionGeneral = :observacionGeneral"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByUsuarioCrea", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTerminalCrea", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByFechaHoraCrea", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByUsuarioModifica", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByTerminalModifica", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RefAnexo9DatosClinicos.findByFechaHoraModifica", query = "SELECT r FROM RefAnexo9DatosClinicos r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RefAnexo9DatosClinicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_datos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDatos;
    @Column(name = "triage")
    private Integer triage;
    @Size(max = 2048)
    @Column(name = "antecedentes")
    private String antecedentes;
    @Size(max = 2048)
    @Column(name = "resumen_clinico")
    private String resumenClinico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "temperatura")
    private BigDecimal temperatura;
    @Column(name = "frecuencia_cardiaca")
    private BigDecimal frecuenciaCardiaca;
    @Column(name = "tension_artedial_diastole")
    private BigDecimal tensionArtedialDiastole;
    @Column(name = "tension_arterial_sistole")
    private BigDecimal tensionArterialSistole;
    @Column(name = "frecuencia_respiratoria")
    private BigDecimal frecuenciaRespiratoria;
    @Column(name = "saturacion_oxigeno")
    private BigDecimal saturacionOxigeno;
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "talla")
    private Integer talla;
    @Column(name = "perimetro_abdominal")
    private BigDecimal perimetroAbdominal;
    @Column(name = "imc")
    private BigDecimal imc;
    @Size(max = 2048)
    @Column(name = "hallazgos_examen_fisico")
    private String hallazgosExamenFisico;
    @Size(max = 2048)
    @Column(name = "examenes_apoyo_diagnostico")
    private String examenesApoyoDiagnostico;
    @Size(max = 2048)
    @Column(name = "tratamiento_aplicado")
    private String tratamientoAplicado;
    @Size(max = 2048)
    @Column(name = "motivo_remision")
    private String motivoRemision;
    @Column(name = "escala_glasgow")
    private Integer escalaGlasgow;
    @Size(max = 2048)
    @Column(name = "sistema_neurologico")
    private String sistemaNeurologico;
    @Size(max = 2048)
    @Column(name = "sistema_sentidos")
    private String sistemaSentidos;
    @Size(max = 2048)
    @Column(name = "sistema_cardiobascular")
    private String sistemaCardiobascular;
    @Size(max = 2048)
    @Column(name = "sistema_respiratorio")
    private String sistemaRespiratorio;
    @Size(max = 2048)
    @Column(name = "sistema_digestivo")
    private String sistemaDigestivo;
    @Size(max = 2048)
    @Column(name = "sistema_genito_urinario")
    private String sistemaGenitoUrinario;
    @Size(max = 2048)
    @Column(name = "sistema_osteomuscular")
    private String sistemaOsteomuscular;
    @Size(max = 2048)
    @Column(name = "observacion_general")
    private String observacionGeneral;
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
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;

    public RefAnexo9DatosClinicos() {
    }

    public RefAnexo9DatosClinicos(Integer id) {
        this.id = id;
    }

    public RefAnexo9DatosClinicos(Integer id, Date fechaHoraDatos, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaHoraDatos = fechaHoraDatos;
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

    public Date getFechaHoraDatos() {
        return fechaHoraDatos;
    }

    public void setFechaHoraDatos(Date fechaHoraDatos) {
        this.fechaHoraDatos = fechaHoraDatos;
    }

    public Integer getTriage() {
        return triage;
    }

    public void setTriage(Integer triage) {
        this.triage = triage;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getResumenClinico() {
        return resumenClinico;
    }

    public void setResumenClinico(String resumenClinico) {
        this.resumenClinico = resumenClinico;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public BigDecimal getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(BigDecimal frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public BigDecimal getTensionArtedialDiastole() {
        return tensionArtedialDiastole;
    }

    public void setTensionArtedialDiastole(BigDecimal tensionArtedialDiastole) {
        this.tensionArtedialDiastole = tensionArtedialDiastole;
    }

    public BigDecimal getTensionArterialSistole() {
        return tensionArterialSistole;
    }

    public void setTensionArterialSistole(BigDecimal tensionArterialSistole) {
        this.tensionArterialSistole = tensionArterialSistole;
    }

    public BigDecimal getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(BigDecimal frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public BigDecimal getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(BigDecimal saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public BigDecimal getPerimetroAbdominal() {
        return perimetroAbdominal;
    }

    public void setPerimetroAbdominal(BigDecimal perimetroAbdominal) {
        this.perimetroAbdominal = perimetroAbdominal;
    }

    public BigDecimal getImc() {
        return imc;
    }

    public void setImc(BigDecimal imc) {
        this.imc = imc;
    }

    public String getHallazgosExamenFisico() {
        return hallazgosExamenFisico;
    }

    public void setHallazgosExamenFisico(String hallazgosExamenFisico) {
        this.hallazgosExamenFisico = hallazgosExamenFisico;
    }

    public String getExamenesApoyoDiagnostico() {
        return examenesApoyoDiagnostico;
    }

    public void setExamenesApoyoDiagnostico(String examenesApoyoDiagnostico) {
        this.examenesApoyoDiagnostico = examenesApoyoDiagnostico;
    }

    public String getTratamientoAplicado() {
        return tratamientoAplicado;
    }

    public void setTratamientoAplicado(String tratamientoAplicado) {
        this.tratamientoAplicado = tratamientoAplicado;
    }

    public String getMotivoRemision() {
        return motivoRemision;
    }

    public void setMotivoRemision(String motivoRemision) {
        this.motivoRemision = motivoRemision;
    }

    public Integer getEscalaGlasgow() {
        return escalaGlasgow;
    }

    public void setEscalaGlasgow(Integer escalaGlasgow) {
        this.escalaGlasgow = escalaGlasgow;
    }

    public String getSistemaNeurologico() {
        return sistemaNeurologico;
    }

    public void setSistemaNeurologico(String sistemaNeurologico) {
        this.sistemaNeurologico = sistemaNeurologico;
    }

    public String getSistemaSentidos() {
        return sistemaSentidos;
    }

    public void setSistemaSentidos(String sistemaSentidos) {
        this.sistemaSentidos = sistemaSentidos;
    }

    public String getSistemaCardiobascular() {
        return sistemaCardiobascular;
    }

    public void setSistemaCardiobascular(String sistemaCardiobascular) {
        this.sistemaCardiobascular = sistemaCardiobascular;
    }

    public String getSistemaRespiratorio() {
        return sistemaRespiratorio;
    }

    public void setSistemaRespiratorio(String sistemaRespiratorio) {
        this.sistemaRespiratorio = sistemaRespiratorio;
    }

    public String getSistemaDigestivo() {
        return sistemaDigestivo;
    }

    public void setSistemaDigestivo(String sistemaDigestivo) {
        this.sistemaDigestivo = sistemaDigestivo;
    }

    public String getSistemaGenitoUrinario() {
        return sistemaGenitoUrinario;
    }

    public void setSistemaGenitoUrinario(String sistemaGenitoUrinario) {
        this.sistemaGenitoUrinario = sistemaGenitoUrinario;
    }

    public String getSistemaOsteomuscular() {
        return sistemaOsteomuscular;
    }

    public void setSistemaOsteomuscular(String sistemaOsteomuscular) {
        this.sistemaOsteomuscular = sistemaOsteomuscular;
    }

    public String getObservacionGeneral() {
        return observacionGeneral;
    }

    public void setObservacionGeneral(String observacionGeneral) {
        this.observacionGeneral = observacionGeneral;
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

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
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
        if (!(object instanceof RefAnexo9DatosClinicos)) {
            return false;
        }
        RefAnexo9DatosClinicos other = (RefAnexo9DatosClinicos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefAnexo9DatosClinicos[ id=" + id + " ]";
    }
    
}
