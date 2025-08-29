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
@Table(name = "mp_prescripcion_recobrantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionRecobrantes.findAll", query = "SELECT m FROM MpPrescripcionRecobrantes m"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findById", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFallo", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fallo = :fallo"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFechaFallo", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fechaFallo = :fechaFallo"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFechaPrimeraInstancia", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fechaPrimeraInstancia = :fechaPrimeraInstancia"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFechaSegundaInstancia", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fechaSegundaInstancia = :fechaSegundaInstancia"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFechaCorte", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fechaCorte = :fechaCorte"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFechaDesacato", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fechaDesacato = :fechaDesacato"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotivaPrincipalId", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotivaPrincipalId = :maDiagnosticoMotivaPrincipalId"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotivaPrincipalCodigo", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotivaPrincipalCodigo = :maDiagnosticoMotivaPrincipalCodigo"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotivaPrincipalValor", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotivaPrincipalValor = :maDiagnosticoMotivaPrincipalValor"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotiva2Id", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotiva2Id = :maDiagnosticoMotiva2Id"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotiva2Codigo", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotiva2Codigo = :maDiagnosticoMotiva2Codigo"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotiva2Valor", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotiva2Valor = :maDiagnosticoMotiva2Valor"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotiva3Id", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotiva3Id = :maDiagnosticoMotiva3Id"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotiva3Codigo", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotiva3Codigo = :maDiagnosticoMotiva3Codigo"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByMaDiagnosticoMotiva3Valor", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.maDiagnosticoMotiva3Valor = :maDiagnosticoMotiva3Valor"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByCriterio1Corte", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.criterio1Corte = :criterio1Corte"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByCriterio2Corte", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.criterio2Corte = :criterio2Corte"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByCriterio3Corte", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.criterio3Corte = :criterio3Corte"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByCriterio4Corte", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.criterio4Corte = :criterio4Corte"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByAclaracionFallo", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.aclaracionFallo = :aclaracionFallo"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByJustificacionMedica", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.justificacionMedica = :justificacionMedica"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByTipoTutela", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.tipoTutela = :tipoTutela"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionRecobrantes.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionRecobrantes m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpPrescripcionRecobrantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "fallo")
    private String fallo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fallo")
    @Temporal(TemporalType.DATE)
    private Date fechaFallo;
    @Column(name = "fecha_primera_instancia")
    @Temporal(TemporalType.DATE)
    private Date fechaPrimeraInstancia;
    @Column(name = "fecha_segunda_instancia")
    @Temporal(TemporalType.DATE)
    private Date fechaSegundaInstancia;
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;
    @Column(name = "fecha_desacato")
    @Temporal(TemporalType.DATE)
    private Date fechaDesacato;
    @Column(name = "ma_diagnostico_motiva_principal_id")
    private Integer maDiagnosticoMotivaPrincipalId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_motiva_principal_codigo")
    private String maDiagnosticoMotivaPrincipalCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_motiva_principal_valor")
    private String maDiagnosticoMotivaPrincipalValor;
    @Column(name = "ma_diagnostico_motiva_2_id")
    private Integer maDiagnosticoMotiva2Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_motiva_2_codigo")
    private String maDiagnosticoMotiva2Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_motiva_2_valor")
    private String maDiagnosticoMotiva2Valor;
    @Column(name = "ma_diagnostico_motiva_3_id")
    private Integer maDiagnosticoMotiva3Id;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_motiva_3_codigo")
    private String maDiagnosticoMotiva3Codigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_motiva_3_valor")
    private String maDiagnosticoMotiva3Valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criterio_1_corte")
    private boolean criterio1Corte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criterio_2_corte")
    private boolean criterio2Corte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criterio_3_corte")
    private boolean criterio3Corte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criterio_4_corte")
    private boolean criterio4Corte;
    @Size(max = 254)
    @Column(name = "aclaracion_fallo")
    private String aclaracionFallo;
    @Size(max = 2048)
    @Column(name = "justificacion_medica")
    private String justificacionMedica;
    @Size(max = 48)
    @Column(name = "tipo_tutela")
    private String tipoTutela;
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
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;

    public MpPrescripcionRecobrantes() {
    }

    public MpPrescripcionRecobrantes(Integer id) {
        this.id = id;
    }

    public MpPrescripcionRecobrantes(Integer id, String fallo, Date fechaFallo, boolean criterio1Corte, boolean criterio2Corte, boolean criterio3Corte, boolean criterio4Corte, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fallo = fallo;
        this.fechaFallo = fechaFallo;
        this.criterio1Corte = criterio1Corte;
        this.criterio2Corte = criterio2Corte;
        this.criterio3Corte = criterio3Corte;
        this.criterio4Corte = criterio4Corte;
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

    public String getFallo() {
        return fallo;
    }

    public void setFallo(String fallo) {
        this.fallo = fallo;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public Date getFechaPrimeraInstancia() {
        return fechaPrimeraInstancia;
    }

    public void setFechaPrimeraInstancia(Date fechaPrimeraInstancia) {
        this.fechaPrimeraInstancia = fechaPrimeraInstancia;
    }

    public Date getFechaSegundaInstancia() {
        return fechaSegundaInstancia;
    }

    public void setFechaSegundaInstancia(Date fechaSegundaInstancia) {
        this.fechaSegundaInstancia = fechaSegundaInstancia;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Date getFechaDesacato() {
        return fechaDesacato;
    }

    public void setFechaDesacato(Date fechaDesacato) {
        this.fechaDesacato = fechaDesacato;
    }

    public Integer getMaDiagnosticoMotivaPrincipalId() {
        return maDiagnosticoMotivaPrincipalId;
    }

    public void setMaDiagnosticoMotivaPrincipalId(Integer maDiagnosticoMotivaPrincipalId) {
        this.maDiagnosticoMotivaPrincipalId = maDiagnosticoMotivaPrincipalId;
    }

    public String getMaDiagnosticoMotivaPrincipalCodigo() {
        return maDiagnosticoMotivaPrincipalCodigo;
    }

    public void setMaDiagnosticoMotivaPrincipalCodigo(String maDiagnosticoMotivaPrincipalCodigo) {
        this.maDiagnosticoMotivaPrincipalCodigo = maDiagnosticoMotivaPrincipalCodigo;
    }

    public String getMaDiagnosticoMotivaPrincipalValor() {
        return maDiagnosticoMotivaPrincipalValor;
    }

    public void setMaDiagnosticoMotivaPrincipalValor(String maDiagnosticoMotivaPrincipalValor) {
        this.maDiagnosticoMotivaPrincipalValor = maDiagnosticoMotivaPrincipalValor;
    }

    public Integer getMaDiagnosticoMotiva2Id() {
        return maDiagnosticoMotiva2Id;
    }

    public void setMaDiagnosticoMotiva2Id(Integer maDiagnosticoMotiva2Id) {
        this.maDiagnosticoMotiva2Id = maDiagnosticoMotiva2Id;
    }

    public String getMaDiagnosticoMotiva2Codigo() {
        return maDiagnosticoMotiva2Codigo;
    }

    public void setMaDiagnosticoMotiva2Codigo(String maDiagnosticoMotiva2Codigo) {
        this.maDiagnosticoMotiva2Codigo = maDiagnosticoMotiva2Codigo;
    }

    public String getMaDiagnosticoMotiva2Valor() {
        return maDiagnosticoMotiva2Valor;
    }

    public void setMaDiagnosticoMotiva2Valor(String maDiagnosticoMotiva2Valor) {
        this.maDiagnosticoMotiva2Valor = maDiagnosticoMotiva2Valor;
    }

    public Integer getMaDiagnosticoMotiva3Id() {
        return maDiagnosticoMotiva3Id;
    }

    public void setMaDiagnosticoMotiva3Id(Integer maDiagnosticoMotiva3Id) {
        this.maDiagnosticoMotiva3Id = maDiagnosticoMotiva3Id;
    }

    public String getMaDiagnosticoMotiva3Codigo() {
        return maDiagnosticoMotiva3Codigo;
    }

    public void setMaDiagnosticoMotiva3Codigo(String maDiagnosticoMotiva3Codigo) {
        this.maDiagnosticoMotiva3Codigo = maDiagnosticoMotiva3Codigo;
    }

    public String getMaDiagnosticoMotiva3Valor() {
        return maDiagnosticoMotiva3Valor;
    }

    public void setMaDiagnosticoMotiva3Valor(String maDiagnosticoMotiva3Valor) {
        this.maDiagnosticoMotiva3Valor = maDiagnosticoMotiva3Valor;
    }

    public boolean getCriterio1Corte() {
        return criterio1Corte;
    }

    public void setCriterio1Corte(boolean criterio1Corte) {
        this.criterio1Corte = criterio1Corte;
    }

    public boolean getCriterio2Corte() {
        return criterio2Corte;
    }

    public void setCriterio2Corte(boolean criterio2Corte) {
        this.criterio2Corte = criterio2Corte;
    }

    public boolean getCriterio3Corte() {
        return criterio3Corte;
    }

    public void setCriterio3Corte(boolean criterio3Corte) {
        this.criterio3Corte = criterio3Corte;
    }

    public boolean getCriterio4Corte() {
        return criterio4Corte;
    }

    public void setCriterio4Corte(boolean criterio4Corte) {
        this.criterio4Corte = criterio4Corte;
    }

    public String getAclaracionFallo() {
        return aclaracionFallo;
    }

    public void setAclaracionFallo(String aclaracionFallo) {
        this.aclaracionFallo = aclaracionFallo;
    }

    public String getJustificacionMedica() {
        return justificacionMedica;
    }

    public void setJustificacionMedica(String justificacionMedica) {
        this.justificacionMedica = justificacionMedica;
    }

    public String getTipoTutela() {
        return tipoTutela;
    }

    public void setTipoTutela(String tipoTutela) {
        this.tipoTutela = tipoTutela;
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

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
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
        if (!(object instanceof MpPrescripcionRecobrantes)) {
            return false;
        }
        MpPrescripcionRecobrantes other = (MpPrescripcionRecobrantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionRecobrantes[ id=" + id + " ]";
    }
    
}
