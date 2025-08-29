/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.crue;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2DatoAtencion;

/**
 *
 * @author AlexanderDiaz
 */
public interface AuAnexo2DatoAtencionRemoto {
    
    
    /**
     * Consulta un registro de RefAnexos9 por id
     * @param id
     * @return AuAnexo2
     * @throws Exception 
     */
    AuAnexo2DatoAtencion consultar(int id) throws Exception;
    
    /**
     * Consulta un registro por RefAnexos9
     * @param AuAnexo2Id
     * @return
     * @throws Exception 
     */
    public AuAnexo2DatoAtencion consultarPorAuAnexo2(int AuAnexo2Id) throws Exception;

    public int insertar(AuAnexo2DatoAtencion obj) throws Exception;
    
    void actualizar(AuAnexo2DatoAtencion obj) throws Exception;
}
