package com.saviasaludeps.savia.dominio.contratacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CntContrato extends Auditoria implements Cloneable {

    private Integer id;
    private String negociacion;
    private String contrato;
    private String descripcion;
    private boolean activo;
    private int maeEstadoContratoId;
    private String maeEstadoContratoCodigo;
    private String maeEstadoContratoValor;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal valor;
    //2021-06-11 jyperez Sprint 1 se cambia valorTope por valorMes
    //private BigDecimal valorTope;
    private BigDecimal valorMes;
    private BigDecimal valorPresupuestoTotal;
    private Integer diasLimitePago;
    //2023-06-21 jyperez nuevo campo
    private boolean autorizaGestion;
    //nuevos campos
    private Integer numAfiliados;
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private CntPrestador cntPrestador;
    private List<CntContratoDescuento> listaContratoDescuentos;
    private List<AuAnexo4> listaAuAnexos4;
    private List<CntContratoAdjunto> listaContratoAdjuntos;
    private List<CntContratoSede> listaContratoSedes;
    private List<CntContratoJuridico> listaContratoJuridicos;
//   private List<CntContratoGiroCapita> listaContratoGiroCapitas;
    private List<CntContratoDetalle> listaContratoDetalles;
//    private List<CmAuditoriaDescuentoCapita> listaAuditoriaDescuentoCapitas;
    private Empresa gnEmpresa;
    private CntContrato cntContrato;

    //
    private BigDecimal ejecucionContratoAutorizado;
    private Double porcentajeEjecucionContratoAutorizado;
    private BigDecimal ejecucionContratoPrestado;
    private BigDecimal ejecucionTotalContrato;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (CntContrato) super.clone();
    }

    public CntContrato() {

    }

    public CntContrato(Integer id) {
        this.id = id;
    }

    public CntContrato(Integer id, String contrato) {
        this.id = id;
        this.contrato = contrato;
    }

    public CntContrato(Integer id, String contrato, String maeEstadoContratoValor) {
        this.id = id;
        this.contrato = contrato;
        this.maeEstadoContratoValor = maeEstadoContratoValor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNegociacion() {
        return negociacion;
    }

    public void setNegociacion(String negociacion) {
        this.negociacion = negociacion;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return activo ? "Si" : "No";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMaeEstadoContratoId() {
        return maeEstadoContratoId;
    }

    public void setMaeEstadoContratoId(int maeEstadoContratoId) {
        this.maeEstadoContratoId = maeEstadoContratoId;
    }

    public String getMaeEstadoContratoCodigo() {
        return maeEstadoContratoCodigo;
    }

    public void setMaeEstadoContratoCodigo(String maeEstadoContratoCodigo) {
        this.maeEstadoContratoCodigo = maeEstadoContratoCodigo;
    }

    public String getMaeEstadoContratoValor() {
        return maeEstadoContratoValor;
    }

    public void setMaeEstadoContratoValor(String maeEstadoContratoValor) {
        this.maeEstadoContratoValor = maeEstadoContratoValor;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorPresupuestoTotal() {
        return valorPresupuestoTotal;
    }

    public void setValorPresupuestoTotal(BigDecimal valorPresupuestoTotal) {
        this.valorPresupuestoTotal = valorPresupuestoTotal;
    }

    public Integer getDiasLimitePago() {
        return diasLimitePago;
    }

    public void setDiasLimitePago(Integer diasLimitePago) {
        this.diasLimitePago = diasLimitePago;
    }

    public CntPrestador getCntPrestador() {
        if (cntPrestador == null) {
            cntPrestador = new CntPrestador();
        }
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    public List<CntContratoDescuento> getListaContratoDescuentos() {
        return listaContratoDescuentos;
    }

    public void setListaContratoDescuentos(List<CntContratoDescuento> listaContratoDescuentos) {
        this.listaContratoDescuentos = listaContratoDescuentos;
    }

    public List<AuAnexo4> getListaAuAnexos4() {
        return listaAuAnexos4;
    }

    public void setListaAuAnexos4(List<AuAnexo4> listaAuAnexos4) {
        this.listaAuAnexos4 = listaAuAnexos4;
    }

    public List<CntContratoAdjunto> getListaContratoAdjuntos() {
        return listaContratoAdjuntos;
    }

    public void setListaContratoAdjuntos(List<CntContratoAdjunto> listaContratoAdjuntos) {
        this.listaContratoAdjuntos = listaContratoAdjuntos;
    }

    public List<CntContratoSede> getListaContratoSedes() {
        return listaContratoSedes;
    }

    public void setListaContratoSedes(List<CntContratoSede> listaContratoSedes) {
        this.listaContratoSedes = listaContratoSedes;
    }

    public List<CntContratoJuridico> getListaContratoJuridicos() {
        return listaContratoJuridicos;
    }

    public void setListaContratoJuridicos(List<CntContratoJuridico> listaContratoJuridicos) {
        this.listaContratoJuridicos = listaContratoJuridicos;
    }

    public List<CntContratoDetalle> getListaContratoDetalles() {
        return listaContratoDetalles;
    }

    public void setListaContratoDetalles(List<CntContratoDetalle> listaContratoDetalles) {
        this.listaContratoDetalles = listaContratoDetalles;
    }

    /**
     * @return the gnEmpresa
     */
    public Empresa getGnEmpresa() {
        return gnEmpresa;
    }

    /**
     * @param gnEmpresa the gnEmpresa to set
     */
    public void setGnEmpresa(Empresa gnEmpresa) {
        this.gnEmpresa = gnEmpresa;
    }

    public CntContrato getCntContrato() {
        return cntContrato;
    }

    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    public BigDecimal getEjecucionContratoAutorizado() {
        return ejecucionContratoAutorizado;
    }

    public void setEjecucionContratoAutorizado(BigDecimal ejecucionContratoAutorizado) {
        this.ejecucionContratoAutorizado = ejecucionContratoAutorizado;
    }

    public Double getPorcentajeEjecucionContratoAutorizado() {
        return porcentajeEjecucionContratoAutorizado;
    }

    public void setPorcentajeEjecucionContratoAutorizado(Double porcentajeEjecucionContratoAutorizado) {
        this.porcentajeEjecucionContratoAutorizado = porcentajeEjecucionContratoAutorizado;
    }

    public BigDecimal getEjecucionContratoPrestado() {
        return ejecucionContratoPrestado;
    }

    public void setEjecucionContratoPrestado(BigDecimal ejecucionContratoPrestado) {
        this.ejecucionContratoPrestado = ejecucionContratoPrestado;
    }

    public BigDecimal getEjecucionTotalContrato() {
        return ejecucionTotalContrato;
    }

    public void setEjecucionTotalContrato(BigDecimal ejecucionTotalContrato) {
        this.ejecucionTotalContrato = ejecucionTotalContrato;
    }

    /**
     * @return the valorMes
     */
    public BigDecimal getValorMes() {
        return valorMes;
    }

    /**
     * @param valorMes the valorMes to set
     */
    public void setValorMes(BigDecimal valorMes) {
        this.valorMes = valorMes;
    }

    /**
     * @return the numAfiliados
     */
    public Integer getNumAfiliados() {
        return numAfiliados;
    }

    /**
     * @param numAfiliados the numAfiliados to set
     */
    public void setNumAfiliados(Integer numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    /**
     * @return the maeRegimenId
     */
    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    /**
     * @param maeRegimenId the maeRegimenId to set
     */
    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    /**
     * @return the maeRegimenCodigo
     */
    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    /**
     * @param maeRegimenCodigo the maeRegimenCodigo to set
     */
    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    /**
     * @return the maeRegimenValor
     */
    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    /**
     * @param maeRegimenValor the maeRegimenValor to set
     */
    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    @Override
    public String toString() {
        return "CntContrato{" + "id=" + id + ", negociacion=" + negociacion + ", contrato=" + contrato + ", descripcion=" + descripcion + ", activo=" + activo + ", maeEstadoContratoId=" + maeEstadoContratoId + ", maeEstadoContratoCodigo=" + maeEstadoContratoCodigo + ", maeEstadoContratoValor=" + maeEstadoContratoValor + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valor=" + valor + ", valorMes=" + getValorMes() + ", valorPresupuestoTotal=" + valorPresupuestoTotal + ", diasLimitePago=" + diasLimitePago + ", numAfiliados=" + numAfiliados + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", cntPrestador=" + cntPrestador + ", listaContratoSedes=" + listaContratoSedes + ", gnEmpresa=" + gnEmpresa + '}';
    }

    // 2022-07-28 cmartins - Se comenta toStringJson ya que se utiliza en DTOCntContratoDetalle
//    public String toStringJson() {
//        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
//        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
//        gsonBuilderRespuesta.serializeNulls();
//        Gson gson = gsonBuilderRespuesta.create();
////2022-04-26 (rpalacic)Clonar clase y ajustar datos
//        CntContrato clonContrato;
//        try {
//            clonContrato = (CntContrato) this.clone();
//            clonContrato.setCntPrestador(null);
//            clonContrato.setGnEmpresa(null);
//            clonContrato.setListaAuAnexos4(null);
//            clonContrato.setListaContratoAdjuntos(null);
//            clonContrato.setListaContratoDescuentos(null);
//            clonContrato.setListaContratoJuridicos(null);
//            clonContrato.setListaContratoDetalles(null);
//        } catch (CloneNotSupportedException ex) {
//            clonContrato = this;
//        }        
//        return gson.toJson(clonContrato);
//    }
    /**
     * @return the autorizaGestion
     */
    public boolean getAutorizaGestion() {
        return autorizaGestion;
    }

    /**
     * @param autorizaGestion the autorizaGestion to set
     */
    public void setAutorizaGestion(boolean autorizaGestion) {
        this.autorizaGestion = autorizaGestion;
    }
}
