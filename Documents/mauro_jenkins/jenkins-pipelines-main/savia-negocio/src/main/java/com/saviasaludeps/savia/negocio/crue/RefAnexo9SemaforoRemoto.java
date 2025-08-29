/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9Semaforo;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9SemaforoRemoto {
    
    List<RefAnexo9Semaforo> consularTodos() throws Exception;
}
