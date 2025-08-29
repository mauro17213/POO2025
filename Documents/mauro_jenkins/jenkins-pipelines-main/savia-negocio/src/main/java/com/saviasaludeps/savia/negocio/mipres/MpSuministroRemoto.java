/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres;

import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MpSuministroRemoto {
    
    /**
     * Método para consultar lista de prescripciones
     * @param id
     * @return
     * @throws Exception
     */
    List<MpEntregaSuministro> consultarListaPorEntrega(int id) throws Exception;
}
