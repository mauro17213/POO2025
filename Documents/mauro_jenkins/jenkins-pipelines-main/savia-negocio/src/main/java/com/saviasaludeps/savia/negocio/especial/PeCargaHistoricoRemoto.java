/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeCargaHistorico;

/**
 *
 * @author sgiraldo
 */
public interface PeCargaHistoricoRemoto {
    
    /**
     * Consulta el historico de la carga dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    PeCargaHistorico consultar(int id) throws Exception;
    
    /**
     * Inserta el historico de la carga
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PeCargaHistorico obj) throws Exception;
    
}
