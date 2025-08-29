/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.tutela.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import com.saviasaludeps.savia.dominio.tutela.TuTutelaEstadoRepresentante;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuRepresentanteRemoto;
import com.saviasaludeps.savia.negocio.tutela.TuTutelaEstadoRepresentanteRemoto;
import com.saviasaludeps.savia.web.tutela.bean.TuRepresentanteBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author pavacca
 */
public class TuRepresentanteServicioImpl extends AccionesBO implements TuRepresentanteServicioIface {

    private TuRepresentanteRemoto getTuRepresentanteRemoto() throws Exception {
        return (TuRepresentanteRemoto) RemotoEJB.getEJBRemoto("TuRepresentanteServicio", TuRepresentanteRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private TuTutelaEstadoRepresentanteRemoto getTuTutelaEstadoRepresentanteRemoto() throws Exception {
        return (TuTutelaEstadoRepresentanteRemoto) RemotoEJB.getEJBRemoto("TuTutelaEstadoRepresentanteServicio", TuTutelaEstadoRepresentanteRemoto.class.getName());
    }

    @Override
    public void Accion(TuRepresentanteBean bean) {
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
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(TuRepresentanteBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap());
            }

            bean.getParamConsulta().setCantidadRegistros(getTuRepresentanteRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTuRepresentanteRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception e) {
            bean.addError(e.getMessage());

        }
    }

    private void ver(TuRepresentanteBean bean) {
        try {
            bean.setObjeto(getTuRepresentanteRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(TuRepresentanteBean bean) {
        try {
            bean.setObjeto(new TuRepresentante());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(TuRepresentanteBean bean) {
        try {
            int idRepresentante = 0;
            TuRepresentante tuRepresentante = bean.getObjeto();
            bean.auditoriaGuardar(tuRepresentante);
            idRepresentante = getTuRepresentanteRemoto().insertar(tuRepresentante);
            tuRepresentante.setId(idRepresentante);
            if (!bean.isError()) {
                bean.addMensaje("Se creo el representante con id (" + idRepresentante + ") de manera exitosa \n ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(TuRepresentanteBean bean) {
        try {
            TuRepresentante tuRepresentante = getTuRepresentanteRemoto().consultar(bean.getObjeto().getId());
            if (tuRepresentante != null) {
                bean.setObjeto(tuRepresentante);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(TuRepresentanteBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getTuRepresentanteRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(TuRepresentanteBean bean) {
        try {
            TuRepresentante tuRepresentante = bean.getObjeto();
            List<TuTutelaEstadoRepresentante> conultarRepresentanteEstado = getTuTutelaEstadoRepresentanteRemoto().consultarRespresentantesPorIdRepresentante(tuRepresentante.getId());
            if (conultarRepresentanteEstado.isEmpty()) {
                getTuRepresentanteRemoto().eliminar(tuRepresentante.getId());
                bean.addMensaje("Se eliminó un registro de manera exitosa");
            } else {
                bean.addError("No se puede eliminar el representante porque tiene estados de tutela");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInicial(TuRepresentanteBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));

            bean.setListaTipoEstadoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setHashTipoEstadoPersona(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));

            bean.setListaSexo(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setHashSexo(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
}
