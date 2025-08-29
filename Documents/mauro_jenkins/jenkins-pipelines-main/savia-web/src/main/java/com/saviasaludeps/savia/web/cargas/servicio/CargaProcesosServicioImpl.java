/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoPrestador;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarPeriodoRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoPrestadoresRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.cargas.CarProcesosRemoto;
import com.saviasaludeps.savia.web.cargas.bean.CargaProcesosBean;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aguevara
 */
public class CargaProcesosServicioImpl extends AccionesBO implements CargaProcesosServicioIface {

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private CarProcesosRemoto getCarProcesosRemoto() throws Exception {
        return (CarProcesosRemoto) RemotoEJB.getEJBRemoto("CarProcesosServicio", CarProcesosRemoto.class.getName());
    }

    private CarProcesoPrestadoresRemoto getCarProcesoPrestadoresRemoto() throws Exception {
        return (CarProcesoPrestadoresRemoto) RemotoEJB.getEJBRemoto("CarProcesoPrestadoresServicio", CarProcesoPrestadoresRemoto.class.getName());
    }

    private CarProcesoUsuarioRemoto getCarProcesoUsuarioRemoto() throws Exception {
        return (CarProcesoUsuarioRemoto) RemotoEJB.getEJBRemoto("CarProcesoUsuarioServicio", CarProcesoUsuarioRemoto.class.getName());
    }

    private CarPeriodoRemoto getCarPeriodoRemoto() throws Exception {
        return (CarPeriodoRemoto) RemotoEJB.getEJBRemoto("CarPeriodoServicio", CarPeriodoRemoto.class.getName());
    }

    @Override
    public void cargasInicial(CargaProcesosBean bean) {

        try {
            if (bean.getConexion().getUsuario() != null) {
                //bean.setListaClasePrestador(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_CLASE_PRESTADOR));
                bean.setListaClasePrestador(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_CLASE_PRESTADOR));
                bean.setHashClasePrestador(Util.convertToHash(bean.getListaClasePrestador()));
                //bean.setListaTipoDocumento(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                bean.setListaTipoDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
                bean.setHashTipoDocumento(Util.convertToHash(bean.getListaTipoDocumento()));
                bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
            }

        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al adminitrador");
        }
    }

    @Override
    public void Accion(CargaProcesosBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarPrestadores(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearPrestador(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarPrestador(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarPrestador(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarPrestador(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarPrestador(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarPeriodo(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearPeriodo(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarPeriodo(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarPeriodo(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarPeriodo(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarPeriodo(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(CargaProcesosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCarProcesosRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCarProcesosRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(CargaProcesosBean bean) {
        try {
            bean.setObjeto(getCarProcesosRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCarProcesoUsuariosList(getCarProcesoUsuarioRemoto().listarPorIdProceso(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CargaProcesosBean bean) {
        try {
            bean.setObjeto(new CarProceso());
        } catch (Exception e) {
            bean.addError(e.getMessage() + "Hubo un fallo al crear, por favor contactar al adminisrtador");
        }
    }

    private void guardar(CargaProcesosBean bean) {
        try {
            // Verificar si el campo EstructuraJson no está vacío
            String estructuraJson = bean.getObjeto().getEstructuraJson();
            if (estructuraJson != null && !estructuraJson.trim().isEmpty() && bean.getObjeto().isValidJson(estructuraJson)) {
                bean.auditoriaGuardar(bean.getObjeto());
                bean.getObjeto().setId(getCarProcesosRemoto().insertar(bean.getObjeto()));
                try {
                    if (bean.getObjeto().getCarProcesoUsuariosList() != null) {
                        for (CarProcesoUsuario usuario : bean.getObjeto().getCarProcesoUsuariosList()) {
                            usuario.setProceso(bean.getObjeto());
                            bean.auditoriaGuardar(usuario);
                            usuario.setId(getCarProcesoUsuarioRemoto().insertar(usuario));
                        }
                    }
                    bean.auditoriaGuardar(bean.getObjeto());
                } catch (Exception e) {
                    throw new Exception("Fallo al ingresar usuarios");
                }
            } else {
                throw new Exception("El campo EstructuraJson está vacío o no corresponde a una estructura JSON válñida.");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, por favor contactar al administrador: " + e.getMessage());
        }

    }

    private void editar(CargaProcesosBean bean) {
        try {
            bean.setObjeto(getCarProcesosRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCarProcesoUsuariosList(getCarProcesoUsuarioRemoto().listarPorIdProceso(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(CargaProcesosBean bean) {
        try {
            // Obtener la lista original de usuarios del proceso actual
            List<CarProcesoUsuario> usuariosOriginales = getCarProcesoUsuarioRemoto().listarPorIdProceso(bean.getObjeto().getId());

            // Actualizar el proceso principal
            getCarProcesosRemoto().actualizar(bean.getObjeto());

            // Crear una lista para los usuarios actuales del proceso
            List<CarProcesoUsuario> usuariosActuales = bean.getObjeto().getCarProcesoUsuariosList();

            // Insertar o actualizar usuarios del proceso actual
            for (CarProcesoUsuario usuario : usuariosActuales) {
                if (usuario.getId() == null) {
                    // Nuevo usuario, agregar
                    usuario.setProceso(bean.getObjeto());
                    bean.auditoriaGuardar(usuario);
                    usuario.setId(getCarProcesoUsuarioRemoto().insertar(usuario));
                } else if (usuario.isModificado()) {
                    // Usuario existente, modificar
                    bean.auditoriaModificar(usuario);
                    getCarProcesoUsuarioRemoto().actualizar(usuario);
                }
            }

            // Eliminar solo los usuarios del proceso actual que ya no están en la lista actual
            for (CarProcesoUsuario usuarioOriginal : usuariosOriginales) {
                boolean usuarioExiste = false;

                // Verificar si el usuario original sigue presente en la lista actual
                for (CarProcesoUsuario usuarioActual : usuariosActuales) {
                    if (usuarioActual.getId() != null && usuarioActual.getId().equals(usuarioOriginal.getId())) {
                        usuarioExiste = true;
                        break;
                    }
                }

                // Si el usuario original no está en la lista actual, eliminarlo
                if (!usuarioExiste) {
                    getCarProcesoUsuarioRemoto().eliminar(usuarioOriginal.getId());

                }
            }

            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(CargaProcesosBean bean) {
        try {
            bean.setObjeto(getCarProcesosRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarPrestadores(CargaProcesosBean bean) {
        try {
            bean.getListaParamConsultas().get(0).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(0).setCantidadRegistros(getCarProcesoPrestadoresRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosPrestadores(getCarProcesoPrestadoresRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearPrestador(CargaProcesosBean bean) {
        try {
            bean.setObjetoPrestador(new CarProcesoPrestador());
            bean.getObjetoPrestador().setProceso(new CarProceso(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage() + "Hubo un fallo al crear, por favor contactar al adminisrtador");
        }
    }

    private void guardarPrestador(CargaProcesosBean bean) {
        try {
            // Validar si hay errores
            if (!bean.isError()) {
                // Guardar en la base de datos
                bean.auditoriaGuardar(bean.getObjetoPrestador());
                bean.getObjetoPrestador().setId(getCarProcesoPrestadoresRemoto().insertar(bean.getObjetoPrestador()));
                listarPrestadores(bean);
                bean.addMensaje("Registro guardado de manera exitosa");
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar el prestador, favor contactar al administrador " + ex.toString());
        }

    }

    private void editarPrestador(CargaProcesosBean bean) {
        try {
            bean.setObjetoPrestador(getCarProcesoPrestadoresRemoto().consultar(bean.getObjetoPrestador().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificarPrestador(CargaProcesosBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoPrestador());
            getCarProcesoPrestadoresRemoto().actualizar(bean.getObjetoPrestador());
            listarPrestadores(bean);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar el prestador, favor contactar al administrador " + e.getMessage());
        }
    }

    private void borrarPrestador(CargaProcesosBean bean) {
        try {
            bean.setObjetoPrestador(getCarProcesoPrestadoresRemoto().eliminar(bean.getObjetoPrestador().getId()));
            listarPrestadores(bean);
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al eliminar el prestador, favor contactar al administrador " + e.getMessage());
        }
    }

    private void crearPeriodo(CargaProcesosBean bean) {
        try {
            bean.setObjetoPeriodo(new CarPeriodo());
            bean.getObjetoPeriodo().setCarProceso(new CarProceso(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarPeriodo(CargaProcesosBean bean) {
        try {
            bean.getListaParamConsultas().get(1).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getCarPeriodoRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosPeriodos(getCarPeriodoRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarPeriodo(CargaProcesosBean bean) {

        try {
            bean.auditoriaGuardar(bean.getObjetoPeriodo());
            bean.getObjetoPeriodo().setId(getCarPeriodoRemoto().insertar(bean.getObjetoPeriodo()));

            listarPeriodo(bean);
            bean.addMensaje("Registro guardado de manera exitosa");

        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar el periodo, favor contactar al administrador " + e.getMessage());
        }

    }

    private void borrarPeriodo(CargaProcesosBean bean) {
        try {
            bean.setObjetoPeriodo(getCarPeriodoRemoto().eliminar(bean.getObjetoPeriodo().getId()));
            listarPeriodo(bean);
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al eliminar el periodo, favor contactar al administrador  " + e.getMessage());
        }
    }

    private void editarPeriodo(CargaProcesosBean bean) {
        try {
            bean.setObjetoPeriodo(getCarPeriodoRemoto().consultar(bean.getObjetoPeriodo().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }

    }

    private void modificarPeriodo(CargaProcesosBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoPeriodo());
            getCarPeriodoRemoto().actualizar(bean.getObjetoPeriodo());
            listarPeriodo(bean);
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar el periodo, favor contactar al administrador " + e.getMessage());
        }

    }

}
