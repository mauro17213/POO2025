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
import java.util.List;

/**
 *
 * @author rpalacios
 */
public class CmConciliacion extends Auditoria implements Cloneable{

 
    private Integer id;
    private CntPrestador cntPrestadores;
    private List<CmGlosaRespuesta> cmGlosaRespuestas;
    private BigDecimal valorFacturasPrecalculado;
    private BigDecimal valorFacturasGlosaInicialPrecalculado;
    private BigDecimal valorFacturasPendienteActualPrecalculado;
    private BigDecimal valorFacturasPendienteActualMasivo;
    private BigDecimal valorFacturasPagadoEpsPrecalculado;
    private BigDecimal valorFacturasAceptadoIpsPrecalculado;
    private int tipoEstadoRespuesta;
    private BigDecimal porcentajeAceptadoIps;
    private BigDecimal valorAceptadoIps;
    private BigDecimal porcentajePagadoEps;
    private BigDecimal valorPagadoEps;
    private String observacion;
    private BigDecimal valor;
    private BigDecimal porcentaje;
    private String representanteEps;
    private String representanteIps;
    private String nit;
    private String ips;
    private Integer estadoProceso;
    private Integer cantidadFacturas;
    private Integer cantidadFacturasRegistradas;
    private Date horaFinalizacionRegistro;
    private String archivo;
    private String archivoNombre;
    private String archivoRuta;
    private boolean archivoExiste;
    private Integer idConciliacionMasiva;
    private Integer idRadicado;
    
    public static final int ESTADO_EN_PROCESO = 0;
    public static final int ESTADO_REGISTRADA = 1;
    public String descripcionFacturasSincronizadas;
    
    public boolean facturasPendientes;
    public CmRadicado cmRadicado;
    
    public CmConciliacion() {
    }
   
    @Override
    public Object clone()throws CloneNotSupportedException{  
        CmConciliacion clone;
           try {
              clone = (CmConciliacion)super.clone(); 
           }
           catch (CloneNotSupportedException e) {
               throw new Error("Error al clonar objeto CmFactura");
           }
	 return clone;
    }

    public CmConciliacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntPrestador getCntPrestadores() {
        return cntPrestadores;
    }

    public void setCntPrestadores(CntPrestador cntPrestadores) {
        this.cntPrestadores = cntPrestadores;
    }

    public List<CmGlosaRespuesta> getCmGlosaRespuestas() {
        return cmGlosaRespuestas;
    }

    public void setCmGlosaRespuestas(List<CmGlosaRespuesta> cmGlosaRespuestas) {
        this.cmGlosaRespuestas = cmGlosaRespuestas;
    }

    public BigDecimal getValorFacturasPrecalculado() {
        return valorFacturasPrecalculado;
    }

    public void setValorFacturasPrecalculado(BigDecimal valorFacturasPrecalculado) {
        this.valorFacturasPrecalculado = valorFacturasPrecalculado;
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

    public int getTipoEstadoRespuesta() {
        return tipoEstadoRespuesta;
    }

    public void setTipoEstadoRespuesta(int tipoEstadoRespuesta) {
        this.tipoEstadoRespuesta = tipoEstadoRespuesta;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getValorFacturasPagadoEpsPrecalculado() {
        return valorFacturasPagadoEpsPrecalculado;
    }

    public void setValorFacturasPagadoEpsPrecalculado(BigDecimal valorFacturasPagadoEpsPrecalculado) {
        this.valorFacturasPagadoEpsPrecalculado = valorFacturasPagadoEpsPrecalculado;
    }

    public BigDecimal getValorFacturasAceptadoIpsPrecalculado() {
        return valorFacturasAceptadoIpsPrecalculado;
    }

    public void setValorFacturasAceptadoIpsPrecalculado(BigDecimal valorFacturasAceptadoIpsPrecalculado) {
        this.valorFacturasAceptadoIpsPrecalculado = valorFacturasAceptadoIpsPrecalculado;
    }

    public BigDecimal getValorFacturasPendienteActualMasivo() {
        return valorFacturasPendienteActualMasivo;
    }

    public void setValorFacturasPendienteActualMasivo(BigDecimal valorFacturasPendienteActualMasivo) {
        this.valorFacturasPendienteActualMasivo = valorFacturasPendienteActualMasivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    } 

    public String getRepresentanteEps() {
        return representanteEps;
    }

    public void setRepresentanteEps(String representanteEps) {
        this.representanteEps = representanteEps;
    }

    public String getRepresentanteIps() {
        return representanteIps;
    }

    public void setRepresentanteIps(String representanteIps) {
        this.representanteIps = representanteIps;
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

    public String getDescripcionFacturasSincronizadas() {
        return descripcionFacturasSincronizadas;
    }

    public void setDescripcionFacturasSincronizadas(String descripcionFacturasSincronizadas) {
        this.descripcionFacturasSincronizadas = descripcionFacturasSincronizadas;
    }

    public boolean isFacturasPendientes() {
        return facturasPendientes;
    }

    public void setFacturasPendientes(boolean facturasPendientes) {
        this.facturasPendientes = facturasPendientes;
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

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public boolean isArchivoExiste() {
        return archivoExiste;
    }

    public void setArchivoExiste(boolean archivoExiste) {
        this.archivoExiste = archivoExiste;
    }

     public boolean getArchivoExiste() {
        return archivoExiste;
    }
    
     public String getEstadoProcesoStr() {
        String _str = "";     
         if (estadoProceso != null) {
             switch (estadoProceso) {
                 case ESTADO_EN_PROCESO:
                     _str = "En proceso";
                     break;
                 case ESTADO_REGISTRADA:
                     _str = "Registrada";
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
        return "CmConciliacion{" + "id=" + id + ", cntPrestadores=" + cntPrestadores + ", cmGlosaRespuestas=" + cmGlosaRespuestas + ", valorFacturasPrecalculado=" + valorFacturasPrecalculado + ", valorFacturasGlosaInicialPrecalculado=" + valorFacturasGlosaInicialPrecalculado + ", valorFacturasPendienteActualPrecalculado=" + valorFacturasPendienteActualPrecalculado + ", valorFacturasPendienteActualMasivo=" + valorFacturasPendienteActualMasivo + ", valorFacturasPagadoEpsPrecalculado=" + valorFacturasPagadoEpsPrecalculado + ", valorFacturasAceptadoIpsPrecalculado=" + valorFacturasAceptadoIpsPrecalculado + ", tipoEstadoRespuesta=" + tipoEstadoRespuesta + ", porcentajeAceptadoIps=" + porcentajeAceptadoIps + ", valorAceptadoIps=" + valorAceptadoIps + ", porcentajePagadoEps=" + porcentajePagadoEps + ", valorPagadoEps=" + valorPagadoEps + ", observacion=" + observacion + ", valor=" + valor + ", porcentaje=" + porcentaje + ", representanteEps=" + representanteEps + ", representanteIps=" + representanteIps + ", nit=" + nit + ", ips=" + ips + ", estadoProceso=" + estadoProceso + ", cantidadFacturas=" + cantidadFacturas + ", cantidadFacturasRegistradas=" + cantidadFacturasRegistradas + ", horaFinalizacionRegistro=" + horaFinalizacionRegistro + ", archivo=" + archivo + ", archivoNombre=" + archivoNombre + ", archivoRuta=" + archivoRuta + ", archivoExiste=" + archivoExiste + ", idConciliacionMasiva=" + idConciliacionMasiva + ", idRadicado=" + idRadicado + ", descripcionFacturasSincronizadas=" + descripcionFacturasSincronizadas + ", facturasPendientes=" + facturasPendientes + ", cmRadicado=" + cmRadicado + '}';
    }

   
     
}
