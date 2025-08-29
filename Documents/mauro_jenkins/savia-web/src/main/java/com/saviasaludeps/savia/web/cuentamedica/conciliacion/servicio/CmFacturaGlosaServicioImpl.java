/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;


import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGestionUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaConceptoContableRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMotivosGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGestionUsuarioRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmFacturaGlosaBean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raul-palacios
 */
public class CmFacturaGlosaServicioImpl extends AccionesBO implements CmFacturaGlosaServicioIface {
    
    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;
    public static final int TIPO_OPERACION_MODIFIACION_VALORES_AUDITORIA = 5;
    public static final int LONGITUD_HISTORIA_PROCESO = 512;
    
    private CmFacturaGlosaRemoto getCmFacturaGlosaRemoto() throws Exception {
        return (CmFacturaGlosaRemoto) RemotoEJB.getEJBRemoto("CmFacturaGlosaServicio", CmFacturaGlosaRemoto.class.getName());
    }
    
    private CmGlosaRespuestaDetalleRemoto getCmGlosaRespuestaDetalleRemoto() throws Exception {
        return (CmGlosaRespuestaDetalleRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaDetalleServicio", CmGlosaRespuestaDetalleRemoto.class.getName());
    }
    
    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }
    
    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private CmGestionUsuarioRemoto getCmGestionUsuarioRemoto() throws Exception {
        return (CmGestionUsuarioRemoto) RemotoEJB.getEJBRemoto("CmGestionUsuarioServicio", CmGestionUsuarioRemoto.class.getName());
    }
    
    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }
    
      private CmAuditoriaDiagnosticoRemoto getCmAuditoriaDiagnosticoRemoto() throws Exception {
        return (CmAuditoriaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDiagnosticoServicio", CmAuditoriaDiagnosticoRemoto.class.getName());
    }
    
    private CmAuditoriaConceptoContableRemoto getCmAuditoriaConceptoContableRemoto() throws Exception {
        return (CmAuditoriaConceptoContableRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaConceptoContableServicio", CmAuditoriaConceptoContableRemoto.class.getName());
    }
    
    private CmAuditoriaMotivosGlosaRemoto getCmAuditoriaMotivosGlosaRemoto() throws Exception {
        return (CmAuditoriaMotivosGlosaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaMotivosGlosaServicio", CmAuditoriaMotivosGlosaRemoto.class.getName());
    }
    
    private CmAuditoriaAutorizacionRemoto getCmAuditoriaAutorizacionRemoto() throws Exception {
        return (CmAuditoriaAutorizacionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAutorizacionServicio", CmAuditoriaAutorizacionRemoto.class.getName());
    }
    
     private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }
     
    private CmAuditoriaDescuentoCapitaRemoto getCmAuditoriaDescuentoCapitaRemoto() throws Exception {
        return (CmAuditoriaDescuentoCapitaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDescuentoCapitaServicio", CmAuditoriaDescuentoCapitaRemoto.class.getName());
    }
    
    @Override
    public void Accion(CmFacturaGlosaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                      switch (bean.getDoAccion()) {
                          case CmFacturaGlosaBean.ACCION_VER_FACTURA:
                               verFactura(bean);
                            break;
                           case CmFacturaGlosaBean.ACCION_LISTAR_DETALLES:
                              listarDetalles(bean);
                            break;
                            case CmFacturaGlosaBean.ACCION_LISTAR_DETALLES_CON_VALOR_PENDIENTE:
                              bean.getParamConsultaServicios().setParametroConsulta3(true);
                              listarDetalles(bean);
                            break;
                            case CmFacturaGlosaBean.ACCION_LISTAR_DETALLES_SIN_PAGINAR:
                              listarDetallesSinPaginar(bean);
                            break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_GLOSA_RESPUESTA:
                              listarGlosaRespuesta(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_RESPUESTA_DETALLES:
                              listarGlosaRespuestaDetalles(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_VER_RESPUESTA_GLOSA:
                              verRespuestaGlosa(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_VER_DETALLE:
                              verDetalle(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_AUTORIZACIONES:
                              listarAuditoriaAutorizacion(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_CONCEPTOS:
                              listarAuditoriaConceptosContables(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_DIAGNOSTICOS:
                              listarAuditoriaDiagnosticos(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_MOTIVOS_GLOSA:
                              listarAuditoriaMotivosGlosa(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_ADJUNTOS_CMDETALLES:
                              listarAuditoriaAdjuntosCmDetalles(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_ADJUNTOS_FACTURA:
                              listarAdjuntosFactura(bean);
                              break;
                          case CmFacturaGlosaBean.ACCION_LISTAR_DESCUENTO_CAPITA:
                              listarDescuentoCapita(bean);
                              break;  
                    }
                    break;
                case Url.ACCION_CREAR:              
                    break;
                case Url.ACCION_GUARDAR:    
                    break;
                case Url.ACCION_EDITAR:                   
                    break;
                case Url.ACCION_MODIFICAR:           
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                              verFactura(bean);
                            break;
                        case Url.ACCION_GUARDAR:    
                              guardarRespuesta(bean);
                            break;
                         case Url.ACCION_MODIFICAR:    
                              actualizarBloqueoFactura(bean);
                            break;    
                        case CmFacturaGlosaBean.ACCION_BUSCAR_FACTURAS_BLOQUEDAS:
                            consultarFacturasBloquedas(bean);
                            break;
                        case CmFacturaGlosaBean.ACCION_MODIFICAR_ESTADO_AUDITORIA:
                            modificarEstadoAuditoria(bean);
                            break;
                        case CmFacturaGlosaBean.ACCION_GUARDAR_VALORES_AUDITORIA:
                            guardarValoresAuditoria(bean);
                            actualizarHistoriaProcesoFactura(bean, TIPO_OPERACION_MODIFIACION_VALORES_AUDITORIA);
                            break;            
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    bloquearFacturas(bean);
                    break;
                case Url.ACCION_ADICIONAL_3:
                    reporteRespuestaGlosa(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    guardarReintentoSincronizacion(bean);
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    private void listar(CmFacturaGlosaBean bean) {
        try {
            bean.getParamConsulta().setParametroConsulta4(bean.isHabilitarBusquedaMuliUsuario());
            bean.getParamConsulta().setCantidadRegistros(getCmFacturaGlosaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmFacturaGlosaRemoto().consultarLista(bean.getParamConsulta()));
            bean.getParamConsulta().setParametroConsulta4(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetallesSinPaginar(CmFacturaGlosaBean bean) {
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            bean.getParamConsultaServicios().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServicios().setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            bean.getParamConsultaServicios().setParametroConsulta3(1);
            bean.getParamConsultaServicios().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServicios()));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServicios()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetalles(CmFacturaGlosaBean bean) {
        try {
            bean.getParamConsultaServicios().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServicios().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServicios()));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServicios()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarGlosaRespuesta(CmFacturaGlosaBean bean) {
        try {
            bean.getParamConsultaRespuestaGlosa().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaRespuestaGlosa().setCantidadRegistros(getCmGlosaRespuestaRemoto().consultarCantidadLista(bean.getParamConsultaRespuestaGlosa()));
            bean.setRegistrosGlosaRespuesta(getCmGlosaRespuestaRemoto().consultarLista(bean.getParamConsultaRespuestaGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarGlosaRespuestaDetalles(CmFacturaGlosaBean bean) {
        try {
            bean.getParamConsultaDestallesGlosa().setCantidadRegistros(getCmGlosaRespuestaDetalleRemoto().consultarCantidadLista(bean.getParamConsultaDestallesGlosa()));
            bean.setRegistrosGlosaRespuestaDetalle(getCmGlosaRespuestaDetalleRemoto().consultarLista(bean.getParamConsultaDestallesGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
       private void listarAuditoriaMotivosGlosa(CmFacturaGlosaBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaMotivosGlosaRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaMotivoGlosa(getCmAuditoriaMotivosGlosaRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarAuditoriaAutorizacion(CmFacturaGlosaBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaAutorizacion(getCmAuditoriaAutorizacionRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarAuditoriaConceptosContables(CmFacturaGlosaBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaConceptoContableRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosConceptoContable(getCmAuditoriaConceptoContableRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarAuditoriaDiagnosticos(CmFacturaGlosaBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaDiagnosticoRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaDiagnostico(getCmAuditoriaDiagnosticoRemoto().consultarListaPorDetalle(paramConsulta));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void listarAuditoriaAdjuntosCmDetalles(CmFacturaGlosaBean bean) {
        try { 
            bean.setParamConsultaUtilitario( new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta3(bean.getDetalleServicioActual().getId());
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_DETALLE);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaAdjutoCmDetalle(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAdjuntosFactura(CmFacturaGlosaBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getObjeto().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setParametroConsulta4(CmAuditoriaAdjunto.TIPO_FACTURA);
            paramConsulta.setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAdjuntosFactura(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void listarDescuentoCapita(CmFacturaGlosaBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaDescuentoCapitaRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosDescuentoCapita(getCmAuditoriaDescuentoCapitaRemoto().consultarListaPorDetalle(paramConsulta));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
          
    private void verFactura(CmFacturaGlosaBean bean) {
        try {
            bean.setObjeto( getCmFacturaRemoto().consultar(bean.getObjeto().getId()) );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void verRespuestaGlosa(CmFacturaGlosaBean bean) {
        try {
             bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getCmGlosaRespuesta().getId());
             bean.setCmGlosaRespuesta(getCmGlosaRespuestaRemoto().consultar((int) bean.getParamConsultaUtilitario().getParametroConsulta1()));
             bean.getParamConsultaUtilitario().setParametroConsulta1(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void verDetalle(CmFacturaGlosaBean bean) {
        try {
            bean.setDetalleServicioActual(getCmDetalleRemoto().consultar(bean.getDetalleServicioActual().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void actualizarBloqueoFactura(CmFacturaGlosaBean bean){
        try {
            getCmFacturaRemoto().actualizarEstadoGestion(bean.getParamConsultaUtilitario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarRespuesta(CmFacturaGlosaBean bean) {
        try {
            int idRespuesta = 0;
            CmFactura factura = bean.getObjeto();
            List<CmFactura> registroVerificar = new ArrayList<>();
            registroVerificar.add(factura);
            BigDecimal valorPendienteTotalRespuestaDetalle = new BigDecimal("0");
            BigDecimal valorPagadoTotalRespuestaDetalle = new BigDecimal("0");
            BigDecimal valorFacturadoTotalRespuestaDetalle = new BigDecimal("0");
            boolean respuestaConValoresEpsIps = true;
            boolean hayGlosadoDetalles = false;
            
            if (verificarBloqueoFacturas(bean, registroVerificar)) {

                CmGlosaRespuestaDetalle respuestaDetalle = factura.getCmGlosaRespuestaDetalle();
                CmGlosaRespuesta respuesta = new CmGlosaRespuesta();
                respuesta.setCmFactura(new CmFactura(factura.getId()));
                respuesta.setTipoRespuesta(respuestaDetalle.getTipoEstadoRespuesta());
                String observacionRespuesta = respuestaDetalle.getObservacion() == null
                        || respuestaDetalle.getObservacion().equals("") ? " "
                        : respuestaDetalle.getObservacion();
                respuesta.setObservacion(observacionRespuesta);
                respuesta.setEstadoSincronizacion(CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA);
                respuesta.setValorPendiente(new BigDecimal("0"));
                respuesta.setValorPagado(new BigDecimal("0"));
                respuesta.setValorCobroDetalle(new BigDecimal("0"));
                respuesta.setValorFacturado(new BigDecimal("0"));
                respuesta.setValorPagadoEps(new BigDecimal("0"));
                respuesta.setValorAceptadoIps(new BigDecimal("0"));
                bean.auditoriaGuardar(respuesta);

                if (CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION == respuestaDetalle.getTipoEstadoRespuesta()) {
                    respuesta.setValorPendiente(factura.getValorPendienteActual());
                    respuesta.setValorFacturado(factura.getValorFactura());
                    idRespuesta = getCmGlosaRespuestaRemoto().insertar(respuesta);
                    respuesta.setId(idRespuesta);
                    
                    for (CmDetalle detalle : bean.getRegistrosDetalles()) {
                        BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
                        if (validarDetalleCandidatoRatificar(detalle)) {
                            valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                                    add(detalle.getValorFacturado()).
                                    setScale(4, RoundingMode.HALF_UP);
                            valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                                    add(valorPendienteActualDetalle).
                                    setScale(4, RoundingMode.HALF_UP);
                            insertarRespuestaDetalleRatificada(idRespuesta, detalle, bean);
                            borrarValoresAuditoria(detalle.getId());
                        }
                    }
                    respuesta.setEstadoSincronizacion(CmGlosaRespuesta.ESTADO_SINCRONIZACION_RATIFICADA);
                    respuesta.setValorCobroDetalle(valorFacturadoTotalRespuestaDetalle);
                    respuesta.setValorFacturado(valorFacturadoTotalRespuestaDetalle);
                    respuesta.setValorPagado(valorPagadoTotalRespuestaDetalle);
                    respuesta.setValorPendiente(valorPendienteTotalRespuestaDetalle);
                    getCmGlosaRespuestaRemoto().actualizar(respuesta);
                }

                if (CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA == respuestaDetalle.getTipoEstadoRespuesta()) {
                    
                    respuesta.setValorPagadoEps(respuestaDetalle.getValorPagadoEpsPrecalculado());
                    respuesta.setValorAceptadoIps(respuestaDetalle.getValorAceptadoIpsPrecalculado());
                    idRespuesta = getCmGlosaRespuestaRemoto().insertar(respuesta);
                    respuesta.setId(idRespuesta);

                    for (CmDetalle detalle : bean.getRegistrosDetalles()) {

                        BigDecimal valorEPS = detalle.getValorPagadoEPS() == null
                                ? new BigDecimal("0") : detalle.getValorPagadoEPS();
                        BigDecimal valorIPS = detalle.getValorAceptadoIPS() == null
                                ? new BigDecimal("0") : detalle.getValorAceptadoIPS();
                        detalle.setValorAceptadoIPS(valorIPS);
                        detalle.setValorPagadoEPS(valorEPS);
                        String observacionGlosa = detalle.getObservacionGlosa() == null
                          || detalle.getObservacionGlosa().equals("") ? " "
                          : detalle.getObservacionGlosa();
                        String observacionHistorica = detalle.getObservacionRespuestaDetalles() == null ?
                                                      "": " - "+detalle.getObservacionRespuestaDetalles();
                        observacionGlosa += observacionHistorica;
                        
                        detalle.setObservacionGlosa(observacionGlosa);
                        
                        BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
                        detalle.setValorPendienteActual(valorPendienteActualDetalle);
                        
                        if (detalle.getValorPendienteActual().compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                                && (detalle.getValorPagadoEPS().compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                                || detalle.getValorAceptadoIPS().compareTo(new BigDecimal(BigInteger.ZERO)) > 0)) {
                            hayGlosadoDetalles = true;
                            respuestaDetalle.setDocumento(detalle.getDocumento());
                            respuestaDetalle.setCmGlosaRespuesta(new CmGlosaRespuesta(idRespuesta));
                            respuestaDetalle.setCmDetalle(new CmDetalle(detalle.getId()));
                            // se cobro detalle y facturado se toman de facturado detalle
                            respuestaDetalle.setValorCobroDetalle(detalle.getValorFacturado());
                            respuestaDetalle.setValorFacturado(detalle.getValorFacturado());
                            respuestaDetalle.setObservacion(detalle.getObservacionGlosa());
                            //se calculan y asignan valores para CMGLOSA_RESPUESTA_DETALLE
                            //(valorPagado, valor pendiente)
                            calcularPorcentajesRespuestaDetalle(detalle, respuestaDetalle);

                            valorPagadoTotalRespuestaDetalle = valorPagadoTotalRespuestaDetalle.
                                    add(respuestaDetalle.getValorPagado()).
                                    setScale(4, RoundingMode.HALF_UP);

                            valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                                    add(respuestaDetalle.getValorPendiente()).
                                    setScale(4, RoundingMode.HALF_UP);

                            valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                                    add(respuestaDetalle.getValorFacturado()).
                                    setScale(4, RoundingMode.HALF_UP);

                            bean.auditoriaGuardar(respuestaDetalle);
                            getCmGlosaRespuestaDetalleRemoto().insertar(respuestaDetalle);
                            //borra valores auditoria de detalle
                            borrarValoresAuditoria(detalle.getId());
                            limpiarValoresDetallesAuditables(detalle);
                            //actualiza: valor pendiente actual detalle - valor pagado respuesta detalle
                            actualizarValorPendienteActualDetalles(detalle, respuestaDetalle, bean);
                        } else {
                            if ( validarDetalleCandidatoRatificar(detalle) ) {
                               
                                valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                                    add(detalle.getValorFacturado()).
                                    setScale(4, RoundingMode.HALF_UP);
                                valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                                    add(detalle.getValorPendienteActual()).
                                    setScale(4, RoundingMode.HALF_UP);
                                insertarRespuestaDetalleRatificada(idRespuesta, detalle, bean);
                                //borra valores auditoria de detalle
                                borrarValoresAuditoria(detalle.getId());
                            }
                        }
                    }
                    //actualiza : respuesta con acumulados de sumas de detalles
                    respuesta.setValorCobroDetalle(valorFacturadoTotalRespuestaDetalle);
                    respuesta.setValorFacturado(valorFacturadoTotalRespuestaDetalle);
                    respuesta.setValorPagado(valorPagadoTotalRespuestaDetalle);
                    respuesta.setValorPendiente(valorPendienteTotalRespuestaDetalle);
                    respuesta.setEstadoSincronizacion( hayGlosadoDetalles ? CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA : CmGlosaRespuesta.ESTADO_SINCRONIZACION_RATIFICADA );
                    
                    if( respuesta.getValorAceptadoIps().compareTo(new BigDecimal(BigInteger.ZERO)) == 0 &&
                        respuesta.getValorPagadoEps().compareTo(new BigDecimal(BigInteger.ZERO)) == 0 ){
                        respuestaConValoresEpsIps = false;
                        //respuesta.setTipoRespuesta(CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION);
                    }     
                    
                    getCmGlosaRespuestaRemoto().actualizar(respuesta);

                    //actualiza: valor pendente actual factura - valor pagado respuesta
                    actualizarValorPendienteActualFactura(factura, respuesta, bean);
                }
                actualizarHistoriaProcesoFactura(bean, respuestaDetalle.getTipoEstadoRespuesta());
                bean.setParamConsultaUtilitario(new ParamConsulta());
                bean.getParamConsultaUtilitario().setParametroConsulta1(factura.getId());
                bean.getParamConsultaUtilitario().setParametroConsulta2(null);
                bean.getParamConsultaUtilitario().setParametroConsulta3(null);
                bean.getParamConsultaUtilitario().setParametroConsulta4(0);
                getCmFacturaGlosaRemoto().actualizarBloqueoFactura(bean.getParamConsultaUtilitario());
                if ( respuesta.getId() != null && respuesta.getId() > 0 && 
                    respuesta.getTipoRespuesta() != CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION && 
                    respuestaConValoresEpsIps ) {
                    CmEnviosGlosasServicioImpl enviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
                    enviosGlosasServicioImpl.crearRadicadoXCmGlosaRta(respuesta.getId());
                }
                bean.addMensaje(" Se creo la respuesta glosa de manera exitosa con id " + idRespuesta);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void actualizarValorPendienteActualFactura(CmFactura factura,
            CmGlosaRespuesta glosaRespuesta,
            CmFacturaGlosaBean bean) throws Exception {
        //MathContext mc  = new MathContext(4);
        BigDecimal valorPendienteActual = factura.getValorPendienteActual() == null
                ? new BigDecimal("0") : factura.getValorPendienteActual();
        BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(glosaRespuesta.getValorPagado())
                .setScale(4, RoundingMode.HALF_UP);
        if(nuevoPendientaActual.compareTo(new BigDecimal("0.1")) <= 0 && 
           nuevoPendientaActual.compareTo(new BigDecimal("0.0001")) >= 0){
           nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }
        factura.setValorPendienteActual(nuevoPendientaActual);
        bean.auditoriaModificar(factura);
        getCmFacturaRemoto().actualizar(factura);
    }
     
    private void actualizarValorPendienteActualDetalles(CmDetalle detalle,
               CmGlosaRespuestaDetalle respuestaDetalle, CmFacturaGlosaBean bean ) throws Exception{
        
        
            //MathContext mc  = new MathContext(4);
            BigDecimal valorPendienteActual = detalle.getValorPendienteActual() == null ? 
                                              new BigDecimal("0") : detalle.getValorPendienteActual();
            BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuestaDetalle.getValorPagado())
                                             .setScale(4, RoundingMode.HALF_UP);
            
            if (nuevoPendientaActual.equals(new BigDecimal("-0.0001"))) {
                nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
            }
            detalle.setValorPendienteActual(nuevoPendientaActual);
            
            bean.auditoriaModificar(detalle);
            getCmDetalleRemoto().actualizar(detalle);
     }
    
    public void limpiarValoresDetallesAuditables(CmDetalle detalle){
        detalle.setValorPagadoEPS(null);
        detalle.setValorAceptadoIPS(BigDecimal.ZERO);
        detalle.setPorcentajeAceptadoIPS(null);
        detalle.setPorcentajePagadoEPS(null);
        detalle.setObservacionRespuestaDetalles("");
    }

    private void calcularPorcentajesRespuestaDetalle(CmDetalle detalle, CmGlosaRespuestaDetalle respuestaDetalle){

      
        BigDecimal valorPendientePagoDetalle = detalle.getValorPendienteActual() == null ? 
                                         new BigDecimal("0") : detalle.getValorPendienteActual() ;
        
        respuestaDetalle.setValorPagadoEps  ( detalle.getValorPagadoEPS() );
        respuestaDetalle.setValorAceptadoIps( detalle.getValorAceptadoIPS() );
        respuestaDetalle.setPorcentajeAceptadoIps(detalle.getPorcentajeAceptadoIPS());
        respuestaDetalle.setPorcentajePagadoEps(detalle.getPorcentajePagadoEPS());
        
        // Valor pagado = valor aceptado ips + valor pagado eps
         BigDecimal valorPagado = respuestaDetalle.getValorAceptadoIps().
                    add(respuestaDetalle.getValorPagadoEps()).setScale(4, RoundingMode.HALF_UP);
         respuestaDetalle.setValorPagado(valorPagado);
        
         //Valor pendiente = valor pendiente en detalle -  valor pagado(eps+ips)
         BigDecimal valorPendiente = valorPendientePagoDetalle.subtract(respuestaDetalle.getValorPagado())
                                     .setScale(4, RoundingMode.HALF_UP);
        
         if(valorPendiente.equals(new BigDecimal("-0.0001"))){
            valorPendiente = new BigDecimal(BigInteger.ZERO);
         }
         
         respuestaDetalle.setValorPendiente(valorPendiente);   
     }

    @Override
    public void cargaInial(CmFacturaGlosaBean bean) {
        try {
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
     
    private void consultarFacturasBloquedas(CmFacturaGlosaBean bean) {
        try {
            bean.setFacturasBloquedas(getCmFacturaRemoto().consultarFacturasBloquedas(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificarEstadoAuditoria(CmFacturaGlosaBean bean) {
        try {
            getCmFacturaGlosaRemoto().actualizarTipoAuditoria(bean.getParamConsultaUtilitario());
            if(bean.getParamConsultaUtilitario() != null ) {
                if(bean.getParamConsultaUtilitario().getParametroConsulta2().equals(0)){
                    bean.addMensaje("Se ha realizado el cambio de estado sin auditoría"); 
                }else{
                    bean.addMensaje("Se ha realizado el cambio de estado auditoría");
                }  
            }   
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarValoresAuditoria(CmFacturaGlosaBean bean){
        try {
            if (bean.getDestallesConValoresAuditar() != null) {
                for (CmDetalle registrosDetalle : bean.getDestallesConValoresAuditar()) {
                    
                    CmGestionUsuario historialValores = new CmGestionUsuario();
                    historialValores.setEstado(CmGestionUsuario.ESTADO_GUARDADO_TEMPORAL_RESPUESTA);
                    historialValores.setCmDetalle(new CmDetalle(registrosDetalle.getId()));
                    
                    if (registrosDetalle.getValorPagadoEPS() != null
                            && registrosDetalle.getPorcentajePagadoEPS() != null) {
                        bean.getParamConsultaAuditoria().setParametroConsulta1(registrosDetalle.getValorPagadoEPS());
                        bean.getParamConsultaAuditoria().setParametroConsulta2(registrosDetalle.getPorcentajePagadoEPS());
                        historialValores.setValorPagadoEps(registrosDetalle.getValorPagadoEPS());
                        historialValores.setPorcentajePagadoEps(registrosDetalle.getPorcentajePagadoEPS());
                        
                    }
                    if (registrosDetalle.getValorAceptadoIPS() != null
                            && registrosDetalle.getPorcentajeAceptadoIPS() != null) {
                        bean.getParamConsultaAuditoria().setParametroConsulta3(registrosDetalle.getValorAceptadoIPS());
                        bean.getParamConsultaAuditoria().setParametroConsulta4(registrosDetalle.getPorcentajeAceptadoIPS());
                        historialValores.setValorAceptadoIps(registrosDetalle.getValorAceptadoIPS());
                        historialValores.setPorcentajeAceptadoIps(registrosDetalle.getPorcentajeAceptadoIPS());
                    }
                    if (registrosDetalle.getObservacionGlosa() != null) {
                        String observacionHistorica = registrosDetalle.getObservacionRespuestaDetalles() == null ? 
                                                  "" :  registrosDetalle.getObservacionRespuestaDetalles();
                        String observacionConcatenada;                        
                        if(observacionHistorica.equals(registrosDetalle.getObservacionGlosa())){
                            observacionConcatenada = registrosDetalle.getObservacionGlosa(); 
                        }else{               
                            observacionHistorica = !"".equals(observacionHistorica)  ? " - " + observacionHistorica: "";
                            observacionConcatenada = registrosDetalle.getObservacionGlosa() + observacionHistorica; 
                        }                        
                        bean.getParamConsultaAuditoria().setParametroConsulta5(observacionConcatenada);
                    }

                    bean.getParamConsultaAuditoria().setParametroConsulta6(registrosDetalle.getId());
                    getCmFacturaGlosaRemoto().actualizarValoresAuditoria(bean.getParamConsultaAuditoria());
                    bean.setParamConsultaAuditoria(new ParamConsulta());
                   
                    bean.auditoriaGuardar(historialValores);
                    historialValores.setUsuarioGestiona(historialValores.getUsuarioCrea());
                    getCmGestionUsuarioRemoto().insertar(historialValores);
                }
            }
            bean.addMensaje("Se han guardado valores de detalles en el sistema ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarReintentoSincronizacion(CmFacturaGlosaBean bean) {
        try {
            CmGlosaRespuesta glosa = getCmGlosaRespuestaRemoto().consultar(bean.getCmGlosaRespuesta().getId());
            if (glosa != null) {
                
                if (CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION == glosa.getTipoRespuesta()
                        || (glosa.getValorAceptadoIps().compareTo(new BigDecimal(BigInteger.ZERO)) == 0
                        && glosa.getValorPagadoEps().compareTo(new BigDecimal(BigInteger.ZERO)) == 0)) {
                    bean.addError("La glosa tiene sus valores Eps e Ips en cero, por esto no se puede sincronizar.");
                } else {
                    Integer idCmRadicado = glosa.getCmRadicado() != null && glosa.getCmRadicado().getId() != null ? glosa.getCmRadicado().getId() : null;
                    Integer idCmFactura = glosa.getCmFactura() != null && glosa.getCmFactura().getId() != null ? glosa.getCmFactura().getId() : null;
                    if (idCmRadicado != null && idCmFactura != null) {
                        CmSincronizacionEncabezado sincro = getCmSincronizacionEncabezadoRemoto().consultarPorRadicadoFactura(idCmRadicado, idCmFactura);
                        if (sincro != null && sincro.getId() != null) {
                            bean.addError("La factura ya tiene un proceso id sincronización encabezado : " + sincro.getId());
                        }
                    }
                    if (!bean.isError()) {
                        CmEnviosGlosasServicioImpl enviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
                        enviosGlosasServicioImpl.crearRadicadoXCmGlosaRta(bean.getCmGlosaRespuesta().getId());
                    }
                }
            } else {
                bean.addError("El identificador de la respuesta glosa no existe, por favor verifique.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void borrarValoresAuditoria(int idDetalle) {
        try {
            if (idDetalle > 0) {
                ParamConsulta parametroConsulta = new ParamConsulta();
                parametroConsulta.setParametroConsulta1(idDetalle);
                getCmFacturaGlosaRemoto().borrarValoresAuditoria(parametroConsulta);
            }
        } catch (Exception e) {
             Logger.getLogger(CmFacturaGlosaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void reporteRespuestaGlosa(CmFacturaGlosaBean bean) {
        List<ReporteRespuestaGlosa> listaReportes = new ArrayList();
        try {
            int idGlosaRespuesta = bean.getReporteRespuestaGlosa().getId();
            boolean repuesta =  getCmGlosaRespuestaRemoto().verificacionEnvioSapExitoso(idGlosaRespuesta);
            if( repuesta ){
                listaReportes = getCmGlosaRespuestaRemoto().reporteRespuestaGlosa(idGlosaRespuesta);
            }else{
               String mensaje = "La respuesta de id "+idGlosaRespuesta+" no ha sido enviada a Sap, o esta en un estado donde no se permite generar pdf.";
               bean.addError(mensaje);
            }        
            bean.setListaReporteRespuestaGlosa(listaReportes);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void bloquearFacturas(CmFacturaGlosaBean bean){
        try {
            String mensaje =    bean.getParamConsultaUtilitario().getParametroConsulta2() != null && 
                                bean.getParamConsultaUtilitario().getParametroConsulta3() != null ? "Se ha realizado bloqueo " : "Se ha realizado desbloqueo "  ;  
            mensaje +=  "de la factura " +bean.getParamConsultaUtilitario().getParametroConsulta1();
            getCmFacturaGlosaRemoto().actualizarBloqueoFactura(bean.getParamConsultaUtilitario());
            bean.addMensaje(mensaje);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private boolean verificarBloqueoFacturas(CmFacturaGlosaBean bean, List<CmFactura> registrosFacturaSeleccionada){
        boolean facturaPermitida = true;
        try {
            int usuarioGestiona = bean.getConexion().getUsuario().getId();
            List<String> listIdFacturasEncontradas = new ArrayList<>();
            for (CmFactura cmFactura : registrosFacturaSeleccionada) {
                listIdFacturasEncontradas.add(String.valueOf(cmFactura.getId()));
            }
            String idsFacturas = String.join(",", listIdFacturasEncontradas);
            bean.getParamConsultaUtilitario().setParametroConsulta1(idsFacturas);
            bean.getParamConsultaUtilitario().setParametroConsulta2(usuarioGestiona);
            String facturasEncontradasBloqueadas = getCmFacturaRemoto().consultarFacturasBloquedas(bean.getParamConsultaUtilitario());
            bean.getParamConsultaUtilitario().setParametroConsulta1(null);
            bean.getParamConsultaUtilitario().setParametroConsulta2(null);
            if (facturasEncontradasBloqueadas != null && facturasEncontradasBloqueadas.length() > 0) {
                    bean.addError("Las siguentes facturas estan bloquedas por favor contacte administrador, "
                            + facturasEncontradasBloqueadas);
                    facturaPermitida = false;
            }
            
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
        return facturaPermitida;
    }
     
    private void actualizarHistoriaProcesoFactura(CmFacturaGlosaBean bean, int tipoOperacion){
       try{
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
           String fecha = sdf.format(new Date());
           ParamConsulta parametroActualizacion = new ParamConsulta();
           CmFactura factura = bean.getObjeto();
            String usuarioGestiona = bean.getConexion().getUsuario().getUsuario() +
                    " (" + bean.getConexion().getUsuario().getNombre() + ")";
           String historial = factura.getHistorialProceso() == null ? "" : factura.getHistorialProceso();
           String postFijoMensaje = "";
           switch (tipoOperacion) {
               case CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION:
               case CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA:
               case CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION:
                   CmGlosaRespuesta respuesta = new CmGlosaRespuesta();
                   respuesta.setTipoRespuesta(tipoOperacion);
                   postFijoMensaje = respuesta.getTipoRespuestaStr();
                   break;
                   case TIPO_OPERACION_MODIFIACION_VALORES_AUDITORIA:
                         postFijoMensaje = " guardado de valores de detalles para auditar.";
                   break;
               default:
                   break;
           } 
           String nuevoProceso = "- "+ usuarioGestiona +" el " + fecha + " ha realizado "
                   + postFijoMensaje;
           nuevoProceso = nuevoProceso + historial;
           if(LONGITUD_HISTORIA_PROCESO <= nuevoProceso.length()){
               nuevoProceso = nuevoProceso.substring(0, LONGITUD_HISTORIA_PROCESO-1);
           }
           parametroActualizacion.setParametroConsulta1(nuevoProceso);
           parametroActualizacion.setParametroConsulta2(factura.getId());
           getCmFacturaRemoto().actualizarHistorialProcesoFactura(parametroActualizacion);
       } catch (Exception ex) {
            Logger.getLogger(CmFacturaGlosaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insertarRespuestaDetalleRatificada( int idRespuesta, CmDetalle detalle, CmFacturaGlosaBean bean) {
       
        CmGlosaRespuestaDetalle respuestaDetalle = new CmGlosaRespuestaDetalle();
        BigDecimal valorDefectoCero = new BigDecimal(BigInteger.ZERO);

        try {
                respuestaDetalle.setCmGlosaRespuesta(new CmGlosaRespuesta(idRespuesta));
                respuestaDetalle.setCmDetalle(new CmDetalle(detalle.getId()));
                respuestaDetalle.setDocumento(detalle.getDocumento());
                respuestaDetalle.setValorCobroDetalle(detalle.getValorFacturado());
                respuestaDetalle.setValorFacturado(detalle.getValorFacturado());
                respuestaDetalle.setValorPagado(valorDefectoCero);
                respuestaDetalle.setValorPagadoEps(valorDefectoCero);
                respuestaDetalle.setPorcentajePagadoEps(null);
                respuestaDetalle.setValorPendiente(detalle.getValorPendienteActual());
                respuestaDetalle.setValorAceptadoIps(valorDefectoCero);
                respuestaDetalle.setPorcentajeAceptadoIps(null);
                respuestaDetalle.setObservacion(detalle.getObservacionGlosa());
                bean.auditoriaGuardar(respuestaDetalle);
                getCmGlosaRespuestaDetalleRemoto().insertar(respuestaDetalle);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean validarDetalleCandidatoRatificar(CmDetalle detalle) {
        boolean esCandidato = false;
        BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
        if (valorPendienteActualDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                && (detalle.getValorPagadoEPS().compareTo(new BigDecimal(BigInteger.ZERO)) == 0
                && detalle.getValorAceptadoIPS().compareTo(new BigDecimal(BigInteger.ZERO)) == 0)
                && (detalle.getObservacionGlosa() != null
                && CmFacturaGlosaBean.LONGITUD_OBSERVACION_RATIFICACION < detalle.getObservacionGlosa().trim().length())) {
            esCandidato = true;
        }
        return esCandidato;
    }

}
