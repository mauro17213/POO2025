/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.aseguramiento.bean.CertificadosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoCertificadoRemoto;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.Util;

/**
 *
 * @author Jaime Andres Olarte
 */
public class CertificadosServicioImpl extends AccionesBO implements CertificadosServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private AfiliadoCertificadoRemoto getAfiliadoCertificadoRemoto() throws Exception {
        return (AfiliadoCertificadoRemoto) RemotoEJB.getEJBRemoto(("AfiliadoCertificadoServicio"), AfiliadoCertificadoRemoto.class.getName());
    }

    @Override
    public void Accion(CertificadosBean bean) {
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

    private void listar(CertificadosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getAfiliadoCertificadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAfiliadoCertificadoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(CertificadosBean bean) {
        try {
            bean.setObjeto(getAfiliadoCertificadoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CertificadosBean bean) {
        try {
            bean.setObjeto(new AsegAfiliadoCertificado());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(CertificadosBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(CertificadosBean bean) {
        try {
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(CertificadosBean bean) {
        try {
            
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(CertificadosBean bean) {
        try {
            
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cargas(CertificadosBean bean) {
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
    public void cargaInicial(CertificadosBean bean) {
        try {
            bean.setListaTiposDocumento(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(Util.convertToHash(bean.getListaTiposDocumento()));
            bean.setListaEstadosAfiliacion(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashEstadosAfiliacion(Util.convertToHash(bean.getListaEstadosAfiliacion()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
