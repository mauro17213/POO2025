/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Zona;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4ZonaRemoto {
    
    /**
     * Devuelve la cantidad de listar
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta la lista al listar
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Zona> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Inserta la zona
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(AuAnexo4Zona obj) throws Exception;
    
    /**
     * Elimina dada la zona
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Zona eliminar(int id) throws Exception;
    
    /**
     * Consulta dado el id
     * @param id
     * @return
     * @throws Exception 
     */
    AuAnexo4Zona consultar(int id) throws Exception;
    
    /**
     * Actualiza dada la zona
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo4Zona obj) throws Exception;   
    
    /**
     * Valida si la zona de origen ya se inserto
     * @param idUbicacion
     * @return
     * @throws Exception 
     */
    boolean validarZona(int idUbicacion) throws Exception;
    
}
