/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeDireccionadoItem;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeDireccionadoItemRemoto {
    
    /**
     * Funcion que permite guardar nuevo registro de DireccionItems
     * @author idbohorquez
     * @creado 12/09/2022
     * @param obj
     * @return int
     * @throws Exception 
     */
    public int insertar(PeDireccionadoItem obj) throws Exception;
    
    /**
     * Funcion que permite guardar fecha de atención en un registro de DireccionItems
     * @author idbohorquez
     * @creado 27/09/2022
     * @param obj
     * @throws Exception 
     */
    void establecerFechaGestion(PeDireccionadoItem obj) throws  Exception;
    
    List<PeDireccionadoItem> getDireccionadoItems(Integer idDireccion) throws Exception;
    
}
