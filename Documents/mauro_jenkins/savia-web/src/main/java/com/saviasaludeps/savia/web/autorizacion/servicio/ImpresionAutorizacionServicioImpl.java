/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ImpresionRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.ImpresionAutorizacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Stiven Giraldo
 */
public class ImpresionAutorizacionServicioImpl extends AccionesBO implements ImpresionAutorizacionServicioIface {

    private AuAnexo4ImpresionRemoto getAuAnexo4ImpresionRemoto() throws Exception {
        return (AuAnexo4ImpresionRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ImpresionServicio", AuAnexo4ImpresionRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    @Override
    public void Accion(ImpresionAutorizacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
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
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesión inactiva");
        }
    }

    private void listar(ImpresionAutorizacionBean bean) {
        try {
            if (bean.getParamConsulta().getParametroConsulta1() != null && bean.getParamConsulta().getParametroConsulta2() != null) {
                Date fechaIni = (Date) bean.getParamConsulta().getParametroConsulta1();
                Date fechaFin = (Date) bean.getParamConsulta().getParametroConsulta2();
                if (fechaFin.before(fechaIni)) {
                    bean.addError("La fecha Hasta no puede ser superior a la fecha Desde.");
                } else {
                    bean.getParamConsulta().setCantidadRegistros(getAuAnexo4ImpresionRemoto().consultarCantidadLista(bean.getParamConsulta()));
                    bean.setRegistros(getAuAnexo4ImpresionRemoto().consultarLista(bean.getParamConsulta()));
                }
            }

        } catch (Exception e) {
            bean.addError("Hubo un fallo listando, favor comunicarse con el administrador");
        }
    }

    private void editar(ImpresionAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4ImpresionRemoto().consultar(bean.getObjeto().getId()));
            getAuAnexo4ImpresionRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ImpresionAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4ImpresionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().getAuAnexo4Id().setAsegAfiliadoId(getAfiliadoRemoto().consultar(bean.getObjeto().getAuAnexo4Id().getAsegAfiliadoId().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultado la impresión, favor comunicarse con el administrador");
        }
    }

    @Override
    public void cargaInicial(ImpresionAutorizacionBean bean) {
        try {
            bean.setListaTipoDocumentoAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentoAfiliado(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar con el administrador");
        }
    }

}
