/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.servicio;

import com.saviasaludeps.savia.dominio.financiera.FinGiro;
import com.saviasaludeps.savia.negocio.financiera.FinGiroRemoto;
import com.saviasaludeps.savia.web.administracion.bean.UsuarioBean;
import com.saviasaludeps.savia.web.financiera.bean.FinGiroBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author jeperez
 */
public class FinGiroServicioImpl extends AccionesBO implements FinGiroServicioIface {

    public static final int ID_PRESTADOR_SAVIA = 1;
    
    private FinGiroRemoto getFinGiroRemoto() throws Exception {
        return (FinGiroRemoto) RemotoEJB.getEJBRemoto("FinGiroServicio", FinGiroRemoto.class.getName());
    }

    @Override
    public void Accion(FinGiroBean bean) {
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
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_ADICIONAL_4:
                    break;
                case Url.ACCION_ADICIONAL_5:
                    break;
                case Url.ACCION_ADICIONAL_6:
                    break;
            }
        }
    }
    
    private void crear(FinGiroBean bean) {
        try {
            bean.setObjeto(new FinGiro());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(FinGiroBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getFinGiroRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa. ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void editar(FinGiroBean bean) {
        try {
            bean.setObjeto(getFinGiroRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void modificar(FinGiroBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getFinGiroRemoto().actualizar(bean.getObjeto());;
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(FinGiroBean bean) {
        try {
            bean.setObjeto(getFinGiroRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargasInicial(FinGiroBean bean) {
        try {
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al administrador");
        }
    }

    private void listar(FinGiroBean bean) {
        try {   
            bean.getParamConsulta().setCantidadRegistros(getFinGiroRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getFinGiroRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

   

   
}
