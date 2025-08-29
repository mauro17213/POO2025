/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeAdjunto;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeAdjuntoProgramaRemoto {
    
     /**
     * Inserta un registro PeAdjunto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(PeAdjunto obj) throws Exception;
    
    List<PeAdjunto> consultarPorIdAfiliadoPrograma(int idAfiliadosProg) throws Exception;
}
