/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9Diagnostico;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9DiagnosticoRemoto {
    
    int insertar(RefAnexo9Diagnostico obj) throws Exception;
    
    void actualizar(RefAnexo9Diagnostico obj) throws Exception;
    
    List<RefAnexo9Diagnostico> consultarPorRefAnexo9(int idRefAnexo9) throws Exception;
    
    RefAnexo9Diagnostico eliminar(int id) throws Exception;
}
