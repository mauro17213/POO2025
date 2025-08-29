/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9Estado;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9EstadoRemoto {
    
    int insertar(RefAnexo9Estado obj) throws Exception;
    
    RefAnexo9Estado consultarPorRefAnexo9(int id) throws Exception;
    
}
