/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuCotizacionItemRemoto {
    
    /**
     * Inserta cotizacion
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuCotizacionItem obj) throws Exception;
    
    int insertarItem(AuCotizacionItem obj) throws Exception;
    
    /**
     * Consulta por el id del anexo3Item
     * @param idAnexo3Item
     * @return
     * @throws Exception 
     */
    AuCotizacionItem consultarPorIdAnexo3(int idAnexo3Item) throws Exception;
    
}
