/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.facturasusuario.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.facturas.bean.CmFacturasBean;
import com.saviasaludeps.savia.web.cuentamedica.facturasusuario.bean.CmFacturasUsuarioBean;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author jepn
 */
public class CmFacturasUsuarioServicioImpl extends AccionesBO implements CmFacturasUsuarioServicioIface {
    
    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;

    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
     private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    @Override
    public void Accion(CmFacturasUsuarioBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case CmFacturasUsuarioBean.ACCION_BUSCAR_FACTURAS:
                    if (bean.hayBusquedaAutorizacion()) {
                         buscarFacturasPorAutorizacion(bean);
                    }
                    if (bean.hayBusquedaDocumento()) {
                        buscarFacturasPorDocumentoAfiliado(bean);
                    }
                    break;
                case CmFacturasUsuarioBean.ACCION_BUSCAR_FACTURAS_DOCUMENTO:
                    buscarFacturasPorDocumentoAfiliado(bean);
                    break;
                case CmFacturasUsuarioBean.ACCION_BUSCAR_FACTURAS_AUTORIAZACION:                
                      buscarFacturasPorAutorizacion(bean);
                    break;
          
            }
        }
    }

 

    
    private void ver(CmFacturasUsuarioBean bean) { 
        try {
          bean.setObjeto( getCmFacturaRemoto().consultar(bean.getObjeto().getId()) ) ;
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verDetalle(CmFacturasUsuarioBean bean) {
        try {
            bean.setDetalleServicioActual(getCmDetalleRemoto().consultar(bean.getDetalleServicioActual().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CmFacturasUsuarioBean bean) {
        try {
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(CmFacturasUsuarioBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(CmFacturasUsuarioBean bean) {
        try {
       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(CmFacturasUsuarioBean bean) {
        try {
           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(CmFacturasUsuarioBean bean) {
        try {
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void buscarFacturasPorDocumentoAfiliado(CmFacturasUsuarioBean bean) { 
        try {
            bean.getParamConsulta(0).setParametroConsulta1(bean.getMaeTipoDocumento());
            bean.getParamConsulta(0).setParametroConsulta2(bean.getNumeroDocumento());
            bean.getParamConsulta(0).setCantidadRegistros(getCmDetalleRemoto().cantidadConsultarPorDocumento(bean.getParamConsulta(0)));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarPorDocumento(bean.getParamConsulta(0) ));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
      private void buscarFacturasPorAutorizacion(CmFacturasUsuarioBean bean) { 
        try {
            int autorizacion = Integer.parseInt(bean.getNumeroAutorizacion());
            bean.getParamConsulta(0).setParametroConsulta1(autorizacion);
            bean.getParamConsulta(0).setCantidadRegistros(getCmDetalleRemoto().cantidadConsultarPorAutorizacion( bean.getParamConsulta(0)));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarPorAutorizacion( bean.getParamConsulta(0))); 

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
    public void cargaInicial(CmFacturasUsuarioBean bean) {
        try {
              // carga de listas maestras
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
