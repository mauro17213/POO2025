/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.facturas.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaConceptoContableRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMotivosGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaTransaccionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.CmAuditoriaFacturaBeanUtil;
import com.saviasaludeps.savia.web.maestro.bean.DiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.facturas.bean.CmFacturasBean;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author jepn
 */
public class CmFacturasServicioImpl extends AccionesBO implements CmFacturasServicioIface {
    
    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;

    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
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
    
    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }
    
    private CmFacturaTransaccionRemoto getCmFacturaTransaccionServicioRemoto() throws Exception {
        return (CmFacturaTransaccionRemoto) RemotoEJB.getEJBRemoto("CmFacturaTransaccionServicio", CmFacturaTransaccionRemoto.class.getName());
    }
    
    private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }
    
    private CmAuditoriaDescuentoCapitaRemoto getCmAuditoriaDescuentoCapitaRemoto() throws Exception {
        return (CmAuditoriaDescuentoCapitaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDescuentoCapitaServicio", CmAuditoriaDescuentoCapitaRemoto.class.getName());
    }
    
    @Override
    public void Accion(CmFacturasBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case CmFacturasBean.ACCION_LISTAR_FACTURAS:
                            listar(bean);
                            break;
                        case CmFacturasBean.ACCION_LISTAR_DETALLES:
                            listarDetalles(bean);
                            break;
                        case CmFacturasBean.ACCION_LISTAR_CONCEPTOS:
                            listarAuditoriaConceptosContables(bean);
                            break;
                        case CmFacturasBean.ACCION_LISTAR_AUTORIZACIONES:
                            listarAuditoriaAutorizacion(bean);
                            break;
                        case CmFacturasBean.ACCION_LISTAR_DIAGNOSTICOS:
                            listarAuditoriaDiagnosticos(bean);
                            break;
                        case CmFacturasBean.ACCION_LISTAR_MOTIVOS_GLOSA:
                            listarAuditoriaMotivosGlosa(bean);
                            break;
                        case CmFacturasBean.ACCION_LISTAR_GLOSA_RESPUESTA:
                            listarGlosaRespuesta(bean);
                            break;
                         case CmFacturasBean.ACCION_LISTAR_FACTURA_TRANSACCIONES:
                            listarFacturaTransacciones(bean);
                            break;
                         case CmFacturasBean.ACCION_LISTAR_ADJUNTOS_CMDETALLES:
                            listarAuditoriaAdjuntosCmDetalles(bean);
                            break;
                         case CmFacturasBean.ACCION_LISTAR_ADJUNTOS_FACTURA:
                             listarAdjuntosFactura(bean);
                            break;
                         case CmFacturasBean.ACCION_LISTAR_DESCUENTO_CAPITA:
                             listarDescuentoCapita(bean);
                            break;    
                    }

                    break;
                case Url.ACCION_VER:

                    switch (bean.getDoAccion()) {
                        case CmFacturasBean.ACCION_VER_FACTURA:
                            ver(bean);
                            break;
                        case CmFacturasBean.ACCION_VER_DETALLE:
                            verDetalle(bean);
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
         
                
            }
            cargas(bean);
        }
    }

    private void listar(CmFacturasBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCmFacturaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmFacturaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetalles(CmFacturasBean bean) {
        try {
            bean.getParamConsultaServicios().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServicios().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServicios()));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarListaDetallesPorFacturaHistorico(bean.getParamConsultaServicios()));
             
            CmAuditoriaFacturaBeanUtil.asignarCodigoMipres(bean.getRegistrosDetalles());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarAuditoriaMotivosGlosa(CmFacturasBean bean) {
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
    
     private void listarAuditoriaAutorizacion(CmFacturasBean bean) {
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
     
     private void listarAuditoriaConceptosContables(CmFacturasBean bean) {
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
     
     private void listarAuditoriaDiagnosticos(CmFacturasBean bean) {
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
     
    private void listarGlosaRespuesta(CmFacturasBean bean) {
        try {
            bean.getParamConsultaRespuestaGlosa().setCantidadRegistros(getCmGlosaRespuestaRemoto().consultarCantidadLista(bean.getParamConsultaRespuestaGlosa()));
            bean.setRegistrosGlosaRespuesta(getCmGlosaRespuestaRemoto().consultarLista(bean.getParamConsultaRespuestaGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarFacturaTransacciones(CmFacturasBean bean) {
        try {
            bean.getParamConsultaTransacciones().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaTransacciones().setCantidadRegistros(getCmFacturaTransaccionServicioRemoto().consultarCantidadLista(bean.getParamConsultaTransacciones()));
            bean.setRegistrosFacturasTransaccion(getCmFacturaTransaccionServicioRemoto().consultarLista(bean.getParamConsultaTransacciones()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
      private void listarAuditoriaAdjuntosCmDetalles(CmFacturasBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta3(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setParametroConsulta4(CmAuditoriaAdjunto.TIPO_DETALLE);
            paramConsulta.setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaAdjutoCmDetalle(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
     private void listarAdjuntosFactura(CmFacturasBean bean) {
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
     
    private void listarDescuentoCapita(CmFacturasBean bean) {
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
     
    private void ver(CmFacturasBean bean) { 
        try {
          bean.setObjeto( getCmFacturaRemoto().consultar(bean.getObjeto().getId()) ) ;
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verDetalle(CmFacturasBean bean) {
        try {
            bean.setDetalleServicioActual(getCmDetalleRemoto().consultar(bean.getDetalleServicioActual().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(DiagnosticosBean bean) {
        try {
            bean.setObjeto(new MaDiagnostico());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(DiagnosticosBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeDiagnosticoCapituloId() != 0) {
                maestro = bean.getHashCapitulo().get(bean.getObjeto().getMaeDiagnosticoCapituloId());
                bean.getObjeto().setMaeDiagnosticoCapituloCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCapituloValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeDiagnosticoCategoriaId() != 0) {
                maestro = bean.getHashCategoria().get(bean.getObjeto().getMaeDiagnosticoCategoriaId());
                bean.getObjeto().setMaeDiagnosticoCategoriaCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCategoriaValor(maestro.getNombre());
            }
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                //getDiagnosticoRemoto().insertar(bean.getObjeto());
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(DiagnosticosBean bean) {
        try {
            //bean.setObjeto(getDiagnosticoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(DiagnosticosBean bean) {
        Maestro maestro;
        try {
            // almacenamos los valores correspondientes al código y valor de los maestros
            if (bean.getObjeto().getMaeDiagnosticoCapituloId() != 0) {
                maestro = bean.getHashCapitulo().get(bean.getObjeto().getMaeDiagnosticoCapituloId());
                bean.getObjeto().setMaeDiagnosticoCapituloCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCapituloValor(maestro.getNombre());
            }
            if (bean.getObjeto().getMaeDiagnosticoCategoriaId() != 0) {
                maestro = bean.getHashCategoria().get(bean.getObjeto().getMaeDiagnosticoCategoriaId());
                bean.getObjeto().setMaeDiagnosticoCategoriaCodigo(maestro.getValor());
                bean.getObjeto().setMaeDiagnosticoCategoriaValor(maestro.getNombre());
            }
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                //getDiagnosticoRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(DiagnosticosBean bean) {
        try {
            //bean.setObjeto(getDiagnosticoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(CmFacturasBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    //Estado
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargaInicial(CmFacturasBean bean) {
        try {
           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
