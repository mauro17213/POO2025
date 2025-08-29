/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.maestro.bean.ServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaTecnologiaServicioHabilitacionRemoto;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;

/**
 *
 * @author jyperez
 */
public class ServiciosHabilitacionServicioImpl extends AccionesBO implements ServiciosHabilitacionServicioIface {
    
    private MaServicioHabilitacionRemoto getServicioHabilitacionRemoto() throws Exception {
        return (MaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto(("MaServicioHabilitacionServicio"), MaServicioHabilitacionRemoto.class.getName());
    }
    
    private MaTecnologiaServicioHabilitacionRemoto getTecnologiaServicioHabilitacionRemoto() throws Exception {
        return (MaTecnologiaServicioHabilitacionRemoto) RemotoEJB.getEJBRemoto(("MaTecnologiaServicioHabilitacionServicio"), MaTecnologiaServicioHabilitacionRemoto.class.getName());
    }

    @Override
    public void Accion(ServiciosHabilitacionBean bean) {
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

    private void listar(ServiciosHabilitacionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getServicioHabilitacionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getServicioHabilitacionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ServiciosHabilitacionBean bean) {
        try {
            bean.setObjeto(getServicioHabilitacionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ServiciosHabilitacionBean bean) {
        try {
            bean.setObjeto(new MaServicioHabilitacion());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ServiciosHabilitacionBean bean) {
        Maestro maestro;
        try {
            // validaciones
            //2022-06-02 jyperez adición maestro grupos
            maestro = bean.getHashGrupos().get(bean.getObjeto().getMaeGrupoId());
            if (maestro != null) {
                    bean.getObjeto().setMaeGrupoCodigo(maestro.getValor());
                    bean.getObjeto().setMaeGrupoValor(maestro.getNombre());
            } else {
                bean.addError("Ocurrió un error consultando el Maestro de Grupos. Contacte al administrador del sistema.");
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                bean.getObjeto().setId(getServicioHabilitacionRemoto().insertar(bean.getObjeto()));
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ServiciosHabilitacionBean bean) {
        try {
            bean.setObjeto(getServicioHabilitacionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ServiciosHabilitacionBean bean) {
        int cantidad = 0;
        Maestro maestro;
        try {
            // validaciones//2022-06-02 jyperez adición maestro grupos
            maestro = bean.getHashGrupos().get(bean.getObjeto().getMaeGrupoId());
            if (maestro != null) {
                    bean.getObjeto().setMaeGrupoCodigo(maestro.getValor());
                    bean.getObjeto().setMaeGrupoValor(maestro.getNombre());
            } else {
                bean.addError("Ocurrió un error consultando el Maestro de Grupos. Contacte al administrador del sistema.");
            }
            //2021-04-26 jyperez validamos si hubo cambio de estado en el objeto, para lanzar la validación
            if (bean.isEstadoInicialTecnologia() != bean.getObjeto().isActivo() && !bean.getObjeto().isActivo()) {
                 cantidad = getTecnologiaServicioHabilitacionRemoto().consultarCantidadPorServicioHabilitacion(bean.getObjeto().getId());
                 if (cantidad > 0) {
                     bean.addError("El servicio de habilitación está relacionado con " + cantidad + " registros de tecnologías. No es posible inactivarlo.");
                 }
            }
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getServicioHabilitacionRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(ServiciosHabilitacionBean bean) {
        try {
            bean.setObjeto(getServicioHabilitacionRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(ServiciosHabilitacionBean bean) {
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
    public void cargaInicial(ServiciosHabilitacionBean bean) {
        try {
            //carga de maestros
            bean.setListaTipos(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_INSUMO_TIPO));
            bean.setHashTipos(Util.convertToHash(bean.getListaTipos()));
            //2022-06-02 jyperez nuevo maestro Grupo
            bean.setListaGrupos(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.MA_GRUPO_SERVICIOS_HABILITACION));
            bean.setHashGrupos(Util.convertToHash(bean.getListaGrupos()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
