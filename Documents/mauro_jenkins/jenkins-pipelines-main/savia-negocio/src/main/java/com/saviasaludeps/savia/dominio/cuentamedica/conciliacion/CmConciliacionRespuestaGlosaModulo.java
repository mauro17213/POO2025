/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;


import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class CmConciliacionRespuestaGlosaModulo extends Auditoria implements Cloneable {
    
    public static final int TIPO_SINCRONIZACION_CONCILIACION_MASIVA         = 1;
    public static final int TIPO_SINCRONIZACION_RESPUESTA_GLOSA             = 2;
    public static final int TIPO_SINCRONIZACION_CONCILIACION_INDIVIDUAL     = 3;
    
    private Integer idConciliacionMasiva;
    private Integer idConciliacionIndividual;
    private Integer idGlosaRespuesta;
    private Integer idTipoRespuestaGlosa;
    private Integer idCmFactura;
    private Integer idUsuarioSolicitanteProceso;
    private Integer tipoSincronizacion;
    private CmConciliacion cmConciliacion;
    private List<CmFactura> facturasEncontradas;
    
    
    public List<CmFactura> getFacturasEncontradas() {
        if (facturasEncontradas == null) {
            facturasEncontradas = new ArrayList<>();
        }
        return facturasEncontradas;
    }

    public void setFacturasEncontradas(List<CmFactura> facturasEncontradas) {
        this.facturasEncontradas = facturasEncontradas;
    }
    
     public CmConciliacion getCmConciliacion() {
        if (cmConciliacion == null) {
            cmConciliacion = new CmConciliacion();
        }
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public Integer getIdConciliacionMasiva() {
        return idConciliacionMasiva;
    }

    public void setIdConciliacionMasiva(Integer idConciliacionMasiva) {
        this.idConciliacionMasiva = idConciliacionMasiva;
    }

    public Integer getIdUsuarioSolicitanteProceso() {
        return idUsuarioSolicitanteProceso;
    }

    public void setIdUsuarioSolicitanteProceso(Integer idUsuarioSolicitanteProceso) {
        this.idUsuarioSolicitanteProceso = idUsuarioSolicitanteProceso;
    }
      
     public String getTipoSincronizacionStr() {
        return CmConciliacionRespuestaGlosaModulo.getTipoSincronizacionStr(getTipoSincronizacion());
    }

    public Integer getTipoSincronizacion() {
        return tipoSincronizacion;
    }

    public void setTipoSincronizacion(Integer tipoSincronizacion) {
        this.tipoSincronizacion = tipoSincronizacion;
    }

    public Integer getIdConciliacionIndividual() {
        return idConciliacionIndividual;
    }

    public void setIdConciliacionIndividual(Integer idConciliacionIndividual) {
        this.idConciliacionIndividual = idConciliacionIndividual;
    }

    public Integer getIdGlosaRespuesta() {
        return idGlosaRespuesta;
    }

    public void setIdGlosaRespuesta(Integer idGlosaRespuesta) {
        this.idGlosaRespuesta = idGlosaRespuesta;
    }

    public Integer getIdTipoRespuestaGlosa() {
        return idTipoRespuestaGlosa;
    }

    public void setIdTipoRespuestaGlosa(Integer idTipoRespuestaGlosa) {
        this.idTipoRespuestaGlosa = idTipoRespuestaGlosa;
    }

    public Integer getIdCmFactura() {
        return idCmFactura;
    }

    public void setIdCmFactura(Integer idCmFactura) {
        this.idCmFactura = idCmFactura;
    }
     
    public static String getTipoSincronizacionStr(int tipoSincronizacion) {
        String str;
        switch (tipoSincronizacion) {
            case TIPO_SINCRONIZACION_CONCILIACION_MASIVA:
                str = "Sincronizacion Conciliacion Masiva ";
                break;
            case TIPO_SINCRONIZACION_RESPUESTA_GLOSA:
                str = "Sincronizacion Respuesta Glosa";
                break;
            case TIPO_SINCRONIZACION_CONCILIACION_INDIVIDUAL:
                str = "Sincronizacion Conciliacion Individual  ";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    
}
