package com.saviasaludeps.savia.dominio.contratacion;

import com.google.gson.annotations.SerializedName;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

public class DTOCntContratoSede extends Auditoria{

    @SerializedName(value = "id")
    private Integer id;
    @SerializedName(value = "maeModalidadContratoId")
    private int maeModalidadContratoId;
    @SerializedName(value = "maeModalidadContratoCodigo")
    private String maeModalidadContratoCodigo;
    @SerializedName(value = "maeModalidadContratoValor")
    private String maeModalidadContratoValor;
    @SerializedName(value = "valorUpcAfiliado")
    private BigDecimal valorUpcAfiliado;
    @SerializedName(value = "aplicaSubsidiado")
    private Boolean aplicaSubsidiado;
    @SerializedName(value = "aplicaContribuitivo")
    private Boolean aplicaContribuitivo;
    @SerializedName(value = "aplicaGlosaExtemporanea")
    private Boolean aplicaGlosaExtemporanea;
    @SerializedName(value = "aplicaAuditoria")
    private Boolean aplicaAuditoria;
    @SerializedName(value = "aplicaPortabilidad")
    private Boolean aplicaPortabilidad;
    @SerializedName(value = "aplicaRecaudoCopagosIps")
    private Boolean aplicaRecaudoCopagosIps;
    //Campos cntPrestadorSede
    @SerializedName(value = "idPrestadorSede")
    private Integer idPrestadorSede;
    @SerializedName(value = "nombreSede")
    private String nombreSede;
    
    //private CntPrestadorSede cntPrestadorSede;

// campos Contratacion
    //private boolean nuevoRegistro;
    //private boolean editado;
    public DTOCntContratoSede() {
    }

    public DTOCntContratoSede(CntContratoSede cntContratoSede) {
        this.id = cntContratoSede.getId();
        this.maeModalidadContratoId = cntContratoSede.getMaeModalidadContratoId();
        this.maeModalidadContratoCodigo = cntContratoSede.getMaeModalidadContratoCodigo();
        this.maeModalidadContratoValor = cntContratoSede.getMaeModalidadContratoValor();
        this.valorUpcAfiliado = cntContratoSede.getValorUpcAfiliado();
        this.aplicaSubsidiado = cntContratoSede.getAplicaSubsidiado();
        this.aplicaContribuitivo = cntContratoSede.getAplicaContribuitivo();
        this.aplicaGlosaExtemporanea = cntContratoSede.getAplicaGlosaExtemporanea();
        this.aplicaAuditoria = cntContratoSede.getAplicaAuditoria();
        this.aplicaPortabilidad = cntContratoSede.getAplicaPortabilidad();
        this.aplicaRecaudoCopagosIps = cntContratoSede.getAplicaRecaudoCopagosIps();
        this.idPrestadorSede = cntContratoSede.getCntPrestadorSede().getId();
        this.nombreSede = cntContratoSede.getCntPrestadorSede().getNombreSede();
        this.usuarioCrea = cntContratoSede.getUsuarioCrea();
        this.terminalCrea = cntContratoSede.getTerminalCrea();
        this.fechaHoraCrea = cntContratoSede.getFechaHoraCrea();
        this.usuarioModifica = cntContratoSede.getUsuarioModifica();
        this.terminalModifica = cntContratoSede.getTerminalModifica();
        this.fechaHoraModifica = cntContratoSede.getFechaHoraModifica();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeModalidadContratoId() {
        return maeModalidadContratoId;
    }

    public void setMaeModalidadContratoId(int maeModalidadContratoId) {
        this.maeModalidadContratoId = maeModalidadContratoId;
    }

    public String getMaeModalidadContratoCodigo() {
        return maeModalidadContratoCodigo;
    }

    public void setMaeModalidadContratoCodigo(String maeModalidadContratoCodigo) {
        this.maeModalidadContratoCodigo = maeModalidadContratoCodigo;
    }

    public String getMaeModalidadContratoValor() {
        return maeModalidadContratoValor;
    }

    public void setMaeModalidadContratoValor(String maeModalidadContratoValor) {
        this.maeModalidadContratoValor = maeModalidadContratoValor;
    }

    public BigDecimal getValorUpcAfiliado() {
        return valorUpcAfiliado;
    }

    public void setValorUpcAfiliado(BigDecimal valorUpcAfiliado) {
        this.valorUpcAfiliado = valorUpcAfiliado;
    }

    public Boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(Boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public Boolean getAplicaContribuitivo() {
        return aplicaContribuitivo;
    }

    public void setAplicaContribuitivo(Boolean aplicaContribuitivo) {
        this.aplicaContribuitivo = aplicaContribuitivo;
    }

    public Boolean getAplicaGlosaExtemporanea() {
        return aplicaGlosaExtemporanea;
    }

    public void setAplicaGlosaExtemporanea(Boolean aplicaGlosaExtemporanea) {
        this.aplicaGlosaExtemporanea = aplicaGlosaExtemporanea;
    }

    public Boolean getAplicaAuditoria() {
        return aplicaAuditoria;
    }

    public void setAplicaAuditoria(Boolean aplicaAuditoria) {
        this.aplicaAuditoria = aplicaAuditoria;
    }

    public Boolean getAplicaPortabilidad() {
        return aplicaPortabilidad;
    }

    public void setAplicaPortabilidad(Boolean aplicaPortabilidad) {
        this.aplicaPortabilidad = aplicaPortabilidad;
    }

    public Boolean getAplicaRecaudoCopagosIps() {
        return aplicaRecaudoCopagosIps;
    }

    public void setAplicaRecaudoCopagosIps(Boolean aplicaRecaudoCopagosIps) {
        this.aplicaRecaudoCopagosIps = aplicaRecaudoCopagosIps;
    }

    public Integer getIdPrestadorSede() {
        return idPrestadorSede;
    }

    public void setIdPrestadorSede(Integer idPrestadorSede) {
        this.idPrestadorSede = idPrestadorSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    @Override
    public String toString() {
        return "DTOCntContratoSede{" + "id=" + id + ", maeModalidadContratoId=" + maeModalidadContratoId + ", maeModalidadContratoCodigo=" + maeModalidadContratoCodigo + ", maeModalidadContratoValor=" + maeModalidadContratoValor + ", valorUpcAfiliado=" + valorUpcAfiliado + ", aplicaSubsidiado=" + aplicaSubsidiado + ", aplicaContribuitivo=" + aplicaContribuitivo + ", aplicaGlosaExtemporanea=" + aplicaGlosaExtemporanea + ", aplicaAuditoria=" + aplicaAuditoria + ", aplicaPortabilidad=" + aplicaPortabilidad + ", aplicaRecaudoCopagosIps=" + aplicaRecaudoCopagosIps + ", idPrestadorSede=" + idPrestadorSede + ", nombreSede=" + nombreSede + '}';
    }
}
