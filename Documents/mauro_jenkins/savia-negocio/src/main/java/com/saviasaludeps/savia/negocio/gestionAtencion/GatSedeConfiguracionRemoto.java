/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;

/**
 *
 * @author sgiraldov
 */
public interface GatSedeConfiguracionRemoto {
    
    /**
     * Consulta la configuracion por el id de la sede
     * @param id
     * @return
     * @throws Exception
     */
    GatSedeConfiguracion consultarPorIdSede(int id) throws Exception;

    /**
     * Inserta la configuracion de la sede
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatSedeConfiguracion obj) throws Exception;
    
    /**
     * Actualiza la configuracion de la sede
     * @param obj
     * @throws Exception 
     */
    void actualizar(GatSedeConfiguracion obj) throws Exception;
    
}
