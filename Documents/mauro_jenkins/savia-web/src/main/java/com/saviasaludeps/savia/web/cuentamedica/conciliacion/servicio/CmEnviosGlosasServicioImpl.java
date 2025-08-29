/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaCierreRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDevolucionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.NotificacionSAPCuentasMedicasRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmConciliacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmEnviosGlosaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmEnviosGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionPaqueteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionRemoto;
import com.saviasaludeps.savia.negocio.sincronizasap.EnvioTransaccionesSapRemoto;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.servicio.NotificacionFacturaServicio;
import com.saviasaludeps.savia.web.utilidades.Conexion;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJBSincronizaSap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CmEnviosGlosasServicioImpl extends AccionesBO implements CmEnviosGlosasServicioIface {

    public static final int TIPO_CONCILIACION_INDIVIDUAL = 1;
    public static final int TIPO_CONCILIACION_MASIVA = 2;
    public static final int TIPO_RESPUESTA_GLOSA = 3;
    public static final int TIPO_CAUSACION_M2 = 4;
    public static final int TIPO_DEVOLUCION = 5;
    public static final int TIPO_RESPUESTA_GLOSA_MASIVA = 6;
    private Conexion conexion = null;

    public static final int TIPO_ACTUALIZACION_CONCILIACION_FINAL = 1;
    public static final int TIPO_ACTUALIZACION_CONCILIACION_PARCIAL = 2;
    public static final int TIPO_ACTUALIZACION_AUDITORIA_CIERRE_FINAL = 1;
    public static final int TIPO_ACTUALIZACION_AUDITORIA_CIERRE_PARCIAL = 2;
    public static final int CANTIDAD_FACURAS_ACTUALIZAR = 50;
    public static final int CANTIDAD_SINCRONIZACIONES_ACTIVAS = 10000;
    public static final String REINTENTO_CANTIDAD_MAXIMA = "10";
    
    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    private CmEnviosGlosaRemoto getCmReintentoGlosaRemoto() throws Exception {
        return (CmEnviosGlosaRemoto) RemotoEJB.getEJBRemoto(("CmEnviosGlosasServicio"), CmEnviosGlosaRemoto.class.getName());
    }

    private CmRadicadoRemoto getCmRadicadoServicioRemoto() throws Exception {
        return (CmRadicadoRemoto) RemotoEJB.getEJBRemoto(("CmRadicadoServicio"), CmRadicadoRemoto.class.getName());
    }

    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }

    private CmSincronizacionDetalleRemoto getCmSincronizacionDetalleRemoto() throws Exception {
        return (CmSincronizacionDetalleRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionDetalleServicio", CmSincronizacionDetalleRemoto.class.getName());
    }

    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }

    private CmConciliacionRemoto getCmConciliacionRemoto() throws Exception {
        return (CmConciliacionRemoto) RemotoEJB.getEJBRemoto("CmConciliacionServicio", CmConciliacionRemoto.class.getName());
    }

    private NotificacionFacturaServicio getNotificacionFacturaServicio() {
        return new NotificacionFacturaServicio();
    }

    private CmSincronizacionRemoto getCmSincronizacionRemoto() throws Exception {
        return (CmSincronizacionRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionServicio", CmSincronizacionRemoto.class.getName());
    }

    private CmSincronizacionPaqueteRemoto getCmSincronizacionPaqueteRemoto() throws Exception {
        return (CmSincronizacionPaqueteRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionPaqueteServicio", CmSincronizacionPaqueteRemoto.class.getName());
    }

    private CmAuditoriaDevolucionRemoto getCmAuditoriaDevolucionRemoto() throws Exception {
        return (CmAuditoriaDevolucionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDevolucionServicio", CmAuditoriaDevolucionRemoto.class.getName());
    }

    private CmAuditoriaCierreRemoto getCmAuditoriaCierreRemoto() throws Exception {
        return (CmAuditoriaCierreRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaCierreServicio", CmAuditoriaCierreRemoto.class.getName());
    }

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }

    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
    
    private NotificacionSAPCuentasMedicasRemoto getNotificacionSAPCuentasMedicasRemoto() throws Exception {
        return (NotificacionSAPCuentasMedicasRemoto) RemotoEJB.getEJBRemotoGenerico("NotificacionSapCuentasMedicasServicio", NotificacionSAPCuentasMedicasRemoto.class.getName());
    }
    
    private EnvioTransaccionesSapRemoto getEnvioTransaccionesSapRemoto() throws Exception {
        return (EnvioTransaccionesSapRemoto) RemotoEJBSincronizaSap.getEJBRemotoSincronizaSap("EnvioTransaccionesSapServicio", EnvioTransaccionesSapRemoto.class.getName());
    }
    
    private WsCmTransaccionRemoto getWsCmTransaccionRemoto() throws Exception {
        return (WsCmTransaccionRemoto) RemotoEJB.getEJBRemoto("WsCmTransaccionServicio", WsCmTransaccionRemoto.class.getName());
    }
    
    private WsCmTransaccionDetalleRemoto getWsCmTransaccionDetalleRemoto() throws Exception {
        return (WsCmTransaccionDetalleRemoto) RemotoEJB.getEJBRemoto("WsCmTransaccionDetalleServicio", WsCmTransaccionDetalleRemoto.class.getName());
    }
    
    private WsCmFacturaRemoto getWsCmFacturaRemoto() throws Exception {
        return (WsCmFacturaRemoto) RemotoEJB.getEJBRemoto("WsCmFacturaServicio", WsCmFacturaRemoto.class.getName());
    }
    
    private WsCmFacturaDetalleRemoto getWsCmFacturaDetalleRemoto() throws Exception {
        return (WsCmFacturaDetalleRemoto) RemotoEJB.getEJBRemoto("WsCmFacturaDetalleServicio", WsCmFacturaDetalleRemoto.class.getName());
    }

    @Override
    public void Accion(CmEnviosGlosaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmEnviosGlosaBean.ACCION_VER_ENCABEZADOS_FACTURAS:
                            ver(bean);
                            verResumenEncabezados(bean);
                            break;
                        case CmEnviosGlosaBean.ACCION_VER_TRANSACCIONES:
                            verWsTransacciones(bean);
                            break;
                        case CmEnviosGlosaBean.ACCION_VER_TRANSACCIONES_DETALLES:
                            verWsTransaccionesDetalles(bean);
                            break;
                        case CmEnviosGlosaBean.ACCION_VER_CM_FACTURAS:
                            verWsCmFacturas(bean);
                            break;                           
                        case CmEnviosGlosaBean.ACCION_VER_CM_FACTURAS_DETALLES:
                            verWsCmFacturaDetalle(bean);
                            break;
                         case CmEnviosGlosaBean.ACCION_VER_CM_RADICADO:
                            verCmRadicado(bean);
                            break;
                    }
               break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    reintentar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case CmEnviosGlosaBean.ACCION_BUSCAR_REINTENTOS:
                            verReintentos(bean);
                            break;
                        case CmEnviosGlosaBean.ACCION_BUSCAR_PAQUETES_REINTENTO:
                            verPaquetesJson(bean);
                            break;
                        case CmEnviosGlosaBean.ACCION_BUSCAR_RADICADO_PENDIENTE_POR_FACTURA:
                            buscarRadicadoPendientePorFactura(bean);
                            break;
                    }
                    break;

                case Url.ACCION_ADICIONAL_3:
                    guardarIncrementoMaximoIntentoPermitido(bean);
                    break;

            }
        }
    }

    private void listar(CmEnviosGlosaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(
                    getCmReintentoGlosaRemoto().consultarCantidadRadicadosActivos(bean.getParamConsulta()));
            bean.setRegistros(getCmReintentoGlosaRemoto()
                    .consultarListaRadicadosActivos(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void buscarRadicadoPendientePorFactura(CmEnviosGlosaBean bean) {
        try {
            bean.setObjeto( getCmRadicadoServicioRemoto().consultarRadicadoPendientePorFactura(bean.getParamConsultaUtilitario()) );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(CmEnviosGlosaBean bean) {
        try {
            bean.setListaDetalles(getCmSincronizacionEncabezadoRemoto().consultarTodos(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    private void verResumenEncabezados(CmEnviosGlosaBean bean) {
        try {
            bean.setListaCmSincronizaconEncabezadoResumen(getCmSincronizacionEncabezadoRemoto().consultarResumenEncabezado(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verWsTransacciones(CmEnviosGlosaBean bean) {
        try {      
            bean.getParamConsulta(0).setCantidadRegistros(getWsCmTransaccionRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosWsTransacciones(getWsCmTransaccionRemoto().consultarLista(bean.getParamConsulta(0)));             
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verWsTransaccionesDetalles(CmEnviosGlosaBean bean) {
        try {
            bean.getParamConsulta(1).setCantidadRegistros(getWsCmTransaccionDetalleRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosWsTransaccionDetalles(getWsCmTransaccionDetalleRemoto().consultarLista(bean.getParamConsulta(1)));       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verWsCmFacturas(CmEnviosGlosaBean bean) {
        try {
            bean.getParamConsulta(2).setCantidadRegistros(getWsCmFacturaRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.setRegistrosWsCmFacturas(getWsCmFacturaRemoto().consultarLista(bean.getParamConsulta(2)));       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verWsCmFacturaDetalle(CmEnviosGlosaBean bean) {
        try {
            bean.getParamConsulta(3).setCantidadRegistros(getWsCmFacturaDetalleRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setRegistrosWsCmFacturaDetalles(getWsCmFacturaDetalleRemoto().consultarLista(bean.getParamConsulta(3)));       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verCmRadicado(CmEnviosGlosaBean bean) {
        try {
          bean.setCmRadicado(getCmRadicadoServicioRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(CmEnviosGlosaBean bean) {
        try {
            CmSincronizacion cmSincronizacion = new CmSincronizacion();
            cmSincronizacion.setCmGlosaRespuesta(new CmGlosaRespuesta(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void guardarIncrementoMaximoIntentoPermitido(CmEnviosGlosaBean bean) {
        try {
           CmRadicado radicado =   getCmRadicadoServicioRemoto().consultar(bean.getObjeto().getId());
           if(radicado != null){
              short intentos =  (short) (radicado.getIntentosPermitidos() + 1);
              getCmRadicadoServicioRemoto().actualizarIntentosPermitidos(intentos, bean.getObjeto().getId());
              bean.addMensaje("El máximo de reintentos permitidos ha incrementado a : "+intentos+" para radicado de id : "+bean.getObjeto().getId());
           }                              
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInial(CmEnviosGlosaBean bean) {
        try {
            listar(bean);

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void crearRadicadoXConciliaciones(int cm_conciliaciones_id) {
        try {
            //List<CmGlosaRespuesta> listaGlosaRespuesta = getCmGlosaRespuestaRemoto().consultarXCmConciliacionesId(cm_conciliaciones_id);
            List<CmGlosaRespuesta> listaGlosaRespuesta = getCmGlosaRespuestaRemoto().consultarXCmConciliacionesIdPorBloque(cm_conciliaciones_id);
            procesoRadicado(listaGlosaRespuesta, TIPO_CONCILIACION_MASIVA, cm_conciliaciones_id);

        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearRadicadoXFactura(int cm_facturas_id, int tipoRespuesta, int cm_glosa_respuestas_id) {
        try {
            List<CmGlosaRespuesta> listaGlosaRespuesta = getCmGlosaRespuestaRemoto().consultarXCmFacturasId(cm_facturas_id, tipoRespuesta);
            procesoRadicado(listaGlosaRespuesta, TIPO_CONCILIACION_INDIVIDUAL, cm_glosa_respuestas_id);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearRadicadoXCmGlosaRta(int cm_glosa_respuestas_id) {
        try {
            List<CmGlosaRespuesta> listaGlosaRespuesta = getCmGlosaRespuestaRemoto().consultarXCmGlosaRta(cm_glosa_respuestas_id);
            procesoRadicado(listaGlosaRespuesta, TIPO_RESPUESTA_GLOSA, cm_glosa_respuestas_id);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void crearRadicadoCmGlosaMasiva(int idGlosaMasiva) {
        try {
            procesoRadicadoGlosaMasiva(idGlosaMasiva);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearRadicadoXDevolucionFactuar(int idDevolucion) {
        try {
            CmAuditoriaDevolucion devolucion = getCmAuditoriaDevolucionRemoto().consultar(idDevolucion);
            procesoRadicadoDevolucion(devolucion);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void crearRadicadoXCierreAuditoria(int idCierreAuditoria) {
        try {
            CmAuditoriaCierre cierre = getCmAuditoriaCierreRemoto().consultar(idCierreAuditoria);
            procesoRadicadoCierreAuditoria(cierre);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void procesoRadicadoDevolucion(CmAuditoriaDevolucion devolucion) throws Exception {
        if (devolucion != null && devolucion.getId() != null) {
            CmRadicado cmRadicado = new CmRadicado();
            cmRadicado.setEstado_radicado(false);
            cmRadicado.setRadicado(0);
            cmRadicado.setCmAuditoriaDevolucion(devolucion);
            setVariablesAuditoria(cmRadicado);
            asignarCamposReintentosCmRadicado(cmRadicado, TIPO_DEVOLUCION);
            cmRadicado.setEstado(CmRadicado.ESTADO_CREADO);
            cmRadicado.setTipoRelacion(CmRadicado.TIPO_RELACION_DEVOLUCION_INDIVIDUAL);
            int idCmRadicado = getCmRadicadoServicioRemoto().insertar(cmRadicado);

            /**
            //DOCUMENTACION-AN: antigua forma de reistro encabezados
            CmSincronizacionEncabezado cmSincronizacionEncabezado = new CmSincronizacionEncabezado();
            cmSincronizacionEncabezado.setCmRadicadosId(new CmRadicado(idCmRadicado));
            cmSincronizacionEncabezado.setEstado(CmSincronizacionEncabezado.ESTADO_PROCESO);
            cmSincronizacionEncabezado.setProveedorNit(devolucion.getCmFactura().getNit());
            cmSincronizacionEncabezado.setNumeroDocumento("" + devolucion.getCmFactura().getNumeroFacturado());
            cmSincronizacionEncabezado.setNumeroRadicado("" + devolucion.getCmFactura().getNumeroRadicado());
            String regimenDesc = devolucion.getCmFactura().getMaeRegimenValor() != null
                    ? devolucion.getCmFactura().getMaeRegimenValor() : "";
            cmSincronizacionEncabezado.setRegimen(regimenDesc);
            cmSincronizacionEncabezado.setFactura(devolucion.getCmFactura().getId());
            cmSincronizacionEncabezado.setFechaHoraDocumento(devolucion.getCmFactura().getFechaRadicacion());
            cmSincronizacionEncabezado.setFechaHoraProceso(devolucion.getCmFactura().getFechaPrestacion());
            cmSincronizacionEncabezado.setValorDocumento(devolucion.getCmFactura().getValorFactura());
            cmSincronizacionEncabezado.setValorCopago(devolucion.getCmFactura().getValorCopago());
            setVariablesAuditoria(cmSincronizacionEncabezado);
            getCmSincronizacionEncabezadoRemoto().insertar(cmSincronizacionEncabezado);

            List<SincronizacionNotificacionFactura> listSincornizacionNotificacion = new ArrayList<>();
            SincronizacionNotificacionFactura sincronizacionNotificacion = new SincronizacionNotificacionFactura();
            sincronizacionNotificacion.setRadicadoId(idCmRadicado);
            listSincornizacionNotificacion.add(sincronizacionNotificacion);

            if (devolucion.getCmFactura() != null
                    && devolucion.getCmFactura().getId() != null) {
                ParamConsulta paramConsulta = new ParamConsulta();
                paramConsulta.setParametroConsulta1(devolucion.getCmFactura().getId());
                paramConsulta.setParametroConsulta3(CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION);
                getCmFacturaRemoto().actualizarEstadoAuditoria(paramConsulta);
                guardarHistorialEstadoFactura(devolucion.getCmFactura().getId(), CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION);
            }

            //getEnvioTransaccionesSapRemoto().enviarTransaccionSap(idCmRadicado);
            //TODO: USOSERVICIOENVIOSAP
            //getNotificacionFacturaServicio().consumoServicioDevolucionFactua(listSincornizacionNotificacion);
            */
        }
    }

    private void procesoRadicado(List<CmGlosaRespuesta> listaGlosaRespuesta, int tipoOperacion, int valorOperacion) throws Exception {
        if (!listaGlosaRespuesta.isEmpty()) {
            CmRadicado cmRadicado = new CmRadicado();
            CmSincronizacionEncabezado cmSincronizacionEncabezado;
            CmSincronizacionDetalle cmSincronizacionDetalle;
            List<CmSincronizacionDetalle> listSincronizacionDetalle;
            cmRadicado.setEstado(CmRadicado.ESTADO_CREADO);
            if (TIPO_CONCILIACION_MASIVA == tipoOperacion) {
                cmRadicado.setCmConciliacion(new CmConciliacion(valorOperacion));
                cmRadicado.setTipoRelacion(CmRadicado.TIPO_RELACION_CONCILIACION_MASIVA);
            }
            if (TIPO_CONCILIACION_INDIVIDUAL == tipoOperacion) {
                cmRadicado.setCmGlosaRespuestasConciliacion(new CmGlosaRespuesta(valorOperacion));
                cmRadicado.setTipoRelacion(CmRadicado.TIPO_RELACION_CONCILIACION_INDIVIDUAL );
            }
            if (TIPO_RESPUESTA_GLOSA == tipoOperacion) {
                cmRadicado.setCmGlosaRespuesta(new CmGlosaRespuesta(valorOperacion));
                cmRadicado.setTipoRelacion(CmRadicado.TIPO_RELACION_REPUESTA_GLOSA);
            }
            
            asignarCamposReintentosCmRadicado(cmRadicado, tipoOperacion);
           
            cmRadicado.setEstado_radicado(false);
            cmRadicado.setRadicado(0); //@Alert Verificar como generar el consecutivo que se guarda.
            setVariablesAuditoria(cmRadicado);
            CmGlosaRespuesta glosaRespuesta = listaGlosaRespuesta.get(0);
            
            //si el sistema tiene una falla trata de recuperar  el radicado y apartir de aqui crear el proceso
            int idCmRadicado ;
            HashMap<Object, Object> sincronizacionEnProcesada = new HashMap<>();
            if (glosaRespuesta.getCmRadicado() != null && glosaRespuesta.getCmRadicado().getId() != null) {
                idCmRadicado = glosaRespuesta.getCmRadicado().getId();
                ParamConsulta paramConsulta  = new ParamConsulta();
                paramConsulta.setParametroConsulta1(idCmRadicado);
                paramConsulta.setRegistrosPagina(CANTIDAD_SINCRONIZACIONES_ACTIVAS);
                List<CmSincronizacionEncabezado> encabezadosEncontrados =  getCmSincronizacionEncabezadoRemoto().consultarLista(paramConsulta);
                for (CmSincronizacionEncabezado encabezadoEncontrado: encabezadosEncontrados) {
                    sincronizacionEnProcesada.put(encabezadoEncontrado.getFactura(), encabezadoEncontrado.getFactura());
                }
            
            } else {
                idCmRadicado = getCmRadicadoServicioRemoto().insertar(cmRadicado);
            }
            
            /*
            //DOCUMENTACION-AN: antigua forma de reistro encabezados
            BigDecimal valor_pagado;
            BigDecimal valor_operacion;
            BigDecimal valor_documento;

            if (idCmRadicado != 0) {
                int cantidadFacturas = 0;
                int banderaCambioNumero = 0;
                for (CmGlosaRespuesta cmGlosaRespuesta : listaGlosaRespuesta) {
                    
                    if (sincronizacionEnProcesada.get(cmGlosaRespuesta.getCmFactura().getId()) == null) {
                        cmSincronizacionEncabezado = new CmSincronizacionEncabezado();
                        cmSincronizacionEncabezado.setCmRadicadosId(new CmRadicado(idCmRadicado));
                        listSincronizacionDetalle = new ArrayList<>();
                        cmSincronizacionEncabezado.setEstado(CmSincronizacionEncabezado.ESTADO_PROCESO);
                        cmSincronizacionEncabezado.setProveedorNit(cmGlosaRespuesta.getCmFactura().getNit());
                        cmSincronizacionEncabezado.setNumeroDocumento("" + cmGlosaRespuesta.getCmFactura().getNumeroFacturado());
                        cmSincronizacionEncabezado.setNumeroRadicado("" + cmGlosaRespuesta.getCmFactura().getNumeroRadicado());
                        String regimenDesc = cmGlosaRespuesta.getCmFactura().getMaeRegimenValor() != null
                                ? cmGlosaRespuesta.getCmFactura().getMaeRegimenValor() : "";
                        cmSincronizacionEncabezado.setRegimen(regimenDesc);
                        cmSincronizacionEncabezado.setValorCopago(cmGlosaRespuesta.getCmFactura().getValorCopago());
                        cmSincronizacionEncabezado.setFactura(cmGlosaRespuesta.getCmFactura().getId());
                        cmSincronizacionEncabezado.setFechaHoraDocumento(cmGlosaRespuesta.getCmFactura().getFechaRadicacion());
                        cmSincronizacionEncabezado.setFechaHoraProceso(cmGlosaRespuesta.getCmFactura().getFechaPrestacion());
                        valor_documento = cmGlosaRespuesta.getCmFactura().getValorFactura();
                        setVariablesAuditoria(cmSincronizacionEncabezado);
                        valor_pagado = BigDecimal.ZERO;
                        int estadoSincronizacion = CmSincronizacionEncabezado.ESTADO_SIN_VALORES_EPS;
                        for (CmGlosaRespuestaDetalle cmGlosaRespuestaDetalle : cmGlosaRespuesta.getListaGlosaRespuestaDetalle()) {
                            // si existe valor de detalle en eps se agrega a la sincronizacion.
                            if (cmGlosaRespuestaDetalle.getValorPagadoEps() != null
                                    && cmGlosaRespuestaDetalle.getValorPagadoEps().compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                                estadoSincronizacion = CmSincronizacionEncabezado.ESTADO_SIN_CONCEPTOS_CANTABLES;
                                for (CmAuditoriaConceptoContable cmConceptoContable : cmGlosaRespuestaDetalle.getCmDetalle().getListaCmAuditoriaConceptoContable()) {
                                    cmSincronizacionDetalle = new CmSincronizacionDetalle();
                                    valor_operacion = BigDecimal.ZERO;
                                    cmSincronizacionDetalle.setConsecutivo("0");
                                    cmSincronizacionDetalle.setCodigoMunicipio(cmConceptoContable.getCodigoMunicipio());
                                    cmSincronizacionDetalle.setConceptoContable("" + cmConceptoContable.getMaeConceptosCodigo());
                                    valor_operacion = cmGlosaRespuestaDetalle.getValorPagadoEps().multiply(cmConceptoContable.getPorcentaje()).divide(BigDecimal.valueOf(100D));
                                    valor_operacion = valor_operacion.setScale(2, RoundingMode.DOWN);
                                    valor_pagado = valor_pagado.add(valor_operacion).setScale(2, RoundingMode.DOWN);
                                    cmSincronizacionDetalle.setTipologia(CmSincronizacionDetalle.TIPOLOGIA_CREDITO);
                                    cmSincronizacionDetalle.setValorOperacion(valor_operacion);
                                    Integer idDetalle = cmGlosaRespuestaDetalle.getCmDetalle() != null
                                            && cmGlosaRespuestaDetalle.getCmDetalle().getId() != null
                                            ? cmGlosaRespuestaDetalle.getCmDetalle().getId() : null;
                                    cmSincronizacionDetalle.setIdDetalles(idDetalle);
                                    setVariablesAuditoria(cmSincronizacionDetalle);
                                    listSincronizacionDetalle.add(cmSincronizacionDetalle);
                                }
                            }
                        }

                        cmSincronizacionEncabezado.setValorPagado(valor_pagado);
                        cmSincronizacionEncabezado.setValorDocumento(valor_documento);
                        //el encabezado no tiene detalles a enviar
                        if (listSincronizacionDetalle.isEmpty()) {
                            cmSincronizacionEncabezado.setEstado(estadoSincronizacion);
                        }

                        int idSincronizacionEncabezado = getCmSincronizacionEncabezadoRemoto().insertar(cmSincronizacionEncabezado);

                        for (CmSincronizacionDetalle cmSincronizacionDetalle1 : listSincronizacionDetalle) {
                            cmSincronizacionDetalle1.setCmSincronizacionEncabezadosId(new CmSincronizacionEncabezado(idSincronizacionEncabezado));
                            getCmSincronizacionDetalleRemoto().insertar(cmSincronizacionDetalle1);
                        }

                        cantidadFacturas++;
                        banderaCambioNumero++;
                        if (TIPO_CONCILIACION_MASIVA == tipoOperacion && CANTIDAD_FACURAS_ACTUALIZAR == banderaCambioNumero) {
                            actualizarProcesoConciliacion(valorOperacion, cantidadFacturas, TIPO_ACTUALIZACION_CONCILIACION_PARCIAL);
                            banderaCambioNumero = 0;
                        }

                        if (cmGlosaRespuesta.getCmFactura() != null
                                && cmGlosaRespuesta.getCmFactura().getId() != null
                                && cmGlosaRespuesta.getTipoRespuesta() != CmGlosaRespuesta.TIPO_RESPUESTA_RATIFICACION
                                && !listSincronizacionDetalle.isEmpty()) {
                            ParamConsulta paramConsulta = new ParamConsulta();
                            paramConsulta.setParametroConsulta1(cmGlosaRespuesta.getCmFactura().getId());
                            paramConsulta.setParametroConsulta3(CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION);
                            getCmFacturaRemoto().actualizarEstadoAuditoria(paramConsulta);
                            guardarHistorialEstadoFactura(cmGlosaRespuesta.getCmFactura().getId(), CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_RESPUESTA_CONCILIACION);
                            guardarEstadoSincronizacionCmRespuestaGlosa( cmGlosaRespuesta, CmGlosaRespuesta.ESTADO_SINCRONIZACION_EN_RADICACION_ENCABEZADO );
                        }
                    }
                }

                List<SincronizacionNotificacionFactura> listSincornizacionNotificacion = new ArrayList<>();
                SincronizacionNotificacionFactura sincronizacionNotificacion = new SincronizacionNotificacionFactura();
                sincronizacionNotificacion.setRadicadoId(idCmRadicado);

                if (TIPO_CONCILIACION_INDIVIDUAL == tipoOperacion || TIPO_RESPUESTA_GLOSA == tipoOperacion) {
                    sincronizacionNotificacion.setGlosaRespuestaId(valorOperacion);
                }
                if (TIPO_CONCILIACION_MASIVA == tipoOperacion) {
                    sincronizacionNotificacion.setConciliacionId(valorOperacion);
                    sincronizacionNotificacion.setTipoTransaccion(SincronizacionNotificacionFactura.TIPO_TRANSACCION_M4_CONCILIACION);
                    actualizarProcesoConciliacion(valorOperacion, cantidadFacturas, TIPO_ACTUALIZACION_CONCILIACION_FINAL);
                }
            
                if (TIPO_RESPUESTA_GLOSA == tipoOperacion) {
                    sincronizacionNotificacion.setTipoTransaccion(SincronizacionNotificacionFactura.TIPO_TRANSACCION_M3_RESPUESTA_GLOSA);
                }

                if (TIPO_CONCILIACION_INDIVIDUAL == tipoOperacion) {
                    sincronizacionNotificacion.setTipoTransaccion(SincronizacionNotificacionFactura.TIPO_TRANSACCION_M4_CONCILIACION);
                }

                listSincornizacionNotificacion.add(sincronizacionNotificacion);
                //TODO: USOSERVICIOENVIOSAP
                //getNotificacionFacturaServicio().consumoServicioNotificacionFactura(listSincornizacionNotificacion);
                // getEnvioTransaccionesSapRemoto().enviarTransaccionSap(idCmRadicado);
            }
            */
        }
    }

    private void procesoRadicadoCierreAuditoria(CmAuditoriaCierre cierreAuditoria) throws Exception {

        CmRadicado cmRadicado = new CmRadicado();
        cmRadicado.setCmAuditoriaCierre(new CmAuditoriaCierre(cierreAuditoria.getId()));
        cmRadicado.setEstado_radicado(false);
        cmRadicado.setRadicado(0); 
        setVariablesAuditoria(cmRadicado);
        asignarCamposReintentosCmRadicado(cmRadicado, TIPO_CAUSACION_M2);
        cmRadicado.setEstado(CmRadicado.ESTADO_CREADO);
        cmRadicado.setTipoRelacion(CmRadicado.TIPO_RELACION_CIERRE_INDIVIDUAL);
        cmRadicado.setCmRipsCarga(cierreAuditoria.getCmFactura().getCmRipCarga());
        cmRadicado.setCmFeRipsCarga(cierreAuditoria.getCmFactura().getCmFeRipsCarga());
        getCmRadicadoServicioRemoto().insertar(cmRadicado);
    }
    
    private void procesoRadicadoGlosaMasiva(int idGlosaMasiva) throws Exception {
        CmRadicado cmRadicado = new CmRadicado();
        cmRadicado.setCmGlosaMasiva(new CmGlosaMasivaN(idGlosaMasiva));
        cmRadicado.setEstado_radicado(false);
        cmRadicado.setRadicado(0); 
        setVariablesAuditoria(cmRadicado);
        asignarCamposReintentosCmRadicado(cmRadicado, TIPO_RESPUESTA_GLOSA_MASIVA);
        cmRadicado.setEstado(CmRadicado.ESTADO_CREADO);
        cmRadicado.setTipoRelacion(CmRadicado.TIPO_RELACION_RESPUESTA_GLOSA_MASIVA);       
        getCmRadicadoServicioRemoto().insertar(cmRadicado);         
    }


    private NotificacionFacturaServicio servicioReintento;

    public NotificacionFacturaServicio getServicioReintento() {
        servicioReintento = new NotificacionFacturaServicio();
        return servicioReintento;
    }

    public void setServicioReintento(NotificacionFacturaServicio servicioReintento) {
        this.servicioReintento = servicioReintento;
    }

    public void reintentar(CmEnviosGlosaBean bean) {
        try {    
            CmRadicado radicado = getCmRadicadoServicioRemoto().consultar(bean.getObjeto().getRadicado());

            if (radicado.getEstado_radicado() == true || CmRadicado.ESTADO_ENVIO_SAP_FIN != radicado.getEstado()) {
                String mensaje = radicado.getEstado_radicado() ? " la radicación del proceso ha terminado." : " no se han realizado las fases de sincronización.";
                bean.addError("El reintento no se puede realizar, por que "+mensaje);
            } else {
                cambirFechaReintentoEnvioSap(bean, radicado);      
            }        
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean cambirFechaReintentoEnvioSap(CmEnviosGlosaBean bean, CmRadicado radicado) {
        boolean hayCambioFecha = true;
        try {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(new Date());
            calendario.add(Calendar.HOUR_OF_DAY, -24);
            getCmRadicadoServicioRemoto().actualizarFechaRegistroEnvioSap(radicado.getId(),calendario.getTime());
        } catch (Exception e) {
            hayCambioFecha = false;
            bean.addError(e.getMessage());
        }
        return hayCambioFecha;
    }

    private void verReintentos(CmEnviosGlosaBean bean) {
        try {
            bean.setListaSincronizaciones(getCmSincronizacionRemoto().consultarPorRadicado(bean.getParamConsultaUtilitario()));
            bean.getParamConsultaUtilitario().setParametroConsulta1(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    //al utilizar proceso de envio de envio en hilos las variables
    //sesion de primefaces falla entonces si hay excepcion se procede
    //utilizar el armado de auditoria a pedal.

    private void setVariablesAuditoria(Auditoria objAuditoria) {

        Url url = null;
        try {
            url = new Url();
        } catch (Exception e) {
        }

        try {
            if (url != null) {
                url.auditoriaGuardar(objAuditoria);
            } else {
                if (getConexion() != null) {
                    objAuditoria.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
                    objAuditoria.setTerminalCrea(getConexion().getIp());
                    objAuditoria.setFechaHoraCrea(new Date());
                } else {
                    Logger.getLogger("Error en setVariablesAuditoria " + CmEnviosGlosasServicioImpl.class.getName()).
                            log(Level.SEVERE, null, new Exception("Error al obtener objeto URL u Objeto Conexion"));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProcesoConciliacion(int idConciliacion, int cantidadFacturas, int tipoActualizacion) {
        try {
            CmConciliacion obj = getCmConciliacionRemoto().consultar(idConciliacion);
            obj.setCantidadFacturasRegistradas(cantidadFacturas);
            if (TIPO_ACTUALIZACION_CONCILIACION_FINAL == tipoActualizacion) {
                obj.setHoraFinalizacionRegistro(new Date());
                obj.setEstadoProceso(CmConciliacion.ESTADO_REGISTRADA);
            }
            getCmConciliacionRemoto().actualizar(obj);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProcesoCierreAuditoria(int idAuditoriaCierre, int cantidadDetalles, int tipoActualizacion) {
        try {
            CmAuditoriaCierre obj = getCmAuditoriaCierreRemoto().consultar(idAuditoriaCierre);
            obj.setCantidadDetallesRegistradas(cantidadDetalles);
            if (TIPO_ACTUALIZACION_CONCILIACION_FINAL == tipoActualizacion) {
                obj.setFechaHoraRegistroFinalizacion(new Date());
            }
            getCmAuditoriaCierreRemoto().actualizar(obj);
        } catch (Exception ex) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verPaquetesJson(CmEnviosGlosaBean bean) {
        try {
            bean.setListaSincronizacionPaquete(getCmSincronizacionPaqueteRemoto().consultarPorSincronizacion(bean.getParamConsultaUtilitario()));
            bean.getParamConsultaUtilitario().setParametroConsulta1(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<CmDetalle> listarTodosDetalles(int idFactura) {
        List<CmDetalle> lista = new ArrayList<>();
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            ParamConsulta parametroConsulta = new ParamConsulta();
            parametroConsulta.setParametroConsulta1(idFactura);
            parametroConsulta.setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            lista = getCmDetalleRemoto().consultarListaDetallesPorFactura(parametroConsulta);
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
        return lista;
    }
    
    private void guardarHistorialEstadoFactura( int idFactura, int estadoFactura) {
        try {
            CmFacturaEstado facturaEstado = new CmFacturaEstado();
            facturaEstado.setCmFactura(new CmFactura(idFactura));
            facturaEstado.setEstadoFactura(estadoFactura);
            setVariablesAuditoria(facturaEstado);
            getCmFacturaEstadoRemoto().insertar(facturaEstado);
        } catch (Exception e) {
             Logger.getLogger("Error en guardarHistorialEstadoFactura :"+CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void guardarEstadoSincronizacionCmRespuestaGlosa(CmGlosaRespuesta cmGlosaRespuesta, int estadoSincronizacon) {
        try {
            if (cmGlosaRespuesta != null && cmGlosaRespuesta.getId() != null) {
                CmGlosaRespuesta obj = new CmGlosaRespuesta();
                obj.setId(cmGlosaRespuesta.getId());
                obj.setEstadoSincronizacion(estadoSincronizacon);
                getCmGlosaRespuestaRemoto().actualizaEstadoSincronizacion(obj);
            }
        } catch (Exception e) {}
    }

    private void asignarCamposReintentosCmRadicado(CmRadicado cmRadicado, int tipoOperacion) {
        try {
            int cmTipoTrasaccion = 0;
            int cmIntentosPermitidos = Integer.parseInt(Optional.ofNullable(PropApl.getInstance().get(PropApl.CM_REINTENTO_CANTIDAD_MAXIMA)).orElse(REINTENTO_CANTIDAD_MAXIMA));
            switch (tipoOperacion) {
                case TIPO_CONCILIACION_MASIVA:
                case TIPO_CONCILIACION_INDIVIDUAL:
                    cmTipoTrasaccion = CmRadicado.TIPO_TRANSACCION_CONCILIACION;
                    break;
                case TIPO_RESPUESTA_GLOSA:
                case TIPO_RESPUESTA_GLOSA_MASIVA:    
                    cmTipoTrasaccion = CmRadicado.TIPO_TRANSACCION_RESPUESTA_GLOSA;
                    break;
                case TIPO_CAUSACION_M2:
                    cmTipoTrasaccion = CmRadicado.TIPO_TRANSACCION_CAUSACION;
                    break;
                 case TIPO_DEVOLUCION:
                    cmTipoTrasaccion = CmRadicado.TIPO_TRANSACCION_DEVOLUCION;
                    break;
            }
            cmRadicado.setTipoTransaccion((short) cmTipoTrasaccion);
            cmRadicado.setIntentosPermitidos((short) cmIntentosPermitidos);
        } catch (Exception e) {
        }
    }
}
