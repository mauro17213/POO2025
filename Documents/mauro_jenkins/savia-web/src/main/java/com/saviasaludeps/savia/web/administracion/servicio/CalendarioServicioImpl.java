/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.web.administracion.bean.CalendarioBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author raul-palacios
 */
public class CalendarioServicioImpl extends AccionesBO implements CalendarioServicioIface{
    
    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }
    
    @Override
    public void Accion(CalendarioBean bean) {
        if(super.ValidarSesion(bean)){
            switch(bean.getAccion()){
                case Url.ACCION_LISTAR:
                    listar(bean);
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
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
        }else{
            System.err.println("Sesion inactiva");
        }
    }
    
    public void listar(CalendarioBean bean){
        try{
            bean.setRegistros(getCalendarioRemoto().consultarTodos(bean.getParamConsulta()));
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(CalendarioBean bean){
        try{
            bean.auditoriaGuardar(bean.getObjeto());
            getCalendarioRemoto().actualizar(bean.getObjeto());
//            if (getCalendarioRemoto().actualizar(bean.getObjeto())){
//                bean.addMensaje("Se creo un registro de manera exitosa");
//            } else {
//                bean.addMensaje("Se eliminó un registro de manera exitosa");
//            }
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
}
