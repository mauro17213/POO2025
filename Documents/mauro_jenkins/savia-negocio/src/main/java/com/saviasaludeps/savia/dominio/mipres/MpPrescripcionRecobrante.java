
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;



public class MpPrescripcionRecobrante extends Auditoria {
    
    private Integer id;
    private String fallo;
     private String tipoTutela;
    private Date fechaFallo;
    private Date fechaPrimeraInstancia;
    private Date fechaSegundaInstancia;
    private Date fechaCorte;
    private Date fechaDesacato;
    private int maDiagnosticoMotivaPrincipalId;
    private String maDiagnosticoMotivaPrincipalCodigo;
    private String maDiagnosticoMotivaPrincipalValor;
    private Integer maDiagnosticoMotiva2Id;
    private String maDiagnosticoMotiva2Codigo;
    private String maDiagnosticoMotiva2Valor;
    private Integer maDiagnosticoMotiva3Id;
    private String maDiagnosticoMotiva3Codigo;
    private String maDiagnosticoMotiva3Valor;
    private boolean criterio1Corte;
    private boolean criterio2Corte;
    private boolean criterio3Corte;
    private boolean criterio4Corte;
    private String aclaracionFallo;
    private String justificacionMedica;
    private MpPrescripcion mpPrescripcionId;

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

    public String getTipoTutela() {
        return tipoTutela;
    }

    public void setTipoTutela(String tipoTutela) {
        this.tipoTutela = tipoTutela;
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

    public int getMaDiagnosticoMotivaPrincipalId() {
        return maDiagnosticoMotivaPrincipalId;
    }

    public void setMaDiagnosticoMotivaPrincipalId(int maDiagnosticoMotivaPrincipalId) {
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

    public boolean isCriterio1Corte() {
        return criterio1Corte;
    }

    public void setCriterio1Corte(boolean criterio1Corte) {
        this.criterio1Corte = criterio1Corte;
    }

    public boolean isCriterio2Corte() {
        return criterio2Corte;
    }

    public void setCriterio2Corte(boolean criterio2Corte) {
        this.criterio2Corte = criterio2Corte;
    }

    public boolean isCriterio3Corte() {
        return criterio3Corte;
    }

    public void setCriterio3Corte(boolean criterio3Corte) {
        this.criterio3Corte = criterio3Corte;
    }

    public boolean isCriterio4Corte() {
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

    public MpPrescripcion getMpPrescripcionId() {
        return mpPrescripcionId;
    }

    public void setMpPrescripcionId(MpPrescripcion mpPrescripcionId) {
        this.mpPrescripcionId = mpPrescripcionId;
    }

    @Override
    public String toString() {
        return "MpPrescripcionRecobrante{" + "id=" + id + ", fallo=" + fallo + ", fechaFallo=" + fechaFallo + ", fechaPrimeraInstancia=" + fechaPrimeraInstancia + ", fechaSegundaInstancia=" + fechaSegundaInstancia + ", fechaCorte=" + fechaCorte + ", fechaDesacato=" + fechaDesacato + ", maDiagnosticoMotivaPrincipalId=" + maDiagnosticoMotivaPrincipalId + ", maDiagnosticoMotivaPrincipalCodigo=" + maDiagnosticoMotivaPrincipalCodigo + ", maDiagnosticoMotivaPrincipalValor=" + maDiagnosticoMotivaPrincipalValor + ", maDiagnosticoMotiva2Id=" + maDiagnosticoMotiva2Id + ", maDiagnosticoMotiva2Codigo=" + maDiagnosticoMotiva2Codigo + ", maDiagnosticoMotiva2Valor=" + maDiagnosticoMotiva2Valor + ", maDiagnosticoMotiva3Id=" + maDiagnosticoMotiva3Id + ", maDiagnosticoMotiva3Codigo=" + maDiagnosticoMotiva3Codigo + ", maDiagnosticoMotiva3Valor=" + maDiagnosticoMotiva3Valor + ", criterio1Corte=" + criterio1Corte + ", criterio2Corte=" + criterio2Corte + ", criterio3Corte=" + criterio3Corte + ", criterio4Corte=" + criterio4Corte + ", aclaracionFallo=" + aclaracionFallo + ", justificacionMedica=" + justificacionMedica + ", mpPrescripcionId=" + mpPrescripcionId + '}';
    }
    
}
