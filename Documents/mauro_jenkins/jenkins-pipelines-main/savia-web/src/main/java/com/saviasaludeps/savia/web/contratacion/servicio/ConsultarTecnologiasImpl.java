/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.servicio;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoDetalleRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoHistoricoRemoto;
import com.saviasaludeps.savia.web.contratacion.bean.ConsultarTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;

/**
 *
 * @author Jose Perez
 */
public class ConsultarTecnologiasImpl extends AccionesBO implements ConsultarTecnologiasIface {
    
    private CntContratoDetalleRemoto getContratoDetalleRemoto() throws Exception {
        return (CntContratoDetalleRemoto) RemotoEJB.getEJBRemoto(("CntContratoDetalleServicio"), CntContratoDetalleRemoto.class.getName());
    }
    
    private CntContratoHistoricoRemoto getContratoHistoricoRemoto() throws Exception {
        return (CntContratoHistoricoRemoto) RemotoEJB.getEJBRemoto(("CntContratoHistoricoServicio"), CntContratoHistoricoRemoto.class.getName());
    }

    @Override
    public void Accion(ConsultarTecnologiasBean bean) {
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
//                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
//                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
//                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    verHistoricos(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(ConsultarTecnologiasBean bean) {
        
    }
    
    private void listar(ConsultarTecnologiasBean bean) {
        try {
            if (bean.isEjecutoBusqueda()) {
                bean.getParamConsulta().setCantidadRegistros(getContratoDetalleRemoto().consultarCantidadListaTecnologias(bean.getParamConsulta()));
                bean.setRegistros(getContratoDetalleRemoto().consultarListaTecnologias(bean.getParamConsulta()));
            } else {
                // inicializamos la tabla con valores vacios
                bean.getParamConsulta().setCantidadRegistros(0);
                bean.setRegistros( new ArrayList<>());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(ConsultarTecnologiasBean bean) {
         try {
            bean.setObjeto(new CntContratoDetalle());           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(ConsultarTecnologiasBean bean) {

    }

    private void ver(ConsultarTecnologiasBean bean) {
        try {
            bean.setObjeto(getContratoDetalleRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verHistoricos(ConsultarTecnologiasBean bean) {
        try {
            bean.getParamConsultaHistorico().setCantidadRegistros(getContratoHistoricoRemoto().consultarCantidadLista(bean.getParamConsultaHistorico()));
            bean.setRegistrosHistorico(getContratoHistoricoRemoto().consultarLista(bean.getParamConsultaHistorico()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
}
