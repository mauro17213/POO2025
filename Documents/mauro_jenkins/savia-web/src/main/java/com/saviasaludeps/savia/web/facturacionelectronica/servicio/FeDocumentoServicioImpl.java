package com.saviasaludeps.savia.web.facturacionelectronica.servicio;

import com.saviasaludeps.savia.negocio.facturacionelectronica.FeDocumentoRemoto;
import com.saviasaludeps.savia.web.facturacionelectronica.bean.FeDocumentoBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_LISTAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_VER;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeDocumentoServicioImpl extends AccionesBO implements FeDocumentoServicioIface {

    private FeDocumentoRemoto getDocumentoRemoto() throws Exception {
        return (FeDocumentoRemoto) RemotoEJB.getEJBRemoto("FeDocumentoServicio", FeDocumentoRemoto.class.getName());
    }

    @Override
    public void Accion(FeDocumentoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case ACCION_LISTAR:
                    listar(bean);
                    break;
                case ACCION_VER:
                    ver(bean);
                    break;
                default:
                    bean.addError("Acción no implementada");
                    break;
            }
        }
    }


    private void listar(FeDocumentoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getDocumentoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getDocumentoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void cargar(FeDocumentoBean bean) {
        cargaInicial(bean);
    }

    private void ver(FeDocumentoBean bean) {
        try {
            bean.setObjeto(getDocumentoRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Error al cargar detalle: " + e.getMessage());
        }
    }

    @Override
    public void cargaInicial(FeDocumentoBean bean) {
        try {
            bean.setFeDocumentoServicio((FeDocumentoServicioIface) getDocumentoRemoto());
        } catch (Exception ex) {
            Logger.getLogger(FeDocumentoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
