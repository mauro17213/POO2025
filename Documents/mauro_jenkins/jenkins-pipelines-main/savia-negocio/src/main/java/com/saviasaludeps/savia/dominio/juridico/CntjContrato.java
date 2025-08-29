package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public class CntjContrato extends Auditoria {

    private Integer id;
    private Integer maeTipoContratoId;
    private String maeTipoContratoCodigo;
    private String maeTipoContratoValor;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal valorInicial;
    private BigDecimal valorPagadoTotal;
    private BigDecimal valorContratoMasAdiciones;
    private BigDecimal valorTotalOtrosies;
    private String contrato;
    private Integer maeEstadoContratoId;
    private String maeEstadoContratoCodigo;
    private String maeEstadoContratoValor;
    private String proceso;
    private Integer maeClaseContratoId;
    private String maeClaseContratoCodigo;
    private String maeClaseContratoValor;
    private Integer estadoLegalizacion;
    private Integer maeModalidadContratoId;
    private String maeModalidadContratoCodigo;
    private String maeModalidadContratoValor;
    private Integer complejidad;
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private String plazoInicialMeses;
    private String plazoInicialDias;
    private Integer plazoProrrogas;
    private Integer plazoTotalDias;
    private BigDecimal valorMes;
    private BigDecimal valorDia;
    private BigDecimal valorUpc;
    private BigDecimal valorAdiciones;
    private BigDecimal valorTotal;
    private Integer formaPago;
    private Integer tipoAnticipo;
    private BigDecimal valorAnticipo;
    private List<CntjContratoIndicador> cntjContratoIndicadorList;
    private List<CntjContratoObligacion> cntjContratoObligacionList;
    private List<CntjContratoGarantia> cntjContratoGarantiaList;
    private CntjExpediente cntjExpedienteId;
    private CntjTercero cntjTerceroId;
    private List<CntjContratoHistorico> cntjContratoHistoricoList;
    private List<CntjDocumento> cntjDocumentosList;
    private List<CntjContratoSupervisor> cntjContratoSupervisorList;
    private List<CntjContratoSeguimiento> cntjContratoSeguimientoList;
    private List<CntjOtrosie> cntjOtrosieList;
    private List<CntjContratoInforme> cntjContratoInformeList;
    private String objeto;
    private Date fechaSuscripcion;
    private Integer tipoGasto;
    private Date fechaSuspension;
    private String motivoSuspension;
    private Integer periodicidadSeguimiento;
    
    private String motivoTerminaAnticipada;
    private String enlacePublicaSecop;
    private Date fechaPublicaSecop;
    private String responsablePublicaSecop;
    private Date fechaLiquidacion;
    private BigDecimal valorFacturado;
    
    
    public CntjContrato() {
        this.cntjTerceroId = new CntjTercero();
        this.cntjContratoSupervisorList = new ArrayList<>();
        this.cntjContratoGarantiaList = new ArrayList<>();
        this.cntjContratoIndicadorList = new ArrayList<>();        
        this.cntjContratoObligacionList = new ArrayList<>();        
        this.cntjContratoSeguimientoList = new ArrayList<>();        
    }

    public CntjContrato(Integer id) {
        this.id = id;
        this.cntjTerceroId = new CntjTercero();
        this.cntjContratoSupervisorList = new ArrayList<>();
        this.cntjContratoGarantiaList = new ArrayList<>();
        this.cntjContratoIndicadorList = new ArrayList<>();        
        this.cntjContratoObligacionList = new ArrayList<>();        
        this.cntjContratoSeguimientoList = new ArrayList<>(); 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(Integer maeTipoContratoId) {
        this.maeTipoContratoId = maeTipoContratoId;
    }

    public String getMaeTipoContratoCodigo() {
        return maeTipoContratoCodigo;
    }

    public void setMaeTipoContratoCodigo(String maeTipoContratoCodigo) {
        this.maeTipoContratoCodigo = maeTipoContratoCodigo;
    }

    public String getMaeTipoContratoValor() {
        return maeTipoContratoValor;
    }

    public void setMaeTipoContratoValor(String maeTipoContratoValor) {
        this.maeTipoContratoValor = maeTipoContratoValor;
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

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorPagadoTotal() {
        return valorPagadoTotal;
    }

    public void setValorPagadoTotal(BigDecimal valorPagadoTotal) {
        this.valorPagadoTotal = valorPagadoTotal;
    }

    public BigDecimal getValorContratoMasAdiciones() {
        return valorContratoMasAdiciones;
    }

    public void setValorContratoMasAdiciones(BigDecimal valorContratoMasAdiciones) {
        this.valorContratoMasAdiciones = valorContratoMasAdiciones;
    }

    public BigDecimal getValorTotalOtrosies() {
        return valorTotalOtrosies;
    }

    public void setValorTotalOtrosies(BigDecimal valorTotalOtrosies) {
        this.valorTotalOtrosies = valorTotalOtrosies;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getMaeEstadoContratoId() {
        return maeEstadoContratoId;
    }

    public void setMaeEstadoContratoId(Integer maeEstadoContratoId) {
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

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Integer getMaeClaseContratoId() {
        return maeClaseContratoId;
    }

    public void setMaeClaseContratoId(Integer maeClaseContratoId) {
        this.maeClaseContratoId = maeClaseContratoId;
    }

    public String getMaeClaseContratoCodigo() {
        return maeClaseContratoCodigo;
    }

    public void setMaeClaseContratoCodigo(String maeClaseContratoCodigo) {
        this.maeClaseContratoCodigo = maeClaseContratoCodigo;
    }

    public String getMaeClaseContratoValor() {
        return maeClaseContratoValor;
    }

    public void setMaeClaseContratoValor(String maeClaseContratoValor) {
        this.maeClaseContratoValor = maeClaseContratoValor;
    }

    public Integer getEstadoLegalizacion() {
        return estadoLegalizacion;
    }

    public void setEstadoLegalizacion(Integer estadoLegalizacion) {
        this.estadoLegalizacion = estadoLegalizacion;
    }

    public Integer getMaeModalidadContratoId() {
        return maeModalidadContratoId;
    }

    public void setMaeModalidadContratoId(Integer maeModalidadContratoId) {
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

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public String getPlazoInicialMeses() {
        return plazoInicialMeses;
    }

    public void setPlazoInicialMeses(String plazoInicialMeses) {
        this.plazoInicialMeses = plazoInicialMeses;
    }

    public String getPlazoInicialDias() {
        return plazoInicialDias;
    }

    public void setPlazoInicialDias(String plazoInicialDias) {
        this.plazoInicialDias = plazoInicialDias;
    }

    public Integer getPlazoProrrogas() {
        return plazoProrrogas;
    }

    public void setPlazoProrrogas(Integer plazoProrrogas) {
        this.plazoProrrogas = plazoProrrogas;
    }

    public Integer getPlazoTotalDias() {
        return plazoTotalDias;
    }

    public void setPlazoTotalDias(Integer plazoTotalDias) {
        this.plazoTotalDias = plazoTotalDias;
    }

    public BigDecimal getValorMes() {
        return valorMes;
    }

    public void setValorMes(BigDecimal valorMes) {
        this.valorMes = valorMes;
    }

    public BigDecimal getValorDia() {
        return valorDia;
    }

    public void setValorDia(BigDecimal valorDia) {
        this.valorDia = valorDia;
    }

    public BigDecimal getValorUpc() {
        return valorUpc;
    }

    public void setValorUpc(BigDecimal valorUpc) {
        this.valorUpc = valorUpc;
    }

    public BigDecimal getValorAdiciones() {
        return valorAdiciones;
    }

    public void setValorAdiciones(BigDecimal valorAdiciones) {
        this.valorAdiciones = valorAdiciones;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getTipoAnticipo() {
        return tipoAnticipo;
    }

    public void setTipoAnticipo(Integer tipoAnticipo) {
        this.tipoAnticipo = tipoAnticipo;
    }

    public BigDecimal getValorAnticipo() {
        return valorAnticipo;
    }

    public void setValorAnticipo(BigDecimal valorAnticipo) {
        this.valorAnticipo = valorAnticipo;
    }

    public List<CntjContratoIndicador> getCntjContratoIndicadorList() {
        return cntjContratoIndicadorList;
    }

    public void setCntjContratoIndicadorList(List<CntjContratoIndicador> cntjContratoIndicadorList) {
        this.cntjContratoIndicadorList = cntjContratoIndicadorList;
    }

    public List<CntjContratoObligacion> getCntjContratoObligacionList() {
        return cntjContratoObligacionList;
    }

    public void setCntjContratoObligacionList(List<CntjContratoObligacion> cntjContratoObligacionList) {
        this.cntjContratoObligacionList = cntjContratoObligacionList;
    }

    public List<CntjContratoGarantia> getCntjContratoGarantiaList() {
        return cntjContratoGarantiaList;
    }

    public void setCntjContratoGarantiaList(List<CntjContratoGarantia> cntjContratoGarantiaList) {
        this.cntjContratoGarantiaList = cntjContratoGarantiaList;
    }

    public CntjExpediente getCntjExpedienteId() {
        return cntjExpedienteId;
    }

    public void setCntjExpedienteId(CntjExpediente cntjExpedienteId) {
        this.cntjExpedienteId = cntjExpedienteId;
    }

    public CntjTercero getCntjTerceroId() {
        return cntjTerceroId;
    }

    public void setCntjTerceroId(CntjTercero cntjTerceroId) {
        this.cntjTerceroId = cntjTerceroId;
    }

    public List<CntjContratoHistorico> getCntjContratoHistoricoList() {
        return cntjContratoHistoricoList;
    }

    public void setCntjContratoHistoricoList(List<CntjContratoHistorico> cntjContratoHistoricoList) {
        this.cntjContratoHistoricoList = cntjContratoHistoricoList;
    }

    public List<CntjDocumento> getCntjDocumentosList() {
        return cntjDocumentosList;
    }

    public void setCntjDocumentosList(List<CntjDocumento> cntjDocumentosList) {
        this.cntjDocumentosList = cntjDocumentosList;
    }

    public List<CntjContratoSupervisor> getCntjContratoSupervisorList() {
        return cntjContratoSupervisorList;
    }

    public void setCntjContratoSupervisorList(List<CntjContratoSupervisor> cntjContratoSupervisorList) {
        this.cntjContratoSupervisorList = cntjContratoSupervisorList;
    }

    public List<CntjContratoSeguimiento> getCntjContratoSeguimientoList() {
        return cntjContratoSeguimientoList;
    }

    public void setCntjContratoSeguimientoList(List<CntjContratoSeguimiento> cntjContratoSeguimientoList) {
        this.cntjContratoSeguimientoList = cntjContratoSeguimientoList;
    }

    public List<CntjOtrosie> getCntjOtrosieList() {
        return cntjOtrosieList;
    }

    public void setCntjOtrosieList(List<CntjOtrosie> cntjOtrosieList) {
        this.cntjOtrosieList = cntjOtrosieList;
    }

    public List<CntjContratoInforme> getCntjContratoInformeList() {
        return cntjContratoInformeList;
    }

    public void setCntjContratoInformeList(List<CntjContratoInforme> cntjContratoInformeList) {
        this.cntjContratoInformeList = cntjContratoInformeList;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public Integer getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(Integer tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Date getFechaSuspension() {
        return fechaSuspension;
    }

    public void setFechaSuspension(Date fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }

    public Integer getPeriodicidadSeguimiento() {
        return periodicidadSeguimiento;
    }

    public void setPeriodicidadSeguimiento(Integer periodicidadSeguimiento) {
        this.periodicidadSeguimiento = periodicidadSeguimiento;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public String getMotivoTerminaAnticipada() {
        return motivoTerminaAnticipada;
    }

    public void setMotivoTerminaAnticipada(String motivoTerminaAnticipada) {
        this.motivoTerminaAnticipada = motivoTerminaAnticipada;
    }

    public Date getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(Date fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public String getEnlacePublicaSecop() {
        return enlacePublicaSecop;
    }

    public void setEnlacePublicaSecop(String enlacePublicaSecop) {
        this.enlacePublicaSecop = enlacePublicaSecop;
    }

    public Date getFechaPublicaSecop() {
        return fechaPublicaSecop;
    }

    public void setFechaPublicaSecop(Date fechaPublicaSecop) {
        this.fechaPublicaSecop = fechaPublicaSecop;
    }

    public String getResponsablePublicaSecop() {
        return responsablePublicaSecop;
    }

    public void setResponsablePublicaSecop(String responsablePublicaSecop) {
        this.responsablePublicaSecop = responsablePublicaSecop;
    }
    
    

    public Date getMinFechaInicioOtrosi() {
        Date min = this.fechaFin;
        if (min != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.fechaFin);
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Añade 1 día
            min = calendar.getTime();
        }
        return min;
    }

    @Override
    public String toString() {
        return "CntjContrato{" + "id=" + id + ", maeTipoContratoId=" + maeTipoContratoId + ", maeTipoContratoCodigo=" + maeTipoContratoCodigo + ", maeTipoContratoValor=" + maeTipoContratoValor + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valorInicial=" + valorInicial + ", valorPagadoTotal=" + valorPagadoTotal + ", valorContratoMasAdiciones=" + valorContratoMasAdiciones + ", valorTotalOtrosies=" + valorTotalOtrosies + ", contrato=" + contrato + ", maeEstadoContratoId=" + maeEstadoContratoId + ", maeEstadoContratoCodigo=" + maeEstadoContratoCodigo + ", maeEstadoContratoValor=" + maeEstadoContratoValor + ", proceso=" + proceso + ", maeClaseContratoId=" + maeClaseContratoId + ", maeClaseContratoCodigo=" + maeClaseContratoCodigo + ", maeClaseContratoValor=" + maeClaseContratoValor + ", estadoLegalizacion=" + estadoLegalizacion + ", maeModalidadContratoId=" + maeModalidadContratoId + ", maeModalidadContratoCodigo=" + maeModalidadContratoCodigo + ", maeModalidadContratoValor=" + maeModalidadContratoValor + ", complejidad=" + complejidad + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", plazoInicialMeses=" + plazoInicialMeses + ", plazoInicialDias=" + plazoInicialDias + ", plazoProrrogas=" + plazoProrrogas + ", plazoTotalDias=" + plazoTotalDias + ", valorMes=" + valorMes + ", valorDia=" + valorDia + ", valorUpc=" + valorUpc + ", valorAdiciones=" + valorAdiciones + ", valorTotal=" + valorTotal + ", formaPago=" + formaPago + ", tipoAnticipo=" + tipoAnticipo + ", valorAnticipo=" + valorAnticipo + ", cntjExpedienteId=" + cntjExpedienteId + ", cntjTerceroId=" + cntjTerceroId + ", objeto=" + objeto + ", fechaSuscripcion=" + fechaSuscripcion + ", tipoGasto=" + tipoGasto + ", fechaSuspension=" + fechaSuspension + ", motivoSuspension=" + motivoSuspension + ", periodicidadSeguimiento=" + periodicidadSeguimiento + ", motivoTerminaAnticipada=" + motivoTerminaAnticipada + ", enlacePublicaSecop=" + enlacePublicaSecop + ", fechaPublicaSecop=" + fechaPublicaSecop + ", responsablePublicaSecop=" + responsablePublicaSecop + ", fechaLiquidacion=" + fechaLiquidacion + ", valorFacturado=" + valorFacturado + '}';
    }

}
