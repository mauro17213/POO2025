/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rpalacios
 */

public class CmGlosaRespuesta extends Auditoria {

    private Integer id;
    private Integer tipoRespuesta;
    private BigDecimal valorCobroDetalle;
    private BigDecimal valorFacturado;
    private BigDecimal valorPagado;
    private BigDecimal valorPagadoEps;
    private BigDecimal valorPendiente;
    private BigDecimal valorAceptadoIps;
    private String observacion;
    private CmFactura cmFactura;
    CmConciliacion cmConciliacion;
    private int estadoSincronizacion;
    private String representanteEps;
    private String representanteIps;
    private boolean procesadaSap;
    private CmRadicado cmRadicado;
    private boolean visualizarPdf;
    private CmGlosaMasivaN cmGlosaMasiva;
    
    public static final int ESTADO_SINCRONIZACION_CREADA      = 0;
    public static final int ESTADO_SINCRONIZACION_ENVIADA     = 1;
    public static final int ESTADO_SINCRONIZACION_EN_RADICACION_ENCABEZADO = 6;
    public static final int ESTADO_SINCRONIZACION_ACEPTADA    = 2;
    public static final int ESTADO_SINCRONIZACION_RECHAZADA   = 3;
    public static final int ESTADO_SINCRONIZACION_MANUAL      = 4;
    public static final int ESTADO_SINCRONIZACION_RATIFICADA  = 5;
    
    public static final int TIPO_RESPUESTA_SIN_RESPUESTA   = 0;
    public static final int TIPO_RESPUESTA_RESPUESTA       = 1;
    public static final int TIPO_RESPUESTA_CONCILIACION    = 2;
    public static final int TIPO_RESPUESTA_RATIFICACION    = 3;
   
    
    
    public static final int TAMANIO_OBSERVACION = 20;
    
    private List<CmGlosaRespuestaDetalle> listaGlosaRespuestaDetalle;
    
    public CmGlosaRespuesta() {
    }

    public CmGlosaRespuesta(Integer id) {
        this.id = id;
    }
    
    public CmGlosaRespuesta(Integer id ,Integer tipoRespuesta) {
        this.id = id;
        this.tipoRespuesta = tipoRespuesta;
    }


    public CmGlosaRespuesta(Integer id, BigDecimal valorCobroDetalle, BigDecimal valorFacturado, BigDecimal valorPagado, BigDecimal vaorPagadoEps, BigDecimal valorPendiente, BigDecimal valorAceptadoIps, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.valorCobroDetalle = valorCobroDetalle;
        this.valorFacturado = valorFacturado;
        this.valorPagado = valorPagado;
        this.valorPagadoEps = vaorPagadoEps;
        this.valorPendiente = valorPendiente;
        this.valorAceptadoIps = valorAceptadoIps;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorCobroDetalle() {
        return valorCobroDetalle;
    }

    public void setValorCobroDetalle(BigDecimal valorCobroDetalle) {
        this.valorCobroDetalle = valorCobroDetalle;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorPagadoEps() {
        return valorPagadoEps;
    }

    public void setValorPagadoEps(BigDecimal vaorPagadoEps) {
        this.valorPagadoEps = vaorPagadoEps;
    }

    public BigDecimal getValorPendiente() {
        return valorPendiente;
    }

    public void setValorPendiente(BigDecimal valorPendiente) {
        this.valorPendiente = valorPendiente;
    }

    public BigDecimal getValorAceptadoIps() {
        return valorAceptadoIps;
    }

    public void setValorAceptadoIps(BigDecimal valorAceptadoIps) {
        this.valorAceptadoIps = valorAceptadoIps;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
     public String getObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
        
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public List<CmGlosaRespuestaDetalle> getListaGlosaRespuestaDetalle() {
        return listaGlosaRespuestaDetalle;
    }

    public void setListaGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> 
            listaGlosaRespuestaDetalle) {
        this.listaGlosaRespuestaDetalle = listaGlosaRespuestaDetalle;
    }

    public Integer getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(Integer tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public CmConciliacion getCmConciliacion() {
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public CmGlosaMasivaN getCmGlosaMasiva() {
        return cmGlosaMasiva;
    }

    public void setCmGlosaMasiva(CmGlosaMasivaN cmGlosaMasiva) {
        this.cmGlosaMasiva = cmGlosaMasiva;
    }

    public CmRadicado getCmRadicado() {
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public int getEstadoSincronizacion() {
        return estadoSincronizacion;
    }

    public void setEstadoSincronizacion(int estadoSincronizacion) {
        this.estadoSincronizacion = estadoSincronizacion;
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

    public boolean isProcesadaSap() {
        return procesadaSap;
    }

    public void setProcesadaSap(boolean procesadaSap) {
        this.procesadaSap = procesadaSap;
    }

    public String isProcesadaSapStr() {
        return procesadaSap ? "Si" :"No";
    }
    
    public String esProcesadaExitosamenteSapStr() {
        return estadoSincronizacion == ESTADO_SINCRONIZACION_ACEPTADA ? "Si" :"No";
    }
      
    public  boolean esGlosaRespuestaValida(){
        return Optional.ofNullable(getId()).orElse(0) > 0;
    }
    
    public boolean esTipoRespuestaRatificacion(){
        return getTipoRespuesta() == CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION;
    }
    
    public boolean esTipoRespuestaGlosa(){
        return getTipoRespuesta() == CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA;
    }
    
    public boolean esTipoRespuestaConciliacion(){
        return getTipoRespuesta() == CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION;
    }

    public int getTipoRespuestaSegunValoresPorcentaje( BigDecimal valorEPS, BigDecimal valorIPS) {
       
        int tipoRespuestaCalculada;
        valorIPS = Optional.ofNullable(valorIPS).orElse(new BigDecimal("0"));
        valorEPS = Optional.ofNullable(valorEPS).orElse(new BigDecimal("0"));
        if (valorIPS.compareTo(new BigDecimal("0")) == 0
                && valorEPS.compareTo(new BigDecimal("0")) == 0) {
            tipoRespuestaCalculada = CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION;
        } else {
            tipoRespuestaCalculada = CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA;
        }
        return tipoRespuestaCalculada;
    }
    
    public String getEstadoSincronizacionStr() {
        switch (estadoSincronizacion) {
            case ESTADO_SINCRONIZACION_ACEPTADA:
                return "Aceptada";
            case ESTADO_SINCRONIZACION_CREADA:
                return "Creada";
            case ESTADO_SINCRONIZACION_MANUAL:
                return "Manual";
            case ESTADO_SINCRONIZACION_RECHAZADA:
                return "Rechazada";
            case ESTADO_SINCRONIZACION_RATIFICADA:
                return "Ratificada";
            case ESTADO_SINCRONIZACION_EN_RADICACION_ENCABEZADO:
                return "Creada en sincronizacion Encabezado";
            default:
                return "";
        }
    }
    
      public String getTipoRespuestaStr() {
        switch (tipoRespuesta) {
            case TIPO_RESPUESTA_RESPUESTA:
                return "Respuesta";
            case TIPO_RESPUESTA_CONCILIACION:
                return "Conciliación";
            case TIPO_RESPUESTA_RATIFICACION:
                return "Ratificación";
            case TIPO_RESPUESTA_SIN_RESPUESTA:
                return "Sin Respuesta";
            default:
                return "";
        }
    }
      
    public static String getTipoRespuestaStr(int idTipo) {
        switch (idTipo) {
            case TIPO_RESPUESTA_RESPUESTA:
                return "Respuesta";
            case TIPO_RESPUESTA_CONCILIACION:
                return "Conciliación";
            case TIPO_RESPUESTA_RATIFICACION:
                return "Ratificación";
            default:
                return "";
        }
    }

    public boolean isVisualizarPdf() {
        return visualizarPdf;
    }

    public void setVisualizarPdf(boolean visualizarPdf) {
        this.visualizarPdf = visualizarPdf;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CmGlosaRespuesta)) {
            return false;
        }
        CmGlosaRespuesta other = (CmGlosaRespuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CmGlosaRespuesta{" + "id=" + id + ", valorCobroDetalle=" + valorCobroDetalle + ", valorFacturado=" + valorFacturado + ", valorPagado=" + valorPagado + ", vaorPagadoEps=" + valorPagadoEps + ", valorPendiente=" + valorPendiente + ", valorAceptadoIps=" + valorAceptadoIps + ", observacion=" + observacion + ", cmFactura=" + cmFactura + ", listaGlosaRespuestaDetalle=" + listaGlosaRespuestaDetalle + '}';
    }

   
    
}
