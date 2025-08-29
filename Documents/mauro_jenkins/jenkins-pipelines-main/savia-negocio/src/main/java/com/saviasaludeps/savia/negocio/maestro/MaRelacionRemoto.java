/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaRelacion;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface MaRelacionRemoto {
    
   
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<MaRelacion> consultarLista(ParamConsulta paramConsulta) throws Exception;
    

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaRelacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaRelacion obj) throws Exception;
    
    /**
     * Método para consultar por maId
     * @param maId
     * @return
     * @throws Exception 
     */
    public List<MaRelacion> consultarPorMaServicioHabilitacionId(int maId) throws Exception;
    
   
    
}
