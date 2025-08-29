/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.rips.regla.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmControlCierre;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.ControlCierreRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.regla.bean.CmControlCierreBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;


/**
 *
 * @author raul-palacios
 */
public class CmControlCierreServicioImpl extends AccionesBO implements CmControlCierreServicioIface {

    private ControlCierreRemoto getControlCierreRemoto() throws Exception {
        return (ControlCierreRemoto) RemotoEJB.getEJBRemoto("CmControlCierreServicio", ControlCierreRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    @Override
    public void Accion(CmControlCierreBean bean) {
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

                default:
                    break;
            }
        }
    }

    private void listar(CmControlCierreBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getControlCierreRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getControlCierreRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(CmControlCierreBean bean) {
        try {
            bean.setObjeto(getControlCierreRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(CmControlCierreBean bean) {
        try {
            bean.setObjeto(new CmControlCierre());
        } catch (Exception ex) {
            bean.addError("Error: " + ex.toString());
        }
    }

    @Override
    public void editar(CmControlCierreBean bean) {
        try {
            bean.setObjeto(getControlCierreRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void guardar(CmControlCierreBean bean) {
        try {
            
            if (validarNoExistenCierresSimilares(bean, CmControlCierreBean.TIPO_OPERACION_INSERCION)) {
                for (Map.Entry<Integer, Maestro> mae : bean.getHashModalidadContratos().entrySet()) {

                    if (mae.getValue().getId().equals(bean.getObjeto().getMaeContratoModalidadId())) {
                        bean.getObjeto().setMaeContratoModalidadCodigo(mae.getValue().getValor());
                        bean.getObjeto().setMaeContratoModalidadValor(mae.getValue().getNombre());
                        bean.auditoriaGuardar(bean.getObjeto());
                        bean.getObjeto().setId(getControlCierreRemoto().insertar(bean.getObjeto()));
                    }
                }
                bean.addMensaje("Se creo un registro de manera exitosa. ");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean validarNoExistenCierresSimilares(CmControlCierreBean bean, int tipoOperacion) throws Exception {
        boolean noHayCierres;
        ParamConsulta paramConsulta = new ParamConsulta();
        paramConsulta.setParametroConsulta1(bean.getObjeto().getFechaHoraDesde());
        paramConsulta.setParametroConsulta2(bean.getObjeto().getMaeContratoModalidadId());
        paramConsulta.setParametroConsulta3(bean.getObjeto().getCoberturaCierre());
        paramConsulta.setParametroConsulta4(bean.getObjeto().getFechaHoraHasta());
        if(CmControlCierreBean.TIPO_OPERACION_EDICION == tipoOperacion){
          paramConsulta.setParametroConsulta5(bean.getObjeto().getId());
        }
        
        
        List<CmControlCierre> cierres = getControlCierreRemoto().consultarPresenciaFechaEnIntervalo(paramConsulta);
        noHayCierres = cierres.isEmpty();
        if(!noHayCierres){
            bean.addError("Existen cierres que tienen incluida la fecha de cierre o"
                    + " fecha apertura ej: Motivo ("+cierres.get(0).getMotivo()+") e Id ("+cierres.get(0).getId()+")");
        }
        return noHayCierres;
    }

    private void modificar(CmControlCierreBean bean) {
        try {
            if (validarNoExistenCierresSimilares(bean, CmControlCierreBean.TIPO_OPERACION_EDICION )) {
                for (Map.Entry<Integer, Maestro> mae : bean.getHashModalidadContratos().entrySet()) {
                    if (mae.getValue().getId().equals(bean.getObjeto().getMaeContratoModalidadId())) {
                        bean.getObjeto().setMaeContratoModalidadCodigo(mae.getValue().getValor());
                        bean.getObjeto().setMaeContratoModalidadValor(mae.getValue().getNombre());
                        bean.auditoriaModificar(bean.getObjeto());
                        getControlCierreRemoto().actualizar(bean.getObjeto());
                    }
                }
                bean.addMensaje("Se actualizó un registro de manera exitosa");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

     @Override
    public void cargasInicial(CmControlCierreBean bean) {
        try {
            bean.setHashModalidadContratos(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CNT_MODALIDAD));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
