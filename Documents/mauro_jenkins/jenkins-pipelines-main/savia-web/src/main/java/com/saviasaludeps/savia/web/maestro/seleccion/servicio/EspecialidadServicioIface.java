/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.servicio;

import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;

/**
 *
 * @author AlexanderDiaz
 */
public interface EspecialidadServicioIface {
    
    /**
     * Método para cargar inicial del Bean
     * @param bean
     */
    void cargaInicial(SelEspecialidadesBean bean);
}
