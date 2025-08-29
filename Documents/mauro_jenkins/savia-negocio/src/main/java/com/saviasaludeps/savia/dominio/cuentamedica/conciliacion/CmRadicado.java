/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmDevolucionMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CmRadicado extends Auditoria {
    
    public static final int TIPO_TRANSACCION_DEVOLUCION = 0;
    public static final int TIPO_TRANSACCION_RADICACION = 1;
    public static final int TIPO_TRANSACCION_CAUSACION  = 2;
    public static final int TIPO_TRANSACCION_RESPUESTA_GLOSA  = 3;
    public static final int TIPO_TRANSACCION_CONCILIACION  = 4;
    public static final int TIPO_TRANSACCION_POR_DEFECTO = 99;
                    
    public static final short ESTADO_CREADO = 0;
    public static final short ESTADO_FACTURAS_INICIO = 1;
    public static final short ESTADO_FACTURAS_FIN = 2;
    public static final short ESTADO_TRANSACCION_INICIO = 3;
    public static final short ESTADO_TRANSACCION_FIN = 4;
    public static final short ESTADO_ENVIO_SAP_INICIO = 5;
    public static final short ESTADO_ENVIO_SAP_FIN = 6;
    
    public static final short TIPO_RELACION_CARGA_RIPS = 1;
    public static final short TIPO_RELACION_DEVOLUCION_INDIVIDUAL = 2;
    public static final short TIPO_RELACION_DEVOLUCION_MASIVA = 3;
    public static final short TIPO_RELACION_CIERRE_INDIVIDUAL = 4;
    public static final short TIPO_RELACION_CIERRE_MASIVO  = 5;
    public static final short TIPO_RELACION_REPUESTA_GLOSA = 6;
    public static final short TIPO_RELACION_CONCILIACION_INDIVIDUAL = 7;
    public static final short TIPO_RELACION_CONCILIACION_MASIVA = 8;
    public static final short TIPO_RELACION_RESPUESTA_GLOSA_MASIVA = 9;
    
    public static final boolean ESTADO_RADICADO_ACTIVO  = false;
    public static final boolean ESTADO_RADICADO_CERRADO = true;
    
    private Integer id;
    private int radicado;
    private Boolean estado_radicado;
    private CmConciliacion cmConciliacion;
    private CmGlosaRespuesta cmGlosaRespuesta;
    private CmGlosaRespuesta cmGlosaRespuestasConciliacion;
    private CmGlosaMasivaN cmGlosaMasiva;
    private CmAuditoriaDevolucion cmAuditoriaDevolucion;
    private CmAuditoriaCierre cmAuditoriaCierre;
    private CmFactura cmFactura;
    private CmSincronizacionEncabezado cmSincronizacionEncabezado;
    private CmAuditoriaMasivaN cmAuditoriaMasivaN;
    private CmDevolucionMasivaN cmDevolucionMasivaN;
    private CmRipsCarga cmRipsCarga;
    private CmFeRipsCarga cmFeRipsCarga;
    private short tipoTransaccion;
    private short intentosPermitidos;
    private short intentosEjecutados;
    private Short estado;
    private Short tipoRelacion;
    private Date fechaHoraFactura;
    private Date fechaHoraTransaccion;
    private Date fechaHoraEnvioSap;
    private int cantidadCmFacturas;
    private int cantidadWsCmFacturas;
    private int cantidadTransaccionDetalles;
    private int cantidadTransaccionDetallesHechas;
    private int cantidadTransaccionDetallesEnviadas;
    private int cantidadWsCmFacturasHechas;
    private int fuenteOrigenFacturas;
    
    
    public CmRadicado() {
    }
    
    public CmRadicado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEstado_radicado() {
        return estado_radicado;
    }

    public void setEstado_radicado(Boolean estado_radicado) {
        this.estado_radicado = estado_radicado;
    }

    public int getRadicado() {
        return radicado;
    }

    public void setRadicado(int radicado) {
        this.radicado = radicado;
    }

    public CmConciliacion getCmConciliacion() {
        return cmConciliacion;
    }

    public void setCmConciliacion(CmConciliacion cmConciliacion) {
        this.cmConciliacion = cmConciliacion;
    }

    public CmGlosaRespuesta getCmGlosaRespuesta() {
        return cmGlosaRespuesta;
    }

    public void setCmGlosaRespuesta(CmGlosaRespuesta cmGlosaRespuesta) {
        this.cmGlosaRespuesta = cmGlosaRespuesta;
    }

    public CmGlosaRespuesta getCmGlosaRespuestasConciliacion() {
        return cmGlosaRespuestasConciliacion;
    }

    public void setCmGlosaRespuestasConciliacion(CmGlosaRespuesta cmGlosaRespuestasConciliacion) {
        this.cmGlosaRespuestasConciliacion = cmGlosaRespuestasConciliacion;
    }

    public CmAuditoriaDevolucion getCmAuditoriaDevolucion() {
        return cmAuditoriaDevolucion;
    }

    public void setCmAuditoriaDevolucion(CmAuditoriaDevolucion cmAuditoriaDevolucion) {
        this.cmAuditoriaDevolucion = cmAuditoriaDevolucion;
    }

    public CmAuditoriaCierre getCmAuditoriaCierre() {
        return cmAuditoriaCierre;
    }

    public void setCmAuditoriaCierre(CmAuditoriaCierre cmAuditoriaCierre) {
        this.cmAuditoriaCierre = cmAuditoriaCierre;
    }

    public CmSincronizacionEncabezado getCmSincronizacionEncabezado() {
        if( cmSincronizacionEncabezado == null ){
            cmSincronizacionEncabezado = new CmSincronizacionEncabezado();
        }
        return cmSincronizacionEncabezado;
    }

    public void setCmSincronizacionEncabezado(CmSincronizacionEncabezado cmSincronizacionEncabezado) {
        this.cmSincronizacionEncabezado = cmSincronizacionEncabezado;
    }

    public CmFactura getCmFactura() {
        return cmFactura;
    }

    public void setCmFactura(CmFactura cmFactura) {
        this.cmFactura = cmFactura;
    }

    public CmAuditoriaMasivaN getCmAuditoriaMasivaN() {
        return cmAuditoriaMasivaN;
    }

    public void setCmAuditoriaMasivaN(CmAuditoriaMasivaN cmAuditoriaMasivaN) {
        this.cmAuditoriaMasivaN = cmAuditoriaMasivaN;
    }

    public CmDevolucionMasivaN getCmDevolucionMasivaN() {
        return cmDevolucionMasivaN;
    }

    public void setCmDevolucionMasivaN(CmDevolucionMasivaN cmDevolucionMasivaN) {
        this.cmDevolucionMasivaN = cmDevolucionMasivaN;
    }

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCarga) {
        this.cmRipsCarga = cmRipsCarga;
    }

    public short getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(short tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public short getIntentosPermitidos() {
        return intentosPermitidos;
    }

    public void setIntentosPermitidos(short intentosPermitidos) {
        this.intentosPermitidos = intentosPermitidos;
    }

    public short getIntentosEjecutados() {
        return intentosEjecutados;
    }

    public void setIntentosEjecutados(short intentosEjecutados) {
        this.intentosEjecutados = intentosEjecutados;
    }

    public int getCantidadTransaccionDetallesEnviadas() {
        return cantidadTransaccionDetallesEnviadas;
    }

    public void setCantidadTransaccionDetallesEnviadas(int cantidadTransaccionDetallesEnviadas) {
        this.cantidadTransaccionDetallesEnviadas = cantidadTransaccionDetallesEnviadas;
    }
    
    public String getTipoTransaccionStr() {
        return CmRadicado.getTipoTransaccionStr(getTipoTransaccion());
    }

    public CmFeRipsCarga getCmFeRipsCarga() {
        return cmFeRipsCarga;
    }

    public void setCmFeRipsCarga(CmFeRipsCarga cmFeRipsCarga) {
        this.cmFeRipsCarga = cmFeRipsCarga;
    }
    
    
    
    public static boolean esCmRadicadoActivoValido(CmRadicado radicado){
        radicado = Optional.ofNullable(radicado).orElse(new CmRadicado());
        return radicado.getId() != null && radicado.getId()> 0;
    }
    
    public  boolean esCmRadicadoActivoValido(){
        CmRadicado radicado = Optional.ofNullable(this).orElse(new CmRadicado());
        return radicado.getId() != null && radicado.getId()> 0;
    }

    public Short getEstado() {
        if (Objects.isNull(estado)) {
            estado = -1;
        }
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Short getTipoRelacion() {
        if (Objects.isNull(tipoRelacion)) {
            tipoRelacion = 0;
        }
        return tipoRelacion;
    }

    public void setTipoRelacion(Short tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Date getFechaHoraFactura() {
        return fechaHoraFactura;
    }

    public void setFechaHoraFactura(Date fechaHoraFactura) {
        this.fechaHoraFactura = fechaHoraFactura;
    }

    public Date getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(Date fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public Date getFechaHoraEnvioSap() {
        return fechaHoraEnvioSap;
    }

    public void setFechaHoraEnvioSap(Date fechaHoraEnvioSap) {
        this.fechaHoraEnvioSap = fechaHoraEnvioSap;
    }

    public int getCantidadCmFacturas() {
        return cantidadCmFacturas;
    }

    public void setCantidadCmFacturas(int cantidadCmFacturas) {
        this.cantidadCmFacturas = cantidadCmFacturas;
    }

    public int getCantidadWsCmFacturas() {
        return cantidadWsCmFacturas;
    }

    public void setCantidadWsCmFacturas(int cantidadWsCmFacturas) {
        this.cantidadWsCmFacturas = cantidadWsCmFacturas;
    }

    public int getCantidadTransaccionDetalles() {
        return cantidadTransaccionDetalles;
    }

    public void setCantidadTransaccionDetalles(int cantidadTransaccionDetalles) {
        this.cantidadTransaccionDetalles = cantidadTransaccionDetalles;
    }

    public int getCantidadTransaccionDetallesHechas() {
        return cantidadTransaccionDetallesHechas;
    }

    public void setCantidadTransaccionDetallesHechas(int cantidadTransaccionDetallesHechas) {
        this.cantidadTransaccionDetallesHechas = cantidadTransaccionDetallesHechas;
    }

    public int getCantidadWsCmFacturasHechas() {
        return cantidadWsCmFacturasHechas;
    }

    public void setCantidadWsCmFacturasHechas(int cantidadWsCmFacturasHechas) {
        this.cantidadWsCmFacturasHechas = cantidadWsCmFacturasHechas;
    }

    public int getFuenteOrigenFacturas() {
        return fuenteOrigenFacturas;
    }

    public void setFuenteOrigenFacturas(int fuenteOrigenFacturas) {
        this.fuenteOrigenFacturas = fuenteOrigenFacturas;
    }

    public CmGlosaMasivaN getCmGlosaMasiva() {
        return cmGlosaMasiva;
    }

    public void setCmGlosaMasiva(CmGlosaMasivaN cmGlosaMasiva) {
        this.cmGlosaMasiva = cmGlosaMasiva;
    }

  
    public static String getTipoTransaccionStr(int tipoTransaccion) {
        String str;
        switch (tipoTransaccion) {
            case TIPO_TRANSACCION_DEVOLUCION:
                str = "Devolución";
                break;
            case TIPO_TRANSACCION_RADICACION:
                str = "M1 - Provisión";
                break;
            case TIPO_TRANSACCION_CAUSACION:
                str = "M2 - Causación";
                break;
            case TIPO_TRANSACCION_RESPUESTA_GLOSA:
                str = "M3 - Respuesta Glosa";
                break;
            case TIPO_TRANSACCION_CONCILIACION:
                str = "M4 - Conciliación";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
     
    public String getEstadoStr() {
        return CmRadicado.getEstadoStr(getEstado());
    }
    
    public static String getEstadoStr(int estado) {
        String str;
        switch (estado) {
            case ESTADO_CREADO:
                str = "Creado";
                break;
            case ESTADO_FACTURAS_INICIO:
                str = "Facturas Inicio";
                break;
            case ESTADO_FACTURAS_FIN:
                str = "Facturas Fin";
                break;
             case ESTADO_TRANSACCION_INICIO:
                str = "Transacciones Inicio";
                break;
             case ESTADO_TRANSACCION_FIN:
                str = "Transacciones Fin";
                break;
            case ESTADO_ENVIO_SAP_FIN:
                str = "Envío SAP";
                break;
             case ESTADO_ENVIO_SAP_INICIO:
                str = "En Proceso Envío SAP";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }
    
    public String getTipoRelacionStr() {
        return CmRadicado.getTipoRelacionStr(getTipoRelacion());
    }
    
    public static String getTipoRelacionStr(int tipoRelacion) {
        String str;
        switch (tipoRelacion) {
            case TIPO_RELACION_CARGA_RIPS:
                str = " M1 - Provisión";
                break;
            case TIPO_RELACION_DEVOLUCION_INDIVIDUAL:
                str = "Devolución Individual";
                break;
            case TIPO_RELACION_DEVOLUCION_MASIVA:
                str = "Devolución Masiva";
                break;
             case TIPO_RELACION_CIERRE_INDIVIDUAL:
                str = "M2 - Causación Individual";
                break;
             case TIPO_RELACION_CIERRE_MASIVO:
                str = "M2 - Causación Masivo ";
                break;
            case TIPO_RELACION_REPUESTA_GLOSA:
                str = "M3 - Respuesta Glosa";
                break;
            case TIPO_RELACION_RESPUESTA_GLOSA_MASIVA:
                str = "M3 - Respuesta Glosa Masiva";
                break;
            case TIPO_RELACION_CONCILIACION_INDIVIDUAL:
                str = "M4 - Conciliación Individual ";
                break;
            case TIPO_RELACION_CONCILIACION_MASIVA:
                str = "M4 - Conciliación Masiva";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }


    public boolean esMomentoDevolucion() {
        return getTipoTransaccion() == TIPO_TRANSACCION_DEVOLUCION;
    }

    public boolean esMomentoM1Radicacion() {
        return getTipoTransaccion() == TIPO_TRANSACCION_RADICACION;
    }

    public boolean esMomentoM2Causasion() {
        return getTipoTransaccion() == TIPO_TRANSACCION_CAUSACION;
    }
    
    public boolean esMomentoM2CausasionFe() {
        CmFeRipsCarga cmFeCargaRip =  Optional.ofNullable(getCmFeRipsCarga()).
                                    orElse(new CmFeRipsCarga());
        return cmFeCargaRip.getId() != null && ( getTipoTransaccion() == TIPO_TRANSACCION_CAUSACION ||
               ( esOperacionM2CierreIndividual() || esOperacionM2CierreMasivo() ) );
    }
    
    public boolean esMomentoM3RespuestaGlosa() {
        return getTipoTransaccion() == TIPO_TRANSACCION_RESPUESTA_GLOSA;
    }
    
    public boolean esMomentoM4Conciliacion() {
        return getTipoTransaccion() == TIPO_TRANSACCION_CONCILIACION;
    }
    
    public boolean esOperacionM1Radicacion(){
        return this.getTipoRelacion() == TIPO_RELACION_CARGA_RIPS;
    }
    
    public boolean esOperacionM2CierreIndividual(){
        return this.getTipoRelacion() == TIPO_RELACION_CIERRE_INDIVIDUAL;
    }
    
    public boolean esOperacionM2CierreMasivo(){
        return  this.getTipoRelacion() == TIPO_RELACION_CIERRE_MASIVO;
    }
    
    public boolean esOperacionDevolucionMasiva(){
        return  this.getTipoRelacion() == TIPO_RELACION_DEVOLUCION_MASIVA;
    }
    
    public boolean esOperacionDevolucionIndividual(){
        return  this.getTipoRelacion() == TIPO_RELACION_DEVOLUCION_INDIVIDUAL;
    }
    
    public boolean esOperacionM4ConciliacionMasiva(){
        return  this.getTipoRelacion() == TIPO_RELACION_CONCILIACION_MASIVA;
    }
    
    public boolean esOperacionM4ConciliacionIndividual(){
        return  this.getTipoRelacion() == TIPO_RELACION_CONCILIACION_INDIVIDUAL;
    }
    
    public boolean esOperacionM3RespuestaGlosa(){
        return  this.getTipoRelacion() == TIPO_RELACION_REPUESTA_GLOSA;
    }
    
    public boolean esOperacionM3RespuestaGlosaMasiva(){
        return  this.getTipoRelacion() == TIPO_RELACION_RESPUESTA_GLOSA_MASIVA;
    }
    
    public short getTipoTransaccionCalculada() {
        short tipoObtenido = TIPO_TRANSACCION_POR_DEFECTO;
        if (TIPO_TRANSACCION_POR_DEFECTO == getTipoTransaccion()) {
            if (esMomentoDevolucion()) {
                tipoObtenido = TIPO_TRANSACCION_DEVOLUCION;
            }
            if (esMomentoM1Radicacion()) {
                tipoObtenido = TIPO_TRANSACCION_RADICACION;
            }
            if (esMomentoM2Causasion()) {
                tipoObtenido = TIPO_TRANSACCION_CAUSACION;
            }
            if (esMomentoM3RespuestaGlosa()) {
                tipoObtenido = TIPO_TRANSACCION_RESPUESTA_GLOSA;
            }
            if (esMomentoM4Conciliacion()) {
                tipoObtenido = TIPO_TRANSACCION_CONCILIACION;
            }
        } else {
            tipoObtenido = getTipoTransaccion();
        }
        return tipoObtenido;
    }
    
    @Override
    public String toString() {
        return "CmRadicado{" + "id=" + id + ", radicado=" + radicado + ", estado_radicado=" + estado_radicado + '}';
    }

}
