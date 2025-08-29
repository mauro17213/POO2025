/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.servicio;

import com.saviasaludeps.savia.negocio.financiera.FinPostulacionRemoto;
import com.saviasaludeps.savia.web.financiera.bean.FinPostulacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;

/**
 *
 * @author jeperez
 */
public class FinPostulacionServicioImpl extends AccionesBO implements FinPostulacionServicioIface {

    public static final int ID_PRESTADOR_SAVIA = 1;
    
    private FinPostulacionRemoto getFinPostulacionRemoto() throws Exception {
        return (FinPostulacionRemoto) RemotoEJB.getEJBRemoto("FinPostulacionServicio", FinPostulacionRemoto.class.getName());
    }

    @Override
    public void Accion(FinPostulacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                   listar(bean);
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
                case Url.ACCION_ADICIONAL_6:
                    break;
            }
        }
    }

    @Override
    public void cargasInicial(FinPostulacionBean bean) {
        try {
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la carga inicial, favor contactar al administrador");
        }
    }

    private void listar(FinPostulacionBean bean) {
        try {
            
            if (ID_PRESTADOR_SAVIA !=  bean.getConexion().getEmpresa().getId() ) {
                int idPrestador = bean.getConexion().getEmpresa().getCntPrestador().getId();
                if (idPrestador > 0) {
                    bean.getParamConsulta().setParametroConsulta3(idPrestador);
                } else {
                    bean.addError("Error la empresa asociada el usuari no tiene asociado un prestador");
                }
            }
            
            bean.getParamConsulta().setCantidadRegistros(getFinPostulacionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getFinPostulacionRemoto().consultarLista(bean.getParamConsulta()));

        } catch (Exception e) {
            bean.addError("Hubo un fallo al listar, favor contactar al administrador");
        }
    }

   

   
}
