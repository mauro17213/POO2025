/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.web.contratacion.bean.MarcacionesBean;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;

/**
 *
 * @author Jose Perez
 */
public class MarcacionesServicioImpl extends AccionesBO implements MarcacionesServicioIface {

    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }

    @Override
    public void Accion(MarcacionesBean bean) {
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
        }
    }

    @Override
    public void cargaInicial(MarcacionesBean bean) {
        try {
            //Maestros
//            bean.setListaAmbito(bean.getContratacionSingle().listarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setListaAmbito(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_AMBITO));
            bean.setHashAmbito(Util.convertToHash(bean.getListaAmbito()));
//            bean.setListaModalidadContrato(bean.getContratacionSingle().listarPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setListaModalidadContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setHashModalidadContrato(Util.convertToHash(bean.getListaModalidadContrato()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(MarcacionesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getContratoDetalleRemoto().consultarCantidadListaMarcaciones(bean.getParamConsulta()));
            bean.setRegistros(getContratoDetalleRemoto().consultarListaMarcaciones(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(MarcacionesBean bean) {
        try {
            bean.setObjeto(getContratoDetalleRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(MarcacionesBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(MarcacionesBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(MarcacionesBean bean) {
        try {
            bean.setObjeto(getContratoDetalleRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(MarcacionesBean bean) {
        try {
            if (!bean.isError()){
                bean.auditoriaModificar(bean.getObjeto());
                getContratoDetalleRemoto().actualizar(bean.getObjeto());
                bean.addMensaje("Se actualizó un registro de manera exitosa.");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(MarcacionesBean bean) {
        try {

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
