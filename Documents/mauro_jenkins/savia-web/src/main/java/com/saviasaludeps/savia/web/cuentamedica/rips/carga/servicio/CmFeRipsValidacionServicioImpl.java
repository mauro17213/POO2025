package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsValidacion;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsValidacionHistoricoRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsValidacionRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmFeRipsValidacionBean;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmUtilidades;

public class CmFeRipsValidacionServicioImpl extends AccionesBO implements CmFeRipsValidacionServicioIface {
    
    private CmFeRipsValidacionRemoto getCmFeRipsValidacionRemoto() throws Exception {
        return (CmFeRipsValidacionRemoto) RemotoEJB.getEJBRemoto("CmFeRipsValidacionServicio", CmFeRipsValidacionRemoto.class.getName());
    }
    
    private CmFeRipsValidacionHistoricoRemoto getCmFeRipsValidacionHistoricoRemoto() throws Exception {
        return (CmFeRipsValidacionHistoricoRemoto) RemotoEJB.getEJBRemoto("CmFeRipsValidacionHistoricoServicio", CmFeRipsValidacionHistoricoRemoto.class.getName());
    }

    @Override
    public void Accion(CmFeRipsValidacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    switch (bean.getDoAccion()) {
                        case CmFeRipsValidacionBean.DO_ACCION_LISTAR_VALIDACIONES:
                            listar(bean);
                            break;
                        case CmFeRipsValidacionBean.DO_ACCION_LISTAR_HISTORICO_VALIDACIONES:
                            listarHistorico(bean);
                            break;
                    }
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
                case Url.ACCION_ADICIONAL_3:
                   
                    break;
                case Url.ACCION_ADICIONAL_4:
                     
                    break;
                 case Url.ACCION_ADICIONAL_5:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(CmFeRipsValidacionBean bean) {
        try {

        } catch (Exception ex) {
        
        }
    }

    public void listar(CmFeRipsValidacionBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCmFeRipsValidacionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmFeRipsValidacionRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }
    
    public void listarHistorico(CmFeRipsValidacionBean bean) {
        try {
            bean.getParamConsultaValidacionHistorico().setCantidadRegistros(
                    getCmFeRipsValidacionHistoricoRemoto().consultarCantidadLista(bean.getParamConsultaValidacionHistorico()));
            bean.setRegistrosValidacionHistorico(getCmFeRipsValidacionHistoricoRemoto().consultarLista(bean.getParamConsultaValidacionHistorico()));

        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    private void crear(CmFeRipsValidacionBean bean) {
        try {

        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    public void ver(CmFeRipsValidacionBean bean) {
        try {          
            bean.setObjeto(getCmFeRipsValidacionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError("Error :" + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    private void editar(CmFeRipsValidacionBean bean) {
        try {
           bean.setObjeto(getCmFeRipsValidacionRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    private void guardar(CmFeRipsValidacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjeto());
            bean.addMensaje("Se creo un registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError("Error: " + CmUtilidades.obtenerErrorStrFormateado(ex));
        }
    }

    private void modificar(CmFeRipsValidacionBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjeto());
            getCmFeRipsValidacionRemoto().actualizar(bean.getObjeto());
            bean.auditoriaGuardar(bean.getObjetoValidacionHistorico());
            bean.getObjetoValidacionHistorico().setCmFeRipsValidacion(new CmFeRipsValidacion(bean.getObjeto().getId()));
            bean.getObjetoValidacionHistorico().setEstado(bean.getObjeto().getEstado());
            getCmFeRipsValidacionHistoricoRemoto().insertar(bean.getObjetoValidacionHistorico());
            bean.addMensaje("Se actualizó el registro de manera exitosa");
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrar(CmFeRipsValidacionBean bean) {
        try {
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}