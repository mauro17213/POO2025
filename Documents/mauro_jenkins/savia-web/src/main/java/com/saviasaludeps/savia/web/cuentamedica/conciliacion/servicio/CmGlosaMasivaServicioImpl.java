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
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
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
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaMasivaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmFacturaGlosaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmGlosaMasivaBean;
import static com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmGlosaMasivaBean.DIAS_ADICIONALES_BLOQUEO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jepn
 */
public class CmGlosaMasivaServicioImpl extends AccionesBO implements CmGlosaMasivaServicioIface {
    
    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;
    public static final int TIPO_OPERACION_MODIFIACION_VALORES_AUDITORIA = 5;
    public static final int LONGITUD_HISTORIA_PROCESO = 512;
    private boolean realizarEnvioSap;
    
    
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
    
    private CmGlosaMasivaRemoto getCmGlosaMasivaRemoto() throws Exception {
        return (CmGlosaMasivaRemoto) RemotoEJB.getEJBRemoto("CmGlosaMasivaServicio", CmGlosaMasivaRemoto.class.getName());
    }
    
    private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }
    
    private CmAuditoriaDescuentoCapitaRemoto getCmAuditoriaDescuentoCapitaRemoto() throws Exception {
        return (CmAuditoriaDescuentoCapitaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDescuentoCapitaServicio", CmAuditoriaDescuentoCapitaRemoto.class.getName());
    }

    public boolean hayEnvioParaSap() {
        return realizarEnvioSap;
    }

    public void setRealizarEnvioSap(boolean realizarEnvioSap) {
        this.realizarEnvioSap = realizarEnvioSap;
    }
    
    @Override
    public void Accion(CmGlosaMasivaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                      switch (bean.getDoAccion()) {
                          case CmGlosaMasivaBean.ACCION_VER_FACTURA:
                               verFactura(bean);
                            break;
                           case CmGlosaMasivaBean.ACCION_LISTAR_DETALLES:
                              listarDetalles(bean);
                            break;
                            case CmGlosaMasivaBean.ACCION_LISTAR_DETALLES_CON_VALOR_PENDIENTE:
                              bean.getParamConsultaServicios().setParametroConsulta3(true);
                              listarDetalles(bean);
                            break;
                            case CmGlosaMasivaBean.ACCION_LISTAR_DETALLES_SIN_PAGINAR:
                              listarDetallesSinPaginar(bean);
                            break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_GLOSA_RESPUESTA:
                              listarGlosaRespuesta(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_RESPUESTA_DETALLES:
                              listarGlosaRespuestaDetalles(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_VER_RESPUESTA_GLOSA:
                              verRespuestaGlosa(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_VER_DETALLE:
                              verDetalle(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_AUTORIZACIONES:
                              listarAuditoriaAutorizacion(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_CONCEPTOS:
                              listarAuditoriaConceptosContables(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_DIAGNOSTICOS:
                              listarAuditoriaDiagnosticos(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_MOTIVOS_GLOSA:
                              listarAuditoriaMotivosGlosa(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_VER_FACTURAS_GESTIONADAS_POR_OTRO_USUARIO:
                              consultarFacturasGestionadasPorOtrosUsuarios(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_DETALLES_MULTI_FACTURA:
                              listarDetallesMultiFacturaConValoresPendientes(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_VER_MOTIVOS_ESPECIFICOS_USADOS:
                              verMotivosEspecificosUsados(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_DESCARGAR_REPORTE_GLOSA:
                              descargarRespuestaGlosa(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_ADJUNTOS_CMDETALLES:
                              listarAuditoriaAdjuntosCmDetalles(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_ADJUNTOS_FACTURA:
                              listarAdjuntosFactura(bean);
                              break;
                          case CmGlosaMasivaBean.ACCION_LISTAR_DESCUENTO_CAPITA:
                              listarDescuentoCapita(bean);
                              break; 

                    }
                    break;
                case Url.ACCION_CREAR:              
                    break;
                case Url.ACCION_GUARDAR:  
                     switch (bean.getDoAccion()) {
                       case CmGlosaMasivaBean.ACCION_GUARDAR_BLOQUEO_FACTURA_USADA:    
                            actualizarBloqueoFactura(bean);
                            break;
                        case CmGlosaMasivaBean.ACCION_GUARDAR_ESTADO_AUDITORIA:
                            guardarEstadoAuditoria(bean);
                            break;
                        case CmGlosaMasivaBean.ACCION_GUARDAR_RESPUESTA_GLOSA:
                            guardarRespuestaGlosaMasiva(bean);
                            break;
                        case CmGlosaMasivaBean.ACCION_GUARDAR_PORCENTAJES_DETALLES_GLOSADOS:
                            guardarValoresAuditoria(bean);
                            break;
                        case CmGlosaMasivaBean.ACCION_MARCAR_FACTURAS:
                            marcarFacturas(bean);
                            break;
                        case CmGlosaMasivaBean.ACCION_DESMARCAR_FACTURAS:
                            desMarcarFacturas(bean);
                            break;
                    }
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
                            break;
                         case Url.ACCION_MODIFICAR:
                            break;         
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:  
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    private void listar(CmGlosaMasivaBean bean) {
        try {
            boolean esFiltroOmitirFacturasCargaElectronica = true;
            bean.getParamConsulta().setParametroConsulta4(bean.isHabilitarBusquedaMuliUsuario());
            bean.getParamConsulta().setParametroConsulta5(esFiltroOmitirFacturasCargaElectronica);
            bean.getParamConsulta().setCantidadRegistros(getCmFacturaGlosaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmFacturaGlosaRemoto().consultarLista(bean.getParamConsulta()));
            bean.getParamConsulta().setParametroConsulta4(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetallesSinPaginar(CmGlosaMasivaBean bean) {
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
    
    private List<CmDetalle> listarTodosDetallesConValorPendientesActuales(int idFactura) {
        List<CmDetalle> detalles;
        try {

            int CONSULTAR_SIN_PAGINAR = 1;
            ParamConsulta paramConsultaServicios = new ParamConsulta();
            paramConsultaServicios.setParametroConsulta1(idFactura);
            paramConsultaServicios.setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            paramConsultaServicios.setParametroConsulta3(1);
            detalles = getCmDetalleRemoto().consultarListaDetallesPorFactura(paramConsultaServicios);
        } catch (Exception e) {
            detalles = new ArrayList<>();
        }
        return detalles;
    }
    
    private void listarDetalles(CmGlosaMasivaBean bean) {
        try {
            bean.getParamConsultaServicios().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServicios().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServicios()));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServicios()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetallesMultiFacturaConValoresPendientes(CmGlosaMasivaBean bean) {
        try {
            bean.getParamConsultaDetallesPorFactura().setParametroConsulta2(true);
            bean.getParamConsultaDetallesPorFactura().setParametroConsulta3(true);
            bean.getParamConsultaDetallesPorFactura().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesMultiFactura(bean.getParamConsultaDetallesPorFactura()));
            bean.setRegistrosDetallesMultifactura(getCmDetalleRemoto().consultarListaDetallesMultiFactura(bean.getParamConsultaDetallesPorFactura()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarGlosaRespuesta(CmGlosaMasivaBean bean) {
        try {
            bean.getParamConsultaRespuestaGlosa().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaRespuestaGlosa().setCantidadRegistros(getCmGlosaRespuestaRemoto().consultarCantidadLista(bean.getParamConsultaRespuestaGlosa()));
            bean.setRegistrosGlosaRespuesta(getCmGlosaRespuestaRemoto().consultarLista(bean.getParamConsultaRespuestaGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarGlosaRespuestaDetalles(CmGlosaMasivaBean bean) {
        try {
            bean.getParamConsultaDestallesGlosa().setCantidadRegistros(getCmGlosaRespuestaDetalleRemoto().consultarCantidadLista(bean.getParamConsultaDestallesGlosa()));
            bean.setRegistrosGlosaRespuestaDetalle(getCmGlosaRespuestaDetalleRemoto().consultarLista(bean.getParamConsultaDestallesGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
       private void listarAuditoriaMotivosGlosa(CmGlosaMasivaBean bean) {
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
     
     private void listarAuditoriaAutorizacion(CmGlosaMasivaBean bean) {
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
     
     private void listarAuditoriaConceptosContables(CmGlosaMasivaBean bean) {
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
     
     private void listarAuditoriaDiagnosticos(CmGlosaMasivaBean bean) {
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
     
     private void listarAuditoriaAdjuntosCmDetalles(CmGlosaMasivaBean bean) {
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
     
     private void listarAdjuntosFactura(CmGlosaMasivaBean bean) {
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
     
    private void listarDescuentoCapita(CmGlosaMasivaBean bean) {
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
          
    private void verFactura(CmGlosaMasivaBean bean) {
        try {
            bean.setObjeto( getCmFacturaRemoto().consultar(bean.getObjeto().getId()) );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void verRespuestaGlosa(CmGlosaMasivaBean bean) {
        try {
             bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getCmGlosaRespuesta().getId());
             bean.setCmGlosaRespuesta(getCmGlosaRespuestaRemoto().consultar((int) bean.getParamConsultaUtilitario().getParametroConsulta1()));
             bean.getParamConsultaUtilitario().setParametroConsulta1(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void verDetalle(CmGlosaMasivaBean bean) {
        try {
            bean.setDetalleServicioActual(getCmDetalleRemoto().consultar(bean.getDetalleServicioActual().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void verMotivosEspecificosUsados(CmGlosaMasivaBean bean) {
        try { 
            bean.setListaCmAuditoriaMotivoGlosa(getCmAuditoriaMotivosGlosaRemoto().consultarPorMultiFacturas(bean.getIdsFacturasProcesoMasivo()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void actualizarBloqueoFactura(CmGlosaMasivaBean bean){
        try {
            getCmFacturaRemoto().actualizarEstadoGestion(bean.getParamConsultaUtilitario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void evaluarSiHayEnvioSap(CmGlosaRespuesta cmGlosaRespuesta){   
        if(cmGlosaRespuesta.esGlosaRespuestaValida() && cmGlosaRespuesta.isProcesadaSap()){
            setRealizarEnvioSap(true);
        }
    }
    
    private void contarTipoFacturasGlosaMasiva(CmGlosaMasivaN cmGlosasMasivas, CmGlosaRespuesta cmGlosaRespuesta){
        try {
             int respuesta =    cmGlosaRespuesta.getEstadoSincronizacion() == CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA ? 1 : 0;
             int ratifiacion =  cmGlosaRespuesta.getEstadoSincronizacion() == CmGlosaRespuesta.ESTADO_SINCRONIZACION_RATIFICADA ? 1 : 0; 
             cmGlosasMasivas.setCantidadFacturasRatificacionGlosa(cmGlosasMasivas.getCantidadFacturasRatificacionGlosa()+ratifiacion);
             cmGlosasMasivas.setCantidadFacturasRespuestaGlosa(cmGlosasMasivas.getCantidadFacturasRespuestaGlosa()+respuesta);
             cmGlosasMasivas.setHoraFinalizacionRegistro(new Date());
        } catch (Exception e) {
        }
    }
    
    private void guardarRespuestaGlosaMasiva(CmGlosaMasivaBean bean) {
        try {
            CmGlosaMasivaN cmGlosasMasivas ;
            List<CmDetalle> cmDetalles;
            
            if (validarFacturasNoUsadasPorOtroUsuarioGestiona(bean)) {              
                
                 cmGlosasMasivas = guardarCmRespuestaGlosaMasiva(bean);
                 
                for (Map.Entry<Integer, CmFactura> entry : bean.getHashFacturasSelecionadas().entrySet()) {
                    CmFactura cmFactura  = entry.getValue();
                    cmFactura.setTipoAuditoria(asignacionTipoAuditoria(bean.getObjeto().getTipoAuditoria(), cmFactura.getTipoAuditoria()));
                    CmGlosaRespuesta cmGlosaRespuseta = guardarCmGlosaRespuesta( bean, cmFactura, cmGlosasMasivas);
                    cmDetalles = cmFactura.getListaCmDetalles();
                    procesarCmGlosaDetalle(bean, cmGlosaRespuseta, cmFactura, cmDetalles);
                    actualizarCampoHistoriaProcesoCmFactura(bean, cmFactura, cmGlosaRespuseta.getTipoRespuesta());   
                    evaluarSiHayEnvioSap(cmGlosaRespuseta);
                    contarTipoFacturasGlosaMasiva(cmGlosasMasivas,cmGlosaRespuseta);
                }  
                actualizarCmRespuestaGlosaMasiva(cmGlosasMasivas);
                liberarCmFacturasUsadasGlosaMasiva(bean.getIdsFacturasProcesoMasivo());
                
                if ( hayEnvioParaSap() ) {
                    CmEnviosGlosasServicioImpl enviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
                    enviosGlosasServicioImpl.crearRadicadoCmGlosaMasiva(cmGlosasMasivas.getId());
                }
                bean.addMensaje(" Se creo la respuesta glosa masiva de manera exitosa con id " + cmGlosasMasivas.getId());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private int asignacionTipoAuditoria(int tipoAuditoriaPropuesta, int tipoAuditoriaCmFactura) {
       return tipoAuditoriaPropuesta > 0 ? tipoAuditoriaPropuesta : tipoAuditoriaCmFactura;
    }

    private boolean liberarCmFacturasUsadasGlosaMasiva(String idFactuaras) throws Exception {
        boolean hayLiberacion = true;
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(idFactuaras);
            paramConsulta.setParametroConsulta2(null);
            paramConsulta.setParametroConsulta3(null);
            paramConsulta.setParametroConsulta4(0);
            getCmFacturaGlosaRemoto().actualizarBloqueoFactura(paramConsulta);
        } catch (Exception e) {
            hayLiberacion = false;
        }
        return hayLiberacion;
    }

    private boolean procesarCmGlosaDetallesEstadoRatificacion(CmGlosaMasivaBean bean, CmGlosaRespuesta respuestaGlosa, List<CmDetalle> cmDetalles) {
        boolean hayProcesamiento = false;
        if (respuestaGlosa.esGlosaRespuestaValida() && respuestaGlosa.esTipoRespuestaRatificacion() ) {
            guardarCmGlosaRespuestaDetalleRatificada(bean, respuestaGlosa, cmDetalles);
            hayProcesamiento = true;
        }
        return hayProcesamiento;
    }

    private boolean procesarCmGlosaDetalle(CmGlosaMasivaBean bean, CmGlosaRespuesta respuestaGlosa,  CmFactura cmFactura,List<CmDetalle> cmDetalles ){
        boolean respuestaConValoresEpsIps = true;
        boolean hayGlosadoDetalles = false;
        BigDecimal valorPendienteTotalRespuestaDetalle = new BigDecimal("0");
        BigDecimal valorPagadoTotalRespuestaDetalle = new BigDecimal("0");
        BigDecimal valorFacturadoTotalRespuestaDetalle = new BigDecimal("0");
        BigDecimal valorPagadoEpsTotal = new BigDecimal("0");
        BigDecimal valorAceptadoIpsTotal = new BigDecimal("0");
        CmGlosaRespuestaDetalle respuestaDetalle = new CmGlosaRespuestaDetalle();

        try {

            if (respuestaGlosa.esGlosaRespuestaValida()) {

                for (CmDetalle detalle : cmDetalles) {

                    detalle.setValorPendienteActual(asignarValorPorDefectoBigDecimal(detalle.getValorPendienteActual()));
                    detalle.setValorAceptadoIPS(conversionPorcentajeEnValor(detalle.getPorcentajeAceptadoIPS(), detalle.getValorPendienteActual()));
                    detalle.setValorPagadoEPS(conversionPorcentajeEnValor(detalle.getPorcentajePagadoEPS(), detalle.getValorPendienteActual()));
                    String observacionGlosa = 
                                              asignarValorPorDefectoObservacion(detalle.getObservacionGlosa()) +
                                              " - " + asignarValorPorDefectoObservacion(detalle.getObservacionRespuestaDetalles());
                    detalle.setObservacionGlosa(observacionGlosa);
                    
                    valorPagadoEpsTotal = valorPagadoEpsTotal.
                                add(detalle.getValorPagadoEPS()).
                                setScale(4, RoundingMode.HALF_UP);
                    valorAceptadoIpsTotal = valorAceptadoIpsTotal.
                                add(detalle.getValorAceptadoIPS()).
                                setScale(4, RoundingMode.HALF_UP);

                    if (validarCondicionesGlosadoParaCmDetalle(detalle)) {
                        hayGlosadoDetalles = true;
                        respuestaDetalle.setDocumento(detalle.getDocumento());
                        respuestaDetalle.setCmGlosaRespuesta(new CmGlosaRespuesta(respuestaGlosa.getId()));
                        respuestaDetalle.setCmDetalle(new CmDetalle(detalle.getId()));
                        respuestaDetalle.setValorCobroDetalle(detalle.getValorFacturado());
                        respuestaDetalle.setValorFacturado(detalle.getValorFacturado());
                        respuestaDetalle.setObservacion(detalle.getObservacionGlosa());
                        respuestaDetalle.setValorPagadoEps(detalle.getValorPagadoEPS());
                        respuestaDetalle.setValorAceptadoIps(detalle.getValorAceptadoIPS());
                        respuestaDetalle.setPorcentajeAceptadoIps(detalle.getPorcentajeAceptadoIPS());
                        respuestaDetalle.setPorcentajePagadoEps(detalle.getPorcentajePagadoEPS());

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
                        borrarValoresPorcentajesEnCmDetalle(detalle.getId());
                        limpiarValoresPorcentajesEnCmDetalles(detalle);
                        //actualiza: valor pendiente actual detalle - valor pagado respuesta detalle
                        actualizarValorPendienteActualCmDetalles(detalle, respuestaDetalle, bean);
                    } else {
                        if (validarDetalleCandidatoRatificar(detalle)) {

                            valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                                    add(detalle.getValorFacturado()).
                                    setScale(4, RoundingMode.HALF_UP);
                            valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                                    add(detalle.getValorPendienteActual()).
                                    setScale(4, RoundingMode.HALF_UP);
                            insertarRespuestaDetalleRatificada(respuestaGlosa.getId(), detalle, bean);
                            //borra valores auditoria de detalle
                            borrarValoresPorcentajesEnCmDetalle(detalle.getId());
                        }
                    }
                }
                //actualiza : respuesta con acumulados de sumas de detalles
                respuestaGlosa.setValorCobroDetalle(valorFacturadoTotalRespuestaDetalle);
                respuestaGlosa.setValorFacturado(valorFacturadoTotalRespuestaDetalle);
                respuestaGlosa.setValorPagado(valorPagadoTotalRespuestaDetalle);
                respuestaGlosa.setValorPendiente(valorPendienteTotalRespuestaDetalle);
                respuestaGlosa.setEstadoSincronizacion(hayGlosadoDetalles ? CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA : CmGlosaRespuesta.ESTADO_SINCRONIZACION_RATIFICADA);
                respuestaGlosa.setTipoRespuesta(CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA);
                respuestaGlosa.setValorPagadoEps(valorPagadoEpsTotal);
                respuestaGlosa.setValorAceptadoIps(valorAceptadoIpsTotal);
                respuestaGlosa.setProcesadaSap(hayGlosadoDetalles);
                
                getCmGlosaRespuestaRemoto().actualizar(respuestaGlosa);

                //actualiza: valor pendente actual factura - valor pagado respuesta
                actualizarValorPendienteActualCmFactura(cmFactura, respuestaGlosa, bean);
            }
        } catch (Exception e) {
            respuestaConValoresEpsIps = false;
        }
        return respuestaConValoresEpsIps;
    }

    private static boolean validarCondicionesGlosadoParaCmDetalle(CmDetalle detalle) {
        return detalle.getValorPendienteActual().compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                && (detalle.getValorPagadoEPS().compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                || detalle.getValorAceptadoIPS().compareTo(new BigDecimal(BigInteger.ZERO)) > 0);
    }
    
    private CmGlosaMasivaN guardarCmRespuestaGlosaMasiva(CmGlosaMasivaBean bean){
        CmGlosaMasivaN cmGlosaMasiva = bean.getCmGlosaMasiva();
        try{
            cmGlosaMasiva.setEstadoProceso(CmGlosaMasivaN.ESTADO_INICIALIZADO);
            bean.auditoriaGuardar(cmGlosaMasiva);
            cmGlosaMasiva.setId(getCmGlosaMasivaRemoto().insertar(cmGlosaMasiva));
        }catch(Exception e){
            cmGlosaMasiva = new CmGlosaMasivaN();
        }    
        return cmGlosaMasiva;
    }
    
    private CmGlosaMasivaN actualizarCmRespuestaGlosaMasiva( CmGlosaMasivaN cmGlosaMasiva ) {
        try {
            getCmGlosaMasivaRemoto().actualizar(cmGlosaMasiva);
        } catch (Exception e) {
            cmGlosaMasiva = new CmGlosaMasivaN();
        }
        return cmGlosaMasiva;
    }
    
 
    private CmGlosaRespuesta guardarCmGlosaRespuesta( CmGlosaMasivaBean bean, CmFactura cmFactura, CmGlosaMasivaN cmGlosasMasiva ) {
        CmGlosaRespuesta respuestaGlosa = new CmGlosaRespuesta();
        try {
            if (cmGlosasMasiva.existeCmGlosaMasiva()) {
                respuestaGlosa.setCmFactura(new CmFactura(cmFactura.getId()));
                respuestaGlosa.setTipoRespuesta(CmGlosaRespuesta.TIPO_RESPUESTA_SIN_RESPUESTA);
                respuestaGlosa.setObservacion(asignarValorPorDefectoObservacion(bean.getCmGlosaMasiva().getObservacion()));
                respuestaGlosa.setEstadoSincronizacion(CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA);
                respuestaGlosa.setValorPendiente(new BigDecimal("0"));
                respuestaGlosa.setValorPagado(new BigDecimal("0"));
                respuestaGlosa.setValorCobroDetalle(new BigDecimal("0"));
                respuestaGlosa.setValorFacturado(new BigDecimal("0"));
                respuestaGlosa.setCmGlosaMasiva(new CmGlosaMasivaN(cmGlosasMasiva.getId()));
                respuestaGlosa.setValorPagadoEps(Optional.ofNullable(cmFactura.getValorPagadoEPS()).orElse(new BigDecimal("0")));
                respuestaGlosa.setValorAceptadoIps(Optional.ofNullable(cmFactura.getValorAceptadoIPS()).orElse(new BigDecimal("0")));
                bean.auditoriaGuardar(respuestaGlosa);
                if (respuestaGlosa.esTipoRespuestaRatificacion()) {
                    respuestaGlosa.setValorPendiente(Optional.ofNullable(cmFactura.getValorPendienteActual()).orElse(new BigDecimal("0")));
                    respuestaGlosa.setValorFacturado(Optional.ofNullable(cmFactura.getValorFactura()).orElse(new BigDecimal("0")));
                }
                int idRespuesta = getCmGlosaRespuestaRemoto().insertar(respuestaGlosa);
                respuestaGlosa.setId(idRespuesta);
            }
        } catch (Exception e) {
            respuestaGlosa = new CmGlosaRespuesta();
        }
        return respuestaGlosa;
    }

    private String asignarValorPorDefectoObservacion(String observacion) {
        String observacionRespuesta = Optional.ofNullable(observacion).orElse(" ");
        observacionRespuesta =  observacionRespuesta.equals("") ? " " : observacionRespuesta;
        return observacionRespuesta;
    }
    
    private BigDecimal asignarValorPorDefectoBigDecimal( BigDecimal valor) {
        return Optional.ofNullable(valor).orElse( new BigDecimal("0"));
    }
    
        
    public boolean guardarCmGlosaRespuestaDetalleRatificada(CmGlosaMasivaBean bean, CmGlosaRespuesta cmGlosaRespuesta, List<CmDetalle> cmDetalles) {
        boolean hayActualizacion = true;
        BigDecimal valorPendienteTotalRespuestaDetalle = new BigDecimal("0");
        BigDecimal valorPagadoTotalRespuestaDetalle = new BigDecimal("0");
        BigDecimal valorFacturadoTotalRespuestaDetalle = new BigDecimal("0");
        try {
            for (CmDetalle detalle : cmDetalles) {
                BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
                if (validarDetalleCandidatoRatificar(detalle)) {
                    valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                            add(detalle.getValorFacturado()).
                            setScale(4, RoundingMode.HALF_UP);
                    valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                            add(valorPendienteActualDetalle).
                            setScale(4, RoundingMode.HALF_UP);
                    insertarRespuestaDetalleRatificada(cmGlosaRespuesta.getId(), detalle, bean);
                    borrarValoresPorcentajesEnCmDetalle(detalle.getId());
                }
            }

            cmGlosaRespuesta.setEstadoSincronizacion(CmGlosaRespuesta.ESTADO_SINCRONIZACION_RATIFICADA);
            cmGlosaRespuesta.setValorCobroDetalle(valorFacturadoTotalRespuestaDetalle);
            cmGlosaRespuesta.setValorFacturado(valorFacturadoTotalRespuestaDetalle);
            cmGlosaRespuesta.setValorPagado(valorPagadoTotalRespuestaDetalle);
            cmGlosaRespuesta.setValorPendiente(valorPendienteTotalRespuestaDetalle);
            getCmGlosaRespuestaRemoto().actualizar(cmGlosaRespuesta);

        } catch (Exception e) {
            hayActualizacion = false;
        }

        return hayActualizacion;
    }
    
    private void actualizarValorPendienteActualCmFactura(CmFactura factura,
            CmGlosaRespuesta glosaRespuesta,
            CmGlosaMasivaBean bean) throws Exception {
        //MathContext mc  = new MathContext(4);
        BigDecimal valorPendienteActual =  asignarValorPorDefectoBigDecimal(factura.getValorPendienteActual());
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
     
    private void actualizarValorPendienteActualCmDetalles(CmDetalle detalle,
               CmGlosaRespuestaDetalle respuestaDetalle, CmGlosaMasivaBean bean ) throws Exception{
               
            //MathContext mc  = new MathContext(4);
            BigDecimal valorPendienteActual = asignarValorPorDefectoBigDecimal(detalle.getValorPendienteActual());
            BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuestaDetalle.getValorPagado())
                                             .setScale(4, RoundingMode.HALF_UP);
            
            if (nuevoPendientaActual.equals(new BigDecimal("-0.0001"))) {
                nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
            }
            detalle.setValorPendienteActual(nuevoPendientaActual);
            
            bean.auditoriaModificar(detalle);
            getCmDetalleRemoto().actualizar(detalle);
     }
    
    public void limpiarValoresPorcentajesEnCmDetalles(CmDetalle detalle){
        detalle.setValorPagadoEPS(null);
        detalle.setValorAceptadoIPS(BigDecimal.ZERO);
        detalle.setPorcentajeAceptadoIPS(null);
        detalle.setPorcentajePagadoEPS(null);
        detalle.setObservacionRespuestaDetalles("");
    }

    private void calcularPorcentajesRespuestaDetalle(CmDetalle detalle, CmGlosaRespuestaDetalle respuestaDetalle){

      
        BigDecimal valorPendientePagoDetalle =  asignarValorPorDefectoBigDecimal(detalle.getValorPendienteActual());

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
    public void cargaInial(CmGlosaMasivaBean bean) {
        try {
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
     
    
    private void guardarEstadoAuditoria(CmGlosaMasivaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta2(bean.getObjeto().getTipoAuditoria());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getIdsFacturasProcesoMasivo());
            getCmFacturaGlosaRemoto().actualizarTipoAuditoria(bean.getParamConsultaUtilitario());   
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarValoresAuditoria(CmGlosaMasivaBean bean){
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
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarReintentoSincronizacion(CmGlosaMasivaBean bean) {
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

    public void borrarValoresPorcentajesEnCmDetalle(int idDetalle) {
        try {
            if (idDetalle > 0) {
                ParamConsulta parametroConsulta = new ParamConsulta();
                parametroConsulta.setParametroConsulta1(idDetalle);
                getCmFacturaGlosaRemoto().borrarValoresAuditoria(parametroConsulta);
            }
        } catch (Exception e) {
             Logger.getLogger(CmGlosaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void descargarRespuestaGlosa(CmGlosaMasivaBean bean) {
        List<ReporteRespuestaGlosa> listaReportes = new ArrayList();
        try {
            int idGlosaRespuesta = bean.getReporteRespuestaGlosa().getId();
            if( idGlosaRespuesta>0 ){
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

    private void marcarFacturas(CmGlosaMasivaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getIdsFacturasProcesoMasivo());
            bean.getParamConsultaUtilitario().setParametroConsulta2(new Date());
            Calendar fechaVencimiento = Calendar.getInstance();
            fechaVencimiento.add(Calendar.DAY_OF_MONTH, DIAS_ADICIONALES_BLOQUEO);
            bean.getParamConsultaUtilitario().setParametroConsulta3(fechaVencimiento.getTime());
            bean.getParamConsultaUtilitario().setParametroConsulta4(CmFactura.TIPO_BLOQUEO_GLOSA);
            getCmFacturaGlosaRemoto().actualizarBloqueoFactura(bean.getParamConsultaUtilitario());
            bean.addMensaje( "Se ha realizado bloqueo de las facturas seleccionadas.");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void desMarcarFacturas(CmGlosaMasivaBean bean){
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getIdsFacturasProcesoMasivo());
            bean.getParamConsultaUtilitario().setParametroConsulta2(null);
            bean.getParamConsultaUtilitario().setParametroConsulta3(null);
            bean.getParamConsultaUtilitario().setParametroConsulta4(0);
            getCmFacturaGlosaRemoto().actualizarBloqueoFactura(bean.getParamConsultaUtilitario());
            bean.addMensaje("Se ha realizado desbloqueo de las facturas seleccionadas." );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private boolean validarFacturasNoUsadasPorOtroUsuarioGestiona(CmGlosaMasivaBean bean){
        boolean facturaPermitida = true;
        try {
            consultarFacturasGestionadasPorOtrosUsuarios(bean);
             if (bean.getFacturasUsadasPorOtrosUsurios() != null && bean.getFacturasUsadasPorOtrosUsurios().length() > 0) {
                    bean.addError("Las siguentes facturas estan bloquedas por favor contacte administrador, "
                            + bean.getFacturasUsadasPorOtrosUsurios());
                    facturaPermitida = false;
            }
            
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
        return facturaPermitida;
    }
     
    private void actualizarCampoHistoriaProcesoCmFactura(CmGlosaMasivaBean bean,  CmFactura factura, int tipoRespuesta){
       try{
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
           String fecha = sdf.format(new Date());
           ParamConsulta parametroActualizacion = new ParamConsulta();
           String usuarioGestiona = bean.getConexion().getUsuario().getUsuario() +
                    " (" + bean.getConexion().getUsuario().getNombre() + ")";
           String historial = Optional.ofNullable( factura.getHistorialProceso()).orElse("");       
           String postFijoMensaje = "";
           switch (tipoRespuesta) {
               case CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION:
               case CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA:
               case CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION:
                   CmGlosaRespuesta respuesta = new CmGlosaRespuesta();
                   respuesta.setTipoRespuesta(tipoRespuesta);
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
            //Logger.getLogger(CmFacturaGlosaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insertarRespuestaDetalleRatificada( int idGlosaRespuesta, CmDetalle detalle, CmGlosaMasivaBean bean) {
       
        CmGlosaRespuestaDetalle respuestaDetalle = new CmGlosaRespuestaDetalle();
        BigDecimal valorDefectoCero = new BigDecimal(BigInteger.ZERO);

        try {
                respuestaDetalle.setCmGlosaRespuesta(new CmGlosaRespuesta(idGlosaRespuesta));
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
    
    private void consultarFacturasGestionadasPorOtrosUsuarios(CmGlosaMasivaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            int idUsuarioSolicita = bean.getConexion().getUsuario().getId();
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getIdsFacturasProcesoMasivo());
            bean.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioSolicita);
            bean.setFacturasUsadasPorOtrosUsurios(getCmFacturaRemoto().consultarFacturasBloquedas(bean.getParamConsultaUtilitario())); 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public BigDecimal conversionPorcentajeEnValor( BigDecimal porcentaje,  BigDecimal valorPendiente) {

        BigDecimal valorFinal = new BigDecimal("0");       
        porcentaje = Optional.ofNullable(porcentaje).orElse(new BigDecimal(BigInteger.ZERO));
        valorPendiente = Optional.ofNullable(valorPendiente).orElse(new BigDecimal(BigInteger.ZERO));
        
        if(porcentaje.equals(new BigDecimal(BigInteger.ZERO))){
            return valorFinal;
        }
        
        if(!valorPendiente.equals(new BigDecimal(BigInteger.ZERO))){  
            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            valorFinal = promedio.multiply(valorPendiente).setScale(4, RoundingMode.HALF_UP);
            return valorFinal;
        }
        return valorFinal; 
    }
    

}
