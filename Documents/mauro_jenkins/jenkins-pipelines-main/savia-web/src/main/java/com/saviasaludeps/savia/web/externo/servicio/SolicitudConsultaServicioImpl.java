/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

import com.saviasaludeps.savia.negocio.solicitud.GsSolicitudRemoto;
import com.saviasaludeps.savia.web.externo.bean.SolicitudConsultaBean;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;

/**
 *
 * @author Fabian Coronel
 */
public class SolicitudConsultaServicioImpl extends AccionesBO implements SolicitudConsultaServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private GsSolicitudRemoto getGsSolicitudRemoto() throws Exception {
        return (GsSolicitudRemoto) RemotoEJB.getEJBRemoto("GsSolicitudServicio", GsSolicitudRemoto.class.getName());
    }

    @Override
    public void Accion(SolicitudConsultaBean bean) {
        switch (bean.getAccion()) {
            case SolicitudConsultaBean.ACCION_BUSCAR_SOLICITUDES_SERVICE:
            case Url.ACCION_LISTAR:
                consultarServicios(bean);
                break;
            default:
                break;
        }
    }

    private void consultarServicios(SolicitudConsultaBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() == null) {
                bean.getParamConsulta().setFiltros(new HashMap<>());            }
            bean.getParamConsulta().setParametroConsulta1(bean.getObjeto().getGsAfiliado().getDocumentoTipo());
            bean.getParamConsulta().setParametroConsulta2(bean.getObjeto().getGsAfiliado().getDocumentoNumero());
            bean.getParamConsulta().setParametroConsulta3(bean.getObjeto().getGsAfiliado().getFechaNacimiento());
            bean.getParamConsulta().setCantidadRegistros(getGsSolicitudRemoto().consultarCantidadListaExterna(bean.getParamConsulta()));
            bean.setRegistros(getGsSolicitudRemoto().consultarListaExterna(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void cargaInicial(SolicitudConsultaBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
        } catch (Exception ex) {
            bean.addError("Error carga inicial : " + ex.getMessage());
        }
    }

}
