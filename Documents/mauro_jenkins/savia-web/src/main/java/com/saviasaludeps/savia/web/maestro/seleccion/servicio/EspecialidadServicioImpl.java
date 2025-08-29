/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.negocio.maestro.MaEspecialidadRemoto;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;

/**
 *
 * @author AlexanderDiaz
 */
public class EspecialidadServicioImpl  extends AccionesBO implements EspecialidadServicioIface{
    private MaEspecialidadRemoto getMaEspecialidadRemoto() throws Exception {
        return (MaEspecialidadRemoto) RemotoEJB.getEJBRemoto("MaEspecialidadServicio", MaEspecialidadRemoto.class.getName());
    }
    
    @Override
    public void cargaInicial(SelEspecialidadesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMaEspecialidadRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMaEspecialidadRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    } 
}
