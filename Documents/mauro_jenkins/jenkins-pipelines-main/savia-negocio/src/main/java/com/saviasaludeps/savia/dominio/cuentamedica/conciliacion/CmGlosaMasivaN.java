/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author jeperez
 */
public class CmGlosaMasivaN extends Auditoria implements Cloneable{

    public static final int ESTADO_INICIALIZADO = 0;
    public static final int ESTADO_FINALIZADO = 1;
    
    private Integer id;
    private CntPrestador cntPrestadores;
    private int estadoProceso;
    private int cantidadFacturas;
    private int cantidadFacturasRespuestaGlosa;
    private int cantidadFacturasRatificacionGlosa;
    private int estadoGlosaMasivaGlobal;
    private BigDecimal valorFacturas;
    private BigDecimal valorTotalPagadoEps;
    private BigDecimal valorTotalAceptadoIps;
    private BigDecimal valorFacturasGlosaInicialPrecalculado;
    private BigDecimal valorFacturasPendienteActualPrecalculado;
    private BigDecimal porcentajeAceptadoIps;
    private BigDecimal valorAceptadoIps;
    private BigDecimal porcentajePagadoEps;
    private BigDecimal valorPagadoEps;
    private BigDecimal valorFacturasPrecalculado;
    private BigDecimal valorFacturasPendienteActualMasivo;
    private BigDecimal valorPendienteActualDetallesSeleccionados;
    private BigDecimal valorFacturasPagadoEpsPrecalculado;
    private BigDecimal valorFacturasAceptadoIpsPrecalculado;
    private Date horaFinalizacionRegistro;
    public CmRadicado cmRadicado;
    private String observacion;

    public CmGlosaMasivaN() {
    }
    
    public CmGlosaMasivaN(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public int getCantidadFacturas() {
        return cantidadFacturas;
    }

    public void setCantidadFacturas(int cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    public int getCantidadFacturasRespuestaGlosa() {
        return cantidadFacturasRespuestaGlosa;
    }

    public void setCantidadFacturasRespuestaGlosa(int cantidadFacturasRespuestaGlosa) {
        this.cantidadFacturasRespuestaGlosa = cantidadFacturasRespuestaGlosa;
    }

    public int getCantidadFacturasRatificacionGlosa() {
        return cantidadFacturasRatificacionGlosa;
    }

    public void setCantidadFacturasRatificacionGlosa(int cantidadFacturasRatificacionGlosa) {
        this.cantidadFacturasRatificacionGlosa = cantidadFacturasRatificacionGlosa;
    }
   
   
    public CntPrestador getCntPrestadores() {
        return cntPrestadores;
    }

    public void setCntPrestadores(CntPrestador cntPrestadores) {
        this.cntPrestadores = cntPrestadores;
    }


    public int getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(int estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public BigDecimal getValorFacturas() {
        return valorFacturas;
    }

    public void setValorFacturas(BigDecimal valorFacturas) {
        this.valorFacturas = valorFacturas;
    }

    public BigDecimal getValorTotalPagadoEps() {
        return valorTotalPagadoEps;
    }

    public void setValorTotalPagadoEps(BigDecimal valorTotalPagadoEps) {
        this.valorTotalPagadoEps = valorTotalPagadoEps;
    }

    public BigDecimal getValorTotalAceptadoIps() {
        return valorTotalAceptadoIps;
    }

    public void setValorTotalAceptadoIps(BigDecimal valorTotalAceptadoIps) {
        this.valorTotalAceptadoIps = valorTotalAceptadoIps;
    }

    public BigDecimal getValorFacturasGlosaInicialPrecalculado() {
        return valorFacturasGlosaInicialPrecalculado;
    }

    public void setValorFacturasGlosaInicialPrecalculado(BigDecimal valorFacturasGlosaInicialPrecalculado) {
        this.valorFacturasGlosaInicialPrecalculado = valorFacturasGlosaInicialPrecalculado;
    }

    public BigDecimal getValorFacturasPendienteActualPrecalculado() {
        return valorFacturasPendienteActualPrecalculado;
    }

    public void setValorFacturasPendienteActualPrecalculado(BigDecimal valorFacturasPendienteActualPrecalculado) {
        this.valorFacturasPendienteActualPrecalculado = valorFacturasPendienteActualPrecalculado;
    }

    public BigDecimal getPorcentajeAceptadoIps() {
        return porcentajeAceptadoIps;
    }

    public void setPorcentajeAceptadoIps(BigDecimal porcentajeAceptadoIps) {
        this.porcentajeAceptadoIps = porcentajeAceptadoIps;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public BigDecimal getPorcentajePagadoEps() {
        return porcentajePagadoEps;
    }

    public void setPorcentajePagadoEps(BigDecimal porcentajePagadoEps) {
        this.porcentajePagadoEps = porcentajePagadoEps;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal valorPagadoEps) {
        this.valorPagadoEps = valorPagadoEps;
    }

    public BigDecimal getValorFacturasPrecalculado() {
        return valorFacturasPrecalculado;
    }

    public void setValorFacturasPrecalculado(BigDecimal valorFacturasPrecalculado) {
        this.valorFacturasPrecalculado = valorFacturasPrecalculado;
    }

    public BigDecimal getValorFacturasPendienteActualMasivo() {
        return valorFacturasPendienteActualMasivo;
    }

    public void setValorFacturasPendienteActualMasivo(BigDecimal valorFacturasPendienteActualMasivo) {
        this.valorFacturasPendienteActualMasivo = valorFacturasPendienteActualMasivo;
    }

    public BigDecimal getValorFacturasPagadoEpsPrecalculado() {
        return valorFacturasPagadoEpsPrecalculado;
    }

    public void setValorFacturasPagadoEpsPrecalculado(BigDecimal valorFacturasPagadoEpsPrecalculado) {
        this.valorFacturasPagadoEpsPrecalculado = valorFacturasPagadoEpsPrecalculado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getValorFacturasAceptadoIpsPrecalculado() {
        return valorFacturasAceptadoIpsPrecalculado;
    }

    public void setValorFacturasAceptadoIpsPrecalculado(BigDecimal valorFacturasAceptadoIpsPrecalculado) {
        this.valorFacturasAceptadoIpsPrecalculado = valorFacturasAceptadoIpsPrecalculado;
    }

    public int getEstadoGlosaMasivaGlobal() {
        return estadoGlosaMasivaGlobal;
    }

    public void setEstadoGlosaMasivaGlobal(int estadoGlosaMasivaGlobal) {
        this.estadoGlosaMasivaGlobal = estadoGlosaMasivaGlobal;
    }

    public BigDecimal getValorPendienteActualDetallesSeleccionados() {
        return valorPendienteActualDetallesSeleccionados;
    }

    public void setValorPendienteActualDetallesSeleccionados(BigDecimal valorPendienteActualDetallesSeleccionados) {
        this.valorPendienteActualDetallesSeleccionados = valorPendienteActualDetallesSeleccionados;
    }
    
    public  boolean existeCmGlosaMasiva(){
        return Optional.ofNullable(getId()).orElse(0)>0;
    }
    

    public CmRadicado getCmRadicado() {
        if(cmRadicado == null){
            cmRadicado = new CmRadicado();
        }
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado radicado) {
        this.cmRadicado = radicado;
    }
   
    public String getEstadoProcesoStr(){
      return CmGlosaMasivaN.getEstadoProcesoStr(getEstadoProceso());
    }

    public Date getHoraFinalizacionRegistro() {
        return horaFinalizacionRegistro;
    }

    public void setHoraFinalizacionRegistro(Date horaFinalizacionRegistro) {
        this.horaFinalizacionRegistro = horaFinalizacionRegistro;
    }
      
     public static String getEstadoProcesoStr(int estadoProceso) {
        String _str = "";     
             switch (estadoProceso) {
                 case ESTADO_INICIALIZADO:
                     _str = "Inicializado";
                     break;
                 case ESTADO_FINALIZADO:
                     _str = "Finalizado";
                     break;
                 default:
                     _str = "";
                     break;
             }
     
        return _str;
    }
     
    @Override
    public Object clone()throws CloneNotSupportedException{  
        CmGlosaMasivaN clone;
           try {
              clone = (CmGlosaMasivaN)super.clone(); 
           }
           catch (CloneNotSupportedException e) {
               throw new Error("Error al clonar objeto CmFactura");
           }
	 return clone;
    }


    @Override
    public String toString() {
        return "CmRespuestaGlosaMasiva{" + "id=" + id + ", cntPrestadores=" + cntPrestadores + ", cmRadicado=" + cmRadicado + '}';
    }

}
