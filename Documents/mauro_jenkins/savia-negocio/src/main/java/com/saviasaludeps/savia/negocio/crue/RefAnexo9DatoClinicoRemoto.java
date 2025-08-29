/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.RefAnexo9DatoClinico;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface RefAnexo9DatoClinicoRemoto {
    
    
    /**
     * Consulta un registro de RefAnexos9 por id
     * @param id
     * @return RefAnexo9
     * @throws Exception 
     */
    RefAnexo9DatoClinico consultar(int id) throws Exception;
    
    /**
     * Consulta un registro por RefAnexos9
     * @param RefAnexo9Id
     * @return
     * @throws Exception 
     */
    public RefAnexo9DatoClinico consultarPorRefAnexo9(int RefAnexo9Id) throws Exception;

    public int insertar(RefAnexo9DatoClinico obj) throws Exception;
    
    void actualizar(RefAnexo9DatoClinico obj) throws Exception;
}
