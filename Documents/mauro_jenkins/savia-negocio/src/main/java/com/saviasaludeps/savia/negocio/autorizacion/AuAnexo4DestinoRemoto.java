/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Destino;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4DestinoRemoto {
    
    /**
     * Inserta un destino
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo4Destino obj) throws Exception;
    
    /**
     * Consulta una lista de destinos dada la zona
     * @param idZona
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Destino> consultarListaPorIdZona(int idZona) throws Exception;
    
    /**
     * Elimina dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Destino eliminar(int id) throws Exception;
    
    /**
     * Actualiza el destino
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo4Destino obj) throws Exception;
    
    /**
     * Consulta el destino dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Destino consultar(int id) throws Exception;
    
}
