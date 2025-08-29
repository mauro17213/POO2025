/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;

/**
 *
 * @author jyperez
 */
public class MedicamentoServicioImpl extends AccionesBO implements MedicamentoServicioIface {
    
    private MaMedicamentoRemoto getMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaMedicamentoServicio"), MaMedicamentoRemoto.class.getName());
    }

    @Override
    public void Accion(SelMedicamentoBean bean) {
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

    private void listar(SelMedicamentoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMedicamentoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMedicamentoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(SelMedicamentoBean bean) {
        try {
            bean.setObjeto(getMedicamentoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(SelMedicamentoBean bean) {
        try {
            bean.setObjeto(new MaMedicamento());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(SelMedicamentoBean bean) {
        try {
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaGuardar(bean.getObjeto());
                //guardamos el registro
                getMedicamentoRemoto().insertar(bean.getObjeto());
                bean.addMensaje("Se creó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(SelMedicamentoBean bean) {
        try {
            bean.setObjeto(getMedicamentoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(SelMedicamentoBean bean) {
        try {
            // validaciones
            //verificamos si se pasa las validaciones para poder ejecutar la acción
            if (!bean.isError()) {
                bean.auditoriaModificar(bean.getObjeto());
                //guardamos el registro
                getMedicamentoRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(SelMedicamentoBean bean) {
        try {
            bean.setObjeto(getMedicamentoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(SelMedicamentoBean bean) {
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
    public void cargaInicial(SelMedicamentoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMedicamentoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta()));
            bean.setRegistros(getMedicamentoRemoto().consultarListaBuscador(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
