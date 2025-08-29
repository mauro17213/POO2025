/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

 public class CmAuditoriaMasivaN extends Auditoria implements Serializable{
     
    public static final int ESTADO_EN_PROCESO = 0;
    public static final int ESTADO_REGISTRADA_SINCRONIZADA = 1;
    public static final int ESTADO_REGISTRADA_SIN_SINCRONIZACION = 2;

    private Integer id;
    private Integer estadoProceso;
    private Integer cantidadFacturas;
    private Integer cantidadFacturasRegistradas;
    private String nit;
    private String ips;
    private String cmRadicado;
    private String observacion;
    public  String descripcionFacturasSincronizadas;
    private BigDecimal sumaValorFacturado;
    private BigDecimal sumaValorCopago;
    private BigDecimal sumaValorBruto;
    private BigDecimal valorAuditoriaExitosa;
    private BigDecimal valorGlosado;
    private Date horaFinalizacionRegistro;
    private CntPrestador cntPrestadores;
    private boolean tieneFactuasPendientesProcesarEnSap;
  
    public CmAuditoriaMasivaN() {
    }
    

    public CmAuditoriaMasivaN(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public BigDecimal getSumaValorFacturado() {
        return sumaValorFacturado;
    }

    public void setSumaValorFacturado(BigDecimal sumaValorFacturado) {
        this.sumaValorFacturado = sumaValorFacturado;
    }

    public BigDecimal getSumaValorCopago() {
        return sumaValorCopago;
    }

    public void setSumaValorCopago(BigDecimal sumaValorCopago) {
        this.sumaValorCopago = sumaValorCopago;
    }

    public BigDecimal getSumaValorBruto() {
        return sumaValorBruto;
    }

    public void setSumaValorBruto(BigDecimal sumaValorBruto) {
        this.sumaValorBruto = sumaValorBruto;
    }

    public BigDecimal getValorAuditoriaExitosa() {
        return valorAuditoriaExitosa;
    }

    public void setValorAuditoriaExitosa(BigDecimal valorAuditoriaExitosa) {
        this.valorAuditoriaExitosa = valorAuditoriaExitosa;
    }

    public BigDecimal getValorGlosado() {
        return valorGlosado;
    }

    public void setValorGlosado(BigDecimal valorGlosado) {
        this.valorGlosado = valorGlosado;
    }

    public String getCmRadicado() {
        return cmRadicado;
    }

    public void setCmRadicado(String cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public Integer getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Integer estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Integer getCantidadFacturas() {
        return cantidadFacturas;
    }

    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    public Integer getCantidadFacturasRegistradas() {
        return cantidadFacturasRegistradas;
    }

    public void setCantidadFacturasRegistradas(Integer cantidadFacturasRegistradas) {
        this.cantidadFacturasRegistradas = cantidadFacturasRegistradas;
    }

    public Date getHoraFinalizacionRegistro() {
        return horaFinalizacionRegistro;
    }

    public void setHoraFinalizacionRegistro(Date horaFinalizacionRegistro) {
        this.horaFinalizacionRegistro = horaFinalizacionRegistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CntPrestador getCntPrestadores() {
        return cntPrestadores;
    }

    public void setCntPrestadores(CntPrestador cntPrestadores) {
        this.cntPrestadores = cntPrestadores;
    }
    
    public String getEstadoProcesoStr() {
        return CmAuditoriaMasivaN.getEstadoProcesoStr(getEstadoProceso());
    }

    public String getDescripcionFacturasSincronizadas() {
        return descripcionFacturasSincronizadas;
    }

    public void setDescripcionFacturasSincronizadas(String descripcionFacturasSincronizadas) {
        this.descripcionFacturasSincronizadas = descripcionFacturasSincronizadas;
    }

    public boolean isTieneFactuasPendientesProcesarEnSap() {
        return tieneFactuasPendientesProcesarEnSap;
    }

    public void setTieneFactuasPendientesProcesarEnSap(boolean tieneFactuasPendientesProcesarEnSap) {
        this.tieneFactuasPendientesProcesarEnSap = tieneFactuasPendientesProcesarEnSap;
    }
    
     public static String getEstadoProcesoStr(Integer estadoProceso) {
        String _str = "";     
         if (estadoProceso != null) {
             switch (estadoProceso) {
                 case ESTADO_EN_PROCESO:
                     _str = "En proceso";
                     break;
                 case ESTADO_REGISTRADA_SINCRONIZADA:
                     _str = "Registrada Sincronizada";
                     break;
                   case ESTADO_REGISTRADA_SIN_SINCRONIZACION:
                     _str = "Registrada Sin Sincronizacion (Capita o Pgp)";
                     break;
                 default:
                     _str = "";
                     break;
             }
         }
     
        return _str;
    }

    @Override
    public String toString() {
        return "CmAuditoriaMasiva{" + "id=" + id + ", cmRadicado=" + cmRadicado + '}';
    }
    
}
