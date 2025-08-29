/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaDiagnosticoRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author Jaime Andres Olarte
 */
public class DiagnosticoServicioImpl extends AccionesBO implements DiagnosticoServicioIface {

    private MaDiagnosticoRemoto getMaDiagnosticoRemoto() throws Exception {
        return (MaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("MaDiagnosticoServicio", MaDiagnosticoRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelDiagnosticosBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaDiagnosticoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta()));
            bean.setRegistros(getMaDiagnosticoRemoto().consultarListaBuscador(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
