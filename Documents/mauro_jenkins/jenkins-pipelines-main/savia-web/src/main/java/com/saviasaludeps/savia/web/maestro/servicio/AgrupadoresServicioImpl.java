/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.maestro.MaAgrupadoresMedicamentos;
import com.saviasaludeps.savia.web.maestro.bean.AgrupadoresMedicamentoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaAgrupadoresMedicamentoRemoto;

/**
 *
 * @author jarodriguez
 */
public class AgrupadoresServicioImpl extends AccionesBO implements AgrupadoresServicioIface {
    
    private MaAgrupadoresMedicamentoRemoto getAgrupadoresRemoto() throws Exception {
       return (MaAgrupadoresMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaAgrupadoresServicio"), MaAgrupadoresMedicamentoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AgrupadoresMedicamentoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                
            }
            cargas(bean);
        }    
    }
    
    private void listar(AgrupadoresMedicamentoBean bean) {
        try{
            bean.getParamConsulta().setCantidadRegistros(getAgrupadoresRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAgrupadoresRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(AgrupadoresMedicamentoBean bean) {
        try {
            bean.setObjeto(getAgrupadoresRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(AgrupadoresMedicamentoBean bean) {
        try {
            bean.setObjeto(new MaAgrupadoresMedicamentos());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(AgrupadoresMedicamentoBean bean) {
        try {
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                int cantcodigo = getAgrupadoresRemoto().consultarCodigo(bean.getObjeto());
                if (cantcodigo == 0){
                //guardamos el registro
                    bean.getObjeto().setId(getAgrupadoresRemoto().insertar(bean.getObjeto()));
                    bean.addMensaje("Se creó un registro de manera exitosa.");
                }else{
                    bean.addError("El codigo ya existe.");
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void editar(AgrupadoresMedicamentoBean bean) {
        try {
            bean.setObjeto(getAgrupadoresRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(AgrupadoresMedicamentoBean bean) {
        try {
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                int cantcodigo = getAgrupadoresRemoto().consultarCodigo(bean.getObjeto());
                if (cantcodigo == 0) {
                    //guardamos el registro
                    getAgrupadoresRemoto().actualizar(bean.getObjeto());
                    bean.addMensaje("Se actualizó un registro de manera exitosa.");
                }else {
                    bean.addError("El codigo ya existe.");
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void borrar(AgrupadoresMedicamentoBean bean) {
        try {
            bean.setObjeto(getAgrupadoresRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
      private void cargas(AgrupadoresMedicamentoBean bean) {
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
    public void cargaInicial(AgrupadoresMedicamentoBean bean) {
        try {
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
   
}
