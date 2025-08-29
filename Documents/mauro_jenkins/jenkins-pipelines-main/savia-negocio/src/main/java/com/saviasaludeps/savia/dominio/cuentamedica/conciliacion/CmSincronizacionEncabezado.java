/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CmSincronizacionEncabezado extends Auditoria {

    public static final int ESTADO_PROCESO    = 0;
    public static final int ESTADO_FINALIZADO  = 1;
    public static final int ESTADO_SIN_CONCEPTOS_CANTABLES  = 2;
    public static final int ESTADO_SIN_VALORES_EPS = 3;
    public static final int ESTADO_SIN_DETALLES = 4;
    public static final int ESTADO_SIN_PROCESAR_MOMENTO_1 = 10;
   
    
    private Integer id;
    private int estado;
    private String proveedorNit;
    private String numeroDocumento;
    private String numeroRadicado;
    private Integer factura;
    private String regimen;
    private BigDecimal valorDocumento;
    private Date fechaHoraDocumento;
    private Date fechaHoraProceso;
    private CmRadicado cmRadicadosId;
    private boolean poseeConceptosContables;
    private BigDecimal valorPagado;
    private BigDecimal valorGlosado;
    private BigDecimal valorCopago;
    private Integer idContrato;
    private String numeroContrato;
    private String contrato;
    private BigDecimal cuotaModeradora;
    
    public CmSincronizacionEncabezado() {
    }

    public CmSincronizacionEncabezado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(String proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public BigDecimal getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public Date getFechaHoraDocumento() {
        return fechaHoraDocumento;
    }

    public void setFechaHoraDocumento(Date fechaHoraDocumento) {
        this.fechaHoraDocumento = fechaHoraDocumento;
    }

    public Date getFechaHoraProceso() {
        return fechaHoraProceso;
    }

    public void setFechaHoraProceso(Date fechaHoraProceso) {
        this.fechaHoraProceso = fechaHoraProceso;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }
    

    public CmRadicado getCmRadicadosId() {
        return cmRadicadosId;
    }

    public void setCmRadicadosId(CmRadicado cmRadicadosId) {
        this.cmRadicadosId = cmRadicadosId;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public BigDecimal getCuotaModeradora() {
        return cuotaModeradora;
    }

    public void setCuotaModeradora(BigDecimal cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
    }
        
    public String getRegimenInicial() {
        switch (regimen) {
            case "SUBSIDIADO":
                return "S";
            case "CONTRIBUTIVO":
                return "C";
            default:
                return "";
        }
    }
    
      public String getEstadoStr() {
        switch (estado) {
            case ESTADO_PROCESO:
                return "Pendiente por procesar en Sap";
            case ESTADO_FINALIZADO:
                return "Procesada en Sap";
            case ESTADO_SIN_CONCEPTOS_CANTABLES:
                return "Sin conceptos contables";
            case ESTADO_SIN_VALORES_EPS:
                return "Sin valores EPS";
            case ESTADO_SIN_PROCESAR_MOMENTO_1:
                return "Sin procesar momento 1";
            case ESTADO_SIN_DETALLES:
                return "Sin detalles";
            default:
                return "";
        }
    }
      
    public static String getEstadoStr(int estado) {
        switch (estado) {
            case ESTADO_PROCESO:
                return "Pendiente por procesar en Sap";
            case ESTADO_FINALIZADO:
                return "Procesada en Sap";
            case ESTADO_SIN_CONCEPTOS_CANTABLES:
                return "Sin conceptos contables";
            case ESTADO_SIN_VALORES_EPS:
                return "Sin valores EPS";
            case ESTADO_SIN_PROCESAR_MOMENTO_1:
                return "Sin procesar momento 1";
            case ESTADO_SIN_DETALLES:
                return "Sin detalles";
            default:
                return "";
        }
    }
    
       public static String getDescripcionEstadoControlStr(int estado) {
        switch (estado) {
            case ESTADO_PROCESO:
                return "Pendiente por procesar en Sap";
            case ESTADO_FINALIZADO:
                return "Procesada en Sap";
            case ESTADO_SIN_CONCEPTOS_CANTABLES:
                return "Sin conceptos contables";
            case ESTADO_SIN_VALORES_EPS:
                return "Sin valores EPS";
            case ESTADO_SIN_PROCESAR_MOMENTO_1:
                return "Sin procesar momento 1";
            case ESTADO_SIN_DETALLES:
                return "Sin detalles";
            default:
                return "";
        }
    }

    public boolean isPoseeConceptosContables() {
        if(this.getEstado() != ESTADO_SIN_VALORES_EPS){
            poseeConceptosContables = true;
        }
        return poseeConceptosContables;
    }

    public void setPoseeConceptosContables(boolean poseeConceptosContables) {
        this.poseeConceptosContables = poseeConceptosContables;
    }

}
