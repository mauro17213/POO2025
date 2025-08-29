/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.web.administracion.bean.LogBean;
import com.saviasaludeps.savia.dominio.administracion.Log;
import com.saviasaludeps.savia.negocio.administracion.LogRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author raul-palacios
 */
public class LogServicioImpl extends AccionesBO implements LogServicioIface{
    
    private LogRemoto getLogRemoto() throws Exception {
        return (LogRemoto) RemotoEJB.getEJBRemoto("LogServicio", LogRemoto.class.getName());
    }

    @Override
    public void Accion(LogBean bean) {
        if(super.ValidarSesion(bean)){
            switch(bean.getAccion()){
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
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
    
    private void listar(LogBean bean){
        try{
            bean.getParamConsulta().setCantidadRegistros(getLogRemoto().consultarCantidadTodos(bean.getParamConsulta()));
            bean.setRegistros(getLogRemoto().consultarTodos(bean.getParamConsulta()));
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(LogBean bean){
        try{
            bean.setObjeto(getLogRemoto().consultar(bean.getObjeto().getId()));
        }catch(Exception e){
            bean.addError(e.getMessage());
        }
    }
    
    public void guardar(Log obj){
        try{
            obj.setId(getLogRemoto().insertar(obj));
        }catch(Exception e){}
    }
    
}
