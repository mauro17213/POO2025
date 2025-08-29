/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9Adjunto;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9AdjuntoRemoto {

    int insertar(RefAnexo9Adjunto obj) throws Exception;
    
    List<RefAnexo9Adjunto> consultarPorRefAnexo9(int idRefAnexo9) throws Exception;
    
    void borradoLogico(RefAnexo9Adjunto obj) throws Exception;
    
}
