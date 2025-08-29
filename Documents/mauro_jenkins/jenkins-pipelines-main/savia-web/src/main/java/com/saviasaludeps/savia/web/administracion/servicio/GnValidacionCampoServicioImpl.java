/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnValidacionCampo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.administracion.GnValidacionCampoRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroTipoRemoto;
import com.saviasaludeps.savia.web.administracion.bean.GnValidacionCampoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;

/**
 *
 * @author sgiraldov
 */
public class GnValidacionCampoServicioImpl extends AccionesBO implements GnValidacionCampoIface {

    private GnValidacionCampoRemoto getGnValidacionCampoRemoto() throws Exception {
        return (GnValidacionCampoRemoto) RemotoEJB.getEJBRemoto("GnValidacionCampoServicio", GnValidacionCampoRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private MaestroTipoRemoto getMaestroTipoRemoto() throws Exception {
        return (MaestroTipoRemoto) RemotoEJB.getEJBRemoto("MaestroTipoServicio", MaestroTipoRemoto.class.getName());
    }
    
    @Override
    public void Accion(GnValidacionCampoBean bean) {
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
    public void cargasInicial(GnValidacionCampoBean bean) {
        try {
            bean.setListaMaestroTipos(getMaestroTipoRemoto().consultarActivos());
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al adminitrador");
        }
    }

    private void listar(GnValidacionCampoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getGnValidacionCampoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getGnValidacionCampoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al adminitrador");
        }
    }

    private void ver(GnValidacionCampoBean bean) {
        try {
            bean.setObjeto(getGnValidacionCampoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setGnMaestrosList(getMaestroRemoto().listarPorIdValidacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al ver, favor contactar al administrador");
        }
    }

    private void crear(GnValidacionCampoBean bean) {
        try {
            bean.setObjeto(new GnValidacionCampo());
            bean.getObjeto().setGnMaestroTiposTipo(new MaestroTipo());
            bean.getObjeto().setGnMaestrosList(new ArrayList<>());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al crear, favor contactar al adminitrador");
        }
    }

    private void guardar(GnValidacionCampoBean bean) {
        try {
            bean.getObjeto().setId(getGnValidacionCampoRemoto().insertar(bean.getObjeto()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al guardar, favor contactar al adminitrador");
        }
    }

    private void editar(GnValidacionCampoBean bean) {
        try {
            bean.setObjeto(getGnValidacionCampoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setGnMaestrosList(getMaestroRemoto().listarPorIdValidacion(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al editar, favor contactar al administrador");
        }
    }

    private void modificar(GnValidacionCampoBean bean) {
        try {
            getGnValidacionCampoRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se modifico de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al modificar, favor contactar al administrador");
        }
    }

    private void borrar(GnValidacionCampoBean bean) {
        try {
            getGnValidacionCampoRemoto().eliminar(bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError("Hubo un fallo al borrar, favor contactar al administrador");
        }
    }
    
}
