/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;

/**
 *
 * @author sgiraldov
 */
public interface MaestroAccionRemoto {
    
    /**
     * Consulta por id
     * @param id
     * @return
     * @throws Exception 
     */
    MaestroAccion consultar(int id) throws Exception;
    
}
