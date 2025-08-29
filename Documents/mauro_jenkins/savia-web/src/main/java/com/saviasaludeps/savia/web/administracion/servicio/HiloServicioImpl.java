package com.saviasaludeps.savia.web.administracion.servicio;

import com.saviasaludeps.savia.dominio.generico.Hilo;
import com.saviasaludeps.savia.negocio.generico.SingletonHiloRemoto;
import com.saviasaludeps.savia.web.administracion.bean.HiloBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;

public class HiloServicioImpl extends AccionesBO implements HiloServicioIface {

//    private SingletonHiloRemoto getSingletonRemoto() throws Exception {
//        return RemotoEJB.getGenericoSingletonRemoto();
//    }

    @Override
    public void Accion(HiloBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    terminarHilo(bean);
                    break;
            }
        }
    }

    private void listar(HiloBean bean) {
//        try {
//            bean.setRegistros(new ArrayList<>(getSingletonRemoto().getHashReportes().values()));
//        } catch (Exception ex) {
//            bean.addError(ex.toString());
//        }
    }

    private void terminarHilo(HiloBean bean) {
//        try {
//            getSingletonRemoto().setEstadoPorHilo(bean.getObjeto().getId(), Hilo.ESTADO_INACTIVO);
//            bean.addMensaje("Se ha cambiado el estado al hilo será removido de la lista tan pronto finalice");
//        } catch (Exception ex) {
//            bean.addError(ex.toString());
//        }
    }

}
