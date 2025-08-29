/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Recurrencia;
import com.saviasaludeps.savia.negocio.administracion.RecurrenciaRemoto;
import com.saviasaludeps.savia.web.administracion.bean.RecurrenciaBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;


/**
 *
 * @author jjmosquera
 */
public class RecurrenciaServicioImpl extends AccionesBO implements RecurrenciaServicioIface {

      private Recurrencia per = new Recurrencia();

    private RecurrenciaRemoto getRecurrenciaRemoto() throws Exception {
        return (RecurrenciaRemoto) RemotoEJB.getEJBRemoto("RecurrenciaServicio", RecurrenciaRemoto.class.getName());
    }
    
    @Override
    public void Accion(RecurrenciaBean bean) {
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
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
        private void cargas(RecurrenciaBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:                    
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:                   
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }
        
           @Override
    public void cargasInicial(RecurrenciaBean bean) {
           try {

        } catch (Exception ex) {
           bean.addError("No fue posible cargar las listas de apoyo");
        }
    }      
    
       private void listar(RecurrenciaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getRecurrenciaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getRecurrenciaRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(RecurrenciaBean bean) {
        try {
            bean.setObjeto(getRecurrenciaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(RecurrenciaBean bean) {
        try {
            bean.setObjeto(new Recurrencia());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(RecurrenciaBean bean) {
        try {
            per.setId(bean.getObjeto().getId());
            bean.setObjeto(getRecurrenciaRemoto().consultar(per.getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(RecurrenciaBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getRecurrenciaRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(RecurrenciaBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getRecurrenciaRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(RecurrenciaBean bean) {
        try {
            per = new Recurrencia();
            per.setId(bean.getObjeto().getId());
            bean.setObjeto(getRecurrenciaRemoto().eliminar(per.getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

 

   

}
