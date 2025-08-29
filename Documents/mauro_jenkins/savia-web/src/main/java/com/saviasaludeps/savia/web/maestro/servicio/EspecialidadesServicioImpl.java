/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.web.maestro.bean.EspecialidadesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaEspecialidadRemoto;

/**
 *
 * @author jyperez
 */
public class EspecialidadesServicioImpl extends AccionesBO implements EspecialidadesServicioIface {
    
    private MaEspecialidadRemoto getEspecialidadRemoto() throws Exception {
        return (MaEspecialidadRemoto) RemotoEJB.getEJBRemoto(("MaEspecialidadServicio"), MaEspecialidadRemoto.class.getName());
    }
    
    @Override
    public void Accion(EspecialidadesBean bean) {
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
                
            }
            cargas(bean);
        }
    }

    private void listar(EspecialidadesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getEspecialidadRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getEspecialidadRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(EspecialidadesBean bean) {
        try {
            bean.setObjeto(getEspecialidadRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(EspecialidadesBean bean) {
        try {
            bean.setObjeto(new MaEspecialidad());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(EspecialidadesBean bean) {
        try {
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getEspecialidadRemoto().insertar(bean.getObjeto()));
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(EspecialidadesBean bean) {
        try {
            bean.setObjeto(getEspecialidadRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(EspecialidadesBean bean) {
        try {
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getEspecialidadRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(EspecialidadesBean bean) {
        try {
            bean.setObjeto(getEspecialidadRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(EspecialidadesBean bean) {
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
    public void cargaInicial(EspecialidadesBean bean) {
        try {
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
