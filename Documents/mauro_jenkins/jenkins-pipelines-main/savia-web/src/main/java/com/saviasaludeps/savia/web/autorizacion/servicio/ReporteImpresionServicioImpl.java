/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.negocio.autorizacion.ReporteImpresionRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.ReporteImpresionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Stiven Giraldo
 */
public class ReporteImpresionServicioImpl extends AccionesBO implements ReporteImpresionServicioIface {

    private ReporteImpresionRemoto getReporteImpresionRemoto() throws Exception {
        return (ReporteImpresionRemoto) RemotoEJB.getEJBRemoto("ReporteImpresionServicio", ReporteImpresionRemoto.class.getName());
    }

    @Override
    public void Accion(ReporteImpresionBean bean) {
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
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(ReporteImpresionBean bean) {
        try {
            if (bean.getFechaInicio() == null || bean.getFechaFin() == null) {
                return;
            }
            if (bean.getFechaFin().before(bean.getFechaInicio())) {
                bean.addError("La fecha Hasta no puede ser superior a la fecha Desde.");
            } else {
                if (bean.getFechaInicio() != null && bean.getFechaFin() != null) {
                    long difMilesimas = Math.abs(bean.getFechaInicio().getTime() - bean.getFechaFin().getTime());
                    if (TimeUnit.DAYS.convert(difMilesimas, TimeUnit.MILLISECONDS) > 60) {
                        bean.addError("Solo se permiten consultas de un rango máximo a 60 días");
                        return;
                    }
                }
                bean.setRegistros(getReporteImpresionRemoto()
                        .consultarImpresionesAnexo4PorFecha(bean.getFechaInicio(), bean.getFechaFin()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo listando, favor comunicarse con el administrador");
        }
    }

}
